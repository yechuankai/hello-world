package com.wms.vo;

import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.SysRoleDataTEntity;

public class RoleCompanyVO extends SysRoleDataTEntity{

	private String companyDescr;
	private String companyCode;
	
	
	public RoleCompanyVO(){
	}
	
	public RoleCompanyVO(SysRoleDataTEntity roleData){
		BeanUtils.copyBeanProp(this, roleData, Boolean.TRUE);
	}

	public String getCompanyDescr() {
		return companyDescr;
	}

	public void setCompanyDescr(String companyDescr) {
		this.companyDescr = companyDescr;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	
	
}
