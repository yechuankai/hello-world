package com.wms.pub.rest.inventory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.utils.StringUtils;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.LpnTEntity;
import com.wms.services.base.ILocationService;
import com.wms.services.inventory.IInventoryService;
import com.wms.services.inventory.ILpnService;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.PutawayVO;

@RestController("publicInventoryRest")
@RequestMapping("/services/public/inventory")
public class InventoryRest extends BaseController {
	@Autowired
	private IInventoryService inventoryService;
	@Autowired
	private ILpnService lpnService;
	@Autowired
	private ILocationService locationService;

	/**
	 * 多维度查找库存
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/find")
	public PageResult<InventoryOnhandVO> find(@RequestBody String req) {
		List<InventoryOnhandVO> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			return inventoryService.find(pageRequest);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
	}
	
	/**
	 * 按容器号查找库存
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/containerFind")
	public PageResult<InventoryOnhandVO> containerFind(@RequestBody String req) {
		List<InventoryOnhandVO> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			String containerNumber = pageRequest.getString(LpnTEntity.Column.containerNumber.getJavaProperty());
			if(StringUtils.isEmpty(containerNumber))
				return page(Lists.newArrayList());
			Page page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			return inventoryService.find(pageRequest);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
	}
	
	/**
	 * 按LPN移动
	 * 可移动目标容器号、目标库位，不可移动数量
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/lpnMove")
	public AjaxResult lpnMove(@RequestBody String req) {
		AjaxRequest<InventoryOnhandVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<InventoryOnhandVO>>() {});
		try {
			InventoryOnhandVO moveOnhand = request.getData();
			locationService.find(LocationTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.locationCode(moveOnhand.getLocationCode())
					.build());
			
			LpnTEntity lpn = lpnService.find(LpnTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.lpnNumber(moveOnhand.getLpnNumber())
					.build());
			
			List<InventoryOnhandTEntity> inventoryOnhands = inventoryService.findByLpnId(InventoryOnhandTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.build(), Sets.newHashSet(lpn.getLpnId()));
			
			List<InventoryOnhandVO> inventoryOnhandVOs = Lists.newArrayList();
			inventoryOnhands.forEach(v -> {
				InventoryOnhandVO vo = new InventoryOnhandVO(v);
				vo.setToQuantity(v.getQuantityOnhand());
				vo.setToLocationCode(moveOnhand.getToLocationCode());
				vo.setContainerNumber(moveOnhand.getToContainerNumber());
				inventoryOnhandVOs.add(vo);
			});
			inventoryService.move(new AjaxRequest<List<InventoryOnhandVO>>(inventoryOnhandVOs, request));
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	/**
	 * 按容器号移动库存
	 * 可移动目标容器号、目标库位，不可移动数量
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/containerMove")
	public AjaxResult containerMove(@RequestBody String req) {
		AjaxRequest<InventoryOnhandVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<InventoryOnhandVO>>() {});
		try {
			InventoryOnhandVO moveOnhand = request.getData();
			locationService.find(LocationTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.locationCode(moveOnhand.getLocationCode())
					.build());
			
			List<LpnTEntity> lpns = lpnService.findByContainerNumber(LpnTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.containerNumber(moveOnhand.getContainerNumber())
					.build());
			
			Set<Long> lpnIds = lpns.stream().map(LpnTEntity::getLpnId).collect(Collectors.toSet());
			List<InventoryOnhandTEntity> inventoryOnhands = inventoryService.findByLpnId(InventoryOnhandTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.build(), lpnIds);
			
			List<InventoryOnhandVO> inventoryOnhandVOs = Lists.newArrayList();
			inventoryOnhands.forEach(v -> {
				InventoryOnhandVO vo = new InventoryOnhandVO(v);
				vo.setToQuantity(v.getQuantityOnhand());
				vo.setToLocationCode(moveOnhand.getToLocationCode());
				vo.setContainerNumber(moveOnhand.getToContainerNumber());
				inventoryOnhandVOs.add(vo);
			});
			inventoryService.move(new AjaxRequest<List<InventoryOnhandVO>>(inventoryOnhandVOs, request));
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	/**
	 * 按库位移动库存
	 * 可移动目标库位，不可移动数量
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/locationMove")
	public AjaxResult locationMove(@RequestBody String req) {
		AjaxRequest<InventoryOnhandVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<InventoryOnhandVO>>() {});
		try {
			InventoryOnhandVO moveOnhand = request.getData();
			locationService.find(LocationTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.locationCode(moveOnhand.getLocationCode())
					.build());
			
			List<InventoryOnhandTEntity> inventoryOnhands = inventoryService.findByLocationId(InventoryOnhandTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.build(), Sets.newHashSet(moveOnhand.getLocationId()));
			
			List<InventoryOnhandVO> inventoryOnhandVOs = Lists.newArrayList();
			inventoryOnhands.forEach(v -> {
				InventoryOnhandVO vo = new InventoryOnhandVO(v);
				vo.setToQuantity(v.getQuantityOnhand());
				vo.setToLocationCode(moveOnhand.getToLocationCode());
				inventoryOnhandVOs.add(vo);
			});
			inventoryService.move(new AjaxRequest<List<InventoryOnhandVO>>(inventoryOnhandVOs, request));
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/move")
	public AjaxResult move(@RequestBody String req) {
		AjaxRequest<InventoryOnhandVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<InventoryOnhandVO>>() {});
		try {
			InventoryOnhandVO moveOnhand = request.getData();
			inventoryService.move(new AjaxRequest<List<InventoryOnhandVO>>(Lists.newArrayList(moveOnhand), request));
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
}
