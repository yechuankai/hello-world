package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SupplierTEntity;

import java.util.List;

public interface ISupplierService {
	
	List<SupplierTEntity> find(PageRequest request) throws BusinessServiceException;
	
	SupplierTEntity find(SupplierTEntity supplier)  throws BusinessServiceException;

	SupplierTEntity findByCode(SupplierTEntity supplier)  throws BusinessServiceException;

	Boolean modify(AjaxRequest<List<SupplierTEntity>> request) throws BusinessServiceException;

	Boolean add(AjaxRequest<List<SupplierTEntity>> request) throws BusinessServiceException;

	Boolean add(List<SupplierTEntity> suppliers) throws BusinessServiceException;

	Boolean delete(AjaxRequest<List<SupplierTEntity>> request) throws BusinessServiceException;
}
