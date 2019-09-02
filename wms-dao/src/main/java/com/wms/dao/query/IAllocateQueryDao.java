package com.wms.dao.query;

import java.util.List;

import com.wms.vo.InventoryOnhandVO;

public interface IAllocateQueryDao {

    List<InventoryOnhandVO> availabelInventory(InventoryOnhandVO inventoryOnhand);
}