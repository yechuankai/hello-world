package com.wms.services.inventory;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.InventoryLockedTEntity;
import com.wms.vo.InventoryLockedVO;

/**
 * 库存冻结
 * @author yechuankai.chnet
 *
 */
public interface IInventoryLockService {
	
	List<InventoryLockedVO> find(PageRequest request) throws BusinessServiceException;

	List<InventoryLockedTEntity> find(InventoryLockedTEntity lock) throws BusinessServiceException;
	
	InventoryLockedTEntity findById(InventoryLockedTEntity lock) throws BusinessServiceException;
	
	Boolean lock(List<InventoryLockedVO> lockList) throws BusinessServiceException;
	
	Boolean unLock(List<InventoryLockedVO> unLockList) throws BusinessServiceException;
	
	Boolean lock(AjaxRequest<List<InventoryLockedVO>> request) throws BusinessServiceException;
	
	Boolean unLock(AjaxRequest<List<InventoryLockedVO>> request) throws BusinessServiceException;
	
	
}
