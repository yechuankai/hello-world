package com.wms.services.outbound;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.AllocateShortTEntity;

/**
 * 拣货缺量记录表
 * @author yechuankai.chnet
 *
 */
public interface IAllocateShortService {
	List<AllocateShortTEntity> find(PageRequest request) throws BusinessServiceException;
	
	Boolean add(AjaxRequest<AllocateShortTEntity> request) throws BusinessServiceException;
}
