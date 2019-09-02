package com.wms.pub.inner.system;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.SysOrderNumberTEntity;
import com.wms.services.sys.ISysOrderNumberService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 单据号
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-05 15:37
 **/
@RestController
@RequestMapping("/services/inner/orderNumber")
public class OrderNumberRest extends BaseController {
    @Autowired
    ISysOrderNumberService orderNumberService;

    @RequestMapping(value = "/find")
    public PageResult<SysOrderNumberTEntity> find(@RequestBody String req) {
        List<SysOrderNumberTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = orderNumberService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/save")
    public AjaxResult save(@RequestBody String req) {
        try {
            AjaxRequest<List<SysOrderNumberTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysOrderNumberTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }

            boolean flag = orderNumberService.modify(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
}
