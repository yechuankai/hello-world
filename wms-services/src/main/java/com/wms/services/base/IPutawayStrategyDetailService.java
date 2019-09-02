package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.PutawayStrategyDetailTEntity;
import com.wms.entity.auto.PutawayStrategyTEntity;

import java.util.List;

/**
 * @description: 上架策略明细服务类
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-03 10:40
 **/

public interface IPutawayStrategyDetailService {
    List<PutawayStrategyDetailTEntity> find(PageRequest request) throws BusinessServiceException;

    Boolean modify(AjaxRequest<List<PutawayStrategyDetailTEntity>> request) throws BusinessServiceException;

    Boolean add(AjaxRequest<List<PutawayStrategyDetailTEntity>> request) throws BusinessServiceException;

    Boolean delete(AjaxRequest<List<PutawayStrategyDetailTEntity>> request) throws BusinessServiceException;

    List<PutawayStrategyDetailTEntity> findByPutawayStrategy(PutawayStrategyTEntity putawayStrategy) throws BusinessServiceException;

    Boolean delete(List<PutawayStrategyDetailTEntity> detailList) throws BusinessServiceException;
}
