package com.wms.pub.inner.outbound;

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
import com.wms.entity.auto.OutboundDetailTEntity;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.services.outbound.IOutboundDetailService;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.vo.outbound.OutboundDetailVO;
import com.wms.vo.outbound.OutboundVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-12 09:17
 **/
@RestController
@RequestMapping("/services/inner/outboundDetail")
public class OutboundDetailRest extends BaseController {

    @Autowired
    IOutboundDetailService outboundDetailService;
    @Autowired
    IOutboundHeaderService outboundHeaderService;

    @RequestMapping(value = "/findByHeader")
    public PageResult<OutboundDetailVO> find(@RequestBody String req) {
        List<OutboundDetailVO> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            Page<Object> page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            Long outboundHeaderId = pageRequest.getLong(OutboundDetailTEntity.Column.outboundHeaderId.getJavaProperty());
            if(null != outboundHeaderId){
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

    @RequestMapping(value = "/save")
    public AjaxResult<OutboundVO> save(@RequestBody String req) {
        try {
            AjaxRequest<OutboundVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<OutboundVO>>() {});
            OutboundVO outboundVO = request.getData();

            outboundVO.setOperatorType(OperatorTypeEnum.Add);
            outboundVO.setWarehouseId(request.getWarehouseId());
            outboundVO.setCompanyId(request.getCompanyId());
            if (outboundVO.getOutboundHeaderId() == null) {
                outboundHeaderService.save(request);
            }else {
                outboundDetailService.save(request);
                outboundHeaderService.outboundStatus(outboundVO, Boolean.TRUE);
            }
            return success(outboundVO);
        } catch (Exception e) {
            return fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/listSave")
    public AjaxResult<OutboundVO> listSave(@RequestBody String req) {
        try {
            AjaxRequest<List<OutboundDetailVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<OutboundDetailVO>>>() {});
            List<OutboundDetailVO> outboundDetailVO = request.getData();
            if (CollectionUtils.isEmpty(outboundDetailVO)) {
                return fail("no record update.");
            }
            OutboundDetailVO detail = outboundDetailVO.get(0);
            OutboundHeaderTEntity outboundHeader = outboundHeaderService.find(OutboundHeaderTEntity.builder()
                    .warehouseId(request.getWarehouseId())
                    .companyId(request.getCompanyId())
                    .outboundHeaderId(detail.getOutboundHeaderId())
                    .build());
            OutboundVO outboundVO = new OutboundVO(outboundHeader);
            outboundVO.setOperatorType(OperatorTypeEnum.Modify);
            outboundVO.setDetail(outboundDetailVO);

            outboundDetailService.save(ajaxRequest(outboundVO, request));

            return success(outboundVO);
        } catch (Exception e) {
            return fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/delete")
    public AjaxResult delete(@RequestBody String req) {
        try {
            AjaxRequest<List<OutboundDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<OutboundDetailTEntity>>>() {});
            List<OutboundDetailTEntity> updateList = request.getData();
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record delete.");
            }

            boolean flag = outboundDetailService.delete(request);
            if (!flag) {
                return fail();
            }

            Set<Long> headerIds = updateList.stream().map(OutboundDetailTEntity::getOutboundHeaderId).collect(Collectors.toSet());

            headerIds.forEach(d -> {
                OutboundHeaderTEntity header = OutboundHeaderTEntity.builder()
                        .warehouseId(request.getWarehouseId())
                        .companyId(request.getCompanyId())
                        .outboundHeaderId(d)
                        .build();
                outboundHeaderService.outboundStatus(header, Boolean.TRUE);
                return;
            });
            return success();
        } catch (Exception e) {
            return fail(e.getMessage());
        }
    }
}
