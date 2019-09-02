package com.wms.services.sys;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysOrderNumberTEntity;

import java.util.List;

/**
 * @description: 单号规则
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-05 15:28
 **/

public interface ISysOrderNumberService {

    List<SysOrderNumberTEntity> find(PageRequest request) throws BusinessServiceException;

    Boolean modify(AjaxRequest<List<SysOrderNumberTEntity>> request) throws BusinessServiceException;
}
