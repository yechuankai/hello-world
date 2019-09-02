package com.wms.common.enums;

public enum AdjustmentStatusEnum {
	New("10", "新建"), Adjustmented("20", "已调整");
	private final String code;
	private final String desc;

	private AdjustmentStatusEnum(String code, String desc) {
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
