package com.genaral.http;

import com.genaral.date.DateConvertUtils;
import com.genaral.security.SHA1;
import com.model.JsonBean;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsynClient {
    public static JsonBean asynPost(String url, Map<String, String> map, String secret, String from) {
        String reqId = UUID.randomUUID().toString().replaceAll("-","");
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();// 默认的配置
        try {
            httpclient.start();
            map.put("From", from);
            String data = sort(map);
            String timestamp = String.valueOf(DateConvertUtils.getDateByDateTime(new Date()));
            String sign = SHA1.encrypt(data + secret + timestamp, "UTF-8").toUpperCase();
            map.put("sign", sign);
            map.put("timestamp", timestamp);
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            if (map != null) {
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    NameValuePair nvp = new BasicNameValuePair(key, value);
                    formParams.add(nvp);
                }
            }
            HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
            HttpPost request = new HttpPost(url);
            request.setHeader("Accept", "application/json");
            request.setEntity(entity);
            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();// 获取结果
            JsonBean res = null;
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();
                String html = null;
                if (httpEntity != null) {
                    html = EntityUtils.toString(httpEntity);
                }
                res = new JsonBean("0", "操作成功", null, html);
            } else {
                res = new JsonBean("1", response.getStatusLine().getStatusCode() + "=操作失败", response.getStatusLine().getReasonPhrase(), null);
            }
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
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }






    /**
     * 排序
     * @param map
     * @return
     */
    public static String sort(Map<String,String> map){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getValue()!=null && !entry.getValue().equals("")){
                list.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            if(i == 0) {
                sb.append(arrayToSort[i]);
            } else {
                sb.append("&" + arrayToSort[i]);
            }
        }
        return sb.toString();
    }
}
