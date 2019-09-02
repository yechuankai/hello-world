package com.wms.common.enums.allocate;

public enum AllocateTypeEnum {
	
	Noraml("NORMAL", "普通分配");

	private final String code;
	private final String desc;

	private AllocateTypeEnum(String code, String desc) {
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
