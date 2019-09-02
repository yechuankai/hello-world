package com.wms.services.sys;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysCompanysTEntity;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.entity.auto.SysWarehousesTEntity;

public interface ISysCompanyService {
	
	 List<SysCompanysTEntity> find(PageRequest request) throws BusinessServiceException;
	 
	 List<SysCompanysTEntity> findAll();
	
	 Boolean modify(AjaxRequest<List<SysCompanysTEntity>> request) throws BusinessServiceException;
	
	 Boolean add(AjaxRequest<List<SysCompanysTEntity>> request) throws BusinessServiceException;
	
	 Boolean delete(AjaxRequest<List<SysCompanysTEntity>> request) throws BusinessServiceException;
	
	 SysCompanysTEntity findById(Long companyId) throws BusinessServiceException;
	 
	 List<SysCompanysTEntity> findRoleCompany(List<SysRoleTEntity> roles) throws BusinessServiceException;
	 
	 List<SysCompanysTEntity> findRoleAvailable(SysRoleTEntity role) throws BusinessServiceException;
	 
	 List<SysCompanysTEntity> findUserCompany(SysUserTEntity user) throws BusinessServiceException;
	
}
