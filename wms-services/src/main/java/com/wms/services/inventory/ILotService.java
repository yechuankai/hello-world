package com.wms.services.inventory;

import java.util.List;
import java.util.Set;

import com.wms.common.core.domain.IBaseEntity;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.LotAttributeTEntity;

public interface ILotService {

	LotAttributeTEntity find(LotAttributeTEntity lot) throws BusinessServiceException;
	
	List<LotAttributeTEntity> findByAttribute(LotAttributeTEntity lot) throws BusinessServiceException;
	
	List<LotAttributeTEntity> find(PageRequest request) throws BusinessServiceException;
	
	List<LotAttributeTEntity> findByIds(LotAttributeTEntity lpn, Set<Long> ids) throws BusinessServiceException;
	
	List<LotAttributeTEntity> findBylotNumbers(LotAttributeTEntity lot, Set<String> lotNumbers) throws BusinessServiceException;
	
	Boolean add(LotAttributeTEntity lot) throws BusinessServiceException;
	
	LotAttributeTEntity registLot(LotAttributeTEntity lot) throws BusinessServiceException;
	
	Boolean validateLotAttribute(IBaseEntity l1, IBaseEntity l2) throws BusinessServiceException;
}
