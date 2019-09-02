package com.wms.pub.inner.base;

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
import com.wms.entity.auto.AreaDetailTEntity;
import com.wms.services.base.IAreaDetailService;

/**
 * @description: 基础数据-区域明细Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-24 14:49
 **/

@RestController
@RequestMapping("/services/inner/base/areaDetail")
public class AreaDetailRest extends BaseController {

    @Autowired
    IAreaDetailService areaDetailService;

    @RequestMapping(value = "/find")
    public PageResult<AreaDetailTEntity> find(@RequestBody String req) {
        List<AreaDetailTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = areaDetailService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/add")
    public AjaxResult add(@RequestBody String req) {
        try {
            AjaxRequest<List<AreaDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AreaDetailTEntity>>>() {});
            boolean flag = areaDetailService.add(request);
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
            AjaxRequest<List<AreaDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AreaDetailTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = areaDetailService.modify(request);
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
        	AjaxRequest<List<AreaDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AreaDetailTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = areaDetailService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

}
