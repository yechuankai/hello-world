package com.wms.services.core;

import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.LpnTEntity;
import com.wms.entity.auto.PutawayStrategyTEntity;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.PutawayVO;

public interface IPutawayCoreService {

	/**
	 * 按lpn上架
	 * @param lpn
	 * @param strategy 强制指定策略，可为空，为空时从货品主数据中获取
	 * @return
	 * @throws BusinessServiceException
	 */
	PutawayVO lpnPutaway(LpnTEntity lpn, PutawayStrategyTEntity strategy) throws BusinessServiceException;
	
	/**
	 * 按库存上架
	 * @param inventory
	 * @param strategy 强制指定策略，可为空，为空时从货品主数据中获取
	 * @return
	 * @throws BusinessServiceException
	 */
	PutawayVO inventoryPutaway(InventoryOnhandVO inventory, PutawayStrategyTEntity strategy) throws BusinessServiceException;
	
	/**
	 * 按lpn上架
	 * @param lpn
	 * @return
	 * @throws BusinessServiceException
	 */
	PutawayVO lpnPutaway(LpnTEntity lpn) throws BusinessServiceException;
	
	/**
	 * 按库存上架
	 * @param inventory
	 * @return
	 * @throws BusinessServiceException
	 */
	PutawayVO inventoryPutaway(InventoryOnhandVO inventory) throws BusinessServiceException;
	
	
}
