package com.wms.common.enums;

/**
 *  月台状态
 * @author yechuankai.chnet
 *
 */
public enum PlatFormStatusEnum {
	Idle("10", "空闲"), 
	Appointment("20", "已预约"), 
	Arrived("30", "已到达"),
	WaitLeave("40", "待离开"),
	Cancel("50", "已禁用");
	
	private final String code;
	private final String desc;

	private PlatFormStatusEnum(String code, String desc) {
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
