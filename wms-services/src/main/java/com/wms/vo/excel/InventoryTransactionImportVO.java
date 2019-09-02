package com.wms.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.wms.common.core.domain.AbstractExcelModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 交易导出VO
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-01 11:52
 **/

public class InventoryTransactionImportVO extends AbstractExcelModel {

    @Excel(name = "transactionType")
    private String transactionType;

    @Excel(name = "transactionCategory")
    private String transactionCategory;

    @Excel(name = "transactionDate")
    private Date transactionDate;

    @Excel(name = "ownerCode")
    private String ownerCode;

    @Excel(name = "skuCode")
    private String skuCode;

    @Excel(name = "skuAlias")
    private String skuAlias;
    
    @Excel(name = "lotNumber")
    private String lotNumber;

    @Excel(name = "packCode")
    private String packCode;

    @Excel(name = "uom")
    private String uom;

    @Excel(name = "uomQuantity")
    private BigDecimal uomQuantity;

    @Excel(name = "sourceBillNumber")
    private String sourceBillNumber;
    
    @Excel(name = "sourceLineNumber")
    private String sourceLineNumber;

    @Excel(name = "fromLocationCode")
    private String fromLocationCode;

    @Excel(name = "fromLpnNumber")
    private String fromLpnNumber;

    @Excel(name = "locationCode")
    private String locationCode;

    @Excel(name = "lpnNumber")
    private String lpnNumber;

    @Excel(name = "quantity")
    private BigDecimal quantity;
    
    @Excel(name = "createBy")
    private String createBy;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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

    public String getSkuAlias() {
        return skuAlias;
    }

    public void setSkuAlias(String skuAlias) {
        this.skuAlias = skuAlias;
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

    public BigDecimal getUomQuantity() {
        return uomQuantity;
    }

    public void setUomQuantity(BigDecimal uomQuantity) {
        this.uomQuantity = uomQuantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLpnNumber() {
        return lpnNumber;
    }

    public void setLpnNumber(String lpnNumber) {
        this.lpnNumber = lpnNumber;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getSourceLineNumber() {
        return sourceLineNumber;
    }

    public void setSourceLineNumber(String sourceLineNumber) {
        this.sourceLineNumber = sourceLineNumber;
    }

    public String getSourceBillNumber() {
        return sourceBillNumber;
    }

    public void setSourceBillNumber(String sourceBillNumber) {
        this.sourceBillNumber = sourceBillNumber;
    }

    public String getFromLocationCode() {
        return fromLocationCode;
    }

    public void setFromLocationCode(String fromLocationCode) {
        this.fromLocationCode = fromLocationCode;
    }

    public String getFromLpnNumber() {
        return fromLpnNumber;
    }

    public void setFromLpnNumber(String fromLpnNumber) {
        this.fromLpnNumber = fromLpnNumber;
    }

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

    
}
