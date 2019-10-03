package com.wms.pub.inner.report;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.StringUtils;
import com.wms.entity.auto.AllocateTEntity;
import com.wms.entity.auto.BillingLeaseTEntity;
import com.wms.entity.auto.OwnerTEntity;
import com.wms.services.base.IOwnerService;
import com.wms.services.billing.ILeaseService;
import com.wms.vo.BillingLeaseVO;

@RestController
@RequestMapping("/services/inner/report/billing/lease")
public class BillingLeaseRest extends BaseController{

	@Autowired
	private ILeaseService leaseService;
	@Autowired
	private IOwnerService ownerService;
	
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
        	AjaxRequest<BillingLeaseVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<BillingLeaseVO>>() {});
        	BillingLeaseVO vo = request.getData();
        	if (StringUtils.isEmpty(vo.getOwnerCode()))
        		throw new BusinessServiceException("BillingLeaseRest", "owner.isnull", null);
        	
        	String [] ownerCodes = vo.getOwnerCode().split(",");
        	List<OwnerTEntity> owners = ownerService.findByOwnerCodes(OwnerTEntity.builder()
									        							.warehouseId(request.getWarehouseId())
									        							.companyId(request.getCompanyId())
									        							.build(), Sets.newHashSet(ownerCodes));
        	if (CollectionUtils.isEmpty(owners)) 
        		throw new BusinessServiceException("BillingLeaseRest", "owner.record.not.exists", new Object[] {StringUtils.join(ownerCodes, ",")});
        	
        	if (ownerCodes.length != owners.size()) {
        		Set<String> selectOwnerSet = owners.stream().map(OwnerTEntity::getOwnerCode).collect(Collectors.toSet());
        		List<String> notExists = Lists.newArrayList();
        		for (String o : ownerCodes) {
					if (!selectOwnerSet.contains(o)) {
						notExists.add(o);
					}
				}
        		throw new BusinessServiceException("BillingLeaseRest", "owner.record.not.exists", new Object[] {StringUtils.join(notExists, ",")});
        	}
        	owners.forEach(o -> {
        		leaseService.createOwnerLease(ajaxRequest(o, request), vo.getBillingDateBegin(), vo.getBillingDateEnd());
        	});
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return success();
    }
}
