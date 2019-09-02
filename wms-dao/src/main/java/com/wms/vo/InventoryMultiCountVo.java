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
	
	@Excel(name = "availableAmount")
	private BigDecimal availableAmount;
	
	@Excel(name = "onHandAmount")
	private BigDecimal onHandAmount;
	
	@Excel(name = "lockAmount")
	private BigDecimal lockAmount;
	
	@Excel(name = "allocateAmount")
	private BigDecimal allocateAmount;
	
	@Excel(name = "softAllocateAmount")
	private BigDecimal softAllocateAmount;
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
	public BigDecimal getAvailableAmount() {
		if (onHandAmount != null && allocateAmount != null && lockAmount != null) {
			availableAmount = onHandAmount.subtract(allocateAmount).subtract(lockAmount);
		}
		return availableAmount;
	}
	public void setAvailableAmount(BigDecimal availableAmount) {
		this.availableAmount = availableAmount;
	}
	public BigDecimal getOnHandAmount() {
		return onHandAmount;
	}
	public void setOnHandAmount(BigDecimal onHandAmount) {
		this.onHandAmount = onHandAmount;
	}
	public BigDecimal getLockAmount() {
		return lockAmount;
	}
	public void setLockAmount(BigDecimal lockAmount) {
		this.lockAmount = lockAmount;
	}
	public BigDecimal getAllocateAmount() {
		return allocateAmount;
	}
	public void setAllocateAmount(BigDecimal allocateAmount) {
		this.allocateAmount = allocateAmount;
	}
	public BigDecimal getSoftAllocateAmount() {
		return softAllocateAmount;
	}
	public void setSoftAllocateAmount(BigDecimal softAllocateAmount) {
		this.softAllocateAmount = softAllocateAmount;
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
}
