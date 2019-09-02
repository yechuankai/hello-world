package com.wms.web.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wms.common.config.Global;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.utils.ServletUtils;
import com.wms.common.utils.StringUtils;
import com.wms.web.constants.AttrConstants;
import com.wms.web.constants.RedirectConstants;

/**
 * 登录验证
 * 
 */
@Controller
public class WebLoginController extends BaseController {
	
	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		// 如果是Ajax请求，返回Json字符串。
		if (ServletUtils.isAjaxRequest(request)) {
			String responseString = nologin().toString();
			return ServletUtils.renderString(response, responseString);
		}
		if (ServletUtils.isMobile())
			return RedirectConstants.REDIRECT_MOBILE_LOGIN;
		else
			return RedirectConstants.WEB_LOGIN;
			
	}
	
	@GetMapping("/web/login")
	public String webLogin(Model model) {
		if (ServletUtils.isMobile()) 
			return RedirectConstants.REDIRECT_MOBILE_LOGIN;
		
		model.addAttribute(AttrConstants.ATTR_CURR_LOCALE, Global.locale);
		return RedirectConstants.WEB_LOGIN;
	}
	
	@PostMapping("/login")
	@ResponseBody
	public AjaxResult ajaxLogin(String username, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, false);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return success();
		} catch (AuthenticationException e) {
			String msg = "用户或密码错误";
			if (StringUtils.isNotEmpty(e.getMessage())) {
				msg = e.getMessage();
			}
			return fail(msg);
		}
	}

	@GetMapping("/unauth")
	public String unauth() {
		return "/error/unauth";
	}
}
