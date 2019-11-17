
package com.wms.common.enums;

/**
 * 状态历史类型
 * @author yechuankai.chnet
 *
 */
public enum StatusHistoryTypeEnum {

	Inbound("INBOUND","入库"),
	Outbound("OUTBOUND", "出库");
	
	private final String code;
	private final String desc;

	private StatusHistoryTypeEnum(String code, String desc) {
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
