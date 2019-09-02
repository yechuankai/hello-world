package com.wms.services.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import com.wms.entity.auto.SysUserRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.services.sys.ISysUserRoleService;
import com.wms.vo.UserRoleVO;

@Service
public class SysUserRoleServiceImpl implements ISysUserRoleService {

	@Autowired
	private ISysRoleTDao roleDao;
	
	@Autowired
	private ISysUserRoleTDao userRoleDao;
	
	@Override
	public List<SysRoleTEntity> findUserRole(SysUserTEntity user) {
		
		SysUserRoleTExample example = new SysUserRoleTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andUserIdEqualTo(user.getUserId());
		
		List<SysUserRoleTEntity> userRoles = userRoleDao.selectByExample(example);
		if (CollectionUtils.isEmpty(userRoles))
			return null;
		
		Set<Long> roleIds = Sets.newHashSet();
		for (SysUserRoleTEntity userRole : userRoles) {
			roleIds.add(userRole.getRoleId());
		}
		
		SysRoleTExample roleExample = new SysRoleTExample();
		roleExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andRoleIdIn(Lists.newArrayList(roleIds));
		
		List<SysRoleTEntity> roles = roleDao.selectByExample(roleExample);
		
		return roles;
		
	}
	
	
	@Override
	@Transactional
	public Boolean add(AjaxRequest<List<UserRoleVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}
		
		List<UserRoleVO> list = request.getData();
		
		for (UserRoleVO r : list) {
			
			SysUserRoleTEntity update = SysUserRoleTEntity.builder()
			.createBy(request.getUserName())
			.createTime(new Date())
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.roleId(r.getRoleId())
			.userId(r.getUserId())
			.userRoleId(KeyUtils.getUID())
			.build();
			
			int row = userRoleDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<UserRoleVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<UserRoleVO> list = request.getData();
		
		for (UserRoleVO r : list) {
			SysUserRoleTEntity update = SysUserRoleTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.delFlag(YesNoEnum.Yes.getCode())
			.build();
			
			SysUserRoleTExample example = new SysUserRoleTExample();
			example.createCriteria()
			.andUserRoleIdEqualTo(r.getUserRoleId());
			
			int row = userRoleDao.updateWithVersionByExampleSelective(r.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}
	
	@Override
	public List<UserRoleVO> find(PageRequest request) throws BusinessServiceException {
		SysUserRoleTExample TExample = new SysUserRoleTExample();
		SysUserRoleTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SysUserRoleTEntity.Column.class, SysUserRoleTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		List<SysUserRoleTEntity> list = userRoleDao.selectByExample(TExample);
		if(CollectionUtils.isEmpty(list)) 
			return null;
		
		Set<Long> ids = Sets.newHashSet();
		list.forEach(r -> {
			ids.add(r.getRoleId());
		});
		
		SysRoleTExample roleExample = new SysRoleTExample();
		roleExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andRoleIdIn(Lists.newArrayList(ids));
		
		List<SysRoleTEntity> roles = roleDao.selectByExample(roleExample);
		if (CollectionUtils.isEmpty(roles))
			return null;
		
		Map<Long, SysRoleTEntity> rolesMap = Maps.newHashMap();
		roles.forEach(r -> {
			rolesMap.put(r.getRoleId(), r);
		});
				
		
		List<UserRoleVO> returnList = Lists.newArrayList();
		list.forEach(r -> {
			UserRoleVO userRoleVo = new UserRoleVO(r);
			SysRoleTEntity role = rolesMap.get(r.getRoleId());
			if (role == null) 
				return;
			
			userRoleVo.setRoleCode(role.getCode());
			userRoleVo.setRoleDescr(role.getDescr());
			returnList.add(userRoleVo);
		});
		
		return returnList;
		
	}

	
}
