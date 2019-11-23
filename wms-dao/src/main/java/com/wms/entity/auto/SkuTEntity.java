package com.wms.entity.auto;

import com.wms.entity.BaseEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class SkuTEntity extends BaseEntity {
    private Long skuId;

    private Long ownerId;

    private String ownerCode;

    private String skuCode;

    private String skuAlias;

    private String barcode;

    private String skuDescr;

    private String skuType;

    private Long packId;

    private String packCode;

    private String uom;

    private BigDecimal volume;

    private BigDecimal length;

    private BigDecimal width;

    private BigDecimal height;

    private BigDecimal weightGross;

    private BigDecimal weightNet;

    private BigDecimal weightTare;

    private Long lotValidateId;

    private String lotValidateCode;

    private Long putawayStrategyId;

    private String putawayStrategyCode;

    private String putawayZoneCode;

    private String putawayLocationCode;

    private Long allocateStrategyId;

    private String allocateStrategyCode;

    private String lotAttribute1Label;

    private String lotAttribute2Label;

    private String lotAttribute3Label;

    private String lotAttribute4Label;

    private String lotAttribute5Label;

    private String lotAttribute6Label;

    private String lotAttribute7Label;

    private String lotAttribute8Label;

    private String lotAttribute9Label;

    private String lotAttribute10Label;

    private String lotAttribute11Label;

    private String lotAttribute12Label;

    private String active;

    private String remark;

    private Long companyId;

    private Long warehouseId;

    private String delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Long updateVersion;

    private String description;

    private String uomBilling;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode == null ? null : ownerCode.trim();
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public String getSkuAlias() {
        return skuAlias;
    }

    public void setSkuAlias(String skuAlias) {
        this.skuAlias = skuAlias == null ? null : skuAlias.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getSkuDescr() {
        return skuDescr;
    }

    public void setSkuDescr(String skuDescr) {
        this.skuDescr = skuDescr == null ? null : skuDescr.trim();
    }

    public String getSkuType() {
        return skuType;
    }

    public void setSkuType(String skuType) {
        this.skuType = skuType == null ? null : skuType.trim();
    }

    public Long getPackId() {
        return packId;
    }

    public void setPackId(Long packId) {
        this.packId = packId;
    }

    public String getPackCode() {
        return packCode;
    }

    public void setPackCode(String packCode) {
        this.packCode = packCode == null ? null : packCode.trim();
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom == null ? null : uom.trim();
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

    public Long getLotValidateId() {
        return lotValidateId;
    }

    public void setLotValidateId(Long lotValidateId) {
        this.lotValidateId = lotValidateId;
    }

    public String getLotValidateCode() {
        return lotValidateCode;
    }

    public void setLotValidateCode(String lotValidateCode) {
        this.lotValidateCode = lotValidateCode == null ? null : lotValidateCode.trim();
    }

    public Long getPutawayStrategyId() {
        return putawayStrategyId;
    }

    public void setPutawayStrategyId(Long putawayStrategyId) {
        this.putawayStrategyId = putawayStrategyId;
    }

    public String getPutawayStrategyCode() {
        return putawayStrategyCode;
    }

    public void setPutawayStrategyCode(String putawayStrategyCode) {
        this.putawayStrategyCode = putawayStrategyCode == null ? null : putawayStrategyCode.trim();
    }

    public String getPutawayZoneCode() {
        return putawayZoneCode;
    }

    public void setPutawayZoneCode(String putawayZoneCode) {
        this.putawayZoneCode = putawayZoneCode == null ? null : putawayZoneCode.trim();
    }

    public String getPutawayLocationCode() {
        return putawayLocationCode;
    }

    public void setPutawayLocationCode(String putawayLocationCode) {
        this.putawayLocationCode = putawayLocationCode == null ? null : putawayLocationCode.trim();
    }

    public Long getAllocateStrategyId() {
        return allocateStrategyId;
    }

    public void setAllocateStrategyId(Long allocateStrategyId) {
        this.allocateStrategyId = allocateStrategyId;
    }

    public String getAllocateStrategyCode() {
        return allocateStrategyCode;
    }

    public void setAllocateStrategyCode(String allocateStrategyCode) {
        this.allocateStrategyCode = allocateStrategyCode == null ? null : allocateStrategyCode.trim();
    }

    public String getLotAttribute1Label() {
        return lotAttribute1Label;
    }

    public void setLotAttribute1Label(String lotAttribute1Label) {
        this.lotAttribute1Label = lotAttribute1Label == null ? null : lotAttribute1Label.trim();
    }

    public String getLotAttribute2Label() {
        return lotAttribute2Label;
    }

    public void setLotAttribute2Label(String lotAttribute2Label) {
        this.lotAttribute2Label = lotAttribute2Label == null ? null : lotAttribute2Label.trim();
    }

    public String getLotAttribute3Label() {
        return lotAttribute3Label;
    }

    public void setLotAttribute3Label(String lotAttribute3Label) {
        this.lotAttribute3Label = lotAttribute3Label == null ? null : lotAttribute3Label.trim();
    }

    public String getLotAttribute4Label() {
        return lotAttribute4Label;
    }

    public void setLotAttribute4Label(String lotAttribute4Label) {
        this.lotAttribute4Label = lotAttribute4Label == null ? null : lotAttribute4Label.trim();
    }

    public String getLotAttribute5Label() {
        return lotAttribute5Label;
    }

    public void setLotAttribute5Label(String lotAttribute5Label) {
        this.lotAttribute5Label = lotAttribute5Label == null ? null : lotAttribute5Label.trim();
    }

    public String getLotAttribute6Label() {
        return lotAttribute6Label;
    }

    public void setLotAttribute6Label(String lotAttribute6Label) {
        this.lotAttribute6Label = lotAttribute6Label == null ? null : lotAttribute6Label.trim();
    }

    public String getLotAttribute7Label() {
        return lotAttribute7Label;
    }

    public void setLotAttribute7Label(String lotAttribute7Label) {
        this.lotAttribute7Label = lotAttribute7Label == null ? null : lotAttribute7Label.trim();
    }

    public String getLotAttribute8Label() {
        return lotAttribute8Label;
    }

    public void setLotAttribute8Label(String lotAttribute8Label) {
        this.lotAttribute8Label = lotAttribute8Label == null ? null : lotAttribute8Label.trim();
    }

    public String getLotAttribute9Label() {
        return lotAttribute9Label;
    }

    public void setLotAttribute9Label(String lotAttribute9Label) {
        this.lotAttribute9Label = lotAttribute9Label == null ? null : lotAttribute9Label.trim();
    }

    public String getLotAttribute10Label() {
        return lotAttribute10Label;
    }

    public void setLotAttribute10Label(String lotAttribute10Label) {
        this.lotAttribute10Label = lotAttribute10Label == null ? null : lotAttribute10Label.trim();
    }

    public String getLotAttribute11Label() {
        return lotAttribute11Label;
    }

    public void setLotAttribute11Label(String lotAttribute11Label) {
        this.lotAttribute11Label = lotAttribute11Label == null ? null : lotAttribute11Label.trim();
    }

    public String getLotAttribute12Label() {
        return lotAttribute12Label;
    }

    public void setLotAttribute12Label(String lotAttribute12Label) {
        this.lotAttribute12Label = lotAttribute12Label == null ? null : lotAttribute12Label.trim();
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active == null ? null : active.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SKU_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public void andLogicalDeleted(boolean deleted) {
        setDelFlag(deleted ? DelFlag.IS_DELETED.value() : DelFlag.NOT_DELETED.value());
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateVersion() {
        return updateVersion;
    }

    public void setUpdateVersion(Long updateVersion) {
        this.updateVersion = updateVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUomBilling() {
        return uomBilling;
    }

    public void setUomBilling(String uomBilling) {
        this.uomBilling = uomBilling == null ? null : uomBilling.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", skuId=").append(skuId);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", skuAlias=").append(skuAlias);
        sb.append(", barcode=").append(barcode);
        sb.append(", skuDescr=").append(skuDescr);
        sb.append(", skuType=").append(skuType);
        sb.append(", packId=").append(packId);
        sb.append(", packCode=").append(packCode);
        sb.append(", uom=").append(uom);
        sb.append(", volume=").append(volume);
        sb.append(", length=").append(length);
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
        sb.append(", weightGross=").append(weightGross);
        sb.append(", weightNet=").append(weightNet);
        sb.append(", weightTare=").append(weightTare);
        sb.append(", lotValidateId=").append(lotValidateId);
        sb.append(", lotValidateCode=").append(lotValidateCode);
        sb.append(", putawayStrategyId=").append(putawayStrategyId);
        sb.append(", putawayStrategyCode=").append(putawayStrategyCode);
        sb.append(", putawayZoneCode=").append(putawayZoneCode);
        sb.append(", putawayLocationCode=").append(putawayLocationCode);
        sb.append(", allocateStrategyId=").append(allocateStrategyId);
        sb.append(", allocateStrategyCode=").append(allocateStrategyCode);
        sb.append(", lotAttribute1Label=").append(lotAttribute1Label);
        sb.append(", lotAttribute2Label=").append(lotAttribute2Label);
        sb.append(", lotAttribute3Label=").append(lotAttribute3Label);
        sb.append(", lotAttribute4Label=").append(lotAttribute4Label);
        sb.append(", lotAttribute5Label=").append(lotAttribute5Label);
        sb.append(", lotAttribute6Label=").append(lotAttribute6Label);
        sb.append(", lotAttribute7Label=").append(lotAttribute7Label);
        sb.append(", lotAttribute8Label=").append(lotAttribute8Label);
        sb.append(", lotAttribute9Label=").append(lotAttribute9Label);
        sb.append(", lotAttribute10Label=").append(lotAttribute10Label);
        sb.append(", lotAttribute11Label=").append(lotAttribute11Label);
        sb.append(", lotAttribute12Label=").append(lotAttribute12Label);
        sb.append(", active=").append(active);
        sb.append(", remark=").append(remark);
        sb.append(", companyId=").append(companyId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateVersion=").append(updateVersion);
        sb.append(", description=").append(description);
        sb.append(", uomBilling=").append(uomBilling);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SkuTEntity other = (SkuTEntity) that;
        return (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getOwnerCode() == null ? other.getOwnerCode() == null : this.getOwnerCode().equals(other.getOwnerCode()))
            && (this.getSkuCode() == null ? other.getSkuCode() == null : this.getSkuCode().equals(other.getSkuCode()))
            && (this.getSkuAlias() == null ? other.getSkuAlias() == null : this.getSkuAlias().equals(other.getSkuAlias()))
            && (this.getBarcode() == null ? other.getBarcode() == null : this.getBarcode().equals(other.getBarcode()))
            && (this.getSkuDescr() == null ? other.getSkuDescr() == null : this.getSkuDescr().equals(other.getSkuDescr()))
            && (this.getSkuType() == null ? other.getSkuType() == null : this.getSkuType().equals(other.getSkuType()))
            && (this.getPackId() == null ? other.getPackId() == null : this.getPackId().equals(other.getPackId()))
            && (this.getPackCode() == null ? other.getPackCode() == null : this.getPackCode().equals(other.getPackCode()))
            && (this.getUom() == null ? other.getUom() == null : this.getUom().equals(other.getUom()))
            && (this.getVolume() == null ? other.getVolume() == null : this.getVolume().equals(other.getVolume()))
            && (this.getLength() == null ? other.getLength() == null : this.getLength().equals(other.getLength()))
            && (this.getWidth() == null ? other.getWidth() == null : this.getWidth().equals(other.getWidth()))
            && (this.getHeight() == null ? other.getHeight() == null : this.getHeight().equals(other.getHeight()))
            && (this.getWeightGross() == null ? other.getWeightGross() == null : this.getWeightGross().equals(other.getWeightGross()))
            && (this.getWeightNet() == null ? other.getWeightNet() == null : this.getWeightNet().equals(other.getWeightNet()))
            && (this.getWeightTare() == null ? other.getWeightTare() == null : this.getWeightTare().equals(other.getWeightTare()))
            && (this.getLotValidateId() == null ? other.getLotValidateId() == null : this.getLotValidateId().equals(other.getLotValidateId()))
            && (this.getLotValidateCode() == null ? other.getLotValidateCode() == null : this.getLotValidateCode().equals(other.getLotValidateCode()))
            && (this.getPutawayStrategyId() == null ? other.getPutawayStrategyId() == null : this.getPutawayStrategyId().equals(other.getPutawayStrategyId()))
            && (this.getPutawayStrategyCode() == null ? other.getPutawayStrategyCode() == null : this.getPutawayStrategyCode().equals(other.getPutawayStrategyCode()))
            && (this.getPutawayZoneCode() == null ? other.getPutawayZoneCode() == null : this.getPutawayZoneCode().equals(other.getPutawayZoneCode()))
            && (this.getPutawayLocationCode() == null ? other.getPutawayLocationCode() == null : this.getPutawayLocationCode().equals(other.getPutawayLocationCode()))
            && (this.getAllocateStrategyId() == null ? other.getAllocateStrategyId() == null : this.getAllocateStrategyId().equals(other.getAllocateStrategyId()))
            && (this.getAllocateStrategyCode() == null ? other.getAllocateStrategyCode() == null : this.getAllocateStrategyCode().equals(other.getAllocateStrategyCode()))
            && (this.getLotAttribute1Label() == null ? other.getLotAttribute1Label() == null : this.getLotAttribute1Label().equals(other.getLotAttribute1Label()))
            && (this.getLotAttribute2Label() == null ? other.getLotAttribute2Label() == null : this.getLotAttribute2Label().equals(other.getLotAttribute2Label()))
            && (this.getLotAttribute3Label() == null ? other.getLotAttribute3Label() == null : this.getLotAttribute3Label().equals(other.getLotAttribute3Label()))
            && (this.getLotAttribute4Label() == null ? other.getLotAttribute4Label() == null : this.getLotAttribute4Label().equals(other.getLotAttribute4Label()))
            && (this.getLotAttribute5Label() == null ? other.getLotAttribute5Label() == null : this.getLotAttribute5Label().equals(other.getLotAttribute5Label()))
            && (this.getLotAttribute6Label() == null ? other.getLotAttribute6Label() == null : this.getLotAttribute6Label().equals(other.getLotAttribute6Label()))
            && (this.getLotAttribute7Label() == null ? other.getLotAttribute7Label() == null : this.getLotAttribute7Label().equals(other.getLotAttribute7Label()))
            && (this.getLotAttribute8Label() == null ? other.getLotAttribute8Label() == null : this.getLotAttribute8Label().equals(other.getLotAttribute8Label()))
            && (this.getLotAttribute9Label() == null ? other.getLotAttribute9Label() == null : this.getLotAttribute9Label().equals(other.getLotAttribute9Label()))
            && (this.getLotAttribute10Label() == null ? other.getLotAttribute10Label() == null : this.getLotAttribute10Label().equals(other.getLotAttribute10Label()))
            && (this.getLotAttribute11Label() == null ? other.getLotAttribute11Label() == null : this.getLotAttribute11Label().equals(other.getLotAttribute11Label()))
            && (this.getLotAttribute12Label() == null ? other.getLotAttribute12Label() == null : this.getLotAttribute12Label().equals(other.getLotAttribute12Label()))
            && (this.getActive() == null ? other.getActive() == null : this.getActive().equals(other.getActive()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getWarehouseId() == null ? other.getWarehouseId() == null : this.getWarehouseId().equals(other.getWarehouseId()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateVersion() == null ? other.getUpdateVersion() == null : this.getUpdateVersion().equals(other.getUpdateVersion()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getUomBilling() == null ? other.getUomBilling() == null : this.getUomBilling().equals(other.getUomBilling()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getOwnerCode() == null) ? 0 : getOwnerCode().hashCode());
        result = prime * result + ((getSkuCode() == null) ? 0 : getSkuCode().hashCode());
        result = prime * result + ((getSkuAlias() == null) ? 0 : getSkuAlias().hashCode());
        result = prime * result + ((getBarcode() == null) ? 0 : getBarcode().hashCode());
        result = prime * result + ((getSkuDescr() == null) ? 0 : getSkuDescr().hashCode());
        result = prime * result + ((getSkuType() == null) ? 0 : getSkuType().hashCode());
        result = prime * result + ((getPackId() == null) ? 0 : getPackId().hashCode());
        result = prime * result + ((getPackCode() == null) ? 0 : getPackCode().hashCode());
        result = prime * result + ((getUom() == null) ? 0 : getUom().hashCode());
        result = prime * result + ((getVolume() == null) ? 0 : getVolume().hashCode());
        result = prime * result + ((getLength() == null) ? 0 : getLength().hashCode());
        result = prime * result + ((getWidth() == null) ? 0 : getWidth().hashCode());
        result = prime * result + ((getHeight() == null) ? 0 : getHeight().hashCode());
        result = prime * result + ((getWeightGross() == null) ? 0 : getWeightGross().hashCode());
        result = prime * result + ((getWeightNet() == null) ? 0 : getWeightNet().hashCode());
        result = prime * result + ((getWeightTare() == null) ? 0 : getWeightTare().hashCode());
        result = prime * result + ((getLotValidateId() == null) ? 0 : getLotValidateId().hashCode());
        result = prime * result + ((getLotValidateCode() == null) ? 0 : getLotValidateCode().hashCode());
        result = prime * result + ((getPutawayStrategyId() == null) ? 0 : getPutawayStrategyId().hashCode());
        result = prime * result + ((getPutawayStrategyCode() == null) ? 0 : getPutawayStrategyCode().hashCode());
        result = prime * result + ((getPutawayZoneCode() == null) ? 0 : getPutawayZoneCode().hashCode());
        result = prime * result + ((getPutawayLocationCode() == null) ? 0 : getPutawayLocationCode().hashCode());
        result = prime * result + ((getAllocateStrategyId() == null) ? 0 : getAllocateStrategyId().hashCode());
        result = prime * result + ((getAllocateStrategyCode() == null) ? 0 : getAllocateStrategyCode().hashCode());
        result = prime * result + ((getLotAttribute1Label() == null) ? 0 : getLotAttribute1Label().hashCode());
        result = prime * result + ((getLotAttribute2Label() == null) ? 0 : getLotAttribute2Label().hashCode());
        result = prime * result + ((getLotAttribute3Label() == null) ? 0 : getLotAttribute3Label().hashCode());
        result = prime * result + ((getLotAttribute4Label() == null) ? 0 : getLotAttribute4Label().hashCode());
        result = prime * result + ((getLotAttribute5Label() == null) ? 0 : getLotAttribute5Label().hashCode());
        result = prime * result + ((getLotAttribute6Label() == null) ? 0 : getLotAttribute6Label().hashCode());
        result = prime * result + ((getLotAttribute7Label() == null) ? 0 : getLotAttribute7Label().hashCode());
        result = prime * result + ((getLotAttribute8Label() == null) ? 0 : getLotAttribute8Label().hashCode());
        result = prime * result + ((getLotAttribute9Label() == null) ? 0 : getLotAttribute9Label().hashCode());
        result = prime * result + ((getLotAttribute10Label() == null) ? 0 : getLotAttribute10Label().hashCode());
        result = prime * result + ((getLotAttribute11Label() == null) ? 0 : getLotAttribute11Label().hashCode());
        result = prime * result + ((getLotAttribute12Label() == null) ? 0 : getLotAttribute12Label().hashCode());
        result = prime * result + ((getActive() == null) ? 0 : getActive().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getWarehouseId() == null) ? 0 : getWarehouseId().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateVersion() == null) ? 0 : getUpdateVersion().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getUomBilling() == null) ? 0 : getUomBilling().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SKU_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static SkuTEntity.Builder builder() {
        return new SkuTEntity.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table SKU_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private SkuTEntity obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new SkuTEntity();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.SKU_ID
         *
         * @param skuId the value for SKU_T.SKU_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder skuId(Long skuId) {
            obj.setSkuId(skuId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.OWNER_ID
         *
         * @param ownerId the value for SKU_T.OWNER_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder ownerId(Long ownerId) {
            obj.setOwnerId(ownerId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.OWNER_CODE
         *
         * @param ownerCode the value for SKU_T.OWNER_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder ownerCode(String ownerCode) {
            obj.setOwnerCode(ownerCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.SKU_CODE
         *
         * @param skuCode the value for SKU_T.SKU_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder skuCode(String skuCode) {
            obj.setSkuCode(skuCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.SKU_ALIAS
         *
         * @param skuAlias the value for SKU_T.SKU_ALIAS
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder skuAlias(String skuAlias) {
            obj.setSkuAlias(skuAlias);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.BARCODE
         *
         * @param barcode the value for SKU_T.BARCODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder barcode(String barcode) {
            obj.setBarcode(barcode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.SKU_DESCR
         *
         * @param skuDescr the value for SKU_T.SKU_DESCR
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder skuDescr(String skuDescr) {
            obj.setSkuDescr(skuDescr);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.SKU_TYPE
         *
         * @param skuType the value for SKU_T.SKU_TYPE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder skuType(String skuType) {
            obj.setSkuType(skuType);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.PACK_ID
         *
         * @param packId the value for SKU_T.PACK_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder packId(Long packId) {
            obj.setPackId(packId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.PACK_CODE
         *
         * @param packCode the value for SKU_T.PACK_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder packCode(String packCode) {
            obj.setPackCode(packCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.UOM
         *
         * @param uom the value for SKU_T.UOM
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder uom(String uom) {
            obj.setUom(uom);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.UOM_BILLING
         *
         * @param uomBilling the value for SKU_T.UOM_BILLING
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder uomBilling(String uomBilling) {
            obj.setUomBilling(uomBilling);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.VOLUME
         *
         * @param volume the value for SKU_T.VOLUME
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder volume(BigDecimal volume) {
            obj.setVolume(volume);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LENGTH
         *
         * @param length the value for SKU_T.LENGTH
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder length(BigDecimal length) {
            obj.setLength(length);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.WIDTH
         *
         * @param width the value for SKU_T.WIDTH
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder width(BigDecimal width) {
            obj.setWidth(width);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.HEIGHT
         *
         * @param height the value for SKU_T.HEIGHT
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder height(BigDecimal height) {
            obj.setHeight(height);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.WEIGHT_GROSS
         *
         * @param weightGross the value for SKU_T.WEIGHT_GROSS
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder weightGross(BigDecimal weightGross) {
            obj.setWeightGross(weightGross);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.WEIGHT_NET
         *
         * @param weightNet the value for SKU_T.WEIGHT_NET
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder weightNet(BigDecimal weightNet) {
            obj.setWeightNet(weightNet);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.WEIGHT_TARE
         *
         * @param weightTare the value for SKU_T.WEIGHT_TARE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder weightTare(BigDecimal weightTare) {
            obj.setWeightTare(weightTare);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_VALIDATE_ID
         *
         * @param lotValidateId the value for SKU_T.LOT_VALIDATE_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotValidateId(Long lotValidateId) {
            obj.setLotValidateId(lotValidateId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_VALIDATE_CODE
         *
         * @param lotValidateCode the value for SKU_T.LOT_VALIDATE_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotValidateCode(String lotValidateCode) {
            obj.setLotValidateCode(lotValidateCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.PUTAWAY_STRATEGY_ID
         *
         * @param putawayStrategyId the value for SKU_T.PUTAWAY_STRATEGY_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder putawayStrategyId(Long putawayStrategyId) {
            obj.setPutawayStrategyId(putawayStrategyId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.PUTAWAY_STRATEGY_CODE
         *
         * @param putawayStrategyCode the value for SKU_T.PUTAWAY_STRATEGY_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder putawayStrategyCode(String putawayStrategyCode) {
            obj.setPutawayStrategyCode(putawayStrategyCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.PUTAWAY_ZONE_CODE
         *
         * @param putawayZoneCode the value for SKU_T.PUTAWAY_ZONE_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder putawayZoneCode(String putawayZoneCode) {
            obj.setPutawayZoneCode(putawayZoneCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.PUTAWAY_LOCATION_CODE
         *
         * @param putawayLocationCode the value for SKU_T.PUTAWAY_LOCATION_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder putawayLocationCode(String putawayLocationCode) {
            obj.setPutawayLocationCode(putawayLocationCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.ALLOCATE_STRATEGY_ID
         *
         * @param allocateStrategyId the value for SKU_T.ALLOCATE_STRATEGY_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder allocateStrategyId(Long allocateStrategyId) {
            obj.setAllocateStrategyId(allocateStrategyId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.ALLOCATE_STRATEGY_CODE
         *
         * @param allocateStrategyCode the value for SKU_T.ALLOCATE_STRATEGY_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder allocateStrategyCode(String allocateStrategyCode) {
            obj.setAllocateStrategyCode(allocateStrategyCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_ATTRIBUTE1_LABEL
         *
         * @param lotAttribute1Label the value for SKU_T.LOT_ATTRIBUTE1_LABEL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute1Label(String lotAttribute1Label) {
            obj.setLotAttribute1Label(lotAttribute1Label);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_ATTRIBUTE2_LABEL
         *
         * @param lotAttribute2Label the value for SKU_T.LOT_ATTRIBUTE2_LABEL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute2Label(String lotAttribute2Label) {
            obj.setLotAttribute2Label(lotAttribute2Label);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_ATTRIBUTE3_LABEL
         *
         * @param lotAttribute3Label the value for SKU_T.LOT_ATTRIBUTE3_LABEL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute3Label(String lotAttribute3Label) {
            obj.setLotAttribute3Label(lotAttribute3Label);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_ATTRIBUTE4_LABEL
         *
         * @param lotAttribute4Label the value for SKU_T.LOT_ATTRIBUTE4_LABEL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute4Label(String lotAttribute4Label) {
            obj.setLotAttribute4Label(lotAttribute4Label);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_ATTRIBUTE5_LABEL
         *
         * @param lotAttribute5Label the value for SKU_T.LOT_ATTRIBUTE5_LABEL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute5Label(String lotAttribute5Label) {
            obj.setLotAttribute5Label(lotAttribute5Label);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_ATTRIBUTE6_LABEL
         *
         * @param lotAttribute6Label the value for SKU_T.LOT_ATTRIBUTE6_LABEL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute6Label(String lotAttribute6Label) {
            obj.setLotAttribute6Label(lotAttribute6Label);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_ATTRIBUTE7_LABEL
         *
         * @param lotAttribute7Label the value for SKU_T.LOT_ATTRIBUTE7_LABEL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute7Label(String lotAttribute7Label) {
            obj.setLotAttribute7Label(lotAttribute7Label);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_ATTRIBUTE8_LABEL
         *
         * @param lotAttribute8Label the value for SKU_T.LOT_ATTRIBUTE8_LABEL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute8Label(String lotAttribute8Label) {
            obj.setLotAttribute8Label(lotAttribute8Label);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_ATTRIBUTE9_LABEL
         *
         * @param lotAttribute9Label the value for SKU_T.LOT_ATTRIBUTE9_LABEL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute9Label(String lotAttribute9Label) {
            obj.setLotAttribute9Label(lotAttribute9Label);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_ATTRIBUTE10_LABEL
         *
         * @param lotAttribute10Label the value for SKU_T.LOT_ATTRIBUTE10_LABEL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute10Label(String lotAttribute10Label) {
            obj.setLotAttribute10Label(lotAttribute10Label);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_ATTRIBUTE11_LABEL
         *
         * @param lotAttribute11Label the value for SKU_T.LOT_ATTRIBUTE11_LABEL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute11Label(String lotAttribute11Label) {
            obj.setLotAttribute11Label(lotAttribute11Label);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.LOT_ATTRIBUTE12_LABEL
         *
         * @param lotAttribute12Label the value for SKU_T.LOT_ATTRIBUTE12_LABEL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute12Label(String lotAttribute12Label) {
            obj.setLotAttribute12Label(lotAttribute12Label);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.ACTIVE
         *
         * @param active the value for SKU_T.ACTIVE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder active(String active) {
            obj.setActive(active);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.REMARK
         *
         * @param remark the value for SKU_T.REMARK
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.COMPANY_ID
         *
         * @param companyId the value for SKU_T.COMPANY_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder companyId(Long companyId) {
            obj.setCompanyId(companyId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.WAREHOUSE_ID
         *
         * @param warehouseId the value for SKU_T.WAREHOUSE_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder warehouseId(Long warehouseId) {
            obj.setWarehouseId(warehouseId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.DEL_FLAG
         *
         * @param delFlag the value for SKU_T.DEL_FLAG
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder delFlag(String delFlag) {
            obj.setDelFlag(delFlag);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.CREATE_BY
         *
         * @param createBy the value for SKU_T.CREATE_BY
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder createBy(String createBy) {
            obj.setCreateBy(createBy);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.CREATE_TIME
         *
         * @param createTime the value for SKU_T.CREATE_TIME
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder createTime(Date createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.UPDATE_BY
         *
         * @param updateBy the value for SKU_T.UPDATE_BY
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder updateBy(String updateBy) {
            obj.setUpdateBy(updateBy);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.UPDATE_TIME
         *
         * @param updateTime the value for SKU_T.UPDATE_TIME
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder updateTime(Date updateTime) {
            obj.setUpdateTime(updateTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.UPDATE_VERSION
         *
         * @param updateVersion the value for SKU_T.UPDATE_VERSION
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder updateVersion(Long updateVersion) {
            obj.setUpdateVersion(updateVersion);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column SKU_T.DESCRIPTION
         *
         * @param description the value for SKU_T.DESCRIPTION
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder description(String description) {
            obj.setDescription(description);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public SkuTEntity build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table SKU_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum DelFlag {
        NOT_DELETED(new String("N"), "未删除"),
        IS_DELETED(new String("Y"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        DelFlag(String value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table SKU_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        skuId("SKU_ID", "skuId", "DECIMAL", false),
        ownerId("OWNER_ID", "ownerId", "DECIMAL", false),
        ownerCode("OWNER_CODE", "ownerCode", "VARCHAR", false),
        skuCode("SKU_CODE", "skuCode", "VARCHAR", false),
        skuAlias("SKU_ALIAS", "skuAlias", "VARCHAR", false),
        barcode("BARCODE", "barcode", "VARCHAR", false),
        skuDescr("SKU_DESCR", "skuDescr", "VARCHAR", false),
        skuType("SKU_TYPE", "skuType", "VARCHAR", false),
        packId("PACK_ID", "packId", "DECIMAL", false),
        packCode("PACK_CODE", "packCode", "VARCHAR", false),
        uom("UOM", "uom", "VARCHAR", false),
        volume("VOLUME", "volume", "DECIMAL", false),
        length("LENGTH", "length", "DECIMAL", true),
        width("WIDTH", "width", "DECIMAL", false),
        height("HEIGHT", "height", "DECIMAL", false),
        weightGross("WEIGHT_GROSS", "weightGross", "DECIMAL", false),
        weightNet("WEIGHT_NET", "weightNet", "DECIMAL", false),
        weightTare("WEIGHT_TARE", "weightTare", "DECIMAL", false),
        lotValidateId("LOT_VALIDATE_ID", "lotValidateId", "DECIMAL", false),
        lotValidateCode("LOT_VALIDATE_CODE", "lotValidateCode", "VARCHAR", false),
        putawayStrategyId("PUTAWAY_STRATEGY_ID", "putawayStrategyId", "DECIMAL", false),
        putawayStrategyCode("PUTAWAY_STRATEGY_CODE", "putawayStrategyCode", "VARCHAR", false),
        putawayZoneCode("PUTAWAY_ZONE_CODE", "putawayZoneCode", "VARCHAR", false),
        putawayLocationCode("PUTAWAY_LOCATION_CODE", "putawayLocationCode", "VARCHAR", false),
        allocateStrategyId("ALLOCATE_STRATEGY_ID", "allocateStrategyId", "DECIMAL", false),
        allocateStrategyCode("ALLOCATE_STRATEGY_CODE", "allocateStrategyCode", "VARCHAR", false),
        lotAttribute1Label("LOT_ATTRIBUTE1_LABEL", "lotAttribute1Label", "VARCHAR", false),
        lotAttribute2Label("LOT_ATTRIBUTE2_LABEL", "lotAttribute2Label", "VARCHAR", false),
        lotAttribute3Label("LOT_ATTRIBUTE3_LABEL", "lotAttribute3Label", "VARCHAR", false),
        lotAttribute4Label("LOT_ATTRIBUTE4_LABEL", "lotAttribute4Label", "VARCHAR", false),
        lotAttribute5Label("LOT_ATTRIBUTE5_LABEL", "lotAttribute5Label", "VARCHAR", false),
        lotAttribute6Label("LOT_ATTRIBUTE6_LABEL", "lotAttribute6Label", "VARCHAR", false),
        lotAttribute7Label("LOT_ATTRIBUTE7_LABEL", "lotAttribute7Label", "VARCHAR", false),
        lotAttribute8Label("LOT_ATTRIBUTE8_LABEL", "lotAttribute8Label", "VARCHAR", false),
        lotAttribute9Label("LOT_ATTRIBUTE9_LABEL", "lotAttribute9Label", "VARCHAR", false),
        lotAttribute10Label("LOT_ATTRIBUTE10_LABEL", "lotAttribute10Label", "VARCHAR", false),
        lotAttribute11Label("LOT_ATTRIBUTE11_LABEL", "lotAttribute11Label", "VARCHAR", false),
        lotAttribute12Label("LOT_ATTRIBUTE12_LABEL", "lotAttribute12Label", "VARCHAR", false),
        active("ACTIVE", "active", "CHAR", false),
        remark("REMARK", "remark", "VARCHAR", false),
        companyId("COMPANY_ID", "companyId", "DECIMAL", false),
        warehouseId("WAREHOUSE_ID", "warehouseId", "DECIMAL", false),
        delFlag("DEL_FLAG", "delFlag", "CHAR", false),
        createBy("CREATE_BY", "createBy", "VARCHAR", false),
        createTime("CREATE_TIME", "createTime", "TIMESTAMP", false),
        updateBy("UPDATE_BY", "updateBy", "VARCHAR", false),
        updateTime("UPDATE_TIME", "updateTime", "TIMESTAMP", false),
        updateVersion("UPDATE_VERSION", "updateVersion", "DECIMAL", false),
        description("DESCRIPTION", "description", "VARCHAR", false),
        uomBilling("UOM_BILLING", "uomBilling", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table SKU_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}