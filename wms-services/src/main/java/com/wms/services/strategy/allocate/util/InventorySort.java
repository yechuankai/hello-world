package com.wms.services.strategy.allocate.util;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.allocate.AllocateInventoryVO;

public class InventorySort {
	
	private static final String STRING_ZZZZZZZZZZ = "ZZZZZZZZZZ";
	
	public static class Sort {
		String pro;
		SortType orderBy = SortType.ASC;
		Sort(String pro){
			this.pro = pro;
		}
		Sort(String pro, SortType orderBy){
			this.pro = pro;
			this.orderBy = orderBy;
		}
	}
	
	public static enum SortType{
		ASC,DESC;
	}
	/**
	 * 1默认按路线排序
	 * 2默认按数量排序
	 */
	private static final List<Sort> SORT_DEFAULT = Lists.newArrayList(
				new Sort("locationLogical"),  //路线顺序
				new Sort("quantityAvailable", SortType.DESC), //满足数量优先 
				new Sort("createTime", SortType.ASC)); //先进先出

	private static Map<String, FastMethod> getMap = Maps.newConcurrentMap();
	
	static {
		Map<String, Method> inventoryGetMap = BeanUtils.beanGet2map(InventoryOnhandVO.class);
		FastClass cglibBeanClass = FastClass.create(InventoryOnhandVO.class);
		inventoryGetMap.forEach((k,v)->{
			getMap.put(k, cglibBeanClass.getMethod(v));
		});
	}
	
	protected static Comparable sortValue(Object o, String pro, String def) {
		Object value = null;
		try {
			FastMethod m = getMap.get(pro);
			if (m != null)
				value = m.invoke(o, null);
		} catch (Exception e) {}
		
		if (value == null)
			return def;
		
		if (value instanceof Comparable)
			return (Comparable) value;
		
		return def;
	}
	
	protected static int sortValueCompare(Object o1, Object o2, Sort sort, String def) {
		Comparable m1v = sortValue(o1, sort.pro, def);
		Comparable m2v = sortValue(o2, sort.pro, def);
		//类型不一致时比较
		if (m1v.getClass() != m2v.getClass()) {
			if(!(m1v instanceof String) && m2v instanceof String  )
				return 1;
			if (m1v instanceof String && !(m2v instanceof String))
				return -1;
			return 0;
		}
			
		if (sort.orderBy == SortType.ASC)
			return m1v.compareTo(m2v);
		else
			return m2v.compareTo(m1v);
	}
	
	public static void sort(List<AllocateInventoryVO> inventory, String ... orderBy) {
		List<Sort> customSort = Lists.newArrayList();
		for (String o : orderBy) {
			if (o != null)
				customSort.add(new Sort(o));
		}
		//用于后续判断
		Set<String> customSet = Sets.newHashSet(orderBy);
		
		inventory.sort(new Comparator<AllocateInventoryVO>() {
			@Override
			public int compare(AllocateInventoryVO o1, AllocateInventoryVO o2) {
				for (Sort s : customSort) {
					int compare = sortValueCompare(o1, o2, s, STRING_ZZZZZZZZZZ);
					if (compare != 0) {
						return compare;
					}
				}
				
				for (Sort s : SORT_DEFAULT) {
					if (customSet.contains(s.pro))
						continue;
					int compare = sortValueCompare(o1, o2, s, STRING_ZZZZZZZZZZ);
					if (compare != 0) {
						return compare;
					}
				}
				return 0;
			}
		});
	}
	/*test
	public static void main(String[] args) throws ParseException {
		List<AllocateInventoryVO> list = Lists.newArrayList();
		
		AllocateInventoryVO vo1 = new AllocateInventoryVO();
		vo1.setInventoryOnhandId(10L);
		vo1.setLocationCode("A1010");
		vo1.setLocationLogical("10104");
		vo1.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-01"));
		vo1.setQuantityAvailable(new BigDecimal(500));
		list.add(vo1);
		
		AllocateInventoryVO vo2 = new AllocateInventoryVO();
		vo2.setInventoryOnhandId(20L);
		vo2.setLocationCode("A1010");
		vo2.setLocationLogical("10100");
		vo2.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-01"));
		vo2.setQuantityAvailable(new BigDecimal(500));
		list.add(vo2);
		
		AllocateInventoryVO vo3 = new AllocateInventoryVO();
		vo3.setInventoryOnhandId(30L);
		vo3.setLocationCode("A1000");
		vo3.setLocationLogical("10101");
		vo3.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-01"));
		vo3.setQuantityAvailable(new BigDecimal(600));
		list.add(vo3);
		
		AllocateInventoryVO vo4 = new AllocateInventoryVO();
		vo4.setInventoryOnhandId(40L);
		vo4.setLocationCode("A1001");
		vo4.setLocationLogical("10102");
		vo4.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-01"));
		vo4.setQuantityAvailable(new BigDecimal(600));
		list.add(vo4);
		
		for (long i = 5; i < 100000; i++) {
			AllocateInventoryVO newvo = new AllocateInventoryVO();
			newvo.setInventoryOnhandId(i*10);
			newvo.setLocationCode("A1010");
			newvo.setLocationLogical("10102");
			newvo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-01"));
			newvo.setQuantityAvailable(new BigDecimal(600));
			list.add(newvo);
		}
		
		long start = System.currentTimeMillis();
		InventorySort.sort(list, "locationCode");
		System.out.println(System.currentTimeMillis() - start);
		
		List<AllocateInventoryVO> sublist = list.subList(0, 5);
		sublist.forEach(d->{
			System.out.println(d);
		});
	}
	*/
}
