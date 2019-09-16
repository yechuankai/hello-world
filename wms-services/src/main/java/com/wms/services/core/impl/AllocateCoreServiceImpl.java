package com.wms.services.core.impl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.common.constants.ConfigConstants;
import com.wms.common.enums.OutboundProcessStatusEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.enums.allocate.AllocateStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.MessageUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.cache.ConfigUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.entity.auto.AllocateStrategyDetailTEntity;
import com.wms.entity.auto.AllocateStrategyTEntity;
import com.wms.entity.auto.AllocateTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.services.base.IAllocateStrategDetailService;
import com.wms.services.base.IAllocateStrategyHeaderService;
import com.wms.services.core.IAllocateCoreService;
import com.wms.services.inventory.IInventoryService;
import com.wms.services.outbound.IAllocateService;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.services.strategy.allocate.IAllocateStrategyService;
import com.wms.services.strategy.allocate.thead.ThreadPoolConfig;
import com.wms.services.strategy.allocate.util.AllocateLog;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.allocate.InventoryAllocateDetailVO;
import com.wms.vo.allocate.AllocateInventoryVO;
import com.wms.vo.allocate.InventoryAllocateVO;
import com.wms.vo.outbound.OutboundVO;

@Service
public class AllocateCoreServiceImpl implements IAllocateCoreService, InitializingBean {
	
	private static Logger log = LoggerFactory.getLogger(AllocateCoreServiceImpl.class);
	
	private Map<String, IAllocateStrategyService> strategyServiceMap = Maps.newHashMap();
	
	@Autowired
	private IAllocateStrategDetailService strategDetailService;
	@Autowired
	private IAllocateStrategyHeaderService strategHeaderService;
	@Autowired
	private IInventoryService inventoryService;
	@Autowired
	private IAllocateService allocateService;
	@Autowired
	private IOutboundHeaderService outboundHeaderService;
	
	/**
	 * 加载策略逻辑类
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, IAllocateStrategyService> strategyServices = SpringUtils.getBeansOfType(IAllocateStrategyService.class);
		strategyServices.forEach((k, v) -> {
			strategyServiceMap.put(v.getAllocateType(), v);
		});
	}
	
	protected List<AllocateStrategyDetailTEntity> getStrategyDetail(InventoryAllocateDetailVO detail) {
		//获取分配策略
		List<AllocateStrategyDetailTEntity> strategyList = strategDetailService.findByAllocateStrategy(AllocateStrategyTEntity.builder()
														.companyId(detail.getCompanyId())
														.warehouseId(detail.getWarehouseId())
														.allocateStrategyId(detail.getAllocateStrategyId())
														.build());
		strategyList = strategyList.stream().filter(v-> 
						YesNoEnum.Yes.getCode().equals(v.getActive())
					).sorted(Comparator.comparing(AllocateStrategyDetailTEntity::getLineNumber)).collect(Collectors.toList());
		return strategyList;
	}
	
	protected AllocateStrategyTEntity getStrategy(InventoryAllocateDetailVO detail) {
		//获取分配策略
		AllocateStrategyTEntity strategy = strategHeaderService.find(AllocateStrategyTEntity.builder()
														.companyId(detail.getCompanyId())
														.warehouseId(detail.getWarehouseId())
														.allocateStrategyId(detail.getAllocateStrategyId())
														.build());
		return strategy;
	}
	
	@Override
	public InventoryAllocateVO allocateTask(InventoryAllocateVO allocate, Boolean wait) {
		boolean allocateMonitor = ConfigUtils.getBooleanValue(allocate.getCompanyId(), allocate.getWarehouseId(), ConfigConstants.CONFIG_ALLOCATE_MONITOR);
		//产生本次分配批次号，用于追踪日志
		allocate.setAllocateBatchId(KeyUtils.getUID());
		if (allocateMonitor) {
			String message = MessageUtils.message("allocate task start::batch_id={0},source_bill_number={1},user={2}", allocate.getAllocateBatchId(), allocate.getOutboundNumber(), allocate.getUpdateBy());
			AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
		}
		
		//按货品进行分组
		List<InventoryAllocateDetailVO> detail = allocate.getDetail();
		Map<Long, List<InventoryAllocateDetailVO>> detailMap = detail.stream().collect(Collectors.groupingBy(InventoryAllocateDetailVO::getSkuId));
		int taskCount = detailMap.size();
		if (allocateMonitor) {
			String message = MessageUtils.message("allocate task process::batch_id={0},source_bill_number={1},task_count={2}", allocate.getAllocateBatchId(), allocate.getOutboundNumber(), taskCount);
			AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
		}
		//按货品进行多线程处理
		try {
			//设置等等线程执行完毕
			CountDownLatch cdl = new CountDownLatch(taskCount);
			detailMap.forEach((k, v) -> {
				InventoryAllocateVO newAllocate = new InventoryAllocateVO();
				BeanUtils.copyBeanProp(newAllocate, allocate);
				//重新设置明细，准备新线程处理
				newAllocate.setDetail(v);
				
				ThreadPoolTaskExecutor pool = ThreadPoolConfig.newAllocateThead(k);
				pool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							IAllocateCoreService allocateCoreService = SpringUtils.getBean(IAllocateCoreService.class);
							allocateCoreService.allocate(newAllocate);
						} catch (Exception e) {
							throw e;
						}finally {
							cdl.countDown();
						}
						if (allocateMonitor) {
							String message = MessageUtils.message("allocate task process::batch_id={0} remaining={1}", allocate.getAllocateBatchId(), cdl.getCount());
							AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
						}
					}
				});
				
			});
			//不等待结果
			if (!wait) {
				return allocate;
			}
			cdl.await();
			if (allocateMonitor) {
				String message = MessageUtils.message("allocate task end::batch_id={0}", allocate.getAllocateBatchId());
				AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
			}
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
		return allocate;
	}
	
	
	@Override
	@Transactional
	public InventoryAllocateVO allocate(InventoryAllocateVO allocate) throws BusinessServiceException{
		boolean allocateMonitor = ConfigUtils.getBooleanValue(allocate.getCompanyId(), allocate.getWarehouseId(), ConfigConstants.CONFIG_ALLOCATE_MONITOR);
		//产生本次分配批次号，用于追踪日志
		if (allocate.getAllocateBatchId() == null)
			allocate.setAllocateBatchId(KeyUtils.getUID());
		
		if (allocateMonitor) {
			String message = MessageUtils.message("allocate start::batch_id={0},source_bill_number={1},user={2}", allocate.getAllocateBatchId(), allocate.getOutboundNumber(), allocate.getUpdateBy());
			AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
		}
		
		
		allocate.getDetail().forEach(d -> {
			
			d.setWarehouseId(allocate.getWarehouseId());
			d.setCompanyId(allocate.getCompanyId());
			d.setUpdateBy(allocate.getUpdateBy());
			d.setAllocateBatchId(allocate.getAllocateBatchId());
			
			AllocateStrategyTEntity strategy = getStrategy(d);
			if(strategy == null) {
				String errormsg = MessageUtils.message("allocateStrategy.is.null", new Object[] {allocate.getOutboundNumber(),  d.getLineNumber() });
				d.setError(errormsg);
				if (allocateMonitor) {
					String message = MessageUtils.message("** allocate line {0}::batch_id={1},error={2}", d.getLineNumber(), allocate.getAllocateBatchId(), errormsg);
					AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
				}
				return;
			}

			d.setAllocateStrategyType(strategy.getAllocateStrategyType());
			
			List<AllocateStrategyDetailTEntity> strategyList = getStrategyDetail(d);
			d.setStrategys(strategyList);

			allocate(allocate, d);
			
		});
		if (allocateMonitor) {
			String message = MessageUtils.message("allocate end::batch_id={0},source_bill_number={1}", allocate.getAllocateBatchId(), allocate.getOutboundNumber());
			AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
		}
		
		//更新出库单状态
		OutboundHeaderTEntity header = OutboundHeaderTEntity.builder()
				.warehouseId(allocate.getWarehouseId())
				.companyId(allocate.getCompanyId())
				.outboundHeaderId(allocate.getOutboundHeaderId())
				.updateBy(allocate.getUpdateBy())
				.processStatus(OutboundProcessStatusEnum.Allocated.getCode())
				.build();
		outboundHeaderService.modify(new OutboundVO(header));
	
		return allocate;
	}
	
	protected InventoryAllocateDetailVO allocate(InventoryAllocateVO allocate, InventoryAllocateDetailVO detail) {
		boolean allocateMonitor = ConfigUtils.getBooleanValue(detail.getCompanyId(), detail.getWarehouseId(), ConfigConstants.CONFIG_ALLOCATE_MONITOR);
		
		List<AllocateStrategyDetailTEntity> strategyList = detail.getStrategys();
		if (CollectionUtils.isEmpty(strategyList)) {
			String errormsg = MessageUtils.message("allocateStrategy.is.empty", new Object[] {allocate.getOutboundNumber(),  detail.getLineNumber() });
			detail.setError(errormsg);
			if (allocateMonitor) {
				String message = MessageUtils.message("** allocate line {0}::batch_id={1},error={2}", detail.getLineNumber(), allocate.getAllocateBatchId(), errormsg);
				AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
			}
			return detail;
		}
		
		if (allocateMonitor) {
			String message = MessageUtils.message("** allocate line {0} start::batch_id={1}", detail.getLineNumber(), allocate.getAllocateBatchId());
			AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
		}
		
		//计时器
		StopWatch watch = StopWatch.createStarted();
		
		Iterator<AllocateStrategyDetailTEntity> strategyIterator = strategyList.iterator();
		while(strategyIterator.hasNext()) {
			AllocateStrategyDetailTEntity strategy = strategyIterator.next();
			detail.setStrategy(strategy);
			
			IAllocateStrategyService strategyServices = strategyServiceMap.get(detail.getStrategy().getType());
			if (strategyServices == null) {
				String errormsg = MessageUtils.message("allocateStrategyCode.not.service", new Object[] {detail.getAllocateStrategyCode()});
				detail.setError(errormsg);
				if (allocateMonitor) {
					String message = MessageUtils.message("**** allocate line {0}::batch_id={1},error={2}" ,  detail.getLineNumber(), allocate.getAllocateBatchId(), errormsg);
					AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
				}
				continue;
			}
			
			if (allocateMonitor) {
				String message = MessageUtils.message("**** allocate line {0} process {1}::batch_id={2}, strategy_line={3}, allocate_type={4}" ,  detail.getLineNumber(), detail.getOutboundDetailId(),  allocate.getAllocateBatchId(), strategy.getLineNumber(), strategy.getType());
				AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
			}
			
			strategyServices.before(detail);
			List<AllocateInventoryVO> inventoryList = strategyServices.getInventory(detail);
			
			if (allocateMonitor) {
				String message = MessageUtils.message("**** allocate line {0} process {1}::batch_id={2}, find inventory {3}" ,  detail.getLineNumber(), detail.getOutboundDetailId(), allocate.getAllocateBatchId(), inventoryList.size());
				AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
			}
			
			strategyServices.filter(detail, inventoryList);
			
			if (allocateMonitor) {
				String message = MessageUtils.message("**** allocate line {0} process {1}::batch_id={2}, filter inventory {3}" ,  detail.getLineNumber(), detail.getOutboundDetailId(), allocate.getAllocateBatchId(), detail.getInventoryList().size());
				AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
			}
			
			strategyServices.after(detail, inventoryList);
			
			BigDecimal demandAllocateQuantity = detail.getQuantityExpected().subtract(detail.getQuantityAllocated()).subtract(detail.getQuantityPicked());
			
			if (allocateMonitor) {
				//开始计时
				watch.split();
				String message = MessageUtils.message("**** allocate line {0} process {1}::batch_id={2}, demand_allocate_quantity={3}, time={4}" ,  detail.getLineNumber(), detail.getOutboundDetailId(), allocate.getAllocateBatchId(), demandAllocateQuantity, watch.getSplitTime());
				AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
			}
			
			//分配完成，退出
			if (BigDecimal.ZERO.compareTo(demandAllocateQuantity) == 0)
				break;
		}
		if (CollectionUtils.isNotEmpty(detail.getInventoryList()))
			processAllocate(allocate, detail);
			
		if (allocateMonitor) {
			String message = MessageUtils.message("** allocate line {0} end::batch_id={1}, allocate inventory {2} " ,  detail.getLineNumber(), allocate.getAllocateBatchId(), detail.getInventoryList().size());
			AllocateLog.log(log, message, allocate.getAllocateBatchId(), allocate);
		}
		
		return detail;
	}
	

	protected void processAllocate(InventoryAllocateVO allocate, InventoryAllocateDetailVO detail) {
		List<AllocateTEntity> allocateList = Lists.newArrayList();
		List<InventoryOnhandVO> onhandList = detail.getInventoryList();
		
		
		InventoryOnhandTEntity onhandQuery = InventoryOnhandTEntity.builder()
		.companyId(detail.getCompanyId())
		.warehouseId(detail.getWarehouseId())
		.build();
		
		Set<Long> locations = onhandList.stream().map(InventoryOnhandVO::getLocationId).collect(Collectors.toSet());
		List<InventoryOnhandTEntity> locationInventory = inventoryService.findByLocationId(onhandQuery, locations);
		Map<Long, BigDecimal> locationInventoryMap = Maps.newHashMap();
		locationInventory.forEach(i -> {
			if (locationInventoryMap.containsKey(i.getLocationId())) {
				BigDecimal toOnhand = locationInventoryMap.get(i.getLocationId()).add(i.getQuantityOnhand());
				locationInventoryMap.put(i.getLocationId(), toOnhand);
			}else {
				locationInventoryMap.put(i.getLocationId(), i.getQuantityOnhand());
			}
		});
		
		
		Set<Long> lpns = onhandList.stream().map(InventoryOnhandVO::getLpnId).collect(Collectors.toSet());
		List<InventoryOnhandTEntity> lpnInventory = inventoryService.findByLpnId(onhandQuery, lpns);
		Map<Long, BigDecimal> lpnInventoryMap = Maps.newHashMap();
		lpnInventory.forEach(i -> {
			if (lpnInventoryMap.containsKey(i.getLocationId())) {
				BigDecimal toOnhand = lpnInventoryMap.get(i.getLocationId()).add(i.getQuantityOnhand());
				lpnInventoryMap.put(i.getLocationId(), toOnhand);
			}else {
				lpnInventoryMap.put(i.getLocationId(), i.getQuantityOnhand());
			}
		});
		
		onhandList.forEach(onhand -> {
			AllocateTEntity allocateDetail = new AllocateTEntity();
			BeanUtils.copyBeanProp(allocateDetail, onhand, Boolean.FALSE);
			 
			allocateDetail.setUpdateBy(detail.getUpdateBy());
			allocateDetail.setCreateBy(detail.getUpdateBy());
			Date currentDate = new Date();
			allocateDetail.setCreateTime(currentDate);
			allocateDetail.setUpdateTime(currentDate);
			allocateDetail.setSourceBillNumber(allocate.getOutboundNumber());
			allocateDetail.setSourceWaveNumber(allocate.getSourceWaveNumber());
			allocateDetail.setSourceNumber(detail.getOutboundDetailId());
			allocateDetail.setSourceLineNumber(detail.getLineNumber());
			allocateDetail.setStatus(AllocateStatusEnum.Allocated.getCode());
			
			//整库位分配
			BigDecimal locationQuantity = locationInventoryMap.get(onhand.getLocationId());
			if (locationQuantity != null && locationQuantity.compareTo(onhand.getQuantityAllocated()) <= 0){
				allocateDetail.setFullLocationFlag(YesNoEnum.Yes.getCode());
			}
			if (onhand.getLpnId() != null) {
				//整LPN分配
				BigDecimal lpnQuantity = locationInventoryMap.get(onhand.getLocationId());
				if (lpnQuantity != null && lpnQuantity.compareTo(onhand.getQuantityAllocated()) <= 0){
					allocateDetail.setFullLpnFlag(YesNoEnum.Yes.getCode());
				}
			}
			allocateList.add(allocateDetail);
		});
		//新增分配明细
		allocateService.add(allocateList);
	}


}
