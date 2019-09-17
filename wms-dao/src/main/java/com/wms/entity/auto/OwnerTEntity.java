package com.wms.entity.auto;

import com.wms.entity.BaseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class OwnerTEntity extends BaseEntity {
    private Long ownerId;

    private String ownerCode;

    private String ownerDescr;

    private String contact1;

    private String contact2;

    private String phone1;

    private String phone2;

    private String address1;

    private String address2;

    private String fax;

    private String email1;

    private String barcodePrefix;

    private Long barcodeLength;

    private Long barcodeStart;

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

    private String webSite;

    private String email2;

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

    public String getOwnerDescr() {
        return ownerDescr;
    }

    public void setOwnerDescr(String ownerDescr) {
        this.ownerDescr = ownerDescr == null ? null : ownerDescr.trim();
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

    public String getBarcodePrefix() {
        return barcodePrefix;
    }

    public void setBarcodePrefix(String barcodePrefix) {
        this.barcodePrefix = barcodePrefix == null ? null : barcodePrefix.trim();
    }

    public Long getBarcodeLength() {
        return barcodeLength;
    }

    public void setBarcodeLength(Long barcodeLength) {
        this.barcodeLength = barcodeLength;
    }

    public Long getBarcodeStart() {
        return barcodeStart;
    }

    public void setBarcodeStart(Long barcodeStart) {
        this.barcodeStart = barcodeStart;
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
     * This method corresponds to the database table OWNER_T
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

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite == null ? null : webSite.trim();
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2 == null ? null : email2.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", ownerDescr=").append(ownerDescr);
        sb.append(", contact1=").append(contact1);
        sb.append(", contact2=").append(contact2);
        sb.append(", phone1=").append(phone1);
        sb.append(", phone2=").append(phone2);
        sb.append(", address1=").append(address1);
        sb.append(", address2=").append(address2);
        sb.append(", fax=").append(fax);
        sb.append(", email1=").append(email1);
        sb.append(", barcodePrefix=").append(barcodePrefix);
        sb.append(", barcodeLength=").append(barcodeLength);
        sb.append(", barcodeStart=").append(barcodeStart);
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
        sb.append(", webSite=").append(webSite);
        sb.append(", email2=").append(email2);
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
        OwnerTEntity other = (OwnerTEntity) that;
        return (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getOwnerCode() == null ? other.getOwnerCode() == null : this.getOwnerCode().equals(other.getOwnerCode()))
            && (this.getOwnerDescr() == null ? other.getOwnerDescr() == null : this.getOwnerDescr().equals(other.getOwnerDescr()))
            && (this.getContact1() == null ? other.getContact1() == null : this.getContact1().equals(other.getContact1()))
            && (this.getContact2() == null ? other.getContact2() == null : this.getContact2().equals(other.getContact2()))
            && (this.getPhone1() == null ? other.getPhone1() == null : this.getPhone1().equals(other.getPhone1()))
            && (this.getPhone2() == null ? other.getPhone2() == null : this.getPhone2().equals(other.getPhone2()))
            && (this.getAddress1() == null ? other.getAddress1() == null : this.getAddress1().equals(other.getAddress1()))
            && (this.getAddress2() == null ? other.getAddress2() == null : this.getAddress2().equals(other.getAddress2()))
            && (this.getFax() == null ? other.getFax() == null : this.getFax().equals(other.getFax()))
            && (this.getEmail1() == null ? other.getEmail1() == null : this.getEmail1().equals(other.getEmail1()))
            && (this.getBarcodePrefix() == null ? other.getBarcodePrefix() == null : this.getBarcodePrefix().equals(other.getBarcodePrefix()))
            && (this.getBarcodeLength() == null ? other.getBarcodeLength() == null : this.getBarcodeLength().equals(other.getBarcodeLength()))
            && (this.getBarcodeStart() == null ? other.getBarcodeStart() == null : this.getBarcodeStart().equals(other.getBarcodeStart()))
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
            && (this.getWebSite() == null ? other.getWebSite() == null : this.getWebSite().equals(other.getWebSite()))
            && (this.getEmail2() == null ? other.getEmail2() == null : this.getEmail2().equals(other.getEmail2()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getOwnerCode() == null) ? 0 : getOwnerCode().hashCode());
        result = prime * result + ((getOwnerDescr() == null) ? 0 : getOwnerDescr().hashCode());
        result = prime * result + ((getContact1() == null) ? 0 : getContact1().hashCode());
        result = prime * result + ((getContact2() == null) ? 0 : getContact2().hashCode());
        result = prime * result + ((getPhone1() == null) ? 0 : getPhone1().hashCode());
        result = prime * result + ((getPhone2() == null) ? 0 : getPhone2().hashCode());
        result = prime * result + ((getAddress1() == null) ? 0 : getAddress1().hashCode());
        result = prime * result + ((getAddress2() == null) ? 0 : getAddress2().hashCode());
        result = prime * result + ((getFax() == null) ? 0 : getFax().hashCode());
        result = prime * result + ((getEmail1() == null) ? 0 : getEmail1().hashCode());
        result = prime * result + ((getBarcodePrefix() == null) ? 0 : getBarcodePrefix().hashCode());
        result = prime * result + ((getBarcodeLength() == null) ? 0 : getBarcodeLength().hashCode());
        result = prime * result + ((getBarcodeStart() == null) ? 0 : getBarcodeStart().hashCode());
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
        result = prime * result + ((getWebSite() == null) ? 0 : getWebSite().hashCode());
        result = prime * result + ((getEmail2() == null) ? 0 : getEmail2().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OWNER_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static OwnerTEntity.Builder builder() {
        return new OwnerTEntity.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OWNER_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private OwnerTEntity obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new OwnerTEntity();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OWNER_T.OWNER_ID
         *
         * @param ownerId the value for OWNER_T.OWNER_ID
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
         * This method sets the value of the database column OWNER_T.OWNER_CODE
         *
         * @param ownerCode the value for OWNER_T.OWNER_CODE
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
         * This method sets the value of the database column OWNER_T.OWNER_DESCR
         *
         * @param ownerDescr the value for OWNER_T.OWNER_DESCR
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder ownerDescr(String ownerDescr) {
            obj.setOwnerDescr(ownerDescr);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OWNER_T.CONTACT1
         *
         * @param contact1 the value for OWNER_T.CONTACT1
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
         * This method sets the value of the database column OWNER_T.CONTACT2
         *
         * @param contact2 the value for OWNER_T.CONTACT2
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
         * This method sets the value of the database column OWNER_T.PHONE1
         *
         * @param phone1 the value for OWNER_T.PHONE1
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
         * This method sets the value of the database column OWNER_T.PHONE2
         *
         * @param phone2 the value for OWNER_T.PHONE2
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
         * This method sets the value of the database column OWNER_T.ADDRESS1
         *
         * @param address1 the value for OWNER_T.ADDRESS1
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
         * This method sets the value of the database column OWNER_T.ADDRESS2
         *
         * @param address2 the value for OWNER_T.ADDRESS2
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
         * This method sets the value of the database column OWNER_T.FAX
         *
         * @param fax the value for OWNER_T.FAX
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
         * This method sets the value of the database column OWNER_T.EMAIL1
         *
         * @param email1 the value for OWNER_T.EMAIL1
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
         * This method sets the value of the database column OWNER_T.BARCODE_PREFIX
         *
         * @param barcodePrefix the value for OWNER_T.BARCODE_PREFIX
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder barcodePrefix(String barcodePrefix) {
            obj.setBarcodePrefix(barcodePrefix);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OWNER_T.BARCODE_LENGTH
         *
         * @param barcodeLength the value for OWNER_T.BARCODE_LENGTH
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder barcodeLength(Long barcodeLength) {
            obj.setBarcodeLength(barcodeLength);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OWNER_T.BARCODE_START
         *
         * @param barcodeStart the value for OWNER_T.BARCODE_START
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder barcodeStart(Long barcodeStart) {
            obj.setBarcodeStart(barcodeStart);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OWNER_T.ACTIVE
         *
         * @param active the value for OWNER_T.ACTIVE
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
         * This method sets the value of the database column OWNER_T.REMARK
         *
         * @param remark the value for OWNER_T.REMARK
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
         * This method sets the value of the database column OWNER_T.COMPANY_ID
         *
         * @param companyId the value for OWNER_T.COMPANY_ID
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
         * This method sets the value of the database column OWNER_T.WAREHOUSE_ID
         *
         * @param warehouseId the value for OWNER_T.WAREHOUSE_ID
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
         * This method sets the value of the database column OWNER_T.DEL_FLAG
         *
         * @param delFlag the value for OWNER_T.DEL_FLAG
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
         * This method sets the value of the database column OWNER_T.CREATE_BY
         *
         * @param createBy the value for OWNER_T.CREATE_BY
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
         * This method sets the value of the database column OWNER_T.CREATE_TIME
         *
         * @param createTime the value for OWNER_T.CREATE_TIME
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
         * This method sets the value of the database column OWNER_T.UPDATE_BY
         *
         * @param updateBy the value for OWNER_T.UPDATE_BY
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
         * This method sets the value of the database column OWNER_T.UPDATE_TIME
         *
         * @param updateTime the value for OWNER_T.UPDATE_TIME
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
         * This method sets the value of the database column OWNER_T.UPDATE_VERSION
         *
         * @param updateVersion the value for OWNER_T.UPDATE_VERSION
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
         * This method sets the value of the database column OWNER_T.DESCRIPTION
         *
         * @param description the value for OWNER_T.DESCRIPTION
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
         * This method sets the value of the database column OWNER_T.WEB_SITE
         *
         * @param webSite the value for OWNER_T.WEB_SITE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder webSite(String webSite) {
            obj.setWebSite(webSite);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column OWNER_T.EMAIL2
         *
         * @param email2 the value for OWNER_T.EMAIL2
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
         * This method corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public OwnerTEntity build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table OWNER_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum DelFlag {
        NOT_DELETED(new String("N"), "未删除"),
        IS_DELETED(new String("Y"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OWNER_T
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
         * This method corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OWNER_T
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
     * This enum corresponds to the database table OWNER_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        ownerId("OWNER_ID", "ownerId", "DECIMAL", false),
        ownerCode("OWNER_CODE", "ownerCode", "VARCHAR", false),
        ownerDescr("OWNER_DESCR", "ownerDescr", "VARCHAR", false),
        contact1("CONTACT1", "contact1", "VARCHAR", false),
        contact2("CONTACT2", "contact2", "VARCHAR", false),
        phone1("PHONE1", "phone1", "VARCHAR", false),
        phone2("PHONE2", "phone2", "VARCHAR", false),
        address1("ADDRESS1", "address1", "VARCHAR", false),
        address2("ADDRESS2", "address2", "VARCHAR", false),
        fax("FAX", "fax", "VARCHAR", false),
        email1("EMAIL1", "email1", "VARCHAR", false),
        barcodePrefix("BARCODE_PREFIX", "barcodePrefix", "VARCHAR", false),
        barcodeLength("BARCODE_LENGTH", "barcodeLength", "DECIMAL", false),
        barcodeStart("BARCODE_START", "barcodeStart", "DECIMAL", false),
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
        webSite("WEB_SITE", "webSite", "VARCHAR", false),
        email2("EMAIL2", "email2", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OWNER_T
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
         * This method corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table OWNER_T
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
         * This method corresponds to the database table OWNER_T
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
         * This method corresponds to the database table OWNER_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}