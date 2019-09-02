package com.wms.services.strategy.putaway.impl;

import org.springframework.stereotype.Service;

import com.wms.common.enums.PutawayTypeEnum;
import com.wms.common.utils.StringUtils;
import com.wms.vo.PutawayVO;

/**
 * 从指定库位移出时将库存上架到目标库位
 * @author yechuankai.chnet
 *
 */
@Service
public class Putaway10ServiceImpl extends CommonPutawayServiceImpl {

	@Override
	public PutawayVO findPutawayLoc(PutawayVO putaway) {
		if (StringUtils.isEmpty(putaway.getStrategy().getFromLocationCode()))
			return putaway;
		
		if (putaway.getInventoryOnhand() == null)
			return putaway;
		
		String formLoc = putaway.getInventoryOnhand().getLocationCode();
		
		if (StringUtils.isEmpty(formLoc))
			return putaway;
		
		if (StringUtils.isEmpty(putaway.getStrategy().getToLocationCode()))
			return putaway;
		
		//库位匹配
		if (!formLoc.equals(putaway.getStrategy().getFromLocationCode()))
			return putaway;
		
		putaway.setToLocationCode(putaway.getStrategy().getToLocationCode());
		
		return putaway;
	}

	@Override
	public String getPutawayType() {
		return PutawayTypeEnum.TYPE_10.getCode();
	}

}
