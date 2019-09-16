package com.wms.services.base.impl;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISupplierTDao;
import com.wms.dao.example.SupplierTExample;
import com.wms.entity.auto.SupplierTEntity;
import com.wms.services.base.ISupplierService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	private ISupplierTDao supplierDao;
	
	@Override
	public List<SupplierTEntity> find(PageRequest request) throws BusinessServiceException {
		SupplierTExample TExample = new SupplierTExample();
		SupplierTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SupplierTEntity.Column.class,SupplierTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<SupplierTEntity> list = supplierDao.selectByExample(TExample);
		
		return list;
	}

	@Override
	public SupplierTEntity find(SupplierTEntity supplier) throws BusinessServiceException {
		SupplierTExample example = new SupplierTExample();
		SupplierTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(supplier.getWarehouseId())
		.andCompanyIdEqualTo(supplier.getCompanyId());

		int conditionCount = 0;
		if (supplier.getSupplierId() != null) {
			criteria.andSupplierIdEqualTo(supplier.getSupplierId());
			conditionCount++;
		}
		if (StringUtils.isNotEmpty(supplier.getSupplierCode())) {
			criteria.andSupplierCodeEqualTo(supplier.getSupplierCode());
			conditionCount++;
		}
		if (conditionCount == 0){
			return null;
		}
		SupplierTEntity selectSupplier = supplierDao.selectOneByExample(example);
		if (selectSupplier == null) 
			throw new BusinessServiceException("SupplierServiceImpl", "supplier.not.exists" , new Object[] {supplier.getSupplierId()}); 
		return selectSupplier;
	}

	@Override
	public SupplierTEntity findByCode(SupplierTEntity supplier) throws BusinessServiceException {
		SupplierTExample example = new SupplierTExample();
		SupplierTExample.Criteria criteria = example.createCriteria();
		criteria
				.andDelFlagEqualTo(YesNoEnum.No.getCode())
				.andWarehouseIdEqualTo(supplier.getWarehouseId())
				.andSupplierCodeEqualTo(supplier.getSupplierCode())
				.andCompanyIdEqualTo(supplier.getCompanyId());

		SupplierTEntity selectSupplier = supplierDao.selectOneByExample(example);

		return selectSupplier;
	}

	@Override
	public Boolean modify(AjaxRequest<List<SupplierTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}

		List<SupplierTEntity> list = request.getData();

		for (SupplierTEntity w : list) {

			SupplierTEntity update = SupplierTEntity.builder()
					.supplierDescr(w.getSupplierDescr())
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

			SupplierTExample example = new SupplierTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(w.getWarehouseId())
					.andCompanyIdEqualTo(w.getCompanyId())
					.andSupplierIdEqualTo(w.getSupplierId());

			int row = supplierDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(AjaxRequest<List<SupplierTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}

		List<SupplierTEntity> list = request.getData();

		for (SupplierTEntity w : list) {

			String code = w.getSupplierCode().toUpperCase();

			SupplierTExample example = new SupplierTExample();
			example.createCriteria()
					.andDelFlagEqualTo(YesNoEnum.No.getCode())
					.andCompanyIdEqualTo(request.getCompanyId())
					.andWarehouseIdEqualTo(request.getWarehouseId())
					.andSupplierCodeEqualTo(code);
			long count = supplierDao.countByExample(example);
			if (count > 0) {
				throw new BusinessServiceException("SupplierServiceImpl", "supplier.record.exists" , new Object[] {code});
			}

			SupplierTEntity update = SupplierTEntity.builder()
					.supplierId(KeyUtils.getUID())
					.supplierCode(code)
					.supplierDescr(w.getSupplierDescr())
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

			int row = supplierDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(List<SupplierTEntity> suppliers) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(suppliers)) {
			throw new BusinessServiceException("no record add.");
		}

		for (SupplierTEntity w : suppliers) {

			String code = w.getSupplierCode().toUpperCase();

			SupplierTEntity update = SupplierTEntity.builder()
					.supplierId(KeyUtils.getUID())
					.supplierCode(code)
					.supplierDescr(w.getSupplierDescr())
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
					.createTime(new Date())
					.companyId(w.getCompanyId())
					.warehouseId(w.getWarehouseId())
					.build();

			int row = supplierDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<SupplierTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<SupplierTEntity> list = request.getData();

		for (SupplierTEntity w : list) {
			SupplierTEntity update = SupplierTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.delFlag(YesNoEnum.Yes.getCode())
					.build();

			SupplierTExample example = new SupplierTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(w.getWarehouseId())
					.andCompanyIdEqualTo(w.getCompanyId())
					.andSupplierIdEqualTo(w.getSupplierId());


			int row = supplierDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}

}
