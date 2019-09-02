package com.wms.common.enums;

/**
 * 用户会话
 * 
 */
public enum RoleDataTypeEnum {
	
	Company("10", "公司"), Warehouse("20", "仓库");

	private final String code;
	private final String desc;

	private RoleDataTypeEnum(String code, String desc) {
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
