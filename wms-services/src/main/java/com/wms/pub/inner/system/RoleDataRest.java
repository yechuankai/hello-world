package com.wms.pub.inner.system;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.enums.RoleDataTypeEnum;
import com.wms.entity.auto.SysCompanysTEntity;
import com.wms.entity.auto.SysRoleDataTEntity;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysWarehousesTEntity;
import com.wms.services.sys.ISysCompanyService;
import com.wms.services.sys.ISysRoleDataService;
import com.wms.services.sys.ISysWarehouseService;
import com.wms.vo.RoleCompanyVO;
import com.wms.vo.RoleWarehouseVO;

@RestController
@RequestMapping("/services/inner/roleData")
public class RoleDataRest extends BaseController{
	
	@Autowired
	private ISysRoleDataService roleDataService;
	
	@Autowired
	private ISysWarehouseService warehouseService;
	
	@Autowired
	private ISysCompanyService companyService;
	
	@RequestMapping(value = "/haveWarehouse")
	public PageResult<RoleWarehouseVO> haveWarehouse(@RequestBody String req) {
		List<RoleWarehouseVO> list = null;
		try {
			PageRequest request = pageRequest(req);
			String roleId = request.getString(SysRoleDataTEntity.Column.roleId.getJavaProperty());
			SysRoleTEntity role = SysRoleTEntity.builder().roleId(Long.parseLong(roleId)).build();
			list = roleDataService.findRoleWarehouse(Lists.newArrayList(role));
			if (list == null) list = Lists.newArrayList();
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	@RequestMapping(value = "/findWarehouse")
	public PageResult<SysWarehousesTEntity> findWarehouse(@RequestBody String req) {
		List<SysWarehousesTEntity> list = null;
		try {
			SysRoleTEntity role = JSON.parseObject(req, SysRoleTEntity.class);
			list = warehouseService.findRoleAvailable(role);
			if (list == null) list = Lists.newArrayList();
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	@RequestMapping(value = "/haveCompany")
	public PageResult<RoleCompanyVO> haveCompany(@RequestBody String req) {
		List<RoleCompanyVO> list = null;
		try {
			PageRequest request = pageRequest(req);
			String roleId = request.getString(SysRoleDataTEntity.Column.roleId.getJavaProperty());
			SysRoleTEntity role = SysRoleTEntity.builder().roleId(Long.parseLong(roleId)).build();
			list = roleDataService.findRoleCompany(Lists.newArrayList(role));
			if (list == null) list = Lists.newArrayList();
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	@RequestMapping(value = "/findCompany")
	public PageResult<SysCompanysTEntity> findCompany(@RequestBody String req) {
		List<SysCompanysTEntity> list = null;
		try {
			SysRoleTEntity role = JSON.parseObject(req, SysRoleTEntity.class);
			list = companyService.findRoleAvailable(role);
			if (list == null) list = Lists.newArrayList();
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	
	@RequestMapping(value = "/delete")
	public AjaxResult delete(@RequestBody String req) {
		try {
			AjaxRequest<List<SysRoleDataTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysRoleDataTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = roleDataService.delete(request);
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}
	
	@RequestMapping(value = "/addWarehouse")
	public AjaxResult addWarehouse(@RequestBody String req) {
		try {
			AjaxRequest<List<SysRoleDataTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysRoleDataTEntity>>>() {});
			List<SysRoleDataTEntity> add = request.getData();
			add.forEach(d -> {
				d.setDataType(RoleDataTypeEnum.Warehouse.getCode());
			});
			boolean flag = roleDataService.add(ajaxRequest(add, request));
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}
	
	@RequestMapping(value = "/addCompany")
	public AjaxResult addCompany(@RequestBody String req) {
		try {
			AjaxRequest<List<SysRoleDataTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysRoleDataTEntity>>>() {});
			List<SysRoleDataTEntity> add = request.getData();
			add.forEach(d -> {
				d.setDataType(RoleDataTypeEnum.Company.getCode());
			});
			boolean flag = roleDataService.add(ajaxRequest(add, request));
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}

}
