package com.wms.pub.inner.inventory;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.services.inventory.IInventoryLockService;
import com.wms.services.inventory.IInventoryService;
import com.wms.vo.InventoryLockedVO;
import com.wms.vo.InventoryOnhandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/services/inner/inventory/onhand")
public class InventoryRest extends BaseController {
	@Autowired
	IInventoryService inventoryService;

	@Autowired
	private IInventoryLockService lockService;
	
	@RequestMapping(value = "/find")
	public PageResult<InventoryOnhandVO> find(@RequestBody String req) {
		try {
			PageRequest pageRequest = pageRequest(req);
			PageResult<InventoryOnhandVO> page = inventoryService.find(pageRequest);
			return page;
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/move")
    public AjaxResult move(@RequestBody String req) {
        try {
            AjaxRequest<List<InventoryOnhandVO>> inventoryList = ajaxRequest(req, new TypeReference<AjaxRequest<List<InventoryOnhandVO>>>() {});
            boolean flag = inventoryService.move(inventoryList);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

	@RequestMapping(value = "/lock")
	public AjaxResult lock(@RequestBody String req) {
		try {
			AjaxRequest<List<InventoryLockedVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InventoryLockedVO>>>() {});
			boolean flag = lockService.lock(request);
			if (!flag)
				return fail();
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}

	@RequestMapping(value = "/findLocked")
	public PageResult<InventoryLockedVO> findLocked(@RequestBody String req) {
		List<InventoryLockedVO> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			Page page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = lockService.find(pageRequest);
			if (list == null)
				list = Lists.newArrayList();
			
			return page(page, list);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
	}

	@RequestMapping(value = "/unLock")
	public AjaxResult unLock(@RequestBody String req) {
		try {
			AjaxRequest<List<InventoryLockedVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InventoryLockedVO>>>() {});
			boolean flag = lockService.unLock(request);
			if (!flag)
				return fail();
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
}
