package com.wms.shiro.utils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import com.wms.common.utils.MessageUtils;
import com.wms.dao.example.OutboundHeaderTExample.Criteria;
import com.wms.entity.auto.OutboundDetailTEntity;
import com.wms.entity.auto.WaveBuildDetailTEntity;
import com.wms.services.strategy.allocate.util.AllocateLog;

public class WaveBuildUtils {
	private static Logger log = LoggerFactory.getLogger(WaveBuildUtils.class);
	private static final String SUFFIX = "and";
	private static final String SEPERATOR = ",";
	private static final String DATE_FORMAT = "YYYY-MM-dd";
	
	
	public static boolean validate(List<WaveBuildDetailTEntity> deatils,List<OutboundDetailTEntity> outboundDetailTEntities) {
		return validateDetail(deatils,outboundDetailTEntities) && validateDetailSum(deatils,outboundDetailTEntities);
	}
	
	/**
	 * 只能校验bigDecimal类型的和
	 * @param deatils
	 * @param outboundDetailTEntities
	 * @return
	 */
	private static boolean validateDetailSum(List<WaveBuildDetailTEntity> deatils,
			List<OutboundDetailTEntity> outboundDetailTEntities) {
		List<WaveBuildDetailTEntity> list = deatils.stream().filter(e -> ConditionType.DETAIL_SUM.getCode().equals(e.getPropertyType()))
				.collect(Collectors.toList());
		if (list.isEmpty()) {
			return true;
		}
		
		FastClass clazz = FastClass.create(OutboundDetailTEntity.class);
		
		for (WaveBuildDetailTEntity waveBuildDetailTEntity : list) {
			FastMethod method = clazz.getMethod(waveBuildDetailTEntity.getPropertyCode(), null);
			BigDecimal reduce = outboundDetailTEntities.stream().map(e -> {
				BigDecimal value = BigDecimal.ZERO;
				try {
					value = (BigDecimal)method.invoke(e, null);
				} catch (InvocationTargetException e1) {
					String message = MessageUtils.message("{0}", "");
					AllocateLog.log(log, message, waveBuildDetailTEntity.getWaveBuildDetailId(), waveBuildDetailTEntity);
					e1.printStackTrace();
				}
				return value;
			}).reduce(BigDecimal.ZERO,BigDecimal :: add);
			int i = reduce.compareTo(new BigDecimal(waveBuildDetailTEntity.getPropertyValue1()));
			List<Integer> collect = Arrays.asList(waveBuildDetailTEntity.getPropertyExp().split(SEPERATOR)).stream().map(Integer::parseInt).collect(Collectors.toList());
			if (!collect.contains(i)) {
				return false;
			}
		}
		return true;
	}

	private static boolean validateDetail(List<WaveBuildDetailTEntity> deatils,List<OutboundDetailTEntity> outboundDetailTEntities) {
		List<WaveBuildDetailTEntity> list = deatils.stream().filter(e -> ConditionType.DETAIL.getCode().equals(e.getTableName()))
				.collect(Collectors.toList());
		if (list.isEmpty()) {
			return true;
		}
		
		FastClass clazz = FastClass.create(OutboundDetailTEntity.class);
		for (WaveBuildDetailTEntity waveBuildDetailTEntity : list) {
			FastMethod method = clazz.getMethod(waveBuildDetailTEntity.getPropertyCode(), null);
			for (OutboundDetailTEntity outboundDetailTEntity : outboundDetailTEntities) {
				try {
					Object invoke = method.invoke(outboundDetailTEntity, null);
					Object object = getValue(waveBuildDetailTEntity);
					boolean contains = validateContains(object,invoke);
					if (contains) {
						return true;
					}
				} catch (InvocationTargetException | ParseException e1) {
					String message = MessageUtils.message("{0}", "");
					AllocateLog.log(log, message, waveBuildDetailTEntity.getWaveBuildDetailId(), waveBuildDetailTEntity);
					e1.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 判断一个对象有没有包含另一个对象,只实现string,list类型
	 * @param object
	 * @param sunObject
	 * @return
	 */
	private static boolean validateContains(Object object, Object sunObject) {
		if (object instanceof String) {
			return ((String) object).contains(sunObject.toString());
		}
		
		if (object instanceof List) {
			return ((List) object).contains(sunObject);
		}
		
		return false;
		
	}

	/**
	 * 添加出库单头条件
	 * @param criteria
	 * @param deatils
	 */
	public static void addHeaderCondition(Criteria criteria,List<WaveBuildDetailTEntity> deatils) {
		FastClass fastClass = FastClass.create(criteria.getClass());
		deatils.stream().filter(e -> ConditionType.HEADER.getCode().equals(e.getTableName()))
						.forEach(detail ->{
			FastMethod method;
			try {
				method = fastClass.getMethod(getMethodNameByDeatil(detail), new Class[] {DataType.findByCode(detail.getPropertyType()).getCodeClass()});
				if (method != null) {
					method.invoke(criteria, new Object[] {getValue(detail)});
				}
			} catch (InvocationTargetException | ParseException e1) {
				String message = MessageUtils.message("{0}", "");
				AllocateLog.log(log, message, detail.getWaveBuildDetailId(), detail);
				e1.printStackTrace();
			}
		});
	}
	
	enum DataType{
		LIST("list",List.class)
		,STRING("string",String.class)
		,INT("int",int.class)
		,DATE("date",Date.class)
		,DECIMAL("decimal",BigDecimal.class);
		
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
		
		public static DataType findByCode(String code) {
			DataType[] values = DataType.values();
			for (DataType dataType : values) {
				if (dataType.getCode().equals(code)) {
					return dataType;
				}
			}
			return null;
		}
	}
	
	enum ConditionType{
		HEADER("header"),DETAIL("detail"),DETAIL_SUM("sum");
		private String code;
				
		ConditionType(String type) {
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
	private static Object getValue(WaveBuildDetailTEntity detail) throws ParseException {
		String type = detail.getPropertyType();
		DataType dataType = DataType.findByCode(type);
		String propertyValue1 = detail.getPropertyValue1();
		switch (dataType) {
		case STRING:
			return propertyValue1;
		case LIST:
			return Arrays.asList(propertyValue1.split(SEPERATOR));
		case INT:
			return Integer.parseInt(propertyValue1);
		case DATE:
			return new SimpleDateFormat(DATE_FORMAT).parse(propertyValue1);
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
