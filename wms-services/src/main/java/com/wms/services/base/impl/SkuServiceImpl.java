package com.wms.services.base.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.services.IExcelService;
import com.wms.common.enums.ExcelTemplateEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISkuTDao;
import com.wms.dao.example.SkuTExample;
import com.wms.entity.auto.*;
import com.wms.services.base.*;
import com.wms.services.inventory.IInventoryService;
import com.wms.vo.LotLabelVO;
import com.wms.vo.excel.SkuVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SkuServiceImpl implements ISkuService, IExcelService<SkuVO> {
	
	//缓存sku 批属性显示描述
	private static Map<String, LotLabelVO> skuLotLabel = Maps.newConcurrentMap();

	@Autowired
	private ISkuTDao skuDao;
	
	@Autowired
	private IInventoryService inventoryService;
	
	@Autowired
    private IPutawayStrategyHeaderService putawayStrategyHeaderService;

    @Autowired
    private IAllocateStrategyHeaderService allocateStrategyHeaderService;
    
    @Autowired
    private IOwnerService ownerService;
    
    @Autowired
    private IPackService packService;
    
    @Autowired
    private ILotValidateService lotValidateService;
    
	
	@Override
	public List<SkuTEntity> findByIds(SkuTEntity sku, Set<Long> ids)  throws BusinessServiceException{
		if (CollectionUtils.isEmpty(ids)) 
			return Lists.newArrayList();
		
		SkuTExample example = new SkuTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(sku.getWarehouseId())
		.andCompanyIdEqualTo(sku.getCompanyId())
		.andSkuIdIn(Lists.newArrayList(ids));
		
		List<SkuTEntity> list = skuDao.selectByExample(example);
		if (CollectionUtils.isEmpty(list))
			return Lists.newArrayList();
		
		return list;
		
	}
	
	@Override
	public List<SkuTEntity> findBySkuCodes(SkuTEntity sku, Set<String> codes) throws BusinessServiceException{
		if (CollectionUtils.isEmpty(codes))
			return Lists.newArrayList();
		
		SkuTExample example = new SkuTExample();
		SkuTExample.Criteria criteria = example.createCriteria();
		
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(sku.getWarehouseId())
		.andCompanyIdEqualTo(sku.getCompanyId())
		.andSkuCodeIn(Lists.newArrayList(codes));
		
		List<SkuTEntity> skus = skuDao.selectByExample(example);
		
		return skus;
	}

	@Override
	public List<SkuTEntity> find(PageRequest request) throws BusinessServiceException {
		SkuTExample TExample = new SkuTExample();
		SkuTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SkuTEntity.Column.class, SkuTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<SkuTEntity> skuList = skuDao.selectByExample(TExample);
		
		return skuList;
	}

	@Override
	public SkuTEntity find(SkuTEntity sku) throws BusinessServiceException {
		SkuTExample example = new SkuTExample();
		SkuTExample.Criteria criteria = example.createCriteria();
		
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(sku.getWarehouseId())
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
        if(StringUtils.isNotEmpty(sku.getOwnerCode())){
        	criteria.andOwnerCodeEqualTo(sku.getOwnerCode());
        	conditionCount++;
		}
        if (StringUtils.isNotEmpty(sku.getBarcode())) {
            criteria.andBarcodeEqualTo(sku.getBarcode());
            conditionCount++;
        }
        if (conditionCount == 0){
            return null;
        }
        
		SkuTEntity selectSku = skuDao.selectOneByExample(example);
		if (selectSku == null)
			throw new BusinessServiceException("SkuServiceImpl", "sku.not.exists" , new Object[] {sku.getSkuId() + "/" + sku.getSkuCode()});
		
		return selectSku;
	}
	
	@Override
	public LotLabelVO getSkuLotLabel(SkuTEntity sku) throws BusinessServiceException {
		StringBuilder sb = new StringBuilder();
		sb.append(sku.getCompanyId()).append(sku.getWarehouseId()).append(sku.getSkuCode());
		if (skuLotLabel.containsKey(sb.toString())) {
			return skuLotLabel.get(sb.toString());
		}
		SkuTEntity selectSku = find(sku);
		LotLabelVO label = new LotLabelVO();
		BeanUtils.copyBeanProp(label, selectSku);
		skuLotLabel.put(sb.toString(), label);
		return label;
	}
	
	private void setSkuLotLabel(SkuTEntity sku) throws BusinessServiceException {
		StringBuilder sb = new StringBuilder();
		sb.append(sku.getCompanyId()).append(sku.getWarehouseId()).append(sku.getSkuCode());
		SkuTEntity selectSku = find(sku);
		LotLabelVO label = new LotLabelVO();
		BeanUtils.copyBeanProp(label, selectSku);
		skuLotLabel.put(sb.toString(), label);
	}
	

	@Override
	public ExcelTemplate<SkuVO> getExcelTemplate() {
		return new ExcelTemplate<SkuVO>(ExcelTemplateEnum.Sku.getCode(), SkuVO.class);
	}

	@Override
	public void importData(AjaxRequest<List<SkuVO>> request) throws BusinessServiceException {
		List<SkuVO> list = request.getData();
		if (CollectionUtils.isEmpty(list)) {
			throw new BusinessServiceException("no data.");
		}
		for (SkuVO skuVO : list) {
			if(StringUtils.isBlank(skuVO.getSkuCode())){
				return;
			}
			SkuTEntity sku = new SkuTEntity();
			BeanUtils.copyBeanProp(sku, skuVO, Boolean.FALSE);
			Set<String> codes = Sets.newHashSet();
			codes.add(sku.getSkuCode());
			sku.setCompanyId(request.getCompanyId());
			sku.setWarehouseId(request.getWarehouseId());
			List<SkuTEntity> skus =findBySkuCodes(sku,codes);

			List<SkuTEntity> updates=Lists.newArrayList();
			request.setUserName((String) request.get(SkuTEntity.Column.updateBy.getJavaProperty()));
			if(CollectionUtils.isEmpty(skus)){
				//根据code没有找到数据就add
				updates.add(sku);
				add(new AjaxRequest<List<SkuTEntity>>(updates,request));
			}else {
				sku.setSkuId(skus.get(0).getSkuId());
				sku.setUpdateVersion(skus.get(0).getUpdateVersion());
				updates.add(sku);
				modify(new AjaxRequest<List<SkuTEntity>>(updates,request));
			}
		}
	}

	@Override
	public List<SkuVO> exportData(PageRequest request) throws BusinessServiceException {
		List<SkuVO> returnList = Lists.newArrayList();
		List<SkuTEntity> skus = find(request);
		if (CollectionUtils.isEmpty(skus)) {
			return returnList;
		}
		skus.forEach(d ->{
			SkuVO sku = new SkuVO();
			BeanUtils.copyBeanProp(sku, d);
			returnList.add(sku);
		});
		return returnList;
	}
	
	@Override
	public Boolean modify(AjaxRequest<List<SkuTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}
		
		List<SkuTEntity> list = request.getData();
		
		for (SkuTEntity sku : list) {
			
			SkuTEntity update = new SkuTEntity();
			BeanUtils.copyBeanProp(update, sku);
			validate(update);

			update.setUpdateBy(request.getUserName());
			update.setUpdateTime(new Date());


			SkuTExample example = new SkuTExample();
			example.createCriteria()
			.andWarehouseIdEqualTo(sku.getWarehouseId())
            .andCompanyIdEqualTo(sku.getCompanyId())
            .andSkuIdEqualTo(sku.getSkuId());
			
			int row = skuDao.updateWithVersionByExampleSelective(sku.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
			
			setSkuLotLabel(sku);
		}
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean delete(AjaxRequest<List<SkuTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<SkuTEntity> list = request.getData();
		
		for (SkuTEntity sku : list) {
			
			sku.setWarehouseId(request.getWarehouseId());
			sku.setCompanyId(request.getCompanyId());
			boolean flag = inventoryService.find(sku);
			if (flag)
				throw new BusinessServiceException("SkuServiceImpl", "sku.inventory.exists" , new Object[] {sku.getSkuId() + "/" + sku.getSkuCode()});
			
			 SkuTEntity update = SkuTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.delFlag(YesNoEnum.Yes.getCode())
			.build();
			
			SkuTExample example = new SkuTExample();
			example.createCriteria()
			.andWarehouseIdEqualTo(sku.getWarehouseId())
            .andCompanyIdEqualTo(sku.getCompanyId())
            .andSkuIdEqualTo(sku.getSkuId());
			
			int row = skuDao.updateWithVersionByExampleSelective(sku.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
			
		}
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean add(AjaxRequest<List<SkuTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}
		
		List<SkuTEntity> list = request.getData();
		
		for (SkuTEntity sku : list) {
            String code = sku.getSkuCode().toUpperCase();
			sku.setWarehouseId(request.getWarehouseId());
			sku.setCompanyId(request.getCompanyId());
			sku.setSkuCode(code);
            SkuTExample example = new SkuTExample();
            example.createCriteria()
                    .andDelFlagEqualTo(YesNoEnum.No.getCode())
					.andCompanyIdEqualTo(request.getCompanyId())
					.andWarehouseIdEqualTo(request.getWarehouseId())
					.andOwnerCodeEqualTo(sku.getOwnerCode())
                    .andSkuCodeEqualTo(code);
            Long count = skuDao.countByExample(example);
            if (count > 0) {
                throw new BusinessServiceException("SkuServiceImpl", "sku.record.exists" , new Object[] {sku.getSkuCode()});
            }
			
			validate(sku);
			sku.setSkuId(KeyUtils.getUID());
			sku.setUpdateBy(request.getUserName());
			sku.setCreateBy(request.getUserName());
			sku.setCreateTime(new Date());
			sku.setUpdateTime(new Date());
			
			int row = skuDao.insertSelective(sku);
			if (row == 0) {
				throw new BusinessServiceException("record insert error.");
			}
			
			setSkuLotLabel(sku);
		}
		return Boolean.TRUE;
	}
	
	private Boolean validate(SkuTEntity sku) {
		
		if (StringUtils.isEmpty(sku.getOwnerCode()))
			throw new BusinessServiceException("SkuServiceImpl", "owner.isnull" , null); 
		
		if (StringUtils.isEmpty(sku.getSkuCode()))
			throw new BusinessServiceException("SkuServiceImpl", "sku.isnull" , null); 
		
		if (StringUtils.isEmpty(sku.getUom()))
			throw new BusinessServiceException("SkuServiceImpl", "uom.isnull" , null); 
		
		//获取货主ID
		OwnerTEntity owner = ownerService.find(OwnerTEntity.builder()
                .warehouseId(sku.getWarehouseId())
                .companyId(sku.getCompanyId())
                .ownerCode(sku.getOwnerCode())
                .build());
		sku.setOwnerId(owner.getOwnerId());
		sku.setOwnerCode(owner.getOwnerCode());
		
		//获取包装ID
		PackTEntity pack = packService.find(PackTEntity.builder()
                .warehouseId(sku.getWarehouseId())
                .companyId(sku.getCompanyId())
                .packCode(sku.getPackCode())
                .build());
		sku.setPackId(pack.getPackId());
		sku.setPackCode(pack.getPackCode());
		
		if (!(sku.getUom().equals(pack.getUom()) 
				|| sku.getUom().equals(pack.getUomInner())
				|| sku.getUom().equals(pack.getUomCase()))) {
			throw new BusinessServiceException("SkuServiceImpl", "pack.uom.error" , new Object[] {pack.getPackCode(), sku.getUom()}); 
		}
		
		
		//获取上架策略ID
		PutawayStrategyTEntity putawayStrategy = putawayStrategyHeaderService.find(PutawayStrategyTEntity.builder()
				.warehouseId(sku.getWarehouseId())
                .companyId(sku.getCompanyId())
                .putawayStrategyCode(sku.getPutawayStrategyCode())
                .build());
		sku.setPutawayStrategyId(putawayStrategy.getPutawayStrategyId());
		sku.setPutawayStrategyCode(putawayStrategy.getPutawayStrategyCode());
		
		
		//获取分配策略ID
		AllocateStrategyTEntity allocateStrategy = allocateStrategyHeaderService.find(AllocateStrategyTEntity.builder()
				.warehouseId(sku.getWarehouseId())
                .companyId(sku.getCompanyId())
                .allocateStrategyCode(sku.getAllocateStrategyCode())
                .build());
		sku.setAllocateStrategyId(allocateStrategy.getAllocateStrategyId());
		sku.setAllocateStrategyCode(allocateStrategy.getAllocateStrategyCode());
		
		//获取批次验证规则
		LotValidateTEntity lotv = lotValidateService.find(LotValidateTEntity.builder()
				.warehouseId(sku.getWarehouseId())
                .companyId(sku.getCompanyId())
                .lotValidateCode(sku.getLotValidateCode())
                .build());
		sku.setLotValidateId(lotv.getLotValidateId());
		sku.setLotValidateCode(lotv.getLotValidateCode());
		
		//验证毛重/净重
		if (sku.getWeightGross().compareTo(sku.getWeightNet()) < 0) 
			throw new BusinessServiceException("SkuServiceImpl", "weight.gross.morethen.net" , new Object[] {sku.getSkuCode()}); 
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(List<SkuTEntity> list) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(list)) {
			throw new BusinessServiceException("no record add.");
		}

		for (SkuTEntity sku : list) {
			String code = sku.getSkuCode().toUpperCase();
			sku.setWarehouseId(sku.getWarehouseId());
			sku.setCompanyId(sku.getCompanyId());
			sku.setSkuCode(code);

			validate(sku);
			sku.setSkuId(KeyUtils.getUID());
			sku.setCreateBy(sku.getUpdateBy());
			sku.setCreateTime(new Date());
			sku.setUpdateTime(new Date());

			int row = skuDao.insertSelective(sku);
			if (row == 0) {
				throw new BusinessServiceException("record insert error.");
			}

			setSkuLotLabel(sku);
		}
		return Boolean.TRUE;
	}
}
