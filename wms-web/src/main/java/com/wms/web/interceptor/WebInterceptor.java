package com.wms.web.interceptor;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.wms.common.utils.cache.LocaleUtils;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;
import com.wms.web.constants.AttrConstants;

@Component
public class WebInterceptor implements BaseInterceptor {
	
	@Value("${shiro.user.unauthorizedUrl}")
	private String unauthorizedUrl;
	
	@Autowired
	Environment env;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	String locale = getLocale(request);
    	LocaleUtils.setLocale(locale); //设置当前语言
    	Map<String, String> lableMap = LocaleUtils.getLocaleLabels(locale);
    	Set<String> lableKeys = lableMap.keySet();
    	for (String key : lableKeys) {
    		request.setAttribute(key, lableMap.get(key));
		}
    	UserVO user = ShiroUtils.getSysUser();
    	//设置用户默认语言
    	if (user != null && !locale.equals(user.getLocale())) {
    		user.setLocale(locale);
    		ShiroUtils.setSysUser(user);
    	}
    	request.setAttribute(AttrConstants.ATTR_CURR_LOCALE, locale);
    	return true;
    }
 
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {}
 
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {}

}
