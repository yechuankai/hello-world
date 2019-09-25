package com.wms.services.sys.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.wms.common.constants.DefaultConstants;
import com.wms.common.core.domain.OrderNumberVO;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
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
		if (orderNumber == null) {
			orderNumber = SysOrderNumberTEntity.builder()
							.orderNumberId(KeyUtils.getUID())
							.companyId(companyId)
							.warehouseId(warehouseId)
							.code(code)
							.length(10L)
							.dateFormat(DefaultConstants.ORDER_NUMBER_DATE_FORMAT)
							.sequenceIncrement(1L)
							.sequenceCache(5L)
							.sequence(1L)
							.updateVersion(0L)
							.build();
			orderNumberDao.insertSelective(orderNumber);
		}
			
		vo = new OrderNumberVO();
		vo.setCode(orderNumber.getCode());
		vo.setPrefix(orderNumber.getPrefix());
		vo.setLength(orderNumber.getLength());
		vo.setDateFormat(orderNumber.getDateFormat());
		vo.setIncrement(orderNumber.getSequenceIncrement());
		vo.setCacheSequence(orderNumber.getSequenceCache());
		vo.setCurrentSequence(orderNumber.getSequence());
		vo.setSequence(orderNumber.getSequence());
		
		String dateStr = null;
		//如果设置了日期，日期不一样则需要重新设置序列
		if (StringUtils.isNotEmpty(vo.getDateFormat())) {
			dateStr = new SimpleDateFormat(orderNumber.getDateFormat()).format(new Date());
			String lastDateStr = orderNumber.getDescription();
			//重置序列
			if (!dateStr.equals(lastDateStr)) {
				vo.setSequence(0L);
			}
		}
		
		long seq = vo.getSequence() + vo.getCacheSequence();
		
		example.getOredCriteria().clear();
		example.createCriteria()
		.andWarehouseIdEqualTo(warehouseId)
		.andCompanyIdEqualTo(companyId)
		.andOrderNumberIdEqualTo(orderNumber.getOrderNumberId());
		
		SysOrderNumberTEntity update = SysOrderNumberTEntity.builder()
				.updateTime(new Date())
				.sequence(seq)
				.orderNumberId(orderNumber.getOrderNumberId())
				.description(dateStr) //记录日期字符
				.build();
		int row = orderNumberDao.updateWithVersionByExampleSelective(orderNumber.getUpdateVersion(), update, example);
		if (row == 0) {
			throw new BusinessServiceException("record update error.");
		}
		return vo;
	}

}
