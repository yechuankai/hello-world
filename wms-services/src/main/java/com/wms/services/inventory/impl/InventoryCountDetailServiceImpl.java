package com.wms.services.inventory.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.CountStatusEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IInventoryCountDetailTDao;
import com.wms.dao.example.InventoryCountDetailTExample;
import com.wms.entity.auto.InventoryCountDetailTEntity;
import com.wms.entity.auto.InventoryCountHeaderTEntity;
import com.wms.services.inventory.IInventoryCountDetailService;
import com.wms.services.inventory.IInventoryCountHeaderService;

@Service
public class InventoryCountDetailServiceImpl implements IInventoryCountDetailService {

	@Autowired
	private IInventoryCountHeaderService headerService;
	
	@Autowired
	private IInventoryCountDetailTDao detailDao;
	
	@Override
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
	public Boolean moidfy(AjaxRequest<List<InventoryCountDetailTEntity>> request) throws BusinessServiceException {
		// TODO Auto-generated method stub
		return null;
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
	public List<InventoryCountDetailTEntity> findByHeaderId(InventoryCountDetailTEntity detail)
			throws BusinessServiceException {
		InventoryCountDetailTExample TExample = new InventoryCountDetailTExample();
		InventoryCountDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		TExampleCriteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(detail.getWarehouseId())
		.andCompanyIdEqualTo(detail.getCompanyId())
		.andInventoryCountHeaderIdEqualTo(detail.getInventoryCountHeaderId());
		
		List<InventoryCountDetailTEntity> countDetailList = detailDao.selectByExample(TExample);
		return countDetailList;
	}

}
