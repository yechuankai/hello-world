package com.wms.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.jiderhamn.classloader.leak.prevention.ClassLoaderLeakPreventorListener;

@EnableConfigServer
@SpringBootApplication
@Configuration
public class ConfigServerApplication  extends SpringBootServletInitializer{
	private static final Logger log = LoggerFactory.getLogger(ConfigServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
		log.info("配置中心启动成功.");
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ConfigServerApplication.class);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new ClassLoaderLeakPreventorListener());
        return srb;
    }
}
