package com.wms.common.enums;

public enum BarCodeTypeEnum {
	Lpn("LPN", "LPN/容器");
	private final String code;
	private final String desc;

	private BarCodeTypeEnum(String code, String desc) {
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
