package com.wms.services.base;

import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.*;
import com.wms.vo.inventory.EntInventoryOnhandVO;

import java.util.List;

/**
 * @description: 企业基础数据服务
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-21 10:50
 **/

public interface IEnterpriseService {
    EntSkuTEntity findSku(EntSkuTEntity sku) throws BusinessServiceException;

    List<EntSkuTEntity> findSku(PageRequest request) throws BusinessServiceException;

    EntPackTEntity findPack(EntPackTEntity pack) throws BusinessServiceException;

    List<EntPackTEntity> findPack(PageRequest request) throws BusinessServiceException;

    EntSupplierTEntity findSupplier(EntSupplierTEntity supplier) throws BusinessServiceException;

    List<EntSupplierTEntity> findSupplier(PageRequest request) throws BusinessServiceException;

    EntOwnerTEntity findOwner(EntOwnerTEntity owner) throws BusinessServiceException;

    List<EntOwnerTEntity> findOwner(PageRequest request) throws BusinessServiceException;

    EntCustomerTEntity findCustomer(EntCustomerTEntity customer) throws BusinessServiceException;

    List<EntCustomerTEntity> findCustomer(PageRequest request) throws BusinessServiceException;

    List<EntInventoryOnhandVO> findInventoryOnhand(PageRequest request) throws BusinessServiceException;

    List<EntCarrierTEntity> findCarrier(PageRequest request) throws BusinessServiceException;

    EntCarrierTEntity findCarrier(EntCarrierTEntity carrier) throws BusinessServiceException;
}
