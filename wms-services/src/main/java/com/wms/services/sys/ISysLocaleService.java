package com.wms.services.sys;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysLocaleTEntity;
import com.wms.entity.auto.SysLocaleVEntity;

public interface ISysLocaleService {

	public List<SysLocaleVEntity> findAll() throws BusinessServiceException;
	
	public SysLocaleTEntity findByLocaleCode(String localeCode) throws BusinessServiceException;
	
	public List<SysLocaleTEntity> find(PageRequest request) throws BusinessServiceException;
	
	public Boolean modify(AjaxRequest<List<SysLocaleTEntity>> request) throws BusinessServiceException;
	
	public Boolean add(AjaxRequest<List<SysLocaleTEntity>> request) throws BusinessServiceException;
	
	public Boolean delete(AjaxRequest<List<SysLocaleTEntity>> request) throws BusinessServiceException;
	
	public void loadLocale();
}
