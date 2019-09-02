package com.wms.pub.inner.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.LpnTEntity;
import com.wms.services.inventory.ILpnService;

@RestController
@RequestMapping("/services/inner/inventory/lpn")
public class LpnRest extends BaseController {
	@Autowired
	ILpnService lpnService;
	
	@RequestMapping(value = "/find")
	public PageResult<LpnTEntity> find(@RequestBody String req) {
		List<LpnTEntity> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = lpnService.find(pageRequest);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}

}
