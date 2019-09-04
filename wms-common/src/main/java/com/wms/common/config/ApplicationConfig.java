package com.wms.common.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import se.jiderhamn.classloader.leak.prevention.ClassLoaderLeakPreventorListener;

@Component
@Configuration
public class ApplicationConfig  implements ServletContextInitializer {

	@Value("${spring.application.name}")
	private String applicationName;
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("webAppRootKey", applicationName);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new ClassLoaderLeakPreventorListener());
        return srb;
    }

}
