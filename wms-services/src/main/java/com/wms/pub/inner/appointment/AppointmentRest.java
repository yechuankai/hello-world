package com.wms.pub.inner.appointment;

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
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.enums.AppointmentStatusEnum;
import com.wms.common.enums.AppointmentTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.utils.StringUtils;
import com.wms.dao.auto.IInboundCancelDetailTDao;
import com.wms.entity.auto.AppointmentTEntity;
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.services.appointment.IAppointmentService;
import com.wms.services.appointment.impl.AppointmentServiceImpl;
import com.wms.services.inbound.IInboundHeaderService;
import com.wms.services.inbound.impl.InboundHeaderServiceImpl;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.vo.AppointmentVO;

/**
 * 预约
 * @author yechuankai.chnet
 *
 */
@RestController
@RequestMapping("/services/inner/appointment")
public class AppointmentRest extends BaseController {

    @Autowired
    private IAppointmentService appointmentService;
    @Autowired
    private IInboundHeaderService inboundHeaderService;
    @Autowired
    private IOutboundHeaderService outBoundHeaderService;

    @RequestMapping(value = "/find")
    public PageResult<AppointmentTEntity> find(@RequestBody String req) {
        List<AppointmentTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = appointmentService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }
    
    @RequestMapping(value = "/findOrder")
    public PageResult<AppointmentVO> findOrder(@RequestBody String req) {
        List<AppointmentVO> list = Lists.newArrayList();
        try {
            PageRequest pageRequest = pageRequest(req);
            Page page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            pageRequest.put(AppointmentServiceImpl.APPOINTMENT_AVAILABLE, YesNoEnum.Yes.getCode());
            
            String type = pageRequest.getString(AppointmentTEntity.Column.type.getJavaProperty());
            pageRequest.remove(AppointmentTEntity.Column.type.getJavaProperty());
            if (AppointmentTypeEnum.Inbound.getCode().equals(type)) {
            	convertWordQ(pageRequest, InboundHeaderTEntity.Column.inboundNumber.getJavaProperty());
            	List<InboundHeaderTEntity> inboundList = inboundHeaderService.find(pageRequest);
            	if (CollectionUtils.isNotEmpty(inboundList)) {
            		inboundList.forEach(i -> {
            			AppointmentVO vo = new AppointmentVO();
            			vo.setSourceBillNumber(i.getInboundNumber());
            			vo.setSourceBillNumber2(i.getSourceNumber());
            			vo.setExpectedDate(i.getExpectedInboundDate());
            			vo.setStatus(i.getStatus());
            			vo.setOwnerCode(i.getOwnerCode());
            			list.add(vo);
            		});
            	}
            }else if (AppointmentTypeEnum.Outbound.getCode().equals(type)){
            	convertWordQ(pageRequest, OutboundHeaderTEntity.Column.outboundNumber.getJavaProperty());
            	List<OutboundHeaderTEntity> outboundList = outBoundHeaderService.find(pageRequest);
            	if (CollectionUtils.isNotEmpty(outboundList)) {
            		outboundList.forEach(o -> {
            			AppointmentVO vo = new AppointmentVO();
            			vo.setSourceBillNumber(o.getOutboundNumber());
            			vo.setSourceBillNumber2(o.getSourceNumber());
            			vo.setExpectedDate(o.getExpectedOutboundDate());
            			vo.setStatus(o.getStatus());
            			vo.setOwnerCode(o.getOwnerCode());
            			list.add(vo);
            		});
            	}
            }
            return page(page, list);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
       
    }

    @RequestMapping(value = "/add")
    public AjaxResult add(@RequestBody String req) {
        try {
            AjaxRequest<AppointmentTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<AppointmentTEntity>>() {});
            List<AppointmentTEntity> updateList = Lists.newArrayList(request.getData());
            boolean flag = appointmentService.add(ajaxRequest(updateList, request));
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
            AjaxRequest<List<AppointmentTEntity> > request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AppointmentTEntity> >>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = appointmentService.modify(request);
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
        	AjaxRequest<List<AppointmentTEntity> > request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AppointmentTEntity> >>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = appointmentService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
    
    @RequestMapping(value = "/cancel")
    public AjaxResult cancel(@RequestBody String req) {
        try {
        	AjaxRequest<List<AppointmentTEntity> > request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AppointmentTEntity> >>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = appointmentService.cancel(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
    
    @RequestMapping(value = "/arrival")
    public AjaxResult arrival(@RequestBody String req) {
        try {
        	AjaxRequest<List<AppointmentTEntity> > request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AppointmentTEntity> >>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            request.getData().forEach(a -> {
            	a.setStatus(AppointmentStatusEnum.Arrived.getCode());
            });
            boolean flag = appointmentService.modify(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
    
    @RequestMapping(value = "/leave")
    public AjaxResult leave(@RequestBody String req) {
        try {
        	AjaxRequest<List<AppointmentTEntity> > request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AppointmentTEntity> >>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            request.getData().forEach(a -> {
            	a.setStatus(AppointmentStatusEnum.Leave.getCode());
            });
            boolean flag = appointmentService.modify(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

}
