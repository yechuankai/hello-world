package com.wms.services.base.impl;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ILotValidateTDao;
import com.wms.dao.example.LotValidateTExample;
import com.wms.entity.auto.LotAttributeTEntity;
import com.wms.entity.auto.LotValidateTEntity;
import com.wms.entity.auto.SkuTEntity;
import com.wms.services.base.ILotValidateService;
import com.wms.services.base.ISkuService;
import com.wms.vo.LotLabelVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class LotValidateServiceImpl implements ILotValidateService {

	@Autowired
	private ILotValidateTDao lotvDao;
	@Autowired
	private ISkuService skuService;
	
	@Override
	public List<LotValidateTEntity> find(PageRequest request) throws BusinessServiceException {
		LotValidateTExample TExample = new LotValidateTExample();
		LotValidateTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(LotValidateTEntity.Column.class, LotValidateTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<LotValidateTEntity> ownerList = lotvDao.selectByExample(TExample);
		
		return ownerList;
	}

	@Override
	public LotValidateTEntity find(LotValidateTEntity lotv) throws BusinessServiceException {
		LotValidateTExample example = new LotValidateTExample();
	    LotValidateTExample.Criteria criteria = example.createCriteria();

        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(lotv.getWarehouseId())
                .andCompanyIdEqualTo(lotv.getCompanyId());

        int conditionCount = 0;
        if (StringUtils.isNotEmpty(lotv.getLotValidateCode())) {
            criteria.andLotValidateCodeEqualTo(lotv.getLotValidateCode());
            conditionCount++;
        }
        if (lotv.getLotValidateId() != null) {
        	criteria.andLotValidateIdEqualTo(lotv.getLotValidateId());
        	conditionCount++;
        }
        if (conditionCount == 0){
            return null;
        }

        LotValidateTEntity selectLocation = lotvDao.selectOneByExample(example);
        if (selectLocation == null){
            throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.record.not.exists", new Object[]{lotv.getLotValidateId()+"/"+lotv.getLotValidateCode()});
        }

        return selectLocation;
	
	}

	@Override
	public Boolean modify(AjaxRequest<List<LotValidateTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}

		List<LotValidateTEntity> list = request.getData();

		for (LotValidateTEntity w : list) {

			LotValidateTEntity update = LotValidateTEntity.builder()
					.lotValidateDescr(w.getLotValidateDescr())
					.lotAttribute1View(StringUtils.isBlank(w.getLotAttribute1View()) ?"N": w.getLotAttribute1View())
					.lotAttribute2View(StringUtils.isBlank(w.getLotAttribute2View()) ? "N":w.getLotAttribute2View())
					.lotAttribute3View(StringUtils.isBlank(w.getLotAttribute3View()) ? "N":w.getLotAttribute3View())
					.lotAttribute4View(StringUtils.isBlank(w.getLotAttribute4View()) ? "N":w.getLotAttribute4View())
					.lotAttribute5View(StringUtils.isBlank(w.getLotAttribute5View()) ? "N":w.getLotAttribute5View())
					.lotAttribute6View(StringUtils.isBlank(w.getLotAttribute6View()) ? "N":w.getLotAttribute6View())
					.lotAttribute7View(StringUtils.isBlank(w.getLotAttribute7View()) ? "N":w.getLotAttribute7View())
					.lotAttribute8View(StringUtils.isBlank(w.getLotAttribute8View()) ? "N":w.getLotAttribute8View())
					.lotAttribute9View(StringUtils.isBlank(w.getLotAttribute9View()) ? "N":w.getLotAttribute9View())
					.lotAttribute10View(StringUtils.isBlank(w.getLotAttribute10View()) ? "N":w.getLotAttribute10View())
					.lotAttribute11View(StringUtils.isBlank(w.getLotAttribute11View()) ? "N":w.getLotAttribute11View())
					.lotAttribute12View(StringUtils.isBlank(w.getLotAttribute12View()) ?"N": w.getLotAttribute12View())
					.lotAttribute1Required(StringUtils.isBlank(w.getLotAttribute1Required()) ? "N":w.getLotAttribute1Required())
					.lotAttribute2Required(StringUtils.isBlank(w.getLotAttribute2Required()) ? "N": w.getLotAttribute2Required())
					.lotAttribute3Required(StringUtils.isBlank(w.getLotAttribute3Required()) ? "N": w.getLotAttribute3Required())
					.lotAttribute4Required(StringUtils.isBlank(w.getLotAttribute4Required()) ? "N": w.getLotAttribute4Required())
					.lotAttribute5Required(StringUtils.isBlank(w.getLotAttribute5Required()) ? "N": w.getLotAttribute5Required())
					.lotAttribute6Required(StringUtils.isBlank(w.getLotAttribute6Required()) ? "N": w.getLotAttribute6Required())
					.lotAttribute7Required(StringUtils.isBlank(w.getLotAttribute7Required()) ? "N": w.getLotAttribute7Required())
					.lotAttribute8Required(StringUtils.isBlank(w.getLotAttribute8Required()) ? "N": w.getLotAttribute8Required())
					.lotAttribute9Required(StringUtils.isBlank(w.getLotAttribute9Required()) ? "N": w.getLotAttribute9Required())
					.lotAttribute10Required(StringUtils.isBlank(w.getLotAttribute10Required()) ? "N": w.getLotAttribute10Required())
					.lotAttribute11Required(StringUtils.isBlank(w.getLotAttribute11Required()) ? "N": w.getLotAttribute11Required())
					.lotAttribute12Required(StringUtils.isBlank(w.getLotAttribute12Required()) ? "N": w.getLotAttribute12Required())
					.build();

			update.setUpdateBy(request.getUserName());
			update.setUpdateTime(new Date());

			LotValidateTExample example = new LotValidateTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(w.getWarehouseId())
					.andCompanyIdEqualTo(w.getCompanyId())
					.andLotValidateIdEqualTo(w.getLotValidateId());

			int row = lotvDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(AjaxRequest<List<LotValidateTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}

		List<LotValidateTEntity> list = request.getData();

		for (LotValidateTEntity w : list) {

			String code = w.getLotValidateCode().toUpperCase();

			LotValidateTExample example = new LotValidateTExample();
			example.createCriteria()
					.andDelFlagEqualTo(YesNoEnum.No.getCode())
					.andLotValidateCodeEqualTo(code)
					.andCompanyIdEqualTo(request.getCompanyId())
					.andWarehouseIdEqualTo(request.getWarehouseId());
			Long count = lotvDao.countByExample(example);
			if (count > 0) {
				throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.record.exists" , new Object[] {code});
			}

			LotValidateTEntity update = new LotValidateTEntity();
			BeanUtils.copyBeanProp(update, w, Boolean.FALSE);
			update.setLotValidateId(KeyUtils.getUID());
			update.setLotValidateCode(code);
			update.setCreateBy(request.getUserName());
			update.setCreateTime(new Date());
			update.setUpdateBy(request.getUserName());
			update.setUpdateTime(new Date());
			update.setCompanyId(request.getCompanyId());
			update.setWarehouseId(request.getWarehouseId());

			int row = lotvDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<LotValidateTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<LotValidateTEntity> list = request.getData();

		for (LotValidateTEntity w : list) {
			LotValidateTEntity update = LotValidateTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.delFlag(YesNoEnum.Yes.getCode())
					.build();

			LotValidateTExample example = new LotValidateTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(w.getWarehouseId())
					.andCompanyIdEqualTo(w.getCompanyId())
					.andLotValidateIdEqualTo(w.getLotValidateId());


			int row = lotvDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean validate(LotValidateTEntity lotv, LotAttributeTEntity lot) throws BusinessServiceException {
		SkuTEntity skuExample = SkuTEntity.builder().warehouseId(lot.getWarehouseId()).companyId(lot.getCompanyId()).skuId(lot.getSkuId()).skuCode(lot.getSkuCode()).ownerCode(lot.getOwnerCode()).build();
		if (lotv == null) {
			SkuTEntity sku = skuService.find(skuExample);
			lotv = find(LotValidateTEntity.builder().warehouseId(lot.getWarehouseId()).companyId(lot.getCompanyId()).lotValidateId(sku.getLotValidateId()).lotValidateCode(sku.getLotValidateCode()).build());
		}
		if (lotv == null)
			throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.record.not.exists", new Object[]{lot.getSkuCode()});
		//获取批属性显示
		LotLabelVO lotLabel = skuService.getSkuLotLabel(skuExample);
		
		//开始验证批属性
		
		if (YesNoEnum.Yes.getCode().equals(lotv.getLotAttribute1Required())
				&& StringUtils.isEmpty(lot.getLotAttribute1())) {
			throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.lotattribute.isnull", new Object[] {lotLabel.getLotAttribute1Label(), "1"});
		}
		if (YesNoEnum.Yes.getCode().equals(lotv.getLotAttribute2Required())
				&& StringUtils.isEmpty(lot.getLotAttribute2())) {
			throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.lotattribute.isnull", new Object[] {lotLabel.getLotAttribute2Label(), "2"});
		}
		if (YesNoEnum.Yes.getCode().equals(lotv.getLotAttribute3Required())
				&& StringUtils.isEmpty(lot.getLotAttribute3())) {
			throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.lotattribute.isnull", new Object[] {lotLabel.getLotAttribute3Label(), "3"});
		}
		if (YesNoEnum.Yes.getCode().equals(lotv.getLotAttribute4Required())
				&& StringUtils.isNull(lot.getLotAttribute4())) {
			throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.lotattribute.isnull", new Object[] {lotLabel.getLotAttribute4Label(), "4"});
		}
		if (YesNoEnum.Yes.getCode().equals(lotv.getLotAttribute5Required())
				&& StringUtils.isNull(lot.getLotAttribute5())) {
			throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.lotattribute.isnull", new Object[] {lotLabel.getLotAttribute5Label(), "5"});
		}
		if (YesNoEnum.Yes.getCode().equals(lotv.getLotAttribute6Required())
				&& StringUtils.isEmpty(lot.getLotAttribute6())) {
			throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.lotattribute.isnull", new Object[] {lotLabel.getLotAttribute6Label(), "6"});
		}
		if (YesNoEnum.Yes.getCode().equals(lotv.getLotAttribute7Required())
				&& StringUtils.isEmpty(lot.getLotAttribute7())) {
			throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.lotattribute.isnull", new Object[] {lotLabel.getLotAttribute7Label(), "7"});
		}
		if (YesNoEnum.Yes.getCode().equals(lotv.getLotAttribute8Required())
				&& StringUtils.isEmpty(lot.getLotAttribute8())) {
			throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.lotattribute.isnull", new Object[] {lotLabel.getLotAttribute8Label(), "8"});
		}
		if (YesNoEnum.Yes.getCode().equals(lotv.getLotAttribute9Required())
				&& StringUtils.isEmpty(lot.getLotAttribute9())) {
			throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.lotattribute.isnull", new Object[] {lotLabel.getLotAttribute9Label(), "9"});
		}
		if (YesNoEnum.Yes.getCode().equals(lotv.getLotAttribute10Required())
				&& StringUtils.isEmpty(lot.getLotAttribute10())) {
			throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.lotattribute.isnull", new Object[] {lotLabel.getLotAttribute10Label(), "10"});
		}
		if (YesNoEnum.Yes.getCode().equals(lotv.getLotAttribute11Required())
				&& StringUtils.isNull(lot.getLotAttribute11())) {
			throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.lotattribute.isnull", new Object[] {lotLabel.getLotAttribute11Label(), "11"});
		}
		if (YesNoEnum.Yes.getCode().equals(lotv.getLotAttribute12Required())
				&& StringUtils.isNull(lot.getLotAttribute12())) {
			throw new BusinessServiceException("LotValidateServiceImpl", "lotvalidate.lotattribute.isnull", new Object[] {lotLabel.getLotAttribute12Label(), "12"});
		}
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean validate(LotAttributeTEntity lot) throws BusinessServiceException {
		return validate(null, lot);
	}

	@Override
	public List<LotValidateTEntity> findByIds(LotValidateTEntity lotv, Set<Long> ids) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(ids))
			return Lists.newArrayList();
		
		LotValidateTExample example = new LotValidateTExample();
	    LotValidateTExample.Criteria criteria = example.createCriteria();

        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andWarehouseIdEqualTo(lotv.getWarehouseId())
                .andCompanyIdEqualTo(lotv.getCompanyId())
                .andLotValidateIdIn(Lists.newArrayList(ids));

        
        List<LotValidateTEntity> selectLocation = lotvDao.selectByExample(example);
        if (CollectionUtils.isEmpty(selectLocation)){
        	return Lists.newArrayList();
        }
        return selectLocation;
	}

}
