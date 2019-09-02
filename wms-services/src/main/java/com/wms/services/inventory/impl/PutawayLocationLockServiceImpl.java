package com.wms.services.inventory.impl;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wms.async.manager.AsyncManager;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.key.KeyUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.dao.auto.IPutawayLocationLockTDao;
import com.wms.dao.example.PutawayLocationLockTExample;
import com.wms.entity.auto.PutawayLocationLockTEntity;
import com.wms.entity.auto.SysWarehousesTEntity;
import com.wms.entity.auto.WarehouseActiveTEntity;
import com.wms.services.inventory.IPutawayLocationLockService;
import com.wms.services.sys.ISysWarehouseService;
import com.wms.services.sys.IWarehouseActiveService;

/**
 * 获取锁定的上架库位
 * @author yechuankai.chnet
 *
 */
@Service
public class PutawayLocationLockServiceImpl implements IPutawayLocationLockService {
	
	@Autowired
	private IPutawayLocationLockTDao lockDao;

	@Override
	public List<PutawayLocationLockTEntity> find(PutawayLocationLockTEntity lock) throws BusinessServiceException {
		PutawayLocationLockTExample example = new PutawayLocationLockTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(lock.getWarehouseId())
		.andCompanyIdEqualTo(lock.getCompanyId())
		.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<PutawayLocationLockTEntity> list = lockDao.selectByExample(example);
		if (list == null)
			return Lists.newArrayList();
		
		return list;
	}
	
	@Override
	public Boolean add(PutawayLocationLockTEntity lock) throws BusinessServiceException {
		lock.setPutawayLocationLockId(KeyUtils.getUID());
		int rowcount = lockDao.insertSelective(lock);
		if (rowcount == 0)
			throw new BusinessServiceException("record add error.");
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean clean(PutawayLocationLockTEntity lock) throws BusinessServiceException {
		PutawayLocationLockTExample example = new PutawayLocationLockTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(lock.getWarehouseId())
		.andCompanyIdEqualTo(lock.getCompanyId())
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		//6个小时前的记录自动释放
		.andCreateTimeLessThan(DateUtils.addHours(new Date(), -6));
		
		List<PutawayLocationLockTEntity> list = lockDao.selectByExample(example);
		if (CollectionUtils.isEmpty(list))
			return Boolean.TRUE;
		
		for (PutawayLocationLockTEntity lockObj : list) {
			PutawayLocationLockTExample updateExample = new PutawayLocationLockTExample();
			updateExample.createCriteria()
			.andWarehouseIdEqualTo(lock.getWarehouseId())
			.andCompanyIdEqualTo(lock.getCompanyId())
			.andPutawayLocationLockIdEqualTo(lockObj.getPutawayLocationLockId());
			
			PutawayLocationLockTEntity updateLock = new PutawayLocationLockTEntity();
			updateLock.setDelFlag(YesNoEnum.Yes.getCode());
			lockDao.updateWithVersionByExampleSelective(lockObj.getUpdateVersion(), updateLock, updateExample);
		}
		
		return Boolean.TRUE;
	}
	
	@Scheduled(initialDelay = (1000 * 60), fixedDelay = (1000 * 60 * 10)) //上一次执行完毕时间点之后10分钟执行一次
	public void scheduleClean() {
		//获取所有仓库
		IWarehouseActiveService warehouseService = SpringUtils.getBean(IWarehouseActiveService.class);
		List<WarehouseActiveTEntity> wareList = warehouseService.findAll();
		wareList.forEach(w -> {
			final long warehouseId = w.getWarehouseId();
			final long companyId = w.getCompanyId();
			AsyncManager.me().execute(new TimerTask() {
				@Override
				public void run() {
					IPutawayLocationLockService lockService = SpringUtils.getBean(IPutawayLocationLockService.class);
					lockService.clean(PutawayLocationLockTEntity.builder()
											.companyId(companyId)
											.warehouseId(warehouseId)
											.build());
				}
			});
			
		});
	}

}
