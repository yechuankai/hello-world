package com.wms.common.enums;

public enum InboundStatusEnum {

	Draft("0","草稿"),
	WaitingReview("3","待审核"),
	WaitingDeclaration("5","待报关"),
	New("10", "新建"), 
	InReceive("20", "收货中"), 
	Receive("30", "已收货"), 
	Closed("40", "已关闭"), 
	Cancel("50", "已取消");
	
	private final String code;
	private final String desc;

	private InboundStatusEnum(String code, String desc) {
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
