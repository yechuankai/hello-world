package com.wms.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wms.common.config.Global;
import com.wms.common.utils.http.HttpUtils;

/**
 * 获取地址类
 */
public class AddressUtils {
	private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

	public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

	public static String getRealAddressByIP(String ip) {
		String address = "XX XX";

		// 内网不查询
		if (IpUtils.internalIp(ip)) {
			return "内网IP";
		}
		if (Global.isAddressEnabled) {
			String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
			if (StringUtils.isEmpty(rspStr)) {
				log.error("获取地理位置异常 {}", ip);
				return address;
			}
			JSONObject obj = null;
			try {
				obj = JSON.parseObject(rspStr, JSONObject.class);
				JSONObject data = obj.getJSONObject("data");
				Object region = data.get("region");
				Object city = data.get("city");
				address = region + " " + city;
			} catch (Exception e) {
				log.error("获取地理位置异常 {}", ip);
			}
		}
		return address;
	}
}
