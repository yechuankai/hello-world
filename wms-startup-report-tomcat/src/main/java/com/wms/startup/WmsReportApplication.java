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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan("com.wms.report")
@ComponentScan("com.wms.services.sys")
@ComponentScan("com.wms.services.report")
@ComponentScan("com.wms.common")
@MapperScan("com.wms.dao")
@SpringBootApplication
@RefreshScope
@EnableWebMvc
public class WmsReportApplication extends SpringBootServletInitializer {
	private static final Logger log = LoggerFactory.getLogger(WmsReportApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WmsReportApplication.class, args);
		log.info("服务启动成功");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WmsReportApplication.class);
	}
}
