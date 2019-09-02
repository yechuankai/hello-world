package com.wms.web.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.common.collect.Lists;
import com.wms.common.config.Global;
import com.wms.common.core.controller.BaseController;
import com.wms.entity.auto.SysWarehousesTEntity;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;
import com.wms.vo.WarehouseVO;
import com.wms.web.constants.AttrConstants;
import com.wms.web.constants.RedirectConstants;

/**
 * 登录验证
 * 
 */
@Controller
public class MobileLoginController extends BaseController {
	
	@Value("${spring.cloud.config.profile}")
	private String env;
	
	@GetMapping("/mobile/login")
	public String mobileLogin(Model model) {
		model.addAttribute(AttrConstants.ATTR_CURR_LOCALE, Global.locale);
		return RedirectConstants.MOBILE_LOGIN;
	}
	
	@GetMapping("/mobile/warehouses")
	public String warehouses(Model model) {
		UserVO user = ShiroUtils.getSysUser();
		//获取仓库
		List<WarehouseVO> warehouses = user.getWarehouses();
		
		List<WarehouseVO> newList = Lists.newArrayList();
		warehouses.forEach(w -> {
			if (w.getWarehouseId() != 0L) {
				newList.add(w);
			}
		});
		
		model.addAttribute(AttrConstants.ATTR_ENV, env);
		//设置到界面
		model.addAttribute(AttrConstants.ATTR_WAREHOUSE_LIST, newList);
		return RedirectConstants.MOBILE_WAREHOUSES;
	}
	
}
