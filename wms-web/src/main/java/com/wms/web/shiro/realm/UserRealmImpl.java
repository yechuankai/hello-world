package com.wms.web.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.collect.Lists;
import com.wms.common.exception.user.RoleBlockedException;
import com.wms.common.exception.user.UserBlockedException;
import com.wms.common.exception.user.UserNoPermissionException;
import com.wms.common.exception.user.UserNotExistsException;
import com.wms.common.exception.user.UserPasswordNotMatchException;
import com.wms.common.exception.user.UserPasswordRetryLimitExceedException;
import com.wms.entity.auto.SysPermissionTEntity;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.services.sys.ISysLoginService;
import com.wms.services.sys.ISysRolePermissionService;
import com.wms.services.sys.ISysUserRoleService;
import com.wms.shiro.realm.IUserRealm;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;

/**
 * 自定义Realm 处理登录 权限
 * 
 */
public class UserRealmImpl extends AuthorizingRealm implements IUserRealm {
	private static final Logger log = LoggerFactory.getLogger(UserRealmImpl.class);

	@Autowired
	private ISysRolePermissionService rolePermissionService;

	@Autowired
	private ISysUserRoleService userRoleService;

	@Autowired
	@Qualifier("SysWebLoginServiceImpl")
	private ISysLoginService loginService;

	/**
	 * 授权
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		UserVO user = ShiroUtils.getSysUser();
		// 角色列表
		Set<String> roleSets = new HashSet<String>();
		// 功能列表
		Set<String> permissionSets = new HashSet<String>();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 管理员拥有所有权限
		if (user.isAdmin()) {
			info.addRole("admin");
			info.addStringPermission("*:*:*");
		} else {
			//clean
			user.setFunctionPermission(Lists.newArrayList());
			user.setMenuPermission(Lists.newArrayList());
			List<SysRoleTEntity> roles = userRoleService.findUserRole(user);
			if (CollectionUtils.isEmpty(roles)) {
				throw new UserNoPermissionException(user.getUserName());
			}
			List<SysPermissionTEntity> permission = rolePermissionService.findRolePermission(roles);
			user.setPermission(permission);
			if (CollectionUtils.isEmpty(permission)) {
				throw new UserNoPermissionException(user.getUserName());
			}
			// 角色加入AuthorizationInfo认证对象
			for (SysRoleTEntity SysRoleTEntity : roles) {
				roleSets.add(SysRoleTEntity.getCode());
			}
			info.setRoles(roleSets);
			// 权限加入AuthorizationInfo认证对象
			for (SysPermissionTEntity p : permission) {
				permissionSets.add(p.getPerms());
			}
			info.setStringPermissions(permissionSets);
		}
		return info;
	}

	/**
	 * 登录认证
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		String password = "";
		if (upToken.getPassword() != null) {
			password = new String(upToken.getPassword());
		}

		SysUserTEntity user = null;
		try {
			user = loginService.login(username, password);
		} catch (UserNotExistsException e) {
			throw new UnknownAccountException(e.getMessage(), e);
		} catch (UserPasswordNotMatchException e) {
			throw new IncorrectCredentialsException(e.getMessage(), e);
		} catch (UserPasswordRetryLimitExceedException e) {
			throw new ExcessiveAttemptsException(e.getMessage(), e);
		} catch (UserBlockedException e) {
			throw new LockedAccountException(e.getMessage(), e);
		} catch (RoleBlockedException e) {
			throw new LockedAccountException(e.getMessage(), e);
		} catch (Exception e) {
			log.info("对用户[" + username + "]进行登录验证..验证未通过{}", e.getMessage());
			throw new AuthenticationException(e.getMessage(), e);
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

	/**
	 * 清理缓存权限
	 */
	public void clearCachedAuthorizationInfo() {
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}
}
