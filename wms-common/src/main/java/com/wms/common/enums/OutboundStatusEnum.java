package com.wms.common.enums;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public enum OutboundStatusEnum {

	Draft("0","草稿"),
	WaitingReview("3","待审核"),
	New("10", "新建"),
	PartAllocated("20", "部分分配"), 
	Allocated("30", "已全部分配"), 
	Release("40", "已下发"), 
	PartPicked("50", "部分拣货"), 
	Picked("60", "已全部拣货"), 
	PartShiped("70", "已部分发运"), 
	Shiped("80", "已全部发运"),
	Cancel("90", "已取消");
	
	private final String code;
	private final String desc;

	private OutboundStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	//将枚举转换成list格式，这样前台遍历的时候比较容易，列如 下拉框 后台调用toList方法，你就可以得到code 和name了
	public static List toList() {
		List list = Lists.newArrayList();


		for (OutboundStatusEnum outboundStatusEnum : OutboundStatusEnum.values()) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("code", outboundStatusEnum.getCode());
			map.put("desc", outboundStatusEnum.getDesc());
			list.add(map);
		}
		return list;
	}
}
