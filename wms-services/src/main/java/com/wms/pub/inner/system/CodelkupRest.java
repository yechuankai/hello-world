package com.wms.pub.inner.system;

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
import com.wms.entity.auto.SysCodelkupTEntity;
import com.wms.services.sys.ISysCodelkupService;

/**
 * @description: 数据字典明细Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-26 10:04
 **/

@RestController
@RequestMapping("/services/inner/codelkup")
public class CodelkupRest extends BaseController {

    @Autowired
    ISysCodelkupService codelkupService;

    @RequestMapping(value = "/find")
    public PageResult<SysCodelkupTEntity> find(@RequestBody String req) {
        List<SysCodelkupTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = codelkupService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/add")
    public AjaxResult add(@RequestBody String req) {
        try {
            AjaxRequest<SysCodelkupTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<SysCodelkupTEntity>>() {});
            List<SysCodelkupTEntity> updateList = Lists.newArrayList(request.getData());
            boolean flag = codelkupService.add(ajaxRequest(updateList, request));
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
            AjaxRequest<List<SysCodelkupTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysCodelkupTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = codelkupService.modify(request);
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
        	AjaxRequest<List<SysCodelkupTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysCodelkupTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = codelkupService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
}
