package com.wms.pub.rest.outbound;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wms.common.constants.TableNameConstants;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.enums.OperatorTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.cache.LocaleUtils;
import com.wms.entity.auto.OutboundDetailTEntity;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.StatusHistoryTEntity;
import com.wms.services.base.IEnterpriseService;
import com.wms.services.outbound.IOutboundDetailService;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.services.sys.IStatusHistoryService;
import com.wms.vo.inventory.EntInventoryOnhandVO;
import com.wms.vo.outbound.OutboundDetailVO;
import com.wms.vo.outbound.OutboundVO;

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
    
    @Autowired
    IStatusHistoryService statusService;

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
            Page page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = enterpriseService.findInventoryOnhand(pageRequest);
            if(CollectionUtils.isEmpty(list)){
                return page(Lists.newArrayList());
            }
            return page(page, list);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
       
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
    
    @RequestMapping(value = "/delete")
    public AjaxResult delete(@RequestBody String req) {
        try {
            AjaxRequest<List<OutboundHeaderTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<OutboundHeaderTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record delete.");
            }
            boolean flag = outboundHeaderService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
    
    @RequestMapping(value = "/statusList")
    public AjaxResult<List<StatusHistoryTEntity>> statusList(@RequestBody String req) {
        try {
            AjaxRequest<StatusHistoryTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<StatusHistoryTEntity>>() {});
            if (request.getData() == null) {
                return fail("no record find.");
            }
            
            List<StatusHistoryTEntity> list = statusService.findBySourceNumber(StatusHistoryTEntity.builder()
            									.warehouseId(request.getWarehouseId())
            									.companyId(request.getCompanyId())
            									.sourceNumber(request.getData().getSourceNumber())
            									.build());
            if (CollectionUtils.isEmpty(list)) 
            	return success();
            
            //转换状态
            list.forEach(d -> {
            	//根据国际化进行转换
    			StringBuilder localeSb = new StringBuilder();
    			localeSb.append(TableNameConstants.CODELKUP);
    			localeSb.append(LocaleUtils.CONTACT);
    			localeSb.append("OMSOUTBOUNDSTATUS");
    			localeSb.append(LocaleUtils.CONTACT);
    			localeSb.append(d.getNewStatus());
    			String descr = LocaleUtils.getLocaleLabel(localeSb.toString());
    			if (StringUtils.isEmpty(descr))
    				descr = d.getNewStatus();
    			
    			d.setDescription(descr);
            });
            return success(list);
        } catch (Exception e) {
            return fail(e.getMessage());
        }
    }
}
