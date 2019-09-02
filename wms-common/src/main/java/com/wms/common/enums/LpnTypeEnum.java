package com.wms.common.enums;

public enum LpnTypeEnum {
	
	Container("10", "容器"), 
	Pallet("20", "托盘"),
	Carton("30","纸箱");

	private final String code;
	private final String desc;

	private LpnTypeEnum(String code, String desc) {
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
