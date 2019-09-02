package com.wms.pub.inner.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * 库存多维度查询
 * @author liuxiangying.chnet
 */

import com.github.pagehelper.PageHelper;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.PageResult;
import com.wms.services.inventory.IInventoryMultiService;
import com.wms.vo.InventoryMultiCountVo;
@RestController
@RequestMapping("/services/inventory/multi")
public class InventoryMultiRest extends BaseController{
	@Autowired
	private IInventoryMultiService inventoryMultiService;
	
	@RequestMapping("container")
	@ResponseBody
	public PageResult<InventoryMultiCountVo> selectByContainer(@RequestBody String req){
		PageRequest request = pageRequest(req);
		PageHelper.startPage(request.getPageStart(), request.getPageSize());
		List<InventoryMultiCountVo> list = inventoryMultiService.selectByContainer(request);
		return page(list);
	}
	@RequestMapping("lpn")
	public PageResult<InventoryMultiCountVo> selectByLPN(@RequestBody String req){
		PageRequest request = pageRequest(req);
		PageHelper.startPage(request.getPageStart(), request.getPageSize());
		List<InventoryMultiCountVo> list = inventoryMultiService.selectByLPN(request);
		return page(list);
	}
	@RequestMapping("location")
	public PageResult<InventoryMultiCountVo> selectByLocation(@RequestBody String req){
		PageRequest request = pageRequest(req);
		PageHelper.startPage(request.getPageStart(), request.getPageSize());
		List<InventoryMultiCountVo> list = inventoryMultiService.selectByLocation(request);
		return page(list);
	}
	@RequestMapping("sku")
	public PageResult<InventoryMultiCountVo> selectBySku(@RequestBody String req){
		PageRequest request = pageRequest(req);
		PageHelper.startPage(request.getPageStart(), request.getPageSize());
		List<InventoryMultiCountVo> list = inventoryMultiService.selectBySku(request);
		return page(list);
	}
}
