package com.wms.pub.inner.outbound;

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
import com.wms.entity.auto.WaveDetailTEntity;
import com.wms.services.outbound.IWaveDetailService;
import com.wms.vo.WaveDetailVO;

/**
 * 波次操作
 * @author yechuankai.chnet
 *
 */
@RestController
@RequestMapping("/services/inner/outbound/wavedetail")
public class WaveDetailRest extends BaseController{
	@Autowired
	private IWaveDetailService waveDetailService;
	
	@RequestMapping(value = "/find")
	public PageResult<WaveDetailVO> find(@RequestBody String req) {
		List<WaveDetailVO> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
            Page<Object> page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
            Long waveId = pageRequest.getLong(WaveDetailTEntity.Column.waveId.getJavaProperty());
            if(null != waveId){
                list = waveDetailService.find(pageRequest);
            }

            if (CollectionUtils.isEmpty(list)) {
                list = Lists.newArrayList();
            }
            return page(page, list);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
	}
	
	@RequestMapping("/add")
	public AjaxResult add(@RequestBody String req) {
		AjaxRequest<List<WaveDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<WaveDetailTEntity>>>() {});
		try {
			Boolean save = waveDetailService.add(request);
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
		AjaxRequest<List<WaveDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<WaveDetailTEntity>>>() {});
			try {
			if (CollectionUtils.isEmpty(request.getData())) {
	            return fail("no record delete.");
	        }
			Boolean save = waveDetailService.delete(request);
			if (save) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
        return fail();
	}
	
}
