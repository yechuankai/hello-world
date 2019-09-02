package com.wms.pub.inner.system;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.wms.aspect.ExceptionAspect;
import com.wms.common.core.controller.BaseController;
import com.wms.services.sys.ISysRolePermissionService;
import com.wms.vo.PermissionTreeVO;
import com.wms.vo.UserVO;

@RestController
@RequestMapping("/services/inner/userPermission")
public class UserPermissionRest extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(UserPermissionRest.class);
	
	@Autowired
	ISysRolePermissionService userPermissionService;
	
	@RequestMapping(value = "/find")
	public List<PermissionTreeVO> find(UserVO user) {
		List<PermissionTreeVO> list = Lists.newArrayList();
		try {
			PermissionTreeVO tree = null;
			if(user.isAdmin())
				tree = userPermissionService.findAll2Tree(Lists.newArrayList());
			else
				tree = userPermissionService.findAll2Tree(user);
			
			if(tree != null)
				list.add(tree);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}

}
