package com.wms.services.inventory;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.CountStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.InventoryCountDetailTEntity;
import com.wms.entity.auto.InventoryCountHeaderTEntity;
import com.wms.entity.auto.InventoryCountRequestTEntity;

/**
 * 盘点单头
 * @author yechuankai.chnet
 * 盘点单头通过盘点请求生成
 */
public interface IInventoryCountHeaderService {
	
	List<InventoryCountHeaderTEntity> find(PageRequest request) throws BusinessServiceException;
	
	InventoryCountHeaderTEntity find(InventoryCountHeaderTEntity header) throws BusinessServiceException;
	
	/**
	 * 通过盘点请求生成盘点单，返回生成的明细数
	 */
	Long createInventoryCount(AjaxRequest<InventoryCountRequestTEntity> request) throws BusinessServiceException;
	
	/**
	 * 产生复盘任务
	 */
	Long createReplayCount(AjaxRequest<InventoryCountHeaderTEntity> request) throws BusinessServiceException;
	
	/**
	 * 按明细产生复盘任务
	 */
	Long createReplayCount(AjaxRequest<InventoryCountHeaderTEntity> request, List<InventoryCountDetailTEntity> detailList) throws BusinessServiceException;
	
	/**
	 * 查询复盘任务
	 */
	List<InventoryCountHeaderTEntity> replayCount(InventoryCountHeaderTEntity header, CountStatusEnum ... countStatusEnums ) throws BusinessServiceException;
	
	Boolean add(AjaxRequest<InventoryCountHeaderTEntity> request) throws BusinessServiceException;
	
	String countStatus(InventoryCountHeaderTEntity header, Boolean updateFlag) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<List<InventoryCountHeaderTEntity>> request) throws BusinessServiceException;
	
	Boolean cancel(AjaxRequest<List<InventoryCountHeaderTEntity>> request) throws BusinessServiceException;
	
	/**
	 * 盘点过账，调整库存
	 */
	Boolean post(AjaxRequest<List<InventoryCountHeaderTEntity>> request) throws BusinessServiceException;
}
