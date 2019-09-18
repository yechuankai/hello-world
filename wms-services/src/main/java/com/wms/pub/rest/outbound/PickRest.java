package com.wms.pub.rest.outbound;

import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.enums.InboundStatusEnum;
import com.wms.common.enums.allocate.AllocateStatusEnum;
import com.wms.common.enums.allocate.AllocateStrategyTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.dao.query.IAllocateQueryDao;
import com.wms.entity.auto.*;
import com.wms.services.base.ILocationService;
import com.wms.services.base.ILotValidateService;
import com.wms.services.base.IPackService;
import com.wms.services.base.ISkuService;
import com.wms.services.inventory.ILotService;
import com.wms.services.outbound.IAllocateService;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.LotLabelVO;
import com.wms.vo.allocate.AllocateVO;
import com.wms.vo.outbound.OutboundVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 拣货对外服务
 * @author yechuankai.chnet
 *
 */
@RestController
@RequestMapping("/services/public/outbound/pick")
public class PickRest extends BaseController {

    @Autowired
    private IAllocateService allocateService;
    @Autowired
    private IAllocateQueryDao allocateQueryDao;
    @Autowired
    private IOutboundHeaderService outboundHeaderService;
    @Autowired
	private IPackService packService;
	@Autowired
	private ISkuService skuService;
	@Autowired
	private ILocationService locService;
	@Autowired
	private ILotValidateService lotvService;
	@Autowired
	private ILotService lotService;
	
	
	@RequestMapping("getOutbound")
	public AjaxResult<OutboundVO> getOutbound(@RequestBody String req){
		AjaxRequest<OutboundVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<OutboundVO>>() {});
		try {
			OutboundVO outbound = request.getData();
			outbound.setWarehouseId(request.getWarehouseId());
			outbound.setCompanyId(request.getCompanyId());
			OutboundVO header = outboundHeaderService.find(outbound);
			if (InboundStatusEnum.Cancel.getCode().equals(header.getStatus()))
				throw new BusinessServiceException("PickRest", "outbound.status.not.process" , new Object[] {header.getOutboundNumber()});
			return success(header);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	@RequestMapping("getPickList")
	public AjaxResult<List<AllocateVO>> getPickList(@RequestBody String req){
		AjaxRequest<AllocateVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<AllocateVO>>() {});
		try {
			AllocateVO detail = request.getData();
			detail.setWarehouseId(request.getWarehouseId());
			detail.setCompanyId(request.getCompanyId());
			
			
			//验证出库单是否存在
			OutboundHeaderTEntity header = outboundHeaderService.find(OutboundHeaderTEntity.builder()
										.warehouseId(request.getWarehouseId())
										.companyId(request.getCompanyId())
										.outboundNumber(detail.getSourceBillNumber())
										.build());
			
			//根据出库订单获取明细
			
			if (StringUtils.isNotBlank(detail.getSkuCode())) {
				detail.setSkuCode(detail.getSkuCode().toUpperCase());
				//验证货品扫描条码
				SkuTEntity sku = null;
				try {
					//查询货品 按条码查询
					sku = skuService.find(SkuTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).barcode(detail.getSkuCode()).ownerCode(detail.getOwnerCode()).build());
					detail.setSkuCode(sku.getSkuCode());
				} catch (BusinessServiceException e) {
					sku = skuService.find(SkuTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).skuCode(detail.getSkuCode()).ownerCode(detail.getOwnerCode()).build());
					detail.setSkuCode(sku.getSkuCode());
				}
			}
			
			List<AllocateTEntity> list = allocateService.findBySourceBillNumber(detail, header.getOutboundNumber(), AllocateStatusEnum.Allocated, AllocateStatusEnum.Release);
			if (CollectionUtils.isEmpty(list))
				throw new BusinessServiceException("PickRest", "allocate.not.exists" , new Object[] {header.getOutboundNumber()});
			
			Set<Long> locIds = list.stream().map(AllocateTEntity::getLocationId).collect(Collectors.toSet());
			List<LocationTEntity> locList = locService.findByLocationIds(LocationTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).build(), locIds);
			Map<Long, LocationTEntity> locMap = locList.stream().collect(Collectors.toMap(LocationTEntity::getLocationId, v -> v));
			
			Set<Long> skuIds = list.stream().map(AllocateTEntity::getSkuId).collect(Collectors.toSet());
			List<SkuTEntity> skuList = skuService.findByIds(SkuTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).build(), skuIds);
			Map<Long, SkuTEntity> skuMap = skuList.stream().collect(Collectors.toMap(SkuTEntity::getSkuId, v -> v));
			
			Set<Long> lotIds = list.stream().filter(v->v.getLotId() != null).map(AllocateTEntity::getLotId).collect(Collectors.toSet());
			List<LotAttributeTEntity> lotList = lotService.findByIds(LotAttributeTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).build(), lotIds);
			Map<Long, LotAttributeTEntity> lotMap = lotList.stream().collect(Collectors.toMap(LotAttributeTEntity::getLotAttributeId, v -> v));
			
			Set<Long> packIds = skuList.stream().map(SkuTEntity::getPackId).collect(Collectors.toSet());
			List<PackTEntity> packList = packService.findByIds(PackTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).build(), packIds);
			Map<Long, PackTEntity> packMap = packList.stream().collect(Collectors.toMap(PackTEntity::getPackId, v -> v));
			
			Set<Long> lotvIds = skuList.stream().map(SkuTEntity::getLotValidateId).collect(Collectors.toSet());
			List<LotValidateTEntity> lotvList = lotvService.findByIds(LotValidateTEntity.builder().warehouseId(request.getWarehouseId()).companyId(request.getCompanyId()).build(), lotvIds);
			Map<Long, LotValidateTEntity> lotvMap = lotvList.stream().collect(Collectors.toMap(LotValidateTEntity::getLotValidateId, v -> v));
			
			List<AllocateVO> pickList = Lists.newArrayList();
			list.forEach(al -> {
				AllocateVO vo = new AllocateVO(al);
				pickList.add(vo);
				
				//设置库位
				LocationTEntity loc = locMap.get(vo.getLocationId());
				if (loc != null)
					vo.setLoc(loc);
				
				LotAttributeTEntity lot = lotMap.get(vo.getLotId());
				if (lot != null)
					BeanUtils.copyBeanProp(vo, lot);
				
				//设置货品
				SkuTEntity sku = skuMap.get(vo.getSkuId());
				if (sku == null) 
					return;
			
				vo.setSku(sku);
				
				//设置包装
				PackTEntity pack = packMap.get(sku.getPackId());
				if (pack != null)
					vo.setPack(pack);
				
				//设置批属性显示值
				LotLabelVO lotLabel = skuService.getSkuLotLabel(sku);
				vo.setLotLabel(lotLabel);
				
				//设置批属性验证值
				LotValidateTEntity lotv = lotvMap.get(sku.getLotValidateId());
				if (lotv != null)
					vo.setLotv(lotv);
			});
			
			//排序，按库位路线排序
			pickList.sort(new Comparator<AllocateVO>() {
				@Override
				public int compare(AllocateVO o1, AllocateVO o2) {
					String loc1 = o1.getLoc().getLocationLogical();
					if (StringUtils.isEmpty(loc1))
						return -1;
					
					String loc2 = o1.getLoc().getLocationLogical();
					if (StringUtils.isEmpty(loc2))
						return 1; 
					
					return loc1.compareTo(loc2);
				}
			});
			return success(pickList);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/validate")
    public AjaxResult<AllocateVO> validate(@RequestBody String req) {
		try { 
	    	AjaxRequest<AllocateVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<AllocateVO>>() {});
	        AllocateVO allocate = request.getData();
	        
	        //获取分配明细
	        AllocateTEntity selectAll = allocateService.find(allocate);
	        if (!(AllocateStatusEnum.Allocated.getCode().equals(selectAll.getStatus())
	        		|| AllocateStatusEnum.Release.getCode().equals(selectAll.getStatus()))) 
	        	throw new BusinessServiceException("PickRest", "allocate.line.status.not.process" , new Object[] {selectAll.getAllocateId()});
	        
	        //验证货品
	        if(!selectAll.getSkuCode().equals(allocate.getSkuCode()))
	        	throw new BusinessServiceException("PickRest", "pick.sku.different" , new Object[] {selectAll.getSkuCode(), allocate.getSkuCode()});
	        
        	//验证库位
	        if(!selectAll.getLocationCode().equals(allocate.getLocationCode()))
	        	throw new BusinessServiceException("PickRest", "pick.loc.different" , new Object[] {selectAll.getLocationCode(), allocate.getLocationCode()});
	        
	        //验证数量，拣选数量不能大于预期数量
	        if(selectAll.getQuantityAllocated().compareTo(allocate.getQuantityPick()) < 0)
	        	throw new BusinessServiceException("PickRest", "pick.quantity.exceed" , new Object[] {selectAll.getQuantityAllocated(), allocate.getQuantityPick()});
	        
	        //硬分配
	        if (AllocateStrategyTypeEnum.Hard.getCode().equals(selectAll.getAllocateStrategyType())) {
	        	//验证LPN 不为空时
	        	if (StringUtils.isNotBlank(selectAll.getLpnNumber())
	        			&& !selectAll.getLpnNumber().equals(allocate.getLpnNumber()))
	        		throw new BusinessServiceException("PickRest", "pick.lpn.different" , new Object[] {selectAll.getLpnNumber(), allocate.getLpnNumber()});
	        	
	        	if (StringUtils.isBlank(selectAll.getLpnNumber())
	        			&& !StringUtils.isBlank(allocate.getLpnNumber()))
	        		throw new BusinessServiceException("PickRest", "pick.lpn.different" , new Object[] {selectAll.getLpnNumber(), allocate.getLpnNumber()});
	        	
	        	
	        	
	        }else { //软分配，确认批次信息
	        	InventoryOnhandVO onhandSelect = new InventoryOnhandVO();
	        	BeanUtils.copyBeanProp(onhandSelect, allocate);
	        	List<InventoryOnhandVO> inventoryList = allocateQueryDao.availabelInventory(onhandSelect);
	        	if (CollectionUtils.isEmpty(inventoryList))
	        		throw new BusinessServiceException("PickRest", "pick.no.inventory" , new Object[] {selectAll.getLpnNumber(), allocate.getLpnNumber()});
	        	
	        	//输入的LPN为空，再次进行筛选
	        	if (StringUtils.isEmpty(allocate.getLpnNumber()))
	        		inventoryList.stream().filter(v -> StringUtils.isEmpty(v.getLpnNumber())).collect(Collectors.toList());
	        	
	        	if (inventoryList.size() > 1)
	        		throw new BusinessServiceException("PickRest", "pick.inventory.not.only.one" , new Object[] {selectAll.getLpnNumber(), allocate.getLpnNumber()});
	        	
	        	InventoryOnhandVO onhand = inventoryList.get(0);
	        	if (onhand.getQuantityAvailable().compareTo(allocate.getQuantityPick()) < 0)
	        		throw new BusinessServiceException("PickRest", "pick.line.noavailable.inventory" , new Object[] {selectAll.getSkuCode()});
	        	
	        	//设置具体的拣货信息
	        	allocate.setInventoryOnhandId(onhand.getInventoryOnhandId());
	        	allocate.setLotNumber(onhand.getLotNumber());
	        	allocate.setLotId(onhand.getLotId());
	        	allocate.setLpnId(onhand.getLpnId());
	        	allocate.setLpnNumber(onhand.getLpnNumber());
	        }
	        return success(allocate);
		} catch (Exception e) {
            return fail(e.getMessage());
        }
	}
	
    
    @RequestMapping(value = "/confirm")
    public AjaxResult pick(@RequestBody String req) {
    	try { 
	    	AjaxRequest<List<AllocateVO>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<AllocateVO>>>() {});
	        List<AllocateVO> allocateList = request.getData();
	        if (CollectionUtils.isEmpty(allocateList)) {
	            return fail("no record update.");
	        }
	        List<AllocateVO> pickList = Lists.newArrayList();
	        //填充数据
	        allocateList.forEach(al -> {
	        	AllocateTEntity selectAll = allocateService.find(AllocateTEntity.builder()
	        																		.warehouseId(request.getWarehouseId())
	        																		.companyId(request.getCompanyId())
	        																		.allocateId(al.getAllocateId())
	        																		.status(AllocateStatusEnum.Allocated.getCode())
	        																		.build());	
	        	if (!(AllocateStatusEnum.Allocated.getCode().equals(selectAll.getStatus())
	        			|| AllocateStatusEnum.Release.getCode().equals(selectAll.getStatus())))
	        		return;
	        	
	        	AllocateVO vo = new AllocateVO(selectAll);
	        	vo.setLpnNumber(al.getLpnNumber());
	        	vo.setLotNumber(al.getLotNumber());
	        	vo.setProcessTaskFlag(al.getProcessTaskFlag());
	        	vo.setShortFlag(al.getShortFlag());
	        	vo.setAllocateShort(al.getAllocateShort());
	        	vo.setQuantityPick(al.getQuantityPick());
	        	vo.setLoadLpnNumber(al.getLoadLpnNumber());
	        	vo.setTransactionCategory(al.getTransactionCategory());
	        	pickList.add(vo);
	        	
	        });
	        if (CollectionUtils.isEmpty(pickList))
	        	return fail("no.picklist");
            boolean flag = allocateService.pick(ajaxRequest(pickList, request));
            if (!flag)
            	return fail();
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return success();
    }
    
}
