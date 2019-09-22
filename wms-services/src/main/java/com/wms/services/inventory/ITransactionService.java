package com.wms.services.inventory;

import java.util.List;

import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.InventoryTransactionTEntity;

public interface ITransactionService {
	Boolean add(InventoryTransactionTEntity tran) throws BusinessServiceException;

	List<InventoryTransactionTEntity> find(PageRequest pageRequest);
	
	/**
	 * 按大于某个交易日期查询
	 * @param tran
	 * @return
	 */
	List<InventoryTransactionTEntity> findGreaterThanDate(InventoryTransactionTEntity tran);
}
