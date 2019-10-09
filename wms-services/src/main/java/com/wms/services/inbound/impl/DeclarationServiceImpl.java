package com.wms.services.inbound.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wms.common.enums.InboundStatusEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.dao.auto.IInboundHeaderTDao;
import com.wms.dao.example.InboundHeaderTExample;
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.services.inbound.IDeclarationService;

@Service
public class DeclarationServiceImpl implements IDeclarationService {

	@Autowired
	private IInboundHeaderTDao inboundDao;
	
	@Override
	public List<InboundHeaderTEntity> findByDeclareNumber(InboundHeaderTEntity inbound) {
		InboundHeaderTExample example = new InboundHeaderTExample();
		example.createCriteria()
		.andReferenceNumberEqualTo(inbound.getReferenceNumber())
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andStatusNotEqualTo(InboundStatusEnum.Cancel.getCode());
		
		List<InboundHeaderTEntity> list = inboundDao.selectByExample(example);
		
		return list;
	}


}
