package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.AreaTEntity;
import com.wms.entity.auto.LocationTEntity;

import java.util.List;
import java.util.Set;

public interface ILocationService {
	
	LocationTEntity find(LocationTEntity location)  throws BusinessServiceException;

	List<LocationTEntity> find(PageRequest request) throws BusinessServiceException;

	Boolean modify(AjaxRequest<List<LocationTEntity>> request) throws BusinessServiceException;

	Boolean add(AjaxRequest<List<LocationTEntity>> request) throws BusinessServiceException;

	Boolean delete(AjaxRequest<List<LocationTEntity>> request) throws BusinessServiceException;
	
	List<LocationTEntity> findByLocationCodes(LocationTEntity location, Set<String> codes)  throws BusinessServiceException;
	
	List<LocationTEntity> findByLocationIds(LocationTEntity location, Set<Long> ids) throws BusinessServiceException;
	
	List<LocationTEntity> findByZone(LocationTEntity location) throws BusinessServiceException;

	List<LocationTEntity> findlocationAvailable( AreaTEntity area) throws BusinessServiceException;
}
