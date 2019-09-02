package com.wms.entity.auto;

import com.wms.entity.BaseEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class InventoryCountDetailTEntity extends BaseEntity {
    private Long inventoryCountDetailId;

    private Long inventoryCountHeaderId;

    private Long lineNumber;

    private Long sourceLineNumber;

    private Long inventoryOnhandId;

    private Long ownerId;

    private String ownerCode;

    private Long skuId;

    private String skuCode;

    private String skuAlias;

    private Long locationId;

    private String locationCode;

    private Long lpnId;

    private String lpnNumber;

    private Long lotId;

    private String lotNumber;

    private BigDecimal quantitySystem;

    private BigDecimal quantityCount;

    private BigDecimal quantityReplay;

    private BigDecimal quantityDifference;

    private BigDecimal quantityConfirm;

    private String status;

    private String reason;

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

    public Long getInventoryCountDetailId() {
        return inventoryCountDetailId;
    }

    public void setInventoryCountDetailId(Long inventoryCountDetailId) {
        this.inventoryCountDetailId = inventoryCountDetailId;
    }

    public Long getInventoryCountHeaderId() {
        return inventoryCountHeaderId;
    }

    public void setInventoryCountHeaderId(Long inventoryCountHeaderId) {
        this.inventoryCountHeaderId = inventoryCountHeaderId;
    }

    public Long getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Long getSourceLineNumber() {
        return sourceLineNumber;
    }

    public void setSourceLineNumber(Long sourceLineNumber) {
        this.sourceLineNumber = sourceLineNumber;
    }

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

    public BigDecimal getQuantitySystem() {
        return quantitySystem;
    }

    public void setQuantitySystem(BigDecimal quantitySystem) {
        this.quantitySystem = quantitySystem;
    }

    public BigDecimal getQuantityCount() {
        return quantityCount;
    }

    public void setQuantityCount(BigDecimal quantityCount) {
        this.quantityCount = quantityCount;
    }

    public BigDecimal getQuantityReplay() {
        return quantityReplay;
    }

    public void setQuantityReplay(BigDecimal quantityReplay) {
        this.quantityReplay = quantityReplay;
    }

    public BigDecimal getQuantityDifference() {
        return quantityDifference;
    }

    public void setQuantityDifference(BigDecimal quantityDifference) {
        this.quantityDifference = quantityDifference;
    }

    public BigDecimal getQuantityConfirm() {
        return quantityConfirm;
    }

    public void setQuantityConfirm(BigDecimal quantityConfirm) {
        this.quantityConfirm = quantityConfirm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
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
     * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
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
        sb.append(", inventoryCountDetailId=").append(inventoryCountDetailId);
        sb.append(", inventoryCountHeaderId=").append(inventoryCountHeaderId);
        sb.append(", lineNumber=").append(lineNumber);
        sb.append(", sourceLineNumber=").append(sourceLineNumber);
        sb.append(", inventoryOnhandId=").append(inventoryOnhandId);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", skuId=").append(skuId);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", skuAlias=").append(skuAlias);
        sb.append(", locationId=").append(locationId);
        sb.append(", locationCode=").append(locationCode);
        sb.append(", lpnId=").append(lpnId);
        sb.append(", lpnNumber=").append(lpnNumber);
        sb.append(", lotId=").append(lotId);
        sb.append(", lotNumber=").append(lotNumber);
        sb.append(", quantitySystem=").append(quantitySystem);
        sb.append(", quantityCount=").append(quantityCount);
        sb.append(", quantityReplay=").append(quantityReplay);
        sb.append(", quantityDifference=").append(quantityDifference);
        sb.append(", quantityConfirm=").append(quantityConfirm);
        sb.append(", status=").append(status);
        sb.append(", reason=").append(reason);
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
        InventoryCountDetailTEntity other = (InventoryCountDetailTEntity) that;
        return (this.getInventoryCountDetailId() == null ? other.getInventoryCountDetailId() == null : this.getInventoryCountDetailId().equals(other.getInventoryCountDetailId()))
            && (this.getInventoryCountHeaderId() == null ? other.getInventoryCountHeaderId() == null : this.getInventoryCountHeaderId().equals(other.getInventoryCountHeaderId()))
            && (this.getLineNumber() == null ? other.getLineNumber() == null : this.getLineNumber().equals(other.getLineNumber()))
            && (this.getSourceLineNumber() == null ? other.getSourceLineNumber() == null : this.getSourceLineNumber().equals(other.getSourceLineNumber()))
            && (this.getInventoryOnhandId() == null ? other.getInventoryOnhandId() == null : this.getInventoryOnhandId().equals(other.getInventoryOnhandId()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getOwnerCode() == null ? other.getOwnerCode() == null : this.getOwnerCode().equals(other.getOwnerCode()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getSkuCode() == null ? other.getSkuCode() == null : this.getSkuCode().equals(other.getSkuCode()))
            && (this.getSkuAlias() == null ? other.getSkuAlias() == null : this.getSkuAlias().equals(other.getSkuAlias()))
            && (this.getLocationId() == null ? other.getLocationId() == null : this.getLocationId().equals(other.getLocationId()))
            && (this.getLocationCode() == null ? other.getLocationCode() == null : this.getLocationCode().equals(other.getLocationCode()))
            && (this.getLpnId() == null ? other.getLpnId() == null : this.getLpnId().equals(other.getLpnId()))
            && (this.getLpnNumber() == null ? other.getLpnNumber() == null : this.getLpnNumber().equals(other.getLpnNumber()))
            && (this.getLotId() == null ? other.getLotId() == null : this.getLotId().equals(other.getLotId()))
            && (this.getLotNumber() == null ? other.getLotNumber() == null : this.getLotNumber().equals(other.getLotNumber()))
            && (this.getQuantitySystem() == null ? other.getQuantitySystem() == null : this.getQuantitySystem().equals(other.getQuantitySystem()))
            && (this.getQuantityCount() == null ? other.getQuantityCount() == null : this.getQuantityCount().equals(other.getQuantityCount()))
            && (this.getQuantityReplay() == null ? other.getQuantityReplay() == null : this.getQuantityReplay().equals(other.getQuantityReplay()))
            && (this.getQuantityDifference() == null ? other.getQuantityDifference() == null : this.getQuantityDifference().equals(other.getQuantityDifference()))
            && (this.getQuantityConfirm() == null ? other.getQuantityConfirm() == null : this.getQuantityConfirm().equals(other.getQuantityConfirm()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
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
        result = prime * result + ((getInventoryCountDetailId() == null) ? 0 : getInventoryCountDetailId().hashCode());
        result = prime * result + ((getInventoryCountHeaderId() == null) ? 0 : getInventoryCountHeaderId().hashCode());
        result = prime * result + ((getLineNumber() == null) ? 0 : getLineNumber().hashCode());
        result = prime * result + ((getSourceLineNumber() == null) ? 0 : getSourceLineNumber().hashCode());
        result = prime * result + ((getInventoryOnhandId() == null) ? 0 : getInventoryOnhandId().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getOwnerCode() == null) ? 0 : getOwnerCode().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getSkuCode() == null) ? 0 : getSkuCode().hashCode());
        result = prime * result + ((getSkuAlias() == null) ? 0 : getSkuAlias().hashCode());
        result = prime * result + ((getLocationId() == null) ? 0 : getLocationId().hashCode());
        result = prime * result + ((getLocationCode() == null) ? 0 : getLocationCode().hashCode());
        result = prime * result + ((getLpnId() == null) ? 0 : getLpnId().hashCode());
        result = prime * result + ((getLpnNumber() == null) ? 0 : getLpnNumber().hashCode());
        result = prime * result + ((getLotId() == null) ? 0 : getLotId().hashCode());
        result = prime * result + ((getLotNumber() == null) ? 0 : getLotNumber().hashCode());
        result = prime * result + ((getQuantitySystem() == null) ? 0 : getQuantitySystem().hashCode());
        result = prime * result + ((getQuantityCount() == null) ? 0 : getQuantityCount().hashCode());
        result = prime * result + ((getQuantityReplay() == null) ? 0 : getQuantityReplay().hashCode());
        result = prime * result + ((getQuantityDifference() == null) ? 0 : getQuantityDifference().hashCode());
        result = prime * result + ((getQuantityConfirm() == null) ? 0 : getQuantityConfirm().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
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
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static InventoryCountDetailTEntity.Builder builder() {
        return new InventoryCountDetailTEntity.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table INVENTORY_COUNT_DETAIL_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private InventoryCountDetailTEntity obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new InventoryCountDetailTEntity();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.INVENTORY_COUNT_DETAIL_ID
         *
         * @param inventoryCountDetailId the value for INVENTORY_COUNT_DETAIL_T.INVENTORY_COUNT_DETAIL_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder inventoryCountDetailId(Long inventoryCountDetailId) {
            obj.setInventoryCountDetailId(inventoryCountDetailId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.INVENTORY_COUNT_HEADER_ID
         *
         * @param inventoryCountHeaderId the value for INVENTORY_COUNT_DETAIL_T.INVENTORY_COUNT_HEADER_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder inventoryCountHeaderId(Long inventoryCountHeaderId) {
            obj.setInventoryCountHeaderId(inventoryCountHeaderId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.LINE_NUMBER
         *
         * @param lineNumber the value for INVENTORY_COUNT_DETAIL_T.LINE_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lineNumber(Long lineNumber) {
            obj.setLineNumber(lineNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.SOURCE_LINE_NUMBER
         *
         * @param sourceLineNumber the value for INVENTORY_COUNT_DETAIL_T.SOURCE_LINE_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder sourceLineNumber(Long sourceLineNumber) {
            obj.setSourceLineNumber(sourceLineNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.INVENTORY_ONHAND_ID
         *
         * @param inventoryOnhandId the value for INVENTORY_COUNT_DETAIL_T.INVENTORY_ONHAND_ID
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.OWNER_ID
         *
         * @param ownerId the value for INVENTORY_COUNT_DETAIL_T.OWNER_ID
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.OWNER_CODE
         *
         * @param ownerCode the value for INVENTORY_COUNT_DETAIL_T.OWNER_CODE
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.SKU_ID
         *
         * @param skuId the value for INVENTORY_COUNT_DETAIL_T.SKU_ID
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.SKU_CODE
         *
         * @param skuCode the value for INVENTORY_COUNT_DETAIL_T.SKU_CODE
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.SKU_ALIAS
         *
         * @param skuAlias the value for INVENTORY_COUNT_DETAIL_T.SKU_ALIAS
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.LOCATION_ID
         *
         * @param locationId the value for INVENTORY_COUNT_DETAIL_T.LOCATION_ID
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.LOCATION_CODE
         *
         * @param locationCode the value for INVENTORY_COUNT_DETAIL_T.LOCATION_CODE
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.LPN_ID
         *
         * @param lpnId the value for INVENTORY_COUNT_DETAIL_T.LPN_ID
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.LPN_NUMBER
         *
         * @param lpnNumber the value for INVENTORY_COUNT_DETAIL_T.LPN_NUMBER
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.LOT_ID
         *
         * @param lotId the value for INVENTORY_COUNT_DETAIL_T.LOT_ID
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.LOT_NUMBER
         *
         * @param lotNumber the value for INVENTORY_COUNT_DETAIL_T.LOT_NUMBER
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.QUANTITY_SYSTEM
         *
         * @param quantitySystem the value for INVENTORY_COUNT_DETAIL_T.QUANTITY_SYSTEM
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantitySystem(BigDecimal quantitySystem) {
            obj.setQuantitySystem(quantitySystem);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.QUANTITY_COUNT
         *
         * @param quantityCount the value for INVENTORY_COUNT_DETAIL_T.QUANTITY_COUNT
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantityCount(BigDecimal quantityCount) {
            obj.setQuantityCount(quantityCount);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.QUANTITY_REPLAY
         *
         * @param quantityReplay the value for INVENTORY_COUNT_DETAIL_T.QUANTITY_REPLAY
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantityReplay(BigDecimal quantityReplay) {
            obj.setQuantityReplay(quantityReplay);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.QUANTITY_DIFFERENCE
         *
         * @param quantityDifference the value for INVENTORY_COUNT_DETAIL_T.QUANTITY_DIFFERENCE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantityDifference(BigDecimal quantityDifference) {
            obj.setQuantityDifference(quantityDifference);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.QUANTITY_CONFIRM
         *
         * @param quantityConfirm the value for INVENTORY_COUNT_DETAIL_T.QUANTITY_CONFIRM
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantityConfirm(BigDecimal quantityConfirm) {
            obj.setQuantityConfirm(quantityConfirm);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.STATUS
         *
         * @param status the value for INVENTORY_COUNT_DETAIL_T.STATUS
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder status(String status) {
            obj.setStatus(status);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.REASON
         *
         * @param reason the value for INVENTORY_COUNT_DETAIL_T.REASON
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder reason(String reason) {
            obj.setReason(reason);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.REMARK
         *
         * @param remark the value for INVENTORY_COUNT_DETAIL_T.REMARK
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.COMPANY_ID
         *
         * @param companyId the value for INVENTORY_COUNT_DETAIL_T.COMPANY_ID
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.WAREHOUSE_ID
         *
         * @param warehouseId the value for INVENTORY_COUNT_DETAIL_T.WAREHOUSE_ID
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.DEL_FLAG
         *
         * @param delFlag the value for INVENTORY_COUNT_DETAIL_T.DEL_FLAG
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.CREATE_BY
         *
         * @param createBy the value for INVENTORY_COUNT_DETAIL_T.CREATE_BY
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.CREATE_TIME
         *
         * @param createTime the value for INVENTORY_COUNT_DETAIL_T.CREATE_TIME
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.UPDATE_BY
         *
         * @param updateBy the value for INVENTORY_COUNT_DETAIL_T.UPDATE_BY
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.UPDATE_TIME
         *
         * @param updateTime the value for INVENTORY_COUNT_DETAIL_T.UPDATE_TIME
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.UPDATE_VERSION
         *
         * @param updateVersion the value for INVENTORY_COUNT_DETAIL_T.UPDATE_VERSION
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
         * This method sets the value of the database column INVENTORY_COUNT_DETAIL_T.DESCRIPTION
         *
         * @param description the value for INVENTORY_COUNT_DETAIL_T.DESCRIPTION
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
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public InventoryCountDetailTEntity build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table INVENTORY_COUNT_DETAIL_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum DelFlag {
        NOT_DELETED(new String("N"), "未删除"),
        IS_DELETED(new String("Y"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
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
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
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
     * This enum corresponds to the database table INVENTORY_COUNT_DETAIL_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        inventoryCountDetailId("INVENTORY_COUNT_DETAIL_ID", "inventoryCountDetailId", "DECIMAL", false),
        inventoryCountHeaderId("INVENTORY_COUNT_HEADER_ID", "inventoryCountHeaderId", "DECIMAL", false),
        lineNumber("LINE_NUMBER", "lineNumber", "DECIMAL", false),
        sourceLineNumber("SOURCE_LINE_NUMBER", "sourceLineNumber", "DECIMAL", false),
        inventoryOnhandId("INVENTORY_ONHAND_ID", "inventoryOnhandId", "DECIMAL", false),
        ownerId("OWNER_ID", "ownerId", "DECIMAL", false),
        ownerCode("OWNER_CODE", "ownerCode", "VARCHAR", false),
        skuId("SKU_ID", "skuId", "DECIMAL", false),
        skuCode("SKU_CODE", "skuCode", "VARCHAR", false),
        skuAlias("SKU_ALIAS", "skuAlias", "VARCHAR", false),
        locationId("LOCATION_ID", "locationId", "DECIMAL", false),
        locationCode("LOCATION_CODE", "locationCode", "VARCHAR", false),
        lpnId("LPN_ID", "lpnId", "DECIMAL", false),
        lpnNumber("LPN_NUMBER", "lpnNumber", "VARCHAR", false),
        lotId("LOT_ID", "lotId", "DECIMAL", false),
        lotNumber("LOT_NUMBER", "lotNumber", "VARCHAR", false),
        quantitySystem("QUANTITY_SYSTEM", "quantitySystem", "DECIMAL", false),
        quantityCount("QUANTITY_COUNT", "quantityCount", "DECIMAL", false),
        quantityReplay("QUANTITY_REPLAY", "quantityReplay", "DECIMAL", false),
        quantityDifference("QUANTITY_DIFFERENCE", "quantityDifference", "DECIMAL", false),
        quantityConfirm("QUANTITY_CONFIRM", "quantityConfirm", "DECIMAL", false),
        status("STATUS", "status", "VARCHAR", true),
        reason("REASON", "reason", "VARCHAR", false),
        remark("REMARK", "remark", "VARCHAR", false),
        companyId("COMPANY_ID", "companyId", "DECIMAL", false),
        warehouseId("WAREHOUSE_ID", "warehouseId", "DECIMAL", false),
        delFlag("DEL_FLAG", "delFlag", "CHAR", false),
        createBy("CREATE_BY", "createBy", "VARCHAR", false),
        createTime("CREATE_TIME", "createTime", "TIMESTAMP", false),
        updateBy("UPDATE_BY", "updateBy", "VARCHAR", false),
        updateTime("UPDATE_TIME", "updateTime", "TIMESTAMP", false),
        updateVersion("UPDATE_VERSION", "updateVersion", "DECIMAL", false),
        description("DESCRIPTION", "description", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
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
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
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
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
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
         * This method corresponds to the database table INVENTORY_COUNT_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}