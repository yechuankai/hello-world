/**  
* <p>Title: AppUserRestServiceImpl.java</p>  
* <p>Description: </p>    
* <p>Company: CMHIT</p>  
* @author yupu.chnet  
* @date 2019年8月16日  
* @version 1.0  
*/  
package com.wms.services.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wms.common.enums.PermissionTypeEnum;
import com.wms.common.exception.user.UserPasswordNotMatchException;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.cas.CasClientUtil;
import com.wms.entity.auto.SysPermissionTEntity;
import com.wms.entity.auto.SysUserTEntity;
import com.wms.services.app.IAppUserRestService;
import com.wms.services.sys.impl.SysPermissionServiceImpl;
import com.wms.services.sys.impl.SysUserServiceImpl;
import com.wms.vo.PermissionVO;
import com.wms.vo.UserVO;

/**
 * <p>Title: AppUserRestServiceImpl</p>  
 * <p>Description: </p>  
 * @author yupu.chnet
 * @date 2019年8月16日
 * @version V1.0.0
 */
@Service
public class AppUserRestServiceImpl implements IAppUserRestService {

	private static final String WMS_MOBILE_MENU_HEADER = "0";
	
	@Value("${cas.tickets}")
	private String server;
	
	
	@Autowired
	private SysUserServiceImpl sysUserServiceImpl;
	
	@Autowired
	private SysPermissionServiceImpl sysPermissionServiceImpl;
	
	/* (non-Javadoc)
	 * @see com.wms.services.app.IAppUserPermService#queryAppUserPermByUser(com.wms.vo.UserVO)
	 */
	@Override
	public List<PermissionVO> queryAppUserPermByUser(SysUserTEntity paramUser, String locale) {
		List<SysPermissionTEntity> list = Lists.newArrayList();
		UserVO user = new UserVO();
		BeanUtils.copyBeanProp(user, paramUser, Boolean.FALSE);
		if (user.isAdmin()) {
			list = sysPermissionServiceImpl.findPermission(PermissionTypeEnum.Function, locale);
		} else {
			list = sysPermissionServiceImpl.findUserPermission(paramUser, PermissionTypeEnum.Function, locale);
		}
		return getMobileMenu(list);
	}

	/* (non-Javadoc)
	 * @see com.wms.services.app.IAppUserRestService#appLogin(com.wms.vo.UserVO)
	 */
	@Override
	public UserVO appLogin(SysUserTEntity user) {
		String username = user.getUserName();
		String password = user.getPassword();
		String token = CasClientUtil.getTicketGrantingTicket(server, username, password);
		if (StringUtils.isBlank(token)) {
			throw new UserPasswordNotMatchException();
		}
		UserVO result = new UserVO();
		SysUserTEntity userTEntity = sysUserServiceImpl.findUserByLoginName(username);
		SysUserTEntity entity = sysUserServiceImpl.findUserById(userTEntity.getUserId());
		BeanUtils.copyBeanProp(result, entity, Boolean.FALSE);
		result.setToken(token);
		return result;
	}

	/**
	 *  获取移动端菜单
	 * <p>Title: getMobileMenu</p>  
	 * <p>Description: </p>  
	 * @author yupu.chnet
	 * @date 2019年8月19日
	 * @param list
	 * @return
	 */
	private List<PermissionVO> getMobileMenu(List<SysPermissionTEntity> list) {
		// 底部主菜单
		List<SysPermissionTEntity> mobileHeaderMenu = new ArrayList<SysPermissionTEntity>();
		// 子菜单
		List<SysPermissionTEntity> mobileSubMenu = new ArrayList<SysPermissionTEntity>();
		
		List<PermissionVO> result = Lists.newArrayList();
		list.forEach(entity -> {
			if (StringUtils.equals(WMS_MOBILE_MENU_HEADER, String.valueOf(entity.getParentId()))) {
				mobileHeaderMenu.add(entity);
			} else {
				mobileSubMenu.add(entity);
			}
		});
		
		mobileHeaderMenu.forEach(header -> {
			// 主菜单对应的子菜单集合
			List<SysPermissionTEntity> headerSubMenu = new ArrayList<SysPermissionTEntity>();
			mobileSubMenu.forEach(sub -> {
				if (StringUtils.equals(String.valueOf(sub.getParentId()), String.valueOf(header.getPermissionId()))) {
					headerSubMenu.add(sub);
				}
			});
			PermissionVO permission = new PermissionVO();
			BeanUtils.copyBeanProp(permission, header, Boolean.FALSE);
			permission.setMobileSubFunc(headerSubMenu);
			result.add(permission);
		});
//		mobileHeaderMenu.remove(2);
//		mobileHeaderMenu.remove(1);
		return result;
	}
}
