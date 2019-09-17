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
import com.wms.common.enums.PlatFormStatusEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IPlatformTDao;
import com.wms.dao.example.PlatformTExample;
import com.wms.entity.auto.AppointmentTEntity;
import com.wms.entity.auto.PlatformTEntity;
import com.wms.services.appointment.IAppointmentService;
import com.wms.services.appointment.IPlatFormService;

/**
 * 泊位管理
 * @author yechuankai.chnet
 *
 */
@Service
public class PlatFormServiceImpl implements IPlatFormService {
	
	public static final String AVAILABEL = "PLATFORM_AVAILABEL";

    @Autowired
    private IPlatformTDao platDao;
    
    @Autowired
    private IAppointmentService appointmentService;

    @Override
    public List<PlatformTEntity> find(PageRequest request) throws BusinessServiceException {
        PlatformTExample PlatformTExample = new PlatformTExample();
        PlatformTExample.Criteria PlatformTExampleCriteria = PlatformTExample.createCriteria();

        //转换查询方法
        ExampleUtils.create(PlatformTEntity.Column.class, PlatformTExample.Criterion.class)
                .criteria(PlatformTExampleCriteria)
                .data(request)
                .build(request)
                .orderby(PlatformTExample);
        PlatformTExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        
        if (YesNoEnum.Yes.getCode().equals(request.getString(AVAILABEL))) {
        	PlatformTExampleCriteria.andStatusIn(Lists.newArrayList(PlatFormStatusEnum.Idle.getCode(), PlatFormStatusEnum.Appointment.getCode()));
        }
        
        return platDao.selectByExample(PlatformTExample);
    }
    

    @Override
    @Transactional
    public Boolean modify(AjaxRequest<List<PlatformTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record update.");
        }

        List<PlatformTEntity> list = request.getData();

        for (PlatformTEntity p : list) {

            PlatformTEntity update = PlatformTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .platformType(p.getPlatformType())
                    .carDriver(p.getCarDriver())
                    .carDriverPhone(p.getCarDriverPhone())
                    .carNumber(p.getCarNumber())
                    .containerNumber(p.getContainerNumber())
                    .status(p.getStatus())
                    .active(p.getActive())
                    .build();

            PlatformTExample example = new PlatformTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andPlatformIdEqualTo(p.getPlatformId());

            int row = platDao.updateByExampleSelective(update, example);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean add(AjaxRequest<List<PlatformTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record add.");
        }
        List<PlatformTEntity> list = request.getData();
        for (PlatformTEntity p : list) {
        	p.setWarehouseId(request.getWarehouseId());
        	p.setCompanyId(request.getCompanyId());
        	
        	//验证是否存在
        	PlatformTEntity selectPlatform = null;
        	try{
        		selectPlatform = find(p);
        	}catch (BusinessServiceException e) {}
        	
        	if (selectPlatform != null) 
        		throw new BusinessServiceException("PlatFormServiceImpl", "platform.record.exists", new Object[] { p.getPlatformCode() });
        	
        	p.setPlatformCode(p.getPlatformCode().toUpperCase());
        	p.setPlatformId(KeyUtils.getUID());
        	p.setStatus(PlatFormStatusEnum.Idle.getCode());
        	p.setCreateBy(request.getUserName());
        	p.setUpdateBy(request.getUserName());
        	p.setCreateTime(new Date());
        	p.setUpdateTime(new Date());
        	
            int row = platDao.insertSelective(p);
            if (row == 0) {
                throw new BusinessServiceException("record add error.");
            }
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(AjaxRequest<List<PlatformTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record delete.");
        }
        List<PlatformTEntity> list = request.getData();

        for (PlatformTEntity p : list) {
        	
        	p.setWarehouseId(request.getWarehouseId());
        	p.setCompanyId(request.getCompanyId());
        	PlatformTEntity selectForm = find(p);
        	if (!PlatFormStatusEnum.Idle.getCode().equals(selectForm.getStatus())){
        		throw new BusinessServiceException("PlatFormServiceImpl", "platform.status.not.process", new Object[] { p.getPlatformCode() });
        	}
        	
            PlatformTEntity update = PlatformTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .delFlag(YesNoEnum.Yes.getCode())
                    .build();

            PlatformTExample example = new PlatformTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(p.getWarehouseId())
                    .andCompanyIdEqualTo(p.getCompanyId())
                    .andPlatformIdEqualTo(p.getPlatformId());

            int row = platDao.updateWithVersionByExampleSelective(p.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("delete update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public PlatformTEntity find(PlatformTEntity p) throws BusinessServiceException {
        PlatformTExample TExample = new PlatformTExample();
        PlatformTExample.Criteria criteria = TExample.createCriteria();
        criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(p.getWarehouseId())
                .andCompanyIdEqualTo(p.getCompanyId());
        int conditionCount = 0;
        if (null != p.getPlatformCode()) {
            criteria.andPlatformCodeEqualTo(p.getPlatformCode());
            conditionCount++;
        }
        if (null != p.getPlatformId()) {
            criteria.andPlatformIdEqualTo(p.getPlatformId());
            conditionCount++;
        }
        if (conditionCount == 0) {
            return null;
        }
        PlatformTEntity w = platDao.selectOneByExample(TExample);
        if (w == null) {
            throw new BusinessServiceException("PlatFormServiceImpl", "platform.record.not.exists", new Object[] { p.getPlatformCode() });
        }
        return w;
    }

	@Override
	public Boolean modifyStats(AjaxRequest<PlatformTEntity> request, PlatFormStatusEnum status) throws BusinessServiceException {
		PlatformTEntity plat = request.getData();
		plat.setWarehouseId(request.getWarehouseId());
		plat.setCompanyId(request.getCompanyId());
		plat = find(plat);
		
		String statusStr = status.getCode();
		
		//查询是否还存在预约的单据
		List<AppointmentTEntity> appList = appointmentService.findByPlatForm(AppointmentTEntity.builder()
											.warehouseId(request.getWarehouseId())
											.companyId(request.getCompanyId())
											.platformCode(plat.getPlatformCode())
											.build());
		if (CollectionUtils.isNotEmpty(appList))
			statusStr = PlatFormStatusEnum.Appointment.getCode();
		
		PlatformTEntity update = PlatformTEntity.builder()
                .updateBy(request.getUserName())
                .updateTime(new Date())
                .status(statusStr)
                .build();
		
		//更新空闲状态时清空车辆信息
		if (PlatFormStatusEnum.Idle.getCode().equals(statusStr)) {
			update.setCarDriver("");
			update.setCarDriverPhone("");
			update.setCarNumber("");
		}

        PlatformTExample example = new PlatformTExample();
        example.createCriteria()
                .andWarehouseIdEqualTo(request.getWarehouseId())
                .andCompanyIdEqualTo(request.getCompanyId())
                .andPlatformIdEqualTo(plat.getPlatformId());

        int row = platDao.updateWithVersionByExampleSelective(plat.getUpdateVersion(), update, example);
        if (row == 0) {
            throw new BusinessServiceException("record update error.");
        }
        return Boolean.TRUE;
	}


  
}
