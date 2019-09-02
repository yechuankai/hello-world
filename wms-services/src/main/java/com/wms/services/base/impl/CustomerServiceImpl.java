package com.wms.services.base.impl;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ICustomerTDao;
import com.wms.dao.example.CustomerTExample;
import com.wms.entity.auto.CustomerTEntity;
import com.wms.services.base.ICustomerService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerTDao customerDao;
	
	@Override
	public List<CustomerTEntity> find(PageRequest request) throws BusinessServiceException {
		CustomerTExample TExample = new CustomerTExample();
		CustomerTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(CustomerTEntity.Column.class, CustomerTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<CustomerTEntity> list = customerDao.selectByExample(TExample);
		
		return list;
	}

	@Override
	public CustomerTEntity find(CustomerTEntity customer) throws BusinessServiceException {
		CustomerTExample example = new CustomerTExample();
		CustomerTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(customer.getWarehouseId())
		.andCompanyIdEqualTo(customer.getCompanyId());

		int conditionCount = 0;
		if (customer.getCustomerId() != null) {
			criteria.andCustomerIdEqualTo(customer.getCustomerId());
			conditionCount++;
		}
		if (StringUtils.isNotEmpty(customer.getCustomerCode())) {
			criteria.andCustomerCodeEqualTo(customer.getCustomerCode());
			conditionCount++;
		}
		if (conditionCount == 0){
			return null;
		}
		
		CustomerTEntity selectCustomer = customerDao.selectOneByExample(example);
		if (selectCustomer == null) 
			throw new BusinessServiceException("customerServiceImpl", "customer.not.exists" , new Object[] {customer.getCustomerId()}); 
		return selectCustomer;
	}

	@Override
	public CustomerTEntity findByCode(CustomerTEntity customer) throws BusinessServiceException {
		CustomerTExample example = new CustomerTExample();
		CustomerTExample.Criteria criteria = example.createCriteria();
		criteria
				.andDelFlagEqualTo(YesNoEnum.No.getCode())
				.andWarehouseIdEqualTo(customer.getWarehouseId())
				.andCustomerCodeEqualTo(customer.getCustomerCode())
				.andCompanyIdEqualTo(customer.getCompanyId());

		CustomerTEntity selectCustomer = customerDao.selectOneByExample(example);
		return selectCustomer;
	}

	@Override
	public Boolean modify(AjaxRequest<List<CustomerTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}

		List<CustomerTEntity> list = request.getData();

		for (CustomerTEntity w : list) {

			CustomerTEntity update = CustomerTEntity.builder()
					.customerDescr(w.getCustomerDescr())
					.contact1(w.getContact1())
					.contact2(w.getContact2())
					.phone1(w.getPhone1())
					.phone2(w.getPhone2())
					.address1(w.getAddress1())
					.address2(w.getAddress2())
					.fax(w.getFax())
					.email(w.getEmail())
					.shipLabel(w.getShipLabel())
					.active(w.getActive())
					.remark(w.getRemark())
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.build();

			CustomerTExample example = new CustomerTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(w.getWarehouseId())
					.andCompanyIdEqualTo(w.getCompanyId())
					.andCustomerIdEqualTo(w.getCustomerId());

			int row = customerDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(AjaxRequest<List<CustomerTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}

		List<CustomerTEntity> list = request.getData();

		for (CustomerTEntity w : list) {

			String code = w.getCustomerCode().toUpperCase();

			CustomerTExample example = new CustomerTExample();
			example.createCriteria()
					.andDelFlagEqualTo(YesNoEnum.No.getCode())
					.andCompanyIdEqualTo(request.getCompanyId())
					.andWarehouseIdEqualTo(request.getWarehouseId())
					.andCustomerCodeEqualTo(code);
			long count = customerDao.countByExample(example);
			if (count > 0) {
				throw new BusinessServiceException("CustomerServiceImpl", "customer.record.exists" , new Object[] {code});
			}

			CustomerTEntity update = CustomerTEntity.builder()
					.customerId(KeyUtils.getUID())
					.customerCode(code)
					.customerDescr(w.getCustomerDescr())
					.contact1(w.getContact1())
					.contact2(w.getContact2())
					.phone1(w.getPhone1())
					.phone2(w.getPhone2())
					.address1(w.getAddress1())
					.address2(w.getAddress2())
					.fax(w.getFax())
					.email(w.getEmail())
					.shipLabel(w.getShipLabel())
					.active(w.getActive())
					.remark(w.getRemark())
					.createBy(request.getUserName())
					.createTime(new Date())
					.companyId(request.getCompanyId())
					.warehouseId(request.getWarehouseId())
					.build();

			int row = customerDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(List<CustomerTEntity> customers) throws BusinessServiceException {

		for (CustomerTEntity w : customers) {

			String code = w.getCustomerCode().toUpperCase();

			CustomerTExample example = new CustomerTExample();
			example.createCriteria()
					.andDelFlagEqualTo(YesNoEnum.No.getCode())
					.andCompanyIdEqualTo(w.getCompanyId())
					.andWarehouseIdEqualTo(w.getWarehouseId())
					.andCustomerCodeEqualTo(code);
			long count = customerDao.countByExample(example);
			if (count > 0) {
				throw new BusinessServiceException("CustomerServiceImpl", "customer.record.exists" , new Object[] {code});
			}

			CustomerTEntity update = CustomerTEntity.builder()
					.customerId(KeyUtils.getUID())
					.customerCode(code)
					.customerDescr(w.getCustomerDescr())
					.contact1(w.getContact1())
					.contact2(w.getContact2())
					.phone1(w.getPhone1())
					.phone2(w.getPhone2())
					.address1(w.getAddress1())
					.address2(w.getAddress2())
					.fax(w.getFax())
					.email(w.getEmail())
					.shipLabel(w.getShipLabel())
					.active(w.getActive())
					.remark(w.getRemark())
					.createBy(w.getUpdateBy())
					.createTime(new Date())
					.companyId(w.getCompanyId())
					.warehouseId(w.getWarehouseId())
					.build();

			int row = customerDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<CustomerTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<CustomerTEntity> list = request.getData();

		for (CustomerTEntity w : list) {
			CustomerTEntity update = CustomerTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.delFlag(YesNoEnum.Yes.getCode())
					.build();

			CustomerTExample example = new CustomerTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(w.getWarehouseId())
					.andCompanyIdEqualTo(w.getCompanyId())
					.andCustomerIdEqualTo(w.getCustomerId());


			int row = customerDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}

}
