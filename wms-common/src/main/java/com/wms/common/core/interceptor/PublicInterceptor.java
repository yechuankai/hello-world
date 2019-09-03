package com.wms.common.core.interceptor;

import com.google.common.collect.Maps;
import com.wms.common.config.Global;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.core.services.IAuthService;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.cache.LocaleUtils;
import com.wms.common.utils.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 拦截对外服务
 * @author yechuankai.chnet
 *
 */
@Component
public class PublicInterceptor implements HandlerInterceptor,InitializingBean  {
	
	private Map<String, IAuthService> authServiceMap = Maps.newHashMap();
	
	@Value("${spring.http.encoding.charset}")
	private String charset;
	private static final String LOCALE = "locale";
	private static final String AUTH_TOKEN = "authtoken";
	private static final String AUTH_TYPE = "authtype";
	

	private static final Logger log = LoggerFactory.getLogger(PublicInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	//校验token
    	String authToken = request.getHeader(AUTH_TOKEN);
    	String authType = request.getHeader(AUTH_TYPE);
    	IAuthService authService = authServiceMap.get(authType);
    	/*if (authService == null) {
    		log.error("auth {} type {} error, not find service.", authToken, authType);
    		fail(request, response, "auth type error.");
    		return false;
    	}
    	
    	boolean authFlag = authService.validateToken(authToken);
    	if (!authFlag) {
    		log.error("auth {} type {} fail.", authToken, authType);
    		fail(request, response, "auth token fail.");
    		return false;
    	}*/
    	
    	//设置国际化
    	String locale = request.getHeader(LOCALE);
    	if (StringUtils.isEmpty(locale)) {
    		locale = Global.locale;  
    	}
    	LocaleUtils.setLocale(locale); //设置当前语言
    	return true;
    }
    
    protected void fail(HttpServletRequest request, HttpServletResponse response, String message) throws UnsupportedEncodingException, IOException {
    	response.setHeader("Content-type", "application/json;charset=UTF-8");
		response.setCharacterEncoding(charset);
		String responseContent = null;
		if (request.getRequestURI().indexOf("/find") > -1)
			responseContent = PageResult.createFail(message).toString();
		else
			responseContent = AjaxResult.nologin().toString();
		response.getOutputStream().write(responseContent.getBytes(charset));
    }
 
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {}
 
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {}

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, IAuthService> strategyServices = SpringUtils.getBeansOfType(IAuthService.class);
		strategyServices.forEach((k, v) -> {
			authServiceMap.put(v.autoType(), v);
		});
	}

}
