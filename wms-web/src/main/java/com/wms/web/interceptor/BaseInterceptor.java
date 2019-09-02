package com.wms.web.interceptor;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.HandlerInterceptor;

import com.wms.common.config.Global;
import com.wms.common.core.domain.CodelkupVO;
import com.wms.common.utils.ServletUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.cache.CodelkUpUtils;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;
import com.wms.web.constants.AttrConstants;
import com.wms.web.constants.CodelkupConstants;

public interface BaseInterceptor extends HandlerInterceptor{

	default String checkLocale(String locale) {
    	//验证语言是否在系统中配置
		//获取字典
		List<CodelkupVO> codelkups = CodelkUpUtils.getCodelkup(CodelkupConstants.LOCALE);
		for (CodelkupVO cl : codelkups) {
			if (cl.getCode().equalsIgnoreCase(locale)) {
				return cl.getCode();
			}
		}
		return null;
    }
    
	default String getLocale(HttpServletRequest request) {
    	//获取传入参数语言
    	String locale = request.getParameter(AttrConstants.ATTR_REQUEST_LOCALE);
    	if (StringUtils.isNotEmpty(locale)) {
    		locale = checkLocale(locale);
    		if (StringUtils.isNotEmpty(locale)) {
        		return locale;
        	}
    	}
    	//获取cookie语言
		Cookie c = ServletUtils.getCookie(request, AttrConstants.ATTR_WMS_LOCALE);
		if (c != null) {
			locale = checkLocale(c.getValue());
    		if (StringUtils.isNotEmpty(locale)) {
        		return locale;
        	}
		}
		UserVO user = ShiroUtils.getSysUser();
		if (user != null) {
			return user.getLocale();
		}
		//获取浏览器默认语言
		Locale browserLocale = ServletUtils.getLanguage(request);
		if (locale != null) {
			locale = browserLocale.getLanguage();
			locale = checkLocale(c.getValue());
			if (StringUtils.isNotEmpty(locale)) {
	    		return locale;
	    	}
		}
		return Global.locale;
    }
}
