package com.wms.services.inventory.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.dao.auto.IInventoryOnhandSnapshotTDao;
import com.wms.dao.example.InventoryOnhandSnapshotTExample;
import com.wms.entity.auto.InventoryOnhandSnapshotTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.services.inventory.IInventoryService;
import com.wms.services.inventory.IInventorySnapshotService;

@Service
public class InventorySnapshotServiceImpl implements IInventorySnapshotService {

	@Autowired
	private IInventoryOnhandSnapshotTDao inventorySnapshotDao;
	
	@Autowired
	private IInventoryService inventoryService;
	
	@Override
	public List<InventoryOnhandSnapshotTEntity> findByOwner(InventoryOnhandSnapshotTEntity inventory)
			throws BusinessServiceException {
		InventoryOnhandSnapshotTExample example = new InventoryOnhandSnapshotTExample();
		InventoryOnhandSnapshotTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(inventory.getWarehouseId())
		.andCompanyIdEqualTo(inventory.getCompanyId())
		.andDayEqualTo(inventory.getDay())
		.andQuantityOnhandGreaterThan(BigDecimal.ZERO);
		
		if (inventory.getOwnerId() == null && StringUtils.isEmpty(inventory.getOwnerCode()))
			return Lists.newArrayList();
		
		if (inventory.getOwnerId() != null)
			criteria.andOwnerIdEqualTo(inventory.getOwnerId());
		else
			criteria.andOwnerCodeEqualTo(inventory.getOwnerCode());
		
		List<InventoryOnhandSnapshotTEntity> selectInventory = inventorySnapshotDao.selectByExample(example);
		return selectInventory;
	}
	
	@Override
	public Boolean add(List<InventoryOnhandSnapshotTEntity> inventory) throws BusinessServiceException {
		inventory.forEach(i -> {
			i.setCreateTime(new Date());
			i.setUpdateTime(new Date());
			add(i);
		});
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(InventoryOnhandSnapshotTEntity inventory) throws BusinessServiceException {
		inventorySnapshotDao.insertSelective(inventory);
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean delete(InventoryOnhandSnapshotTEntity inventory) throws BusinessServiceException {
		InventoryOnhandSnapshotTExample example = new InventoryOnhandSnapshotTExample();
		InventoryOnhandSnapshotTExample.Criteria criteria = example.createCriteria();
		
		//按快照日期删除
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andDayEqualTo(inventory.getDay());
		
		if (inventory.getWarehouseId() != null) {
			criteria.andWarehouseIdEqualTo(inventory.getWarehouseId());
		}
		
		if (inventory.getCompanyId() != null) {
			criteria.andCompanyIdEqualTo(inventory.getCompanyId());
		}
		
		InventoryOnhandSnapshotTEntity update = InventoryOnhandSnapshotTEntity.builder()
				.delFlag(YesNoEnum.Yes.getCode())
				.updateTime(new Date())
				.build();
		inventorySnapshotDao.updateByExampleSelective(update, example);
		return Boolean.TRUE;
	}

	@Override
	public Boolean synchronize(InventoryOnhandSnapshotTEntity inventory) throws BusinessServiceException {
		if (StringUtils.isEmpty(inventory.getDay()))
			throw new BusinessServiceException("day is null.");
		
		delete(inventory);
		//获取所有库存，大于0
		List<InventoryOnhandTEntity> list = inventoryService.findAll(InventoryOnhandTEntity.builder()
									.warehouseId(inventory.getWarehouseId())
									.companyId(inventory.getCompanyId())
									.build());
		if (CollectionUtils.isEmpty(list))
			return Boolean.FALSE;
		
		List<InventoryOnhandSnapshotTEntity> snapshotList = Lists.newArrayList();
		list.forEach(i -> {
			InventoryOnhandSnapshotTEntity sanpshot = new InventoryOnhandSnapshotTEntity();
			BeanUtils.copyBeanProp(sanpshot, i);
			sanpshot.setDay(inventory.getDay());
			snapshotList.add(sanpshot);
		});
		add(snapshotList);
		return Boolean.TRUE;
	}

	@Override
	public Boolean clean(InventoryOnhandSnapshotTEntity inventory) throws BusinessServiceException {
		InventoryOnhandSnapshotTExample example = new InventoryOnhandSnapshotTExample();
		InventoryOnhandSnapshotTExample.Criteria criteria = example.createCriteria();
		
		//快照半年前的数据删除
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCreateTimeLessThan(DateUtils.addMonths(new Date(), -6));
		
		if (inventory.getWarehouseId() != null) {
			criteria.andWarehouseIdEqualTo(inventory.getWarehouseId());
		}
		
		if (inventory.getCompanyId() != null) {
			criteria.andCompanyIdEqualTo(inventory.getCompanyId());
		}
		
		List<InventoryOnhandSnapshotTEntity> list = inventorySnapshotDao.selectByExample(example);
		if (CollectionUtils.isEmpty(list))
			return Boolean.TRUE;
		
		Set<String> days = list.stream().map(InventoryOnhandSnapshotTEntity::getDay).collect(Collectors.toSet());
		days.forEach(d -> {
			delete(InventoryOnhandSnapshotTEntity.builder()
									.warehouseId(inventory.getWarehouseId())
									.companyId(inventory.getCompanyId())
									.day(d)
									.build());
		});
		
		return Boolean.TRUE;
	}

}
