package com.wms.common.enums;

/**
 *  权限类型
 * 
 */
public enum PermissionTypeEnum {
	
	Menu("M", "菜单"), Function("F","功能");

	private final String code;
	private final String desc;

	private PermissionTypeEnum(String code, String desc) {
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
