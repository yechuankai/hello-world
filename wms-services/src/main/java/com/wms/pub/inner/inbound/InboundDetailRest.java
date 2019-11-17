package com.wms.pub.inner.inbound;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.enums.OperatorTypeEnum;
import com.wms.common.enums.TransactionCategoryEnum;
import com.wms.entity.auto.InboundDetailTEntity;
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.services.inbound.IInboundDetailService;
import com.wms.services.inbound.IInboundHeaderService;
import com.wms.vo.inbound.InboundDetailVO;
import com.wms.vo.inbound.InboundVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/services/inner/inboundDetail")
public class InboundDetailRest extends BaseController{
	
	@Autowired
	IInboundDetailService inboundDetailService;
	
	@Autowired
	IInboundHeaderService inboundHeaderService;
	
	@RequestMapping(value = "/findByHeader")
	public PageResult<InboundDetailVO> findByHeader(@RequestBody String req) {
		List<InboundDetailVO> list = null;
		try {
			PageRequest pageRequest = pageRequest(req);
			Page page = PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());
			Long inboundHeaderId = pageRequest.getLong(InboundDetailTEntity.Column.inboundHeaderId.getJavaProperty());
			if (inboundHeaderId != null)
				list = inboundDetailService.find(pageRequest);
			
			if (CollectionUtils.isEmpty(list))
				list = Lists.newArrayList();
			
			return page(page, list);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/maxLineNumber")
	public AjaxResult<Long> maxLineNumber(@RequestBody String req) {
		try {
			AjaxRequest<InboundDetailTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<InboundDetailTEntity>>() {});
			request.getData().setWarehouseId(request.getWarehouseId());
			request.getData().setCompanyId(request.getCompanyId());
			long maxLine = inboundDetailService.findMaxLine(request.getData());
			return success(maxLine);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}

	@RequestMapping(value = "/save")
	public AjaxResult<InboundVO> save(@RequestBody String req) {
		try {
			AjaxRequest<InboundVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<InboundVO>>() {});
			InboundVO inboundVO = request.getData();
			
			inboundVO.setOperatorType(OperatorTypeEnum.Add);
			inboundVO.setWarehouseId(request.getWarehouseId());
			inboundVO.setCompanyId(request.getCompanyId());
			if (CollectionUtils.isNotEmpty(inboundVO.getDetail())) {
				request.getData().getDetail().forEach(d->{
					// ready to receive
					d.setTransactionCategory(TransactionCategoryEnum.PCReceive.getCode());
				});
			}
			if (inboundVO.getInboundHeaderId() == null) {
				inboundHeaderService.save(request);
				//再次查询
				inboundVO = inboundHeaderService.find(inboundVO);
			}else {
				inboundDetailService.save(request);
				inboundHeaderService.inboundStatus(inboundVO, Boolean.TRUE);
			}
			
			return success(inboundVO);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/receiveAll")
	public AjaxResult<InboundVO> receiveAll(@RequestBody String req) {
		try {
			AjaxRequest<List<InboundDetailVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InboundDetailVO>>>() {});
			inboundDetailService.receiveAll(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}

	@RequestMapping(value = "/unReceive")
	public AjaxResult<InboundVO> unReceive(@RequestBody String req) {
		try {
			AjaxRequest<List<InboundDetailVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InboundDetailVO>>>() {});
			inboundDetailService.unReceive(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/listSave")
	public AjaxResult<InboundVO> listSave(@RequestBody String req) {
		try {
			AjaxRequest<List<InboundDetailVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InboundDetailVO>>>() {});
			List<InboundDetailVO> inboundDetailVO = request.getData();
			if (CollectionUtils.isEmpty(inboundDetailVO)) 
				return fail("no record update.");
			
			InboundDetailVO detail = inboundDetailVO.get(0);
			InboundHeaderTEntity inboundHeader = inboundHeaderService.find(InboundHeaderTEntity.builder()
																			.warehouseId(request.getWarehouseId())
																			.companyId(request.getCompanyId())
																			.inboundHeaderId(detail.getInboundHeaderId())
																			.build());
			InboundVO inboundVO = new InboundVO(inboundHeader);
			inboundVO.setOperatorType(OperatorTypeEnum.Modify);
			inboundDetailVO.forEach(d->{
				// ready to receive
				d.setTransactionCategory(TransactionCategoryEnum.PCReceive.getCode());
			});
			inboundVO.setDetail(inboundDetailVO);
			
			inboundDetailService.save(ajaxRequest(inboundVO, request));
			inboundHeaderService.inboundStatus(inboundVO, Boolean.TRUE);
			
			return success(inboundVO);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}

	@RequestMapping(value = "/delete")
	public AjaxResult delete(@RequestBody String req) {
		try {
			AjaxRequest<List<InboundDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InboundDetailTEntity>>>() {});
			List<InboundDetailTEntity> updateList = request.getData();
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record delete.");
			}
			
			boolean flag = inboundDetailService.delete(request);
			if (!flag) {
				return fail();
			}
			
			Set<Long> headerIds = updateList.stream().map(InboundDetailTEntity::getInboundHeaderId).collect(Collectors.toSet());
			
			headerIds.forEach(d -> {
				InboundHeaderTEntity header = InboundHeaderTEntity.builder()
						.warehouseId(request.getWarehouseId())
						.companyId(request.getCompanyId())
						.inboundHeaderId(d)
						.build();
				inboundHeaderService.inboundStatus(header, Boolean.TRUE);
				return;
			});
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
}
