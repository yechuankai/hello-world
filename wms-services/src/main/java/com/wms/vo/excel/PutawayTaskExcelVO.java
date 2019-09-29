package com.wms.vo.excel;

import java.math.BigDecimal;
import java.util.Date;

import com.wms.common.core.domain.AbstractExcelModel;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class PutawayTaskExcelVO  extends AbstractExcelModel{

	@Excel(name = "taskDetailNumber")
    private String taskDetailNumber;

	@Excel(name = "taskType")
    private String taskType;

	@Excel(name = "ownerCode")
    private String ownerCode;

	@Excel(name = "skuCode")
    private String skuCode;

	@Excel(name = "lotNumber")
    private String lotNumber;
    
	@Excel(name = "quantity")
    private BigDecimal quantity;

	@Excel(name = "fromLpnNumber")
    private String fromLpnNumber;

	@Excel(name = "fromLocationCode")
    private String fromLocationCode;
    
	@Excel(name = "fromLocationLogical")
    private String fromLocationLogical;

	@Excel(name = "fromZoneCode")
    private String fromZoneCode;

	@Excel(name = "toLpnNumber")
    private String toLpnNumber;

	@Excel(name = "toLocationCode")
    private String toLocationCode;
	
	@Excel(name = "toLocationLogical")
    private String toLocationLogical;

	@Excel(name = "toZoneCode")
    private String toZoneCode;

	@Excel(name = "userName")
    private String userName;

	@Excel(name = "releaseTime")
    private Date releaseTime;

	@Excel(name = "completeTime")
    private Date completeTime;

	@Excel(name = "startTime")
    private Date startTime;

	@Excel(name = "endTime")
    private Date endTime;

	@Excel(name = "sourceLineNumber")
    private Long sourceLineNumber;

	@Excel(name = "sourceBillNumber")
    private String sourceBillNumber;

	@Excel(name = "status")
    private String status;

	public String getTaskDetailNumber() {
		return taskDetailNumber;
	}

	public void setTaskDetailNumber(String taskDetailNumber) {
		this.taskDetailNumber = taskDetailNumber;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getFromLpnNumber() {
		return fromLpnNumber;
	}

	public void setFromLpnNumber(String fromLpnNumber) {
		this.fromLpnNumber = fromLpnNumber;
	}

	public String getFromLocationCode() {
		return fromLocationCode;
	}

	public void setFromLocationCode(String fromLocationCode) {
		this.fromLocationCode = fromLocationCode;
	}

	public String getFromLocationLogical() {
		return fromLocationLogical;
	}

	public void setFromLocationLogical(String fromLocationLogical) {
		this.fromLocationLogical = fromLocationLogical;
	}

	public String getFromZoneCode() {
		return fromZoneCode;
	}

	public void setFromZoneCode(String fromZoneCode) {
		this.fromZoneCode = fromZoneCode;
	}

	public String getToLpnNumber() {
		return toLpnNumber;
	}

	public void setToLpnNumber(String toLpnNumber) {
		this.toLpnNumber = toLpnNumber;
	}

	public String getToLocationCode() {
		return toLocationCode;
	}

	public void setToLocationCode(String toLocationCode) {
		this.toLocationCode = toLocationCode;
	}

	public String getToLocationLogical() {
		return toLocationLogical;
	}

	public void setToLocationLogical(String toLocationLogical) {
		this.toLocationLogical = toLocationLogical;
	}

	public String getToZoneCode() {
		return toZoneCode;
	}

	public void setToZoneCode(String toZoneCode) {
		this.toZoneCode = toZoneCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
		this.sourceBillNumber = sourceBillNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
