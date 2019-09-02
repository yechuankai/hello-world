package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.AllocateStrategyTEntity;
import com.wms.entity.auto.AreaTEntity;

import java.util.List;

public interface IAreaHeaderService {

    List<AreaTEntity> find(PageRequest request) throws BusinessServiceException;

    AreaTEntity find(AreaTEntity area)  throws BusinessServiceException;

    Boolean modify(AjaxRequest<List<AreaTEntity>> request) throws BusinessServiceException;

    Boolean add(AjaxRequest<List<AreaTEntity>> request) throws BusinessServiceException;

    Boolean delete(AjaxRequest<List<AreaTEntity>> request) throws BusinessServiceException;

    List<AreaTEntity> findAreaAvailable( AllocateStrategyTEntity allocateStrategyDetail) throws BusinessServiceException;

}
