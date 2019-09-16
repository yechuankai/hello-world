package com.wms.pub.inner.outbound;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.WaveBuildDetailTEntity;
import com.wms.entity.auto.WaveBuildTEntity;
import com.wms.services.outbound.IWaveBuildService;
import com.wms.vo.outbound.WaveBuildVO;

@RestController
@RequestMapping("/services/inner/outbound/waveTemplate")
public class WaveBuildRest extends BaseController{
	@Autowired
	private IWaveBuildService waveBuildService;
	
	@RequestMapping(value = "/find")
	public PageResult<WaveBuildTEntity> find(@RequestBody String req) {
		List<WaveBuildTEntity> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = waveBuildService.find(pageRequest);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	@RequestMapping(value = "/findDetail")
	public AjaxResult<List<WaveBuildDetailTEntity>> findDetail(@RequestBody String req) {
		AjaxRequest<WaveBuildVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<WaveBuildVO>>() {});
		List<WaveBuildDetailTEntity> list = null;
		try {
			list = waveBuildService.findDetail(WaveBuildDetailTEntity.builder()
												.warehouseId(request.getWarehouseId())
												.companyId(request.getCompanyId())
												.waveBuildId(request.getData().getWaveBuildId())
												.build());
			return success(list);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
	}
	
	@RequestMapping("/add")
	public AjaxResult add(@RequestBody String req) {
		AjaxRequest<WaveBuildVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<WaveBuildVO>>() {});
		try {
			WaveBuildVO data = request.getData();
			if (null == data) {
	            return fail("no record add.");
	        }
			Boolean save = waveBuildService.add(request);
			if (save) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
        return fail();
	}
	
	@RequestMapping("/save")
	public AjaxResult save(@RequestBody String req) {
		AjaxRequest<WaveBuildVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<WaveBuildVO>>() {});
		try {
			WaveBuildVO data = request.getData();
			if (null == data) {
	            return fail("no record cancle.");
	        }
			Boolean save = waveBuildService.modify(request);
			if (save) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
        return fail();
	}
	
	@RequestMapping("/rowSave")
	public AjaxResult rowSave(@RequestBody String req) {
		AjaxRequest<List<WaveBuildVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<WaveBuildVO>>>() {});
		try {
			if (CollectionUtils.isEmpty(request.getData())) {
	            return fail("no record save.");
	        }
			Boolean save = waveBuildService.rowModify(request);
			if (save) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
        return fail();
	}
	
	@RequestMapping("/delete")
	public AjaxResult delete(@RequestBody String req) {
		AjaxRequest<List<WaveBuildVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<WaveBuildVO>>>() {});
			try {
			if (CollectionUtils.isEmpty(request.getData())) {
	            return fail("no record delete.");
	        }
			Boolean save = waveBuildService.delete(request);
			if (save) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
        return fail();
	}
	
	/**
	 * 通过波次模板查询出库单
	 * @param str
	 * @return
	 */
	@RequestMapping("/findOutbound")
	public AjaxResult<List<OutboundHeaderTEntity>> findOutboundsByWaveTemplate(@RequestBody String req){
		AjaxRequest<WaveBuildVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<WaveBuildVO>>() {});
		List<OutboundHeaderTEntity> list = null;
		try {
			list = waveBuildService.findOutbounds(request);
			return success(list);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
}
