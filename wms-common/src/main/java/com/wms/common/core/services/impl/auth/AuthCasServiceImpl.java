package com.wms.common.core.services.impl.auth;

import org.apache.commons.httpclient.HttpClient;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.wms.common.core.services.IAuthService;
import com.wms.common.enums.AuthTypeEnum;
import com.wms.common.utils.cas.CasClientUtil;
import com.wms.common.utils.cas.HttpClientUtil;

/**
 * Cas 验证票据是否有效
 * @author yechuankai.chnet
 *
 */
@CacheConfig(cacheManager = "authCacheMap" , cacheNames = "casAuth")//设置缓存对象
@Component
public class AuthCasServiceImpl implements IAuthService {

	private static final Logger log = LoggerFactory.getLogger(AuthCasServiceImpl.class);
	
	@Value("${cas.server}")
	private String casServerUrl;
	@Value("${cas.tickets}")
	private String ticketsUrl;
	@Value("${wms.url.service}")
	public String serviceUrl;
	
	
	@Override
	@Cacheable(key = "#token")
	public Boolean validateToken(String token) {
		TicketValidator ticketValidator = new Cas20ServiceTicketValidator(casServerUrl);
		try {
			//获取ST 
			String st = CasClientUtil.getServiceTicket(ticketsUrl, token, serviceUrl);
			//验证st有效性
			Assertion casAssertion = ticketValidator.validate(st, serviceUrl);
			AttributePrincipal casPrincipal = casAssertion.getPrincipal();
			String userId = casPrincipal.getName();
			if (log.isDebugEnabled())
				log.debug("cas tickets={},userId={} auth success.", token, userId);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@Override
	public String autoType() {
		return AuthTypeEnum.Cas.getCode();
	}

}
