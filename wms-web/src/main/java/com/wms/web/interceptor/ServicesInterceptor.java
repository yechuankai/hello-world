package com.wms.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.wms.common.core.interceptor.PublicInterceptor;
import com.wms.common.utils.cache.LocaleUtils;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;

@Component
public class ServicesInterceptor  extends PublicInterceptor implements BaseInterceptor {
	
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	String locale = getLocale(request);
    	LocaleUtils.setLocale(locale); //设置当前语言
    	
    	//验证当前用户是否有效
    	UserVO user = ShiroUtils.getSysUser();
    	if (user == null) {
    		fail(request, response, "user.not.login");
    		return false;
    	}
    	
    	return true;
    }

}
