package com.wms.pub.inner.outbound;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.enums.OperatorTypeEnum;
import com.wms.common.utils.MessageUtils;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.vo.outbound.OutboundVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services/inner/outbound")
public class OutboundRest extends BaseController{
	@Autowired
    IOutboundHeaderService outboundService;

    @RequestMapping(value = "/allocate")
    public AjaxResult<OutboundVO> allocate(@RequestBody String req) {
    	try {
			AjaxRequest<List<OutboundVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<OutboundVO>>>() {});
			outboundService.allocate(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
    }
    
    @RequestMapping(value = "/unallocate")
    public AjaxResult<OutboundVO> unallocate(@RequestBody String req) {
    	try {
			AjaxRequest<List<OutboundVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<OutboundVO>>>() {});
			outboundService.unAllocate(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
    }

	@RequestMapping(value = "/find")
	public PageResult<OutboundHeaderTEntity> find(@RequestBody String req) {
		List<OutboundHeaderTEntity> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			list = outboundService.find(pageRequest);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}

	@RequestMapping(value = "/save")
	public AjaxResult<OutboundVO> save(@RequestBody String req) {
		try {
			AjaxRequest<OutboundVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<OutboundVO>>() {});
			OutboundVO outboundVO = request.getData();
			if (outboundVO.getOutboundHeaderId() == null){
				outboundVO.setOperatorType(OperatorTypeEnum.Add);
			}
			else {
				outboundVO.setOperatorType(OperatorTypeEnum.Modify);
			}
			outboundService.save(request);

			if (outboundVO.getOperatorType() == OperatorTypeEnum.Add) {
				String message = "billnumber.show";
				message = MessageUtils.message(message, outboundVO.getOutboundNumber());
				return success(outboundVO, message);
			}

			return success(outboundVO);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
    }

    @RequestMapping(value = "/pick")
    public AjaxResult<OutboundVO> pick(@RequestBody String req) {
    	try {
			AjaxRequest<List<OutboundVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<OutboundVO>>>() {});
			outboundService.pick(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
    }
    
    @RequestMapping(value = "/unpick")
    public AjaxResult<OutboundVO> unpick(@RequestBody String req) {
    	try {
			AjaxRequest<List<OutboundVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<OutboundVO>>>() {});
			outboundService.unPick(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
    }
    
    @RequestMapping(value = "/ship")
    public AjaxResult<OutboundVO> ship(@RequestBody String req) {
    	try {
			AjaxRequest<List<OutboundVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<OutboundVO>>>() {});
			outboundService.ship(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
    }

    @RequestMapping(value = "/delete")
    public AjaxResult delete(@RequestBody String req) {
        try {
            AjaxRequest<List<OutboundHeaderTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<OutboundHeaderTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record delete.");
            }
            boolean flag = outboundService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

    @RequestMapping(value = "/release")
    public AjaxResult<OutboundVO> release(@RequestBody String req) {
        try {
            AjaxRequest<List<OutboundVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<OutboundVO>>>() {});
            outboundService.release(request);
            return success();
        } catch (Exception e) {
            return fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/cancel")
    public AjaxResult cancel(@RequestBody String req) {
        try {
            AjaxRequest<List<OutboundHeaderTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<OutboundHeaderTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record cancle.");
            }
            boolean flag = outboundService.cancel(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
}
