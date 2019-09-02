package com.wms.common.enums;

public enum YesNoEnum {

	Yes("Y"), No("N");
	
	private final String code;
	
	private YesNoEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
}
