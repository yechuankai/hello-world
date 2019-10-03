package com.wms.pub.inner.system;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.services.sys.ISysPermissionService;
import com.wms.vo.PermissionVO;

@RestController
@RequestMapping("/services/inner/permission")
public class PermissionRest extends BaseController{
	
	@Autowired
	ISysPermissionService permissionService;
	
	private void processShowName(List<PermissionVO> list, Boolean join) {
		list.forEach(p -> {
			int level = p.getLevel();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < level; i++) {
				sb.append("<span class='tree-indent tree-line'></span>");
			}
			sb.append("<span class='tree-indent tree-join'></span>");
			if (join)
				p.setShow(sb.toString() + p.getPermissionName());
			else
				p.setShow(sb.toString());
		});
	}
	
	@RequestMapping(value = "/find")
	public PageResult<PermissionVO> find(@RequestBody String req) {
		List<PermissionVO> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			//PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = permissionService.find(pageRequest);
			if (CollectionUtils.isEmpty(list))
				list = Lists.newArrayList();
			
			processShowName(list, Boolean.FALSE);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	@RequestMapping(value = "/findMenuDropdown")
	public List<PermissionVO> findMenuDropdown(@RequestBody String req) {
		List<PermissionVO> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			//pageRequest.put(SysPermissionTEntity.Column.permissionType.getJavaProperty(), PermissionTypeEnum.Menu.getCode());
			list = permissionService.find(pageRequest);
			if (CollectionUtils.isEmpty(list))
				list = Lists.newArrayList();
			
			processShowName(list, Boolean.TRUE);
			
		} catch (Exception e) {
			return Lists.newArrayList();
		}
		return list;
	}
	
	
	@RequestMapping(value = "/save")
	public AjaxResult save(@RequestBody String req) {
		try {
			AjaxRequest<List<PermissionVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<PermissionVO>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = permissionService.modify(request);
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
			AjaxRequest<List<PermissionVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<PermissionVO>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = permissionService.delete(request);
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
			AjaxRequest<PermissionVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<PermissionVO>>() {});
			List<PermissionVO> updateList = Lists.newArrayList(request.getData());
			boolean flag = permissionService.add(ajaxRequest(updateList, request));
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}
	
}
