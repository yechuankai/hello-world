package com.wms.common.enums.allocate;

import com.wms.common.utils.StringUtils;

public enum AllocateRuleFIFOEnum {
	
	Lot4("lotAttribute4", "批属性4"),
	Lot5("lotAttribute5", "批属性5"),
	Lot11("lotAttribute11", "批属性11"),
	Lot12("lotAttribute12", "批属性12");

	private final String code;
	private final String desc;

	private AllocateRuleFIFOEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static AllocateRuleFIFOEnum get(String code) {
		if (StringUtils.isEmpty(code))
			return null;
		
		AllocateRuleFIFOEnum [] enums = AllocateRuleFIFOEnum.values();
		for (AllocateRuleFIFOEnum e : enums) {
			if (e.getCode().toUpperCase().equals(code.toUpperCase()))
				return e;
		}
		return null;
	}
}
