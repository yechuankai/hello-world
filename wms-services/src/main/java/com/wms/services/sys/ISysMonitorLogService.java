package com.wms.services.sys;

import java.util.List;

import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysMonitorLogTEntity;
import com.wms.entity.auto.SysOperLogTEntity;

public interface ISysMonitorLogService {

	List<SysMonitorLogTEntity> find(PageRequest request) throws BusinessServiceException;
	
}
