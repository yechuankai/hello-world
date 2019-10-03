package com.wms.common.core.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.MapDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.AjaxResult.Type;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.utils.DateUtils;
import com.wms.common.utils.ServletUtils;
import com.wms.common.utils.StringUtils;

/**
 * web层通用数据处理
 * 
 */ 
public class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

	protected static final String PRO_Q = "q";
	
	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});
	}
	
	protected void convertWordQ(PageRequest pageRequest, String proName) {
		//下拉搜索框
		if (pageRequest.containsKey(PRO_Q)) {
			pageRequest.put(proName, pageRequest.getString(PRO_Q).toUpperCase().concat("*"));
		}
	}


	/**
	 * 获取request
	 */
	public HttpServletRequest getRequest() {
		return ServletUtils.getRequest();
	}

	/**
	 * 获取response
	 */
	public HttpServletResponse getResponse() {
		return ServletUtils.getResponse();
	}

	/**
	 * 获取session
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}


	/**
	 * 响应返回结果
	 * 
	 * @param rows 影响行数
	 * @return 操作结果
	 */
	protected <T> AjaxResult<T> toAjax(int rows) {
		return rows > 0 ? success() : fail();
	}

	/**
	 * 响应返回结果
	 * 
	 * @param result 结果
	 * @return 操作结果
	 */
	protected <T> AjaxResult<T> toAjax(boolean result) {
		return result ? success() : fail();
	}

	/**
	 * 返回成功
	 */
	protected <T> AjaxResult<T> success() {
		return AjaxResult.success();
	}

	/**
	 * 返回成功消息
	 */
	protected <T> AjaxResult<T> success(T t) {
		return AjaxResult.success(t);
	}
	
	/**
	 * 返回成功消息
	 */
	protected <T> AjaxResult<T> success(T t, String message) {
		return AjaxResult.success(message, t);
	}

	/**
	 * 返回成功消息
	 */
	protected <T> AjaxResult<T> success(String message) {
		return AjaxResult.success(message);
	}
	
	/**
	 * 返回失败消息
	 */
	protected <T> AjaxResult<T> fail() {
		return AjaxResult.fail();
	}

	/**
	 * 返回失败消息
	 */
	protected <T> AjaxResult<T> fail(String message) {
		return AjaxResult.fail(message);
	}

	/**
	 * 返回错误码消息
	 */
	protected <T> AjaxResult<T> fail(Type type, String message) {
		return AjaxResult.fail(type, message);
	}
	
	/**
	 * 返回失败消息
	 */
	protected <T> AjaxResult<T> nologin() {
		return AjaxResult.nologin();
	}

	/**
	 * 页面跳转
	 */
	protected String redirect(String url) {
		return StringUtils.format("redirect:{}", url);
	}
	
	protected PageRequest pageRequest(Map<String, String> map){
		return new PageRequest(map);
	}
	
	protected PageRequest pageRequest(String text){
		Map map = JSON.parseObject(text, Map.class);
		return new PageRequest(map);
	}
	
	protected AjaxRequest ajaxRequest(HttpServletRequest request){
		Map<String, String> map = Maps.newHashMap();
		request.getParameterMap().forEach((k, v) -> {
			map.put(k, v[0]);
		});
		return new AjaxRequest(map);
	}
	
	protected AjaxRequest ajaxRequest(Map<String, String> map){
		return new AjaxRequest(map);
	}
	
	protected <T> AjaxRequest<T> ajaxRequest(T data, Map<String, String> map){
		return new AjaxRequest<T>(data, map);
	}
	
	protected <T> PageResult<T> page(List<T> list) {
		return PageResult.create(list);
	}
	
	protected <T> PageResult<T> page(Page page, List<T> list) {
		return PageResult.create(page, list);
	}
	
	protected <T> PageResult<T> page(PageInfo<T> page) {
		return PageResult.create(page);
	}
	
	protected <T> PageResult<T> page(Page page) {
		return PageResult.create(page);
	}
	
	protected <T> PageResult<T> pageFail(String msg) {
		return PageResult.createFail(msg);
	}
	
	protected <T> AjaxRequest<T> ajaxRequest(T t) {
		return new AjaxRequest<T>(t);
	}
	
	protected AjaxRequest ajaxRequest(String req) {
		Map map = JSON.parseObject(req, Map.class);
		return new AjaxRequest(map);
	}
	
	protected <T> AjaxRequest<T> ajaxRequest(String text, TypeReference<AjaxRequest<T>> type) {
		ObjectDeserializer od = ParserConfig.getGlobalInstance().getDeserializer(type.getType());
		if ( od == null || od instanceof MapDeserializer )
			ParserConfig.getGlobalInstance().putDeserializer(type.getType(), new JavaBeanDeserializer(ParserConfig.getGlobalInstance(), AjaxRequest.class, type.getType()));
		return (AjaxRequest<T>) JSON.parseObject(text, type);
	}
}
