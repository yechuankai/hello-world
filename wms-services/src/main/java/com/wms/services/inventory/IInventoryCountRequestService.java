package com.wms.services.inventory;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.InventoryCountRequestTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;

import java.util.List;

/**
 * 盘点请求
 * @author yechuankai.chnet
 *
 */
public interface IInventoryCountRequestService {

	List<InventoryCountRequestTEntity> find(PageRequest request) throws BusinessServiceException;
	
	InventoryCountRequestTEntity find(InventoryCountRequestTEntity countRequest) throws BusinessServiceException;
	
	Boolean modify(AjaxRequest<List<InventoryCountRequestTEntity>> request) throws BusinessServiceException;
	
	Boolean add(AjaxRequest<List<InventoryCountRequestTEntity>> request) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<List<InventoryCountRequestTEntity>> request) throws BusinessServiceException;
	
	List<InventoryOnhandTEntity> findInventory(InventoryCountRequestTEntity countRequest) throws BusinessServiceException;
}
