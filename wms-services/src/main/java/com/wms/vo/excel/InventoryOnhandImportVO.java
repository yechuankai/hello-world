package com.wms.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.wms.common.core.domain.AbstractExcelModel;

import java.math.BigDecimal;

/**
 * @description: 库存导出VO
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-01 11:39
 **/

public class InventoryOnhandImportVO extends AbstractExcelModel {

    @Excel(name = "ownerCode")
    private String ownerCode;

    @Excel(name = "skuCode")
    private String skuCode;

    @Excel(name = "skuAlias")
    private String skuAlias;

    @Excel(name = "lotNumber")
    private String lotNumber;
    
    @Excel(name = "locationCode")
    private String locationCode;

    @Excel(name = "lpnNumber")
    private String lpnNumber;
    
    @Excel(name = "quantityOnhand")
    private BigDecimal quantityOnhand;

    @Excel(name = "quantityAllocated")
    private BigDecimal quantityAllocated;

    @Excel(name = "quantityLocked")
    private BigDecimal quantityLocked;

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

    public BigDecimal getQuantityOnhand() {
        return quantityOnhand;
    }

    public void setQuantityOnhand(BigDecimal quantityOnhand) {
        this.quantityOnhand = quantityOnhand;
    }

    public BigDecimal getQuantityAllocated() {
        return quantityAllocated;
    }

    public void setQuantityAllocated(BigDecimal quantityAllocated) {
        this.quantityAllocated = quantityAllocated;
    }

    public BigDecimal getQuantityLocked() {
        return quantityLocked;
    }

    public void setQuantityLocked(BigDecimal quantityLocked) {
        this.quantityLocked = quantityLocked;
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
}
