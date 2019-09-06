package com.wms.services.inventory;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.CountStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.InventoryCountDetailTEntity;

/**
 * 盘点明细
 * @author yechuankai.chnet
 */
public interface IInventoryCountDetailService {
	
	List<InventoryCountDetailTEntity> find(PageRequest request) throws BusinessServiceException;
	
	InventoryCountDetailTEntity find(InventoryCountDetailTEntity detail) throws BusinessServiceException;
	
	List<InventoryCountDetailTEntity> findByHeaderId(InventoryCountDetailTEntity detail) throws BusinessServiceException;
	
	Boolean add(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException;
	
	Boolean moidfy(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException;
	
	Boolean cancel(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException;
	
	List<InventoryCountDetailTEntity> replayCountDetail(InventoryCountDetailTEntity detail, CountStatusEnum ... countStatusEnums ) throws BusinessServiceException;

}
