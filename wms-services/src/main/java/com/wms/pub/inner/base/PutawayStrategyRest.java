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
import com.wms.entity.auto.PutawayStrategyTEntity;
import com.wms.services.base.IPutawayStrategyHeaderService;

/**
 * @description: 上架策略Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-03 09:52
 **/
@RestController
@RequestMapping("/services/inner/base/putawayStrategy")
public class PutawayStrategyRest extends BaseController {
    @Autowired
    IPutawayStrategyHeaderService putawayStrategyService;

    @RequestMapping(value = "/find")
    public PageResult<PutawayStrategyTEntity> find(@RequestBody String req) {
        List<PutawayStrategyTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = putawayStrategyService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/add")
    public AjaxResult add(@RequestBody String req) {
        try {
            AjaxRequest<PutawayStrategyTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<PutawayStrategyTEntity>>() {});
            List<PutawayStrategyTEntity> updateList = Lists.newArrayList(request.getData());
            boolean flag = putawayStrategyService.add(ajaxRequest(updateList, request));
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
            AjaxRequest<List<PutawayStrategyTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<PutawayStrategyTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = putawayStrategyService.modify(request);
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
        	AjaxRequest<List<PutawayStrategyTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<PutawayStrategyTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = putawayStrategyService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
}
