package com.wms.services.sys;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.entity.auto.SysWarehousesTEntity;
import com.wms.vo.WarehouseVO;

import java.util.List;
import java.util.Set;

public interface ISysWarehouseService {

	 List<SysWarehousesTEntity> findAll();
	
	 List<SysWarehousesTEntity> findRoleAvailable(SysRoleTEntity role) throws BusinessServiceException;
	
	 SysWarehousesTEntity findById(Long warehouseId);
	
	 SysWarehousesTEntity findByCode(String code);

	List<SysWarehousesTEntity> findByIds(Set<Long> ids) throws BusinessServiceException;
	
	 List<SysWarehousesTEntity> findUserWarehouse(SysUserTEntity user) throws BusinessServiceException;
	
	 List<SysWarehousesTEntity> findRoleWarehouse(List<SysRoleTEntity> roles) throws BusinessServiceException;
	
	 List<SysWarehousesTEntity> find(PageRequest request) throws BusinessServiceException;
	
	 Boolean modify(AjaxRequest<List<SysWarehousesTEntity>> request) throws BusinessServiceException;
	
	 Boolean add(AjaxRequest<List<SysWarehousesTEntity>> request) throws BusinessServiceException;
	
	 Boolean delete(AjaxRequest<List<SysWarehousesTEntity>> request) throws BusinessServiceException;
	
	 Boolean init(AjaxRequest<WarehouseVO> request) throws BusinessServiceException;
	
}
