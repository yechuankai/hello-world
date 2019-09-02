package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.CustomerTEntity;

import java.util.List;

public interface ICustomerService {
	
	List<CustomerTEntity> find(PageRequest request) throws BusinessServiceException;
	
	CustomerTEntity find(CustomerTEntity customer)  throws BusinessServiceException;

	CustomerTEntity findByCode(CustomerTEntity customer)  throws BusinessServiceException;

	Boolean modify(AjaxRequest<List<CustomerTEntity>> request) throws BusinessServiceException;

	Boolean add(AjaxRequest<List<CustomerTEntity>> request) throws BusinessServiceException;

	Boolean add(List<CustomerTEntity> customers) throws BusinessServiceException;

	Boolean delete(AjaxRequest<List<CustomerTEntity>> request) throws BusinessServiceException;
}
