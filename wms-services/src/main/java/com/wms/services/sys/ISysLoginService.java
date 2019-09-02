package com.wms.services.sys;

import com.wms.vo.UserVO;

public interface ISysLoginService {

	public UserVO login(String username, String password) ;
}
