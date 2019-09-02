package com.wms.pub.inner.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.response.PageResult;
import com.wms.services.report.IReportService;
import com.wms.vo.ReportVO;

@RestController
@RequestMapping("/services/inner/report")
public class ReportRest extends BaseController{
	
	@Autowired
	private IReportService reportService;

	@RequestMapping(value = "/find")
	public PageResult<ReportVO> find(@RequestBody String req) {
		List<ReportVO> list = null;
		try {
			//PageRequest pageRequest = pageRequest(req);
			list = reportService.find();
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
}
