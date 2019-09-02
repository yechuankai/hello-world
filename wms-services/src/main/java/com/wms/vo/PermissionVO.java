package com.wms.vo;

import java.util.List;

import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.SysPermissionTEntity;

public class PermissionVO extends SysPermissionTEntity  {

	private int level = 0;
	private String show;
	private String parentName;

	private List<SysPermissionTEntity> mobileSubFunc;
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public PermissionVO() {}
	
	public PermissionVO(SysPermissionTEntity per) {
		BeanUtils.copyBeanProp(this, per, Boolean.TRUE);
	}

	/**
	 * @return the mobileFuntion
	 */
	public List<SysPermissionTEntity> getMobileSubFunc() {
		return mobileSubFunc;
	}

	/**
	 * @param mobileSubFunc the mobileSubFunc to set
	 */
	public void setMobileSubFunc(List<SysPermissionTEntity> mobileSubFunc) {
		this.mobileSubFunc = mobileSubFunc;
	}
	

}
