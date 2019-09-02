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
import com.wms.entity.auto.AllocateStrategyTEntity;
import com.wms.services.base.IAllocateStrategyHeaderService;

/**
 * @description: 分配策略Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-27 09:33
 **/
@RestController
@RequestMapping("/services/inner/base/allocateStrategy")
public class AllocateStrategyRest extends BaseController {

    @Autowired
    IAllocateStrategyHeaderService allocateStrategyHeaderService;

    @RequestMapping(value = "/find")
    public PageResult<AllocateStrategyTEntity> find(@RequestBody String req) {
        List<AllocateStrategyTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = allocateStrategyHeaderService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/add")
    public AjaxResult add(@RequestBody String req) {
        try {
            AjaxRequest<AllocateStrategyTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<AllocateStrategyTEntity>>() {});
            List<AllocateStrategyTEntity> updateList = Lists.newArrayList(request.getData());
            boolean flag = allocateStrategyHeaderService.add(ajaxRequest(updateList, request));
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
            AjaxRequest<List<AllocateStrategyTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AllocateStrategyTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = allocateStrategyHeaderService.modify(request);
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
        	AjaxRequest<List<AllocateStrategyTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AllocateStrategyTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = allocateStrategyHeaderService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
}
