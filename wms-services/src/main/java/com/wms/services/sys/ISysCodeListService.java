package com.wms.services.sys;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysCodelistTEntity;

import java.util.List;

/**
 * @description: 数据字典主表服务类
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-25 16:43
 **/

public interface ISysCodeListService {
    List<SysCodelistTEntity> find(PageRequest request) throws BusinessServiceException;

    Boolean modify(AjaxRequest<List<SysCodelistTEntity>> request) throws BusinessServiceException;

    Boolean add(AjaxRequest<List<SysCodelistTEntity>> request) throws BusinessServiceException;

    Boolean delete(AjaxRequest<List<SysCodelistTEntity>> request) throws BusinessServiceException;
}
