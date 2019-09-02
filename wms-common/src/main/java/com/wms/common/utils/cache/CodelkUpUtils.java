package com.wms.common.utils.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.common.constants.TableNameConstants;
import com.wms.common.core.domain.CodelkupVO;
import com.wms.common.utils.bean.BeanUtils;

/**
 *   字典文件
 */
public class CodelkUpUtils {
	public static final Logger log = LoggerFactory.getLogger(CodelkUpUtils.class);
	private static Map<String, List<CodelkupVO>> codelkupMap = Maps.newHashMap();
	public final static String CONTACT = ".";
	
	public static void put(String codelistName, CodelkupVO codelkup) {
		if (codelkupMap.containsKey(codelistName)) {
			codelkupMap.get(codelistName).add(codelkup);
		}else {
			 List<CodelkupVO> codelkupList = new ArrayList<CodelkupVO>();
			 codelkupList.add(codelkup);
			 codelkupMap.put(codelistName, codelkupList);
		}
	}
	
	public static void clear() {
		codelkupMap.clear();
	}
	
	public static List<CodelkupVO> getCodelkup(String codelistName) {
		return getCodelkup(0L, 0L, codelistName);
	}
	
	public static List<CodelkupVO> getCodelkup(Long companyId, Long warehouseId, String codelistName) {
		StringBuilder sb = new StringBuilder(codelistName);
		sb.append(CONTACT).append(companyId);
		sb.append(CONTACT).append(warehouseId);
		List<CodelkupVO> codelkups = codelkupMap.get(sb.toString());
		if (CollectionUtils.isEmpty(codelkups)) {
			codelkups = codelkupMap.get(codelistName);
		}
		if (CollectionUtils.isEmpty(codelkups)) {
			return Lists.newArrayList();
		}
		
		List<CodelkupVO> returnList = Lists.newArrayList();
		
		//转换多语言
		codelkups.forEach(v -> {
			CodelkupVO codeVo = new CodelkupVO();
			BeanUtils.copyBeanProp(codeVo, v, Boolean.FALSE);
			
			StringBuilder localeSb = new StringBuilder();
			localeSb.append(TableNameConstants.CODELKUP);
			localeSb.append(CONTACT);
			localeSb.append(codelistName);
			localeSb.append(CONTACT);
			localeSb.append(v.getCode());
			String descr = LocaleUtils.getLocaleLabel(localeSb.toString());
			if (!localeSb.toString().equals(descr))
				codeVo.setDescr(descr);
			
			returnList.add(codeVo);
		});
		
		return returnList;
	}
	
}
