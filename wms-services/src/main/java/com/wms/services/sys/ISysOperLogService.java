package com.wms.services.sys;

import java.util.List;

import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysOperLogTEntity;

public interface ISysOperLogService {

	List<SysOperLogTEntity> find(PageRequest request) throws BusinessServiceException;
	Boolean save(SysOperLogTEntity operLog) throws BusinessServiceException;
	
}
