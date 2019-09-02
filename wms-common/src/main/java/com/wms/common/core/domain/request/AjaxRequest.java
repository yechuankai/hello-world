package com.wms.common.core.domain.request;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.wms.common.config.Global;
import com.wms.common.utils.AddressUtils;
import com.wms.common.utils.DateUtils;
import com.wms.common.utils.IpUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;

public class AjaxRequest<T> extends HashMap {

	public static final String PRO_WAREHOUSEID = "warehouseId";
	public static final String PRO_COMPANYID = "companyId";
	public static final String PRO_USERNAME = "userName";
	public static final String PRO_USERID = "userId";
	public static final String PRO_LOCALE = "locale";
	public static final String PRO_UUID = "uuid";
	public static final String PRO_TIME = "time";
	public static final String PRO_DATA = "data";
	private static final String PRO_DATE_BEGIN = "Begin";
	private static final String PRO_DATE_END = "End";
	
	
	private Long warehouseId;
	private Long companyId;
	private Long userId;
	private String userName;
	private String locale;
	private String time;
	private String uuid;
	private T data;
	
	
	public T getData() {
		if (data != null)
			return data;
		return (T) this.get(PRO_DATA);
	}
	
	public String getLocation() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String ip = IpUtils.getIpAddr(request);
		return AddressUtils.getRealAddressByIP(ip);
	}
	
	public String getIpAddr() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return IpUtils.getIpAddr(request);
	}
	
	public String getString(String key) {
		Object value = this.get(key);
		if (value == null) 
			return null;
		
		return value.toString();
	}
	
	public Long getLong(String key) {
		long value = 0;
		try {
			Object val = get(key);
			value = Long.parseLong(val.toString());
		} catch (Exception e) {
		} 
		return value;
	}
	
	public Integer getInt(String key) {
		int value = 0;
		try {
			Object val = get(key);
			value = Integer.parseInt(val.toString());
		} catch (Exception e) {
		} 
		return value;
	}

	public String getTime() {
		if (time != null)
			return time;
		return getString(PRO_TIME);
	}
	
	public String getUuid() {
		if (uuid != null)
			return uuid;
		return getString(PRO_UUID);
	}
	
	public AjaxRequest() {
		
	}
	
	public AjaxRequest(Map<String, String> map) {
		this.putAll(map);
	}
	
	public AjaxRequest(T data) {
		this.put(PRO_DATA, data);
	}
	
	public AjaxRequest(T data, Map<String, String> map) {
		this.putAll(map);
		this.put(PRO_DATA, data);
	}
	
	public AjaxRequest(T data, AjaxRequest<Object> request) {
		this.putAll(request);
		this.put(PRO_DATA, data);
	}
	
	public Long getWarehouseId() {
		if (warehouseId != null)
			return warehouseId;
		return getLong(PRO_WAREHOUSEID);
	}
	
	public Long getCompanyId() {
		if (companyId != null)
			return companyId;
		return getLong(PRO_COMPANYID);
	}
	
	public String getLocale() {
		if (locale != null)
			return locale;
		return getString(PRO_LOCALE);
	}
	
	public String getUserName() {
		if (userName != null)
			return userName;
		return getString(PRO_USERNAME);
	}
	
	public Long getUserId() {
		if (userId != null)
			return userId;
		return getLong(PRO_USERID);
	}
	
	public Date getDate(String key) {
		Date value = null;
		try {
			Object val = get(key);
			if (val == null) return null;
			
			String valueStr = val.toString();
			if (valueStr.trim().length() > 10) {
				value = DateUtils.dateTime(Global.dateFormater, valueStr);
			}else {
				String dateFormater = StringUtils.split(Global.dateFormater, " ")[0];
				value = DateUtils.dateTime(dateFormater, valueStr);
			}
		} catch (Exception e) {
			
		} 
		return value;
	}
	
	public Date getDateBegin(String key) {
		return getDate(StringUtils.join(key, PRO_DATE_BEGIN));
	}
	
	public Date getDateEnd(String key) {
		return getDate(StringUtils.join(key, PRO_DATE_END));
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
		this.put(PRO_WAREHOUSEID, warehouseId);
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
		this.put(PRO_COMPANYID, companyId);
	}

	public void setUserId(Long userId) {
		this.userId = userId;
		this.put(PRO_USERID, userId);
	}

	public void setUserName(String userName) {
		this.userName = userName;
		this.put(PRO_USERNAME, userName);
	}

	public void setLocale(String locale) {
		this.locale = locale;
		this.put(PRO_LOCALE, locale);
	}

	public void setTime(String time) {
		this.time = time;
		this.put(PRO_TIME, time);
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
		this.put(PRO_UUID, uuid);
	}

	public void setData(T data) {
		this.data = data;
		this.put(PRO_DATA, data);
	}
	
}
