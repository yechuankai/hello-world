package com.wms.services.base.impl;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.AreaTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IAllocateStrategyDetailTDao;
import com.wms.dao.example.AllocateStrategyDetailTExample;
import com.wms.entity.auto.AllocateStrategyDetailTEntity;
import com.wms.entity.auto.AllocateStrategyTEntity;
import com.wms.entity.auto.AreaTEntity;
import com.wms.services.base.IAllocateStrategDetailService;
import com.wms.services.base.IAreaHeaderService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-27 11:19
 **/
@Service
public class AllocateStrategDetailServiceImpl implements IAllocateStrategDetailService {

    @Autowired
    IAllocateStrategyDetailTDao allocateStrategyDetailDao;

    @Autowired
    IAreaHeaderService areaHeaderService;

    @Override
    public List<AllocateStrategyDetailTEntity> find(PageRequest request) throws BusinessServiceException {
        AllocateStrategyDetailTExample allocateStrategyDetailTExample = new AllocateStrategyDetailTExample();
        AllocateStrategyDetailTExample.Criteria areaTExampleCriteria = allocateStrategyDetailTExample.createCriteria();

        //转换查询方法
        ExampleUtils.create(AllocateStrategyDetailTEntity.Column.class, AllocateStrategyDetailTExample.Criterion.class)
                .criteria(areaTExampleCriteria)
                .data(request)
                .build(request)
                .orderby(allocateStrategyDetailTExample);
        areaTExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return allocateStrategyDetailDao.selectByExample(allocateStrategyDetailTExample);
    }

    @Override
    @Transactional
    public Boolean modify(AjaxRequest<List<AllocateStrategyDetailTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record update.");
        }

        List<AllocateStrategyDetailTEntity> list = request.getData();

        for (AllocateStrategyDetailTEntity detailTEntity : list) {

            //判断行号是否重复
            AllocateStrategyDetailTExample allocateStrategyDetailTExample = new AllocateStrategyDetailTExample();
            allocateStrategyDetailTExample.createCriteria()
                    .andDelFlagEqualTo(YesNoEnum.No.getCode())
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andAllocateStrategyIdEqualTo(detailTEntity.getAllocateStrategyId())
                    .andLineNumberEqualTo(detailTEntity.getLineNumber())
                    .andAllocateStrategyDetailIdNotEqualTo(detailTEntity.getAllocateStrategyDetailId());
            Long count = allocateStrategyDetailDao.countByExample(allocateStrategyDetailTExample);
            if (count > 0) {
                throw new BusinessServiceException("AllocateStrategDetailServiceImpl", "linenumber.record.exists", new Object[]{detailTEntity.getLineNumber()});
            }
            
            if (StringUtils.isNotEmpty(detailTEntity.getAreaCode())) {
            	AreaTEntity area = areaHeaderService.find(AreaTEntity.builder()
            												.warehouseId(request.getWarehouseId())
            												.companyId(request.getCompanyId())
            												.type(AreaTypeEnum.PK.getCode())
            												.areaCode(detailTEntity.getAreaCode().toUpperCase())
            												.build());
            	detailTEntity.setAreaCode(area.getAreaCode());
            	detailTEntity.setAreaId(area.getAreaId());
            }else {
            	detailTEntity.setAreaCode("");
            	detailTEntity.setAreaId(0L);
            }

            AllocateStrategyDetailTEntity update = AllocateStrategyDetailTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .lineNumber(detailTEntity.getLineNumber())
                    .areaCode(detailTEntity.getAreaCode())
                    .areaId(detailTEntity.getAreaId())
                    .type(detailTEntity.getType())
                    .uom(detailTEntity.getUom())
                    .sortRule(detailTEntity.getSortRule())
                    .fifoRule(detailTEntity.getFifoRule())
                    .remark(detailTEntity.getRemark())
                    .active(detailTEntity.getActive())
                    .build();

            AllocateStrategyDetailTExample example = new AllocateStrategyDetailTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(detailTEntity.getWarehouseId())
                    .andCompanyIdEqualTo(detailTEntity.getCompanyId())
                    .andAllocateStrategyDetailIdEqualTo(detailTEntity.getAllocateStrategyDetailId())
                    .andAllocateStrategyIdEqualTo(detailTEntity.getAllocateStrategyId());

            int row = allocateStrategyDetailDao.updateWithVersionByExampleSelective(detailTEntity.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean add(AjaxRequest<List<AllocateStrategyDetailTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record add.");
        }

        List<AllocateStrategyDetailTEntity> list = request.getData();

        for (AllocateStrategyDetailTEntity detail : list) {

            long allocateStrategyId = detail.getAllocateStrategyId();
            
            long lineNumber = detail.getLineNumber();
            //判断行号是否重复
            AllocateStrategyDetailTExample example = new AllocateStrategyDetailTExample();
            example.createCriteria()
                    .andDelFlagEqualTo(YesNoEnum.No.getCode())
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andAllocateStrategyIdEqualTo(allocateStrategyId)
                    .andLineNumberEqualTo(lineNumber);
            Long count = allocateStrategyDetailDao.countByExample(example);
            if (count > 0) {
                throw new BusinessServiceException("AllocateStrategDetailServiceImpl", "linenumber.record.exists", new Object[]{lineNumber});
            }
            
            if (StringUtils.isNotEmpty(detail.getAreaCode())) {
            	AreaTEntity area = areaHeaderService.find(AreaTEntity.builder()
            												.warehouseId(request.getWarehouseId())
            												.companyId(request.getCompanyId())
            												.type(AreaTypeEnum.PK.getCode())
            												.areaCode(detail.getAreaCode().toUpperCase())
            												.build());
            	detail.setAreaCode(area.getAreaCode());
            	detail.setAreaId(area.getAreaId());
            }else {
            	detail.setAreaCode(null);
            	detail.setAreaId(null);
            }

            AllocateStrategyDetailTEntity insert = AllocateStrategyDetailTEntity.builder()
                    .createBy(request.getUserName())
                    .createTime(new Date())
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .companyId(request.getCompanyId())
                    .warehouseId(request.getWarehouseId())
                    .allocateStrategyDetailId(KeyUtils.getUID())
                    .allocateStrategyId(allocateStrategyId)
                    .lineNumber(lineNumber)
                    .type(detail.getType())
                    .areaId(detail.getAreaId())
                    .areaCode(detail.getAreaCode())
                    .uom(detail.getUom())
                    .sortRule(detail.getSortRule())
                    .fifoRule(detail.getFifoRule())
                    .remark(detail.getRemark())
                    .active(detail.getActive())
                    .build();

            int row = allocateStrategyDetailDao.insertSelective(insert);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean delete(AjaxRequest<List<AllocateStrategyDetailTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record delete.");
        }
        List<AllocateStrategyDetailTEntity> list = request.getData();

        for (AllocateStrategyDetailTEntity detailTEntity : list) {
            AllocateStrategyDetailTEntity update = AllocateStrategyDetailTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .delFlag(YesNoEnum.Yes.getCode())
                    .build();

            AllocateStrategyDetailTExample example = new AllocateStrategyDetailTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(detailTEntity.getWarehouseId())
                    .andCompanyIdEqualTo(detailTEntity.getCompanyId())
                    .andAllocateStrategyIdEqualTo(detailTEntity.getAllocateStrategyId())
                    .andAllocateStrategyDetailIdEqualTo(detailTEntity.getAllocateStrategyDetailId());

            int row = allocateStrategyDetailDao.updateWithVersionByExampleSelective(detailTEntity.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("delete update error.");
            }
        }

        return Boolean.TRUE;
    }

    @Override
    public List<AllocateStrategyDetailTEntity> findByAllocateStrategy(AllocateStrategyTEntity allocateStrategy) throws BusinessServiceException {
        AllocateStrategyDetailTExample detailExample = new AllocateStrategyDetailTExample();
        detailExample.createCriteria()
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(allocateStrategy.getWarehouseId())
                .andCompanyIdEqualTo(allocateStrategy.getCompanyId())
                .andAllocateStrategyIdEqualTo(allocateStrategy.getAllocateStrategyId());
        List<AllocateStrategyDetailTEntity> list = allocateStrategyDetailDao.selectByExample(detailExample);
        return list;
    }

    @Override
    @Transactional
    public Boolean delete(List<AllocateStrategyDetailTEntity> detailList) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(detailList)) {
            throw new BusinessServiceException("no record delete.");
        }

        for (AllocateStrategyDetailTEntity detail : detailList) {
            AllocateStrategyDetailTEntity update = AllocateStrategyDetailTEntity.builder()
                    .updateBy(detail.getUpdateBy())
                    .updateTime(new Date())
                    .delFlag(YesNoEnum.Yes.getCode())
                    .build();

            AllocateStrategyDetailTExample example = new AllocateStrategyDetailTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(detail.getWarehouseId())
                    .andCompanyIdEqualTo(detail.getCompanyId())
                    .andAllocateStrategyDetailIdEqualTo(detail.getAllocateStrategyDetailId())
                    .andAllocateStrategyIdEqualTo(detail.getAllocateStrategyId());

            int row = allocateStrategyDetailDao.updateWithVersionByExampleSelective(detail.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("delete update error.");
            }
        }
        return Boolean.TRUE;
    }
    
}
