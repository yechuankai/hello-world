package com.wms.services.appointment;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.PlatFormStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.PlatformTEntity;

/**
 * 泊位管理
 * @author yechuankai.chnet
 *
 */
public interface IPlatFormService {

    List<PlatformTEntity> find(PageRequest request) throws BusinessServiceException;
    
    Boolean modify(AjaxRequest<List<PlatformTEntity>> request) throws BusinessServiceException;
    
    Boolean modifyStats(AjaxRequest<PlatformTEntity> request, PlatFormStatusEnum status) throws BusinessServiceException;

    Boolean add(AjaxRequest<List<PlatformTEntity>> request) throws BusinessServiceException;

    Boolean delete(AjaxRequest<List<PlatformTEntity>> request) throws BusinessServiceException;

    PlatformTEntity find(PlatformTEntity zone) throws BusinessServiceException;

}
