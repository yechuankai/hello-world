package com.wms.pub.inner.system;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.utils.StringUtils;
import com.wms.entity.auto.SysCompanysTEntity;
import com.wms.services.sys.ISysCompanyService;

@RestController
@RequestMapping("/services/inner/company")
public class CompanyRest extends BaseController{
	
	@Autowired
	ISysCompanyService companyService;
	
	@RequestMapping(value = "/find")
	public PageResult<SysCompanysTEntity> find(@RequestBody String req) {
		List<SysCompanysTEntity> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			//排除查询条件
			pageRequest.remove(SysCompanysTEntity.Column.companyId.getJavaProperty());
			
			//下拉搜索框
			if (StringUtils.isNotNull(pageRequest.get(PRO_Q))) {
				pageRequest.put(SysCompanysTEntity.Column.code.getJavaProperty(), pageRequest.getString(PRO_Q).concat("*"));
			}
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = companyService.find(pageRequest);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	
	@RequestMapping(value = "/save")
	public AjaxResult save(@RequestBody String req) {
		try {
			AjaxRequest<List<SysCompanysTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysCompanysTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = companyService.modify(request);
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
			AjaxRequest<List<SysCompanysTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysCompanysTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = companyService.delete(request);
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
			AjaxRequest<SysCompanysTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<SysCompanysTEntity>>() {});
			List<SysCompanysTEntity> updateList = Lists.newArrayList(request.getData());
			boolean flag = companyService.add(ajaxRequest(updateList, request));
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}

}
