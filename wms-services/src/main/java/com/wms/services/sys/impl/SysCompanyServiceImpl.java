package com.wms.services.sys.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.RoleDataTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysCompanysTDao;
import com.wms.dao.example.SysCompanysTExample;
import com.wms.dao.example.SysWarehousesTExample;
import com.wms.entity.auto.SysCompanysTEntity;
import com.wms.entity.auto.SysRoleDataTEntity;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.entity.auto.SysWarehousesTEntity;
import com.wms.services.sys.ISysCompanyService;
import com.wms.services.sys.ISysRoleDataService;
import com.wms.services.sys.ISysUserRoleService;

@Service
public class SysCompanyServiceImpl implements ISysCompanyService {

	private static final Logger log = LoggerFactory.getLogger(SysCompanyServiceImpl.class);
	
	@Autowired
	private ISysCompanysTDao companyDao ;
	
	@Autowired
	private ISysUserRoleService userRoleService;
	
	@Autowired
	private ISysRoleDataService roleDataService;;
	
	@Override
	public List<SysCompanysTEntity> find(PageRequest request) throws BusinessServiceException {
		SysCompanysTExample TExample = new SysCompanysTExample();
		SysCompanysTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SysCompanysTEntity.Column.class, SysCompanysTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		return companyDao.selectByExample(TExample);
	}
	
	@Override
	public List<SysCompanysTEntity> findAll() {
		SysCompanysTExample companyExample = new SysCompanysTExample();
		companyExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode());
		List<SysCompanysTEntity> companys = companyDao.selectByExample(companyExample);
		if (CollectionUtils.isEmpty(companys)) {
			return null;
		}
		return companys;
	}

	@Override
	@Transactional
	public Boolean modify(AjaxRequest<List<SysCompanysTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}
		
		List<SysCompanysTEntity> list = request.getData();
		
		for (SysCompanysTEntity c : list) {
			
			SysCompanysTEntity update = SysCompanysTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.descr(c.getDescr())
			.active(c.getActive())
			.build();
			
			SysCompanysTExample example = new SysCompanysTExample();
			example.createCriteria()
			.andCompanyIdEqualTo(c.getCompanyId());
			
			int row = companyDao.updateWithVersionByExampleSelective(c.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean add(AjaxRequest<List<SysCompanysTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}
		
		List<SysCompanysTEntity> list = request.getData();
		
		for (SysCompanysTEntity c : list) {
			
			String code = c.getCode().toUpperCase();
			
			SysCompanysTExample TExample = new SysCompanysTExample();
			TExample.createCriteria()
			.andDelFlagEqualTo(YesNoEnum.No.getCode())
			.andCodeEqualTo(code);
			Long count = companyDao.countByExample(TExample);
			if (count > 0) {
				throw new BusinessServiceException("SysConfigServiceImpl", "company.record.exists" , new Object[] {code}); 
			}
			
			
			SysCompanysTEntity update = SysCompanysTEntity.builder()
			.createBy(request.getUserName())
			.createTime(new Date())
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.code(code)
			.descr(c.getDescr())
			.active(c.getActive())
			.companyId(KeyUtils.getUID())
			.build();
			
			int row = companyDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<SysCompanysTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<SysCompanysTEntity> list = request.getData();
		
		for (SysCompanysTEntity c : list) {
			SysCompanysTEntity update = SysCompanysTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.delFlag(YesNoEnum.Yes.getCode())
			.build();
			
			SysCompanysTExample example = new SysCompanysTExample();
			example.createCriteria()
			.andCompanyIdEqualTo(c.getCompanyId());
			
			int row = companyDao.updateWithVersionByExampleSelective(c.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public SysCompanysTEntity findById(Long companyId) throws BusinessServiceException {
		SysCompanysTExample TExample = new SysCompanysTExample();
		SysCompanysTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		TExampleCriteria.andCompanyIdEqualTo(companyId).andDelFlagEqualTo(YesNoEnum.No.getCode());
		SysCompanysTEntity company = companyDao.selectOneByExample(TExample);
		if (company == null) {
			throw new BusinessServiceException("SysConfigServiceImpl", "company.record.not.exists", null);
		}
		return company;
	}
	
	@Override
	public List<SysCompanysTEntity> findUserCompany(SysUserTEntity user) throws BusinessServiceException {
		List<SysRoleTEntity> userRoles = userRoleService.findUserRole(user);
		if (CollectionUtils.isEmpty(userRoles)) {
			return null;
		}
		return findRoleCompany(userRoles);
	}
	
	@Override
	public List<SysCompanysTEntity> findRoleCompany(List<SysRoleTEntity> roles) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(roles))
			return null;
		
		List<SysRoleDataTEntity> roleDatas = roleDataService.findRoleData(roles, RoleDataTypeEnum.Company);
		if (CollectionUtils.isEmpty(roleDatas)) {
			return null;
		}
		
		Set<Long> ids = Sets.newHashSet();
		roleDatas.forEach(r -> {
			ids.add(r.getDataId());
		});
		
		SysCompanysTExample companyExample = new SysCompanysTExample();
		companyExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCompanyIdIn(Lists.newArrayList(ids));
		List<SysCompanysTEntity> warehouses = companyDao.selectByExample(companyExample);
		return warehouses;
	}

	@Override
	public List<SysCompanysTEntity> findRoleAvailable(SysRoleTEntity role) throws BusinessServiceException {
		List<SysCompanysTEntity> existsList = findRoleCompany(Lists.newArrayList(role));
		
		SysCompanysTExample companyExample = new SysCompanysTExample();
		SysCompanysTExample.Criteria createCriteria = companyExample.createCriteria();
		createCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		if (CollectionUtils.isNotEmpty(existsList)) {
			Set<Long> ids = Sets.newHashSet();
			existsList.forEach(w -> {
				ids.add(w.getCompanyId());
			});
			createCriteria.andCompanyIdNotIn(Lists.newArrayList(ids));
		}
		List<SysCompanysTEntity> companys = companyDao.selectByExample(companyExample);
		return companys;
	}

}
