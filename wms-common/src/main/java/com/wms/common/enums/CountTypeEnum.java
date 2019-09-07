package com.wms.common.enums;

/**
 * 盘点类型
 * @author yechuankai.chnet
 *
 */
public enum CountTypeEnum {

	Detail("0","按明细"),
	Normal("10", "普通");
	
	private final String code;
	private final String desc;

	private CountTypeEnum(String code, String desc) {
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
