package com.wms.pub.inner.inventory;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.TypeReference;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.LotAttributeTEntity;
import com.wms.entity.auto.SysConfigTEntity;
import com.wms.services.inventory.ILotService;

@RestController
@RequestMapping("/services/inner/inventory/lot")
public class LotRest extends BaseController {
	@Autowired
	ILotService lotService;
	
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

}
