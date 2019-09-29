package com.wms.entity.auto;

import com.wms.entity.BaseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class InboundHeaderTEntity extends BaseEntity {
    private Long inboundHeaderId;

    private String inboundNumber;

    private String sourceNumber;

    private String referenceNumber;

    private String type;

    private Long ownerId;

    private String ownerCode;

    private Long supplierId;

    private String supplierCode;

    private Date inboundDate;

    private Date expectedInboundDate;

    private Date closedDate;

    private String status;

    private Long carrierId;

    private String carrierCode;

    private String carrierCarNumber;

    private String carrierDriver;

    private String shipAddress1;

    private String shipAddress2;

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

    private String toWarehouseCode;

    private Long toWarehouseId;

    private String carrierDriverPhone;

    private String containerNumber;

    public Long getInboundHeaderId() {
        return inboundHeaderId;
    }

    public void setInboundHeaderId(Long inboundHeaderId) {
        this.inboundHeaderId = inboundHeaderId;
    }

    public String getInboundNumber() {
        return inboundNumber;
    }

    public void setInboundNumber(String inboundNumber) {
        this.inboundNumber = inboundNumber == null ? null : inboundNumber.trim();
    }

    public String getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(String sourceNumber) {
        this.sourceNumber = sourceNumber == null ? null : sourceNumber.trim();
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber == null ? null : referenceNumber.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode == null ? null : supplierCode.trim();
    }

    public Date getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(Date inboundDate) {
        this.inboundDate = inboundDate;
    }

    public Date getExpectedInboundDate() {
        return expectedInboundDate;
    }

    public void setExpectedInboundDate(Date expectedInboundDate) {
        this.expectedInboundDate = expectedInboundDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode == null ? null : carrierCode.trim();
    }

    public String getCarrierCarNumber() {
        return carrierCarNumber;
    }

    public void setCarrierCarNumber(String carrierCarNumber) {
        this.carrierCarNumber = carrierCarNumber == null ? null : carrierCarNumber.trim();
    }

    public String getCarrierDriver() {
        return carrierDriver;
    }

    public void setCarrierDriver(String carrierDriver) {
        this.carrierDriver = carrierDriver == null ? null : carrierDriver.trim();
    }

    public String getShipAddress1() {
        return shipAddress1;
    }

    public void setShipAddress1(String shipAddress1) {
        this.shipAddress1 = shipAddress1 == null ? null : shipAddress1.trim();
    }

    public String getShipAddress2() {
        return shipAddress2;
    }

    public void setShipAddress2(String shipAddress2) {
        this.shipAddress2 = shipAddress2 == null ? null : shipAddress2.trim();
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
     * This method corresponds to the database table INBOUND_HEADER_T
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

    public String getToWarehouseCode() {
        return toWarehouseCode;
    }

    public void setToWarehouseCode(String toWarehouseCode) {
        this.toWarehouseCode = toWarehouseCode == null ? null : toWarehouseCode.trim();
    }

    public Long getToWarehouseId() {
        return toWarehouseId;
    }

    public void setToWarehouseId(Long toWarehouseId) {
        this.toWarehouseId = toWarehouseId;
    }

    public String getCarrierDriverPhone() {
        return carrierDriverPhone;
    }

    public void setCarrierDriverPhone(String carrierDriverPhone) {
        this.carrierDriverPhone = carrierDriverPhone == null ? null : carrierDriverPhone.trim();
    }

    public String getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber == null ? null : containerNumber.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", inboundHeaderId=").append(inboundHeaderId);
        sb.append(", inboundNumber=").append(inboundNumber);
        sb.append(", sourceNumber=").append(sourceNumber);
        sb.append(", referenceNumber=").append(referenceNumber);
        sb.append(", type=").append(type);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", supplierCode=").append(supplierCode);
        sb.append(", inboundDate=").append(inboundDate);
        sb.append(", expectedInboundDate=").append(expectedInboundDate);
        sb.append(", closedDate=").append(closedDate);
        sb.append(", status=").append(status);
        sb.append(", carrierId=").append(carrierId);
        sb.append(", carrierCode=").append(carrierCode);
        sb.append(", carrierCarNumber=").append(carrierCarNumber);
        sb.append(", carrierDriver=").append(carrierDriver);
        sb.append(", shipAddress1=").append(shipAddress1);
        sb.append(", shipAddress2=").append(shipAddress2);
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
        sb.append(", toWarehouseCode=").append(toWarehouseCode);
        sb.append(", toWarehouseId=").append(toWarehouseId);
        sb.append(", carrierDriverPhone=").append(carrierDriverPhone);
        sb.append(", containerNumber=").append(containerNumber);
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
        InboundHeaderTEntity other = (InboundHeaderTEntity) that;
        return (this.getInboundHeaderId() == null ? other.getInboundHeaderId() == null : this.getInboundHeaderId().equals(other.getInboundHeaderId()))
            && (this.getInboundNumber() == null ? other.getInboundNumber() == null : this.getInboundNumber().equals(other.getInboundNumber()))
            && (this.getSourceNumber() == null ? other.getSourceNumber() == null : this.getSourceNumber().equals(other.getSourceNumber()))
            && (this.getReferenceNumber() == null ? other.getReferenceNumber() == null : this.getReferenceNumber().equals(other.getReferenceNumber()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getOwnerCode() == null ? other.getOwnerCode() == null : this.getOwnerCode().equals(other.getOwnerCode()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getSupplierCode() == null ? other.getSupplierCode() == null : this.getSupplierCode().equals(other.getSupplierCode()))
            && (this.getInboundDate() == null ? other.getInboundDate() == null : this.getInboundDate().equals(other.getInboundDate()))
            && (this.getExpectedInboundDate() == null ? other.getExpectedInboundDate() == null : this.getExpectedInboundDate().equals(other.getExpectedInboundDate()))
            && (this.getClosedDate() == null ? other.getClosedDate() == null : this.getClosedDate().equals(other.getClosedDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCarrierId() == null ? other.getCarrierId() == null : this.getCarrierId().equals(other.getCarrierId()))
            && (this.getCarrierCode() == null ? other.getCarrierCode() == null : this.getCarrierCode().equals(other.getCarrierCode()))
            && (this.getCarrierCarNumber() == null ? other.getCarrierCarNumber() == null : this.getCarrierCarNumber().equals(other.getCarrierCarNumber()))
            && (this.getCarrierDriver() == null ? other.getCarrierDriver() == null : this.getCarrierDriver().equals(other.getCarrierDriver()))
            && (this.getShipAddress1() == null ? other.getShipAddress1() == null : this.getShipAddress1().equals(other.getShipAddress1()))
            && (this.getShipAddress2() == null ? other.getShipAddress2() == null : this.getShipAddress2().equals(other.getShipAddress2()))
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
            && (this.getToWarehouseCode() == null ? other.getToWarehouseCode() == null : this.getToWarehouseCode().equals(other.getToWarehouseCode()))
            && (this.getToWarehouseId() == null ? other.getToWarehouseId() == null : this.getToWarehouseId().equals(other.getToWarehouseId()))
            && (this.getCarrierDriverPhone() == null ? other.getCarrierDriverPhone() == null : this.getCarrierDriverPhone().equals(other.getCarrierDriverPhone()))
            && (this.getContainerNumber() == null ? other.getContainerNumber() == null : this.getContainerNumber().equals(other.getContainerNumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInboundHeaderId() == null) ? 0 : getInboundHeaderId().hashCode());
        result = prime * result + ((getInboundNumber() == null) ? 0 : getInboundNumber().hashCode());
        result = prime * result + ((getSourceNumber() == null) ? 0 : getSourceNumber().hashCode());
        result = prime * result + ((getReferenceNumber() == null) ? 0 : getReferenceNumber().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getOwnerCode() == null) ? 0 : getOwnerCode().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getSupplierCode() == null) ? 0 : getSupplierCode().hashCode());
        result = prime * result + ((getInboundDate() == null) ? 0 : getInboundDate().hashCode());
        result = prime * result + ((getExpectedInboundDate() == null) ? 0 : getExpectedInboundDate().hashCode());
        result = prime * result + ((getClosedDate() == null) ? 0 : getClosedDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCarrierId() == null) ? 0 : getCarrierId().hashCode());
        result = prime * result + ((getCarrierCode() == null) ? 0 : getCarrierCode().hashCode());
        result = prime * result + ((getCarrierCarNumber() == null) ? 0 : getCarrierCarNumber().hashCode());
        result = prime * result + ((getCarrierDriver() == null) ? 0 : getCarrierDriver().hashCode());
        result = prime * result + ((getShipAddress1() == null) ? 0 : getShipAddress1().hashCode());
        result = prime * result + ((getShipAddress2() == null) ? 0 : getShipAddress2().hashCode());
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
        result = prime * result + ((getToWarehouseCode() == null) ? 0 : getToWarehouseCode().hashCode());
        result = prime * result + ((getToWarehouseId() == null) ? 0 : getToWarehouseId().hashCode());
        result = prime * result + ((getCarrierDriverPhone() == null) ? 0 : getCarrierDriverPhone().hashCode());
        result = prime * result + ((getContainerNumber() == null) ? 0 : getContainerNumber().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INBOUND_HEADER_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static InboundHeaderTEntity.Builder builder() {
        return new InboundHeaderTEntity.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table INBOUND_HEADER_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private InboundHeaderTEntity obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new InboundHeaderTEntity();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.INBOUND_HEADER_ID
         *
         * @param inboundHeaderId the value for INBOUND_HEADER_T.INBOUND_HEADER_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder inboundHeaderId(Long inboundHeaderId) {
            obj.setInboundHeaderId(inboundHeaderId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.INBOUND_NUMBER
         *
         * @param inboundNumber the value for INBOUND_HEADER_T.INBOUND_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder inboundNumber(String inboundNumber) {
            obj.setInboundNumber(inboundNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.SOURCE_NUMBER
         *
         * @param sourceNumber the value for INBOUND_HEADER_T.SOURCE_NUMBER
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
         * This method sets the value of the database column INBOUND_HEADER_T.REFERENCE_NUMBER
         *
         * @param referenceNumber the value for INBOUND_HEADER_T.REFERENCE_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder referenceNumber(String referenceNumber) {
            obj.setReferenceNumber(referenceNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.TYPE
         *
         * @param type the value for INBOUND_HEADER_T.TYPE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder type(String type) {
            obj.setType(type);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.OWNER_ID
         *
         * @param ownerId the value for INBOUND_HEADER_T.OWNER_ID
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
         * This method sets the value of the database column INBOUND_HEADER_T.OWNER_CODE
         *
         * @param ownerCode the value for INBOUND_HEADER_T.OWNER_CODE
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
         * This method sets the value of the database column INBOUND_HEADER_T.SUPPLIER_ID
         *
         * @param supplierId the value for INBOUND_HEADER_T.SUPPLIER_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder supplierId(Long supplierId) {
            obj.setSupplierId(supplierId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.SUPPLIER_CODE
         *
         * @param supplierCode the value for INBOUND_HEADER_T.SUPPLIER_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder supplierCode(String supplierCode) {
            obj.setSupplierCode(supplierCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.INBOUND_DATE
         *
         * @param inboundDate the value for INBOUND_HEADER_T.INBOUND_DATE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder inboundDate(Date inboundDate) {
            obj.setInboundDate(inboundDate);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.EXPECTED_INBOUND_DATE
         *
         * @param expectedInboundDate the value for INBOUND_HEADER_T.EXPECTED_INBOUND_DATE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder expectedInboundDate(Date expectedInboundDate) {
            obj.setExpectedInboundDate(expectedInboundDate);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.CLOSED_DATE
         *
         * @param closedDate the value for INBOUND_HEADER_T.CLOSED_DATE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder closedDate(Date closedDate) {
            obj.setClosedDate(closedDate);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.STATUS
         *
         * @param status the value for INBOUND_HEADER_T.STATUS
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
         * This method sets the value of the database column INBOUND_HEADER_T.CARRIER_ID
         *
         * @param carrierId the value for INBOUND_HEADER_T.CARRIER_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder carrierId(Long carrierId) {
            obj.setCarrierId(carrierId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.CARRIER_CODE
         *
         * @param carrierCode the value for INBOUND_HEADER_T.CARRIER_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder carrierCode(String carrierCode) {
            obj.setCarrierCode(carrierCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.CARRIER_CAR_NUMBER
         *
         * @param carrierCarNumber the value for INBOUND_HEADER_T.CARRIER_CAR_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder carrierCarNumber(String carrierCarNumber) {
            obj.setCarrierCarNumber(carrierCarNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.CARRIER_DRIVER
         *
         * @param carrierDriver the value for INBOUND_HEADER_T.CARRIER_DRIVER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder carrierDriver(String carrierDriver) {
            obj.setCarrierDriver(carrierDriver);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.CARRIER_DRIVER_PHONE
         *
         * @param carrierDriverPhone the value for INBOUND_HEADER_T.CARRIER_DRIVER_PHONE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder carrierDriverPhone(String carrierDriverPhone) {
            obj.setCarrierDriverPhone(carrierDriverPhone);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.SHIP_ADDRESS1
         *
         * @param shipAddress1 the value for INBOUND_HEADER_T.SHIP_ADDRESS1
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder shipAddress1(String shipAddress1) {
            obj.setShipAddress1(shipAddress1);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.SHIP_ADDRESS2
         *
         * @param shipAddress2 the value for INBOUND_HEADER_T.SHIP_ADDRESS2
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder shipAddress2(String shipAddress2) {
            obj.setShipAddress2(shipAddress2);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.REMARK
         *
         * @param remark the value for INBOUND_HEADER_T.REMARK
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
         * This method sets the value of the database column INBOUND_HEADER_T.COMPANY_ID
         *
         * @param companyId the value for INBOUND_HEADER_T.COMPANY_ID
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
         * This method sets the value of the database column INBOUND_HEADER_T.WAREHOUSE_ID
         *
         * @param warehouseId the value for INBOUND_HEADER_T.WAREHOUSE_ID
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
         * This method sets the value of the database column INBOUND_HEADER_T.DEL_FLAG
         *
         * @param delFlag the value for INBOUND_HEADER_T.DEL_FLAG
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
         * This method sets the value of the database column INBOUND_HEADER_T.CREATE_BY
         *
         * @param createBy the value for INBOUND_HEADER_T.CREATE_BY
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
         * This method sets the value of the database column INBOUND_HEADER_T.CREATE_TIME
         *
         * @param createTime the value for INBOUND_HEADER_T.CREATE_TIME
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
         * This method sets the value of the database column INBOUND_HEADER_T.UPDATE_BY
         *
         * @param updateBy the value for INBOUND_HEADER_T.UPDATE_BY
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
         * This method sets the value of the database column INBOUND_HEADER_T.UPDATE_TIME
         *
         * @param updateTime the value for INBOUND_HEADER_T.UPDATE_TIME
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
         * This method sets the value of the database column INBOUND_HEADER_T.UPDATE_VERSION
         *
         * @param updateVersion the value for INBOUND_HEADER_T.UPDATE_VERSION
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
         * This method sets the value of the database column INBOUND_HEADER_T.DESCRIPTION
         *
         * @param description the value for INBOUND_HEADER_T.DESCRIPTION
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
         * This method sets the value of the database column INBOUND_HEADER_T.TO_WAREHOUSE_CODE
         *
         * @param toWarehouseCode the value for INBOUND_HEADER_T.TO_WAREHOUSE_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder toWarehouseCode(String toWarehouseCode) {
            obj.setToWarehouseCode(toWarehouseCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.TO_WAREHOUSE_ID
         *
         * @param toWarehouseId the value for INBOUND_HEADER_T.TO_WAREHOUSE_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder toWarehouseId(Long toWarehouseId) {
            obj.setToWarehouseId(toWarehouseId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column INBOUND_HEADER_T.CONTAINER_NUMBER
         *
         * @param containerNumber the value for INBOUND_HEADER_T.CONTAINER_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder containerNumber(String containerNumber) {
            obj.setContainerNumber(containerNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public InboundHeaderTEntity build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table INBOUND_HEADER_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum DelFlag {
        NOT_DELETED(new String("N"), "未删除"),
        IS_DELETED(new String("Y"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INBOUND_HEADER_T
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
         * This method corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INBOUND_HEADER_T
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
     * This enum corresponds to the database table INBOUND_HEADER_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        inboundHeaderId("INBOUND_HEADER_ID", "inboundHeaderId", "DECIMAL", false),
        inboundNumber("INBOUND_NUMBER", "inboundNumber", "VARCHAR", false),
        sourceNumber("SOURCE_NUMBER", "sourceNumber", "VARCHAR", false),
        referenceNumber("REFERENCE_NUMBER", "referenceNumber", "VARCHAR", false),
        type("TYPE", "type", "VARCHAR", true),
        ownerId("OWNER_ID", "ownerId", "DECIMAL", false),
        ownerCode("OWNER_CODE", "ownerCode", "VARCHAR", false),
        supplierId("SUPPLIER_ID", "supplierId", "DECIMAL", false),
        supplierCode("SUPPLIER_CODE", "supplierCode", "VARCHAR", false),
        inboundDate("INBOUND_DATE", "inboundDate", "TIMESTAMP", false),
        expectedInboundDate("EXPECTED_INBOUND_DATE", "expectedInboundDate", "TIMESTAMP", false),
        closedDate("CLOSED_DATE", "closedDate", "TIMESTAMP", false),
        status("STATUS", "status", "VARCHAR", true),
        carrierId("CARRIER_ID", "carrierId", "DECIMAL", false),
        carrierCode("CARRIER_CODE", "carrierCode", "VARCHAR", false),
        carrierCarNumber("CARRIER_CAR_NUMBER", "carrierCarNumber", "VARCHAR", false),
        carrierDriver("CARRIER_DRIVER", "carrierDriver", "VARCHAR", false),
        shipAddress1("SHIP_ADDRESS1", "shipAddress1", "VARCHAR", false),
        shipAddress2("SHIP_ADDRESS2", "shipAddress2", "VARCHAR", false),
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
        toWarehouseCode("TO_WAREHOUSE_CODE", "toWarehouseCode", "VARCHAR", false),
        toWarehouseId("TO_WAREHOUSE_ID", "toWarehouseId", "DECIMAL", false),
        carrierDriverPhone("CARRIER_DRIVER_PHONE", "carrierDriverPhone", "VARCHAR", false),
        containerNumber("CONTAINER_NUMBER", "containerNumber", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INBOUND_HEADER_T
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
         * This method corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table INBOUND_HEADER_T
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
         * This method corresponds to the database table INBOUND_HEADER_T
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
         * This method corresponds to the database table INBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}