package com.wms.services.inventory;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.CountStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.InventoryCountDetailTEntity;

/**
 * 盘点明细
 * @author yechuankai.chnet
 */
public interface IInventoryCountDetailService {
	
	List<InventoryCountDetailTEntity> find(PageRequest request) throws BusinessServiceException;
	
	/**
	 * 按明细创建复盘任务
	 * @param request
	 * @return
	 * @throws BusinessServiceException
	 */
	Long createReplayCount(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException;
	
	InventoryCountDetailTEntity find(InventoryCountDetailTEntity detail) throws BusinessServiceException;
	
	List<InventoryCountDetailTEntity> findByHeaderId(InventoryCountDetailTEntity detail, CountStatusEnum ... countStatusEnums) throws BusinessServiceException;
	
	Boolean add(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException;
	
	/**
	 * PC端修改，仅可修改盘点数量字段
	 * @param request
	 * @return
	 * @throws BusinessServiceException
	 */
	Boolean moidfy(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException;
	
	/**
	 * 更新复盘标识（当前明细行状态）
	 * @param request
	 * @return
	 * @throws BusinessServiceException
	 */
	Boolean moidfyStatus(AjaxRequest<List<InventoryCountDetailTEntity>> request, CountStatusEnum status) throws BusinessServiceException;
	
	Boolean cancel(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException;
	
	/**
	 * 复盘任务
	 * @param detail
	 * @param countStatusEnums
	 * @return
	 * @throws BusinessServiceException
	 */
	List<InventoryCountDetailTEntity> replayCountDetail(InventoryCountDetailTEntity detail, CountStatusEnum ... countStatusEnums ) throws BusinessServiceException;

	/**
	 * 提交盘点，盘点完成也可继续修改（PC端）
	 * @param request
	 * @return
	 * @throws BusinessServiceException
	 */
	Boolean submit(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException;
	
	/**
	 * 过账
	 */
	Boolean post(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException;
}
