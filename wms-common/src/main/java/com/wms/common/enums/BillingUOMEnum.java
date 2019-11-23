package com.wms.common.enums;

public enum BillingUOMEnum {
	Weigth("WEIGHT", "重量"),
	Volume("VOLUME", "体积");
	private final String code;
	private final String desc;

	private BillingUOMEnum(String code, String desc) {
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
