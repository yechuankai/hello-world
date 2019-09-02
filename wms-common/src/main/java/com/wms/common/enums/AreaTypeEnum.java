package com.wms.common.enums;

public enum AreaTypeEnum {
	PK("PK", "拣货"),
	RP("RP", "补货");
	private final String code;
	private final String desc;

	private AreaTypeEnum(String code, String desc) {
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
