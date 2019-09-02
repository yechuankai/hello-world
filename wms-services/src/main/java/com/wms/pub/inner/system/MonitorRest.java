package com.wms.pub.inner.system;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.mongodb.RestContent;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.enums.MonitorTypeEnum;
import com.wms.common.utils.mongodb.MongoUtils;
import com.wms.entity.auto.SysMonitorLogTEntity;
import com.wms.services.sys.ISysMonitorLogService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

@RestController
@RequestMapping("/services/inner/monitor")
public class MonitorRest extends BaseController{
	
	@Autowired
	ISysMonitorLogService monitorService;
	
	@RequestMapping(value = "/find")
	public PageResult<SysMonitorLogTEntity> find(@RequestBody String req) {
		List<SysMonitorLogTEntity> list = null;
		try {
			PageRequest request = pageRequest(req);
			PageHelper.startPage(request.getPageStart(), request.getPageSize());
			list = monitorService.find(request);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	@RequestMapping(value = "/view")
	public AjaxResult<RestContent> view(@RequestBody String req) {
		RestContent response = null;
		try {
			AjaxRequest<RestContent> request = ajaxRequest(req, new TypeReference<AjaxRequest<RestContent>>() {});
			RestContent content = request.getData();
			if (content == null)
				return fail("no data");
			
			response = MongoUtils.findById(new ObjectId(content.getId()), RestContent.class);
			if (response == null)
				return fail("no data");
			
			return success(response);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
	}
	
}
