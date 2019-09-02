package com.wms.common.enums;

/**
 * 登录类型
 */
public enum LoginTypeEnum {
	
	/** 登录类型 */
	Normal("NORMAL", "普通登录"),
	SSO("SSO","单点登录");

	private final String code;
	private final String desc;

	private LoginTypeEnum(String code, String desc) {
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
