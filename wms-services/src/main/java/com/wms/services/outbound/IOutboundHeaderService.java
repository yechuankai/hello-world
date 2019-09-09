package com.wms.services.outbound;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.OutboundProcessStatusEnum;
import com.wms.common.enums.OutboundStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.vo.outbound.OutboundVO;

import java.util.List;
import java.util.Set;

public interface IOutboundHeaderService {
	
	List<OutboundHeaderTEntity> find(PageRequest request) throws BusinessServiceException;
	
	OutboundVO find(OutboundVO outbound) throws BusinessServiceException;
	
	OutboundHeaderTEntity find(OutboundHeaderTEntity outbound) throws BusinessServiceException;
	
	List<OutboundHeaderTEntity> find(OutboundHeaderTEntity outbound, Set<Long> ids) throws BusinessServiceException;
	
	OutboundVO save(AjaxRequest<OutboundVO> request) throws BusinessServiceException;
	
	Boolean modify(OutboundVO outbound) throws BusinessServiceException;
	/**
	 * @Description 更新时不做状态校验，取消时用
	 * @param inbound
	 * @return
	 * @throws BusinessServiceException
	 */
	Boolean modify(OutboundHeaderTEntity inbound) throws BusinessServiceException;

	Boolean add(OutboundVO outbound) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<List<OutboundHeaderTEntity>> request) throws BusinessServiceException;

	OutboundStatusEnum outboundStatus(OutboundHeaderTEntity header, Boolean updateFlag) throws BusinessServiceException;
	
	Boolean processStatus(OutboundHeaderTEntity header, OutboundProcessStatusEnum processStatus) throws BusinessServiceException;
	
	Boolean allocate(AjaxRequest<List<OutboundVO>> request) throws BusinessServiceException;
	
	Boolean unAllocate(AjaxRequest<List<OutboundVO>> request) throws BusinessServiceException;
	
	Boolean pick(AjaxRequest<List<OutboundVO>> request) throws BusinessServiceException;
	
	Boolean unPick(AjaxRequest<List<OutboundVO>> request) throws BusinessServiceException;

	Boolean ship(AjaxRequest<List<OutboundVO>> request) throws BusinessServiceException;

	Boolean release(AjaxRequest<List<OutboundVO>> request) throws BusinessServiceException;

	Boolean cancel(AjaxRequest<List<OutboundHeaderTEntity>> request) throws BusinessServiceException;

	/**
	* @Description: OMS生成并操作出库单
	 * @Param: [request]
	* @return: com.wms.vo.outbound.OutboundVO
	* @Author: pengzhen@cmhit.com
	* @Date: 2019/8/27
	*/
    OutboundVO saveFromOms(AjaxRequest<OutboundVO> request) throws BusinessServiceException;
}
