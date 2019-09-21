package com.wms.web.confg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wms.web.interceptor.ServicesInterceptor;
import com.wms.web.interceptor.WebInterceptor;

/**
 * 通用配置
 * 
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
	/**
	 * 登陆
	 */
	@Value("${shiro.user.loginUrl}")
	private String loginUrl;

	/**
	 * 首页地址
	 */
	@Value("${shiro.user.logoutUrl}")
	private String logoutUrl;

	/**
	 * 首页地址
	 */
	@Value("${shiro.user.indexUrl}")
	private String indexUrl;

	@Autowired
	private WebInterceptor localeInterceptor;

	@Autowired
	private ServicesInterceptor serviceInterceptor;

	/**
	 * 默认首页的设置，当输入域名是可以自动跳转到默认指定的网页
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("redirect:" + indexUrl);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/** 文件上传路径 */
		//registry.addResourceHandler("/upload/**").addResourceLocations("file:" + Global.uploadPath);
		/** 配置路径，对应静态资源所在的目录 */
		registry.addResourceHandler("/static/ui/**").addResourceLocations("classpath:/static/jquery-easyui-1.8.1/");
		registry.addResourceHandler("/static/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/static/images/**").addResourceLocations("classpath:/static/images/");
		registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns("/**") 表示拦截所有的请求，
		// excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
		registry.addInterceptor(serviceInterceptor).addPathPatterns("/services/inner/**").excludePathPatterns("/services/web/**","/**/restPassword");
		registry.addInterceptor(localeInterceptor).addPathPatterns("/web/**", "/mobile/**");// .excludePathPatterns(loginUrl, // logoutUrl);
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
}