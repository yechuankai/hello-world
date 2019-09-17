package com.wms.pub.inner.outbound;

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
import com.wms.common.utils.MessageUtils;
import com.wms.entity.auto.WaveTEntity;
import com.wms.services.outbound.IWaveService;
import com.wms.vo.WaveVO;

/**
 * 波次操作
 * @author yechuankai.chnet
 *
 */
@RestController
@RequestMapping("/services/inner/outbound/wave")
public class WaveRest extends BaseController{
	@Autowired
	private IWaveService waveService;
	
	@RequestMapping(value = "/find")
	public PageResult<WaveTEntity> find(@RequestBody String req) {
		List<WaveTEntity> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = waveService.find(pageRequest);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	@RequestMapping("/add")
	public AjaxResult add(@RequestBody String req) {
		AjaxRequest<WaveVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<WaveVO>>() {});
		try {
			WaveVO wave = request.getData();
			Boolean save = waveService.createWave(request);
			if (save) {
				String message = "billnumber.show";
				message = MessageUtils.message(message, wave.getWaveNumber());
				return success(message);
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
        return fail();
	}
	
	@RequestMapping("/save")
	public AjaxResult save(@RequestBody String req) {
		AjaxRequest<List<WaveTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<WaveTEntity>>>() {});
		try {
			List<WaveTEntity> data = request.getData();
			if (CollectionUtils.isEmpty(data)) {
	            return fail("no record save.");
	        }
			Boolean save = waveService.modify(request);
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
		AjaxRequest<List<WaveTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<WaveTEntity>>>() {});
			try {
			if (CollectionUtils.isEmpty(request.getData())) {
	            return fail("no record delete.");
	        }
			Boolean save = waveService.delete(request);
			if (save) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
        return fail();
	}
	
	@RequestMapping(value = "/allocate")
    public AjaxResult allocate(@RequestBody String req) {
    	try {
			AjaxRequest<List<WaveVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<WaveVO>>>() {});
			waveService.allocate(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
    }
    
    @RequestMapping(value = "/unallocate")
    public AjaxResult unallocate(@RequestBody String req) {
    	try {
			AjaxRequest<List<WaveVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<WaveVO>>>() {});
			waveService.unAllocate(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
    }
	
	@RequestMapping(value = "/pick")
    public AjaxResult pick(@RequestBody String req) {
    	try {
			AjaxRequest<List<WaveVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<WaveVO>>>() {});
			waveService.pick(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
    }
    
    @RequestMapping(value = "/unpick")
    public AjaxResult unpick(@RequestBody String req) {
    	try {
			AjaxRequest<List<WaveVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<WaveVO>>>() {});
			waveService.unPick(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
    }
    
    @RequestMapping(value = "/ship")
    public AjaxResult ship(@RequestBody String req) {
    	try {
			AjaxRequest<List<WaveVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<WaveVO>>>() {});
			waveService.ship(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
    }
}
