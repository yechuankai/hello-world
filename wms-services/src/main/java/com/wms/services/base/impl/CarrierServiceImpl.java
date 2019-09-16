package com.wms.services.base.impl;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ICarrierTDao;
import com.wms.dao.example.CarrierTExample;
import com.wms.entity.auto.CarrierTEntity;
import com.wms.services.base.ICarrierService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarrierServiceImpl implements ICarrierService {

	@Autowired
	private ICarrierTDao carrierDao;
	
	@Override
	public List<CarrierTEntity> find(PageRequest request) throws BusinessServiceException {
		CarrierTExample TExample = new CarrierTExample();
		CarrierTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(CarrierTEntity.Column.class, CarrierTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<CarrierTEntity> list = carrierDao.selectByExample(TExample);
		
		return list;
	}

	@Override
	public CarrierTEntity find(CarrierTEntity carrier) throws BusinessServiceException {
		CarrierTExample example = new CarrierTExample();
		CarrierTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(carrier.getWarehouseId())
		.andCompanyIdEqualTo(carrier.getCompanyId());

		int conditionCount = 0;
		if (carrier.getCarrierId() != null) {
			criteria.andCarrierIdEqualTo(carrier.getCarrierId());
			conditionCount++;
		}
		if (StringUtils.isNotEmpty(carrier.getCarrierCode())) {
			criteria.andCarrierCodeEqualTo(carrier.getCarrierCode());
			conditionCount++;
		}
		if (conditionCount == 0){
			return null;
		}
		
		CarrierTEntity selectCarrier = carrierDao.selectOneByExample(example);
		if (selectCarrier == null) 
			throw new BusinessServiceException("CarrierServiceImpl", "carrier.not.exists" , new Object[] {carrier.getCarrierId()}); 
		return selectCarrier;
	}

	@Override
	public CarrierTEntity findByCode(CarrierTEntity carrier) throws BusinessServiceException {
		CarrierTExample example = new CarrierTExample();
		CarrierTExample.Criteria criteria = example.createCriteria();
		criteria
				.andDelFlagEqualTo(YesNoEnum.No.getCode())
				.andWarehouseIdEqualTo(carrier.getWarehouseId())
				.andCarrierCodeEqualTo(carrier.getCarrierCode())
				.andCompanyIdEqualTo(carrier.getCompanyId());

		CarrierTEntity selectCarrier = carrierDao.selectOneByExample(example);

		return selectCarrier;
	}

	@Override
	public Boolean modify(AjaxRequest<List<CarrierTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}

		List<CarrierTEntity> list = request.getData();

		for (CarrierTEntity w : list) {

			CarrierTEntity update = CarrierTEntity.builder()
					.carrierDescr(w.getCarrierDescr())
					.contact1(w.getContact1())
					.contact2(w.getContact2())
					.phone1(w.getPhone1())
					.phone2(w.getPhone2())
					.address1(w.getAddress1())
					.address2(w.getAddress2())
					.fax(w.getFax())
					.webSite(w.getWebSite())
					.email1(w.getEmail1())
					.email2(w.getEmail2())
					.active(w.getActive())
					.remark(w.getRemark())
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.build();

			CarrierTExample example = new CarrierTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(w.getWarehouseId())
					.andCompanyIdEqualTo(w.getCompanyId())
					.andCarrierIdEqualTo(w.getCarrierId());

			int row = carrierDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(AjaxRequest<List<CarrierTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}

		List<CarrierTEntity> list = request.getData();

		for (CarrierTEntity w : list) {

			String code = w.getCarrierCode().toUpperCase();

			CarrierTExample example = new CarrierTExample();
			example.createCriteria()
					.andDelFlagEqualTo(YesNoEnum.No.getCode())
					.andCarrierCodeEqualTo(code)
					.andCompanyIdEqualTo(request.getCompanyId())
					.andWarehouseIdEqualTo(request.getWarehouseId());
			long count = carrierDao.countByExample(example);
			if (count > 0) {
				throw new BusinessServiceException("CarrierServiceImpl", "carrier.record.exists" , new Object[] {code});
			}

			CarrierTEntity update = CarrierTEntity.builder()
					.carrierId(KeyUtils.getUID())
					.carrierCode(code)
					.carrierDescr(w.getCarrierDescr())
					.contact1(w.getContact1())
					.contact2(w.getContact2())
					.phone1(w.getPhone1())
					.phone2(w.getPhone2())
					.address1(w.getAddress1())
					.address2(w.getAddress2())
					.fax(w.getFax())
					.webSite(w.getWebSite())
					.email1(w.getEmail1())
					.email2(w.getEmail2())
					.active(w.getActive())
					.remark(w.getRemark())
					.createBy(request.getUserName())
					.createTime(new Date())
					.companyId(request.getCompanyId())
					.warehouseId(request.getWarehouseId())
					.build();

			int row = carrierDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<CarrierTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<CarrierTEntity> list = request.getData();

		for (CarrierTEntity w : list) {
			CarrierTEntity update = CarrierTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.delFlag(YesNoEnum.Yes.getCode())
					.build();

			CarrierTExample example = new CarrierTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(w.getWarehouseId())
					.andCompanyIdEqualTo(w.getCompanyId())
					.andCarrierIdEqualTo(w.getCarrierId());


			int row = carrierDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(List<CarrierTEntity> carriers) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(carriers)) {
			throw new BusinessServiceException("no record add.");
		}

		for (CarrierTEntity w : carriers) {

			String code = w.getCarrierCode().toUpperCase();

			CarrierTEntity update = CarrierTEntity.builder()
					.carrierId(KeyUtils.getUID())
					.carrierCode(code)
					.carrierDescr(w.getCarrierDescr())
					.contact1(w.getContact1())
					.contact2(w.getContact2())
					.phone1(w.getPhone1())
					.phone2(w.getPhone2())
					.address1(w.getAddress1())
					.address2(w.getAddress2())
					.fax(w.getFax())
					.webSite(w.getWebSite())
					.email1(w.getEmail1())
					.email2(w.getEmail2())
					.active(w.getActive())
					.remark(w.getRemark())
					.createBy(w.getUpdateBy())
					.updateBy(w.getUpdateBy())
					.createTime(new Date())
					.companyId(w.getCompanyId())
					.warehouseId(w.getWarehouseId())
					.build();

			int row = carrierDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}
}
