package com.wms.common.enums.allocate;

public enum AllocateStatusEnum {
	
	Allocated("10", "分配"),
	Release("15", "发放"),
	Picked("20", "拣货"),
	Load("30", "装载"),
	Ship("40", "发运");

	private final String code;
	private final String desc;

	private AllocateStatusEnum(String code, String desc) {
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
