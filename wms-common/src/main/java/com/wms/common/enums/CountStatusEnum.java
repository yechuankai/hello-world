package com.wms.common.enums;

/**
 * 盘点状态
 * @author yechuankai.chnet
 *
 */
public enum CountStatusEnum {

	New("10", "新建"), 
	Counting("20", "盘点中"), 
	Complated("30", "已完成"), 
	Replay("35", "复盘中"), 
	Post("40", "已过账"), 
	Cancel("50", "已取消");
	
	private final String code;
	private final String desc;

	private CountStatusEnum(String code, String desc) {
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
