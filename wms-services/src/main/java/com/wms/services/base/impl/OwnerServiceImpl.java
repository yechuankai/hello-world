package com.wms.services.base.impl;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.BarCodeTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IBarcodeTDao;
import com.wms.dao.auto.IOwnerTDao;
import com.wms.dao.example.OwnerTExample;
import com.wms.entity.auto.BarcodeTEntity;
import com.wms.entity.auto.OwnerTEntity;
import com.wms.services.base.IOwnerService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class OwnerServiceImpl implements IOwnerService {

	@Autowired
	private IOwnerTDao ownerDao;

	@Autowired
	private IBarcodeTDao barCodeDao;

	@Override
	public List<OwnerTEntity> find(PageRequest request) throws BusinessServiceException {
		OwnerTExample TExample = new OwnerTExample();
		OwnerTExample.Criteria TExampleCriteria  = TExample.createCriteria();

		//转换查询方法
		ExampleUtils.create(OwnerTEntity.Column.class, OwnerTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);

		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());

		List<OwnerTEntity> ownerList = ownerDao.selectByExample(TExample);

		return ownerList;
	}

	@Override
	public OwnerTEntity find(OwnerTEntity owner) throws BusinessServiceException {
		OwnerTExample example = new OwnerTExample();
		OwnerTExample.Criteria criteria = example.createCriteria();

		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode()).andWarehouseIdEqualTo(owner.getWarehouseId())
				.andCompanyIdEqualTo(owner.getCompanyId());

		int conditionCount = 0;
		if (owner.getOwnerId() != null) {
			criteria.andOwnerIdEqualTo(owner.getOwnerId());
			conditionCount++;
		}
		if (StringUtils.isNotEmpty(owner.getOwnerCode())) {
			criteria.andOwnerCodeEqualTo(owner.getOwnerCode());
			conditionCount++;
		}
		if (conditionCount == 0) {
			return null;
		}

		OwnerTEntity selectOwner = ownerDao.selectOneByExample(example);
		if (selectOwner == null) {
			throw new BusinessServiceException("OwnerServiceImpl", "owner.record.not.exists",
					new Object[] { owner.getOwnerId() == null ? owner.getOwnerCode() : owner.getOwnerId() });
		}
		return selectOwner;
	}
	
	@Override
	public List<OwnerTEntity> findAll(OwnerTEntity owner) throws BusinessServiceException {

		OwnerTExample example = new OwnerTExample();
		OwnerTExample.Criteria criteria = example.createCriteria();

		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode()).andActiveEqualTo(YesNoEnum.Yes.getCode())
				.andWarehouseIdEqualTo(owner.getWarehouseId()).andCompanyIdEqualTo(owner.getCompanyId());

		List<OwnerTEntity> selectOwner = ownerDao.selectByExample(example);

		return selectOwner;

	}

	@Override
	public List<OwnerTEntity> findByOwnerCodes(OwnerTEntity owner, Set<String> codes) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(codes))
			return Lists.newArrayList();

		OwnerTExample example = new OwnerTExample();
		OwnerTExample.Criteria criteria = example.createCriteria();

		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(owner.getWarehouseId())
		.andCompanyIdEqualTo(owner.getCompanyId())
		.andOwnerCodeIn(Lists.newArrayList(codes));

		List<OwnerTEntity> owners = ownerDao.selectByExample(example);

		return owners;
	}

	@Override
	public Boolean modify(AjaxRequest<List<OwnerTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}
		List<OwnerTEntity> list = request.getData();
		for (OwnerTEntity o : list) {
			o.setWarehouseId(o.getWarehouseId());
			o.setCompanyId(o.getCompanyId());
			o.setUpdateTime(new Date());
			o.setUpdateBy(request.getUserName());
			modify(o);
		}
		return Boolean.TRUE;
	}
	
	public Boolean modify(OwnerTEntity owner) throws BusinessServiceException {
		OwnerTExample example = new OwnerTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(owner.getWarehouseId())
        .andCompanyIdEqualTo(owner.getCompanyId())
        .andOwnerIdEqualTo(owner.getOwnerId());
		int row = ownerDao.updateWithVersionByExampleSelective(owner.getUpdateVersion(), owner, example);
		if (row == 0) {
			throw new BusinessServiceException("record update error.");
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(AjaxRequest<List<OwnerTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}

		List<OwnerTEntity> list = request.getData();

		for (OwnerTEntity o : list) {

			String code = o.getOwnerCode().toUpperCase();

			OwnerTExample TExample = new OwnerTExample();
			TExample.createCriteria()
                    .andDelFlagEqualTo(YesNoEnum.No.getCode())
                    .andWarehouseIdEqualTo(request.getWarehouseId())
                    .andCompanyIdEqualTo(request.getCompanyId())
                    .andOwnerCodeEqualTo(code);
			Long count = ownerDao.countByExample(TExample);
			if (count > 0) {
				throw new BusinessServiceException("OwnerServiceImpl", "owner.record.exists" , new Object[] {code});
			}

			OwnerTEntity update = OwnerTEntity.builder()
					.ownerId(KeyUtils.getUID())
					.ownerCode(code)
					.ownerDescr(o.getOwnerDescr())
					.contact1(o.getContact1())
					.contact2(o.getContact2())
					.phone1(o.getPhone1())
					.phone2(o.getPhone2())
					.address1(o.getAddress1())
					.address2(o.getAddress2())
					.fax(o.getFax())
					.webSite(o.getWebSite())
					.email1(o.getEmail1())
					.email2(o.getEmail2())
					.barcodeLength(o.getBarcodeLength())
					.barcodePrefix(o.getBarcodePrefix())
					.barcodeStart(o.getBarcodeStart())
					.active(o.getActive())
					.remark(o.getRemark())
					.createBy(request.getUserName())
					.createTime(new Date())
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.companyId(request.getCompanyId())
					.warehouseId(request.getWarehouseId())
					.build();

			int row = ownerDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(List<OwnerTEntity> owners) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(owners)){
			throw new BusinessServiceException("no record add.");
		}

		for (OwnerTEntity o : owners) {

			String code = o.getOwnerCode().toUpperCase();

			OwnerTExample TExample = new OwnerTExample();
			TExample.createCriteria()
					.andDelFlagEqualTo(YesNoEnum.No.getCode())
					.andWarehouseIdEqualTo(o.getWarehouseId())
					.andCompanyIdEqualTo(o.getCompanyId())
					.andOwnerCodeEqualTo(code);
			Long count = ownerDao.countByExample(TExample);
			if (count > 0) {
				throw new BusinessServiceException("OwnerServiceImpl", "owner.record.exists" , new Object[] {code});
			}

			OwnerTEntity update = OwnerTEntity.builder()
					.ownerId(KeyUtils.getUID())
					.ownerCode(code)
					.ownerDescr(o.getOwnerDescr())
					.contact1(o.getContact1())
					.contact2(o.getContact2())
					.phone1(o.getPhone1())
					.phone2(o.getPhone2())
					.address1(o.getAddress1())
					.address2(o.getAddress2())
					.fax(o.getFax())
					.webSite(o.getWebSite())
					.email1(o.getEmail1())
					.email2(o.getEmail2())
					.barcodeLength(o.getBarcodeLength())
					.barcodePrefix(o.getBarcodePrefix())
					.barcodeStart(o.getBarcodeStart())
					.active(o.getActive())
					.remark(o.getRemark())
					.createBy(o.getUpdateBy())
					.createTime(new Date())
					.updateBy(o.getUpdateBy())
					.updateTime(new Date())
					.companyId(o.getCompanyId())
					.warehouseId(o.getWarehouseId())
					.build();

			int row = ownerDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<OwnerTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<OwnerTEntity> list = request.getData();

		for (OwnerTEntity w : list) {
			OwnerTEntity update = OwnerTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.delFlag(YesNoEnum.Yes.getCode())
			.build();

			OwnerTExample example = new OwnerTExample();
			example.createCriteria()
			.andWarehouseIdEqualTo(w.getWarehouseId())
            .andCompanyIdEqualTo(w.getCompanyId())
            .andOwnerIdEqualTo(w.getOwnerId());


			int row = ownerDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Long generatorBarcode(AjaxRequest<OwnerTEntity> request, Long count) throws BusinessServiceException {
		OwnerTEntity owner = request.getData();
		owner.setWarehouseId(request.getWarehouseId());
		owner.setCompanyId(request.getCompanyId());

		OwnerTEntity selectOwner = find(owner);

		List<String> barcodes = Lists.newArrayList();
		for (int i = 1; i <= count; i++) {
			StringBuilder sb = new StringBuilder();
			if (StringUtils.isNotEmpty(selectOwner.getBarcodePrefix()))
				sb.append(selectOwner.getBarcodePrefix());

			//获取序号长度
			long sequenceLength = selectOwner.getBarcodeLength() - sb.length();
			StringBuilder defaultSequence = new StringBuilder(KeyUtils.ZERO);
			defaultSequence.append((selectOwner.getBarcodeStart() + i));
			int start = (int) (defaultSequence.length() - sequenceLength);
			int end = defaultSequence.length();
			sb.append(defaultSequence.substring( start , end));

			barcodes.add(sb.toString());
		}
		//保存到条码表
		final long batchId = KeyUtils.getUID();
		barcodes.forEach(b -> {
			BarcodeTEntity barcode = new BarcodeTEntity();
			barcode.setCompanyId(request.getCompanyId());
			barcode.setWarehouseId(request.getWarehouseId());
			barcode.setUpdateBy(request.getUserName());
			barcode.setCreateBy(request.getUserName());
			barcode.setUpdateTime(new Date());
			barcode.setCreateTime(new Date());
			barcode.setType(BarCodeTypeEnum.Lpn.getCode());
			barcode.setBarcodeId(KeyUtils.getUID());
			barcode.setBarcode(b);
			barcode.setBatchId(batchId);
			barCodeDao.insertSelective(barcode);
		});

		OwnerTEntity update = OwnerTEntity.builder()
				.barcodeStart(selectOwner.getBarcodeStart() + count)
				.updateBy(request.getUserName())
				.updateTime(new Date())
				.build();

		OwnerTExample example = new OwnerTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(request.getWarehouseId())
        .andCompanyIdEqualTo(request.getCompanyId())
        .andOwnerIdEqualTo(selectOwner.getOwnerId());

		int row = ownerDao.updateWithVersionByExampleSelective(selectOwner.getUpdateVersion(), update, example);
		if (row == 0) {
			throw new BusinessServiceException("record update error.");
		}


		return batchId;
	}

}
