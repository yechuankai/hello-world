package com.wms.services.appointment;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.AppointmentTEntity;

/**
 * 预约管理
 * @author yechuankai.chnet
 *
 */
public interface IAppointmentService {

    List<AppointmentTEntity> find(PageRequest request) throws BusinessServiceException;
    
    Boolean modify(AjaxRequest<List<AppointmentTEntity>> request) throws BusinessServiceException;

    Boolean add(AjaxRequest<List<AppointmentTEntity>> request) throws BusinessServiceException;

    Boolean delete(AjaxRequest<List<AppointmentTEntity>> request) throws BusinessServiceException;
    
    Boolean cancel(AjaxRequest<List<AppointmentTEntity>> request) throws BusinessServiceException;

    AppointmentTEntity find(AppointmentTEntity p) throws BusinessServiceException;
    
    List<AppointmentTEntity> findByBillNumber(AppointmentTEntity p) throws BusinessServiceException;
    
    List<AppointmentTEntity> findByPlatForm(AppointmentTEntity p) throws BusinessServiceException;

}
