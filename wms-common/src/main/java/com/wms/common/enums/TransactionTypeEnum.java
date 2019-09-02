package com.wms.common.enums;

public enum TransactionTypeEnum {

	Inbound("I", "入库"), 
	Outbound("O", "出库"), 
	Move("M", "移动"), 
	Adjustment("A", "调整"); 
	
	private final String code;
	private final String desc;

	private TransactionTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static TransactionTypeEnum getTransationTypeEnum(String code) {
		switch (code) {
		case "I":
			return TransactionTypeEnum.Inbound;
		case "O":
			return TransactionTypeEnum.Outbound;
		default:
			break;
		}
		return null;
	}
	
	
}
