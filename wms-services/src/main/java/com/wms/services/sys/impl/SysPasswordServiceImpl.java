package com.wms.services.sys.impl;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wms.common.exception.user.UserPasswordNotMatchException;
import com.wms.common.exception.user.UserPasswordRetryLimitExceedException;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.services.sys.ISysPasswordService;

/**
 * 登录密码方法
 * 
 */
@Service
public class SysPasswordServiceImpl implements ISysPasswordService{
	
	private Cache<String, AtomicInteger> loginRecordCache;

	@Value("${user.password.maxRetryCount:10}")
	private String maxRetryCount;

	@PostConstruct
	public void init() {
		try {
			CacheManager cacheManager = SpringUtils.getBean(CacheManager.class);
			if (cacheManager != null)
				loginRecordCache = cacheManager.getCache("loginRecordCache");
		} catch (Exception e) {
		}
	}

	public void validate(SysUserTEntity user, String password) {
		String loginName = user.getLoginName();

		AtomicInteger retryCount = loginRecordCache.get(loginName);

		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			loginRecordCache.put(loginName, retryCount);
		}
		if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue()) {
			throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
		}

		if (!matches(user, password)) {
			loginRecordCache.put(loginName, retryCount);
			throw new UserPasswordNotMatchException();
		} else {
			clearLoginRecordCache(loginName);
		}
	}

	private boolean matches(SysUserTEntity user, String newPassword) {
		return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
	}

	private void clearLoginRecordCache(String username) {
		loginRecordCache.remove(username);
	}

	public String encryptPassword(String loginname, String password, String salt) {
		return new Md5Hash(loginname + password + salt).toHex().toString();
	}

}
