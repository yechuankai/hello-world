package com.wms.entity.auto;

import com.wms.entity.BaseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class LotAttributeTEntity extends BaseEntity {
    private Long lotAttributeId;

    private String lotNumber;

    private Long ownerId;

    private String ownerCode;

    private Long skuId;

    private String skuCode;

    private String skuAlias;

    private String lotAttribute1;

    private String lotAttribute2;

    private String lotAttribute3;

    private Date lotAttribute4;

    private Date lotAttribute5;

    private String lotAttribute6;

    private String lotAttribute7;

    private String lotAttribute8;

    private String lotAttribute9;

    private String lotAttribute10;

    private Date lotAttribute11;

    private Date lotAttribute12;

    private String md5;

    private Long companyId;

    private Long warehouseId;

    private String delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Long updateVersion;

    private String description;

    public Long getLotAttributeId() {
        return lotAttributeId;
    }

    public void setLotAttributeId(Long lotAttributeId) {
        this.lotAttributeId = lotAttributeId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber == null ? null : lotNumber.trim();
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

    public Date getLotAttribute4() {
        return lotAttribute4;
    }

    public void setLotAttribute4(Date lotAttribute4) {
        this.lotAttribute4 = lotAttribute4;
    }

    public Date getLotAttribute5() {
        return lotAttribute5;
    }

    public void setLotAttribute5(Date lotAttribute5) {
        this.lotAttribute5 = lotAttribute5;
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

    public Date getLotAttribute11() {
        return lotAttribute11;
    }

    public void setLotAttribute11(Date lotAttribute11) {
        this.lotAttribute11 = lotAttribute11;
    }

    public Date getLotAttribute12() {
        return lotAttribute12;
    }

    public void setLotAttribute12(Date lotAttribute12) {
        this.lotAttribute12 = lotAttribute12;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
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
     * This method corresponds to the database table LOT_ATTRIBUTE_T
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
        sb.append(", lotAttributeId=").append(lotAttributeId);
        sb.append(", lotNumber=").append(lotNumber);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", skuId=").append(skuId);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", skuAlias=").append(skuAlias);
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
        sb.append(", md5=").append(md5);
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
        LotAttributeTEntity other = (LotAttributeTEntity) that;
        return (this.getLotAttributeId() == null ? other.getLotAttributeId() == null : this.getLotAttributeId().equals(other.getLotAttributeId()))
            && (this.getLotNumber() == null ? other.getLotNumber() == null : this.getLotNumber().equals(other.getLotNumber()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getOwnerCode() == null ? other.getOwnerCode() == null : this.getOwnerCode().equals(other.getOwnerCode()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getSkuCode() == null ? other.getSkuCode() == null : this.getSkuCode().equals(other.getSkuCode()))
            && (this.getSkuAlias() == null ? other.getSkuAlias() == null : this.getSkuAlias().equals(other.getSkuAlias()))
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
            && (this.getMd5() == null ? other.getMd5() == null : this.getMd5().equals(other.getMd5()))
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
        result = prime * result + ((getLotAttributeId() == null) ? 0 : getLotAttributeId().hashCode());
        result = prime * result + ((getLotNumber() == null) ? 0 : getLotNumber().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getOwnerCode() == null) ? 0 : getOwnerCode().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getSkuCode() == null) ? 0 : getSkuCode().hashCode());
        result = prime * result + ((getSkuAlias() == null) ? 0 : getSkuAlias().hashCode());
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
        result = prime * result + ((getMd5() == null) ? 0 : getMd5().hashCode());
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
     * This method corresponds to the database table LOT_ATTRIBUTE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static LotAttributeTEntity.Builder builder() {
        return new LotAttributeTEntity.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table LOT_ATTRIBUTE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private LotAttributeTEntity obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new LotAttributeTEntity();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_ATTRIBUTE_ID
         *
         * @param lotAttributeId the value for LOT_ATTRIBUTE_T.LOT_ATTRIBUTE_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttributeId(Long lotAttributeId) {
            obj.setLotAttributeId(lotAttributeId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_NUMBER
         *
         * @param lotNumber the value for LOT_ATTRIBUTE_T.LOT_NUMBER
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.OWNER_ID
         *
         * @param ownerId the value for LOT_ATTRIBUTE_T.OWNER_ID
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.OWNER_CODE
         *
         * @param ownerCode the value for LOT_ATTRIBUTE_T.OWNER_CODE
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.SKU_ID
         *
         * @param skuId the value for LOT_ATTRIBUTE_T.SKU_ID
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.SKU_CODE
         *
         * @param skuCode the value for LOT_ATTRIBUTE_T.SKU_CODE
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.SKU_ALIAS
         *
         * @param skuAlias the value for LOT_ATTRIBUTE_T.SKU_ALIAS
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_ATTRIBUTE1
         *
         * @param lotAttribute1 the value for LOT_ATTRIBUTE_T.LOT_ATTRIBUTE1
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_ATTRIBUTE12
         *
         * @param lotAttribute12 the value for LOT_ATTRIBUTE_T.LOT_ATTRIBUTE12
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute12(Date lotAttribute12) {
            obj.setLotAttribute12(lotAttribute12);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_ATTRIBUTE11
         *
         * @param lotAttribute11 the value for LOT_ATTRIBUTE_T.LOT_ATTRIBUTE11
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute11(Date lotAttribute11) {
            obj.setLotAttribute11(lotAttribute11);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_ATTRIBUTE10
         *
         * @param lotAttribute10 the value for LOT_ATTRIBUTE_T.LOT_ATTRIBUTE10
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_ATTRIBUTE2
         *
         * @param lotAttribute2 the value for LOT_ATTRIBUTE_T.LOT_ATTRIBUTE2
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_ATTRIBUTE3
         *
         * @param lotAttribute3 the value for LOT_ATTRIBUTE_T.LOT_ATTRIBUTE3
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_ATTRIBUTE4
         *
         * @param lotAttribute4 the value for LOT_ATTRIBUTE_T.LOT_ATTRIBUTE4
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute4(Date lotAttribute4) {
            obj.setLotAttribute4(lotAttribute4);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_ATTRIBUTE5
         *
         * @param lotAttribute5 the value for LOT_ATTRIBUTE_T.LOT_ATTRIBUTE5
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder lotAttribute5(Date lotAttribute5) {
            obj.setLotAttribute5(lotAttribute5);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_ATTRIBUTE6
         *
         * @param lotAttribute6 the value for LOT_ATTRIBUTE_T.LOT_ATTRIBUTE6
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_ATTRIBUTE7
         *
         * @param lotAttribute7 the value for LOT_ATTRIBUTE_T.LOT_ATTRIBUTE7
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_ATTRIBUTE8
         *
         * @param lotAttribute8 the value for LOT_ATTRIBUTE_T.LOT_ATTRIBUTE8
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.LOT_ATTRIBUTE9
         *
         * @param lotAttribute9 the value for LOT_ATTRIBUTE_T.LOT_ATTRIBUTE9
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.MD5
         *
         * @param md5 the value for LOT_ATTRIBUTE_T.MD5
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder md5(String md5) {
            obj.setMd5(md5);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column LOT_ATTRIBUTE_T.COMPANY_ID
         *
         * @param companyId the value for LOT_ATTRIBUTE_T.COMPANY_ID
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.WAREHOUSE_ID
         *
         * @param warehouseId the value for LOT_ATTRIBUTE_T.WAREHOUSE_ID
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.DEL_FLAG
         *
         * @param delFlag the value for LOT_ATTRIBUTE_T.DEL_FLAG
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.CREATE_BY
         *
         * @param createBy the value for LOT_ATTRIBUTE_T.CREATE_BY
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.CREATE_TIME
         *
         * @param createTime the value for LOT_ATTRIBUTE_T.CREATE_TIME
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.UPDATE_BY
         *
         * @param updateBy the value for LOT_ATTRIBUTE_T.UPDATE_BY
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.UPDATE_TIME
         *
         * @param updateTime the value for LOT_ATTRIBUTE_T.UPDATE_TIME
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.UPDATE_VERSION
         *
         * @param updateVersion the value for LOT_ATTRIBUTE_T.UPDATE_VERSION
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
         * This method sets the value of the database column LOT_ATTRIBUTE_T.DESCRIPTION
         *
         * @param description the value for LOT_ATTRIBUTE_T.DESCRIPTION
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
         * This method corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public LotAttributeTEntity build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table LOT_ATTRIBUTE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum DelFlag {
        NOT_DELETED(new String("N"), "未删除"),
        IS_DELETED(new String("Y"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table LOT_ATTRIBUTE_T
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
         * This method corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table LOT_ATTRIBUTE_T
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
     * This enum corresponds to the database table LOT_ATTRIBUTE_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        lotAttributeId("LOT_ATTRIBUTE_ID", "lotAttributeId", "DECIMAL", false),
        lotNumber("LOT_NUMBER", "lotNumber", "VARCHAR", false),
        ownerId("OWNER_ID", "ownerId", "DECIMAL", false),
        ownerCode("OWNER_CODE", "ownerCode", "VARCHAR", false),
        skuId("SKU_ID", "skuId", "DECIMAL", false),
        skuCode("SKU_CODE", "skuCode", "VARCHAR", false),
        skuAlias("SKU_ALIAS", "skuAlias", "VARCHAR", false),
        lotAttribute1("LOT_ATTRIBUTE1", "lotAttribute1", "VARCHAR", false),
        lotAttribute2("LOT_ATTRIBUTE2", "lotAttribute2", "VARCHAR", false),
        lotAttribute3("LOT_ATTRIBUTE3", "lotAttribute3", "VARCHAR", false),
        lotAttribute4("LOT_ATTRIBUTE4", "lotAttribute4", "TIMESTAMP", false),
        lotAttribute5("LOT_ATTRIBUTE5", "lotAttribute5", "TIMESTAMP", false),
        lotAttribute6("LOT_ATTRIBUTE6", "lotAttribute6", "VARCHAR", false),
        lotAttribute7("LOT_ATTRIBUTE7", "lotAttribute7", "VARCHAR", false),
        lotAttribute8("LOT_ATTRIBUTE8", "lotAttribute8", "VARCHAR", false),
        lotAttribute9("LOT_ATTRIBUTE9", "lotAttribute9", "VARCHAR", false),
        lotAttribute10("LOT_ATTRIBUTE10", "lotAttribute10", "VARCHAR", false),
        lotAttribute11("LOT_ATTRIBUTE11", "lotAttribute11", "TIMESTAMP", false),
        lotAttribute12("LOT_ATTRIBUTE12", "lotAttribute12", "TIMESTAMP", false),
        md5("MD5", "md5", "VARCHAR", false),
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
         * This field corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table LOT_ATTRIBUTE_T
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
         * This method corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table LOT_ATTRIBUTE_T
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
         * This method corresponds to the database table LOT_ATTRIBUTE_T
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
         * This method corresponds to the database table LOT_ATTRIBUTE_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}