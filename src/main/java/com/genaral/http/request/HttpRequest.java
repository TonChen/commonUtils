package com.genaral.http.request;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * http请求工具类
 * @author rsh 2015-09-30
 * @version 1.0
 */
public class HttpRequest {

	protected static Logger log = LoggerFactory.getLogger(HttpRequest.class);
	
	/**
	 * http POST请求
	 * @param url 请求地址
	 * @param param 请求参数
	 * @return 
	 * 返回数据均包含error、msg、data<br>
	 * error：int状态码0成功1失败、msg：错误信息、data为返回数据
	 * @throws Exception
	 */
	public static JSONObject postRequest(String url, String param) throws Exception{
		JSONObject jsonRet=null;
		try {
			if(url==null || url.equals(""))
				return new JSONObject("{\"error\":1,\"msg\":\"url is null\"}");
		
			URL u = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod("POST");
			connection.setReadTimeout(10000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			if(param != null){
				connection.getOutputStream().write(param.getBytes("UTF-8"));
				connection.getOutputStream().flush();
				connection.getOutputStream().close();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String temp;
			StringBuffer ret = new StringBuffer("");
			while((temp = br.readLine()) != null){
				ret.append(temp);
			}
			br.close();
			//断开连接  
			connection.disconnect();
			log.info("ret:\n"+ret.toString());
		
			jsonRet = new JSONObject(ret.toString());
			
		} catch(java.net.SocketTimeoutException e) {
			jsonRet = new JSONObject("{\"error\":1,\"msg\":\"post request timeout\"}");
			log.error("http POST请求异常：", e);
		} catch (Exception e) {
			jsonRet = new JSONObject("{\"error\":1,\"msg\":\"post request error\"}");
			log.error("http POST请求异常：", e);
		}
		return jsonRet;
	}
	
	
	
	/**
	 * http GET请求
	 * @param url 请求地址
	 * @param param 请求参数
	 * @return 
	 * 返回数据均包含error、msg、data<br>
	 * error：int状态码0成功1失败、msg：错误信息、data为返回数据
	 * @throws Exception
	 */
	public static JSONObject getRequest(String url, String param) throws Exception{
		JSONObject jsonRet=null;
		try {
			if(url==null || url.equals(""))
				return new JSONObject("{\"error\":1,\"msg\":\"url is null\"}");
		
			if(param != null && param.equals(""))
				url += URLEncoder.encode(param, "UTF-8");
			URL u = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(10000);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String temp;
			StringBuffer ret = new StringBuffer("");
			while((temp = br.readLine()) != null){
				ret.append(temp);
			}
			br.close();
			//断开连接  
			connection.disconnect();
			log.info("ret:\n"+ret.toString());
		
			jsonRet = new JSONObject(ret.toString());
			
		} catch(java.net.SocketTimeoutException e) {
			jsonRet = new JSONObject("{\"error\":1,\"msg\":\"get request timeout\"}");
			log.error("http GET请求异常：", e);
		} catch (Exception e) {
			jsonRet = new JSONObject("{\"error\":1,\"msg\":\"get request error\"}");
			log.error("http GET请求异常：", e);
		}
		return jsonRet;
	}
}
