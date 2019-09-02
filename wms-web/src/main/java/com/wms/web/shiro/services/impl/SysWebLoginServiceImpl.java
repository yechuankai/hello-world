package com.wms.web.shiro.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wms.async.manager.AsyncManager;
import com.wms.common.enums.LoginStatusEnum;
import com.wms.common.enums.UserStatusEnum;
import com.wms.common.exception.user.UserBlockedException;
import com.wms.common.exception.user.UserNotExistsException;
import com.wms.common.utils.MessageUtils;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.services.sys.ISysUserService;
import com.wms.services.sys.impl.SysLoginServiceImpl;
import com.wms.services.sys.impl.SysPasswordServiceImpl;
import com.wms.vo.UserVO;
import com.wms.web.async.LoginAsync;

/**
 * 登录校验方法
 * 
 */
@Service("SysWebLoginServiceImpl")
public class SysWebLoginServiceImpl extends SysLoginServiceImpl {

	@Autowired
	private SysPasswordServiceImpl passwordService;

	@Autowired
	private ISysUserService userService;

	/**
	 * 登录
	 */
	public UserVO login(String username, String password) {
		// 用户名或密码为空 错误
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			AsyncManager.me().execute(LoginAsync.recordLogininfor(username, LoginStatusEnum.Fail.getCode(), MessageUtils.message("not.null")));
			throw new UserNotExistsException();
		}
		/*
		// 密码如果不在指定范围内 错误
		if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
				|| password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
			AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, LoginStatusEnum.Fail.getCode(), MessageUtils.message("user.password.not.match")));
			throw new UserPasswordNotMatchException();
		}

		// 用户名不在指定范围内 错误
		if (username.length() < UserConstants.USERNAME_MIN_LENGTH
				|| username.length() > UserConstants.USERNAME_MAX_LENGTH) {
			AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, LoginStatusEnum.Fail.getCode(), MessageUtils.message("user.password.not.match")));
			throw new UserPasswordNotMatchException();
		}
		*/

		username = username.toUpperCase();
		// 查询用户信息
		SysUserTEntity user = userService.findUserByLoginName(username);

		if (user == null) {
			AsyncManager.me().execute(LoginAsync.recordLogininfor(username, LoginStatusEnum.Fail.getCode(), MessageUtils.message("user.not.exists")));
			throw new UserNotExistsException();
		}

		if (UserStatusEnum.Disable.getCode().equals(user.getActive())) {
			 AsyncManager.me().execute(LoginAsync.recordLogininfor(username, LoginStatusEnum.Fail.getCode(), MessageUtils.message("user.blocked")));
			throw new UserBlockedException();
		}

		passwordService.validate(user, password);
		AsyncManager.me().execute(LoginAsync.recordLogininfor(username, LoginStatusEnum.Login.getCode(), MessageUtils.message("user.login.success")));
		recordLoginInfo(user);
		
		//获取更多用户信息
		UserVO userVo = userService.findUserById(user.getUserId());
		
		return userVo;
	}
}
