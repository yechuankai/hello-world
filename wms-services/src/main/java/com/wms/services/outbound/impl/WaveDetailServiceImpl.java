package com.wms.services.outbound.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IWaveDetailTDao;
import com.wms.dao.example.WaveDetailTExample;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.WaveDetailTEntity;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.services.outbound.IWaveDetailService;
import com.wms.vo.WaveDetailVO;

@Service
public class WaveDetailServiceImpl implements IWaveDetailService {

	@Autowired
	private IWaveDetailTDao waveDetailDao;
	
	@Autowired
	private IOutboundHeaderService outboundService;
	
	@Override
	public List<WaveDetailVO> find(PageRequest request) throws BusinessServiceException {
		WaveDetailTExample example = new WaveDetailTExample();
		WaveDetailTExample.Criteria criteria = example.createCriteria();

		//转换查询方法
		ExampleUtils.create(WaveDetailTEntity.Column.class, WaveDetailTExample.Criterion.class)
				.criteria(criteria)
				.data(request)
				.build(request)
				.orderby(example);
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode());

		List<WaveDetailTEntity> list = waveDetailDao.selectByExample(example);
		if (list == null)
			return Lists.newArrayList();
		
		//查詢關聯的出庫單
		Set<Long> outboundIds = list.stream().map(WaveDetailTEntity::getOutboundHeaderId).collect(Collectors.toSet());
		List<OutboundHeaderTEntity> outbounds = outboundService.find(OutboundHeaderTEntity.builder()
								.warehouseId(request.getWarehouseId())
								.companyId(request.getCompanyId())
								.build(), outboundIds);
		Map<Long, OutboundHeaderTEntity> outboundMap = outbounds.stream().collect(Collectors.toMap(OutboundHeaderTEntity::getOutboundHeaderId, v -> v));
		
		List<WaveDetailVO> returnList = Lists.newArrayList();
		list.forEach(l -> {
			WaveDetailVO vo = new WaveDetailVO();
			OutboundHeaderTEntity h = outboundMap.get(l.getOutboundHeaderId());
			if (h != null) {
				BeanUtils.copyBeanProp(vo, h);
			}
			BeanUtils.copyBeanProp(vo, l);
			returnList.add(vo);
		});
		return returnList;
	}
	
	@Override
	public WaveDetailTEntity find(WaveDetailTEntity detail)  throws BusinessServiceException{
		WaveDetailTExample example = new WaveDetailTExample();
		WaveDetailTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(detail.getWarehouseId())
		.andCompanyIdEqualTo(detail.getCompanyId())
		.andWaveIdEqualTo(detail.getWaveId())
		.andOutboundHeaderIdEqualTo(detail.getOutboundHeaderId());
		
		List<WaveDetailTEntity> list = waveDetailDao.selectByExample(example);
		if (CollectionUtils.isEmpty(list))
			return null;
		
		return list.get(0);
	}

	@Override
	public List<WaveDetailTEntity> findDetail(WaveDetailTEntity waveDetail) throws BusinessServiceException {
		WaveDetailTExample example = new WaveDetailTExample();
		WaveDetailTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(waveDetail.getWarehouseId())
		.andCompanyIdEqualTo(waveDetail.getCompanyId())
		.andWaveIdEqualTo(waveDetail.getWaveId());
		
		List<WaveDetailTEntity> list = waveDetailDao.selectByExample(example);
		if (list == null)
			return Lists.newArrayList();
		
		return list;
	}

	@Override
	public Boolean delete(AjaxRequest<List<WaveDetailTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data modify.");
		
		request.getData().forEach(w -> {
			WaveDetailTEntity update = WaveDetailTEntity.builder()
									.delFlag(YesNoEnum.Yes.getCode())
									.updateBy(request.getUserName())
									.updateTime(new Date())
									.build();
			
			WaveDetailTExample example = new WaveDetailTExample();
			WaveDetailTExample.Criteria criteria = example.createCriteria();
			criteria
			.andDelFlagEqualTo(YesNoEnum.No.getCode())
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andWaveDetailIdEqualTo(w.getWaveDetailId());
			
			int rowcount = waveDetailDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (rowcount == 0)
				 throw new BusinessServiceException("record delete error.");
			
			//更新出库单来源波次字段
			OutboundHeaderTEntity outboundHeader = OutboundHeaderTEntity.builder()
													.sourceWaveNumber("")
													.outboundHeaderId(w.getOutboundHeaderId())
													.warehouseId(request.getWarehouseId())
													.companyId(request.getCompanyId())
													.updateBy(request.getUserName())
													.build();
			OutboundHeaderTEntity find = outboundService.find(outboundHeader);
			outboundHeader.setUpdateVersion(find.getUpdateVersion());
			outboundService.modifyStatus(outboundHeader);
		});
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(AjaxRequest<List<WaveDetailVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data add.");
		
		request.getData().forEach(w -> {
			WaveDetailTEntity update = WaveDetailTEntity.builder()
									.waveDetailId(KeyUtils.getUID())
									.waveId(w.getWaveId())
									.outboundHeaderId(w.getOutboundHeaderId())
									.warehouseId(request.getWarehouseId())
									.companyId(request.getCompanyId())
									.createBy(request.getUserName())
									.createTime(new Date())
									.updateBy(request.getUserName())
									.updateTime(new Date())
									.build();
			
			//验证是否已存在
			WaveDetailTEntity selectDetail = find(update);
			if (selectDetail != null)
				 throw new BusinessServiceException("WaveDetailServiceImpl", "wave.exists.outbound", new Object[] {w.getOutboundNumber()});
			
			int rowcount = waveDetailDao.insertSelective(update);
			if (rowcount == 0)
				 throw new BusinessServiceException("record add error.");
			
			//更新出库单来源波次字段
			OutboundHeaderTEntity outboundHeader = OutboundHeaderTEntity.builder()
													.sourceWaveNumber(w.getWaveNumber())
													.outboundHeaderId(w.getOutboundHeaderId())
													.warehouseId(request.getWarehouseId())
													.companyId(request.getCompanyId())
													.updateBy(request.getUserName())
													.build();
			OutboundHeaderTEntity find = outboundService.find(outboundHeader);
			outboundHeader.setUpdateVersion(find.getUpdateVersion());
			outboundService.modifyStatus(outboundHeader);
			
		});
		return Boolean.TRUE;
	}

}
