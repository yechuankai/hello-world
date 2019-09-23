package com.wms.vo.excel;

import java.math.BigDecimal;
import java.util.Date;

import com.wms.common.core.domain.AbstractExcelModel;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class BillingLeaseExcelVO extends AbstractExcelModel{
	
	@Excel(name = "billingDate")
	private Date billingDate;

	@Excel(name = "billingMonth")
    private String billingMonth;

	@Excel(name = "billingLeaseCode")
    private String billingLeaseCode;

	@Excel(name = "ownerCode")
    private String ownerCode;
	
	@Excel(name = "sourceBillNumber")
    private String sourceBillNumber;
	
	@Excel(name = "skuCode")
    private String skuCode;
	
	@Excel(name = "days")
    private Long days;
	
	@Excel(name = "packCode")
    private String packCode;
	
	@Excel(name = "uom")
    private String uom;
	
	@Excel(name = "inboundDate")
    private Date inboundDate;
	
	@Excel(name = "quantityReceived")
    private BigDecimal quantityReceived;
	
	@Excel(name = "outboundDate")
    private Date outboundDate;
	
	@Excel(name = "quantityShiped")
    private BigDecimal quantityShiped;
	
	@Excel(name = "volume")
    private BigDecimal volume;
	
	@Excel(name = "weightGross")
    private BigDecimal weightGross;
	
	@Excel(name = "weightNet")
    private BigDecimal weightNet;
	
	@Excel(name = "weightTare")
    private BigDecimal weightTare;

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public String getBillingMonth() {
		return billingMonth;
	}

	public void setBillingMonth(String billingMonth) {
		this.billingMonth = billingMonth;
	}

	public String getBillingLeaseCode() {
		return billingLeaseCode;
	}

	public void setBillingLeaseCode(String billingLeaseCode) {
		this.billingLeaseCode = billingLeaseCode;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getSourceBillNumber() {
		return sourceBillNumber;
	}

	public void setSourceBillNumber(String sourceBillNumber) {
		this.sourceBillNumber = sourceBillNumber;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Long getDays() {
		return days;
	}

	public void setDays(Long days) {
		this.days = days;
	}

	public String getPackCode() {
		return packCode;
	}

	public void setPackCode(String packCode) {
		this.packCode = packCode;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Date getInboundDate() {
		return inboundDate;
	}

	public void setInboundDate(Date inboundDate) {
		this.inboundDate = inboundDate;
	}

	public BigDecimal getQuantityReceived() {
		return quantityReceived;
	}

	public void setQuantityReceived(BigDecimal quantityReceived) {
		this.quantityReceived = quantityReceived;
	}

	public Date getOutboundDate() {
		return outboundDate;
	}

	public void setOutboundDate(Date outboundDate) {
		this.outboundDate = outboundDate;
	}

	public BigDecimal getQuantityShiped() {
		return quantityShiped;
	}

	public void setQuantityShiped(BigDecimal quantityShiped) {
		this.quantityShiped = quantityShiped;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getWeightGross() {
		return weightGross;
	}

	public void setWeightGross(BigDecimal weightGross) {
		this.weightGross = weightGross;
	}

	public BigDecimal getWeightNet() {
		return weightNet;
	}

	public void setWeightNet(BigDecimal weightNet) {
		this.weightNet = weightNet;
	}

	public BigDecimal getWeightTare() {
		return weightTare;
	}

	public void setWeightTare(BigDecimal weightTare) {
		this.weightTare = weightTare;
	}

}
