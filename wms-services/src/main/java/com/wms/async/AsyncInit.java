package com.wms.async;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.wms.common.constants.ConfigConstants;
import com.wms.common.constants.DefaultConstants;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.enums.ConfigTypeEnum;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.enums.allocate.AllocateStrategyTypeEnum;
import com.wms.common.enums.allocate.AllocateTypeEnum;
import com.wms.common.enums.allocate.AllocateUOMEnum;
import com.wms.common.utils.key.KeyUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.dao.auto.IAllocateStrategyDetailTDao;
import com.wms.dao.auto.IAllocateStrategyTDao;
import com.wms.dao.auto.ILotValidateTDao;
import com.wms.dao.auto.IPackTDao;
import com.wms.dao.auto.IPutawayStrategyDetailTDao;
import com.wms.dao.auto.IPutawayStrategyTDao;
import com.wms.dao.auto.ISysOrderNumberTDao;
import com.wms.dao.auto.IZoneTDao;
import com.wms.entity.auto.AllocateStrategyDetailTEntity;
import com.wms.entity.auto.AllocateStrategyTEntity;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.LotValidateTEntity;
import com.wms.entity.auto.PackTEntity;
import com.wms.entity.auto.PutawayStrategyDetailTEntity;
import com.wms.entity.auto.PutawayStrategyTEntity;
import com.wms.entity.auto.SysConfigTEntity;
import com.wms.entity.auto.SysOrderNumberTEntity;
import com.wms.entity.auto.ZoneTEntity;
import com.wms.services.base.ILocationService;
import com.wms.services.sys.ISysConfigService;

/**
 * 异步工厂（产生任务用）
 *
 */
public class AsyncInit {
	private static final Logger log = LoggerFactory.getLogger(AsyncInit.class);

	private static final String STD = "STD";
	
	
	/**
	 * 初始化库位
	 */
	public static TimerTask location(Long companyId, Long warehouseId) {
		return new TimerTask() {
			@Override
			public void run() {
				
				ZoneTEntity recZone = ZoneTEntity.builder()
						.warehouseId(warehouseId).
						companyId(companyId)
						.zoneCode("REC")
						.type("0")
						.pickToLocation("PICKTO")
						.zoneId(KeyUtils.getUID())
						.updateBy(DefaultConstants.SYSTEM_USER)
						.createBy(DefaultConstants.SYSTEM_USER)
						.updateTime(new Date())
						.createTime(new Date())
						.build();
				
				
				ZoneTEntity shipZone = ZoneTEntity.builder()
						.warehouseId(warehouseId).
						companyId(companyId)
						.zoneCode("SHIP")
						.type("0")
						.pickToLocation("PICKTO")
						.zoneId(KeyUtils.getUID())
						.updateBy(DefaultConstants.SYSTEM_USER)
						.createBy(DefaultConstants.SYSTEM_USER)
						.updateTime(new Date())
						.createTime(new Date())
						.build();
				
				IZoneTDao zoneDao = SpringUtils.getBean(IZoneTDao.class);
				zoneDao.insertSelective(recZone);
				zoneDao.insertSelective(shipZone);
				
				LocationTEntity stageLoc = LocationTEntity.builder()
						.warehouseId(warehouseId)
						.companyId(companyId)
						.locationCode("STAGE")
						.locationLogical("ZZZZZZZZZZ")
						.locationType("0")
						.zoneCode("REC")
						.updateBy(DefaultConstants.SYSTEM_USER)
						.createBy(DefaultConstants.SYSTEM_USER)
						.updateTime(new Date())
						.createTime(new Date())
						.build();
				
				LocationTEntity unknownLoc = LocationTEntity.builder()
						.warehouseId(warehouseId)
						.companyId(companyId)
						.locationCode("UNKNOWN")
						.locationLogical("ZZZZZZZZZZ")
						.locationType("0")
						.zoneCode("REC")
						.updateBy(DefaultConstants.SYSTEM_USER)
						.createBy(DefaultConstants.SYSTEM_USER)
						.updateTime(new Date())
						.createTime(new Date())
						.build();
				
				
				LocationTEntity picktoLoc = LocationTEntity.builder()
						.warehouseId(warehouseId)
						.companyId(companyId)
						.locationCode("PICKTO")
						.locationLogical("ZZZZZZZZZZ")
						.locationType("0")
						.zoneCode("SHIP")
						.updateBy(DefaultConstants.SYSTEM_USER)
						.createBy(DefaultConstants.SYSTEM_USER)
						.updateTime(new Date())
						.createTime(new Date())
						.build();
				
				List<LocationTEntity> list = Lists.newArrayList(stageLoc, unknownLoc, picktoLoc);
				
				AjaxRequest<List<LocationTEntity>> request = new AjaxRequest<List<LocationTEntity>>(list);
				request.setWarehouseId(warehouseId);
				request.setCompanyId(companyId);
				ILocationService locationService = SpringUtils.getBean(ILocationService.class);
				locationService.add(request);
				
			}
		};
	}
	
	
	/**
	 * 初始化单据号
	 */
	public static TimerTask orderNumber(Long companyId, Long warehouseId) {
		return new TimerTask() {
			@Override
			public void run() {
				//按枚举值初始化
				OrderNumberTypeEnum [] values = OrderNumberTypeEnum.values();
				for (OrderNumberTypeEnum on : values) {
					SysOrderNumberTEntity ont = SysOrderNumberTEntity.builder()
							.warehouseId(warehouseId).
							companyId(companyId)
							.code(on.getCode())
							.descr(on.getDesc())
							.orderNumberId(KeyUtils.getUID())
							.dateFormat(DefaultConstants.ORDER_NUMBER_DATE_FORMAT)
							.updateBy(DefaultConstants.SYSTEM_USER)
							.createBy(DefaultConstants.SYSTEM_USER)
							.updateTime(new Date())
							.createTime(new Date())
							.build();
					ISysOrderNumberTDao onDao = SpringUtils.getBean(ISysOrderNumberTDao.class);
					onDao.insertSelective(ont);
				}
				
			}
		};
	}
	
	/**
	 * 初始化批属性验证
	 */
	public static TimerTask lotValidate(Long companyId, Long warehouseId) {
		return new TimerTask() {
			@Override
			public void run() {
				LotValidateTEntity lotv = LotValidateTEntity.builder()
						.warehouseId(warehouseId).
						companyId(companyId)
						.lotValidateCode(STD)
						.lotValidateDescr(STD)
						.lotValidateId(KeyUtils.getUID())
						.updateBy(DefaultConstants.SYSTEM_USER)
						.createBy(DefaultConstants.SYSTEM_USER)
						.updateTime(new Date())
						.createTime(new Date())
						.build();
				
				ILotValidateTDao lotVdao = SpringUtils.getBean(ILotValidateTDao.class);
				lotVdao.insertSelective(lotv);
			}
		};
	}
	
	/**
	 * 初始化包装
	 */
	public static TimerTask pack(Long companyId, Long warehouseId) {
		return new TimerTask() {
			@Override
			public void run() {
				PackTEntity pack = PackTEntity.builder()
						.warehouseId(warehouseId).
						companyId(companyId)
						.packCode(STD)
						.packDescr(STD)
						.uom("EA")
						.qty(BigDecimal.ONE)
						.packId(KeyUtils.getUID())
						.updateBy(DefaultConstants.SYSTEM_USER)
						.createBy(DefaultConstants.SYSTEM_USER)
						.updateTime(new Date())
						.createTime(new Date())
						.build();
				
				IPackTDao packDao = SpringUtils.getBean(IPackTDao.class);
				packDao.insertSelective(pack);
			}
		};
	}
	
	/**
	 * 初始化上架策略
	 */
	public static TimerTask putawayStrategy(Long companyId, Long warehouseId) {
		return new TimerTask() {
			@Override
			public void run() {
				
				Long headerId = KeyUtils.getUID();
				PutawayStrategyTEntity header = PutawayStrategyTEntity.builder()
						.warehouseId(warehouseId).
						companyId(companyId)
						.putawayStrategyCode(STD)
						.putawayStrategyDescr(STD)
						.putawayStrategyId(headerId)
						.updateBy(DefaultConstants.SYSTEM_USER)
						.createBy(DefaultConstants.SYSTEM_USER)
						.updateTime(new Date())
						.createTime(new Date())
						.build();
				
				PutawayStrategyDetailTEntity detail = PutawayStrategyDetailTEntity.builder()
						.warehouseId(warehouseId).
						companyId(companyId)
						.putawayStrategyDetailId(KeyUtils.getUID())
						.putawayStrategyId(headerId)
						.lineNumber(10L)
						.type("50")
						.toLocationCode("UNKNOWN")
						.updateBy(DefaultConstants.SYSTEM_USER)
						.createBy(DefaultConstants.SYSTEM_USER)
						.updateTime(new Date())
						.createTime(new Date())
						.build();
				
				IPutawayStrategyTDao headerDao = SpringUtils.getBean(IPutawayStrategyTDao.class);
				headerDao.insertSelective(header);
				
				IPutawayStrategyDetailTDao detailDao = SpringUtils.getBean(IPutawayStrategyDetailTDao.class);
				detailDao.insertSelective(detail);
			}
		};
	}
	
	/**
	 * 初始化分配策略
	 * 
	 */
	public static TimerTask allocateStrategy(Long companyId, Long warehouseId) {
		return new TimerTask() {
			@Override
			public void run() {
				
				Long headerId = KeyUtils.getUID();
				AllocateStrategyTEntity header = AllocateStrategyTEntity.builder()
						.warehouseId(warehouseId).
						companyId(companyId)
						.allocateStrategyCode(STD)
						.allocateStrategyDescr(STD)
						.allocateStrategyId(headerId)
						.allocateStrategyType(AllocateStrategyTypeEnum.Hard.getCode())
						.updateBy(DefaultConstants.SYSTEM_USER)
						.createBy(DefaultConstants.SYSTEM_USER)
						.updateTime(new Date())
						.createTime(new Date())
						.build();
				
				AllocateStrategyDetailTEntity detail = AllocateStrategyDetailTEntity.builder()
						.warehouseId(warehouseId).
						companyId(companyId)
						.allocateStrategyDetailId(KeyUtils.getUID())
						.allocateStrategyId(headerId)
						.lineNumber(10L)
						.type(AllocateTypeEnum.Noraml.getCode())
						.uom(AllocateUOMEnum.Pcs.getCode())
						.updateBy(DefaultConstants.SYSTEM_USER)
						.createBy(DefaultConstants.SYSTEM_USER)
						.updateTime(new Date())
						.createTime(new Date())
						.build();
				
				IAllocateStrategyTDao headerDao = SpringUtils.getBean(IAllocateStrategyTDao.class);
				headerDao.insertSelective(header);
				
				IAllocateStrategyDetailTDao detailDao = SpringUtils.getBean(IAllocateStrategyDetailTDao.class);
				detailDao.insertSelective(detail);
			}
		};
	}
	
	/**
	 * 初始配置
	 */
	public static TimerTask config(Long companyId, Long warehouseId) {
		return new TimerTask() {
			@Override
			public void run() {
				
				SysConfigTEntity config_allocate = SysConfigTEntity.builder()
						.warehouseId(warehouseId).
						companyId(companyId)
						.configCode(ConfigConstants.CONFIG_ALLOCATE_MONITOR)
						.configType(ConfigTypeEnum.OnOff.getCode())
						.configDescr("Switch On-Off")
						.build();
				
				
				SysConfigTEntity receive_exceed = SysConfigTEntity.builder()
						.warehouseId(warehouseId).
						companyId(companyId)
						.configCode(ConfigConstants.CONFIG_INBOUND_RECEIVE_EXCEED)
						.configType(ConfigTypeEnum.OnOff.getCode())
						.configDescr("Inbound Receive Can exceed the receipt")
						.build();
				
				SysConfigTEntity recieve_nosku = SysConfigTEntity.builder()
						.warehouseId(warehouseId).
						companyId(companyId)
						.configCode(ConfigConstants.CONFIG_RF_INBOUND_RECEIVE_NO_SKU)
						.configType(ConfigTypeEnum.OnOff.getCode())
						.configDescr("SKU not exists In zhe Inbound Order can be rf receive")
						.build();
				
				ISysConfigService configService = SpringUtils.getBean(ISysConfigService.class);
				AjaxRequest<List<SysConfigTEntity>> request = new AjaxRequest<List<SysConfigTEntity>>(Lists.newArrayList(config_allocate, receive_exceed, recieve_nosku));
				request.setWarehouseId(warehouseId);
				request.setCompanyId(companyId);
				request.setUserName(DefaultConstants.SYSTEM_USER);
				configService.add(request);
			}
		};
	}
	
}
