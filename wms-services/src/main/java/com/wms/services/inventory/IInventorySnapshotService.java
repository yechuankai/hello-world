package com.wms.services.inventory;

import java.util.List;

import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.InventoryOnhandSnapshotTEntity;

/**
 * 库存快照
 * @author yechuankai.chnet
 *
 */
public interface IInventorySnapshotService {

	List<InventoryOnhandSnapshotTEntity> findByOwner(InventoryOnhandSnapshotTEntity inventory) throws BusinessServiceException;
	
	Boolean add(InventoryOnhandSnapshotTEntity inventory) throws BusinessServiceException;
	
	Boolean add(List<InventoryOnhandSnapshotTEntity> inventory) throws BusinessServiceException;
	
	Boolean delete(InventoryOnhandSnapshotTEntity inventory) throws BusinessServiceException;
	
	Boolean synchronize(InventoryOnhandSnapshotTEntity inventory) throws BusinessServiceException;
	
	Boolean clean(InventoryOnhandSnapshotTEntity inventory) throws BusinessServiceException;

}
