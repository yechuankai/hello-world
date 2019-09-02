/**
 * 
 */
package com.wms.pub.rest.app;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.services.app.IAppUserRestService;
import com.wms.vo.PermissionVO;
import com.wms.vo.UserVO;

/**
 * <p>Title: AppRestServices</p>  
 * <p>Description: </p>  
 * @author yupu.chnet
 * @date 2019年8月15日
 * @version V1.0.0
 */

@RestController
@RequestMapping("/services/public")
public class AppRestServices extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(AppRestServices.class);
	
	@Autowired
	private IAppUserRestService appUserPermService;
	
	/**
	 * 
	 * <p>Title: appLogin</p>  
	 * <p>Description: app用户登录</p>  
	 * @author yupu.chnet
	 * @date 2019年8月15日
	 * @param params
	 * @return
	 */
	@RequestMapping(value = {"/login"})
	@ResponseBody
	public AjaxResult<UserVO> appLogin(@RequestBody String params) {
		AjaxRequest<SysUserTEntity> request = ajaxRequest(params, new TypeReference<AjaxRequest<SysUserTEntity>>() {});
		try {
			SysUserTEntity paramUser = request.getData();
			UserVO result = appUserPermService.appLogin(paramUser);;
			return success(result);
		}catch (Exception e) {
			logger.error("AppLogin Fail:", e);
			return fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 * <p>Title: getUserPermission</p>  
	 * <p>Description: </p>  
	 * @author yupu.chnet
	 * @date 2019年8月15日
	 * @param params
	 * @return
	 */
	@RequestMapping(value = {"/getUserPermission"})
	@ResponseBody
	public AjaxResult<List<PermissionVO>> getUserPermission(@RequestBody String params) {
		AjaxRequest<SysUserTEntity> request = ajaxRequest(params, new TypeReference<AjaxRequest<SysUserTEntity>>() {});
		try {
			SysUserTEntity paramUser = request.getData();
			List<PermissionVO> result = appUserPermService.queryAppUserPermByUser(paramUser, request.getLocale());
			return success(result);
		}catch (Exception e) {
			logger.error("GetUserPermission Fail:", e);
			return fail(e.getMessage());
		}
	}
}
