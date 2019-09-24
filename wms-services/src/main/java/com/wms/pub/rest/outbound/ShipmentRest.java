package com.wms.pub.rest.outbound;

import java.math.BigDecimal;
import java.util.Iterator;
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
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.enums.allocate.AllocateStatusEnum;
import com.wms.common.utils.StringUtils;
import com.wms.entity.auto.AllocateTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.LpnTEntity;
import com.wms.entity.auto.OutboundDetailTEntity;
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
			
			PageResult<AllocateTEntity> pageResult = page(page, list);
			List<Map<String, BigDecimal>> quantityList = Lists.newArrayList(); 
			BigDecimal totalPicked= BigDecimal.ZERO;
			BigDecimal totalAllocated = BigDecimal.ZERO;
			
			Iterator<AllocateTEntity> itertor = list.iterator();
			while(itertor.hasNext()) {
				AllocateTEntity l = itertor.next();
				if (AllocateStatusEnum.Ship.getCode().equals(l.getStatus())) {
					itertor.remove();
					continue;
				}
				if (AllocateStatusEnum.Allocated.getCode().equals(l.getStatus())
						|| AllocateStatusEnum.Release.getCode().equals(l.getStatus())){
					totalAllocated = totalAllocated.add(l.getQuantityAllocated());
				}
				if (AllocateStatusEnum.Picked.getCode().equals(l.getStatus())){
					totalPicked = totalPicked.add(l.getQuantityAllocated());
				}
			}
			
			Map<String, BigDecimal> quantityMap = Maps.newHashMap();
			quantityMap.put(OutboundDetailTEntity.Column.quantityAllocated.getJavaProperty(), totalAllocated);
			quantityMap.put(OutboundDetailTEntity.Column.quantityPicked.getJavaProperty(), totalPicked);
			quantityList.add(quantityMap);
			pageResult.setFooter(quantityList);
			
			return pageResult;
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
