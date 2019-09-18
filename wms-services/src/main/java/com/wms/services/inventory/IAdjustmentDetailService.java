package com.wms.services.inventory;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.InventoryAdjustmentDetailTEntity;
import com.wms.vo.adjustment.AdjustmentDetailVO;
import com.wms.vo.adjustment.AdjustmentVO;

public interface IAdjustmentDetailService {

	List<AdjustmentDetailVO> find(PageRequest request) throws BusinessServiceException;
	
	List<InventoryAdjustmentDetailTEntity> findByHeaderId(InventoryAdjustmentDetailTEntity detail) throws BusinessServiceException;
	
	Long findMaxLine(InventoryAdjustmentDetailTEntity detail) throws BusinessServiceException;
	
	InventoryAdjustmentDetailTEntity find(InventoryAdjustmentDetailTEntity adjustment) throws BusinessServiceException;
	
	AdjustmentVO save(AjaxRequest<AdjustmentVO> request) throws BusinessServiceException;
	
}
