package com.wms.common.enums;

public enum LogBusinessTypeEnum {

	File("File", "文件操作"),
	Inbound("INBOUND","入库操作"), 
	Outbound("OUTBOUND", "出库操作");

	private final String code;
	private final String desc;

	private LogBusinessTypeEnum(String code, String desc) {
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
