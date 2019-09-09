package com.wms.services.strategy.putaway.impl;

import com.google.common.collect.Lists;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.dao.auto.IInventoryOnhandTDao;
import com.wms.dao.example.InventoryOnhandTExample;
import com.wms.entity.auto.*;
import com.wms.services.base.ILocationService;
import com.wms.services.base.ISkuService;
import com.wms.services.inventory.IPutawayLocationLockService;
import com.wms.services.strategy.putaway.IPutawayService;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.PutawayVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class CommonPutawayServiceImpl implements IPutawayService{
	
	@Autowired
	private ILocationService locationService;
	@Autowired
	private ISkuService skuService;
	@Autowired
	private IInventoryOnhandTDao inventoryDao;
	@Autowired
	private IPutawayLocationLockService putawayLocationLockService;
	
	
	protected ZoneTEntity getZone(InventoryOnhandVO inventory) {
		LocationTEntity loc = locationService.find(LocationTEntity.builder()
				.warehouseId(inventory.getWarehouseId())
				.companyId(inventory.getCompanyId())
				.zoneCode(inventory.getLocationCode())
				.build());
		if (loc == null) 
			return null;
		
		ZoneTEntity zone = new ZoneTEntity();
		BeanUtils.copyBeanProp(zone, loc, Boolean.FALSE);
		return zone;
	}
	
	protected SkuTEntity getSku(InventoryOnhandVO inventory) {
		SkuTEntity sku = skuService.find(SkuTEntity.builder()
				.warehouseId(inventory.getWarehouseId())
				.companyId(inventory.getCompanyId())
				.skuCode(inventory.getSkuCode())
				.skuId(inventory.getSkuId())
				.ownerCode(inventory.getOwnerCode())
				.build());
		return sku;
	}
	
	
	/**
	 * 根据排序规则获取优先级最高库位
	 * @param strategy
	 * @param list
	 * @return
	 */
	protected LocationTEntity getFirstLoc(PutawayStrategyDetailTEntity strategy, List<LocationTEntity> list) {
		if (CollectionUtils.isEmpty(list))
			return null;
		
		//获取排序规则是否为库位顺序
		boolean locationLogicalFlag = LocationTEntity.Column.locationLogical.getJavaProperty().equalsIgnoreCase(strategy.getSortRule());
		LocationTEntity loc = list.stream().min(new Comparator<LocationTEntity>() {
			@Override
			public int compare(LocationTEntity o1, LocationTEntity o2) {
				if (locationLogicalFlag) {
					return o1.getLocationLogical().compareTo(o2.getLocationLogical());
				}else {
					return o1.getLocationCode().compareTo(o2.getLocationCode());
				}
			}
		}).get();
		
		return loc;
	}
	
	/**
	 * 查询库区下空库位
	 * @param zone
	 * @return
	 */
	protected LocationTEntity findEmptyLocByZone(PutawayStrategyDetailTEntity strategy) {
		if(StringUtils.isEmpty(strategy.getToZoneCode())) 
			return null;
		
		//根据库区获取库位
		List<LocationTEntity> locs = locationService.findByZone(LocationTEntity.builder()
																	.warehouseId(strategy.getWarehouseId())
																	.companyId(strategy.getCompanyId())
																	.zoneCode(strategy.getToZoneCode())
																	.build());
		if (CollectionUtils.isEmpty(locs))
			return null;
		
		List<Long> locIds = locs.stream().map(LocationTEntity::getLocationId).collect(Collectors.toList());
		//转换二维数组
		List<List<Long>> mulitLocIds = Lists.newArrayList();
		int size = locs.size();
		int max = 999;
		int f = (int) Math.ceil((double)size / (double)max);
		for (int i = 0; i < f; i++) {
			int toIndex = (i * max) + max;
			if (toIndex > size)
				toIndex = size;
			
			mulitLocIds.add(locIds.subList(i * max, toIndex));
		}
		
		//根据二维数组查询库存
		List<InventoryOnhandTEntity> inventoryList = Lists.newArrayList();
		for (List<Long> ids : mulitLocIds) {
			InventoryOnhandTExample example = new InventoryOnhandTExample();
			InventoryOnhandTExample.Criteria criteria = example.createCriteria();
			criteria
			.andDelFlagEqualTo(YesNoEnum.No.getCode())
			.andWarehouseIdEqualTo(strategy.getWarehouseId())
			.andCompanyIdEqualTo(strategy.getCompanyId())
			.andQuantityOnhandGreaterThan(BigDecimal.ZERO)
			.andLocationIdIn(ids);
			List<InventoryOnhandTEntity> inv = inventoryDao.selectByExample(example);
			if (CollectionUtils.isNotEmpty(inv))
				inventoryList.addAll(inv);
		}
		//获取库存中的库位
		Set<Long> inventoryLocSet = inventoryList.stream().map(InventoryOnhandTEntity::getLocationId).collect(Collectors.toSet());
		
		//排除存在库存的库位
		List<LocationTEntity> emptyLocList = locs.stream().filter(v -> !inventoryLocSet.contains(v.getLocationId())).collect(Collectors.toList());
		
		//排除锁定的库位
		List<PutawayLocationLockTEntity> lockLocs = putawayLocationLockService.find(PutawayLocationLockTEntity.builder()
																					.warehouseId(strategy.getWarehouseId())
																					.companyId(strategy.getCompanyId())
																					.build());
		Set<String> lockLocSet = lockLocs.stream().map(PutawayLocationLockTEntity::getLocationCode).collect(Collectors.toSet());
		emptyLocList = emptyLocList.stream().filter(v -> !lockLocSet.contains(v.getLocationCode())).collect(Collectors.toList());
		
		//排序后获取库存
		LocationTEntity emptyLoc = getFirstLoc(strategy, emptyLocList);
		
		return emptyLoc;
	}
	
	/**
	 * 查询库区下空库位
	 * @param zone
	 * @return
	 */
	protected LocationTEntity findEmptyLocByZone(PutawayStrategyDetailTEntity strategy, String zoneCode) {
		if(StringUtils.isEmpty(zoneCode))
			return null;
		
		strategy.setToZoneCode(zoneCode);
		return findEmptyLocByZone(strategy);
	}
	
	/**
	 * 锁定库位
	 * @param putaway
	 * @return
	 */
	protected Boolean lockLocation(PutawayVO putaway) {
		PutawayLocationLockTEntity lock = PutawayLocationLockTEntity.builder()
											.warehouseId(putaway.getWarehouseId())
											.companyId(putaway.getCompanyId())
											.locationCode(putaway.getToLocationCode())
											.createBy(putaway.getCreateBy())
											.updateBy(putaway.getCreateBy())
											.createTime(new Date())
											.updateTime(new Date())
											.build();
		lock.setContainerNumber(putaway.getContainerNumber());
		lock.setLpnNumber(putaway.getLpnNumber());
		
		InventoryOnhandVO onhand = putaway.getInventoryOnhand();
		if (onhand != null) {
			
			lock.setSkuCode(onhand.getSkuCode());
		}
		boolean addFlag = putawayLocationLockService.add(lock);
		return addFlag;
	}
	
	
}
