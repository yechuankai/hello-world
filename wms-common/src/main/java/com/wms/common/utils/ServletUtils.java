package com.wms.common.utils;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wms.common.text.Convert;

/**
 * 客户端工具类
 * 
 */
public class ServletUtils {
	
	// \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔), 
	// 字符串在编译时会被转码一次,所以是 "\\b" 
	// \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔) 
	private final static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i" 
											+"|windows (phone|ce)|blackberry" 
											+"|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp" 
											+"|laystation portable)|nokia|fennec|htc[-_]" 
											+"|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b"; 
	
	private final static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser" 
											+"|[1-4][0-9]{2}x[1-4][0-9]{2})\\b"; 

	//移动设备正则匹配：手机端、平板 
	private static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE); 
	private static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE); 
	
	/**
	 * 获取String参数
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	/**
	 * 获取String参数
	 */
	public static String getParameter(String name, String defaultValue) {
		return Convert.toStr(getRequest().getParameter(name), defaultValue);
	}

	/**
	 * 获取Integer参数
	 */
	public static Integer getParameterToInt(String name) {
		return Convert.toInt(getRequest().getParameter(name));
	}

	/**
	 * 获取Integer参数
	 */
	public static Integer getParameterToInt(String name, Integer defaultValue) {
		return Convert.toInt(getRequest().getParameter(name), defaultValue);
	}

	/**
	 * 获取request
	 */
	public static HttpServletRequest getRequest() {
		return getRequestAttributes().getRequest();
	}

	/**
	 * 获取response
	 */
	public static HttpServletResponse getResponse() {
		return getRequestAttributes().getResponse();
	}

	/**
	 * 获取session
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static ServletRequestAttributes getRequestAttributes() {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		return (ServletRequestAttributes) attributes;
	}

	/**
	 * 将字符串渲染到客户端
	 * 
	 * @param response 渲染对象
	 * @param string   待渲染的字符串
	 * @return null
	 */
	public static String renderString(HttpServletResponse response, String string) {
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Cookie getCookie(HttpServletRequest request, String string) {
		Cookie [] cookie = request.getCookies();
		if (cookie == null)
			return null;
		
		for (Cookie c : cookie) {
			if (c.getName().equals(string)) {
				return c;
			}
		}
		return null;
	}
	
	public static Locale getLanguage(HttpServletRequest request) {
		return request.getLocale();
	}

	/**
	 * 是否是Ajax异步请求
	 * 
	 * @param request
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {

		String accept = request.getHeader("accept");
		if (accept != null && accept.indexOf("application/json") != -1) {
			return true;
		}

		String xRequestedWith = request.getHeader("X-Requested-With");
		if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
			return true;
		}

		String uri = request.getRequestURI();
		if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml")) {
			return true;
		}

		String ajax = request.getParameter("__ajax");
		if (StringUtils.inStringIgnoreCase(ajax, "json", "xml")) {
			return true;
		}

		return false;
	}
	
	/**
	 * 是否是移动端请求
	 * 
	 * @param request
	 */
	public static boolean isMobile() {
		HttpServletRequest request = ServletUtils.getRequest();
		String agent = request.getHeader("User-Agent").toLowerCase();
		// 匹配 
		Matcher matcherPhone = phonePat.matcher(agent); 
		Matcher matcherTable = tablePat.matcher(agent); 
		if(matcherPhone.find() || matcherTable.find()) 
			return true; 
		 else 
			return false; 
		
	}
}
