package com.wms.common.enums.allocate;

import com.wms.common.utils.StringUtils;

public enum AllocateRuleSortEnum {
	
	Location("locationCode", "按库位代码"),
	locationLogical("locationLogical", "按库位路线"),
	Clean("Clean", "清空库存");

	private final String code;
	private final String desc;

	private AllocateRuleSortEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static AllocateRuleSortEnum get(String code) {
		if (StringUtils.isEmpty(code))
			return null;
		
		AllocateRuleSortEnum [] enums = AllocateRuleSortEnum.values();
		for (AllocateRuleSortEnum e : enums) {
			if (e.getCode().toUpperCase().equals(code.toUpperCase()))
				return e;
		}
		return null;
	}
}
