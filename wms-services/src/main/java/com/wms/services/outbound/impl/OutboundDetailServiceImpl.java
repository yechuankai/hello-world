package com.wms.services.outbound.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.constants.DefaultConstants;
import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.services.IExcelService;
import com.wms.common.enums.*;
import com.wms.common.enums.allocate.AllocateStatusEnum;
import com.wms.common.enums.allocate.AllocateStrategyTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IOutboundDetailTDao;
import com.wms.dao.example.OutboundDetailTExample;
import com.wms.entity.auto.*;
import com.wms.services.base.IAllocateStrategyHeaderService;
import com.wms.services.base.IOwnerService;
import com.wms.services.base.IPackService;
import com.wms.services.base.ISkuService;
import com.wms.services.core.IAllocateCoreService;
import com.wms.services.outbound.IAllocateService;
import com.wms.services.outbound.IOutboundDetailService;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.services.sys.IStatusHistoryService;
import com.wms.vo.allocate.AllocateVO;
import com.wms.vo.allocate.InventoryAllocateDetailVO;
import com.wms.vo.allocate.InventoryAllocateVO;
import com.wms.vo.excel.OutboundDetailImportVO;
import com.wms.vo.outbound.OutboundDetailVO;
import com.wms.vo.outbound.OutboundVO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OutboundDetailServiceImpl implements IOutboundDetailService, IExcelService<OutboundDetailImportVO> {

	private static Logger log = LoggerFactory.getLogger(OutboundDetailServiceImpl.class);
	private final String WAREHOUSECODE ="warehouseCode";

	@Autowired
	private IOutboundDetailTDao outboundDetailDao;
	@Autowired
	private IOutboundHeaderService outboundHeaderService;
	@Autowired
	private IAllocateCoreService allocateCoreService;
	@Autowired
	private IAllocateService allocateService;
	@Autowired
	private IAllocateStrategyHeaderService allocateStrategyHeaderService;
	@Autowired
	private ISkuService skuService;
	@Autowired
	private IPackService packService;
	@Autowired
	private IOwnerService ownerService;
	@Autowired
	IStatusHistoryService statusHistoryService;

	@Override
	public List<OutboundDetailVO> find(PageRequest request) throws BusinessServiceException {
		OutboundDetailTExample TExample = new OutboundDetailTExample();
		OutboundDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();

		//转换查询方法
		ExampleUtils.create(OutboundDetailTEntity.Column.class, OutboundDetailTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);

		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());

		List<OutboundDetailTEntity> outboundDetailList = outboundDetailDao.selectByExample(TExample);

		if (CollectionUtils.isEmpty(outboundDetailList))
			return Lists.newArrayList();
		
		Set<Long> headerIds = outboundDetailList.stream().map(OutboundDetailTEntity::getOutboundHeaderId).collect(Collectors.toSet());
		List<OutboundHeaderTEntity> headerList = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(headerIds)){
			OutboundHeaderTEntity header = OutboundHeaderTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.build();
			headerList = outboundHeaderService.find(header, headerIds);
		}
		Map<Long, OutboundHeaderTEntity> headerIdMap = headerList.stream().collect(
			      Collectors.toMap(OutboundHeaderTEntity::getOutboundHeaderId, (s) -> s));

		List<OutboundDetailVO> returnList = Lists.newArrayList();
		outboundDetailList.forEach(d -> {
			OutboundDetailVO outboundDetailVO = new OutboundDetailVO(d);
			
			OutboundHeaderTEntity header = headerIdMap.get(d.getOutboundHeaderId());
			if (header != null)
				outboundDetailVO.setOutboundNumber(header.getOutboundNumber());
			//根据传过来的主数据的warehouseCode补全明细
			if(StringUtils.isNotBlank(request.getString(WAREHOUSECODE))){
				outboundDetailVO.setWarehouseCode(request.getString(WAREHOUSECODE));
			}

			returnList.add(outboundDetailVO);

		});

		return returnList;
	}

	@Override
	public List<OutboundDetailTEntity> findByHeaderId(OutboundDetailTEntity outbound) throws BusinessServiceException {
		return findByHeaderIds(outbound, Sets.newHashSet(outbound.getOutboundHeaderId()));
	}
	
	@Override
	public List<OutboundDetailTEntity> findByHeaderIds(OutboundDetailTEntity outbound, Set<Long> ids) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(ids))
			return Lists.newArrayList();
		OutboundDetailTExample TExample = new OutboundDetailTExample();
		TExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(outbound.getWarehouseId())
		.andCompanyIdEqualTo(outbound.getCompanyId())
		.andOutboundHeaderIdIn(Lists.newArrayList(ids));
		List<OutboundDetailTEntity> outboundDetailList = outboundDetailDao.selectByExample(TExample);
		return outboundDetailList;
	}

	@Override
	public OutboundDetailTEntity find(OutboundDetailTEntity outbound) throws BusinessServiceException {
		OutboundDetailTExample TExample = new OutboundDetailTExample();
		OutboundDetailTExample.Criteria criteria = TExample.createCriteria();
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(outbound.getWarehouseId())
		.andCompanyIdEqualTo(outbound.getCompanyId());
		
		int conditionCount = 0;
		
		if (outbound.getLineNumber() != null) {
			criteria.andLineNumberEqualTo(outbound.getLineNumber());
			conditionCount ++;
		}
		if (outbound.getOutboundHeaderId() != null) {
			criteria.andOutboundHeaderIdEqualTo(outbound.getOutboundHeaderId());
			conditionCount ++;
		}
		if (outbound.getOutboundDetailId() != null) {
			criteria.andOutboundDetailIdEqualTo(outbound.getOutboundDetailId());
			conditionCount ++;
		}
		if (conditionCount == 0)
			return null;
		
		OutboundDetailTEntity outboundDetail = outboundDetailDao.selectOneByExample(TExample);
		if (outboundDetail == null) 
			throw new BusinessServiceException("outboundDetailServiceImpl", "outbound.line.not.exists" , new Object[] {outbound.getOutboundDetailId()});
		
		return outboundDetail;
	}

	@Override
	public Boolean save(AjaxRequest<OutboundVO> request) throws BusinessServiceException {
		OutboundVO outboundVO = request.getData();

		outboundVO.getDetail().forEach(d -> {
			d.setWarehouseId(request.getWarehouseId());
			d.setCompanyId(request.getCompanyId());
			d.setOutboundHeaderId(outboundVO.getOutboundHeaderId());
			d.setUpdateBy(request.getUserName());
			d.setUpdateTime(new Date());

			validate(outboundVO, d);
			switch (outboundVO.getOperatorType()) {
				case Add:
					d.setCreateBy(request.getUserName());
					d.setCreateTime(new Date());
					add(d);
					break;
				case Modify:
					modify(d,Boolean.TRUE);
					break;
				default:
					throw new BusinessServiceException("InboundDetailServiceImpl", "opertiontype.not.exists" , null );
			}
		});
			return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean modify(OutboundDetailVO outbound, Boolean replaceFlag) throws BusinessServiceException {
		OutboundDetailTEntity selectDetail = find(OutboundDetailTEntity.builder()
				.warehouseId(outbound.getWarehouseId())
				.companyId(outbound.getCompanyId())
				.outboundDetailId(outbound.getOutboundDetailId())
				.build());
		if (OutboundStatusEnum.Shiped.getCode().equals(selectDetail.getStatus())
				|| OutboundStatusEnum.Cancel.getCode().equals(selectDetail.getStatus())) 
			throw new BusinessServiceException("outboundDetailServiceImpl", "outbound.line.status.not.process" , new Object[] {selectDetail.getLineNumber()});
		
		BigDecimal updateQuantityExpected = selectDetail.getQuantityExpected();
		BigDecimal updateQuantityAllocated = selectDetail.getQuantityAllocated();
		BigDecimal updateQuantityPicked = selectDetail.getQuantityPicked();
		BigDecimal updateQuantityShiped = selectDetail.getQuantityShiped();
		
		boolean updateExpectedFlag = false;
		boolean updateAllocatedFlag = false;
		boolean updatePickFlag = false;
		boolean updateShipFlag = false;
		
		if (outbound.getQuantityExpected() != null) {
			updateQuantityExpected = outbound.getQuantityExpected();
			updateExpectedFlag = true;
		}
		if (outbound.getQuantityAllocated() != null ) { 
			updateQuantityAllocated = outbound.getQuantityAllocated();
			updateAllocatedFlag = true;
		}
		if (outbound.getQuantityPicked() != null) { 
			updateQuantityPicked = outbound.getQuantityPicked();
			updatePickFlag = true;
		}
		if (outbound.getQuantityShiped() != null) {
			updateQuantityShiped = outbound.getQuantityShiped();
			updateShipFlag = true;
		}
		if (!replaceFlag) {
			if (updateExpectedFlag)
				updateQuantityExpected = selectDetail.getQuantityExpected().add(updateQuantityExpected);
			if (updateAllocatedFlag)
				updateQuantityAllocated = selectDetail.getQuantityAllocated().add(updateQuantityAllocated);
			if (updatePickFlag)
				updateQuantityPicked = selectDetail.getQuantityPicked().add(updateQuantityPicked);
			if (updateShipFlag)
				updateQuantityShiped = selectDetail.getQuantityShiped().add(updateQuantityShiped);
		}
			
		
		if(BigDecimal.ZERO.compareTo(updateQuantityExpected) > 0)
			throw new BusinessServiceException("outboundDetailServiceImpl", "outbound.line.quantity.expected.not.lessthan.zero" , new Object[] {selectDetail.getLineNumber(), updateExpectedFlag});
		if (BigDecimal.ZERO.compareTo(updateQuantityAllocated) > 0)
			throw new BusinessServiceException("outboundDetailServiceImpl", "outbound.line.quantity.allocate.not.lessthan.zero" , new Object[] {selectDetail.getLineNumber(), updateAllocatedFlag});
		if (BigDecimal.ZERO.compareTo(updateQuantityPicked) > 0)
			throw new BusinessServiceException("outboundDetailServiceImpl", "outbound.line.quantity.pick.not.lessthan.zero" , new Object[] {selectDetail.getLineNumber(), updateQuantityPicked});
		if (BigDecimal.ZERO.compareTo(updateQuantityShiped) > 0)
			throw new BusinessServiceException("outboundDetailServiceImpl", "outbound.line.quantity.shiped.not.lessthan.zero" , new Object[] {selectDetail.getLineNumber(), updateQuantityShiped});
			
		BigDecimal demandQuantity = updateQuantityExpected.subtract(updateQuantityAllocated).subtract(updateQuantityPicked);
		if (BigDecimal.ZERO.compareTo(demandQuantity) > 0)
			throw new BusinessServiceException("outboundDetailServiceImpl", "outbound.line.quantity.must.available" , new Object[] {selectDetail.getLineNumber(), updateQuantityExpected, updateQuantityAllocated, updateQuantityPicked});
		
		OutboundDetailTEntity updateDetail = new OutboundDetailTEntity();
		BeanUtils.copyBeanProp(updateDetail, outbound, Boolean.FALSE);
		updateDetail.setUpdateBy(outbound.getUpdateBy());
		updateDetail.setUpdateTime(new Date());
		if (updateExpectedFlag)
			updateDetail.setQuantityExpected(updateQuantityExpected);
		else
			updateDetail.setQuantityExpected(null);
		if (updateAllocatedFlag)
			updateDetail.setQuantityAllocated(updateQuantityAllocated);
		else
			updateDetail.setQuantityAllocated(null);
		if (updatePickFlag)
			updateDetail.setQuantityPicked(updateQuantityPicked);
		else
			updateDetail.setQuantityPicked(null);
		if (updateShipFlag)
			updateDetail.setQuantityShiped(updateQuantityShiped);
		else
			updateDetail.setQuantityShiped(null);
		
		String newStatus = outbound.getStatus();
		if (StringUtils.isEmpty(newStatus)) {
			newStatus = selectDetail.getStatus();
		}

		if (StringUtils.isEmpty(outbound.getStatus())) {
			//处理状态
			OutboundStatusEnum status = outboundDetailStatus(OutboundDetailTEntity.builder()
																		.quantityExpected(updateQuantityExpected)
																		.quantityAllocated(updateQuantityAllocated)
																		.quantityPicked(updateQuantityPicked)
																		.quantityShiped(updateQuantityShiped)
																		.status(newStatus)
																		.build(), Boolean.FALSE);
			updateDetail.setStatus(status.getCode());
		}

		OutboundDetailTExample example = new OutboundDetailTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(outbound.getWarehouseId())
		.andCompanyIdEqualTo(outbound.getCompanyId())
		.andOutboundDetailIdEqualTo(outbound.getOutboundDetailId());
		
		int rowcount = outboundDetailDao.updateWithVersionByExampleSelective(selectDetail.getUpdateVersion(), updateDetail, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");
		
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean add(OutboundDetailVO outbound) throws BusinessServiceException {
		outbound.setOutboundDetailId(KeyUtils.getUID());
		OutboundDetailTEntity updateDetail = new OutboundDetailTEntity();
		BeanUtils.copyBeanProp(updateDetail, outbound, Boolean.FALSE);
		updateDetail.setCreateBy(outbound.getCreateBy());
		updateDetail.setCreateTime(new Date());
		updateDetail.setUpdateBy(outbound.getUpdateBy());
		updateDetail.setUpdateTime(new Date());

		int rowcount = outboundDetailDao.insertSelective(updateDetail);
		if (rowcount == 0) {
			throw new BusinessServiceException("record add error.");
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<OutboundDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<OutboundDetailTEntity> list = request.getData();

		list.forEach(d -> {
			OutboundDetailTEntity detail = find(OutboundDetailTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.outboundDetailId(d.getOutboundDetailId())
					.build());
			delete(detail);
		});

		return Boolean.TRUE;
	}

	private Boolean delete(OutboundDetailTEntity detail) throws BusinessServiceException {
		if(0L != detail.getWarehouseId()){
			OutboundStatusEnum status = outboundDetailStatus(detail, Boolean.FALSE);
			if (OutboundStatusEnum.New != status) {
				throw new BusinessServiceException("OutboundDetailServiceImpl", "outbound.line.status.not.process" , new Object[] {detail.getLineNumber()});
			}
		}

		OutboundDetailTEntity updateDetail = OutboundDetailTEntity.builder()
				.updateBy(detail.getUpdateBy())
				.updateTime(new Date())
				.delFlag(YesNoEnum.Yes.getCode())
				.build();

		OutboundDetailTExample example = new OutboundDetailTExample();
		example.createCriteria()
				.andWarehouseIdEqualTo(detail.getWarehouseId())
				.andCompanyIdEqualTo(detail.getCompanyId())
				.andOutboundDetailIdEqualTo(detail.getOutboundDetailId());

		int rowcount = outboundDetailDao.updateWithVersionByExampleSelective(detail.getUpdateVersion(), updateDetail, example);
		if (rowcount == 0) {
			throw new BusinessServiceException("record delete error.");
		}
		return Boolean.TRUE;
	}
	
	@Override
	@Transactional
	public Boolean delete(OutboundHeaderTEntity header) throws BusinessServiceException {
		List<OutboundDetailTEntity> detailList = findByHeaderId(OutboundDetailTEntity.builder()
														.warehouseId(header.getWarehouseId())
														.companyId(header.getCompanyId())
														.outboundHeaderId(header.getOutboundHeaderId())
														.build());
		detailList.forEach(d -> {
			delete(d);
		});
		
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public OutboundStatusEnum outboundDetailStatus(OutboundDetailTEntity detail, Boolean updateFlag)
			throws BusinessServiceException {
		OutboundStatusEnum status = OutboundStatusEnum.New;

		//预期量、分配量、拣货量都为0时，状态修改为发运状态
		if (BigDecimal.ZERO.compareTo(detail.getQuantityExpected()) == 0
				&& BigDecimal.ZERO.compareTo(detail.getQuantityAllocated()) == 0
					&& BigDecimal.ZERO.compareTo(detail.getQuantityPicked()) == 0)
			status = OutboundStatusEnum.Shiped;
		//预期量大于0时，发运量大于0，状态修改为部分发运状态
		else if (BigDecimal.ZERO.compareTo(detail.getQuantityExpected()) < 0
				&& BigDecimal.ZERO.compareTo(detail.getQuantityShiped()) < 0)
			status = OutboundStatusEnum.PartShiped;
		//预期量等于拣货量，状态修改为拣货状态
		else if (detail.getQuantityExpected().compareTo(detail.getQuantityPicked()) == 0)
			status = OutboundStatusEnum.Picked;
		//拣货量大于0，状态修改为拣货状态
		else if (BigDecimal.ZERO.compareTo(detail.getQuantityPicked()) < 0)
			status = OutboundStatusEnum.PartPicked;
		//非已发放状态，预期量等于分配量，状态修改为分配状态
		else if (detail.getQuantityExpected().compareTo(detail.getQuantityAllocated()) == 0
				&& !OutboundStatusEnum.Release.getCode().equals(detail.getStatus()))
			status = OutboundStatusEnum.Allocated;
		//非已发放状态，分配量大于0，状态修改为部分分配状态
		else if (BigDecimal.ZERO.compareTo(detail.getQuantityAllocated()) < 0
					&& !OutboundStatusEnum.Release.getCode().equals(detail.getStatus()))
			status = OutboundStatusEnum.PartAllocated;

		if (!updateFlag)
			return status;

		OutboundDetailTEntity selectDetail = find(OutboundDetailTEntity.builder()
													.warehouseId(detail.getWarehouseId())
													.companyId(detail.getCompanyId())
													.outboundDetailId(detail.getOutboundDetailId())
													.build());

		OutboundDetailTEntity updateDetail = OutboundDetailTEntity.builder()
												.status(status.getCode())
												.build();
		OutboundDetailTExample example = new OutboundDetailTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(detail.getWarehouseId())
		.andCompanyIdEqualTo(detail.getCompanyId())
		.andOutboundDetailIdEqualTo(detail.getOutboundDetailId());
		int rowcount = outboundDetailDao.updateWithVersionByExampleSelective(selectDetail.getUpdateVersion(), updateDetail, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");

		detail.setStatus(status.getCode());

		return status;
	}

    @Override
    public Boolean modify(OutboundDetailTEntity detail) throws BusinessServiceException {
        OutboundDetailTEntity updateDetail = OutboundDetailTEntity.builder()
                .updateBy(detail.getUpdateBy())
                .updateTime(new Date())
                .status(detail.getStatus())
                .build();

        OutboundDetailTExample example = new OutboundDetailTExample();
        example.createCriteria()
                .andWarehouseIdEqualTo(detail.getWarehouseId())
                .andCompanyIdEqualTo(detail.getCompanyId())
                .andOutboundDetailIdEqualTo(detail.getOutboundDetailId());

        int rowcount = outboundDetailDao.updateWithVersionByExampleSelective(detail.getUpdateVersion(), updateDetail, example);
        if (rowcount == 0) {
            throw new BusinessServiceException("record update error.");
        }
        return Boolean.TRUE;
    }

    @Override
	public Boolean allocate(List<OutboundDetailVO> detail) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(detail))
			throw new BusinessServiceException("no record allocate.");
		
		Map<Long, List<OutboundDetailVO>> outbounds = detail.stream().collect(Collectors.groupingBy(OutboundDetailVO::getOutboundHeaderId));
		
		outbounds.forEach((k, v) -> {
			if (CollectionUtils.isEmpty(v))
				return;
			
			Long companyId = v.get(0).getCompanyId();
			Long warehouseId = v.get(0).getWarehouseId();
			OutboundHeaderTEntity header = outboundHeaderService.find(OutboundHeaderTEntity.builder().warehouseId(warehouseId).companyId(companyId).outboundHeaderId(k).build());
			
			InventoryAllocateVO allocateVO = new InventoryAllocateVO(header);
			
			List<InventoryAllocateDetailVO> allocateDetail = Lists.newArrayList();
			v.forEach(d -> {
				OutboundDetailTEntity detailObj = find((OutboundDetailTEntity)d);
				BigDecimal demandAllocateQuantity = detailObj.getQuantityExpected().subtract(detailObj.getQuantityAllocated()).subtract(detailObj.getQuantityPicked());
				if (BigDecimal.ZERO.compareTo(demandAllocateQuantity) == 0)
					return;
				
				InventoryAllocateDetailVO allocate = new InventoryAllocateDetailVO(detailObj);
				allocate.setUpdateBy(d.getUpdateBy());
				allocateVO.setUpdateBy(d.getUpdateBy());
				allocateDetail.add(allocate);
			});
			allocateVO.setDetail(allocateDetail);
			outboundHeaderService.processStatus(header, OutboundProcessStatusEnum.Allocating);
			allocateCoreService.allocateTask(allocateVO, Boolean.TRUE);
		});
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean unAllocate(List<OutboundDetailVO> detail) throws BusinessServiceException {
		long warehouseId = 0L;
		long companyId = 0L;
		String updateBy = null;
		for (OutboundDetailVO d : detail) {
			warehouseId = d.getWarehouseId();
			companyId = d.getCompanyId();
			updateBy = d.getUpdateBy();
			break;
		}
		final long _warehouseId = warehouseId;
		final long _companyId = companyId;
		final String _updateBy = updateBy;
		
		Set<Long> headerIds = detail.stream().map(OutboundDetailVO::getOutboundHeaderId).collect(Collectors.toSet());
		Set<Long> detailIds = detail.stream().map(OutboundDetailVO::getOutboundDetailId).collect(Collectors.toSet());
		List<AllocateTEntity> allocateList = allocateService.findBySourceNumber(AllocateTEntity.builder().warehouseId(warehouseId).companyId(companyId).build(), detailIds, AllocateStatusEnum.Allocated, AllocateStatusEnum.Release);
		
		headerIds.forEach(h -> {
			outboundHeaderService.processStatus(OutboundHeaderTEntity.builder().warehouseId(_warehouseId).companyId(_companyId).outboundHeaderId(h).updateBy(_updateBy).build(), OutboundProcessStatusEnum.UnAllocating);
		});
		
		boolean unAllocateFlag = allocateService.delete(allocateList);
		
		
		headerIds.forEach(h -> {
			OutboundHeaderTEntity header = OutboundHeaderTEntity.builder()
						.warehouseId(_warehouseId)
						.companyId(_companyId)
						.outboundHeaderId(h)
						.updateBy(_updateBy)
						.processStatus(OutboundProcessStatusEnum.UnAllocated.getCode())
						.build();
			outboundHeaderService.modify(new OutboundVO(header));
		});
		return unAllocateFlag;
	}

	@Override
	public Boolean pick(List<OutboundDetailVO> detail) throws BusinessServiceException {
		long warehouseId = 0L;
		long companyId = 0L;
		String updateBy = null;
		for (OutboundDetailVO d : detail) {
			warehouseId = d.getWarehouseId();
			companyId = d.getCompanyId();
			updateBy = d.getUpdateBy();
			break;
		}
		final long _warehouseId = warehouseId;
		final long _companyId = companyId;
		final String _updateBy = updateBy;
		
		Set<Long> headerIds = detail.stream().map(OutboundDetailVO::getOutboundHeaderId).collect(Collectors.toSet());
		Set<Long> detailIds = detail.stream().map(OutboundDetailVO::getOutboundDetailId).collect(Collectors.toSet());
		List<AllocateTEntity> allocateList = allocateService.findBySourceNumber(AllocateTEntity.builder().warehouseId(warehouseId).companyId(companyId).build(), detailIds, AllocateStatusEnum.Allocated,  AllocateStatusEnum.Release);
		long count = allocateList.stream().filter(v->AllocateStrategyTypeEnum.Soft.getCode().equals(v.getAllocateStrategyType())).count();
		if (count > 0)
			throw new BusinessServiceException("OutboundDetailServiceImpl", "outbound.line.pick.must.hardallocate", null);
		
		headerIds.forEach(h -> {
			outboundHeaderService.processStatus(OutboundHeaderTEntity.builder().warehouseId(_warehouseId).companyId(_companyId).outboundHeaderId(h).updateBy(_updateBy).build(), OutboundProcessStatusEnum.Picking);
		});
		
		List<AllocateVO> pickList = Lists.newArrayList();
		allocateList.forEach(al -> {
			AllocateVO vo = new AllocateVO(al);
			vo.setQuantityPick(al.getQuantityAllocated());
			vo.setUpdateBy(_updateBy);
			pickList.add(vo);
		});
		
		boolean pickFlag = allocateService.pick(pickList);
		
		headerIds.forEach(h -> {
			OutboundHeaderTEntity header = OutboundHeaderTEntity.builder()
						.warehouseId(_warehouseId)
						.companyId(_companyId)
						.outboundHeaderId(h)
						.updateBy(_updateBy)
						.processStatus(OutboundProcessStatusEnum.Picked.getCode())
						.build();
			outboundHeaderService.modify(new OutboundVO(header));
		});
		return pickFlag;
	}

	@Override
	public Boolean unPick(List<OutboundDetailVO> detail) throws BusinessServiceException {
		long warehouseId = 0L;
		long companyId = 0L;
		String updateBy = null;
		for (OutboundDetailVO d : detail) {
			warehouseId = d.getWarehouseId();
			companyId = d.getCompanyId();
			updateBy = d.getUpdateBy();
			break;
		}
		final long _warehouseId = warehouseId;
		final long _companyId = companyId;
		final String _updateBy = updateBy;
		
		Set<Long> headerIds = detail.stream().map(OutboundDetailVO::getOutboundHeaderId).collect(Collectors.toSet());
		Set<Long> detailIds = detail.stream().map(OutboundDetailVO::getOutboundDetailId).collect(Collectors.toSet());
		List<AllocateTEntity> allocateList = allocateService.findBySourceNumber(AllocateTEntity.builder().warehouseId(warehouseId).companyId(companyId).build(), detailIds, AllocateStatusEnum.Picked);
		headerIds.forEach(h -> {
			outboundHeaderService.processStatus(OutboundHeaderTEntity.builder().warehouseId(_warehouseId).companyId(_companyId).outboundHeaderId(h).updateBy(_updateBy).build(), OutboundProcessStatusEnum.UnPicking);
		});
		boolean unPickFlag = allocateService.delete(allocateList);
		
		headerIds.forEach(h -> {
			OutboundHeaderTEntity header = OutboundHeaderTEntity.builder()
					.warehouseId(_warehouseId)
					.companyId(_companyId)
					.outboundHeaderId(h)
					.updateBy(_updateBy)
					.processStatus(OutboundProcessStatusEnum.UnPicked.getCode())
					.build();
			outboundHeaderService.modify(new OutboundVO(header));
		});
		return unPickFlag;
	}

	@Override
	public Boolean ship(List<OutboundDetailVO> detail) throws BusinessServiceException {
		long warehouseId = 0L;
		long companyId = 0L;
		String updateBy = null;
		for (OutboundDetailVO d : detail) {
			warehouseId = d.getWarehouseId();
			companyId = d.getCompanyId();
			updateBy = d.getUpdateBy();
			break;
		}
		final long _warehouseId = warehouseId;
		final long _companyId = companyId;
		final String _updateBy = updateBy;
		
		Set<Long> headerIds = detail.stream().map(OutboundDetailVO::getOutboundHeaderId).collect(Collectors.toSet());
		Set<Long> detailIds = detail.stream().map(OutboundDetailVO::getOutboundDetailId).collect(Collectors.toSet());
		List<AllocateTEntity> allocateList = allocateService.findBySourceNumber(AllocateTEntity.builder().warehouseId(warehouseId).companyId(companyId).build(), detailIds, AllocateStatusEnum.Picked);
		long count = allocateList.stream().filter(v->AllocateStrategyTypeEnum.Soft.getCode().equals(v.getAllocateStrategyType())).count();
		if (count > 0) {
            throw new BusinessServiceException("OutboundDetailServiceImpl", "outbound.line.pick.must.hardallocate", null);
        }
		headerIds.forEach(h -> {
			outboundHeaderService.processStatus(OutboundHeaderTEntity.builder().warehouseId(_warehouseId).companyId(_companyId).outboundHeaderId(h).updateBy(_updateBy).build(), OutboundProcessStatusEnum.Shiping);
		});
		
		List<AllocateVO> shipList = Lists.newArrayList();
		allocateList.forEach(al -> {
			AllocateVO vo = new AllocateVO(al);
			vo.setQuantityShip(al.getQuantityAllocated());
			vo.setUpdateBy(_updateBy);
			shipList.add(vo);
		});
		
		boolean shiFlag = allocateService.ship(shipList);
		
		headerIds.forEach(h -> {
			OutboundHeaderTEntity header = OutboundHeaderTEntity.builder()
					.warehouseId(_warehouseId)
					.companyId(_companyId)
					.outboundHeaderId(h)
					.updateBy(_updateBy)
					.processStatus(OutboundProcessStatusEnum.Picked.getCode())
					.build();
			outboundHeaderService.modify(new OutboundVO(header));
		});
		return shiFlag;
	}

	@Override
	public Boolean release(List<OutboundDetailVO> detail) throws BusinessServiceException {
		List<AllocateVO> allocateVOList = Lists.newArrayList();
		List<OutboundDetailVO> updateDetails = Lists.newArrayList();
		long warehouseId = 0L;
		long companyId = 0L;
		for (OutboundDetailVO d : detail) {
			warehouseId = d.getWarehouseId();
			companyId = d.getCompanyId();
			break;
		}
		Set<Long> detailIds = detail.stream().map(OutboundDetailVO::getOutboundDetailId).collect(Collectors.toSet());
		List<AllocateTEntity> allocateList = allocateService.findBySourceNumber(AllocateTEntity.builder().warehouseId(warehouseId).companyId(companyId).build(), detailIds, AllocateStatusEnum.Allocated);

		if (CollectionUtils.isEmpty(allocateList)){
			throw new BusinessServiceException("OutboundDetailServiceImpl", "outbound.line.release.must.allocate", null);
		}
		//出库单明细包装、单位等信息存入分配明细VO中
		for(AllocateTEntity allocate :allocateList){
			detail.forEach( v->{
				if(v.getOutboundDetailId().longValue()==allocate.getSourceNumber().longValue()){
					AllocateVO allocateVO = new AllocateVO(allocate);
					allocateVO.setUom(v.getUom());
					allocateVO.setPackId(v.getPackId());
					allocateVO.setPackCode(v.getPackCode());
					allocateVO.setUpdateBy(v.getUpdateBy());
					allocateVOList.add(allocateVO);
					updateDetails.add(v);
				}
			});
		}
		boolean releaseFlag = allocateService.release(allocateVOList);
		if(releaseFlag){
			//发放成功后修改发放成功明细的状态
			updateDetails.forEach(d ->{
				d.setWarehouseId(d.getWarehouseId());
				d.setCompanyId(d.getCompanyId());
				d.setUpdateBy(d.getUpdateBy());
				d.setUpdateTime(new Date());
				d.setStatus(OutboundStatusEnum.Release.getCode());
				modify(d);
			});
		}
		return releaseFlag;
	}
	
	/**
	 * 处理明细 修改，删除
	 * @param type
	 * @param newDetail
	 * @param oriDetail
	 * @return
	 */
	private Boolean processOMSOutboundDetail(OperatorTypeEnum type, List<OutboundDetailVO> newDetail, List<OutboundDetailTEntity> oriDetail) {
		//没有原始行，默认原类型
		if (CollectionUtils.isEmpty(oriDetail) && CollectionUtils.isNotEmpty(newDetail)) {
			newDetail.forEach(d -> {
				d.setOperatorType(type);
			});
		}
		//没有新行，将原始行都删除
		if (CollectionUtils.isEmpty(newDetail)) {
			oriDetail.forEach(d -> {
				OutboundDetailVO delObj = new OutboundDetailVO(d);
				delObj.setOperatorType(OperatorTypeEnum.Delete);
				newDetail.add(delObj);
			});
		} 
		//默认为修改
		newDetail.stream().forEach(add -> {
			add.setOperatorType(OperatorTypeEnum.Modify);
		});
		
		//计算新增，明细ID为空则认为新增
		List<OutboundDetailVO> addDetail = newDetail.stream().filter(v -> v.getOutboundDetailId() == null).collect(Collectors.toList());
		if (CollectionUtils.isNotEmpty(addDetail)) {
			long maxLine = 0L;
			if (CollectionUtils.isNotEmpty(oriDetail)) {
				//获取最大行号
				OutboundDetailTEntity max = oriDetail.stream().max(new Comparator<OutboundDetailTEntity>() {
					@Override
					public int compare(OutboundDetailTEntity o1, OutboundDetailTEntity o2) {
						return o1.getLineNumber().compareTo(o2.getLineNumber());
					}
				}).get();
				
				if (max != null)
					maxLine = max.getLineNumber();
			}
			for (int i = 0; i < addDetail.size(); i++) {
				OutboundDetailVO addObj = addDetail.get(i);
				addObj.setLineNumber((i + 1) * DefaultConstants.LINE_INCREMENT + maxLine);
				//查询货品默认包装
				SkuTEntity sku = skuService.find(SkuTEntity.builder()
								.companyId(addObj.getCompanyId())
								.warehouseId(addObj.getWarehouseId())
								.skuCode(addObj.getSkuCode())
								.build());
				
				addObj.setPackCode(sku.getPackCode());
				addObj.setUom(sku.getUom());
				addObj.setAllocateStrategyCode(sku.getAllocateStrategyCode());
				addObj.setAllocateStrategyCode(sku.getAllocateStrategyCode());
				addObj.setOperatorType(OperatorTypeEnum.Add);
			}
		} 
		Map<Long, OutboundDetailVO> newMap = newDetail.stream().filter(v -> v.getOutboundDetailId() != null).collect(Collectors.toMap(OutboundDetailVO::getOutboundDetailId, v->v));
		//计算删除
		oriDetail.forEach(od -> {
			OutboundDetailVO newObj = newMap.get(od.getOutboundDetailId());
			if (newObj == null) {
				OutboundDetailVO delObj = new OutboundDetailVO(od);
				delObj.setOperatorType(OperatorTypeEnum.Delete);
				newDetail.add(delObj);
			}else {
				newObj.setLineNumber(od.getLineNumber());
				newObj.setPackCode(od.getPackCode());
				newObj.setUom(od.getUom());
				newObj.setAllocateStrategyCode(od.getAllocateStrategyCode());
				newObj.setUomQuantityOrder(newObj.getUomQuantityExpected());
			}
		});
		
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean saveFromOms(OutboundVO outboundVO) throws BusinessServiceException {
		OutboundVO outbound = outboundHeaderService.find(outboundVO);
		outbound.setOperatorType(outboundVO.getOperatorType());
		outboundVO.getDetail().forEach(d -> {
			d.setOperatorType(outboundVO.getOperatorType());
		});
		if(outboundVO.getOperatorType() == OperatorTypeEnum.Modify
				|| outboundVO.getOperatorType() == OperatorTypeEnum.Submit) {
			//需要计算明细新增/删除/修改
			//1.获取所有明细
			List<OutboundDetailTEntity> detail = findByHeaderId(outbound.getOutboundHeaderId());
			if (CollectionUtils.isNotEmpty(detail)) {
				processOMSOutboundDetail(outboundVO.getOperatorType(), outboundVO.getDetail(), detail);
			}else {
				outboundVO.getDetail().forEach(d -> {
					d.setOperatorType(OperatorTypeEnum.Add);
				});
			}
		}

		outboundVO.getDetail().forEach(d->{
			d.setOutboundHeaderId(outbound.getOutboundHeaderId());
			d.setCompanyId(outboundVO.getCompanyId());
			d.setWarehouseId(outboundVO.getWarehouseId());
			d.setUpdateBy(outboundVO.getUpdateBy());
			outbound.setOperatorType(d.getOperatorType());
			validate(outbound,d);
			switch (d.getOperatorType()) {
				case Add:
					d.setCreateBy(outboundVO.getUpdateBy());
					d.setStatus(OutboundStatusEnum.Draft.getCode());
					add(d);
					break;
				case Modify:
					d.setStatus(OutboundStatusEnum.Draft.getCode());
					//判断是否为新增或修改
					modify(d,Boolean.TRUE);
					break;
				case Delete:
					delete(d);
					break;
				case Submit:
					d.setStatus(OutboundStatusEnum.WaitingReview.getCode());
					if(null == d.getOutboundDetailId()){
						d.setCreateBy(outboundVO.getUpdateBy());
						add(d);
					}else {
						modify(d,Boolean.TRUE);
					}
					break;
				case Review:
					d.setStatus(OutboundStatusEnum.New.getCode());
					modify(d,Boolean.TRUE);
					break;
				default:
					throw new BusinessServiceException("OutboundHeaderServiceImpl", "opertiontype.not.exists" , null );
			}

		});
		return Boolean.TRUE;
	}

	private Boolean validate(OutboundVO outboundVO, OutboundDetailVO detail) {
		if (outboundVO.getOutboundHeaderId() == null) {
			throw new BusinessServiceException("OutboundDetailServiceImpl", "outbound.header.isnull", new Object[]{outboundVO.getOutboundNumber()});
		}else {
			detail.setOutboundHeaderId(outboundVO.getOutboundHeaderId());
		}
		if (detail.getLineNumber() == null) {
			throw new BusinessServiceException("OutboundDetailServiceImpl", "outbound.line.isnull", new Object[]{outboundVO.getOutboundNumber()});
		}
		if (outboundVO.getOwnerId() == null && StringUtils.isEmpty(outboundVO.getOwnerCode()) ) {
			throw new BusinessServiceException("OutboundDetailServiceImpl", "outbound.owner.isnull", new Object[]{outboundVO.getOutboundNumber()});
		}

		if (detail.getSkuId() == null && StringUtils.isEmpty(detail.getSkuCode())) {
			throw new BusinessServiceException("OutboundDetailServiceImpl", "outbound.sku.isnull", new Object[]{outboundVO.getOutboundNumber(), detail.getLineNumber()});
		}
		if (detail.getPackId() == null && StringUtils.isEmpty(detail.getPackCode())) {
			throw new BusinessServiceException("OutboundDetailServiceImpl", "outbound.pack.isnull", new Object[]{outboundVO.getOutboundNumber(), detail.getLineNumber()});
		}
		if (StringUtils.isEmpty(detail.getUom())) {
			throw new BusinessServiceException("OutboundDetailServiceImpl", "outbound.uom.isnull", new Object[]{outboundVO.getOutboundNumber(), detail.getLineNumber()});
		}
		PackTEntity pack = packService.find(PackTEntity.builder()
				.warehouseId(detail.getWarehouseId())
				.companyId(detail.getCompanyId())
				.packId(detail.getPackId())
				.packCode(detail.getPackCode())
				.build());
		detail.setPackCode(pack.getPackCode());
		detail.setPackId(pack.getPackId());

		BigDecimal uomQuantity = packService.getUOMQty(pack, detail.getUom());
		if (uomQuantity.compareTo(BigDecimal.ZERO) == 0) {
			throw new BusinessServiceException("OutboundDetailServiceImpl", "outbound.uom.error", new Object[]{outboundVO.getOutboundNumber(), detail.getLineNumber(), detail.getUom()});
		}
		detail.setUomQuantity(uomQuantity);

		if (detail.getUomQuantityExpected() != null && detail.getUomQuantityExpected().compareTo(BigDecimal.ZERO) > 0) {
			detail.setQuantityExpected(detail.getUomQuantityExpected().multiply(uomQuantity));
		}
		if (detail.getQuantityExpected() == null) {
			detail.setQuantityExpected(BigDecimal.ZERO);
		}

		/*
		if (detail.getUomQuantityOrder() != null && detail.getUomQuantityOrder().compareTo(BigDecimal.ZERO) > 0) {
			detail.setQuantityOrder(detail.getUomQuantityOrder().multiply(uomQuantity));
		}
		if (detail.getQuantityOrder() == null) {
			detail.setQuantityOrder(BigDecimal.ZERO);
		}
		if (detail.getUomQuantityAllocated() != null && detail.getUomQuantityAllocated().compareTo(BigDecimal.ZERO) > 0) {
			detail.setQuantityAllocated(detail.getUomQuantityAllocated().multiply(uomQuantity));
		}
		if (detail.getQuantityAllocated() == null) {
			detail.setQuantityAllocated(BigDecimal.ZERO);
		}
		if (detail.getUomQuantityPicked() != null && detail.getUomQuantityPicked().compareTo(BigDecimal.ZERO) > 0) {
			detail.setQuantityPicked(detail.getUomQuantityPicked().multiply(uomQuantity));
		}
		if (detail.getQuantityPicked() == null) {
			detail.setQuantityPicked(BigDecimal.ZERO);
		}
		if (detail.getUomQuantityShiped() != null && detail.getUomQuantityShiped().compareTo(BigDecimal.ZERO) > 0) {
			detail.setQuantityShiped(detail.getUomQuantityShiped().multiply(uomQuantity));
		}
		if (detail.getQuantityShiped() == null) {
			detail.setQuantityShiped(BigDecimal.ZERO);
		}
		*/
		
		if(detail.getAllocateStrategyId() != null || StringUtils.isNotEmpty(detail.getAllocateStrategyCode())){
			AllocateStrategyTEntity allocateStrategy = allocateStrategyHeaderService.find(AllocateStrategyTEntity.builder()
					.warehouseId(outboundVO.getWarehouseId())
					.companyId(outboundVO.getCompanyId())
					.allocateStrategyId(detail.getAllocateStrategyId())
					.allocateStrategyCode(detail.getAllocateStrategyCode())
					.build());
			detail.setAllocateStrategyCode(allocateStrategy.getAllocateStrategyCode());
			detail.setAllocateStrategyId(allocateStrategy.getAllocateStrategyId());
		}
		if (outboundVO.getOperatorType() != OperatorTypeEnum.Add 
				&& null != detail.getOutboundDetailId()  ) {
			return Boolean.TRUE;
		}

		if(detail.getQuantityExpected().compareTo(BigDecimal.ZERO) <= 0){
			throw new BusinessServiceException("OutboundDetailServiceImpl", "outbound.expectedquantity.equaltozero", new Object[]{outboundVO.getOutboundNumber(), detail.getLineNumber()});
		}
		
		//新增订单数量要默认预期数量
		if (detail.getQuantityOrder() == null 
				|| BigDecimal.ZERO.compareTo(detail.getQuantityOrder()) == 0){
			detail.setQuantityOrder(detail.getQuantityExpected());
		}

		OwnerTEntity owner = ownerService.find(OwnerTEntity.builder()
				.warehouseId(outboundVO.getWarehouseId())
				.companyId(outboundVO.getCompanyId())
				.ownerId(outboundVO.getOwnerId())
				.ownerCode(outboundVO.getOwnerCode())
				.build());
		detail.setOwnerCode(owner.getOwnerCode());
		detail.setOwnerId(owner.getOwnerId());

		SkuTEntity sku = skuService.find(SkuTEntity.builder()
				.warehouseId(detail.getWarehouseId())
				.companyId(detail.getCompanyId())
				.skuId(detail.getSkuId())
				.skuCode(detail.getSkuCode())
				.ownerCode(detail.getOwnerCode())
				.build());
		detail.setSkuId(sku.getSkuId());
		detail.setSkuAlias(sku.getSkuAlias());
		detail.setSkuCode(sku.getSkuCode());
		detail.setSkuDescr(sku.getSkuDescr());
		
		OutboundDetailTEntity selectDetail = null;
		try {
			selectDetail = find(detail);
		} catch (BusinessServiceException e) {}
		if (selectDetail != null) {
			throw new BusinessServiceException("OutboundDetailServiceImpl", "outbound.linenumber.exists", new Object[]{outboundVO.getOutboundNumber(), detail.getLineNumber()});
		}
		return Boolean.TRUE;
	}

	@Override
	public void importData(AjaxRequest<List<OutboundDetailImportVO>> request) throws BusinessServiceException {
		List<OutboundDetailImportVO> list = request.getData();
		if (CollectionUtils.isEmpty(list)) {
			throw new BusinessServiceException("no data.");
		}
		//如果存在单号就进行更新
		Map<String, List<OutboundDetailImportVO>> headerMap = list.stream().filter(v -> StringUtils.isNotEmpty(v.getOutboundNumber()))
				.collect(Collectors.groupingBy(OutboundDetailImportVO::getOutboundNumber));

		headerMap.forEach((k, v) -> {
			if (CollectionUtils.isEmpty(v)) {
				return;
			}
			OutboundHeaderTEntity header = null;
			try {
				header = outboundHeaderService.find(OutboundHeaderTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).outboundNumber(k).build());
			} catch (Exception e) {
				String message = e.getMessage();
				log.error(message, e);
				v.forEach(d -> {
					d.setSuccess(YesNoEnum.No.getCode());
					d.setProcessMessage(message);
				});
				return;
			}
			List<OutboundDetailTEntity> oriDetail = findByHeaderId(OutboundDetailTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.outboundHeaderId(header.getOutboundHeaderId())
					.build());
			long maxLinenumber = DefaultConstants.LINE_INCREMENT;

			if (CollectionUtils.isNotEmpty(oriDetail)) {
				OutboundDetailTEntity maxDetail = oriDetail.stream().max(new Comparator<OutboundDetailTEntity>() {
					@Override
					public int compare(OutboundDetailTEntity o1, OutboundDetailTEntity o2) {
						return (o1.getLineNumber() - o2.getLineNumber()) > 0 ? 1 : 0;
					}
				}).get();
				maxLinenumber = maxDetail.getLineNumber();
			}

			OutboundVO outbound = new OutboundVO();
			outbound.setOperatorType(OperatorTypeEnum.Modify);
			OutboundDetailImportVO detailFirst = v.get(0);
			BeanUtils.copyBeanProp(outbound, detailFirst);
			try {
				//更新表头
				outbound.setOutboundHeaderId(header.getOutboundHeaderId());
				outboundHeaderService.save(new AjaxRequest<OutboundVO>(outbound, request));
			} catch (Exception e) {
				String message = e.getMessage();
				log.error(message, e);
				v.forEach(d -> {
					d.setSuccess(YesNoEnum.No.getCode());
					d.setProcessMessage(message);
				});
				return;
			}
			for (OutboundDetailImportVO outboundDetailImportVO : v) {
				try {
					OutboundDetailTEntity existsDetail = null;
					long currentLineNumber = 0L;
					if (outboundDetailImportVO.getLineNumber() == null) {
						currentLineNumber = maxLinenumber + DefaultConstants.LINE_INCREMENT;
						maxLinenumber = currentLineNumber;
					} else {
						currentLineNumber = outboundDetailImportVO.getLineNumber();
						try {
							//验证行号是否存在
							existsDetail = find(OutboundDetailTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).outboundHeaderId(header.getOutboundHeaderId()).lineNumber(outboundDetailImportVO.getLineNumber()).build());
						} catch (BusinessServiceException e) {
							if (currentLineNumber > maxLinenumber)
								maxLinenumber = currentLineNumber;
						}
					}

					OutboundDetailVO vo = new OutboundDetailVO();
					BeanUtils.copyBeanProp(vo, outboundDetailImportVO);
					vo.setLineNumber(currentLineNumber);

					outbound = new OutboundVO(header);
					//状态后台自动计算不传
					vo.setStatus(null);
					if (existsDetail == null) {
						//分配、拣选、发货数量不可修改
						vo.setUomQuantityShiped(BigDecimal.ZERO);
						vo.setUomQuantityPicked(BigDecimal.ZERO);
						vo.setUomQuantityAllocated(BigDecimal.ZERO);
						outbound.setOperatorType(OperatorTypeEnum.Add);
					} else {
						OutboundDetailVO existsDetailVO =new OutboundDetailVO(existsDetail);
						vo.setOutboundHeaderId(outbound.getOutboundHeaderId());
						vo.setOutboundDetailId(existsDetail.getOutboundDetailId());
						//分配、拣选、发货数量不可修改
						vo.setUomQuantityShiped(existsDetailVO.getUomQuantityShiped());
						vo.setUomQuantityPicked(existsDetailVO.getUomQuantityPicked());
						vo.setUomQuantityAllocated(existsDetailVO.getUomQuantityAllocated());
						outbound.setOperatorType(OperatorTypeEnum.Modify);
					}
					outbound.setDetail(Lists.newArrayList(vo));
					request.setUserName((String) request.get(OutboundHeaderTEntity.Column.updateBy.getJavaProperty()));
					save(new AjaxRequest<OutboundVO>(outbound, request));
					outboundDetailImportVO.setSuccess(YesNoEnum.Yes.getCode());
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					outboundDetailImportVO.setProcessMessage(e.getMessage());
					outboundDetailImportVO.setSuccess(YesNoEnum.No.getCode());
				}
			}
			//更新表头
			outboundHeaderService.outboundStatus(header, Boolean.TRUE);
		});

		//按来源单号进行分组
		headerMap = list.stream().filter(v -> StringUtils.isEmpty(v.getOutboundNumber()))
				.collect(Collectors.groupingBy(OutboundDetailImportVO::getSourceNumber));

		headerMap.forEach((k, v) -> {
			if (CollectionUtils.isEmpty(v)) {
				return;
			}
			//获取行
			long maxLinenumber = 0L;
			OutboundVO outbound = new OutboundVO();
			outbound.setOperatorType(OperatorTypeEnum.Add);
			OutboundDetailImportVO detailFirst = v.get(0);
			BeanUtils.copyBeanProp(outbound, detailFirst);

			try {
				//更新表头
				outboundHeaderService.save(new AjaxRequest<OutboundVO>(outbound, request));
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
				OutboundDetailImportVO d = v.get(i);
				try {
					OutboundDetailTEntity existsDetail = null;
					long currentLineNumber = 0L;
					if (d.getLineNumber() == null) {
						currentLineNumber = maxLinenumber + DefaultConstants.LINE_INCREMENT;
						maxLinenumber = currentLineNumber;
					} else {
						try {
							//验证行号是否存在
							existsDetail = find(OutboundDetailTEntity.builder()
									.warehouseId(request.getWarehouseId())
									.companyId(request.getCompanyId())
									.outboundHeaderId(outbound.getOutboundHeaderId())
									.lineNumber(d.getLineNumber())
									.build());
						} catch (BusinessServiceException e) {
							currentLineNumber = d.getLineNumber();
							if (currentLineNumber > maxLinenumber)
								maxLinenumber = currentLineNumber;
						}
					}

					OutboundDetailVO vo = new OutboundDetailVO();
					BeanUtils.copyBeanProp(vo, d);
					vo.setLineNumber(currentLineNumber);
					//状态后台自动计算不传
					vo.setStatus(null);
					if (existsDetail == null) {
						//分配、拣选、发货数量不可修改
						vo.setUomQuantityShiped(BigDecimal.ZERO);
						vo.setUomQuantityPicked(BigDecimal.ZERO);
						vo.setUomQuantityAllocated(BigDecimal.ZERO);
						outbound.setOperatorType(OperatorTypeEnum.Add);
					} else {
						OutboundDetailVO existsDetailVO =new OutboundDetailVO(existsDetail);
						//分配、拣选、发货数量不可修改
						vo.setUomQuantityShiped(existsDetailVO.getUomQuantityShiped());
						vo.setUomQuantityPicked(existsDetailVO.getUomQuantityPicked());
						vo.setUomQuantityAllocated(existsDetailVO.getUomQuantityAllocated());
						outbound.setOperatorType(OperatorTypeEnum.Modify);
					}
					outbound.setDetail(Lists.newArrayList(vo));
					save(new AjaxRequest<OutboundVO>(outbound, request));
					d.setSuccess(YesNoEnum.Yes.getCode());
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					d.setProcessMessage(e.getMessage());
					d.setSuccess(YesNoEnum.No.getCode());
				}
			}
			//更新表头
			outboundHeaderService.outboundStatus(outbound, Boolean.TRUE);
		});

	}

	@Override
	public ExcelTemplate getExcelTemplate() {
		return new ExcelTemplate<OutboundDetailImportVO>(ExcelTemplateEnum.Outbound.getCode(), OutboundDetailImportVO.class);
	}

	@Override
	public List<OutboundDetailImportVO> exportData(PageRequest request) throws BusinessServiceException {
		final int MAX_SIZE = 999;
		List<OutboundHeaderTEntity> header = outboundHeaderService.find(request);
		if (CollectionUtils.isEmpty(header)) {
			return Lists.newArrayList();
		}
		Set<Long> headerIds = header.stream().map(OutboundHeaderTEntity::getOutboundHeaderId).collect(Collectors.toSet());
		Map<Long, OutboundHeaderTEntity> headerMap = header.stream().collect(Collectors.toMap(OutboundHeaderTEntity::getOutboundHeaderId, (v) -> v));
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

		List<OutboundDetailImportVO> returnList = Lists.newArrayList();

		mulSetList.forEach(idSets -> {
			List<OutboundDetailTEntity> list = findByHeaderIds(OutboundDetailTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).build(), idSets);
			list.forEach(d -> {
				OutboundDetailImportVO vo = new OutboundDetailImportVO();
				OutboundHeaderTEntity h = headerMap.get(d.getOutboundHeaderId());
				OutboundDetailVO detailVo = new OutboundDetailVO(d);
				BeanUtils.copyBeanProp(vo, h);
				BeanUtils.copyBeanProp(vo, detailVo);
				vo.setOutboundNumber(h.getOutboundNumber());
				returnList.add(vo);
			});
		});
		return returnList;
	}

	@Override
	public List<OutboundDetailTEntity> findByHeaderId(Long headId) {
		OutboundDetailTExample TExample = new OutboundDetailTExample();
		TExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andOutboundHeaderIdEqualTo(headId);
		List<OutboundDetailTEntity> outboundDetailList = outboundDetailDao.selectByExample(TExample);
		return outboundDetailList;
	}

	@Override
	public Long findMaxLine(OutboundDetailTEntity outbound) throws BusinessServiceException {
		if (outbound.getOutboundHeaderId() == null)
			return DefaultConstants.LINE_INCREMENT;
		
		List<OutboundDetailTEntity> allDetail = findByHeaderId(outbound);
		if (CollectionUtils.isEmpty(allDetail))
			return DefaultConstants.LINE_INCREMENT;
		
		OutboundDetailTEntity maxLine = allDetail.stream().max(new Comparator<OutboundDetailTEntity>() {
			@Override
			public int compare(OutboundDetailTEntity o1, OutboundDetailTEntity o2) {
				return o1.getLineNumber().compareTo(o2.getLineNumber());
			}
		}).get();
		
		long maxLineNumber = maxLine.getLineNumber() + DefaultConstants.LINE_INCREMENT;
		
		return maxLineNumber;
	}
}
