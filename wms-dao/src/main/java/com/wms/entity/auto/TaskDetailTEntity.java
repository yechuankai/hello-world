package com.wms.entity.auto;

import com.wms.entity.BaseEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class TaskDetailTEntity extends BaseEntity {
    private Long taskDetailId;

    private String taskDetailNumber;

    private String taskType;

    private String sourceType;

    private Long ownerId;

    private String ownerCode;

    private Long skuId;

    private String skuCode;

    private String uom;

    private Long packId;

    private String packCode;

    private String lotNumber;

    private String fromLpnNumber;

    private String fromLpnType;

    private String fromLocationCode;

    private String fromZoneCode;

    private String toLpnNumber;

    private String toLocationCode;

    private String toZoneCode;

    private String fromLocationLogical;

    private String toLocationLogical;

    private String userName;

    private Date releaseTime;

    private Date completeTime;

    private Date startTime;

    private Date endTime;

    private BigDecimal quantity;

    private String reason;

    private Long sourceNumber;

    private Long sourceLineNumber;

    private String sourceBillNumber;

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

    private BigDecimal volume;

    private BigDecimal weightGross;

    private BigDecimal weightNet;

    private BigDecimal weightTare;

    private String userCompany;

    public Long getTaskDetailId() {
        return taskDetailId;
    }

    public void setTaskDetailId(Long taskDetailId) {
        this.taskDetailId = taskDetailId;
    }

    public String getTaskDetailNumber() {
        return taskDetailNumber;
    }

    public void setTaskDetailNumber(String taskDetailNumber) {
        this.taskDetailNumber = taskDetailNumber == null ? null : taskDetailNumber.trim();
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
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

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom == null ? null : uom.trim();
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

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber == null ? null : lotNumber.trim();
    }

    public String getFromLpnNumber() {
        return fromLpnNumber;
    }

    public void setFromLpnNumber(String fromLpnNumber) {
        this.fromLpnNumber = fromLpnNumber == null ? null : fromLpnNumber.trim();
    }

    public String getFromLpnType() {
        return fromLpnType;
    }

    public void setFromLpnType(String fromLpnType) {
        this.fromLpnType = fromLpnType == null ? null : fromLpnType.trim();
    }

    public String getFromLocationCode() {
        return fromLocationCode;
    }

    public void setFromLocationCode(String fromLocationCode) {
        this.fromLocationCode = fromLocationCode == null ? null : fromLocationCode.trim();
    }

    public String getFromZoneCode() {
        return fromZoneCode;
    }

    public void setFromZoneCode(String fromZoneCode) {
        this.fromZoneCode = fromZoneCode == null ? null : fromZoneCode.trim();
    }

    public String getToLpnNumber() {
        return toLpnNumber;
    }

    public void setToLpnNumber(String toLpnNumber) {
        this.toLpnNumber = toLpnNumber == null ? null : toLpnNumber.trim();
    }

    public String getToLocationCode() {
        return toLocationCode;
    }

    public void setToLocationCode(String toLocationCode) {
        this.toLocationCode = toLocationCode == null ? null : toLocationCode.trim();
    }

    public String getToZoneCode() {
        return toZoneCode;
    }

    public void setToZoneCode(String toZoneCode) {
        this.toZoneCode = toZoneCode == null ? null : toZoneCode.trim();
    }

    public String getFromLocationLogical() {
        return fromLocationLogical;
    }

    public void setFromLocationLogical(String fromLocationLogical) {
        this.fromLocationLogical = fromLocationLogical == null ? null : fromLocationLogical.trim();
    }

    public String getToLocationLogical() {
        return toLocationLogical;
    }

    public void setToLocationLogical(String toLocationLogical) {
        this.toLocationLogical = toLocationLogical == null ? null : toLocationLogical.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
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
     * This method corresponds to the database table TASK_DETAIL_T
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

    public String getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(String userCompany) {
        this.userCompany = userCompany == null ? null : userCompany.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskDetailId=").append(taskDetailId);
        sb.append(", taskDetailNumber=").append(taskDetailNumber);
        sb.append(", taskType=").append(taskType);
        sb.append(", sourceType=").append(sourceType);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", skuId=").append(skuId);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", uom=").append(uom);
        sb.append(", packId=").append(packId);
        sb.append(", packCode=").append(packCode);
        sb.append(", lotNumber=").append(lotNumber);
        sb.append(", fromLpnNumber=").append(fromLpnNumber);
        sb.append(", fromLpnType=").append(fromLpnType);
        sb.append(", fromLocationCode=").append(fromLocationCode);
        sb.append(", fromZoneCode=").append(fromZoneCode);
        sb.append(", toLpnNumber=").append(toLpnNumber);
        sb.append(", toLocationCode=").append(toLocationCode);
        sb.append(", toZoneCode=").append(toZoneCode);
        sb.append(", fromLocationLogical=").append(fromLocationLogical);
        sb.append(", toLocationLogical=").append(toLocationLogical);
        sb.append(", userName=").append(userName);
        sb.append(", releaseTime=").append(releaseTime);
        sb.append(", completeTime=").append(completeTime);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", quantity=").append(quantity);
        sb.append(", reason=").append(reason);
        sb.append(", sourceNumber=").append(sourceNumber);
        sb.append(", sourceLineNumber=").append(sourceLineNumber);
        sb.append(", sourceBillNumber=").append(sourceBillNumber);
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
        sb.append(", volume=").append(volume);
        sb.append(", weightGross=").append(weightGross);
        sb.append(", weightNet=").append(weightNet);
        sb.append(", weightTare=").append(weightTare);
        sb.append(", userCompany=").append(userCompany);
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
        TaskDetailTEntity other = (TaskDetailTEntity) that;
        return (this.getTaskDetailId() == null ? other.getTaskDetailId() == null : this.getTaskDetailId().equals(other.getTaskDetailId()))
            && (this.getTaskDetailNumber() == null ? other.getTaskDetailNumber() == null : this.getTaskDetailNumber().equals(other.getTaskDetailNumber()))
            && (this.getTaskType() == null ? other.getTaskType() == null : this.getTaskType().equals(other.getTaskType()))
            && (this.getSourceType() == null ? other.getSourceType() == null : this.getSourceType().equals(other.getSourceType()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getOwnerCode() == null ? other.getOwnerCode() == null : this.getOwnerCode().equals(other.getOwnerCode()))
            && (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getSkuCode() == null ? other.getSkuCode() == null : this.getSkuCode().equals(other.getSkuCode()))
            && (this.getUom() == null ? other.getUom() == null : this.getUom().equals(other.getUom()))
            && (this.getPackId() == null ? other.getPackId() == null : this.getPackId().equals(other.getPackId()))
            && (this.getPackCode() == null ? other.getPackCode() == null : this.getPackCode().equals(other.getPackCode()))
            && (this.getLotNumber() == null ? other.getLotNumber() == null : this.getLotNumber().equals(other.getLotNumber()))
            && (this.getFromLpnNumber() == null ? other.getFromLpnNumber() == null : this.getFromLpnNumber().equals(other.getFromLpnNumber()))
            && (this.getFromLpnType() == null ? other.getFromLpnType() == null : this.getFromLpnType().equals(other.getFromLpnType()))
            && (this.getFromLocationCode() == null ? other.getFromLocationCode() == null : this.getFromLocationCode().equals(other.getFromLocationCode()))
            && (this.getFromZoneCode() == null ? other.getFromZoneCode() == null : this.getFromZoneCode().equals(other.getFromZoneCode()))
            && (this.getToLpnNumber() == null ? other.getToLpnNumber() == null : this.getToLpnNumber().equals(other.getToLpnNumber()))
            && (this.getToLocationCode() == null ? other.getToLocationCode() == null : this.getToLocationCode().equals(other.getToLocationCode()))
            && (this.getToZoneCode() == null ? other.getToZoneCode() == null : this.getToZoneCode().equals(other.getToZoneCode()))
            && (this.getFromLocationLogical() == null ? other.getFromLocationLogical() == null : this.getFromLocationLogical().equals(other.getFromLocationLogical()))
            && (this.getToLocationLogical() == null ? other.getToLocationLogical() == null : this.getToLocationLogical().equals(other.getToLocationLogical()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getReleaseTime() == null ? other.getReleaseTime() == null : this.getReleaseTime().equals(other.getReleaseTime()))
            && (this.getCompleteTime() == null ? other.getCompleteTime() == null : this.getCompleteTime().equals(other.getCompleteTime()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getSourceNumber() == null ? other.getSourceNumber() == null : this.getSourceNumber().equals(other.getSourceNumber()))
            && (this.getSourceLineNumber() == null ? other.getSourceLineNumber() == null : this.getSourceLineNumber().equals(other.getSourceLineNumber()))
            && (this.getSourceBillNumber() == null ? other.getSourceBillNumber() == null : this.getSourceBillNumber().equals(other.getSourceBillNumber()))
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
            && (this.getVolume() == null ? other.getVolume() == null : this.getVolume().equals(other.getVolume()))
            && (this.getWeightGross() == null ? other.getWeightGross() == null : this.getWeightGross().equals(other.getWeightGross()))
            && (this.getWeightNet() == null ? other.getWeightNet() == null : this.getWeightNet().equals(other.getWeightNet()))
            && (this.getWeightTare() == null ? other.getWeightTare() == null : this.getWeightTare().equals(other.getWeightTare()))
            && (this.getUserCompany() == null ? other.getUserCompany() == null : this.getUserCompany().equals(other.getUserCompany()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskDetailId() == null) ? 0 : getTaskDetailId().hashCode());
        result = prime * result + ((getTaskDetailNumber() == null) ? 0 : getTaskDetailNumber().hashCode());
        result = prime * result + ((getTaskType() == null) ? 0 : getTaskType().hashCode());
        result = prime * result + ((getSourceType() == null) ? 0 : getSourceType().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getOwnerCode() == null) ? 0 : getOwnerCode().hashCode());
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getSkuCode() == null) ? 0 : getSkuCode().hashCode());
        result = prime * result + ((getUom() == null) ? 0 : getUom().hashCode());
        result = prime * result + ((getPackId() == null) ? 0 : getPackId().hashCode());
        result = prime * result + ((getPackCode() == null) ? 0 : getPackCode().hashCode());
        result = prime * result + ((getLotNumber() == null) ? 0 : getLotNumber().hashCode());
        result = prime * result + ((getFromLpnNumber() == null) ? 0 : getFromLpnNumber().hashCode());
        result = prime * result + ((getFromLpnType() == null) ? 0 : getFromLpnType().hashCode());
        result = prime * result + ((getFromLocationCode() == null) ? 0 : getFromLocationCode().hashCode());
        result = prime * result + ((getFromZoneCode() == null) ? 0 : getFromZoneCode().hashCode());
        result = prime * result + ((getToLpnNumber() == null) ? 0 : getToLpnNumber().hashCode());
        result = prime * result + ((getToLocationCode() == null) ? 0 : getToLocationCode().hashCode());
        result = prime * result + ((getToZoneCode() == null) ? 0 : getToZoneCode().hashCode());
        result = prime * result + ((getFromLocationLogical() == null) ? 0 : getFromLocationLogical().hashCode());
        result = prime * result + ((getToLocationLogical() == null) ? 0 : getToLocationLogical().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getReleaseTime() == null) ? 0 : getReleaseTime().hashCode());
        result = prime * result + ((getCompleteTime() == null) ? 0 : getCompleteTime().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getSourceNumber() == null) ? 0 : getSourceNumber().hashCode());
        result = prime * result + ((getSourceLineNumber() == null) ? 0 : getSourceLineNumber().hashCode());
        result = prime * result + ((getSourceBillNumber() == null) ? 0 : getSourceBillNumber().hashCode());
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
        result = prime * result + ((getVolume() == null) ? 0 : getVolume().hashCode());
        result = prime * result + ((getWeightGross() == null) ? 0 : getWeightGross().hashCode());
        result = prime * result + ((getWeightNet() == null) ? 0 : getWeightNet().hashCode());
        result = prime * result + ((getWeightTare() == null) ? 0 : getWeightTare().hashCode());
        result = prime * result + ((getUserCompany() == null) ? 0 : getUserCompany().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TASK_DETAIL_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static TaskDetailTEntity.Builder builder() {
        return new TaskDetailTEntity.Builder();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TASK_DETAIL_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private TaskDetailTEntity obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new TaskDetailTEntity();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column TASK_DETAIL_T.TASK_DETAIL_ID
         *
         * @param taskDetailId the value for TASK_DETAIL_T.TASK_DETAIL_ID
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder taskDetailId(Long taskDetailId) {
            obj.setTaskDetailId(taskDetailId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column TASK_DETAIL_T.TASK_DETAIL_NUMBER
         *
         * @param taskDetailNumber the value for TASK_DETAIL_T.TASK_DETAIL_NUMBER
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder taskDetailNumber(String taskDetailNumber) {
            obj.setTaskDetailNumber(taskDetailNumber);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column TASK_DETAIL_T.TASK_TYPE
         *
         * @param taskType the value for TASK_DETAIL_T.TASK_TYPE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder taskType(String taskType) {
            obj.setTaskType(taskType);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column TASK_DETAIL_T.SOURCE_TYPE
         *
         * @param sourceType the value for TASK_DETAIL_T.SOURCE_TYPE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder sourceType(String sourceType) {
            obj.setSourceType(sourceType);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column TASK_DETAIL_T.OWNER_ID
         *
         * @param ownerId the value for TASK_DETAIL_T.OWNER_ID
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
         * This method sets the value of the database column TASK_DETAIL_T.OWNER_CODE
         *
         * @param ownerCode the value for TASK_DETAIL_T.OWNER_CODE
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
         * This method sets the value of the database column TASK_DETAIL_T.SKU_ID
         *
         * @param skuId the value for TASK_DETAIL_T.SKU_ID
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
         * This method sets the value of the database column TASK_DETAIL_T.SKU_CODE
         *
         * @param skuCode the value for TASK_DETAIL_T.SKU_CODE
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
         * This method sets the value of the database column TASK_DETAIL_T.UOM
         *
         * @param uom the value for TASK_DETAIL_T.UOM
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
         * This method sets the value of the database column TASK_DETAIL_T.PACK_ID
         *
         * @param packId the value for TASK_DETAIL_T.PACK_ID
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
         * This method sets the value of the database column TASK_DETAIL_T.PACK_CODE
         *
         * @param packCode the value for TASK_DETAIL_T.PACK_CODE
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
         * This method sets the value of the database column TASK_DETAIL_T.LOT_NUMBER
         *
         * @param lotNumber the value for TASK_DETAIL_T.LOT_NUMBER
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
         * This method sets the value of the database column TASK_DETAIL_T.FROM_LPN_NUMBER
         *
         * @param fromLpnNumber the value for TASK_DETAIL_T.FROM_LPN_NUMBER
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
         * This method sets the value of the database column TASK_DETAIL_T.FROM_LPN_TYPE
         *
         * @param fromLpnType the value for TASK_DETAIL_T.FROM_LPN_TYPE
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fromLpnType(String fromLpnType) {
            obj.setFromLpnType(fromLpnType);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column TASK_DETAIL_T.FROM_LOCATION_CODE
         *
         * @param fromLocationCode the value for TASK_DETAIL_T.FROM_LOCATION_CODE
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
         * This method sets the value of the database column TASK_DETAIL_T.FROM_ZONE_CODE
         *
         * @param fromZoneCode the value for TASK_DETAIL_T.FROM_ZONE_CODE
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
         * This method sets the value of the database column TASK_DETAIL_T.TO_LPN_NUMBER
         *
         * @param toLpnNumber the value for TASK_DETAIL_T.TO_LPN_NUMBER
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
         * This method sets the value of the database column TASK_DETAIL_T.TO_LOCATION_CODE
         *
         * @param toLocationCode the value for TASK_DETAIL_T.TO_LOCATION_CODE
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
         * This method sets the value of the database column TASK_DETAIL_T.TO_ZONE_CODE
         *
         * @param toZoneCode the value for TASK_DETAIL_T.TO_ZONE_CODE
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
         * This method sets the value of the database column TASK_DETAIL_T.FROM_LOCATION_LOGICAL
         *
         * @param fromLocationLogical the value for TASK_DETAIL_T.FROM_LOCATION_LOGICAL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder fromLocationLogical(String fromLocationLogical) {
            obj.setFromLocationLogical(fromLocationLogical);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column TASK_DETAIL_T.TO_LOCATION_LOGICAL
         *
         * @param toLocationLogical the value for TASK_DETAIL_T.TO_LOCATION_LOGICAL
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder toLocationLogical(String toLocationLogical) {
            obj.setToLocationLogical(toLocationLogical);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column TASK_DETAIL_T.USER_NAME
         *
         * @param userName the value for TASK_DETAIL_T.USER_NAME
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder userName(String userName) {
            obj.setUserName(userName);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column TASK_DETAIL_T.RELEASE_TIME
         *
         * @param releaseTime the value for TASK_DETAIL_T.RELEASE_TIME
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder releaseTime(Date releaseTime) {
            obj.setReleaseTime(releaseTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column TASK_DETAIL_T.COMPLETE_TIME
         *
         * @param completeTime the value for TASK_DETAIL_T.COMPLETE_TIME
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder completeTime(Date completeTime) {
            obj.setCompleteTime(completeTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column TASK_DETAIL_T.START_TIME
         *
         * @param startTime the value for TASK_DETAIL_T.START_TIME
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder startTime(Date startTime) {
            obj.setStartTime(startTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column TASK_DETAIL_T.END_TIME
         *
         * @param endTime the value for TASK_DETAIL_T.END_TIME
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder endTime(Date endTime) {
            obj.setEndTime(endTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column TASK_DETAIL_T.QUANTITY
         *
         * @param quantity the value for TASK_DETAIL_T.QUANTITY
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
         * This method sets the value of the database column TASK_DETAIL_T.REASON
         *
         * @param reason the value for TASK_DETAIL_T.REASON
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
         * This method sets the value of the database column TASK_DETAIL_T.SOURCE_NUMBER
         *
         * @param sourceNumber the value for TASK_DETAIL_T.SOURCE_NUMBER
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
         * This method sets the value of the database column TASK_DETAIL_T.SOURCE_LINE_NUMBER
         *
         * @param sourceLineNumber the value for TASK_DETAIL_T.SOURCE_LINE_NUMBER
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
         * This method sets the value of the database column TASK_DETAIL_T.SOURCE_BILL_NUMBER
         *
         * @param sourceBillNumber the value for TASK_DETAIL_T.SOURCE_BILL_NUMBER
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
         * This method sets the value of the database column TASK_DETAIL_T.STATUS
         *
         * @param status the value for TASK_DETAIL_T.STATUS
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
         * This method sets the value of the database column TASK_DETAIL_T.REMARK
         *
         * @param remark the value for TASK_DETAIL_T.REMARK
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
         * This method sets the value of the database column TASK_DETAIL_T.COMPANY_ID
         *
         * @param companyId the value for TASK_DETAIL_T.COMPANY_ID
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
         * This method sets the value of the database column TASK_DETAIL_T.WAREHOUSE_ID
         *
         * @param warehouseId the value for TASK_DETAIL_T.WAREHOUSE_ID
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
         * This method sets the value of the database column TASK_DETAIL_T.DEL_FLAG
         *
         * @param delFlag the value for TASK_DETAIL_T.DEL_FLAG
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
         * This method sets the value of the database column TASK_DETAIL_T.CREATE_BY
         *
         * @param createBy the value for TASK_DETAIL_T.CREATE_BY
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
         * This method sets the value of the database column TASK_DETAIL_T.CREATE_TIME
         *
         * @param createTime the value for TASK_DETAIL_T.CREATE_TIME
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
         * This method sets the value of the database column TASK_DETAIL_T.UPDATE_BY
         *
         * @param updateBy the value for TASK_DETAIL_T.UPDATE_BY
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
         * This method sets the value of the database column TASK_DETAIL_T.UPDATE_TIME
         *
         * @param updateTime the value for TASK_DETAIL_T.UPDATE_TIME
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
         * This method sets the value of the database column TASK_DETAIL_T.UPDATE_VERSION
         *
         * @param updateVersion the value for TASK_DETAIL_T.UPDATE_VERSION
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
         * This method sets the value of the database column TASK_DETAIL_T.DESCRIPTION
         *
         * @param description the value for TASK_DETAIL_T.DESCRIPTION
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
         * This method sets the value of the database column TASK_DETAIL_T.VOLUME
         *
         * @param volume the value for TASK_DETAIL_T.VOLUME
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
         * This method sets the value of the database column TASK_DETAIL_T.WEIGHT_GROSS
         *
         * @param weightGross the value for TASK_DETAIL_T.WEIGHT_GROSS
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
         * This method sets the value of the database column TASK_DETAIL_T.WEIGHT_NET
         *
         * @param weightNet the value for TASK_DETAIL_T.WEIGHT_NET
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
         * This method sets the value of the database column TASK_DETAIL_T.WEIGHT_TARE
         *
         * @param weightTare the value for TASK_DETAIL_T.WEIGHT_TARE
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
         * This method sets the value of the database column TASK_DETAIL_T.USER_COMPANY
         *
         * @param userCompany the value for TASK_DETAIL_T.USER_COMPANY
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder userCompany(String userCompany) {
            obj.setUserCompany(userCompany);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public TaskDetailTEntity build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table TASK_DETAIL_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum DelFlag {
        NOT_DELETED(new String("N"), "未删除"),
        IS_DELETED(new String("Y"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table TASK_DETAIL_T
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
         * This method corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table TASK_DETAIL_T
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
     * This enum corresponds to the database table TASK_DETAIL_T
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        taskDetailId("TASK_DETAIL_ID", "taskDetailId", "DECIMAL", false),
        taskDetailNumber("TASK_DETAIL_NUMBER", "taskDetailNumber", "VARCHAR", false),
        taskType("TASK_TYPE", "taskType", "VARCHAR", false),
        sourceType("SOURCE_TYPE", "sourceType", "VARCHAR", false),
        ownerId("OWNER_ID", "ownerId", "DECIMAL", false),
        ownerCode("OWNER_CODE", "ownerCode", "VARCHAR", false),
        skuId("SKU_ID", "skuId", "DECIMAL", false),
        skuCode("SKU_CODE", "skuCode", "VARCHAR", false),
        uom("UOM", "uom", "VARCHAR", false),
        packId("PACK_ID", "packId", "DECIMAL", false),
        packCode("PACK_CODE", "packCode", "VARCHAR", false),
        lotNumber("LOT_NUMBER", "lotNumber", "VARCHAR", false),
        fromLpnNumber("FROM_LPN_NUMBER", "fromLpnNumber", "VARCHAR", false),
        fromLpnType("FROM_LPN_TYPE", "fromLpnType", "VARCHAR", false),
        fromLocationCode("FROM_LOCATION_CODE", "fromLocationCode", "VARCHAR", false),
        fromZoneCode("FROM_ZONE_CODE", "fromZoneCode", "VARCHAR", false),
        toLpnNumber("TO_LPN_NUMBER", "toLpnNumber", "VARCHAR", false),
        toLocationCode("TO_LOCATION_CODE", "toLocationCode", "VARCHAR", false),
        toZoneCode("TO_ZONE_CODE", "toZoneCode", "VARCHAR", false),
        fromLocationLogical("FROM_LOCATION_LOGICAL", "fromLocationLogical", "VARCHAR", false),
        toLocationLogical("TO_LOCATION_LOGICAL", "toLocationLogical", "VARCHAR", false),
        userName("USER_NAME", "userName", "VARCHAR", false),
        releaseTime("RELEASE_TIME", "releaseTime", "TIMESTAMP", false),
        completeTime("COMPLETE_TIME", "completeTime", "TIMESTAMP", false),
        startTime("START_TIME", "startTime", "TIMESTAMP", false),
        endTime("END_TIME", "endTime", "TIMESTAMP", false),
        quantity("QUANTITY", "quantity", "DECIMAL", false),
        reason("REASON", "reason", "VARCHAR", false),
        sourceNumber("SOURCE_NUMBER", "sourceNumber", "DECIMAL", false),
        sourceLineNumber("SOURCE_LINE_NUMBER", "sourceLineNumber", "DECIMAL", false),
        sourceBillNumber("SOURCE_BILL_NUMBER", "sourceBillNumber", "VARCHAR", false),
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
        volume("VOLUME", "volume", "DECIMAL", false),
        weightGross("WEIGHT_GROSS", "weightGross", "DECIMAL", false),
        weightNet("WEIGHT_NET", "weightNet", "DECIMAL", false),
        weightTare("WEIGHT_TARE", "weightTare", "DECIMAL", false),
        userCompany("USER_COMPANY", "userCompany", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table TASK_DETAIL_T
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
         * This method corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table TASK_DETAIL_T
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
         * This method corresponds to the database table TASK_DETAIL_T
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
         * This method corresponds to the database table TASK_DETAIL_T
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}