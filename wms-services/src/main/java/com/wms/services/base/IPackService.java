package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.PackTEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface IPackService {
	
	List<PackTEntity> find(PageRequest request) throws BusinessServiceException;
	
	PackTEntity find(PackTEntity pack) throws BusinessServiceException;
	
	List<PackTEntity> findByIds(PackTEntity pack, Set<Long> ids) throws BusinessServiceException;
	
	List<PackTEntity> findByPackcodes(PackTEntity pack, Set<String> codes) throws BusinessServiceException;
	
	BigDecimal getUOMQty(PackTEntity pack, String uom) throws BusinessServiceException;

	Boolean modify(AjaxRequest<List<PackTEntity>> request) throws BusinessServiceException;
	
    Boolean add(AjaxRequest<List<PackTEntity>> request) throws BusinessServiceException;

	Boolean add(List<PackTEntity> packs) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<List<PackTEntity>> request) throws BusinessServiceException; 
}
