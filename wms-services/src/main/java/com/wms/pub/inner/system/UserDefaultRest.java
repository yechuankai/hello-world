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
import com.wms.entity.auto.SysUserDefaultTEntity;
import com.wms.services.sys.ISysUserDefaultService;

@RestController
@RequestMapping("/services/inner/userdefault")
public class UserDefaultRest extends BaseController{
	
	@Autowired
	private ISysUserDefaultService userdefaultService;
	
	
	@RequestMapping(value = "/find")
	public PageResult<SysUserDefaultTEntity> find(@RequestBody String req) {
		List<SysUserDefaultTEntity> list = null;
		try {
			//处理请求对象与查询条件重复
			PageRequest pageRequest = pageRequest(req);
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			
			list = userdefaultService.find(pageRequest);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	
	@RequestMapping(value = "/save")
	public AjaxResult save(@RequestBody String req) {
		try {
			AjaxRequest<List<SysUserDefaultTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysUserDefaultTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = userdefaultService.modify(request);
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
			AjaxRequest<List<SysUserDefaultTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysUserDefaultTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = userdefaultService.delete(request);
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
			AjaxRequest<SysUserDefaultTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<SysUserDefaultTEntity>>() {});
			List<SysUserDefaultTEntity> updateList = Lists.newArrayList(request.getData());
			
			boolean flag = userdefaultService.add(ajaxRequest(updateList, request));
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}

}
