package com.wms.common.core.services;

/**
 * 服务认证逻辑
 * @author yechuankai.chnet
 *
 */
public interface IAuthService {

	Boolean validateToken(String token);
	
	String autoType();
	
}
