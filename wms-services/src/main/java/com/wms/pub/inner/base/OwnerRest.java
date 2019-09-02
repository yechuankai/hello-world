package com.wms.pub.inner.base;

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
import com.wms.entity.auto.OwnerTEntity;
import com.wms.services.base.IOwnerService;

@RestController
@RequestMapping("/services/inner/base/owner")
public class OwnerRest extends BaseController{
	
	@Autowired
	IOwnerService ownerService;
	
	@RequestMapping(value = "/find")
	public PageResult<OwnerTEntity> find(@RequestBody String req) {
		List<OwnerTEntity> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			//下拉搜索框
			if (pageRequest.containsKey(PRO_Q)) {
				pageRequest.put(OwnerTEntity.Column.ownerCode.getJavaProperty(), pageRequest.getString(PRO_Q).toUpperCase().concat("*"));
				pageRequest.remove(OwnerTEntity.Column.ownerId.getJavaProperty());
			}
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = ownerService.find(pageRequest);
			if (CollectionUtils.isEmpty(list))
				list = Lists.newArrayList();
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	
	
	@RequestMapping(value = "/save")
	public AjaxResult save(@RequestBody String req) {
		try {
			AjaxRequest<List<OwnerTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<OwnerTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = ownerService.modify(request);
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
			AjaxRequest<List<OwnerTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<OwnerTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = ownerService.delete(request);
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
			AjaxRequest<OwnerTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<OwnerTEntity>>() {});
			List<OwnerTEntity> updateList = Lists.newArrayList(request.getData());
			boolean flag = ownerService.add(ajaxRequest(updateList, request));
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}

}
