package com.wms.services.sys;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.enums.PermissionTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysPermissionTEntity;
import com.wms.entity.auto.SysRolePermissionTEntity;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.vo.PermissionTreeVO;

public interface ISysRolePermissionService {
	
	List<SysPermissionTEntity> findRolePermission(List<SysRoleTEntity> roles);
	
	List<SysPermissionTEntity> findRolePermission(List<SysRoleTEntity> roles, PermissionTypeEnum permissionType);
	
	List<SysPermissionTEntity> findRolePermission(List<SysRoleTEntity> roles, PermissionTypeEnum permissionType, String locale);
	
	PermissionTreeVO findAll2Tree(List<SysRoleTEntity> roles);
	
	PermissionTreeVO findAll2Tree(SysUserTEntity user);
	
	Boolean add(AjaxRequest<List<SysRolePermissionTEntity>> request) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<SysRolePermissionTEntity> request) throws BusinessServiceException;
	
}
