package com.wms.services.outbound;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.OutboundStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.OutboundDetailTEntity;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.vo.outbound.OutboundDetailVO;
import com.wms.vo.outbound.OutboundVO;

import java.util.List;
import java.util.Set;

public interface IOutboundDetailService {

	List<OutboundDetailVO> find(PageRequest request) throws BusinessServiceException;
	
	List<OutboundDetailTEntity> findByHeaderId(OutboundDetailTEntity outbound) throws BusinessServiceException;
	
	List<OutboundDetailTEntity> findByHeaderIds(OutboundDetailTEntity outbound, Set<Long> ids) throws BusinessServiceException;
	
	OutboundDetailTEntity find(OutboundDetailTEntity outbound) throws BusinessServiceException;

	/**
	 * 前台新增和修改调用
	 * @param request
	 * @return
	 * @throws BusinessServiceException
	 */
	Boolean save(AjaxRequest<OutboundVO> request) throws BusinessServiceException;
	
	Boolean modify(OutboundDetailVO outbound, Boolean replaceFlag) throws BusinessServiceException;
	
	Boolean add(OutboundDetailVO outbound) throws BusinessServiceException;
	
	Boolean delete(AjaxRequest<List<OutboundDetailTEntity>> request) throws BusinessServiceException;
	
	Boolean delete(OutboundHeaderTEntity header) throws BusinessServiceException;
	
	Boolean allocate(List<OutboundDetailVO> detail) throws BusinessServiceException;
	
	Boolean unAllocate(List<OutboundDetailVO> detail) throws BusinessServiceException;
	
	Boolean pick(List<OutboundDetailVO> detail) throws BusinessServiceException;
	
	Boolean unPick(List<OutboundDetailVO> detail) throws BusinessServiceException;
	
	Boolean ship(List<OutboundDetailVO> detail) throws BusinessServiceException;

	Boolean release(List<OutboundDetailVO> detail) throws BusinessServiceException;

	
	OutboundStatusEnum outboundDetailStatus(OutboundDetailTEntity detail, Boolean updateFlag) throws BusinessServiceException;

	/**
	 * @Description 更新时不做状态校验，取消、关闭时用
	 * @param detail
	 * @return
	 * @throws BusinessServiceException
	 */
	Boolean modify(OutboundDetailTEntity detail) throws BusinessServiceException;

	/**
	* @Description:  OMS生成并操作出库单
	* @Param: [request]
	* @return: java.lang.Boolean
	* @Author: pengzhen@cmhit.com
	* @Date: 2019/8/29
	*/
	Boolean saveFromOms(OutboundVO outboundVO) throws BusinessServiceException;
}
