package com.wms.services.base.impl;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IAllocateStrategyTDao;
import com.wms.dao.example.AllocateStrategyTExample;
import com.wms.entity.auto.AllocateStrategyDetailTEntity;
import com.wms.entity.auto.AllocateStrategyTEntity;
import com.wms.services.base.IAllocateStrategDetailService;
import com.wms.services.base.IAllocateStrategyHeaderService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-26 18:01
 **/
@Service
public class AllocateStrategyHeaderServiceImpl implements IAllocateStrategyHeaderService {

    @Autowired
    IAllocateStrategyTDao allocateStrategyDao;

    @Autowired
    IAllocateStrategDetailService allocateStrategDetailService;

    @Override
    public List<AllocateStrategyTEntity> find(PageRequest request) throws BusinessServiceException {
        AllocateStrategyTExample areaTExample=new AllocateStrategyTExample();
        AllocateStrategyTExample.Criteria areaTExampleCriteria = areaTExample.createCriteria();

        //转换查询方法
        ExampleUtils.create(AllocateStrategyTEntity.Column.class,AllocateStrategyTExample.Criterion.class)
                .criteria(areaTExampleCriteria)
                .data(request)
                .build(request)
                .orderby(areaTExample);
        areaTExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return allocateStrategyDao.selectByExample(areaTExample);
    }

    @Override
    @Transactional
    public Boolean modify(AjaxRequest<List<AllocateStrategyTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record update.");
        }

        List<AllocateStrategyTEntity> list = request.getData();

        for (AllocateStrategyTEntity allocateStrategy : list) {

            AllocateStrategyTEntity update = AllocateStrategyTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .allocateStrategyType(allocateStrategy.getAllocateStrategyType())
                    .remark(allocateStrategy.getRemark())
                    .allocateStrategyDescr(allocateStrategy.getAllocateStrategyDescr())
                    .active(allocateStrategy.getActive())
                    .build();

            AllocateStrategyTExample example = new AllocateStrategyTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(allocateStrategy.getWarehouseId())
                    .andCompanyIdEqualTo(allocateStrategy.getCompanyId())
                    .andAllocateStrategyIdEqualTo(allocateStrategy.getAllocateStrategyId());

            int row = allocateStrategyDao.updateWithVersionByExampleSelective(allocateStrategy.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean add(AjaxRequest<List<AllocateStrategyTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record add.");
        }

        List<AllocateStrategyTEntity> list = request.getData();

        for (AllocateStrategyTEntity w : list) {

            String code = w.getAllocateStrategyCode().toUpperCase();

            AllocateStrategyTExample example = new AllocateStrategyTExample();
            example.createCriteria()
                    .andDelFlagEqualTo(YesNoEnum.No.getCode())
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andAllocateStrategyCodeEqualTo(code);
            Long count = allocateStrategyDao.countByExample(example);
            if (count > 0) {
                throw new BusinessServiceException("AllocateStrategyHeaderServiceImpl", "allocateStrategyCode.record.exists" , new Object[] {code});
            }

            AllocateStrategyTEntity insert = AllocateStrategyTEntity.builder()
                    .createBy(request.getUserName())
                    .createTime(new Date())
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .companyId(request.getCompanyId())
                    .warehouseId(request.getWarehouseId())
                    .allocateStrategyId(KeyUtils.getUID())
                    .allocateStrategyCode(code)
                    .allocateStrategyDescr(w.getAllocateStrategyDescr())
                    .allocateStrategyType(w.getAllocateStrategyType())
                    .active(w.getActive())
                    .remark(w.getRemark())
                    .build();

            int row = allocateStrategyDao.insertSelective(insert);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean delete(AjaxRequest<List<AllocateStrategyTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record delete.");
        }
        List<AllocateStrategyTEntity> list = request.getData();

        for (AllocateStrategyTEntity allocateStrategy : list) {
            try{
                List<AllocateStrategyDetailTEntity> detailList = allocateStrategDetailService.findByAllocateStrategy(allocateStrategy);

                if(detailList.size()>0){
                    //存在明细
                    for(AllocateStrategyDetailTEntity detail :detailList){
                        detail.setUpdateBy(request.getUserName());
                    }
                    allocateStrategDetailService.delete(detailList);
                }
                AllocateStrategyTEntity update = AllocateStrategyTEntity.builder()
                        .updateBy(request.getUserName())
                        .updateTime(new Date())
                        .delFlag(YesNoEnum.Yes.getCode())
                        .build();

                AllocateStrategyTExample example = new AllocateStrategyTExample();
                example.createCriteria()
                        .andWarehouseIdEqualTo(allocateStrategy.getWarehouseId())
                        .andCompanyIdEqualTo(allocateStrategy.getCompanyId())
                        .andAllocateStrategyIdEqualTo(allocateStrategy.getAllocateStrategyId());

                int row = allocateStrategyDao.updateWithVersionByExampleSelective(allocateStrategy.getUpdateVersion(), update, example);
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
	public AllocateStrategyTEntity find(AllocateStrategyTEntity allocate) throws BusinessServiceException {
		AllocateStrategyTExample example = new AllocateStrategyTExample();
		AllocateStrategyTExample.Criteria criteria = example.createCriteria();

        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(allocate.getWarehouseId())
                .andCompanyIdEqualTo(allocate.getCompanyId());

        int conditionCount = 0;
        if (StringUtils.isNotEmpty(allocate.getAllocateStrategyCode())) {
            criteria.andAllocateStrategyCodeEqualTo(allocate.getAllocateStrategyCode());
            conditionCount++;
        }
        if (allocate.getAllocateStrategyId() != null) {
            criteria.andAllocateStrategyIdEqualTo(allocate.getAllocateStrategyId());
            conditionCount++;
        }
        if (conditionCount == 0){
            return null;
        }

        AllocateStrategyTEntity selectStrategy = allocateStrategyDao.selectOneByExample(example);
        if (selectStrategy == null){
        	throw new BusinessServiceException("AllocateStrategyHeaderServiceImpl", "allocateStrategyCode.record.exists" , new Object[] {allocate.getAllocateStrategyCode()});
        }

        return selectStrategy;

	}
}
