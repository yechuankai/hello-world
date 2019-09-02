package com.wms.entity.auto;

import com.wms.entity.BaseEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class InventoryTransactionTEntity extends BaseEntity {
    private Long transactionId;

    private String transactionType;

    private String transactionCategory;

    private Date transactionDate;

    private Long inventoryOnhandId;

    private String ownerCode;

    private String skuCode;

    private String skuAlias;

    private String packCode;

    private String uom;

    private BigDecimal uomQuantity;

    private BigDecimal quantity;

    private String locationCode;

    private String lpnNumber;

    private String lotNumber;

    private String sourceNumber;

    private String sourceLineNumber;

    private String sourceBillNumber;

    private Long fromInventoryOnhandId;

    private String fromOwnerCode;

    private String fromSkuCode;

    private String fromSkuAlias;

    private String fromLocationCode;

    private String fromLpnNumber;

    private String fromLotNumber;

    private Long companyId;

    private Long warehouseId;

    private String delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Long updateVersion;

    private String description;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType == null ? null : transactionType.trim();
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory == null ? null : transactionCategory.trim();
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getInventoryOnhandId() {
        return inventoryOnhandId;
    }

    public void setInventoryOnhandId(Long inventoryOnhandId) {
        this.inventoryOnhandId = inventoryOnhandId;
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

    public BigDecimal getUomQuantity() {
        return uomQuantity;
    }

    public void setUomQuantity(BigDecimal uomQuantity) {
        this.uomQuantity = uomQuantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode == null ? null : locationCode.trim();
    }

    public String getLpnNumber() {
        return lpnNumber;
    }

    public void setLpnNumber(String lpnNumber) {
        this.lpnNumber = lpnNumber == null ? null : lpnNumber.trim();
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber == null ? null : lotNumber.trim();
    }

    public String getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(String sourceNumber) {
        this.sourceNumber = sourceNumber == null ? null : sourceNumber.trim();
    }

    public String getSourceLineNumber() {
        return sourceLineNumber;
    }

    public void setSourceLineNumber(String sourceLineNumber) {
        this.sourceLineNumber = sourceLineNumber == null ? null : sourceLineNumber.trim();
    }

    public String getSourceBillNumber() {
        return sourceBillNumber;
    }

    public void setSourceBillNumber(String sourceBillNumber) {
        this.sourceBillNumber = sourceBillNumber == null ? null : sourceBillNumber.trim();
    }

    public Long getFromInventoryOnhandId() {
        return fromInventoryOnhandId;
    }

    public void setFromInventoryOnhandId(Long fromInventoryOnhandId) {
        this.fromInventoryOnhandId = fromInventoryOnhandId;
    }

    public String getFromOwnerCode() {
        return fromOwnerCode;
    }

    public void setFromOwnerCode(String fromOwnerCode) {
        this.fromOwnerCode = fromOwnerCode == null ? null : fromOwnerCode.trim();
    }

    public String getFromSkuCode() {
        return fromSkuCode;
    }

    public void setFromSkuCode(String fromSkuCode) {
        this.fromSkuCode = fromSkuCode == null ? null : fromSkuCode.trim();
    }

    public String getFromSkuAlias() {
        return fromSkuAlias;
    }

    public void setFromSkuAlias(String fromSkuAlias) {
        this.fromSkuAlias = fromSkuAlias == null ? null : fromSkuAlias.trim();
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

    public String getFromLotNumber() {
        return fromLotNumber;
    }

    public void setFromLotNumber(String fromLotNumber) {
        this.fromLotNumber = fromLotNumber == null ? null : fromLotNumber.trim();
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
     * This method corresponds to the database table INVENTORY_TRANSACTION_T
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
        sb.append(", transactionId=").append(transactionId);
        sb.append(", transactionType=").append(transactionType);
        sb.append(", transactionCategory=").append(transactionCategory);
        sb.append(", transactionDate=").append(transactionDate);
        sb.append(", inventoryOnhandId=").append(inventoryOnhandId);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", skuAlias=").append(skuAlias);
        sb.append(", packCode=").append(packCode);
        sb.append(", uom=").append(uom);
        sb.append(", uomQuantity=").append(uomQuantity);
        sb.append(", quantity=").append(quantity);
        sb.append(", locationCode=").append(locationCode);
        sb.append(", lpnNumber=").append(lpnNumber);
        sb.append(", lotNumber=").append(lotNumber);
        sb.append(", sourceNumber=").append(sourceNumber);
        sb.append(", sourceLineNumber=").append(sourceLineNumber);
        sb.append(", sourceBillNumber=").append(sourceBillNumber);
        sb.append(", fromInventoryOnhandId=").append(fromInventoryOnhandId);
        sb.append(", fromOwnerCode=").append(fromOwnerCode);
        sb.append(", fromSkuCode=").append(fromSkuCode);
        sb.append(", fromSkuAlias=").append(fromSkuAlias);
        sb.append(", fromLocationCode=").append(fromLocationCode);
        sb.append(", fromLpnNumber=").append(fromLpnNumber);
        sb.append(", fromLotNumber=").append(fromLotNumber);
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
        InventoryTransactionTEntity other = (InventoryTransactionTEntity) that;
        return (this.getTransactionId() == null ? other.getTransactionId() == null : this.getTransactionId().equals(other.getTransactionId()))
            && (this.getTransactionType() == null ? other.getTransactionType() == null : this.getTransactionType().equals(other.getTransactionType()))
            && (this.getTransactionCategory() == null ? other.getTransactionCategory() == null : this.getTransactionCategory().equals(other.getTransactionCategory()))
            && (this.getTransactionDate() == null ? other.getTransactionDate() == null : this.getTransactionDate().equals(other.getTransactionDate()))
            && (this.getInventoryOnhandId() == null ? other.getInventoryOnhandId() == null : this.getInventoryOnhandId().equals(other.getInventoryOnhandId()))
            && (this.getOwnerCode() == null ? other.getOwnerCode() == null : this.getOwnerCode().equals(other.getOwnerCode()))
            && (this.getSkuCode() == null ? other.getSkuCode() == null : this.getSkuCode().equals(other.getSkuCode()))
            && (this.getSkuAlias() == null ? other.getSkuAlias() == null : this.getSkuAlias().equals(other.getSkuAlias()))
            && (this.getPackCode() == null ? other.getPackCode() == null : this.getPackCode().equals(other.getPackCode()))
            && (this.getUom() == null ? other.getUom() == null : this.getUom().equals(other.getUom()))
            && (this.getUomQuantity() == null ? other.getUomQuantity() == null : this.getUomQuantity().equals(other.getUomQuantity()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getLocationCode() == null ? other.getLocationCode() == null : this.getLocationCode().equals(other.getLocationCode()))
            && (this.getLpnNumber() == null ? other.getLpnNumber() == null : this.getLpnNumber().equals(other.getLpnNumber()))
            && (this.getLotNumber() == null ? other.getLotNumber() == null : this.getLotNumber().equals(other.getLotNumber()))
            && (this.getSourceNumber() == null ? other.getSourceNumber() == null : this.getSourceNumber().equals(other.getSourceNumber()))
            && (this.getSourceLineNumber() == null ? other.getSourceLineNumber() == null : this.getSourceLineNumber().equals(other.getSourceLineNumber()))
            && (this.getSourceBillNumber() == null ? other.getSourceBillNumber() == null : this.getSourceBillNumber().equals(other.getSourceBillNumber()))
            && (this.getFromInventoryOnhandId() == null ? other.getFromInventoryOnhandId() == null : this.getFromInventoryOnhandId().equals(other.getFromInventoryOnhandId()))
            && (this.getFromOwnerCode() == null ? other.getFromOwnerCode() == null : this.getFromOwnerCode().equals(other.getFromOwnerCode()))
            && (this.getFromSkuCode() == null ? other.getFromSkuCode() == null : this.getFromSkuCode().equals(other.getFromSkuCode()))
            && (this.getFromSkuAlias() == null ? other.getFromSkuAlias() == null : this.getFromSkuAlias().equals(other.getFromSkuAlias()))
            && (this.getFromLocationCode() == null ? other.getFromLocationCode() == null : this.getFromLocationCode().equals(other.getFromLocationCode()))
            && (this.getFromLpnNumber() == null ? other.getFromLpnNumber() == null : this.getFromLpnNumber().equals(other.getFromLpnNumber()))
            && (this.getFromLotNumber() == null ? other.getFromLotNumber() == null : this.getFromLotNumber().equals(other.getFromLotNumber()))
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
        result = prime * result + ((getTransactionId() == null) ? 0 : getTransactionId().hashCode());
        result = prime * result + ((getTransactionType() == null) ? 0 : getTransactionType().hashCode());
        result = prime * result + ((getTransactionCategory() == null) ? 0 : getTransactionCategory().hashCode());
        result = prime * result + ((getTransactionDate() == null) ? 0 : getTransactionDate().hashCode());
        result = prime * result + ((getInventoryOnhandId() == null) ? 0 : getInventoryOnhandId().hashCode());
        result = prime * result + ((getOwnerCode() == null) ? 0 : getOwnerCode().hashCode());
        result = prime * result + ((getSkuCode() == null) ? 0 : getSkuCode().hashCode());
        result = prime * result + ((getSkuAlias() == null) ? 0 : getSkuAlias().hashCode());
        result = prime * result + ((getPackCode() == null) ? 0 : getPackCode().hashCode());
        result = prime * result + ((getUom() == null) ? 0 : getUom().hashCode());
        result = prime * result + ((getUomQuantity() == null) ? 0 : getUomQuantity().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getLocationCode() == null) ? 0 : getLocationCode().hashCode());
        result = prime * result + ((getLpnNumber() == null) ? 0 : getLpnNumber().hashCode());
        result = prime * result + ((getLotNumber() == null) ? 0 : getLotNumber().hashCode());
        result = prime * result + ((getSourceNumber() == null) ? 0 : getSourceNumber().hashCode());
        result = prime * result + ((getSourceLineNumber() == null) ? 0 : getSourceLineNumber().hashCode());
        result = prime * result + ((getSourceBillNumber() == null) ? 0 : getSourceBillNumber().hashCode());
        result = prime * result + ((getFromInventoryOnhandId() == null) ? 0 : getFromInventoryOnhandId().hashCode());
        result = prime * result + ((getFromOwnerCode() == null) ? 0 : getFromOwnerCode().hashCode());
        result = prime * result + ((getFromSkuCode() == null) ? 0 : getFromSkuCode().hashCode());
        result = prime * result + ((getFromSkuAlias() == null) ? 0 : getFromSkuAlias().hashCode());
        result = prime * result + ((getFromLocationCode() == null) ? 0 : getFromLocationCode().hashCode());
        result = prime * result + ((getFromLpnNumber() == null) ? 0 : getFromLpnNumber().hashCode());
        result = prime * result + ((getFromLotNumber() == null) ? 0 : getFromLotNumber().hashCode());
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
     * This method corresponds to the database table INVENTORY_TRANSACTION_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static InventoryTransactionTEntity.Builder builder() {
        return new InventoryTransactionTEntity.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table INVENTORY_TRANSACTION_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private InventoryTransactionTEntity obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new InventoryTransactionTEntity();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.TRANSACTION_ID
         *
         * @param transactionId the value for INVENTORY_TRANSACTION_T.TRANSACTION_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder transactionId(Long transactionId) {
            obj.setTransactionId(transactionId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.TRANSACTION_TYPE
         *
         * @param transactionType the value for INVENTORY_TRANSACTION_T.TRANSACTION_TYPE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder transactionType(String transactionType) {
            obj.setTransactionType(transactionType);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.TRANSACTION_CATEGORY
         *
         * @param transactionCategory the value for INVENTORY_TRANSACTION_T.TRANSACTION_CATEGORY
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder transactionCategory(String transactionCategory) {
            obj.setTransactionCategory(transactionCategory);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.TRANSACTION_DATE
         *
         * @param transactionDate the value for INVENTORY_TRANSACTION_T.TRANSACTION_DATE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder transactionDate(Date transactionDate) {
            obj.setTransactionDate(transactionDate);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.INVENTORY_ONHAND_ID
         *
         * @param inventoryOnhandId the value for INVENTORY_TRANSACTION_T.INVENTORY_ONHAND_ID
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.OWNER_CODE
         *
         * @param ownerCode the value for INVENTORY_TRANSACTION_T.OWNER_CODE
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.SKU_CODE
         *
         * @param skuCode the value for INVENTORY_TRANSACTION_T.SKU_CODE
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.SKU_ALIAS
         *
         * @param skuAlias the value for INVENTORY_TRANSACTION_T.SKU_ALIAS
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.PACK_CODE
         *
         * @param packCode the value for INVENTORY_TRANSACTION_T.PACK_CODE
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.UOM
         *
         * @param uom the value for INVENTORY_TRANSACTION_T.UOM
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.UOM_QUANTITY
         *
         * @param uomQuantity the value for INVENTORY_TRANSACTION_T.UOM_QUANTITY
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder uomQuantity(BigDecimal uomQuantity) {
            obj.setUomQuantity(uomQuantity);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.QUANTITY
         *
         * @param quantity the value for INVENTORY_TRANSACTION_T.QUANTITY
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantity(BigDecimal quantity) {
            obj.setQuantity(quantity);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.LOCATION_CODE
         *
         * @param locationCode the value for INVENTORY_TRANSACTION_T.LOCATION_CODE
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.LPN_NUMBER
         *
         * @param lpnNumber the value for INVENTORY_TRANSACTION_T.LPN_NUMBER
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.LOT_NUMBER
         *
         * @param lotNumber the value for INVENTORY_TRANSACTION_T.LOT_NUMBER
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.SOURCE_NUMBER
         *
         * @param sourceNumber the value for INVENTORY_TRANSACTION_T.SOURCE_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder sourceNumber(String sourceNumber) {
            obj.setSourceNumber(sourceNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.SOURCE_LINE_NUMBER
         *
         * @param sourceLineNumber the value for INVENTORY_TRANSACTION_T.SOURCE_LINE_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder sourceLineNumber(String sourceLineNumber) {
            obj.setSourceLineNumber(sourceLineNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.SOURCE_BILL_NUMBER
         *
         * @param sourceBillNumber the value for INVENTORY_TRANSACTION_T.SOURCE_BILL_NUMBER
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.FROM_INVENTORY_ONHAND_ID
         *
         * @param fromInventoryOnhandId the value for INVENTORY_TRANSACTION_T.FROM_INVENTORY_ONHAND_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fromInventoryOnhandId(Long fromInventoryOnhandId) {
            obj.setFromInventoryOnhandId(fromInventoryOnhandId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.FROM_OWNER_CODE
         *
         * @param fromOwnerCode the value for INVENTORY_TRANSACTION_T.FROM_OWNER_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fromOwnerCode(String fromOwnerCode) {
            obj.setFromOwnerCode(fromOwnerCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.FROM_SKU_CODE
         *
         * @param fromSkuCode the value for INVENTORY_TRANSACTION_T.FROM_SKU_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fromSkuCode(String fromSkuCode) {
            obj.setFromSkuCode(fromSkuCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.FROM_SKU_ALIAS
         *
         * @param fromSkuAlias the value for INVENTORY_TRANSACTION_T.FROM_SKU_ALIAS
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fromSkuAlias(String fromSkuAlias) {
            obj.setFromSkuAlias(fromSkuAlias);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.FROM_LOCATION_CODE
         *
         * @param fromLocationCode the value for INVENTORY_TRANSACTION_T.FROM_LOCATION_CODE
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.FROM_LPN_NUMBER
         *
         * @param fromLpnNumber the value for INVENTORY_TRANSACTION_T.FROM_LPN_NUMBER
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.FROM_LOT_NUMBER
         *
         * @param fromLotNumber the value for INVENTORY_TRANSACTION_T.FROM_LOT_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fromLotNumber(String fromLotNumber) {
            obj.setFromLotNumber(fromLotNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.COMPANY_ID
         *
         * @param companyId the value for INVENTORY_TRANSACTION_T.COMPANY_ID
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.WAREHOUSE_ID
         *
         * @param warehouseId the value for INVENTORY_TRANSACTION_T.WAREHOUSE_ID
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.DEL_FLAG
         *
         * @param delFlag the value for INVENTORY_TRANSACTION_T.DEL_FLAG
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.CREATE_BY
         *
         * @param createBy the value for INVENTORY_TRANSACTION_T.CREATE_BY
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.CREATE_TIME
         *
         * @param createTime the value for INVENTORY_TRANSACTION_T.CREATE_TIME
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.UPDATE_BY
         *
         * @param updateBy the value for INVENTORY_TRANSACTION_T.UPDATE_BY
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.UPDATE_TIME
         *
         * @param updateTime the value for INVENTORY_TRANSACTION_T.UPDATE_TIME
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.UPDATE_VERSION
         *
         * @param updateVersion the value for INVENTORY_TRANSACTION_T.UPDATE_VERSION
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
         * This method sets the value of the database column INVENTORY_TRANSACTION_T.DESCRIPTION
         *
         * @param description the value for INVENTORY_TRANSACTION_T.DESCRIPTION
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
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public InventoryTransactionTEntity build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table INVENTORY_TRANSACTION_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum DelFlag {
        NOT_DELETED(new String("N"), "未删除"),
        IS_DELETED(new String("Y"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
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
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
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
     * This enum corresponds to the database table INVENTORY_TRANSACTION_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        transactionId("TRANSACTION_ID", "transactionId", "DECIMAL", false),
        transactionType("TRANSACTION_TYPE", "transactionType", "VARCHAR", false),
        transactionCategory("TRANSACTION_CATEGORY", "transactionCategory", "VARCHAR", false),
        transactionDate("TRANSACTION_DATE", "transactionDate", "TIMESTAMP", false),
        inventoryOnhandId("INVENTORY_ONHAND_ID", "inventoryOnhandId", "DECIMAL", false),
        ownerCode("OWNER_CODE", "ownerCode", "VARCHAR", false),
        skuCode("SKU_CODE", "skuCode", "VARCHAR", false),
        skuAlias("SKU_ALIAS", "skuAlias", "VARCHAR", false),
        packCode("PACK_CODE", "packCode", "VARCHAR", false),
        uom("UOM", "uom", "VARCHAR", false),
        uomQuantity("UOM_QUANTITY", "uomQuantity", "DECIMAL", false),
        quantity("QUANTITY", "quantity", "DECIMAL", false),
        locationCode("LOCATION_CODE", "locationCode", "VARCHAR", false),
        lpnNumber("LPN_NUMBER", "lpnNumber", "VARCHAR", false),
        lotNumber("LOT_NUMBER", "lotNumber", "VARCHAR", false),
        sourceNumber("SOURCE_NUMBER", "sourceNumber", "VARCHAR", false),
        sourceLineNumber("SOURCE_LINE_NUMBER", "sourceLineNumber", "VARCHAR", false),
        sourceBillNumber("SOURCE_BILL_NUMBER", "sourceBillNumber", "VARCHAR", false),
        fromInventoryOnhandId("FROM_INVENTORY_ONHAND_ID", "fromInventoryOnhandId", "DECIMAL", false),
        fromOwnerCode("FROM_OWNER_CODE", "fromOwnerCode", "VARCHAR", false),
        fromSkuCode("FROM_SKU_CODE", "fromSkuCode", "VARCHAR", false),
        fromSkuAlias("FROM_SKU_ALIAS", "fromSkuAlias", "VARCHAR", false),
        fromLocationCode("FROM_LOCATION_CODE", "fromLocationCode", "VARCHAR", false),
        fromLpnNumber("FROM_LPN_NUMBER", "fromLpnNumber", "VARCHAR", false),
        fromLotNumber("FROM_LOT_NUMBER", "fromLotNumber", "VARCHAR", false),
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
         * This field corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
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
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
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
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
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
         * This method corresponds to the database table INVENTORY_TRANSACTION_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}