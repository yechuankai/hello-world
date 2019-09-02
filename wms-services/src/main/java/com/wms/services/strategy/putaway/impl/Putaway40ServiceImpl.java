package com.wms.services.strategy.putaway.impl;

import org.springframework.stereotype.Service;

import com.wms.common.enums.PutawayTypeEnum;
import com.wms.common.utils.StringUtils;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.ZoneTEntity;
import com.wms.vo.PutawayVO;

/**
 * 从指定库区移出时将库存上架到目标库区中空库位
 * @author yechuankai.chnet
 *
 */
@Service
public class Putaway40ServiceImpl extends CommonPutawayServiceImpl {

	@Override
	public PutawayVO findPutawayLoc(PutawayVO putaway) {
		if (StringUtils.isEmpty(putaway.getStrategy().getFromZoneCode()))
			return putaway;
		
		if (putaway.getInventoryOnhand() == null)
			return putaway;
		
		String formLoc = putaway.getInventoryOnhand().getLocationCode();
		
		if (StringUtils.isEmpty(formLoc))
			return putaway;
		
		if (StringUtils.isEmpty(putaway.getStrategy().getToZoneCode()))
			return putaway;
		
		//获取库位所在的库区
		ZoneTEntity zone = getZone(putaway.getInventoryOnhand());
		if (zone == null)
			return putaway;
		
		//库区匹配
		if (!putaway.getStrategy().getFromZoneCode().equals(zone.getZoneCode()))
			return putaway;
		
		LocationTEntity emptyLoc = findEmptyLocByZone(putaway.getStrategy());
		
		putaway.setToLocationCode(emptyLoc.getLocationCode());
		//锁定库位
		lockLocation(putaway);
		
		return putaway;
	}

	@Override
	public String getPutawayType() {
		return PutawayTypeEnum.TYPE_40.getCode();
	}

}
