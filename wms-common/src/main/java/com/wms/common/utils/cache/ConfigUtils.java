package com.wms.common.utils.cache;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.wms.common.enums.YesNoEnum;

/**
 *   语言文件
 */
public class ConfigUtils {
	
	public static final Logger log = LoggerFactory.getLogger(ConfigUtils.class);
	private static Map<String, String> localMap = Maps.newHashMap();
	public final static String CONTACT = ".";
	
	public static void put(String key, String value) {
		localMap.put(key, value);
	}
	
	public static void clear() {
		localMap.clear();
	}
	
	public static String getValue(String key) {
		return localMap.get(key);
	}
	
	public static Boolean getBooleanValue(String key) {
		String value = getValue(key);
		if(YesNoEnum.Yes.getCode().equals(value)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public static Long getLongValue(String key) {
		String value = getValue(key);
		return Long.parseLong(value);
	}
	
	
	public static String getValue( Long companyId, Long warehouseId, String key) {
		StringBuilder sb = new StringBuilder();
		sb.append(companyId).append(CONTACT);
		sb.append(warehouseId).append(CONTACT);
		sb.append(key);
		return getValue(sb.toString());
	}
	
	public static Boolean getBooleanValue(Long companyId, Long warehouseId, String key) {
		String value = getValue(warehouseId, companyId, key);
		if(YesNoEnum.Yes.getCode().equals(value)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public static Long getLongValue(Long companyId, Long warehouseId, String key) {
		String value = getValue(warehouseId, companyId, key);
		return Long.parseLong(value);
	}
	
}
