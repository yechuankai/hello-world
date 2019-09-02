package com.wms.common.core.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Maps;
import com.wms.common.constants.ExceptionConstant;
import com.wms.common.core.domain.OrderNumberVO;
import com.wms.common.core.services.IKeyGeneratorService;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.key.KeyUtils;
import com.wms.common.utils.key.UidGenerator;

public class KeyGeneratorServiceImpl implements IKeyGeneratorService {
	private static Logger log = LoggerFactory.getLogger(KeyGeneratorServiceImpl.class);
	
	@Autowired
	private UidGenerator uidGenerator;
	
	private Map<String, OrderNumberVO> sequenceConfig = Maps.newConcurrentMap();
	
	@Override
	public Long getUID() throws BusinessServiceException {
		try {
			long uid = uidGenerator.nextId();
			return uid;
		} catch (Exception e) {
			throw new BusinessServiceException("KeyGeneratorService", ExceptionConstant.ERROR_KEY_GENERATOR, null, e.getMessage());
		}
	}

	@Override
	public synchronized String getOrderNumber(OrderNumberTypeEnum type) throws BusinessServiceException {
		return getOrderNumber(0L, 0L, type);
	}
	
	@Override
	public String getOrderNumber(Long companyId, Long warehouseId, OrderNumberTypeEnum type)
			throws BusinessServiceException {
		OrderNumberVO vo = getSequenceConfig(companyId, warehouseId, type.getCode());
		if (vo == null) {
			throw new BusinessServiceException("KeyGeneratorServiceImpl", "ordernumber.not.generator", new Object[] {type.getCode()});
		}
		StringBuilder cacheKey = new StringBuilder();
		cacheKey.append(warehouseId).append(companyId).append(type.getCode());
		//达到缓存最大值
		if ((vo.getCurrentSequence() - vo.getSequence()) >= vo.getCacheSequence() ) {
			sequenceConfig.remove(cacheKey.toString());
			vo = null;
			vo = getSequenceConfig(companyId, warehouseId, type.getCode());
			if (vo == null) {
				throw new BusinessServiceException("KeyGeneratorServiceImpl", "ordernumber.not.generator", new Object[] {type.getCode()});
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(vo.getPrefix());
		if (StringUtil.isNotEmpty(vo.getDateFormat())) {
			try {
				sb.append(new SimpleDateFormat(vo.getDateFormat()).format(new Date()));
			} catch (Exception e) {
				throw new BusinessServiceException("KeyGeneratorServiceImpl", "ordernumber.not.generator", new Object[] {type.getCode()});
			}
		}
		
		//获取序号长度
		long sequenceLength = vo.getLength() - sb.length();
		StringBuilder defaultSequence = new StringBuilder(KeyUtils.ZERO);
		defaultSequence.append(vo.increment());
		
		int start = (int) (defaultSequence.length() - sequenceLength);
		int end = defaultSequence.length();
		sb.append(defaultSequence.substring( start , end));
		
		sequenceConfig.put(cacheKey.toString(), vo);
		
		return sb.toString();
	}


	@Override
	public OrderNumberVO getSequenceConfig(Long companyId, Long warehouseId, String code) {
		StringBuilder sb = new StringBuilder();
		sb.append(warehouseId).append(companyId).append(code);
		return sequenceConfig.get(sb.toString());
	}

	

}
