package com.wms.common.enums;

public enum ExcelTypeEnum {
	
	XLS(".xls"), 
	XLSX(".xlsx");
	
	private final String code;

	private ExcelTypeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
