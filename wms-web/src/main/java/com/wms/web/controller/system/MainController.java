package com.wms.web.controller.system;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.CodelkupVO;
import com.wms.common.utils.cache.CodelkUpUtils;
import com.wms.common.utils.cache.LocaleUtils;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;
import com.wms.vo.WarehouseVO;
import com.wms.web.constants.AttrConstants;
import com.wms.web.constants.CodelkupConstants;
import com.wms.web.constants.RedirectConstants;

@Controller
public class MainController extends BaseController {
	
	private static final String MOBILE="mobile";
	
	@Value("${spring.cloud.config.profile}")
	private String env;
	
	@GetMapping("/{type}/main")
	public String main(Model model,@PathVariable String type,@RequestParam Map<String, String> params) {
		UserVO user = ShiroUtils.getSysUser();
		//获取仓库
		List<WarehouseVO> warehouses = user.getWarehouses();
		
		//获取字典
		List<CodelkupVO> codelkups = CodelkUpUtils.getCodelkup(CodelkupConstants.LOCALE);
		
		//设置到界面
		model.addAttribute(AttrConstants.ATTR_LOCALE_LIST, codelkups);
		model.addAttribute(AttrConstants.ATTR_WAREHOUSE_LIST, warehouses);
		model.addAttribute(AttrConstants.ATTR_USER, user);
		model.addAttribute(AttrConstants.ATTR_CURR_LOCALE, LocaleUtils.getLocale());
		model.addAttribute(AttrConstants.ATTR_CURR_WAREHOUSE, user.getWarehouseId());
		model.addAttribute(AttrConstants.ATTR_CURR_COMPANY, user.getCompanyId());
		model.addAttribute(AttrConstants.ATTR_SELECT_WAREHOUSE, LocaleUtils.getLocaleLabel("web.tip.warehouse.choice"));
		model.addAttribute(AttrConstants.ATTR_ENV, env);
		if (!params.isEmpty()) {
			params.forEach((key, value) -> {
				model.addAttribute(key, value);
			});
		}
		if (MOBILE.equals(type)) {
			if ((user.getWarehouseId() == null || user.getWarehouseId() == 0L))
				return RedirectConstants.MOBILE_WAREHOUSES;
				
			return RedirectConstants.MOBILE_MAIN;
		}
		return RedirectConstants.WEB_MAIN;
	}
	
	
}
