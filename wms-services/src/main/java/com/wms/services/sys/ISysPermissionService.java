package com.wms.services.sys;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.PermissionTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysPermissionTEntity;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.vo.PermissionTreeVO;
import com.wms.vo.PermissionVO;

/**
 * 菜单 业务层
 * 
 */
public interface ISysPermissionService {
	
	List<SysPermissionTEntity> findPermission(PermissionTypeEnum permissionType);
	
	List<SysPermissionTEntity> findPermission(PermissionTypeEnum permissionType, String locale);
	
	List<SysPermissionTEntity> findUserPermission(SysUserTEntity user);
	
	SysPermissionTEntity find(SysPermissionTEntity per);
	
	List<SysPermissionTEntity> findUserPermission(SysUserTEntity user, PermissionTypeEnum permissionType);
	
	List<SysPermissionTEntity> findUserPermission(SysUserTEntity user, PermissionTypeEnum permissionType, String locale);
	
	List<PermissionVO> find(PageRequest request) throws BusinessServiceException;
	
	Boolean modify(AjaxRequest<List<PermissionVO>> request) throws BusinessServiceException;
	
	Boolean add(AjaxRequest<List<PermissionVO>> request) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<List<PermissionVO>> request) throws BusinessServiceException;
	
	SysPermissionTEntity insertTop(List<SysPermissionTEntity> list) throws BusinessServiceException;
	
}
