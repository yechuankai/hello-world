package com.wms.services.inbound;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.InboundStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.InboundDetailTEntity;
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.vo.inbound.InboundDetailVO;
import com.wms.vo.inbound.InboundVO;

import java.util.List;
import java.util.Set;

public interface IInboundDetailService {

	List<InboundDetailVO> find(PageRequest request) throws BusinessServiceException;
		
	List<InboundDetailTEntity> findByHeaderId(InboundDetailTEntity inbound) throws BusinessServiceException;
	
	List<InboundDetailTEntity> findByHeaderId(Long headerId) throws BusinessServiceException;
	
	Long findMaxLine(InboundDetailTEntity inbound) throws BusinessServiceException;
	
	List<InboundDetailVO> findExpected(InboundDetailTEntity inbound) throws BusinessServiceException;
	
	InboundDetailTEntity find(InboundDetailTEntity inbound) throws BusinessServiceException;

	List<InboundDetailTEntity> findByHeaderIds(InboundDetailTEntity inbound, Set<Long> ids) throws BusinessServiceException;
	
	Boolean save(AjaxRequest<InboundVO> request) throws BusinessServiceException;
	
	Boolean modify(InboundDetailVO inbound) throws BusinessServiceException;
	
	Boolean add(InboundDetailVO inbound) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<List<InboundDetailTEntity>> request) throws BusinessServiceException;
	
	Boolean delete(InboundHeaderTEntity header) throws BusinessServiceException;
	
	Boolean receive(List<InboundDetailVO> detail) throws BusinessServiceException;
	
	Boolean unReceive(AjaxRequest<List<InboundDetailVO>> request) throws BusinessServiceException;
	
	Boolean receive(AjaxRequest<List<InboundDetailVO>> request) throws BusinessServiceException;
	
	Boolean receiveAll(AjaxRequest<List<InboundDetailVO>> request) throws BusinessServiceException;
	
	InboundStatusEnum inboundDetailStatus(InboundDetailTEntity detail, Boolean updateFlag) throws BusinessServiceException;

	Long createPutaway(List<InboundDetailVO> detail) throws BusinessServiceException;

	/**
	 * @Description 更新时不做状态校验，取消、关闭时用
	 * @param detail
	 * @return
	 * @throws BusinessServiceException
	 */
	Boolean modify(InboundDetailTEntity detail) throws BusinessServiceException;

	/** 
	* @Description: OMS生成并操作入库单 
	* @Param: [request] 
	* @return: java.lang.Boolean 
	* @Author: pengzhen@cmhit.com 
	* @Date: 2019/8/20 
	*/ 
	Boolean saveFromOms(AjaxRequest<InboundVO> request) throws BusinessServiceException;

}
