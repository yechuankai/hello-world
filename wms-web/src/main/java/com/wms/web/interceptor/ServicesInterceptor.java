package com.wms.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.wms.common.utils.cache.LocaleUtils;

@Component
public class ServicesInterceptor  implements BaseInterceptor {
	
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	String locale = getLocale(request);
    	LocaleUtils.setLocale(locale); //设置当前语言
    	return true;
    }

}
