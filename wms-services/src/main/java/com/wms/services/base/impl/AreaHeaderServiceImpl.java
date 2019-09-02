package com.wms.services.base.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IAreaTDao;
import com.wms.dao.example.AreaTExample;
import com.wms.entity.auto.AllocateStrategyDetailTEntity;
import com.wms.entity.auto.AllocateStrategyTEntity;
import com.wms.entity.auto.AreaDetailTEntity;
import com.wms.entity.auto.AreaTEntity;
import com.wms.services.base.IAllocateStrategDetailService;
import com.wms.services.base.IAreaDetailService;
import com.wms.services.base.IAreaHeaderService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @description: 基础数据-区域服务实现类
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-24 11:13
 **/

@Service
public class AreaHeaderServiceImpl implements IAreaHeaderService {

    @Autowired
    IAreaTDao areaDao;

    @Autowired
    IAreaDetailService areaDetailService;

    @Autowired
    IAllocateStrategDetailService allocateStrategDetailService;

    @Override
    public List<AreaTEntity> find(PageRequest request) throws BusinessServiceException {
        AreaTExample areaTExample=new AreaTExample();
        AreaTExample.Criteria areaTExampleCriteria = areaTExample.createCriteria();

        //转换查询方法
        ExampleUtils.create(AreaTEntity.Column.class,AreaTExample.Criterion.class)
                .criteria(areaTExampleCriteria)
                .data(request)
                .build(request)
                .orderby(areaTExample);
        areaTExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return areaDao.selectByExample(areaTExample);
    }

    @Override
    public AreaTEntity find(AreaTEntity area) throws BusinessServiceException {
        AreaTExample example = new AreaTExample();
        AreaTExample.Criteria criteria = example.createCriteria();

        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(area.getWarehouseId())
                .andCompanyIdEqualTo(area.getCompanyId());

        int conditionCount = 0;
        if (area.getAreaId() != null) {
            criteria.andAreaIdEqualTo(area.getAreaId());
            conditionCount++;
        }
        if (StringUtils.isNotEmpty(area.getAreaCode())) {
            criteria.andAreaCodeEqualTo(area.getAreaCode());
            conditionCount++;
        }
        if (conditionCount == 0){
            return null;
        }

        AreaTEntity selectLocation = areaDao.selectOneByExample(example);
        if (selectLocation == null){
            throw new BusinessServiceException("AreaHeaderServiceImpl", "area.record.not.exists", new Object[]{area.getAreaId() == null ? area.getAreaCode() : area.getAreaId()});
        }

        return selectLocation;
    }

    @Override
    @Transactional
    public Boolean modify(AjaxRequest<List<AreaTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record update.");
        }

        List<AreaTEntity> list = request.getData();

        for (AreaTEntity area : list) {

            AreaTEntity update = AreaTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .type(area.getType())
                    .remark(area.getRemark())
                    .areaDescr(area.getAreaDescr())
                    .active(area.getActive())
                    .build();

            AreaTExample example = new AreaTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(area.getWarehouseId())
                    .andCompanyIdEqualTo(area.getCompanyId())
                    .andAreaIdEqualTo(area.getAreaId());

            int row = areaDao.updateWithVersionByExampleSelective(area.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean add(AjaxRequest<List<AreaTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record add.");
        }

        List<AreaTEntity> list = request.getData();

        for (AreaTEntity w : list) {

            String areaCode = w.getAreaCode().toUpperCase();

            AreaTExample example = new AreaTExample();
            example.createCriteria()
                    .andDelFlagEqualTo(YesNoEnum.No.getCode())
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andAreaCodeEqualTo(areaCode);
            Long count = areaDao.countByExample(example);
            if (count > 0) {
                throw new BusinessServiceException("AreaHeaderServiceImpl", "area.record.exists" , new Object[] {areaCode});
            }

            AreaTEntity insert = AreaTEntity.builder()
                    .createBy(request.getUserName())
                    .createTime(new Date())
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .companyId(request.getCompanyId())
                    .warehouseId(request.getWarehouseId())
                    .areaId(KeyUtils.getUID())
                    .areaCode(areaCode)
                    .areaDescr(w.getAreaDescr())
                    .remark(w.getRemark())
                    .type(w.getType())
                    .active(w.getActive())
                    .build();

            int row = areaDao.insertSelective(insert);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean delete(AjaxRequest<List<AreaTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record delete.");
        }
        List<AreaTEntity> list = request.getData();

        for (AreaTEntity area : list) {
            try{
                List<AreaDetailTEntity> detailList = areaDetailService.findByArea(area);
                if(detailList.size()>0){
                    //区域存在明细
                    for(AreaDetailTEntity detail :detailList){
                        detail.setUpdateBy(request.getUserName());
                    }
                    areaDetailService.delete(detailList);
                }
                AreaTEntity update = AreaTEntity.builder()
                        .updateBy(request.getUserName())
                        .updateTime(new Date())
                        .delFlag(YesNoEnum.Yes.getCode())
                        .build();

                AreaTExample example = new AreaTExample();
                example.createCriteria()
                        .andWarehouseIdEqualTo(area.getWarehouseId())
                        .andCompanyIdEqualTo(area.getCompanyId())
                        .andAreaIdEqualTo(area.getAreaId());

                int row = areaDao.updateWithVersionByExampleSelective(area.getUpdateVersion(), update, example);
                if (row == 0) {
                    throw new BusinessServiceException("delete update error.");
                }
            }catch (Exception e){
                throw new BusinessServiceException("delete update error.");
            }
        }

        return Boolean.TRUE;
    }

    @Override
    public List<AreaTEntity> findAreaAvailable(AllocateStrategyTEntity allocateStrategy) throws BusinessServiceException {
        List<AllocateStrategyDetailTEntity> existsList = allocateStrategDetailService.findByAllocateStrategy(allocateStrategy);
        AreaTExample areaTExample = new AreaTExample();
        AreaTExample.Criteria createCriteria = areaTExample.createCriteria();
        createCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andCompanyIdEqualTo(allocateStrategy.getCompanyId())
                .andWarehouseIdEqualTo(allocateStrategy.getWarehouseId());

        if (CollectionUtils.isNotEmpty(existsList)) {
            Set<Long> ids = Sets.newHashSet();
            existsList.forEach(w -> {
                ids.add(w.getAreaId());
            });
            createCriteria.andAreaIdNotIn(Lists.newArrayList(ids));
        }
        List<AreaTEntity> areaList = areaDao.selectByExample(areaTExample);
        return areaList;
    }
}
