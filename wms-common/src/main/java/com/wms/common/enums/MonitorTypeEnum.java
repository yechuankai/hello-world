package com.wms.common.enums;

public enum MonitorTypeEnum {
	
	Rest("10"), 
	Exception("20"), 
	
	Request("REQUEST"),
	Response("RESPONSE"),
	Error("ERROR");
	
	private final String code;
	
	private MonitorTypeEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
