package com.wms.pub.rest.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.OwnerTEntity;
import com.wms.services.report.ISummaryReportService;
import com.wms.vo.allocate.AllocateVO;
import com.wms.vo.outbound.OutboundVO;
import com.wms.vo.report.OrderSummaryByMonthVO;
import com.wms.vo.report.OwnerOrderSummaryVO;

/**
 * 报表统计
 * @author yechuankai.chnet
 *
 */
@RestController("publicSummaryRest")
@RequestMapping("/services/public/report/summary")
public class SummaryRest extends BaseController{
	
	@Autowired
	private ISummaryReportService summaryReportService;

	@RequestMapping(value = "/ownerOrderSummary")
    public AjaxResult<List<OwnerOrderSummaryVO>> ownerOrderSumarry(@RequestBody String req) {
		try {
			AjaxRequest<OwnerTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<OwnerTEntity>>() {});
			List<OwnerOrderSummaryVO> list = summaryReportService.getOwnerOrderSumarry(request);
			return success(list);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/orderSummaryByMonth")
    public AjaxResult<List<OrderSummaryByMonthVO>> orderSumarryByMonth(@RequestBody String req) {
		try {
			AjaxRequest<OwnerTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<OwnerTEntity>>() {});
			List<OrderSummaryByMonthVO> list = summaryReportService.getOrderSumarryByMonth(request);
			return success(list);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
}
