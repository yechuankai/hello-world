package com.wms.pub.rest.outbound;

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
import com.wms.entity.auto.AllocateTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.LpnTEntity;
import com.wms.services.outbound.IAllocateService;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.allocate.AllocateVO;

@RestController("publicShipmentRest")
@RequestMapping("/services/public/shipment")
public class ShipmentRest extends BaseController {
	
	@Autowired
	private IAllocateService allocateService;

	/**
	 * 查找分配明细
	 * 可按装载LPN或出库订单号进行查询，查询所有（包括未拣货）
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/find")
	public PageResult<AllocateTEntity> find(@RequestBody String req) {
		List<AllocateTEntity> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			Page page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = allocateService.find(pageRequest);
			return page(page, list);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
	}
	
	
	/**
	 * 按LPN发运
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "confirm")
	public AjaxResult lpnMove(@RequestBody String req) {
		AjaxRequest<List<AllocateVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AllocateVO>>>() {});
		try {
			allocateService.ship(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
}
