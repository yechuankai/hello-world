package com.wms.common.enums;

/**
 * 单据号类型
 * 
 */
public enum OrderNumberTypeEnum {
	
	Inbound("INBOUND", "入库单"), 
	Outbound("OUTBOUND","出库单"),
	Adjustment("ADJUSTMENT", "调整单"),
	Transfer("TRANSFER", "移动单"),
	Lot("LOT", "批次号"),
	TaskNumber("TASKNUMBER","任务号"),
	RequestNumber("REQUESTNUMBER","请求单号"),
	CountNumber("COUNT", "盘点单号"),
	WaveTemplate("WAVETEMPLATE", "波次模板编号"),
	Wave("WAVE", "波次单号"),
	Appointment("APPOINTMENT", "预约单号");

	private final String code;
	private final String desc;

	private OrderNumberTypeEnum(String code, String desc) {
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
