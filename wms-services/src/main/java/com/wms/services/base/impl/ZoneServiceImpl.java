package com.wms.services.base.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.services.IExcelService;
import com.wms.common.enums.ExcelTemplateEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IZoneTDao;
import com.wms.dao.example.ZoneTExample;
import com.wms.entity.auto.ZoneTEntity;
import com.wms.services.base.IZoneService;
import com.wms.vo.excel.ZoneImportVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @description: 基础数据-库区服务实现类
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-19 10:41
 **/

@Service
public class ZoneServiceImpl implements IZoneService , IExcelService<ZoneImportVO> {

    @Autowired
    private IZoneTDao zoneDao;

    @Override
    public List<ZoneTEntity> find(PageRequest request) throws BusinessServiceException {
        ZoneTExample zoneTExample = new ZoneTExample();
        ZoneTExample.Criteria zoneTExampleCriteria = zoneTExample.createCriteria();

        //转换查询方法
        ExampleUtils.create(ZoneTEntity.Column.class, ZoneTExample.Criterion.class)
                .criteria(zoneTExampleCriteria)
                .data(request)
                .build(request)
                .orderby(zoneTExample);
        zoneTExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return zoneDao.selectByExample(zoneTExample);
    }
    
    @Override
    public List<ZoneTEntity> find(ZoneTEntity zone, Set<Long> ids) throws BusinessServiceException {
    	if (CollectionUtils.isEmpty(ids))
    		return Lists.newArrayList();
    	
        ZoneTExample zoneTExample = new ZoneTExample();
        ZoneTExample.Criteria zoneTExampleCriteria = zoneTExample.createCriteria();
        zoneTExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
        .andWarehouseIdEqualTo(zone.getWarehouseId())
        .andCompanyIdEqualTo(zone.getCompanyId())
        .andZoneIdIn(Lists.newArrayList(ids));
        return zoneDao.selectByExample(zoneTExample);
    }

    @Override
    @Transactional
    public Boolean modify(AjaxRequest<List<ZoneTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record update.");
        }

        List<ZoneTEntity> list = request.getData();

        for (ZoneTEntity zone : list) {

            ZoneTEntity update = ZoneTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .type(zone.getType())
                    .pickToLocation(zone.getPickToLocation())
                    .active(zone.getActive())
                    .build();

            ZoneTExample example = new ZoneTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(zone.getWarehouseId())
                    .andCompanyIdEqualTo(zone.getCompanyId())
                    .andZoneIdEqualTo(zone.getZoneId());

            int row = zoneDao.updateWithVersionByExampleSelective(zone.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean add(AjaxRequest<List<ZoneTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record add.");
        }
        List<ZoneTEntity> list = request.getData();
        ZoneTExample zoneTExample = new ZoneTExample();
        for (ZoneTEntity zone : list) {
            String zoneCode = zone.getZoneCode().toUpperCase();

            zoneTExample.createCriteria()
                    .andDelFlagEqualTo(YesNoEnum.No.getCode())
                    .andZoneCodeEqualTo(zoneCode)
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId());

            long count = zoneDao.countByExample(zoneTExample);
            if (count > 0) {
                throw new BusinessServiceException("IZoneServiceImpl", "zone.record.exists", new Object[]{zoneCode});
            }

            ZoneTEntity insert = new ZoneTEntity().builder()
                    .createBy(request.getUserName())
                    .createTime(new Date())
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .zoneId(KeyUtils.getUID())
                    .zoneCode(zoneCode)
                    .type(zone.getType())
                    .pickToLocation(zone.getPickToLocation())
                    .active(zone.getActive())
                    .companyId(request.getCompanyId())
                    .warehouseId(request.getWarehouseId())
                    .build();
            int row = zoneDao.insertSelective(insert);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(AjaxRequest<List<ZoneTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record delete.");
        }
        List<ZoneTEntity> list = request.getData();

        for (ZoneTEntity zone : list) {
            ZoneTEntity update = ZoneTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .delFlag(YesNoEnum.Yes.getCode())
                    .build();

            ZoneTExample example = new ZoneTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(zone.getWarehouseId())
                    .andCompanyIdEqualTo(zone.getCompanyId())
                    .andZoneIdEqualTo(zone.getZoneId());

            int row = zoneDao.updateWithVersionByExampleSelective(zone.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("delete update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public ZoneTEntity find(ZoneTEntity zone) throws BusinessServiceException {
        ZoneTExample TExample = new ZoneTExample();
        ZoneTExample.Criteria criteria = TExample.createCriteria();
        criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(zone.getWarehouseId())
                .andCompanyIdEqualTo(zone.getCompanyId());
        int conditionCount = 0;
        if (null != zone.getZoneCode()) {
            criteria.andZoneCodeEqualTo(zone.getZoneCode());
            conditionCount++;
        }
        if (null != zone.getZoneId()) {
            criteria.andZoneIdEqualTo(zone.getZoneId());
            conditionCount++;
        }
        if (conditionCount == 0) {
            return null;
        }
        ZoneTEntity w = zoneDao.selectOneByExample(TExample);
        if (w == null) {
            throw new BusinessServiceException("ZoneServiceImpl", "zone.record.not.exists", null);
        }
        return w;
    }

    @Override
    public List<ZoneTEntity> findByZoneCodes(ZoneTEntity sku, Set<String> codes) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(codes))
            return Lists.newArrayList();

        ZoneTExample example = new ZoneTExample();
        ZoneTExample.Criteria criteria = example.createCriteria();

        criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(sku.getWarehouseId())
                .andCompanyIdEqualTo(sku.getCompanyId())
                .andZoneCodeIn(Lists.newArrayList(codes));

        List<ZoneTEntity> zones = zoneDao.selectByExample(example);

        return zones;
    }

    @Override
    public ExcelTemplate getExcelTemplate() {
        return new ExcelTemplate<ZoneImportVO>(ExcelTemplateEnum.Zone.getCode(), ZoneImportVO.class);
    }

    @Override
    public void importData(AjaxRequest<List<ZoneImportVO>> request) throws BusinessServiceException {
        List<ZoneImportVO> list = request.getData();
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessServiceException("no data.");
        }
        list.forEach(d->{
            if(StringUtils.isBlank(d.getZoneCode())){
                return;
            }
            ZoneTEntity zone = new ZoneTEntity();
            BeanUtils.copyBeanProp(zone, d, Boolean.FALSE);
            Set<String> codes = Sets.newHashSet();
            codes.add(zone.getZoneCode());
            zone.setCompanyId(request.getCompanyId());
            zone.setWarehouseId(request.getWarehouseId());
            List<ZoneTEntity> zones =findByZoneCodes(zone,codes);
            List<ZoneTEntity> updates=Lists.newArrayList();
            request.setUserName((String) request.get(ZoneTEntity.Column.updateBy.getJavaProperty()));
            if(CollectionUtils.isEmpty(zones)){
                updates.add(zone);
                add(new AjaxRequest<List<ZoneTEntity>>(updates,request));
            }else {
                zone.setZoneId(zones.get(0).getZoneId());
                zone.setUpdateVersion(zones.get(0).getUpdateVersion());
                updates.add(zone);
                modify(new AjaxRequest<List<ZoneTEntity>>(updates,request));
            }
        });
    }

    @Override
    public List<ZoneImportVO> exportData(PageRequest request) throws BusinessServiceException {
        List<ZoneImportVO> returnList = Lists.newArrayList();
        List<ZoneTEntity> zones = find(request);
        if (CollectionUtils.isEmpty(zones)) {
            return returnList;
        }
        zones.forEach(d ->{
            ZoneImportVO zone = new ZoneImportVO();
            BeanUtils.copyBeanProp(zone, d);
            returnList.add(zone);
        });
        return returnList;
    }
}
