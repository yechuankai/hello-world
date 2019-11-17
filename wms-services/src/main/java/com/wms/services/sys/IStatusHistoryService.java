package com.wms.services.sys;

import java.util.List;
import java.util.Set;

import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.StatusHistoryTEntity;

/**
 * @description: 状态改变历史
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-20 15:50
 **/

public interface IStatusHistoryService {

    Boolean add(StatusHistoryTEntity statusHistory) throws BusinessServiceException;
    
    StatusHistoryTEntity find(StatusHistoryTEntity statusHistory) throws BusinessServiceException;
    
    List<StatusHistoryTEntity> findBySourceNumber(StatusHistoryTEntity statusHistory) throws BusinessServiceException;
    
    List<StatusHistoryTEntity> findByNotice1(StatusHistoryTEntity statusHistory, Set<String> status) throws BusinessServiceException;
    
    Boolean modify(StatusHistoryTEntity statusHistory) throws BusinessServiceException;
}
