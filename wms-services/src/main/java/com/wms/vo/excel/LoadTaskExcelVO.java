package com.wms.vo.excel;

import java.math.BigDecimal;
import java.util.Date;

import com.wms.common.core.domain.AbstractExcelModel;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class LoadTaskExcelVO  extends AbstractExcelModel{

	@Excel(name = "taskDetailNumber")
    private String taskDetailNumber;
	
	@Excel(name = "sourceBillNumber")
    private String sourceBillNumber;

	@Excel(name = "taskType")
    private String taskType;

	@Excel(name = "ownerCode")
    private String ownerCode;


	@Excel(name = "carNumber")
    private String carNumber;

	@Excel(name = "containerNumber")
    private String containerNumber;
    
	@Excel(name = "platFormCode")
    private String platFormCode;

	@Excel(name = "volume")
    private BigDecimal volume;

	@Excel(name = "weightGross")
    private BigDecimal weightGross;

	@Excel(name = "userName")
    private String userName;
	
	@Excel(name = "userCompany")
    private String userCompany;
	
	@Excel(name = "abnormal")
    private String reason;

	@Excel(name = "releaseTime")
    private Date releaseTime;

	@Excel(name = "completeTime")
    private Date completeTime;

	@Excel(name = "startTime")
    private Date startTime;

	@Excel(name = "endTime")
    private Date endTime;

	@Excel(name = "status")
    private String status;

	public String getTaskDetailNumber() {
		return taskDetailNumber;
	}

	public void setTaskDetailNumber(String taskDetailNumber) {
		this.taskDetailNumber = taskDetailNumber;
	}

	public String getSourceBillNumber() {
		return sourceBillNumber;
	}

	public void setSourceBillNumber(String sourceBillNumber) {
		this.sourceBillNumber = sourceBillNumber;
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

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getContainerNumber() {
		return containerNumber;
	}

	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}

	public String getPlatFormCode() {
		return platFormCode;
	}

	public void setPlatFormCode(String platFormCode) {
		this.platFormCode = platFormCode;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
