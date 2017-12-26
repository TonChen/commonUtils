package com.genaral.aliyun.push;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 阿里云移动推送服务请求客户端
 * Created by rsh on 16/12/28.
 */
public class AliyunAcsClient implements InitializingBean, DisposableBean {
    protected Logger log = LoggerFactory.getLogger(AliyunAcsClient.class);

    protected String accessKeyId;
    protected String accessKeySecret;
    protected long aliyunPushAppKey;
    protected String aliyunPushIosEnv;
    protected String aliyunPushRegionId;
    protected DefaultAcsClient client;

    @Override
    public void afterPropertiesSet() throws Exception {
        IClientProfile profile = DefaultProfile.getProfile(aliyunPushRegionId, accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(profile);
        log.info("========初始化阿里云移动推送服务客户端成功========");
    }

    @Override
    public void destroy() throws Exception {

    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public long getAliyunPushAppKey() {
        return aliyunPushAppKey;
    }

    public void setAliyunPushAppKey(long aliyunPushAppKey) {
        this.aliyunPushAppKey = aliyunPushAppKey;
    }

    public String getAliyunPushIosEnv() {
        return aliyunPushIosEnv;
    }

    public void setAliyunPushIosEnv(String aliyunPushIosEnv) {
        this.aliyunPushIosEnv = aliyunPushIosEnv;
    }

    public String getAliyunPushRegionId() {
        return aliyunPushRegionId;
    }

    public void setAliyunPushRegionId(String aliyunPushRegionId) {
        this.aliyunPushRegionId = aliyunPushRegionId;
    }

    public DefaultAcsClient getClient() {
        return client;
    }

    public void setClient(DefaultAcsClient client) {
        this.client = client;
    }
}
