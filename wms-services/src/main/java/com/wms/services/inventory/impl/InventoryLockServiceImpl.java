package com.wms.services.inventory.impl;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IInventoryLockedTDao;
import com.wms.dao.example.InventoryLockedTExample;
import com.wms.entity.auto.InventoryLockedTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.services.inventory.IInventoryLockService;
import com.wms.services.inventory.IInventoryService;
import com.wms.vo.InventoryLockedVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 库存冻结
 * @author yechuankai.chnet
 *
 */
@Service
public class InventoryLockServiceImpl implements IInventoryLockService {

	@Autowired
	private IInventoryLockedTDao inventoryLockDao;
	@Autowired
	private IInventoryService inventoryService;
	
	
	@Override
	public List<InventoryLockedVO> find(PageRequest request) throws BusinessServiceException {
		InventoryLockedTExample TExample = new InventoryLockedTExample();
		InventoryLockedTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils eu = ExampleUtils.create(InventoryLockedTEntity.Column.class, InventoryLockedTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
        List<InventoryLockedTEntity> lockList = inventoryLockDao.selectByExample(TExample);
		
		if (CollectionUtils.isEmpty(lockList)) 
			return null;
		
		//查询库存
		Set<Long> onhandIds = lockList.stream().map(InventoryLockedTEntity::getInventoryOnhandId).collect(Collectors.toSet());
		List<InventoryOnhandTEntity> onhandList = inventoryService.findByOnhandId(InventoryOnhandTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).build(), onhandIds);
		Map<Long, InventoryOnhandTEntity> onhandMap = onhandList.stream().collect(Collectors.toMap(InventoryOnhandTEntity::getInventoryOnhandId, v->v));
		
		List<InventoryLockedVO> returnList = Lists.newArrayList();
		lockList.forEach(d -> {
			InventoryOnhandTEntity onhand = onhandMap.get(d.getInventoryOnhandId());
			if (onhand == null)
				return;
			
			InventoryLockedVO lockVO = new InventoryLockedVO(onhand);
			BeanUtils.copyBeanProp(lockVO, d);
			
			returnList.add(lockVO);
		});
		return returnList;
	}

	@Override
	public List<InventoryLockedTEntity> find(InventoryLockedTEntity lock) throws BusinessServiceException {
		InventoryLockedTExample TExample = new InventoryLockedTExample();
		InventoryLockedTExample.Criteria criteria = TExample.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCompanyIdEqualTo(lock.getCompanyId())
		.andWarehouseIdEqualTo(lock.getWarehouseId())
		.andInventoryOnhandIdEqualTo(lock.getInventoryOnhandId());
		
		List<InventoryLockedTEntity> list = inventoryLockDao.selectByExample(TExample);
		
		return list;
	}
	
	@Override
	public InventoryLockedTEntity findById(InventoryLockedTEntity lock) throws BusinessServiceException {
		InventoryLockedTExample TExample = new InventoryLockedTExample();
		InventoryLockedTExample.Criteria criteria = TExample.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCompanyIdEqualTo(lock.getCompanyId())
		.andWarehouseIdEqualTo(lock.getWarehouseId())
		.andInventoryLockedIdEqualTo(lock.getInventoryLockedId());
		
		InventoryLockedTEntity selectLock = inventoryLockDao.selectOneByExample(TExample);
		if (selectLock == null)
			throw new BusinessServiceException("InventoryLockServiceImpl", "lock.record.not.exists", new Object[] {lock.getInventoryLockedId()});
		
		return selectLock;
	}

	@Override
	@Transactional
	public Boolean lock(List<InventoryLockedVO> lockList) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(lockList))
			throw new BusinessServiceException("no data");
		
		
		lockList.forEach(d -> {
			
			if (null==d.getQuantityLocked() || BigDecimal.ZERO.compareTo(d.getQuantityLocked()) >= 0)
				return;
			
			//查询库存
			InventoryOnhandTEntity onhand = inventoryService.find(InventoryOnhandTEntity.builder()
									.warehouseId(d.getWarehouseId())
									.companyId(d.getCompanyId())
									.inventoryOnhandId(d.getInventoryOnhandId())
									.build());
			
			BigDecimal vqty = onhand.getQuantityOnhand().subtract(onhand.getQuantityAllocated()).subtract(onhand.getQuantityLocked());
			//验证可用量
			if (d.getQuantityLocked().compareTo(vqty) > 0)
				throw new BusinessServiceException("InventoryLockServiceImpl", "lock.quantity.morethan.quantity", new Object[] {d.getSkuCode(), d.getLotNumber(), d.getLpnNumber(), d.getLocationCode()});
			
			//插入冻结表
			InventoryLockedTEntity lockObj = new InventoryLockedTEntity();
			BeanUtils.copyBeanProp(lockObj, d);
			lockObj.setInventoryLockedId(KeyUtils.getUID());
			lockObj.setLockFlag(YesNoEnum.Yes.getCode());
			inventoryLockDao.insertSelective(lockObj);
			
			//更新库存冻结量
			InventoryOnhandTEntity onhandObj = InventoryOnhandTEntity.builder()
												.warehouseId(d.getWarehouseId())
												.companyId(d.getCompanyId())
												.inventoryOnhandId(d.getInventoryOnhandId())
												.quantityLocked(d.getQuantityLocked())
												.updateBy(d.getUpdateBy())
												.updateTime(new Date())
												.build();
			inventoryService.modify(onhandObj, Boolean.FALSE);
		});
		
		
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean unLock(List<InventoryLockedVO> unLockList) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(unLockList))
			throw new BusinessServiceException("no data");
		
		unLockList.forEach(d -> {
			
			InventoryLockedTEntity selectLock = findById(InventoryLockedTEntity.builder()
															.warehouseId(d.getWarehouseId())
															.companyId(d.getCompanyId())
															.inventoryLockedId(d.getInventoryLockedId())
															.build());
			
			//删除冻结表
			InventoryLockedTEntity lockObj = InventoryLockedTEntity.builder()
					.lockFlag(YesNoEnum.No.getCode())
					.delFlag(YesNoEnum.Yes.getCode())
					.updateBy(d.getUpdateBy())
					.updateTime(new Date())
					.build();
			
			InventoryLockedTExample example = new InventoryLockedTExample();
			example.createCriteria()
			.andWarehouseIdEqualTo(d.getWarehouseId())
			.andCompanyIdEqualTo(d.getCompanyId())
			.andInventoryLockedIdEqualTo(d.getInventoryLockedId());
			
			inventoryLockDao.updateWithVersionByExampleSelective(selectLock.getUpdateVersion(), lockObj, example);
			
			//更新库存冻结量
			InventoryOnhandTEntity onhandObj = InventoryOnhandTEntity.builder()
												.warehouseId(d.getWarehouseId())
												.companyId(d.getCompanyId())
												.inventoryOnhandId(d.getInventoryOnhandId())
												.quantityLocked(BigDecimal.ZERO.subtract(selectLock.getQuantityLocked()))
												.updateBy(d.getUpdateBy())
												.updateTime(new Date())
												.build();
			inventoryService.modify(onhandObj, Boolean.FALSE);
		});
		
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean lock(AjaxRequest<List<InventoryLockedVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data");
		
		request.getData().forEach(d->{
			if (StringUtils.isEmpty(d.getReason())) {
				throw new BusinessServiceException("InventoryLockServiceImpl", "lock.reason.isnull" , new Object[] {d.getInventoryOnhandId()});
			}

			d.setWarehouseId(request.getWarehouseId());
			d.setCompanyId(request.getCompanyId());
			d.setCreateBy(request.getUserName());
			d.setUpdateBy(request.getUserName());
		});
		lock(request.getData());
		return Boolean.TRUE;
	}

	@Override
	public Boolean unLock(AjaxRequest<List<InventoryLockedVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data");
		
		request.getData().forEach(d->{
			d.setWarehouseId(request.getWarehouseId());
			d.setCompanyId(request.getCompanyId());
			d.setCreateBy(request.getUserName());
			d.setUpdateBy(request.getUserName());
		});
		unLock(request.getData());
		return Boolean.TRUE;
	}

}
