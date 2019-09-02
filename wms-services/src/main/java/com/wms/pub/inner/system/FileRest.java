package com.wms.pub.inner.system;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.SysFileTEntity;
import com.wms.services.sys.ISysFileService;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;

@RestController
@RequestMapping("/services/inner/file")
public class FileRest extends BaseController{
	
	@Autowired
	ISysFileService fileService;
	
	@RequestMapping(value = "/userFind")
	public PageResult<SysFileTEntity> userFind(@RequestBody String req) {
		List<SysFileTEntity> list = null;
		try {
			PageRequest request = pageRequest(req);
			PageHelper.startPage(request.getPageStart(), request.getPageSize());
			
			UserVO user = ShiroUtils.getSysUser();
			if (!user.isAdmin())
				request.put(SysFileTEntity.Column.userId.getJavaProperty(), user.getUserId());
			list = fileService.find(request);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	@RequestMapping(value = "/find")
	public PageResult<SysFileTEntity> find(@RequestBody String req) {
		List<SysFileTEntity> list = null;
		try {
			PageRequest request = pageRequest(req);
			PageHelper.startPage(request.getPageStart(), request.getPageSize());
			list = fileService.find(request);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	@RequestMapping(value = "/delete")
	public AjaxResult delete(@RequestBody String req) {
		try {
			AjaxRequest<List<SysFileTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysFileTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record delete.");
			}
			boolean flag = fileService.delete(request);
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}

	
}
