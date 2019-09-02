package com.wms.services.outbound;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.allocate.AllocateStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.AllocateTEntity;
import com.wms.vo.allocate.AllocateVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description: 出库-分配明细服务类
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-03 17:00
 **/

public interface IAllocateService {
    List<AllocateTEntity> find(PageRequest request) throws BusinessServiceException;
    
    AllocateTEntity find(AllocateTEntity allocate) throws BusinessServiceException;
    
    List<AllocateTEntity> findBySourceNumber(AllocateTEntity allocate, Set<Long> sourceNumbers, AllocateStatusEnum ... status) throws BusinessServiceException;
    
    Boolean add(List<AllocateTEntity> list) throws BusinessServiceException;
   
    Boolean modify(List<AllocateTEntity> list) throws BusinessServiceException;
    
    Map<Long, BigDecimal> getSoftQuantityByLocation(AllocateTEntity allocate) throws BusinessServiceException;
    
    Map<Long, BigDecimal> getSoftQuantityBySku(AllocateTEntity allocate) throws BusinessServiceException;
    
    Boolean delete(List<AllocateTEntity> list) throws BusinessServiceException;
    
    Boolean pick(List<AllocateVO> list) throws BusinessServiceException;
    
    Boolean ship(List<AllocateVO> list) throws BusinessServiceException;
    
    Boolean delete(AjaxRequest<List<AllocateTEntity>> request) throws BusinessServiceException;
    
    Boolean pick(AjaxRequest<List<AllocateVO>> request) throws BusinessServiceException;
    
    Boolean ship(AjaxRequest<List<AllocateVO>> request) throws BusinessServiceException;

    /**
     * @Description 发放
     * @param list
     * @return
     * @throws BusinessServiceException
     */
    Boolean release(List<AllocateVO> list) throws BusinessServiceException;
    
    /**
     * 
     * <p>Title: findBySourceBillNumber</p>  
     * <p>Description: 根据出库单号查询分配明细</p>  
     * @author yupu.chnet
     * @date 2019年8月28日
     * @param allocate
     * @param sourceBillNumber
     * @param status
     * @return
     * @throws BusinessServiceException
     */
    List<AllocateTEntity> findBySourceBillNumber(AllocateTEntity allocate,  String sourceBillNumber, AllocateStatusEnum ... status) throws BusinessServiceException;

    /**
     * 根据sku的id集合,库位的id,集合查找软分配的数据集
     * @param warehouseId 仓库id
     * @param companyId 公司id
     * @param skuIds 货品id集合
     * @param locationIds 库位id集合
     * @return
     */
	List<AllocateTEntity> findBySkuAndLocation(Long warehouseId, Long companyId, Set<Long> skuIds,
			Set<Long> locationIds);
}
