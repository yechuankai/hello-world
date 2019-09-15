package com.wms.services.outbound.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IWaveDetailTDao;
import com.wms.dao.auto.IWaveTDao;
import com.wms.dao.example.WaveTExample;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.WaveDetailTEntity;
import com.wms.entity.auto.WaveTEntity;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.services.outbound.IWaveDetailService;
import com.wms.services.outbound.IWaveService;
import com.wms.vo.WaveVO;

@Service
public class WaveServiceImpl implements IWaveService {
	
	@Autowired
	private IWaveTDao waveDao;
	
	@Autowired
	private IOutboundHeaderService outboundService;
	
	@Autowired
	private IWaveDetailService waveDetailService;

	@Override
	public Boolean modify(AjaxRequest<List<WaveTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data modify");
		
		request.getData().forEach(w -> {
			WaveTEntity update = WaveTEntity.builder()
									.warehouseId(request.getWarehouseId())
									.companyId(request.getCompanyId())
									.waveId(w.getWaveId())
									.remark(w.getRemark())
									.updateBy(request.getUserName())
									.updateTime(new Date())
									.build();
			
			modify(update);
		});

		return Boolean.TRUE;
	}
	
	private Boolean modify(WaveTEntity wave) {
		WaveTExample example = new WaveTExample();
		WaveTExample.Criteria criteria = example.createCriteria();
		criteria
		.andWarehouseIdEqualTo(wave.getWarehouseId())
		.andCompanyIdEqualTo(wave.getCompanyId())
		.andWaveIdEqualTo(wave.getWaveId());
		
		int rowcount = waveDao.updateWithVersionByExampleSelective(wave.getUpdateVersion(), wave, example);
		if (rowcount == 0)
			 throw new BusinessServiceException("record modify error.");
		
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean delete(AjaxRequest<List<WaveTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data delete.");
		
		request.getData().forEach(w -> {
			WaveTEntity update = WaveTEntity.builder()
									.delFlag(YesNoEnum.Yes.getCode())
									.updateBy(request.getUserName())
									.updateTime(new Date())
									.build();
			
			WaveTExample example = new WaveTExample();
			WaveTExample.Criteria criteria = example.createCriteria();
			criteria
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andWaveIdEqualTo(w.getWaveId());
			
			int rowcount = waveDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (rowcount == 0)
				 throw new BusinessServiceException("record delete error.");
			
			//delete detail
			List<WaveDetailTEntity> detail = waveDetailService.findDetail(WaveDetailTEntity.builder()
																			.warehouseId(request.getWarehouseId())
																			.companyId(request.getCompanyId())
																			.waveId(w.getWaveId())
																			.build());
			if (CollectionUtils.isNotEmpty(detail))
				waveDetailService.delete(new AjaxRequest<List<WaveDetailTEntity>>(detail, request));
		});

		return Boolean.TRUE;
	}

	@Override
	public Boolean createWave(AjaxRequest<WaveVO> request) throws BusinessServiceException {
		WaveVO wave = request.getData();
		
		if (CollectionUtils.isEmpty(wave.getDetail()))
			throw new BusinessServiceException("no outbound add.");
		
		// 创建表头
		String waveNumber = KeyUtils.getOrderNumber(request.getCompanyId(), request.getWarehouseId(), OrderNumberTypeEnum.Wave);
		WaveTEntity insertWave = WaveTEntity.builder()
							.warehouseId(request.getWarehouseId())
							.companyId(request.getCompanyId())
							.waveId(KeyUtils.getUID())
							.waveNumber(waveNumber)
							.waveType(wave.getWaveType())
							.remark(wave.getRemark())
							.createBy(request.getUserName())
							.updateBy(request.getUserName())
							.createTime(new Date())
							.updateTime(new Date())
							.build();
		int rowcount = waveDao.insertSelective(insertWave);
		if (rowcount == 0)
			 throw new BusinessServiceException("record delete error.");
		
		//add detail
		wave.getDetail().forEach(d -> {
			d.setWaveId(insertWave.getWaveId());
		});
		
		waveDetailService.add(new AjaxRequest<List<WaveDetailTEntity>>(wave.getDetail(), request));
		
		return Boolean.TRUE;
	}

	@Override
	public List<WaveTEntity> find(PageRequest request) throws BusinessServiceException {
		WaveTExample example = new WaveTExample();
		WaveTExample.Criteria criteria = example.createCriteria();

		//转换查询方法
		ExampleUtils.create(WaveTEntity.Column.class, WaveTExample.Criterion.class)
				.criteria(criteria)
				.data(request)
				.build(request)
				.orderby(example);
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode());

		List<WaveTEntity> list = waveDao.selectByExample(example);

		return list;
	}

}
