package com.wms.common.enums;

public enum UserStatusEnum {

	Disable("N"), Enable("Y");
	
	private final String code;
	
	private UserStatusEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
}
