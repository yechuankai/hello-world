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
import com.wms.common.constants.TableNameConstants;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.enums.PermissionTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.cache.LocaleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysPermissionTDao;
import com.wms.dao.auto.ISysRolePermissionTDao;
import com.wms.dao.example.SysPermissionTExample;
import com.wms.dao.example.SysRolePermissionTExample;
import com.wms.entity.auto.SysCodelistTEntity;
import com.wms.entity.auto.SysPermissionTEntity;
import com.wms.entity.auto.SysRolePermissionTEntity;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.services.sys.ISysPermissionService;
import com.wms.services.sys.ISysRolePermissionService;
import com.wms.services.sys.ISysUserRoleService;
import com.wms.vo.PermissionTreeVO;


@Service
public class SysRolePermissionServiceImpl implements ISysRolePermissionService {

	private final static int MAX_SIZE = 100;
	
	@Autowired
	private ISysRolePermissionTDao rolePermissionDao;
	
	@Autowired
	private ISysUserRoleService userRoleService;

	@Autowired
	private ISysPermissionTDao permissionDao;
	
	@Autowired
	private ISysPermissionService permissionService;

	public List<SysPermissionTEntity> findRolePermission(List<SysRoleTEntity> roles) {
		return findRolePermission(roles, null);
	}

	public List<SysPermissionTEntity> findRolePermission(List<SysRoleTEntity> roles, PermissionTypeEnum permissionType) {

		if (CollectionUtils.isEmpty(roles)) 
			return Lists.newArrayList();
		
		Set<Long> ids = Sets.newHashSet();
		for (SysRoleTEntity userRole : roles) {
			ids.add(userRole.getRoleId());
		}

		SysRolePermissionTExample example = new SysRolePermissionTExample();
		example.createCriteria().andDelFlagEqualTo(YesNoEnum.No.getCode()).andRoleIdIn(Lists.newArrayList(ids));

		List<SysRolePermissionTEntity> rolePermission = rolePermissionDao.selectByExample(example);

		ids.clear();
		if (CollectionUtils.isNotEmpty(rolePermission)) {
			for (SysRolePermissionTEntity rolePer : rolePermission) {
				if (!ids.contains(rolePer.getPermissionId()))
					ids.add(rolePer.getPermissionId());
			}
		}

		if (CollectionUtils.isEmpty(ids)) {
			return Lists.newArrayList();
		}
		
		List<SysPermissionTEntity> perminssions = Lists.newArrayList();
		List<Set<Long>> multiIds = convertMultiArray(ids);
		
		multiIds.forEach(_ids -> {
			SysPermissionTExample perminssionExample = new SysPermissionTExample();
			SysPermissionTExample.Criteria criterion = perminssionExample.createCriteria();
			criterion
			.andDelFlagEqualTo(YesNoEnum.No.getCode())
			.andPermissionIdIn(Lists.newArrayList(_ids));

			if (StringUtils.isNotNull(permissionType)) {
				criterion.andPermissionTypeEqualTo(permissionType.getCode());
				criterion.andVisibleEqualTo(YesNoEnum.Yes.getCode());
			}
			perminssionExample.orderBy(StringUtils.join(SysPermissionTEntity.Column.parentId.getValue()," , ",SysPermissionTEntity.Column.sort.getValue()));
			

			List<SysPermissionTEntity> _perminssions = permissionDao.selectByExample(perminssionExample);
			if (CollectionUtils.isNotEmpty(_perminssions))
				perminssions.addAll(_perminssions);
		});

		return perminssions;

	}
	
	//按语言转换菜单
	@Override
	public List<SysPermissionTEntity> findRolePermission(List<SysRoleTEntity> roles, PermissionTypeEnum permissionType,
			String locale) {
		List<SysPermissionTEntity> permissionList = findRolePermission(roles, permissionType);
		if (StringUtils.isEmpty(locale))
			return permissionList;
		
		permissionList.forEach(p -> {
			//根据国际化进行转换
			StringBuilder localeSb = new StringBuilder();
			localeSb.append(TableNameConstants.PERMISSION);
			localeSb.append(LocaleUtils.CONTACT);
			localeSb.append(p.getPerms());
			String descr = LocaleUtils.getLocaleLabel(locale, localeSb.toString());
			if (StringUtils.isNotEmpty(descr))
				p.setPermissionName(descr);
		}); 
		return permissionList;
	}
	
	@Override
	public PermissionTreeVO findAll2Tree(List<SysRoleTEntity> roles) {
		List<SysPermissionTEntity> allList = permissionService.findPermission(null);
		SysPermissionTEntity top = permissionService.insertTop(allList);
		
		List<SysPermissionTEntity> roleList = null;
		
		if (CollectionUtils.isEmpty(roles)) {
			roleList = permissionService.findPermission(null);
		}else {
			roleList = findRolePermission(roles);
		}
		
		Map<Long, List<SysPermissionTEntity>> allTreeMap = SysPermissionServiceImpl.convertTreeMap(allList);
		Set<Long> userPerIds = Sets.newHashSet();
		if (CollectionUtils.isNotEmpty(roleList)) {
			for (SysPermissionTEntity per : roleList) {
				userPerIds.add(per.getPermissionId());
			}
		}
		PermissionTreeVO tree = new PermissionTreeVO(top);
		tree.createTree(allTreeMap, userPerIds);
		return tree;
	}

	@Override
	@Transactional
	public Boolean add(AjaxRequest<List<SysRolePermissionTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}
		
		List<SysRolePermissionTEntity> list = request.getData();
		Long roleId = list.get(0).getRoleId();
		List<SysPermissionTEntity> roleList = findRolePermission(
			Lists.newArrayList(
					new SysRoleTEntity.Builder().roleId(roleId).build()
			)
		);
		
		Map<Long, SysPermissionTEntity> rolePerMap = Maps.newHashMap();
		if (CollectionUtils.isNotEmpty(roleList))
			roleList.forEach(p -> {
				rolePerMap.put(p.getPermissionId(), p);
			});
		
		Map<Long, SysRolePermissionTEntity> updateMap = Maps.newHashMap();
		
		//计算新增
		List<SysRolePermissionTEntity> addList = Lists.newArrayList();
		for (SysRolePermissionTEntity per : list) {
			if (!rolePerMap.containsKey(per.getPermissionId())) {
				addList.add(per);
			}
			updateMap.put(per.getPermissionId(), per);
		}
		
		for (SysRolePermissionTEntity p : addList) {
			
			SysRolePermissionTEntity update = SysRolePermissionTEntity.builder()
			.createBy(request.getUserName())
			.createTime(new Date())
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.roleId(p.getRoleId())
			.permissionId(p.getPermissionId())
			.rolePermissionId(KeyUtils.getUID())
			.build();
			
			int row = rolePermissionDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		
		if (CollectionUtils.isEmpty(roleList))
			return Boolean.TRUE;
		
		//计算删除
		Set<Long> removeList = Sets.newHashSet();
		for (SysPermissionTEntity per : roleList) {
			if (!updateMap.containsKey(per.getPermissionId())) {
				removeList.add(per.getPermissionId());
			}
		}
		
		if (CollectionUtils.isEmpty(removeList))
			return Boolean.TRUE;
		
		
		SysRolePermissionTExample example = new SysRolePermissionTExample();
		example.createCriteria()
		.andPermissionIdIn(Lists.newArrayList(removeList))
		.andRoleIdEqualTo(roleId);
		
		SysRolePermissionTEntity update = SysRolePermissionTEntity.builder()
		.delFlag(YesNoEnum.Yes.getCode())
		.updateBy(request.getUserName())
		.updateTime(new Date()).build();
		
		int row = rolePermissionDao.updateByExampleSelective(update, example);
		if (row == 0) {
			throw new BusinessServiceException("record update error.");
		}
		
		return Boolean.TRUE;
	}

	@Override
	public PermissionTreeVO findAll2Tree(SysUserTEntity user) {
		List<SysRoleTEntity> roles = userRoleService.findUserRole(user);
		if (CollectionUtils.isEmpty(roles)) {
			return null;
		}
		return findAll2Tree(roles);
	}

	@Override
	public Boolean delete(AjaxRequest<SysRolePermissionTEntity> request) throws BusinessServiceException {
		List<SysPermissionTEntity> roleList = findRolePermission(
			Lists.newArrayList(
					new SysRoleTEntity.Builder().roleId(request.getData().getRoleId()).build()
			)
		);
		
		if (CollectionUtils.isEmpty(roleList))
			return Boolean.TRUE;
		
		Set<Long> removeList = Sets.newHashSet();
		roleList.forEach(p -> {
			removeList.add(p.getPermissionId());
		});
		
		SysRolePermissionTExample example = new SysRolePermissionTExample();
		example.createCriteria()
		.andPermissionIdIn(Lists.newArrayList(removeList))
		.andRoleIdEqualTo(request.getData().getRoleId());
		
		SysRolePermissionTEntity update = SysRolePermissionTEntity.builder()
		.delFlag(YesNoEnum.Yes.getCode())
		.updateBy(request.getUserName())
		.updateTime(new Date()).build();
		
		int row = rolePermissionDao.updateByExampleSelective(update, example);
		if (row == 0) {
			throw new BusinessServiceException("record update error.");
		}
		
		return Boolean.TRUE;
	}
	
	/**
	 * 转换多维数组， 使用 in 条件太多会造成数据库异常
	 * @return
	 */
	private List<Set<Long>> convertMultiArray(Set<Long> list){
		
		Set<Long> ids = Sets.newHashSet();
		List<Set<Long>> multiIds = new ArrayList<Set<Long>>();
		multiIds.add(ids);
		
		int count = 0;
		for (Long l : list) {
			count ++;
			ids.add(l);
			if (count == MAX_SIZE) {
				ids = Sets.newHashSet();
				count = 0;
				multiIds.add(ids);
			}
		}
		
		return multiIds;
	}


}
