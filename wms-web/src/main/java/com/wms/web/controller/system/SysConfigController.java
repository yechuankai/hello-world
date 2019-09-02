package com.wms.web.controller.system;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.common.core.controller.BaseController;

/**
 * 获取配置转换为js
 */
@Controller
public class SysConfigController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(SysConfigController.class);

	@Autowired
	private Environment env;

	private List<String> property = Lists.newArrayList("wms.url.service", "wms.url.report", "wms.url.file");

	@GetMapping("/js/config")
	public void config(HttpServletRequest request, HttpServletResponse response, String listName) {
		Map<String, String> propertyValue = Maps.newHashMap();
		property.forEach(p -> {
			propertyValue.put(p, env.getProperty(p));
		});
		
		StringBuilder sb = new StringBuilder("var global = ");
		sb.append(JSON.toJSONString(propertyValue));
		sb.append(";");		
		try {
			response.setContentType("application/javascript;charset=UTF-8");
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

	}
}
