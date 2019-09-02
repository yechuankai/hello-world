package com.wms.common.core.services;

import com.wms.common.core.domain.OrderNumberVO;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.exception.BusinessServiceException;

public interface IKeyGeneratorService {
	
	public Long getUID() throws BusinessServiceException;
	
	public String getOrderNumber(OrderNumberTypeEnum type) throws BusinessServiceException;
	
	public String getOrderNumber(Long companyId, Long warehouseId, OrderNumberTypeEnum type) throws BusinessServiceException;

	public OrderNumberVO getSequenceConfig(Long companyId, Long warehouseId, String code);
	
}
