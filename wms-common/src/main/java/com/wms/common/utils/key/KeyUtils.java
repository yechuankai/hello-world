package com.wms.common.utils.key;

import com.wms.common.core.services.IKeyGeneratorService;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.utils.spring.SpringUtils;

public class KeyUtils {
	
	public static final String ZERO = "0000000000";

	public static Long getUID() {
		IKeyGeneratorService keyGenerator = SpringUtils.getBean(IKeyGeneratorService.class);
		return keyGenerator.getUID();
	}
	
	public static String getOrderNumber(OrderNumberTypeEnum type) {
		IKeyGeneratorService keyGenerator = SpringUtils.getBean(IKeyGeneratorService.class);
		return keyGenerator.getOrderNumber(type);
	}
	
	public static String getOrderNumber(Long companyId, Long warehouseId, OrderNumberTypeEnum type) {
		IKeyGeneratorService keyGenerator = SpringUtils.getBean(IKeyGeneratorService.class);
		return keyGenerator.getOrderNumber(companyId, warehouseId, type);
	}
}
