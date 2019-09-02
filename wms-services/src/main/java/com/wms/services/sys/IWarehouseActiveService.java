package com.wms.services.sys;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.WarehouseActiveTEntity;
import com.wms.vo.WarehouseActiveVO;

import java.util.List;
import java.util.Set;

public interface IWarehouseActiveService {

	 List<WarehouseActiveTEntity> findAll() throws BusinessServiceException;
	
	 WarehouseActiveTEntity find(Long companyId, Long warehouseId) throws BusinessServiceException;
	 
	 List<WarehouseActiveTEntity> find(Set<Long> companyId, Set<Long> warehouseId) throws BusinessServiceException;

	 /** 
	 * @Description: OMS根据公司查询公司底下已经激活的仓库
	 * @Param: [request] 
	 * @return: java.util.List<com.wms.entity.auto.WarehouseActiveTEntity> 
	 * @Author: pengzhen@cmhit.com 
	 * @Date: 2019/8/27 
	 */ 
	List<WarehouseActiveVO> find(PageRequest request) throws BusinessServiceException;
	 
	 Boolean isActive(Long companyId, Long warehouseId);
	
	 Boolean add(AjaxRequest<List<WarehouseActiveTEntity>> request) throws BusinessServiceException;
	
	 Boolean modify(AjaxRequest<List<WarehouseActiveTEntity>> request) throws BusinessServiceException;
	 
	 Boolean active(AjaxRequest<List<WarehouseActiveTEntity>> request) throws BusinessServiceException;
}
