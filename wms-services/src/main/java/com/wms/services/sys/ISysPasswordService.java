package com.wms.services.sys;

import com.wms.entity.auto.SysUserTEntity;

public interface ISysPasswordService {

	public String encryptPassword(String loginname, String password, String salt) ;
	
	public void validate(SysUserTEntity user, String password);
	
}
