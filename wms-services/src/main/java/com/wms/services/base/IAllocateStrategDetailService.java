package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.AllocateStrategyDetailTEntity;
import com.wms.entity.auto.AllocateStrategyTEntity;

import java.util.List;

/**
 * @description: 分配策略明细服务类
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-27 11:18
 **/

public interface IAllocateStrategDetailService {

    List<AllocateStrategyDetailTEntity> find(PageRequest request) throws BusinessServiceException;

    Boolean modify(AjaxRequest<List<AllocateStrategyDetailTEntity>> request) throws BusinessServiceException;

    Boolean add(AjaxRequest<List<AllocateStrategyDetailTEntity>> request) throws BusinessServiceException;

    Boolean delete(AjaxRequest<List<AllocateStrategyDetailTEntity>> request) throws BusinessServiceException;

    List<AllocateStrategyDetailTEntity> findByAllocateStrategy(AllocateStrategyTEntity allocateStrategy) throws BusinessServiceException;

    Boolean delete(List<AllocateStrategyDetailTEntity> detailList) throws BusinessServiceException;
}
