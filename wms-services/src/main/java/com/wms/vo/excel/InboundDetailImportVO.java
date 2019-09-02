package com.wms.vo.excel;

import java.math.BigDecimal;
import java.util.Date;

import com.wms.common.core.domain.AbstractExcelModel;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class InboundDetailImportVO  extends AbstractExcelModel {
	
	@Excel(name = "inboundNumber")
	private String inboundNumber;
	
	@Excel(name = "sourceNumber")
    private String sourceNumber;

	@Excel(name = "referenceNumber")
    private String referenceNumber;

	@Excel(name = "type")
    private String type;
	
	@Excel(name = "ownerCode")
    private String ownerCode;

	@Excel(name = "supplierCode")
    private String supplierCode;

	@Excel(name = "expectedInboundDate")
    private Date expectedInboundDate;

	@Excel(name = "carrierCode")
    private String carrierCode;

	@Excel(name = "carrierCarNumber")
    private String carrierCarNumber;

	@Excel(name = "carrierDriver")
    private String carrierDriver;

	@Excel(name = "shipAddress1")
    private String shipAddress1;

	@Excel(name = "shipAddress2")
    private String shipAddress2;

	@Excel(name = "lineNumber")
	private Long lineNumber;
	
	@Excel(name = "sourceLineNumber")
    private Long sourceLineNumber;

	@Excel(name = "poNumber")
    private String poNumber;

	@Excel(name = "poLineNumber")
    private String poLineNumber;

	@Excel(name = "skuCode")
    private String skuCode;

	@Excel(name = "uom")
    private String uom;

	@Excel(name = "packCode")
    private String packCode;

	@Excel(name = "uomQuantityExpected")
    private BigDecimal uomQuantityExpected;
	
	@Excel(name = "uomQuantityReceive")
    private BigDecimal uomQuantityReceive;
	
	@Excel(name = "status")
    private String status;

	@Excel(name = "locationCode")
    private String locationCode;

	@Excel(name = "containerNumber")
    private String containerNumber;

	@Excel(name = "lpnNumber")
    private String lpnNumber;

	@Excel(name = "lotAttribute1")
    private String lotAttribute1;
	
	@Excel(name = "lotAttribute2")
    private String lotAttribute2;

	@Excel(name = "lotAttribute3")
    private String lotAttribute3;

	@Excel(name = "lotAttribute4")
    private Date lotAttribute4;

	@Excel(name = "lotAttribute5")
    private Date lotAttribute5;

	@Excel(name = "lotAttribute6")
    private String lotAttribute6;

	@Excel(name = "lotAttribute7")
    private String lotAttribute7;

	@Excel(name = "lotAttribute8")
    private String lotAttribute8;

	@Excel(name = "lotAttribute9")
    private String lotAttribute9;

	@Excel(name = "lotAttribute10")
    private String lotAttribute10;

	@Excel(name = "lotAttribute11")
    private Date lotAttribute11;

	@Excel(name = "lotAttribute12")
    private Date lotAttribute12;
	
	public String getSourceNumber() {
		return sourceNumber;
	}

	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public Date getExpectedInboundDate() {
		return expectedInboundDate;
	}

	public void setExpectedInboundDate(Date expectedInboundDate) {
		this.expectedInboundDate = expectedInboundDate;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getCarrierCarNumber() {
		return carrierCarNumber;
	}

	public void setCarrierCarNumber(String carrierCarNumber) {
		this.carrierCarNumber = carrierCarNumber;
	}

	public String getCarrierDriver() {
		return carrierDriver;
	}

	public void setCarrierDriver(String carrierDriver) {
		this.carrierDriver = carrierDriver;
	}

	public String getShipAddress1() {
		return shipAddress1;
	}

	public void setShipAddress1(String shipAddress1) {
		this.shipAddress1 = shipAddress1;
	}

	public String getShipAddress2() {
		return shipAddress2;
	}

	public void setShipAddress2(String shipAddress2) {
		this.shipAddress2 = shipAddress2;
	}

	public Long getSourceLineNumber() {
		return sourceLineNumber;
	}

	public void setSourceLineNumber(Long sourceLineNumber) {
		this.sourceLineNumber = sourceLineNumber;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getPoLineNumber() {
		return poLineNumber;
	}

	public void setPoLineNumber(String poLineNumber) {
		this.poLineNumber = poLineNumber;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getPackCode() {
		return packCode;
	}

	public void setPackCode(String packCode) {
		this.packCode = packCode;
	}

	public BigDecimal getUomQuantityExpected() {
		return uomQuantityExpected;
	}

	public void setUomQuantityExpected(BigDecimal uomQuantityExpected) {
		this.uomQuantityExpected = uomQuantityExpected;
	}

	public BigDecimal getUomQuantityReceive() {
		return uomQuantityReceive;
	}

	public void setUomQuantityReceive(BigDecimal uomQuantityReceive) {
		this.uomQuantityReceive = uomQuantityReceive;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getContainerNumber() {
		return containerNumber;
	}

	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}

	public String getLpnNumber() {
		return lpnNumber;
	}

	public void setLpnNumber(String lpnNumber) {
		this.lpnNumber = lpnNumber;
	}

	public String getLotAttribute1() {
		return lotAttribute1;
	}

	public void setLotAttribute1(String lotAttribute1) {
		this.lotAttribute1 = lotAttribute1;
	}

	public String getLotAttribute2() {
		return lotAttribute2;
	}

	public void setLotAttribute2(String lotAttribute2) {
		this.lotAttribute2 = lotAttribute2;
	}

	public String getLotAttribute3() {
		return lotAttribute3;
	}

	public void setLotAttribute3(String lotAttribute3) {
		this.lotAttribute3 = lotAttribute3;
	}

	public Date getLotAttribute4() {
		return lotAttribute4;
	}

	public void setLotAttribute4(Date lotAttribute4) {
		this.lotAttribute4 = lotAttribute4;
	}

	public Date getLotAttribute5() {
		return lotAttribute5;
	}

	public void setLotAttribute5(Date lotAttribute5) {
		this.lotAttribute5 = lotAttribute5;
	}

	public String getLotAttribute6() {
		return lotAttribute6;
	}

	public void setLotAttribute6(String lotAttribute6) {
		this.lotAttribute6 = lotAttribute6;
	}

	public String getLotAttribute7() {
		return lotAttribute7;
	}

	public void setLotAttribute7(String lotAttribute7) {
		this.lotAttribute7 = lotAttribute7;
	}

	public String getLotAttribute8() {
		return lotAttribute8;
	}

	public void setLotAttribute8(String lotAttribute8) {
		this.lotAttribute8 = lotAttribute8;
	}

	public String getLotAttribute9() {
		return lotAttribute9;
	}

	public void setLotAttribute9(String lotAttribute9) {
		this.lotAttribute9 = lotAttribute9;
	}

	public String getLotAttribute10() {
		return lotAttribute10;
	}

	public void setLotAttribute10(String lotAttribute10) {
		this.lotAttribute10 = lotAttribute10;
	}

	public Date getLotAttribute11() {
		return lotAttribute11;
	}

	public void setLotAttribute11(Date lotAttribute11) {
		this.lotAttribute11 = lotAttribute11;
	}

	public Date getLotAttribute12() {
		return lotAttribute12;
	}

	public void setLotAttribute12(Date lotAttribute12) {
		this.lotAttribute12 = lotAttribute12;
	}
	
	public String getInboundNumber() {
		return inboundNumber;
	}

	public void setInboundNumber(String inboundNumber) {
		this.inboundNumber = inboundNumber;
	}

	public Long getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Long lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
