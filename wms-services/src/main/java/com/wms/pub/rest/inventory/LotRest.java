package com.wms.pub.rest.inventory;

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
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.LotAttributeTEntity;
import com.wms.entity.auto.SkuTEntity;
import com.wms.services.base.ISkuService;
import com.wms.services.inventory.ILotService;
import com.wms.vo.InventoryCountDetailVO;
import com.wms.vo.LotAttributeVO;
import com.wms.vo.LotLabelVO;

@RestController("publicLotRest")
@RequestMapping("/services/public/inventory/lot")
public class LotRest extends BaseController {
	@Autowired
	private ILotService lotService;
	@Autowired
	private ISkuService skuService;
	
	@RequestMapping(value = "/find")
	public PageResult<LotAttributeTEntity> find(@RequestBody String req) {
		List<LotAttributeTEntity> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = lotService.find(pageRequest);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	@RequestMapping(value = "/findByLotNumber")
	public PageResult<LotAttributeVO> findByLotNumber(@RequestBody String req) {
		try {
			AjaxRequest<LotAttributeTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<LotAttributeTEntity>>() {});
			LotAttributeTEntity lotRequest = request.getData();
			if (lotRequest == null)
				return page(Lists.newArrayList());
			
			if (StringUtils.isEmpty(lotRequest.getLotNumber()))
				return page(Lists.newArrayList());
			
			lotRequest.setCompanyId(request.getCompanyId());
			lotRequest.setWarehouseId(request.getWarehouseId());
			String [] lotNumbers = lotRequest.getLotNumber().split(",");
			List<LotAttributeTEntity> list = lotService.findBylotNumbers(lotRequest, Sets.newHashSet(lotNumbers));
			if (CollectionUtils.isEmpty(list))
				return page(Lists.newArrayList());
			
			List<LotAttributeVO> returnList = Lists.newArrayList();
			list.forEach(v -> {
				LotAttributeVO vo = new LotAttributeVO();
				BeanUtils.copyBeanProp(vo, v);
				LotLabelVO labelVo = skuService.getSkuLotLabel(SkuTEntity.builder()
												.companyId(request.getCompanyId())
												.warehouseId(request.getWarehouseId())
												.ownerCode(v.getOwnerCode())
												.skuCode(v.getSkuCode())
												.build());
				vo.setLotLabel(labelVo);
				returnList.add(vo);
			});
			return page(returnList);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		
	}

}
