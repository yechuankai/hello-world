package com.wms.pub.inner.inbound;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.enums.OperatorTypeEnum;
import com.wms.common.utils.MessageUtils;
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.services.inbound.IInboundHeaderService;
import com.wms.vo.inbound.InboundVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/services/inner/inbound")
public class InboundRest extends BaseController{
	
	@Autowired
	IInboundHeaderService inboundHeaderService;
	
	@RequestMapping(value = "/find")
	public PageResult<InboundHeaderTEntity> find(@RequestBody String req) {
		List<InboundHeaderTEntity> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = inboundHeaderService.find(pageRequest);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	
	@RequestMapping(value = "/save")
	public AjaxResult<InboundVO> save(@RequestBody String req) {
		try {
			AjaxRequest<InboundVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<InboundVO>>() {});
			InboundVO inboundVO = request.getData();
			if (inboundVO.getInboundHeaderId() == null)
				inboundVO.setOperatorType(OperatorTypeEnum.Add);
			else
				inboundVO.setOperatorType(OperatorTypeEnum.Modify);
			
			inboundHeaderService.save(request);
			
			if (inboundVO.getOperatorType() == OperatorTypeEnum.Add) {
				String message = "billnumber.show";
				message = MessageUtils.message(message, inboundVO.getInboundNumber());
				return success(inboundVO, message);
			}
			
			return success(inboundVO);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}


	@RequestMapping(value = "/receiveAll")
	public AjaxResult<InboundVO> receiveAll(@RequestBody String req) {
		try {
			AjaxRequest<List<InboundVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InboundVO>>>() {});
			inboundHeaderService.receiveAll(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}

    @RequestMapping(value = "/unReceive")
    public AjaxResult<InboundVO> unReceive(@RequestBody String req) {
        try {
            AjaxRequest<List<InboundVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InboundVO>>>() {});
            inboundHeaderService.unReceive(request);
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

	@RequestMapping(value = "/cancel")
	public AjaxResult cancel(@RequestBody String req) {
		try {
			AjaxRequest<List<InboundHeaderTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InboundHeaderTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record cancle.");
			}
			boolean flag = inboundHeaderService.cancel(request);
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}

    @RequestMapping(value = "/close")
    public AjaxResult close(@RequestBody String req) {
        try {
            AjaxRequest<List<InboundHeaderTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InboundHeaderTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record close.");
            }
            boolean flag = inboundHeaderService.close(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
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

}
