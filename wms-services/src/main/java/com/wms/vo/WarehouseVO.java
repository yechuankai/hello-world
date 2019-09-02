package com.wms.vo;

import com.wms.entity.BaseEntity;

public class WarehouseVO extends BaseEntity{

	private Long warehouseId;
	private String warehouseCode;
	private String warehouseDescr;
	
	private Long companyId;
	private String companyCode;
	private String companyDescr;
	
	public WarehouseVO(){}
	
	public WarehouseVO(Long companyId, Long warehouseId){
		this.companyId = companyId;
		this.warehouseId = warehouseId;
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

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyDescr() {
		return companyDescr;
	}

	public void setCompanyDescr(String companyDescr) {
		this.companyDescr = companyDescr;
	}
	
	
	
}
