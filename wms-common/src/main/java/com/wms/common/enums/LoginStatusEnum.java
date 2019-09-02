package com.wms.common.enums;

/**
 * 用户会话
 * 
 */
public enum LoginStatusEnum {
	
	/** 登录状态 */
	Login("Login", "登录"),Logout("Logout","登出"), Fail("LoginFail", "失败");

	private final String code;
	private final String desc;

	private LoginStatusEnum(String code, String desc) {
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
