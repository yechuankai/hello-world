package com.wms.vo;

import java.math.BigDecimal;

import com.wms.common.core.domain.AbstractExcelModel;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class InventoryMultiCountVo extends AbstractExcelModel{
	@Excel(name = "ownerCode")
	private String ownerCode;
	
	@Excel(name = "skuCode")
	private String skuCode;
	
	@Excel(name = "skuAlias")
	private String skuAlias;
	
	@Excel(name = "lpnNumber")
	private String lpnNumber;
	
	@Excel(name = "containerNumber")
	private String containerNumber;
	
	@Excel(name = "locationCode")
	private String locationCode;
	
	@Excel(name = "quantityAvailable")
	private BigDecimal quantityAvailable;
	
	@Excel(name = "quantityOnhand")
	private BigDecimal quantityOnhand;
	
	@Excel(name = "quantityLocked")
	private BigDecimal quantityLocked;
	
	@Excel(name = "quantityAllocated")
	private BigDecimal quantityAllocated;
	
	@Excel(name = "softQuantityAllocated")
	private BigDecimal softQuantityAllocated;
	/**
	 * 用于获取软分配量
	 */
	private Long skuId;
	private Long locationId;
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
	public String getContainerNumber() {
		return containerNumber;
	}
	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
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
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public BigDecimal getQuantityAvailable() {
		if (this.getQuantityOnhand() == null)
			return BigDecimal.ZERO;
		
		this.quantityAvailable = this.getQuantityOnhand();
		
		if (this.getQuantityAllocated() != null)
			this.setQuantityAvailable(quantityAvailable.subtract(this.getQuantityAllocated()));
		
		if (this.getQuantityLocked() != null)
			this.setQuantityAvailable(quantityAvailable.subtract(this.getQuantityLocked()));
		
		if (this.getSoftQuantityAllocated() != null)
			this.setQuantityAvailable(quantityAvailable.subtract(this.getSoftQuantityAllocated()));
		
		return quantityAvailable;
	}
	public void setQuantityAvailable(BigDecimal quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	public BigDecimal getQuantityOnhand() {
		return quantityOnhand;
	}
	public void setQuantityOnhand(BigDecimal quantityOnhand) {
		this.quantityOnhand = quantityOnhand;
	}
	public BigDecimal getQuantityLocked() {
		return quantityLocked;
	}
	public void setQuantityLocked(BigDecimal quantityLocked) {
		this.quantityLocked = quantityLocked;
	}
	public BigDecimal getQuantityAllocated() {
		return quantityAllocated;
	}
	public void setQuantityAllocated(BigDecimal quantityAllocated) {
		this.quantityAllocated = quantityAllocated;
	}
	public BigDecimal getSoftQuantityAllocated() {
		return softQuantityAllocated;
	}
	public void setSoftQuantityAllocated(BigDecimal softQuantityAllocated) {
		this.softQuantityAllocated = softQuantityAllocated;
	}
	
}
