package com.wms.vo;

import java.util.Date;

import com.wms.entity.auto.BillingLeaseTEntity;

public class BillingLeaseVO extends BillingLeaseTEntity {

	private Date billingDateBegin;
	
	private Date billingDateEnd;

	public Date getBillingDateBegin() {
		return billingDateBegin;
	}

	public void setBillingDateBegin(Date billingDateBegin) {
		this.billingDateBegin = billingDateBegin;
	}

	public Date getBillingDateEnd() {
		return billingDateEnd;
	}

	public void setBillingDateEnd(Date billingDateEnd) {
		this.billingDateEnd = billingDateEnd;
	}
	
}
