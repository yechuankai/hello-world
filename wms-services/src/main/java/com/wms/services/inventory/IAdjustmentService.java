package com.wms.services.inventory;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.InboundStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.InventoryAdjustmentHeaderTEntity;
import com.wms.vo.adjustment.AdjustmentVO;

public interface IAdjustmentService {

	List<InventoryAdjustmentHeaderTEntity> find(PageRequest request) throws BusinessServiceException;
	
	InventoryAdjustmentHeaderTEntity find(InventoryAdjustmentHeaderTEntity adjustment) throws BusinessServiceException;
	
	Boolean modify(AjaxRequest<List<AdjustmentVO>> request) throws BusinessServiceException;
	
	Boolean modify(InventoryAdjustmentHeaderTEntity adjustment) throws BusinessServiceException;
	
	Boolean add(AjaxRequest<AdjustmentVO> request) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<List<AdjustmentVO>> request) throws BusinessServiceException;
	
}
