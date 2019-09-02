package com.wms.services.strategy.allocate.impl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.wms.common.constants.ConfigConstants;
import com.wms.common.enums.allocate.AllocateRuleFIFOEnum;
import com.wms.common.enums.allocate.AllocateRuleSortEnum;
import com.wms.common.enums.allocate.AllocateStrategyTypeEnum;
import com.wms.common.enums.allocate.AllocateUOMEnum;
import com.wms.common.utils.MessageUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.cache.ConfigUtils;
import com.wms.dao.query.IAllocateQueryDao;
import com.wms.entity.auto.AllocateStrategyDetailTEntity;
import com.wms.entity.auto.AllocateTEntity;
import com.wms.entity.auto.AreaDetailTEntity;
import com.wms.entity.auto.AreaTEntity;
import com.wms.services.base.IAreaDetailService;
import com.wms.services.core.impl.AllocateCoreServiceImpl;
import com.wms.services.outbound.IAllocateService;
import com.wms.services.strategy.allocate.IAllocateStrategyService;
import com.wms.services.strategy.allocate.util.AllocateLog;
import com.wms.services.strategy.allocate.util.InventorySort;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.allocate.InventoryAllocateDetailVO;
import com.wms.vo.allocate.AllocateInventoryVO;

public abstract class CommonAllocateServiceImpl implements IAllocateStrategyService{
	
	private static Logger log = LoggerFactory.getLogger(CommonAllocateServiceImpl.class);
	
	@Autowired
	private IAllocateQueryDao allocateDao;
	
	@Autowired
	private IAllocateService allocateService;
	
	@Autowired
	private IAreaDetailService areaService;
	
	
	protected List<InventoryOnhandVO> getHardInventory(InventoryAllocateDetailVO detail) {
		
		boolean allocateMonitor = ConfigUtils.getBooleanValue(detail.getCompanyId(), detail.getWarehouseId(), ConfigConstants.CONFIG_ALLOCATE_MONITOR);
		
		if (allocateMonitor) {
			String message = MessageUtils.message("******** allocate line {0} process {1}::batch_id={2}, query inventory start ", detail.getLineNumber(), detail.getOutboundDetailId(), detail.getAllocateBatchId());
			AllocateLog.log(log, message, detail.getAllocateBatchId(), detail);
		}
		
		//计时器
		StopWatch watch = StopWatch.createStarted();
				
		AllocateStrategyDetailTEntity strategy = detail.getStrategy();
		
		AllocateInventoryVO onhand = new AllocateInventoryVO();
		BeanUtils.copyBeanProp(onhand, detail, Boolean.FALSE);
		
		//按区域查询
		if (strategy.getAreaId() != null && strategy.getAreaId() != 0) {
			List<AreaDetailTEntity> areaDetail = areaService.findByArea(AreaTEntity.builder()
									.warehouseId(detail.getWarehouseId())
									.companyId(detail.getCompanyId())
									.areaId(detail.getStrategy().getAreaId())
									.build());
			Set<Long> locations = areaDetail.stream().map(AreaDetailTEntity::getLocationId).collect(Collectors.toSet());
			if (CollectionUtils.isEmpty(locations))
				return Lists.newArrayList();
			
			onhand.setLocations(locations);
			
			if (allocateMonitor) {
				String message = MessageUtils.message("******** allocate line {0} process {1}::batch_id={2}, query inventory by area_id={3}, location_count={4} ", detail.getLineNumber(), detail.getOutboundDetailId(), detail.getAllocateBatchId(), strategy.getAreaId(), locations.size());
				AllocateLog.log(log, message, detail.getAllocateBatchId(), detail);
			}
		}
		List<InventoryOnhandVO> list = allocateDao.availabelInventory(onhand);
		
		if (allocateMonitor) {
			String message = MessageUtils.message("******** allocate line {0} process {1}::batch_id={2}, query inventory {3} time {4} end ", detail.getLineNumber(), detail.getOutboundDetailId(), detail.getAllocateBatchId(), list.size(), watch.getTime());
			AllocateLog.log(log, message, detail.getAllocateBatchId(), detail);
		}
		
		return list;
	}
	
	
	protected List<InventoryOnhandVO> getSoftInventory(InventoryAllocateDetailVO detail) {
		boolean allocateMonitor = ConfigUtils.getBooleanValue(detail.getCompanyId(), detail.getWarehouseId(), ConfigConstants.CONFIG_ALLOCATE_MONITOR);
		
		List<InventoryOnhandVO> list = getHardInventory(detail);
		if (CollectionUtils.isEmpty(list))
			return list;
		
		if (allocateMonitor) {
			String message = MessageUtils.message("******** allocate line {0} process {1}::batch_id={2}, soft allocate start ", detail.getLineNumber(), detail.getOutboundDetailId(), detail.getAllocateBatchId());
			AllocateLog.log(log, message, detail.getAllocateBatchId(), detail);
		}
		
		//默认按库位软分配
		Map<Long, InventoryOnhandVO> locationInventoryMap = list.stream().collect(Collectors.toMap(InventoryOnhandVO::getLocationId, s -> s, (s1, s2) -> {
			s1.setLotId(null);
			s1.setLotNumber(null);
			s1.setLpnId(null);
			s1.setLpnNumber(null);
			s1.setInventoryOnhandId(null);
			s1.setQuantityOnhand(s1.getQuantityOnhand().add(s2.getQuantityOnhand()));
			s1.setQuantityAvailable(s1.getQuantityAvailable().add(s2.getQuantityAvailable()));
			return s1;
		}));
		
		//获取已软分配的数量
		Map<Long, BigDecimal> softMap = allocateService.getSoftQuantityByLocation(AllocateTEntity.builder()
																					.warehouseId(detail.getWarehouseId())
																					.companyId(detail.getCompanyId())
																					.ownerId(detail.getOwnerId())
																					.skuId(detail.getSkuId())
																					.build());
		locationInventoryMap.forEach((k, v) -> {
			BigDecimal softQuantity = BigDecimal.ZERO;
			if (softMap.containsKey(v.getLocationId()))
				softQuantity = softMap.get(v.getLocationId());
			
			v.setQuantityAvailable(v.getQuantityAvailable().subtract(softQuantity));
		});
		
		List<InventoryOnhandVO> softInventoryList = locationInventoryMap.values().stream().collect(Collectors.toList());
		
		if (allocateMonitor) {
			String message = MessageUtils.message("******** allocate line {0} process {1}::batch_id={2}, soft allocate {3} end ", detail.getLineNumber(), detail.getOutboundDetailId(), detail.getAllocateBatchId(), softInventoryList.size());
			AllocateLog.log(log, message, detail.getAllocateBatchId(), detail);
		}
		
		return softInventoryList;
	}
	
	@Override
	public List<AllocateInventoryVO> getInventory(InventoryAllocateDetailVO detail) {
		boolean allocateMonitor = ConfigUtils.getBooleanValue(detail.getCompanyId(), detail.getWarehouseId(), ConfigConstants.CONFIG_ALLOCATE_MONITOR);
		
		if (allocateMonitor) {
			String message = MessageUtils.message("****** allocate line {0} process {1}::batch_id={2}, qeury inventory start ", detail.getLineNumber(), detail.getOutboundDetailId(), detail.getAllocateBatchId());
			AllocateLog.log(log, message, detail.getAllocateBatchId(), detail);
		}
		
		//计时器
		StopWatch watch = StopWatch.createStarted();
		
		List<InventoryOnhandVO> inventoryList = null; 
		if( AllocateStrategyTypeEnum.Hard.getCode().equals(detail.getAllocateStrategyType()))
			inventoryList = getHardInventory(detail);
		if( AllocateStrategyTypeEnum.Soft.getCode().equals(detail.getAllocateStrategyType()))
			inventoryList = getSoftInventory(detail);
		
		if (CollectionUtils.isEmpty(inventoryList))
			return Lists.newArrayList();
		
		List<AllocateInventoryVO> list = Lists.newArrayList();
		inventoryList.forEach(onhand -> {
			AllocateInventoryVO inventory = new AllocateInventoryVO();
			BeanUtils.copyBeanProp(inventory, onhand);
			
			inventory.setAllocateType(detail.getStrategy().getType());
			inventory.setAllocateStrategyType(detail.getAllocateStrategyType());
			inventory.setAllocateStrategyId(detail.getAllocateStrategyId());
			inventory.setAllocateStrategyCode(detail.getAllocateStrategyCode());
			
			list.add(inventory);
		});
		if (allocateMonitor) {
			String message = MessageUtils.message("****** allocate line {0} process {1}::batch_id={2}, query inventory {3} time {4} end ", detail.getLineNumber(), detail.getOutboundDetailId() , detail.getAllocateBatchId(), list.size() , watch.getTime());
			AllocateLog.log(log, message, detail.getAllocateBatchId(), detail);
		}
		
		return list;
	}
	
	
	protected void sort(InventoryAllocateDetailVO detail, List<AllocateInventoryVO> inventory) {
		boolean allocateMonitor = ConfigUtils.getBooleanValue(detail.getCompanyId(), detail.getWarehouseId(), ConfigConstants.CONFIG_ALLOCATE_MONITOR);
		
		if (allocateMonitor) {
			String message = MessageUtils.message("******** allocate line {0} process {1}::batch_id={2}, sort inventory {3} start ", detail.getLineNumber(), detail.getOutboundDetailId(), detail.getAllocateBatchId(), inventory.size() );
			AllocateLog.log(log, message, detail.getAllocateBatchId(), detail);
		}
		
		//计时器
		StopWatch watch = StopWatch.createStarted();
		
		AllocateStrategyDetailTEntity strategy = detail.getStrategy();
		String [] sort = new String[2];
		if (StringUtils.isNotEmpty(strategy.getFifoRule()))
			sort[0] = AllocateRuleFIFOEnum.get(strategy.getFifoRule()).getCode();
		if (StringUtils.isNotEmpty(strategy.getSortRule()))
			sort[1] = AllocateRuleSortEnum.get(strategy.getSortRule()).getCode();
		
		InventorySort.sort(inventory, sort);
		
		if (allocateMonitor) {
			String message = MessageUtils.message("******** allocate line {0} process {1}::batch_id={2}, sort inventory {3} time {4} end ", detail.getLineNumber(), detail.getOutboundDetailId(), detail.getAllocateBatchId(), inventory.size(), watch.getTime());
			AllocateLog.log(log, message, detail.getAllocateBatchId(), detail);
		}
		
	}

	@Override
	public List<AllocateInventoryVO> filter(InventoryAllocateDetailVO detail, List<AllocateInventoryVO> inventory) {
		boolean allocateMonitor = ConfigUtils.getBooleanValue(detail.getCompanyId(), detail.getWarehouseId(), ConfigConstants.CONFIG_ALLOCATE_MONITOR);
		
		List<AllocateInventoryVO> allocateList = Lists.newArrayList();
		if (CollectionUtils.isEmpty(inventory)) 
			return allocateList;
		
		if (allocateMonitor) {
			String message = MessageUtils.message("****** allocate line {0} process {1}::batch_id={2}, filter inventory {3} start ", detail.getLineNumber(), detail.getOutboundDetailId(), detail.getAllocateBatchId(), inventory.size());
			AllocateLog.log(log, message, detail.getAllocateBatchId(), detail);
		}
		
		//按规则排序
		sort(detail, inventory);
		//需求分配数量
		BigDecimal demandAllocateQuantity = detail.getQuantityExpected().subtract(detail.getQuantityAllocated()).subtract(detail.getQuantityPicked());
		
		if (allocateMonitor) {
			String message = MessageUtils.message("****** allocate line {0} process {1}::batch_id={2}, demand_allocate_auantity={3}", detail.getLineNumber(), detail.getOutboundDetailId(), demandAllocateQuantity);
			AllocateLog.log(log, message, detail.getAllocateBatchId(), detail);
		}
		
		Iterator<AllocateInventoryVO> onhandIterator = inventory.iterator();
		while(onhandIterator.hasNext()) {
			AllocateInventoryVO onhand = onhandIterator.next();
			
			BigDecimal currentAllocateQuantity = null;
			
			if (BigDecimal.ZERO.compareTo(onhand.getQuantityAvailable()) >= 0)
				continue;
			
			//判断单位
			//按LPN分配，必须整LPN满足
			if (AllocateUOMEnum.Lpn.getCode().equals(detail.getStrategy().getUom())
					&& onhand.getQuantityOnhand().compareTo(onhand.getQuantityAvailable()) == 0
					&& onhand.getQuantityAvailable().compareTo(demandAllocateQuantity) > 0) {
				continue;
			}
			//按库位分配， 无LPN管理 或软分配时使用
			if (AllocateUOMEnum.Loc.getCode().equals(detail.getStrategy().getUom())
					&& onhand.getQuantityOnhand().compareTo(onhand.getQuantityAvailable()) == 0
					&& onhand.getQuantityAvailable().compareTo(demandAllocateQuantity) > 0) {
				continue;
			}
			
			//可用量大于等于需求数量
			if (onhand.getQuantityAvailable().compareTo(demandAllocateQuantity) >= 0)
				currentAllocateQuantity = demandAllocateQuantity;
			else //可用数量小于需求数量
				currentAllocateQuantity = onhand.getQuantityAvailable();
			
			onhand.setQuantityAllocated(currentAllocateQuantity);
			
			allocateList.add(onhand);
			
			//增加分配数量
			detail.setQuantityAllocated(detail.getQuantityAllocated().add(currentAllocateQuantity));
			
			demandAllocateQuantity = demandAllocateQuantity.subtract(currentAllocateQuantity);
			
			//停止继续匹配
			if (demandAllocateQuantity.compareTo(BigDecimal.ZERO) <= 0)
				break;
		}
		
		if (allocateMonitor) {
			String message = MessageUtils.message("****** allocate line {0} process {1}::batch_id={2}, filter inventory {3} end ", detail.getLineNumber(), detail.getOutboundDetailId(), detail.getAllocateBatchId(), allocateList.size());
			AllocateLog.log(log, message, detail.getAllocateBatchId(), detail);
		}
		
		detail.getInventoryList().addAll(allocateList);
		return allocateList;
	}

}
