package com.wms.services.sys;

import java.util.Date;
import java.util.List;

import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysUserOnlineTEntity;
import com.wms.entity.auto.ZoneTEntity;

public interface ISysUserOnlineService {
	
	List<SysUserOnlineTEntity> find(PageRequest request) throws BusinessServiceException;

	Boolean save(SysUserOnlineTEntity useronline);
	
	SysUserOnlineTEntity findOnlineById(String sessionid);
	
	SysUserOnlineTEntity find(String sessionid);
	
	List<SysUserOnlineTEntity> findOnline(SysUserOnlineTEntity userOnline);
	
	List<SysUserOnlineTEntity> findOnlineByExpired(Date expiredDate);
	
	Boolean offline(List<SysUserOnlineTEntity> userOnlines);
	
}
