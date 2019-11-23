package com.wms.common.constants;

public interface ConfigConstants {

	//分配监控
	public static final String CONFIG_ALLOCATE_MONITOR = "ALLOCATE_MONITOR";
	//系统异常监控
	public static final String CONFIG_SYSTEM_MONITOR_EXCEPTION = "SYSTEM_MONITOR_EXCEPTION";
	//系统接口日志监控
	public static final String CONFIG_SYSTEM_MONITOR_REST = "SYSTEM_MONITOR_REST";
	//超收验证开关
	public static final String CONFIG_INBOUND_RECEIVE_EXCEED = "INBOUND_RECEIVE_EXCEED";
	//无SKU收货
	public static final String CONFIG_RF_INBOUND_RECEIVE_NO_SKU = "RF_INBOUND_RECEIVE_NO_SKU";
	//入库收货通知标题
	public static final String CONFIG_NOTIC_SUBJECT_INBOUND= "NOTIC_SUBJECT_INBOUND";
	//出库通知标题
	public static final String CONFIG_NOTIC_SUBJECT_OUTBOUND = "NOTIC_SUBJECT_OUTBOUND";
	//复制入库信息到批属性10、批属性11
	public static final String CONFIG_COPY_INBOUND_TO_LOT10X11 = "COPY_INBOUND_TO_LOT10X11";
	//复制包装信息到批属性8、批属性9
	public static final String CONFIG_COPY_PACK_TO_LOT8X9 = "COPY_PACK_TO_LOT8X9";
}
