package com.wms.pub.rest.inventory;

import java.math.BigDecimal;
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
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.enums.CountStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.InventoryCountDetailTEntity;
import com.wms.entity.auto.InventoryCountHeaderTEntity;
import com.wms.entity.auto.InventoryCountRequestTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.ZoneTEntity;
import com.wms.services.base.ILocationService;
import com.wms.services.inventory.IInventoryCountDetailService;
import com.wms.services.inventory.IInventoryCountHeaderService;
import com.wms.services.inventory.IInventoryCountRequestService;
import com.wms.services.inventory.IInventoryService;
import com.wms.vo.InventoryCountDetailVO;
import com.wms.vo.InventoryCountHeaderVO;
import com.wms.vo.InventoryOnhandVO;

@RestController("publicInventoryCountRest")
@RequestMapping("/services/public/inventory/count")
public class InventoryCountRest extends BaseController  {

	@Autowired
	private IInventoryCountHeaderService countHeaderService;
	@Autowired
	private IInventoryCountDetailService countDetailService;
	@Autowired
	private ILocationService locationService;
	@Autowired
	private IInventoryService inventoryService;
	@Autowired
	private IInventoryCountRequestService requestService;

	
	@RequestMapping("getCount")
	public AjaxResult<InventoryCountHeaderVO> getCount(@RequestBody String req){
		AjaxRequest<InventoryCountHeaderTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<InventoryCountHeaderTEntity>>() {});
		try {
			InventoryCountHeaderTEntity count = request.getData();
			count.setWarehouseId(request.getWarehouseId());
			count.setCompanyId(request.getCompanyId());
			InventoryCountHeaderTEntity selectHeader = countHeaderService.find(count);
			if (CountStatusEnum.Cancel.getCode().equals(selectHeader.getStatus())
					|| CountStatusEnum.Post.getCode().equals(selectHeader.getStatus()))
				throw new BusinessServiceException("InventoryCountRest", "count.status.not.process" , new Object[] {selectHeader.getCountNumber()});
			
			InventoryCountRequestTEntity requestTEntity = requestService.find(InventoryCountRequestTEntity.builder()
																				.warehouseId(request.getWarehouseId())
																				.companyId(request.getCompanyId())
																				.inventoryCountRequestId(selectHeader.getInventoryCountRequestId())
																				.build());
			if (null == requestTEntity) 
				throw new BusinessServiceException("InventoryCountRest", "count.not.exists" , new Object[] {selectHeader.getCountNumber()});
			
			InventoryCountHeaderVO countHeaderVO = new InventoryCountHeaderVO();
			BeanUtils.copyBeanProp(countHeaderVO, selectHeader, Boolean.FALSE);
			countHeaderVO.setQuantityShowFlag(requestTEntity.getQuantityShowFlag());
			return success(countHeaderVO);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	/**
	 * 获取盘点明细，按库位、货品分组
	 * @param req
	 * @return
	 */
	@RequestMapping("getLocationCountDetail")
	public PageResult<InventoryCountDetailTEntity> getLocationCountDetail(@RequestBody String req){
		AjaxRequest<InventoryCountDetailVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<InventoryCountDetailVO>>() {});
		try {
			InventoryCountDetailVO countDetail = request.getData();
			countDetail.setWarehouseId(request.getWarehouseId());
			countDetail.setCompanyId(request.getCompanyId());
			
			Set<Long> locIds = Sets.newHashSet();
			if (StringUtils.isNotEmpty(countDetail.getZoneCode())) {
				PageRequest locationRequest = pageRequest(req);
				locationRequest.put(ZoneTEntity.Column.zoneCode.getJavaProperty(), countDetail.getZoneCode().toUpperCase().concat("*"));
				List<LocationTEntity> locationList = locationService.find(locationRequest);
				
				if (CollectionUtils.isEmpty(locationList))
					return page(Lists.newArrayList());
		
				locIds = locationList.stream().map(LocationTEntity::getLocationId).collect(Collectors.toSet());
			}
			
			//获取所有盘点明细
			List<InventoryCountDetailTEntity> selectDetail = countDetailService.findByHeaderId(InventoryCountDetailTEntity.builder()
																								.warehouseId(request.getWarehouseId())
																								.companyId(request.getCompanyId())
																								.inventoryCountHeaderId(countDetail.getInventoryCountHeaderId())
																								.build(), CountStatusEnum.New, CountStatusEnum.Counting);
			
			if (CollectionUtils.isEmpty(selectDetail))
				return page(Lists.newArrayList());
			
			final Set<Long> _locIdSet = locIds;
			Map<String, InventoryCountDetailTEntity> locationCountMap = Maps.newHashMap();
			selectDetail.forEach(cd -> {
				if (StringUtils.isNotEmpty(countDetail.getZoneCode()) 
						&& !_locIdSet.contains(cd.getLocationId())) {
					return;
				}
				
				String key = cd.getLocationCode() + cd.getSkuCode();
				if (locationCountMap.containsKey(key)) {
					return;
				}else {
					locationCountMap.put(key, cd);
				}
			});
			
			List<InventoryCountDetailTEntity> returnList = locationCountMap.values().stream().collect(Collectors.toList());
			return page(returnList);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
	}
	
	/**
	 * 显示盘点明细，按货品、库位查询
	 * @param req
	 * @return
	 */
	@RequestMapping("getCountDetail")
	public PageResult<InventoryCountDetailTEntity> getCountDetail(@RequestBody String req){
		try {
			AjaxRequest<InventoryCountDetailVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<InventoryCountDetailVO>>() {});
			InventoryCountDetailVO countDetail = request.getData();
			countDetail.setWarehouseId(request.getWarehouseId());
			countDetail.setCompanyId(request.getCompanyId());
			
			if (StringUtils.isEmpty(countDetail.getLocationCode())) {
				throw new BusinessServiceException("InventoryCountRest", "inventory.locationcode.isnull", null);
			}
			
			if (StringUtils.isEmpty(countDetail.getSkuCode())) {
				throw new BusinessServiceException("InventoryCountRest", "sku.isnull", null);
			}
			
			//获取所有盘点明细
			List<InventoryCountDetailTEntity> selectDetail = countDetailService.findByHeaderId(InventoryCountDetailTEntity.builder()
																								.warehouseId(request.getWarehouseId())
																								.companyId(request.getCompanyId())
																								.inventoryCountHeaderId(countDetail.getInventoryCountHeaderId())
																								.skuCode(countDetail.getSkuCode())
																								.locationCode(countDetail.getLocationCode())
																								.build(), CountStatusEnum.New, CountStatusEnum.Counting);
			if (CollectionUtils.isEmpty(selectDetail)) {
				throw new BusinessServiceException("InventoryCountRest", "inventory.count.detail.isnull", null);
			}
			selectDetail.forEach(cd -> {
				//查询不到库存则默认为0
				BigDecimal countQuantity = BigDecimal.ZERO;
				try {
					//查询系统库存
					InventoryOnhandTEntity onhand = inventoryService.find(InventoryOnhandTEntity.builder()
														.warehouseId(request.getWarehouseId())
														.companyId(request.getCompanyId())
														.inventoryOnhandId(cd.getInventoryOnhandId())
														.build());
					countQuantity = onhand.getQuantityOnhand();
				}catch(BusinessServiceException e) {}
				
				//设置判断数量
				cd.setQuantityCount(countQuantity);
			});
			
			// 设置校验数量
			BigDecimal validateQty = BigDecimal.ZERO;
			// 筛选LPN
			List<String> lpnList = Lists.newArrayList(Sets.newHashSet(selectDetail.stream()
									.map(InventoryCountDetailTEntity::getLpnNumber).collect(Collectors.toSet())));
			// 筛选lot
			List<String> lotList = Lists.newArrayList(Sets.newHashSet(selectDetail.stream()
									.map(InventoryCountDetailTEntity::getLotNumber).collect(Collectors.toSet())));
			for (InventoryCountDetailTEntity entity: selectDetail) {
				validateQty = validateQty.add(entity.getQuantityCount());
			}
			Map<String, Object> footMap = Maps.newHashMap();
			footMap.put("validateQty", validateQty);
			footMap.put("lpnList", lpnList);
			footMap.put("lotList", lotList);
			List<Map<String, Object>> footList = Lists.newArrayList();
			footList.add(footMap);
			
			PageResult<InventoryCountDetailTEntity> resultPage = page(selectDetail);
			resultPage.setFooter(footList);
			return resultPage;
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
	}
	
	/**
	 * 第一个弹出框按库位盘点无差异，则直接将库位 货品对应的所有盘点明细更新为盘点完成，并且无差异
	 * @param req
	 * @return
	 */
	@RequestMapping("locationConfirm")
	public AjaxResult locationConfirm(@RequestBody String req){
		AjaxRequest<InventoryCountDetailVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<InventoryCountDetailVO>>() {});
		try {
			InventoryCountDetailVO countDetail = request.getData();
			countDetail.setWarehouseId(request.getWarehouseId());
			countDetail.setCompanyId(request.getCompanyId());
			
			if (StringUtils.isEmpty(countDetail.getLocationCode())) {
				throw new BusinessServiceException("InventoryCountRest", "sku.isnull", null);
			}
			
			if (StringUtils.isEmpty(countDetail.getSkuCode())) {
				throw new BusinessServiceException("InventoryCountRest", "inventory.locationcode.isnull", null);
			}
			
			//获取所有盘点明细
			List<InventoryCountDetailTEntity> selectDetail = countDetailService.findByHeaderId(InventoryCountDetailTEntity.builder()
																								.warehouseId(request.getWarehouseId())
																								.companyId(request.getCompanyId())
																								.inventoryCountHeaderId(countDetail.getInventoryCountHeaderId())
																								.skuCode(countDetail.getSkuCode())
																								.locationCode(countDetail.getLocationCode())
																								.build(), CountStatusEnum.New, CountStatusEnum.Counting);
			
			if (CollectionUtils.isEmpty(selectDetail))
				throw new BusinessServiceException("InventoryCountRest", "no detail update.", null);
			
			selectDetail.forEach(cd -> {
				//查询不到库存则默认为0
				BigDecimal countQuantity = BigDecimal.ZERO;
				try {
					//查询系统库存
					InventoryOnhandTEntity onhand = inventoryService.find(InventoryOnhandTEntity.builder()
														.warehouseId(request.getWarehouseId())
														.companyId(request.getCompanyId())
														.inventoryOnhandId(cd.getInventoryOnhandId())
														.build());
					countQuantity = onhand.getQuantityOnhand();
				}catch(BusinessServiceException e) {}
				
				//设置判断数量
				cd.setQuantityCount(countQuantity);
			});
			
			//更新盘点数量
			countDetailService.modify(new AjaxRequest<List<InventoryCountDetailTEntity>>(selectDetail, request));
			
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	
	
	/**
	 * 按明细进行盘点
	 * @param req
	 * @return
	 */
	@RequestMapping("confirm")
	public AjaxResult confirm(@RequestBody String req){
		try {
			AjaxRequest<List<InventoryCountDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InventoryCountDetailTEntity>>>() {});
			List<InventoryCountDetailTEntity> list = request.getData();
			if (CollectionUtils.isEmpty(list))
				return fail("no data.");
			
			//更新盘点数量
			countDetailService.confirm(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	
}
