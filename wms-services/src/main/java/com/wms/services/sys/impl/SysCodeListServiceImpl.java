package com.wms.services.sys.impl;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysCodelistTDao;
import com.wms.dao.example.SysCodelistTExample;
import com.wms.entity.auto.SysCodelistTEntity;
import com.wms.entity.auto.SysCodelkupTEntity;
import com.wms.services.sys.ISysCodeListService;
import com.wms.services.sys.ISysCodelkupService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @description: 数据字典主表服务实现类
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-25 16:46
 **/
@Service
public class SysCodeListServiceImpl implements ISysCodeListService {

    @Autowired
    ISysCodelistTDao sysCodelistDao;

    @Autowired
    ISysCodelkupService codelkupService;

    @Override
    public List<SysCodelistTEntity> find(PageRequest request) throws BusinessServiceException {
        SysCodelistTExample example=new SysCodelistTExample();
        SysCodelistTExample.Criteria exampleCriteria = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(SysCodelistTEntity.Column.class,SysCodelistTExample.Criterion.class)
                .criteria(exampleCriteria)
                .data(request)
                .build(request)
                .orderby(example);
        exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return sysCodelistDao.selectByExample(example);
    }

    @Override
    @Transactional
    public Boolean modify(AjaxRequest<List<SysCodelistTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record update.");
        }
        List<SysCodelistTEntity> list =request.getData();

        for(SysCodelistTEntity codelistTEntity : list){
            SysCodelistTEntity update = SysCodelistTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .type(codelistTEntity.getType())
                    .descr(codelistTEntity.getDescr())
                    .active(codelistTEntity.getActive())
                    .build();

            SysCodelistTExample example = new SysCodelistTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(codelistTEntity.getWarehouseId())
                    .andCompanyIdEqualTo(codelistTEntity.getCompanyId())
                    .andCodelistIdEqualTo(codelistTEntity.getCodelistId());

            int row = sysCodelistDao.updateWithVersionByExampleSelective(codelistTEntity.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean add(AjaxRequest<List<SysCodelistTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record add.");
        }

        List<SysCodelistTEntity> list = request.getData();

        for (SysCodelistTEntity w : list) {

            String code = w.getCode().toUpperCase();

            SysCodelistTExample TExample = new SysCodelistTExample();
            TExample.createCriteria()
                    .andDelFlagEqualTo(YesNoEnum.No.getCode())
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andCodeEqualTo(code);
            Long count = sysCodelistDao.countByExample(TExample);
            if (count > 0) {
                throw new BusinessServiceException("SysCodeListServiceImpl", "code.record.exists" , new Object[] {code});
            }
            
            String isAll = YesNoEnum.No.getCode();
            if (request.getWarehouseId() == 0L)
            	isAll = YesNoEnum.Yes.getCode();

            SysCodelistTEntity insert = SysCodelistTEntity.builder()
                    .createBy(request.getUserName())
                    .createTime(new Date())
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .companyId(request.getCompanyId())
                    .warehouseId(request.getWarehouseId())
                    .codelistId(KeyUtils.getUID())
                    .code(code)
                    .descr(w.getDescr())
                    .type(w.getType())
                    .active(w.getActive())
                    .isAll(isAll)
                    .build();

            int row = sysCodelistDao.insertSelective(insert);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean delete(AjaxRequest<List<SysCodelistTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record delete.");
        }
        List<SysCodelistTEntity> list = request.getData();

        for (SysCodelistTEntity codelistTEntity : list) {
            try{
                List<SysCodelkupTEntity> detailList = codelkupService.findByCodelist(codelistTEntity);
                if(detailList.size()>0){
                    for(SysCodelkupTEntity detail :detailList){
                        detail.setUpdateBy(request.getUserName());
                    }
                    codelkupService.delete(detailList);
                }
                SysCodelistTEntity update = SysCodelistTEntity.builder()
                        .updateBy(request.getUserName())
                        .updateTime(new Date())
                        .delFlag(YesNoEnum.Yes.getCode())
                        .build();

                SysCodelistTExample example = new SysCodelistTExample();
                example.createCriteria()
                        .andWarehouseIdEqualTo(codelistTEntity.getWarehouseId())
                        .andCompanyIdEqualTo(codelistTEntity.getCompanyId())
                        .andCodelistIdEqualTo(codelistTEntity.getCodelistId());

                int row = sysCodelistDao.updateWithVersionByExampleSelective(codelistTEntity.getUpdateVersion(), update, example);
                if (row == 0) {
                    throw new BusinessServiceException("delete update error.");
                }
            }catch (Exception e){
                throw new BusinessServiceException("delete update error.");
            }
        }

        return Boolean.TRUE;
    }
}
