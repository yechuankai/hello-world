package com.wms.common.enums;

public enum MonitorTimeEnum {
	
	T_0_100("0-100"),
	T_100_500("100-500"),
	T_500_1000("500-1000"),
	T_1000_MORE(">1000");

	private final String code;

	private MonitorTimeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
