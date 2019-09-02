package com.wms.entity.auto;

import com.wms.entity.BaseEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AllocateShortTEntity extends BaseEntity {
    private Long allocateShortId;

    private Long sourceNumber;

    private Long sourceLineNumber;

    private String sourceBillNumber;

    private String reason;

    private String fileId;

    private BigDecimal quantityOriginal;

    private BigDecimal quantityActual;

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

    public Long getAllocateShortId() {
        return allocateShortId;
    }

    public void setAllocateShortId(Long allocateShortId) {
        this.allocateShortId = allocateShortId;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public BigDecimal getQuantityOriginal() {
        return quantityOriginal;
    }

    public void setQuantityOriginal(BigDecimal quantityOriginal) {
        this.quantityOriginal = quantityOriginal;
    }

    public BigDecimal getQuantityActual() {
        return quantityActual;
    }

    public void setQuantityActual(BigDecimal quantityActual) {
        this.quantityActual = quantityActual;
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
     * This method corresponds to the database table ALLOCATE_SHORT_T
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
        sb.append(", allocateShortId=").append(allocateShortId);
        sb.append(", sourceNumber=").append(sourceNumber);
        sb.append(", sourceLineNumber=").append(sourceLineNumber);
        sb.append(", sourceBillNumber=").append(sourceBillNumber);
        sb.append(", reason=").append(reason);
        sb.append(", fileId=").append(fileId);
        sb.append(", quantityOriginal=").append(quantityOriginal);
        sb.append(", quantityActual=").append(quantityActual);
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
        AllocateShortTEntity other = (AllocateShortTEntity) that;
        return (this.getAllocateShortId() == null ? other.getAllocateShortId() == null : this.getAllocateShortId().equals(other.getAllocateShortId()))
            && (this.getSourceNumber() == null ? other.getSourceNumber() == null : this.getSourceNumber().equals(other.getSourceNumber()))
            && (this.getSourceLineNumber() == null ? other.getSourceLineNumber() == null : this.getSourceLineNumber().equals(other.getSourceLineNumber()))
            && (this.getSourceBillNumber() == null ? other.getSourceBillNumber() == null : this.getSourceBillNumber().equals(other.getSourceBillNumber()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()))
            && (this.getQuantityOriginal() == null ? other.getQuantityOriginal() == null : this.getQuantityOriginal().equals(other.getQuantityOriginal()))
            && (this.getQuantityActual() == null ? other.getQuantityActual() == null : this.getQuantityActual().equals(other.getQuantityActual()))
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
        result = prime * result + ((getAllocateShortId() == null) ? 0 : getAllocateShortId().hashCode());
        result = prime * result + ((getSourceNumber() == null) ? 0 : getSourceNumber().hashCode());
        result = prime * result + ((getSourceLineNumber() == null) ? 0 : getSourceLineNumber().hashCode());
        result = prime * result + ((getSourceBillNumber() == null) ? 0 : getSourceBillNumber().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
        result = prime * result + ((getQuantityOriginal() == null) ? 0 : getQuantityOriginal().hashCode());
        result = prime * result + ((getQuantityActual() == null) ? 0 : getQuantityActual().hashCode());
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
     * This method corresponds to the database table ALLOCATE_SHORT_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static AllocateShortTEntity.Builder builder() {
        return new AllocateShortTEntity.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ALLOCATE_SHORT_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private AllocateShortTEntity obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new AllocateShortTEntity();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_SHORT_T.ALLOCATE_SHORT_ID
         *
         * @param allocateShortId the value for ALLOCATE_SHORT_T.ALLOCATE_SHORT_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder allocateShortId(Long allocateShortId) {
            obj.setAllocateShortId(allocateShortId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_SHORT_T.SOURCE_NUMBER
         *
         * @param sourceNumber the value for ALLOCATE_SHORT_T.SOURCE_NUMBER
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.SOURCE_LINE_NUMBER
         *
         * @param sourceLineNumber the value for ALLOCATE_SHORT_T.SOURCE_LINE_NUMBER
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.SOURCE_BILL_NUMBER
         *
         * @param sourceBillNumber the value for ALLOCATE_SHORT_T.SOURCE_BILL_NUMBER
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.REASON
         *
         * @param reason the value for ALLOCATE_SHORT_T.REASON
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.FILE_ID
         *
         * @param fileId the value for ALLOCATE_SHORT_T.FILE_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fileId(String fileId) {
            obj.setFileId(fileId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_SHORT_T.QUANTITY_ORIGINAL
         *
         * @param quantityOriginal the value for ALLOCATE_SHORT_T.QUANTITY_ORIGINAL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantityOriginal(BigDecimal quantityOriginal) {
            obj.setQuantityOriginal(quantityOriginal);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_SHORT_T.QUANTITY_ACTUAL
         *
         * @param quantityActual the value for ALLOCATE_SHORT_T.QUANTITY_ACTUAL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantityActual(BigDecimal quantityActual) {
            obj.setQuantityActual(quantityActual);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column ALLOCATE_SHORT_T.OWNER_ID
         *
         * @param ownerId the value for ALLOCATE_SHORT_T.OWNER_ID
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.OWNER_CODE
         *
         * @param ownerCode the value for ALLOCATE_SHORT_T.OWNER_CODE
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.SKU_ID
         *
         * @param skuId the value for ALLOCATE_SHORT_T.SKU_ID
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.SKU_CODE
         *
         * @param skuCode the value for ALLOCATE_SHORT_T.SKU_CODE
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.SKU_ALIAS
         *
         * @param skuAlias the value for ALLOCATE_SHORT_T.SKU_ALIAS
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.LOCATION_ID
         *
         * @param locationId the value for ALLOCATE_SHORT_T.LOCATION_ID
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.LOCATION_CODE
         *
         * @param locationCode the value for ALLOCATE_SHORT_T.LOCATION_CODE
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.LPN_ID
         *
         * @param lpnId the value for ALLOCATE_SHORT_T.LPN_ID
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.LPN_NUMBER
         *
         * @param lpnNumber the value for ALLOCATE_SHORT_T.LPN_NUMBER
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.LOT_ID
         *
         * @param lotId the value for ALLOCATE_SHORT_T.LOT_ID
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.LOT_NUMBER
         *
         * @param lotNumber the value for ALLOCATE_SHORT_T.LOT_NUMBER
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.REMARK
         *
         * @param remark the value for ALLOCATE_SHORT_T.REMARK
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.COMPANY_ID
         *
         * @param companyId the value for ALLOCATE_SHORT_T.COMPANY_ID
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.WAREHOUSE_ID
         *
         * @param warehouseId the value for ALLOCATE_SHORT_T.WAREHOUSE_ID
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.DEL_FLAG
         *
         * @param delFlag the value for ALLOCATE_SHORT_T.DEL_FLAG
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.CREATE_BY
         *
         * @param createBy the value for ALLOCATE_SHORT_T.CREATE_BY
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.CREATE_TIME
         *
         * @param createTime the value for ALLOCATE_SHORT_T.CREATE_TIME
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.UPDATE_BY
         *
         * @param updateBy the value for ALLOCATE_SHORT_T.UPDATE_BY
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.UPDATE_TIME
         *
         * @param updateTime the value for ALLOCATE_SHORT_T.UPDATE_TIME
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.UPDATE_VERSION
         *
         * @param updateVersion the value for ALLOCATE_SHORT_T.UPDATE_VERSION
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
         * This method sets the value of the database column ALLOCATE_SHORT_T.DESCRIPTION
         *
         * @param description the value for ALLOCATE_SHORT_T.DESCRIPTION
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
         * This method corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public AllocateShortTEntity build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table ALLOCATE_SHORT_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum DelFlag {
        NOT_DELETED(new String("N"), "未删除"),
        IS_DELETED(new String("Y"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_SHORT_T
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
         * This method corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_SHORT_T
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
     * This enum corresponds to the database table ALLOCATE_SHORT_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        allocateShortId("ALLOCATE_SHORT_ID", "allocateShortId", "DECIMAL", false),
        sourceNumber("SOURCE_NUMBER", "sourceNumber", "DECIMAL", false),
        sourceLineNumber("SOURCE_LINE_NUMBER", "sourceLineNumber", "DECIMAL", false),
        sourceBillNumber("SOURCE_BILL_NUMBER", "sourceBillNumber", "VARCHAR", false),
        reason("REASON", "reason", "VARCHAR", false),
        fileId("FILE_ID", "fileId", "VARCHAR", false),
        quantityOriginal("QUANTITY_ORIGINAL", "quantityOriginal", "DECIMAL", false),
        quantityActual("QUANTITY_ACTUAL", "quantityActual", "DECIMAL", false),
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
         * This field corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_SHORT_T
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
         * This method corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table ALLOCATE_SHORT_T
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
         * This method corresponds to the database table ALLOCATE_SHORT_T
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
         * This method corresponds to the database table ALLOCATE_SHORT_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}