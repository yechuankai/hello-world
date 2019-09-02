package com.wms.pub.inner.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.utils.StringUtils;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.entity.auto.SysWarehousesTEntity;
import com.wms.services.sys.ISysUserService;
import com.wms.services.sys.ISysWarehouseService;
import com.wms.vo.UserVO;

@RestController
@RequestMapping("/services/inner/userWarehouse")
public class UserWarehouseRest extends BaseController{
	
	@Autowired
	private ISysWarehouseService warehouseService;
	@Autowired
	private ISysUserService userService;
	
	@RequestMapping(value = "/find")
	public PageResult<SysWarehousesTEntity> find(@RequestBody String req) {
		List<SysWarehousesTEntity> list = null;
		try {
			PageRequest request = pageRequest(req);
			SysUserTEntity user = null;
			String userId = request.getString(SysUserTEntity.Column.userId.getJavaProperty());
			if (StringUtils.isEmpty(userId)) {
				String loginName = request.getString(SysUserTEntity.Column.loginName.getJavaProperty());
				user = userService.findUserByLoginName(loginName);
			} else {
				user = SysUserTEntity.builder().userId(Long.parseLong(userId)).build();
			}
			if (new UserVO(user).isAdmin())
				list = warehouseService.find(request);
			else
				list = warehouseService.findUserWarehouse(user);
			
			if (list == null) list = Lists.newArrayList();
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	

}
