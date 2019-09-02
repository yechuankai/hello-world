package com.wms.web.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.CodelkupVO;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.enums.OnlineStatusEnum;
import com.wms.common.utils.cache.CodelkUpUtils;
import com.wms.entity.auto.SysUserOnlineTEntity;
import com.wms.entity.auto.SysWarehousesTEntity;
import com.wms.services.sys.ISysCodelkupService;
import com.wms.services.sys.ISysUserOnlineService;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;
import com.wms.vo.WarehouseVO;
import com.wms.web.constants.CodelkupConstants;
import com.wms.web.shiro.session.OnlineSession;
import com.wms.web.shiro.session.OnlineSessionDao;

@RestController
@RequestMapping("/services/web")
public class RestControler extends BaseController {
	
	@Autowired
	private ISysUserOnlineService onlineService;

	@Autowired
	ISysCodelkupService codelkupService;
	
	@Autowired
	private OnlineSessionDao onlineSessionDAO;
	
	@PostMapping("/setWarehouse")
	public AjaxResult setWarehouse(Long warehouseId, Long companyId) {
		UserVO user = ShiroUtils.getSysUser();
		if (user == null) {
			return nologin();
		}
		boolean allowSet = false;
		List<WarehouseVO> whs = user.getWarehouses();
		for (WarehouseVO wh : whs) {
			if (wh.getWarehouseId().equals(warehouseId)
					&& wh.getCompanyId().equals(companyId)) {
				allowSet = true;
				user.setWarehouseId(wh.getWarehouseId());
				user.setCompanyId(wh.getCompanyId());
				break;
			}
		}
		if (!allowSet) {
			return fail("warehouse.not.allow.access");
		}
		ShiroUtils.setSysUser(user);
		return success();
	}
	
	@PostMapping("/setLocale")
	public AjaxResult setWarehouse(String locale, HttpServletResponse response) {
		UserVO user = ShiroUtils.getSysUser();
		if (user == null) {
			return nologin();
		}
		boolean allowSet = false;
		//获取字典
		List<CodelkupVO> codelkups = CodelkUpUtils.getCodelkup(CodelkupConstants.LOCALE);
		for (CodelkupVO c : codelkups) {
			if (c.getCode().equalsIgnoreCase(locale)) {
				allowSet = true;
				locale = c.getCode();
				break;
			}
		}
		if (!allowSet) {
			return fail("locale.record.not.exists");
		}
		
		user.setLocale(locale);
		ShiroUtils.setSysUser(user);
		return success();
	}
	
	@RequestMapping("/test")
	public AjaxResult test() {
		UserVO user = ShiroUtils.getSysUser();
		if (user == null)
			return nologin();
		return success();
	}
	
	@RequestMapping("/offline")
	public AjaxResult offline(@RequestBody String req) {
		AjaxRequest<List<SysUserOnlineTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysUserOnlineTEntity>>>() {});
		List<SysUserOnlineTEntity> list = request.getData();
		if (CollectionUtils.isEmpty(list))
			return fail("no data.");
		
		for (SysUserOnlineTEntity o : list) {
			if (OnlineStatusEnum.OffLine.getCode().equals(o.getStatus()))
				continue;
			
			SysUserOnlineTEntity online = onlineService.findOnlineById(o.getSessionid());
	        if (online.getSessionid().equals(ShiroUtils.getSessionId())){
	            return fail("force.logout.not.current.user");
	        }
	        if (online == null){
	            return fail("user.is.logout");
	        }
	        OnlineSession onlineSession = (OnlineSession) onlineSessionDAO.readSession(online.getSessionid());
	        if (onlineSession == null) {
	            return fail("user.is.logout");
	        }
	        onlineSession.setStatus(OnlineStatusEnum.OffLine);
	        onlineSessionDAO.update(onlineSession);
	        online.setStatus(OnlineStatusEnum.OffLine.getCode());
	        online.setUpdateBy(request.getUserName());
	        onlineService.offline(Lists.newArrayList(online));
		}
        return success();
	}

	/**
	 * @Description 重新加载数据字典
	 * @return
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public AjaxResult refresh() {
		codelkupService.load();
		return success();
	}
}
