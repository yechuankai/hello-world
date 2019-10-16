package com.wms.pub.rest.inbound;

import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.wms.common.constants.ConfigConstants;
import com.wms.common.constants.DefaultConstants;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.enums.InboundStatusEnum;
import com.wms.common.enums.TransactionCategoryEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.cache.ConfigUtils;
import com.wms.entity.auto.LotValidateTEntity;
import com.wms.entity.auto.PackTEntity;
import com.wms.entity.auto.SkuTEntity;
import com.wms.services.base.ILotValidateService;
import com.wms.services.base.IPackService;
import com.wms.services.base.ISkuService;
import com.wms.services.inbound.IInboundDetailService;
import com.wms.services.inbound.IInboundHeaderService;
import com.wms.vo.LotLabelVO;
import com.wms.vo.inbound.InboundDetailVO;
import com.wms.vo.inbound.InboundVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services/public/inbound")
public class InboundReceiveRest extends BaseController{
	
	@Autowired
	IInboundHeaderService inboundHeaderService;
	@Autowired
	IInboundDetailService inboundDetailService;
	@Autowired
	IPackService packService;
	@Autowired
	ISkuService skuService;
	@Autowired
	ILotValidateService lotvService;

	@RequestMapping("getInbound")
	public AjaxResult<InboundVO> getInbound(@RequestBody String req){
		AjaxRequest<InboundVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<InboundVO>>() {});
		try {
			InboundVO inbound = request.getData();
			inbound.setWarehouseId(request.getWarehouseId());
			inbound.setCompanyId(request.getCompanyId());
			InboundVO header = inboundHeaderService.find(inbound);
			if (InboundStatusEnum.Closed.getCode().equals(header.getStatus())
					|| InboundStatusEnum.Cancel.getCode().equals(header.getStatus()))
				throw new BusinessServiceException("InboundRest", "inbound.status.not.process" , new Object[] {header.getInboundNumber()});
			return success(header);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	@RequestMapping("getInboundDetail")
	public AjaxResult<InboundDetailVO> getInboundDetail(@RequestBody String req){
		AjaxRequest<InboundDetailVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<InboundDetailVO>>() {});
		try {
			InboundDetailVO detail = request.getData();
			detail.setWarehouseId(request.getWarehouseId());
			detail.setCompanyId(request.getCompanyId());
			detail.setSkuCode(detail.getSkuCode().toUpperCase());
			
			//验证货品扫描条码
			SkuTEntity sku = null;
			try {
				//查询货品 按条码查询
				sku = skuService.find(SkuTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).barcode(detail.getSkuCode()).ownerCode(detail.getOwnerCode()).build());
				detail.setSkuCode(sku.getSkuCode());
			} catch (BusinessServiceException e) {
				sku = skuService.find(SkuTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).ownerCode(detail.getOwnerCode()).skuCode(detail.getSkuCode()).build());
				detail.setSkuCode(sku.getSkuCode());
			}
			
			List<InboundDetailVO> list = inboundDetailService.findExpected(detail);
			if (CollectionUtils.isEmpty(list)) {
				//是否开启RF无SKU收货功能
				if (!ConfigUtils.getBooleanValue(request.getCompanyId(), request.getWarehouseId(), ConfigConstants.CONFIG_RF_INBOUND_RECEIVE_NO_SKU)) {
					throw new BusinessServiceException("InboundRest", "inbound.not.expected" , new Object[] {request.getData().getSkuCode()});
				}else {
					list = Lists.newArrayList();
					InboundDetailVO vo = new InboundDetailVO();
					BeanUtils.copyBeanProp(vo, sku, Boolean.FALSE);
					vo.setInboundHeaderId(detail.getInboundHeaderId());
					vo.setNoSku(YesNoEnum.Yes.getCode());
					vo.setLocationCode(DefaultConstants.RECEIVE_LOCATION);
					list.add(vo);
				}
					
			}
			//一次只获取一行
			InboundDetailVO one = list.get(0);
			
			//设置货品
			one.setSku(sku);
			
			//设置包装
			PackTEntity pack = packService.find(PackTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).packId(one.getPackId()).build());
			one.setPack(pack);
			
			//设置批属性显示值
			LotLabelVO lotLabel = skuService.getSkuLotLabel(sku);
			one.setLotLabel(lotLabel);
			
			//设置批属性验证值
			LotValidateTEntity lotv = lotvService.find(LotValidateTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).lotValidateId(sku.getLotValidateId()).build());
			one.setLotv(lotv);
			
			return success(one);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/receive")
	public AjaxResult<InboundVO> receive(@RequestBody String req) {
		try {
			AjaxRequest<List<InboundDetailVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<InboundDetailVO>>>() {});
			if (CollectionUtils.isNotEmpty(request.getData())) {
				request.getData().forEach(d->{
					// ready to receive
					d.setTransactionCategory(TransactionCategoryEnum.RFReceive.getCode());
				});
			}
			inboundDetailService.receive(request);
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
}
