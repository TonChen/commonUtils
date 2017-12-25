package com.genaral.http;

import com.genaral.json.JsonUtil;
import com.genaral.sequence.IdWorker;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by renfx on 2016/7/11.
 */
public class HttpPostClient {
    static Log log = LogFactory.getLog(HttpPostClient.class);

    private String Url;

    // 初始化数据
    public HttpPostClient(String url) {
        Url = url;
    }

    public String sendData(String data) {
        String receivedData = null;
        try {

            Map<String, String> paramsData = new HashMap<String, String>();
            paramsData.put("data", data);
            receivedData = send(Url, paramsData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receivedData;
    }

    public static String send(String url, Map<String, String> paramsMap) {
        String result = null;
        PostMethod postMethod = null;
        try {
            HttpClient httpClient = new HttpClient();
            IdWorker idWorker = new IdWorker(0, 0);
//            		(IdWorker) SpringContextUtil.getBean("idWorker");
            long reqId = idWorker.nextId();
            httpClient.getParams().setParameter(
                    HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            postMethod = new PostMethod(url);
            log.info(reqId + ":" + url + ">data:" + JsonUtil.toJSon(paramsMap));
            if (paramsMap != null && paramsMap.size() > 0) {
                NameValuePair[] datas = new NameValuePair[paramsMap.size()];
                int index = 0;
                for (String key : paramsMap.keySet()) {
                    datas[index++] = new NameValuePair(key, paramsMap.get(key));
                }
                postMethod.setRequestBody(datas);

            }

            HttpClientParams httparams = new HttpClientParams();
            httparams.setSoTimeout(60000);
            postMethod.setParams(httparams);

            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                result = postMethod.getResponseBodyAsString();
                log.info("发送成功！");
            } else {
                result = String.valueOf(statusCode);
                log.error(" http response status is " + statusCode);
            }
            log.info(reqId + ":" + url + ">res:" + JsonUtil.toJSon(result));
        } catch (HttpException e) {
            log.error("error url=" + url, e);
        } catch (IOException e) {
            log.error("error url=" + url, e);
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
        }

        return result;
    }
}