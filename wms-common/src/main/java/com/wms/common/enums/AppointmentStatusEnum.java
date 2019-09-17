package com.wms.common.enums;

/**
 * 预约状态
 * @author yechuankai.chnet
 *
 */
public enum AppointmentStatusEnum {
	Appointment("10", "已预约"), 
	Arrived("20", "已到达"),
	WaitLeave("30", "待离开"),
	Leave("40", "已离开"),
	Cancel("50", "已取消");
	
	private final String code;
	private final String desc;

	private AppointmentStatusEnum(String code, String desc) {
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
