package com.wms.pub.inner.report;

import java.util.Date;
import java.util.List;

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
import com.wms.entity.auto.AllocateTEntity;
import com.wms.entity.auto.BillingLeaseTEntity;
import com.wms.services.billing.ILeaseService;
import com.wms.vo.WarehouseVO;

@RestController
@RequestMapping("/services/inner/report/billing/lease")
public class BillingLeaseRest extends BaseController{

	@Autowired
	private ILeaseService leaseService;
	
	@RequestMapping(value = "/find")
    public PageResult<BillingLeaseTEntity> find(@RequestBody String req) {
        List<BillingLeaseTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = leaseService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }
	
	@RequestMapping(value = "/create")
    public AjaxResult create(@RequestBody String req) {
        try {
        	AjaxRequest request = ajaxRequest(req);
        	request.setData(new Date());
            leaseService.createLease(request);
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return success();
    }
}
