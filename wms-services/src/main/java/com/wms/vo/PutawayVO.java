package com.wms.vo;

import com.wms.entity.auto.PutawayStrategyDetailTEntity;

public class PutawayVO extends PutawayStrategyDetailTEntity{

	private PutawayStrategyDetailTEntity strategy;
	private String containerNumber;
	private InventoryOnhandVO inventoryOnhand;
	private String locationCode;
	
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
	
}
