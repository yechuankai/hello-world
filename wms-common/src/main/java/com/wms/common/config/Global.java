package com.wms.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 全局配置类
 * 
 */
@Configuration
public class Global {
	
	public static Boolean isAddressEnabled = false;
	public static String locale = "zh_CN";
	public static String dateFormater = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获取ip地址开关
	 */
	@Autowired(required = false)
	public void isAddressEnabled(@Value("${wms.addressEnabled}") String addressEnabled) {
		Global.isAddressEnabled = Boolean.valueOf(addressEnabled);
	}

	/**
	 * 默认语言
	 */
	@Autowired(required = false)
	public void getLocale(@Value("${wms.locale}") String locale) {
		Global.locale = locale;
	}
	
	/**
	 * 默认日期格式
	 */
	@Autowired(required = false)
	public void getDateFormater(@Value("${spring.fastjson.date-format}") String datafromat) {
		Global.dateFormater = datafromat;
	}
}
