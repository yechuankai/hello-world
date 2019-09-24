package com.wms.services.inventory.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import com.wms.common.constants.DefaultConstants;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.AdjustmentReasonEnum;
import com.wms.common.enums.CountStatusEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IInventoryCountDetailTDao;
import com.wms.dao.example.InventoryCountDetailTExample;
import com.wms.entity.auto.InventoryCountDetailTEntity;
import com.wms.entity.auto.InventoryCountHeaderTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.services.inventory.IAdjustmentDetailService;
import com.wms.services.inventory.IInventoryCountDetailService;
import com.wms.services.inventory.IInventoryCountHeaderService;
import com.wms.services.inventory.IInventoryService;
import com.wms.vo.adjustment.AdjustmentDetailVO;
import com.wms.vo.adjustment.AdjustmentVO;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InventoryCountDetailServiceImpl implements IInventoryCountDetailService {

	@Autowired
	private IInventoryCountHeaderService headerService;
	
	@Autowired
	private IInventoryCountDetailTDao detailDao;
	
	@Autowired
	private IInventoryService inventoryService;
	
	@Autowired
	private IAdjustmentDetailService adjustmentDetailService;
	
	@Override
	@Transactional
	public Boolean add(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data add.");
		
		List<InventoryCountDetailTEntity> detailList = request.getData();
		detailList.forEach(d -> {
			d.setInventoryCountDetailId(KeyUtils.getUID());
			d.setStatus(CountStatusEnum.New.getCode());
			d.setCompanyId(request.getCompanyId());
			d.setWarehouseId(request.getWarehouseId());
			d.setCreateBy(request.getUserName());
			d.setUpdateBy(request.getUserName());
			d.setCreateTime(new Date());
			d.setUpdateTime(new Date());
			int rowcount = detailDao.insertSelective(d);
			if (rowcount == 0)
				throw new BusinessServiceException("record add error.");
		});
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean delete(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data delete.");
		
		List<InventoryCountDetailTEntity> countList = request.getData();
		countList.forEach(c -> {
			c.setWarehouseId(request.getWarehouseId());
			c.setCompanyId(request.getCompanyId());
			InventoryCountDetailTEntity countDetail = find(c);
			//判断状态
			notProcess(countDetail);
			//判断是否存在复盘未完成的任务
			List<InventoryCountDetailTEntity> replayCountList = replayCountDetail(countDetail, CountStatusEnum.New, CountStatusEnum.Counting, CountStatusEnum.Replay);
			if (CollectionUtils.isNotEmpty(replayCountList))
				throw new BusinessServiceException("InventoryCountDetailServiceImpl", "countdetail.has.replaycount" , new Object[] {countDetail.getLineNumber()});
				
			// cancel header
			InventoryCountDetailTExample TExample = new InventoryCountDetailTExample();
			InventoryCountDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();
			
			TExampleCriteria
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andInventoryCountDetailIdEqualTo(countDetail.getInventoryCountDetailId());
			
			InventoryCountDetailTEntity update = InventoryCountDetailTEntity.builder()
													.delFlag(YesNoEnum.Yes.getCode())
													.updateBy(request.getUserName())
													.updateTime(new Date())
													.build();
			
			int rowcount = detailDao.updateWithVersionByExampleSelective(countDetail.getUpdateVersion(), update, TExample);
			if (rowcount == 0)
				throw new BusinessServiceException("record cancel error.");
		});
		
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean modify(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException {
		//PC端保存则调用提交逻辑
		submit(request);
		
		//获取第一行表头，更新状态
		long hearderId = request.getData().get(0).getInventoryCountHeaderId();
		InventoryCountHeaderTEntity header = InventoryCountHeaderTEntity.builder()
				.warehouseId(request.getWarehouseId())
				.companyId(request.getCompanyId())
				.inventoryCountHeaderId(hearderId)
				.build();
		headerService.countStatus(header, Boolean.TRUE);
		
		return Boolean.TRUE;
	}
	
	private Boolean notProcess(InventoryCountDetailTEntity detail) {
		if (CountStatusEnum.Cancel.getCode().equals(detail.getStatus())
				|| CountStatusEnum.Post.getCode().equals(detail.getStatus())) {
			throw new BusinessServiceException("InventoryCountDetailServiceImpl", "countdetail.status.not.process" , new Object[] {detail.getLineNumber()});
		}
		return Boolean.TRUE;
	}
	
	
	/**
	 * 复盘任务
	 * @param header
	 * @return
	 */
	@Override
	public List<InventoryCountDetailTEntity> replayCountDetail(InventoryCountDetailTEntity detail, CountStatusEnum ... countStatusEnums ) {
		//查询复盘单
		InventoryCountHeaderTEntity header = InventoryCountHeaderTEntity.builder()
												.warehouseId(detail.getWarehouseId())
												.companyId(detail.getCompanyId())
												.inventoryCountHeaderId(detail.getInventoryCountHeaderId())
												.build();
		List<InventoryCountHeaderTEntity> countList = headerService.replayCount(header, countStatusEnums);
		if (CollectionUtils.isEmpty(countList))
			return Lists.newArrayList();
		
		List<Long> headerIds = countList.stream().map(InventoryCountHeaderTEntity::getInventoryCountHeaderId).collect(Collectors.toList());
		
		InventoryCountDetailTExample TExample = new InventoryCountDetailTExample();
		InventoryCountDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		TExampleCriteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(header.getWarehouseId())
		.andCompanyIdEqualTo(header.getCompanyId())
		.andSourceLineNumberEqualTo(detail.getLineNumber())
		.andInventoryCountHeaderIdIn(headerIds);
		
		if (countStatusEnums != null) {
			List<String> status = Lists.newArrayList();
			for (CountStatusEnum c : countStatusEnums) {
				status.add(c.getCode());
			}
			TExampleCriteria.andStatusIn(status);
		}
		List<InventoryCountDetailTEntity> countDetailList = detailDao.selectByExample(TExample);
		return countDetailList;
	}

	@Override
	@Transactional
	public Boolean cancel(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data cancel.");
		
		List<InventoryCountDetailTEntity> countList = request.getData();
		countList.forEach(c -> {
			c.setWarehouseId(request.getWarehouseId());
			c.setCompanyId(request.getCompanyId());
			InventoryCountDetailTEntity countDetail = find(c);
			//判断状态
			notProcess(countDetail);
			//判断是否存在复盘未完成的任务
			List<InventoryCountDetailTEntity> replayCountList = replayCountDetail(countDetail, CountStatusEnum.New, CountStatusEnum.Counting, CountStatusEnum.Replay);
			if (CollectionUtils.isNotEmpty(replayCountList))
				throw new BusinessServiceException("InventoryCountDetailServiceImpl", "countdetail.has.replaycount" , new Object[] {countDetail.getLineNumber()});
				
			/*
			//复盘任务已完成状态不可更新
			if (CountStatusEnum.Complated.getCode().equals(countDetail.getStatus())
					&& countDetail.getSourceLineNumber() != null)
				throw new BusinessServiceException("InventoryCountDetailServiceImpl", "replay.countdetail.is.complate" , new Object[] {countDetail.getLineNumber()});
			*/
			
			// cancel header
			InventoryCountDetailTExample TExample = new InventoryCountDetailTExample();
			InventoryCountDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();
			
			TExampleCriteria
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andInventoryCountDetailIdEqualTo(countDetail.getInventoryCountDetailId());
			
			InventoryCountDetailTEntity update = InventoryCountDetailTEntity.builder()
													.status(CountStatusEnum.Cancel.getCode())
													.updateBy(request.getUserName())
													.updateTime(new Date())
													.build();
			
			int rowcount = detailDao.updateWithVersionByExampleSelective(countDetail.getUpdateVersion(), update, TExample);
			if (rowcount == 0)
				throw new BusinessServiceException("record cancel error.");
		});
		
		return Boolean.TRUE;
	}

	@Override
	public List<InventoryCountDetailTEntity> find(PageRequest request) throws BusinessServiceException {
		InventoryCountDetailTExample TExample = new InventoryCountDetailTExample();
		InventoryCountDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(InventoryCountDetailTEntity.Column.class, InventoryCountDetailTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<InventoryCountDetailTEntity> countDetailList = detailDao.selectByExample(TExample);
		
		return countDetailList;
	}

	@Override
	public InventoryCountDetailTEntity find(InventoryCountDetailTEntity detail) throws BusinessServiceException {
		InventoryCountDetailTExample TExample = new InventoryCountDetailTExample();
		InventoryCountDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		TExampleCriteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(detail.getWarehouseId())
		.andCompanyIdEqualTo(detail.getCompanyId());
		
		if (detail.getInventoryCountDetailId() != null) {
			TExampleCriteria.andInventoryCountDetailIdEqualTo(detail.getInventoryCountDetailId());
		}else {
			TExampleCriteria.andInventoryCountHeaderIdEqualTo(detail.getInventoryCountHeaderId());
			TExampleCriteria.andLineNumberEqualTo(detail.getLineNumber());
		}
		InventoryCountDetailTEntity countDetail = detailDao.selectOneByExample(TExample);
		if (countDetail == null)
			throw new BusinessServiceException("InventoryCountDetailServiceImpl", "countdetail.not.exists" , new Object[] {detail.getInventoryCountDetailId()});
			
		return countDetail;
	}

	@Override
	public List<InventoryCountDetailTEntity> findByHeaderId(InventoryCountDetailTEntity detail, CountStatusEnum ... countStatusEnums)
			throws BusinessServiceException {
		InventoryCountDetailTExample TExample = new InventoryCountDetailTExample();
		InventoryCountDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		TExampleCriteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(detail.getWarehouseId())
		.andCompanyIdEqualTo(detail.getCompanyId())
		.andInventoryCountHeaderIdEqualTo(detail.getInventoryCountHeaderId());
		
		if (countStatusEnums.length > 0) {
			List<String> status = Lists.newArrayList();
			for (CountStatusEnum c : countStatusEnums) {
				status.add(c.getCode());
			}
			TExampleCriteria.andStatusIn(status);
		}
		
		if(detail.getLocationId() != null) {
			TExampleCriteria.andLocationIdEqualTo(detail.getLocationId());
		}
		if(detail.getSkuId() != null) {
			TExampleCriteria.andSkuIdEqualTo(detail.getSkuId());
		}
		if (StringUtils.isNotEmpty(detail.getLocationCode())) {
			TExampleCriteria.andLocationCodeEqualTo(detail.getLocationCode());
		}
		if (StringUtils.isNotEmpty(detail.getSkuCode())) {
			TExampleCriteria.andLocationCodeEqualTo(detail.getSkuCode());
		}
		
		
		List<InventoryCountDetailTEntity> countDetailList = detailDao.selectByExample(TExample);
		return countDetailList;
	}

	@Override
	@Transactional
	public Long createReplayCount(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("not data createReplayCount.");
		
		List<InventoryCountDetailTEntity> detailList = request.getData();
		List<InventoryCountDetailTEntity> newList = Lists.newArrayList();
		
		
		long headerId = 0L;
		for (InventoryCountDetailTEntity d : detailList) {
			d.setWarehouseId(request.getWarehouseId());
			d.setCompanyId(request.getCompanyId());
			InventoryCountDetailTEntity detail = find(d);
			//验证状态
			notProcess(detail);
			if (CountStatusEnum.Complated.getCode().equals(detail.getStatus())) {
				newList.add(detail);
				headerId = detail.getInventoryCountHeaderId();
			}
		}
		
		InventoryCountHeaderTEntity header = InventoryCountHeaderTEntity.builder()
												.warehouseId(request.getWarehouseId())
												.companyId(request.getCompanyId())
												.inventoryCountHeaderId(headerId)
												.build();
		
		long detailSize = headerService.createReplayCount(new AjaxRequest<InventoryCountHeaderTEntity>(header, request), detailList);
		
		return detailSize;
	}

	@Override
	@Transactional
	public Boolean submit(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("not data submit.");
		
		List<InventoryCountDetailTEntity> detailList = request.getData();
		long headerId = 0L;
		for (InventoryCountDetailTEntity d : detailList) {
			d.setWarehouseId(request.getWarehouseId());
			d.setCompanyId(request.getCompanyId());
			InventoryCountDetailTEntity detail = find(d);
			//验证状态
			notProcess(detail);
			
			//复盘状态则不可更新
			if (CountStatusEnum.Replay.getCode().equals(detail.getStatus())) 
				throw new BusinessServiceException("InventoryCountDetailServiceImpl", "countdetail.is.replaystatus" , new Object[] {detail.getLineNumber()});
			
			//复盘任务已完成状态不可更新
			if (CountStatusEnum.Complated.getCode().equals(detail.getStatus())
					&& d.getSourceLineNumber() != null)
				throw new BusinessServiceException("InventoryCountDetailServiceImpl", "replay.countdetail.is.complate" , new Object[] {detail.getLineNumber()});
			
			//更新状态、盘点数量、系统数量（如果前台传入不为空，则系统自动获取，试用于PC）// cancel header
			InventoryCountDetailTEntity update = InventoryCountDetailTEntity.builder()
													.status(CountStatusEnum.Complated.getCode())
													.reason(d.getReason())
													.remark(d.getRemark())
													.updateBy(request.getUserName())
													.updateTime(new Date())
													.build();
			
			BigDecimal quantitySystem = detail.getQuantitySystem();
			BigDecimal quantityDifference = BigDecimal.ZERO;
			//前台传入为空，则通过系统获取
			if (d.getQuantitySystem() == null 
					|| BigDecimal.ZERO.compareTo(quantitySystem) == 0) {
				try {
					InventoryOnhandTEntity onhand = inventoryService.find(InventoryOnhandTEntity.builder()
							.companyId(request.getCompanyId())
							.warehouseId(request.getWarehouseId())
							.inventoryOnhandId(detail.getInventoryOnhandId())
							.build());
					quantitySystem = onhand.getQuantityOnhand();
				} catch (BusinessServiceException e) {
					quantitySystem = BigDecimal.ZERO;
				}
			}
			
			//计算差异数量
			quantityDifference = d.getQuantityCount().subtract(quantitySystem);
			update.setQuantitySystem(quantitySystem);
			update.setQuantityCount(d.getQuantityCount());
			update.setQuantityDifference(quantityDifference);
			if (BigDecimal.ZERO.compareTo(d.getQuantityConfirm()) == 0)
				update.setQuantityConfirm(quantityDifference);
			else
				update.setQuantityConfirm(d.getQuantityConfirm());
			
			InventoryCountDetailTExample TExample = new InventoryCountDetailTExample();
			InventoryCountDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();
			
			TExampleCriteria
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andInventoryCountDetailIdEqualTo(detail.getInventoryCountDetailId());
			
			int rowcount = detailDao.updateWithVersionByExampleSelective(detail.getUpdateVersion(), update, TExample);
			if (rowcount == 0)
				throw new BusinessServiceException("record submit error.");
			
			detail.setQuantitySystem(quantitySystem);
			detail.setQuantityCount(d.getQuantityCount());
			detail.setQuantityDifference(quantityDifference);
			update.setQuantityConfirm(quantityDifference);
			//一次提交要求必须是一个单据
			headerId = detail.getInventoryCountHeaderId();
		}
		
		//判断当前是否为复盘任务
		InventoryCountHeaderTEntity header = InventoryCountHeaderTEntity.builder()
																		.warehouseId(request.getWarehouseId())
																		.companyId(request.getCompanyId())
																		.inventoryCountHeaderId(headerId)
																		.build();
		//更新父项任务（如果当前盘点单位复盘任务则需要递归更新父项任务）
		submitParentCount(new AjaxRequest<List<InventoryCountDetailTEntity>>(detailList, request), header);
		
		return Boolean.TRUE;
	}
	
	/**
	 * 递归更新父项盘点行
	 * @param request
	 * @param header
	 * @return
	 */
	private Boolean submitParentCount(AjaxRequest<List<InventoryCountDetailTEntity>> request, InventoryCountHeaderTEntity header) {
		if (CollectionUtils.isEmpty(request.getData()))
			return Boolean.FALSE;
		
		header = headerService.find(header);
		if (!YesNoEnum.Yes.getCode().equals(header.getReplayFlag()))
			return Boolean.FALSE;
		
		List<InventoryCountDetailTEntity> detailList = request.getData();
		Set<Long> sourceLineNumberSets =  detailList.stream().map(InventoryCountDetailTEntity::getSourceLineNumber).collect(Collectors.toSet());
		Map<Long, InventoryCountDetailTEntity> detailMap = detailList.stream().collect(Collectors.toMap(InventoryCountDetailTEntity::getSourceLineNumber, v -> v));
		
		//查询父项盘点行
		InventoryCountDetailTExample TExample = new InventoryCountDetailTExample();
		InventoryCountDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		TExampleCriteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(request.getWarehouseId())
		.andCompanyIdEqualTo(request.getCompanyId())
		.andInventoryCountHeaderIdEqualTo(header.getParentCountHeaderId())
		.andLineNumberIn(Lists.newArrayList(sourceLineNumberSets));
		
		List<InventoryCountDetailTEntity> parentLineList = detailDao.selectByExample(TExample);
		if (CollectionUtils.isEmpty(parentLineList))
			return Boolean.FALSE;
		
		//更新父项盘点行状态、复盘数量
		parentLineList.forEach(pd -> {
			
			//原始提交行
			InventoryCountDetailTEntity countDetail = detailMap.get(pd.getLineNumber());
			if (countDetail == null)
				return;
			
			//开始更新父行
			InventoryCountDetailTEntity update = InventoryCountDetailTEntity.builder()
					.quantityReplay(countDetail.getQuantityCount())
					.quantitySystem(countDetail.getQuantitySystem())
					.quantityDifference(countDetail.getQuantityDifference())
					.quantityConfirm(countDetail.getQuantityConfirm())
					.status(CountStatusEnum.Complated.getCode())
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.build();

			
			InventoryCountDetailTExample newExample = new InventoryCountDetailTExample();
			InventoryCountDetailTExample.Criteria newExampleCriteria  = newExample.createCriteria();
			
			newExampleCriteria
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andInventoryCountDetailIdEqualTo(pd.getInventoryCountDetailId());
			
			int rowcount = detailDao.updateWithVersionByExampleSelective(pd.getUpdateVersion(), update, newExample);
			if (rowcount == 0)
				throw new BusinessServiceException("parent record submit error.");
			
			pd.setQuantitySystem(countDetail.getQuantitySystem());
			pd.setQuantityDifference(countDetail.getQuantityDifference());
			pd.setQuantityCount(countDetail.getQuantityCount());
			pd.setQuantityConfirm(countDetail.getQuantityConfirm());
		});
		
		//更新状态
		InventoryCountHeaderTEntity updateHeader = InventoryCountHeaderTEntity.builder()
				.warehouseId(request.getWarehouseId())
				.companyId(request.getCompanyId())
				.inventoryCountHeaderId(header.getParentCountHeaderId())
				.build();
		headerService.countStatus(updateHeader, Boolean.TRUE);
		
		//判断父项是否也为复盘任务
		InventoryCountHeaderTEntity parentHeader = InventoryCountHeaderTEntity.builder()
				.warehouseId(request.getWarehouseId())
				.companyId(request.getCompanyId())
				.inventoryCountHeaderId(header.getParentCountHeaderId())
				.build();
		
		//继续更新复盘任务
		boolean updateFlag = submitParentCount(new AjaxRequest<List<InventoryCountDetailTEntity>>(parentLineList, request), parentHeader);
		return updateFlag;
	}

	@Override
	@Transactional
	public Boolean modifyStatus(AjaxRequest<List<InventoryCountDetailTEntity>> request, CountStatusEnum status)
			throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data modify.");
		
		request.getData().forEach(d -> {
			d.setWarehouseId(request.getWarehouseId());
			d.setCompanyId(request.getCompanyId());
			InventoryCountDetailTEntity detail = find(d);
			//验证状态
			notProcess(detail);
			
			InventoryCountDetailTExample TExample = new InventoryCountDetailTExample();
			InventoryCountDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();
			
			TExampleCriteria
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andInventoryCountDetailIdEqualTo(detail.getInventoryCountDetailId());
			
			InventoryCountDetailTEntity update = InventoryCountDetailTEntity.builder()
													.status(status.getCode())
													.updateBy(request.getUserName())
													.updateTime(new Date())
													.build();
			int rowcount = detailDao.updateWithVersionByExampleSelective(detail.getUpdateVersion(), update, TExample);
			if (rowcount == 0)
				throw new BusinessServiceException("record modify error.");
		});
		return Boolean.TRUE;
	}

	@Override
	public Boolean post(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data post.");
		
		List<InventoryCountDetailTEntity> postDetail = Lists.newArrayList();
		
		request.getData().forEach(d -> {
			d.setWarehouseId(request.getWarehouseId());
			d.setCompanyId(request.getCompanyId());
			InventoryCountDetailTEntity detail = find(d);
			//验证状态
			notProcess(detail);
			
			//有差异的执行调整
			if (BigDecimal.ZERO.compareTo(d.getQuantityConfirm()) != 0) {
				postDetail.add(detail);
			}
			
			InventoryCountDetailTExample TExample = new InventoryCountDetailTExample();
			InventoryCountDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();
			
			TExampleCriteria
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andInventoryCountDetailIdEqualTo(detail.getInventoryCountDetailId());
			
			InventoryCountDetailTEntity update = InventoryCountDetailTEntity.builder()
													.status(CountStatusEnum.Post.getCode())
													.updateBy(request.getUserName())
													.updateTime(new Date())
													.build();
			int rowcount = detailDao.updateWithVersionByExampleSelective(detail.getUpdateVersion(), update, TExample);
			if (rowcount == 0)
				throw new BusinessServiceException("record modify error.");
		});
		
		//处理调整
		Map<Long, List<InventoryCountDetailTEntity>> postDetailMap = postDetail.stream().collect(Collectors.groupingBy(InventoryCountDetailTEntity::getInventoryCountHeaderId));
		postDetailMap.forEach((k, v) -> {
			if (CollectionUtils.isEmpty(v))
				return;
			
			InventoryCountHeaderTEntity header = headerService.find(InventoryCountHeaderTEntity.builder()
																.warehouseId(request.getWarehouseId())
																.companyId(request.getCompanyId())
																.inventoryCountHeaderId(k)
																.build());
			AdjustmentVO adjustment = new AdjustmentVO();
			adjustment.setSourceNumber(header.getCountNumber());
			List<AdjustmentDetailVO> adjustmentDetail = Lists.newArrayList();
			long adjustmentLineNumber = 0;
			for (InventoryCountDetailTEntity d : v) {
				//行号累加
				adjustmentLineNumber += DefaultConstants.LINE_INCREMENT;

				AdjustmentDetailVO detailVO = new AdjustmentDetailVO();
				BeanUtils.copyBeanProp(detailVO, d, Boolean.FALSE);
				detailVO.setLineNumber(adjustmentLineNumber);
				detailVO.setUpdateBy(request.getUserName());
				detailVO.setCreateBy(request.getUserName());
				detailVO.setSourceLineNumber(String.valueOf(d.getLineNumber()));
				detailVO.setSourceNumber(String.valueOf(d.getInventoryCountDetailId()));
				detailVO.setQuantityAdjustment(d.getQuantityConfirm());
				detailVO.setReason(AdjustmentReasonEnum.Cc.getCode());
				detailVO.setTransactionCategory(AdjustmentReasonEnum.Cc.getCode());
				adjustmentDetail.add(detailVO);
			}
			adjustment.setDetail(adjustmentDetail);
			
			adjustmentDetailService.save(new AjaxRequest<AdjustmentVO>(adjustment, request));
		});
		return Boolean.TRUE;
	}

}
