package com.wms.pub.rest.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.CodelkupVO;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.cache.CodelkUpUtils;
import com.wms.common.utils.cache.LocaleUtils;

@RestController("publicCodelkupRest")
@RequestMapping("/services/public/codelkup")
public class CodelkupRest extends BaseController{
	
	@RequestMapping(value = "/download")
	public AjaxResult<Map<String, List<CodelkupVO>>> download(@RequestBody String req) {
		AjaxRequest<String> request = ajaxRequest(req, new TypeReference<AjaxRequest<String>>() {});
		try {
			if (StringUtils.isEmpty(request.getData())) {
				return fail();
			}
			
			LocaleUtils.setLocale(request.getLocale());
			
			String [] listNames = request.getData().split(",");
			Map<String, List<CodelkupVO>> codesMap = new HashMap<String, List<CodelkupVO>>();
			for (String l : listNames) {
				List<CodelkupVO> cs = CodelkUpUtils.getCodelkup(request.getCompanyId(), request.getWarehouseId(), l);
				if (CollectionUtils.isEmpty(cs)) {
					continue;
				}
				List<CodelkupVO> list = Lists.newArrayList();
				CodelkupVO empty = new CodelkupVO();
				empty.setCode("undefined");
				empty.setDescr("---");
				list.add(empty);
				cs.forEach(c -> {
					list.add(c);
				});
				codesMap.put(l, list);
			}
			return success(codesMap);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	

}
