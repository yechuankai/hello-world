package com.wms.common.enums.allocate;

import com.wms.common.utils.StringUtils;

public enum AllocateUOMEnum {
	
	Lpn("LPN", "按LPN"),
	Pcs("PCS", "按件"),
	Loc("LOC", "按库位");

	private final String code;
	private final String desc;

	private AllocateUOMEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static AllocateUOMEnum get(String code) {
		if (StringUtils.isEmpty(code))
			return null;
		
		AllocateUOMEnum [] enums = AllocateUOMEnum.values();
		for (AllocateUOMEnum e : enums) {
			if (e.getCode().toUpperCase().equals(code.toUpperCase()))
				return e;
		}
		return null;
	}
}
