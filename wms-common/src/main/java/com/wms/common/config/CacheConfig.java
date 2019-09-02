package com.wms.common.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wms.common.utils.cache.MapCacheManager;

@Configuration
public class CacheConfig {

	//注册认证缓存对象，5分钟过期
	@Bean("authCacheMap")
	public CacheManager mapCache() {
		return new MapCacheManager(1000 * 60 * 5, 1000);
	}
}
