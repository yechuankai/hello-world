package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.CarrierTEntity;

import java.util.List;

public interface ICarrierService {
	
	List<CarrierTEntity> find(PageRequest request) throws BusinessServiceException;
	
	CarrierTEntity find(CarrierTEntity carrier)  throws BusinessServiceException;

	CarrierTEntity findByCode(CarrierTEntity carrier)  throws BusinessServiceException;

	Boolean modify(AjaxRequest<List<CarrierTEntity>> request) throws BusinessServiceException;

	Boolean add(AjaxRequest<List<CarrierTEntity>> request) throws BusinessServiceException;

	Boolean add(List<CarrierTEntity> carriers) throws BusinessServiceException;

	Boolean delete(AjaxRequest<List<CarrierTEntity>> request) throws BusinessServiceException;
}
