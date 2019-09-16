package com.wms.services.outbound.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IWaveBuildDetailTDao;
import com.wms.dao.auto.IWaveBuildTDao;
import com.wms.dao.example.WaveBuildDetailTExample;
import com.wms.dao.example.WaveBuildTExample;
import com.wms.dao.example.WaveBuildTExample.Criteria;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.WaveBuildDetailTEntity;
import com.wms.entity.auto.WaveBuildTEntity;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.services.outbound.IWaveBuildService;
import com.wms.vo.outbound.WaveBuildVO;

@Service
public class WaveBuildServiceImpl implements IWaveBuildService{
	
	@Autowired
	private IWaveBuildTDao waveBuildTDao;
	@Autowired
	private IWaveBuildDetailTDao waveBuildDetailTDao;
	@Autowired
	private IOutboundHeaderService outboundHeaderService;

	@Override
	public List<WaveBuildTEntity> find(PageRequest request) {
		WaveBuildTExample example = new WaveBuildTExample();
		Criteria criteria = example.createCriteria();

		//转换查询方法
		ExampleUtils.create(WaveBuildTEntity.Column.class, WaveBuildTExample.Criterion.class)
				.criteria(criteria)
				.data(request)
				.build(request)
				.orderby(example);
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode());

		List<WaveBuildTEntity> list = waveBuildTDao.selectByExample(example);

		return list;
	}

	@Override
	@Transactional
	public Boolean add(AjaxRequest<WaveBuildVO> request) {
		WaveBuildVO vo = request.getData();
		//验证
		validate(vo);
		
		Long waveBuildId = KeyUtils.getUID();
		String buildCode = KeyUtils.getOrderNumber(request.getCompanyId(), request.getWarehouseId(), OrderNumberTypeEnum.Wave);
		WaveBuildTEntity waveBuildTEntity = WaveBuildTEntity.builder()
						.waveBuildId(waveBuildId)
						.buildCode(buildCode)
						.waveType(vo.getWaveType())
						.buildDescr(vo.getBuildDescr())
						.companyId(request.getCompanyId())
						.warehouseId(request.getWarehouseId())
						.createBy(request.getUserName())
						.createTime(new Date())
						.updateBy(request.getUserName())
						.updateTime(new Date())
						.build();
		int insert = waveBuildTDao.insertSelective(waveBuildTEntity);
		if (insert == 0) {
            throw new BusinessServiceException("record add error.");
        }
		
		if (CollectionUtils.isEmpty(vo.getDetail())) {
			return Boolean.TRUE;
		}
		
		vo.getDetail().stream().forEach(d -> {
			d.setWaveBuildId(waveBuildId);
		});
		addDetail(new AjaxRequest<List<WaveBuildDetailTEntity>>(vo.getDetail(), request));
		return Boolean.TRUE;
	}
	
	private Boolean addDetail(AjaxRequest<List<WaveBuildDetailTEntity>> request) {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data add.");
		
		request.getData().stream().forEach(d -> {
			d.setWarehouseId(request.getWarehouseId());
			d.setCompanyId(request.getCompanyId());
			d.setWaveBuildDetailId(KeyUtils.getUID());
			d.setCreateBy(request.getUserName());
			d.setCreateTime(new Date());
			d.setUpdateBy(request.getUserName());
			d.setUpdateTime(new Date());
			int rowcount = waveBuildDetailTDao.insertSelective(d);
			if (rowcount == 0) {
	            throw new BusinessServiceException("record add error.");
	        }
		});
		return Boolean.TRUE;
	}
	
	private Boolean deleteDetail(AjaxRequest<List<WaveBuildDetailTEntity>> request) {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data delete.");
		
		request.getData().forEach(d -> {
			WaveBuildDetailTExample example = new WaveBuildDetailTExample();
			WaveBuildDetailTExample.Criteria criteria = example.createCriteria();
			
			criteria
			.andCompanyIdEqualTo(request.getCompanyId())
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andWaveBuildDetailIdEqualTo(d.getWaveBuildDetailId());
			
			WaveBuildDetailTEntity update = new WaveBuildDetailTEntity();
			update.setDelFlag(YesNoEnum.Yes.getCode());
			update.setUpdateBy(request.getUserName());
			update.setUpdateTime(new Date());
			
			int rowcount = waveBuildDetailTDao.updateWithVersionByExampleSelective(d.getUpdateVersion(), update, example);
			if (rowcount == 0)
				throw new BusinessServiceException("record delete error.");
		});
		return Boolean.TRUE;
	}
	
	private Boolean modifyDetail(AjaxRequest<List<WaveBuildDetailTEntity>> request) {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data modify.");
		
		request.getData().stream().forEach(d -> {
			WaveBuildDetailTExample example = new WaveBuildDetailTExample();
			WaveBuildDetailTExample.Criteria criteria = example.createCriteria();
			
			criteria
			.andCompanyIdEqualTo(request.getCompanyId())
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andWaveBuildDetailIdEqualTo(d.getWaveBuildDetailId());
			
			WaveBuildDetailTEntity update = new WaveBuildDetailTEntity();
			update.setPropertyType(d.getPropertyType());
			update.setPropertyValue1(d.getPropertyValue1());
			update.setUpdateBy(request.getUserName());
			update.setUpdateTime(new Date());
			
			int rowcount = waveBuildDetailTDao.updateWithVersionByExampleSelective(d.getUpdateVersion(), update, example);
			if (rowcount == 0)
				throw new BusinessServiceException("record update error.");
		});
		return Boolean.TRUE;
	}

	@Override
	public List<OutboundHeaderTEntity> findOutbounds(AjaxRequest<WaveBuildVO> request) {
		List<WaveBuildDetailTEntity> list = findDetail(WaveBuildDetailTEntity.builder()
														.warehouseId(request.getWarehouseId())
														.companyId(request.getCompanyId())
														.waveBuildId(request.getData().getWaveBuildId())
														.build());
		if (CollectionUtils.isEmpty(list)) {
			return Lists.newArrayList();
		}
		request.getData().setDetail(list);	
		return outboundHeaderService.findByWaveTemplate(request);
	}

	@Override
	@Transactional
	public Boolean rowModify(AjaxRequest<List<WaveBuildVO>> request) {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data modify.");
		
		request.getData().forEach(d -> {
			//验证
			validate(d);
			
			WaveBuildTExample example = new WaveBuildTExample();
			WaveBuildTExample.Criteria criteria = example.createCriteria();
			
			criteria
			.andCompanyIdEqualTo(request.getCompanyId())
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andWaveBuildIdEqualTo(d.getWaveBuildId());
			
			WaveBuildTEntity update = new WaveBuildTEntity();
			update.setUpdateBy(request.getUserName());
			update.setUpdateTime(new Date());
			update.setBuildDescr(d.getBuildDescr());
			update.setWaveType(d.getWaveType());
			
			int rowcount = waveBuildTDao.updateWithVersionByExampleSelective(d.getUpdateVersion(), update, example);
			if (rowcount == 0)
				throw new BusinessServiceException("record delete error.");
			
		});
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean delete(AjaxRequest<List<WaveBuildVO>> request) {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data delete.");
		
		request.getData().forEach(d -> {
			WaveBuildTEntity wb = find(d);
			
			WaveBuildTExample example = new WaveBuildTExample();
			WaveBuildTExample.Criteria criteria = example.createCriteria();
			criteria
			.andCompanyIdEqualTo(request.getCompanyId())
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andWaveBuildIdEqualTo(wb.getWaveBuildId());
			
			WaveBuildTEntity update = new WaveBuildTEntity();
			update.setDelFlag(YesNoEnum.Yes.getCode());
			update.setUpdateBy(request.getUserName());
			update.setUpdateTime(new Date());
			
			int rowcount = waveBuildTDao.updateWithVersionByExampleSelective(wb.getUpdateVersion(), update, example);
			if (rowcount == 0)
				throw new BusinessServiceException("record delete error.");
			
			List<WaveBuildDetailTEntity> detail = findDetail(WaveBuildDetailTEntity.builder()
																.warehouseId(request.getWarehouseId())
																.companyId(request.getCompanyId())
																.waveBuildId(d.getWaveBuildId())
																.build());
			if (CollectionUtils.isNotEmpty(detail))
				deleteDetail(new AjaxRequest<List<WaveBuildDetailTEntity>>(detail, request));
			
		});
		
		return null;
	}

	@Override
	public WaveBuildTEntity find(WaveBuildTEntity wb) {
		WaveBuildTExample example = new WaveBuildTExample();
		WaveBuildTExample.Criteria criteria = example.createCriteria();
		
		criteria
		.andCompanyIdEqualTo(wb.getCompanyId())
		.andWarehouseIdEqualTo(wb.getWarehouseId());
		
		int conditioncount = 0;
		if (StringUtils.isNotEmpty(wb.getBuildCode())) {
			criteria.andBuildCodeEqualTo(wb.getBuildCode());
			conditioncount ++;
		}
		if (wb.getWaveBuildId() != null) {
			criteria.andWaveBuildIdEqualTo(wb.getWaveBuildId());
			conditioncount ++;
		}
		if (conditioncount == 0) 
			return null;
		
		WaveBuildTEntity selectWb = waveBuildTDao.selectOneByExample(example);
		if (selectWb == null)
			throw new BusinessServiceException("WaveBuildServiceImpl", "wave.template.not.exists", new Object[] {wb.getWaveBuildId() + "/" + wb.getBuildCode()});
		
		return selectWb;
	}

	@Override
	@Transactional
	public Boolean modify(AjaxRequest<WaveBuildVO> request) {
		
		WaveBuildVO vo = request.getData();
		
		//验证
		validate(vo);
				
		//更新表头
		rowModify(new AjaxRequest<List<WaveBuildVO>>(Lists.newArrayList(vo), request));
		
		if (CollectionUtils.isEmpty(vo.getDetail()))
			return Boolean.TRUE;
		
		//更新明细
		List<WaveBuildDetailTEntity> oriDetail = findDetail(WaveBuildDetailTEntity.builder()
																.warehouseId(request.getWarehouseId())
																.companyId(request.getCompanyId())
																.waveBuildId(vo.getWaveBuildId())
																.build());
		
		Map<String, WaveBuildDetailTEntity> oriDetailMap = oriDetail.stream().collect(Collectors.toMap( k -> (k.getPropertyCode() + k.getPropertyExp()), v -> v));
		
		Map<String, WaveBuildDetailTEntity> newDetailMap = vo.getDetail().stream().collect(Collectors.toMap( k -> (k.getPropertyCode() + k.getPropertyExp()), v -> v));
		
		//计算新增，删除，修改
		List<WaveBuildDetailTEntity> newData = Lists.newArrayList();
		List<WaveBuildDetailTEntity> updateData = Lists.newArrayList();
		List<WaveBuildDetailTEntity> deleteData = Lists.newArrayList();
		newDetailMap.forEach((k, v) -> {
			if (!oriDetailMap.containsKey(k)) {
				v.setWaveBuildId(vo.getWaveBuildId());
				newData.add(v);
			}else {
				WaveBuildDetailTEntity d = oriDetailMap.get(k);
				v.setWaveBuildDetailId(d.getWaveBuildDetailId());
				v.setUpdateVersion(d.getUpdateVersion());
				updateData.add(v);
			}
		});
		oriDetailMap.forEach((k, v) -> {
			if (!newDetailMap.containsKey(k)) {
				deleteData.add(v);
			}
		});
		
		//执行数据库操作
		if (CollectionUtils.isNotEmpty(newData)) {
			addDetail(new AjaxRequest<List<WaveBuildDetailTEntity>>(newData, request));
		}
		
		if (CollectionUtils.isNotEmpty(updateData)) {
			modifyDetail(new AjaxRequest<List<WaveBuildDetailTEntity>>(updateData, request));
		}
		
		if (CollectionUtils.isNotEmpty(deleteData)) {
			deleteDetail(new AjaxRequest<List<WaveBuildDetailTEntity>>(deleteData, request));
		}
		
		return Boolean.TRUE;
	}

	@Override
	public List<WaveBuildDetailTEntity> findDetail(WaveBuildDetailTEntity wb) {
		
		WaveBuildDetailTExample example = new WaveBuildDetailTExample();
		WaveBuildDetailTExample.Criteria criteria = example.createCriteria();
		
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCompanyIdEqualTo(wb.getCompanyId())
		.andWarehouseIdEqualTo(wb.getWarehouseId())
		.andWaveBuildIdEqualTo(wb.getWaveBuildId());
		
		List<WaveBuildDetailTEntity> detail = waveBuildDetailTDao.selectByExample(example);
		if (detail == null)
			return Lists.newArrayList();
		
		return detail;
	}
	
	private Boolean validate(WaveBuildTEntity wb) {
		if (StringUtils.isEmpty(wb.getWaveType()))
			throw new BusinessServiceException("WaveBuildServiceImpl", "wavetype.isnull", null);
		
		return Boolean.TRUE;
	}

	
}
