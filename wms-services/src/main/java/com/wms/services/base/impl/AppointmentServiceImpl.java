package com.wms.services.base.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.AppointmentStatusEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.dao.auto.IAppointmentTDao;
import com.wms.dao.example.AppointmentTExample;
import com.wms.entity.auto.AppointmentTEntity;
import com.wms.entity.auto.PlatformTEntity;
import com.wms.services.base.IAppointmentService;
import com.wms.services.base.IPlatFormService;

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

        for (AppointmentTEntity a : list) {

            AppointmentTEntity update = AppointmentTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .status(a.getStatus())
                    .build();

            AppointmentTExample example = new AppointmentTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andAppointmentIdEqualTo(a.getAppointmentId());

            int row = appointDao.updateWithVersionByExampleSelective(a.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
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
        	
        	//验证月台是否存在
        	PlatformTEntity selectPlatform = platFormService.find(PlatformTEntity.builder()
        												.warehouseId(request.getWarehouseId())
        												.companyId(request.getCompanyId())
        												.platformCode(p.getPlatformCode())
        												.platformId(p.getPlatformId())
        												.build());
        	
        	//验证单据是否在使用中
        	List<AppointmentTEntity> selectAppoint = findByBillNumber(p);
        	if (CollectionUtils.isNotEmpty(selectAppoint))
        		throw new BusinessServiceException("PlatFormServiceImpl", "billnumber.appointtment", new Object[] { p.getSourceBillNumber() });
        	
        	p.setPlatformCode(selectPlatform.getPlatformCode());
        	p.setPlatformId(selectPlatform.getPlatformId());

        	p.setCreateBy(request.getUserName());
        	p.setUpdateBy(request.getUserName());
        	p.setCreateTime(new Date());
        	p.setUpdateTime(new Date());
        	
            int row = appointDao.insertSelective(p);
            if (row == 0) {
                throw new BusinessServiceException("record add error.");
            }
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
	        	
	        List<AppointmentTEntity> list = appointDao.selectByExample(TExample);
	        return list;
	}


  
}
