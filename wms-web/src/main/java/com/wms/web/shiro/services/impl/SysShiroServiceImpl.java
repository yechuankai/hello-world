package com.wms.web.shiro.services.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.wms.common.constants.DefaultConstants;
import com.wms.common.utils.StringUtils;
import com.wms.entity.auto.SysUserOnlineTEntity;
import com.wms.services.sys.ISysUserOnlineService;
import com.wms.web.shiro.services.ISysShiroService;
import com.wms.web.shiro.session.OnlineSession;

/**
 * 会话db操作处理
 * 
 */
@Component
public class SysShiroServiceImpl implements ISysShiroService {
	@Autowired
	private ISysUserOnlineService onlineService;

	/**
	 * 删除会话
	 *
	 * @param onlineSession 会话信息
	 */
	public void deleteSession(OnlineSession onlineSession) {
		SysUserOnlineTEntity useronline = SysUserOnlineTEntity.builder().sessionid(String.valueOf(onlineSession.getId()))
				.updateBy(DefaultConstants.SYSTEM_USER).build();
		List<SysUserOnlineTEntity> list = Lists.newArrayList();
		list.add(useronline);
		onlineService.offline(list);
	}

	/**
	 * 获取会话信息
	 *
	 * @param sessionId
	 * @return
	 */
	public Session getSession(Serializable sessionId) {
		SysUserOnlineTEntity userOnline = onlineService.findOnlineById(String.valueOf(sessionId));
		return StringUtils.isNull(userOnline) ? null : createSession(userOnline);
	}

	public Session createSession(SysUserOnlineTEntity userOnline) {
		OnlineSession onlineSession = new OnlineSession();
		if (StringUtils.isNotNull(userOnline)) {
			onlineSession.setId(userOnline.getSessionid());
			onlineSession.setHost(userOnline.getIpaddr());
			onlineSession.setBrowser(userOnline.getBrowser());
			onlineSession.setOs(userOnline.getOs());
			onlineSession.setLoginName(userOnline.getLoginName());
			onlineSession.setStartTimestamp(userOnline.getStartTimestamp());
			onlineSession.setLastAccessTime(userOnline.getLastAccessTime());
			onlineSession.setTimeout(userOnline.getExpireTime());

		}
		return onlineSession;
	}
}
