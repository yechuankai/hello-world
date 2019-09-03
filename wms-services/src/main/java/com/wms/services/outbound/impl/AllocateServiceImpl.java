package com.wms.services.outbound.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.services.IExcelService;
import com.wms.common.enums.ExcelTemplateEnum;
import com.wms.common.enums.TaskReasonEnum;
import com.wms.common.enums.TaskSourceTypeEnum;
import com.wms.common.enums.TaskStatusEnum;
import com.wms.common.enums.TaskTypeEnum;
import com.wms.common.enums.TransactionTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.enums.allocate.AllocateStatusEnum;
import com.wms.common.enums.allocate.AllocateStrategyTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IAllocateTDao;
import com.wms.dao.example.AllocateTExample;
import com.wms.dao.example.AllocateTExample.Criteria;
import com.wms.entity.auto.AllocateShortTEntity;
import com.wms.entity.auto.AllocateTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.TaskDetailTEntity;
import com.wms.entity.auto.ZoneTEntity;
import com.wms.services.base.ILocationService;
import com.wms.services.base.IZoneService;
import com.wms.services.core.IInventoryCoreService;
import com.wms.services.inventory.IInventoryService;
import com.wms.services.inventory.ITaskService;
import com.wms.services.outbound.IAllocateService;
import com.wms.services.outbound.IAllocateShortService;
import com.wms.services.outbound.IOutboundDetailService;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.vo.InventoryTranDetailVO;
import com.wms.vo.InventoryTranVO;
import com.wms.vo.allocate.AllocateVO;
import com.wms.vo.excel.AllocateImportVO;
import com.wms.vo.outbound.OutboundDetailVO;

/**
 * @description:
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-04 11:33
 **/
@Service
public class AllocateServiceImpl implements IAllocateService , IExcelService<AllocateImportVO> {
	
	private static final Logger log = LoggerFactory.getLogger(AllocateServiceImpl.class);
	
    @Autowired
    private IAllocateTDao allocateDao;
    @Autowired
    private IInventoryService inventoryService;
    @Autowired
    private IInventoryCoreService inventoryCoreService;
    @Autowired
    private IOutboundDetailService outboundDetailService;
    @Autowired
    private ILocationService locationService;
    @Autowired
    private IZoneService zoneService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IOutboundHeaderService outboundHeaderService;
    @Autowired
    private IAllocateShortService allocateShortService;

    @Override
    public List<AllocateTEntity> find(PageRequest request) throws BusinessServiceException {
        AllocateTExample example = new AllocateTExample();
        AllocateTExample.Criteria exampleCriteria = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(AllocateTEntity.Column.class, AllocateTExample.Criterion.class)
                .criteria(exampleCriteria)
                .data(request)
                .build(request)
                .orderby(example);
        exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return allocateDao.selectByExample(example);
    }

    @Override
    public AllocateTEntity find(AllocateTEntity allocate) throws BusinessServiceException{
    	 AllocateTExample example = new AllocateTExample();
         AllocateTExample.Criteria exampleCriteria = example.createCriteria();
         exampleCriteria.
         andDelFlagEqualTo(YesNoEnum.No.getCode())
         .andWarehouseIdEqualTo(allocate.getWarehouseId())
         .andCompanyIdEqualTo(allocate.getCompanyId())
         .andAllocateIdEqualTo(allocate.getAllocateId());
         
         AllocateTEntity selectAllocate =  allocateDao.selectOneByExample(example);
         if (selectAllocate == null)
        	 throw new BusinessServiceException("AllocateServiceImpl", "allocate.not.exists" , new Object[] {allocate.getAllocateId()});

         return selectAllocate;
    }
    
    @Override
	public List<AllocateTEntity> findBySourceNumber(AllocateTEntity allocate, Set<Long> sourceNumbers, AllocateStatusEnum ... status)
			throws BusinessServiceException {
    	if (CollectionUtils.isEmpty(sourceNumbers))
    		return Lists.newArrayList();

    	AllocateTExample example = new AllocateTExample();
        AllocateTExample.Criteria exampleCriteria = example.createCriteria();
        exampleCriteria.
        andDelFlagEqualTo(YesNoEnum.No.getCode())
        .andWarehouseIdEqualTo(allocate.getWarehouseId())
        .andCompanyIdEqualTo(allocate.getCompanyId())
        .andSourceNumberIn(Lists.newArrayList(sourceNumbers));

        if (status != null && status.length > 0)
        	exampleCriteria.andStatusIn(Lists.newArrayList(status).stream().map(AllocateStatusEnum::getCode).collect(Collectors.toList()));

        List<AllocateTEntity> selectAllocate =  allocateDao.selectByExample(example);
        return selectAllocate;
	}


    @Override
    public Map<Long, BigDecimal> getSoftQuantityByLocation(AllocateTEntity allocate) throws BusinessServiceException {
    	Map<Long, BigDecimal> softMap = Maps.newHashMap();

    	AllocateTExample example = new AllocateTExample();
        AllocateTExample.Criteria exampleCriteria = example.createCriteria();

        exampleCriteria
        .andDelFlagEqualTo(YesNoEnum.No.getCode())
        .andWarehouseIdEqualTo(allocate.getWarehouseId())
        .andCompanyIdEqualTo(allocate.getCompanyId())
        .andAllocateStrategyTypeEqualTo(AllocateStrategyTypeEnum.Soft.getCode())
        .andOwnerIdEqualTo(allocate.getOwnerId())
        .andSkuIdEqualTo(allocate.getSkuId());

        List<AllocateTEntity> list = allocateDao.selectByExample(example);
        if (CollectionUtils.isEmpty(list))
        	return softMap;

        for (AllocateTEntity al : list) {
        	if (softMap.containsKey(al.getLocationId())) {
        		 BigDecimal quantity = softMap.get(al.getLocationId()).add(al.getQuantityAllocated());
        		 softMap.put(al.getLocationId(), quantity);
        	}else {
        		softMap.put(al.getLocationId(), al.getQuantityAllocated());
        	}
		}
        return softMap;
    }
    
    @Override
    public Map<Long, BigDecimal> getSoftQuantityBySku(AllocateTEntity allocate) throws BusinessServiceException {
    	Map<Long, BigDecimal> softMap = Maps.newHashMap();

    	AllocateTExample example = new AllocateTExample();
        AllocateTExample.Criteria exampleCriteria = example.createCriteria();

        exampleCriteria
        .andDelFlagEqualTo(YesNoEnum.No.getCode())
        .andWarehouseIdEqualTo(allocate.getWarehouseId())
        .andCompanyIdEqualTo(allocate.getCompanyId())
        .andAllocateStrategyTypeEqualTo(AllocateStrategyTypeEnum.Soft.getCode())
        .andOwnerIdEqualTo(allocate.getOwnerId())
        .andSkuIdEqualTo(allocate.getSkuId());

        List<AllocateTEntity> list = allocateDao.selectByExample(example);
        if (CollectionUtils.isEmpty(list))
        	return softMap;

        for (AllocateTEntity al : list) {
        	if (softMap.containsKey(al.getSkuId())) {
        		 BigDecimal quantity = softMap.get(al.getLocationId()).add(al.getQuantityAllocated());
        		 softMap.put(al.getSkuId(), quantity);
        	}else {
        		softMap.put(al.getSkuId(), al.getQuantityAllocated());
        	}
		}
        return softMap;
    }
    
    @Override
    public List<AllocateTEntity> findBySkuAndLocation(Long warehouseId,Long companyId,Set<Long> skuIds,Set<Long> locationIds) {
    	AllocateTExample example = new AllocateTExample();
    	Criteria criteria = example.createCriteria();
    	criteria.andCompanyIdEqualTo(companyId)
    			.andWarehouseIdEqualTo(warehouseId)
    			.andAllocateStrategyTypeEqualTo(AllocateStrategyTypeEnum.Soft.getCode())
    			.andSkuIdIn(Lists.newArrayList(skuIds));
    
    	if (locationIds != null && locationIds.size() != 0) {
    		criteria.andLocationIdIn(Lists.newArrayList(locationIds));
		}
    	List<AllocateTEntity> results = allocateDao.selectByExample(example);
    	return results;
    }

	@Override
	@Transactional
	public Boolean add(List<AllocateTEntity> list) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(list))
			return Boolean.FALSE;

		long warehouseId = 0L;
		long companyId = 0L;

		for (AllocateTEntity allocate : list) {
			warehouseId = allocate.getWarehouseId();
			companyId = allocate.getCompanyId();
			break;
		}

		//get all location
		Set<Long> locationIds = list.stream().map(AllocateTEntity::getLocationId).collect(Collectors.toSet());
		List<LocationTEntity> locations = locationService.findByLocationIds(LocationTEntity.builder().warehouseId(warehouseId).companyId(companyId).build(), locationIds);
		//get all zone
		Set<Long> zoneIds = locations.stream().map(LocationTEntity::getZoneId).collect(Collectors.toSet());
		List<ZoneTEntity> zones = zoneService.find(ZoneTEntity.builder().warehouseId(warehouseId).companyId(companyId).build(), zoneIds);

		Map<Long, LocationTEntity> locationMap = locations.stream().collect(Collectors.toMap(LocationTEntity::getLocationId, (v) -> v));
		Map<Long, ZoneTEntity> zoneMap = zones.stream().collect(Collectors.toMap(ZoneTEntity::getZoneId, (v) -> v));

		for (AllocateTEntity allocate : list) {

			allocate.setAllocateId(KeyUtils.getUID());
			LocationTEntity locatoin = locationMap.get(allocate.getLocationId());
			allocate.setZoneCode(locatoin.getZoneCode());
			ZoneTEntity zone = zoneMap.get(locatoin.getZoneId());
			allocate.setToLocationCode(zone.getPickToLocation());

			allocateDao.insertSelective(allocate);

			//硬分配时更新库存
			if (AllocateStatusEnum.Allocated.getCode().equals(allocate.getStatus())
					&& AllocateStrategyTypeEnum.Hard.getCode().equals(allocate.getAllocateStrategyType())) {
				InventoryOnhandTEntity inventory = InventoryOnhandTEntity.builder()
													.warehouseId(allocate.getWarehouseId())
													.companyId(allocate.getCompanyId())
													.inventoryOnhandId(allocate.getInventoryOnhandId())
													.quantityAllocated(allocate.getQuantityAllocated())
													.updateBy(allocate.getUpdateBy())
													.build();
				inventoryService.modify(inventory, Boolean.FALSE);
			}
		}
		//更新明细数量
		modifyQuantity(list);
		return Boolean.TRUE;
	}


	@Override
	@Transactional
	public Boolean delete(List<AllocateTEntity> list) throws BusinessServiceException {
		List<AllocateTEntity> selectList = Lists.newArrayList();
		for (AllocateTEntity allocate : list) {

			AllocateTEntity selectAllocate = find(allocate);
			if (AllocateStatusEnum.Ship.getCode().equals(selectAllocate.getStatus()))
				throw new BusinessServiceException("AllocateServiceImpl", "allocate.line.status.not.process" , new Object[] {selectAllocate.getAllocateId()});

			if (AllocateStrategyTypeEnum.Hard.getCode().equals(selectAllocate.getAllocateStrategyType())) {
				//负数
				BigDecimal unAllocateQuantity = BigDecimal.ZERO.subtract(selectAllocate.getQuantityAllocated());

				InventoryOnhandTEntity inventory = InventoryOnhandTEntity.builder()
													.warehouseId(allocate.getWarehouseId())
													.companyId(allocate.getCompanyId())
													.inventoryOnhandId(selectAllocate.getInventoryOnhandId())
													.quantityAllocated(unAllocateQuantity)
													.updateBy(allocate.getUpdateBy())
													.build();
				inventoryService.modify(inventory, Boolean.FALSE);
			}

			//执行删除
			AllocateTExample example = new AllocateTExample();
	        AllocateTExample.Criteria exampleCriteria = example.createCriteria();
	        exampleCriteria
	        .andWarehouseIdEqualTo(allocate.getWarehouseId())
	        .andCompanyIdEqualTo(allocate.getCompanyId())
	        .andAllocateIdEqualTo(selectAllocate.getAllocateId());

	        AllocateTEntity updateAllocate = AllocateTEntity.builder()
	        									.updateBy(allocate.getUpdateBy())
	        									.updateTime(new Date())
	        									.delFlag(YesNoEnum.Yes.getCode())
	        									.build();

	        int rowcount = allocateDao.updateWithVersionByExampleSelective(selectAllocate.getUpdateVersion(), updateAllocate, example);
	        if (rowcount == 0)
	        	throw new BusinessServiceException("not data delete.");
	        
	        TaskDetailTEntity task = null;
	        //判断是否存在任务
	        try {
	        	task = taskService.findBySourceNumber(TaskDetailTEntity.builder().warehouseId(allocate.getWarehouseId()).companyId(allocate.getCompanyId()).sourceNumber(allocate.getAllocateId()).build());
			} catch (BusinessServiceException e) {}
	        //任务不为空时，需取消任务
	        if (task != null) {
	        	TaskDetailTEntity updateTask = TaskDetailTEntity.builder()
	        										.warehouseId(allocate.getWarehouseId())
	        										.companyId(allocate.getCompanyId())
	        										.status(TaskStatusEnum.Cancel.getCode())
	        										.reason(TaskReasonEnum.UnAllocate.getCode())
	        										.taskDetailId(task.getTaskDetailId())
	        										.updateBy(allocate.getUpdateBy())
	        										.updateTime(new Date())
	        										.build();
	        	
	        	taskService.modify(updateTask);
	        }

	        //设置更新人
	        selectAllocate.setUpdateBy(allocate.getUpdateBy());
	        //数量扣减
	        selectAllocate.setQuantityAllocated(BigDecimal.ZERO.subtract(selectAllocate.getQuantityAllocated()));
			selectList.add(selectAllocate);
		}
		//更新明细数量
		modifyQuantity(selectList);
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean modify(List<AllocateTEntity> list) throws BusinessServiceException {
		List<AllocateTEntity> selectList = Lists.newArrayList();
		for (AllocateTEntity allocate : list) {
			AllocateTEntity selectAllocate = find(allocate);
			if (AllocateStatusEnum.Ship.getCode().equals(selectAllocate.getStatus()))
				throw new BusinessServiceException("AllocateServiceImpl", "allocate.line.status.not.process" , new Object[] {selectAllocate.getAllocateId()});

			//执行修改
			AllocateTExample example = new AllocateTExample();
	        AllocateTExample.Criteria exampleCriteria = example.createCriteria();
	        exampleCriteria
	        .andWarehouseIdEqualTo(allocate.getWarehouseId())
	        .andCompanyIdEqualTo(allocate.getCompanyId())
	        .andAllocateIdEqualTo(selectAllocate.getAllocateId());

	        BigDecimal diffQuantity = BigDecimal.ZERO;
	        if (allocate.getQuantityAllocated() != null) {
	        	//将分配数量更新为0
	        	if (BigDecimal.ZERO.compareTo(allocate.getQuantityAllocated()) == 0)
	        		allocate.setDelFlag(YesNoEnum.Yes.getCode());

	        	diffQuantity = allocate.getQuantityAllocated().subtract(selectAllocate.getQuantityAllocated());
	        }

	        //设置更新人
	        selectAllocate.setUpdateBy(allocate.getUpdateBy());

	        boolean continueFlag = true;

	        //更新为拣货状态，由库存核心逻辑负责扣减库存，此处仅负责处理出库订单行数据
	        if (AllocateStatusEnum.Picked.getCode().equals(allocate.getStatus()) && !selectAllocate.getStatus().equals(allocate.getStatus())) {
	        	allocate.setStatus(null); //由分配量更改为拣货量时，原记录扣减，不更新状态
	        	//计算分配量
	        	AllocateTEntity allocateQ = new AllocateTEntity();
	        	BeanUtils.copyBeanProp(allocateQ, selectAllocate);
	        	allocateQ.setStatus(AllocateStatusEnum.Allocated.getCode());
	        	allocateQ.setQuantityAllocated(diffQuantity);
	        	selectList.add(allocateQ);
	        	continueFlag = false;
	        }

	        //更新为发运状态，由库存核心逻辑负责扣减库存，此处仅负责处理出库订单行数据
	        if (AllocateStatusEnum.Ship.getCode().equals(allocate.getStatus()) && !selectAllocate.getStatus().equals(allocate.getStatus())) {
	        	selectAllocate.setStatus(AllocateStatusEnum.Ship.getCode());
	        	selectList.add(selectAllocate);
	        	continueFlag = false;
	        }


	        //设置最后更新时间
	        allocate.setUpdateTime(new Date());

	        int rowcount = allocateDao.updateWithVersionByExampleSelective(selectAllocate.getUpdateVersion(), allocate, example);
	        if (rowcount == 0)
	        	throw new BusinessServiceException("not data update.");

	        if (!continueFlag)
	        	continue;

	        if (BigDecimal.ZERO.compareTo(diffQuantity) == 0)
				continue;

	        //计算是否修改数量, 硬分配时更新库存
			if (AllocateStrategyTypeEnum.Hard.getCode().equals(selectAllocate.getAllocateStrategyType())) {
				InventoryOnhandTEntity inventory = InventoryOnhandTEntity.builder()
													.warehouseId(allocate.getWarehouseId())
													.companyId(allocate.getCompanyId())
													.inventoryOnhandId(selectAllocate.getInventoryOnhandId())
													.quantityAllocated(diffQuantity)
													.updateBy(allocate.getUpdateBy())
													.build();
				inventoryService.modify(inventory, Boolean.FALSE);
			}

			//修改订单明细数量
			selectAllocate.setQuantityAllocated(diffQuantity);
			selectList.add(selectAllocate);

		}
		//更新明细数量
		modifyQuantity(selectList);
		return Boolean.TRUE;
	}

	/**
	 * 更新订单行数量
	 * @param allocateList
	 * @return
	 */
	private Boolean modifyQuantity(List<AllocateTEntity> allocateList ) {
		long warehouseId = 0L;
		long companyId = 0L;
		String updateBy = null;
		for (AllocateTEntity allocate : allocateList) {
			warehouseId = allocate.getWarehouseId();
			companyId = allocate.getCompanyId();
			updateBy = allocate.getUpdateBy();
			break;
		}

		//按出库订单行进行分组
		Map<Long, List<AllocateTEntity>> outboundAllocateMap = allocateList.stream().collect(Collectors.groupingBy(AllocateTEntity::getSourceNumber));

		Map<Long, BigDecimal> outboundAllocateQuantityMap = Maps.newHashMap();
		Map<Long, BigDecimal> outboundPickQuantityMap = Maps.newHashMap();
		Map<Long, BigDecimal> outboundShipQuantityMap = Maps.newHashMap();
		outboundAllocateMap.forEach((k, v) -> {
			outboundAllocateQuantityMap.put(k, BigDecimal.ZERO);
			outboundPickQuantityMap.put(k, BigDecimal.ZERO);
			outboundShipQuantityMap.put(k, BigDecimal.ZERO);
			v.forEach(d -> {
				BigDecimal quantity = d.getQuantityAllocated();
				if (AllocateStatusEnum.Allocated.getCode().equals(d.getStatus()) || AllocateStatusEnum.Release.getCode().equals(d.getStatus()))
					outboundAllocateQuantityMap.put(k, outboundAllocateQuantityMap.get(k).add(quantity));
				else if (AllocateStatusEnum.Ship.getCode().equals(d.getStatus()))
					outboundShipQuantityMap.put(k, outboundShipQuantityMap.get(k).add(quantity));
				else
					outboundPickQuantityMap.put(k, outboundPickQuantityMap.get(k).add(quantity));
			});
		});
		final long _warehouseId = warehouseId;
		final long _companyId = companyId;
		final String _updateBy = updateBy;
		outboundAllocateMap.forEach((k, v) -> {
			//更新出库订单
			OutboundDetailVO updateDetail = new OutboundDetailVO();
			updateDetail.setOutboundDetailId(k);
			updateDetail.setWarehouseId(_warehouseId);
			updateDetail.setCompanyId(_companyId);
			updateDetail.setUpdateBy(_updateBy);
			if(outboundAllocateQuantityMap.containsKey(k)) {
				//分配量处理
				BigDecimal allocateQuantity = outboundAllocateQuantityMap.get(k);
				if (BigDecimal.ZERO.compareTo(allocateQuantity) != 0)
					updateDetail.setQuantityAllocated(allocateQuantity);
			}
			if(outboundPickQuantityMap.containsKey(k)) {
				//拣货数量处理
				BigDecimal pickQuantity = outboundPickQuantityMap.get(k);
				if (BigDecimal.ZERO.compareTo(pickQuantity) != 0)
					updateDetail.setQuantityPicked(pickQuantity);
			}
			if(outboundShipQuantityMap.containsKey(k)) {
				//发运数量处理
				BigDecimal shipQuantity = outboundShipQuantityMap.get(k);
				if (BigDecimal.ZERO.compareTo(shipQuantity) != 0) {
					updateDetail.setQuantityShiped(shipQuantity);
					updateDetail.setQuantityPicked(BigDecimal.ZERO.subtract(shipQuantity));
					updateDetail.setQuantityExpected(BigDecimal.ZERO.subtract(shipQuantity));
				}
			}
			outboundDetailService.modify(updateDetail, Boolean.FALSE);
		});
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean pick(List<AllocateVO> list) throws BusinessServiceException {
		//按单据执行转移
		Map<String, List<AllocateVO>> outbounds = list.stream().collect(Collectors.groupingBy(AllocateTEntity::getSourceBillNumber));
		outbounds.forEach((k, v) -> {

			if (CollectionUtils.isEmpty(v))
				return;

			long warehouseId = v.get(0).getWarehouseId();
			long companyId = v.get(0).getCompanyId();
			String userName = v.get(0).getUpdateBy();

			InventoryTranVO tran = new InventoryTranVO();
			tran.setTransationType(TransactionTypeEnum.Move.getCode());
			tran.setUserName(userName);
			tran.setWarehouseId(warehouseId);
			tran.setCompanyId(companyId);
			tran.setSouceBillNumber(k);
			tran.setAllocateFlag(Boolean.TRUE);

			List<InventoryTranDetailVO> tranList = Lists.newArrayList();
			List<AllocateVO> pickList = Lists.newArrayList();
			//明细
			v.forEach(d -> {

				//必须为硬分配
				if (AllocateStrategyTypeEnum.Soft.getCode().equals(d.getAllocateStrategyType())) {
					InventoryOnhandTEntity onhand = null;
					try {
						//验证是否能对应库存
						onhand = inventoryService.findByLogicalKey(InventoryOnhandTEntity.builder()
															.warehouseId(warehouseId)
															.companyId(companyId)
															.ownerCode(d.getOwnerCode())
															.skuCode(d.getSkuCode())
															.lotNumber(d.getLotNumber())
															.locationCode(d.getLocationCode())
															.lpnNumber(d.getLpnNumber())
															.build());
					} catch (BusinessServiceException e) {
						// no inventory
					}
					if (onhand == null) {
						log.error(d.toString());
						throw new BusinessServiceException("AllocateServiceImpl", "pick.line.noavailable.inventory",new Object[] {d.getSourceLineNumber()}) ;
					}
					
					d.setInventoryOnhandId(onhand.getInventoryOnhandId());
					d.setLotId(onhand.getLotId());
					d.setLotNumber(onhand.getLotNumber());
					d.setLpnId(onhand.getLpnId());
					d.setLpnNumber(onhand.getLpnNumber());
					//d.setAllocateStrategyType(AllocateStrategyTypeEnum.Hard.getCode());
					//throw new BusinessServiceException("AllocateServiceImpl", "allocate.line.pick.must.hardallocate",new Object[] {d.getSourceLineNumber()}) ;
				}
				
				if (!(AllocateStatusEnum.Allocated.getCode().equals(d.getStatus())
						|| AllocateStatusEnum.Release.getCode().equals(d.getStatus())))
						return;

				InventoryOnhandTEntity sourceOnhand = new InventoryOnhandTEntity();
				BeanUtils.copyBeanProp(sourceOnhand, d, Boolean.FALSE);

				InventoryTranDetailVO tranDetail = new InventoryTranDetailVO();
				BeanUtils.copyBeanProp(tranDetail, d, Boolean.FALSE);
				tranDetail.setTransactionCategory(d.getTransactionCategory());
				tranDetail.setTranQuantity(d.getQuantityPick());
				tranDetail.setSourceNumber(String.valueOf(d.getAllocateId()));
				tranDetail.setSourceLineNumber(String.valueOf(d.getSourceLineNumber()));
				tranDetail.setLocationCode(d.getToLocationCode());
				tranDetail.setLpnNumber(StringUtils.isEmpty(d.getLoadLpnNumber())? k : d.getLoadLpnNumber()); //默认模板LPN为订单号
				tranDetail.setLpnId(null);
				tranDetail.setSourceInventoryOnhand(sourceOnhand);
				//库存处理无需分配量
				tranDetail.setQuantityAllocated(BigDecimal.ZERO);

				//需要更新的数据
				AllocateTEntity updateAllocate = AllocateTEntity.builder()
													.warehouseId(warehouseId)
													.companyId(companyId)
													.updateBy(userName)
													.allocateId(d.getAllocateId())
													.sourceNumber(d.getSourceNumber())
													.sourceBillNumber(d.getSourceBillNumber())
													.sourceLineNumber(d.getSourceLineNumber())
													.allocateStrategyType(d.getAllocateStrategyType())
													.status(AllocateStatusEnum.Picked.getCode())
													.quantityAllocated(d.getQuantityAllocated())
													.build();
				AllocateVO updateVo = new AllocateVO(updateAllocate);
				updateVo.setShortFlag(d.getShortFlag());
				tranDetail.setAllocate(updateVo);

				tranList.add(tranDetail);
				pickList.add(d);
			});
			tran.setDetail(tranList);

			inventoryCoreService.move(tran);
			
			pickList.forEach(allocate -> {
				//判断是否处理任务
				if (!YesNoEnum.Yes.getCode().equals(allocate.getProcessTaskFlag()))
					return;
				
				TaskDetailTEntity task = null;
		        //判断是否存在任务
		        try {
		        	task = taskService.findBySourceNumber(TaskDetailTEntity.builder().warehouseId(allocate.getWarehouseId()).companyId(allocate.getCompanyId()).sourceNumber(allocate.getAllocateId()).build());
				} catch (BusinessServiceException e) {}
		        //任务不为空时，需取消任务
		        if (task != null) {
		        	TaskDetailTEntity updateTask = TaskDetailTEntity.builder()
		        										.warehouseId(allocate.getWarehouseId())
		        										.companyId(allocate.getCompanyId())
		        										.status(TaskStatusEnum.Completed.getCode())
		        										.userName(allocate.getUpdateBy())
		        										.endTime(new Date())
		        										.completeTime(new Date())
		        										.taskDetailId(task.getTaskDetailId())
		        										.updateBy(allocate.getUpdateBy())
		        										.updateTime(new Date())
		        										.build();
		        	
		        	taskService.modify(updateTask);
		        }
			});
		});

		return Boolean.TRUE;
	}

	@Override
	public Boolean ship(List<AllocateVO> list) throws BusinessServiceException {
		//按单据执行发运
		Map<String, List<AllocateVO>> outbounds = list.stream().collect(Collectors.groupingBy(AllocateTEntity::getSourceBillNumber));
		outbounds.forEach((k, v) -> {
			if (CollectionUtils.isEmpty(v))
				return;

			long warehouseId = v.get(0).getWarehouseId();
			long companyId = v.get(0).getCompanyId();
			String userName = v.get(0).getUpdateBy();

			InventoryTranVO tran = new InventoryTranVO();
			tran.setTransationType(TransactionTypeEnum.Outbound.getCode());
			tran.setUserName(userName);
			tran.setWarehouseId(warehouseId);
			tran.setCompanyId(companyId);
			tran.setSouceBillNumber(k);
			tran.setAllocateFlag(Boolean.TRUE);

			List<InventoryTranDetailVO> tranList = Lists.newArrayList();
			//明细
			v.forEach(d -> {

				//必须为硬分配
				if (AllocateStrategyTypeEnum.Soft.getCode().equals(d.getAllocateStrategyType()))
						throw new BusinessServiceException("AllocateServiceImpl", "allocate.line.pick.must.hardallocate",new Object[] {d.getSourceLineNumber()}) ;

				InventoryOnhandTEntity sourceOnhand = new InventoryOnhandTEntity();
				BeanUtils.copyBeanProp(sourceOnhand, d, Boolean.FALSE);

				InventoryTranDetailVO tranDetail = new InventoryTranDetailVO();
				BeanUtils.copyBeanProp(tranDetail, d, Boolean.FALSE);
				tranDetail.setTransactionCategory(d.getTransactionCategory());
				tranDetail.setTranQuantity(d.getQuantityShip());
				tranDetail.setSourceNumber(String.valueOf(d.getAllocateId()));
				tranDetail.setSourceLineNumber(String.valueOf(d.getSourceLineNumber()));
				tranDetail.setSourceInventoryOnhand(sourceOnhand);
				//库存处理无需分配量
				tranDetail.setQuantityAllocated(BigDecimal.ZERO);

				//需要更新的数据
				d.setStatus(AllocateStatusEnum.Ship.getCode());
				tranDetail.setAllocate(d);

				tranList.add(tranDetail);
			});
			tran.setDetail(tranList);

			inventoryCoreService.outbound(tran);
		});

		return Boolean.TRUE;
	}

    @Override
    @Transactional
    public Boolean release(List<AllocateVO> list) throws BusinessServiceException {
        List<AllocateTEntity> updateList =Lists.newArrayList();

        list.forEach(d -> {
            LocationTEntity fromLocation =new LocationTEntity();
            LocationTEntity toLocation =new LocationTEntity();

            Set<String> locations = Sets.newHashSet();
            locations.add(d.getLocationCode());
            locations.add(d.getToLocationCode());
            List<LocationTEntity> locationList = locationService.findByLocationCodes(LocationTEntity.builder()
                    .warehouseId(d.getWarehouseId())
                    .companyId(d.getCompanyId())
                    .build(), locations);
           for(LocationTEntity location : locationList){
               if(StringUtils.equals(d.getLocationCode(),location.getLocationCode())){
                   fromLocation =location;
               } else {
                   toLocation =location;
               }
           }

            TaskDetailTEntity taskdetail = TaskDetailTEntity.builder()
                    .createBy(d.getUpdateBy())
                    .createTime(new Date())
                    .updateBy(d.getUpdateBy())
                    .updateTime(new Date())
                    .warehouseId(d.getWarehouseId())
                    .companyId(d.getCompanyId())
                    .sourceNumber(d.getAllocateId())
                    .sourceLineNumber(d.getSourceLineNumber())
                    .sourceBillNumber(d.getSourceBillNumber())
                    .fromLpnNumber(d.getLpnNumber())
                    .ownerId(d.getOwnerId())
                    .ownerCode(d.getOwnerCode())
                    .skuId(d.getSkuId())
                    .skuCode(d.getSkuCode())
                    .lotNumber(d.getLotNumber())
					.quantity(d.getQuantityAllocated())
                    .fromLocationCode(d.getLocationCode())
                    .toLocationCode(d.getToLocationCode())
                    .fromZoneCode(fromLocation.getZoneCode())
                    .toZoneCode(toLocation.getZoneCode())
                    .fromLocationLogical(fromLocation.getLocationLogical())
                    .toLocationLogical(toLocation.getLocationLogical())
                    .taskType(TaskTypeEnum.Pick.getCode())
                    .uom(d.getUom())
                    .packId(d.getPackId())
                    .packCode(d.getPackCode())
                    .userName(d.getUpdateBy())
                    .releaseTime(new Date())
                    .build();
           if(YesNoEnum.Yes.getCode().equals(d.getFullLocationFlag())){
               taskdetail.setSourceType(TaskSourceTypeEnum.PICKBYLOC.toString());
           }else if(YesNoEnum.Yes.getCode().equals(d.getFullLpnFlag())){
               taskdetail.setSourceType(TaskSourceTypeEnum.PICKBYLPN.toString());
           }else {
               taskdetail.setSourceType(TaskSourceTypeEnum.PICKBYPCS.toString());
           }
            taskService.add(taskdetail);

            AllocateTEntity update = AllocateTEntity.builder()
                    .companyId(d.getCompanyId())
                    .warehouseId(d.getWarehouseId())
                    .allocateId(d.getAllocateId())
                    .status(AllocateStatusEnum.Release.getCode())
                    .build();
            updateList.add(update);
        });
        //新增任务后修改分配明细状态
        modify(updateList);
        return Boolean.TRUE;
    }

	@Override
	@Transactional
	public Boolean pick(AjaxRequest<List<AllocateVO>> request) throws BusinessServiceException {
		List<AllocateVO> list = request.getData();
		list.forEach(a -> {
			a.setUpdateBy(request.getUserName());
			a.setWarehouseId(request.getWarehouseId());
			a.setCompanyId(request.getCompanyId());
		});
		boolean pickFlag = pick(list);
		
		//处理短拣
		list.forEach(a -> {
			if (!YesNoEnum.Yes.getCode().equals(a.getShortFlag()))
				return;
			if (a.getAllocateShort() == null)
				return;
			
			AllocateShortTEntity allshort = new AllocateShortTEntity();
			BeanUtils.copyBeanProp(allshort, a);
			allshort.setReason(a.getAllocateShort().getReason());
			allshort.setRemark(a.getAllocateShort().getRemark());
			allshort.setFileId(a.getAllocateShort().getFileId());
			allshort.setQuantityActual(a.getQuantityPick());
			allshort.setQuantityOriginal(a.getQuantityAllocated());
			allocateShortService.add(new AjaxRequest<AllocateShortTEntity>(allshort, request));
		});
		
		Set<String> headerNumber = list.stream().map(AllocateTEntity::getSourceBillNumber).collect(Collectors.toSet());
		headerNumber.forEach(h -> {
			OutboundHeaderTEntity header = outboundHeaderService.find(OutboundHeaderTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).outboundNumber(h).build());
			outboundHeaderService.outboundStatus(header, Boolean.TRUE);
		});
		return pickFlag;
	}

	@Override
	@Transactional
	public Boolean ship(AjaxRequest<List<AllocateVO>> request) throws BusinessServiceException {
		List<AllocateVO> list = request.getData();
		list.forEach(a -> {
			a.setUpdateBy(request.getUserName());
			a.setWarehouseId(request.getWarehouseId());
			a.setCompanyId(request.getCompanyId());
		});
		boolean shipFlag = ship(list);
		Set<String> headerNumber = list.stream().map(AllocateTEntity::getSourceBillNumber).collect(Collectors.toSet());
		headerNumber.forEach(h -> {
			OutboundHeaderTEntity header = outboundHeaderService.find(OutboundHeaderTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).outboundNumber(h).build());
			outboundHeaderService.outboundStatus(header, Boolean.TRUE);
		});
		return shipFlag;
	}

	@Override
	public Boolean delete(AjaxRequest<List<AllocateTEntity>> request) throws BusinessServiceException {
		List<AllocateTEntity> list = request.getData();
		list.forEach(a -> {
			a.setUpdateBy(request.getUserName());
		});
		boolean delFlag = delete(list);
		Set<String> headerNumber = list.stream().map(AllocateTEntity::getSourceBillNumber).collect(Collectors.toSet());
		headerNumber.forEach(h -> {
			OutboundHeaderTEntity header = outboundHeaderService.find(OutboundHeaderTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).outboundNumber(h).build());
			outboundHeaderService.outboundStatus(header, Boolean.TRUE);
		});
		return delFlag;
	}

	@Override
	public ExcelTemplate getExcelTemplate() {
		return new ExcelTemplate<AllocateImportVO>(ExcelTemplateEnum.Allocate.getCode(), AllocateImportVO.class);
	}

	@Override
	public List<AllocateImportVO> exportData(PageRequest request) throws BusinessServiceException {
		List<AllocateImportVO> returnList = Lists.newArrayList();
		List<AllocateTEntity> allocates = find(request);
		if (CollectionUtils.isEmpty(allocates)) {
			return returnList;
		}
		allocates.forEach(d ->{
			AllocateImportVO allocate = new AllocateImportVO();
			BeanUtils.copyBeanProp(allocate, d);
			returnList.add(allocate);
		});
		return returnList;
	}

	/* (non-Javadoc)
	 * @see com.wms.services.outbound.IAllocateService#findBySourceBillNumber(com.wms.entity.auto.AllocateTEntity, java.util.Set, com.wms.common.enums.allocate.AllocateStatusEnum[])
	 */
	@Override
	public List<AllocateTEntity> findBySourceBillNumber(AllocateTEntity allocate, String sourceBillNumber,
			AllocateStatusEnum... status) throws BusinessServiceException {
		
    	AllocateTExample example = new AllocateTExample();
        AllocateTExample.Criteria exampleCriteria = example.createCriteria();
        exampleCriteria.
        andDelFlagEqualTo(YesNoEnum.No.getCode())
        .andWarehouseIdEqualTo(allocate.getWarehouseId())
        .andCompanyIdEqualTo(allocate.getCompanyId())
        .andSourceBillNumberEqualTo(sourceBillNumber);
        
        if (StringUtils.isNotEmpty(allocate.getSkuCode())) {
        	exampleCriteria.andSkuCodeEqualTo(allocate.getSkuCode());
        }
        
        if (status != null && status.length > 0)
        	exampleCriteria.andStatusIn(Lists.newArrayList(status).stream().map(AllocateStatusEnum::getCode).collect(Collectors.toList()));

        List<AllocateTEntity> selectAllocate =  allocateDao.selectByExample(example);
        return selectAllocate;
	}
}
