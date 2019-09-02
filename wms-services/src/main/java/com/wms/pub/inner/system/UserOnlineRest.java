package com.wms.pub.inner.system;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.SysUserOnlineTEntity;
import com.wms.entity.auto.SysWarehousesTEntity;
import com.wms.services.sys.ISysUserOnlineService;
import com.wms.services.sys.ISysWarehouseService;

@RestController
@RequestMapping("/services/inner/useronline")
public class UserOnlineRest extends BaseController{
	
	@Autowired
	ISysUserOnlineService onlineService;
	
	@RequestMapping(value = "/find")
	public PageResult<SysUserOnlineTEntity> find(@RequestBody String req) {
		List<SysUserOnlineTEntity> list = null;
		try {
			PageRequest request = pageRequest(req);
			PageHelper.startPage(request.getPageStart(), request.getPageSize());
			list = onlineService.find(request);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
}
