package com.wms.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.common.config.Global;
import com.wms.common.enums.LoginTypeEnum;
import com.wms.common.enums.PermissionTypeEnum;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.SysPermissionTEntity;
import com.wms.entity.auto.SysUserDefaultTEntity;
import com.wms.entity.auto.SysUserTEntity;

public class UserVO extends SysUserTEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<SysPermissionTEntity> menuPermission;

	private List<SysPermissionTEntity> functionPermission;
	
	private List<WarehouseVO> warehouses;
	
	private List<WarehouseVO> allWarehouses;
	
	private Map<Long, WarehouseVO> warehouseMap;

	private Long currCompanyId = 0L;
	
	private Long currWarehouseId = 0L;

	private String locale;
	
	private String loginType = LoginTypeEnum.Normal.getCode();

	private String token;
	
	public UserVO() {
		menuPermission = Lists.newArrayList();
		functionPermission = Lists.newArrayList();
		warehouses = Lists.newArrayList();
		allWarehouses = Lists.newArrayList();
		warehouseMap = Maps.newHashMap();
		currWarehouseId = 0L;
		currCompanyId = 0L;
		locale = Global.locale;
	}
	
	public UserVO(SysUserTEntity user) {
		BeanUtils.copyBeanProp(this, user, Boolean.TRUE);
	}

	public void setUserDefault(SysUserDefaultTEntity userDefault) {
		if (StringUtils.isNotEmpty(userDefault.getWarehouseCode())) {
			for (WarehouseVO w : warehouses) {
				if (userDefault.getWarehouseCode().equals(w.getWarehouseCode())
						&& w.getCompanyId() == this.getCompanyId()) {
					this.currWarehouseId = w.getWarehouseId();
					this.currCompanyId = w.getCompanyId();
					break;
				}
			}
		}

		if (StringUtils.isNotEmpty(userDefault.getLocaleCode())) {
			locale = userDefault.getLocaleCode();
		} 
	}

	public Long getWarehouseId() {
		return currWarehouseId;
	}
	
	public String getWarehouseDescr() {
		WarehouseVO wh = this.warehouseMap.get(this.currWarehouseId);
		if (wh != null) 
			return wh.getWarehouseDescr();
		return null;
	}
	public void setWarehouseDescr(String warehouseDescr){}
	
	public void setWarehouseId(Long warehouseId) {
		this.currWarehouseId = warehouseId;
	}
	
	public Long getCompanyId() {
		return currCompanyId;
	}
	
	public void setCompanyId(Long companyId) {
		this.currCompanyId = companyId;
	}

	public String getLocale() {
		return locale;
	}
	
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Boolean isAdmin() {
		return getUserId() == 0L;
	}

	public List<SysPermissionTEntity> getMenuPermission() {
		return menuPermission;
	}

	public List<SysPermissionTEntity> getFunctionPermission() {
		return functionPermission;
	}

	public void setPermission(List<SysPermissionTEntity> permission) {
		if (CollectionUtils.isEmpty(functionPermission)) {
			return;
		}
		
		for (SysPermissionTEntity per : functionPermission) {
			//menu
			if (PermissionTypeEnum.Menu.getCode().equals(per.getPermissionType())) {
				this.menuPermission.add(per);
			}
			//function
			if (PermissionTypeEnum.Function.getCode().equals(per.getPermissionType())) { 
				this.functionPermission.add(per);
			}
		}
	}
	
	public void setWarehouses(List<WarehouseVO> warehouses) {
		this.warehouses = warehouses;
	}
	
	public List<WarehouseVO> getWarehouses() {
		return warehouses;
	}
	
	public void setMenuPermission(List<SysPermissionTEntity> menuPermission) {
		this.menuPermission = menuPermission;
	}

	public void setFunctionPermission(List<SysPermissionTEntity> functionPermission) {
		this.functionPermission = functionPermission;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public List<WarehouseVO> getAllWarehouses() {
		return allWarehouses;
	}

	public void setAllWarehouses(List<WarehouseVO> allWarehouses) {
		this.allWarehouses = allWarehouses;
		for (WarehouseVO wh : allWarehouses) {
			this.warehouseMap.put(wh.getWarehouseId(), wh);
		}
	}

	public Map<Long, WarehouseVO> getWarehouseMap() {
		return warehouseMap;
	}

	public void setWarehouseMap(Map<Long, WarehouseVO> warehouseMap) {
		this.warehouseMap = warehouseMap;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	
	
}
