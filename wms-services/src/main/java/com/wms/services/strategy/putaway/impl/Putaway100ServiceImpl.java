package com.wms.services.strategy.putaway.impl;

import org.springframework.stereotype.Service;

import com.wms.common.enums.PutawayTypeEnum;
import com.wms.common.utils.StringUtils;
import com.wms.vo.PutawayVO;

/**
 * 上架到指定库位
 * @author yechuankai.chnet
 *
 */
@Service
public class Putaway100ServiceImpl extends CommonPutawayServiceImpl {

	@Override
	public PutawayVO findPutawayLoc(PutawayVO putaway) {
		if (StringUtils.isEmpty(putaway.getStrategy().getToLocationCode()))
			return putaway;
		
		putaway.setToLocationCode(putaway.getStrategy().getToLocationCode());
		
		return putaway;
	}

	@Override
	public String getPutawayType() {
		return PutawayTypeEnum.TYPE_100.getCode();
	}

}
