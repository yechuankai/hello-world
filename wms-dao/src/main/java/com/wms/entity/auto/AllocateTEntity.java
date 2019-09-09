package com.wms.entity.auto;

import com.wms.entity.BaseEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AllocateTEntity extends BaseEntity {
    private Long allocateId;

    private Long allocateBatchId;

    private Long sourceNumber;

    private Long sourceLineNumber;

    private String sourceBillNumber;

    private String sourceWaveNumber;

    private String allocateType;

    private String allocateStrategyType;

    private Long allocateStrategyId;

    private String allocateStrategyCode;

    private Long inventoryOnhandId;

    private Long ownerId;

    private String ownerCode;

    private Long skuId;

    private String skuCode;

    private String skuAlias;

    private BigDecimal quantityAllocated;

    private Long locationId;

    private String locationCode;

    private String zoneCode;

    private Long lpnId;

    private String lpnNumber;

    private Long lotId;

    private String lotNumber;

    private String loadLpnNumber;

    private String status;

    private String fullLpnFlag;

    private String fullLocationFlag;

    private String toLocationCode;

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

    private String fromLocationCode;

    private String fromLpnNumber;

    public Long getAllocateId() {
        return allocateId;
    }

    public void setAllocateId(Long allocateId) {
        this.allocateId = allocateId;
    }

    public Long getAllocateBatchId() {
        return allocateBatchId;
    }

    public void setAllocateBatchId(Long allocateBatchId) {
        this.allocateBatchId = allocateBatchId;
    }

    public Long getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(Long sourceNumber) {
        this.sourceNumber = sourceNumber;
    }

    public Long getSourceLineNumber() {
        return sourceLineNumber;
    }

    public void setSourceLineNumber(Long sourceLineNumber) {
        this.sourceLineNumber = sourceLineNumber;
    }

    public String getSourceBillNumber() {
        return sourceBillNumber;
    }

    public void setSourceBillNumber(String sourceBillNumber) {
        this.sourceBillNumber = sourceBillNumber == null ? null : sourceBillNumber.trim();
    }

    public String getSourceWaveNumber() {
        return sourceWaveNumber;
    }

    public void setSourceWaveNumber(String sourceWaveNumber) {
        this.sourceWaveNumber = sourceWaveNumber == null ? null : sourceWaveNumber.trim();
    }

    public String getAllocateType() {
        return allocateType;
    }

    public void setAllocateType(String allocateType) {
        this.allocateType = allocateType == null ? null : allocateType.trim();
    }

    public String getAllocateStrategyType() {
        return allocateStrategyType;
    }

    public void setAllocateStrategyType(String allocateStrategyType) {
        this.allocateStrategyType = allocateStrategyType == null ? null : allocateStrategyType.trim();
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

    public BigDecimal getQuantityAllocated() {
        return quantityAllocated;
    }

    public void setQuantityAllocated(BigDecimal quantityAllocated) {
        this.quantityAllocated = quantityAllocated;
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

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode == null ? null : zoneCode.trim();
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

    public String getLoadLpnNumber() {
        return loadLpnNumber;
    }

    public void setLoadLpnNumber(String loadLpnNumber) {
        this.loadLpnNumber = loadLpnNumber == null ? null : loadLpnNumber.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getFullLpnFlag() {
        return fullLpnFlag;
    }

    public void setFullLpnFlag(String fullLpnFlag) {
        this.fullLpnFlag = fullLpnFlag == null ? null : fullLpnFlag.trim();
    }

    public String getFullLocationFlag() {
        return fullLocationFlag;
    }

    public void setFullLocationFlag(String fullLocationFlag) {
        this.fullLocationFlag = fullLocationFlag == null ? null : fullLocationFlag.trim();
    }

    public String getToLocationCode() {
        return toLocationCode;
    }

    public void setToLocationCode(String toLocationCode) {
        this.toLocationCode = toLocationCode == null ? null : toLocationCode.trim();
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
     * This method corresponds to the database table ALLOCATE_T
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

    public String getFromLocationCode() {
        return fromLocationCode;
    }

    public void setFromLocationCode(String fromLocationCode) {
        this.fromLocationCode = fromLocationCode == null ? null : fromLocationCode.trim();
    }

    public String getFromLpnNumber() {
        return fromLpnNumber;
    }

    public void setFromLpnNumber(String fromLpnNumber) {
        this.fromLpnNumber = fromLpnNumber == null ? null : fromLpnNumber.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", allocateId=").append(allocateId);
        sb.append(", allocateBatchId=").append(allocateBatchId);
        sb.append(", sourceNumber=").append(sourceNumber);
        sb.append(", sourceLineNumber=").append(sourceLineNumber);
        sb.append(", sourceBillNumber=").append(sourceBillNumber);
        sb.append(", sourceWaveNumber=").append(sourceWaveNumber);
        sb.append(", allocateType=").append(allocateType);
        sb.append(", allocateStrategyType=").append(allocateStrategyType);
        sb.append(", allocateStrategyId=").append(allocateStrategyId);
        sb.append(", allocateStrategyCode=").append(allocateStrategyCode);
        sb.append(", inventoryOnhandId=").append(inventoryOnhandId);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", skuId=").append(skuId);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", skuAlias=").append(skuAlias);
        sb.append(", quantityAllocated=").append(quantityAllocated);
        sb.append(", locationId=").append(locationId);
        sb.append(", locationCode=").append(locationCode);
        sb.append(", zoneCode=").append(zoneCode);
        sb.append(", lpnId=").append(lpnId);
        sb.append(", lpnNumber=").append(lpnNumber);
        sb.append(", lotId=").append(lotId);
        sb.append(", lotNumber=").append(lotNumber);
        sb.append(", loadLpnNumber=").append(loadLpnNumber);
        sb.append(", status=").append(status);
        sb.append(", fullLpnFlag=").append(fullLpnFlag);
        sb.append(", fullLocationFlag=").append(fullLocationFlag);
        sb.append(", toLocationCode=").append(toLocationCode);
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
        sb.append(", fromLocationCode=").append(fromLocationCode);
        sb.append(", fromLpnNumber=").append(fromLpnNumber);
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
        AllocateTEntity other = (AllocateTEntity) that;
        return (this.getAllocateId() == null ? other.getAllocateId() == null : this.getAllocateId().equals(other.getAllocateId()))
            && (this.getAllocateBatchId() == null ? other.getAllocateBatchId() == null : this.getAllocateBatchId().equals(other.getAllocateBatchId()))
            && (this.getSourceNumber() == null ? other.getSourceNumber() == null : this.getSourceNumber().equals(other.getSourceNumber()))
            && (this.getSourceLineNumber() == null ? other.getSourceLineNumber() == null : this.getSourceLineNumber().equals(other.getSourceLineNumber()))
            && (this.getSourceBillNumber() == null ? other.getSourceBillNumber() == null : this.getSourceBillNumber().equals(other.getSourceBillNumber()))
            && (this.getSourceWaveNumber() == null ? other.getSourceWaveNumber() == null : this.getSourceWaveNumber().equals(other.getSourceWaveNumber()))
            && (this.getAllocateType() == null ? other.getAllocateType() == null : this.getAllocateType().equals(other.getAllocateType()))
            && (this.getAllocateStrategyType() == null ? other.getAllocateStrategyType() == null : this.getAllocateStrategyType().equals(other.getAllocateStrategyType()))
            && (this.getAllocateStrategyId() == null ? other.getAllocateStrategyId() == null : this.getAllocateStrategyId().equals(other.getAllocateStrategyId()))
            && (this.getAllocateStrategyCode() == null ? other.getAllocateStrategyCode() == null : this.getAllocateStrategyCode().equals(other.getAllocateStrategyCode()))
            && (this.getInventoryOnhandId() == null ? other.getInventoryOnhandId() == null : this.getInventoryOnhandId().equals(other.getInventoryOnhandId()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getOwnerCode() == null ? other.getOwnerCode() == null : this.getOwnerCode().equals(other.getOwnerCode()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getSkuCode() == null ? other.getSkuCode() == null : this.getSkuCode().equals(other.getSkuCode()))
            && (this.getSkuAlias() == null ? other.getSkuAlias() == null : this.getSkuAlias().equals(other.getSkuAlias()))
            && (this.getQuantityAllocated() == null ? other.getQuantityAllocated() == null : this.getQuantityAllocated().equals(other.getQuantityAllocated()))
            && (this.getLocationId() == null ? other.getLocationId() == null : this.getLocationId().equals(other.getLocationId()))
            && (this.getLocationCode() == null ? other.getLocationCode() == null : this.getLocationCode().equals(other.getLocationCode()))
            && (this.getZoneCode() == null ? other.getZoneCode() == null : this.getZoneCode().equals(other.getZoneCode()))
            && (this.getLpnId() == null ? other.getLpnId() == null : this.getLpnId().equals(other.getLpnId()))
            && (this.getLpnNumber() == null ? other.getLpnNumber() == null : this.getLpnNumber().equals(other.getLpnNumber()))
            && (this.getLotId() == null ? other.getLotId() == null : this.getLotId().equals(other.getLotId()))
            && (this.getLotNumber() == null ? other.getLotNumber() == null : this.getLotNumber().equals(other.getLotNumber()))
            && (this.getLoadLpnNumber() == null ? other.getLoadLpnNumber() == null : this.getLoadLpnNumber().equals(other.getLoadLpnNumber()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getFullLpnFlag() == null ? other.getFullLpnFlag() == null : this.getFullLpnFlag().equals(other.getFullLpnFlag()))
            && (this.getFullLocationFlag() == null ? other.getFullLocationFlag() == null : this.getFullLocationFlag().equals(other.getFullLocationFlag()))
            && (this.getToLocationCode() == null ? other.getToLocationCode() == null : this.getToLocationCode().equals(other.getToLocationCode()))
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
            && (this.getFromLocationCode() == null ? other.getFromLocationCode() == null : this.getFromLocationCode().equals(other.getFromLocationCode()))
            && (this.getFromLpnNumber() == null ? other.getFromLpnNumber() == null : this.getFromLpnNumber().equals(other.getFromLpnNumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAllocateId() == null) ? 0 : getAllocateId().hashCode());
        result = prime * result + ((getAllocateBatchId() == null) ? 0 : getAllocateBatchId().hashCode());
        result = prime * result + ((getSourceNumber() == null) ? 0 : getSourceNumber().hashCode());
        result = prime * result + ((getSourceLineNumber() == null) ? 0 : getSourceLineNumber().hashCode());
        result = prime * result + ((getSourceBillNumber() == null) ? 0 : getSourceBillNumber().hashCode());
        result = prime * result + ((getSourceWaveNumber() == null) ? 0 : getSourceWaveNumber().hashCode());
        result = prime * result + ((getAllocateType() == null) ? 0 : getAllocateType().hashCode());
        result = prime * result + ((getAllocateStrategyType() == null) ? 0 : getAllocateStrategyType().hashCode());
        result = prime * result + ((getAllocateStrategyId() == null) ? 0 : getAllocateStrategyId().hashCode());
        result = prime * result + ((getAllocateStrategyCode() == null) ? 0 : getAllocateStrategyCode().hashCode());
        result = prime * result + ((getInventoryOnhandId() == null) ? 0 : getInventoryOnhandId().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getOwnerCode() == null) ? 0 : getOwnerCode().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getSkuCode() == null) ? 0 : getSkuCode().hashCode());
        result = prime * result + ((getSkuAlias() == null) ? 0 : getSkuAlias().hashCode());
        result = prime * result + ((getQuantityAllocated() == null) ? 0 : getQuantityAllocated().hashCode());
        result = prime * result + ((getLocationId() == null) ? 0 : getLocationId().hashCode());
        result = prime * result + ((getLocationCode() == null) ? 0 : getLocationCode().hashCode());
        result = prime * result + ((getZoneCode() == null) ? 0 : getZoneCode().hashCode());
        result = prime * result + ((getLpnId() == null) ? 0 : getLpnId().hashCode());
        result = prime * result + ((getLpnNumber() == null) ? 0 : getLpnNumber().hashCode());
        result = prime * result + ((getLotId() == null) ? 0 : getLotId().hashCode());
        result = prime * result + ((getLotNumber() == null) ? 0 : getLotNumber().hashCode());
        result = prime * result + ((getLoadLpnNumber() == null) ? 0 : getLoadLpnNumber().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getFullLpnFlag() == null) ? 0 : getFullLpnFlag().hashCode());
        result = prime * result + ((getFullLocationFlag() == null) ? 0 : getFullLocationFlag().hashCode());
        result = prime * result + ((getToLocationCode() == null) ? 0 : getToLocationCode().hashCode());
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
        result = prime * result + ((getFromLocationCode() == null) ? 0 : getFromLocationCode().hashCode());
        result = prime * result + ((getFromLpnNumber() == null) ? 0 : getFromLpnNumber().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ALLOCATE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static AllocateTEntity.Builder builder() {
        return new AllocateTEntity.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ALLOCATE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private AllocateTEntity obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new AllocateTEntity();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.ALLOCATE_ID
         *
         * @param allocateId the value for ALLOCATE_T.ALLOCATE_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder allocateId(Long allocateId) {
            obj.setAllocateId(allocateId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.ALLOCATE_BATCH_ID
         *
         * @param allocateBatchId the value for ALLOCATE_T.ALLOCATE_BATCH_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder allocateBatchId(Long allocateBatchId) {
            obj.setAllocateBatchId(allocateBatchId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.SOURCE_NUMBER
         *
         * @param sourceNumber the value for ALLOCATE_T.SOURCE_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder sourceNumber(Long sourceNumber) {
            obj.setSourceNumber(sourceNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.SOURCE_LINE_NUMBER
         *
         * @param sourceLineNumber the value for ALLOCATE_T.SOURCE_LINE_NUMBER
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
         * This method sets the value of the database column ALLOCATE_T.SOURCE_BILL_NUMBER
         *
         * @param sourceBillNumber the value for ALLOCATE_T.SOURCE_BILL_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder sourceBillNumber(String sourceBillNumber) {
            obj.setSourceBillNumber(sourceBillNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.SOURCE_WAVE_NUMBER
         *
         * @param sourceWaveNumber the value for ALLOCATE_T.SOURCE_WAVE_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder sourceWaveNumber(String sourceWaveNumber) {
            obj.setSourceWaveNumber(sourceWaveNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.ALLOCATE_TYPE
         *
         * @param allocateType the value for ALLOCATE_T.ALLOCATE_TYPE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder allocateType(String allocateType) {
            obj.setAllocateType(allocateType);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.ALLOCATE_STRATEGY_TYPE
         *
         * @param allocateStrategyType the value for ALLOCATE_T.ALLOCATE_STRATEGY_TYPE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder allocateStrategyType(String allocateStrategyType) {
            obj.setAllocateStrategyType(allocateStrategyType);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.ALLOCATE_STRATEGY_ID
         *
         * @param allocateStrategyId the value for ALLOCATE_T.ALLOCATE_STRATEGY_ID
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
         * This method sets the value of the database column ALLOCATE_T.ALLOCATE_STRATEGY_CODE
         *
         * @param allocateStrategyCode the value for ALLOCATE_T.ALLOCATE_STRATEGY_CODE
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
         * This method sets the value of the database column ALLOCATE_T.INVENTORY_ONHAND_ID
         *
         * @param inventoryOnhandId the value for ALLOCATE_T.INVENTORY_ONHAND_ID
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
         * This method sets the value of the database column ALLOCATE_T.OWNER_ID
         *
         * @param ownerId the value for ALLOCATE_T.OWNER_ID
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
         * This method sets the value of the database column ALLOCATE_T.OWNER_CODE
         *
         * @param ownerCode the value for ALLOCATE_T.OWNER_CODE
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
         * This method sets the value of the database column ALLOCATE_T.SKU_ID
         *
         * @param skuId the value for ALLOCATE_T.SKU_ID
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
         * This method sets the value of the database column ALLOCATE_T.SKU_CODE
         *
         * @param skuCode the value for ALLOCATE_T.SKU_CODE
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
         * This method sets the value of the database column ALLOCATE_T.SKU_ALIAS
         *
         * @param skuAlias the value for ALLOCATE_T.SKU_ALIAS
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
         * This method sets the value of the database column ALLOCATE_T.QUANTITY_ALLOCATED
         *
         * @param quantityAllocated the value for ALLOCATE_T.QUANTITY_ALLOCATED
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
         * This method sets the value of the database column ALLOCATE_T.LOCATION_ID
         *
         * @param locationId the value for ALLOCATE_T.LOCATION_ID
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
         * This method sets the value of the database column ALLOCATE_T.LOCATION_CODE
         *
         * @param locationCode the value for ALLOCATE_T.LOCATION_CODE
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
         * This method sets the value of the database column ALLOCATE_T.ZONE_CODE
         *
         * @param zoneCode the value for ALLOCATE_T.ZONE_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder zoneCode(String zoneCode) {
            obj.setZoneCode(zoneCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.LPN_ID
         *
         * @param lpnId the value for ALLOCATE_T.LPN_ID
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
         * This method sets the value of the database column ALLOCATE_T.LPN_NUMBER
         *
         * @param lpnNumber the value for ALLOCATE_T.LPN_NUMBER
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
         * This method sets the value of the database column ALLOCATE_T.LOT_ID
         *
         * @param lotId the value for ALLOCATE_T.LOT_ID
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
         * This method sets the value of the database column ALLOCATE_T.LOT_NUMBER
         *
         * @param lotNumber the value for ALLOCATE_T.LOT_NUMBER
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
         * This method sets the value of the database column ALLOCATE_T.LOAD_LPN_NUMBER
         *
         * @param loadLpnNumber the value for ALLOCATE_T.LOAD_LPN_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder loadLpnNumber(String loadLpnNumber) {
            obj.setLoadLpnNumber(loadLpnNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.STATUS
         *
         * @param status the value for ALLOCATE_T.STATUS
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
         * This method sets the value of the database column ALLOCATE_T.FULL_LPN_FLAG
         *
         * @param fullLpnFlag the value for ALLOCATE_T.FULL_LPN_FLAG
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fullLpnFlag(String fullLpnFlag) {
            obj.setFullLpnFlag(fullLpnFlag);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.FULL_LOCATION_FLAG
         *
         * @param fullLocationFlag the value for ALLOCATE_T.FULL_LOCATION_FLAG
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fullLocationFlag(String fullLocationFlag) {
            obj.setFullLocationFlag(fullLocationFlag);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.TO_LOCATION_CODE
         *
         * @param toLocationCode the value for ALLOCATE_T.TO_LOCATION_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder toLocationCode(String toLocationCode) {
            obj.setToLocationCode(toLocationCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.REMARK
         *
         * @param remark the value for ALLOCATE_T.REMARK
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
         * This method sets the value of the database column ALLOCATE_T.COMPANY_ID
         *
         * @param companyId the value for ALLOCATE_T.COMPANY_ID
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
         * This method sets the value of the database column ALLOCATE_T.WAREHOUSE_ID
         *
         * @param warehouseId the value for ALLOCATE_T.WAREHOUSE_ID
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
         * This method sets the value of the database column ALLOCATE_T.DEL_FLAG
         *
         * @param delFlag the value for ALLOCATE_T.DEL_FLAG
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
         * This method sets the value of the database column ALLOCATE_T.CREATE_BY
         *
         * @param createBy the value for ALLOCATE_T.CREATE_BY
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
         * This method sets the value of the database column ALLOCATE_T.CREATE_TIME
         *
         * @param createTime the value for ALLOCATE_T.CREATE_TIME
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
         * This method sets the value of the database column ALLOCATE_T.UPDATE_BY
         *
         * @param updateBy the value for ALLOCATE_T.UPDATE_BY
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
         * This method sets the value of the database column ALLOCATE_T.UPDATE_TIME
         *
         * @param updateTime the value for ALLOCATE_T.UPDATE_TIME
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
         * This method sets the value of the database column ALLOCATE_T.UPDATE_VERSION
         *
         * @param updateVersion the value for ALLOCATE_T.UPDATE_VERSION
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
         * This method sets the value of the database column ALLOCATE_T.DESCRIPTION
         *
         * @param description the value for ALLOCATE_T.DESCRIPTION
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
         * This method sets the value of the database column ALLOCATE_T.FROM_LOCATION_CODE
         *
         * @param fromLocationCode the value for ALLOCATE_T.FROM_LOCATION_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fromLocationCode(String fromLocationCode) {
            obj.setFromLocationCode(fromLocationCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_T.FROM_LPN_NUMBER
         *
         * @param fromLpnNumber the value for ALLOCATE_T.FROM_LPN_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fromLpnNumber(String fromLpnNumber) {
            obj.setFromLpnNumber(fromLpnNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public AllocateTEntity build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table ALLOCATE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum DelFlag {
        NOT_DELETED(new String("N"), "未删除"),
        IS_DELETED(new String("Y"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_T
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
         * This method corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_T
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
     * This enum corresponds to the database table ALLOCATE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        allocateId("ALLOCATE_ID", "allocateId", "DECIMAL", false),
        allocateBatchId("ALLOCATE_BATCH_ID", "allocateBatchId", "DECIMAL", false),
        sourceNumber("SOURCE_NUMBER", "sourceNumber", "DECIMAL", false),
        sourceLineNumber("SOURCE_LINE_NUMBER", "sourceLineNumber", "DECIMAL", false),
        sourceBillNumber("SOURCE_BILL_NUMBER", "sourceBillNumber", "VARCHAR", false),
        sourceWaveNumber("SOURCE_WAVE_NUMBER", "sourceWaveNumber", "VARCHAR", false),
        allocateType("ALLOCATE_TYPE", "allocateType", "VARCHAR", false),
        allocateStrategyType("ALLOCATE_STRATEGY_TYPE", "allocateStrategyType", "VARCHAR", false),
        allocateStrategyId("ALLOCATE_STRATEGY_ID", "allocateStrategyId", "DECIMAL", false),
        allocateStrategyCode("ALLOCATE_STRATEGY_CODE", "allocateStrategyCode", "VARCHAR", false),
        inventoryOnhandId("INVENTORY_ONHAND_ID", "inventoryOnhandId", "DECIMAL", false),
        ownerId("OWNER_ID", "ownerId", "DECIMAL", false),
        ownerCode("OWNER_CODE", "ownerCode", "VARCHAR", false),
        skuId("SKU_ID", "skuId", "DECIMAL", false),
        skuCode("SKU_CODE", "skuCode", "VARCHAR", false),
        skuAlias("SKU_ALIAS", "skuAlias", "VARCHAR", false),
        quantityAllocated("QUANTITY_ALLOCATED", "quantityAllocated", "DECIMAL", false),
        locationId("LOCATION_ID", "locationId", "DECIMAL", false),
        locationCode("LOCATION_CODE", "locationCode", "VARCHAR", false),
        zoneCode("ZONE_CODE", "zoneCode", "VARCHAR", false),
        lpnId("LPN_ID", "lpnId", "DECIMAL", false),
        lpnNumber("LPN_NUMBER", "lpnNumber", "VARCHAR", false),
        lotId("LOT_ID", "lotId", "DECIMAL", false),
        lotNumber("LOT_NUMBER", "lotNumber", "VARCHAR", false),
        loadLpnNumber("LOAD_LPN_NUMBER", "loadLpnNumber", "VARCHAR", false),
        status("STATUS", "status", "VARCHAR", true),
        fullLpnFlag("FULL_LPN_FLAG", "fullLpnFlag", "CHAR", false),
        fullLocationFlag("FULL_LOCATION_FLAG", "fullLocationFlag", "CHAR", false),
        toLocationCode("TO_LOCATION_CODE", "toLocationCode", "VARCHAR", false),
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
        fromLocationCode("FROM_LOCATION_CODE", "fromLocationCode", "VARCHAR", false),
        fromLpnNumber("FROM_LPN_NUMBER", "fromLpnNumber", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_T
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
         * This method corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_T
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
         * This method corresponds to the database table ALLOCATE_T
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
         * This method corresponds to the database table ALLOCATE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}