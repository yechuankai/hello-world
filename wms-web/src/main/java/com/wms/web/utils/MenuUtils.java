package com.wms.web.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.wms.common.enums.PermissionTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.cache.LocaleUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.entity.auto.SysPermissionTEntity;
import com.wms.services.sys.ISysPermissionService;
import com.wms.services.sys.impl.SysPermissionServiceImpl;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;


public class MenuUtils {
	
	private static final String TOP_MENU = "<a href=\"javascript:void(0);\" class=\"easyui-menubutton\" data-url=\"%s\" data-options=\"menu:'#%s'\">%s</a>";
	
	private static final String CHILD_MENU = "<div id=\"%s\" data-url=\"%s\">%s</div>";
	
	private static final String CHILDS_MENU = "<div id=\"%s\"><span>%s</span><div>%s</div></div>";
	
	public static String loadUserMenu() {
		UserVO user = ShiroUtils.getSysUser();
		if (StringUtils.isNull(user)) {
			return "";
		}
		
		if (user.getWarehouseId() == -1L) {
			return "";
		}
		
		//获取用户菜单
		ISysPermissionService permissionService = SpringUtils.getBean(ISysPermissionService.class);
		List<SysPermissionTEntity> menus = user.getMenuPermission();
		if (CollectionUtils.isEmpty(menus)) {
			if (ShiroUtils.isAdmin()) {
				menus = permissionService.findPermission(PermissionTypeEnum.Menu, LocaleUtils.getLocale());
			}else {
				menus = permissionService.findUserPermission(user, PermissionTypeEnum.Menu, LocaleUtils.getLocale());
			}
		}
		if (CollectionUtils.isEmpty(menus)) {
			return "";
		}
		Iterator<SysPermissionTEntity> menusIterator = menus.iterator();
		//获取顶级菜单
		List<SysPermissionTEntity> topMenus = new ArrayList<SysPermissionTEntity>();
		while(menusIterator.hasNext()) {
			SysPermissionTEntity per = menusIterator.next();
			//仓库级菜单没有选择仓库时排除
			if (user.getWarehouseId() == 0 && YesNoEnum.Yes.getCode().equals(per.getWarehouseFlag())) {
				menusIterator.remove();
				continue;
			}
			if (per.getParentId() == null || per.getParentId()  == 0) {
				topMenus.add(per);
				menusIterator.remove();
				continue;
			}
		}
		
		StringBuilder allMenuHtml = new StringBuilder();
		StringBuilder topMenuHtml = new StringBuilder();
		
		for (SysPermissionTEntity per : topMenus) {
			String topMenuButton = String.format(TOP_MENU, per.getUrl(), per.getPermissionId(), per.getPermissionName());
			topMenuHtml.append(topMenuButton);
		}
		allMenuHtml.append(topMenuHtml);
		
		Map<Long, List<SysPermissionTEntity>> treeMenus = SysPermissionServiceImpl.convertTreeMap(menus);
		
		StringBuilder childMenuHtml = new StringBuilder();
		for (SysPermissionTEntity per : topMenus) {
			String childMenu = processChildMenu(treeMenus, per);
			String menuHtml = String.format(CHILD_MENU, per.getPermissionId(), per.getUrl(), childMenu);
			childMenuHtml.append(menuHtml);
		}
		allMenuHtml.append(childMenuHtml);

		return allMenuHtml.toString();
	}
	
	public static String processChildMenu(Map<Long, List<SysPermissionTEntity>> treeMap, SysPermissionTEntity curr) {
		StringBuilder childMenuHtml = new StringBuilder();
		//获取当前菜单下层
		List<SysPermissionTEntity> childMenus = treeMap.get(curr.getPermissionId());
		if (CollectionUtils.isEmpty(childMenus)) {
			return "";
		}
		for (SysPermissionTEntity per : childMenus) {
			String menuHtml = null;
			//有下层
			if (treeMap.containsKey(per.getPermissionId())) {
				String menus = processChildMenu(treeMap, per);
				menuHtml = String.format(CHILDS_MENU, per.getPermissionId(), per.getPermissionName(), menus);
			}else {
				menuHtml = String.format(CHILD_MENU, per.getPermissionId(), per.getUrl(), per.getPermissionName());
			}
			childMenuHtml.append(menuHtml);
		}
		return childMenuHtml.toString();
	}
	
	
	
}
