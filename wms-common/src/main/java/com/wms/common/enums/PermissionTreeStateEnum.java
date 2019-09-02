package com.wms.common.enums;

public enum PermissionTreeStateEnum {

	Open("open"), Closed("closed");
	
	private final String code;
	
	private PermissionTreeStateEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
