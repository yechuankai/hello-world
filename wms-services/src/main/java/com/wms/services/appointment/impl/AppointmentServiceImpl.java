package com.wms.services.appointment.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.AppointmentStatusEnum;
import com.wms.common.enums.AppointmentTypeEnum;
import com.wms.common.enums.InboundStatusEnum;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.enums.OutboundStatusEnum;
import com.wms.common.enums.PlatFormStatusEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.DateUtils;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IAppointmentTDao;
import com.wms.dao.auto.IInboundCancelHeaderTDao;
import com.wms.dao.example.AppointmentTExample;
import com.wms.entity.auto.AppointmentTEntity;
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.PlatformTEntity;
import com.wms.services.appointment.IAppointmentService;
import com.wms.services.appointment.IPlatFormService;
import com.wms.services.inbound.IInboundHeaderService;
import com.wms.services.inbound.impl.InboundHeaderServiceImpl;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.services.outbound.impl.OutboundHeaderServiceImpl;

/**
 * 泊位管理
 * @author yechuankai.chnet
 *
 */
@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private IAppointmentTDao appointDao;
    
    @Autowired
    private IPlatFormService platFormService;
    
    @Autowired
    private IOutboundHeaderService outboundService;
    
    @Autowired
    private IInboundHeaderService inboundService;

    @Override
    public List<AppointmentTEntity> find(PageRequest request) throws BusinessServiceException {
        AppointmentTExample AppointmentTExample = new AppointmentTExample();
        AppointmentTExample.Criteria AppointmentTExampleCriteria = AppointmentTExample.createCriteria();

        //转换查询方法
        ExampleUtils.create(AppointmentTEntity.Column.class, AppointmentTExample.Criterion.class)
                .criteria(AppointmentTExampleCriteria)
                .data(request)
                .build(request)
                .orderby(AppointmentTExample);
        AppointmentTExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return appointDao.selectByExample(AppointmentTExample);
    }
    

    @Override
    @Transactional
    public Boolean modify(AjaxRequest<List<AppointmentTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record update.");
        }

        List<AppointmentTEntity> list = request.getData();

        for (AppointmentTEntity p : list) {

        	p.setWarehouseId(request.getWarehouseId());
        	p.setCompanyId(request.getCompanyId());
        	
        	validate(p);
        	
            AppointmentTEntity update = AppointmentTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .status(p.getStatus())
                    .platformCode(p.getPlatformCode())
                    .platformId(p.getPlatformId())
                    .arrivalDate(p.getArrivalDate())
                    .carDriver(p.getCarDriver())
                    .carNumber(p.getCarNumber())
                    .carDriverPhone(p.getCarDriverPhone())
                    .build();

            AppointmentTExample example = new AppointmentTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andAppointmentIdEqualTo(p.getAppointmentId());

            int row = appointDao.updateWithVersionByExampleSelective(p.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
            
          //更新月台状态
          modifyPlatFormStatus(new AjaxRequest<AppointmentTEntity>(p, request));
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean add(AjaxRequest<List<AppointmentTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record add.");
        }
        List<AppointmentTEntity> list = request.getData();
        for (AppointmentTEntity p : list) {
        	p.setWarehouseId(request.getWarehouseId());
        	p.setCompanyId(request.getCompanyId());
        	
        	validate(p);
        	
        	p.setAppointmentCode(KeyUtils.getOrderNumber(request.getCompanyId(), request.getWarehouseId(), OrderNumberTypeEnum.Appointment));
        	p.setAppointmentId(KeyUtils.getUID());
        	p.setCreateBy(request.getUserName());
        	p.setUpdateBy(request.getUserName());
        	p.setCreateTime(new Date());
        	p.setUpdateTime(new Date());
        	
            int row = appointDao.insertSelective(p);
            if (row == 0) {
                throw new BusinessServiceException("record add error.");
            }
            //更新月台状态
            modifyPlatFormStatus(new AjaxRequest<AppointmentTEntity>(p, request));
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(AjaxRequest<List<AppointmentTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record delete.");
        }
        List<AppointmentTEntity> list = request.getData();

        for (AppointmentTEntity p : list) {
        	p.setWarehouseId(request.getWarehouseId());
        	p.setCompanyId(request.getCompanyId());
        	
        	AppointmentTEntity selectAppointment = find(p);
        	if (!AppointmentStatusEnum.Appointment.getCode().equals(selectAppointment.getStatus())) 
        		throw new BusinessServiceException("AppointmentServiceImpl", "appointment.status.not.process" , new Object[] {selectAppointment.getAppointmentCode()});
        	
            AppointmentTEntity update = AppointmentTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .delFlag(YesNoEnum.Yes.getCode())
                    .build();

            AppointmentTExample example = new AppointmentTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(p.getWarehouseId())
                    .andCompanyIdEqualTo(p.getCompanyId())
                    .andAppointmentIdEqualTo(p.getAppointmentId());

            int row = appointDao.updateWithVersionByExampleSelective(p.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("delete update error.");
            }
            
            PlatformTEntity plat = PlatformTEntity.builder()
					.platformId(p.getPlatformId())
					.build();
			platFormService.modifyStats(new AjaxRequest<PlatformTEntity>(plat, request), PlatFormStatusEnum.Idle);
            
        }
        return Boolean.TRUE;
    }
    
    @Override
    @Transactional
    public Boolean cancel(AjaxRequest<List<AppointmentTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record delete.");
        }
        List<AppointmentTEntity> list = request.getData();

        for (AppointmentTEntity p : list) {
            AppointmentTEntity update = AppointmentTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .status(AppointmentStatusEnum.Cancel.getCode())
                    .build();

            AppointmentTExample example = new AppointmentTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(p.getWarehouseId())
                    .andCompanyIdEqualTo(p.getCompanyId())
                    .andAppointmentIdEqualTo(p.getAppointmentId());

            int row = appointDao.updateWithVersionByExampleSelective(p.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("cancel record error.");
            }
            
            PlatformTEntity plat = PlatformTEntity.builder()
					.platformId(p.getPlatformId())
					.build();
			platFormService.modifyStats(new AjaxRequest<PlatformTEntity>(plat, request), PlatFormStatusEnum.Idle);
        }
        return Boolean.TRUE;
    }

    @Override
    public AppointmentTEntity find(AppointmentTEntity p) throws BusinessServiceException {
        AppointmentTExample TExample = new AppointmentTExample();
        AppointmentTExample.Criteria criteria = TExample.createCriteria();
        criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(p.getWarehouseId())
                .andCompanyIdEqualTo(p.getCompanyId());
        int conditionCount = 0;
        if (null != p.getAppointmentId()) {
            criteria.andAppointmentIdEqualTo(p.getAppointmentId());
            conditionCount++;
        }
        if (null != p.getAppointmentCode()) {
            criteria.andAppointmentCodeEqualTo(p.getAppointmentCode());
            conditionCount++;
        }
        if (conditionCount == 0) {
            return null;
        }
        AppointmentTEntity w = appointDao.selectOneByExample(TExample);
        if (w == null) {
            throw new BusinessServiceException("PlatFormServiceImpl", "appointment.record.not.exists", null);
        }
        return w;
    }


    /**
     * 查询非取消状态的预约单据
     */
	@Override
	public List<AppointmentTEntity> findByBillNumber(AppointmentTEntity p) throws BusinessServiceException {
		 AppointmentTExample TExample = new AppointmentTExample();
	        AppointmentTExample.Criteria criteria = TExample.createCriteria();
	        criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
	        		.andStatusNotEqualTo(AppointmentStatusEnum.Cancel.getCode())
	                .andWarehouseIdEqualTo(p.getWarehouseId())
	                .andCompanyIdEqualTo(p.getCompanyId())
	                .andSourceBillNumberEqualTo(p.getSourceBillNumber());
	        if (StringUtils.isNotEmpty(p.getType()))
	        	criteria.andTypeEqualTo(p.getType());
	        
	        //排除自己
	        if (p.getAppointmentId() != null)
	        	criteria.andAppointmentIdNotEqualTo(p.getAppointmentId());
	        	
	        List<AppointmentTEntity> list = appointDao.selectByExample(TExample);
	        return list;
	}
	
	private Boolean validate(AppointmentTEntity appointment) {
		if (StringUtils.isEmpty(appointment.getType()))
			throw new BusinessServiceException("AppointmentServiceImpl", "type.isnull");
		
		if (StringUtils.isEmpty(appointment.getStatus()))
			throw new BusinessServiceException("AppointmentServiceImpl", "status.isnull");
		
		if (appointment.getExpectedDate() == null)
			throw new BusinessServiceException("AppointmentServiceImpl", "expecteddate.isnull");
		
		
		if(appointment.getExpectedDate().compareTo(DateUtils.parseDate(DateUtils.getDate())) < 0)
			throw new BusinessServiceException("AppointmentServiceImpl", "expected.date.less.now" , new Object[] {appointment.getAppointmentCode()}); 
		
		//到达状态时，到达时间不能为空
		if (AppointmentStatusEnum.Arrived.getCode().equals(appointment.getStatus())
				&& appointment.getArrivalDate() == null )
			throw new BusinessServiceException("AppointmentServiceImpl", "arrivaldate.isnull");
		
		
		//验证单据是否在使用中
    	List<AppointmentTEntity> selectAppoint = findByBillNumber(appointment);
    	if (CollectionUtils.isNotEmpty(selectAppoint))
    		throw new BusinessServiceException("PlatFormServiceImpl", "billnumber.appointtment", new Object[] { appointment.getSourceBillNumber() });
		
		//验证月台是否存在
		PlatformTEntity platForm = platFormService.find(PlatformTEntity.builder()
								.warehouseId(appointment.getWarehouseId())
								.companyId(appointment.getCompanyId())
								.platformCode(appointment.getPlatformCode())
								.build());		
		
		if ((AppointmentStatusEnum.Appointment.getCode().equals(appointment.getStatus()) 
				|| AppointmentStatusEnum.Arrived.getCode().equals(appointment.getStatus()))
				&& !(PlatFormStatusEnum.Idle.getCode().equals(platForm.getStatus())
				|| PlatFormStatusEnum.Appointment.getCode().equals(platForm.getStatus())))
			throw new BusinessServiceException("AppointmentServiceImpl", "platform.status.not.process", new Object[] { platForm.getPlatformCode() });
		
		appointment.setPlatformCode(platForm.getPlatformCode());
		appointment.setPlatformId(platForm.getPlatformId());
		
		String carNumber;
	    String carDriver;
	    String carDriverPhone;
		
		//验证出入库单据
		if (AppointmentTypeEnum.Inbound.getCode().equals(appointment.getType())) {
			InboundHeaderTEntity header = inboundService.find(InboundHeaderTEntity.builder()
					.warehouseId(appointment.getWarehouseId())
					.companyId(appointment.getCompanyId())
					.inboundNumber(appointment.getSourceBillNumber())
					.build());
			//单据状态必须为新
			if (!InboundStatusEnum.New.getCode().equals(header.getStatus()))
				throw new BusinessServiceException("AppointmentServiceImpl", "inbound.status.not.process" , new Object[] {header.getInboundNumber()});
		
			carNumber = header.getCarrierCarNumber();
			carDriver = header.getCarrierDriver();
			carDriverPhone = header.getCarrierDriverPhone();
		}else {
			OutboundHeaderTEntity header = outboundService.find(OutboundHeaderTEntity.builder()
					.warehouseId(appointment.getWarehouseId())
					.companyId(appointment.getCompanyId())
					.outboundNumber(appointment.getSourceBillNumber())
					.build());
			//单据状态不能为部分发运 全部发运 取消 三个状态（仓库可提前拣货）
			if (OutboundStatusEnum.PartShiped.getCode().equals(header.getStatus())
					|| OutboundStatusEnum.Shiped.getCode().equals(header.getStatus())
					|| OutboundStatusEnum.Cancel.getCode().equals(header.getStatus()))
				throw new BusinessServiceException("AppointmentServiceImpl", "outbound.status.not.process" , new Object[] {header.getOutboundNumber()});
		
			carNumber = header.getCarNumber();
			carDriver = header.getDriver();
			carDriverPhone = header.getDriverPhone();
		}
		
		if (StringUtils.isEmpty(appointment.getCarDriver()))
			appointment.setCarDriver(carDriver);
		
		if (StringUtils.isEmpty(appointment.getCarNumber()))
			appointment.setCarNumber(carNumber);
		
		if (StringUtils.isEmpty(appointment.getCarDriverPhone()))
			appointment.setCarDriverPhone(carDriverPhone);
		
		if (appointment.getAppointmentId() == null)
			return Boolean.TRUE;
		
		//修改时比较校验状态
		AppointmentTEntity selectAppointment = find(appointment);
    	if (AppointmentStatusEnum.Leave.getCode().equals(selectAppointment.getStatus())
    			|| AppointmentStatusEnum.Cancel.getCode().equals(selectAppointment.getStatus())) 
    		throw new BusinessServiceException("AppointmentServiceImpl", "appointment.status.not.process" , new Object[] {selectAppointment.getAppointmentCode()});
    	
    	//只有预约状态才可修改月台
    	if (!AppointmentStatusEnum.Appointment.getCode().equals(selectAppointment.getStatus())
    			&& !platForm.getPlatformCode().equals(selectAppointment.getPlatformCode()))
    			throw new BusinessServiceException("AppointmentServiceImpl", "appointment.status.not.change.plantform" , new Object[] {selectAppointment.getAppointmentCode()});
    	
		return Boolean.TRUE;
	}
	
	
	private Boolean modifyPlatFormStatus(AjaxRequest<AppointmentTEntity> request) {
		AppointmentTEntity appointment = request.getData();
		
		PlatformTEntity plat = PlatformTEntity.builder()
									.platformId(appointment.getPlatformId())
									.build();
		
		if (AppointmentStatusEnum.Appointment.getCode().equals(appointment.getStatus())) {
			platFormService.modifyStats(new AjaxRequest<PlatformTEntity>(plat, request), PlatFormStatusEnum.Appointment);
		}else if (AppointmentStatusEnum.Arrived.getCode().equals(appointment.getStatus())) {
			//到达时更新车辆信息
			plat.setStatus(PlatFormStatusEnum.Arrived.getCode());
			plat.setCarDriver(appointment.getCarDriver());
			plat.setCarDriverPhone(appointment.getCarDriverPhone());
			plat.setCarNumber(appointment.getCarNumber());
			platFormService.modify(new AjaxRequest<List<PlatformTEntity>>(Lists.newArrayList(plat), request));
		}else if (AppointmentStatusEnum.Leave.getCode().equals(appointment.getStatus())) {
			platFormService.modifyStats(new AjaxRequest<PlatformTEntity>(plat, request), PlatFormStatusEnum.Idle);
		}else if (AppointmentStatusEnum.Cancel.getCode().equals(appointment.getStatus())) {
			platFormService.modifyStats(new AjaxRequest<PlatformTEntity>(plat, request), PlatFormStatusEnum.Idle);
		}
		return Boolean.TRUE;
	}


	@Override
	public List<AppointmentTEntity> findByPlatForm(AppointmentTEntity p) throws BusinessServiceException {
		AppointmentTExample TExample = new AppointmentTExample();
        AppointmentTExample.Criteria criteria = TExample.createCriteria();
        criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
        		.andStatusEqualTo(AppointmentStatusEnum.Appointment.getCode())
                .andWarehouseIdEqualTo(p.getWarehouseId())
                .andCompanyIdEqualTo(p.getCompanyId())
                .andPlatformCodeEqualTo(p.getPlatformCode());
        	
        List<AppointmentTEntity> list = appointDao.selectByExample(TExample);
        return list;
	}


  
}
