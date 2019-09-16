package com.wms.common.enums;

public enum OutboundProcessStatusEnum {

	NotStart("0", "未开始"),
	Allocating("10", "分配中"), 
	Allocated("20", "分配完成"), 
	UnAllocating("30", "撤销分配中"), 
	UnAllocated("40", "撤销分配完成"), 
	Releasing("50", "发放中"), 
	Released("60", "发放完成"), 
	Picking("70", "拣货中"), 
	Picked("80", "拣货完成"),
	UnPicking("83", "撤销拣货中"), 
	UnPicked("85", "撤销拣货完成"),
	Shiping("90", "发运中"),
	Shiped("100", "发运完成");
	
	private final String code;
	private final String desc;

	private OutboundProcessStatusEnum(String code, String desc) {
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
