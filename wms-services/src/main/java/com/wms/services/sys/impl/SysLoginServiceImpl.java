package com.wms.services.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wms.common.enums.UserStatusEnum;
import com.wms.common.exception.user.UserBlockedException;
import com.wms.common.exception.user.UserNotExistsException;
import com.wms.common.utils.DateUtils;
import com.wms.dao.auto.ISysUserTDao;
import com.wms.dao.example.SysUserTExample;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.services.sys.ISysLoginService;
import com.wms.services.sys.ISysUserService;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;

/**
 * 登录校验方法
 * 
 */
@Service
public class SysLoginServiceImpl implements ISysLoginService {

	@Autowired
	private SysPasswordServiceImpl passwordService;

	@Autowired
	private ISysUserService userService;

	@Autowired
	private ISysUserTDao userDao;

	/**
	 * 登录
	 */
	public UserVO login(String username, String password) {
		// 用户名或密码为空 错误
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new UserNotExistsException();
		}

		username = username.toUpperCase();
		// 查询用户信息
		SysUserTEntity user = userService.findUserByLoginName(username);

		if (user == null) {
			throw new UserNotExistsException();
		}

		if (UserStatusEnum.Disable.getCode().equals(user.getActive())) {
			throw new UserBlockedException();
		}

		passwordService.validate(user, password);
		recordLoginInfo(user);
		
		//获取更多用户信息
		UserVO userVo = userService.findUserById(user.getUserId());
		
		return userVo;
	}

	/**
	 * 记录登录信息
	 */
	protected void recordLoginInfo(SysUserTEntity user) {
		SysUserTEntity updateUser = new SysUserTEntity();
		updateUser.setLoginIp(ShiroUtils.getIp());
		updateUser.setLoginDate(DateUtils.getNowDate());
		updateUser.setUpdateBy(user.getUserName());
		updateUser.setUpdateTime(DateUtils.getNowDate());
		
		SysUserTExample example = new SysUserTExample();
		example.createCriteria()
		.andUserIdEqualTo(user.getUserId());
		
		userDao.updateWithVersionByExampleSelective(user.getUpdateVersion(), updateUser, example);
	}
}
