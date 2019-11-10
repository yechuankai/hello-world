package com.wms.services.inventory.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.AppointmentTypeEnum;
import com.wms.common.enums.LpnTypeEnum;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.enums.TaskStatusEnum;
import com.wms.common.enums.TransactionCategoryEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ITaskDetailTDao;
import com.wms.dao.example.TaskDetailTExample;
import com.wms.entity.auto.AppointmentTEntity;
import com.wms.entity.auto.InboundDetailTEntity;
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.LpnTEntity;
import com.wms.entity.auto.OutboundDetailTEntity;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.PackTEntity;
import com.wms.entity.auto.SkuTEntity;
import com.wms.entity.auto.TaskDetailTEntity;
import com.wms.services.appointment.IAppointmentService;
import com.wms.services.base.ILocationService;
import com.wms.services.base.IPackService;
import com.wms.services.base.ISkuService;
import com.wms.services.inbound.IInboundDetailService;
import com.wms.services.inbound.IInboundHeaderService;
import com.wms.services.inventory.IInventoryService;
import com.wms.services.inventory.ILpnService;
import com.wms.services.inventory.ITaskService;
import com.wms.services.outbound.IOutboundDetailService;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.PackVO;
import com.wms.vo.TaskDetailVO;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.math.BigDecimal.ROUND_FLOOR;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-04 14:43
 **/
@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    ITaskDetailTDao taskdetailTDao;
    @Autowired
	ILocationService locationService;
    @Autowired
	IInventoryService inventoryService;
    @Autowired
	ILpnService lpnService;
    @Autowired
    IInboundHeaderService inboundHeaderService;
    @Autowired
    IInboundDetailService inboundDetailService;
    @Autowired
    IOutboundHeaderService outboundHeaderService;
    @Autowired
    IOutboundDetailService outboundDetailService;
    @Autowired
    IAppointmentService appointmentService;
    @Autowired
    ISkuService skuService;
    @Autowired
    IPackService packService;

    @Override
    public List<TaskDetailTEntity> find(PageRequest request) throws BusinessServiceException {
        TaskDetailTExample example = new TaskDetailTExample();
        TaskDetailTExample.Criteria exampleCriteria = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(TaskDetailTEntity.Column.class, TaskDetailTExample.Criterion.class)
                .criteria(exampleCriteria)
                .data(request)
                .build(request)
                .betweenDate(TaskDetailTEntity.Column.releaseTime.getJavaProperty(),
                        TaskDetailTEntity.Column.completeTime.getJavaProperty(),
                        TaskDetailTEntity.Column.startTime.getJavaProperty(),
                        TaskDetailTEntity.Column.endTime.getJavaProperty()
                )
                .orderby(example);
        exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return taskdetailTDao.selectByExample(example);
    }
    
    @Override
    public List<TaskDetailVO> findUnLoad(PageRequest request) throws BusinessServiceException {
        TaskDetailTExample example = new TaskDetailTExample();
        TaskDetailTExample.Criteria exampleCriteria = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(TaskDetailTEntity.Column.class, TaskDetailTExample.Criterion.class)
                .criteria(exampleCriteria)
                .data(request)
                .build(request)
                .betweenDate(TaskDetailTEntity.Column.releaseTime.getJavaProperty(),
                        TaskDetailTEntity.Column.completeTime.getJavaProperty(),
                        TaskDetailTEntity.Column.startTime.getJavaProperty(),
                        TaskDetailTEntity.Column.endTime.getJavaProperty()
                )
                .orderby(example);
        exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        List<TaskDetailTEntity> list = taskdetailTDao.selectByExample(example);
        if (CollectionUtils.isEmpty(list))
        	return Lists.newArrayList();
        
        //获取入库单据信息
        Set<String> sourceNumber = list.stream().filter(v -> StringUtils.isNotEmpty(v.getSourceBillNumber())).map(TaskDetailTEntity::getSourceBillNumber).collect(Collectors.toSet());
        List<InboundHeaderTEntity> inboundList = inboundHeaderService.findByNumber(InboundHeaderTEntity.builder()
        								.warehouseId(request.getWarehouseId())
        								.companyId(request.getCompanyId())
        								.build(), sourceNumber);
        Map<String, InboundHeaderTEntity> inboundMap = Maps.newHashMap();
        Map<Long, BigDecimal> weightMap = Maps.newHashMap();
        Map<Long, BigDecimal> volumeMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(inboundList)) {
        	inboundMap = inboundList.stream().collect(Collectors.toMap(InboundHeaderTEntity::getInboundNumber, v -> v));
        
        	 //获取明细，计算重量，体积汇总信息
            Set<Long> headerIds = inboundList.stream().map(InboundHeaderTEntity::getInboundHeaderId).collect(Collectors.toSet());
            List<InboundDetailTEntity> inboundDetail = inboundDetailService.findByHeaderIds(InboundDetailTEntity.builder()
        								.warehouseId(request.getWarehouseId())
        								.companyId(request.getCompanyId())
        								.build(), headerIds);
            if (CollectionUtils.isNotEmpty(inboundDetail)) {
            	for (InboundDetailTEntity detail : inboundDetail) {
            		//仅查询预期收货数据
            		if (BigDecimal.ZERO.compareTo(detail.getQuantityExpected()) >= 0) {
            			continue;
            		}
					BigDecimal weight = weightMap.get(detail.getInboundHeaderId());
					if (weight != null) {
						weight  = weight.add(detail.getWeightGross());
					}else {
						weight = detail.getWeightGross();
					}
					weightMap.put(detail.getInboundHeaderId(), weight);
					
					BigDecimal volume = volumeMap.get(detail.getInboundHeaderId());
					if (volume != null) {
						volume  = volume.add(detail.getVolume());
					}else {
						volume = detail.getVolume();
					}
					volumeMap.put(detail.getInboundHeaderId(), volume);
				}
            }
            
        }
        
        //获取泊位信息
        List<AppointmentTEntity> appointmentList = appointmentService.findByBillNumber(AppointmentTEntity.builder()
        								.warehouseId(request.getWarehouseId())
        								.companyId(request.getCompanyId())
        								.type(AppointmentTypeEnum.Inbound.getCode())
        								.build(), sourceNumber);
        
        Map<String, AppointmentTEntity> appointmentMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(appointmentList))
        	appointmentMap = appointmentList.stream().collect(Collectors.toMap(AppointmentTEntity::getSourceBillNumber, v -> v));
        
        List<TaskDetailVO> returnLists = Lists.newArrayList();
        for (TaskDetailTEntity task : list) {
        	TaskDetailVO vo = new TaskDetailVO(task);
        	
        	InboundHeaderTEntity inbound = inboundMap.get(task.getSourceBillNumber());
        	if (inbound != null) {
        		vo.setCarNumber(inbound.getCarrierCarNumber());
        		vo.setContainerNumber(inbound.getContainerNumber());
        		
        		BigDecimal weight = weightMap.get(inbound.getInboundHeaderId());
        		BigDecimal volume = volumeMap.get(inbound.getInboundHeaderId());
        		vo.setWeightGross(weight);
        		vo.setVolume(volume);
        	}
        	
        	AppointmentTEntity appointment = appointmentMap.get(task.getSourceBillNumber());
        	if (appointment != null) {
        		vo.setPlatFormCode(appointment.getPlatformCode());
        	}
        	returnLists.add(vo);
		}
        return returnLists;
    }
    
    @Override
    public List<TaskDetailVO> findLoad(PageRequest request) throws BusinessServiceException {
        TaskDetailTExample example = new TaskDetailTExample();
        TaskDetailTExample.Criteria exampleCriteria = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(TaskDetailTEntity.Column.class, TaskDetailTExample.Criterion.class)
                .criteria(exampleCriteria)
                .data(request)
                .build(request)
                .betweenDate(TaskDetailTEntity.Column.releaseTime.getJavaProperty(),
                        TaskDetailTEntity.Column.completeTime.getJavaProperty(),
                        TaskDetailTEntity.Column.startTime.getJavaProperty(),
                        TaskDetailTEntity.Column.endTime.getJavaProperty()
                )
                .orderby(example);
        exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        List<TaskDetailTEntity> list = taskdetailTDao.selectByExample(example);
        if (CollectionUtils.isEmpty(list))
        	return Lists.newArrayList();
        
        //获取出库单据信息
        Set<String> sourceNumber = list.stream().filter(v -> StringUtils.isNotEmpty(v.getSourceBillNumber())).map(TaskDetailTEntity::getSourceBillNumber).collect(Collectors.toSet());
        List<OutboundHeaderTEntity> outboundList = outboundHeaderService.findByNumber(OutboundHeaderTEntity.builder()
        								.warehouseId(request.getWarehouseId())
        								.companyId(request.getCompanyId())
        								.build(), sourceNumber);
        Map<String, OutboundHeaderTEntity> outboundMap = Maps.newHashMap();
        Map<Long, BigDecimal> weightMap = Maps.newHashMap();
        Map<Long, BigDecimal> volumeMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(outboundList)) {
        	outboundMap = outboundList.stream().collect(Collectors.toMap(OutboundHeaderTEntity::getOutboundNumber, v -> v));
        
        	//获取明细，计算重量，体积汇总信息
            Set<Long> headerIds = outboundList.stream().map(OutboundHeaderTEntity::getOutboundHeaderId).collect(Collectors.toSet());
            List<OutboundDetailTEntity> outboundDetail = outboundDetailService.findByHeaderIds(OutboundDetailTEntity.builder()
        								.warehouseId(request.getWarehouseId())
        								.companyId(request.getCompanyId())
        								.build(), headerIds);
            if (CollectionUtils.isNotEmpty(outboundDetail)) {
            	//获取包装
            	Set<Long> packIds = outboundDetail.stream().map(OutboundDetailTEntity::getPackId).collect(Collectors.toSet());
            	List<PackTEntity> packList = packService.findByIds(PackTEntity.builder()
        								.warehouseId(request.getWarehouseId())
        								.companyId(request.getCompanyId())
        								.build(), packIds);
            	Map<Long, PackTEntity> packMap = packList.stream().collect(Collectors.toMap(PackTEntity::getPackId, v -> v));
            	
            	//获取货品
            	Set<Long> skuIds = outboundDetail.stream().map(OutboundDetailTEntity::getSkuId).collect(Collectors.toSet());
            	List<SkuTEntity> skuList = skuService.findByIds(SkuTEntity.builder()
						.warehouseId(request.getWarehouseId())
						.companyId(request.getCompanyId())
						.build(), skuIds);
            	Map<Long, SkuTEntity> skuMap = skuList.stream().collect(Collectors.toMap(SkuTEntity::getSkuId, v -> v));
            	
            	for (OutboundDetailTEntity detail : outboundDetail) {
            		BigDecimal qty = detail.getQuantityExpected().add(detail.getQuantityShiped());
            		if (BigDecimal.ZERO.compareTo(qty) >= 0) {
            			continue;
            		}
            		
            		//计算单位数量
            		qty = qty.divide(detail.getUomQuantity(), 5, ROUND_FLOOR);
            		
            		PackTEntity pack = packMap.get(detail.getPackId());
            		SkuTEntity sku = skuMap.get(detail.getSkuId());
            		
            		PackVO packVo = packService.getPack(pack, sku, detail.getUom());
            		
					BigDecimal weight = weightMap.get(detail.getOutboundHeaderId());
					if (weight != null) {
						weight  = weight.add(packVo.getWeightGross().multiply(qty));
					}else {
						weight = packVo.getWeightGross().multiply(qty);
					}
					weightMap.put(detail.getOutboundHeaderId(), weight);
					
					BigDecimal volume = volumeMap.get(detail.getOutboundHeaderId());
					if (volume != null) {
						volume  = volume.add(packVo.getVolume().multiply(qty));
					}else {
						volume = packVo.getVolume().multiply(qty);
					}
					volumeMap.put(detail.getOutboundHeaderId(), volume);
				}
            }
            
        }
        
        //获取泊位信息
        List<AppointmentTEntity> appointmentList = appointmentService.findByBillNumber(AppointmentTEntity.builder()
        								.warehouseId(request.getWarehouseId())
        								.companyId(request.getCompanyId())
        								.type(AppointmentTypeEnum.Outbound.getCode())
        								.build(), sourceNumber);
        
        Map<String, AppointmentTEntity> appointmentMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(appointmentList))
        	appointmentMap = appointmentList.stream().collect(Collectors.toMap(AppointmentTEntity::getSourceBillNumber, v -> v));
        
        List<TaskDetailVO> returnLists = Lists.newArrayList();
        for (TaskDetailTEntity task : list) {
        	TaskDetailVO vo = new TaskDetailVO(task);
        	
        	OutboundHeaderTEntity inbound = outboundMap.get(task.getSourceBillNumber());
        	if (inbound != null) {
        		vo.setCarNumber(inbound.getCarNumber());
        		vo.setContainerNumber(inbound.getContainerNumber());
        		
        		BigDecimal weight = weightMap.get(inbound.getOutboundHeaderId());
        		BigDecimal volume = volumeMap.get(inbound.getOutboundHeaderId());
        		vo.setWeightGross(weight);
        		vo.setVolume(volume);
        	}
        	
        	AppointmentTEntity appointment = appointmentMap.get(task.getSourceBillNumber());
        	if (appointment != null) {
        		vo.setPlatFormCode(appointment.getPlatformCode());
        	}
        	returnLists.add(vo);
		}
        return returnLists;
    }

    @Override
    @Transactional
    public Boolean add(TaskDetailTEntity task) throws BusinessServiceException {
        task.setTaskDetailId(KeyUtils.getUID());
        task.setStatus(TaskStatusEnum.New.getCode());
        task.setTaskDetailNumber(KeyUtils.getOrderNumber(task.getCompanyId(), task.getWarehouseId(), OrderNumberTypeEnum.TaskNumber));
        int rowcount = taskdetailTDao.insertSelective(task);
        if (rowcount == 0) {
            throw new BusinessServiceException("record add error.");
        }
        return Boolean.TRUE;
    }

	@Override
	public TaskDetailTEntity findBySourceNumber(TaskDetailTEntity task) throws BusinessServiceException {
		TaskDetailTExample example = new TaskDetailTExample();
	    TaskDetailTExample.Criteria exampleCriteria = example.createCriteria();
	    
	    exampleCriteria
        .andDelFlagEqualTo(YesNoEnum.No.getCode())
        .andWarehouseIdEqualTo(task.getWarehouseId())
        .andCompanyIdEqualTo(task.getCompanyId())
        .andSourceNumberEqualTo(task.getSourceNumber())
	    .andStatusIn(Lists.newArrayList(TaskStatusEnum.New.getCode(), TaskStatusEnum.Processing.getCode(), TaskStatusEnum.Wait.getCode()));

		TaskDetailTEntity selectTask = taskdetailTDao.selectOneByExample(example);
		if (selectTask == null)
		    throw new BusinessServiceException("TaskServiceImpl", "task.record.not.exists", new Object[]{task.getSourceNumber()});
		
		return selectTask;
	}
	
	@Override
	public List<TaskDetailTEntity> findBySourceBillNumber(TaskDetailTEntity task) throws BusinessServiceException {
		TaskDetailTExample example = new TaskDetailTExample();
	    TaskDetailTExample.Criteria exampleCriteria = example.createCriteria();
	    
	    exampleCriteria
        .andDelFlagEqualTo(YesNoEnum.No.getCode())
        .andWarehouseIdEqualTo(task.getWarehouseId())
        .andCompanyIdEqualTo(task.getCompanyId())
        .andSourceBillNumberEqualTo(task.getSourceBillNumber());

	    List<TaskDetailTEntity> selectTask = taskdetailTDao.selectByExample(example);
		if (CollectionUtils.isEmpty(selectTask)) {
			return Lists.newArrayList();
		}
		return selectTask;
	}

	@Override
	public List<TaskDetailTEntity> find(TaskDetailTEntity task) throws BusinessServiceException {
		TaskDetailTExample example = new TaskDetailTExample();
	    TaskDetailTExample.Criteria exampleCriteria = example.createCriteria();
	    
	    exampleCriteria
        .andDelFlagEqualTo(YesNoEnum.No.getCode())
        .andWarehouseIdEqualTo(task.getWarehouseId())
        .andCompanyIdEqualTo(task.getCompanyId());

		int conditionCount = 0;
		if (StringUtils.isNotEmpty(task.getTaskDetailNumber())) {
			exampleCriteria.andFromLpnNumberEqualTo(task.getTaskDetailNumber());
		    conditionCount++;
		}
		if (task.getTaskDetailId() != null) {
			exampleCriteria.andTaskDetailIdEqualTo(task.getTaskDetailId());
		    conditionCount++;
		}
		if(task.getSkuId() != null){
			exampleCriteria.andSkuIdEqualTo(task.getSkuId());
			conditionCount++;
		}
		if(task.getFromLpnNumber() != null){
			exampleCriteria.andFromLpnNumberEqualTo(task.getFromLpnNumber());
			conditionCount++;
		}
		if (conditionCount == 0){
		    return null;
		}

		List<TaskDetailTEntity> selectTask = taskdetailTDao.selectByExample(example);
	    
		return selectTask;
	}

	@Override
	public Boolean modify(TaskDetailTEntity task) throws BusinessServiceException {
		List<TaskDetailTEntity> selectTasks = find(task);
		if(CollectionUtils.isEmpty(selectTasks)){
			throw new BusinessServiceException("TaskServiceImpl", "task.record.not.exists", new Object[]{task.getTaskDetailId()+"/"+task.getTaskDetailNumber()});
		}
		TaskDetailTEntity selectTask=selectTasks.get(0);
		notProcess(selectTask);
		
		TaskDetailTExample example = new TaskDetailTExample();
	    TaskDetailTExample.Criteria exampleCriteria = example.createCriteria();
	    
	    exampleCriteria
        .andDelFlagEqualTo(YesNoEnum.No.getCode())
        .andWarehouseIdEqualTo(task.getWarehouseId())
        .andCompanyIdEqualTo(task.getCompanyId())
        .andTaskDetailIdEqualTo(selectTask.getTaskDetailId());
	    
	    int rowcount = taskdetailTDao.updateWithVersionByExampleSelective(selectTask.getUpdateVersion(), task, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean modify(AjaxRequest<List<TaskDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}

		List<TaskDetailTEntity> list = request.getData();

		for (TaskDetailTEntity task : list) {
			
			if (StringUtils.isNotEmpty(task.getToLocationCode())) {
				String locationCode = task.getToLocationCode().toUpperCase();
				LocationTEntity location = locationService.find(LocationTEntity.builder()
						.locationCode(locationCode)
						.warehouseId(request.getWarehouseId())
						.companyId(request.getCompanyId())
						.build());
				task.setToLocationCode(locationCode);
				task.setToZoneCode(location.getZoneCode());
			}
			
			if (TaskStatusEnum.Completed.getCode().equals(task.getStatus())) {
				if (task.getCompleteTime() == null 
						&& task.getEndTime() != null)
					task.setCompleteTime(task.getEndTime());
				
				if (task.getEndTime() == null 
						&& task.getCompleteTime() != null)
					task.setEndTime(task.getCompleteTime());
				
				if (task.getEndTime() == null 
						&& task.getCompleteTime() == null)
					task.setEndTime(new Date());
					task.setCompleteTime(task.getEndTime());
			}

			TaskDetailTEntity update = TaskDetailTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.userName(task.getUserName())
					.userCompany(task.getUserCompany())
					.weightGross(task.getWeightGross())
					.weightNet(task.getWeightNet())
					.weightTare(task.getWeightTare())
					.volume(task.getVolume())
					.toLocationCode(task.getToLocationCode())
					.toZoneCode(task.getToZoneCode())
					.startTime(task.getStartTime())
					.reason(task.getReason())
					.endTime(task.getEndTime())
					.completeTime(task.getCompleteTime())
					.status(task.getStatus())
					.taskDetailId(task.getTaskDetailId())
					.warehouseId(task.getWarehouseId())
					.companyId(task.getCompanyId())
					.build();
			modify(update);
		}
		return Boolean.TRUE;
	}

	private Boolean notProcess(TaskDetailTEntity task) {
		if (TaskStatusEnum.Completed.getCode().equals(task.getStatus())
				|| TaskStatusEnum.Cancel.getCode().equals(task.getStatus())) {
			throw new BusinessServiceException("TaskServiceImpl", "task.status.not.process" , new Object[] {task.getTaskDetailNumber()});
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean cancel(AjaxRequest<List<TaskDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}

		List<TaskDetailTEntity> list = request.getData();

		for (TaskDetailTEntity task : list) {
			notProcess(task);
			TaskDetailTEntity update = TaskDetailTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.status(TaskStatusEnum.Cancel.getCode())
					.build();

			TaskDetailTExample example = new TaskDetailTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(task.getWarehouseId())
					.andCompanyIdEqualTo(task.getCompanyId())
					.andTaskDetailIdEqualTo(task.getTaskDetailId());

			int row = taskdetailTDao.updateWithVersionByExampleSelective(task.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("cancel update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean putawayConfirm(AjaxRequest<List<TaskDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}

		List<TaskDetailTEntity> list = request.getData();
		list.forEach(d ->{
			if(!StringUtils.equals(TaskStatusEnum.New.getCode(),d.getStatus())){
				return;
			}
			List<LpnTEntity> lpns =Lists.newArrayList();
			LpnTEntity lpnTEntity = LpnTEntity.builder()
					.warehouseId(d.getWarehouseId())
					.companyId(d.getCompanyId())
					.build();
			InventoryOnhandTEntity inventory = InventoryOnhandTEntity.builder()
					.warehouseId(d.getWarehouseId())
					.companyId(d.getCompanyId())
					.build();
			if(StringUtils.equals(LpnTypeEnum.Container.getCode(),d.getFromLpnType())){
				//容器号
				lpnTEntity.setContainerNumber(d.getFromLpnNumber());
				lpns = lpnService.findByContainerNumber(lpnTEntity);
			}else if(StringUtils.equals(LpnTypeEnum.Carton.getCode(), d.getFromLpnType())){
				//lpn
				Set<String> lpnNumbers = Sets.newHashSet();
				lpnNumbers.add(d.getFromLpnNumber());
				lpns = lpnService.findByLpnNumbers(lpnTEntity, lpnNumbers);
			}
			Set<Long> lpnIds = lpns.stream().map(LpnTEntity::getLpnId).collect(Collectors.toSet());
			List<InventoryOnhandTEntity> inventoryOnhands=inventoryService.findByLpnId(inventory,lpnIds);

			List<InventoryOnhandVO> inventoryOnhandVOs = Lists.newArrayList();
			inventoryOnhands.forEach(v -> {
				InventoryOnhandVO vo = new InventoryOnhandVO(v);
				vo.setToQuantity(v.getQuantityOnhand());
				vo.setToLocationCode(d.getToLocationCode());
				vo.setTransactionCategory(TransactionCategoryEnum.PCPutaway.getCode());
				inventoryOnhandVOs.add(vo);
			});

			boolean falg = inventoryService.move(new AjaxRequest<List<InventoryOnhandVO>>(inventoryOnhandVOs, request));
			if(falg){
				//成功修改状态
				TaskDetailTEntity update = TaskDetailTEntity.builder()
						.updateBy(request.getUserName())
						.updateTime(new Date())
						.status(TaskStatusEnum.Completed.getCode())
						.taskDetailId(d.getTaskDetailId())
						.warehouseId(d.getWarehouseId())
						.companyId(d.getCompanyId())
						.build();
				if (d.getStartTime() != null)
					update.setStartTime(d.getStartTime());
				else
					update.setStartTime(d.getReleaseTime());
				
				if (d.getEndTime() != null)
					update.setEndTime(d.getEndTime());
				else
					update.setEndTime(new Date());
				
				if (d.getCompleteTime() != null)
					update.setCompleteTime(d.getCompleteTime());
				else
					update.setCompleteTime(new Date());
				
				modify(update);
			}
		});
		return Boolean.TRUE;
	}

	@Override
	public List<TaskDetailTEntity> findByFromLpn(TaskDetailTEntity task, TaskStatusEnum... taskStatusEnums)
			throws BusinessServiceException {
		TaskDetailTExample example = new TaskDetailTExample();
	    TaskDetailTExample.Criteria exampleCriteria = example.createCriteria();
	    
	    exampleCriteria
        .andDelFlagEqualTo(YesNoEnum.No.getCode())
        .andWarehouseIdEqualTo(task.getWarehouseId())
        .andCompanyIdEqualTo(task.getCompanyId())
        .andFromLpnNumberEqualTo(task.getFromLpnNumber());
	    
	    if (StringUtils.isNotEmpty(task.getTaskType())) {
	    	exampleCriteria.andTaskTypeEqualTo(task.getTaskType());
	    }
	    
	    if (taskStatusEnums != null) {
	    	List<String> statusList = Lists.newArrayList();
	    	for (TaskStatusEnum taskStatusEnum : taskStatusEnums) {
	    		statusList.add(taskStatusEnum.getCode());
			}
	    	exampleCriteria.andStatusIn(statusList);
	    }
	    List<TaskDetailTEntity> selectTask = taskdetailTDao.selectByExample(example);
		
		return selectTask;
	}

	@Override
	public Boolean delete(AjaxRequest<List<TaskDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}

		List<TaskDetailTEntity> list = request.getData();

		for (TaskDetailTEntity task : list) {
			notProcess(task);
			TaskDetailTEntity update = TaskDetailTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.delFlag(YesNoEnum.Yes.getCode())
					.build();

			TaskDetailTExample example = new TaskDetailTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(task.getWarehouseId())
					.andCompanyIdEqualTo(task.getCompanyId())
					.andTaskDetailIdEqualTo(task.getTaskDetailId());

			int row = taskdetailTDao.updateWithVersionByExampleSelective(task.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("cancel update error.");
			}
		}
		return Boolean.TRUE;
	}
}
