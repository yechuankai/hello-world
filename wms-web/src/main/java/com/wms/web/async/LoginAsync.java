package com.wms.web.async;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wms.common.enums.LoginStatusEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.utils.AddressUtils;
import com.wms.common.utils.ServletUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.entity.auto.SysLoginInforTEntity;
import com.wms.entity.auto.SysUserOnlineTEntity;
import com.wms.services.sys.ISysLoginInforService;
import com.wms.services.sys.ISysUserOnlineService;
import com.wms.shiro.utils.ShiroUtils;
import com.wms.web.shiro.session.OnlineSession;
import com.wms.web.utils.LogUtils;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * 异步工厂（产生任务用）
 *
 */
public class LoginAsync {
	private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

	/**
	 * 同步session到数据库
	 * 
	 * @param session 在线用户会话
	 * @return 任务task
	 */
	public static TimerTask syncSessionToDb(final OnlineSession session) {
		return new TimerTask() {
			@Override
			public void run() {
				SysUserOnlineTEntity online = new SysUserOnlineTEntity();
				online.setSessionid(String.valueOf(session.getId()));
				online.setLoginName(session.getLoginName());
				online.setStartTimestamp(session.getStartTimestamp());
				online.setLastAccessTime(session.getLastAccessTime());
				online.setExpireTime(session.getTimeout());
				online.setIpaddr(session.getHost());
				online.setLoginLocation(AddressUtils.getRealAddressByIP(session.getHost()));
				online.setBrowser(session.getBrowser());
				online.setOs(session.getOs());
				online.setStatus(session.getStatus().getCode());
				SpringUtils.getBean(ISysUserOnlineService.class).save(online);

			}
		};
	}

	/**
	 * 记录登陆信息
	 * 
	 * @param username 用户名
	 * @param status   状态
	 * @param message  消息
	 * @param args     列表
	 * @return 任务task
	 */
	public static TimerTask recordLogininfor(final String username, final String status, final String message,
			final Object... args) {
		final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
		final String ip = ShiroUtils.getIp();
		return new TimerTask() {
			@Override
			public void run() {
				StringBuilder s = new StringBuilder();
				s.append(LogUtils.getBlock(ip));
				s.append(AddressUtils.getRealAddressByIP(ip));
				s.append(LogUtils.getBlock(username));
				s.append(LogUtils.getBlock(status));
				s.append(LogUtils.getBlock(message));
				// 打印信息到日志
				sys_user_logger.info(s.toString(), args);
				// 获取客户端操作系统
				String os = userAgent.getOperatingSystem().getName();
				// 获取客户端浏览器
				String browser = userAgent.getBrowser().getName();
				// 封装对象
				SysLoginInforTEntity logininfor = new SysLoginInforTEntity();
				logininfor.setLoginName(username);
				logininfor.setIpAddress(ip);
				logininfor.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
				logininfor.setBrowser(browser);
				logininfor.setOs(os);
				logininfor.setMsg(message);
				// 日志状态
				if (LoginStatusEnum.Login.getCode().equals(status) || LoginStatusEnum.Logout.getCode().equals(status)) {
					logininfor.setStatus(YesNoEnum.Yes.getCode());
				} else if (LoginStatusEnum.Fail.getCode().equals(status)) {
					logininfor.setStatus(YesNoEnum.No.getCode());
				}
				// 插入数据
				SpringUtils.getBean(ISysLoginInforService.class).save(logininfor);
			}
		};
	}
}
