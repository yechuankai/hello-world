package com.wms.shiro.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.wms.common.constants.UserConstants;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.shiro.realm.IUserRealm;
import com.wms.vo.UserVO;

/**
 * shiro 工具类
 * 
 */
public class ShiroUtils {
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static void logout() {
		getSubject().logout();
	}

	public static UserVO getSysUser() {
		UserVO user = null;
		Object obj = getSubject().getPrincipal();
		if (StringUtils.isNotNull(obj)) {
			if(obj instanceof UserVO) {
				user = new UserVO();
				BeanUtils.copyBeanProp(user, obj, Boolean.FALSE);
			}else if(obj instanceof String) {
				user = (UserVO)SecurityUtils.getSubject().getSession().getAttribute(UserConstants.SYS_USER);
				if (!obj.equals(user.getLoginName()))
					return null;
			}else {
				return null;
			}
		}
		return user;
	}

	public static void setSysUser(UserVO user) {
		Subject subject = getSubject();
		PrincipalCollection principalCollection = subject.getPrincipals();
		String realmName = principalCollection.getRealmNames().iterator().next();
		PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
		// 重新加载Principal
		subject.runAs(newPrincipalCollection);
	}

	public static void clearCachedAuthorizationInfo() {
		RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		IUserRealm realm = (IUserRealm) rsm.getRealms().iterator().next();
		realm.clearCachedAuthorizationInfo();
	}

	public static Long getUserId() {
		return getSysUser().getUserId().longValue();
	}

	public static String getLoginName() {
		return getSysUser().getLoginName();
	}

	public static String getIp() {
		return getSubject().getSession().getHost();
	}

	public static String getSessionId() {
		return String.valueOf(getSubject().getSession().getId());
	}
	
	public static Boolean isAdmin() {
		return getSysUser().isAdmin();
	}

	/**
	 * 生成随机盐
	 */
	public static String randomSalt() {
		// 一个Byte占两个字节，此处生成的3字节，字符串长度为6
		SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
		String hex = secureRandom.nextBytes(3).toHex();
		return hex;
	}
}
