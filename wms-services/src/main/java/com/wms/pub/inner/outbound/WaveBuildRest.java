package com.wms.pub.inner.outbound;

import java.util.ArrayList;
import java.util.List;

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
import com.wms.entity.auto.WaveBuildTEntity;
import com.wms.services.outbound.IWaveBuildService;
import com.wms.vo.outbound.WaveBuildVo;

@RestController
@RequestMapping("/services/inner/waveTemplate")
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
	
	@RequestMapping("/save")
	public AjaxResult<?> save(@RequestBody String req) {
		AjaxRequest<WaveBuildVo> request = ajaxRequest(req, new TypeReference<AjaxRequest<WaveBuildVo>>() {});
        WaveBuildVo data = request.getData();
		if (null == data) {
            return fail("no record cancle.");
        }
		Boolean save = waveBuildService.save(request);
		if (save) {
			return success();
		}
        return fail();
	}
	
	/**
	 * 通过波次模板查询出库单
	 * @param str
	 * @return
	 */
	@RequestMapping("/findByTemplate")
	public List<OutboundHeaderTEntity> findOutboundsByWaveTemplate(@RequestBody String req){
		List<OutboundHeaderTEntity> list = null;
		try {
			AjaxRequest request = ajaxRequest(req);
			list = waveBuildService.findOutbounds(request);
		} catch (Exception e) {
			return new ArrayList<OutboundHeaderTEntity>();
		}
		return list;
	}
}
