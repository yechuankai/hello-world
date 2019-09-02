package com.wms.common.enums;

public enum AdjustmentReasonEnum {
	Normal("NORMAL", "普通调整"), UnReceive("UNRECEIVE", "撤销收货"), Cc("CC", "盘点差异确认");
	private final String code;
	private final String desc;

	private AdjustmentReasonEnum(String code, String desc) {
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
