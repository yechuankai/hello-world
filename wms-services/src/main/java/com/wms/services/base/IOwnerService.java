package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.OwnerTEntity;

import java.util.List;
import java.util.Set;

public interface IOwnerService {
	
	List<OwnerTEntity> find(PageRequest request) throws BusinessServiceException;
	
	OwnerTEntity find(OwnerTEntity owner)  throws BusinessServiceException;
	
	List<OwnerTEntity> findAll(OwnerTEntity owner)  throws BusinessServiceException;
	
	List<OwnerTEntity> findByOwnerCodes(OwnerTEntity owner, Set<String> codes)  throws BusinessServiceException;

    Boolean modify(AjaxRequest<List<OwnerTEntity>> request) throws BusinessServiceException;
    
    Boolean modify(OwnerTEntity owner) throws BusinessServiceException;
	
	Boolean add(AjaxRequest<List<OwnerTEntity>> request) throws BusinessServiceException;

	Boolean add(List<OwnerTEntity> owners) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<List<OwnerTEntity>> request) throws BusinessServiceException;
	
	Long generatorBarcode(AjaxRequest<OwnerTEntity> request, Long count) throws BusinessServiceException;
}
