package com.wms.services.strategy.putaway.impl;

import org.springframework.stereotype.Service;

import com.wms.common.enums.PutawayTypeEnum;
import com.wms.common.utils.StringUtils;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.SkuTEntity;
import com.wms.vo.PutawayVO;

/**
 * 上架到货品主数据配置的上架库区中的空库位
 * @author yechuankai.chnet
 *
 */
@Service
public class Putaway50ServiceImpl extends CommonPutawayServiceImpl {
	
	@Override
	public PutawayVO findPutawayLoc(PutawayVO putaway) {
		if (putaway.getInventoryOnhand() == null)
			return putaway;
		
		String skuCode = putaway.getInventoryOnhand().getSkuCode();
		
		if (StringUtils.isEmpty(skuCode))
			return putaway;
		
		SkuTEntity sku = getSku(putaway.getInventoryOnhand());
		
		if (StringUtils.isEmpty(sku.getPutawayZoneCode()))
			return putaway;
		
		putaway.getStrategy().setToZoneCode(sku.getPutawayZoneCode());
		LocationTEntity emptyLoc = findEmptyLocByZone(putaway.getStrategy());
		if (emptyLoc == null)
			return putaway;
		
		putaway.setToLocationCode(emptyLoc.getLocationCode());
		//锁定库位
		lockLocation(putaway);
		
		return putaway;
	}

	@Override
	public String getPutawayType() {
		return PutawayTypeEnum.TYPE_50.getCode();
	}

}
