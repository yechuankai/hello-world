package com.wms.pub.rest.inbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.entity.auto.LpnTEntity;
import com.wms.services.core.IPutawayCoreService;
import com.wms.vo.PutawayVO;

@RestController
@RequestMapping("/services/public/putaway")
public class PutawayRest extends BaseController{
	
	@Autowired
	IPutawayCoreService putawayCoreService;

	@RequestMapping("findPutawayLoc")
	public AjaxResult<String> findPutawayLoc(@RequestBody String req){
		AjaxRequest<LpnTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<LpnTEntity>>() {});
		try {
			LpnTEntity lpn = request.getData();
			lpn.setWarehouseId(request.getWarehouseId());
			lpn.setCompanyId(request.getCompanyId());
			PutawayVO putaway = putawayCoreService.lpnPutaway(lpn);
			return success(putaway.getToLocationCode());
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
}
