package com.wms.pub.rest.system;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.wms.common.core.controller.BaseController;
import com.wms.common.utils.cache.LocaleUtils;
import com.wms.entity.auto.SysFileTEntity;
import com.wms.services.sys.ISysFileService;

@RestController("publicLocaleRest")
@RequestMapping("/services/public/locale")
public class LocaleRest extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(LocaleRest.class);
	
	@Autowired
	private ISysFileService fileService;
	
	@RequestMapping(value = "/download")
	public Map<String, Object> download(@RequestBody String params) {
		Map<String, Object> result = Maps.newHashMap();
		SysFileTEntity appInfo = fileService.findMobileApp();
		result.put("local", LocaleUtils.get());
		if (null != appInfo) 
			result.put("appInfo", appInfo);
		
		return result;
	}

}
