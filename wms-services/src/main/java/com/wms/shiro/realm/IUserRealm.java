package com.wms.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

public interface IUserRealm {

	public void clearCachedAuthorizationInfo();
	
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException;
	
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0);
}
