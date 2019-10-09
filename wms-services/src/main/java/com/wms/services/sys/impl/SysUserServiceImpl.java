package com.wms.services.sys.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.common.constants.DefaultConstants;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysUserDefaultTDao;
import com.wms.dao.auto.ISysUserTDao;
import com.wms.dao.example.SysUserDefaultTExample;
import com.wms.dao.example.SysUserTExample;
import com.wms.entity.auto.*;
import com.wms.services.sys.*;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.vo.UserVO;
import com.wms.vo.WarehouseVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户 业务层
 * 
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

	
	@Autowired
	private ISysUserTDao userDao;
	
	@Autowired
	private ISysUserDefaultTDao userDefaultDao;
	
	@Autowired
	private ISysWarehouseService warehouseService;
	
	@Autowired
	private ISysCompanyService companyService;
	
	@Autowired
	private ISysPasswordService passwordService;
	
	@Autowired
	private IWarehouseActiveService warehouseActiveService;
	
	
	@Override
	public SysUserTEntity findUserByLoginName(String userName) {
		
		SysUserTExample example = new SysUserTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andActiveEqualTo(YesNoEnum.Yes.getCode())
		.andLoginNameEqualTo(userName);
		
		return userDao.selectOneByExample(example);
		
	}

	@Override
	public UserVO findUserById(Long userid) {
		SysUserTExample example = new SysUserTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andActiveEqualTo(YesNoEnum.Yes.getCode())
		.andUserIdEqualTo(userid);
		
		SysUserTEntity user = userDao.selectOneByExample(example);
		if (user == null) {
			throw new BusinessServiceException("SysUserServiceImpl", "user.not.found.byid", new Object[]{userid} , "user not find: " + userid);
		}
		
		UserVO userVo = new UserVO();
		BeanUtils.copyBeanProp(userVo, user, Boolean.FALSE);//复制属性
		
		//查找仓库
		List<SysWarehousesTEntity> warehouses = null;
		if(userVo.isAdmin()) {
			warehouses = warehouseService.findAll();
		}else {
			warehouses = warehouseService.findUserWarehouse(user);
		}
		
		//查找公司
		List<SysCompanysTEntity> companys = null;
		if(userVo.isAdmin()) {
			companys = companyService.findAll();
		}else {
			companys = companyService.findUserCompany(user);
		}
		if (userVo.getCompanyId() != null) {
			if (companys == null)
				companys = Lists.newArrayList();
			
			if (!userVo.isAdmin())
				companys.add(SysCompanysTEntity.builder().companyId(userVo.getCompanyId()).descr(userVo.getCompanyDescr()).build());
		}
		
		//合并公司、仓库
		if (CollectionUtils.isNotEmpty(warehouses) && 
				CollectionUtils.isNotEmpty(companys)) {
			List<WarehouseVO> userWarehouse = Lists.newArrayList();
			for (SysWarehousesTEntity w : warehouses) {
				for (SysCompanysTEntity c : companys) {
					WarehouseVO vo = new WarehouseVO();
					vo.setWarehouseId(w.getWarehouseId());
					vo.setWarehouseCode(w.getCode());
					vo.setWarehouseDescr(w.getDescr());
					vo.setCompanyId(c.getCompanyId());
					vo.setCompanyCode(c.getCode());
					vo.setCompanyDescr(c.getDescr());
					//添加到用户变量中
					userWarehouse.add(vo);
				}
			}
			userVo.setAllWarehouses(userWarehouse);
		}
		
		int systemCount = 0;
		if (CollectionUtils.isNotEmpty(userVo.getAllWarehouses())) {
			
			Set<Long> warehouseIds = userVo.getAllWarehouses().stream().filter(d->null != d.getWarehouseId()).map(WarehouseVO::getWarehouseId).collect(Collectors.toSet());
			Set<Long> companyIds = userVo.getAllWarehouses().stream().filter(d->null != d.getCompanyId()).map(WarehouseVO::getCompanyId).collect(Collectors.toSet());
			List<WarehouseActiveTEntity> waList = warehouseActiveService.find(companyIds, warehouseIds);
			Map<String, String> waMap = Maps.newHashMap();
			if (CollectionUtils.isNotEmpty(waList)) {
				waMap = waList.stream().collect(Collectors.toMap(k -> (StringUtils.join(k.getCompanyId(), k.getWarehouseId())), v -> (v.getActive())));
			}
			
			final Map<String, String> checkMap = waMap;
			userVo.getAllWarehouses().forEach(w -> {
				//验证仓库是否激活
				boolean active = YesNoEnum.Yes.getCode().equals(checkMap.get(StringUtils.join(w.getCompanyId(), w.getWarehouseId())));
				if (active)
					userVo.getWarehouses().add(w);
			});
			if (userVo.isAdmin() 
					&& CollectionUtils.isNotEmpty(userVo.getWarehouses())) {
				//增加系统仓库
				WarehouseVO wh = new WarehouseVO();
				wh.setCompanyId(0L);
				wh.setCompanyDescr(DefaultConstants.COMPANY_DESCR);
				wh.setWarehouseId(0L);
				wh.setWarehouseCode(DefaultConstants.WAREHOUSE_CODE);
				wh.setWarehouseDescr(DefaultConstants.WAREHOUSE_DESCR);
				userVo.getWarehouses().add(0, wh);
				systemCount += 1;
			}
		}
		
		//仅拥有一个仓库时，自动默认该仓库
		if ((userVo.getWarehouses().size() - systemCount) == 1) {
			WarehouseVO w = userVo.getWarehouses().get(0);
			userVo.setWarehouseId(w.getWarehouseId());
			userVo.setCompanyId(w.getCompanyId());
		}else {
			//查找默认值配置
			SysUserDefaultTExample defaultExample = new SysUserDefaultTExample();
			defaultExample.createCriteria()
			.andLoginNameEqualTo(user.getLoginName())
			.andDelFlagEqualTo(YesNoEnum.No.getCode());
			SysUserDefaultTEntity userDefault = userDefaultDao.selectOneByExample(defaultExample);
			if (userDefault != null)
				userVo.setUserDefault(userDefault);
		}
		
		
		return userVo;
	}

	@Override
	public Boolean restPassword(SysUserTEntity user, String newPassword) {
		if (StringUtils.isEmpty(newPassword)) {
			throw new BusinessServiceException("SysUserServiceImpl", "password.is.null" , null);
		}
		
		SysUserTExample example = new SysUserTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andUserIdEqualTo(user.getUserId());
		
		SysUserTEntity saveUser = userDao.selectOneByExample(example);
		if (saveUser == null) {
			throw new BusinessServiceException("SysUserServiceImpl", "user.not.found.byid", new Object[]{user.getUserId()} , "user not find: " + user.getUserId());
		}
		
		if (newPassword.equals(user.getPassword())) {
			throw new BusinessServiceException("SysUserServiceImpl", "password.original.match" , null);
		}
		
		String salt = ShiroUtils.randomSalt();
		String encodepassword = passwordService.encryptPassword(saveUser.getLoginName(), newPassword, salt);
		
		SysUserTEntity updateUser = new SysUserTEntity();
		updateUser.setUserId(user.getUserId());
		updateUser.setPassword(encodepassword);
		updateUser.setSalt(salt);
		updateUser.setUpdateBy(user.getUserName());
		updateUser.setUpdateTime(new Date());
		
		int row = userDao.updateWithVersionByExampleSelective(saveUser.getUpdateVersion(), updateUser, example);
		if (row == 0) {
			throw new BusinessServiceException("SysUserServiceImpl", "update user error" , null);
		}
		
		return Boolean.TRUE;
	}
	
	@Override
	public List<SysUserTEntity> find(PageRequest request) throws BusinessServiceException {
		SysUserTExample example = new SysUserTExample();
		SysUserTExample.Criteria exampleCriteria = example.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SysUserTEntity.Column.class, SysUserTExample.Criterion.class)
				.criteria(exampleCriteria)
				.data(request)
				.build(request)
				.orderby(example);
		
		exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		List<SysUserTEntity> list = userDao.selectByExample(example);
		list.forEach(u -> {
			u.setPassword("");
		});
		return list;
	}

	@Override
	@Transactional
	public Boolean modify(AjaxRequest<List<SysUserTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}
		
		List<SysUserTEntity> list = request.getData();
		
		for (SysUserTEntity u : list) {
			
			SysUserTEntity update = SysUserTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.active(u.getActive())
			.userName(u.getUserName())
			.build();
			
			if (StringUtils.isNotEmpty(u.getPassword())) {
				String salt = ShiroUtils.randomSalt();
				String encodepassword = passwordService.encryptPassword(u.getLoginName(), u.getPassword(), salt);
				update.setSalt(salt);
				update.setPassword(encodepassword);
			}
			
			SysUserTExample example = new SysUserTExample();
			example.createCriteria()
			.andUserIdEqualTo(u.getUserId());
			
			int row = userDao.updateWithVersionByExampleSelective(u.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean add(AjaxRequest<List<SysUserTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}
		
		List<SysUserTEntity> list = request.getData();
		
		for (SysUserTEntity u : list) {
			
			String loginName = u.getLoginName().toUpperCase();
			
			SysUserTExample example = new SysUserTExample();
			example.createCriteria()
			.andDelFlagEqualTo(YesNoEnum.No.getCode())
			.andLoginNameEqualTo(loginName);
			Long count = userDao.countByExample(example);
			if (count > 0) {
				throw new BusinessServiceException("SysUserServiceImpl", "user.record.exists" , new Object[] {loginName}); 
			}
			
			String salt = ShiroUtils.randomSalt();
			String encodepassword = passwordService.encryptPassword(loginName, u.getPassword(), salt);
			
			SysUserTEntity update = SysUserTEntity.builder()
			.createBy(request.getUserName())
			.createTime(new Date())
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.loginName(loginName)
			.userName(u.getUserName())
			.password(encodepassword)
			.active(u.getActive())
			.salt(salt)
			.userId(KeyUtils.getUID())
			.build();
			
			int row = userDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<SysUserTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<SysUserTEntity> list = request.getData();
		
		for (SysUserTEntity u : list) {
			SysUserTEntity update = SysUserTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.delFlag(YesNoEnum.Yes.getCode())
			.userId(u.getUserId())
			.build();
			
			SysUserTExample example = new SysUserTExample();
			example.createCriteria()
			.andUserIdEqualTo(u.getUserId());
			
			int row = userDao.updateWithVersionByExampleSelective(u.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}
	
	
	

}
