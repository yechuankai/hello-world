package com.wms.services.sys.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysRoleTDao;
import com.wms.dao.auto.ISysUserRoleTDao;
import com.wms.dao.example.SysRoleTExample;
import com.wms.dao.example.SysUserRoleTExample;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.services.sys.ISysRoleService;
import com.wms.services.sys.ISysUserRoleService;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

	@Autowired
	ISysRoleTDao roleDao;
	
	@Autowired
	ISysUserRoleTDao userRoleDao;
	
	@Autowired
	ISysUserRoleService userRoleService;
	
	@Override
	public List<SysRoleTEntity> find(PageRequest request) throws BusinessServiceException {
		SysRoleTExample TExample = new SysRoleTExample();
		SysRoleTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SysRoleTEntity.Column.class, SysUserRoleTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		return roleDao.selectByExample(TExample);
	}

	@Override
	@Transactional
	public Boolean modify(AjaxRequest<List<SysRoleTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}
		
		List<SysRoleTEntity> list = request.getData();
		
		for (SysRoleTEntity r : list) {
			
			SysRoleTEntity update = SysRoleTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.descr(r.getDescr())
			.active(r.getActive())
			.build();
			
			SysRoleTExample example = new SysRoleTExample();
			example.createCriteria()
			.andRoleIdEqualTo(r.getRoleId());
			
			int row = roleDao.updateWithVersionByExampleSelective(r.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean add(AjaxRequest<List<SysRoleTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}
		
		List<SysRoleTEntity> list = request.getData();
		
		for (SysRoleTEntity c : list) {
			
			String code = c.getCode().toUpperCase();
			
			SysRoleTExample TExample = new SysRoleTExample();
			TExample.createCriteria()
			.andDelFlagEqualTo(YesNoEnum.No.getCode())
			.andCodeEqualTo(code);
			Long count = roleDao.countByExample(TExample);
			if (count > 0) {
				throw new BusinessServiceException("SysRoleServiceImpl", "role.record.exists" , new Object[] {code}); 
			}
			
			SysRoleTEntity update = SysRoleTEntity.builder()
			.createBy(request.getUserName())
			.createTime(new Date())
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.code(code)
			.descr(c.getDescr())
			.active(c.getActive())
			.roleId(KeyUtils.getUID())
			.build();
			
			int row = roleDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<SysRoleTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<SysRoleTEntity> list = request.getData();
		
		for (SysRoleTEntity r : list) {
			SysRoleTEntity update = SysRoleTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.delFlag(YesNoEnum.Yes.getCode())
			.build();
			
			SysRoleTExample example = new SysRoleTExample();
			example.createCriteria()
			.andRoleIdEqualTo(r.getRoleId());
			
			int row = roleDao.updateWithVersionByExampleSelective(r.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public SysRoleTEntity findByRoleId(Long roleId) throws BusinessServiceException {
		SysRoleTExample TExample = new SysRoleTExample();
		SysRoleTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		TExampleCriteria.andRoleIdEqualTo(roleId).andDelFlagEqualTo(YesNoEnum.No.getCode());
		SysRoleTEntity role = roleDao.selectOneByExample(TExample);
		if (role == null) {
			throw new BusinessServiceException("SysRoleServiceImpl", "company.record.not.exists", null);
		}
		return role;
	}

	@Override
	public List<SysRoleTEntity> findUserAvailable(AjaxRequest<SysUserTEntity> request) throws BusinessServiceException {
		List<SysRoleTEntity> userRoles = userRoleService.findUserRole(request.getData());
		
		SysRoleTExample TExample = new SysRoleTExample();
		SysRoleTExample.Criteria TExampleCriteria = TExample.createCriteria();
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		if (CollectionUtils.isNotEmpty(userRoles)) {
			Set<Long> ids = Sets.newHashSet();
			userRoles.forEach(r -> {
				ids.add(r.getRoleId());
			});
			TExampleCriteria.andRoleIdNotIn(Lists.newArrayList(ids));
		}
		
		return roleDao.selectByExample(TExample);
	}
}
