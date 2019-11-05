package com.wms.services.report;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.entity.auto.OwnerTEntity;
import com.wms.vo.report.OrderSummaryByMonthVO;
import com.wms.vo.report.OwnerOrderSummaryVO;

public interface ISummaryReportService {

	List<OwnerOrderSummaryVO> getOwnerOrderSumarry(AjaxRequest<OwnerTEntity> request);
	
	List<OrderSummaryByMonthVO> getOrderSumarryByMonth(AjaxRequest<OwnerTEntity> request);
	
}
