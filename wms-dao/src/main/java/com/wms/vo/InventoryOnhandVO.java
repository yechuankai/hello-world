package com.wms.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.InventoryOnhandTEntity;

public class InventoryOnhandVO extends InventoryOnhandTEntity{

	private BigDecimal quantityAvailable;
	
	private String locationLogical;
	
	private String containerNumber;
	
	private String toLocationCode;
	
	private String toLpnNumber;
	
	private String toContainerNumber;
	
	private BigDecimal toQuantity;
	
	private String lotAttribute1;

    private String lotAttribute2;

    private String lotAttribute3;

    private Date lotAttribute4;

    private Date lotAttribute5;

    private String lotAttribute6;

    private String lotAttribute7;

    private String lotAttribute8;

    private String lotAttribute9;

    private String lotAttribute10;

    private Date lotAttribute11;

    private Date lotAttribute12;
    
    private Set<Long> locations;
    
    private String transactionCategory;
    
    private String warehouseCode;
    
    private BigDecimal weightGross;

    private BigDecimal weightNet;

    private BigDecimal weightTare;
    
    private BigDecimal volume;
    
    private BigDecimal revenueTon;
	
	public void setQuantityAvailable(BigDecimal quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	
	public BigDecimal getQuantityAvailable() {
		return quantityAvailable;
	}
	
	public InventoryOnhandVO() {
		this.setQuantityAllocated(BigDecimal.ZERO);
		this.setQuantityOnhand(BigDecimal.ZERO);
		this.setQuantityLocked(BigDecimal.ZERO);
	}
	
	public InventoryOnhandVO(InventoryOnhandTEntity inventory) {
		BeanUtils.copyBeanProp(this, inventory, Boolean.TRUE);
		BigDecimal quantityAvailable = inventory.getQuantityOnhand().subtract(inventory.getQuantityAllocated()).subtract(inventory.getQuantityLocked());
		this.quantityAvailable = quantityAvailable;
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
	
	public Set<Long> getLocations() {
		return locations;
	}

	public void setLocations(Set<Long> locations) {
		this.locations = locations;
	}

	public String getLocationLogical() {
		return locationLogical;
	}

	public void setLocationLogical(String locationLogical) {
		this.locationLogical = locationLogical;
	}

	public String getToLocationCode() {
		return toLocationCode;
	}

	public void setToLocationCode(String toLocationCode) {
		this.toLocationCode = toLocationCode;
	}

	public String getToLpnNumber() {
		return toLpnNumber;
	}

	public void setToLpnNumber(String toLpnNumber) {
		this.toLpnNumber = toLpnNumber;
	}

	public BigDecimal getToQuantity() {
		return toQuantity;
	}

	public void setToQuantity(BigDecimal toQuantity) {
		this.toQuantity = toQuantity;
	}

	public String getContainerNumber() {
		return containerNumber;
	}

	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}

	public String getToContainerNumber() {
		return toContainerNumber;
	}

	public void setToContainerNumber(String toContainerNumber) {
		this.toContainerNumber = toContainerNumber;
	}

	public String getTransactionCategory() {
		return transactionCategory;
	}

	public void setTransactionCategory(String transactionCategory) {
		this.transactionCategory = transactionCategory;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public BigDecimal getWeightGross() {
		return weightGross;
	}

	public void setWeightGross(BigDecimal weightGross) {
		this.weightGross = weightGross;
	}

	public BigDecimal getWeightNet() {
		return weightNet;
	}

	public void setWeightNet(BigDecimal weightNet) {
		this.weightNet = weightNet;
	}

	public BigDecimal getWeightTare() {
		return weightTare;
	}

	public void setWeightTare(BigDecimal weightTare) {
		this.weightTare = weightTare;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getRevenueTon() {
		return revenueTon;
	}

	public void setRevenueTon(BigDecimal revenueTon) {
		this.revenueTon = revenueTon;
	}

	
}
