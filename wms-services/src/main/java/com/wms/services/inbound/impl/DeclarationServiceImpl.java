package com.wms.services.inbound.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.services.IExcelService;
import com.wms.common.enums.ExcelTemplateEnum;
import com.wms.common.enums.InboundStatusEnum;
import com.wms.common.enums.TransactionCategoryEnum;
import com.wms.common.enums.TransactionTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.DateUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.dao.auto.IInboundDetailTDao;
import com.wms.dao.auto.IInboundHeaderTDao;
import com.wms.dao.example.InboundDetailTExample;
import com.wms.dao.example.InboundHeaderTExample;
import com.wms.entity.auto.InboundDetailTEntity;
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.LotAttributeTEntity;
import com.wms.services.core.IInventoryCoreService;
import com.wms.services.inbound.IDeclarationService;
import com.wms.services.inbound.IInboundDetailService;
import com.wms.services.inbound.IInboundHeaderService;
import com.wms.services.inventory.IInventoryService;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.InventoryTranDetailVO;
import com.wms.vo.InventoryTranVO;
import com.wms.vo.excel.CustomsClearanceImportVO;

@Service
public class DeclarationServiceImpl implements IDeclarationService, IExcelService<CustomsClearanceImportVO> {

	private static Logger log = LoggerFactory.getLogger(InboundDetailServiceImpl.class);
	
	@Autowired
	private IInboundHeaderTDao inboundHeaderDao;
	@Autowired
	private IInboundDetailTDao inboundDetailDao;
	@Autowired
	private IInboundHeaderService inboundHeaderService;
	@Autowired
	private IInboundDetailService inboundDetailService;
	@Autowired
	private IInventoryService inventoryService;
	@Autowired
	private IInventoryCoreService inventoryCoreService;
	
	@Override
	public List<InboundHeaderTEntity> findByDeclareNumber(InboundHeaderTEntity inbound) {
		InboundHeaderTExample example = new InboundHeaderTExample();
		example.createCriteria()
		.andReferenceNumberEqualTo(inbound.getReferenceNumber())
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andStatusNotEqualTo(InboundStatusEnum.Cancel.getCode());
		
		List<InboundHeaderTEntity> list = inboundHeaderDao.selectByExample(example);
		
		return list;
	}
	
	
	@Override
	public ExcelTemplate getExcelTemplate() {
		return new ExcelTemplate<CustomsClearanceImportVO>(ExcelTemplateEnum.CustomsClearance.getCode(), CustomsClearanceImportVO.class);
	}
	
	@Override
	public void importData(AjaxRequest<List<CustomsClearanceImportVO>> request) throws BusinessServiceException {
		List<CustomsClearanceImportVO> list = request.getData();
		if (CollectionUtils.isEmpty(list))
			throw new BusinessServiceException("no data.");
		
		list.forEach(d -> {
			try {
				SpringUtils.getBean(IDeclarationService.class).modifyCustomsClearance(new AjaxRequest<CustomsClearanceImportVO>(d, request));
			} catch (Exception e) {
				String message = e.getMessage();
				log.error(message, e);
				d.setSuccess(YesNoEnum.No.getCode());
				d.setProcessMessage(message);
				return;
			}
		});
	}


	@Transactional
	@Override
	public Boolean modifyCustomsClearance(AjaxRequest<CustomsClearanceImportVO> request) {
		CustomsClearanceImportVO d = request.getData();
		
		//报关单号不能为空
		if (StringUtils.isEmpty(d.getDeclareNumber())) {
			throw new BusinessServiceException("declareNumber is null");
		}
		
		List<InboundHeaderTEntity> inboundList = Lists.newArrayList();
		//查找入库单号
		if (StringUtils.isNotEmpty(d.getInboundNumber())) {
			InboundHeaderTEntity header = inboundHeaderService.find(InboundHeaderTEntity.builder()
												.warehouseId(request.getWarehouseId())
												.companyId(request.getCompanyId())
												.inboundNumber(d.getInboundNumber())
												.build());	
			inboundList.add(header);
		}else if (StringUtils.isNotEmpty(d.getSourceBillNumber())) {
			inboundList = inboundHeaderService.findBySourceNumber(InboundHeaderTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.sourceNumber(d.getSourceBillNumber())
					.build());	
		}else {
			throw new BusinessServiceException("inboundNumber is null, sourceBillNumber is null.");
		}
		
		//按订单开始更新报关单号
		for (InboundHeaderTEntity header : inboundList) {
			
			//存储新批属性对应关系
			Map<String, String> newLotNumberMap = Maps.newHashMap();
			
			InventoryOnhandVO onhandObj = new InventoryOnhandVO();
			onhandObj.setCompanyId(request.getCompanyId());
			onhandObj.setWarehouseId(request.getWarehouseId());
			onhandObj.setLotAttribute10(header.getInboundNumber());
			//查找库存
			List<InventoryOnhandVO> onhandList = inventoryService.findByLotAttribute(onhandObj);
			if (CollectionUtils.isEmpty(onhandList)) {
				log.info("no inventory.");
			}else {
				//记录原始批属性号
				List<String> oldLotNumber = Lists.newArrayList();
				
				//开始转换批属性
				InventoryTranVO tran = new InventoryTranVO();
				tran.setUserName(request.getUserName());
				tran.setCompanyId(request.getCompanyId());
				tran.setWarehouseId(request.getWarehouseId());
				tran.setTransactionType(TransactionTypeEnum.Adjustment.getCode());
				tran.setSouceBillNumber(header.getInboundNumber());
				
				onhandList.forEach(i -> {
					InventoryTranDetailVO tranDetail = new InventoryTranDetailVO();
					
					//设置目标库存
					BeanUtils.copyBeanProp(tranDetail, i);
					tranDetail.setSourceNumber(String.valueOf(header.getInboundHeaderId()));
					tranDetail.setTransactionCategory(TransactionCategoryEnum.ImportCustomsClearance.getCode());
					tranDetail.setTranQuantity(i.getQuantityOnhand());
					//请空批次信息
					tranDetail.setLotId(null);
					tranDetail.setLotNumber(null);
					//写入目标批次信息，报关单号写入到批属性2
					LotAttributeTEntity lot = new LotAttributeTEntity();
					BeanUtils.copyBeanProp(lot, i);
					lot.setLotAttribute1(header.getSourceNumber());
					lot.setLotAttribute2(d.getDeclareNumber());
					tranDetail.setLotAttribute(lot);
					
					//设置来源扣减库存
					InventoryOnhandTEntity onhand = new InventoryOnhandTEntity();
					BeanUtils.copyBeanProp(onhand, tranDetail);
					tranDetail.setSourceInventoryOnhand(onhand);
					
					oldLotNumber.add(i.getLotNumber());
					tran.getDetail().add(tranDetail);
				});
				
				if (CollectionUtils.isNotEmpty(tran.getDetail())) {
					//转移所有库存
					inventoryCoreService.transfer(tran);
					
					int size = tran.getDetail().size();
					for (int i = 0; i < size; i ++) {
						String oldLot = oldLotNumber.get(i);
						String newLot = tran.getDetail().get(i).getLotNumber();
						newLotNumberMap.put(oldLot, newLot);
					}
				}
			}
			
			//更新表头
			header.setSourceNumber(d.getSourceBillNumber());
			updateInboundHeader(new AjaxRequest<InboundHeaderTEntity>(header, request));
			
			//查询入库单所有明细
			List<InboundDetailTEntity> detail = inboundDetailService.findByHeaderId(InboundDetailTEntity.builder()
												.warehouseId(request.getWarehouseId())
												.companyId(request.getCompanyId())
												.inboundHeaderId(header.getInboundHeaderId())
												.build());
			//更新所有明细
			if (CollectionUtils.isNotEmpty(detail)) {
				detail.forEach(id ->{
					String newLotNumber = newLotNumberMap.get(id.getLotNumber());
					if (StringUtils.isNotEmpty(newLotNumber)) {
						id.setLotNumber(newLotNumber);
					}
					id.setLotAttribute1(header.getSourceNumber());
					id.setLotAttribute2(d.getDeclareNumber());
				});
				updateInboundDetail(new AjaxRequest<List<InboundDetailTEntity>>(detail, request));
			}
		}
		return Boolean.TRUE;
	}
	
	private Boolean updateInboundHeader(AjaxRequest<InboundHeaderTEntity> request) {
		InboundHeaderTEntity header = request.getData();
		header.setCompanyId(request.getCompanyId());
		header.setWarehouseId(request.getWarehouseId());
		InboundHeaderTEntity selectHeader = inboundHeaderService.find(header);
		InboundHeaderTEntity updateHeader = InboundHeaderTEntity.builder()
												.sourceNumber(header.getSourceNumber())
												.referenceNumber(header.getReferenceNumber())
												.updateBy(request.getUserName())
												.updateTime(DateUtils.getNowDate())
												.build();
		InboundHeaderTExample example = new InboundHeaderTExample();
		example.createCriteria()
		.andCompanyIdEqualTo(request.getCompanyId())
		.andWarehouseIdEqualTo(request.getWarehouseId())
		.andInboundHeaderIdEqualTo(selectHeader.getInboundHeaderId());
		
		int rowcount = inboundHeaderDao.updateWithVersionByExampleSelective(selectHeader.getUpdateVersion(), updateHeader, example);
		if (rowcount == 0)
			throw new BusinessServiceException("no data update.");
		
		return Boolean.TRUE;
	}
	
	private Boolean updateInboundDetail(AjaxRequest<List<InboundDetailTEntity>> request) {
		request.getData().forEach(d -> {
			d.setCompanyId(request.getCompanyId());
			d.setWarehouseId(request.getWarehouseId());
			InboundDetailTEntity selectDetail = inboundDetailService.find(d);
			InboundDetailTEntity updateDetail = InboundDetailTEntity.builder()
													.lotAttribute1(d.getLotAttribute1())
													.lotAttribute2(d.getLotAttribute2())
													.lotNumber(d.getLotNumber())
													.updateBy(request.getUserName())
													.updateTime(DateUtils.getNowDate())
													.build();
			InboundDetailTExample example = new InboundDetailTExample();
			example.createCriteria()
			.andCompanyIdEqualTo(request.getCompanyId())
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andInboundDetailIdEqualTo(selectDetail.getInboundDetailId());
			
			int rowcount = inboundDetailDao.updateWithVersionByExampleSelective(selectDetail.getUpdateVersion(), updateDetail, example);
			if (rowcount == 0)
				throw new BusinessServiceException("no data update.");
		});
		return Boolean.TRUE;
	}

}
