package com.wms.common.enums;

public enum FileStatusEnum {
	
	Init("0", "初始化"), 
	Fail("10", "失败"),
	Upload("20","上传成功"), 
	Process("30", "处理中"), 
	ProcessSuccess("40", "处理成功"),
	ProcessFail("50", "处理失败"),;

	private final String code;
	private final String desc;

	private FileStatusEnum(String code, String desc) {
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
