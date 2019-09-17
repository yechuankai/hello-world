package com.wms.pub.inner.outbound;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.AllocateShortTEntity;
import com.wms.services.outbound.IAllocateShortService;

/**
 * 拣选缺量
 * @author yechuankai.chnet
 *
 */
@RestController
@RequestMapping("/services/inner/outbound/allocateShort")
public class AllocateShortRest extends BaseController {

    @Autowired
    IAllocateShortService allocateShortService;

    @RequestMapping(value = "/find")
    public PageResult<AllocateShortTEntity> find(@RequestBody String req) {
        List<AllocateShortTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = allocateShortService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }
    
}
