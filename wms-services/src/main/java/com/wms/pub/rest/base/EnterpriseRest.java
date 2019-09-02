package com.wms.pub.rest.base;

import com.github.pagehelper.PageHelper;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.*;
import com.wms.services.base.IEnterpriseService;
import com.wms.services.sys.IWarehouseActiveService;
import com.wms.vo.WarehouseActiveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 企业服务Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-21 13:50
 **/
@RestController()
@RequestMapping("/services/public/enterprise")
public class EnterpriseRest extends BaseController {
    @Autowired
    IEnterpriseService enterpriseService;

    @Autowired
    IWarehouseActiveService warehouseService;

    @RequestMapping(value = "/findSku")
    public PageResult<EntSkuTEntity> findSku(@RequestBody String req) {
        List<EntSkuTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = enterpriseService.findSku(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/findPack")
    public PageResult<EntPackTEntity> findPack(@RequestBody String req) {
        List<EntPackTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = enterpriseService.findPack(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/findSupplier")
    public PageResult<EntSupplierTEntity> findSupplier(@RequestBody String req) {
        List<EntSupplierTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = enterpriseService.findSupplier(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/findOwner")
    public PageResult<EntOwnerTEntity> findOwner(@RequestBody String req) {
        List<EntOwnerTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = enterpriseService.findOwner(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/findCustomer")
    public PageResult<EntCustomerTEntity> findCustomer(@RequestBody String req) {
        List<EntCustomerTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = enterpriseService.findCustomer(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/findCarrier")
    public PageResult<EntCarrierTEntity> findCarrier(@RequestBody String req) {
        List<EntCarrierTEntity> list = null;
        try {
            PageRequest pageRequest = pageRequest(req);
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            list = enterpriseService.findCarrier(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/findWarehouses")
    public PageResult<WarehouseActiveVO> findWarehouses(@RequestBody String req) {
        List<WarehouseActiveVO> list = null;
        try {
            PageRequest request = pageRequest(req);
            PageHelper.startPage(request.getPageStart(), request.getPageSize());
            list = warehouseService.find(request);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }
}
