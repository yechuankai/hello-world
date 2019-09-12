package com.wms.services.base.impl;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IAreaDetailTDao;
import com.wms.dao.example.AreaDetailTExample;
import com.wms.entity.auto.AreaDetailTEntity;
import com.wms.entity.auto.AreaTEntity;
import com.wms.services.base.IAreaDetailService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @description: 基础数据-区域明细服务实现类
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-24 13:53
 **/

@Service
public class AreaDetailServiceImpl implements IAreaDetailService {

    @Autowired
    IAreaDetailTDao areaDetailDao;

    @Autowired
    IAreaDetailService areaDetailService;

    @Override
    public List<AreaDetailTEntity> find(PageRequest request) throws BusinessServiceException {
        AreaDetailTExample areaTExample=new AreaDetailTExample();
        AreaDetailTExample.Criteria areaTExampleCriteria = areaTExample.createCriteria();

        //转换查询方法
        ExampleUtils.create(AreaDetailTEntity.Column.class,AreaDetailTExample.Criterion.class)
                .criteria(areaTExampleCriteria)
                .data(request)
                .build(request)
                .orderby(areaTExample);
        areaTExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return areaDetailDao.selectByExample(areaTExample);
    }

    @Override
    @Transactional
    public Boolean modify(AjaxRequest<List<AreaDetailTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record update.");
        }

        List<AreaDetailTEntity> list = request.getData();

        for (AreaDetailTEntity areaDetail : list) {

            AreaDetailTEntity update = AreaDetailTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .remark(areaDetail.getRemark())
                    .active(areaDetail.getActive())
                    .build();

            AreaDetailTExample example = new AreaDetailTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(areaDetail.getWarehouseId())
                    .andCompanyIdEqualTo(areaDetail.getCompanyId())
                    .andAreaIdEqualTo(areaDetail.getAreaId())
                    .andAreaDetailIdEqualTo(areaDetail.getAreaDetailId());

            int row = areaDetailDao.updateWithVersionByExampleSelective(areaDetail.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean add(AjaxRequest<List<AreaDetailTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record add.");
        }

        List<AreaDetailTEntity> list = request.getData();

        for (AreaDetailTEntity areaDetail : list) {

            long areaId = areaDetail.getAreaId();
            String locationCode = areaDetail.getLocationCode().toUpperCase();
            AreaDetailTExample example = new AreaDetailTExample();
            example.createCriteria()
                    .andDelFlagEqualTo(YesNoEnum.No.getCode())
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andAreaDetailIdEqualTo(areaId)
                    .andLocationCodeEqualTo(locationCode);
            Long count = areaDetailDao.countByExample(example);
            if (count > 0) {
                throw new BusinessServiceException("AreaDetailServiceImpl", "location.record.exists" , new Object[] {locationCode});
            }

            AreaDetailTEntity insert = AreaDetailTEntity.builder()
                    .createBy(request.getUserName())
                    .createTime(new Date())
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .companyId(request.getCompanyId())
                    .warehouseId(request.getWarehouseId())
                    .areaDetailId(KeyUtils.getUID())
                    .areaId(areaId)
                    .locationId(areaDetail.getLocationId())
                    .locationCode(areaDetail.getLocationCode())
                    .remark(areaDetail.getRemark())
                    .active(areaDetail.getActive())
                    .build();

            int row = areaDetailDao.insertSelective(insert);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean delete(AjaxRequest<List<AreaDetailTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record delete.");
        }
        List<AreaDetailTEntity> list = request.getData();

        for (AreaDetailTEntity areaDetail : list) {
            AreaDetailTEntity update = AreaDetailTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .delFlag(YesNoEnum.Yes.getCode())
                    .build();

            AreaDetailTExample example = new AreaDetailTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(areaDetail.getWarehouseId())
                    .andCompanyIdEqualTo(areaDetail.getCompanyId())
                    .andAreaIdEqualTo(areaDetail.getAreaId())
                    .andAreaDetailIdEqualTo(areaDetail.getAreaDetailId());

            int row = areaDetailDao.updateWithVersionByExampleSelective(areaDetail.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("delete update error.");
            }
        }

        return Boolean.TRUE;
    }

    @Override
    public List<AreaDetailTEntity> findByArea(AreaTEntity area) throws BusinessServiceException {
        AreaDetailTExample detailExample = new AreaDetailTExample();
        detailExample.createCriteria()
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(area.getWarehouseId())
                .andCompanyIdEqualTo(area.getCompanyId())
                .andAreaIdEqualTo(area.getAreaId());

        List<AreaDetailTEntity> list =areaDetailDao.selectByExample(detailExample);
        return list;
    }

    @Override
    @Transactional
    public Boolean delete(List<AreaDetailTEntity> detailList) throws BusinessServiceException {

        if (CollectionUtils.isEmpty(detailList)) {
            throw new BusinessServiceException("no record delete.");
        }

        for (AreaDetailTEntity areaDetail : detailList) {
            AreaDetailTEntity update = AreaDetailTEntity.builder()
                    .updateBy(areaDetail.getUpdateBy())
                    .updateTime(new Date())
                    .delFlag(YesNoEnum.Yes.getCode())
                    .build();

            AreaDetailTExample example = new AreaDetailTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(areaDetail.getWarehouseId())
                    .andCompanyIdEqualTo(areaDetail.getCompanyId())
                    .andAreaIdEqualTo(areaDetail.getAreaId())
                    .andAreaDetailIdEqualTo(areaDetail.getAreaDetailId());

            int row = areaDetailDao.updateWithVersionByExampleSelective(areaDetail.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("delete update error.");
            }
        }
        return Boolean.TRUE;
    }
}
