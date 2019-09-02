package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.AllocateStrategyTEntity;

import java.util.List;

/**
 * @description: 分配策略主表服务类
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-26 17:55
 **/

public interface IAllocateStrategyHeaderService {

    List<AllocateStrategyTEntity> find(PageRequest request) throws BusinessServiceException;
    
    AllocateStrategyTEntity find(AllocateStrategyTEntity allocate) throws BusinessServiceException;

    Boolean modify(AjaxRequest<List<AllocateStrategyTEntity>> request) throws BusinessServiceException;

    Boolean add(AjaxRequest<List<AllocateStrategyTEntity>> request) throws BusinessServiceException;

    Boolean delete(AjaxRequest<List<AllocateStrategyTEntity>> request) throws BusinessServiceException;
}
