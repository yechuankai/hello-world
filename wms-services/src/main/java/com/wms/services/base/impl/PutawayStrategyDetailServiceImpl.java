package com.wms.services.base.impl;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IPutawayStrategyDetailTDao;
import com.wms.dao.example.PutawayStrategyDetailTExample;
import com.wms.entity.auto.PutawayStrategyDetailTEntity;
import com.wms.entity.auto.PutawayStrategyTEntity;
import com.wms.services.base.IPutawayStrategyDetailService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-03 10:42
 **/
@Service
public class PutawayStrategyDetailServiceImpl implements IPutawayStrategyDetailService {

    @Autowired
    IPutawayStrategyDetailTDao putawayStrategyDetailDao;

    @Override
    public List<PutawayStrategyDetailTEntity> find(PageRequest request) throws BusinessServiceException {
        PutawayStrategyDetailTExample example=new PutawayStrategyDetailTExample();
        PutawayStrategyDetailTExample.Criteria exampleCriteria = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(PutawayStrategyDetailTEntity.Column.class,PutawayStrategyDetailTExample.Criterion.class)
                .criteria(exampleCriteria)
                .data(request)
                .build(request)
                .orderby(example);
        exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return putawayStrategyDetailDao.selectByExample(example);
    }

    @Override
    @Transactional
    public Boolean modify(AjaxRequest<List<PutawayStrategyDetailTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record update.");
        }

        List<PutawayStrategyDetailTEntity> list = request.getData();

        for (PutawayStrategyDetailTEntity detail : list) {
        	
        	long putawayStrategyId = detail.getPutawayStrategyId();
            long lineNumber = detail.getLineNumber();
            //判断行号是否重复
            PutawayStrategyDetailTExample example = new PutawayStrategyDetailTExample();
            example.createCriteria()
                    .andDelFlagEqualTo(YesNoEnum.No.getCode())
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andPutawayStrategyIdEqualTo(putawayStrategyId)
                    .andLineNumberEqualTo(lineNumber)
                    .andPutawayStrategyDetailIdNotEqualTo(detail.getPutawayStrategyDetailId());
            Long count = putawayStrategyDetailDao.countByExample(example);
            if (count > 0) {
                throw new BusinessServiceException("PutawayStrategyDetailServiceImpl", "linenumber.record.exists" , new Object[] {lineNumber});
            }
        	
        	if (StringUtils.isNotEmpty(detail.getFromLocationCode())) {
            	detail.setFromLocationCode(detail.getFromLocationCode().toUpperCase());
            }
            if (StringUtils.isNotEmpty(detail.getFromZoneCode())) {
            	detail.setFromZoneCode(detail.getFromZoneCode().toUpperCase());
            }
            if (StringUtils.isNotEmpty(detail.getToLocationCode())) {
            	detail.setToLocationCode(detail.getToLocationCode().toUpperCase());
            }
            if (StringUtils.isNotEmpty(detail.getToZoneCode())) {
            	detail.setToZoneCode(detail.getToZoneCode().toUpperCase());
            }
            
            validate(detail);

            PutawayStrategyDetailTEntity update = PutawayStrategyDetailTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .remark(detail.getRemark())
                    .type(detail.getType())
                    .sortRule(detail.getSortRule())
                    .lineNumber(detail.getLineNumber())
                    .type(detail.getType())
                    .fromLocationCode(detail.getFromLocationCode())
                    .fromZoneCode(detail.getFromZoneCode())
                    .toLocationCode(detail.getToLocationCode())
                    .toZoneCode(detail.getToZoneCode())
                    .active(detail.getActive())
                    .build();

            example.clear();
            example.createCriteria()
                    .andWarehouseIdEqualTo(detail.getWarehouseId())
                    .andCompanyIdEqualTo(detail.getCompanyId())
                    .andPutawayStrategyIdEqualTo(detail.getPutawayStrategyId())
                    .andPutawayStrategyDetailIdEqualTo(detail.getPutawayStrategyDetailId());

            int row = putawayStrategyDetailDao.updateWithVersionByExampleSelective(detail.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean add(AjaxRequest<List<PutawayStrategyDetailTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record add.");
        }

        List<PutawayStrategyDetailTEntity> list = request.getData();

        for (PutawayStrategyDetailTEntity detail : list) {

            long putawayStrategyId = detail.getPutawayStrategyId();
            long lineNumber = detail.getLineNumber();
            //判断行号是否重复
            PutawayStrategyDetailTExample example = new PutawayStrategyDetailTExample();
            example.createCriteria()
                    .andDelFlagEqualTo(YesNoEnum.No.getCode())
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andPutawayStrategyIdEqualTo(putawayStrategyId)
                    .andLineNumberEqualTo(lineNumber);
            Long count = putawayStrategyDetailDao.countByExample(example);
            if (count > 0) {
                throw new BusinessServiceException("PutawayStrategyDetailServiceImpl", "linenumber.record.exists" , new Object[] {lineNumber});
            }
            
            validate(detail);
            
            if (StringUtils.isNotEmpty(detail.getFromLocationCode())) {
            	detail.setFromLocationCode(detail.getFromLocationCode().toUpperCase());
            }
            if (StringUtils.isNotEmpty(detail.getFromZoneCode())) {
            	detail.setFromZoneCode(detail.getFromZoneCode().toUpperCase());
            }
            if (StringUtils.isNotEmpty(detail.getToLocationCode())) {
            	detail.setToLocationCode(detail.getToLocationCode().toUpperCase());
            }
            if (StringUtils.isNotEmpty(detail.getToZoneCode())) {
            	detail.setToZoneCode(detail.getToZoneCode().toUpperCase());
            }

            PutawayStrategyDetailTEntity insert = PutawayStrategyDetailTEntity.builder()
                    .createBy(request.getUserName())
                    .createTime(new Date())
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .companyId(request.getCompanyId())
                    .warehouseId(request.getWarehouseId())
                    .putawayStrategyDetailId(KeyUtils.getUID())
                    .putawayStrategyId(putawayStrategyId)
                    .lineNumber(lineNumber)
                    .type(detail.getType())
                    .fromLocationCode(detail.getFromLocationCode())
                    .fromZoneCode(detail.getFromZoneCode())
                    .toLocationCode(detail.getToLocationCode())
                    .toZoneCode(detail.getToZoneCode())
                    .sortRule(detail.getSortRule())
                    .remark(detail.getRemark())
                    .active(detail.getActive())
                    .build();

            int row = putawayStrategyDetailDao.insertSelective(insert);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean delete(AjaxRequest<List<PutawayStrategyDetailTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record delete.");
        }
        List<PutawayStrategyDetailTEntity> list = request.getData();

        for (PutawayStrategyDetailTEntity detailTEntity : list) {
            PutawayStrategyDetailTEntity update = PutawayStrategyDetailTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .delFlag(YesNoEnum.Yes.getCode())
                    .build();

            PutawayStrategyDetailTExample example = new PutawayStrategyDetailTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(detailTEntity.getWarehouseId())
                    .andCompanyIdEqualTo(detailTEntity.getCompanyId())
                    .andPutawayStrategyIdEqualTo(detailTEntity.getPutawayStrategyId())
                    .andPutawayStrategyDetailIdEqualTo(detailTEntity.getPutawayStrategyDetailId());

            int row = putawayStrategyDetailDao.updateWithVersionByExampleSelective(detailTEntity.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("delete update error.");
            }
        }

        return Boolean.TRUE;
    }

    @Override
    public List<PutawayStrategyDetailTEntity> findByPutawayStrategy(PutawayStrategyTEntity putawayStrategy) throws BusinessServiceException {
        PutawayStrategyDetailTExample detailExample = new PutawayStrategyDetailTExample();
        detailExample.createCriteria()
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(putawayStrategy.getWarehouseId())
                .andCompanyIdEqualTo(putawayStrategy.getCompanyId())
                .andPutawayStrategyIdEqualTo(putawayStrategy.getPutawayStrategyId());

        List<PutawayStrategyDetailTEntity> list =putawayStrategyDetailDao.selectByExample(detailExample);
        return list;
    }

    @Override
    @Transactional
    public Boolean delete(List<PutawayStrategyDetailTEntity> detailList) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(detailList)) {
            throw new BusinessServiceException("no record delete.");
        }

        for (PutawayStrategyDetailTEntity detail : detailList) {
            PutawayStrategyDetailTEntity update = PutawayStrategyDetailTEntity.builder()
                    .updateBy(detail.getUpdateBy())
                    .updateTime(new Date())
                    .delFlag(YesNoEnum.Yes.getCode())
                    .build();

            PutawayStrategyDetailTExample example = new PutawayStrategyDetailTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(detail.getWarehouseId())
                    .andCompanyIdEqualTo(detail.getCompanyId())
                    .andPutawayStrategyIdEqualTo(detail.getPutawayStrategyId())
                    .andPutawayStrategyDetailIdEqualTo(detail.getPutawayStrategyDetailId());

            int row = putawayStrategyDetailDao.updateWithVersionByExampleSelective(detail.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("delete update error.");
            }
        }
        return Boolean.TRUE;
    }
    
    
    private Boolean validate(PutawayStrategyDetailTEntity detail) {
    	if (StringUtils.isEmpty(detail.getType()))
    		throw new BusinessServiceException("PutawayStrategyDetailServiceImpl", "type.isnull");
    	
    	return Boolean.TRUE;
    }
}
