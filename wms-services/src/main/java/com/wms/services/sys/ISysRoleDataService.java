package com.wms.services.sys;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.enums.RoleDataTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysRoleDataTEntity;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.vo.RoleCompanyVO;
import com.wms.vo.RoleWarehouseVO;


public interface ISysRoleDataService {
	
	public List<SysRoleDataTEntity> findRoleData(List<SysRoleTEntity> roles, RoleDataTypeEnum type);
	
	public Boolean add(AjaxRequest<List<SysRoleDataTEntity>> request) throws BusinessServiceException;
	
	public Boolean delete(AjaxRequest<List<SysRoleDataTEntity>> request) throws BusinessServiceException;
	
	public List<RoleWarehouseVO> findRoleWarehouse(List<SysRoleTEntity> roles) throws BusinessServiceException;
	
	public List<RoleCompanyVO> findRoleCompany(List<SysRoleTEntity> roles) throws BusinessServiceException;
	
}
