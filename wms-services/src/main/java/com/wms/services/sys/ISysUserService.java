package com.wms.services.sys;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.vo.UserVO;

/**
 * 用户 业务层
 * 
 */
public interface ISysUserService {

	/**
	 * 通过用户名查询用户
	 * 
	 * @param userName 用户名
	 * @return 用户对象信息
	 */
	public SysUserTEntity findUserByLoginName(String userName);
	
	public UserVO findUserById(Long userid);
	
	public Boolean restPassword(SysUserTEntity user, String newPassword);
	
	public List<SysUserTEntity> find(PageRequest request) throws BusinessServiceException;
	
	public Boolean modify(AjaxRequest<List<SysUserTEntity>> request) throws BusinessServiceException;
	
	public Boolean add(AjaxRequest<List<SysUserTEntity>> request) throws BusinessServiceException;
	
	public Boolean delete(AjaxRequest<List<SysUserTEntity>> request) throws BusinessServiceException;
	
}
