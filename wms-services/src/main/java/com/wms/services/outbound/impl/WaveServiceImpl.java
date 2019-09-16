package com.wms.services.outbound.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.enums.OutboundProcessStatusEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IWaveTDao;
import com.wms.dao.example.WaveTExample;
import com.wms.entity.auto.WaveDetailTEntity;
import com.wms.entity.auto.WaveTEntity;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.services.outbound.IWaveDetailService;
import com.wms.services.outbound.IWaveService;
import com.wms.vo.WaveDetailVO;
import com.wms.vo.WaveVO;
import com.wms.vo.outbound.OutboundVO;

@Service
public class WaveServiceImpl implements IWaveService {
	
	@Autowired
	private IWaveTDao waveDao;
	
	@Autowired
	private IOutboundHeaderService outboundService;
	
	@Autowired
	private IWaveDetailService waveDetailService;

	@Override
	//@Transactional 此方法无需事务处理
	public Boolean processStatus(WaveTEntity wave, OutboundProcessStatusEnum processStatus)
			throws BusinessServiceException {
		WaveTEntity update = WaveTEntity.builder()
												.processStatus(processStatus.getCode())
												.updateBy(wave.getUpdateBy())
												.updateTime(new Date())
												.build();
		WaveTExample example = new WaveTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(wave.getWarehouseId())
		.andCompanyIdEqualTo(wave.getCompanyId())
		.andWaveIdEqualTo(wave.getWaveId());
		int rowcount = waveDao.updateByExampleSelective(update, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");

		return Boolean.TRUE;
	}
	
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
									.updateVersion(w.getUpdateVersion())
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
	@Transactional
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
		
		List<WaveDetailVO> addDetail = Lists.newArrayList();
		//add detail
		wave.getDetail().forEach(d -> {
			WaveDetailVO vo = new WaveDetailVO();
			vo.setWaveId(insertWave.getWaveId());
			vo.setOutboundHeaderId(d.getOutboundHeaderId());
			vo.setWaveNumber(waveNumber);
			addDetail.add(vo);
		});
		
		waveDetailService.add(new AjaxRequest<List<WaveDetailVO>>(addDetail, request));
		
		wave.setWaveNumber(waveNumber);
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
	
	private List<OutboundVO> getWaveDetail(AjaxRequest<WaveVO> request){
		List<OutboundVO> outboundList = Lists.newArrayList();
		List<WaveDetailTEntity> waveDetail = waveDetailService.findDetail(WaveDetailTEntity.builder()
										.warehouseId(request.getWarehouseId())
										.companyId(request.getCompanyId())
										.waveId(request.getData().getWaveId())
										.build());
		if (CollectionUtils.isEmpty(waveDetail))
			return Lists.newArrayList();
		
		waveDetail.forEach(wd -> {
			OutboundVO header = new OutboundVO();
			header.setOutboundHeaderId(wd.getOutboundHeaderId());
			outboundList.add(header);
		});
		return outboundList;
	}

	@Override
	public Boolean allocate(AjaxRequest<List<WaveVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) 
			throw new BusinessServiceException("not data allocate.");
		
		request.getData().forEach(w -> {
			w.setWarehouseId(request.getWarehouseId());
			w.setCompanyId(request.getCompanyId());
			processStatus(w, OutboundProcessStatusEnum.Allocating);
			List<OutboundVO> list = getWaveDetail(new AjaxRequest<WaveVO>(w, request));
			outboundService.allocate(new AjaxRequest<List<OutboundVO>>(list, request));
			processStatus(w, OutboundProcessStatusEnum.Allocated);
		});
		return Boolean.TRUE;
	}

	@Override
	public Boolean unAllocate(AjaxRequest<List<WaveVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) 
			throw new BusinessServiceException("not data allocate.");
		
		request.getData().forEach(w -> {
			w.setWarehouseId(request.getWarehouseId());
			w.setCompanyId(request.getCompanyId());
			processStatus(w, OutboundProcessStatusEnum.UnAllocating);
			List<OutboundVO> list = getWaveDetail(new AjaxRequest<WaveVO>(w, request));
			outboundService.unAllocate(new AjaxRequest<List<OutboundVO>>(list, request));
			processStatus(w, OutboundProcessStatusEnum.UnAllocated);
		});
		return Boolean.TRUE;
	}

	@Override
	public Boolean pick(AjaxRequest<List<WaveVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) 
			throw new BusinessServiceException("not data allocate.");
		
		request.getData().forEach(w -> {
			w.setWarehouseId(request.getWarehouseId());
			w.setCompanyId(request.getCompanyId());
			processStatus(w, OutboundProcessStatusEnum.Picking);
			List<OutboundVO> list = getWaveDetail(new AjaxRequest<WaveVO>(w, request));
			outboundService.pick(new AjaxRequest<List<OutboundVO>>(list, request));
			processStatus(w, OutboundProcessStatusEnum.Picked);
		});
		return Boolean.TRUE;
	}

	@Override
	public Boolean unPick(AjaxRequest<List<WaveVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) 
			throw new BusinessServiceException("not data allocate.");
		
		request.getData().forEach(w -> {
			w.setWarehouseId(request.getWarehouseId());
			w.setCompanyId(request.getCompanyId());
			processStatus(w, OutboundProcessStatusEnum.UnPicking);
			List<OutboundVO> list = getWaveDetail(new AjaxRequest<WaveVO>(w, request));
			outboundService.unPick(new AjaxRequest<List<OutboundVO>>(list, request));
			processStatus(w, OutboundProcessStatusEnum.UnPicked);
		});
		return Boolean.TRUE;
	}

	@Override
	public Boolean ship(AjaxRequest<List<WaveVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) 
			throw new BusinessServiceException("not data allocate.");
		
		request.getData().forEach(w -> {
			w.setWarehouseId(request.getWarehouseId());
			w.setCompanyId(request.getCompanyId());
			processStatus(w, OutboundProcessStatusEnum.Shiping);
			List<OutboundVO> list = getWaveDetail(new AjaxRequest<WaveVO>(w, request));
			outboundService.ship(new AjaxRequest<List<OutboundVO>>(list, request));
			processStatus(w, OutboundProcessStatusEnum.Shiped);
		});
		return Boolean.TRUE;
	}

}
