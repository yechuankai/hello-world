package com.wms.task;

import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wms.async.manager.AsyncManager;
import com.wms.common.utils.DateUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.entity.auto.InventoryOnhandSnapshotTEntity;
import com.wms.entity.auto.WarehouseActiveTEntity;
import com.wms.services.inventory.IInventorySnapshotService;
import com.wms.services.sys.IWarehouseActiveService;

/**
 * 同步库存
 * @author yechuankai.chnet
 *
 */
@RestController
@RequestMapping("/services/inner/schedule")
public class InventorySnapshot {
	private final static Logger log = LoggerFactory.getLogger(InventorySnapshot.class);

	@Scheduled(cron = "0 0 23 * * ?") //每天晚上23点开始触发
	@RequestMapping("/inventorySynchronize")
	public void inventorySynchronize() {
		final String now = DateUtils.getDate();
		//获取所有仓库
		IWarehouseActiveService warehouseService = SpringUtils.getBean(IWarehouseActiveService.class);
		List<WarehouseActiveTEntity> wareList = warehouseService.findAll();
		wareList.forEach(w -> {
			final long warehouseId = w.getWarehouseId();
			final long companyId = w.getCompanyId();
			AsyncManager.me().execute(new TimerTask() {
				@Override
				public void run() {
					log.info("companyid={} warehouseid={} day={} inventory synchronize start", warehouseId, companyId, now);
					IInventorySnapshotService snapshot = SpringUtils.getBean(IInventorySnapshotService.class);
					snapshot.synchronize(InventoryOnhandSnapshotTEntity.builder()
											.companyId(companyId)
											.warehouseId(warehouseId)
											.day(now)
											.build());
					log.info("companyid={} warehouseid={} day={} inventory synchronize end", warehouseId, companyId, now);
				}
			});
			
		});
	}
	
	@Scheduled(cron = "0 0 23 * * ?") //每天晚上23点开始触发
	@RequestMapping("/clean")
	public void clean() {
		//获取所有仓库
		IWarehouseActiveService warehouseService = SpringUtils.getBean(IWarehouseActiveService.class);
		List<WarehouseActiveTEntity> wareList = warehouseService.findAll();
		wareList.forEach(w -> {
			final long warehouseId = w.getWarehouseId();
			final long companyId = w.getCompanyId();
			AsyncManager.me().execute(new TimerTask() {
				@Override
				public void run() {
					log.info("companyid={} warehouseid={} inventory clean start", warehouseId, companyId);
					IInventorySnapshotService snapshot = SpringUtils.getBean(IInventorySnapshotService.class);
					snapshot.clean(InventoryOnhandSnapshotTEntity.builder()
											.companyId(companyId)
											.warehouseId(warehouseId)
											.build());
					log.info("companyid={} warehouseid={} inventory clean end", warehouseId, companyId);
				}
			});
			
		});
	}
	
}
