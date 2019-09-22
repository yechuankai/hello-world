package com.wms.services.billing;

import java.util.Date;
import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.entity.auto.BillingLeaseTEntity;
import com.wms.entity.auto.OwnerTEntity;

/**
 * 仓租计算
 * @author yechuankai.chnet
 *
 */
public interface ILeaseService {

	void createLease();
	
	void createLease(AjaxRequest<Date> request);
	
	void createOwnerLease(AjaxRequest<OwnerTEntity> request, Date endDate);
	
	Boolean add(BillingLeaseTEntity lease);
	
	Boolean delete(BillingLeaseTEntity lease);
	
	List<BillingLeaseTEntity> find(PageRequest lease);
	
	List<BillingLeaseTEntity> findByOwnerMonth(BillingLeaseTEntity request);
}
