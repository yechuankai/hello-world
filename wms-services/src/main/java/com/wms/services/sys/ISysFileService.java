package com.wms.services.sys;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.FileTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysFileTEntity;

public interface ISysFileService {

	public List<SysFileTEntity> findAll() throws BusinessServiceException;
	
	public List<SysFileTEntity> find(PageRequest request) throws BusinessServiceException;
	
	public SysFileTEntity find(SysFileTEntity file) throws BusinessServiceException;
	
	public List<SysFileTEntity> find(FileTypeEnum type, String template) throws BusinessServiceException;
	
	public Boolean add(SysFileTEntity file) throws BusinessServiceException;
	
	public Boolean modify(SysFileTEntity file) throws BusinessServiceException;
	
	public Boolean delete(SysFileTEntity file) throws BusinessServiceException;
	
	public SysFileTEntity findMobileApp();

	public boolean delete(AjaxRequest<List<SysFileTEntity>> request) throws BusinessServiceException;
	
}
