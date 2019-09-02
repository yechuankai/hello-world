package com.wms.common.enums;

public enum AuthTypeEnum {
	
	Cas("CAS", "CAS");

	private final String code;
	private final String desc;

	private AuthTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
