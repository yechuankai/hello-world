package com.wms.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.wms.common.core.domain.AbstractExcelModel;

import java.math.BigDecimal;

/**
 * @description: 分配明细导出VO类
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-30 15:41
 **/

public class AllocateImportVO extends AbstractExcelModel {

    @Excel(name = "sourceWaveNumber")
    private String sourceWaveNumber;
    
    @Excel(name = "sourceBillNumber")
    private String sourceBillNumber;
    
    @Excel(name = "sourceLineNumber")
    private Long sourceLineNumber;

    @Excel(name = "ownerCode")
    private String ownerCode;

    @Excel(name = "skuCode")
    private String skuCode;

    @Excel(name = "skuAlias")
    private String skuAlias;

    @Excel(name = "lotNumber")
    private String lotNumber;
    
    @Excel(name = "zoneCode")
    private String zoneCode;

    @Excel(name = "locationCode")
    private String locationCode;

    @Excel(name = "lpnNumber")
    private String lpnNumber;
    
    @Excel(name = "quantityAllocated")
    private BigDecimal quantityAllocated;

    @Excel(name = "loadLpnNumber")
    private String loadLpnNumber;

    @Excel(name = "status")
    private String status;

    @Excel(name = "fullLpnFlag")
    private String fullLpnFlag;

    @Excel(name = "fullLocationFlag")
    private String fullLocationFlag;
    
    @Excel(name = "fromLocationCode")
    private String fromLocationCode;
    
    @Excel(name = "fromLpnNumber")
    private String fromLpnNumber;

    @Excel(name = "toLocationCode")
    private String toLocationCode;

    @Excel(name = "updateTime")
    private String updateTime;

    public Long getSourceLineNumber() {
        return sourceLineNumber;
    }

    public void setSourceLineNumber(Long sourceLineNumber) {
        this.sourceLineNumber = sourceLineNumber;
    }

    public String getSourceBillNumber() {
        return sourceBillNumber;
    }

    public void setSourceBillNumber(String sourceBillNumber) {
        this.sourceBillNumber = sourceBillNumber;
    }

    public String getSourceWaveNumber() {
        return sourceWaveNumber;
    }

    public void setSourceWaveNumber(String sourceWaveNumber) {
        this.sourceWaveNumber = sourceWaveNumber;
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

    public BigDecimal getQuantityAllocated() {
        return quantityAllocated;
    }

    public void setQuantityAllocated(BigDecimal quantityAllocated) {
        this.quantityAllocated = quantityAllocated;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
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

    public String getLoadLpnNumber() {
        return loadLpnNumber;
    }

    public void setLoadLpnNumber(String loadLpnNumber) {
        this.loadLpnNumber = loadLpnNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullLpnFlag() {
        return fullLpnFlag;
    }

    public void setFullLpnFlag(String fullLpnFlag) {
        this.fullLpnFlag = fullLpnFlag;
    }

    public String getFullLocationFlag() {
        return fullLocationFlag;
    }

    public void setFullLocationFlag(String fullLocationFlag) {
        this.fullLocationFlag = fullLocationFlag;
    }

    public String getToLocationCode() {
        return toLocationCode;
    }

    public void setToLocationCode(String toLocationCode) {
        this.toLocationCode = toLocationCode;
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

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
    
}
