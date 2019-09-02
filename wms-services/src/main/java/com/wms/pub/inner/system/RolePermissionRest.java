package com.wms.pub.inner.system;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.entity.auto.SysRolePermissionTEntity;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.services.sys.ISysRolePermissionService;
import com.wms.vo.PermissionTreeVO;

@RestController
@RequestMapping("/services/inner/rolePermission")
public class RolePermissionRest extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(RolePermissionRest.class);
	
	@Autowired
	ISysRolePermissionService rolePermissionService;
	
	@RequestMapping(value = "/find")
	public List<PermissionTreeVO> find(SysRoleTEntity role) {
		List<PermissionTreeVO> list = null;
		try {
			List<SysRoleTEntity> roles = Lists.newArrayList(role);
			PermissionTreeVO tree = rolePermissionService.findAll2Tree(roles);
			list = Lists.newArrayList(tree);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}
	
	
	@RequestMapping(value = "/add")
	public AjaxResult add(@RequestBody String req) {
		try {
			AjaxRequest<List<SysRolePermissionTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysRolePermissionTEntity>>>() {});
			boolean flag = rolePermissionService.add(request);
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
			AjaxRequest<SysRolePermissionTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<SysRolePermissionTEntity>>() {});
			boolean flag = rolePermissionService.delete(request);
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}

}
