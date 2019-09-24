package com.wms.async.manager.factory;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wms.async.manager.AsyncManager;
import com.wms.common.core.domain.mongodb.RestContent;
import com.wms.common.enums.MonitorTypeEnum;
import com.wms.common.utils.ServletUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.common.utils.mongodb.MongoUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.dao.auto.ISysMonitorLogTDao;
import com.wms.dao.auto.ISysOperLogTDao;
import com.wms.dao.example.SysMonitorLogTExample;
import com.wms.entity.auto.SysMonitorLogTEntity;
import com.wms.entity.auto.SysOperLogTEntity;

/**
 * 异步工厂（产生任务用）
 *
 */
public class AsyncFactory {
	private static final Logger log = LoggerFactory.getLogger(AsyncFactory.class);

	
	private static void monitortoMongodb(SysMonitorLogTEntity monitor) {
		if (monitor.getRequest() != null) {
			RestContent content = new RestContent(monitor.getUrl(), monitor.getRequest(), MonitorTypeEnum.Request.getCode(), monitor.getIp());
			MongoUtils.insert(content);
			monitor.setRequest(content.getId());
		}
		if (monitor.getResponse() != null) {
			RestContent content = new RestContent(monitor.getUrl(), monitor.getResponse(), MonitorTypeEnum.Response.getCode(), monitor.getIp());
			MongoUtils.insert(content);
			monitor.setResponse(content.getId());
		}
		if (monitor.getError() != null) {
			RestContent content = new RestContent(monitor.getUrl(), monitor.getError(), MonitorTypeEnum.Error.getCode(), monitor.getIp());
			MongoUtils.insert(content);
			monitor.setError(content.getId());
		}
	}
	
	/**
	 * 操作日志记录
	 * 
	 * @param operLog 操作日志信息
	 * @return 任务task
	 */
	public static TimerTask recordOper(final SysOperLogTEntity operLog) {
		return new TimerTask() {
			@Override
			public void run() {
				if (operLog.getOperLogId() == null)
					operLog.setOperLogId(KeyUtils.getUID());
				SpringUtils.getBean(ISysOperLogTDao.class).insertSelective(operLog);
			}
		};
	}
	
	/**
	 * 操作日志记录
	 * 
	 * @return 任务task
	 */
	public static TimerTask recordMonitor(final SysMonitorLogTEntity monitorLog, Boolean update, int retryCount) {
		return new TimerTask() {
			@Override
			public void run() {
				if (monitorLog.getMonitorLogId() == null || monitorLog.getMonitorLogId() == 0) {
					Long uuid = KeyUtils.getUID();
					monitorLog.setMonitorLogId(uuid);
				}
				monitortoMongodb(monitorLog);
				if (update) {
					SysMonitorLogTExample example = new SysMonitorLogTExample();
					example.createCriteria()
					.andMonitorLogIdEqualTo(monitorLog.getMonitorLogId());
					int rowcount = SpringUtils.getBean(ISysMonitorLogTDao.class).updateByExampleSelective(monitorLog, example);
					if (rowcount == 0 && retryCount < 3) { //更新失败时继续调用，由于执行速度较快，很可能未插入则执行了更新
						try {
							Thread.sleep(10);
							final int _retryCount = retryCount + 1;
							AsyncManager.me().execute(AsyncFactory.recordMonitor(monitorLog, Boolean.TRUE, _retryCount));
						} catch (InterruptedException e) {}
					}
				}else {
					SpringUtils.getBean(ISysMonitorLogTDao.class).insertSelective(monitorLog);
				}
			}
		};
	}
}
