package com.wms.services.inventory;

import java.util.List;
import java.util.Set;

import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.LpnTEntity;

public interface ILpnService {

	LpnTEntity find(LpnTEntity lpn) throws BusinessServiceException;
	
	List<LpnTEntity> findByLpnNumbers(LpnTEntity lpn, Set<String> lpnNumbers) throws BusinessServiceException;
	
	List<LpnTEntity> findByLpnIds(LpnTEntity lpn, Set<Long> ids) throws BusinessServiceException;
	
	List<LpnTEntity> findByContainerNumber(LpnTEntity lpn) throws BusinessServiceException;
	
	Boolean add(LpnTEntity lpn) throws BusinessServiceException;
	
	Boolean modify(LpnTEntity lpn) throws BusinessServiceException;
	
	Boolean Delete(LpnTEntity lpn) throws BusinessServiceException;

	List<LpnTEntity> find(PageRequest pageRequest);
	
}
