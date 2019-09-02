package com.wms.common.utils.cas;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 
 * <p>Title: CasClientUtil</p>  
 * <p>Description: </p>  
 * @author yupu.chnet
 * @date 2019年8月15日
 * @version V1.0.0
 */
public final class CasClientUtil {
	private static final Logger LOG = Logger.getLogger(CasClientUtil.class.getName());

	private CasClientUtil() {
		
	}
	
	/**
	 * 
	 * <p>Title: getTicket</p>  
	 * <p>Description: 获取ST</p>  
	 * @author yupu.chnet
	 * @date 2019年8月15日
	 * @param server
	 * @param username
	 * @param password
	 * @param service
	 * @return
	 */
	public static String getTicket(final String server, final String username, final String password,
			final String service) {
		notNull(service, "service must not be null");
		return getServiceTicket(server, getTicketGrantingTicket(server, username, password), service);
	}

	/**
	 * 
	 * <p>Title: getServiceTicket</p>  
	 * <p>Description: 根据TGT生成ST</p>  
	 * @author yupu.chnet
	 * @date 2019年8月15日
	 * @param server
	 * @param ticketGrantingTicket
	 * @param service
	 * @return
	 */
	public static String getServiceTicket(final String server, final String ticketGrantingTicket,
			final String service) {
		if (ticketGrantingTicket == null)
			return null;
		final HttpClient client = new HttpClient();
		final PostMethod post = new PostMethod(server + "/" + ticketGrantingTicket);
		post.setRequestBody(new NameValuePair[] { new NameValuePair("service", service) });
		try {
			client.executeMethod(post);
			final String response = post.getResponseBodyAsString();
			switch (post.getStatusCode()) {
			case 200:
				return response;
			default:
				LOG.warning("Invalid response code (" + post.getStatusCode() + ") from CAS server!");
				LOG.info("Response (1k): " + response.substring(0, Math.min(1024, response.length())));
				break;
			}
		}
		catch (final IOException e) {
			LOG.warning(e.getMessage());
		}
		finally {
			post.releaseConnection();
		}
		return null;
	}
	
	/**
	 * 
	 * <p>Title: getTicketGrantingTicket</p>  
	 * <p>Description: 获取TGT</p>  
	 * @author yupu.chnet
	 * @date 2019年8月15日
	 * @param server
	 * @param username
	 * @param password
	 * @return
	 */
	public static String getTicketGrantingTicket(final String server, final String username, final String password) {
		notNull(server, "server must not be null");
		notNull(username, "username must not be null");
		notNull(password, "password must not be null");
		final HttpClient client = new HttpClient();
		final PostMethod post = new PostMethod(server);
		post.setRequestBody(new NameValuePair[] { new NameValuePair("username", username),
				new NameValuePair("password", password) });
		try {
			client.executeMethod(post);
			final String response = post.getResponseBodyAsString();
			switch (post.getStatusCode()) {
			case 201: {
				final Matcher matcher = Pattern.compile(".*action=\".*/(.*?)\".*").matcher(response);
				if (matcher.matches())
					return matcher.group(1);
				LOG.warning("Successful ticket granting request, but no ticket found!");
				LOG.info("Response (1k): " + response.substring(0, Math.min(1024, response.length())));
				break;
			}
			default:
				LOG.warning("Invalid response code (" + post.getStatusCode() + ") from CAS server!");
				LOG.info("Response (1k): " + response.substring(0, Math.min(1024, response.length())));
				break;
			}
		}
		catch (final IOException e) {
			LOG.warning(e.getMessage());
		}
		finally {
			post.releaseConnection();
		}
		return null;
	}

	/**
	 * 
	 * <p>Title: notNull</p>  
	 * <p>Description: 校验参数</p>  
	 * @author yupu.chnet
	 * @date 2019年8月15日
	 * @param object
	 * @param message
	 */
	private static void notNull(final Object object, final String message) {
		if (object == null)
			throw new IllegalArgumentException(message);
	}

	/*
	public static void main(final String[] args) {
//		final String server = "http://192.168.41.107:8080/member/v1/tickets";
//		final String server = "http://10.128.52.47:6662/CAS-JBT/v1/tickets";
		final String server = "";
		final String username = "";
		final String password = "445ba578dc7b669d193de664925d1223";
		final String service = "http://localhost:8180/WMS-JBT-YP";
		LOG.info(getTicket(server, username, password, service));
	}
	*/
}