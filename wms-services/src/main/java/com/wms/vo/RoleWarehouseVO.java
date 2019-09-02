package com.wms.vo;

import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.SysRoleDataTEntity;

public class RoleWarehouseVO extends SysRoleDataTEntity{

	private String warehouseDescr;
	private String warehouseCode;
	
	
	public RoleWarehouseVO(){
	}
	
	public RoleWarehouseVO(SysRoleDataTEntity roleData){
		BeanUtils.copyBeanProp(this, roleData, Boolean.TRUE);
	}

	public String getWarehouseDescr() {
		return warehouseDescr;
	}

	public void setWarehouseDescr(String warehouseDescr) {
		this.warehouseDescr = warehouseDescr;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	
	
	
}
