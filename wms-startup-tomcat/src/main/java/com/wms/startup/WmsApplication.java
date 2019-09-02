package com.wms.startup;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableTransactionManagement
@EnableCaching
@MapperScan("com.wms.dao")
@ComponentScan("com.wms")
@SpringBootApplication
@RefreshScope
@EnableWebMvc
@EnableScheduling
public class WmsApplication extends SpringBootServletInitializer {
	private static final Logger log = LoggerFactory.getLogger(WmsApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WmsApplication.class);
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(WmsApplication.class, args);
		log.info("服务启动成功");
	}

	
}
