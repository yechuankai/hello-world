package com.wms.pub.rest.outbound;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.enums.OperatorTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.OutboundDetailTEntity;
import com.wms.services.base.IEnterpriseService;
import com.wms.services.outbound.IOutboundDetailService;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.vo.inventory.EntInventoryOnhandVO;
import com.wms.vo.outbound.OutboundDetailVO;
import com.wms.vo.outbound.OutboundVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 出库单对外Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-27 15:09
 **/
@RestController("publicOutboundRest")
@RequestMapping("/services/public/outbound")
public class OutboundRest extends BaseController {
    @Autowired
    IOutboundHeaderService outboundHeaderService;

    @Autowired
    IOutboundDetailService outboundDetailService;

    @Autowired
    IEnterpriseService enterpriseService;

    @RequestMapping(value = "/find")
    public PageResult<OutboundVO> find(@RequestBody String req) {
        List<OutboundVO> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = outboundHeaderService.findFromOms(pageRequest);
            if(CollectionUtils.isEmpty(list)){
                return page(Lists.newArrayList());
            }
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/findDetail")
    public PageResult<OutboundDetailVO> findDetail(@RequestBody String req) {
        List<OutboundDetailVO> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            Page<Object> page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            Long outboundHeaderId = pageRequest.getLong(OutboundDetailTEntity.Column.outboundHeaderId.getJavaProperty());
            if (null != outboundHeaderId) {
                list = outboundDetailService.find(pageRequest);
            }

            if (CollectionUtils.isEmpty(list)) {
                list = Lists.newArrayList();
            }
            return page(page, list);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
    }

    @RequestMapping(value = "/findInventoryOnhand")
    public PageResult<EntInventoryOnhandVO> findInventoryOnhand(@RequestBody String req){
        List<EntInventoryOnhandVO> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = enterpriseService.findInventoryOnhand(pageRequest);
            if(CollectionUtils.isEmpty(list)){
                return page(Lists.newArrayList());
            }
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/save")
    public AjaxResult<OutboundVO> save(@RequestBody String req) {
        final String PRO_OPERATORTYPE = "operatorType";
        try {
            PageRequest pageRequest = pageRequest(req);
            String operatorType = pageRequest.getString(PRO_OPERATORTYPE);
            AjaxRequest<OutboundVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<OutboundVO>>() {});
            OutboundVO outboundVO = request.getData();
            if(null == outboundVO){
                throw new BusinessServiceException("no update data.");
            }
            OperatorTypeEnum operatorEnum = OperatorTypeEnum.get(operatorType);
            outboundVO.setOperatorType(operatorEnum);
            outboundHeaderService.saveFromOms(request);

            return success(outboundVO);
        } catch (Exception e) {
            return fail(e.getMessage());
        }
    }
}
