package com.wms.common.enums;

/**
 * 预约类型
 * @author yechuankai.chnet
 *
 */
public enum AppointmentTypeEnum {
	
	Inbound("IN", "入库"),
	Outbound("OUT", "出库");
	
	private final String code;
	private final String desc;
	
	private AppointmentTypeEnum(String code, String desc) {
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
