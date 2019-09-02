package com.wms.pub.inner.base;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.SupplierTEntity;
import com.wms.services.base.ISupplierService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services/inner/base/supplier")
public class SupplierRest extends BaseController{
	
	@Autowired
	ISupplierService supplierService;
	
	@RequestMapping(value = "/find")
	public PageResult<SupplierTEntity> find(@RequestBody String req) {
		List<SupplierTEntity> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			//下拉搜索框
			if (pageRequest.containsKey(PRO_Q)) {
				pageRequest.put(SupplierTEntity.Column.supplierCode.getJavaProperty(), pageRequest.getString(PRO_Q).toUpperCase().concat("*"));
				pageRequest.remove(SupplierTEntity.Column.supplierId.getJavaProperty());
			}
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = supplierService.find(pageRequest);
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
			AjaxRequest<List<SupplierTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SupplierTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = supplierService.modify(request);
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
			AjaxRequest<List<SupplierTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SupplierTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = supplierService.delete(request);
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
			AjaxRequest<SupplierTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<SupplierTEntity>>() {});
			List<SupplierTEntity> updateList = Lists.newArrayList(request.getData());
			boolean flag = supplierService.add(ajaxRequest(updateList, request));
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}

}
