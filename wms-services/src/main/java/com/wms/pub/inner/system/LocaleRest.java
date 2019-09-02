package com.wms.pub.inner.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.TypeReference;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.SysLocaleTEntity;
import com.wms.services.sys.ISysLocaleService;

@RestController
@RequestMapping("/services/inner/locale")
public class LocaleRest extends BaseController{
	
	@Autowired
	ISysLocaleService localeService;
	
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public AjaxResult refresh() {
		localeService.loadLocale();
		return success();
	}
	
	@RequestMapping(value = "/find")
	public PageResult<SysLocaleTEntity> find(@RequestBody String req) {
		List<SysLocaleTEntity> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = localeService.find(pageRequest);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	
	@RequestMapping(value = "/save")
	public AjaxResult save(@RequestBody String req) {
		try {
			AjaxRequest<List<SysLocaleTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysLocaleTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = localeService.modify(request);
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}
	
	@RequestMapping(value = "/delete")
	public AjaxResult delete(@RequestBody String req) {
		try {
			AjaxRequest<List<SysLocaleTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysLocaleTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = localeService.delete(request);
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}
	
	@RequestMapping(value = "/add")
	public AjaxResult add(@RequestBody String req) {
		try {
			AjaxRequest<SysLocaleTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<SysLocaleTEntity>>() {});
			List<SysLocaleTEntity> updateList = Lists.newArrayList(request.getData());
			boolean flag = localeService.add(ajaxRequest(updateList, request));
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}
	

}
