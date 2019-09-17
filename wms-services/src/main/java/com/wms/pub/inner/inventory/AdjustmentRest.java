package com.wms.pub.inner.inventory;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.InventoryAdjustmentDetailTEntity;
import com.wms.entity.auto.InventoryAdjustmentHeaderTEntity;
import com.wms.services.inventory.IAdjustmentDetailService;
import com.wms.services.inventory.IAdjustmentService;
import com.wms.vo.adjustment.AdjustmentDetailVO;
import com.wms.vo.adjustment.AdjustmentVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services/inner/inventory/adjustment")
public class AdjustmentRest extends BaseController {

	@Autowired
	private IAdjustmentService adjustmentService;
	@Autowired
	private IAdjustmentDetailService adjustmentDetailService;

    @RequestMapping(value = "/find")
    public PageResult<InventoryAdjustmentHeaderTEntity> find(@RequestBody String req) {
        List<InventoryAdjustmentHeaderTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = adjustmentService.find(pageRequest);
            if (list == null)
            	list = Lists.newArrayList();
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }
	
	@RequestMapping(value = "/add")
    public AjaxResult add(@RequestBody String req) {
        try {
            AjaxRequest<AdjustmentVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<AdjustmentVO>>() {});
            boolean flag = adjustmentService.add(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
	
	@RequestMapping(value = "/adjustment")
    public AjaxResult adjustment(@RequestBody String req) {
        try {
            AjaxRequest<AdjustmentVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<AdjustmentVO>>() {});
            adjustmentDetailService.save(request);
            return success();
        } catch (Exception e) {
            return fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/findDetail")
    public PageResult<AdjustmentDetailVO> findDetail(@RequestBody String req) {
        List<AdjustmentDetailVO> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            Page<Object> page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            Long inventoryAdjustmentId = pageRequest.getLong(InventoryAdjustmentDetailTEntity.Column.inventoryAdjustmentId.getJavaProperty());
            if(null != inventoryAdjustmentId){
                list = adjustmentDetailService.find(pageRequest);
            }

            if (CollectionUtils.isEmpty(list)) {
                list = Lists.newArrayList();
            }
            return page(page, list);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteHeader")
    public AjaxResult deleteHeader(@RequestBody String req) {
        try {
            AjaxRequest<List<AdjustmentVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AdjustmentVO>>>() {});
            boolean flag = adjustmentService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
	
	
}
