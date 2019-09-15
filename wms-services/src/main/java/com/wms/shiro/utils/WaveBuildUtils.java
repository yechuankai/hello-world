package com.wms.shiro.utils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import com.google.common.collect.Maps;
import com.wms.common.utils.MessageUtils;
import com.wms.dao.example.OutboundHeaderTExample.Criteria;
import com.wms.entity.auto.WaveBuildDetailTEntity;
import com.wms.services.strategy.allocate.util.AllocateLog;

public class WaveBuildUtils {
	private static Logger log = LoggerFactory.getLogger(WaveBuildUtils.class);
	private static final String SUFFIX = "and";
	public static final String SEPERATOR = ",";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	//保存GeneratedCriteria反射方法
	private static Map<String, FastMethod> cacheCriteriaMap = Maps.newConcurrentMap();
	
	/**
	 * 添加出库单头条件
	 * @param criteria
	 * @param deatils
	 */
	public static void addTableCondition(ConditionType condtion, Criteria criteria,List<WaveBuildDetailTEntity> deatils) {
		deatils.stream().filter(e -> condtion.getCode().equals(e.getTableName()))
						.forEach(detail ->{
			String methodString = getMethodNameByDeatil(detail);
			FastMethod method = cacheCriteriaMap.get(detail.getTableName() + methodString);
			try {
				DataType type = DataType.find(detail.getPropertyType());
				if (method == null) {
					FastClass fastClass = FastClass.create(criteria.getClass());
					method = fastClass.getMethod(methodString, new Class[] { type.getCodeClass() });
					cacheCriteriaMap.put(detail.getTableName() + methodString, method);
				}
				if (method != null) {
					Object value = getValue(type, detail.getPropertyValue1());
					method.invoke(criteria, new Object[] {  value });
				}
			} catch (InvocationTargetException | ParseException e1) {
				String message = MessageUtils.message("{0}", "");
				AllocateLog.log(log, message, detail.getWaveBuildDetailId(), detail);
				e1.printStackTrace();
			}
		});
	}
	
	enum DataType{
		LIST("list", List.class), 
		STRING("string", String.class), 
		INT("int", Integer.class), 
		DATE("date", Date.class),
		DECIMAL("decimal", BigDecimal.class);
		
		private String code;
		private Class clazz;
		
		DataType(String type,Class clazz) {
			this.code = type;
			this.clazz = clazz;
		}
		
		public Class getCodeClass() {
			return clazz;
		}
		
		public String getCode() {
			return code;
		}
		
		public static DataType find(String code) {
			DataType[] values = DataType.values();
			for (DataType dataType : values) {
				if (dataType.getCode().equals(code)) {
					return dataType;
				}
			}
			return null;
		}
	}
	
	public static enum ConditionType{
		Header("HEADER"),
		Detail("DETAIL"),
		Aggregate("AGGREGATE");
		private String code;
				
		ConditionType(String type) {
			this.code = type;
		}

		public String getCode() {
			return code;
		}
	}
	
	public static enum Aggregate{
		SkuCodeIn("SkuCodeIn"),
		QuantityOrderGreaterThanOrEqualTo("QuantityOrderGreaterThanOrEqualTo"),
		QuantityOrderLessThanOrEqualTo("QuantityOrderLessThanOrEqualTo");
		private String code;
				
		Aggregate(String type) {
			this.code = type;
		}

		public String getCode() {
			return code;
		}
	}
	
	/**
	 * 获取值
	 * @param detail
	 * @return
	 * @throws ParseException 
	 */
	private static Object getValue(DataType type, Object value) throws ParseException {
		switch (type) {
		case STRING:
			return value.toString();
		case LIST:
			return Arrays.asList(value.toString().split(SEPERATOR));
		case INT:
			return Integer.parseInt(value.toString());
		case DATE:
			return new SimpleDateFormat(DATE_FORMAT).parse(value.toString());
		case DECIMAL:
			return new BigDecimal(value.toString());
		default:
			return null;
		}
	}

	/**
	 * 获取方法名称
	 * @param detail
	 * @return
	 */
	private static String getMethodNameByDeatil(WaveBuildDetailTEntity detail) {
		StringBuffer buffer = new StringBuffer(SUFFIX);
		buffer.append(detail.getPropertyCode());
		buffer.append(detail.getPropertyExp());
		return buffer.toString();
	}
}
