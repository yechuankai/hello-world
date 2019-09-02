/**  
* <p>Title: IAppUserRestService.java</p>  
* <p>Description: </p>    
* <p>Company: CMHIT</p>  
* @author yupu.chnet  
* @date 2019年8月16日  
* @version 1.0  
*/  
package com.wms.services.app;

import java.util.List;

import com.wms.entity.auto.SysUserTEntity;
import com.wms.vo.PermissionVO;
import com.wms.vo.UserVO;

/**
 * <p>Title: IAppUserRestService</p>  
 * <p>Description: </p>  
 * @author yupu.chnet
 * @date 2019年8月16日
 * @version V1.0.0
 */
public interface IAppUserRestService {

	UserVO appLogin(SysUserTEntity user);
	
	List<PermissionVO> queryAppUserPermByUser(SysUserTEntity paramUser, String locale);
}
