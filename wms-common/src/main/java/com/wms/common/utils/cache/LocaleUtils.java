package com.wms.common.utils.cache;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.wms.common.config.Global;
import com.wms.common.utils.StringUtils;

/**
 *   语言文件
 */
public class LocaleUtils {
	public static final Logger log = LoggerFactory.getLogger(LocaleUtils.class);
	private static final ThreadLocal<String> CONTEXT_LOCALE = new ThreadLocal<>();
	private static Map<String, Map<String, String>> localMap = Maps.newHashMap();
	public final static String CONTACT = ".";
	
	public static void put(String locale, String labelKey, String lableValue) {
		if (LocaleUtils.localMap.containsKey(locale)) {
			LocaleUtils.localMap.get(locale).put(labelKey, lableValue);
		}else {
			 Map<String, String> lableMap = new HashMap<String, String>();
			 lableMap.put(labelKey, lableValue);
			 LocaleUtils.localMap.put(locale, lableMap);
		}
	}
	
	/**
	 * 设置全局语言变量
	 */
	public static void setLocale(String locale) {
		CONTEXT_LOCALE.set(locale);
	}
	
	/**
	 * 获取全局语言变量
	 */
	public static String getLocale() {
		return CONTEXT_LOCALE.get();
	}
	
	public static void clear() {
		LocaleUtils.localMap.clear();
	}
	
	public static Map<String, Map<String, String>> get() {
		return LocaleUtils.localMap;
	}
	
	public static String getLocaleLabel(String locale, String labelKey) {
		String lableValue = labelKey;
		if (LocaleUtils.localMap.containsKey(locale)) {
			lableValue = LocaleUtils.localMap.get(locale).get(labelKey);
		}
		if (StringUtils.isEmpty(lableValue)) {
			lableValue = labelKey;
		}
		return lableValue;
	}
	
	public static String getLocaleLabel(String labelKey) {
		String locale = CONTEXT_LOCALE.get();
		if (StringUtils.isEmpty(locale)) {
			locale = Global.locale;
		}
		return getLocaleLabel(locale, labelKey);
	}
	
	public static Map<String,String> getLocaleLabels(String local) {
		Map<String,String> localeMaps = LocaleUtils.localMap.get(local);
		if (localeMaps == null) {
			return new HashMap<String, String>();
		}
		return localeMaps;
	}
	
}
