package com.wms.services.base.impl;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IPutawayStrategyTDao;
import com.wms.dao.example.OwnerTExample;
import com.wms.dao.example.PackTExample;
import com.wms.dao.example.PutawayStrategyTExample;
import com.wms.entity.auto.OwnerTEntity;
import com.wms.entity.auto.PackTEntity;
import com.wms.entity.auto.PutawayStrategyDetailTEntity;
import com.wms.entity.auto.PutawayStrategyTEntity;
import com.wms.services.base.IPutawayStrategyDetailService;
import com.wms.services.base.IPutawayStrategyHeaderService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-03 09:31
 **/
@Service
public class PutawayStrategyHeaderServiceImpl implements IPutawayStrategyHeaderService {

    @Autowired
    IPutawayStrategyTDao putawayStrategyDao;

    @Autowired
    IPutawayStrategyDetailService detailService;

    @Override
    public List<PutawayStrategyTEntity> find(PageRequest request) throws BusinessServiceException {
        PutawayStrategyTExample example = new PutawayStrategyTExample();
        PutawayStrategyTExample.Criteria exampleCriteria = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(PutawayStrategyTEntity.Column.class, PutawayStrategyTExample.Criterion.class)
                .criteria(exampleCriteria)
                .data(request)
                .build(request)
                .orderby(example);
        exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return putawayStrategyDao.selectByExample(example);
    }
    
    //added by Ella on July 12,2019,在货品维护中新增货品选择上架策略时用到。
    @Override
	public PutawayStrategyTEntity find(PutawayStrategyTEntity putawayStrategy) throws BusinessServiceException {
  
		
		PutawayStrategyTExample example = new PutawayStrategyTExample();
		PutawayStrategyTExample.Criteria criteria = example.createCriteria();

        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(putawayStrategy.getWarehouseId())
                .andCompanyIdEqualTo(putawayStrategy.getCompanyId());

        int conditionCount = 0;
        if (putawayStrategy.getPutawayStrategyId() != null) {
            criteria.andPutawayStrategyIdEqualTo(putawayStrategy.getPutawayStrategyId());
            conditionCount++;
        }
        if (StringUtils.isNotEmpty(putawayStrategy.getPutawayStrategyCode())) {
            criteria.andPutawayStrategyCodeEqualTo(putawayStrategy.getPutawayStrategyCode());
            conditionCount++;
        }
        if (conditionCount == 0){
            return null;
        }

        PutawayStrategyTEntity selectLocation = putawayStrategyDao.selectOneByExample(example);
        if (selectLocation == null){
            throw new BusinessServiceException("PutawayStrategyHeaderServiceImpl", "putawayStrategy.record.not.exists", new Object[]{putawayStrategy.getPutawayStrategyId() == null ? putawayStrategy.getPutawayStrategyCode() : putawayStrategy.getPutawayStrategyId()});
        }

        return selectLocation;
	}


    @Override
    @Transactional
    public Boolean modify(AjaxRequest<List<PutawayStrategyTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record update.");
        }

        List<PutawayStrategyTEntity> list = request.getData();

        for (PutawayStrategyTEntity putawayStrategy : list) {

            PutawayStrategyTEntity update = PutawayStrategyTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .remark(putawayStrategy.getRemark())
                    .putawayStrategyDescr(putawayStrategy.getPutawayStrategyDescr())
                    .active(putawayStrategy.getActive())
                    .build();

            PutawayStrategyTExample example = new PutawayStrategyTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(putawayStrategy.getWarehouseId())
                    .andCompanyIdEqualTo(putawayStrategy.getCompanyId())
                    .andPutawayStrategyIdEqualTo(putawayStrategy.getPutawayStrategyId());

            int row = putawayStrategyDao.updateWithVersionByExampleSelective(putawayStrategy.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean add(AjaxRequest<List<PutawayStrategyTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record add.");
        }

        List<PutawayStrategyTEntity> list = request.getData();

        for (PutawayStrategyTEntity w : list) {

            String putawayStrategyCode = w.getPutawayStrategyCode().toUpperCase();

            PutawayStrategyTExample example = new PutawayStrategyTExample();
            example.createCriteria()
                    .andDelFlagEqualTo(YesNoEnum.No.getCode())
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andPutawayStrategyCodeEqualTo(putawayStrategyCode);
            Long count = putawayStrategyDao.countByExample(example);
            if (count > 0) {
                throw new BusinessServiceException("PutawayStrategyHeaderServiceImpl", "code.record.exists", new Object[]{putawayStrategyCode});
            }

            PutawayStrategyTEntity insert = PutawayStrategyTEntity.builder()
                    .createBy(request.getUserName())
                    .createTime(new Date())
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .companyId(request.getCompanyId())
                    .warehouseId(request.getWarehouseId())
                    .putawayStrategyId(KeyUtils.getUID())
                    .putawayStrategyCode(putawayStrategyCode)
                    .putawayStrategyDescr(w.getPutawayStrategyDescr())
                    .remark(w.getRemark())
                    .active(w.getActive())
                    .build();

            int row = putawayStrategyDao.insertSelective(insert);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean delete(AjaxRequest<List<PutawayStrategyTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record delete.");
        }
        List<PutawayStrategyTEntity> list = request.getData();
        for (PutawayStrategyTEntity putawayStrategy : list) {
            try {
                List<PutawayStrategyDetailTEntity> detailList = detailService.findByPutawayStrategy(putawayStrategy);
                if (detailList.size() > 0) {
                    //存在明细
                    for (PutawayStrategyDetailTEntity detail : detailList) {
                        detail.setUpdateBy(request.getUserName());
                    }
                    detailService.delete(detailList);
                }
                PutawayStrategyTEntity update = PutawayStrategyTEntity.builder()
                        .updateBy(request.getUserName())
                        .updateTime(new Date())
                        .delFlag(YesNoEnum.Yes.getCode())
                        .build();

                PutawayStrategyTExample example = new PutawayStrategyTExample();
                example.createCriteria()
                        .andWarehouseIdEqualTo(putawayStrategy.getWarehouseId())
                        .andCompanyIdEqualTo(putawayStrategy.getCompanyId())
                        .andPutawayStrategyIdEqualTo(putawayStrategy.getPutawayStrategyId());

                int row = putawayStrategyDao.updateWithVersionByExampleSelective(putawayStrategy.getUpdateVersion(), update, example);
                if (row == 0) {
                    throw new BusinessServiceException("delete update error.");
                }
            } catch (Exception e) {
                throw new BusinessServiceException("delete update error.");
            }
        }
        return Boolean.TRUE;
    }

}
