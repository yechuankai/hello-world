package com.wms.entity.auto;

import com.wms.entity.BaseEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class EntInventoryOnhandTEntity extends BaseEntity {
    private Long inventoryOnhandId;

    private Long ownerId;

    private String ownerCode;

    private Long skuId;

    private String skuCode;

    private String skuAlias;

    private String packCode;

    private String lotAttribute1;

    private String lotAttribute2;

    private String lotAttribute3;

    private String lotAttribute4;

    private String lotAttribute5;

    private String lotAttribute6;

    private String lotAttribute7;

    private String lotAttribute8;

    private String lotAttribute9;

    private String lotAttribute10;

    private String lotAttribute11;

    private String lotAttribute12;

    private BigDecimal quantityOnhand;

    private BigDecimal quantityAllocated;

    private BigDecimal quantityLocked;

    private BigDecimal qtyCase;

    private BigDecimal volume;

    private BigDecimal weightGross;

    private BigDecimal weightNet;

    private BigDecimal weightTare;

    private Long locationId;

    private String locationCode;

    private Long lpnId;

    private String lpnNumber;

    private Long lotId;

    private String lotNumber;

    private Long companyId;

    private Long warehouseId;

    private String delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Long updateVersion;

    private String description;

    public Long getInventoryOnhandId() {
        return inventoryOnhandId;
    }

    public void setInventoryOnhandId(Long inventoryOnhandId) {
        this.inventoryOnhandId = inventoryOnhandId;
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

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
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

    public String getPackCode() {
        return packCode;
    }

    public void setPackCode(String packCode) {
        this.packCode = packCode == null ? null : packCode.trim();
    }

    public String getLotAttribute1() {
        return lotAttribute1;
    }

    public void setLotAttribute1(String lotAttribute1) {
        this.lotAttribute1 = lotAttribute1 == null ? null : lotAttribute1.trim();
    }

    public String getLotAttribute2() {
        return lotAttribute2;
    }

    public void setLotAttribute2(String lotAttribute2) {
        this.lotAttribute2 = lotAttribute2 == null ? null : lotAttribute2.trim();
    }

    public String getLotAttribute3() {
        return lotAttribute3;
    }

    public void setLotAttribute3(String lotAttribute3) {
        this.lotAttribute3 = lotAttribute3 == null ? null : lotAttribute3.trim();
    }

    public String getLotAttribute4() {
        return lotAttribute4;
    }

    public void setLotAttribute4(String lotAttribute4) {
        this.lotAttribute4 = lotAttribute4 == null ? null : lotAttribute4.trim();
    }

    public String getLotAttribute5() {
        return lotAttribute5;
    }

    public void setLotAttribute5(String lotAttribute5) {
        this.lotAttribute5 = lotAttribute5 == null ? null : lotAttribute5.trim();
    }

    public String getLotAttribute6() {
        return lotAttribute6;
    }

    public void setLotAttribute6(String lotAttribute6) {
        this.lotAttribute6 = lotAttribute6 == null ? null : lotAttribute6.trim();
    }

    public String getLotAttribute7() {
        return lotAttribute7;
    }

    public void setLotAttribute7(String lotAttribute7) {
        this.lotAttribute7 = lotAttribute7 == null ? null : lotAttribute7.trim();
    }

    public String getLotAttribute8() {
        return lotAttribute8;
    }

    public void setLotAttribute8(String lotAttribute8) {
        this.lotAttribute8 = lotAttribute8 == null ? null : lotAttribute8.trim();
    }

    public String getLotAttribute9() {
        return lotAttribute9;
    }

    public void setLotAttribute9(String lotAttribute9) {
        this.lotAttribute9 = lotAttribute9 == null ? null : lotAttribute9.trim();
    }

    public String getLotAttribute10() {
        return lotAttribute10;
    }

    public void setLotAttribute10(String lotAttribute10) {
        this.lotAttribute10 = lotAttribute10 == null ? null : lotAttribute10.trim();
    }

    public String getLotAttribute11() {
        return lotAttribute11;
    }

    public void setLotAttribute11(String lotAttribute11) {
        this.lotAttribute11 = lotAttribute11 == null ? null : lotAttribute11.trim();
    }

    public String getLotAttribute12() {
        return lotAttribute12;
    }

    public void setLotAttribute12(String lotAttribute12) {
        this.lotAttribute12 = lotAttribute12 == null ? null : lotAttribute12.trim();
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

    public BigDecimal getQtyCase() {
        return qtyCase;
    }

    public void setQtyCase(BigDecimal qtyCase) {
        this.qtyCase = qtyCase;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode == null ? null : locationCode.trim();
    }

    public Long getLpnId() {
        return lpnId;
    }

    public void setLpnId(Long lpnId) {
        this.lpnId = lpnId;
    }

    public String getLpnNumber() {
        return lpnNumber;
    }

    public void setLpnNumber(String lpnNumber) {
        this.lpnNumber = lpnNumber == null ? null : lpnNumber.trim();
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber == null ? null : lotNumber.trim();
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
     * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", inventoryOnhandId=").append(inventoryOnhandId);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", skuId=").append(skuId);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", skuAlias=").append(skuAlias);
        sb.append(", packCode=").append(packCode);
        sb.append(", lotAttribute1=").append(lotAttribute1);
        sb.append(", lotAttribute2=").append(lotAttribute2);
        sb.append(", lotAttribute3=").append(lotAttribute3);
        sb.append(", lotAttribute4=").append(lotAttribute4);
        sb.append(", lotAttribute5=").append(lotAttribute5);
        sb.append(", lotAttribute6=").append(lotAttribute6);
        sb.append(", lotAttribute7=").append(lotAttribute7);
        sb.append(", lotAttribute8=").append(lotAttribute8);
        sb.append(", lotAttribute9=").append(lotAttribute9);
        sb.append(", lotAttribute10=").append(lotAttribute10);
        sb.append(", lotAttribute11=").append(lotAttribute11);
        sb.append(", lotAttribute12=").append(lotAttribute12);
        sb.append(", quantityOnhand=").append(quantityOnhand);
        sb.append(", quantityAllocated=").append(quantityAllocated);
        sb.append(", quantityLocked=").append(quantityLocked);
        sb.append(", qtyCase=").append(qtyCase);
        sb.append(", volume=").append(volume);
        sb.append(", weightGross=").append(weightGross);
        sb.append(", weightNet=").append(weightNet);
        sb.append(", weightTare=").append(weightTare);
        sb.append(", locationId=").append(locationId);
        sb.append(", locationCode=").append(locationCode);
        sb.append(", lpnId=").append(lpnId);
        sb.append(", lpnNumber=").append(lpnNumber);
        sb.append(", lotId=").append(lotId);
        sb.append(", lotNumber=").append(lotNumber);
        sb.append(", companyId=").append(companyId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateVersion=").append(updateVersion);
        sb.append(", description=").append(description);
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
        EntInventoryOnhandTEntity other = (EntInventoryOnhandTEntity) that;
        return (this.getInventoryOnhandId() == null ? other.getInventoryOnhandId() == null : this.getInventoryOnhandId().equals(other.getInventoryOnhandId()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getOwnerCode() == null ? other.getOwnerCode() == null : this.getOwnerCode().equals(other.getOwnerCode()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getSkuCode() == null ? other.getSkuCode() == null : this.getSkuCode().equals(other.getSkuCode()))
            && (this.getSkuAlias() == null ? other.getSkuAlias() == null : this.getSkuAlias().equals(other.getSkuAlias()))
            && (this.getPackCode() == null ? other.getPackCode() == null : this.getPackCode().equals(other.getPackCode()))
            && (this.getLotAttribute1() == null ? other.getLotAttribute1() == null : this.getLotAttribute1().equals(other.getLotAttribute1()))
            && (this.getLotAttribute2() == null ? other.getLotAttribute2() == null : this.getLotAttribute2().equals(other.getLotAttribute2()))
            && (this.getLotAttribute3() == null ? other.getLotAttribute3() == null : this.getLotAttribute3().equals(other.getLotAttribute3()))
            && (this.getLotAttribute4() == null ? other.getLotAttribute4() == null : this.getLotAttribute4().equals(other.getLotAttribute4()))
            && (this.getLotAttribute5() == null ? other.getLotAttribute5() == null : this.getLotAttribute5().equals(other.getLotAttribute5()))
            && (this.getLotAttribute6() == null ? other.getLotAttribute6() == null : this.getLotAttribute6().equals(other.getLotAttribute6()))
            && (this.getLotAttribute7() == null ? other.getLotAttribute7() == null : this.getLotAttribute7().equals(other.getLotAttribute7()))
            && (this.getLotAttribute8() == null ? other.getLotAttribute8() == null : this.getLotAttribute8().equals(other.getLotAttribute8()))
            && (this.getLotAttribute9() == null ? other.getLotAttribute9() == null : this.getLotAttribute9().equals(other.getLotAttribute9()))
            && (this.getLotAttribute10() == null ? other.getLotAttribute10() == null : this.getLotAttribute10().equals(other.getLotAttribute10()))
            && (this.getLotAttribute11() == null ? other.getLotAttribute11() == null : this.getLotAttribute11().equals(other.getLotAttribute11()))
            && (this.getLotAttribute12() == null ? other.getLotAttribute12() == null : this.getLotAttribute12().equals(other.getLotAttribute12()))
            && (this.getQuantityOnhand() == null ? other.getQuantityOnhand() == null : this.getQuantityOnhand().equals(other.getQuantityOnhand()))
            && (this.getQuantityAllocated() == null ? other.getQuantityAllocated() == null : this.getQuantityAllocated().equals(other.getQuantityAllocated()))
            && (this.getQuantityLocked() == null ? other.getQuantityLocked() == null : this.getQuantityLocked().equals(other.getQuantityLocked()))
            && (this.getQtyCase() == null ? other.getQtyCase() == null : this.getQtyCase().equals(other.getQtyCase()))
            && (this.getVolume() == null ? other.getVolume() == null : this.getVolume().equals(other.getVolume()))
            && (this.getWeightGross() == null ? other.getWeightGross() == null : this.getWeightGross().equals(other.getWeightGross()))
            && (this.getWeightNet() == null ? other.getWeightNet() == null : this.getWeightNet().equals(other.getWeightNet()))
            && (this.getWeightTare() == null ? other.getWeightTare() == null : this.getWeightTare().equals(other.getWeightTare()))
            && (this.getLocationId() == null ? other.getLocationId() == null : this.getLocationId().equals(other.getLocationId()))
            && (this.getLocationCode() == null ? other.getLocationCode() == null : this.getLocationCode().equals(other.getLocationCode()))
            && (this.getLpnId() == null ? other.getLpnId() == null : this.getLpnId().equals(other.getLpnId()))
            && (this.getLpnNumber() == null ? other.getLpnNumber() == null : this.getLpnNumber().equals(other.getLpnNumber()))
            && (this.getLotId() == null ? other.getLotId() == null : this.getLotId().equals(other.getLotId()))
            && (this.getLotNumber() == null ? other.getLotNumber() == null : this.getLotNumber().equals(other.getLotNumber()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getWarehouseId() == null ? other.getWarehouseId() == null : this.getWarehouseId().equals(other.getWarehouseId()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateVersion() == null ? other.getUpdateVersion() == null : this.getUpdateVersion().equals(other.getUpdateVersion()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInventoryOnhandId() == null) ? 0 : getInventoryOnhandId().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getOwnerCode() == null) ? 0 : getOwnerCode().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getSkuCode() == null) ? 0 : getSkuCode().hashCode());
        result = prime * result + ((getSkuAlias() == null) ? 0 : getSkuAlias().hashCode());
        result = prime * result + ((getPackCode() == null) ? 0 : getPackCode().hashCode());
        result = prime * result + ((getLotAttribute1() == null) ? 0 : getLotAttribute1().hashCode());
        result = prime * result + ((getLotAttribute2() == null) ? 0 : getLotAttribute2().hashCode());
        result = prime * result + ((getLotAttribute3() == null) ? 0 : getLotAttribute3().hashCode());
        result = prime * result + ((getLotAttribute4() == null) ? 0 : getLotAttribute4().hashCode());
        result = prime * result + ((getLotAttribute5() == null) ? 0 : getLotAttribute5().hashCode());
        result = prime * result + ((getLotAttribute6() == null) ? 0 : getLotAttribute6().hashCode());
        result = prime * result + ((getLotAttribute7() == null) ? 0 : getLotAttribute7().hashCode());
        result = prime * result + ((getLotAttribute8() == null) ? 0 : getLotAttribute8().hashCode());
        result = prime * result + ((getLotAttribute9() == null) ? 0 : getLotAttribute9().hashCode());
        result = prime * result + ((getLotAttribute10() == null) ? 0 : getLotAttribute10().hashCode());
        result = prime * result + ((getLotAttribute11() == null) ? 0 : getLotAttribute11().hashCode());
        result = prime * result + ((getLotAttribute12() == null) ? 0 : getLotAttribute12().hashCode());
        result = prime * result + ((getQuantityOnhand() == null) ? 0 : getQuantityOnhand().hashCode());
        result = prime * result + ((getQuantityAllocated() == null) ? 0 : getQuantityAllocated().hashCode());
        result = prime * result + ((getQuantityLocked() == null) ? 0 : getQuantityLocked().hashCode());
        result = prime * result + ((getQtyCase() == null) ? 0 : getQtyCase().hashCode());
        result = prime * result + ((getVolume() == null) ? 0 : getVolume().hashCode());
        result = prime * result + ((getWeightGross() == null) ? 0 : getWeightGross().hashCode());
        result = prime * result + ((getWeightNet() == null) ? 0 : getWeightNet().hashCode());
        result = prime * result + ((getWeightTare() == null) ? 0 : getWeightTare().hashCode());
        result = prime * result + ((getLocationId() == null) ? 0 : getLocationId().hashCode());
        result = prime * result + ((getLocationCode() == null) ? 0 : getLocationCode().hashCode());
        result = prime * result + ((getLpnId() == null) ? 0 : getLpnId().hashCode());
        result = prime * result + ((getLpnNumber() == null) ? 0 : getLpnNumber().hashCode());
        result = prime * result + ((getLotId() == null) ? 0 : getLotId().hashCode());
        result = prime * result + ((getLotNumber() == null) ? 0 : getLotNumber().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getWarehouseId() == null) ? 0 : getWarehouseId().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateVersion() == null) ? 0 : getUpdateVersion().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static EntInventoryOnhandTEntity.Builder builder() {
        return new EntInventoryOnhandTEntity.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ENT_INVETNROY_ONHAND_V
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private EntInventoryOnhandTEntity obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new EntInventoryOnhandTEntity();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.INVENTORY_ONHAND_ID
         *
         * @param inventoryOnhandId the value for ENT_INVETNROY_ONHAND_V.INVENTORY_ONHAND_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder inventoryOnhandId(Long inventoryOnhandId) {
            obj.setInventoryOnhandId(inventoryOnhandId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.OWNER_ID
         *
         * @param ownerId the value for ENT_INVETNROY_ONHAND_V.OWNER_ID
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.OWNER_CODE
         *
         * @param ownerCode the value for ENT_INVETNROY_ONHAND_V.OWNER_CODE
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.SKU_ID
         *
         * @param skuId the value for ENT_INVETNROY_ONHAND_V.SKU_ID
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.SKU_CODE
         *
         * @param skuCode the value for ENT_INVETNROY_ONHAND_V.SKU_CODE
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.SKU_ALIAS
         *
         * @param skuAlias the value for ENT_INVETNROY_ONHAND_V.SKU_ALIAS
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.PACK_CODE
         *
         * @param packCode the value for ENT_INVETNROY_ONHAND_V.PACK_CODE
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE1
         *
         * @param lotAttribute1 the value for ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE1
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute1(String lotAttribute1) {
            obj.setLotAttribute1(lotAttribute1);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE12
         *
         * @param lotAttribute12 the value for ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE12
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute12(String lotAttribute12) {
            obj.setLotAttribute12(lotAttribute12);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE11
         *
         * @param lotAttribute11 the value for ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE11
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute11(String lotAttribute11) {
            obj.setLotAttribute11(lotAttribute11);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE10
         *
         * @param lotAttribute10 the value for ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE10
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute10(String lotAttribute10) {
            obj.setLotAttribute10(lotAttribute10);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE2
         *
         * @param lotAttribute2 the value for ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE2
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute2(String lotAttribute2) {
            obj.setLotAttribute2(lotAttribute2);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE3
         *
         * @param lotAttribute3 the value for ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE3
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute3(String lotAttribute3) {
            obj.setLotAttribute3(lotAttribute3);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE4
         *
         * @param lotAttribute4 the value for ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE4
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute4(String lotAttribute4) {
            obj.setLotAttribute4(lotAttribute4);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE5
         *
         * @param lotAttribute5 the value for ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE5
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute5(String lotAttribute5) {
            obj.setLotAttribute5(lotAttribute5);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE6
         *
         * @param lotAttribute6 the value for ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE6
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute6(String lotAttribute6) {
            obj.setLotAttribute6(lotAttribute6);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE7
         *
         * @param lotAttribute7 the value for ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE7
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute7(String lotAttribute7) {
            obj.setLotAttribute7(lotAttribute7);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE8
         *
         * @param lotAttribute8 the value for ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE8
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute8(String lotAttribute8) {
            obj.setLotAttribute8(lotAttribute8);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE9
         *
         * @param lotAttribute9 the value for ENT_INVETNROY_ONHAND_V.LOT_ATTRIBUTE9
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute9(String lotAttribute9) {
            obj.setLotAttribute9(lotAttribute9);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.QUANTITY_ONHAND
         *
         * @param quantityOnhand the value for ENT_INVETNROY_ONHAND_V.QUANTITY_ONHAND
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantityOnhand(BigDecimal quantityOnhand) {
            obj.setQuantityOnhand(quantityOnhand);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.QUANTITY_ALLOCATED
         *
         * @param quantityAllocated the value for ENT_INVETNROY_ONHAND_V.QUANTITY_ALLOCATED
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantityAllocated(BigDecimal quantityAllocated) {
            obj.setQuantityAllocated(quantityAllocated);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.QUANTITY_LOCKED
         *
         * @param quantityLocked the value for ENT_INVETNROY_ONHAND_V.QUANTITY_LOCKED
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantityLocked(BigDecimal quantityLocked) {
            obj.setQuantityLocked(quantityLocked);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.QTY_CASE
         *
         * @param qtyCase the value for ENT_INVETNROY_ONHAND_V.QTY_CASE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder qtyCase(BigDecimal qtyCase) {
            obj.setQtyCase(qtyCase);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.VOLUME
         *
         * @param volume the value for ENT_INVETNROY_ONHAND_V.VOLUME
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.WEIGHT_GROSS
         *
         * @param weightGross the value for ENT_INVETNROY_ONHAND_V.WEIGHT_GROSS
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.WEIGHT_NET
         *
         * @param weightNet the value for ENT_INVETNROY_ONHAND_V.WEIGHT_NET
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.WEIGHT_TARE
         *
         * @param weightTare the value for ENT_INVETNROY_ONHAND_V.WEIGHT_TARE
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOCATION_ID
         *
         * @param locationId the value for ENT_INVETNROY_ONHAND_V.LOCATION_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder locationId(Long locationId) {
            obj.setLocationId(locationId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOCATION_CODE
         *
         * @param locationCode the value for ENT_INVETNROY_ONHAND_V.LOCATION_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder locationCode(String locationCode) {
            obj.setLocationCode(locationCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LPN_ID
         *
         * @param lpnId the value for ENT_INVETNROY_ONHAND_V.LPN_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lpnId(Long lpnId) {
            obj.setLpnId(lpnId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LPN_NUMBER
         *
         * @param lpnNumber the value for ENT_INVETNROY_ONHAND_V.LPN_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lpnNumber(String lpnNumber) {
            obj.setLpnNumber(lpnNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_ID
         *
         * @param lotId the value for ENT_INVETNROY_ONHAND_V.LOT_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotId(Long lotId) {
            obj.setLotId(lotId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.LOT_NUMBER
         *
         * @param lotNumber the value for ENT_INVETNROY_ONHAND_V.LOT_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotNumber(String lotNumber) {
            obj.setLotNumber(lotNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.COMPANY_ID
         *
         * @param companyId the value for ENT_INVETNROY_ONHAND_V.COMPANY_ID
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.WAREHOUSE_ID
         *
         * @param warehouseId the value for ENT_INVETNROY_ONHAND_V.WAREHOUSE_ID
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.DEL_FLAG
         *
         * @param delFlag the value for ENT_INVETNROY_ONHAND_V.DEL_FLAG
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.CREATE_BY
         *
         * @param createBy the value for ENT_INVETNROY_ONHAND_V.CREATE_BY
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.CREATE_TIME
         *
         * @param createTime the value for ENT_INVETNROY_ONHAND_V.CREATE_TIME
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.UPDATE_BY
         *
         * @param updateBy the value for ENT_INVETNROY_ONHAND_V.UPDATE_BY
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.UPDATE_TIME
         *
         * @param updateTime the value for ENT_INVETNROY_ONHAND_V.UPDATE_TIME
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.UPDATE_VERSION
         *
         * @param updateVersion the value for ENT_INVETNROY_ONHAND_V.UPDATE_VERSION
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
         * This method sets the value of the database column ENT_INVETNROY_ONHAND_V.DESCRIPTION
         *
         * @param description the value for ENT_INVETNROY_ONHAND_V.DESCRIPTION
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
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public EntInventoryOnhandTEntity build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table ENT_INVETNROY_ONHAND_V
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum DelFlag {
        NOT_DELETED(new String("N"), "未删除"),
        IS_DELETED(new String("Y"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
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
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
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
     * This enum corresponds to the database table ENT_INVETNROY_ONHAND_V
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        inventoryOnhandId("INVENTORY_ONHAND_ID", "inventoryOnhandId", "DECIMAL", false),
        ownerId("OWNER_ID", "ownerId", "DECIMAL", false),
        ownerCode("OWNER_CODE", "ownerCode", "VARCHAR", false),
        skuId("SKU_ID", "skuId", "DECIMAL", false),
        skuCode("SKU_CODE", "skuCode", "VARCHAR", false),
        skuAlias("SKU_ALIAS", "skuAlias", "VARCHAR", false),
        packCode("PACK_CODE", "packCode", "VARCHAR", false),
        lotAttribute1("LOT_ATTRIBUTE1", "lotAttribute1", "VARCHAR", false),
        lotAttribute2("LOT_ATTRIBUTE2", "lotAttribute2", "VARCHAR", false),
        lotAttribute3("LOT_ATTRIBUTE3", "lotAttribute3", "CHAR", false),
        lotAttribute4("LOT_ATTRIBUTE4", "lotAttribute4", "CHAR", false),
        lotAttribute5("LOT_ATTRIBUTE5", "lotAttribute5", "CHAR", false),
        lotAttribute6("LOT_ATTRIBUTE6", "lotAttribute6", "CHAR", false),
        lotAttribute7("LOT_ATTRIBUTE7", "lotAttribute7", "CHAR", false),
        lotAttribute8("LOT_ATTRIBUTE8", "lotAttribute8", "CHAR", false),
        lotAttribute9("LOT_ATTRIBUTE9", "lotAttribute9", "CHAR", false),
        lotAttribute10("LOT_ATTRIBUTE10", "lotAttribute10", "CHAR", false),
        lotAttribute11("LOT_ATTRIBUTE11", "lotAttribute11", "CHAR", false),
        lotAttribute12("LOT_ATTRIBUTE12", "lotAttribute12", "CHAR", false),
        quantityOnhand("QUANTITY_ONHAND", "quantityOnhand", "DECIMAL", false),
        quantityAllocated("QUANTITY_ALLOCATED", "quantityAllocated", "DECIMAL", false),
        quantityLocked("QUANTITY_LOCKED", "quantityLocked", "DECIMAL", false),
        qtyCase("QTY_CASE", "qtyCase", "DECIMAL", false),
        volume("VOLUME", "volume", "DECIMAL", false),
        weightGross("WEIGHT_GROSS", "weightGross", "DECIMAL", false),
        weightNet("WEIGHT_NET", "weightNet", "DECIMAL", false),
        weightTare("WEIGHT_TARE", "weightTare", "DECIMAL", false),
        locationId("LOCATION_ID", "locationId", "DECIMAL", false),
        locationCode("LOCATION_CODE", "locationCode", "CHAR", false),
        lpnId("LPN_ID", "lpnId", "DECIMAL", false),
        lpnNumber("LPN_NUMBER", "lpnNumber", "CHAR", false),
        lotId("LOT_ID", "lotId", "DECIMAL", false),
        lotNumber("LOT_NUMBER", "lotNumber", "CHAR", false),
        companyId("COMPANY_ID", "companyId", "DECIMAL", false),
        warehouseId("WAREHOUSE_ID", "warehouseId", "DECIMAL", false),
        delFlag("DEL_FLAG", "delFlag", "CHAR", false),
        createBy("CREATE_BY", "createBy", "CHAR", false),
        createTime("CREATE_TIME", "createTime", "TIMESTAMP", false),
        updateBy("UPDATE_BY", "updateBy", "CHAR", false),
        updateTime("UPDATE_TIME", "updateTime", "TIMESTAMP", false),
        updateVersion("UPDATE_VERSION", "updateVersion", "DECIMAL", false),
        description("DESCRIPTION", "description", "CHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
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
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
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
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
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
         * This method corresponds to the database table ENT_INVETNROY_ONHAND_V
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}