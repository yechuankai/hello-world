package com.wms.services.base.impl;

import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.dao.auto.*;
import com.wms.dao.example.*;
import com.wms.entity.auto.*;
import com.wms.services.base.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-21 10:53
 **/
@Service
public class EnterpriseServiceImpl implements IEnterpriseService {

    @Autowired
    IEntSkuTDao skuDao;

    @Autowired
    IEntCustomerTDao customerDao;

    @Autowired
    IEntOwnerTDao ownerDao;

    @Autowired
    IEntSupplierTDao supplierDao;

    @Autowired
    IEntPackTDao packDao;

    @Autowired
    IEntCarrierTDao carrierDao;

    @Autowired
    IEntInventoryOnhandTDao inventoryOnhandDao;

    @Override
    public EntSkuTEntity findSku(EntSkuTEntity sku) throws BusinessServiceException {
        EntSkuTExample example = new EntSkuTExample();
        EntSkuTExample.Criteria criteria = example.createCriteria();

        criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
                .andCompanyIdEqualTo(sku.getCompanyId());

        int conditionCount = 0;
        if (sku.getSkuId() != null) {
            criteria.andSkuIdEqualTo(sku.getSkuId());
            conditionCount++;
        }
        if (StringUtils.isNotEmpty(sku.getSkuCode())) {
            criteria.andSkuCodeEqualTo(sku.getSkuCode());
            conditionCount++;
        }
        if (StringUtils.isNotEmpty(sku.getBarcode())) {
            criteria.andBarcodeEqualTo(sku.getBarcode());
            conditionCount++;
        }
        if(null != sku.getWarehouseId()){
            criteria.andWarehouseIdEqualTo(sku.getWarehouseId());
            conditionCount++;
        }
        if (conditionCount == 0){
            return null;
        }

        EntSkuTEntity selectSku = skuDao.selectOneByExample(example);

        return selectSku;
    }

    @Override
    public List<EntSkuTEntity> findSku(PageRequest request) throws BusinessServiceException {
        EntSkuTExample example = new EntSkuTExample();
        EntSkuTExample.Criteria TExampleCriteria  = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(EntSkuTEntity.Column.class, EntSkuTExample.Criterion.class)
                .criteria(TExampleCriteria)
                .data(request)
                .build(request)
                .orderby(example);

        TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        TExampleCriteria.andActiveEqualTo(YesNoEnum.Yes.getCode());

        List<EntSkuTEntity> skuList = skuDao.selectByExample(example);

        return skuList;
    }

    @Override
    public EntPackTEntity findPack(EntPackTEntity pack) throws BusinessServiceException {
        EntPackTExample example = new EntPackTExample();
        EntPackTExample.Criteria criteria = example.createCriteria();

        criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
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

        EntPackTEntity select = packDao.selectOneByExample(example);

        return select;
    }

    @Override
    public List<EntPackTEntity> findPack(PageRequest request) throws BusinessServiceException {
        EntPackTExample example = new EntPackTExample();
        EntPackTExample.Criteria TExampleCriteria  = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(EntPackTEntity.Column.class, EntPackTExample.Criterion.class)
                .criteria(TExampleCriteria)
                .data(request)
                .build(request)
                .orderby(example);

        TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        TExampleCriteria.andActiveEqualTo(YesNoEnum.Yes.getCode());

        List<EntPackTEntity> List = packDao.selectByExample(example);

        return List;
    }

    @Override
    public EntSupplierTEntity findSupplier(EntSupplierTEntity supplier) throws BusinessServiceException {
        EntSupplierTExample example = new EntSupplierTExample();
        EntSupplierTExample.Criteria criteria = example.createCriteria();
        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
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
        EntSupplierTEntity selectSupplier = supplierDao.selectOneByExample(example);

        return selectSupplier;
    }

    @Override
    public List<EntSupplierTEntity> findSupplier(PageRequest request) throws BusinessServiceException {
        EntSupplierTExample example = new EntSupplierTExample();
        EntSupplierTExample.Criteria TExampleCriteria  = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(EntSupplierTEntity.Column.class,EntSupplierTExample.Criterion.class)
                .criteria(TExampleCriteria)
                .data(request)
                .build(request)
                .orderby(example);

        TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        TExampleCriteria.andActiveEqualTo(YesNoEnum.Yes.getCode());

        List<EntSupplierTEntity> list = supplierDao.selectByExample(example);

        return list;
    }

    @Override
    public EntOwnerTEntity findOwner(EntOwnerTEntity owner) throws BusinessServiceException {
        EntOwnerTExample example = new EntOwnerTExample();
        EntOwnerTExample.Criteria criteria = example.createCriteria();

        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
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
        if (conditionCount == 0){
            return null;
        }

        EntOwnerTEntity selectOwner = ownerDao.selectOneByExample(example);
        return selectOwner;
    }

    @Override
    public List<EntOwnerTEntity> findOwner(PageRequest request) throws BusinessServiceException {
        EntOwnerTExample example = new EntOwnerTExample();
        EntOwnerTExample.Criteria TExampleCriteria  = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(EntOwnerTEntity.Column.class, EntOwnerTExample.Criterion.class)
                .criteria(TExampleCriteria)
                .data(request)
                .build(request)
                .orderby(example);

        TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        TExampleCriteria.andActiveEqualTo(YesNoEnum.Yes.getCode());

        List<EntOwnerTEntity> ownerList = ownerDao.selectByExample(example);

        return ownerList;
    }

    @Override
    public EntCustomerTEntity findCustomer(EntCustomerTEntity customer) throws BusinessServiceException {
        EntCustomerTExample example = new EntCustomerTExample();
        EntCustomerTExample.Criteria criteria = example.createCriteria();
        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
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

        EntCustomerTEntity selectCustomer = customerDao.selectOneByExample(example);
        return selectCustomer;
    }

    @Override
    public List<EntCustomerTEntity> findCustomer(PageRequest request) throws BusinessServiceException {
        EntCustomerTExample example = new EntCustomerTExample();
        EntCustomerTExample.Criteria TExampleCriteria  = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(EntCustomerTEntity.Column.class, EntCustomerTExample.Criterion.class)
                .criteria(TExampleCriteria)
                .data(request)
                .build(request)
                .orderby(example);

        TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        TExampleCriteria.andActiveEqualTo(YesNoEnum.Yes.getCode());

        List<EntCustomerTEntity> list = customerDao.selectByExample(example);

        return list;
    }

    @Override
    public List<EntInventoryOnhandTEntity> findInventoryOnhand(PageRequest request) throws BusinessServiceException {
        EntInventoryOnhandTExample example = new EntInventoryOnhandTExample();
        EntInventoryOnhandTExample.Criteria exampleCriteria  = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(EntInventoryOnhandTEntity.Column.class, EntInventoryOnhandTExample.Criterion.class)
                .criteria(exampleCriteria)
                .data(request)
                .build(request)
                .orderby(example);

        exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());

        List<EntInventoryOnhandTEntity> list = inventoryOnhandDao.selectByExample(example);

        return list;
    }

    @Override
    public List<EntCarrierTEntity> findCarrier(PageRequest request) throws BusinessServiceException {
        EntCarrierTExample example = new EntCarrierTExample();
        EntCarrierTExample.Criteria TExampleCriteria  = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(EntCarrierTEntity.Column.class, EntCarrierTExample.Criterion.class)
                .criteria(TExampleCriteria)
                .data(request)
                .build(request)
                .orderby(example);

        TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        TExampleCriteria.andActiveEqualTo(YesNoEnum.Yes.getCode());

        List<EntCarrierTEntity> list = carrierDao.selectByExample(example);

        return list;
    }

    @Override
    public EntCarrierTEntity findCarrier(EntCarrierTEntity carrier) throws BusinessServiceException {
        EntCarrierTExample example = new EntCarrierTExample();
        EntCarrierTExample.Criteria criteria = example.createCriteria();
        criteria
                .andDelFlagEqualTo(YesNoEnum.No.getCode())
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

        EntCarrierTEntity selectCarrier = carrierDao.selectOneByExample(example);
        return selectCarrier;
    }
}
