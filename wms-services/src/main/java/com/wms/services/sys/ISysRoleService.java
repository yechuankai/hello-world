package com.wms.services.sys;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;

public interface ISysRoleService {

	public List<SysRoleTEntity> find(PageRequest request) throws BusinessServiceException;
	
	public List<SysRoleTEntity> findUserAvailable(AjaxRequest<SysUserTEntity> request) throws BusinessServiceException;
	
	public Boolean modify(AjaxRequest<List<SysRoleTEntity>> request) throws BusinessServiceException;
	
	public Boolean add(AjaxRequest<List<SysRoleTEntity>> request) throws BusinessServiceException;
	
	public Boolean delete(AjaxRequest<List<SysRoleTEntity>> request) throws BusinessServiceException;
	
	public SysRoleTEntity findByRoleId(Long roleId) throws BusinessServiceException;
	
}
