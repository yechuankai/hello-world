package com.wms.startup;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableTransactionManagement
@MapperScan("com.wms.dao")
@ComponentScan("com.wms.file")
@ComponentScan("com.wms.services")
@ComponentScan("com.wms.common")
@ComponentScan("com.wms.async")
@ComponentScan("com.wms.aspect")
@SpringBootApplication
@RefreshScope
@EnableWebMvc
public class FileApplication extends SpringBootServletInitializer {
	private static final Logger log = LoggerFactory.getLogger(FileApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FileApplication.class, args);
		log.info("服务启动成功");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FileApplication.class);
	}
}
