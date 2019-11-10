package com.wms.vo.excel;

import com.wms.common.core.domain.AbstractExcelModel;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 通关关系匹配
 * @author yechuankai.chnet
 *
 */
public class CustomsClearanceImportVO extends AbstractExcelModel{

	//入库单号
	@Excel(name = "inboundNumber")
	private String inboundNumber;
	//来源单号
	@Excel(name = "sourceBillNumber")
	private String sourceBillNumber;
	//申报单号
	@Excel(name = "declareNumber")
	private String declareNumber;
	
	public String getInboundNumber() {
		return inboundNumber;
	}
	public void setInboundNumber(String inboundNumber) {
		this.inboundNumber = inboundNumber;
	}
	public String getSourceBillNumber() {
		return sourceBillNumber;
	}
	public void setSourceBillNumber(String sourceBillNumber) {
		this.sourceBillNumber = sourceBillNumber;
	}
	public String getDeclareNumber() {
		return declareNumber;
	}
	public void setDeclareNumber(String declareNumber) {
		this.declareNumber = declareNumber;
	}
	
}
