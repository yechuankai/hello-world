package com.wms.web.controller.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;
import com.wms.web.constants.AttrConstants;

/**
 * 通用请求处理
 * 
 */
@Controller
@RequestMapping("/web")
public class WebController {
	private static final Logger log = LoggerFactory.getLogger(WebController.class);

	
	@GetMapping("/{moudle}/{templete}")
	public String redirectUrl(@PathVariable String moudle, @PathVariable String templete,@RequestParam Map<String, String> params ,Model model) {
		UserVO user = ShiroUtils.getSysUser();
		model.addAttribute(AttrConstants.ATTR_USER, user);
		if (!params.isEmpty()) {
			params.forEach((key, value) -> {
				model.addAttribute(key, value);
			});
		}
		return  moudle + "/" + templete;
	}
	
}
