package com.wms.pub.inner.system;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.wms.common.utils.StringUtils;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.services.sys.ISysPasswordService;
import com.wms.services.sys.ISysUserService;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;

@RestController
@RequestMapping("/services/inner/user")
public class UserRest extends BaseController{
	
	@Autowired
	private ISysUserService userService;
	
	@Autowired
	private ISysPasswordService passwordService;
	
	@PostMapping("/restPassword")
	public AjaxResult restPassword(String oriPassword, String newPassword, String confirmPassword) {
		UserVO user = ShiroUtils.getSysUser();
		try {
			passwordService.validate(user, oriPassword);
			if (StringUtils.isEmpty(newPassword)
					 || StringUtils.isEmpty(confirmPassword)) {
				return fail("password.is.null");
			}
			if (!newPassword.equals(confirmPassword)) {
				return fail("password.confirm.not.match");
			}
			userService.restPassword(user, newPassword);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return success();
	}
	
	@RequestMapping(value = "/find")
	public PageResult<SysUserTEntity> find(@RequestBody String req) {
		List<SysUserTEntity> list = null;
		final String PRO_SEARCH_USERNAME = "suserName";
		try {
			//处理请求对象与查询条件重复
			PageRequest pageRequest = pageRequest(req);
			String username = pageRequest.getString(PRO_SEARCH_USERNAME);
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			
			pageRequest.remove(SysUserTEntity.Column.userName.getJavaProperty());
			pageRequest.remove(SysUserTEntity.Column.userId.getJavaProperty());
			
			if (StringUtils.isNotEmpty(username))
				pageRequest.remove(SysUserTEntity.Column.userName.getJavaProperty());
			
			list = userService.find(pageRequest);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	
	@RequestMapping(value = "/save")
	public AjaxResult save(@RequestBody String req) {
		try {
			AjaxRequest<List<SysUserTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysUserTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = userService.modify(request);
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
			AjaxRequest<List<SysUserTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysUserTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = userService.delete(request);
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
			AjaxRequest<SysUserTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<SysUserTEntity>>() {});
			List<SysUserTEntity> updateList = Lists.newArrayList(request.getData());
			boolean flag = userService.add(ajaxRequest(updateList, request));
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}

}
