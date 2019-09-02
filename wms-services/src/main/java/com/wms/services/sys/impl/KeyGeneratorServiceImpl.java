package com.wms.services.sys.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.wms.common.core.domain.OrderNumberVO;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.dao.auto.ISysOrderNumberTDao;
import com.wms.dao.example.SysOrderNumberTExample;
import com.wms.entity.auto.SysOrderNumberTEntity;

@Service
@Primary
public class KeyGeneratorServiceImpl extends com.wms.common.core.services.impl.KeyGeneratorServiceImpl {

	@Autowired
	private ISysOrderNumberTDao orderNumberDao;
	
	@Override
	public OrderNumberVO getSequenceConfig(Long companyId, Long warehouseId, String code) {
		OrderNumberVO vo = super.getSequenceConfig(companyId, warehouseId, code);
		if (vo != null) {
			return vo;
		}
		
		SysOrderNumberTExample example = new SysOrderNumberTExample();
		example.createCriteria()
		.andCodeEqualTo(code)
		.andCompanyIdEqualTo(companyId)
		.andWarehouseIdEqualTo(warehouseId)
		.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		SysOrderNumberTEntity orderNumber = orderNumberDao.selectOneByExample(example);
		if (orderNumber == null)
			return null;
			
		vo = new OrderNumberVO();
		vo.setCode(orderNumber.getCode());
		vo.setPrefix(orderNumber.getPrefix());
		vo.setLength(orderNumber.getLength());
		vo.setDateFormat(orderNumber.getDataFormat());
		vo.setIncrement(orderNumber.getSequenceIncrement());
		vo.setCacheSequence(orderNumber.getSequenceCache());
		vo.setCurrentSequence(orderNumber.getSequence());
		vo.setSequence(orderNumber.getSequence());
		
		example.getOredCriteria().clear();
		example.createCriteria()
		.andWarehouseIdEqualTo(warehouseId)
		.andCompanyIdEqualTo(companyId)
		.andOrderNumberIdEqualTo(orderNumber.getOrderNumberId());
		
		SysOrderNumberTEntity update = SysOrderNumberTEntity.builder()
				.updateTime(new Date())
				.sequence(orderNumber.getSequence() + orderNumber.getSequenceCache())
				.orderNumberId(orderNumber.getOrderNumberId())
				.build();
		int row = orderNumberDao.updateWithVersionByExampleSelective(orderNumber.getUpdateVersion(), update, example);
		if (row == 0) {
			throw new BusinessServiceException("record update error.");
		}
		return vo;
	}

}
