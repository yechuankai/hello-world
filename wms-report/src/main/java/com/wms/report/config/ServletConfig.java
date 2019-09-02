package com.wms.report.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wms.report.config.servlet.Barcode4jServlet;


@Configuration
public class ServletConfig {

	
	@Bean
    public ServletRegistrationBean barbecue() {
		//http://127.0.0.1:8083/wms-report/barcode?type=code128&data=123
        return new ServletRegistrationBean(new net.sourceforge.barbecue.BarcodeServlet(), "/barbecue/*");
    }
	
	@Bean
    public ServletRegistrationBean barcode() {
		//http://127.0.0.1:8083/wms-report/barcode?type=code128&msg=123
        return new ServletRegistrationBean(new Barcode4jServlet(), "/barcode/*");
    }

}
