package com.wms.pub.rest.inbound;

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
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.entity.auto.StatusHistoryTEntity;
import com.wms.services.inbound.IInboundDetailService;
import com.wms.services.inbound.IInboundHeaderService;
import com.wms.services.sys.IStatusHistoryService;
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
    @Autowired
    IStatusHistoryService statusService;

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
            list.forEach(l -> {
            	//如果来源单据号为空，则为内部创建，内部创建来源单号默认为入库单号
            	if (StringUtils.isEmpty(l.getSourceNumber())) {
            		l.setSourceNumber(l.getInboundNumber());
            	}
            });
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
            OperatorTypeEnum operatorEnum = OperatorTypeEnum.get(operatorType);
            inboundVO.setOperatorType(operatorEnum);
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
            Page page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = inboundDetailService.find(pageRequest);
            if(CollectionUtils.isEmpty(list)){
                return page(Lists.newArrayList());
            }
            return page(page, list);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
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

    @RequestMapping(value = "/delete")
    public AjaxResult delete(@RequestBody String req) {
        try {
            AjaxRequest<List<InboundHeaderTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InboundHeaderTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record delete.");
            }
            boolean flag = inboundHeaderService.delete(request);
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
    			localeSb.append("OMSINBOUNDSTATUS");
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
