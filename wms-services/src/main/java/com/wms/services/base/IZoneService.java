package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.ZoneTEntity;

import java.util.List;
import java.util.Set;

public interface IZoneService {

    List<ZoneTEntity> find(PageRequest request) throws BusinessServiceException;
    
    List<ZoneTEntity> find(ZoneTEntity zone, Set<Long> ids) throws BusinessServiceException;

    Boolean modify(AjaxRequest<List<ZoneTEntity>> request) throws BusinessServiceException;

    Boolean add(AjaxRequest<List<ZoneTEntity>> request) throws BusinessServiceException;

    Boolean delete(AjaxRequest<List<ZoneTEntity>> request) throws BusinessServiceException;

    ZoneTEntity find(ZoneTEntity zone) throws BusinessServiceException;

    List<ZoneTEntity> findByZoneCodes(ZoneTEntity sku, Set<String> codes) throws BusinessServiceException;
}
