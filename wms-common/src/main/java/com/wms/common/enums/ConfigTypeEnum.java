
package com.wms.common.enums;

/**
 * 参数类型
 *
 */
public enum ConfigTypeEnum {

	OnOff("O","开关"),
	Value("V", "数值");
	
	private final String code;
	private final String desc;

	private ConfigTypeEnum(String code, String desc) {
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
