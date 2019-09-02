package com.wms.common.enums;

public enum MonitorStatusEnum {
	
	Success("10"), Processing("00"), Fail("20");
	
	private final String code;
	
	private MonitorStatusEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
