package com.wms.common.enums;

/**
 * 用户状态
 * 
 */
public enum OnlineStatusEnum {
	OnLine("Y", "在线"), OffLine("N", "离线");

	private final String code;
	private final String desc;

	private OnlineStatusEnum(String code, String desc) {
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
