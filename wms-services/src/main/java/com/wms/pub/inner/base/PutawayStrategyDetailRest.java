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
import com.wms.entity.auto.PutawayStrategyDetailTEntity;
import com.wms.services.base.IPutawayStrategyDetailService;

/**
 * @description: 上架策略明细Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-03 11:09
 **/
@RestController
@RequestMapping("/services/inner/base/putawayStrategyDetail")
public class PutawayStrategyDetailRest extends BaseController {

    @Autowired
    IPutawayStrategyDetailService putawayStrategyDetailService;

    @RequestMapping(value = "/find")
    public PageResult<PutawayStrategyDetailTEntity> find(@RequestBody String req) {
        List<PutawayStrategyDetailTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = putawayStrategyDetailService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/add")
    public AjaxResult add(@RequestBody String req) {
        try {
            AjaxRequest<PutawayStrategyDetailTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<PutawayStrategyDetailTEntity>>() {});
            List<PutawayStrategyDetailTEntity> updateList = Lists.newArrayList(request.getData());
            boolean flag = putawayStrategyDetailService.add(ajaxRequest(updateList, request));
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
            AjaxRequest<List<PutawayStrategyDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<PutawayStrategyDetailTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = putawayStrategyDetailService.modify(request);
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
        	AjaxRequest<List<PutawayStrategyDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<PutawayStrategyDetailTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = putawayStrategyDetailService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

}
