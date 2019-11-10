package com.wms.vo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.common.enums.TransactionTypeEnum;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.LotAttributeTEntity;
import com.wms.entity.auto.LpnTEntity;
import com.wms.entity.auto.OwnerTEntity;
import com.wms.entity.auto.PackTEntity;
import com.wms.entity.auto.SkuTEntity;

public class InventoryTranVO {

	private String transactionType;
	private TransactionTypeEnum transactionTypeEnum;
	private String souceBillNumber;
	private Long warehouseId;
	private Long companyId;
	private Boolean allocateFlag = Boolean.FALSE;
	private Boolean packFlag = Boolean.FALSE;
	private String userName;
	private List<InventoryTranDetailVO> detail = Lists.newArrayList();
	private Map<String, LpnTEntity> lpnMap = Maps.newHashMap();
	private Map<String, LotAttributeTEntity> lotMap = Maps.newHashMap();
	private Map<String, LocationTEntity> locationMap = Maps.newHashMap();
	private Map<String, PackTEntity> packMap = Maps.newHashMap();
	private Map<String, SkuTEntity> skuMap = Maps.newHashMap();
	private Map<String, OwnerTEntity> ownerMap = Maps.newHashMap();
	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getSouceBillNumber() {
		return souceBillNumber;
	}
	public void setSouceBillNumber(String souceBillNumber) {
		this.souceBillNumber = souceBillNumber;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public List<InventoryTranDetailVO> getDetail() {
		return detail;
	}
	public void setDetail(List<InventoryTranDetailVO> detail) {
		this.detail = detail;
	}
	public Map<String, LpnTEntity> getLpnMap() {
		return lpnMap;
	}
	public void setLpnMap(Map<String, LpnTEntity> lpnMap) {
		this.lpnMap = lpnMap;
	}
	public Map<String, LotAttributeTEntity> getLotMap() {
		return lotMap;
	}
	public void setLotMap(Map<String, LotAttributeTEntity> lotMap) {
		this.lotMap = lotMap;
	}
	public Map<String, LocationTEntity> getLocationMap() {
		return locationMap;
	}
	public void setLocationMap(Map<String, LocationTEntity> locationMap) {
		this.locationMap = locationMap;
	}
	public Map<String, PackTEntity> getPackMap() {
		return packMap;
	}
	public void setPackMap(Map<String, PackTEntity> packMap) {
		this.packMap = packMap;
	}
	public void setTransactionTypeEnum(TransactionTypeEnum transactionTypeEnum) {
		this.transactionTypeEnum = transactionTypeEnum;
	}
	public TransactionTypeEnum getTransactionTypeEnum() {
		return transactionTypeEnum;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public Map<String, SkuTEntity> getSkuMap() {
		return skuMap;
	}
	public void setSkuMap(Map<String, SkuTEntity> skuMap) {
		this.skuMap = skuMap;
	}
	public Map<String, OwnerTEntity> getOwnerMap() {
		return ownerMap;
	}
	public void setOwnerMap(Map<String, OwnerTEntity> ownerMap) {
		this.ownerMap = ownerMap;
	}
	public Boolean getAllocateFlag() {
		return allocateFlag;
	}
	public void setAllocateFlag(Boolean allocateFlag) {
		this.allocateFlag = allocateFlag;
	}
	public Boolean getPackFlag() {
		return packFlag;
	}
	public void setPackFlag(Boolean packFlag) {
		this.packFlag = packFlag;
	}
	
}
