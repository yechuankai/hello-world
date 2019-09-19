package com.wms.common.enums;

import com.wms.common.utils.StringUtils;

public enum OperatorTypeEnum {

	Add("ADD", "增加"),
	Submit("SUBMIT","提交"),
	Review("REVIEW","审核"),
	Reject("REJECT","拒绝"),
	Confirm("CONFIRM","确认"),
	Delete("DELETE","删除"), 
	Modify("MODIFY", "修改"),
	Cancel("CANCEL","取消"), 
	Complate("COMPLATE","完成"), 
	Other("OTHER", "其它");

	private final String code;
	private final String desc;
	
	private OperatorTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static OperatorTypeEnum get(String opertor) {
		if (StringUtils.isEmpty(opertor))
			return null;
		
		OperatorTypeEnum [] enums = OperatorTypeEnum.values();
		for (OperatorTypeEnum o : enums) {
			if (o.getCode().equalsIgnoreCase(opertor))
				return o;
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
}
