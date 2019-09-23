package com.wms.vo;

import com.wms.entity.auto.PutawayStrategyDetailTEntity;

public class PutawayVO extends PutawayStrategyDetailTEntity{

	private PutawayStrategyDetailTEntity strategy;
	private String containerNumber;
	private String lpnNumber;
	private InventoryOnhandVO inventoryOnhand;
	private String locationCode;
	private String lpnType;
	private String toZoneCode;
	
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	
	public String getLocationCode() {
		return locationCode;
	}
	
	public PutawayStrategyDetailTEntity getStrategy() {
		return strategy;
	}
	
	public void setStrategy(PutawayStrategyDetailTEntity strategy) {
		this.strategy = strategy;
	}

	public InventoryOnhandVO getInventoryOnhand() {
		return inventoryOnhand;
	}

	public void setInventoryOnhand(InventoryOnhandVO inventoryOnhand) {
		this.inventoryOnhand = inventoryOnhand;
	}
	
	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}
	
	public String getContainerNumber() {
		return containerNumber;
	}

	public String getLpnType() {
		return lpnType;
	}

	public void setLpnType(String lpnType) {
		this.lpnType = lpnType;
	}

	public String getLpnNumber() {
		return lpnNumber;
	}

	public void setLpnNumber(String lpnNumber) {
		this.lpnNumber = lpnNumber;
	}

	public String getToZoneCode() {
		return toZoneCode;
	}

	public void setToZoneCode(String toZoneCode) {
		this.toZoneCode = toZoneCode;
	}

}
