package com.wms.services.base;

import java.util.List;
import java.util.Set;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.LotAttributeTEntity;
import com.wms.entity.auto.LotValidateTEntity;

public interface ILotValidateService {
	
	List<LotValidateTEntity> find(PageRequest request) throws BusinessServiceException;
	
	LotValidateTEntity find(LotValidateTEntity lotv)  throws BusinessServiceException;
	
	List<LotValidateTEntity> findByIds(LotValidateTEntity lotv, Set<Long> ids)  throws BusinessServiceException;
	
    Boolean modify(AjaxRequest<List<LotValidateTEntity>> request) throws BusinessServiceException;
	
	Boolean add(AjaxRequest<List<LotValidateTEntity>> request) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<List<LotValidateTEntity>> request) throws BusinessServiceException;
	
	Boolean validate(LotValidateTEntity lotv, LotAttributeTEntity lot) throws BusinessServiceException;
	
	Boolean validate(LotAttributeTEntity lot) throws BusinessServiceException;
}
