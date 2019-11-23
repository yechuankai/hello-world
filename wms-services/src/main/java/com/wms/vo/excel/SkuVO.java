package com.wms.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.wms.common.core.domain.AbstractExcelModel;

import java.math.BigDecimal;

public class SkuVO  extends AbstractExcelModel {

	@Excel(name = "ownerCode")
	private String ownerCode;

	@Excel(name = "skuCode")
	private String skuCode;

	@Excel(name = "skuAlias")
	private String skuAlias;

	@Excel(name = "skuDescr")
	private String skuDescr;

	@Excel(name = "skuType")
	private String skuType;

	@Excel(name = "packCode")
	private String packCode;

	@Excel(name = "volume")
	private BigDecimal volume;

	@Excel(name = "length")
	private BigDecimal length;

	@Excel(name = "width")
	private BigDecimal width;

	@Excel(name = "height")
	private BigDecimal height;

	@Excel(name = "weightGross")
	private BigDecimal weightGross;

	@Excel(name = "weightNet")
	private BigDecimal weightNet;

	@Excel(name = "weightTare")
	private BigDecimal weightTare;

	@Excel(name = "putawayStrategyCode")
	private String putawayStrategyCode;

	@Excel(name = "putawayLocationCode")
	private String putawayLocationCode;

	@Excel(name = "allocateStrategyCode")
	private String allocateStrategyCode;

	@Excel(name = "putawayZoneCode")
	private String putawayZoneCode;

	@Excel(name = "barcode")
	private String barcode;

	@Excel(name = "uom")
	private String uom;
	
	@Excel(name = "billingUOM")
	private String uomBilling;

	@Excel(name = "lotValidateCode")
	private String lotValidateCode;

	@Excel(name = "lotAttribute1Label")
	private String lotAttribute1Label;

	@Excel(name = "lotAttribute2Label")
	private String lotAttribute2Label;

	@Excel(name = "lotAttribute3Label")
	private String lotAttribute3Label;

	@Excel(name = "lotAttribute4Label")
	private String lotAttribute4Label;

	@Excel(name = "lotAttribute5Label")
	private String lotAttribute5Label;

	@Excel(name = "lotAttribute6Label")
	private String lotAttribute6Label;

	@Excel(name = "lotAttribute7Label")
	private String lotAttribute7Label;

	@Excel(name = "lotAttribute8Label")
	private String lotAttribute8Label;

	@Excel(name = "lotAttribute9Label")
	private String lotAttribute9Label;

	@Excel(name = "lotAttribute10Label")
	private String lotAttribute10Label;

	@Excel(name = "lotAttribute11Label")
	private String lotAttribute11Label;

	@Excel(name = "lotAttribute12Label")
	private String lotAttribute12Label;

	@Excel(name = "active")
	private String active;

	@Excel(name = "remark")
	private String remark;

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

	public String getSkuDescr() {
		return skuDescr;
	}

	public void setSkuDescr(String skuDescr) {
		this.skuDescr = skuDescr;
	}

	public String getSkuType() {
		return skuType;
	}

	public void setSkuType(String skuType) {
		this.skuType = skuType;
	}

	public String getPackCode() {
		return packCode;
	}

	public void setPackCode(String packCode) {
		this.packCode = packCode;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
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

	public String getPutawayStrategyCode() {
		return putawayStrategyCode;
	}

	public void setPutawayStrategyCode(String putawayStrategyCode) {
		this.putawayStrategyCode = putawayStrategyCode;
	}

	public String getPutawayLocationCode() {
		return putawayLocationCode;
	}

	public void setPutawayLocationCode(String putawayLocationCode) {
		this.putawayLocationCode = putawayLocationCode;
	}

	public String getAllocateStrategyCode() {
		return allocateStrategyCode;
	}

	public void setAllocateStrategyCode(String allocateStrategyCode) {
		this.allocateStrategyCode = allocateStrategyCode;
	}

	public String getLotAttribute1Label() {
		return lotAttribute1Label;
	}

	public void setLotAttribute1Label(String lotAttribute1Label) {
		this.lotAttribute1Label = lotAttribute1Label;
	}

	public String getLotAttribute2Label() {
		return lotAttribute2Label;
	}

	public void setLotAttribute2Label(String lotAttribute2Label) {
		this.lotAttribute2Label = lotAttribute2Label;
	}

	public String getLotAttribute3Label() {
		return lotAttribute3Label;
	}

	public void setLotAttribute3Label(String lotAttribute3Label) {
		this.lotAttribute3Label = lotAttribute3Label;
	}

	public String getLotAttribute4Label() {
		return lotAttribute4Label;
	}

	public void setLotAttribute4Label(String lotAttribute4Label) {
		this.lotAttribute4Label = lotAttribute4Label;
	}

	public String getLotAttribute5Label() {
		return lotAttribute5Label;
	}

	public void setLotAttribute5Label(String lotAttribute5Label) {
		this.lotAttribute5Label = lotAttribute5Label;
	}

	public String getLotAttribute6Label() {
		return lotAttribute6Label;
	}

	public void setLotAttribute6Label(String lotAttribute6Label) {
		this.lotAttribute6Label = lotAttribute6Label;
	}

	public String getLotAttribute7Label() {
		return lotAttribute7Label;
	}

	public void setLotAttribute7Label(String lotAttribute7Label) {
		this.lotAttribute7Label = lotAttribute7Label;
	}

	public String getLotAttribute8Label() {
		return lotAttribute8Label;
	}

	public void setLotAttribute8Label(String lotAttribute8Label) {
		this.lotAttribute8Label = lotAttribute8Label;
	}

	public String getLotAttribute9Label() {
		return lotAttribute9Label;
	}

	public void setLotAttribute9Label(String lotAttribute9Label) {
		this.lotAttribute9Label = lotAttribute9Label;
	}

	public String getLotAttribute10Label() {
		return lotAttribute10Label;
	}

	public void setLotAttribute10Label(String lotAttribute10Label) {
		this.lotAttribute10Label = lotAttribute10Label;
	}

	public String getLotAttribute11Label() {
		return lotAttribute11Label;
	}

	public void setLotAttribute11Label(String lotAttribute11Label) {
		this.lotAttribute11Label = lotAttribute11Label;
	}

	public String getLotAttribute12Label() {
		return lotAttribute12Label;
	}

	public void setLotAttribute12Label(String lotAttribute12Label) {
		this.lotAttribute12Label = lotAttribute12Label;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPutawayZoneCode() {
		return putawayZoneCode;
	}

	public void setPutawayZoneCode(String putawayZoneCode) {
		this.putawayZoneCode = putawayZoneCode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getLotValidateCode() {
		return lotValidateCode;
	}

	public void setLotValidateCode(String lotValidateCode) {
		this.lotValidateCode = lotValidateCode;
	}
}
