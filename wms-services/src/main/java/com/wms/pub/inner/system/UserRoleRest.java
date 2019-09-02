package com.wms.pub.inner.system;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.services.sys.ISysRoleService;
import com.wms.services.sys.ISysUserRoleService;
import com.wms.vo.UserRoleVO;

@RestController
@RequestMapping("/services/inner/userRole")
public class UserRoleRest extends BaseController{
	
	@Autowired
	ISysUserRoleService userRoleService;
	
	@Autowired
	ISysRoleService roleService;
	
	@RequestMapping(value = "/find")
	public PageResult<UserRoleVO> find(@RequestBody String req) {
		List<UserRoleVO> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = userRoleService.find(pageRequest);
			if (list == null) list = Lists.newArrayList();
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	@RequestMapping(value = "/findRole")
	public PageResult<SysRoleTEntity> findRole(@RequestBody String req) {
		List<SysRoleTEntity> list = null;
		try {
			SysUserTEntity user = JSON.parseObject(req, SysUserTEntity.class);
			list = roleService.findUserAvailable(ajaxRequest(user));
			if (list == null) list = Lists.newArrayList();
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	
	@RequestMapping(value = "/delete")
	public AjaxResult delete(@RequestBody String req) {
		try {
			AjaxRequest<List<UserRoleVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<UserRoleVO>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = userRoleService.delete(request);
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
			AjaxRequest<List<UserRoleVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<UserRoleVO>>>() {});
			boolean flag = userRoleService.add(request);
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}

}
