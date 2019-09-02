package com.wms.pub.rest.system;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wms.common.core.controller.BaseController;
import com.wms.common.utils.cache.LocaleUtils;

@RestController("publicLocaleRest")
@RequestMapping("/services/public/locale")
public class LocaleRest extends BaseController{
	
	@RequestMapping(value = "/download")
	public Map<String, Map<String, String>> download() {
		return LocaleUtils.get();
	}
	
	

}
