package com.wms.common.enums.allocate;

public enum AllocateStrategyTypeEnum {
	
	Hard("10", "硬分配"),Soft("20", "软分配");

	private final String code;
	private final String desc;

	private AllocateStrategyTypeEnum(String code, String desc) {
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
