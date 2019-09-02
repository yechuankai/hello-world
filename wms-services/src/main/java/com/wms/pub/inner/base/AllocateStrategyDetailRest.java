package com.wms.pub.inner.base;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.AllocateStrategyDetailTEntity;
import com.wms.services.base.IAllocateStrategDetailService;

/**
 * @description: 分配策略明细Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-27 11:40
 **/
@RestController
@RequestMapping("/services/inner/base/allocateStrategyDetail")
public class AllocateStrategyDetailRest extends BaseController {

    @Autowired
    IAllocateStrategDetailService allocateStrategDetailService;

    @RequestMapping(value = "/find")
    public PageResult<AllocateStrategyDetailTEntity> find(@RequestBody String req) {
        List<AllocateStrategyDetailTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = allocateStrategDetailService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/add")
    public AjaxResult add(@RequestBody String req) {
        try {
            AjaxRequest<AllocateStrategyDetailTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<AllocateStrategyDetailTEntity>>() {});
            List<AllocateStrategyDetailTEntity> updateList = Lists.newArrayList(request.getData());
            boolean flag = allocateStrategDetailService.add(ajaxRequest(updateList, request));
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

    @RequestMapping(value = "/save")
    public AjaxResult save(@RequestBody String req) {
        try {
            AjaxRequest<List<AllocateStrategyDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AllocateStrategyDetailTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = allocateStrategDetailService.modify(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

    @RequestMapping(value = "/delete")
    public AjaxResult delete(@RequestBody String req) {
        try {
        	AjaxRequest<List<AllocateStrategyDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AllocateStrategyDetailTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = allocateStrategDetailService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
}
