package com.wms.services.base.impl;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IPackTDao;
import com.wms.dao.example.PackTExample;
import com.wms.entity.auto.PackTEntity;
import com.wms.services.base.IPackService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class PackServiceImpl implements IPackService {

	@Autowired
	private IPackTDao packDao;
	
	@Override
	public List<PackTEntity> find(PageRequest request) throws BusinessServiceException {
		PackTExample TExample = new PackTExample();
		PackTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(PackTEntity.Column.class, PackTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<PackTEntity> packList = packDao.selectByExample(TExample);
		
		return packList;
	}

	@Override
	public PackTEntity find(PackTEntity pack) throws BusinessServiceException {
	
		PackTExample example = new PackTExample();
		PackTExample.Criteria criteria = example.createCriteria();

        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(pack.getWarehouseId())
                .andCompanyIdEqualTo(pack.getCompanyId());

        int conditionCount = 0;
        if (pack.getPackId() != null) {
            criteria.andPackIdEqualTo(pack.getPackId());
            conditionCount++;
        }
        if (StringUtils.isNotEmpty(pack.getPackCode())) {
            criteria.andPackCodeEqualTo(pack.getPackCode());
            conditionCount++;
        }
        if (conditionCount == 0){
            return null;
        }

        PackTEntity selectLocation = packDao.selectOneByExample(example);
        if (selectLocation == null){
            throw new BusinessServiceException("PackServiceImpl", "pack.record.not.exists", new Object[]{pack.getPackId() == null ? pack.getPackCode() : pack.getPackId()});
        }

        return selectLocation;
	}

	@Override
	public BigDecimal getUOMQty(PackTEntity pack, String uom) throws BusinessServiceException {
		if (StringUtils.isEmpty(uom)) 
			return BigDecimal.ZERO;
		
		if (uom.equals(pack.getUom()))
			return pack.getQty();
		
		if (uom.equals(pack.getUomInner()))
			return pack.getQtyInner();
		
		if (uom.equals(pack.getUomCase()))
			return pack.getQtyCase();
		
		return BigDecimal.ZERO;
	}

	@Override
	public List<PackTEntity> findByPackcodes(PackTEntity pack, Set<String> codes) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(codes))
			return Lists.newArrayList();
		
		PackTExample example = new PackTExample();
		PackTExample.Criteria criteria = example.createCriteria();
		
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(pack.getWarehouseId())
		.andCompanyIdEqualTo(pack.getCompanyId())
		.andPackCodeIn(Lists.newArrayList(codes));
		
		List<PackTEntity> packs = packDao.selectByExample(example);
		
		return packs;
	}
  
	@Override
	public Boolean modify(AjaxRequest<List<PackTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}
		
		List<PackTEntity> list = request.getData();
		
		for (PackTEntity w : list) {
			
			PackTEntity update = PackTEntity.builder()
					.packDescr(w.getPackDescr())
					.uom(w.getUom())
					.qty(w.getQty())
					.uomInner(w.getUomInner())
					.qtyInner(w.getQtyInner())
					.uomCase(w.getUomCase())
					.qtyCase(w.getQtyCase())
					.active(w.getActive())
					.remark(w.getRemark())
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.build();
			
			PackTExample example = new PackTExample();
			example.createCriteria()
			.andWarehouseIdEqualTo(w.getWarehouseId())
            .andCompanyIdEqualTo(w.getCompanyId())
            .andPackIdEqualTo(w.getPackId());
			
			int row = packDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(AjaxRequest<List<PackTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}
		
		List<PackTEntity> list = request.getData();
		
		for (PackTEntity w : list) {
			
			String code = w.getPackCode().toUpperCase();
			
			PackTExample TExample = new PackTExample();
			TExample.createCriteria()
					.andDelFlagEqualTo(YesNoEnum.No.getCode())
					.andWarehouseIdEqualTo(request.getWarehouseId())
					.andCompanyIdEqualTo(request.getCompanyId())
					.andPackCodeEqualTo(code);
			Long count = packDao.countByExample(TExample);
			if (count > 0) {
				throw new BusinessServiceException("PackServiceImpl", "owner.record.exists" , new Object[] {code}); 
			}

			PackTEntity update = PackTEntity.builder()
					.packId(KeyUtils.getUID())
					.packCode(w.getPackCode())
					.packDescr(w.getPackDescr())
					.uom(w.getUom())
					.qty(w.getQty())
					.uomInner(w.getUomInner())
					.qtyInner(w.getQtyInner())
					.uomCase(w.getUomCase())
					.qtyCase(w.getQtyCase())
					.active(w.getActive())
					.remark(w.getRemark())
					.createBy(request.getUserName())
					.createTime(new Date())
					.updateBy(request.getUserName())
					.companyId(request.getCompanyId())
					.warehouseId(request.getWarehouseId())
					.build();

			int row = packDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(List<PackTEntity> packs) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(packs)){
			throw new BusinessServiceException("no record add.");
		}
		for (PackTEntity w : packs) {

			String code = w.getPackCode().toUpperCase();

			PackTEntity update = PackTEntity.builder()
					.packId(KeyUtils.getUID())
					.packCode(code)
					.packDescr(w.getPackDescr())
					.uom(w.getUom())
					.qty(w.getQty())
					.uomInner(w.getUomInner())
					.qtyInner(w.getQtyInner())
					.uomCase(w.getUomCase())
					.qtyCase(w.getQtyCase())
					.active(w.getActive())
					.remark(w.getRemark())
					.createBy(w.getUpdateBy())
                    .updateBy(w.getUpdateBy())
					.createTime(new Date())
					.companyId(w.getCompanyId())
					.warehouseId(w.getWarehouseId())
					.build();

			int row = packDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<PackTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<PackTEntity> list = request.getData();
		
		for (PackTEntity w : list) {
			PackTEntity update = PackTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.delFlag(YesNoEnum.Yes.getCode())
			.build();
			
			PackTExample example = new PackTExample();
			example.createCriteria()
			.andWarehouseIdEqualTo(w.getWarehouseId())
            .andCompanyIdEqualTo(w.getCompanyId())
            .andPackIdEqualTo(w.getPackId());

			
			int row = packDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public List<PackTEntity> findByIds(PackTEntity pack, Set<Long> ids) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(ids))
			return Lists.newArrayList();
		
		PackTExample example = new PackTExample();
		PackTExample.Criteria criteria = example.createCriteria();
		
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(pack.getWarehouseId())
		.andCompanyIdEqualTo(pack.getCompanyId())
		.andPackIdIn(Lists.newArrayList(ids));
		
		List<PackTEntity> packs = packDao.selectByExample(example);
		if (CollectionUtils.isEmpty(packs))
			return Lists.newArrayList();
		
		return packs;
	}
}
