package com.wms.services.inventory.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.services.IExcelService;
import com.wms.common.enums.ExcelTemplateEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.dao.query.IInventoryMultiDao;
import com.wms.entity.auto.AllocateTEntity;
import com.wms.services.inventory.IInventoryMultiService;
import com.wms.services.outbound.IAllocateService;
import com.wms.vo.InventoryMultiCountVo;

@Service
public class InventroyMultiServiceImpl implements IInventoryMultiService{
	
	private static final String exp_1 = "%";
	private static final String exp_2 = "*";
	private static final String LIKE = " LIKE ";
	private static final String EQUALS = " = ";

	@Autowired
	private IInventoryMultiDao inventoryMultiDao;
	@Autowired
	private IAllocateService allocateService;
	
	@Override
	public List<InventoryMultiCountVo> selectByLocation(PageRequest request) {
		replaceExpression(request);
		List<InventoryMultiCountVo> list = inventoryMultiDao.selectByLocation(request);
		fitMultiSoftQuantityLocation(request.getWarehouseId(), request.getCompanyId(), list);
		return list;
	}

	@Override
	public List<InventoryMultiCountVo> selectByLPN(PageRequest request) {
		replaceExpression(request);
		return inventoryMultiDao.selectByLPN(request);
	}

	@Override
	public List<InventoryMultiCountVo> selectBySku(PageRequest request) {
		replaceExpression(request);
		List<InventoryMultiCountVo> list = inventoryMultiDao.selectBySku(request);
		fitMultiSoftQuantitySku(request.getWarehouseId(), request.getCompanyId(), list);
		return list;
	}

	@Override
	public List<InventoryMultiCountVo> selectByContainer(PageRequest request) {
		replaceExpression(request);
		List<InventoryMultiCountVo> list = inventoryMultiDao.selectByContainer(request);
		return list;
	}
	
	/**
	 * 填充软分配量,按库位分组时
	 * @param warehouseId
	 * @param companyId
	 * @param list
	 */
    private void fitMultiSoftQuantityLocation(Long warehouseId,Long companyId,List<InventoryMultiCountVo> list) {
    	HashSet<Long> skuIds = new HashSet<>();
    	HashSet<Long> locationIds = new HashSet<>();
    	for (InventoryMultiCountVo vo : list) {
			Long skuId = vo.getSkuId();
			Long locationId = vo.getLocationId();
			skuIds.add(skuId);
			locationIds.add(locationId);
    	}
    	List<AllocateTEntity> results = allocateService.findBySkuAndLocation(warehouseId, companyId, skuIds, locationIds);
    	//缓存结果集
    	Map<InventoryMultiCountVo, BigDecimal> cache = new HashMap<>();
    	for (InventoryMultiCountVo vo : list) {
    		BigDecimal softQuantity = BigDecimal.ZERO;
    		Long skuId = vo.getSkuId();
    		Long locationId = vo.getLocationId();
    		boolean cached = false;
    		
    		//先查看缓存中是否已计算出该值
    		Set<Entry<InventoryMultiCountVo,BigDecimal>> entrySet = cache.entrySet();
    		for (Entry<InventoryMultiCountVo,BigDecimal> entry : entrySet) {
				InventoryMultiCountVo key = entry.getKey();
				if (key.getSkuId().equals(vo.getSkuId()) && key.getLocationId().equals(vo.getLocationId())) {
					softQuantity = entry.getValue();
					cached = true;
				}
			}
    		//计算软分配量的值
    		if (!cached) {
    			for (AllocateTEntity result : results) {
    				if (result.getSkuId().equals(skuId) && result.getLocationId().equals(locationId)) {
    					softQuantity = softQuantity.add(result.getQuantityAllocated());
    				}	
    			}
    			cache.put(vo, softQuantity);
			}
	    	
	    	vo.setSoftAllocateAmount(softQuantity);
    	}
    }
	
    /**
     * 填充软分配量,仅按skuId分组时
     * @param warehouseId
     * @param companyId
     * @param list
     */
    private void fitMultiSoftQuantitySku(Long warehouseId,Long companyId,List<InventoryMultiCountVo> list) {
    	HashSet<Long> skuIds = new HashSet<>();
    	for (InventoryMultiCountVo vo : list) {
			skuIds.add(vo.getSkuId());
    	}
    	List<AllocateTEntity> results = allocateService.findBySkuAndLocation(warehouseId, companyId, skuIds, null);
    	//缓存结果集
    	Map<Long, BigDecimal> cache = new HashMap<>();
    	for (InventoryMultiCountVo vo : list) {
    		BigDecimal softQuantity = BigDecimal.ZERO;
    		Long skuId = vo.getSkuId();
    		boolean cached = false;
    		
    		//先查看缓存中是否已计算出该值
    		Set<Entry<Long,BigDecimal>> entrySet = cache.entrySet();
    		for (Entry<Long,BigDecimal> entry : entrySet) {
    			Long key = entry.getKey();
				if (key.equals(vo.getSkuId())) {
					softQuantity = entry.getValue();
					cached = true;
				}
			}
    		//计算软分配量的值
    		if (!cached) {
    			for (AllocateTEntity result : results) {
    				if (result.getSkuId().equals(skuId)) {
    					softQuantity = softQuantity.add(result.getQuantityAllocated());
    				}	
    			}
    			cache.put(vo.getSkuId(), softQuantity);
			}
	    	
	    	vo.setSoftAllocateAmount(softQuantity);
    	}
    }
    
	/**
	 * 根据容器导出数据
	 * @author liuxiangying.chnet
	 */
	@Service
	public class StockByContainer implements IExcelService<InventoryMultiCountVo>{
		@Override
		public ExcelTemplate<InventoryMultiCountVo> getExcelTemplate() {
			return new ExcelTemplate<>(ExcelTemplateEnum.StockByContainer.getCode(),InventoryMultiCountVo.class);
		}
		
		@Override
		public List<InventoryMultiCountVo> exportData(PageRequest request) throws BusinessServiceException {
			IInventoryMultiService bean = SpringUtils.getBean(IInventoryMultiService.class);
			return bean.selectByContainer(request);
		}
	}
	
	/**
	 * 根据LPN导出数据
	 * @author liuxiangying.chnet
	 */
	@Service
	public class StockByLpn implements IExcelService<InventoryMultiCountVo>{
		@Override
		public ExcelTemplate<InventoryMultiCountVo> getExcelTemplate() {
			return new ExcelTemplate<>(ExcelTemplateEnum.StockByLpn.getCode(),InventoryMultiCountVo.class);
		}
		
		@Override
		public List<InventoryMultiCountVo> exportData(PageRequest request) throws BusinessServiceException {
			IInventoryMultiService bean = SpringUtils.getBean(IInventoryMultiService.class);
			return bean.selectByLPN(request);
		}
	}
	
	/**
	 * 根据库位导出数据
	 * @author liuxiangying.chnet
	 */
	@Service
	public class StockByLocation implements IExcelService<InventoryMultiCountVo>{
		@Override
		public ExcelTemplate<InventoryMultiCountVo> getExcelTemplate() {
			return new ExcelTemplate<>(ExcelTemplateEnum.StockByLocation.getCode(),InventoryMultiCountVo.class);
		}
		
		@Override
		public List<InventoryMultiCountVo> exportData(PageRequest request) throws BusinessServiceException {
			IInventoryMultiService bean = SpringUtils.getBean(IInventoryMultiService.class);
			return bean.selectByLocation(request);
		}
	}
	
	/**
	 * 根据SKU导出数据
	 * @author liuxiangying.chnet
	 */
	@Service
	public class StockBySku implements IExcelService<InventoryMultiCountVo>{
		@Override
		public ExcelTemplate<InventoryMultiCountVo> getExcelTemplate() {
			return new ExcelTemplate<>(ExcelTemplateEnum.StockBySku.getCode(),InventoryMultiCountVo.class);
		}
		
		@Override
		public List<InventoryMultiCountVo> exportData(PageRequest request) throws BusinessServiceException {
			IInventoryMultiService bean = SpringUtils.getBean(IInventoryMultiService.class);
			return bean.selectBySku(request);
		}
	}
	
	/**
	 * 传入字符串存在%,*则使用模糊查询,不存在则使用等于查询
	 * @param request 参数map
	 */
	@SuppressWarnings("unchecked")
	static final void replaceExpression(PageRequest request) {
		QueryColums[] values = QueryColums.values();
		for (QueryColums queryColum : values) {
			String key = queryColum.javaProperty;
			if (request.containsKey(key)) {
				Object object = request.get(key);
				if (object == null) {
					continue;
				}
				String value = object.toString();
				if (StringUtils.isEmpty(value)) {
					continue;
				}
				
				if (StringUtils.isNotEmpty(value)) {
					boolean likeQuery = false;
					if (value.startsWith(exp_1) || value.endsWith(exp_1)) {
						likeQuery = true;
					}
					
					if (value.startsWith(exp_2)) {
						likeQuery = true;
						value = exp_1.concat(value.substring(1));
					}
					
					if (value.endsWith(exp_2)) {
						likeQuery = true;
						value = value.substring(0, value.length() - 1).concat(exp_1);
					}

					if (likeQuery) {
						request.put(queryColum.column,queryColum.column.concat(LIKE));
						request.put(key, value);
					}else {
						request.put(key, value);
						request.put(queryColum.column, queryColum.column.concat(EQUALS));
					}
				}
			}
		}
	}

	
	/**
	 * 可能输入模糊查询的项
	 * @author liuxiangying.chnet
	 */
	enum QueryColums{
		ownerCode("ownerCode","OWNER_CODE"),
		skuCode("skuCode","SKU_CODE"),
		skuAlias("skuAlias","SKU_ALIAS"),
		locationCode("locationCode","LOCATION_CODE"),
		containerNumber("containerNumber","CONTAINER_NUMBER"),
		lpnNumber("lpnNumber","LPN_NUMBER");
		private final String javaProperty;
		private final String column;
		
		private QueryColums(String javaProperty, String column) {
			this.javaProperty = javaProperty;
			this.column = column;
		}
		public String getJavaProperty() {
			return javaProperty;
		}
		public String getColumn() {
			return column;
		}
	}
}
