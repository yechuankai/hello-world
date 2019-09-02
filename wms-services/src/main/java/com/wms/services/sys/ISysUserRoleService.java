package com.wms.services.sys;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.vo.UserRoleVO;

public interface ISysUserRoleService {

	public List<SysRoleTEntity> findUserRole(SysUserTEntity user);
	
	public List<UserRoleVO> find(PageRequest request) throws BusinessServiceException;
	
	public Boolean add(AjaxRequest<List<UserRoleVO>> request) throws BusinessServiceException;
	
	public Boolean delete(AjaxRequest<List<UserRoleVO>> request) throws BusinessServiceException;
	

}
