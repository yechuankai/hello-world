package com.wms.services.core;

import com.wms.common.exception.BusinessServiceException;
import com.wms.vo.allocate.InventoryAllocateVO;

public interface IAllocateCoreService {

	InventoryAllocateVO allocate(InventoryAllocateVO allocate) throws BusinessServiceException;
	
	InventoryAllocateVO allocateTask(InventoryAllocateVO allocate, Boolean wait);
	
}
