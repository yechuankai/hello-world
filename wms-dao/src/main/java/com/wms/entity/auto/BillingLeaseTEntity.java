package com.wms.entity.auto;

import com.wms.entity.BaseEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class BillingLeaseTEntity extends BaseEntity {
    private Long billingLeaseId;

    private Date billingDate;

    private String billingMonth;

    private String billingLeaseCode;

    private Long ownerId;

    private String ownerCode;

    private String sourceBillNumber;

    private Long skuId;

    private String skuCode;

    private Long packId;

    private String packCode;

    private String uom;

    private Long lotId;

    private String lotNumber;

    private Date inboundDate;

    private BigDecimal quantityReceived;

    private Date outboundDate;

    private BigDecimal quantityShiped;

    private BigDecimal volume;

    private BigDecimal weightGross;

    private BigDecimal weightNet;

    private BigDecimal weightTare;

    private Long days;

    private String status;

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

    private BigDecimal revenueTon;

    public Long getBillingLeaseId() {
        return billingLeaseId;
    }

    public void setBillingLeaseId(Long billingLeaseId) {
        this.billingLeaseId = billingLeaseId;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public String getBillingMonth() {
        return billingMonth;
    }

    public void setBillingMonth(String billingMonth) {
        this.billingMonth = billingMonth == null ? null : billingMonth.trim();
    }

    public String getBillingLeaseCode() {
        return billingLeaseCode;
    }

    public void setBillingLeaseCode(String billingLeaseCode) {
        this.billingLeaseCode = billingLeaseCode == null ? null : billingLeaseCode.trim();
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

    public String getSourceBillNumber() {
        return sourceBillNumber;
    }

    public void setSourceBillNumber(String sourceBillNumber) {
        this.sourceBillNumber = sourceBillNumber == null ? null : sourceBillNumber.trim();
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

    public Date getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(Date inboundDate) {
        this.inboundDate = inboundDate;
    }

    public BigDecimal getQuantityReceived() {
        return quantityReceived;
    }

    public void setQuantityReceived(BigDecimal quantityReceived) {
        this.quantityReceived = quantityReceived;
    }

    public Date getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(Date outboundDate) {
        this.outboundDate = outboundDate;
    }

    public BigDecimal getQuantityShiped() {
        return quantityShiped;
    }

    public void setQuantityShiped(BigDecimal quantityShiped) {
        this.quantityShiped = quantityShiped;
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

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
     * This method corresponds to the database table BILLING_LEASE_T
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

    public BigDecimal getRevenueTon() {
        return revenueTon;
    }

    public void setRevenueTon(BigDecimal revenueTon) {
        this.revenueTon = revenueTon;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", billingLeaseId=").append(billingLeaseId);
        sb.append(", billingDate=").append(billingDate);
        sb.append(", billingMonth=").append(billingMonth);
        sb.append(", billingLeaseCode=").append(billingLeaseCode);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", sourceBillNumber=").append(sourceBillNumber);
        sb.append(", skuId=").append(skuId);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", packId=").append(packId);
        sb.append(", packCode=").append(packCode);
        sb.append(", uom=").append(uom);
        sb.append(", lotId=").append(lotId);
        sb.append(", lotNumber=").append(lotNumber);
        sb.append(", inboundDate=").append(inboundDate);
        sb.append(", quantityReceived=").append(quantityReceived);
        sb.append(", outboundDate=").append(outboundDate);
        sb.append(", quantityShiped=").append(quantityShiped);
        sb.append(", volume=").append(volume);
        sb.append(", weightGross=").append(weightGross);
        sb.append(", weightNet=").append(weightNet);
        sb.append(", weightTare=").append(weightTare);
        sb.append(", days=").append(days);
        sb.append(", status=").append(status);
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
        sb.append(", revenueTon=").append(revenueTon);
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
        BillingLeaseTEntity other = (BillingLeaseTEntity) that;
        return (this.getBillingLeaseId() == null ? other.getBillingLeaseId() == null : this.getBillingLeaseId().equals(other.getBillingLeaseId()))
            && (this.getBillingDate() == null ? other.getBillingDate() == null : this.getBillingDate().equals(other.getBillingDate()))
            && (this.getBillingMonth() == null ? other.getBillingMonth() == null : this.getBillingMonth().equals(other.getBillingMonth()))
            && (this.getBillingLeaseCode() == null ? other.getBillingLeaseCode() == null : this.getBillingLeaseCode().equals(other.getBillingLeaseCode()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getOwnerCode() == null ? other.getOwnerCode() == null : this.getOwnerCode().equals(other.getOwnerCode()))
            && (this.getSourceBillNumber() == null ? other.getSourceBillNumber() == null : this.getSourceBillNumber().equals(other.getSourceBillNumber()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getSkuCode() == null ? other.getSkuCode() == null : this.getSkuCode().equals(other.getSkuCode()))
            && (this.getPackId() == null ? other.getPackId() == null : this.getPackId().equals(other.getPackId()))
            && (this.getPackCode() == null ? other.getPackCode() == null : this.getPackCode().equals(other.getPackCode()))
            && (this.getUom() == null ? other.getUom() == null : this.getUom().equals(other.getUom()))
            && (this.getLotId() == null ? other.getLotId() == null : this.getLotId().equals(other.getLotId()))
            && (this.getLotNumber() == null ? other.getLotNumber() == null : this.getLotNumber().equals(other.getLotNumber()))
            && (this.getInboundDate() == null ? other.getInboundDate() == null : this.getInboundDate().equals(other.getInboundDate()))
            && (this.getQuantityReceived() == null ? other.getQuantityReceived() == null : this.getQuantityReceived().equals(other.getQuantityReceived()))
            && (this.getOutboundDate() == null ? other.getOutboundDate() == null : this.getOutboundDate().equals(other.getOutboundDate()))
            && (this.getQuantityShiped() == null ? other.getQuantityShiped() == null : this.getQuantityShiped().equals(other.getQuantityShiped()))
            && (this.getVolume() == null ? other.getVolume() == null : this.getVolume().equals(other.getVolume()))
            && (this.getWeightGross() == null ? other.getWeightGross() == null : this.getWeightGross().equals(other.getWeightGross()))
            && (this.getWeightNet() == null ? other.getWeightNet() == null : this.getWeightNet().equals(other.getWeightNet()))
            && (this.getWeightTare() == null ? other.getWeightTare() == null : this.getWeightTare().equals(other.getWeightTare()))
            && (this.getDays() == null ? other.getDays() == null : this.getDays().equals(other.getDays()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
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
            && (this.getRevenueTon() == null ? other.getRevenueTon() == null : this.getRevenueTon().equals(other.getRevenueTon()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBillingLeaseId() == null) ? 0 : getBillingLeaseId().hashCode());
        result = prime * result + ((getBillingDate() == null) ? 0 : getBillingDate().hashCode());
        result = prime * result + ((getBillingMonth() == null) ? 0 : getBillingMonth().hashCode());
        result = prime * result + ((getBillingLeaseCode() == null) ? 0 : getBillingLeaseCode().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getOwnerCode() == null) ? 0 : getOwnerCode().hashCode());
        result = prime * result + ((getSourceBillNumber() == null) ? 0 : getSourceBillNumber().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getSkuCode() == null) ? 0 : getSkuCode().hashCode());
        result = prime * result + ((getPackId() == null) ? 0 : getPackId().hashCode());
        result = prime * result + ((getPackCode() == null) ? 0 : getPackCode().hashCode());
        result = prime * result + ((getUom() == null) ? 0 : getUom().hashCode());
        result = prime * result + ((getLotId() == null) ? 0 : getLotId().hashCode());
        result = prime * result + ((getLotNumber() == null) ? 0 : getLotNumber().hashCode());
        result = prime * result + ((getInboundDate() == null) ? 0 : getInboundDate().hashCode());
        result = prime * result + ((getQuantityReceived() == null) ? 0 : getQuantityReceived().hashCode());
        result = prime * result + ((getOutboundDate() == null) ? 0 : getOutboundDate().hashCode());
        result = prime * result + ((getQuantityShiped() == null) ? 0 : getQuantityShiped().hashCode());
        result = prime * result + ((getVolume() == null) ? 0 : getVolume().hashCode());
        result = prime * result + ((getWeightGross() == null) ? 0 : getWeightGross().hashCode());
        result = prime * result + ((getWeightNet() == null) ? 0 : getWeightNet().hashCode());
        result = prime * result + ((getWeightTare() == null) ? 0 : getWeightTare().hashCode());
        result = prime * result + ((getDays() == null) ? 0 : getDays().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        result = prime * result + ((getRevenueTon() == null) ? 0 : getRevenueTon().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BILLING_LEASE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static BillingLeaseTEntity.Builder builder() {
        return new BillingLeaseTEntity.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table BILLING_LEASE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private BillingLeaseTEntity obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new BillingLeaseTEntity();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column BILLING_LEASE_T.BILLING_LEASE_ID
         *
         * @param billingLeaseId the value for BILLING_LEASE_T.BILLING_LEASE_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder billingLeaseId(Long billingLeaseId) {
            obj.setBillingLeaseId(billingLeaseId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column BILLING_LEASE_T.BILLING_DATE
         *
         * @param billingDate the value for BILLING_LEASE_T.BILLING_DATE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder billingDate(Date billingDate) {
            obj.setBillingDate(billingDate);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column BILLING_LEASE_T.BILLING_MONTH
         *
         * @param billingMonth the value for BILLING_LEASE_T.BILLING_MONTH
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder billingMonth(String billingMonth) {
            obj.setBillingMonth(billingMonth);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column BILLING_LEASE_T.BILLING_LEASE_CODE
         *
         * @param billingLeaseCode the value for BILLING_LEASE_T.BILLING_LEASE_CODE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder billingLeaseCode(String billingLeaseCode) {
            obj.setBillingLeaseCode(billingLeaseCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column BILLING_LEASE_T.OWNER_ID
         *
         * @param ownerId the value for BILLING_LEASE_T.OWNER_ID
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
         * This method sets the value of the database column BILLING_LEASE_T.OWNER_CODE
         *
         * @param ownerCode the value for BILLING_LEASE_T.OWNER_CODE
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
         * This method sets the value of the database column BILLING_LEASE_T.SOURCE_BILL_NUMBER
         *
         * @param sourceBillNumber the value for BILLING_LEASE_T.SOURCE_BILL_NUMBER
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
         * This method sets the value of the database column BILLING_LEASE_T.SKU_ID
         *
         * @param skuId the value for BILLING_LEASE_T.SKU_ID
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
         * This method sets the value of the database column BILLING_LEASE_T.SKU_CODE
         *
         * @param skuCode the value for BILLING_LEASE_T.SKU_CODE
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
         * This method sets the value of the database column BILLING_LEASE_T.PACK_ID
         *
         * @param packId the value for BILLING_LEASE_T.PACK_ID
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
         * This method sets the value of the database column BILLING_LEASE_T.PACK_CODE
         *
         * @param packCode the value for BILLING_LEASE_T.PACK_CODE
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
         * This method sets the value of the database column BILLING_LEASE_T.UOM
         *
         * @param uom the value for BILLING_LEASE_T.UOM
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
         * This method sets the value of the database column BILLING_LEASE_T.LOT_ID
         *
         * @param lotId the value for BILLING_LEASE_T.LOT_ID
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
         * This method sets the value of the database column BILLING_LEASE_T.LOT_NUMBER
         *
         * @param lotNumber the value for BILLING_LEASE_T.LOT_NUMBER
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
         * This method sets the value of the database column BILLING_LEASE_T.INBOUND_DATE
         *
         * @param inboundDate the value for BILLING_LEASE_T.INBOUND_DATE
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
         * This method sets the value of the database column BILLING_LEASE_T.QUANTITY_RECEIVED
         *
         * @param quantityReceived the value for BILLING_LEASE_T.QUANTITY_RECEIVED
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantityReceived(BigDecimal quantityReceived) {
            obj.setQuantityReceived(quantityReceived);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column BILLING_LEASE_T.OUTBOUND_DATE
         *
         * @param outboundDate the value for BILLING_LEASE_T.OUTBOUND_DATE
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
         * This method sets the value of the database column BILLING_LEASE_T.QUANTITY_SHIPED
         *
         * @param quantityShiped the value for BILLING_LEASE_T.QUANTITY_SHIPED
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder quantityShiped(BigDecimal quantityShiped) {
            obj.setQuantityShiped(quantityShiped);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column BILLING_LEASE_T.VOLUME
         *
         * @param volume the value for BILLING_LEASE_T.VOLUME
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
         * This method sets the value of the database column BILLING_LEASE_T.WEIGHT_GROSS
         *
         * @param weightGross the value for BILLING_LEASE_T.WEIGHT_GROSS
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
         * This method sets the value of the database column BILLING_LEASE_T.WEIGHT_NET
         *
         * @param weightNet the value for BILLING_LEASE_T.WEIGHT_NET
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
         * This method sets the value of the database column BILLING_LEASE_T.WEIGHT_TARE
         *
         * @param weightTare the value for BILLING_LEASE_T.WEIGHT_TARE
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
         * This method sets the value of the database column BILLING_LEASE_T.DAYS
         *
         * @param days the value for BILLING_LEASE_T.DAYS
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder days(Long days) {
            obj.setDays(days);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column BILLING_LEASE_T.STATUS
         *
         * @param status the value for BILLING_LEASE_T.STATUS
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
         * This method sets the value of the database column BILLING_LEASE_T.REMARK
         *
         * @param remark the value for BILLING_LEASE_T.REMARK
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
         * This method sets the value of the database column BILLING_LEASE_T.COMPANY_ID
         *
         * @param companyId the value for BILLING_LEASE_T.COMPANY_ID
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
         * This method sets the value of the database column BILLING_LEASE_T.WAREHOUSE_ID
         *
         * @param warehouseId the value for BILLING_LEASE_T.WAREHOUSE_ID
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
         * This method sets the value of the database column BILLING_LEASE_T.DEL_FLAG
         *
         * @param delFlag the value for BILLING_LEASE_T.DEL_FLAG
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
         * This method sets the value of the database column BILLING_LEASE_T.CREATE_BY
         *
         * @param createBy the value for BILLING_LEASE_T.CREATE_BY
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
         * This method sets the value of the database column BILLING_LEASE_T.CREATE_TIME
         *
         * @param createTime the value for BILLING_LEASE_T.CREATE_TIME
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
         * This method sets the value of the database column BILLING_LEASE_T.UPDATE_BY
         *
         * @param updateBy the value for BILLING_LEASE_T.UPDATE_BY
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
         * This method sets the value of the database column BILLING_LEASE_T.UPDATE_TIME
         *
         * @param updateTime the value for BILLING_LEASE_T.UPDATE_TIME
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
         * This method sets the value of the database column BILLING_LEASE_T.UPDATE_VERSION
         *
         * @param updateVersion the value for BILLING_LEASE_T.UPDATE_VERSION
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
         * This method sets the value of the database column BILLING_LEASE_T.DESCRIPTION
         *
         * @param description the value for BILLING_LEASE_T.DESCRIPTION
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
         * This method sets the value of the database column BILLING_LEASE_T.REVENUE_TON
         *
         * @param revenueTon the value for BILLING_LEASE_T.REVENUE_TON
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder revenueTon(BigDecimal revenueTon) {
            obj.setRevenueTon(revenueTon);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public BillingLeaseTEntity build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table BILLING_LEASE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum DelFlag {
        NOT_DELETED(new String("N"), "未删除"),
        IS_DELETED(new String("Y"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table BILLING_LEASE_T
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
         * This method corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table BILLING_LEASE_T
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
     * This enum corresponds to the database table BILLING_LEASE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        billingLeaseId("BILLING_LEASE_ID", "billingLeaseId", "DECIMAL", false),
        billingDate("BILLING_DATE", "billingDate", "TIMESTAMP", false),
        billingMonth("BILLING_MONTH", "billingMonth", "VARCHAR", false),
        billingLeaseCode("BILLING_LEASE_CODE", "billingLeaseCode", "VARCHAR", false),
        ownerId("OWNER_ID", "ownerId", "DECIMAL", false),
        ownerCode("OWNER_CODE", "ownerCode", "VARCHAR", false),
        sourceBillNumber("SOURCE_BILL_NUMBER", "sourceBillNumber", "VARCHAR", false),
        skuId("SKU_ID", "skuId", "DECIMAL", false),
        skuCode("SKU_CODE", "skuCode", "VARCHAR", false),
        packId("PACK_ID", "packId", "DECIMAL", false),
        packCode("PACK_CODE", "packCode", "VARCHAR", false),
        uom("UOM", "uom", "VARCHAR", false),
        lotId("LOT_ID", "lotId", "DECIMAL", false),
        lotNumber("LOT_NUMBER", "lotNumber", "VARCHAR", false),
        inboundDate("INBOUND_DATE", "inboundDate", "TIMESTAMP", false),
        quantityReceived("QUANTITY_RECEIVED", "quantityReceived", "DECIMAL", false),
        outboundDate("OUTBOUND_DATE", "outboundDate", "TIMESTAMP", false),
        quantityShiped("QUANTITY_SHIPED", "quantityShiped", "DECIMAL", false),
        volume("VOLUME", "volume", "DECIMAL", false),
        weightGross("WEIGHT_GROSS", "weightGross", "DECIMAL", false),
        weightNet("WEIGHT_NET", "weightNet", "DECIMAL", false),
        weightTare("WEIGHT_TARE", "weightTare", "DECIMAL", false),
        days("DAYS", "days", "DECIMAL", true),
        status("STATUS", "status", "VARCHAR", true),
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
        revenueTon("REVENUE_TON", "revenueTon", "DECIMAL", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table BILLING_LEASE_T
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
         * This method corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table BILLING_LEASE_T
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
         * This method corresponds to the database table BILLING_LEASE_T
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
         * This method corresponds to the database table BILLING_LEASE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}