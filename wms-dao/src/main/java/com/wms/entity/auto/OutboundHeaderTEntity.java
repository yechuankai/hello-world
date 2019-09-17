package com.wms.entity.auto;

import com.wms.entity.BaseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class OutboundHeaderTEntity extends BaseEntity {
    private Long outboundHeaderId;

    private String outboundNumber;

    private String sourceNumber;

    private String referenceNumber;

    private String type;

    private Long ownerId;

    private String ownerCode;

    private Long customerId;

    private String customerCode;

    private String customerDescr;

    private String contact1;

    private String contact2;

    private String phone1;

    private String phone2;

    private String address1;

    private String address2;

    private String fax;

    private String email1;

    private String shipLabel;

    private String carNumber;

    private String driver;

    private String containerNumber;

    private Date outboundDate;

    private Date expectedOutboundDate;

    private String status;

    private String processStatus;

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

    private Long carrierId;

    private String carrierCode;

    private String email2;

    private String sourceWaveNumber;

    private String driverPhone;

    public Long getOutboundHeaderId() {
        return outboundHeaderId;
    }

    public void setOutboundHeaderId(Long outboundHeaderId) {
        this.outboundHeaderId = outboundHeaderId;
    }

    public String getOutboundNumber() {
        return outboundNumber;
    }

    public void setOutboundNumber(String outboundNumber) {
        this.outboundNumber = outboundNumber == null ? null : outboundNumber.trim();
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getCustomerDescr() {
        return customerDescr;
    }

    public void setCustomerDescr(String customerDescr) {
        this.customerDescr = customerDescr == null ? null : customerDescr.trim();
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1 == null ? null : contact1.trim();
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2 == null ? null : contact2.trim();
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1 == null ? null : phone1.trim();
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2 == null ? null : phone2.trim();
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1 == null ? null : address1.trim();
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2 == null ? null : address2.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1 == null ? null : email1.trim();
    }

    public String getShipLabel() {
        return shipLabel;
    }

    public void setShipLabel(String shipLabel) {
        this.shipLabel = shipLabel == null ? null : shipLabel.trim();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver == null ? null : driver.trim();
    }

    public String getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber == null ? null : containerNumber.trim();
    }

    public Date getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(Date outboundDate) {
        this.outboundDate = outboundDate;
    }

    public Date getExpectedOutboundDate() {
        return expectedOutboundDate;
    }

    public void setExpectedOutboundDate(Date expectedOutboundDate) {
        this.expectedOutboundDate = expectedOutboundDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus == null ? null : processStatus.trim();
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
     * This method corresponds to the database table OUTBOUND_HEADER_T
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

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2 == null ? null : email2.trim();
    }

    public String getSourceWaveNumber() {
        return sourceWaveNumber;
    }

    public void setSourceWaveNumber(String sourceWaveNumber) {
        this.sourceWaveNumber = sourceWaveNumber == null ? null : sourceWaveNumber.trim();
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone == null ? null : driverPhone.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", outboundHeaderId=").append(outboundHeaderId);
        sb.append(", outboundNumber=").append(outboundNumber);
        sb.append(", sourceNumber=").append(sourceNumber);
        sb.append(", referenceNumber=").append(referenceNumber);
        sb.append(", type=").append(type);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", customerId=").append(customerId);
        sb.append(", customerCode=").append(customerCode);
        sb.append(", customerDescr=").append(customerDescr);
        sb.append(", contact1=").append(contact1);
        sb.append(", contact2=").append(contact2);
        sb.append(", phone1=").append(phone1);
        sb.append(", phone2=").append(phone2);
        sb.append(", address1=").append(address1);
        sb.append(", address2=").append(address2);
        sb.append(", fax=").append(fax);
        sb.append(", email1=").append(email1);
        sb.append(", shipLabel=").append(shipLabel);
        sb.append(", carNumber=").append(carNumber);
        sb.append(", driver=").append(driver);
        sb.append(", containerNumber=").append(containerNumber);
        sb.append(", outboundDate=").append(outboundDate);
        sb.append(", expectedOutboundDate=").append(expectedOutboundDate);
        sb.append(", status=").append(status);
        sb.append(", processStatus=").append(processStatus);
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
        sb.append(", carrierId=").append(carrierId);
        sb.append(", carrierCode=").append(carrierCode);
        sb.append(", email2=").append(email2);
        sb.append(", sourceWaveNumber=").append(sourceWaveNumber);
        sb.append(", driverPhone=").append(driverPhone);
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
        OutboundHeaderTEntity other = (OutboundHeaderTEntity) that;
        return (this.getOutboundHeaderId() == null ? other.getOutboundHeaderId() == null : this.getOutboundHeaderId().equals(other.getOutboundHeaderId()))
            && (this.getOutboundNumber() == null ? other.getOutboundNumber() == null : this.getOutboundNumber().equals(other.getOutboundNumber()))
            && (this.getSourceNumber() == null ? other.getSourceNumber() == null : this.getSourceNumber().equals(other.getSourceNumber()))
            && (this.getReferenceNumber() == null ? other.getReferenceNumber() == null : this.getReferenceNumber().equals(other.getReferenceNumber()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getOwnerCode() == null ? other.getOwnerCode() == null : this.getOwnerCode().equals(other.getOwnerCode()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerCode() == null ? other.getCustomerCode() == null : this.getCustomerCode().equals(other.getCustomerCode()))
            && (this.getCustomerDescr() == null ? other.getCustomerDescr() == null : this.getCustomerDescr().equals(other.getCustomerDescr()))
            && (this.getContact1() == null ? other.getContact1() == null : this.getContact1().equals(other.getContact1()))
            && (this.getContact2() == null ? other.getContact2() == null : this.getContact2().equals(other.getContact2()))
            && (this.getPhone1() == null ? other.getPhone1() == null : this.getPhone1().equals(other.getPhone1()))
            && (this.getPhone2() == null ? other.getPhone2() == null : this.getPhone2().equals(other.getPhone2()))
            && (this.getAddress1() == null ? other.getAddress1() == null : this.getAddress1().equals(other.getAddress1()))
            && (this.getAddress2() == null ? other.getAddress2() == null : this.getAddress2().equals(other.getAddress2()))
            && (this.getFax() == null ? other.getFax() == null : this.getFax().equals(other.getFax()))
            && (this.getEmail1() == null ? other.getEmail1() == null : this.getEmail1().equals(other.getEmail1()))
            && (this.getShipLabel() == null ? other.getShipLabel() == null : this.getShipLabel().equals(other.getShipLabel()))
            && (this.getCarNumber() == null ? other.getCarNumber() == null : this.getCarNumber().equals(other.getCarNumber()))
            && (this.getDriver() == null ? other.getDriver() == null : this.getDriver().equals(other.getDriver()))
            && (this.getContainerNumber() == null ? other.getContainerNumber() == null : this.getContainerNumber().equals(other.getContainerNumber()))
            && (this.getOutboundDate() == null ? other.getOutboundDate() == null : this.getOutboundDate().equals(other.getOutboundDate()))
            && (this.getExpectedOutboundDate() == null ? other.getExpectedOutboundDate() == null : this.getExpectedOutboundDate().equals(other.getExpectedOutboundDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getProcessStatus() == null ? other.getProcessStatus() == null : this.getProcessStatus().equals(other.getProcessStatus()))
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
            && (this.getCarrierId() == null ? other.getCarrierId() == null : this.getCarrierId().equals(other.getCarrierId()))
            && (this.getCarrierCode() == null ? other.getCarrierCode() == null : this.getCarrierCode().equals(other.getCarrierCode()))
            && (this.getEmail2() == null ? other.getEmail2() == null : this.getEmail2().equals(other.getEmail2()))
            && (this.getSourceWaveNumber() == null ? other.getSourceWaveNumber() == null : this.getSourceWaveNumber().equals(other.getSourceWaveNumber()))
            && (this.getDriverPhone() == null ? other.getDriverPhone() == null : this.getDriverPhone().equals(other.getDriverPhone()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOutboundHeaderId() == null) ? 0 : getOutboundHeaderId().hashCode());
        result = prime * result + ((getOutboundNumber() == null) ? 0 : getOutboundNumber().hashCode());
        result = prime * result + ((getSourceNumber() == null) ? 0 : getSourceNumber().hashCode());
        result = prime * result + ((getReferenceNumber() == null) ? 0 : getReferenceNumber().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getOwnerCode() == null) ? 0 : getOwnerCode().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerCode() == null) ? 0 : getCustomerCode().hashCode());
        result = prime * result + ((getCustomerDescr() == null) ? 0 : getCustomerDescr().hashCode());
        result = prime * result + ((getContact1() == null) ? 0 : getContact1().hashCode());
        result = prime * result + ((getContact2() == null) ? 0 : getContact2().hashCode());
        result = prime * result + ((getPhone1() == null) ? 0 : getPhone1().hashCode());
        result = prime * result + ((getPhone2() == null) ? 0 : getPhone2().hashCode());
        result = prime * result + ((getAddress1() == null) ? 0 : getAddress1().hashCode());
        result = prime * result + ((getAddress2() == null) ? 0 : getAddress2().hashCode());
        result = prime * result + ((getFax() == null) ? 0 : getFax().hashCode());
        result = prime * result + ((getEmail1() == null) ? 0 : getEmail1().hashCode());
        result = prime * result + ((getShipLabel() == null) ? 0 : getShipLabel().hashCode());
        result = prime * result + ((getCarNumber() == null) ? 0 : getCarNumber().hashCode());
        result = prime * result + ((getDriver() == null) ? 0 : getDriver().hashCode());
        result = prime * result + ((getContainerNumber() == null) ? 0 : getContainerNumber().hashCode());
        result = prime * result + ((getOutboundDate() == null) ? 0 : getOutboundDate().hashCode());
        result = prime * result + ((getExpectedOutboundDate() == null) ? 0 : getExpectedOutboundDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getProcessStatus() == null) ? 0 : getProcessStatus().hashCode());
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
        result = prime * result + ((getCarrierId() == null) ? 0 : getCarrierId().hashCode());
        result = prime * result + ((getCarrierCode() == null) ? 0 : getCarrierCode().hashCode());
        result = prime * result + ((getEmail2() == null) ? 0 : getEmail2().hashCode());
        result = prime * result + ((getSourceWaveNumber() == null) ? 0 : getSourceWaveNumber().hashCode());
        result = prime * result + ((getDriverPhone() == null) ? 0 : getDriverPhone().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OUTBOUND_HEADER_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static OutboundHeaderTEntity.Builder builder() {
        return new OutboundHeaderTEntity.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OUTBOUND_HEADER_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private OutboundHeaderTEntity obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new OutboundHeaderTEntity();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.OUTBOUND_HEADER_ID
         *
         * @param outboundHeaderId the value for OUTBOUND_HEADER_T.OUTBOUND_HEADER_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder outboundHeaderId(Long outboundHeaderId) {
            obj.setOutboundHeaderId(outboundHeaderId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.OUTBOUND_NUMBER
         *
         * @param outboundNumber the value for OUTBOUND_HEADER_T.OUTBOUND_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder outboundNumber(String outboundNumber) {
            obj.setOutboundNumber(outboundNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.SOURCE_NUMBER
         *
         * @param sourceNumber the value for OUTBOUND_HEADER_T.SOURCE_NUMBER
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.REFERENCE_NUMBER
         *
         * @param referenceNumber the value for OUTBOUND_HEADER_T.REFERENCE_NUMBER
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.TYPE
         *
         * @param type the value for OUTBOUND_HEADER_T.TYPE
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.OWNER_ID
         *
         * @param ownerId the value for OUTBOUND_HEADER_T.OWNER_ID
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.OWNER_CODE
         *
         * @param ownerCode the value for OUTBOUND_HEADER_T.OWNER_CODE
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.CUSTOMER_ID
         *
         * @param customerId the value for OUTBOUND_HEADER_T.CUSTOMER_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder customerId(Long customerId) {
            obj.setCustomerId(customerId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.CUSTOMER_CODE
         *
         * @param customerCode the value for OUTBOUND_HEADER_T.CUSTOMER_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder customerCode(String customerCode) {
            obj.setCustomerCode(customerCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.CUSTOMER_DESCR
         *
         * @param customerDescr the value for OUTBOUND_HEADER_T.CUSTOMER_DESCR
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder customerDescr(String customerDescr) {
            obj.setCustomerDescr(customerDescr);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.CONTACT1
         *
         * @param contact1 the value for OUTBOUND_HEADER_T.CONTACT1
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder contact1(String contact1) {
            obj.setContact1(contact1);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.CONTACT2
         *
         * @param contact2 the value for OUTBOUND_HEADER_T.CONTACT2
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder contact2(String contact2) {
            obj.setContact2(contact2);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.PHONE1
         *
         * @param phone1 the value for OUTBOUND_HEADER_T.PHONE1
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder phone1(String phone1) {
            obj.setPhone1(phone1);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.PHONE2
         *
         * @param phone2 the value for OUTBOUND_HEADER_T.PHONE2
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder phone2(String phone2) {
            obj.setPhone2(phone2);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.ADDRESS1
         *
         * @param address1 the value for OUTBOUND_HEADER_T.ADDRESS1
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder address1(String address1) {
            obj.setAddress1(address1);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.ADDRESS2
         *
         * @param address2 the value for OUTBOUND_HEADER_T.ADDRESS2
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder address2(String address2) {
            obj.setAddress2(address2);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.FAX
         *
         * @param fax the value for OUTBOUND_HEADER_T.FAX
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fax(String fax) {
            obj.setFax(fax);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.EMAIL1
         *
         * @param email1 the value for OUTBOUND_HEADER_T.EMAIL1
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder email1(String email1) {
            obj.setEmail1(email1);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.SHIP_LABEL
         *
         * @param shipLabel the value for OUTBOUND_HEADER_T.SHIP_LABEL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder shipLabel(String shipLabel) {
            obj.setShipLabel(shipLabel);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.CAR_NUMBER
         *
         * @param carNumber the value for OUTBOUND_HEADER_T.CAR_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder carNumber(String carNumber) {
            obj.setCarNumber(carNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.DRIVER
         *
         * @param driver the value for OUTBOUND_HEADER_T.DRIVER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder driver(String driver) {
            obj.setDriver(driver);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.DRIVER_PHONE
         *
         * @param driverPhone the value for OUTBOUND_HEADER_T.DRIVER_PHONE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder driverPhone(String driverPhone) {
            obj.setDriverPhone(driverPhone);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.CONTAINER_NUMBER
         *
         * @param containerNumber the value for OUTBOUND_HEADER_T.CONTAINER_NUMBER
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.OUTBOUND_DATE
         *
         * @param outboundDate the value for OUTBOUND_HEADER_T.OUTBOUND_DATE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder outboundDate(Date outboundDate) {
            obj.setOutboundDate(outboundDate);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.EXPECTED_OUTBOUND_DATE
         *
         * @param expectedOutboundDate the value for OUTBOUND_HEADER_T.EXPECTED_OUTBOUND_DATE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder expectedOutboundDate(Date expectedOutboundDate) {
            obj.setExpectedOutboundDate(expectedOutboundDate);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.STATUS
         *
         * @param status the value for OUTBOUND_HEADER_T.STATUS
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.PROCESS_STATUS
         *
         * @param processStatus the value for OUTBOUND_HEADER_T.PROCESS_STATUS
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder processStatus(String processStatus) {
            obj.setProcessStatus(processStatus);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.REMARK
         *
         * @param remark the value for OUTBOUND_HEADER_T.REMARK
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.COMPANY_ID
         *
         * @param companyId the value for OUTBOUND_HEADER_T.COMPANY_ID
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.WAREHOUSE_ID
         *
         * @param warehouseId the value for OUTBOUND_HEADER_T.WAREHOUSE_ID
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.DEL_FLAG
         *
         * @param delFlag the value for OUTBOUND_HEADER_T.DEL_FLAG
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.CREATE_BY
         *
         * @param createBy the value for OUTBOUND_HEADER_T.CREATE_BY
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.CREATE_TIME
         *
         * @param createTime the value for OUTBOUND_HEADER_T.CREATE_TIME
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.UPDATE_BY
         *
         * @param updateBy the value for OUTBOUND_HEADER_T.UPDATE_BY
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.UPDATE_TIME
         *
         * @param updateTime the value for OUTBOUND_HEADER_T.UPDATE_TIME
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.UPDATE_VERSION
         *
         * @param updateVersion the value for OUTBOUND_HEADER_T.UPDATE_VERSION
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.DESCRIPTION
         *
         * @param description the value for OUTBOUND_HEADER_T.DESCRIPTION
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.CARRIER_ID
         *
         * @param carrierId the value for OUTBOUND_HEADER_T.CARRIER_ID
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.CARRIER_CODE
         *
         * @param carrierCode the value for OUTBOUND_HEADER_T.CARRIER_CODE
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
         * This method sets the value of the database column OUTBOUND_HEADER_T.EMAIL2
         *
         * @param email2 the value for OUTBOUND_HEADER_T.EMAIL2
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder email2(String email2) {
            obj.setEmail2(email2);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OUTBOUND_HEADER_T.SOURCE_WAVE_NUMBER
         *
         * @param sourceWaveNumber the value for OUTBOUND_HEADER_T.SOURCE_WAVE_NUMBER
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
         * This method corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public OutboundHeaderTEntity build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table OUTBOUND_HEADER_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum DelFlag {
        NOT_DELETED(new String("N"), "未删除"),
        IS_DELETED(new String("Y"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OUTBOUND_HEADER_T
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
         * This method corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OUTBOUND_HEADER_T
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
     * This enum corresponds to the database table OUTBOUND_HEADER_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        outboundHeaderId("OUTBOUND_HEADER_ID", "outboundHeaderId", "DECIMAL", false),
        outboundNumber("OUTBOUND_NUMBER", "outboundNumber", "VARCHAR", false),
        sourceNumber("SOURCE_NUMBER", "sourceNumber", "VARCHAR", false),
        referenceNumber("REFERENCE_NUMBER", "referenceNumber", "VARCHAR", false),
        type("TYPE", "type", "VARCHAR", true),
        ownerId("OWNER_ID", "ownerId", "DECIMAL", false),
        ownerCode("OWNER_CODE", "ownerCode", "VARCHAR", false),
        customerId("CUSTOMER_ID", "customerId", "DECIMAL", false),
        customerCode("CUSTOMER_CODE", "customerCode", "VARCHAR", false),
        customerDescr("CUSTOMER_DESCR", "customerDescr", "VARCHAR", false),
        contact1("CONTACT1", "contact1", "VARCHAR", false),
        contact2("CONTACT2", "contact2", "VARCHAR", false),
        phone1("PHONE1", "phone1", "VARCHAR", false),
        phone2("PHONE2", "phone2", "VARCHAR", false),
        address1("ADDRESS1", "address1", "VARCHAR", false),
        address2("ADDRESS2", "address2", "VARCHAR", false),
        fax("FAX", "fax", "VARCHAR", false),
        email1("EMAIL1", "email1", "VARCHAR", false),
        shipLabel("SHIP_LABEL", "shipLabel", "VARCHAR", false),
        carNumber("CAR_NUMBER", "carNumber", "VARCHAR", false),
        driver("DRIVER", "driver", "VARCHAR", false),
        containerNumber("CONTAINER_NUMBER", "containerNumber", "VARCHAR", false),
        outboundDate("OUTBOUND_DATE", "outboundDate", "TIMESTAMP", false),
        expectedOutboundDate("EXPECTED_OUTBOUND_DATE", "expectedOutboundDate", "TIMESTAMP", false),
        status("STATUS", "status", "VARCHAR", true),
        processStatus("PROCESS_STATUS", "processStatus", "VARCHAR", false),
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
        carrierId("CARRIER_ID", "carrierId", "DECIMAL", false),
        carrierCode("CARRIER_CODE", "carrierCode", "VARCHAR", false),
        email2("EMAIL2", "email2", "VARCHAR", false),
        sourceWaveNumber("SOURCE_WAVE_NUMBER", "sourceWaveNumber", "VARCHAR", false),
        driverPhone("DRIVER_PHONE", "driverPhone", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OUTBOUND_HEADER_T
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
         * This method corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OUTBOUND_HEADER_T
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
         * This method corresponds to the database table OUTBOUND_HEADER_T
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
         * This method corresponds to the database table OUTBOUND_HEADER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}