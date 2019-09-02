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
import com.wms.entity.auto.SysCodelistTEntity;
import com.wms.services.sys.ISysCodeListService;

/**
 * @description: 数据字典主表Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-06-25 17:10
 **/

@RestController
@RequestMapping("/services/inner/codeList")
public class CodeListRest extends BaseController {

    @Autowired
    ISysCodeListService codeListService;

    @RequestMapping(value = "/find")
    public PageResult<SysCodelistTEntity> find(@RequestBody String req) {
        List<SysCodelistTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = codeListService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/add")
    public AjaxResult add(@RequestBody String req) {
        try {
            AjaxRequest<SysCodelistTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<SysCodelistTEntity>>() {});
            List<SysCodelistTEntity> updateList = Lists.newArrayList(request.getData());
            boolean flag = codeListService.add(ajaxRequest(updateList, request));
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
            AjaxRequest<List<SysCodelistTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysCodelistTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }

            boolean flag = codeListService.modify(request);
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
        	AjaxRequest<List<SysCodelistTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysCodelistTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = codeListService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
}
