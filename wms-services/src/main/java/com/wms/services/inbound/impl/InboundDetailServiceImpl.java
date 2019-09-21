package com.wms.services.inbound.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wms.common.constants.ConfigConstants;
import com.wms.common.constants.DefaultConstants;
import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.services.IExcelService;
import com.wms.common.enums.*;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.DateUtils;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.MessageUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.cache.ConfigUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IInboundDetailTDao;
import com.wms.dao.example.InboundDetailTExample;
import com.wms.entity.auto.*;
import com.wms.services.base.*;
import com.wms.services.core.IInventoryCoreService;
import com.wms.services.core.IPutawayCoreService;
import com.wms.services.inbound.IInboundDetailService;
import com.wms.services.inbound.IInboundHeaderService;
import com.wms.services.inventory.*;
import com.wms.services.sys.IStatusHistoryService;
import com.wms.vo.InventoryTranDetailVO;
import com.wms.vo.InventoryTranVO;
import com.wms.vo.PackVO;
import com.wms.vo.PutawayVO;
import com.wms.vo.adjustment.AdjustmentDetailVO;
import com.wms.vo.adjustment.AdjustmentVO;
import com.wms.vo.excel.InboundDetailImportVO;
import com.wms.vo.inbound.InboundDetailVO;
import com.wms.vo.inbound.InboundVO;
import com.wms.vo.outbound.OutboundDetailVO;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ROUND_FLOOR;

@Service
public class InboundDetailServiceImpl implements IInboundDetailService, IExcelService<InboundDetailImportVO> {

	private static Logger log = LoggerFactory.getLogger(InboundDetailServiceImpl.class);
	
	@Autowired
	private IInboundDetailTDao inboundDetailDao;
	
	@Autowired
	private IInboundHeaderService inboundHeaderService;
	
	@Autowired
	private ISkuService skuService;
	
	@Autowired
	private IPackService packService;
	
	@Autowired
	private ILocationService locationService;
	
	@Autowired
	private IOwnerService ownerService;
	
	@Autowired
	private IInventoryCoreService inventoryCoreService;
	
	@Autowired
	private ILotService lotService;
	
	@Autowired
	private IAdjustmentDetailService adjustmentDetailService;

	@Autowired
	IPutawayCoreService putawayCoreService;

	@Autowired
	private ITaskService taskService;

	@Autowired
	IPutawayStrategyHeaderService putawayStrategyHeaderService;

	@Autowired
	IStatusHistoryService statusHistoryService;

	@Autowired
	IEnterpriseService enterpriseService;


	@Override
	public List<InboundDetailVO> find(PageRequest request) throws BusinessServiceException {
		
		InboundDetailTExample TExample = new InboundDetailTExample();
		InboundDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(InboundDetailTEntity.Column.class, InboundDetailTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<InboundDetailTEntity> inboundDetailList = inboundDetailDao.selectByExample(TExample);
		
		if (CollectionUtils.isEmpty(inboundDetailList)) 
			return Lists.newArrayList();

		Set<Long> headerIds = inboundDetailList.stream().map(InboundDetailTEntity::getInboundHeaderId).collect(Collectors.toSet());
		List<InboundHeaderTEntity> headerList = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(headerIds)){
			InboundHeaderTEntity header = InboundHeaderTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.build();
			headerList = inboundHeaderService.find(header, headerIds);
		}

		Map<Long, InboundHeaderTEntity> headerIdMap = headerList.stream().collect(
			      Collectors.toMap(InboundHeaderTEntity::getInboundHeaderId, (s) -> s));

		List<InboundDetailVO> returnList = Lists.newArrayList();
		inboundDetailList.forEach(d -> {
			
			InboundDetailVO inboundDetailVO = new InboundDetailVO(d);
			
			InboundHeaderTEntity header = headerIdMap.get(d.getInboundHeaderId());
			if (header != null)
				inboundDetailVO.setInboundNumber(header.getInboundNumber());

			returnList.add(inboundDetailVO);

		});
		
		return returnList;
	}

	@Override
	public List<InboundDetailTEntity> findByHeaderId(InboundDetailTEntity inbound) throws BusinessServiceException {
		InboundDetailTExample TExample = new InboundDetailTExample();
		TExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(inbound.getWarehouseId())
		.andCompanyIdEqualTo(inbound.getCompanyId())
		.andInboundHeaderIdEqualTo(inbound.getInboundHeaderId());
		List<InboundDetailTEntity> inboundDetailList = inboundDetailDao.selectByExample(TExample);
		return inboundDetailList;
	}
	
	@Override
	public List<InboundDetailTEntity> findByHeaderId(Long headerId) throws BusinessServiceException {
		InboundDetailTExample TExample = new InboundDetailTExample();
		TExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andInboundHeaderIdEqualTo(headerId);
		List<InboundDetailTEntity> inboundDetailList = inboundDetailDao.selectByExample(TExample);
		return inboundDetailList;
	}

	@Override
	public List<InboundDetailTEntity> findByHeaderIds(InboundDetailTEntity inbound, Set<Long> ids) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(ids))
			return null;
		
		InboundDetailTExample TExample = new InboundDetailTExample();
		TExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(inbound.getWarehouseId())
		.andCompanyIdEqualTo(inbound.getCompanyId())
		.andInboundHeaderIdIn(Lists.newArrayList(ids));
		List<InboundDetailTEntity> inboundDetailList = inboundDetailDao.selectByExample(TExample);
		return inboundDetailList;
	}
	
	@Override
	public List<InboundDetailVO> findExpected(InboundDetailTEntity inbound) throws BusinessServiceException {
		InboundDetailTExample TExample = new InboundDetailTExample();
		InboundDetailTExample.Criteria criteria = TExample.createCriteria();
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(inbound.getWarehouseId())
		.andCompanyIdEqualTo(inbound.getCompanyId());
		
		int conditionCount = 0;
		
		if (inbound.getLineNumber() != null) {
			criteria.andLineNumberEqualTo(inbound.getLineNumber());
			conditionCount ++;
		}
		if (inbound.getInboundHeaderId() != null) {
			criteria.andInboundHeaderIdEqualTo(inbound.getInboundHeaderId());
			conditionCount ++;
		}
		if (inbound.getInboundDetailId() != null) {
			criteria.andInboundDetailIdEqualTo(inbound.getInboundDetailId());
			conditionCount ++;
		}
		if (inbound.getSkuId() != null) {
			criteria.andSkuIdEqualTo(inbound.getSkuId());
			conditionCount ++;
		}
		if (inbound.getSkuCode() != null) {
			criteria.andSkuCodeEqualTo(inbound.getSkuCode());
			conditionCount ++;
		}
		if (conditionCount == 0)
			return Lists.newArrayList();
		
		List<InboundDetailTEntity> inboundDetail = inboundDetailDao.selectByExample(TExample);
		if (CollectionUtils.isEmpty(inboundDetail))
			return Lists.newArrayList();
		
		List<InboundDetailVO> returnList = Lists.newArrayList(); 
		inboundDetail.forEach(d-> {
			if (InboundStatusEnum.Closed.getCode().equals(d.getStatus())
					|| InboundStatusEnum.Cancel.getCode().equals(d.getStatus())) 
				return;
			
			if (d.getQuantityReceive().compareTo(d.getQuantityExpected()) >= 0)
				return;
			
			InboundDetailVO vo = new InboundDetailVO(d);
			returnList.add(vo);
		});
		
		return returnList;
	}

	@Override
	public InboundDetailTEntity find(InboundDetailTEntity inbound) throws BusinessServiceException {
		InboundDetailTExample TExample = new InboundDetailTExample();
		InboundDetailTExample.Criteria criteria = TExample.createCriteria();
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCompanyIdEqualTo(inbound.getCompanyId());
		if(null != inbound.getWarehouseId()){
			criteria.andWarehouseIdEqualTo(inbound.getWarehouseId());
		}
		int conditionCount = 0;
		
		if (inbound.getLineNumber() != null) {
			criteria.andLineNumberEqualTo(inbound.getLineNumber());
			conditionCount ++;
		}
		if (inbound.getInboundHeaderId() != null) {
			criteria.andInboundHeaderIdEqualTo(inbound.getInboundHeaderId());
			conditionCount ++;
		}
		if (inbound.getInboundDetailId() != null) {
			criteria.andInboundDetailIdEqualTo(inbound.getInboundDetailId());
			conditionCount ++;
		}
		if (conditionCount == 0)
			return null;
		
		InboundDetailTEntity inboundDetail = inboundDetailDao.selectOneByExample(TExample);
		if (inboundDetail == null) 
			throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.line.not.exists" , new Object[] {inbound.getInboundDetailId()});
		
		return inboundDetail;
	}

	@Override
	@Transactional
	public Boolean add(InboundDetailVO inbound) throws BusinessServiceException {
		inbound.setInboundDetailId(KeyUtils.getUID());
		InboundDetailTEntity updateDetail = new InboundDetailTEntity();
		BeanUtils.copyBeanProp(updateDetail, inbound, Boolean.FALSE);
		updateDetail.setCreateBy(inbound.getCreateBy());
		updateDetail.setCreateTime(new Date());
		updateDetail.setUpdateBy(inbound.getUpdateBy());
		updateDetail.setUpdateTime(new Date());
		
		//验证超收
		validateExceed(null, inbound);

		int rowcount = inboundDetailDao.insertSelective(updateDetail);
		if (rowcount == 0)
			throw new BusinessServiceException("record add error.");
		
		//to do receive
		if (updateDetail.getQuantityReceive() != null 
				&& updateDetail.getQuantityReceive().compareTo(BigDecimal.ZERO) > 0) {
				inbound.setTranQuantity(updateDetail.getQuantityReceive());
		}
		return Boolean.TRUE;
	}
	
	
	private Boolean validate(InboundVO inbound, InboundDetailVO detail) {
		
		if (inbound.getInboundHeaderId() == null)
			throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.header.isnull" , new Object[] {inbound.getInboundNumber()});
		else 
			detail.setInboundHeaderId(inbound.getInboundHeaderId());
		
		if (detail.getLineNumber() == null)
			throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.line.isnull" , new Object[] {inbound.getInboundNumber()});
		
		if (inbound.getOwnerId() == null && StringUtils.isEmpty(inbound.getOwnerCode()))
			throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.owner.isnull" , new Object[] {inbound.getInboundNumber()});
			
		if (detail.getSkuId() == null && StringUtils.isEmpty(detail.getSkuCode()))
			throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.sku.isnull" , new Object[] {inbound.getInboundNumber(), detail.getLineNumber()}); 
		
		if (detail.getPackId() == null && StringUtils.isEmpty(detail.getPackCode()))
			throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.pack.isnull" , new Object[] {inbound.getInboundNumber(), detail.getLineNumber()}); 
		
		if (StringUtils.isEmpty(detail.getUom()))
			throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.uom.isnull" , new Object[] {inbound.getInboundNumber(), detail.getLineNumber()});
		
		if (StringUtils.isEmpty(detail.getLocationCode())) {
			throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.location.isnull" , new Object[] {inbound.getInboundNumber(), detail.getLineNumber()});
		} else {
			locationService.find(LocationTEntity.builder()
									.warehouseId(detail.getWarehouseId())
									.companyId(detail.getCompanyId())
									.locationCode(detail.getLocationCode())
									.build());
		}
		if(null != detail.getPutawayStrategyId() || StringUtils.isNotBlank(detail.getPutawayStrategyCode())){
			PutawayStrategyTEntity putawayStrategy = putawayStrategyHeaderService.find(PutawayStrategyTEntity.builder()
					.warehouseId(detail.getWarehouseId())
					.companyId(detail.getCompanyId())
					.putawayStrategyCode(detail.getPutawayStrategyCode())
					.putawayStrategyId(detail.getPutawayStrategyId())
					.build());
			detail.setPutawayStrategyId(putawayStrategy.getPutawayStrategyId());
			detail.setPutawayStrategyCode(putawayStrategy.getPutawayStrategyCode());
		}
		
		//验证毛重/净重
		if (detail.getWeightGross().compareTo(detail.getWeightNet()) < 0) 
			throw new BusinessServiceException("InboundDetailServiceImpl", "weight.gross.morethen.net" , new Object[] {detail.getLineNumber()}); 
		
		PackTEntity pack = packService.find(PackTEntity.builder()
				.warehouseId(detail.getWarehouseId())
				.companyId(detail.getCompanyId())
				.packId(detail.getPackId())
				.packCode(detail.getPackCode())
				.build());
		detail.setPackCode(pack.getPackCode());
		detail.setPackId(pack.getPackId());

		BigDecimal uomQuantity = packService.getUOMQty(pack, detail.getUom());
		if (uomQuantity.compareTo(BigDecimal.ZERO) == 0)
			throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.uom.error" , new Object[] {inbound.getInboundNumber(), detail.getLineNumber(), detail.getUom()});
		
		detail.setUomQuantity(uomQuantity);
		
		if (detail.getQuantityExpected() == null)
			detail.setQuantityExpected(BigDecimal.ZERO);

		if (detail.getUomQuantityExpected() != null && detail.getUomQuantityExpected().compareTo(BigDecimal.ZERO) > 0)
			detail.setQuantityExpected(detail.getUomQuantityExpected().multiply(uomQuantity));

		if (detail.getQuantityReceive() == null)
			detail.setQuantityReceive(BigDecimal.ZERO);
		
		if (detail.getUomQuantityReceive() != null && detail.getUomQuantityReceive().compareTo(BigDecimal.ZERO) > 0)
			detail.setQuantityReceive(detail.getUomQuantityReceive().multiply(uomQuantity));

		if (StringUtils.isNotEmpty(detail.getLpnNumber())) {
			detail.setLpnNumber(detail.getLpnNumber().toUpperCase());
		}
		
		
		//默认设置批属性10为入库单号、批属性11为入库日期
		if (BigDecimal.ZERO.compareTo(detail.getQuantityReceive()) < 0) {
			if (StringUtils.isEmpty(detail.getLotAttribute10()))
				detail.setLotAttribute10(inbound.getInboundNumber());
			if (detail.getLotAttribute11() == null)
				detail.setLotAttribute11(DateUtils.parseDate(DateUtils.getDate()));
		}
		
		if (inbound.getOperatorType() == OperatorTypeEnum.Modify) {
			return Boolean.TRUE;
		}
		
		OwnerTEntity owner = ownerService.find(OwnerTEntity.builder()
				.warehouseId(inbound.getWarehouseId())
				.companyId(inbound.getCompanyId())
				.ownerId(inbound.getOwnerId())
				.ownerCode(inbound.getOwnerCode())
				.build());
		detail.setOwnerCode(owner.getOwnerCode());
		detail.setOwnerId(owner.getOwnerId());

		SkuTEntity sku = skuService.find(SkuTEntity.builder()
				.warehouseId(detail.getWarehouseId())
				.companyId(detail.getCompanyId())
				.skuId(detail.getSkuId())
				.skuCode(detail.getSkuCode())
				.ownerCode(owner.getOwnerCode())
				.build());
		detail.setSkuId(sku.getSkuId());
		detail.setSkuAlias(sku.getSkuAlias());
		detail.setSkuCode(sku.getSkuCode());
		
		if (BigDecimal.ZERO.compareTo(detail.getQuantityExpected()) < 0) {
			PackVO packVo = packService.getPack(pack, sku, detail.getUom());
			BigDecimal uomQty = detail.getQuantityExpected().divide(detail.getUomQuantity(), 5, ROUND_FLOOR);
			//计算体积
			if (BigDecimal.ZERO.compareTo(detail.getVolume()) == 0) {
				detail.setVolume(packVo.getVolume().multiply(uomQty));
			}
			//计算重量
			if (BigDecimal.ZERO.compareTo(detail.getWeightGross()) == 0
					&& BigDecimal.ZERO.compareTo(detail.getWeightNet()) == 0
					 && BigDecimal.ZERO.compareTo(detail.getWeightTare()) == 0) {
				detail.setWeightGross(packVo.getWeightGross().multiply(uomQty));
				detail.setWeightNet(packVo.getWeightNet().multiply(uomQty));
				detail.setWeightTare(packVo.getWeightTare().multiply(uomQty));
			}
		}

		if (StringUtils.isNotEmpty(detail.getLpnNumber()))
			detail.setLpnNumber(detail.getLpnNumber().toUpperCase());
		
		if (StringUtils.isNotEmpty(detail.getContainerNumber()))
			detail.setContainerNumber(detail.getContainerNumber().toUpperCase());
		if(inbound.getOperatorType() == OperatorTypeEnum.Add){
			//只有新增才判断重复
			InboundDetailTEntity selectDetail = null;
			try {
				selectDetail = find(detail);
			} catch (BusinessServiceException e) {}
			if (selectDetail != null)
				throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.linenumber.exists" , new Object[] {inbound.getInboundNumber(), detail.getLineNumber()});
		}
		
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean save(AjaxRequest<InboundVO> request) throws BusinessServiceException {
		InboundVO inboundVO = request.getData();
		
		inboundVO.getDetail().forEach(d -> {
			d.setWarehouseId(request.getWarehouseId());
			d.setCompanyId(request.getCompanyId());
			d.setInboundHeaderId(inboundVO.getInboundHeaderId());
			d.setUpdateBy(request.getUserName());
			d.setUpdateTime(new Date());
			
			validate(inboundVO, d);
			switch (inboundVO.getOperatorType()) {
			case Add:
				d.setCreateBy(request.getUserName());
				d.setCreateTime(new Date());
				add(d);
				break;
			case Modify:
				modify(d);
				break;
			default:
				throw new BusinessServiceException("InboundDetailServiceImpl", "opertiontype.not.exists" , null ); 
			}
			
			//process status 
			inboundDetailStatus(d, Boolean.TRUE);
		});
		
		//to do receive
		inboundVO.setUpdateBy(request.getUserName());
		receive(inboundVO);
		
		return Boolean.TRUE;
	}
	
	@Override
	@Transactional
	public InboundStatusEnum inboundDetailStatus(InboundDetailTEntity detail, Boolean updateFlag) {
		InboundStatusEnum status = InboundStatusEnum.New;
		
		if (detail.getQuantityExpected().compareTo(BigDecimal.ZERO) == 0
				&& detail.getQuantityReceive().compareTo(BigDecimal.ZERO) == 0)
			status = InboundStatusEnum.New;
		else if (detail.getQuantityReceive().compareTo(detail.getQuantityExpected()) >= 0)
			status = InboundStatusEnum.Receive;
		else if (detail.getQuantityExpected().compareTo(detail.getQuantityReceive()) > 0 
				&& (detail.getQuantityReceive().compareTo(BigDecimal.ZERO) > 0))
			status = InboundStatusEnum.InReceive;
		if (!updateFlag)
			return status;
		
		InboundDetailTEntity selectDetail = find(InboundDetailTEntity.builder()
													.warehouseId(detail.getWarehouseId())
													.companyId(detail.getCompanyId())
													.inboundDetailId(detail.getInboundDetailId())
													.build());
		
		InboundDetailTEntity updateDetail = InboundDetailTEntity.builder()
												.status(status.getCode())
												.build();
		InboundDetailTExample example = new InboundDetailTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(detail.getWarehouseId())
		.andCompanyIdEqualTo(detail.getCompanyId())
		.andInboundDetailIdEqualTo(detail.getInboundDetailId());
		int rowcount = inboundDetailDao.updateWithVersionByExampleSelective(selectDetail.getUpdateVersion(), updateDetail, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");
		
		detail.setStatus(status.getCode());
		
		return status;
	}

	@Override
	public Long createPutaway(List<InboundDetailVO> detail) throws BusinessServiceException {
		long taskCount = 0;
		//分组合并
		List<InboundDetailVO> newList = getListGroupByLpn(detail);
		for (InboundDetailVO d : newList) {
			try {
				String lpnNumber = null;
				LpnTypeEnum lpnType = null;
				LocationTEntity fromLocation = locationService.find(LocationTEntity.builder()
						.companyId(d.getCompanyId())
						.warehouseId(d.getWarehouseId())
						.locationCode(d.getLocationCode())
						.build());

				LpnTEntity lpnTEntity = LpnTEntity.builder()
						.companyId(d.getCompanyId())
						.warehouseId(d.getWarehouseId())
						.build();

				if (StringUtils.isNotBlank((d.getContainerNumber()))) {
					lpnTEntity.setContainerNumber(d.getContainerNumber());
					lpnNumber = d.getContainerNumber();
					lpnType = LpnTypeEnum.Container;
				} else {
					lpnType = LpnTypeEnum.Carton;
					lpnTEntity.setLpnNumber(d.getLpnNumber());
					lpnNumber = d.getLpnNumber();
				}

				PutawayStrategyTEntity putawayStrategy = null;
				if (null != d.getPutawayStrategyId() || StringUtils.isNotBlank(d.getPutawayStrategyCode())) {
					//有上架策略则传入上架策略
					putawayStrategy = PutawayStrategyTEntity.builder()
							.companyId(d.getCompanyId())
							.warehouseId(d.getWarehouseId())
							.putawayStrategyCode(d.getPutawayStrategyCode())
							.putawayStrategyId(d.getPutawayStrategyId())
							.build();
				}
				PutawayVO putawayVO = putawayCoreService.lpnPutaway(lpnTEntity, putawayStrategy);

				LocationTEntity toLocation = locationService.find(LocationTEntity.builder()
						.companyId(d.getCompanyId())
						.warehouseId(d.getWarehouseId())
						.locationCode(putawayVO.getToLocationCode())
						.build());
				List<TaskDetailTEntity> details =taskService.find(TaskDetailTEntity.builder()
						.warehouseId(d.getWarehouseId())
						.companyId(d.getCompanyId())
						.skuId(d.getSkuId())
						.fromLpnNumber(lpnNumber)
						.build());
				//根据SkuId和Lpn或容器号查询有重复的上架任务就不生成
				if(CollectionUtils.isNotEmpty(details)){
					continue;
				}
				//生成上架任务
				TaskDetailTEntity taskdetail = TaskDetailTEntity.builder()
						.createBy(d.getUpdateBy())
						.createTime(new Date())
						.updateBy(d.getUpdateBy())
						.updateTime(new Date())
						.warehouseId(d.getWarehouseId())
						.companyId(d.getCompanyId())
						.taskType(TaskTypeEnum.Putaway.getCode())
						.sourceType(TaskTypeEnum.Putaway.getCode())
						.fromLpnType(lpnType.getCode())
						.ownerCode(d.getOwnerCode())
						.ownerId(d.getOwnerId())
						.skuId(d.getSkuId())
						.skuCode(d.getSkuCode())
						.uom(d.getUom())
						.packId(d.getPackId())
						.packCode(d.getPackCode())
						.lotNumber(d.getLotNumber())
						.quantity(d.getQuantityReceive())
						.fromLpnNumber(lpnNumber)
						.fromLocationCode(fromLocation.getLocationCode())
						.fromLocationLogical(fromLocation.getLocationLogical())
						.fromZoneCode(fromLocation.getZoneCode())
						.toLpnNumber(lpnNumber)
						.toLocationCode(putawayVO.getToLocationCode())
						.toLocationLogical(toLocation.getLocationLogical())
						.toZoneCode(toLocation.getZoneCode())
						.userName(d.getUpdateBy())
						.releaseTime(new Date())
						.sourceNumber(d.getInboundDetailId())
						.sourceLineNumber(d.getLineNumber())
						.sourceBillNumber(d.getInboundNumber())
						.build();
				taskService.add(taskdetail);
				taskCount ++;
			} catch (Exception e) {
				log.error(e.getMessage(),e);
				continue;
			}
		}
		return taskCount;
	}


	private Boolean delete(InboundDetailTEntity detail) throws BusinessServiceException {
		InboundDetailTEntity updateDetail = InboundDetailTEntity.builder()
				.updateBy(detail.getUpdateBy())
				.updateTime(new Date())
				.delFlag(YesNoEnum.Yes.getCode())
				.build();
		InboundDetailTExample example = new InboundDetailTExample();
		example.createCriteria()
				.andCompanyIdEqualTo(detail.getCompanyId())
				.andInboundDetailIdEqualTo(detail.getInboundDetailId());
		if(0L != detail.getWarehouseId()){
			InboundStatusEnum status = inboundDetailStatus(detail, Boolean.FALSE);
			if (InboundStatusEnum.New != status) {
				throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.line.status.not.process" , new Object[] {detail.getLineNumber()});
			}
			example.createCriteria().andWarehouseIdEqualTo(detail.getWarehouseId());
		}

		int rowcount = inboundDetailDao.updateWithVersionByExampleSelective(detail.getUpdateVersion(), updateDetail, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record delete error.");
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<InboundDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<InboundDetailTEntity> list = request.getData();
		
		list.forEach(d -> {
			InboundDetailTEntity detail = find(InboundDetailTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.inboundDetailId(d.getInboundDetailId())
					.build());
			delete(detail);
		});
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(InboundHeaderTEntity header) throws BusinessServiceException {
		List<InboundDetailTEntity> detailList = findByHeaderId(InboundDetailTEntity.builder()
														.warehouseId(header.getWarehouseId())
														.companyId(header.getCompanyId())
														.inboundHeaderId(header.getInboundHeaderId())
														.build());
		detailList.forEach(d -> {
			delete(d);
		});
		
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean modify(InboundDetailVO inbound) throws BusinessServiceException {
		InboundDetailTEntity detailTEntity=InboundDetailTEntity.builder()
				.companyId(inbound.getCompanyId())
				.inboundDetailId(inbound.getInboundDetailId())
				.build();
		InboundDetailTExample example = new InboundDetailTExample();
		InboundDetailTExample.Criteria criteria =example.createCriteria()
				.andCompanyIdEqualTo(inbound.getCompanyId())
				.andInboundDetailIdEqualTo(inbound.getInboundDetailId());

		if(null != inbound.getWarehouseId()&&OperatorTypeEnum.Confirm !=inbound.getOperatorType()){
			//确认状态不使用仓库id查询
			criteria.andWarehouseIdEqualTo(inbound.getWarehouseId());
			detailTEntity.setWarehouseId(inbound.getWarehouseId());
		}

		InboundDetailTEntity selectDetail = find(detailTEntity);
		notProcess(selectDetail);
		
		if (inbound.getQuantityReceive() != null 
				&& inbound.getQuantityReceive().compareTo(selectDetail.getQuantityReceive()) < 0)
			throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.line.receivequantity.not.lessen" 
					, new Object[] {selectDetail.getLineNumber(), selectDetail.getQuantityReceive(), inbound.getQuantityReceive()});
		validateExceed(selectDetail, inbound);
		
		InboundDetailTEntity updateDetail = new InboundDetailTEntity();
		BeanUtils.copyBeanProp(updateDetail, inbound, Boolean.FALSE);
		updateDetail.setUpdateBy(inbound.getUpdateBy());
		updateDetail.setUpdateTime(new Date());
		

		
		int rowcount = inboundDetailDao.updateWithVersionByExampleSelective(selectDetail.getUpdateVersion(), updateDetail, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");
		
		//to do receive
		if (updateDetail.getQuantityReceive() != null 
				&& updateDetail.getQuantityReceive().compareTo(selectDetail.getQuantityReceive()) > 0) {
			inbound.setTranQuantity(updateDetail.getQuantityReceive().subtract(selectDetail.getQuantityReceive()));
		}
		
		return Boolean.TRUE;
	}

	private InboundVO receive(InboundVO inboundVO) {
		List<InventoryTranDetailVO> inventoryDetail = Lists.newArrayList();
		inboundVO.getDetail().forEach(detail -> {
			if (detail.getTranQuantity() == null 
						|| detail.getTranQuantity().compareTo(BigDecimal.ZERO) == 0) {
				return;
			}
			InventoryTranDetailVO inventoryD = new InventoryTranDetailVO();
			BeanUtils.copyBeanProp(inventoryD, detail, Boolean.FALSE);
			
			LpnTEntity lpn = new LpnTEntity();
			BeanUtils.copyBeanProp(lpn, detail, Boolean.FALSE);
			inventoryD.setLpn(lpn);
			
			LotAttributeTEntity lot = new LotAttributeTEntity();
			BeanUtils.copyBeanProp(lot, detail, Boolean.FALSE);
			inventoryD.setLotAttribute(lot);
			
			inventoryD.setSourceLineNumber(String.valueOf(detail.getLineNumber()));
			inventoryD.setSequence(detail.getLineNumber()); //错误提示使用
			inventoryD.setSourceNumber(String.valueOf(detail.getInboundDetailId()));
			inventoryD.setInboundDetail(detail);
			inventoryDetail.add(inventoryD);
		});
		
		if (CollectionUtils.isEmpty(inventoryDetail)) {
			return inboundVO;
		}
		
		InventoryTranVO tran = new InventoryTranVO();
		tran.setTransationType(TransactionTypeEnum.Inbound.getCode());
		tran.setSouceBillNumber(inboundVO.getInboundNumber());
		tran.setCompanyId(inboundVO.getCompanyId());
		tran.setWarehouseId(inboundVO.getWarehouseId());
		tran.setUserName(inboundVO.getUpdateBy());
		tran.setDetail(inventoryDetail);
		
		inventoryCoreService.inbound(tran);
		
		return inboundVO;
	}
	
	//验证超收
	private Boolean validateExceed(InboundDetailTEntity oldDetail, InboundDetailTEntity newDetail) {
		if (ConfigUtils.getBooleanValue(newDetail.getCompanyId(), newDetail.getWarehouseId(),  ConfigConstants.CONFIG_INBOUND_RECEIVE_EXCEED))
			return Boolean.TRUE;
		
		BigDecimal quantityExpected = null;
		
		if (newDetail.getQuantityExpected() != null) 
			quantityExpected = newDetail.getQuantityExpected();
		else if(oldDetail != null)
			quantityExpected = oldDetail.getQuantityExpected();
			
		if (newDetail.getQuantityReceive() == null
				|| BigDecimal.ZERO.compareTo(newDetail.getQuantityReceive()) == 0)
			return Boolean.TRUE;
		
		//获取入库单下所有明细
		List<InboundDetailTEntity> detail = findByHeaderId(newDetail);
		BigDecimal sumExpected = quantityExpected;
		BigDecimal sumReceive = newDetail.getQuantityReceive();
		for (InboundDetailTEntity d : detail) {
			if (d.getInboundDetailId().equals(newDetail.getInboundDetailId()))
				continue;
			if (d.getSkuId().equals(newDetail.getSkuId())) {
				sumExpected = sumExpected.add(d.getQuantityExpected());
				sumReceive = sumReceive.add(d.getQuantityReceive());
			}
		}
			
		if (sumReceive.compareTo(sumExpected) > 0)
			throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.line.receivequantity.not.exceed" 
					, new Object[] {newDetail.getLineNumber()});
		
		return Boolean.TRUE;
	}
	
	@Override
	@Transactional
	public Boolean receive(List<InboundDetailVO> detail) throws BusinessServiceException {
		
		if (CollectionUtils.isEmpty(detail))
			throw new BusinessServiceException("no record receive.");
		
		Map<Long, List<InboundDetailVO>> inbounds = detail.stream().collect(Collectors.groupingBy(InboundDetailVO::getInboundHeaderId));
		
		inbounds.forEach((k, v) -> {
			if (CollectionUtils.isEmpty(v))
				return;
			
			Long companyId = v.get(0).getCompanyId();
			Long warehouseId = v.get(0).getWarehouseId();
			InboundHeaderTEntity header = inboundHeaderService.find(InboundHeaderTEntity.builder().warehouseId(warehouseId).companyId(companyId).inboundHeaderId(k).build());
			
			InboundVO headerVO = new InboundVO(header);
			List<InboundDetailVO> tranDetail = Lists.newArrayList();
			v.forEach(d -> {
				InboundDetailTEntity detailObj = find((InboundDetailTEntity)d);
				notProcess(detailObj); //验证状态
				if (detailObj.getQuantityExpected().compareTo(detailObj.getQuantityReceive()) > 0) {
					InboundDetailVO detailVO = new InboundDetailVO(detailObj);
					detailVO.setTransactionCategory(d.getTransactionCategory());

					detailVO.setTranQuantity(d.getQuantityReceive());

					detailVO.setQuantityReceive(detailObj.getQuantityReceive().add(detailVO.getTranQuantity()));

					tranDetail.add(detailVO);

					//更新入库单数量
					InboundDetailTEntity updateDetail  = InboundDetailTEntity.builder()
															.warehouseId(detailObj.getWarehouseId())
															.companyId(detailObj.getCompanyId())
															.inboundDetailId(detailObj.getInboundDetailId())
															.inboundHeaderId(detailObj.getInboundHeaderId())
															.updateBy(d.getUpdateBy())
															.updateTime(new Date())
															.quantityReceive(detailVO.getQuantityReceive())
															.build();
					modify(new InboundDetailVO(updateDetail));
				}
			});

			headerVO.setDetail(tranDetail);
			receive(headerVO);
			
			//更新单据明细状态
			headerVO.getDetail().forEach(d -> {
				inboundDetailStatus(d, Boolean.TRUE);
			});
			//更新表头状态
			inboundHeaderService.inboundStatus(headerVO, Boolean.TRUE);
		});
		return Boolean.TRUE;
	}
	
	@Override
	@Transactional
	public Boolean unReceive(AjaxRequest<List<InboundDetailVO>> request) throws BusinessServiceException {
		List<InboundDetailVO> detail = request.getData();
		if (CollectionUtils.isEmpty(detail))
			throw new BusinessServiceException("no record receive.");
		
		Map<Long, List<InboundDetailVO>> inbounds = detail.stream().collect(Collectors.groupingBy(InboundDetailVO::getInboundHeaderId));
		
		inbounds.forEach((k, v) -> {
			if (CollectionUtils.isEmpty(v))
				return;
			
			InboundHeaderTEntity header = inboundHeaderService.find(InboundHeaderTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).inboundHeaderId(k).build());
			InboundVO headerVO = new InboundVO(header);
			
			AdjustmentVO adjustment = new AdjustmentVO();
			adjustment.setSourceNumber(header.getInboundNumber());
			List<AdjustmentDetailVO> adjustmentDetail = Lists.newArrayList();
			List<InboundDetailTEntity> unReceiveDetail = Lists.newArrayList();
			long adjustmentLineNumber = 0;
			for (InboundDetailVO d : v) {
				InboundDetailTEntity detailObj = find((InboundDetailTEntity)d);
				notProcess(detailObj); //验证状态
				boolean flag = true;
				if (BigDecimal.ZERO.compareTo(detailObj.getQuantityReceive()) >= 0){
					flag= false;
				}
				if(flag){
					//行号累加
					adjustmentLineNumber += DefaultConstants.LINE_INCREMENT;

					AdjustmentDetailVO detailVO = new AdjustmentDetailVO();
					BeanUtils.copyBeanProp(detailVO, detailObj, Boolean.FALSE);
					detailVO.setLineNumber(adjustmentLineNumber);
					detailVO.setUpdateBy(request.getUserName());
					detailVO.setCreateBy(request.getUserName());
					detailVO.setSourceLineNumber(String.valueOf(d.getLineNumber()));
					detailVO.setSourceNumber(String.valueOf(d.getInboundDetailId()));
					detailVO.setQuantityAdjustment(BigDecimal.ZERO.subtract(d.getQuantityReceive()));
					detailVO.setReason(AdjustmentReasonEnum.UnReceive.getCode());
					detailVO.setTransactionCategory(AdjustmentReasonEnum.UnReceive.getCode());
					adjustmentDetail.add(detailVO);

					//更新入库单数量
					InboundDetailTEntity updateDetail  = InboundDetailTEntity.builder()
							.warehouseId(detailObj.getWarehouseId())
							.companyId(detailObj.getCompanyId())
							.updateBy(d.getUpdateBy())
							.updateTime(new Date())
							.lotNumber("")
							.quantityReceive(detailObj.getQuantityReceive().add(detailVO.getQuantityAdjustment()))
							.quantityCancel(detailObj.getQuantityCancel().add(detailVO.getQuantityAdjustment().abs()))
							.build();
					InboundDetailTExample example = new InboundDetailTExample();
					example.createCriteria()
							.andWarehouseIdEqualTo(request.getWarehouseId())
							.andCompanyIdEqualTo(request.getCompanyId())
							.andInboundDetailIdEqualTo(detailObj.getInboundDetailId());

					int rowcount = inboundDetailDao.updateWithVersionByExampleSelective(detailObj.getUpdateVersion(), updateDetail, example);
					if (rowcount == 0)
						throw new BusinessServiceException("record update error.");

					detailObj.setQuantityReceive(detailObj.getQuantityReceive().add(detailVO.getQuantityAdjustment()));
					unReceiveDetail.add(detailObj);
				}

				
			}

			adjustment.setDetail(adjustmentDetail);
			
			adjustmentDetailService.save(new AjaxRequest<AdjustmentVO>(adjustment, request));
			
			//更新单据明细状态
			unReceiveDetail.forEach(d -> {
				inboundDetailStatus(d, Boolean.TRUE);
			});
			//更新表头状态
			inboundHeaderService.inboundStatus(headerVO, Boolean.TRUE);
		});
		
		return Boolean.TRUE;
	}

	/**
	 * 传入的收货数量为当前收货数量
	 */
	@Override
	@Transactional
	public Boolean receive(AjaxRequest<List<InboundDetailVO>> request) throws BusinessServiceException {
		List<InboundDetailVO> list = request.getData();
		if (CollectionUtils.isEmpty(list))
			throw new BusinessServiceException("no data.");
		
		list.forEach(detail -> {

			if (detail.getQuantityReceive() == null || BigDecimal.ZERO.compareTo(detail.getQuantityReceive()) == 0)
				throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.receivequantity.not.zero" , null);

			detail.setWarehouseId(request.getWarehouseId());
			detail.setCompanyId(request.getCompanyId());
			detail.setCreateBy(request.getUserName());
			detail.setUpdateBy(request.getUserName());
	
			//判断数据相关栏位数据是否一致（包装、单位、库位、LPN、容器、批属性），不一致则需要新建收货行进行收货
			InboundDetailTEntity selectDetail = find(detail);
	
			boolean sameFlag = true;
			//无包装时按原行包装进行收货
			if (StringUtils.isEmpty(detail.getPackCode()))
				detail.setPackCode(selectDetail.getPackCode());
	
			//无单位时按最小单位进行收货
			if (StringUtils.isEmpty(detail.getUom())) {
				PackTEntity pack = packService.find(PackTEntity.builder()
														.warehouseId(request.getWarehouseId())
														.companyId(request.getCompanyId())
														.packCode(detail.getPackCode()).build());
				detail.setUom(pack.getUom());
				detail.setUomQuantity(pack.getQty());
			}
	
			if (!selectDetail.getPackCode().equals(detail.getPackCode())) //包装
				sameFlag = false;
			else if  (!selectDetail.getUom().equals(detail.getUom())) //单位
				sameFlag = false;
			else if  (!selectDetail.getLocationCode().equals(detail.getLocationCode())) //库位
				sameFlag = false;
			else if  ((selectDetail.getLpnNumber() == null && StringUtils.isNotEmpty(detail.getLpnNumber()))
						|| (selectDetail.getLpnNumber() != null && !selectDetail.getLpnNumber().equals(detail.getLpnNumber())) )  //LPN
				sameFlag = false;
			else if  ((selectDetail.getContainerNumber() == null && StringUtils.isNotEmpty(detail.getContainerNumber()))
					|| (selectDetail.getContainerNumber() != null && !selectDetail.getContainerNumber().equals(detail.getContainerNumber())) )  //容器
				sameFlag = false;
			else if  (!lotService.validateLotAttribute(detail, selectDetail))
				sameFlag = false;
	
			//所有信息都一致的情况下，直接按原行进行接收
			if (sameFlag) {
				receive(Lists.newArrayList(detail));
			}else {
				//获取最大行号
				List<InboundDetailTEntity> selectDetailList = findByHeaderId(selectDetail);
				long maxLineNumber = selectDetailList.stream().mapToLong(InboundDetailTEntity::getLineNumber).max().getAsLong();
				maxLineNumber = maxLineNumber + DefaultConstants.LINE_INCREMENT;
				//新增收货行
				detail.setParentLineNumber(detail.getLineNumber());
				detail.setLineNumber(maxLineNumber);
				detail.setQuantityExpected(BigDecimal.ZERO);
				detail.setUomQuantityExpected(BigDecimal.ZERO);
				
				InboundHeaderTEntity header = inboundHeaderService.find(InboundHeaderTEntity.builder().warehouseId(request.getWarehouseId())
														.companyId(request.getCompanyId())
														.inboundHeaderId(detail.getInboundHeaderId()).build());
				InboundVO inboundVo = new InboundVO(header);
				inboundVo.setOperatorType(OperatorTypeEnum.Add);
				inboundVo.setDetail(Lists.newArrayList(detail));
				//保存方法可自动收货
				save(new AjaxRequest<InboundVO>(inboundVo, request));
				
				//判断预收货行是否已全部收货完成
				selectDetailList.add(detail); //将当前收货行加入集合判断
				
				BigDecimal sumExpected = BigDecimal.ZERO;
				BigDecimal sumReceive = BigDecimal.ZERO;
				for (InboundDetailTEntity d : selectDetailList) {
					if (d.getLineNumber() == detail.getParentLineNumber()) {
						sumExpected = d.getQuantityExpected();
						sumReceive = sumReceive.add(d.getQuantityReceive());
						continue;
					}
					
					if (d.getParentLineNumber() == detail.getParentLineNumber())
						sumReceive = sumReceive.add(d.getQuantityReceive());
				}
				InboundStatusEnum status = InboundStatusEnum.InReceive;
				if (sumReceive.compareTo(sumExpected) >= 0)
					status = InboundStatusEnum.Receive;
				
				InboundDetailTEntity updateDetail = InboundDetailTEntity.builder()
														.warehouseId(request.getWarehouseId())
														.companyId(request.getCompanyId())
														.inboundDetailId(selectDetail.getInboundDetailId())
														.status(status.getCode())
														.updateBy(request.getUserName())
														.updateTime(new Date())
														.build();
				modify(new InboundDetailVO(updateDetail));
				
				inboundHeaderService.inboundStatus(header, Boolean.TRUE);
				
			}
		
		});
		
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean receiveAll(AjaxRequest<List<InboundDetailVO>> request) throws BusinessServiceException {
		
		List<InboundDetailVO> detail = request.getData();
		if (CollectionUtils.isEmpty(detail)) 
			throw new BusinessServiceException("no record update.");
		
		Iterator<InboundDetailVO> iterator = detail.iterator();
		while(iterator.hasNext()) {
			InboundDetailVO d = iterator.next();
			if (d.getQuantityExpected().compareTo(d.getQuantityReceive()) <= 0)
				iterator.remove();
			
			d.setWarehouseId(request.getWarehouseId());
			d.setCompanyId(request.getCompanyId());
			d.setUpdateBy(request.getUserName());
			d.setTransactionCategory(TransactionCategoryEnum.PCAllReceive.getCode());
			d.setQuantityReceive(d.getQuantityExpected().subtract(d.getQuantityReceive()));
		}
		
		receive(detail);
		return Boolean.TRUE;
	}

	@Override
    @Transactional
    public Boolean modify(InboundDetailTEntity detail) throws BusinessServiceException {

        InboundDetailTEntity updateDetail = InboundDetailTEntity.builder()
                .updateBy(detail.getUpdateBy())
                .updateTime(new Date())
                .status(detail.getStatus())
                .build();
        if (null != detail.getCancelDate()) {
            updateDetail.setCancelDate(detail.getCancelDate());
        }
        InboundDetailTExample example = new InboundDetailTExample();
        example.createCriteria()
                .andWarehouseIdEqualTo(detail.getWarehouseId())
                .andCompanyIdEqualTo(detail.getCompanyId())
                .andInboundDetailIdEqualTo(detail.getInboundDetailId());

        int rowcount = inboundDetailDao.updateWithVersionByExampleSelective(detail.getUpdateVersion(), updateDetail, example);
        if (rowcount == 0) {
            throw new BusinessServiceException("record update error.");
        }
        return Boolean.TRUE;
    }
	
	
	/**
	 * 处理明细 修改，删除
	 * @param type
	 * @param newDetail
	 * @param oriDetail
	 * @return
	 */
	private Boolean processOMSinboundDetail(OperatorTypeEnum type, List<InboundDetailVO> newDetail, List<InboundDetailTEntity> oriDetail) {
		//没有原始行，默认原类型
		if (CollectionUtils.isEmpty(oriDetail) && CollectionUtils.isNotEmpty(newDetail)) {
			newDetail.forEach(d -> {
				d.setOperatorType(type);
			});
		}
		//没有新行，将原始行都删除
		if (CollectionUtils.isEmpty(newDetail)) {
			oriDetail.forEach(d -> {
				InboundDetailVO delObj = new InboundDetailVO(d);
				delObj.setOperatorType(OperatorTypeEnum.Delete);
				newDetail.add(delObj);
			});
		} 
		//默认为修改
		newDetail.stream().forEach(add -> {
			add.setOperatorType(OperatorTypeEnum.Modify);
			if (add.getInboundHeaderId() != null) {
				add.setLineNumber(null); //清空行号数据
			}
		});
		
		//计算新增，明细ID为空则认为新增
		List<InboundDetailVO> addDetail = newDetail.stream().filter(v -> v.getInboundDetailId() == null).collect(Collectors.toList());
		if (CollectionUtils.isNotEmpty(addDetail)) {
			long maxLine = 0L;
			if (CollectionUtils.isNotEmpty(oriDetail)) {
				//获取最大行号
				InboundDetailTEntity max = oriDetail.stream().max(new Comparator<InboundDetailTEntity>() {
					@Override
					public int compare(InboundDetailTEntity o1, InboundDetailTEntity o2) {
						return o1.getLineNumber().compareTo(o2.getLineNumber());
					}
				}).get();
				
				if (max != null)
					maxLine = max.getLineNumber();
			}
			for (int i = 0; i < addDetail.size(); i++) {
				InboundDetailVO addObj = addDetail.get(i);
				addObj.setLineNumber((i + 1) * DefaultConstants.LINE_INCREMENT + maxLine);
				addObj.setOperatorType(OperatorTypeEnum.Add);
			}
		} 
		
		Set<Long> newIds = newDetail.stream().filter(v -> v.getInboundDetailId() != null).map(InboundDetailVO::getInboundDetailId).collect(Collectors.toSet());
		//计算删除
		oriDetail.forEach(od -> {
			if (!newIds.contains(od.getInboundDetailId())) {
				InboundDetailVO delObj = new InboundDetailVO(od);
				delObj.setOperatorType(OperatorTypeEnum.Delete);
				newDetail.add(delObj);
			}
		});
		
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean saveFromOms(AjaxRequest<InboundVO> request) throws BusinessServiceException {
		InboundVO inboundVO = request.getData();
		InboundVO inbound=inboundHeaderService.findById(inboundVO);
		
		inboundVO.getDetail().forEach(d -> {
			d.setOperatorType(inboundVO.getOperatorType());
		});
		
		if (inboundVO.getOperatorType() == OperatorTypeEnum.Add) {
			processOMSinboundDetail(OperatorTypeEnum.Add, inboundVO.getDetail(), Lists.newArrayList());
		}else if (inboundVO.getOperatorType() == OperatorTypeEnum.Modify ) {
			//1.获取所有明细
			List<InboundDetailTEntity> detail = findByHeaderId(inbound.getInboundHeaderId());
			processOMSinboundDetail(OperatorTypeEnum.Add, inboundVO.getDetail(), detail);
		}
		
			

		inboundVO.getDetail().forEach(d -> {
			d.setCompanyId(request.getCompanyId());
			d.setInboundHeaderId(inboundVO.getInboundHeaderId());
			d.setUpdateBy(request.getUserName());

			StatusHistoryTEntity statusHistory = StatusHistoryTEntity.builder()
					.companyId(request.getCompanyId())
					.createBy(request.getUserName())
					.updateBy(request.getUserName())
					.createTime(new Date())
					.updateTime(new Date())
					.operTime(new Date())
					.build();
			inboundVO.setOperatorType(d.getOperatorType());
			switch (inboundVO.getOperatorType()) {
				case Add:
					d.setCreateBy(request.getUserName());
					d.setCreateTime(new Date());
					d.setStatus(InboundStatusEnum.Draft.getCode());
					add(d);
					break;
                case Modify:
                    modify(d);
                    break;
                case Delete:
                    delete(d);
                    break;
				case Submit:
					d.setStatus(InboundStatusEnum.WaitingReview.getCode());
					if(null == d.getInboundDetailId() || 0L == d.getInboundDetailId()){//提交时新增
						d.setCreateBy(request.getUserName());
						d.setCreateTime(new Date());
						add(d);
					}else {
						modify(d);
					}
					statusHistory.setOldStatus(InboundStatusEnum.Draft.getCode());
					statusHistory.setNewStatus(InboundStatusEnum.WaitingReview.getCode());
					break;
				case Review:
					addPackOrSku(inbound,d);
					d.setStatus(InboundStatusEnum.WaitingDeclaration.getCode());
					//来源单号存到批属性1
					d.setLotAttribute1(inbound.getSourceNumber());
					modify(d);
					statusHistory.setOldStatus(InboundStatusEnum.WaitingReview.getCode());
					statusHistory.setNewStatus(InboundStatusEnum.WaitingDeclaration.getCode());
					break;
				case Confirm:
					final String STAGE = "STAGE";
					d.setStatus(InboundStatusEnum.New.getCode());
					d.setWarehouseId(inbound.getWarehouseId());
					//报关单号存到批属性2
					d.setLotAttribute2(inbound.getReferenceNumber());
					//默认库位STAGE
					d.setLocationCode(STAGE);
					d.setOperatorType(inboundVO.getOperatorType());
					validate(inbound,d);
					modify(d);
					statusHistory.setOldStatus(InboundStatusEnum.New.getCode());
					statusHistory.setNewStatus(InboundStatusEnum.WaitingDeclaration.getCode());
					break;
				default:
					throw new BusinessServiceException("InboundDetailServiceImpl", "opertiontype.not.exists" , null );
			}

			if(null != d.getInboundDetailId()){
				statusHistory.setSourceNumber(d.getInboundDetailId());
			}
			if(null != d.getLineNumber()){
				statusHistory.setSourceBillNumber(d.getLineNumber().toString());
			}
			if(null != statusHistory.getNewStatus()){
				//有更新状态就插入记录
				statusHistoryService.add(statusHistory);
			}
		});


		return Boolean.TRUE;
	}

	private Boolean notProcess(InboundDetailTEntity detail) {
		if (InboundStatusEnum.Closed.getCode().equals(detail.getStatus())
				|| InboundStatusEnum.Cancel.getCode().equals(detail.getStatus())) {
			throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.line.status.not.process" , new Object[] {detail.getLineNumber()});
		}
		return Boolean.TRUE;
	}
	
	
	@Override
	public ExcelTemplate<InboundDetailImportVO> getExcelTemplate() {
		return new ExcelTemplate<InboundDetailImportVO>(ExcelTemplateEnum.Inbound.getCode(), InboundDetailImportVO.class);
	}

	@Override
	public void importData(AjaxRequest<List<InboundDetailImportVO>> request) throws BusinessServiceException {
		List<InboundDetailImportVO> list = request.getData();
		if (CollectionUtils.isEmpty(list))
			throw new BusinessServiceException("no data.");
		
		//处理存在单号的记录
		Map<String, List<InboundDetailImportVO>> headerMap = list.stream().filter(v -> StringUtils.isNotEmpty(v.getInboundNumber())).collect(Collectors.groupingBy(InboundDetailImportVO::getInboundNumber));
		headerMap.forEach((k, v) -> {
			if (CollectionUtils.isEmpty(v))
				return;
			
			InboundHeaderTEntity header = null;
			try {
				header = inboundHeaderService.find(InboundHeaderTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).inboundNumber(k).build());
			} catch (Exception e) {
				String message = e.getMessage();
				log.error(message, e);
				v.forEach(d -> {
					d.setSuccess(YesNoEnum.No.getCode());
					d.setProcessMessage(message);
				});
				return;
			}
			
			//获取行
			List<InboundDetailTEntity> oriDetail = findByHeaderId(InboundDetailTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).inboundHeaderId(header.getInboundHeaderId()).build());
			long maxLinenumber = DefaultConstants.LINE_INCREMENT;
			if (CollectionUtils.isNotEmpty(oriDetail)) {
				InboundDetailTEntity maxDetail = oriDetail.stream().max(new Comparator<InboundDetailTEntity>() {
					@Override
					public int compare(InboundDetailTEntity o1, InboundDetailTEntity o2) {
						return (o1.getLineNumber() - o2.getLineNumber()) > 0 ? 1 : 0;
					}
				}).get();
				maxLinenumber = maxDetail.getLineNumber();
			}
			
			InboundVO inbound = new InboundVO();
			inbound.setOperatorType(OperatorTypeEnum.Modify);
			InboundDetailImportVO detailFirst = v.get(0);
			BeanUtils.copyBeanProp(inbound, detailFirst);
			try {
				//更新表头
				inbound.setInboundHeaderId(header.getInboundHeaderId());
				inboundHeaderService.save(new AjaxRequest<InboundVO>(inbound, request));
			} catch (Exception e) {
				String message = e.getMessage();
				log.error(message, e);
				v.forEach(d -> {
					d.setSuccess(YesNoEnum.No.getCode());
					d.setProcessMessage(message);
				});
				return;
			}
			
			//更新明细
			for (int i = 0; i < v.size(); i++) {
				InboundDetailImportVO d = v.get(i);
				try {
					InboundDetailTEntity existsDetail = null;
					long currentLineNumber = 0L;
					if (d.getLineNumber() == null) {
						currentLineNumber = maxLinenumber + DefaultConstants.LINE_INCREMENT;
						maxLinenumber = currentLineNumber;
					}else {
						currentLineNumber = d.getLineNumber();
						try {
							//验证行号是否存在
							existsDetail = find(InboundDetailTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).inboundHeaderId(header.getInboundHeaderId()).lineNumber(d.getLineNumber()).build());
						}catch(BusinessServiceException e){
							if (currentLineNumber > maxLinenumber)
								maxLinenumber = currentLineNumber;
						}
					}
					
					InboundDetailVO vo = new InboundDetailVO();
					BeanUtils.copyBeanProp(vo, d);
					vo.setLineNumber(currentLineNumber);
					
					inbound = new InboundVO(header);
					if (existsDetail == null) {
						inbound.setOperatorType(OperatorTypeEnum.Add);
					}else {
						vo.setInboundHeaderId(inbound.getInboundHeaderId());
						vo.setInboundDetailId(existsDetail.getInboundDetailId());
						inbound.setOperatorType(OperatorTypeEnum.Modify);
					}
					inbound.setDetail(Lists.newArrayList(vo));
					save(new AjaxRequest<InboundVO>(inbound, request));
					d.setSuccess(YesNoEnum.Yes.getCode());
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					d.setProcessMessage(e.getMessage());
					d.setSuccess(YesNoEnum.No.getCode());
				}
			}
			//更新表头
			inboundHeaderService.inboundStatus(header, Boolean.TRUE);
		});
		
		//按来源单号进行分组
		headerMap = list.stream().filter(v -> StringUtils.isEmpty(v.getInboundNumber())).collect(Collectors.groupingBy(InboundDetailImportVO::getSourceNumber));
		
		headerMap.forEach((k, v) -> {
			if (CollectionUtils.isEmpty(v))
				return;
			
			//获取行
			long maxLinenumber = 0L;
			
			InboundVO inbound = new InboundVO();
			inbound.setOperatorType(OperatorTypeEnum.Add);
			InboundDetailImportVO detailFirst = v.get(0);
			BeanUtils.copyBeanProp(inbound, detailFirst);
			try {
				//更新表头
				inboundHeaderService.save(new AjaxRequest<InboundVO>(inbound, request));
			} catch (Exception e) {
				String message = e.getMessage();
				log.error(message, e);
				v.forEach(d -> {
					d.setSuccess(YesNoEnum.No.getCode());
					d.setProcessMessage(message);
				});
				return;
			}
			
			//更新明细
			for (int i = 0; i < v.size(); i++) {
				InboundDetailImportVO d = v.get(i);
				try {
					InboundDetailTEntity existsDetail = null;
					long currentLineNumber = 0L;
					if (d.getLineNumber() == null) {
						currentLineNumber = maxLinenumber + DefaultConstants.LINE_INCREMENT;
						maxLinenumber = currentLineNumber;
					}else {
						try {
							//验证行号是否存在
							existsDetail = find(InboundDetailTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).inboundHeaderId(inbound.getInboundHeaderId()).lineNumber(d.getLineNumber()).build());
						}catch(BusinessServiceException e){
							currentLineNumber = d.getLineNumber();
							if (currentLineNumber > maxLinenumber)
								maxLinenumber = currentLineNumber;
						}
					}
					
					InboundDetailVO vo = new InboundDetailVO();
					BeanUtils.copyBeanProp(vo, d);
					vo.setLineNumber(currentLineNumber);
					
					if (existsDetail == null) {
						inbound.setOperatorType(OperatorTypeEnum.Add);
					}else {
						inbound.setOperatorType(OperatorTypeEnum.Modify);
					}
					inbound.setDetail(Lists.newArrayList(vo));
					save(new AjaxRequest<InboundVO>(inbound, request));
					d.setSuccess(YesNoEnum.Yes.getCode());
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					d.setProcessMessage(e.getMessage());
					d.setSuccess(YesNoEnum.No.getCode());
				}
			}
			//更新表头
			inboundHeaderService.inboundStatus(inbound, Boolean.TRUE);
		});
		
	}

	@Override
	public List<InboundDetailImportVO> exportData(PageRequest request) throws BusinessServiceException {
		final int MAX_SIZE = 999;
		
		List<InboundHeaderTEntity> header = inboundHeaderService.find(request);
		if (CollectionUtils.isEmpty(header))
			return Lists.newArrayList();
		
		Set<Long> headerIds = header.stream().map(InboundHeaderTEntity::getInboundHeaderId).collect(Collectors.toSet());
		Map<Long, InboundHeaderTEntity> headerMap = header.stream().collect(Collectors.toMap(InboundHeaderTEntity::getInboundHeaderId, (v) -> v));
		
		List<Set<Long>> mulSetList = Lists.newArrayList();
		Iterator<Long> itor = headerIds.iterator();
		int count = 0;
		
		Set<Long> set = Sets.newHashSet();
		mulSetList.add(set);
		while(itor.hasNext()) {
			long id = itor.next();
			if (count == MAX_SIZE) {
				set = Sets.newHashSet();
				mulSetList.add(set);
				count = 0;
			}
			set.add(id);
			count ++;
		}
		
		List<InboundDetailImportVO> returnList = Lists.newArrayList();
		
		mulSetList.forEach(idSets -> {
			List<InboundDetailTEntity> list = findByHeaderIds(InboundDetailTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).build(), idSets);
			list.forEach(d -> {
				InboundDetailImportVO vo = new InboundDetailImportVO();
				InboundHeaderTEntity h = headerMap.get(d.getInboundHeaderId());
				InboundDetailVO detailVo = new InboundDetailVO(d);
				BeanUtils.copyBeanProp(vo, h);
				BeanUtils.copyBeanProp(vo, detailVo);
				vo.setInboundNumber(h.getInboundNumber());
				returnList.add(vo);
			});
		});
		return returnList;
	}

	/**
	* @Description: 根据LPN或者容器号去重
	* @Param: [detail]
	* @return: java.util.List<com.wms.vo.inbound.InboundDetailVO>
	* @Author: pengzhen@cmhit.com
	* @Date: 2019/8/16
	*/
	private List<InboundDetailVO> getListGroupByLpn(List<InboundDetailVO> detail){
		List<InboundDetailVO> returnList = Lists.newArrayList();
		Map<String,InboundDetailVO> tempMap = Maps.newHashMap();
		detail.forEach(d ->{
			if(BigDecimal.ZERO.compareTo(d.getQuantityReceive())>=0){
				//收货量必须大于0才生成上架任务
				return;
			}
			if(StringUtils.isBlank(d.getLpnNumber()) && StringUtils.isBlank(d.getContainerNumber())){
				//没有有LPN或容器号不生成上架任务
				return;
			}

			String temp = null;
			if(StringUtils.isNotBlank((d.getContainerNumber()))){
				 temp = d.getContainerNumber();
			}else {
				temp = d.getLpnNumber();
			}
			if(!tempMap.containsKey(temp)){
				tempMap.put(temp,d);
			}else {
				InboundDetailVO vo = tempMap.get(temp);
				BigDecimal receiveQuantity = d.getQuantityReceive();
				vo.setQuantityReceive(vo.getQuantityReceive().add(receiveQuantity));
			}
		});
		tempMap.forEach((k,v)->{
			returnList.add(v);
		});
		return returnList;
	}
	
	/** 
	* @Description: 审核后对包装和货品进行新增
	* @Param: [detail] 
	* @return: void 
	* @Author: pengzhen@cmhit.com 
	* @Date: 2019/8/21 
	*/
	private void addPackOrSku(InboundVO inbound, InboundDetailVO detail) {

		if (StringUtils.isNotBlank(detail.getPackCode())) {
			//根据仓库+code查询该仓库是否存在包装，不存在则新建
			Set<String> code = Sets.newHashSet();
			PackTEntity pack = PackTEntity.builder()
					.companyId(detail.getCompanyId())
					.packCode(detail.getPackCode())
					.warehouseId(inbound.getToWarehouseId())
					.build();
			code.add(detail.getPackCode());
			List<PackTEntity> packs = packService.findByPackcodes(pack, code);
			if (CollectionUtils.isEmpty(packs)) {
				EntPackTEntity enPack = EntPackTEntity.builder()
						.companyId(detail.getCompanyId())
						.packCode(detail.getPackCode())
						.build();
				EntPackTEntity select = enterpriseService.findPack(enPack);

				BeanUtils.copyBeanProp(pack, select, Boolean.FALSE);
				pack.setWarehouseId(inbound.getToWarehouseId());
				pack.setCompanyId(detail.getCompanyId());
				pack.setUpdateBy(inbound.getUpdateBy());
				pack.setActive(YesNoEnum.Yes.getCode());
				packs.add(pack);

				packService.add(packs);
			}
		}
		if (StringUtils.isNotBlank(detail.getSkuCode())) {
            final String STD = "STD";
			SkuTEntity sku = SkuTEntity.builder()
					.companyId(detail.getCompanyId())
					.warehouseId(inbound.getToWarehouseId())
					.skuCode(detail.getSkuCode().toUpperCase())
					.ownerCode(inbound.getOwnerCode())
					.build();
			Set<String> codes = Sets.newHashSet();
			codes.add(detail.getSkuCode().toUpperCase());
			List<SkuTEntity> skus = skuService.findBySkuCodes(sku, codes);
			if (CollectionUtils.isEmpty(skus)) {
				//货品不存在新增
				sku.setUpdateBy(inbound.getUpdateBy());
				sku.setOwnerId(inbound.getOwnerId());
				sku.setOwnerCode(inbound.getOwnerCode());
				sku.setSkuDescr(detail.getSkuDescr());

				PackTEntity pack = packService.find(PackTEntity.builder()
						.companyId(detail.getCompanyId())
						.packCode(detail.getPackCode())
						.warehouseId(inbound.getToWarehouseId())
						.build());
				sku.setPackCode(pack.getPackCode());
				sku.setPackId(pack.getPackId());
				sku.setUom(detail.getUom());

				BigDecimal uomQuantity = packService.getUOMQty(pack,detail.getUom());
				sku.setVolume(detail.getVolume().divide(uomQuantity,5,ROUND_FLOOR));
				sku.setWeightGross(detail.getWeightGross().divide(uomQuantity,5,ROUND_FLOOR));
				sku.setWeightNet(detail.getWeightNet().divide(uomQuantity,5,ROUND_FLOOR));
				sku.setWeightTare(detail.getWeightTare().divide(uomQuantity,5,ROUND_FLOOR));
				//分配策略，上架策略，批次验证默认为STD
                sku.setAllocateStrategyCode(STD);
                sku.setPutawayStrategyCode(STD);
                sku.setLotValidateCode(STD);
                //批属性取默认值
				sku.setLotAttribute1Label(MessageUtils.message("web.label.lotattribute1"));
				sku.setLotAttribute2Label(MessageUtils.message("web.label.lotattribute2"));
				sku.setLotAttribute3Label(MessageUtils.message("web.label.lotattribute3"));
				sku.setLotAttribute4Label(MessageUtils.message("web.label.lotattribute4"));
				sku.setLotAttribute5Label(MessageUtils.message("web.label.lotattribute5"));
				sku.setLotAttribute6Label(MessageUtils.message("web.label.lotattribute6"));
				sku.setLotAttribute7Label(MessageUtils.message("web.label.lotattribute7"));
				sku.setLotAttribute8Label(MessageUtils.message("web.label.lotattribute8"));
				sku.setLotAttribute9Label(MessageUtils.message("web.label.lotattribute9"));
				sku.setLotAttribute10Label(MessageUtils.message("web.label.lotattribute10"));
				sku.setLotAttribute11Label(MessageUtils.message("web.label.lotattribute11"));
				sku.setLotAttribute12Label(MessageUtils.message("web.label.lotattribute12"));
				skus.add(sku);
				skuService.add(skus);
			}
		}
	}

	@Override
	public Long findMaxLine(InboundDetailTEntity inbound) throws BusinessServiceException {
		if (inbound.getInboundHeaderId() == null)
			return DefaultConstants.LINE_INCREMENT;
		
		List<InboundDetailTEntity> allDetail = findByHeaderId(inbound);
		if (CollectionUtils.isEmpty(allDetail))
			return DefaultConstants.LINE_INCREMENT;
		
		InboundDetailTEntity maxLine = allDetail.stream().max(new Comparator<InboundDetailTEntity>() {
			@Override
			public int compare(InboundDetailTEntity o1, InboundDetailTEntity o2) {
				return o1.getLineNumber().compareTo(o2.getLineNumber());
			}
		}).get();
		
		long maxLineNumber = maxLine.getLineNumber() + DefaultConstants.LINE_INCREMENT;
		
		return maxLineNumber;
	}
}

