package com.wms.common.utils.cas;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ClassUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class HttpClientUtil {
	private static PoolingHttpClientConnectionManager cm;
    private static String EMPTY_STR = "";  
    
    private static void init() {  
        if (cm == null) {  
            cm = new PoolingHttpClientConnectionManager();  
            cm.setMaxTotal(50);// 整个连接池最大连接数  
            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2  
        }  
    }  
  
    /** 
     * 通过连接池获取HttpClient 
     *  
     * @return 
     */  
    private static CloseableHttpClient getHttpClient() {  
        init();  
        return HttpClients.custom().setConnectionManager(cm).build();  
    }  
  
    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {  
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();  
        for (Map.Entry<String, Object> param : params.entrySet()) {  
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));  
        }  
  
        return pairs;  
    }  
  
	public static String getUserTicket(String username, String password,String url) throws UnsupportedEncodingException {  
    	ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();  
		pairs.add(new BasicNameValuePair("username", username));  
		pairs.add(new BasicNameValuePair("password",password));  
		UrlEncodedFormEntity entity1 =new UrlEncodedFormEntity(pairs, "utf-8");
		entity1.setContentType("application/json");
    	HttpPost httpPost = new HttpPost(url);  
        httpPost.setEntity(entity1);  
        return getTicket(httpPost);  
    }
	
	public static String getSTTicket(String service, String url) throws UnsupportedEncodingException {  
    	ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();  
		pairs.add(new BasicNameValuePair("service", service));  
		UrlEncodedFormEntity entity1 =new UrlEncodedFormEntity(pairs, "utf-8");
		entity1.setContentType("application/json");
    	HttpPost httpPost = new HttpPost(url);  
        httpPost.setEntity(entity1);  
        return getTicket(httpPost);  
    }
	
	public static String validateTicket(String service, String stTicket, String url) throws UnsupportedEncodingException {  
    	ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();  
		pairs.add(new BasicNameValuePair("service", service));
		pairs.add(new BasicNameValuePair("ticket", stTicket));
		UrlEncodedFormEntity entity1 =new UrlEncodedFormEntity(pairs, "utf-8");
		entity1.setContentType("application/json");
    	HttpPost httpPost = new HttpPost(url);  
        httpPost.setEntity(entity1);  
        return getTicket(httpPost);  
    }
	/** 
     * 处理Http请求 
     *  
     * @param request 
     * @return 
     */  
    private static String getTicket(HttpRequestBase request) {  
        // CloseableHttpClient httpClient = HttpClients.createDefault();  
        CloseableHttpClient httpClient = getHttpClient(); 
        try {  
            CloseableHttpResponse response = httpClient.execute(request); 
//            Header[] headers=response.getAllHeaders();
            switch (response.getStatusLine().getStatusCode()) {
			case 201:
				String result = EntityUtils.toString(response.getEntity()); 
				Matcher matcher = Pattern.compile(".*action=\".*/(.*?)\".*").matcher(result);
				if (matcher.matches()) {
					String tgt = matcher.group(1);
//					user.setTgt(tgt);
//					user.setUserName(userId);
//					user.setFuncs(getUserMenuPrivForOtherPort(userId, platform));
//					user.setMsg("用户获取票据成功 ！");
//					user.setMsgCode("1");
					return tgt;
				}
				break;
			case 200:
				String result1 = EntityUtils.toString(response.getEntity()); 
				if (!result1.isEmpty()) {
					return result1;
				}
				break;
			default:
//				user.setMsg("用户获取票据失败 ！用户验证信息错误");
//				user.setMsgCode("2");
				break;
			}
            // response.getStatusLine().getStatusCode();  
//            HttpEntity entity = response.getEntity();  
//            if (entity != null) {  
//                // long len = entity.getContentLength();// -1 表示长度未知  
//                String result = EntityUtils.toString(entity);  
                response.close();  
//                // httpClient.close();  
//                return result;  
//            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
  
        }  
  
        return EMPTY_STR;  
    }
    
    /*
    public static void main (String args[]) {
    	try {
    		String tgt = getUserTicket("admin","445ba578dc7b669d193de664925d1223", "http://10.128.52.47:6662/CAS-JBT/v1/tickets");
//    		String tgt = getUserTicket("admin","445ba578dc7b669d193de664925d1223", "http://10.128.8.32:28080/CAS-JBT/v1/tickets");
			System.out.println("票据为:" + tgt);
//    		String tgt = "TGT-7-g6lIGt433bOdnzxbNbowUjCGEjDFGtFAzz1NkXfLbSrUv1TY4x-cas01.example.org";
			String st = getSTTicket("http://10.128.8.111:8180/WMS-JBT-YP", "http://10.128.52.47:6662/CAS-JBT/v1/tickets/"+tgt);
//			String st = getSTTicket("http://10.128.8.111:8180/WMS-JBT-YP", "http://10.128.8.32:28080/CAS-JBT/v1/tickets/"+tgt);
			System.out.println("临时票据为:" + st);
//			String validateResult = validateTicket("http://10.128.8.111:8180/WMS-JBT-YP", st, "http://10.128.52.47:6662/CAS-JBT/serviceValidate");
//			String validateResult = validateTicket("http://10.128.8.111:8180/WMS-JBT-YP", st, "http://10.128.8.32:28080/CAS-JBT/serviceValidate");
//			System.out.println(validateResult);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    */
}  