package com.wms.common.exception.user;

/**
 *  用户无权限
 * 
 */
public class UserNoPermissionException extends UserException {
	private static final long serialVersionUID = 1L;

	public UserNoPermissionException(String username) {
		super("user.no.permission", username);
	}
}
