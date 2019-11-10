package com.wms.services.sys.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IWarehouseActiveTDao;
import com.wms.dao.example.WarehouseActiveTExample;
import com.wms.entity.auto.SysWarehousesTEntity;
import com.wms.entity.auto.WarehouseActiveTEntity;
import com.wms.services.sys.ISysWarehouseService;
import com.wms.services.sys.IWarehouseActiveService;
import com.wms.vo.WarehouseActiveVO;
import com.wms.vo.WarehouseVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WarehouseActiveServiceImpl implements IWarehouseActiveService {

	@Autowired
	private IWarehouseActiveTDao warehouseActiveTDao;
	
	@Autowired
	private ISysWarehouseService warehouseService;
	
	@Override
	public WarehouseActiveTEntity find(Long companyId, Long warehouseId) throws BusinessServiceException {
		WarehouseActiveTExample TExample = new WarehouseActiveTExample();
		WarehouseActiveTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		TExampleCriteria
		.andCompanyIdEqualTo(companyId)
		.andWarehouseIdEqualTo(warehouseId)
		.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		return warehouseActiveTDao.selectOneByExample(TExample);
	}
	
	@Override
	public List<WarehouseActiveTEntity> find(Set<Long> companyId, Set<Long> warehouseId) throws BusinessServiceException {
		WarehouseActiveTExample TExample = new WarehouseActiveTExample();
		WarehouseActiveTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		TExampleCriteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		if (CollectionUtils.isNotEmpty(companyId))
			TExampleCriteria.andCompanyIdIn(Lists.newArrayList(companyId));
		
		if (CollectionUtils.isNotEmpty(warehouseId))
			TExampleCriteria.andWarehouseIdIn(Lists.newArrayList(warehouseId));
		
		return warehouseActiveTDao.selectByExample(TExample);
	}

	@Override
	public List<WarehouseActiveVO> find(PageRequest request) throws BusinessServiceException {
		WarehouseActiveTExample TExample = new WarehouseActiveTExample();
		WarehouseActiveTExample.Criteria TExampleCriteria = TExample.createCriteria();

		TExampleCriteria
				.andDelFlagEqualTo(YesNoEnum.No.getCode())
				.andCompanyIdEqualTo(request.getCompanyId())
				.andActiveEqualTo(YesNoEnum.Yes.getCode());

		List<WarehouseActiveTEntity> activeList = warehouseActiveTDao.selectByExample(TExample);

		Set<Long> ids = Sets.newHashSet(activeList.stream().map(WarehouseActiveTEntity::getWarehouseId).collect(Collectors.toSet()));
		List<SysWarehousesTEntity> selectList =warehouseService.findByIds(ids);

		List<WarehouseActiveVO> returnList = Lists.newArrayList();
		selectList.forEach(d ->{
			WarehouseActiveVO warehouseActiveVO = new WarehouseActiveVO();
			warehouseActiveVO.setWarehouseCode(d.getCode());
			warehouseActiveVO.setWarehouseId(d.getWarehouseId());
			warehouseActiveVO.setWarehouseDescr(d.getDescr());
			returnList.add(warehouseActiveVO);
		});

		return returnList;
	}

	@Transactional
	@Override
	public Boolean add(AjaxRequest<List<WarehouseActiveTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data");
		
		request.getData().forEach(d -> {
			d.setActiveId(KeyUtils.getUID());
			d.setCreateBy(request.getUserName());
			d.setUpdateBy(request.getUserName());
			d.setCreateTime(new Date());
			d.setUpdateTime(new Date());
			int rowcount = warehouseActiveTDao.insertSelective(d);
			if (rowcount == 0) {
				throw new BusinessServiceException("record add error.");
			}
		});
		
		return Boolean.TRUE;
	}

	@Transactional
	@Override
	public Boolean modify(AjaxRequest<List<WarehouseActiveTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data");
		
		request.getData().forEach(d -> {
			WarehouseActiveTExample example = new WarehouseActiveTExample();
			example.createCriteria()
			.andActiveIdEqualTo(d.getActiveId());
			
			WarehouseActiveTEntity update = WarehouseActiveTEntity.builder()
												.active(d.getActive())
												.updateBy(request.getUserName())
												.updateTime(new Date())
												.build();
			
			int rowcount = warehouseActiveTDao.updateWithVersionByExampleSelective(d.getUpdateVersion(), update, example);
			if (rowcount == 0) {
				throw new BusinessServiceException("record update error.");
			}
		});
		return Boolean.TRUE;
	}

	@Transactional
	@Override
	public Boolean active(AjaxRequest<List<WarehouseActiveTEntity>> request)
			throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data");
		
		List<WarehouseActiveTEntity> addList = Lists.newArrayList();
		List<WarehouseActiveTEntity> modifyList = Lists.newArrayList();
		List<WarehouseActiveTEntity> initList = Lists.newArrayList();
		
		request.getData().forEach(d -> {
			//验证是否存在记录
			WarehouseActiveTEntity wa = find(d.getCompanyId(), d.getWarehouseId());
			if (wa == null) {  //插入
				d.setInit(YesNoEnum.Yes.getCode());
				addList.add(d);
			}else {  //更新
				d.setActiveId(wa.getActiveId());
				d.setUpdateVersion(d.getUpdateVersion());
				modifyList.add(d);
			}
			 
			if ((wa == null || YesNoEnum.No.getCode().equals(wa.getInit()))
					&& YesNoEnum.Yes.getCode().equals(d.getActive())) {
				d.setInit(YesNoEnum.Yes.getCode());
				initList.add(d);
			}
		});
		
		if (CollectionUtils.isNotEmpty(addList))
			add(new AjaxRequest<List<WarehouseActiveTEntity>>(addList, request));
		
		if (CollectionUtils.isNotEmpty(modifyList))
			modify(new AjaxRequest<List<WarehouseActiveTEntity>>(modifyList, request));
		
		//验证是否初始化数据
		if (CollectionUtils.isNotEmpty(initList))
			initList.forEach(w -> {
				warehouseService.init(new AjaxRequest<WarehouseVO>(new WarehouseVO(w.getCompanyId(), w.getWarehouseId()), request));
			});
			
		return Boolean.TRUE;
	}

	@Override
	public Boolean isActive(Long companyId, Long warehouseId) {
		WarehouseActiveTEntity wa = find(companyId, warehouseId);
		if (wa == null)
			return Boolean.FALSE;
		return YesNoEnum.Yes.getCode().equals(wa.getActive());
	}

	@Override
	public List<WarehouseActiveTEntity> findAll() throws BusinessServiceException {
		WarehouseActiveTExample TExample = new WarehouseActiveTExample();
		WarehouseActiveTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		TExampleCriteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andActiveEqualTo(YesNoEnum.Yes.getCode());
		
		return warehouseActiveTDao.selectByExample(TExample);
	}

}
