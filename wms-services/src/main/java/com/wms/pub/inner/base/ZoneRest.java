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
import com.wms.entity.auto.ZoneTEntity;
import com.wms.services.base.IZoneService;

/**
 * @description: 基础数据-库区
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-19 10:30
 **/

@RestController
@RequestMapping("/services/inner/base/zone")
public class ZoneRest extends BaseController {

    @Autowired
    private IZoneService zoneService;

    @RequestMapping(value = "/find")
    public PageResult<ZoneTEntity> find(@RequestBody String req) {
        List<ZoneTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = zoneService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/add")
    public AjaxResult add(@RequestBody String req) {
        try {
            AjaxRequest<ZoneTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<ZoneTEntity>>() {});
            List<ZoneTEntity> updateList = Lists.newArrayList(request.getData());
            boolean flag = zoneService.add(ajaxRequest(updateList, request));
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
            AjaxRequest<List<ZoneTEntity> > request = ajaxRequest(req, new TypeReference<AjaxRequest<List<ZoneTEntity> >>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = zoneService.modify(request);
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
        	AjaxRequest<List<ZoneTEntity> > request = ajaxRequest(req, new TypeReference<AjaxRequest<List<ZoneTEntity> >>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = zoneService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

}
