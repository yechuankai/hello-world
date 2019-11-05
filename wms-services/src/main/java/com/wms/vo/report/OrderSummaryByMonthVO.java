package com.wms.vo.report;

public class OrderSummaryByMonthVO {

	private String mon;
	private Long inboundCount;
	private Long outboundCount;
	
	public String getMon() {
		return mon;
	}
	public void setMon(String mon) {
		this.mon = mon;
	}
	public Long getInboundCount() {
		return inboundCount;
	}
	public void setInboundCount(Long inboundCount) {
		this.inboundCount = inboundCount;
	}
	public Long getOutboundCount() {
		return outboundCount;
	}
	public void setOutboundCount(Long outboundCount) {
		this.outboundCount = outboundCount;
	}
	
	
	
}
