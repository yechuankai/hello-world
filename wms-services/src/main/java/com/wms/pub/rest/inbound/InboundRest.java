package com.wms.pub.rest.inbound;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.enums.OperatorTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.services.inbound.IInboundDetailService;
import com.wms.services.inbound.IInboundHeaderService;
import com.wms.vo.inbound.InboundDetailVO;
import com.wms.vo.inbound.InboundVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 提供给OMS使用
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-19 16:04
 **/
@RestController("publicInboundRest")
@RequestMapping("/services/public/inboundFromOms")
public class InboundRest extends BaseController {
    @Autowired
    IInboundHeaderService inboundHeaderService;
    @Autowired
    IInboundDetailService inboundDetailService;

    @RequestMapping(value = "/find")
    public PageResult<InboundHeaderTEntity> find(@RequestBody String req) {
        List<InboundHeaderTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = inboundHeaderService.find(pageRequest);
            if(CollectionUtils.isEmpty(list)){
                return page(Lists.newArrayList());
            }
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/save")
    public AjaxResult<InboundVO> save(@RequestBody String req) {
        final String PRO_OPERATORTYPE = "operatorType";
        try {
            PageRequest pageRequest = pageRequest(req);
            String operatorType = pageRequest.getString(PRO_OPERATORTYPE);
            AjaxRequest<InboundVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<InboundVO>>() {});
            InboundVO inboundVO = request.getData();
            if(null == inboundVO){
                throw new BusinessServiceException("no update data.");
            }
            switch (operatorType){
                case "add":
                    inboundVO.setOperatorType(OperatorTypeEnum.Add);
                    break;
                case "modify":
                    inboundVO.setOperatorType(OperatorTypeEnum.Modify);
                    break;
                case "submit":
                    inboundVO.setOperatorType(OperatorTypeEnum.Submit);
                    break;
                case "review":
                    inboundVO.setOperatorType(OperatorTypeEnum.Review);
                    break;
                case "confirm":
                    inboundVO.setOperatorType(OperatorTypeEnum.Confirm);
                    break;
            }
            inboundHeaderService.saveFromOms(request);

            return success(inboundVO);
        } catch (Exception e) {
            return fail(e.getMessage());
        }
    }
    @RequestMapping(value = "/findDetail")
    public PageResult<InboundDetailVO> findDetail(@RequestBody String req) {
        List<InboundDetailVO> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = inboundDetailService.find(pageRequest);
            if(CollectionUtils.isEmpty(list)){
                return page(Lists.newArrayList());
            }
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/createPutaway")
    public AjaxResult<InboundVO> createPutaway(@RequestBody String req) {
        try {
            AjaxRequest<List<InboundVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InboundVO>>>() {});
            inboundHeaderService.createPutaway(request);
            return success();
        } catch (Exception e) {
            return fail(e.getMessage());
        }
    }
}
