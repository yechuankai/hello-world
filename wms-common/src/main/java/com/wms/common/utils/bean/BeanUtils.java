package com.wms.common.utils.bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import com.google.common.collect.Maps;
import com.wms.common.core.domain.IBaseEntity;
import com.wms.common.utils.ConverterUtils;

/**
 * Bean 工具类
 * 
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
	private static final Logger log = LoggerFactory.getLogger(BeanUtils.class);
	
	private static Map<String, BeanCopier> beanCopyMap = Maps.newConcurrentMap();
	
	/** Bean方法名中属性名开始的下标 */
	private static final int BEAN_METHOD_PROP_INDEX = 3;
	
	/** * 匹配getter方法的正则表达式 */
	private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

	/** * 匹配setter方法的正则表达式 */
	private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

	/**
	 * Bean属性复制工具方法。
	 * 
	 * @param dest
	 *            目标对象
	 * @param src
	 *            源对象
	 * @param allProp
	 *            复制所有属性           
	 *            
	 */
	public static void copyBeanProp(Object dest, Object src, Boolean allProp) {
		try {
			String name = src.getClass().getName() + dest.getClass().getName();
			BeanCopier bc = beanCopyMap.get(name);
			if (bc == null) {
				bc = BeanCopier.create(src.getClass(), dest.getClass(), false);
				beanCopyMap.put(name, bc);
			}
			
			if (allProp) {
				bc.copy(src, dest, null);   //copyProperties(src, dest);
			}else {
				bc.copy(src, dest, null);   //copyProperties(src, dest, "createTime", "createBy", "updateTime", "updateBy", "updateVersion", "descrtion");
				if (dest instanceof IBaseEntity) {
					IBaseEntity entity = (IBaseEntity) dest;
					entity.setDelFlag(null);
					entity.setCreateBy(null);
					entity.setCreateTime(null);
					entity.setUpdateBy(null);
					entity.setUpdateTime(null);
					entity.setUpdateVersion(null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Bean属性复制工具方法。
	 * 
	 * @param dest
	 *            目标对象
	 * @param src
	 *            源对象
	 */
	public static void copyBeanProp(Object dest, Object src) {
		copyBeanProp(dest, src, Boolean.TRUE);
	}
	
	/**
	 * 获取对象的setter方法。
	 * 
	 * @param obj
	 *            对象
	 * @return 对象的setter方法列表
	 */
	public static List<Method> getSetterMethods(Object obj) {
		// setter方法列表
		List<Method> setterMethods = new ArrayList<Method>();

		// 获取所有方法
		Method[] methods = obj.getClass().getMethods();

		// 查找setter方法

		for (Method method : methods) {
			Matcher m = SET_PATTERN.matcher(method.getName());
			if (m.matches() && (method.getParameterTypes().length == 1)) {
				setterMethods.add(method);
			}
		}
		// 返回setter方法列表
		return setterMethods;
	}

	/**
	 * 获取对象的getter方法。
	 * 
	 * @param obj
	 *            对象
	 * @return 对象的getter方法列表
	 */

	public static List<Method> getGetterMethods(Object obj) {
		// getter方法列表
		List<Method> getterMethods = new ArrayList<Method>();
		// 获取所有方法
		Method[] methods = obj.getClass().getMethods();
		// 查找getter方法
		for (Method method : methods) {
			Matcher m = GET_PATTERN.matcher(method.getName());
			if (m.matches() && (method.getParameterTypes().length == 0)) {
				getterMethods.add(method);
			}
		}
		// 返回getter方法列表
		return getterMethods;
	}
	

	/**
	 * 检查Bean方法名中的属性名是否相等。<br>
	 * 如getName()和setName()属性名一样，getName()和setAge()属性名不一样。
	 * 
	 * @param m1
	 *            方法名1
	 * @param m2
	 *            方法名2
	 * @return 属性名一样返回true，否则返回false
	 */

	public static boolean isMethodPropEquals(String m1, String m2) {
		return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
	}
	
	
	/**
	 * 把JavaBean转化为map
	 * @param bean
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws Exception
	 */
	public static Map<String,Object> bean2map(Object bean) {
		Map<String, Object> map = Maps.newHashMap();
		try {
		    //获取JavaBean的描述器
		    BeanInfo b = Introspector.getBeanInfo(bean.getClass(),Object.class);
		    //获取属性描述器
		    PropertyDescriptor [] pds = b.getPropertyDescriptors();
		    //对属性迭代
		    for (PropertyDescriptor pd : pds) {
		        //属性名称
		        String propertyName = pd.getName();
		        //属性值,用getter方法获取
		        Method m = pd.getReadMethod();
		        Object properValue = m.invoke(bean);//用对象执行getter方法获得属性值
		        //把属性名-属性值 存到Map中
		        map.put(propertyName, properValue);
		    }
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	    return map;
	}
	
	/**
	 * 把JavaBean转化为map
	 * @param bean
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws Exception
	 */
	public static void map2bean(Object bean, Map<String,Object> map) {
		try {
		    //获取JavaBean的描述器
		    BeanInfo b = Introspector.getBeanInfo(bean.getClass(),Object.class);
		    //获取属性描述器
		    PropertyDescriptor [] pds = b.getPropertyDescriptors();
		    //对属性迭代
		    for (PropertyDescriptor pd : pds) {
		        //属性名称
		        String propertyName = pd.getName();
		        //属性值,用getter方法获取
		        Method m = pd.getWriteMethod();
		        Object val = ConverterUtils.cast(map.get(propertyName), pd.getPropertyType());
		        m.invoke(bean, val);//用对象执行setter方法获得属性值
		    }
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	
	/**
	 * 获取到JavaBean get方法
	 * @param bean
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws Exception
	 */
	public static Map<String,Method> beanGet2map(Class clz) {
		Map<String, Method> map = Maps.newHashMap();
		try {
		    //获取JavaBean的描述器
		    BeanInfo b = Introspector.getBeanInfo(clz ,Object.class);
		    //获取属性描述器
		    PropertyDescriptor [] pds = b.getPropertyDescriptors();
		    //对属性迭代
		    for (PropertyDescriptor pd : pds) {
		        //属性名称
		        String propertyName = pd.getName();
		        //属性值,用getter方法获取
		        Method m = pd.getReadMethod();
		        map.put(propertyName, m);
		    }
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	    return map;
	}
	
	/**
	 * 获取到JavaBean set方法
	 * @param bean
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws Exception
	 */
	public static Map<String,Method> beanSet2map(Class clz) {
		Map<String, Method> map = Maps.newHashMap();
		try {
		    //获取JavaBean的描述器
		    BeanInfo b = Introspector.getBeanInfo(clz ,Object.class);
		    //获取属性描述器
		    PropertyDescriptor [] pds = b.getPropertyDescriptors();
		    //对属性迭代
		    for (PropertyDescriptor pd : pds) {
		        //属性名称
		        String propertyName = pd.getName();
		        //属性值,用getter方法获取
		        Method m = pd.getWriteMethod();
		        map.put(propertyName, m);
		    }
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	    return map;
	}
}
