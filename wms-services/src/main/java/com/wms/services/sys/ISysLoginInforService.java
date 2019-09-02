package com.wms.services.sys;

import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysLoginInforTEntity;

public interface ISysLoginInforService {

	public boolean save(SysLoginInforTEntity loginInfor) throws BusinessServiceException;
	
}
