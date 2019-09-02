package com.wms.common.enums;

public enum OperatorTypeEnum {

	Add("ADD", "增加"),
	Submit("SUBMIT","提交"),
	Review("REVIEW","审核"),
	Confirm("CONFIRM","确认"),
	Delete("DELETE","删除"), 
	Modify("MOIDFY", "修改"),
	Other("OTHER", "其它");

	private final String code;
	private final String desc;

	private OperatorTypeEnum(String code, String desc) {
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
