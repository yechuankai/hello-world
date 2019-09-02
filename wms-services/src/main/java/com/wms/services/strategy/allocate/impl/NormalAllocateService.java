package com.wms.services.strategy.allocate.impl;

import org.springframework.stereotype.Service;

import com.wms.common.enums.allocate.AllocateTypeEnum;

@Service
public class NormalAllocateService extends CommonAllocateServiceImpl{

	@Override
	public String getAllocateType() {
		return AllocateTypeEnum.Noraml.getCode();
	}

}
