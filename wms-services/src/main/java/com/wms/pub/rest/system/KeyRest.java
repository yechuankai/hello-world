package com.wms.pub.rest.system;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.utils.key.KeyUtils;

@RestController("publicKeyRest")
@RequestMapping("/services/public/key")
public class KeyRest extends BaseController {

	@RequestMapping(value = "create")
	public AjaxResult<String> lpnMove(@RequestBody String req) {
		AjaxRequest<String> request = ajaxRequest(req, new TypeReference<AjaxRequest<String>>() {});
		try {
			OrderNumberTypeEnum code = OrderNumberTypeEnum.get(request.getData());
			if (code == null) {
				return fail("code is invalid.");
			}
			String number = KeyUtils.getOrderNumber(request.getCompanyId(), request.getWarehouseId(), code);
			return success(number);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
}
