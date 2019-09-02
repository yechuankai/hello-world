package com.wms.services.inventory.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.LpnTypeEnum;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.enums.TaskStatusEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ITaskDetailTDao;
import com.wms.dao.example.TaskDetailTExample;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.LpnTEntity;
import com.wms.entity.auto.TaskDetailTEntity;
import com.wms.services.base.ILocationService;
import com.wms.services.inventory.IInventoryService;
import com.wms.services.inventory.ILpnService;
import com.wms.services.inventory.ITaskService;
import com.wms.vo.InventoryOnhandVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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
    @Transactional
    public Boolean add(TaskDetailTEntity task) throws BusinessServiceException {
        task.setTaskDetailId(KeyUtils.getUID());
        task.setStatus(TaskStatusEnum.New.getCode());
        task.setTaskDetailNumber(KeyUtils.getOrderNumber(task.getCompanyId(), task.getWarehouseId(), OrderNumberTypeEnum.TASKNUMBER));
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

			String locationCode =task.getToLocationCode().toUpperCase();

			LocationTEntity location = locationService.find(LocationTEntity.builder()
					.locationCode(locationCode)
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.build());

			TaskDetailTEntity update = TaskDetailTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.toLocationCode(locationCode)
					.toZoneCode(location.getZoneCode())
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
	public Boolean cancel(AjaxRequest<List<TaskDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}

		List<TaskDetailTEntity> list = request.getData();

		for (TaskDetailTEntity task : list) {
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
	public Boolean putawayCnfirm(AjaxRequest<List<TaskDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}

		List<TaskDetailTEntity> list = request.getData();
		list.forEach(d ->{
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
				lpns=lpnService.findByContainerNumber(lpnTEntity);
			}else if(StringUtils.equals(LpnTypeEnum.Pallet.getCode(),d.getFromLpnType())){
				//lpn
				Set<String> lpnNumbers = Sets.newHashSet();
				lpnNumbers.add(d.getToLpnNumber());
				lpns=lpnService.findByLpnNumbers(lpnTEntity,lpnNumbers);
			}
			Set<Long> lpnIds = lpns.stream().map(LpnTEntity::getLpnId).collect(Collectors.toSet());
			List<InventoryOnhandTEntity> inventoryOnhands=inventoryService.findByLpnId(inventory,lpnIds);

			List<InventoryOnhandVO> inventoryOnhandVOs =Lists.newArrayList();
			inventoryOnhands.forEach(v ->{
				InventoryOnhandVO vo =new InventoryOnhandVO(v);
				vo.setToQuantity(v.getQuantityOnhand());
				vo.setToLocationCode(d.getToLocationCode());
				inventoryOnhandVOs.add(vo);
			});

			boolean falg=inventoryService.move(new AjaxRequest<List<InventoryOnhandVO>>(inventoryOnhandVOs, request));
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
				modify(update);
			}
		});
		return Boolean.TRUE;
	}
}
