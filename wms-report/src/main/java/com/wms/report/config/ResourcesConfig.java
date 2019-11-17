package com.wms.report.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通用配置
 * 
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/** 配置路径，对应静态资源所在的目录 */
		registry.addResourceHandler("/static/report/images/**").addResourceLocations("classpath:/report/images/");
	}

}