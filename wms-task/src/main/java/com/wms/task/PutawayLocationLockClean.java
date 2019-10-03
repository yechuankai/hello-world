package com.wms.task;

import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wms.async.manager.AsyncManager;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.entity.auto.PutawayLocationLockTEntity;
import com.wms.entity.auto.WarehouseActiveTEntity;
import com.wms.services.inventory.IPutawayLocationLockService;
import com.wms.services.sys.IWarehouseActiveService;

/**
 * 上架锁定库位到期清除
 * @author yechuankai.chnet
 *
 */
@RestController
@RequestMapping("/services/inner/schedule")
public class PutawayLocationLockClean {
	
	private final static Logger log = LoggerFactory.getLogger(PutawayLocationLockClean.class);

	@Scheduled(initialDelay = (1000 * 60), fixedDelay = (1000 * 60 * 10)) //上一次执行完毕时间点之后10分钟执行一次
	@RequestMapping("/putawayLocationLockClean")
	public void putawayLocationLockClean() {
		//获取所有仓库
		IWarehouseActiveService warehouseService = SpringUtils.getBean(IWarehouseActiveService.class);
		List<WarehouseActiveTEntity> wareList = warehouseService.findAll();
		wareList.forEach(w -> {
			final long warehouseId = w.getWarehouseId();
			final long companyId = w.getCompanyId();
			AsyncManager.me().execute(new TimerTask() {
				@Override
				public void run() {
					log.debug("companyid={} warehouseid={} putawayLocationLock clean start", warehouseId, companyId);
					IPutawayLocationLockService lockService = SpringUtils.getBean(IPutawayLocationLockService.class);
					lockService.clean(PutawayLocationLockTEntity.builder()
											.companyId(companyId)
											.warehouseId(warehouseId)
											.build());
					log.debug("companyid={} warehouseid={} putawayLocationLock clean end", warehouseId, companyId);
				}
			});
			
		});
	}
	
}
