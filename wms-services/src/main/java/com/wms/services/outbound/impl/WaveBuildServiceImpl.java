package com.wms.services.outbound.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
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
import com.wms.vo.outbound.WaveBuildVo;

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
				.build(request);
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode());

		List<WaveBuildTEntity> list = waveBuildTDao.selectByExample(example);

		return list;
	}

	@Override
	public Boolean save(AjaxRequest<WaveBuildVo> request) {
		WaveBuildVo vo = request.getData();
		Long waveBuildId = KeyUtils.getUID();
		WaveBuildTEntity waveBuildTEntity = WaveBuildTEntity.builder()
						.waveBuildId(waveBuildId)
						.buildCode(vo.getBuildCode())
						.buildDescr(vo.getBuildDescr())
						.remark(vo.getRemark())
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
		
		vo.getWaveBuildDetailTEntities().stream().forEach(e -> {
			WaveBuildDetailTEntity waveDetailTEntity = WaveBuildDetailTEntity.builder()
						.waveBuildDetailId(KeyUtils.getUID())
						.waveBuildId(waveBuildId)
						.propertyCode(e.getPropertyCode())
						.propertyExp(e.getPropertyExp())
						.propertyType(e.getPropertyType())
						.tableName(e.getTableName())
						.propertyValue1(e.getPropertyValue1())
						.propertyValue2(e.getPropertyValue2())
						.companyId(request.getCompanyId())
						.warehouseId(request.getWarehouseId())
						.createBy(request.getUserName())
						.createTime(new Date())
						.updateBy(request.getUserName())
						.updateTime(new Date())
						.build();
			int detailInsert = waveBuildDetailTDao.insertSelective(waveDetailTEntity);
			if (detailInsert == 0) {
	            throw new BusinessServiceException("record add error.");
	        }
		});
		return Boolean.TRUE;
	}

	@Override
	public List<OutboundHeaderTEntity> findOutbounds(AjaxRequest request) {
		Long templateId = request.getLong(WaveBuildTEntity.Column.waveBuildId.getJavaProperty());
		WaveBuildDetailTExample example = new WaveBuildDetailTExample();
		example.createCriteria().andCompanyIdEqualTo(request.getCompanyId())
								.andWarehouseIdEqualTo(request.getWarehouseId())
								.andWaveBuildIdEqualTo(templateId);
		List<WaveBuildDetailTEntity> list = waveBuildDetailTDao.selectByExample(example);
		if (list.isEmpty()) {
			return new ArrayList<OutboundHeaderTEntity>();
		}
		WaveBuildVo vo = new WaveBuildVo();
		vo.setWaveBuildId(templateId);
		vo.setWaveBuildDetailTEntities(list);
		request.setData(vo);
		return outboundHeaderService.findByWaveTemplate(request);
	}
	
}
