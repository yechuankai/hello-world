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
import com.wms.entity.auto.AreaTEntity;
import com.wms.services.base.IAreaHeaderService;

/**
 * @description: 基础数据-区域Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-24 10:59
 **/

@RestController
@RequestMapping("/services/inner/base/area")
public class AreaRest extends BaseController {

    @Autowired
    IAreaHeaderService areaHeaderService;

    @RequestMapping(value = "/find")
    public PageResult<AreaTEntity> find(@RequestBody String req) {
        List<AreaTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = areaHeaderService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/add")
    public AjaxResult add(@RequestBody String req) {
        try {
            AjaxRequest<AreaTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<AreaTEntity>>() {});
            List<AreaTEntity> updateList = Lists.newArrayList(request.getData());
            boolean flag = areaHeaderService.add(ajaxRequest(updateList, request));
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
            AjaxRequest<List<AreaTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AreaTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = areaHeaderService.modify(request);
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
        	AjaxRequest<List<AreaTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AreaTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = areaHeaderService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

    @RequestMapping("/findAllocateStrategyArea")
    public PageResult<AreaTEntity> findArealocation(@RequestBody String req){
        List<AreaTEntity> list = null;
        try {
            AjaxRequest<AllocateStrategyTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<AllocateStrategyTEntity>>() {});
            list = areaHeaderService.findAreaAvailable(request.getData());
            if (list == null) {
                list = Lists.newArrayList();
            }
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

}
