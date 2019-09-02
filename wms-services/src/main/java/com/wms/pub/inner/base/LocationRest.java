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
import com.wms.entity.auto.AreaTEntity;
import com.wms.entity.auto.CarrierTEntity;
import com.wms.entity.auto.LocationTEntity;
import com.wms.services.base.ILocationService;

/**
 * @description: 库位Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-20 11:21
 **/
@RestController
@RequestMapping("/services/inner/base/location")
public class LocationRest extends BaseController {

    @Autowired
    private ILocationService locationService;

    @RequestMapping(value = "/find")
    public PageResult<LocationTEntity> find(@RequestBody String req) {
        List<LocationTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            convertWordQ(pageRequest, LocationTEntity.Column.locationCode.getJavaProperty());
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = locationService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }
    @RequestMapping(value = "/add")
    public AjaxResult add(@RequestBody String req) {
        try {
            AjaxRequest<LocationTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<LocationTEntity>>() {});
            List<LocationTEntity> updateList = Lists.newArrayList(request.getData());
            boolean flag = locationService.add(ajaxRequest(updateList, request));
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
            AjaxRequest<List<LocationTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<LocationTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = locationService.modify(request);
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
        	AjaxRequest<List<LocationTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<LocationTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = locationService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

    @RequestMapping(value = "/findArealocation")
    public PageResult<LocationTEntity> findArealocation(@RequestBody String req){
        List<LocationTEntity> list = null;
        try {
            AjaxRequest<AreaTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<AreaTEntity>>() {});
            list = locationService.findlocationAvailable(request.getData());
            if (list == null) {
                list = Lists.newArrayList();
            }
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

}
