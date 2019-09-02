package com.wms.services.sys;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysUserDefaultTEntity;

public interface ISysUserDefaultService {

	
	public List<SysUserDefaultTEntity> find(PageRequest request) throws BusinessServiceException;
	
	public Boolean modify(AjaxRequest<List<SysUserDefaultTEntity>> request) throws BusinessServiceException;
	
	public Boolean add(AjaxRequest<List<SysUserDefaultTEntity>> request) throws BusinessServiceException;
	
	public Boolean delete(AjaxRequest<List<SysUserDefaultTEntity>> request) throws BusinessServiceException;
	
}
