package com.wms.entity.auto;

import com.wms.entity.BaseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class InventoryCountRequestTEntity extends BaseEntity {
    private Long inventoryCountRequestId;

    private String requestNumber;

    private String requestDescr;

    private String requestType;

    private String quantityShowFlag;

    private String fromZoneCode;

    private String toZoneCode;

    private String fromLocationCode;

    private String toLocationCode;

    private String fromSkuCode;

    private String toSkuCode;

    private String skuCodeIn;

    private String fromLpnNumber;

    private String toLpnNumber;

    private String lpnNumberIn;

    private Date requestDate;

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

    public Long getInventoryCountRequestId() {
        return inventoryCountRequestId;
    }

    public void setInventoryCountRequestId(Long inventoryCountRequestId) {
        this.inventoryCountRequestId = inventoryCountRequestId;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber == null ? null : requestNumber.trim();
    }

    public String getRequestDescr() {
        return requestDescr;
    }

    public void setRequestDescr(String requestDescr) {
        this.requestDescr = requestDescr == null ? null : requestDescr.trim();
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType == null ? null : requestType.trim();
    }

    public String getQuantityShowFlag() {
        return quantityShowFlag;
    }

    public void setQuantityShowFlag(String quantityShowFlag) {
        this.quantityShowFlag = quantityShowFlag == null ? null : quantityShowFlag.trim();
    }

    public String getFromZoneCode() {
        return fromZoneCode;
    }

    public void setFromZoneCode(String fromZoneCode) {
        this.fromZoneCode = fromZoneCode == null ? null : fromZoneCode.trim();
    }

    public String getToZoneCode() {
        return toZoneCode;
    }

    public void setToZoneCode(String toZoneCode) {
        this.toZoneCode = toZoneCode == null ? null : toZoneCode.trim();
    }

    public String getFromLocationCode() {
        return fromLocationCode;
    }

    public void setFromLocationCode(String fromLocationCode) {
        this.fromLocationCode = fromLocationCode == null ? null : fromLocationCode.trim();
    }

    public String getToLocationCode() {
        return toLocationCode;
    }

    public void setToLocationCode(String toLocationCode) {
        this.toLocationCode = toLocationCode == null ? null : toLocationCode.trim();
    }

    public String getFromSkuCode() {
        return fromSkuCode;
    }

    public void setFromSkuCode(String fromSkuCode) {
        this.fromSkuCode = fromSkuCode == null ? null : fromSkuCode.trim();
    }

    public String getToSkuCode() {
        return toSkuCode;
    }

    public void setToSkuCode(String toSkuCode) {
        this.toSkuCode = toSkuCode == null ? null : toSkuCode.trim();
    }

    public String getSkuCodeIn() {
        return skuCodeIn;
    }

    public void setSkuCodeIn(String skuCodeIn) {
        this.skuCodeIn = skuCodeIn == null ? null : skuCodeIn.trim();
    }

    public String getFromLpnNumber() {
        return fromLpnNumber;
    }

    public void setFromLpnNumber(String fromLpnNumber) {
        this.fromLpnNumber = fromLpnNumber == null ? null : fromLpnNumber.trim();
    }

    public String getToLpnNumber() {
        return toLpnNumber;
    }

    public void setToLpnNumber(String toLpnNumber) {
        this.toLpnNumber = toLpnNumber == null ? null : toLpnNumber.trim();
    }

    public String getLpnNumberIn() {
        return lpnNumberIn;
    }

    public void setLpnNumberIn(String lpnNumberIn) {
        this.lpnNumberIn = lpnNumberIn == null ? null : lpnNumberIn.trim();
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
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
     * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
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
        sb.append(", inventoryCountRequestId=").append(inventoryCountRequestId);
        sb.append(", requestNumber=").append(requestNumber);
        sb.append(", requestDescr=").append(requestDescr);
        sb.append(", requestType=").append(requestType);
        sb.append(", quantityShowFlag=").append(quantityShowFlag);
        sb.append(", fromZoneCode=").append(fromZoneCode);
        sb.append(", toZoneCode=").append(toZoneCode);
        sb.append(", fromLocationCode=").append(fromLocationCode);
        sb.append(", toLocationCode=").append(toLocationCode);
        sb.append(", fromSkuCode=").append(fromSkuCode);
        sb.append(", toSkuCode=").append(toSkuCode);
        sb.append(", skuCodeIn=").append(skuCodeIn);
        sb.append(", fromLpnNumber=").append(fromLpnNumber);
        sb.append(", toLpnNumber=").append(toLpnNumber);
        sb.append(", lpnNumberIn=").append(lpnNumberIn);
        sb.append(", requestDate=").append(requestDate);
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
        InventoryCountRequestTEntity other = (InventoryCountRequestTEntity) that;
        return (this.getInventoryCountRequestId() == null ? other.getInventoryCountRequestId() == null : this.getInventoryCountRequestId().equals(other.getInventoryCountRequestId()))
            && (this.getRequestNumber() == null ? other.getRequestNumber() == null : this.getRequestNumber().equals(other.getRequestNumber()))
            && (this.getRequestDescr() == null ? other.getRequestDescr() == null : this.getRequestDescr().equals(other.getRequestDescr()))
            && (this.getRequestType() == null ? other.getRequestType() == null : this.getRequestType().equals(other.getRequestType()))
            && (this.getQuantityShowFlag() == null ? other.getQuantityShowFlag() == null : this.getQuantityShowFlag().equals(other.getQuantityShowFlag()))
            && (this.getFromZoneCode() == null ? other.getFromZoneCode() == null : this.getFromZoneCode().equals(other.getFromZoneCode()))
            && (this.getToZoneCode() == null ? other.getToZoneCode() == null : this.getToZoneCode().equals(other.getToZoneCode()))
            && (this.getFromLocationCode() == null ? other.getFromLocationCode() == null : this.getFromLocationCode().equals(other.getFromLocationCode()))
            && (this.getToLocationCode() == null ? other.getToLocationCode() == null : this.getToLocationCode().equals(other.getToLocationCode()))
            && (this.getFromSkuCode() == null ? other.getFromSkuCode() == null : this.getFromSkuCode().equals(other.getFromSkuCode()))
            && (this.getToSkuCode() == null ? other.getToSkuCode() == null : this.getToSkuCode().equals(other.getToSkuCode()))
            && (this.getSkuCodeIn() == null ? other.getSkuCodeIn() == null : this.getSkuCodeIn().equals(other.getSkuCodeIn()))
            && (this.getFromLpnNumber() == null ? other.getFromLpnNumber() == null : this.getFromLpnNumber().equals(other.getFromLpnNumber()))
            && (this.getToLpnNumber() == null ? other.getToLpnNumber() == null : this.getToLpnNumber().equals(other.getToLpnNumber()))
            && (this.getLpnNumberIn() == null ? other.getLpnNumberIn() == null : this.getLpnNumberIn().equals(other.getLpnNumberIn()))
            && (this.getRequestDate() == null ? other.getRequestDate() == null : this.getRequestDate().equals(other.getRequestDate()))
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
        result = prime * result + ((getInventoryCountRequestId() == null) ? 0 : getInventoryCountRequestId().hashCode());
        result = prime * result + ((getRequestNumber() == null) ? 0 : getRequestNumber().hashCode());
        result = prime * result + ((getRequestDescr() == null) ? 0 : getRequestDescr().hashCode());
        result = prime * result + ((getRequestType() == null) ? 0 : getRequestType().hashCode());
        result = prime * result + ((getQuantityShowFlag() == null) ? 0 : getQuantityShowFlag().hashCode());
        result = prime * result + ((getFromZoneCode() == null) ? 0 : getFromZoneCode().hashCode());
        result = prime * result + ((getToZoneCode() == null) ? 0 : getToZoneCode().hashCode());
        result = prime * result + ((getFromLocationCode() == null) ? 0 : getFromLocationCode().hashCode());
        result = prime * result + ((getToLocationCode() == null) ? 0 : getToLocationCode().hashCode());
        result = prime * result + ((getFromSkuCode() == null) ? 0 : getFromSkuCode().hashCode());
        result = prime * result + ((getToSkuCode() == null) ? 0 : getToSkuCode().hashCode());
        result = prime * result + ((getSkuCodeIn() == null) ? 0 : getSkuCodeIn().hashCode());
        result = prime * result + ((getFromLpnNumber() == null) ? 0 : getFromLpnNumber().hashCode());
        result = prime * result + ((getToLpnNumber() == null) ? 0 : getToLpnNumber().hashCode());
        result = prime * result + ((getLpnNumberIn() == null) ? 0 : getLpnNumberIn().hashCode());
        result = prime * result + ((getRequestDate() == null) ? 0 : getRequestDate().hashCode());
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
     * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static InventoryCountRequestTEntity.Builder builder() {
        return new InventoryCountRequestTEntity.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table INVENTORY_COUNT_REQUEST_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private InventoryCountRequestTEntity obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new InventoryCountRequestTEntity();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.INVENTORY_COUNT_REQUEST_ID
         *
         * @param inventoryCountRequestId the value for INVENTORY_COUNT_REQUEST_T.INVENTORY_COUNT_REQUEST_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder inventoryCountRequestId(Long inventoryCountRequestId) {
            obj.setInventoryCountRequestId(inventoryCountRequestId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.REQUEST_NUMBER
         *
         * @param requestNumber the value for INVENTORY_COUNT_REQUEST_T.REQUEST_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder requestNumber(String requestNumber) {
            obj.setRequestNumber(requestNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.REQUEST_DESCR
         *
         * @param requestDescr the value for INVENTORY_COUNT_REQUEST_T.REQUEST_DESCR
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder requestDescr(String requestDescr) {
            obj.setRequestDescr(requestDescr);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.REQUEST_TYPE
         *
         * @param requestType the value for INVENTORY_COUNT_REQUEST_T.REQUEST_TYPE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder requestType(String requestType) {
            obj.setRequestType(requestType);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.QUANTITY_SHOW_FLAG
         *
         * @param quantityShowFlag the value for INVENTORY_COUNT_REQUEST_T.QUANTITY_SHOW_FLAG
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantityShowFlag(String quantityShowFlag) {
            obj.setQuantityShowFlag(quantityShowFlag);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.FROM_ZONE_CODE
         *
         * @param fromZoneCode the value for INVENTORY_COUNT_REQUEST_T.FROM_ZONE_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fromZoneCode(String fromZoneCode) {
            obj.setFromZoneCode(fromZoneCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.TO_ZONE_CODE
         *
         * @param toZoneCode the value for INVENTORY_COUNT_REQUEST_T.TO_ZONE_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder toZoneCode(String toZoneCode) {
            obj.setToZoneCode(toZoneCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.FROM_LOCATION_CODE
         *
         * @param fromLocationCode the value for INVENTORY_COUNT_REQUEST_T.FROM_LOCATION_CODE
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
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.TO_LOCATION_CODE
         *
         * @param toLocationCode the value for INVENTORY_COUNT_REQUEST_T.TO_LOCATION_CODE
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
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.FROM_SKU_CODE
         *
         * @param fromSkuCode the value for INVENTORY_COUNT_REQUEST_T.FROM_SKU_CODE
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
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.TO_SKU_CODE
         *
         * @param toSkuCode the value for INVENTORY_COUNT_REQUEST_T.TO_SKU_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder toSkuCode(String toSkuCode) {
            obj.setToSkuCode(toSkuCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.SKU_CODE_IN
         *
         * @param skuCodeIn the value for INVENTORY_COUNT_REQUEST_T.SKU_CODE_IN
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder skuCodeIn(String skuCodeIn) {
            obj.setSkuCodeIn(skuCodeIn);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.FROM_LPN_NUMBER
         *
         * @param fromLpnNumber the value for INVENTORY_COUNT_REQUEST_T.FROM_LPN_NUMBER
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
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.TO_LPN_NUMBER
         *
         * @param toLpnNumber the value for INVENTORY_COUNT_REQUEST_T.TO_LPN_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder toLpnNumber(String toLpnNumber) {
            obj.setToLpnNumber(toLpnNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.LPN_NUMBER_IN
         *
         * @param lpnNumberIn the value for INVENTORY_COUNT_REQUEST_T.LPN_NUMBER_IN
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lpnNumberIn(String lpnNumberIn) {
            obj.setLpnNumberIn(lpnNumberIn);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.REQUEST_DATE
         *
         * @param requestDate the value for INVENTORY_COUNT_REQUEST_T.REQUEST_DATE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder requestDate(Date requestDate) {
            obj.setRequestDate(requestDate);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.REMARK
         *
         * @param remark the value for INVENTORY_COUNT_REQUEST_T.REMARK
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
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.COMPANY_ID
         *
         * @param companyId the value for INVENTORY_COUNT_REQUEST_T.COMPANY_ID
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
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.WAREHOUSE_ID
         *
         * @param warehouseId the value for INVENTORY_COUNT_REQUEST_T.WAREHOUSE_ID
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
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.DEL_FLAG
         *
         * @param delFlag the value for INVENTORY_COUNT_REQUEST_T.DEL_FLAG
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
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.CREATE_BY
         *
         * @param createBy the value for INVENTORY_COUNT_REQUEST_T.CREATE_BY
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
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.CREATE_TIME
         *
         * @param createTime the value for INVENTORY_COUNT_REQUEST_T.CREATE_TIME
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
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.UPDATE_BY
         *
         * @param updateBy the value for INVENTORY_COUNT_REQUEST_T.UPDATE_BY
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
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.UPDATE_TIME
         *
         * @param updateTime the value for INVENTORY_COUNT_REQUEST_T.UPDATE_TIME
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
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.UPDATE_VERSION
         *
         * @param updateVersion the value for INVENTORY_COUNT_REQUEST_T.UPDATE_VERSION
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
         * This method sets the value of the database column INVENTORY_COUNT_REQUEST_T.DESCRIPTION
         *
         * @param description the value for INVENTORY_COUNT_REQUEST_T.DESCRIPTION
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
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public InventoryCountRequestTEntity build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table INVENTORY_COUNT_REQUEST_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum DelFlag {
        NOT_DELETED(new String("N"), "未删除"),
        IS_DELETED(new String("Y"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
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
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
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
     * This enum corresponds to the database table INVENTORY_COUNT_REQUEST_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        inventoryCountRequestId("INVENTORY_COUNT_REQUEST_ID", "inventoryCountRequestId", "DECIMAL", false),
        requestNumber("REQUEST_NUMBER", "requestNumber", "VARCHAR", false),
        requestDescr("REQUEST_DESCR", "requestDescr", "VARCHAR", false),
        requestType("REQUEST_TYPE", "requestType", "VARCHAR", false),
        quantityShowFlag("QUANTITY_SHOW_FLAG", "quantityShowFlag", "CHAR", false),
        fromZoneCode("FROM_ZONE_CODE", "fromZoneCode", "VARCHAR", false),
        toZoneCode("TO_ZONE_CODE", "toZoneCode", "VARCHAR", false),
        fromLocationCode("FROM_LOCATION_CODE", "fromLocationCode", "VARCHAR", false),
        toLocationCode("TO_LOCATION_CODE", "toLocationCode", "VARCHAR", false),
        fromSkuCode("FROM_SKU_CODE", "fromSkuCode", "VARCHAR", false),
        toSkuCode("TO_SKU_CODE", "toSkuCode", "VARCHAR", false),
        skuCodeIn("SKU_CODE_IN", "skuCodeIn", "VARCHAR", false),
        fromLpnNumber("FROM_LPN_NUMBER", "fromLpnNumber", "VARCHAR", false),
        toLpnNumber("TO_LPN_NUMBER", "toLpnNumber", "VARCHAR", false),
        lpnNumberIn("LPN_NUMBER_IN", "lpnNumberIn", "VARCHAR", false),
        requestDate("REQUEST_DATE", "requestDate", "TIMESTAMP", false),
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
         * This field corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
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
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
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
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
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
         * This method corresponds to the database table INVENTORY_COUNT_REQUEST_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}