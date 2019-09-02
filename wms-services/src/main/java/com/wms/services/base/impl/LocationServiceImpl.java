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
import com.wms.dao.auto.ILocationTDao;
import com.wms.dao.example.LocationTExample;
import com.wms.entity.auto.AreaDetailTEntity;
import com.wms.entity.auto.AreaTEntity;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.ZoneTEntity;
import com.wms.services.base.IAreaDetailService;
import com.wms.services.base.ILocationService;
import com.wms.services.base.IZoneService;
import com.wms.vo.excel.LocationImportVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class LocationServiceImpl implements ILocationService, IExcelService<LocationImportVO> {


    @Autowired
    private ILocationTDao locationDao;

    @Autowired
    private IZoneService zoneService;

    @Autowired
    private IAreaDetailService areaDetailService;

    @Override
    public LocationTEntity find(LocationTEntity location) throws BusinessServiceException {

        LocationTExample example = new LocationTExample();
        LocationTExample.Criteria criteria = example.createCriteria();

        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(location.getWarehouseId())
                .andCompanyIdEqualTo(location.getCompanyId());

        int conditionCount = 0;
        if (location.getLocationId() != null) {
            criteria.andLocationIdEqualTo(location.getLocationId());
            conditionCount++;
        }
        if (StringUtils.isNotEmpty(location.getLocationCode())) {
            criteria.andLocationCodeEqualTo(location.getLocationCode());
            conditionCount++;
        }
        if (conditionCount == 0){
            return null;
        }

        LocationTEntity selectLocation = locationDao.selectOneByExample(example);
        if (selectLocation == null){
            throw new BusinessServiceException("LocationServiceImpl", "location.record.not.exists", new Object[]{location.getLocationId() == null ? location.getLocationCode() : location.getLocationId()});
        }

        return selectLocation;

    }
    
    @Override
	public List<LocationTEntity> findByZone(LocationTEntity location) throws BusinessServiceException {
		LocationTExample example = new LocationTExample();
        LocationTExample.Criteria criteria = example.createCriteria();

        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(location.getWarehouseId())
                .andCompanyIdEqualTo(location.getCompanyId());

        int conditionCount = 0;
        if (location.getZoneId() != null) {
            criteria.andZoneIdEqualTo(location.getZoneId());
            conditionCount++;
        }
        if (StringUtils.isNotEmpty(location.getZoneCode())) {
            criteria.andZoneCodeEqualTo(location.getZoneCode());
            conditionCount++;
        }
        if (conditionCount == 0){
            return null;
        }

        List<LocationTEntity> selectLocation = locationDao.selectByExample(example);

        return selectLocation;
	}

    @Override
    public List<LocationTEntity> find(PageRequest request) throws BusinessServiceException {
        LocationTExample locationTExample = new LocationTExample();
        LocationTExample.Criteria locationTExampleCriteria = locationTExample.createCriteria();

        //转换查询方法
        ExampleUtils.create(LocationTEntity.Column.class, LocationTExample.Criterion.class)
                .criteria(locationTExampleCriteria)
                .data(request)
                .build(request)
                .orderby(locationTExample);
        locationTExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return locationDao.selectByExample(locationTExample);
    }

    @Override
    public List<LocationTEntity> findByLocationCodes(LocationTEntity location, Set<String> codes)
            throws BusinessServiceException {

        if (CollectionUtils.isEmpty(codes)) {
            return Lists.newArrayList();
        }
        LocationTExample example = new LocationTExample();
        LocationTExample.Criteria criteria = example.createCriteria();

        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(location.getWarehouseId())
                .andCompanyIdEqualTo(location.getCompanyId())
                .andLocationCodeIn(Lists.newArrayList(codes));

        List<LocationTEntity> list = locationDao.selectByExample(example);

        return list;
    }
    
    @Override
    public List<LocationTEntity> findByLocationIds(LocationTEntity location, Set<Long> ids)
            throws BusinessServiceException {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        LocationTExample example = new LocationTExample();
        LocationTExample.Criteria criteria = example.createCriteria();

        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(location.getWarehouseId())
                .andCompanyIdEqualTo(location.getCompanyId())
                .andLocationIdIn(Lists.newArrayList(ids));

        List<LocationTEntity> list = locationDao.selectByExample(example);

        return list;
    }

    @Override
    public List<LocationTEntity> findlocationAvailable(AreaTEntity area) throws BusinessServiceException {
        List<AreaDetailTEntity> existsList = areaDetailService.findByArea(area);

        LocationTExample locationTExample = new LocationTExample();
        LocationTExample.Criteria createCriteria = locationTExample.createCriteria();
        createCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andCompanyIdEqualTo(area.getCompanyId())
                .andWarehouseIdEqualTo(area.getWarehouseId());

        if (CollectionUtils.isNotEmpty(existsList)) {
            Set<Long> ids = Sets.newHashSet();
            existsList.forEach(w -> {
                ids.add(w.getLocationId());
            });
            createCriteria.andLocationIdNotIn(Lists.newArrayList(ids));
        }
        List<LocationTEntity> locations = locationDao.selectByExample(locationTExample);
        return locations;
    }

    @Override
    @Transactional
    public Boolean modify(AjaxRequest<List<LocationTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record update.");
        }
        List<LocationTEntity> list = request.getData();

        for (LocationTEntity w : list) {
            ZoneTEntity zone = zoneService.find(ZoneTEntity.builder()
                    .warehouseId(w.getWarehouseId())
                    .companyId(w.getCompanyId())
                    .zoneCode(w.getZoneCode().toUpperCase())
                    .build());

            LocationTEntity update = LocationTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .locationType(w.getLocationType())
                    .locationLogical(w.getLocationLogical())
                    .zoneId(zone.getZoneId())
                    .zoneCode(zone.getZoneCode().toUpperCase())
                    .x(w.getX())
                    .y(w.getY())
                    .z(w.getZ())
                    .skuMix(w.getSkuMix())
                    .lotMix(w.getLotMix())
                    .active(w.getActive())
                    .build();

            LocationTExample example = new LocationTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(w.getWarehouseId())
                    .andCompanyIdEqualTo(w.getCompanyId())
                    .andLocationIdEqualTo(w.getLocationId());

            int row = locationDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean add(AjaxRequest<List<LocationTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record add.");
        }

        List<LocationTEntity> list = request.getData();

        for (LocationTEntity w : list) {

            String locationCode = w.getLocationCode().toUpperCase();

            LocationTExample example = new LocationTExample();
            example.createCriteria()
                    .andDelFlagEqualTo(YesNoEnum.No.getCode())
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andLocationCodeEqualTo(locationCode);
            long count = locationDao.countByExample(example);
            if (count > 0) {
                throw new BusinessServiceException("LocationServiceImpl", "location.record.exists", new Object[]{locationCode});
            }

            ZoneTEntity zone = zoneService.find(ZoneTEntity.builder()
                    .warehouseId(request.getWarehouseId())
                    .companyId(request.getCompanyId())
                    .zoneCode(w.getZoneCode().toUpperCase())
                    .build());

            LocationTEntity insert = LocationTEntity.builder()
                    .createBy(request.getUserName())
                    .createTime(new Date())
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .companyId(request.getCompanyId())
                    .warehouseId(request.getWarehouseId())
                    .locationId(KeyUtils.getUID())
                    .locationCode(locationCode)
                    .locationType(w.getLocationType())
                    .locationLogical(w.getLocationLogical())
                    .zoneId(zone.getZoneId())
                    .zoneCode(zone.getZoneCode().toUpperCase())
                    .x(w.getX())
                    .y(w.getY())
                    .z(w.getZ())
                    .skuMix(w.getSkuMix())
                    .lotMix(w.getLotMix())
                    .active(w.getActive())
                    .build();

            int row = locationDao.insertSelective(insert);
            if (row == 0) {
                throw new BusinessServiceException("record update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean delete(AjaxRequest<List<LocationTEntity>> request) throws BusinessServiceException {
        if (CollectionUtils.isEmpty(request.getData())) {
            throw new BusinessServiceException("no record delete.");
        }
        List<LocationTEntity> list = request.getData();

        for (LocationTEntity w : list) {
            LocationTEntity update = LocationTEntity.builder()
                    .updateBy(request.getUserName())
                    .updateTime(new Date())
                    .delFlag(YesNoEnum.Yes.getCode())
                    .build();

            LocationTExample example = new LocationTExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(w.getWarehouseId())
                    .andCompanyIdEqualTo(w.getCompanyId())
                    .andLocationIdEqualTo(w.getLocationId())
                    .andZoneIdEqualTo(w.getZoneId());

            int row = locationDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
            if (row == 0) {
                throw new BusinessServiceException("delete update error.");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public ExcelTemplate getExcelTemplate() {
        return new ExcelTemplate<LocationImportVO>(ExcelTemplateEnum.Location.getCode(), LocationImportVO.class);
    }

    @Override
    public List<LocationImportVO> exportData(PageRequest request) throws BusinessServiceException {
        List<LocationImportVO> returnList = Lists.newArrayList();
        List<LocationTEntity> locations = find(request);
        if (CollectionUtils.isEmpty(locations)) {
            return returnList;
        }
        locations.forEach(d ->{
            LocationImportVO location = new LocationImportVO();
            BeanUtils.copyBeanProp(location, d);
            returnList.add(location);
        });
        return returnList;
    }

    @Override
    public void importData(AjaxRequest<List<LocationImportVO>> request) throws BusinessServiceException {
        List<LocationImportVO> list = request.getData();
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessServiceException("no data.");
        }
        list.forEach(d->{
            if(StringUtils.isBlank(d.getLocationCode())){
                return;
            }
            LocationTEntity location = new LocationTEntity();
            BeanUtils.copyBeanProp(location, d, Boolean.FALSE);
            Set<String> codes = Sets.newHashSet();
            codes.add(location.getLocationCode());
            location.setCompanyId(request.getCompanyId());
            location.setWarehouseId(request.getWarehouseId());
            List<LocationTEntity> locations =findByLocationCodes(location,codes);
            List<LocationTEntity> updates=Lists.newArrayList();
            request.setUserName((String) request.get(LocationTEntity.Column.updateBy.getJavaProperty()));
            if(CollectionUtils.isEmpty(locations)){
                updates.add(location);
                add(new AjaxRequest<List<LocationTEntity>>(updates,request));
            }else {
                location.setLocationId(locations.get(0).getLocationId());
                location.setUpdateVersion(locations.get(0).getUpdateVersion());
                updates.add(location);
                modify(new AjaxRequest<List<LocationTEntity>>(updates,request));
            }
        });
    }

	
}
