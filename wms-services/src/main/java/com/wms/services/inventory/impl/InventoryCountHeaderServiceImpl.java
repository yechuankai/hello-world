package com.wms.services.inventory.impl;

import java.util.Date;
import java.util.List;
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
import com.wms.common.enums.CountStatusEnum;
import com.wms.common.enums.CountTypeEnum;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IInventoryCountHeaderTDao;
import com.wms.dao.example.InventoryCountHeaderTExample;
import com.wms.entity.auto.InventoryCountDetailTEntity;
import com.wms.entity.auto.InventoryCountHeaderTEntity;
import com.wms.entity.auto.InventoryCountRequestTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.services.inventory.IInventoryCountDetailService;
import com.wms.services.inventory.IInventoryCountHeaderService;
import com.wms.services.inventory.IInventoryCountRequestService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InventoryCountHeaderServiceImpl implements IInventoryCountHeaderService {

	@Autowired
	private IInventoryCountRequestService requestService;
	
	@Autowired
	private IInventoryCountDetailService detailService;
	
	@Autowired
	private IInventoryCountHeaderTDao headerDao;
	
	@Override
	@Transactional
	public Long createInventoryCount(AjaxRequest<InventoryCountRequestTEntity> request)
			throws BusinessServiceException {
		
		InventoryCountRequestTEntity countRequest = request.getData();
		countRequest = requestService.find(countRequest);
		
		//查询库存
		List<InventoryOnhandTEntity> inventoryList = requestService.findInventory(countRequest);
		
		
		if (CollectionUtils.isEmpty(inventoryList))
			return 0L;
		
		InventoryCountHeaderTEntity countHeader = InventoryCountHeaderTEntity.builder()
													.companyId(request.getCompanyId())
													.warehouseId(request.getWarehouseId())
													.inventoryCountRequestId(countRequest.getInventoryCountRequestId())
													.type(CountTypeEnum.Normal.getCode())
													.replayFlag(YesNoEnum.No.getCode())
													.status(CountStatusEnum.New.getCode())
													.build();
		//add header
		add(new AjaxRequest<InventoryCountHeaderTEntity>(countHeader, request));
		
		//add detail
		List<InventoryCountDetailTEntity> detailList = Lists.newArrayList();
		
		long lineNumber = 0;
		for (InventoryOnhandTEntity onhand : inventoryList) {
			lineNumber = lineNumber + DefaultConstants.LINE_INCREMENT;
			InventoryCountDetailTEntity detail = new InventoryCountDetailTEntity();
			BeanUtils.copyBeanProp(detail, onhand, Boolean.FALSE);
			detail.setInventoryCountHeaderId(countHeader.getInventoryCountHeaderId());
			detail.setLineNumber(lineNumber);
			
			//判断明盘
			if (YesNoEnum.Yes.getCode().equals(countRequest.getQuantityShowFlag())) 
				detail.setQuantitySystem(onhand.getQuantityOnhand());
			
			detailList.add(detail);
		}
		
		detailService.add(new AjaxRequest<List<InventoryCountDetailTEntity>>(detailList, request));
		
		//更新最后请求日期
		InventoryCountRequestTEntity requestUpdate = InventoryCountRequestTEntity.builder()
														.requestDate(new Date())
														.inventoryCountRequestId(countRequest.getInventoryCountRequestId())
														.updateVersion(countRequest.getUpdateVersion())
														.build();
		requestService.modify(new AjaxRequest<List<InventoryCountRequestTEntity>>(Lists.newArrayList(requestUpdate), request));
		
		long detailSize = detailList.size();
		
		return detailSize;
	}
	
	@Override
	@Transactional
	public Boolean add(AjaxRequest<InventoryCountHeaderTEntity> request) throws BusinessServiceException {
		String countNumber = KeyUtils.getOrderNumber(request.getCompanyId(), request.getWarehouseId(), OrderNumberTypeEnum.CountNumber);
		InventoryCountHeaderTEntity header = request.getData();
		header.setInventoryCountHeaderId(KeyUtils.getUID());
		header.setCountNumber(countNumber);
		header.setCreateBy(request.getUserName());
		header.setUpdateBy(request.getUserName());
		header.setCreateTime(new Date());
		header.setUpdateTime(new Date());
		int rowcount = headerDao.insertSelective(header);
		if (rowcount == 0)
			throw new BusinessServiceException("record add error.");
		
		return Boolean.TRUE;
	}
	
	/**
	 * 过账与取消状态不可操作
	 * @param header
	 * @return
	 */
	private Boolean notProcess(InventoryCountHeaderTEntity header) {
		if (CountStatusEnum.Cancel.getCode().equals(header.getStatus())
				|| CountStatusEnum.Post.getCode().equals(header.getStatus())) {
			throw new BusinessServiceException("InventoryCountHeaderServiceImpl", "count.status.not.process" , new Object[] {header.getCountNumber()});
		}
		return Boolean.TRUE;
	}
	
	/**
	 * 复盘任务
	 * @param header
	 * @return
	 */
	@Override
	public List<InventoryCountHeaderTEntity> replayCount(InventoryCountHeaderTEntity header, CountStatusEnum ... countStatusEnums ) {
		InventoryCountHeaderTExample TExample = new InventoryCountHeaderTExample();
		InventoryCountHeaderTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		TExampleCriteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andReplayFlagEqualTo(YesNoEnum.Yes.getCode())
		.andWarehouseIdEqualTo(header.getWarehouseId())
		.andCompanyIdEqualTo(header.getCompanyId());
		
		if (StringUtils.isNotEmpty(header.getCountNumber()))
			TExampleCriteria.andParentCountNumberEqualTo(header.getCountNumber());
		
		if (header.getInventoryCountHeaderId() != null)
			TExampleCriteria.andParentCountHeaderIdEqualTo(header.getInventoryCountHeaderId());
		
		if (countStatusEnums != null) {
			List<String> status = Lists.newArrayList();
			for (CountStatusEnum c : countStatusEnums) {
				status.add(c.getCode());
			}
			TExampleCriteria.andStatusIn(status);
		}
		List<InventoryCountHeaderTEntity> countList = headerDao.selectByExample(TExample);
		return countList;
	}

	@Override
	@Transactional
	public Boolean cancel(AjaxRequest<List<InventoryCountHeaderTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data cancel.");
		
		List<InventoryCountHeaderTEntity> countList = request.getData();
		countList.forEach(c -> {
			c.setWarehouseId(request.getWarehouseId());
			c.setCompanyId(request.getCompanyId());
			InventoryCountHeaderTEntity count = find(c);
			//判断状态
			notProcess(count);
			//判断是否存在复盘未完成的任务
			List<InventoryCountHeaderTEntity> replayCountList = replayCount(count, CountStatusEnum.New, CountStatusEnum.Counting, CountStatusEnum.Replay);
			if (CollectionUtils.isNotEmpty(replayCountList))
				throw new BusinessServiceException("InventoryCountHeaderServiceImpl", "count.has.replaycount" , new Object[] {count.getCountNumber()});
				
			// cancel header
			InventoryCountHeaderTExample TExample = new InventoryCountHeaderTExample();
			InventoryCountHeaderTExample.Criteria TExampleCriteria  = TExample.createCriteria();
			
			TExampleCriteria
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andInventoryCountHeaderIdEqualTo(count.getInventoryCountHeaderId());
			
			InventoryCountHeaderTEntity update = InventoryCountHeaderTEntity.builder()
													.status(CountStatusEnum.Cancel.getCode())
													.updateBy(request.getUserName())
													.updateTime(new Date())
													.build();
			
			int rowcount = headerDao.updateWithVersionByExampleSelective(count.getUpdateVersion(), update, TExample);
			if (rowcount == 0)
				throw new BusinessServiceException("record cancel error.");
			
			//cancel detail
			List<InventoryCountDetailTEntity> countDetail = detailService.findByHeaderId(InventoryCountDetailTEntity.builder()
																							.warehouseId(request.getWarehouseId())
																							.companyId(request.getCompanyId())
																							.inventoryCountHeaderId(count.getInventoryCountHeaderId())
																							.build());
			if (CollectionUtils.isNotEmpty(countDetail))
				detailService.cancel(new AjaxRequest<List<InventoryCountDetailTEntity>>(countDetail, request));
			
		});
		
		return Boolean.TRUE;
	}


	@Override
	public List<InventoryCountHeaderTEntity> find(PageRequest request) throws BusinessServiceException {
		InventoryCountHeaderTExample TExample = new InventoryCountHeaderTExample();
		InventoryCountHeaderTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(InventoryCountHeaderTEntity.Column.class, InventoryCountHeaderTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<InventoryCountHeaderTEntity> countList = headerDao.selectByExample(TExample);
		
		return countList;
	}

	@Override
	@Transactional
	public InventoryCountHeaderTEntity find(InventoryCountHeaderTEntity header) throws BusinessServiceException {
		InventoryCountHeaderTExample TExample = new InventoryCountHeaderTExample();
		InventoryCountHeaderTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		TExampleCriteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(header.getWarehouseId())
		.andCompanyIdEqualTo(header.getCompanyId());
		
		if (StringUtils.isNotEmpty(header.getCountNumber()))
			TExampleCriteria.andCountNumberEqualTo(header.getCountNumber());
		
		if (header.getInventoryCountHeaderId() != null)
			TExampleCriteria.andInventoryCountHeaderIdEqualTo(header.getInventoryCountHeaderId());
		
		InventoryCountHeaderTEntity count = headerDao.selectOneByExample(TExample);
		if (count == null)
			throw new BusinessServiceException("InventoryCountRequestImpl", "count.not.exists" , new Object[] {header.getInventoryCountHeaderId() + "/" + count.getCountNumber()});
			
		return count;
	}
	
	@Override
	public String countStatus(InventoryCountHeaderTEntity header, Boolean updateFlag)
			throws BusinessServiceException {
		String status = null;
		List<InventoryCountDetailTEntity> detailList = detailService.findByHeaderId(InventoryCountDetailTEntity.builder()
				.warehouseId(header.getWarehouseId())
				.companyId(header.getCompanyId())
				.inventoryCountHeaderId(header.getInventoryCountHeaderId())
				.build());
		if (CollectionUtils.isEmpty(detailList)) {
			status = CountStatusEnum.New.getCode();
		}else {
			Set<String> detailStatusSet = detailList.stream().map(InventoryCountDetailTEntity::getStatus).collect(Collectors.toSet());
			if (detailStatusSet.size() == 1) {
				status = detailStatusSet.iterator().next();
			}else{
				status = CountStatusEnum.Counting.getCode();
			}
		}
		
		if (!updateFlag)
			return status;
		
		InventoryCountHeaderTEntity selectCount = find(header);
		
		InventoryCountHeaderTExample TExample = new InventoryCountHeaderTExample();
		InventoryCountHeaderTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		TExampleCriteria
		.andWarehouseIdEqualTo(selectCount.getWarehouseId())
		.andCompanyIdEqualTo(selectCount.getCompanyId())
		.andInventoryCountHeaderIdEqualTo(selectCount.getInventoryCountHeaderId());
		
		InventoryCountHeaderTEntity update = InventoryCountHeaderTEntity.builder()
												.status(status)
												.updateBy(header.getUpdateBy())
												.updateTime(new Date())
												.build();
		
		int rowcount = headerDao.updateWithVersionByExampleSelective(selectCount.getUpdateVersion(), update, TExample);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");
		
		return status;
	}

	@Override
	@Transactional
	public Boolean delete(AjaxRequest<List<InventoryCountHeaderTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data delete.");
		
		List<InventoryCountHeaderTEntity> countList = request.getData();
		countList.forEach(c -> {
			c.setWarehouseId(request.getWarehouseId());
			c.setCompanyId(request.getCompanyId());
			InventoryCountHeaderTEntity count = find(c);
			//判断状态
			notProcess(count);
			//判断是否存在复盘未完成的任务
			List<InventoryCountHeaderTEntity> replayCountList = replayCount(count, CountStatusEnum.New, CountStatusEnum.Counting, CountStatusEnum.Replay);
			if (CollectionUtils.isNotEmpty(replayCountList))
				throw new BusinessServiceException("InventoryCountHeaderServiceImpl", "count.has.replaycount" , new Object[] {count.getCountNumber()});
				
			// cancel header
			InventoryCountHeaderTExample TExample = new InventoryCountHeaderTExample();
			InventoryCountHeaderTExample.Criteria TExampleCriteria  = TExample.createCriteria();
			
			TExampleCriteria
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andInventoryCountHeaderIdEqualTo(count.getInventoryCountHeaderId());
			
			InventoryCountHeaderTEntity update = InventoryCountHeaderTEntity.builder()
													.delFlag(YesNoEnum.Yes.getCode())
													.updateBy(request.getUserName())
													.updateTime(new Date())
													.build();
			
			int rowcount = headerDao.updateWithVersionByExampleSelective(count.getUpdateVersion(), update, TExample);
			if (rowcount == 0)
				throw new BusinessServiceException("record delete error.");
			
			//cancel detail
			List<InventoryCountDetailTEntity> countDetail = detailService.findByHeaderId(InventoryCountDetailTEntity.builder()
																							.warehouseId(request.getWarehouseId())
																							.companyId(request.getCompanyId())
																							.inventoryCountHeaderId(count.getInventoryCountHeaderId())
																							.build());
			if (CollectionUtils.isNotEmpty(countDetail))
				detailService.delete(new AjaxRequest<List<InventoryCountDetailTEntity>>(countDetail, request));
			
		});
		
		return Boolean.TRUE;
	}

	/**
	 * 创建复盘任务
	 */
	@Override
	@Transactional
	public Long createReplayCount(AjaxRequest<InventoryCountHeaderTEntity> request) throws BusinessServiceException {
		InventoryCountHeaderTEntity count = request.getData();
		
		//查询盘点完成的明细行
		List<InventoryCountDetailTEntity> detailList = detailService.findByHeaderId(InventoryCountDetailTEntity.builder()
																							.warehouseId(request.getWarehouseId())
																							.companyId(request.getCompanyId())
																							.inventoryCountHeaderId(count.getInventoryCountHeaderId())
																							.build(), CountStatusEnum.Complated);
		long detailSize = createReplayCount(request, detailList);
		return detailSize;
	}
	
	/**
	 * 创建复盘任务
	 */
	@Override
	@Transactional
	public Long createReplayCount(AjaxRequest<InventoryCountHeaderTEntity> request, List<InventoryCountDetailTEntity> detailList) throws BusinessServiceException {
		
		InventoryCountHeaderTEntity count = request.getData();
		count.setWarehouseId(request.getWarehouseId());
		count.setCompanyId(request.getCompanyId());
		count = find(count);
		
		//验证状态不可操作
		notProcess(count);
		
		if (CollectionUtils.isEmpty(detailList))
			throw new BusinessServiceException("InventoryCountHeaderServiceImpl", "count.cannot.create.replay" , new Object[] {count.getCountNumber()});
		
		
		InventoryCountRequestTEntity countRequest = InventoryCountRequestTEntity.builder()
														.warehouseId(request.getWarehouseId())
														.companyId(request.getCompanyId())
														.inventoryCountRequestId(count.getInventoryCountRequestId())
														.build();
		countRequest = requestService.find(countRequest);
		
		InventoryCountHeaderTEntity countHeader = InventoryCountHeaderTEntity.builder()
				.companyId(request.getCompanyId())
				.warehouseId(request.getWarehouseId())
				.inventoryCountRequestId(countRequest.getInventoryCountRequestId())
				.parentCountHeaderId(count.getInventoryCountHeaderId())
				.parentCountNumber(count.getCountNumber())
				.type(CountTypeEnum.Normal.getCode())
				.replayFlag(YesNoEnum.Yes.getCode())
				.status(CountStatusEnum.New.getCode())
				.build();
		//add header
		add(new AjaxRequest<InventoryCountHeaderTEntity>(countHeader, request));
		
		//add detail
		List<InventoryCountDetailTEntity> newDetailList = Lists.newArrayList();
		long lineNumber = 0;
		for (InventoryCountDetailTEntity oriDetail : detailList) {
			lineNumber = lineNumber + DefaultConstants.LINE_INCREMENT;
			InventoryCountDetailTEntity detail = new InventoryCountDetailTEntity();
			BeanUtils.copyBeanProp(detail, oriDetail, Boolean.FALSE);
			detail.setInventoryCountHeaderId(countHeader.getInventoryCountHeaderId());
			detail.setLineNumber(lineNumber);
			detail.setSourceLineNumber(oriDetail.getLineNumber());
			
			//判断明盘
			if (YesNoEnum.Yes.getCode().equals(countRequest.getQuantityShowFlag())) 
				detail.setQuantitySystem(oriDetail.getQuantitySystem());
			
			newDetailList.add(detail);
		}
		detailService.add(new AjaxRequest<List<InventoryCountDetailTEntity>>(newDetailList, request));
		
		//更新当前行为复盘状态
		detailService.modifyStatus(new AjaxRequest<List<InventoryCountDetailTEntity>>(detailList, request), CountStatusEnum.Replay);
		
		long detailSize = newDetailList.size(); 
		return detailSize;
	}

	@Override
	@Transactional
	public Boolean post(AjaxRequest<List<InventoryCountHeaderTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data delete.");
		
		List<InventoryCountHeaderTEntity> countList = request.getData();
		countList.forEach(c -> {
			c.setWarehouseId(request.getWarehouseId());
			c.setCompanyId(request.getCompanyId());
			InventoryCountHeaderTEntity count = find(c);
			//判断状态
			notProcess(count);
			//判断当前是否为复盘任务
			if (YesNoEnum.Yes.getCode().equals(count.getReplayFlag())) {
				throw new BusinessServiceException("InventoryCountHeaderServiceImpl", "replaycount.not.post" , new Object[] {count.getCountNumber()});
			}
			
			//获取所有明细
			List<InventoryCountDetailTEntity> detailList = detailService.findByHeaderId(InventoryCountDetailTEntity.builder()
															.warehouseId(request.getWarehouseId())
															.companyId(request.getCompanyId())
															.inventoryCountHeaderId(count.getInventoryCountHeaderId())
															.build());
			Set<String> statusSet = detailList.stream().map(InventoryCountDetailTEntity::getStatus).collect(Collectors.toSet());
			//存在复盘任务不允许过账
			if (statusSet.contains(CountStatusEnum.Replay.getCode())) {
				throw new BusinessServiceException("InventoryCountHeaderServiceImpl", "count.has.replaycount" , new Object[] {count.getCountNumber()});
			}
			
			InventoryCountHeaderTExample TExample = new InventoryCountHeaderTExample();
			InventoryCountHeaderTExample.Criteria TExampleCriteria  = TExample.createCriteria();
			
			TExampleCriteria
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andInventoryCountHeaderIdEqualTo(count.getInventoryCountHeaderId());
			
			InventoryCountHeaderTEntity update = InventoryCountHeaderTEntity.builder()
													.status(CountStatusEnum.Post.getCode())
													.updateBy(request.getUserName())
													.updateTime(new Date())
													.build();
			
			int rowcount = headerDao.updateWithVersionByExampleSelective(count.getUpdateVersion(), update, TExample);
			if (rowcount == 0)
				throw new BusinessServiceException("record post error.");
			
			//筛选状态
			detailList = detailList.stream().filter(v -> 
						CountStatusEnum.New.getCode().equals(v.getStatus()) 
						|| CountStatusEnum.Counting.getCode().equals(v.getStatus()) 
						|| CountStatusEnum.Complated.getCode().equals(v.getStatus())
					).collect(Collectors.toList());
			
			detailService.post(new AjaxRequest<List<InventoryCountDetailTEntity>>(detailList, request));

		});
		
		postReplayCount(request);
		
		return Boolean.TRUE;
	}
	
	private Boolean postReplayCount(AjaxRequest<List<InventoryCountHeaderTEntity>> request) {
		if (CollectionUtils.isEmpty(request.getData()))
			return Boolean.FALSE;
		
		Set<Long> parentIds = request.getData().stream().map(InventoryCountHeaderTEntity::getInventoryCountHeaderId).collect(Collectors.toSet());
		InventoryCountHeaderTExample TExample = new InventoryCountHeaderTExample();
		InventoryCountHeaderTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		TExampleCriteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(request.getWarehouseId())
		.andCompanyIdEqualTo(request.getCompanyId())
		.andReplayFlagEqualTo(YesNoEnum.Yes.getCode())
		.andParentCountHeaderIdIn(Lists.newArrayList(parentIds));
		
		//所有复盘任务
		List<InventoryCountHeaderTEntity> replayList = headerDao.selectByExample(TExample);
		if (CollectionUtils.isEmpty(replayList))
			return Boolean.FALSE;
		
		replayList.forEach(c -> {
			
			InventoryCountHeaderTExample replayExample = new InventoryCountHeaderTExample();
			InventoryCountHeaderTExample.Criteria replayExampleCriteria  = replayExample.createCriteria();
			
			replayExampleCriteria
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andInventoryCountHeaderIdEqualTo(c.getInventoryCountHeaderId());
			
			InventoryCountHeaderTEntity update = InventoryCountHeaderTEntity.builder()
													.status(CountStatusEnum.Post.getCode())
													.updateBy(request.getUserName())
													.updateTime(new Date())
													.build();
			
			int rowcount = headerDao.updateWithVersionByExampleSelective(c.getUpdateVersion(), update, replayExample);
			if (rowcount == 0)
				throw new BusinessServiceException("record post error.");
			
			//更新明细状态
			List<InventoryCountDetailTEntity> detailList = detailService.findByHeaderId(InventoryCountDetailTEntity.builder()
															.warehouseId(request.getWarehouseId())
															.companyId(request.getCompanyId())
															.inventoryCountHeaderId(c.getInventoryCountHeaderId())
															.build(), CountStatusEnum.New, CountStatusEnum.Counting, CountStatusEnum.Complated);
			
			detailService.modifyStatus(new AjaxRequest<List<InventoryCountDetailTEntity>>(detailList, request), CountStatusEnum.Post);
		});
		
		boolean postFlag = postReplayCount(new AjaxRequest<List<InventoryCountHeaderTEntity>>(replayList, request));
		
		return postFlag;
	}

}
