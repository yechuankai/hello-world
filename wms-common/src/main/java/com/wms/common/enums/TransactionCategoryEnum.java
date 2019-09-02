package com.wms.common.enums;

public enum TransactionCategoryEnum {

	PCReceive("PCRECEIVE", "PC收货"), 
	PCAllReceive("PCALLRECEIVE", "PC全部收货"), 
	RFReceive("RFRECEIVE", "移动端收货"), 
	PCMove("PCMOVE", "PC移动"), 
	PCPick("PCPICK", "PC拣货"), 
	PCShip("PCSHIP", "PC发货"), 
	RFInbound("RFINBOUND", "移动收货"), 
	Outbound("O", "出库"), 
	Move("M", "移动"), 
	Adjustment("A", "调整"); 
	
	private final String code;
	private final String desc;

	private TransactionCategoryEnum(String code, String desc) {
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
