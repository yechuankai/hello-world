package com.wms.services.strategy.allocate;

import java.util.List;

import com.wms.vo.allocate.InventoryAllocateDetailVO;
import com.wms.vo.allocate.AllocateInventoryVO;

public interface IAllocateStrategyService {
	
	default void before(InventoryAllocateDetailVO detail) {};
	
	List<AllocateInventoryVO> getInventory(InventoryAllocateDetailVO detail);
	
	List<AllocateInventoryVO> filter(InventoryAllocateDetailVO allocate, List<AllocateInventoryVO> inventory);
	
	default void after(InventoryAllocateDetailVO detail, List<AllocateInventoryVO> inventory) {};
	
	String getAllocateType();
	
}
