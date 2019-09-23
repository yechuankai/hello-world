package com.wms.pub.rest.inbound;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.enums.LpnTypeEnum;
import com.wms.common.enums.TaskStatusEnum;
import com.wms.common.enums.TaskTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.LpnTEntity;
import com.wms.entity.auto.PutawayLocationLockTEntity;
import com.wms.entity.auto.SkuTEntity;
import com.wms.entity.auto.TaskDetailTEntity;
import com.wms.services.base.ILocationService;
import com.wms.services.base.ISkuService;
import com.wms.services.core.IPutawayCoreService;
import com.wms.services.core.impl.PutawayCoreServiceImpl;
import com.wms.services.inventory.IInventoryService;
import com.wms.services.inventory.ILpnService;
import com.wms.services.inventory.IPutawayLocationLockService;
import com.wms.services.inventory.ITaskService;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.PutawayVO;

@RestController
@RequestMapping("/services/public/putaway")
public class PutawayRest extends BaseController{
	
	@Autowired
	private IPutawayCoreService putawayCoreService;
	
	@Autowired
	private ILpnService lpnService;
	
	@Autowired
	private ITaskService taskService;
	
	@Autowired
	private ISkuService skuService;
	
	@Autowired
	private ILocationService locationService;
	
	@Autowired
	private IInventoryService inventoryService;
	
	@Autowired
	private IPutawayLocationLockService lockService;
	
	
	/**
	 * 推荐库位
	 * @param req
	 * @return
	 */
	@RequestMapping("lpnRecommend")
	public AjaxResult<PutawayVO> recommend(@RequestBody String req){
		AjaxRequest<LpnTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<LpnTEntity>>() {});
		try {
			LpnTEntity lpn = request.getData();
			//验证输入值为容器还是LPN
			lpn.setWarehouseId(request.getWarehouseId());
			lpn.setCompanyId(request.getCompanyId());
			
			//查询是否存在任务，存在任务则以任务推荐库位为主
			List<TaskDetailTEntity> tasks =  taskService.findByFromLpn(TaskDetailTEntity.builder()
										.warehouseId(request.getWarehouseId())
										.companyId(request.getCompanyId())
										.fromLpnNumber(lpn.getLpnNumber())
										.taskType(TaskTypeEnum.Putaway.getCode())
										.build(), TaskStatusEnum.New, TaskStatusEnum.Processing);
			if (CollectionUtils.isNotEmpty(tasks)) {
				TaskDetailTEntity task = tasks.get(0);
				PutawayVO putaway = new PutawayVO();
				InventoryOnhandVO onhand = new InventoryOnhandVO();
				BeanUtils.copyBeanProp(onhand, task);
				onhand.setQuantityOnhand(task.getQuantity());
				onhand.setLocationCode(task.getFromLocationCode());
				if(LpnTypeEnum.Container.getCode().equals(task.getFromLpnType())) {
					onhand.setContainerNumber(task.getFromLpnNumber());
					putaway.setContainerNumber(task.getFromLpnNumber());
				}else {
					onhand.setLpnNumber(task.getFromLpnNumber());
					putaway.setLpnNumber(task.getFromLpnNumber());
				}
				putaway.setInventoryOnhand(onhand);
				putaway.setLocationCode(task.getFromLocationCode());
				putaway.setToLocationCode(task.getToLocationCode());
				putaway.setLpnType(task.getFromLpnType());
				return success(putaway);
			}
			
			//查找库存
			LpnTypeEnum lpnType = null;
			try {
				LpnTEntity selectLpn = lpnService.find(lpn);
				lpn.setLpnNumber(selectLpn.getLpnNumber());
				lpnType = LpnTypeEnum.Carton;
			} catch (BusinessServiceException e) {
				//not lpnNumber
				lpn.setContainerNumber(lpn.getLpnNumber());
				List<LpnTEntity> selectLpn = lpnService.findByContainerNumber(lpn);
				if (CollectionUtils.isNotEmpty(selectLpn)) {
					lpn.setContainerNumber(lpn.getLpnNumber());
					lpn.setLpnNumber(null);
					lpnType = LpnTypeEnum.Container;
				}else {
					throw new BusinessServiceException("PutawayRest", "lpn.record.not.exists", new Object[] {lpn.getLpnNumber()});
				}
			}
			PutawayVO putaway = putawayCoreService.lpnPutaway(lpn);
			//正常推荐库位，保存至任务表
			if (!PutawayCoreServiceImpl.UNKNOWN.equals(putaway.getToLocationCode())) {
				
				SkuTEntity sku = skuService.find(SkuTEntity.builder()
										.warehouseId(request.getWarehouseId())
										.companyId(request.getCompanyId())
										.skuId(putaway.getInventoryOnhand().getSkuId())
										.build());
				
				List<LocationTEntity> locs = locationService.findByLocationCodes(LocationTEntity.builder()
										.warehouseId(request.getWarehouseId())
										.companyId(request.getCompanyId())
										.build(), Sets.newHashSet(putaway.getInventoryOnhand().getLocationCode(), putaway.getToLocationCode()));
				Map<String, LocationTEntity> locMap = locs.stream().collect(Collectors.toMap(LocationTEntity::getLocationCode, v -> v));
				
				//生成上架任务
				TaskDetailTEntity taskdetail = TaskDetailTEntity.builder()
						.createBy(request.getUserName())
						.createTime(new Date())
						.updateBy(request.getUserName())
						.updateTime(new Date())
						.warehouseId(request.getWarehouseId())
						.companyId(request.getCompanyId())
						.taskType(TaskTypeEnum.Putaway.getCode())
						.sourceType(TaskTypeEnum.Putaway.getCode())
						.status(TaskStatusEnum.Processing.getCode())
						.fromLpnType(lpnType.getCode())
						.ownerCode(putaway.getInventoryOnhand().getOwnerCode())
						.ownerId(putaway.getInventoryOnhand().getOwnerId())
						.skuId(putaway.getInventoryOnhand().getSkuId())
						.skuCode(putaway.getInventoryOnhand().getSkuCode())
						.uom(sku.getUom())
						.packId(sku.getPackId())
						.packCode(sku.getPackCode())
						.lotNumber(putaway.getInventoryOnhand().getLotNumber())
						.fromLpnNumber(lpn.getLpnNumber())
						.fromLocationCode(putaway.getInventoryOnhand().getLocationCode())
						.fromLocationLogical(locMap.get(putaway.getInventoryOnhand().getLocationCode()).getLocationLogical())
						.fromZoneCode(locMap.get(putaway.getInventoryOnhand().getLocationCode()).getZoneCode())
						.toLpnNumber(lpn.getLpnNumber())
						.toLocationCode(putaway.getToLocationCode())
						.toLocationLogical(putaway.getToLocationCode())
						.toZoneCode(locMap.get(putaway.getToLocationCode()).getZoneCode())
						.userName(request.getUserName())
						.releaseTime(new Date())
						.build();
				taskService.add(taskdetail);
				
				putaway.setToZoneCode(taskdetail.getToZoneCode());
			}
			putaway.setLpnType(lpnType.getCode());
			return success(putaway);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	/**
	 * 按LPN确认上架
	 * @param req
	 * @return
	 */
	@RequestMapping("lpnConfirm")
	public AjaxResult<PutawayVO> confirm(@RequestBody String req){
		AjaxRequest<PutawayVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<PutawayVO>>() {});
		try {
			PutawayVO putaway = request.getData();
			//验证目标库位是否存在
			locationService.find(LocationTEntity.builder()
										.warehouseId(request.getWarehouseId())
										.companyId(request.getCompanyId())
										.locationCode(putaway.getLocationCode())
										.build());
			//处理上架确认
			List<LpnTEntity> lpns = Lists.newArrayList();
			LpnTEntity lpn = LpnTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.build();
			InventoryOnhandTEntity inventory = InventoryOnhandTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.build();
			String fromLpnNumber = null;
			if(LpnTypeEnum.Container.getCode().equals(putaway.getLpnType())){
				//容器号
				lpn.setContainerNumber(putaway.getContainerNumber());
				lpns = lpnService.findByContainerNumber(lpn);
				fromLpnNumber = putaway.getContainerNumber();
			}else if(LpnTypeEnum.Carton.getCode().equals(putaway.getLpnType())){
				//lpn
				Set<String> lpnNumbers = Sets.newHashSet(putaway.getLpnNumber());
				lpns = lpnService.findByLpnNumbers(lpn, lpnNumbers);
				fromLpnNumber = putaway.getLpnNumber();
			}
			Set<Long> lpnIds = lpns.stream().map(LpnTEntity::getLpnId).collect(Collectors.toSet());
			List<InventoryOnhandTEntity> inventoryOnhands = inventoryService.findByLpnId(inventory, lpnIds);

			List<InventoryOnhandVO> inventoryOnhandVOs = Lists.newArrayList();
			inventoryOnhands.forEach(v -> {
				InventoryOnhandVO vo = new InventoryOnhandVO(v);
				vo.setToQuantity(v.getQuantityOnhand());
				vo.setToLocationCode(putaway.getToLocationCode());
				inventoryOnhandVOs.add(vo);
			});
			inventoryService.move(new AjaxRequest<List<InventoryOnhandVO>>(inventoryOnhandVOs, request));
			
			//清空锁定的库位
			lpn.setContainerNumber(putaway.getContainerNumber());
			lpn.setLpnNumber(putaway.getLpnNumber());
			lpn.setLpnType(putaway.getLpnType());
			List<PutawayLocationLockTEntity> locks = lockService.find(lpn);
			lockService.delete(locks);
			
			//查询任务
			List<TaskDetailTEntity> taskList = taskService.findByFromLpn(TaskDetailTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.fromLpnNumber(fromLpnNumber)
					.build(), TaskStatusEnum.New, TaskStatusEnum.Processing);
			if (CollectionUtils.isEmpty(taskList))
				return success();
			
			List<TaskDetailTEntity> updateTask = Lists.newArrayList();
			taskList.forEach(t -> {
				TaskDetailTEntity update = TaskDetailTEntity.builder()
											.warehouseId(request.getWarehouseId())
											.companyId(request.getCompanyId())
											.toLocationCode(putaway.getLocationCode())
											.status(TaskStatusEnum.Completed.getCode())
											.startTime(t.getReleaseTime())
											.endTime(new Date())
											.completeTime(new Date())
											.userName(request.getUserName())
											.taskDetailId(t.getTaskDetailId())
											.build();
				updateTask.add(update);
			});
			taskService.modify(new AjaxRequest<List<TaskDetailTEntity>>(updateTask, request));
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
}
