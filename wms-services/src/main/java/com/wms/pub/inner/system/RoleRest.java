package com.wms.pub.inner.system;

import java.util.ArrayList;
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
import com.wms.common.utils.StringUtils;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.services.sys.ISysRoleService;

@RestController
@RequestMapping("/services/inner/role")
public class RoleRest extends BaseController{
	
	@Autowired
	ISysRoleService roleService;
	
	@RequestMapping(value = "/find")
	public PageResult<SysRoleTEntity> find(@RequestBody String req) {
		List<SysRoleTEntity> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = roleService.find(pageRequest);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	
	@RequestMapping(value = "/save")
	public AjaxResult save(@RequestBody String req) {
		try {
			AjaxRequest<List<SysRoleTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysRoleTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = roleService.modify(request);
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}
	
	@RequestMapping(value = "/delete")
	public AjaxResult delete(@RequestBody String req) {
		try {
			AjaxRequest<List<SysRoleTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysRoleTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = roleService.delete(request);
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}
	
	@RequestMapping(value = "/add")
	public AjaxResult add(@RequestBody String req) {
		try {
			AjaxRequest<SysRoleTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<SysRoleTEntity>>() {});
			List<SysRoleTEntity> updateList = Lists.newArrayList(request.getData());
			boolean flag = roleService.add(ajaxRequest(updateList, request));
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}

}
