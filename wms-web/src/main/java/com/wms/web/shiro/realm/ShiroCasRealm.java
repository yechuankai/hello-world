package com.wms.web.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.Lists;
import com.wms.common.constants.UserConstants;
import com.wms.common.enums.LoginTypeEnum;
import com.wms.common.exception.user.UserNoPermissionException;
import com.wms.common.utils.ServletUtils;
import com.wms.common.utils.StringUtils;
import com.wms.entity.auto.SysPermissionTEntity;
import com.wms.entity.auto.SysRoleTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.services.sys.ISysRolePermissionService;
import com.wms.services.sys.ISysUserRoleService;
import com.wms.services.sys.ISysUserService;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;
import com.wms.web.constants.AttrConstants;

public class ShiroCasRealm extends CasRealm{
	private static final Logger log = LoggerFactory.getLogger(ShiroCasRealm.class);

	@Autowired
	private ISysRolePermissionService rolePermissionService;

	@Autowired
	private ISysUserRoleService userRoleService;
	
	@Autowired
	private ISysUserService userService;
	
	
    @Value("${cas.server}")
    public String casServerUrlPrefix;
    @Value("${wms.url.service}")
    public String shiroServerUrlPrefix;
    @Value("${cas.casFilterUrl}")
    public String casFilterUrl;
    
    @SuppressWarnings("deprecation")
	@PostConstruct
    public void initProperty(){
        // cas server地址
        setCasServerUrlPrefix(casServerUrlPrefix);
        // 客户端回调地址
        setCasService(shiroServerUrlPrefix + casFilterUrl);
    }

    /**
	 * 授权
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		UserVO user = ShiroUtils.getSysUser();
		ShiroUtils.setSysUser(user);
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
		AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
		if(authc == null) {
			return null;
		}
		String loginName = (String) authc.getPrincipals().getPrimaryPrincipal();
		
		//wms系统数据库用户名统一转大写
		SysUserTEntity findUserByLoginName = userService.findUserByLoginName(loginName);
		if(findUserByLoginName == null) {
			return null;
		}
		UserVO user = userService.findUserById(findUserByLoginName.getUserId());
		//将用户信息存入session中
        SecurityUtils.getSubject().getSession().setAttribute(UserConstants.SYS_USER, user);
        //获取SWP国际化语言
		String languageNo = ServletUtils.getRequest().getParameter(AttrConstants.ATTR_REQUEST_LOCALE);
		if(StringUtils.isNotBlank(languageNo)) {
			user.setLocale(languageNo);
		}
		//sso登录标示
		user.setLoginType(LoginTypeEnum.SSO.getCode());
        return authc;
	}

	/**
	 * 清理缓存权限
	 */
	public void clearCachedAuthorizationInfo() {
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}

}
