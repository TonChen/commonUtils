package com.genaral.http;

import com.genaral.json.JsonUtil;
import com.model.JsonBean;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * http请求工具类-使用httpClient第三方工具类
 * @author rsh
 *
 */
public class HttpUtil {
	
	static Logger log = org.slf4j.LoggerFactory.getLogger(HttpUtil.class);
	
	/**
	 * get 请求
	 * @param url 请求地址
	 * @param paramMap 请求参数
	 * @return JsonBean 0：成功，2：失败，info：返回数据
	 * @throws Exception 
	 */
	public static JsonBean get(String url, Map<String, String> paramMap){
		if(url==null || "".equals(url))
			return null;
		
		StringBuffer param = new StringBuffer();
		if(paramMap!=null && paramMap.size()>0){
			boolean isfirst = true;
			for(Map.Entry<String, String> e : paramMap.entrySet()){
				if(!isfirst) param.append("&");
				else isfirst = false;
				String key = e.getKey();
				String value = e.getValue();
				param.append(key);
				param.append("=");
				param.append(value);
			}
		}
		return get(url, param.toString());
	}
	
	
	/**
	 * get 请求
	 * @param url 请求地址
	 * @param paramStr 请求参数
	 * @return JsonBean 0：成功，2：失败，info：返回数据
	 * @throws Exception 
	 */
	public static JsonBean get(String url, String paramStr){
		if(url==null || "".equals(url))
			return null;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url+"?"+paramStr);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			if(response!=null){
				JsonBean res = null;
				//成功
				if(response.getStatusLine().getStatusCode()==200){
					HttpEntity httpEntity = response.getEntity();
					String html = null;
				    if (httpEntity != null) {
				    	html = EntityUtils.toString(httpEntity);
				    }
				    res = new JsonBean("0", "操作成功", null, html);
				}else{
					res = new JsonBean("2", response.getStatusLine().getStatusCode()+"=请求失败", response.getStatusLine().getReasonPhrase(), null);
				}
				
				log.info("请求结果："+ JsonUtil.toJSon(res));
				return res;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				 if(httpClient!=null)
					 httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new JsonBean("2", "操作失败", "请求异常", "");
	}
	

	/**
	 * post 请求
	 * @param url 请求地址
	 * @param paramMap 请求参数
	 * @return JsonBean 0：成功，2：失败，info：返回数据
	 * @throws Exception 
	 */
	public static JsonBean post(String url, Map<String, String> paramMap){
		if(url==null || "".equals(url))
			return null;
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			if(paramMap!=null && paramMap.size()>0){
				for(Map.Entry<String, String> e : paramMap.entrySet()){
					String key = e.getKey();
					String value = e.getValue();
					NameValuePair nameValuePair = new BasicNameValuePair(key, value);
					formParams.add(nameValuePair);
				}
			}
			HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httpPost.setEntity(entity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			if(response!=null){
				JsonBean res = null;
				//成功
				if(response.getStatusLine().getStatusCode()==200){
					HttpEntity httpEntity = response.getEntity();
					String html = null;
				    if (httpEntity != null) {
				    	html = EntityUtils.toString(httpEntity);
				    }
				    res = new JsonBean("0", "操作成功", null, html);
				}else{
					res = new JsonBean("2", response.getStatusLine().getStatusCode()+"=请求失败", response.getStatusLine().getReasonPhrase(), null);
				}
				log.info("请求结果："+JsonUtil.toJSon(res));
				return res;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				 if(httpClient!=null)
					 httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new JsonBean("2", "操作失败", "请求异常", "");
	}
	
	
	/**
	 * 异步post请求
	 * @param url 请求地址
	 * @param paramMap 请求参数
	 * @return JsonBean 0：成功，2：失败，info：返回数据
	 * @throws Exception 
	 */
    public static JsonBean asynPost(String url, Map<String,String> paramMap) {
    	if(url==null || "".equals(url))
			return null;
    	CloseableHttpAsyncClient httpClient = null;
    	try {
    		httpClient = HttpAsyncClients.createDefault();// 默认的配置
    		httpClient.start();
    		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
    		if(paramMap!=null){
    			for(Map.Entry<String, String> e : paramMap.entrySet()){
					String key = e.getKey();
					String value = e.getValue();
					NameValuePair nameValuePair = new BasicNameValuePair(key, value);
					formParams.add(nameValuePair);
				}
    		}
    		HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");  
    		HttpPost request = new HttpPost(url);
    		request.setHeader("Accept", "application/json");
    		request.setEntity(entity);
    		Future<HttpResponse> future = httpClient.execute(request, null);
    		HttpResponse response = future.get();// 获取结果
    		JsonBean res = null;
    		if(response.getStatusLine().getStatusCode()==200){
    			HttpEntity httpEntity = response.getEntity();
			    String html = null;
			    if (httpEntity != null) {
			    	html = EntityUtils.toString(httpEntity);
			    }
			    res = new JsonBean("0", "操作成功", null, html);
    		}else{
			    res = new JsonBean("2", response.getStatusLine().getStatusCode()+"=操作失败", response.getStatusLine().getReasonPhrase(), null);
    		}
    		log.info("请求结果："+JsonUtil.toJSon(res));
    		return res;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		     try {
		    	 if(httpClient!=null)
		    		 httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return null;
    }

    
}
