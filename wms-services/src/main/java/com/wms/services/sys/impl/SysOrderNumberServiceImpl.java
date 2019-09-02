package com.wms.services.sys.impl;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.dao.auto.ISysOrderNumberTDao;
import com.wms.dao.example.SysOrderNumberTExample;
import com.wms.entity.auto.SysOrderNumberTEntity;
import com.wms.services.sys.ISysOrderNumberService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-05 15:30
 **/
@Service
public class SysOrderNumberServiceImpl implements ISysOrderNumberService {

    @Autowired
    ISysOrderNumberTDao sysOrderNumberDao;
    @Override
    public List<SysOrderNumberTEntity> find(PageRequest request) throws BusinessServiceException {
        SysOrderNumberTExample example=new SysOrderNumberTExample();
        SysOrderNumberTExample.Criteria exampleCriteria = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(SysOrderNumberTEntity.Column.class,SysOrderNumberTExample.Criterion.class)
                .criteria(exampleCriteria)
                .data(request)
                .build(request)
                .orderby(example);
        exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return sysOrderNumberDao.selectByExample(example);
    }

    @Override
    public Boolean modify(AjaxRequest<List<SysOrderNumberTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record update.");
        }
        List<SysOrderNumberTEntity> list =request.getData();

        for(SysOrderNumberTEntity orderNumber : list){
            SysOrderNumberTEntity update = SysOrderNumberTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .descr(orderNumber.getDescr())
                    .prefix(orderNumber.getPrefix())
                    .length(orderNumber.getLength())
                    .dataFormat(orderNumber.getDataFormat())
                    .sequence(orderNumber.getSequence())
                    .sequenceCache(orderNumber.getSequenceCache())
                    .sequenceIncrement(orderNumber.getSequenceIncrement())
                    .build();

            SysOrderNumberTExample example = new SysOrderNumberTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(orderNumber.getWarehouseId())
                    .andCompanyIdEqualTo(orderNumber.getCompanyId())
                    .andOrderNumberIdEqualTo(orderNumber.getOrderNumberId());

            int row = sysOrderNumberDao.updateWithVersionByExampleSelective(orderNumber.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }
}
