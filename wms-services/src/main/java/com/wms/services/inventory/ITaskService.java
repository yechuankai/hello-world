package com.wms.services.inventory;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.TaskStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.TaskDetailTEntity;
import com.wms.vo.TaskDetailVO;

import java.util.List;

/**
 * @description: 上架/拣货任务服务类
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-04 14:35
 **/

public interface ITaskService {
	
    List<TaskDetailTEntity> find(PageRequest request) throws BusinessServiceException;
    
    List<TaskDetailVO> findUnLoad(PageRequest request) throws BusinessServiceException;
    
    List<TaskDetailVO> findLoad(PageRequest request) throws BusinessServiceException;
    
    TaskDetailTEntity findBySourceNumber(TaskDetailTEntity task) throws BusinessServiceException;
    
    List<TaskDetailTEntity> findBySourceBillNumber(TaskDetailTEntity task) throws BusinessServiceException;

    List<TaskDetailTEntity> find(TaskDetailTEntity task) throws BusinessServiceException;
    
    List<TaskDetailTEntity> findByFromLpn(TaskDetailTEntity task, TaskStatusEnum ...taskStatusEnums) throws BusinessServiceException;

    Boolean add(TaskDetailTEntity task) throws BusinessServiceException;
    
    Boolean modify(TaskDetailTEntity task) throws BusinessServiceException;

    Boolean modify(AjaxRequest<List<TaskDetailTEntity>> request) throws BusinessServiceException;

    Boolean cancel(AjaxRequest<List<TaskDetailTEntity>> request) throws BusinessServiceException;
    
    Boolean delete(AjaxRequest<List<TaskDetailTEntity>> request) throws BusinessServiceException;

    /** 
    * @Description: 上架确认 
    * @Param: [request] 
    * @return: java.lang.Boolean 
    * @Author: pengzhen@cmhit.com 
    * @Date: 2019/9/4 
    */ 
    Boolean putawayConfirm(AjaxRequest<List<TaskDetailTEntity>> request) throws BusinessServiceException;
}
