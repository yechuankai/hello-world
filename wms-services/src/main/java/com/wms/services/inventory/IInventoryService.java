package com.wms.services.inventory;

import java.util.List;
import java.util.Set;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.SkuTEntity;
import com.wms.vo.InventoryOnhandVO;

public interface IInventoryService {

	InventoryOnhandTEntity find(InventoryOnhandTEntity inventory) throws BusinessServiceException;
	
	Boolean find(SkuTEntity sku);
	
	PageResult<InventoryOnhandVO> find(PageRequest request) throws BusinessServiceException;
	
	InventoryOnhandTEntity findByLogicalKey(InventoryOnhandTEntity inventory) throws BusinessServiceException;
	
	List<InventoryOnhandTEntity> findByLocationId(InventoryOnhandTEntity inventory, Set<Long> locations) throws BusinessServiceException;
	
	List<InventoryOnhandTEntity> findByOnhandId(InventoryOnhandTEntity inventory, Set<Long> ids) throws BusinessServiceException;
	
	List<InventoryOnhandTEntity> findByOwner(InventoryOnhandTEntity inventory) throws BusinessServiceException;
	
	List<InventoryOnhandTEntity> findByLpnId(InventoryOnhandTEntity inventory, Set<Long> lpns) throws BusinessServiceException;
	
	Boolean add(InventoryOnhandTEntity inventory) throws BusinessServiceException;
	
	Boolean move(AjaxRequest<List<InventoryOnhandVO>> request) throws BusinessServiceException;
	
	Boolean modify(InventoryOnhandTEntity inventory, Boolean replaceFlag) throws BusinessServiceException;
	
}
