package com.wms.pub.inner.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.enums.OperatorTypeEnum;
import com.wms.entity.auto.OwnerTEntity;
import com.wms.services.base.IOwnerService;
import com.wms.vo.LpnPrintVO;
import com.wms.vo.outbound.OutboundVO;

@RestController
@RequestMapping("/services/inner/report/print")
public class PrintRest extends BaseController {

	@Autowired
	private IOwnerService ownerService;

	@RequestMapping("/lpn")
	public AjaxResult<Long> lpn(@RequestBody String req) {
		try {
			AjaxRequest<LpnPrintVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<LpnPrintVO>>() { });
			LpnPrintVO lpnPrint = request.getData();
			
			OwnerTEntity owner = OwnerTEntity.builder()
									.warehouseId(request.getWarehouseId())
									.companyId(request.getCompanyId())
									.ownerCode(lpnPrint.getOwnerCode())
									.build();
			
			long batchid = ownerService.generatorBarcode(ajaxRequest(owner, request), lpnPrint.getCount());
			return success(batchid);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
}
