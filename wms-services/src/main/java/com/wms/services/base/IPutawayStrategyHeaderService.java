package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.PutawayStrategyTEntity;

import java.util.List;

/**
 * @description: 上架策略服务类
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-03 09:28
 **/

public interface IPutawayStrategyHeaderService {
    List<PutawayStrategyTEntity> find(PageRequest request) throws BusinessServiceException;

    Boolean modify(AjaxRequest<List<PutawayStrategyTEntity>> request) throws BusinessServiceException;

    Boolean add(AjaxRequest<List<PutawayStrategyTEntity>> request) throws BusinessServiceException;

    Boolean delete(AjaxRequest<List<PutawayStrategyTEntity>> request) throws BusinessServiceException;

	PutawayStrategyTEntity find(PutawayStrategyTEntity putawayStrategyEntity) throws BusinessServiceException;
}
