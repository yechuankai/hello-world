package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SkuTEntity;
import com.wms.vo.LotLabelVO;

import java.util.List;
import java.util.Set;

public interface ISkuService {

	List<SkuTEntity> findByIds(SkuTEntity sku, Set<Long> ids) throws BusinessServiceException;
	
	List<SkuTEntity> findBySkuCodes(SkuTEntity sku, Set<String> codes) throws BusinessServiceException;
	
	SkuTEntity find(SkuTEntity sku) throws BusinessServiceException;
	
	List<SkuTEntity> find(PageRequest request) throws BusinessServiceException;

	Boolean modify(AjaxRequest<List<SkuTEntity>> request) throws BusinessServiceException;
		
	Boolean add(AjaxRequest<List<SkuTEntity>> request) throws BusinessServiceException;

	Boolean add(List<SkuTEntity> list) throws BusinessServiceException;
		
	Boolean delete(AjaxRequest<List<SkuTEntity>> request) throws BusinessServiceException;
	
	LotLabelVO getSkuLotLabel(SkuTEntity sku) throws BusinessServiceException;
	
}
