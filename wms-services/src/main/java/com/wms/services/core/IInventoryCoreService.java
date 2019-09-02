package com.wms.services.core;

import com.wms.vo.InventoryTranVO;

public interface IInventoryCoreService {

	/**
	 * 增加库存
	 * @param tran
	 * @return
	 */
	InventoryTranVO inbound(InventoryTranVO tran);
	
	/**
	 * 扣减库存
	 * @param tran
	 * @return
	 */
	InventoryTranVO outbound(InventoryTranVO tran);
	
	/**
	 * 移动库存
	 * @param tran
	 * @return
	 */
	InventoryTranVO move(InventoryTranVO tran);
	
	/**
	 * 批属性调整  货主调整  货品调整
	 * @param tran
	 * @return
	 */
	InventoryTranVO transfer(InventoryTranVO tran);
	
	/**
	 * 调整库存
	 * @param tran
	 * @return
	 */
	InventoryTranVO adjustment(InventoryTranVO tran);
	
	
}
