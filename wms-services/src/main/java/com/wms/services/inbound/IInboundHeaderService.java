package com.wms.services.inbound;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.InboundStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.vo.inbound.InboundVO;

import java.util.List;
import java.util.Set;

public interface IInboundHeaderService {

	List<InboundHeaderTEntity> find(PageRequest request) throws BusinessServiceException;
	
	InboundVO find(InboundVO inbound) throws BusinessServiceException;
	
	InboundHeaderTEntity find(InboundHeaderTEntity inbound) throws BusinessServiceException;
	
	List<InboundHeaderTEntity> find(InboundHeaderTEntity inbound, Set<Long> ids) throws BusinessServiceException;
	
	List<InboundHeaderTEntity> findByNumber(InboundHeaderTEntity inbound, Set<String> billNumbers) throws BusinessServiceException;
	
	InboundVO save(AjaxRequest<InboundVO> request) throws BusinessServiceException;
	
	Boolean modify(InboundVO inbound) throws BusinessServiceException;

	/**
	 * @Description 更新时不做状态校验
	 * @param inbound
	 * @return
	 * @throws BusinessServiceException
	 */
	Boolean modify(InboundHeaderTEntity inbound) throws BusinessServiceException;
	
	Boolean add(InboundVO inbound) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<List<InboundHeaderTEntity>> request) throws BusinessServiceException;
	
	Boolean receiveAll(AjaxRequest<List<InboundVO>> request) throws BusinessServiceException;
	
	Boolean unReceive(AjaxRequest<List<InboundVO>> request) throws BusinessServiceException;
	
	InboundStatusEnum inboundStatus(InboundHeaderTEntity header, Boolean updateFlag) throws BusinessServiceException;

	Boolean cancel(AjaxRequest<List<InboundHeaderTEntity>> request) throws BusinessServiceException;

	Boolean close(AjaxRequest<List<InboundHeaderTEntity>> request) throws BusinessServiceException;

	Long createPutaway(AjaxRequest<List<InboundVO>> request) throws BusinessServiceException;

	/** 
	* @Description: OMS生成并操作入库单 
	* @Param: [request] 
	* @return: com.wms.vo.inbound.InboundVO 
	* @Author: pengzhen@cmhit.com 
	* @Date: 2019/8/20
	*/ 
	InboundVO saveFromOms(AjaxRequest<InboundVO> request) throws BusinessServiceException;

	/** 
	* @Description: 订单系统使用，查询不带仓库Id 
	* @Param: [inbound] 
	* @return: com.wms.vo.inbound.InboundVO 
	* @Author: pengzhen@cmhit.com 
	* @Date: 2019/8/21 
	*/ 
	InboundVO findById(InboundVO inbound) throws BusinessServiceException;
}
