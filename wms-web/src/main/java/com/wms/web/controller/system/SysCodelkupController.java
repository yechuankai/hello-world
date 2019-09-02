package com.wms.web.controller.system;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.CodelkupVO;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.cache.CodelkUpUtils;
import com.wms.common.utils.cache.LocaleUtils;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  获取字典转换为js
 */
@Controller
public class SysCodelkupController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(SysCodelkupController.class);

	@GetMapping("/js/codelkupList")
	public void codelkupList(HttpServletRequest request, HttpServletResponse response, String listName) {
		if (StringUtils.isEmpty(listName)) 
			return;
		
		//設置當前語言
		UserVO user = ShiroUtils.getSysUser();
		if ( user != null ) {
			LocaleUtils.setLocale(user.getLocale());
		}
		
		String [] listNames = listName.split(",");
		Map<String, List<CodelkupVO>> codesMap = new HashMap<String, List<CodelkupVO>>();
		for (String l : listNames) {
			List<CodelkupVO> cs = CodelkUpUtils.getCodelkup(user.getCompanyId(),user.getWarehouseId(),l);
			if (CollectionUtils.isEmpty(cs)) {
				continue;
			}
			List<CodelkupVO> list = Lists.newArrayList();
			CodelkupVO empty = new CodelkupVO();
			empty.setCode("");
			empty.setDescr("---");
			list.add(empty);
			cs.forEach(c -> {
				list.add(c);
			});
			codesMap.put(l, list);
		}
		
		StringBuilder sb = new StringBuilder("var codelkups = ");
		sb.append(JSON.toJSONString(codesMap));
		sb.append(";");
		
		Set<String> vlistNames = codesMap.keySet();
		Map<String, CodelkupVO> codelkupMap = new HashMap<String, CodelkupVO>();
		for (String s : vlistNames) {
			List<CodelkupVO> list = codesMap.get(s);
			for (CodelkupVO c : list) {
				codelkupMap.put(s + c.getCode(), c);
			}
		}
		
		sb.append("var codelkupsMap = ");
		sb.append(JSON.toJSONString(codelkupMap));
		sb.append(";");
		
        try {
        	response.setContentType("application/javascript;charset=UTF-8");
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
