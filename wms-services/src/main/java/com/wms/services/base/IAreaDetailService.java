package com.wms.services.base;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.AreaDetailTEntity;
import com.wms.entity.auto.AreaTEntity;

import java.util.List;

/**
 * @description:
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-24 13:53
 **/

public interface IAreaDetailService {

    List<AreaDetailTEntity> find(PageRequest request) throws BusinessServiceException;

    Boolean modify(AjaxRequest<List<AreaDetailTEntity>> request) throws BusinessServiceException;

    Boolean add(AjaxRequest<List<AreaDetailTEntity>> request) throws BusinessServiceException;

    Boolean delete(AjaxRequest<List<AreaDetailTEntity>> request) throws BusinessServiceException;

    List<AreaDetailTEntity> findByArea(AreaTEntity area) throws BusinessServiceException;

    Boolean delete(List<AreaDetailTEntity> detailList) throws BusinessServiceException;

}
