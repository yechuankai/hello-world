package com.wms.services.sys.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.enums.RoleDataTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysCompanysTDao;
import com.wms.dao.auto.ISysRoleDataTDao;
import com.wms.dao.auto.ISysWarehousesTDao;
import com.wms.dao.example.SysCompanysTExample;
import com.wms.dao.example.SysRoleDataTExample;
import com.wms.dao.example.SysWarehousesTExample;
import com.wms.entity.auto.SysCompanysTEntity;
import com.wms.entity.auto.SysRoleDataTEntity;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysWarehousesTEntity;
import com.wms.services.sys.ISysCompanyService;
import com.wms.services.sys.ISysRoleDataService;
import com.wms.services.sys.ISysWarehouseService;
import com.wms.vo.RoleCompanyVO;
import com.wms.vo.RoleWarehouseVO;

@Service
public class SysRoleDataServiceImpl implements ISysRoleDataService {

	@Autowired
	private ISysRoleDataTDao roleDataDao;
	
	@Autowired
	private ISysWarehousesTDao warehouseDao;
	
	@Autowired
	private ISysCompanysTDao companyDao;
	
	@Autowired
	private ISysWarehouseService warehouseService;
	
	@Autowired
	private ISysCompanyService companyService;
	
	@Override
	public List<SysRoleDataTEntity> findRoleData(List<SysRoleTEntity> roles, RoleDataTypeEnum type) {
		if (CollectionUtils.isEmpty(roles)) 
			return null;
		
		Set<Long> ids = Sets.newHashSet();
		roles.forEach(r -> {
			ids.add(r.getRoleId());
		});
		
		SysRoleDataTExample example = new SysRoleDataTExample();
		SysRoleDataTExample.Criteria createCriteria = example.createCriteria();
		createCriteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andRoleIdIn(Lists.newArrayList(ids));
		
		if( type != null )
			createCriteria.andDataTypeEqualTo(type.getCode());
		
		List<SysRoleDataTEntity> dataRoles = roleDataDao.selectByExample(example);
		return dataRoles;
		
	}

	@Override
	public Boolean add(AjaxRequest<List<SysRoleDataTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}
		
		List<SysRoleDataTEntity> list = request.getData();
		
		for (SysRoleDataTEntity r : list) {
			
			if(RoleDataTypeEnum.Warehouse.getCode().equals(r.getDataType()))
				warehouseService.findById(r.getDataId());
			else if(RoleDataTypeEnum.Company.getCode().equals(r.getDataType()))
				companyService.findById(r.getDataId());
					
			SysRoleDataTEntity update = SysRoleDataTEntity.builder()
			.createBy(request.getUserName())
			.createTime(new Date())
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.dataId(r.getDataId())
			.dataType(r.getDataType())
			.roleId(r.getRoleId())
			.roleDataId(KeyUtils.getUID())
			.build();
			
			int row = roleDataDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<SysRoleDataTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<SysRoleDataTEntity> list = request.getData();
		
		for (SysRoleDataTEntity r : list) {
			SysRoleDataTEntity update = SysRoleDataTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.delFlag(YesNoEnum.Yes.getCode())
			.build();
			
			SysRoleDataTExample example = new SysRoleDataTExample();
			example.createCriteria()
			.andRoleDataIdEqualTo(r.getRoleDataId());
			
			int row = roleDataDao.updateWithVersionByExampleSelective(r.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}
	
	@Override
	public List<RoleWarehouseVO> findRoleWarehouse(List<SysRoleTEntity> roles) throws BusinessServiceException {
		List<SysRoleDataTEntity> list = findRoleData(roles, RoleDataTypeEnum.Warehouse);
		if (CollectionUtils.isEmpty(list))
			return null;
		
		Set<Long> ids = Sets.newHashSet();
		list.forEach(w -> {
			ids.add(w.getDataId());
		});
		
		SysWarehousesTExample warehouseExample = new SysWarehousesTExample();
		warehouseExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdIn(Lists.newArrayList(ids));
		List<SysWarehousesTEntity> warehouses = warehouseDao.selectByExample(warehouseExample);
		if (CollectionUtils.isEmpty(warehouses))
			return null;
		
		Map<Long, SysWarehousesTEntity> warehousesMap = warehouses.stream().collect( 
			      Collectors.toMap(SysWarehousesTEntity::getWarehouseId, (w) -> w)); 
		
		List<RoleWarehouseVO> returnList = Lists.newArrayList();
		list.forEach(d -> {
			RoleWarehouseVO vo = new RoleWarehouseVO(d);
			SysWarehousesTEntity w = warehousesMap.get(d.getDataId());
			if (w != null) {
				vo.setWarehouseCode(w.getCode());
				vo.setWarehouseDescr(w.getDescr());
			}
			returnList.add(vo);
		});
		return returnList;
	}
	
	@Override
	public List<RoleCompanyVO> findRoleCompany(List<SysRoleTEntity> roles) throws BusinessServiceException {
		List<SysRoleDataTEntity> list = findRoleData(roles, RoleDataTypeEnum.Company);
		if (CollectionUtils.isEmpty(list))
			return null;
		
		Set<Long> ids = Sets.newHashSet();
		list.forEach(w -> {
			ids.add(w.getDataId());
		});
		
		SysCompanysTExample companyExample = new SysCompanysTExample();
		companyExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCompanyIdIn(Lists.newArrayList(ids));
		List<SysCompanysTEntity> companys = companyDao.selectByExample(companyExample);
		if (CollectionUtils.isEmpty(companys))
			return null;
		
		Map<Long, SysCompanysTEntity> companysMap = companys.stream().collect( 
			      Collectors.toMap(SysCompanysTEntity::getCompanyId, (c) -> c)); 
		
		List<RoleCompanyVO> returnList = Lists.newArrayList();
		list.forEach(d -> {
			RoleCompanyVO vo = new RoleCompanyVO(d);
			SysCompanysTEntity c = companysMap.get(d.getDataId());
			if (c != null) {
				vo.setCompanyCode(c.getCode());
				vo.setCompanyDescr(c.getDescr());
			}
			returnList.add(vo);
		});
		return returnList;
	}

}
