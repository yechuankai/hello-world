package com.wms.services.sys;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.services.IExcelService;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysConfigTEntity;

public interface ISysConfigService {

	public List<SysConfigTEntity> findAll() throws BusinessServiceException;
	
	public List<SysConfigTEntity> find(PageRequest request) throws BusinessServiceException;
	
	public Boolean modify(AjaxRequest<List<SysConfigTEntity>> request) throws BusinessServiceException;
	
	public Boolean add(AjaxRequest<List<SysConfigTEntity>> request) throws BusinessServiceException;
	
	public Boolean delete(AjaxRequest<List<SysConfigTEntity>> request) throws BusinessServiceException;
	
	public void loadConfig();
}
