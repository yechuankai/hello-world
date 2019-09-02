package com.wms.services.strategy.putaway;

import java.util.List;

import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.PutawayVO;

/**
 * 上架获取目标库位
 * @author yechuankai.chnet
 *
 */
public interface IPutawayService {
	
	PutawayVO findPutawayLoc(PutawayVO putaway);
	
	String getPutawayType();
	
}
