package com.wms.pub.inner.system;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.SysUserDefaultTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.entity.auto.SysWarehousesTEntity;
import com.wms.entity.auto.WarehouseActiveTEntity;
import com.wms.services.sys.ISysWarehouseService;
import com.wms.services.sys.IWarehouseActiveService;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;
import com.wms.vo.WarehouseActiveVO;
import com.wms.vo.WarehouseVO;

@RestController
@RequestMapping("/services/inner/warehouse")
public class WarehouseRest extends BaseController{
	
	@Autowired
	private ISysWarehouseService warehouseService;
	@Autowired
	private IWarehouseActiveService warehouseActiveService;
	
	@RequestMapping(value = "/find")
	public PageResult<SysWarehousesTEntity> find(@RequestBody String req) {
		List<SysWarehousesTEntity> list = null;
		try {
			PageRequest request = pageRequest(req);
			//排除查询条件
			request.remove(SysWarehousesTEntity.Column.warehouseId.getJavaProperty());
			convertWordQ(request, SysWarehousesTEntity.Column.code.getJavaProperty());
			PageHelper.startPage(request.getPageStart(), request.getPageSize());
			list = warehouseService.find(request);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
		return page(list);
	}
	
	@RequestMapping(value = "/save")
	public AjaxResult save(@RequestBody String req) {
		try {
			AjaxRequest<List<SysWarehousesTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysWarehousesTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = warehouseService.modify(request);
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}
	
	@RequestMapping(value = "/delete")
	public AjaxResult delete(@RequestBody String req) {
		try {
			AjaxRequest<List<SysWarehousesTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<SysWarehousesTEntity>>>() {});
			if (CollectionUtils.isEmpty(request.getData())) {
				return fail("no record update.");
			}
			boolean flag = warehouseService.delete(request);
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}
	
	@RequestMapping(value = "/add")
	public AjaxResult add(@RequestBody String req) {
		try {
			AjaxRequest<SysWarehousesTEntity> request = ajaxRequest(req, new TypeReference<AjaxRequest<SysWarehousesTEntity>>() {});
			List<SysWarehousesTEntity> updateList = Lists.newArrayList(request.getData());
			boolean flag = warehouseService.add(ajaxRequest(updateList, request));
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}
	
	@RequestMapping(value = "/init")
	public AjaxResult init(@RequestBody String req) {
		try {
			AjaxRequest<WarehouseVO> request = ajaxRequest(req, new TypeReference<AjaxRequest<WarehouseVO>>() {});
			boolean flag = warehouseService.init(request);
			if (flag) {
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}
	
	@RequestMapping(value = "/userWarehouse")
	public PageResult<WarehouseVO> userWarehouse() {
		try {
			UserVO user = ShiroUtils.getSysUser();
			List<WarehouseVO> userWarehouse = user.getWarehouses();
			if (CollectionUtils.isEmpty(userWarehouse)) 
				return page(Lists.newArrayList());
			
			return page(userWarehouse);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
	}
	
	private static final String PRO_WAREHOUSEDESC = "warehouseDesc";
	private Boolean searchUserWarehouse(WarehouseVO wa, PageRequest request ) {
		String warehouseCode = request.getString(SysUserDefaultTEntity.Column.warehouseCode.getJavaProperty());
		if (StringUtils.isNotEmpty(warehouseCode) 
				&& wa.getWarehouseCode().indexOf(warehouseCode) == -1)
			return Boolean.FALSE;
		
		String warehouseDesc = request.getString(PRO_WAREHOUSEDESC);
		if (StringUtils.isNotEmpty(warehouseDesc) 
				&& wa.getWarehouseDescr().indexOf(warehouseDesc) == -1)
			return Boolean.FALSE;
		
		String companyDescr = request.getString(SysUserTEntity.Column.companyDescr.getJavaProperty());
		if (StringUtils.isNotEmpty(companyDescr) 
				&& StringUtils.isNotEmpty(wa.getCompanyDescr())
				&& wa.getCompanyDescr().indexOf(companyDescr) == -1)
			return Boolean.FALSE;
		
		return Boolean.TRUE;
	}
	
	
	@RequestMapping(value = "/findUserWarehouse")
	public PageResult<WarehouseActiveVO> findUserWarehouse(@RequestBody String req) {
		List<WarehouseActiveVO> activeList = Lists.newArrayList();
		try {
			PageRequest request = pageRequest(req);
			UserVO user = ShiroUtils.getSysUser();
			List<WarehouseVO> userWarehouse = user.getAllWarehouses();
			
			List<WarehouseVO> findWarehouse = userWarehouse.stream().filter( v-> searchUserWarehouse(v, request)).collect(Collectors.toList());
			
			if (CollectionUtils.isEmpty(findWarehouse)) 
				return page(Lists.newArrayList());
			
			Set<Long> warehouseIds = findWarehouse.stream().map(WarehouseVO::getWarehouseId).collect(Collectors.toSet());
			Set<Long> companyIds = findWarehouse.stream().map(WarehouseVO::getCompanyId).collect(Collectors.toSet());
			List<WarehouseActiveTEntity> waList = warehouseActiveService.find(companyIds, warehouseIds);
			Map<String, WarehouseActiveTEntity> waMap = Maps.newHashMap();
			if (CollectionUtils.isNotEmpty(waList)) {
				waMap = waList.stream().collect(Collectors.toMap(k -> (StringUtils.join(k.getCompanyId(), k.getWarehouseId())), v -> v));
			}
			for (WarehouseVO d : findWarehouse) {
				WarehouseActiveVO vo = new WarehouseActiveVO();
				BeanUtils.copyBeanProp(vo, d);
				WarehouseActiveTEntity wa = waMap.get(StringUtils.join(d.getCompanyId(), d.getWarehouseId()));
				if (wa != null) {
					vo.setActiveId(wa.getActiveId());
					vo.setActive(wa.getActive());
					vo.setUpdateVersion(wa.getUpdateVersion());
					vo.setUpdateBy(wa.getUpdateBy());
					vo.setUpdateTime(wa.getUpdateTime());
				}else {
					vo.setActiveId(0L);
					vo.setActive(YesNoEnum.No.getCode());
					vo.setUpdateVersion(0L);
				}
				activeList.add(vo);
			}
			
			//filter
			if (request.containsKey(SysWarehousesTEntity.Column.active.getJavaProperty())) {
				String condition = request.getString(SysWarehousesTEntity.Column.active.getJavaProperty());
				if (StringUtils.isNotEmpty(condition))
					activeList = activeList.stream().filter( v-> v.getActive().equals(condition)).collect(Collectors.toList());
			}
			//手动实现分页
			PageInfo<WarehouseActiveVO> pageList = new PageInfo<>();
			pageList.setTotal(activeList.size());
			int pagesize = request.getPageSize();
			int pagestart = request.getPageStart();
			if (pageList.getTotal() >= pagesize) {
				int startindex = (pagestart - 1) * pagesize;
				int endindex = pagestart * pagesize;
				endindex = endindex > pageList.getTotal() ? (int)pageList.getTotal() : endindex;
				activeList = activeList.subList(startindex, endindex);
			}
			pageList.setList(activeList);
			return page(pageList);
		} catch (Exception e) {
			return pageFail(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/active")
	public AjaxResult active(@RequestBody String req) {
		try {
			AjaxRequest<List<WarehouseActiveTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<WarehouseActiveTEntity>>>() {});
			boolean flag = warehouseActiveService.active(request);
			if (flag) {
				UserVO vo = ShiroUtils.getSysUser();
				if (vo != null) {
					//当前启用/禁用的仓库
					request.getData().forEach(a -> {
						boolean existsFlag = false;
						List<WarehouseVO> userActiveWarehouse = vo.getWarehouses();
						Iterator<WarehouseVO> iterator = userActiveWarehouse.iterator();
						while(iterator.hasNext()) {
							WarehouseVO w = iterator.next();
							//与当前用户的进行比较
							if (a.getCompanyId().equals(w.getCompanyId())
									&& a.getWarehouseId().equals(w.getWarehouseId())) {
								existsFlag = true;
								//不启用仓库
								if(!YesNoEnum.Yes.getCode().equals(a.getActive())) {
									//禁用仓库时则移除
									iterator.remove();
								}
							}
						}
						//启用仓库时增加
						if (!existsFlag
								&& YesNoEnum.Yes.getCode().equals(a.getActive())) {
							userActiveWarehouse.add(vo.getWarehouseMap().get(a.getWarehouseId()));
						}
					});
					ShiroUtils.setSysUser(vo);
				}
				return success();
			}
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return fail();
	}

}
