package com.wms.common.enums;

public enum ExcelTemplateEnum {
	
	Sku("SKU", "货品"),
	Location("LOCATION","库位"), 
	Zone("ZONE", "库区"),
	Inbound("INBOUND", "入库单"),
	Outbound("OUTBOUND","出库单"),
	Allocate("ALLOCATE","分配明细"),
    Transaction("TRANSACTION","交易"),
    InventoryOnhand("ONHAND","库存"),
	InventoryByContainer("STOCKGROUPBYCONTAINER","根据容器分组库存"),
	InventoryByLpn("STOCKGROUPBYLPN","根据LPN分组库存"),
	InventoryByLocation("STOCKGROUPBYLOCATION","根据库位分组库存"),
	InventoryBySku("STOCKGROUPBYSKU","根据货品分组库存"),
	BilingLease("BILLINGLEASE","仓租导出"),
	PutawayTask("PUTAWAYTASK","上架任务"),
	UnLoadTask("UNLOADTASK","卸车任务"),
	LoadTask("LOADTASK","装车任务");
	
	private final String code;
	private final String desc;

	private ExcelTemplateEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
