package com.wms.services.sys.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.common.constants.TableNameConstants;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.PermissionTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.MessageUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.cache.LocaleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysPermissionTDao;
import com.wms.dao.example.SysPermissionTExample;
import com.wms.entity.auto.SysPermissionTEntity;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.services.sys.ISysPermissionService;
import com.wms.services.sys.ISysRolePermissionService;
import com.wms.services.sys.ISysUserRoleService;
import com.wms.vo.PermissionVO;

/**
 * 菜单 业务层
 * 
 */
@Service
public class SysPermissionServiceImpl implements ISysPermissionService {

	@Autowired
	private ISysRolePermissionService rolePermissionService;

	@Autowired
	private ISysUserRoleService userRoleService;

	@Autowired
	private ISysPermissionTDao permissionDao;

	@Override
	public List<SysPermissionTEntity> findUserPermission(SysUserTEntity user, PermissionTypeEnum permissionType) {
		return findUserPermission(user, permissionType, null);
	}
	
	@Override
	public List<SysPermissionTEntity> findUserPermission(SysUserTEntity user, PermissionTypeEnum permissionType, String locale) {
		List<SysRoleTEntity> userRoles = userRoleService.findUserRole(user);
		if (CollectionUtils.isEmpty(userRoles))
			return null;
		List<SysPermissionTEntity> perminssion = rolePermissionService.findRolePermission(userRoles, permissionType, locale);
		return perminssion;
	}

	@Override
	public List<SysPermissionTEntity> findUserPermission(SysUserTEntity user) {
		return findUserPermission(user, null);
	}
	
	@Override
	public List<SysPermissionTEntity> findPermission(PermissionTypeEnum permissionType, String locale) {
		SysPermissionTExample perminssionExample = new SysPermissionTExample();
		SysPermissionTExample.Criteria criterion = perminssionExample.createCriteria();
		criterion.andDelFlagEqualTo(YesNoEnum.No.getCode());

		if (StringUtils.isNotNull(permissionType)) {
			criterion.andPermissionTypeEqualTo(permissionType.getCode());
			criterion.andVisibleEqualTo(YesNoEnum.Yes.getCode());
		}
		perminssionExample.orderBy(StringUtils.join(SysPermissionTEntity.Column.parentId.getValue()," , ",SysPermissionTEntity.Column.sort.getValue()));
		List<SysPermissionTEntity> perminssions = permissionDao.selectByExample(perminssionExample);
		
		if (StringUtils.isEmpty(locale))
			return perminssions;
		
		perminssions.forEach(p -> {
			//根据国际化进行转换
			StringBuilder localeSb = new StringBuilder();
			localeSb.append(TableNameConstants.PERMISSION);
			localeSb.append(LocaleUtils.CONTACT);
			localeSb.append(p.getPerms());
			String descr = LocaleUtils.getLocaleLabel(locale, localeSb.toString());
			if (StringUtils.isNotEmpty(descr))
				p.setPermissionName(descr);
		}); 

		return perminssions;
	}

	@Override
	public List<SysPermissionTEntity> findPermission(PermissionTypeEnum permissionType) {
		return findPermission(permissionType, null);
	}
	
	public SysPermissionTEntity find(SysPermissionTEntity per) {
		SysPermissionTExample perminssionExample = new SysPermissionTExample();
		SysPermissionTExample.Criteria criterion = perminssionExample.createCriteria();
		criterion
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andPermissionIdEqualTo(per.getPermissionId());
		SysPermissionTEntity perminssions = permissionDao.selectOneByExample(perminssionExample);
		return perminssions;
	}

	@Override
	public List<PermissionVO> find(PageRequest request) throws BusinessServiceException {
		SysPermissionTExample example = new SysPermissionTExample();
		SysPermissionTExample.Criteria exampleCriteria = example.createCriteria();

		// 转换查询方法
		ExampleUtils.create(SysPermissionTEntity.Column.class, SysPermissionTExample.Criterion.class)
		.criteria(exampleCriteria)
		.data(request)
		.build(request);

		example.orderBy(StringUtils.join(SysPermissionTEntity.Column.parentId.getValue()," , ",SysPermissionTEntity.Column.sort.getValue()));
		

		exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		List<SysPermissionTEntity> list = permissionDao.selectByExample(example);
		if (CollectionUtils.isEmpty(list))
			return null;
		
		//获取最顶层菜单
		SysPermissionTEntity topParent = insertTop(list);
		
		Map<Long, List<SysPermissionTEntity>> treeMap = convertTreeMap(list);
		Map<Long, PermissionVO> perMap = Maps.newHashMap();
		List<PermissionVO> treeList = Lists.newArrayList();
		createTreeList(treeList, perMap, topParent.getParentId(), treeMap);
		
		
		Map<Long, SysPermissionTEntity> existsParent = Maps.newHashMap();
		treeList.forEach(p -> {
			if (StringUtils.isNotEmpty(p.getParentName()) ||  p.getParentId() == null ) 
				return;
			
			//获取父项菜单名
			SysPermissionTEntity per = existsParent.get(p.getParentId());
			if (per == null)
				per = find(SysPermissionTEntity.builder().permissionId(p.getParentId()).build());
			
			if (per != null) {
				p.setParentName(per.getPermissionName());
				existsParent.put(per.getPermissionId(), per);
			}
		});
		
		return treeList;
	}

	@Override
	@Transactional
	public Boolean modify(AjaxRequest<List<PermissionVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}

		List<PermissionVO> list = request.getData();

		for (PermissionVO p : list) {

			SysPermissionTEntity update = SysPermissionTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.parentId(p.getParentId())
					.permissionName(p.getPermissionName())
					.permissionType(p.getPermissionType())
					.perms(p.getPerms())
					.sort(p.getSort())
					.url(p.getUrl())
					.visible(p.getVisible())
					.warehouseFlag(p.getWarehouseFlag())
					.build();

			SysPermissionTExample example = new SysPermissionTExample();
			example.createCriteria()
			.andPermissionIdEqualTo(p.getPermissionId());
			
			int row = permissionDao.updateWithVersionByExampleSelective(p.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean add(AjaxRequest<List<PermissionVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}

		List<PermissionVO> list = request.getData();

		for (PermissionVO p : list) {

			SysPermissionTExample example = new SysPermissionTExample();
			example.createCriteria().andDelFlagEqualTo(YesNoEnum.No.getCode())
					.andPermissionNameEqualTo(p.getPermissionName()).andParentIdEqualTo(p.getParentId());
			Long count = permissionDao.countByExample(example);
			if (count > 0) {
				throw new BusinessServiceException("SysConfigServiceImpl", "permission.record.exists",
						new Object[] { p.getPermissionName() });
			}

			SysPermissionTEntity update = SysPermissionTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.createBy(request.getUserName())
					.createTime(new Date())
					.parentId(p.getParentId())
					.permissionName(p.getPermissionName())
					.permissionType(p.getPermissionType())
					.perms(p.getPerms())
					.sort(p.getSort())
					.url(p.getUrl())
					.visible(p.getVisible())
					.warehouseFlag(p.getWarehouseFlag())
					.permissionId(KeyUtils.getUID())
					.build();

			int row = permissionDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean delete(AjaxRequest<List<PermissionVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<PermissionVO> list = request.getData();
		
		for (PermissionVO p : list) {
			SysPermissionTEntity update = SysPermissionTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.delFlag(YesNoEnum.Yes.getCode())
					.build();
			
			SysPermissionTExample example = new SysPermissionTExample();
			example.createCriteria()
			.andPermissionIdEqualTo(p.getPermissionId());
			
			int row = permissionDao.updateWithVersionByExampleSelective(p.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public SysPermissionTEntity insertTop(List<SysPermissionTEntity> list) throws BusinessServiceException {
		SysPermissionTEntity topParent = Collections.max(list, new Comparator<SysPermissionTEntity>() {
			@Override
			public int compare(SysPermissionTEntity o1, SysPermissionTEntity o2) {
				return o1.getParentId() > o2.getParentId() ? 0 : 1;
			}
		});
		if (topParent.getParentId() != 0L)
			return topParent;
		// top
		SysPermissionTEntity top = new SysPermissionTEntity();
		top.setPermissionId(topParent.getParentId());
		top.setPermissionName(MessageUtils.message("system.menu", null));
		list.add(0, top);
		return top;
	}

	public static Map<Long, List<SysPermissionTEntity>> convertTreeMap(List<SysPermissionTEntity> childMenus) {
		Map<Long, List<SysPermissionTEntity>> treeMenus = new HashMap<Long, List<SysPermissionTEntity>>();
		Iterator<SysPermissionTEntity> menusIterator = childMenus.iterator();
		while(menusIterator.hasNext()) {
			SysPermissionTEntity per = menusIterator.next();
			if(!treeMenus.containsKey(per.getParentId())) {
				treeMenus.put(per.getParentId(), new ArrayList<SysPermissionTEntity>());
			}
			treeMenus.get(per.getParentId()).add(per);
		}
		return treeMenus;
	}
	
	public static void createTreeList(List<PermissionVO> treeList, Map<Long, PermissionVO> perMap, Long currentPerId, Map<Long, List<SysPermissionTEntity>> treeMap) {
		List<SysPermissionTEntity> childList = treeMap.get(currentPerId);
		if (CollectionUtils.isEmpty(childList))
			return;
		
		childList.forEach(p -> {
			PermissionVO perVo = new PermissionVO(p);
			PermissionVO parent = perMap.get(p.getParentId());
			if (parent != null) {
				perVo.setLevel(parent.getLevel() + 1);
				perVo.setParentName(parent.getPermissionName());
			}
			perMap.put(p.getPermissionId(), perVo);
			treeList.add(perVo);
			createTreeList(treeList, perMap, p.getPermissionId(), treeMap);
		});
	}

}
