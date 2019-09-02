package com.wms.pub.inner.outbound;

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
import com.wms.common.enums.TransactionCategoryEnum;
import com.wms.entity.auto.AllocateTEntity;
import com.wms.services.outbound.IAllocateService;
import com.wms.vo.allocate.AllocateVO;

/**
 * @description: 出库-分配明细Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-03 17:01
 **/
@RestController
@RequestMapping("/services/inner/outbound/allocate")
public class AllocateRest extends BaseController {

    @Autowired
    IAllocateService allocateService;

    @RequestMapping(value = "/find")
    public PageResult<AllocateTEntity> find(@RequestBody String req) {
        List<AllocateTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = allocateService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }
    
    @RequestMapping(value = "/save")
    public AjaxResult save(@RequestBody String req) {
    	try { 
	    	AjaxRequest<List<AllocateTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AllocateTEntity>>>() {});
	        List<AllocateTEntity> allocateList = request.getData();
	        if (CollectionUtils.isEmpty(allocateList)) {
	            return fail("no record update.");
	        }
            boolean flag = allocateService.modify(allocateList);
            if (!flag)
            	return fail();
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return success();
    }
    
    @RequestMapping(value = "/pick")
    public AjaxResult pick(@RequestBody String req) {
    	try { 
	    	AjaxRequest<List<AllocateVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AllocateVO>>>() {});
	        List<AllocateVO> allocateList = request.getData();
	        if (CollectionUtils.isEmpty(allocateList)) {
	            return fail("no record update.");
	        }
	        allocateList.forEach(d -> {
	        	d.setTransactionCategory(TransactionCategoryEnum.PCPick.getCode());
	        	d.setQuantityPick(d.getQuantityAllocated());
	        });
            boolean flag = allocateService.pick(request);
            if (!flag)
            	return fail();
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return success();
    }
    
    @RequestMapping(value = "/ship")
    public AjaxResult ship(@RequestBody String req) {
    	try { 
	    	AjaxRequest<List<AllocateVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AllocateVO>>>() {});
	        List<AllocateVO> allocateList = request.getData();
	        if (CollectionUtils.isEmpty(allocateList)) {
	            return fail("no record update.");
	        }
	        allocateList.forEach(d -> {
	        	d.setTransactionCategory(TransactionCategoryEnum.PCShip.getCode());
	        	d.setQuantityShip(d.getQuantityAllocated());
	        });
            boolean flag = allocateService.ship(request);
            if (!flag)
            	return fail();
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return success();
    }
    
    @RequestMapping(value = "/delete")
    public AjaxResult delete(@RequestBody String req) {
    	try { 
	    	AjaxRequest<List<AllocateTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AllocateTEntity>>>() {});
	        List<AllocateTEntity> allocateList = request.getData();
	        if (CollectionUtils.isEmpty(allocateList)) {
	            return fail("no record update.");
	        }
            boolean flag = allocateService.delete(request);
            if (!flag)
            	return fail();
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return success();
    }
}
