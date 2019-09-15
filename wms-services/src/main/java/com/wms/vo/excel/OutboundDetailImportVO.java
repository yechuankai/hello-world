package com.wms.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.wms.common.core.domain.AbstractExcelModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-26 10:13
 **/

public class OutboundDetailImportVO extends AbstractExcelModel {

    @Excel(name = "outboundNumber")
    private String outboundNumber;

    @Excel(name = "sourceNumber")
    private String sourceNumber;

    @Excel(name = "referenceNumber")
    private String referenceNumber;

    @Excel(name = "type")
    private String type;

    @Excel(name = "ownerCode")
    private String ownerCode;

    @Excel(name = "outboundDate")
    private Date outboundDate;

    @Excel(name = "expectedOutboundDate")
    private Date expectedOutboundDate;

    @Excel(name = "customerCode")
    private String customerCode;

    @Excel(name = "contact1")
    private String contact1;

    @Excel(name = "contact2")
    private String contact2;

    @Excel(name = "phone1")
    private String phone1;

    @Excel(name = "phone2")
    private String phone2;

    @Excel(name = "address1")
    private String address1;

    @Excel(name = "address2")
    private String address2;

    @Excel(name = "email1")
    private String email1;
    
    @Excel(name = "email2")
    private String email2;

    @Excel(name = "shipLabel")
    private String shipLabel;

    @Excel(name = "carNumber")
    private String carNumber;

    @Excel(name = "driver")
    private String driver;

    @Excel(name = "containerNumber")
    private String containerNumber;

    @Excel(name = "lineNumber")
    private Long lineNumber;

    @Excel(name = "sourceLineNumber")
    private Long sourceLineNumber;

    @Excel(name = "skuCode")
    private String skuCode;

    @Excel(name = "packCode")
    private String packCode;

    @Excel(name = "uom")
    private String uom;

    @Excel(name = "allocateStrategyCode")
    private String allocateStrategyCode;

    @Excel(name = "uomQuantityOrder")
    private BigDecimal uomQuantityOrder;

    @Excel(name = "uomQuantityExpected")
    private BigDecimal uomQuantityExpected;

    @Excel(name = "uomQuantityAllocated")
    private BigDecimal uomQuantityAllocated;

    @Excel(name = "uomQuantityPicked")
    private BigDecimal uomQuantityPicked;

    @Excel(name = "uomQuantityShiped")
    private BigDecimal uomQuantityShiped;

    @Excel(name = "status")
    private String status;

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

    public String getOutboundNumber() {
        return outboundNumber;
    }

    public void setOutboundNumber(String outboundNumber) {
        this.outboundNumber = outboundNumber;
    }

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

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public Date getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(Date outboundDate) {
        this.outboundDate = outboundDate;
    }

    public Date getExpectedOutboundDate() {
        return expectedOutboundDate;
    }

    public void setExpectedOutboundDate(Date expectedOutboundDate) {
        this.expectedOutboundDate = expectedOutboundDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public Long getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Long getSourceLineNumber() {
        return sourceLineNumber;
    }

    public void setSourceLineNumber(Long sourceLineNumber) {
        this.sourceLineNumber = sourceLineNumber;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
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

    public String getAllocateStrategyCode() {
        return allocateStrategyCode;
    }

    public void setAllocateStrategyCode(String allocateStrategyCode) {
        this.allocateStrategyCode = allocateStrategyCode;
    }

    public BigDecimal getUomQuantityOrder() {
        return uomQuantityOrder;
    }

    public void setUomQuantityOrder(BigDecimal uomQuantityOrder) {
        this.uomQuantityOrder = uomQuantityOrder;
    }

    public BigDecimal getUomQuantityExpected() {
        return uomQuantityExpected;
    }

    public void setUomQuantityExpected(BigDecimal uomQuantityExpected) {
        this.uomQuantityExpected = uomQuantityExpected;
    }

    public BigDecimal getUomQuantityAllocated() {
        return uomQuantityAllocated;
    }

    public void setUomQuantityAllocated(BigDecimal uomQuantityAllocated) {
        this.uomQuantityAllocated = uomQuantityAllocated;
    }

    public BigDecimal getUomQuantityPicked() {
        return uomQuantityPicked;
    }

    public void setUomQuantityPicked(BigDecimal uomQuantityPicked) {
        this.uomQuantityPicked = uomQuantityPicked;
    }

    public BigDecimal getUomQuantityShiped() {
        return uomQuantityShiped;
    }

    public void setUomQuantityShiped(BigDecimal uomQuantityShiped) {
        this.uomQuantityShiped = uomQuantityShiped;
    }

    public String getStatus() {
        /*for (OutboundStatusEnum statusEnum :OutboundStatusEnum.values()){
            if(StringUtils.equals(statusEnum.getCode(),status)){
                return statusEnum.getDesc();
            }
        }*/
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getShipLabel() {
        return shipLabel;
    }

    public void setShipLabel(String shipLabel) {
        this.shipLabel = shipLabel;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber;
    }
}
