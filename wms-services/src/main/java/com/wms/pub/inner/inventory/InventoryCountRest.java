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
import com.wms.entity.auto.InventoryCountDetailTEntity;
import com.wms.entity.auto.InventoryCountHeaderTEntity;
import com.wms.entity.auto.InventoryCountRequestTEntity;
import com.wms.services.inventory.IInventoryCountDetailService;
import com.wms.services.inventory.IInventoryCountHeaderService;
import com.wms.services.inventory.IInventoryCountRequestService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 盘点Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-09-07 11:52
 **/
@RestController
@RequestMapping("/services/inner/inventory/inventoryCount")
public class InventoryCountRest extends BaseController {
    @Autowired
    IInventoryCountHeaderService headerService;

    @Autowired
    IInventoryCountDetailService detailService;

    @Autowired
    IInventoryCountRequestService requestService;

    @RequestMapping(value = "/findRequest")
    public PageResult<InventoryCountRequestTEntity> findRequest(@RequestBody String req) {
        List<InventoryCountRequestTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            Page<Object> page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = requestService.find(pageRequest);
            if (CollectionUtils.isEmpty(list)) {
                list = Lists.newArrayList();
            }
            return page(page, list);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
    }

    @RequestMapping(value = "/findHeader")
    public PageResult<InventoryCountHeaderTEntity> findHeader(@RequestBody String req) {
        List<InventoryCountHeaderTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            Page<Object> page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = headerService.find(pageRequest);
            if (CollectionUtils.isEmpty(list)) {
                list = Lists.newArrayList();
            }
            return page(page, list);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
    }

    @RequestMapping(value = "/findDetail")
    public PageResult<InventoryCountDetailTEntity> findDetail(@RequestBody String req) {
        List<InventoryCountDetailTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            Page<Object> page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = detailService.find(pageRequest);
            if (CollectionUtils.isEmpty(list)) {
                list = Lists.newArrayList();
            }
            return page(page, list);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
    }

    @RequestMapping(value = "/addRequest")
    public AjaxResult addRequest(@RequestBody String req) {
        try {
            AjaxRequest<InventoryCountRequestTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<InventoryCountRequestTEntity>>() {});
            List<InventoryCountRequestTEntity> updateList = Lists.newArrayList(request.getData());
            boolean flag = requestService.add(ajaxRequest(updateList, request));
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

    @RequestMapping(value = "/requestSave")
    public AjaxResult requestSave(@RequestBody String req) {
        try {
            AjaxRequest<List<InventoryCountRequestTEntity> > request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InventoryCountRequestTEntity> >>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = requestService.modify(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

    @RequestMapping(value = "/requestDelete")
    public AjaxResult requestDelete(@RequestBody String req) {
        try {
            AjaxRequest<List<InventoryCountRequestTEntity> > request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InventoryCountRequestTEntity> >>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = requestService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

    @RequestMapping(value = "/createCount")
    public AjaxResult createCount(@RequestBody String req) {
        try {
            AjaxRequest<List<InventoryCountRequestTEntity> > request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InventoryCountRequestTEntity> >>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = requestService.createCount(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

    @RequestMapping(value = "/headerDelete")
    public AjaxResult headerDelete(@RequestBody String req) {
        try {
            AjaxRequest<List<InventoryCountHeaderTEntity> > request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InventoryCountHeaderTEntity> >>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = headerService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

}
