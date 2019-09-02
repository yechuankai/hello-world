package com.wms.web.shiro.services;

import java.io.Serializable;

import org.apache.shiro.session.Session;

import com.wms.entity.auto.SysUserOnlineTEntity;
import com.wms.web.shiro.session.OnlineSession;

public interface ISysShiroService {

	public Session getSession(Serializable sessionId);

	public void deleteSession(OnlineSession onlineSession);

	public Session createSession(SysUserOnlineTEntity userOnline);
}
