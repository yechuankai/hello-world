package com.wms.web.confg;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.wms.common.utils.StringUtils;
import com.wms.web.shiro.realm.ShiroCasRealm;
import com.wms.web.shiro.session.OnlineSessionDao;
import com.wms.web.shiro.session.OnlineSessionFactory;
import com.wms.web.shiro.web.captcha.filter.CaptchaValidateFilter;
import com.wms.web.shiro.web.filter.online.OnlineSessionFilter;
import com.wms.web.shiro.web.filter.sync.SyncOnlineSessionFilter;
import com.wms.web.shiro.web.session.OnlineWebSessionManager;
import com.wms.web.shiro.web.session.SpringSessionValidationScheduler;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * shiro+cas配置
 */
@Configuration
public class ShiroCasConfig {
	private static final Logger log = LoggerFactory.getLogger(ShiroCasConfig.class);

	public static final String PREMISSION_STRING = "perms[\"{0}\"]";

	// Session超时时间，单位为毫秒（默认30分钟）
	@Value("${shiro.session.expireTime}")
	private int expireTime;
	
	//匿名访问地址
	@Value("#{'${shiro.anon}'.split(',')}")
	private List<String> filterChainDefinitionList;

	// 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
	@Value("${shiro.session.validationInterval}")
	private int validationInterval;
	
    // 验证码开关
    @Value("${shiro.user.captchaEnabled}")
    private boolean captchaEnabled;

    // 验证码类型
    @Value("${shiro.user.captchaType}")
    private String captchaType;

	// 设置Cookie的域名
	@Value("${shiro.cookie.domain}")
	private String domain;

	// 设置cookie的有效访问路径
	@Value("${shiro.cookie.path}")
	private String path;

	// 设置HttpOnly属性
	@Value("${shiro.cookie.httpOnly}")
	private boolean httpOnly;

	// 设置Cookie的过期时间，秒为单位
	@Value("${shiro.cookie.maxAge}")
	private int maxAge;

	// 登录地址
	//@Value("${shiro.user.loginUrl}")
	@Value("${cas.loginUrl}")
	private String loginUrl;
	
	// 登录地址
	@Value("${shiro.user.logoutUrl}")
	private String logoutUrl;

	// 权限认证失败地址
	@Value("${shiro.user.unauthorizedUrl}")
	private String unauthorizedUrl;
	
	// 权限认证失败地址
	@Value("${shiro.ehcache}")
	private String ehcacheCinfig;
	
	@Value("${cas.server}") 
	private String casServerUrlPrefix;
	
    @Value("${shiro.user.indexUrl}") 
    private String index;
    
    @Value("${cas.casFilterUrl}")
    private String casFilterUrl;
    
    @Value("${wms.url.service}")
    private String serviceUrl;
    
	/**
	 * 缓存管理器 使用Ehcache实现
	 */
	@Bean
	public EhCacheManager getEhCacheManager() {
		net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.getCacheManager("wms.ehcache");
		EhCacheManager em = new EhCacheManager();
		if (StringUtils.isNull(cacheManager)) {
			em.setCacheManager(new net.sf.ehcache.CacheManager(getCacheManagerConfigFileInputStream()));
			return em;
		} else {
			em.setCacheManager(cacheManager);
			return em;
		}
	}
	
	/**
	 * 返回配置文件流 避免ehcache配置文件一直被占用，无法完全销毁项目重新部署
	 */
	protected InputStream getCacheManagerConfigFileInputStream() {
		//String configFile = "classpath:config/ehcache/ehcache-shiro.xml";
		try (InputStream inputStream = ResourceUtils.getInputStreamForPath(ehcacheCinfig);){
			byte[] b = IOUtils.toByteArray(inputStream);
			InputStream in = new ByteArrayInputStream(b);
			return in;
		} catch (IOException e) {
			throw new ConfigurationException(
					"Unable to obtain input stream for cacheManagerConfigFile [" + ehcacheCinfig + "]", e);
		}
	}
	
	/**
	 * 自定义Realm
	 */
	@Bean
	public ShiroCasRealm myShiroCasRealm(EhCacheManager cacheManager) {
	    ShiroCasRealm realm = new ShiroCasRealm();
	    realm.setCacheManager(cacheManager);
	    return realm;
	}
	
	/**
	 * 自定义sessionDAO会话
	 */
	@Bean
	public OnlineSessionDao sessionDAO() {
		OnlineSessionDao sessionDAO = new OnlineSessionDao();
		return sessionDAO;
	}

	/**
	 * 自定义sessionFactory会话
	 */
	@Bean
	public OnlineSessionFactory sessionFactory() {
		OnlineSessionFactory sessionFactory = new OnlineSessionFactory();
		return sessionFactory;
	}

	/**
	 * 自定义sessionFactory调度器
	 */
	@Bean
	public SpringSessionValidationScheduler sessionValidationScheduler() {
		SpringSessionValidationScheduler sessionValidationScheduler = new SpringSessionValidationScheduler();
		// 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
		sessionValidationScheduler.setSessionValidationInterval(validationInterval * 60 * 1000);
		// 设置会话验证调度器进行会话验证时的会话管理器
		sessionValidationScheduler.setSessionManager(sessionValidationManager());
		return sessionValidationScheduler;
	}

	/**
	 * 会话管理器
	 */
	@Bean
	public OnlineWebSessionManager sessionValidationManager() {
		OnlineWebSessionManager manager = new OnlineWebSessionManager();
		// 加入缓存管理器
		manager.setCacheManager(getEhCacheManager());
		// 删除过期的session
		manager.setDeleteInvalidSessions(true);
		// 设置全局session超时时间
		manager.setGlobalSessionTimeout(expireTime * 60 * 1000);
		// 去掉 JSESSIONID
		manager.setSessionIdUrlRewritingEnabled(false);
		// 是否定时检查session
		manager.setSessionValidationSchedulerEnabled(true);
		// 自定义SessionDao
		manager.setSessionDAO(sessionDAO());
		// 自定义sessionFactory
		manager.setSessionFactory(sessionFactory());
		return manager;
	}

	/**
	 * 会话管理器
	 */
	@Bean
	public OnlineWebSessionManager sessionManager() {
		OnlineWebSessionManager manager = new OnlineWebSessionManager();
		// 加入缓存管理器
		manager.setCacheManager(getEhCacheManager());
		// 删除过期的session
		manager.setDeleteInvalidSessions(true);
		// 设置全局session超时时间
		manager.setGlobalSessionTimeout(expireTime * 60 * 1000);
		// 去掉 JSESSIONID
		manager.setSessionIdUrlRewritingEnabled(false);
		// 定义要使用的无效的Session定时调度器
		manager.setSessionValidationScheduler(sessionValidationScheduler());
		// 是否定时检查session
		manager.setSessionValidationSchedulerEnabled(true);
		// 自定义SessionDao
		manager.setSessionDAO(sessionDAO());
		// 自定义sessionFactory
		manager.setSessionFactory(sessionFactory());
		return manager;
	}

	
	/**
	 * 记住我
	 */
	private CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		cookieRememberMeManager.setCipherKey(Base64.decode("fCq+/xW488hMTCD+cmJ3aQ=="));
		return cookieRememberMeManager;
	}
	
	/**
	 * cookie 属性设置
	 */
	private SimpleCookie rememberMeCookie() {
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setHttpOnly(httpOnly);
		cookie.setMaxAge(maxAge * 24 * 60 * 60);
		return cookie;
	}
	  
	/****************************************************************************************************/
	
	/**
	 * 注册单点登出listener
	 *
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean singleSignOutHttpSessionListener() {
	    ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
	    bean.setListener(new SingleSignOutHttpSessionListener());
	      //bean.setName(""); //默认为bean name
	    bean.setEnabled(true);
	    //bean.setOrder(Ordered.HIGHEST_PRECEDENCE); //设置优先级
	    return bean;
	}
	
	/**
	 * 注册单点登出filter
	 *
	 * @return
	 */
	@Bean
	public FilterRegistrationBean singleSignOutFilter() {
	    FilterRegistrationBean bean = new FilterRegistrationBean();
	    bean.setName("singleSignOutFilter");
	    bean.setFilter(new SingleSignOutFilter());
	    bean.addUrlPatterns(logoutUrl);
	    bean.setEnabled(true);
	    return bean;
	}
	
	/**
	 * 注册DelegatingFilterProxy（Shiro）
	 * @return
	 */
	@Bean
	public FilterRegistrationBean delegatingFilterProxy() {
	    FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
	    filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
	    // 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
	    filterRegistration.addInitParameter("targetFilterLifecycle", "true");
	    filterRegistration.setEnabled(true);
	    filterRegistration.addUrlPatterns("/*");
	    return filterRegistration;
	}
	
	
	/**
	 *
	 * @return 生命周期管理，必须为static，否则无法主任@value
	 */
	@Bean
	public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
	    return new LifecycleBeanPostProcessor();
	}
	 
	
	/**
	 *
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
	    DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
	    daap.setProxyTargetClass(true);
	    return daap;
	}
	
	
	/**
	 *
	 * @param myShiroCasRealm
	 * @return
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(ShiroCasRealm myShiroCasRealm) {
	    DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
	    dwsm.setRealm(myShiroCasRealm);
	    // 记住我
	    dwsm.setRememberMeManager(rememberMeManager());
	    // <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
	    dwsm.setCacheManager(getEhCacheManager());
	    // session管理器
	    dwsm.setSessionManager(sessionManager());
	    // 指定 SubjectFactory
	    dwsm.setSubjectFactory(new CasSubjectFactory());
	    return dwsm;
	}
	
	/**
	 * 注解通知器
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
	    AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
	    aasa.setSecurityManager(securityManager);
	    return aasa;
	}
	
	
	/**
	 * CAS过滤器
	 * @param casServerUrlPrefix
	 * @param shiroServerUrlPrefix
	 * @return
	 */
	@Bean(name = "casFilter")
	public CasFilter getCasFilter() {
	    CasFilter casFilter = new CasFilter();
	    casFilter.setName("casFilter");
	    casFilter.setEnabled(true);
	    // 登录失败后跳转的URL，也就是 Shiro 执行 CasRealm 的 doGetAuthenticationInfo 方法向CasServer验证tiket
	    casFilter.setFailureUrl(loginUrl);// 我们选择认证失败后再打开登录页面
	    casFilter.setSuccessUrl(serviceUrl + index);
	    return casFilter;
	}
	
	/**
	 * ShiroFilter<br/>
	 * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
	 * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
	 * @param securityManager
	 * @param casFilter
	 * @param casServerUrlPrefix
	 * @param shiroServerUrlPrefix
	 * @param loginSuccessUrl
	 * @param unauthorizedUrl
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager,
	                                                        CasFilter casFilter) {
	    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
	    // 必须设置 SecurityManager
	    shiroFilterFactoryBean.setSecurityManager(securityManager);
	    // 身份认证失败，则跳转到登录页面的配置
	    shiroFilterFactoryBean.setLoginUrl(loginUrl);
	    // 登录成功后要跳转的连接
	    shiroFilterFactoryBean.setSuccessUrl(loginUrl);
	    // 权限认证失败，则跳转到指定页面
	    shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
	    
	    // 添加casFilter到shiroFilter中
	    Map<String, Filter> filters = new LinkedHashMap<>();
	    filters.put("onlineSession", onlineSessionFilter());
		filters.put("syncOnlineSession", syncOnlineSessionFilter());
		filters.put("captchaValidate", captchaValidateFilter());
	    filters.put("casFilter", casFilter);
	    // 注销成功，则跳转到指定页面
	    // filters.put("logout",logoutFilter());
	    shiroFilterFactoryBean.setFilters(filters);
	
	    loadShiroFilterChain(shiroFilterFactoryBean, casFilterUrl);
	    return shiroFilterFactoryBean;
	}
	
	/**
	 * 加载shiroFilter权限控制规则（从数据库读取然后配置）,角色/权限信息由MyShiroCasRealm对象提供doGetAuthorizationInfo实现获取来的
	 * @param shiroFilterFactoryBean
	 */
	private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean, @Value("${cas.casFilterUrl}") String casFilterUrl) {
	    
	    Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
	
	    // authc：该过滤器下的页面必须登录后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
	    // anon: 可以理解为不拦截
	    // user: 登录了就不拦截
	    // roles["admin"] 用户拥有admin角色
	    // perms["permission1"] 用户拥有permission1权限
	    // filter顺序按照定义顺序匹配，匹配到就验证，验证完毕结束。
	    // url匹配通配符支持：? * **,分别表示匹配1个，匹配0-n个（不含子路径），匹配下级所有路径
	
	    //1.shiro集成cas后，首先添加该规则
	    filterChainDefinitionMap.put(casFilterUrl, "casFilter");
	    //logut请求采用logout filter
	    
	    // 对静态资源设置匿名访问
 		for (String anon : filterChainDefinitionList) {
 			filterChainDefinitionMap.put(anon, "anon");
 		}
 		// 退出 logout地址，shiro去清除session
 		filterChainDefinitionMap.put(logoutUrl, "logout");
 		// 不需要拦截的访问
 		filterChainDefinitionMap.put(loginUrl, "anon,captchaValidate");
 		// 系统权限列表
 		// filterChainDefinitionMap.putAll(SpringUtils.getBean(IMenuService.class).selectPermsAll());
 		// 所有请求需要认证
		filterChainDefinitionMap.put("/**", "user,onlineSession,syncOnlineSession");
 		
	    /*//2.不拦截的请求
	    filterChainDefinitionMap.put("/css/**", "anon");
	    filterChainDefinitionMap.put("/js/**", "anon");
	    filterChainDefinitionMap.put("/login", "anon");
	    filterChainDefinitionMap.put("/admin/logout", "anon");
	    filterChainDefinitionMap.put("/error", "anon");
	    //3.拦截的请求（从本地数据库获取或者从casserver获取(webservice,http等远程方式)，看你的角色权限配置在哪里）
	    filterChainDefinitionMap.put("/user", "authc"); //需要登录
	    filterChainDefinitionMap.put("/user/add/**", "authc,roles[admin]"); //需要登录，且用户角色为admin
	    filterChainDefinitionMap.put("/user/delete/**", "authc,perms[\"user:delete\"]"); //需要登录，且用户有权限为user:delete
	
	    //4.登录过的不拦截
	    filterChainDefinitionMap.put("/**", "user");*/
	
	    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}
	
	/****************************************************************************************************/
	
	/**
	 * 自定义在线用户处理过滤器
	 */
	@Bean
	public OnlineSessionFilter onlineSessionFilter() {
		OnlineSessionFilter onlineSessionFilter = new OnlineSessionFilter();
		onlineSessionFilter.setLoginUrl(loginUrl);
		return onlineSessionFilter;
	}

	/**
	 * 自定义在线用户同步过滤器
	 */
	@Bean
	public SyncOnlineSessionFilter syncOnlineSessionFilter() {
		SyncOnlineSessionFilter syncOnlineSessionFilter = new SyncOnlineSessionFilter();
		return syncOnlineSessionFilter;
	}
	
	  /**
     * 自定义验证码过滤器
     */
    @Bean
    public CaptchaValidateFilter captchaValidateFilter()
    {
        CaptchaValidateFilter captchaValidateFilter = new CaptchaValidateFilter();
        captchaValidateFilter.setCaptchaEnabled(captchaEnabled);
        captchaValidateFilter.setCaptchaType(captchaType);
        return captchaValidateFilter;
    }
    
    /**
	 * thymeleaf模板引擎和shiro框架的整合
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
	
	/**
	 * 开启Shiro注解通知器
	 */
	/*@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}*/

}
