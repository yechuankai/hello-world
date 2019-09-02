package com.wms.vo;

import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.SysUserRoleTEntity;

public class UserRoleVO extends SysUserRoleTEntity{

	private String roleDescr;
	private String roleCode;
	
	public String getRoleDescr() {
		return roleDescr;
	}
	public void setRoleDescr(String roleDescr) {
		this.roleDescr = roleDescr;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public UserRoleVO(){
	}
	
	public UserRoleVO(SysUserRoleTEntity userRole){
		BeanUtils.copyBeanProp(this, userRole, Boolean.TRUE);
	}
	
	
	
}
