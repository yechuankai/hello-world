package com.wms.services.strategy.allocate.util;

import org.slf4j.Logger;

import com.wms.async.manager.AsyncManager;
import com.wms.async.manager.factory.AsyncFactory;
import com.wms.common.core.domain.IBaseEntity;
import com.wms.common.enums.OperatorTypeEnum;
import com.wms.common.utils.key.KeyUtils;
import com.wms.vo.OperLogVO;

public class AllocateLog {
	
	/**
	 * 操作日志记录
	 * 
	 * @param log 记录原始类
	 * @param message 日志记录
	 * @param allocate 分配对象
	 * @return 任务task
	 */
	public static void log(Logger log, String message, Long allocateBatchId, IBaseEntity allocate) {
		log.info(message);
		
		OperLogVO operLog = new OperLogVO("Allocate", String.valueOf(allocateBatchId), OperatorTypeEnum.Add.getCode(), "allocate", allocate.getUpdateBy());
		operLog.setOperParam(message);
		operLog.setCompanyId(allocate.getCompanyId());
		operLog.setWarehouseId(allocate.getWarehouseId());
		operLog.setOperLogId(KeyUtils.getUID());
		AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
	}
	
	
	
}
