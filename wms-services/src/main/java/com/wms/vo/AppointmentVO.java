package com.wms.vo;

import com.wms.entity.auto.AppointmentTEntity;

public class AppointmentVO extends AppointmentTEntity {
	
	private String sourceBillNumber2;
	private String ownerCode;

	public String getSourceBillNumber2() {
		return sourceBillNumber2;
	}

	public void setSourceBillNumber2(String sourceBillNumber2) {
		this.sourceBillNumber2 = sourceBillNumber2;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	
	
}
