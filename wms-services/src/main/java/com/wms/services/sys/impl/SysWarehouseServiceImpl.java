package com.wms.services.sys.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.async.AsyncInit;
import com.wms.async.manager.AsyncManager;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.RoleDataTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysWarehousesTDao;
import com.wms.dao.example.SysWarehousesTExample;
import com.wms.entity.auto.SysRoleDataTEntity;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.entity.auto.SysWarehousesTEntity;
import com.wms.services.sys.ISysRoleDataService;
import com.wms.services.sys.ISysUserRoleService;
import com.wms.services.sys.ISysWarehouseService;
import com.wms.vo.WarehouseVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class SysWarehouseServiceImpl implements ISysWarehouseService {

	@Autowired
	private ISysWarehousesTDao warehousesDao;
	
	@Autowired
	private ISysUserRoleService userRoleService;
	
	@Autowired
	private ISysRoleDataService roleDataService;;
	
	@Override
	public List<SysWarehousesTEntity> findUserWarehouse(SysUserTEntity user) throws BusinessServiceException {
		List<SysRoleTEntity> userRoles = userRoleService.findUserRole(user);
		if (CollectionUtils.isEmpty(userRoles)) {
			return null;
		}
		return findRoleWarehouse(userRoles);
	}

	@Override
	public List<SysWarehousesTEntity> findAll() {
		SysWarehousesTExample warehouseExample = new SysWarehousesTExample();
		warehouseExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode());
		List<SysWarehousesTEntity> warehouses = warehousesDao.selectByExample(warehouseExample);
		if (CollectionUtils.isEmpty(warehouses)) {
			return null;
		}
		return warehouses;
	}

	@Override
	public List<SysWarehousesTEntity> find(PageRequest request) throws BusinessServiceException {
		SysWarehousesTExample TExample = new SysWarehousesTExample();
		SysWarehousesTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SysWarehousesTEntity.Column.class, SysWarehousesTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		return warehousesDao.selectByExample(TExample);
	}

	@Override
	@Transactional
	public Boolean modify(AjaxRequest<List<SysWarehousesTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}
		
		List<SysWarehousesTEntity> list = request.getData();
		
		for (SysWarehousesTEntity w : list) {
			
			SysWarehousesTEntity update = SysWarehousesTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.descr(w.getDescr())
			.active(w.getActive())
			.build();
			
			SysWarehousesTExample example = new SysWarehousesTExample();
			example.createCriteria()
			.andWarehouseIdEqualTo(w.getWarehouseId());
			
			int row = warehousesDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean add(AjaxRequest<List<SysWarehousesTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}
		
		List<SysWarehousesTEntity> list = request.getData();
		
		for (SysWarehousesTEntity w : list) {
			
			String code = w.getCode().toUpperCase();
			
			SysWarehousesTExample TExample = new SysWarehousesTExample();
			TExample.createCriteria()
			.andDelFlagEqualTo(YesNoEnum.No.getCode())
			.andCodeEqualTo(code);
			Long count = warehousesDao.countByExample(TExample);
			if (count > 0) {
				throw new BusinessServiceException("SysWarehouseServiceImpl", "warehouse.record.exists" , new Object[] {code}); 
			}
			
			SysWarehousesTEntity update = SysWarehousesTEntity.builder()
			.createBy(request.getUserName())
			.createTime(new Date())
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.code(code)
			.descr(w.getDescr())
			.active(w.getActive())
			.warehouseId(KeyUtils.getUID())
			.build();
			
			int row = warehousesDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<SysWarehousesTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<SysWarehousesTEntity> list = request.getData();
		
		for (SysWarehousesTEntity w : list) {
			SysWarehousesTEntity update = SysWarehousesTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.delFlag(YesNoEnum.Yes.getCode())
			.build();
			
			SysWarehousesTExample example = new SysWarehousesTExample();
			example.createCriteria()
			.andWarehouseIdEqualTo(w.getWarehouseId());
			
			int row = warehousesDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public SysWarehousesTEntity findById(Long warehouseId) {
		SysWarehousesTExample TExample = new SysWarehousesTExample();
		TExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(warehouseId);
		SysWarehousesTEntity w = warehousesDao.selectOneByExample(TExample);
		if (w == null) {
			throw new BusinessServiceException("SysWarehouseServiceImpl", "warehouse.record.not.exists" , null); 
		}
		return w;
	}
	
	@Override
	public SysWarehousesTEntity findByCode(String code) {
		SysWarehousesTExample TExample = new SysWarehousesTExample();
		TExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCodeEqualTo(code);
		SysWarehousesTEntity w = warehousesDao.selectOneByExample(TExample);
		if (w == null) {
			throw new BusinessServiceException("SysWarehouseServiceImpl", "warehouse.record.not.exists" , null); 
		}
		return w;
	}

	@Override
	public List<SysWarehousesTEntity> findByIds(Set<Long> ids) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(ids))
			return Lists.newArrayList();

		SysWarehousesTExample example = new SysWarehousesTExample();
		SysWarehousesTExample.Criteria criteria = example.createCriteria();

		criteria
				.andDelFlagEqualTo(YesNoEnum.No.getCode())
				.andWarehouseIdIn(Lists.newArrayList(ids));

		List<SysWarehousesTEntity> warehouses = warehousesDao.selectByExample(example);

		return warehouses;
	}

	@Override
	public List<SysWarehousesTEntity> findRoleWarehouse(List<SysRoleTEntity> roles) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(roles))
			return null;
		
		List<SysRoleDataTEntity> roleDatas = roleDataService.findRoleData(roles, RoleDataTypeEnum.Warehouse);
		if (CollectionUtils.isEmpty(roleDatas)) {
			return null;
		}
		
		Set<Long> ids = Sets.newHashSet();
		roleDatas.forEach(r -> {
			if (r != null && null != r.getDataId())
				ids.add(r.getDataId());
		});
		
		if (CollectionUtils.isEmpty(ids))
			return Lists.newArrayList();
		
		SysWarehousesTExample warehouseExample = new SysWarehousesTExample();
		warehouseExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdIn(Lists.newArrayList(ids));
		List<SysWarehousesTEntity> warehouses = warehousesDao.selectByExample(warehouseExample);
		return warehouses;
	}
	
	public List<SysWarehousesTEntity> findRoleAvailable(SysRoleTEntity role) throws BusinessServiceException{
		List<SysWarehousesTEntity> existsList = findRoleWarehouse(Lists.newArrayList(role));
		
		SysWarehousesTExample warehouseExample = new SysWarehousesTExample();
		SysWarehousesTExample.Criteria createCriteria = warehouseExample.createCriteria();
		createCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		if (CollectionUtils.isNotEmpty(existsList)) {
			Set<Long> ids = Sets.newHashSet();
			existsList.forEach(w -> {
				ids.add(w.getWarehouseId());
			});
			createCriteria.andWarehouseIdNotIn(Lists.newArrayList(ids));
		}
		List<SysWarehousesTEntity> warehouses = warehousesDao.selectByExample(warehouseExample);
		return warehouses;
	}

	@Override
	public Boolean init(AjaxRequest<WarehouseVO> request) throws BusinessServiceException {
		
		WarehouseVO warehouse = request.getData();
		
		//初始化数据字典
		
		//初始化库区&库位
		AsyncManager.me().execute(AsyncInit.location(warehouse.getCompanyId(), warehouse.getWarehouseId()));
		
		//初始化单据号
		AsyncManager.me().execute(AsyncInit.orderNumber(warehouse.getCompanyId(), warehouse.getWarehouseId()));
		
		//初始化批属性验证
		AsyncManager.me().execute(AsyncInit.lotValidate(warehouse.getCompanyId(), warehouse.getWarehouseId()));
		
		//初始化包装 STD
		AsyncManager.me().execute(AsyncInit.pack(warehouse.getCompanyId(), warehouse.getWarehouseId()));
		
		//初始化上架策略 STD
		AsyncManager.me().execute(AsyncInit.putawayStrategy(warehouse.getCompanyId(), warehouse.getWarehouseId()));
		
		//初始化分配策略 STD
		AsyncManager.me().execute(AsyncInit.allocateStrategy(warehouse.getCompanyId(), warehouse.getWarehouseId()));
		
		//初始化参数
		AsyncManager.me().execute(AsyncInit.config(warehouse.getCompanyId(), warehouse.getWarehouseId()));
		
		return Boolean.TRUE;
	}

}
