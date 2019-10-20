package com.wms.common.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.common.core.domain.request.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ExampleUtils {
	private static final Logger log = LoggerFactory.getLogger(ExampleUtils.class);
	
	private final static String METHOD_VALUES = "values"; 
	private final static String METHOD_CRITERIA = "getCriteria"; 
	private final static String METHOD_JAVA_PROPERTY = "getJavaProperty"; 
	private final static String METHOD_VALUE = "getValue"; 
	private final static String METHOD_SET_ORDERBY = "setOrderByClause"; 
	private final static String PRO_SORTNAME_DEFAULT = "createTime";
	private final static String PRO_SORTORDER_DEFAULT = "DESC";
	private final static String PRO_SORT = "sort";
	
	private final static String EQUALS = " = "; 
	private final static String MORE_OR_EQUALS = " >= "; 
	private final static String LESS_OR_EQUALS = " <= "; 
	private final static String LIKE = " LIKE "; 
	private final static String EXP_1 = "%"; 
	private final static String EXP_2 = "*"; 
	private final static String SEPARATOR = ","; 
	
	private Class columnClz;
	private Object criteria;
	private Class criterion;
	private List criterions;
	private PageRequest pageRequest;
	
	//每次拿出来使用
	private Map<String, String> columnMap = Maps.newHashMap();
	//保存所有字段缓存值
	private static Map<String, Column> cacheColumnMap = Maps.newConcurrentMap();
	static class Column{
		Map<String, String> columns = Maps.newHashMap();
	}
	static class Sort{
		String name;
		String orderBy;
		Sort(String name, String orderBy){
			this.name = name;
			this.orderBy = orderBy;
		}
		@Override
		public String toString() {
			return StringUtils.join(name," ", orderBy);
		}
	}
	//保存GeneratedCriteria反射方法
	private static Map<String, FastMethod> cacheCriteriaMap = Maps.newConcurrentMap();
	//保存Example排序反射方法
	private static Map<String, FastMethod> exampleSortMap = Maps.newConcurrentMap();
	//保存criterion构造反射方法
	private static Map<String, Constructor> criterionMap = Maps.newConcurrentMap();
	
	public ExampleUtils data(PageRequest pageRequest){
		this.pageRequest = pageRequest;
		return this;
	}
	
	public ExampleUtils betweenDate(String ... proNames){
		if (proNames == null) 
			return this;
		
		for (String pro : proNames) {
			Date begin = this.pageRequest.getDateBegin(pro);
			Date end = this.pageRequest.getDateEnd(pro);
			
			if (end != null) {
				Calendar date = Calendar.getInstance();
				date.setTime(end);
				date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);
				date.set(Calendar.MILLISECOND, date.get(Calendar.MILLISECOND) - 1);
				end = date.getTime();
			}
			this.addBetweenCriterion(pro, begin, end);
		}
		return this;
	}
	
	public void and(String proName, Object value) {
		try {
			newCriterion(proName, value);
		} catch (InvocationTargetException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	public ExampleUtils criteria(Object o) {
		this.criteria = o;
		getCriterion();
		return this;
	}
	
	public ExampleUtils build(Object obj) {
		if (this.pageRequest == null) 
			return this;
		
		this.pageRequest.forEach((k, v) -> {
			addCriterion(k.toString(), v.toString());
		});
		
		return this;
	}
	
	public ExampleUtils orderby(Object obj) {
		List<Sort> sortList = Lists.newArrayList();
		if (StringUtils.isEmpty(this.pageRequest.getSort())) {
			sortList.add(new Sort(PRO_SORTNAME_DEFAULT, PRO_SORTORDER_DEFAULT));
		}else {
			String [] sortName = StringUtils.split(this.pageRequest.getSort(), SEPARATOR);
			String [] orderBy = StringUtils.split(this.pageRequest.getOrder(), SEPARATOR);
			for (int i = 0; i < sortName.length; i++) {
				sortList.add(new Sort(sortName[i], orderBy[i]));
			}
		}
		
		Iterator<Sort> iterator = sortList.iterator();
		while(iterator.hasNext()) {
			Sort s = iterator.next();
			String columnName = getColumn(s.name);
			if (StringUtils.isEmpty(columnName))
				iterator.remove();
			s.name = columnName;
		}
		
		String orderBy = StringUtils.join(sortList.toArray(), SEPARATOR);
		addOrderBy(obj, orderBy);
		return this;
	}

	//一次性加载所有字段保存到内存中
	private void refreshCacheColumn() {
		try {
			Class clz = this.columnClz;
			if (cacheColumnMap.containsKey(clz.getName()))
				return;
			
			Column column = new Column();
			cacheColumnMap.put(clz.getName(), column);
			
			FastClass fastClass = FastClass.create(clz);
			Method method = clz.getDeclaredMethod(METHOD_VALUES);//得到方法对象
			FastMethod fastMethod = fastClass.getMethod(method);
			Object ret = method.invoke(this.columnClz);
			Object [] rets = (Object[]) ret;
			for (Object object : rets) {
				FastClass columnFastClass = FastClass.create(object.getClass());
				
				Method javaProMethod = object.getClass().getDeclaredMethod(METHOD_JAVA_PROPERTY);
				FastMethod javaProFastMethod = columnFastClass.getMethod(javaProMethod);
				Object javaPro = javaProFastMethod.invoke(object, null);
				
				Method dbProMethod = object.getClass().getMethod(METHOD_VALUE);
				FastMethod dbProFastMethod = columnFastClass.getMethod(dbProMethod);
				Object dbPro = dbProFastMethod.invoke(object, null);
				
				column.columns.put(javaPro.toString(), dbPro.toString());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private String getColumn(String name) {
		if (StringUtils.isEmpty(name))
			return "";
		
		String dbPro = columnMap.get(name);
		if (!StringUtils.isEmpty(dbPro))
			return dbPro;
		
		return dbPro;
	}
	
	private void getCriterion() {
		try {
			FastMethod fastMethod = ExampleUtils.cacheCriteriaMap.get(this.criteria.getClass().getName());
			if (fastMethod != null) {
				this.criterions = (List) fastMethod.invoke(this.criteria, null);
				return;
			}
			FastClass fastClass = FastClass.create(this.criteria.getClass());
			Method method = this.criteria.getClass().getDeclaredMethod(METHOD_CRITERIA);
			fastMethod = fastClass.getMethod(method);
			this.criterions = (List)  fastMethod.invoke(this.criteria, null);
			//加入缓存中，下次无需继续反射
			ExampleUtils.cacheCriteriaMap.put(this.criteria.getClass().getName(), fastMethod);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void newCriterion(String condition, Object value) throws InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException {
		Constructor c = ExampleUtils.criterionMap.get(this.criterion.getName());
		Object o = null;
		if (c != null) {
			o = c.newInstance(new Object[] {condition.toString(), value});
		}else {
			FastClass fastClass = FastClass.create(this.criterion);
			c = this.criterion.getDeclaredConstructor(String.class, Object.class);
			c.setAccessible(true);
			o = c.newInstance(condition.toString(), value);
			ExampleUtils.criterionMap.put(this.criterion.getName(), c);
		}
		if (o != null)
			this.criterions.add(o);
	}
	
	private void addCriterion(String name, String value) {
		try {
			if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value))
				return;
			
			//左右去空格
			value = value.trim();
			
			//获的列名
			String dbPro = getColumn(name);
			if (StringUtils.isEmpty(dbPro)|| StringUtils.equals(name, PRO_SORT)) {
				return;
			}
			StringBuilder condition = new StringBuilder();
			
			String conditionValue = assembleCondition(value);
			Object objValue = value;
			if (StringUtils.isEmpty(conditionValue)) {
				condition.append(dbPro).append(EQUALS);
			}else {
				condition.append(dbPro).append(LIKE);
				objValue = conditionValue;
			}
			newCriterion(condition.toString(), objValue);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void addBetweenCriterion(String name, Date value1, Date value2) {
		try {
			if (StringUtils.isNull(value1) && StringUtils.isNull(value2))
				return;
			
			if (StringUtils.isEmpty(name)) {
				return;
			}
			
			//获的列名
			String dbPro = getColumn(name);
			if (StringUtils.isEmpty(dbPro)) {
				return;
			}
			if (StringUtils.isNotNull(value1)) {
				StringBuilder condition = new StringBuilder();
				condition.append(dbPro).append(MORE_OR_EQUALS);
				newCriterion(condition.toString(), value1);
			}
			if (StringUtils.isNotNull(value2)) {
				StringBuilder condition = new StringBuilder();
				condition.append(dbPro).append(LESS_OR_EQUALS);
				newCriterion(condition.toString(), value2);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private void addOrderBy(Object obj, String orderby) {
		try {
			if (StringUtils.isEmpty(orderby))
				return;
			
			FastMethod fastMethod = exampleSortMap.get(obj.getClass().getName());
			if (fastMethod != null) {
				fastMethod.invoke(obj, new Object[] {orderby});
				return;
			}
			
			FastClass fastClass = FastClass.create(obj.getClass());
			Method method = obj.getClass().getDeclaredMethod(METHOD_SET_ORDERBY, String.class);
			fastMethod = fastClass.getMethod(method);
			fastMethod.invoke(obj, new Object[]{orderby});
			//加入缓存中，下次无需继续反射
			ExampleUtils.exampleSortMap.put(obj.getClass().getName(), fastMethod);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	private String assembleCondition(String value){
		StringBuilder sb = new StringBuilder();
		boolean isLike = false;
		if (StringUtils.isEmpty(value)) {
			return "";
		}
		String first = value.substring(0, 1);
		if (EXP_1.equals(first)) {
			isLike = true;
			sb.append(value);
		}else if (EXP_2.equals(first)) {
			value = value.substring(1, value.length());
			sb.append(EXP_1).append(value);
			isLike = true;
		}
		if (!isLike)
			sb.append(value);
		
		String last = sb.substring(sb.length() - 1, sb.length());
		if (EXP_1.equals(last)) {
			isLike = true;
		}else if (EXP_2.equals(last)) {
			value = sb.substring(0, sb.length()-1);
			sb.setLength(0);
			sb.append(value).append(EXP_1);
			isLike = true;
		}
		if(isLike) {
			return sb.toString();
		}
		return "";
	}
	
	public ExampleUtils(Class columnClz, Class criterion) {
		this.columnClz = columnClz;
		this.criterion = criterion;
		refreshCacheColumn();
		columnMap = ExampleUtils.cacheColumnMap.get(columnClz.getName()).columns;
	}
	
	public static ExampleUtils create(Class column, Class criterion) {
		return new ExampleUtils(column, criterion);
	}
}
