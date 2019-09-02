package com.wms.common.core.domain;

public abstract class AbstractExcelModel implements IExcelModel {

	private String success;
	
	private String processMessage;
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getProcessMessage() {
		return processMessage;
	}

	public void setProcessMessage(String processMessage) {
		this.processMessage = processMessage;
	}
	
	@Override
	public String success() {
		return success;
	}
	
	@Override
	public String processMessage() {
		return processMessage;
	}
	
}
