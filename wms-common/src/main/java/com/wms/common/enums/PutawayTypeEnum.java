package com.wms.common.enums;

public enum PutawayTypeEnum {
	TYPE_10("10", "从指定库位移出时将库存上架到目标库位"),
	TYPE_20("20", "从指定库位移出时将库存上架到目标库区中空库位"),
	TYPE_30("30", "从指定库区移出时将库存上架到目标库位"),
	TYPE_40("40", "从指定库区移出时将库存上架到目标库区中空库位"),
	TYPE_50("50", "上架到货品主数据配置的上架库区中的空库位"),
	TYPE_60("60", "上架到货品主数据配置的上架库位"),
	TYPE_100("100", "上架到指定库位");

	private final String code;
	private final String desc;

	private PutawayTypeEnum(String code, String desc) {
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
