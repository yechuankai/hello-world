package com.wms.services.inventory;

import java.util.List;

import com.wms.common.core.domain.request.PageRequest;
import com.wms.vo.InventoryMultiCountVo;

public interface IInventoryMultiService {

	List<InventoryMultiCountVo> selectByLocation(PageRequest request);

	List<InventoryMultiCountVo> selectByLPN(PageRequest request);

	List<InventoryMultiCountVo> selectByContainer(PageRequest request);

	List<InventoryMultiCountVo> selectBySku(PageRequest request);

}
