package com.wms.services.base.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.PlatFormStatusEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.dao.auto.IPlatformTDao;
import com.wms.dao.example.PlatformTExample;
import com.wms.entity.auto.PlatformTEntity;
import com.wms.services.base.IPlatFormService;

/**
 * 泊位管理
 * @author yechuankai.chnet
 *
 */
@Service
public class PlatFormServiceImpl implements IPlatFormService {

    @Autowired
    private IPlatformTDao platDao;

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
                    .active(p.getActive())
                    .build();

            PlatformTExample example = new PlatformTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andPlatformIdEqualTo(p.getPlatformId());

            int row = platDao.updateWithVersionByExampleSelective(p.getUpdateVersion(), update, example);
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
        		throw new BusinessServiceException("PlatFormServiceImpl", "platform.record.exists", null);
        	
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
        	if (PlatFormStatusEnum.Idle.getCode().equals(selectForm.getStatus())){
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


  
}
