package com.wms.services.billing.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.services.IExcelService;
import com.wms.common.enums.ExcelTemplateEnum;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.enums.TransactionTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.DateUtils;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IBillingLeaseTDao;
import com.wms.dao.example.BillingLeaseTExample;
import com.wms.entity.auto.BillingLeaseTEntity;
import com.wms.entity.auto.InboundDetailTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.InventoryTransactionTEntity;
import com.wms.entity.auto.LotAttributeTEntity;
import com.wms.entity.auto.OwnerTEntity;
import com.wms.entity.auto.PackTEntity;
import com.wms.entity.auto.SkuTEntity;
import com.wms.entity.auto.WarehouseActiveTEntity;
import com.wms.entity.auto.ZoneTEntity;
import com.wms.services.base.IOwnerService;
import com.wms.services.base.IPackService;
import com.wms.services.base.ISkuService;
import com.wms.services.base.impl.SkuServiceImpl;
import com.wms.services.billing.ILeaseService;
import com.wms.services.inbound.IInboundDetailService;
import com.wms.services.inventory.IInventoryService;
import com.wms.services.inventory.ILotService;
import com.wms.services.inventory.ITransactionService;
import com.wms.services.sys.IWarehouseActiveService;
import com.wms.vo.PackVO;
import com.wms.vo.excel.BillingLeaseExcelVO;
import com.wms.vo.excel.ZoneImportVO;

@Service
public class LeaseServiceImpl implements ILeaseService, IExcelService<BillingLeaseExcelVO> {
	
	private static final Logger log = LoggerFactory.getLogger(LeaseServiceImpl.class);
	
	@Autowired
	private IBillingLeaseTDao billingLeaseDao;
	@Autowired
	private IWarehouseActiveService warehouseActiveService;
	@Autowired
	private IOwnerService ownerService;
	@Autowired
	private IInventoryService inventoryService;
	@Autowired
	private ITransactionService transactionService;
	@Autowired
	private ILotService lotService;
	@Autowired
	private IInboundDetailService inboundDetailService;
	@Autowired
	private ISkuService skuService;
	@Autowired
	private IPackService packService;


	@Override
	public void createLease() {
		List<WarehouseActiveTEntity> warehouseList = warehouseActiveService.findAll();
		if (CollectionUtils.isEmpty(warehouseList))
			return;
		
		warehouseList.forEach(w -> {
			AjaxRequest request = new AjaxRequest();
			request.setCompanyId(w.getCompanyId());
			request.setWarehouseId(w.getWarehouseId());
			createLease(request);
		});
	}

	/**
	 * 按仓库生成计费
	 */
	@Override
	public void createLease(AjaxRequest<Date> request) {
		List<OwnerTEntity> owners = ownerService.findAll(OwnerTEntity.builder()
							.warehouseId(request.getWarehouseId())
							.companyId(request.getCompanyId())
							.build());
		if (CollectionUtils.isEmpty(owners)) 
			return;
		
		owners.forEach(o -> {
			try {
				createOwnerLease(new AjaxRequest<OwnerTEntity>(o, request), request.getData());
				OwnerTEntity update = OwnerTEntity.builder()
										.warehouseId(o.getWarehouseId())
										.companyId(o.getCompanyId())
										.ownerId(o.getOwnerId())
										.billingLeaseDate(new Date())
										.updateVersion(o.getUpdateVersion())
										.build();
				if (request.getData() == null) { //如果传入指定日期则不更新最后一次计算日期
					//更新计算日期
					ownerService.modify(update);
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			
		});
	}

	/**
	 * 按仓库货主生成计费
	 */
	@Override
	@Transactional
	public void createOwnerLease(AjaxRequest<OwnerTEntity> request, Date endDate) {
		OwnerTEntity owner = request.getData();
		//获取最后一次计算计费的时间
		//如果日期为空，则按1999-01-01开始计算
		if (owner.getBillingLeaseDate() == null) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, 1999);
			c.set(Calendar.MONTH, 1);
		    c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
		    c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			owner.setBillingLeaseDate(c.getTime());
		}

		//上个月最后一天
		if (endDate == null) {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, 0); 
		    c.set(Calendar.DAY_OF_MONTH, -1);
		    c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			endDate = c.getTime();
		}
		
		//按库存计算仓租
		List<BillingLeaseTEntity> inventoryBillList = getByOnhandInventory(owner);
		
		//按交易计算仓租
		List<BillingLeaseTEntity> transactionBillList = getByTransaction(owner);
		
		List<BillingLeaseTEntity> all = Lists.newArrayList();
		all.addAll(inventoryBillList);
		all.addAll(transactionBillList);
		
		if (CollectionUtils.isEmpty(all))
			return;
		
		//填充批次数据
		fillLotValue(request, all);
		
		//设置计算的月份，上个月
		String monthStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM, endDate);
		
		//检查是否已经产生过记录
		List<BillingLeaseTEntity> oldLeaseList = findByOwnerMonth(BillingLeaseTEntity.builder()
							.warehouseId(request.getWarehouseId())
							.companyId(request.getCompanyId())
							.ownerCode(owner.getOwnerCode())
							.billingMonth(monthStr)
							.build());
		if (CollectionUtils.isNotEmpty(oldLeaseList)) {
			oldLeaseList.forEach(l -> {
				l.setUpdateBy(request.getUserName());
				delete(l);
			});
		}
		
		//生成单号
		String billingCode = KeyUtils.getOrderNumber(request.getCompanyId(), request.getWarehouseId(), OrderNumberTypeEnum.Billing);
		
		Date now = new Date();
		final Date _lastDate = endDate;
		//填充日期
		all.forEach(l -> {
			//无入库日期排除
			if (l.getInboundDate() == null)
				return;
			
			l.setWarehouseId(request.getWarehouseId());
			l.setCompanyId(request.getCompanyId());
			l.setBillingLeaseCode(billingCode);
			l.setBillingMonth(monthStr);
			l.setBillingDate(_lastDate);
			l.setCreateTime(now);
			l.setUpdateTime(now);
			l.setCreateBy(request.getUserName());
			l.setUpdateBy(request.getUserName());
			
			//计算天数
			Date _endDate = _lastDate;
			if (l.getOutboundDate() != null) {
				_endDate = l.getOutboundDate();
			}
			Date startDate = owner.getBillingLeaseDate();
			if (l.getInboundDate().compareTo(startDate) > 0) { //入库日期比上一次计算日期晚，以入库日期为开始日期
				startDate = l.getInboundDate();
			}
			long days = _endDate.getTime() - startDate.getTime();
			days = days / (1000 * 24 * 60 * 60);
			//入库当天需计算为1天
			l.setDays(days + 1);
			add(l);
		});

	}
	
	/**
	 * 根据库存转换仓租列表对象
	 * @param inventory
	 * @return
	 */
	private List<BillingLeaseTEntity> getByOnhandInventory(OwnerTEntity owner){
		//按货主查询所有库存
		List<InventoryOnhandTEntity> inventoryList = inventoryService.findByOwner(InventoryOnhandTEntity.builder()
				.warehouseId(owner.getWarehouseId())
				.companyId(owner.getCompanyId())
				.ownerCode(owner.getOwnerCode())
				.build());
		//按批次分组汇总在库的库存
		Map<String, BigDecimal> onhandLotInventory = Maps.newHashMap();
		if (CollectionUtils.isNotEmpty(inventoryList)) {
			inventoryList.forEach(i -> {
				BigDecimal quantity = onhandLotInventory.get(i.getLotNumber());
				if (quantity == null) {
					quantity = i.getQuantityOnhand();
				}else {
					quantity = quantity.add(i.getQuantityOnhand());
				}
				onhandLotInventory.put(i.getLotNumber(), quantity);
			});
		};
		List<BillingLeaseTEntity> list = Lists.newArrayList();
		onhandLotInventory.forEach((k, v) -> {
			BillingLeaseTEntity lease = BillingLeaseTEntity.builder()
											.lotNumber(k)
											.quantityReceived(v)
											.build();
			list.add(lease);
		});
		return list;
	}
	
	/**
	 * 根据交易转换仓租列表对象
	 * @param inventory
	 * @return
	 */
	private List<BillingLeaseTEntity> getByTransaction(OwnerTEntity owner){
		///按货主查询当月出库交易
		List<InventoryTransactionTEntity> tranList = transactionService.findGreaterThanDate(InventoryTransactionTEntity.builder()
				.warehouseId(owner.getWarehouseId())
				.companyId(owner.getCompanyId())
				.ownerCode(owner.getOwnerCode())
				.transactionType(TransactionTypeEnum.Outbound.getCode())
				.transactionDate(owner.getBillingLeaseDate())
				.build()
				);
		
		//按批次分组汇总出库的库存
		Map<String, BillingLeaseTEntity> outboundLot = Maps.newHashMap();
		if (CollectionUtils.isNotEmpty(tranList)) {
			tranList.forEach(t -> {
				//再根据出库日期进行计算，将交易日期转换为年月日
				String outboundDateStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, t.getTransactionDate());
				BillingLeaseTEntity lease = outboundLot.get(t.getLotNumber() + outboundDateStr);
				if (lease == null) {
					lease = new BillingLeaseTEntity();
					lease.setLotNumber(t.getLotNumber());
					lease.setQuantityShiped(t.getQuantity().abs());
					lease.setOutboundDate(DateUtils.parseDate(outboundDateStr));
					outboundLot.put(outboundDateStr, lease);
				}else {
					BigDecimal quantity = lease.getQuantityShiped();
					quantity = quantity.add(t.getQuantity().abs());
					lease.setQuantityShiped(quantity);
				}
			});
		}
		List<BillingLeaseTEntity> list = outboundLot.values().stream().collect(Collectors.toList());
		return list;
	}
	
	/**
	 * 根据批次号填充入库信息
	 * @param warehouse
	 * @param list
	 * @return
	 */
	private Boolean fillLotValue(AjaxRequest<OwnerTEntity> request, List<BillingLeaseTEntity> list) {
		if (CollectionUtils.isEmpty(list))
			return Boolean.FALSE;
		
		OwnerTEntity owner = request.getData();
		
		List<String> lotNumberList = list.stream().map(BillingLeaseTEntity::getLotNumber).collect(Collectors.toList());
		//转换二维数组，oracle in 操作不可超过1000个字符
		List<List<String>> mulitLotNumberList = Lists.newArrayList();
		int size = lotNumberList.size();
		int max = 999;
		int f = (int) Math.ceil((double)size / (double)max);
		for (int i = 0; i < f; i++) {
			int toIndex = (i * max) + max;
			if (toIndex > size)
				toIndex = size;
			
			mulitLotNumberList.add(lotNumberList.subList(i * max, toIndex));
		}
		
		Map<String, InboundDetailTEntity> lotPackMap = Maps.newHashMap();
		List<LotAttributeTEntity> lotList = Lists.newArrayList();
		for (List<String> lots : mulitLotNumberList) {
			//查询到所有批次
			List<LotAttributeTEntity> selectLots = lotService.findBylotNumbers(LotAttributeTEntity.builder()
											.warehouseId(request.getWarehouseId())
											.companyId(request.getCompanyId())
											.build(), Sets.newHashSet(lots));
			if (CollectionUtils.isNotEmpty(selectLots))
				lotList.addAll(selectLots);
			
			//根据批次信息查询入库单包装， 批次信息已包含入库单、SKU，根据批次则能定位到包装、单位
			List<InboundDetailTEntity> inboundDetailList = inboundDetailService.findByLotNumbers(InboundDetailTEntity.builder()
											.warehouseId(request.getWarehouseId())
											.companyId(request.getCompanyId())
											.build(), Sets.newHashSet(lots));
			
			if(CollectionUtils.isEmpty(inboundDetailList))
				continue;
			
			//按批次号分组计算
			Map<String, List<InboundDetailTEntity>> inboundDetailLotList = inboundDetailList.stream()
					.filter(v-> v.getQuantityReceive().compareTo(BigDecimal.ZERO) > 0)
					.collect(Collectors.groupingBy(InboundDetailTEntity::getLotNumber));
			inboundDetailLotList.forEach((k, v) -> {
				InboundDetailTEntity detail = v.stream().max(new Comparator<InboundDetailTEntity>() {
					@Override
					public int compare(InboundDetailTEntity o1, InboundDetailTEntity o2) {
						//降序排列
						return o2.getInboundDate().compareTo(o1.getInboundDate());
					}
				}).get();
				//按批次获取最后收货行
				lotPackMap.put(k, detail);
			});
		}
		
		if (CollectionUtils.isEmpty(lotList))
			return Boolean.FALSE;
		
		//查询SKU/包装  获取规格信息
		//查询KSU
		Set<Long> skuIdSet = lotList.stream().map(LotAttributeTEntity::getSkuId).collect(Collectors.toSet());
		List<SkuTEntity> skuList = skuService.findByIds(SkuTEntity.builder()
											.warehouseId(request.getWarehouseId())
											.companyId(request.getCompanyId())
											.ownerCode(owner.getOwnerCode())
											.build(), skuIdSet);
		Map<Long, SkuTEntity> skuMap = skuList.stream().collect(Collectors.toMap(SkuTEntity::getSkuId, v -> v));
		
		//查询包装
		Set<Long> packIdSet = lotPackMap.values().stream().map(InboundDetailTEntity::getPackId).collect(Collectors.toSet());
		List<PackTEntity> packList = packService.findByIds(PackTEntity.builder()
				.warehouseId(request.getWarehouseId())
				.companyId(request.getCompanyId())
				.build(), packIdSet);
		Map<Long, PackTEntity> packMap = packList.stream().collect(Collectors.toMap(PackTEntity::getPackId, v -> v));
		
		//将查询出的批次数据转换为map
		Map<String, LotAttributeTEntity> lotMap = lotList.stream().collect(Collectors.toMap(LotAttributeTEntity::getLotNumber, v -> v));
		
		//开始填充数据
		list.forEach(l -> {
			//填充单据号、入库日期
			LotAttributeTEntity lot = lotMap.get(l.getLotNumber());
			if (lot != null) {
				l.setLotId(lot.getLotAttributeId());
				l.setSourceBillNumber(lot.getLotAttribute10());
				l.setInboundDate(lot.getLotAttribute11());
				l.setOwnerId(lot.getOwnerId());
				l.setOwnerCode(lot.getOwnerCode());
				l.setSkuId(lot.getSkuId());
				l.setSkuCode(lot.getSkuCode());
			}
			InboundDetailTEntity inbound = lotPackMap.get(l.getLotNumber());
			if (inbound != null) {
				l.setPackId(inbound.getPackId());
				l.setPackCode(inbound.getPackCode());
				l.setUom(inbound.getUom());
			}
			//查询规格信息
			SkuTEntity sku = skuMap.get(l.getSkuId());
			PackTEntity pack = packMap.get(l.getPackId());
			if (sku != null && pack != null) {
				PackVO packvo = packService.getPack(pack, sku, l.getUom());
				
				BigDecimal quantity = null;
				if (l.getQuantityReceived() != null) {
					quantity = l.getQuantityReceived();
				}else {
					quantity = l.getQuantityShiped();
				}
				l.setVolume(packvo.getVolume().multiply(quantity));
				l.setWeightGross(packvo.getWeightGross().multiply(quantity));
				l.setWeightNet(packvo.getWeightNet().multiply(quantity));
				l.setWeightTare(packvo.getWeightTare().multiply(quantity));
			}
			
		});
		return Boolean.TRUE;
	}

	@Override
	public List<BillingLeaseTEntity> find(PageRequest request) {
		BillingLeaseTExample TExample = new BillingLeaseTExample();
		BillingLeaseTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(BillingLeaseTEntity.Column.class, BillingLeaseTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.betweenDate(BillingLeaseTEntity.Column.billingDate.getJavaProperty())
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		return billingLeaseDao.selectByExample(TExample);
	}

	@Override
	public List<BillingLeaseTEntity> findByOwnerMonth(BillingLeaseTEntity billing) {
		BillingLeaseTExample TExample = new BillingLeaseTExample();
		BillingLeaseTExample.Criteria TExampleCriteria = TExample.createCriteria();
		TExampleCriteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(billing.getWarehouseId())
		.andCompanyIdEqualTo(billing.getCompanyId())
		.andOwnerCodeEqualTo(billing.getOwnerCode())
		.andBillingMonthEqualTo(billing.getBillingMonth());
		return billingLeaseDao.selectByExample(TExample);
	}

	@Override
	public Boolean add(BillingLeaseTEntity lease) {
		lease.setBillingLeaseId(KeyUtils.getUID());
		int rowcount = billingLeaseDao.insertSelective(lease);
		if (rowcount == 0)
			throw new BusinessServiceException("add error.");
		return Boolean.FALSE;
	}

	@Override
	public Boolean delete(BillingLeaseTEntity lease) {
		BillingLeaseTExample TExample = new BillingLeaseTExample();
		BillingLeaseTExample.Criteria TExampleCriteria = TExample.createCriteria();
		TExampleCriteria
		.andWarehouseIdEqualTo(lease.getWarehouseId())
		.andCompanyIdEqualTo(lease.getCompanyId())
		.andBillingLeaseIdEqualTo(lease.getBillingLeaseId());
		
		BillingLeaseTEntity update = BillingLeaseTEntity.builder()
										.delFlag(YesNoEnum.Yes.getCode())
										.updateBy(lease.getUpdateBy())
										.updateTime(new Date())
										.build();
		
		
		int rowcount = billingLeaseDao.updateWithVersionByExampleSelective(lease.getUpdateVersion(), update, TExample);
		if (rowcount == 0)
			throw new BusinessServiceException("add error.");
		return Boolean.FALSE;
	}
	
    @Override
    public ExcelTemplate getExcelTemplate() {
        return new ExcelTemplate<BillingLeaseExcelVO>(ExcelTemplateEnum.BilingLease.getCode(), BillingLeaseExcelVO.class);
    }
	
	@Override
    public List<BillingLeaseExcelVO> exportData(PageRequest request) throws BusinessServiceException {
        List<BillingLeaseExcelVO> returnList = Lists.newArrayList();
        List<BillingLeaseTEntity> zones = find(request);
        if (CollectionUtils.isEmpty(zones)) {
            return returnList;
        }
        zones.forEach(d ->{
        	BillingLeaseExcelVO zone = new BillingLeaseExcelVO();
            BeanUtils.copyBeanProp(zone, d);
            returnList.add(zone);
        });
        return returnList;
    }

}
