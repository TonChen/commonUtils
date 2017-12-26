package com.genaral.wechat;

import java.security.MessageDigest;
import java.util.Arrays;

/***
 * 微信消息接口认证token摘要类
 *
 * 这个摘要类实现为单例，校验一个签名是否合法的例子如下
 * <pre>
 * WeixinMessageDigest wxDigest = WeixinMessageDigest.getInstance();
 * boolean bValid = wxDigest.validate(signature, timestamp, nonce);
 * </pre>
 *
 *
 * @author liguocai
 */
public final class WeixinMessageDigest {

    /**
     * 单例持有类
     *
     * @author liguocai
     */
    private static class SingletonHolder {
        static final WeixinMessageDigest INSTANCE = new WeixinMessageDigest();
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static WeixinMessageDigest getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private MessageDigest digest;

    private WeixinMessageDigest() {
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            throw new InternalError("init MessageDigest error:" + e.getMessage());
        }
    }


    /**
     * 将字节数组转换成16进制字符串
     *
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b) {
        StringBuilder sbDes = new StringBuilder();
        String tmp = null;
        for (int i = 0; i < b.length; i++) {
            tmp = (Integer.toHexString(b[i] & 0xFF));
            if (tmp.length() == 1) {
                sbDes.append("0");
            }
            sbDes.append(tmp);
        }
        return sbDes.toString();
    }

    private String encrypt(String strSrc) {
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        digest.update(bt);
        strDes = byte2hex(digest.digest());
        return strDes;
    }

    /**
     * 校验请求的签名是否合法
     * <p>
     * 加密/校验流程：
     * 1. 将token、timestamp、nonce三个参数进行字典序排序
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public boolean validate(String token, String signature, String timestamp, String nonce) {
        //1. 将token、timestamp、nonce三个参数进行字典序排序
        String[] arrTmp = {token, timestamp, nonce};
        Arrays.sort(arrTmp);
        StringBuffer sb = new StringBuffer();
        //2.将三个参数字符串拼接成一个字符串进行sha1加密
        for (int i = 0; i < arrTmp.length; i++) {
            sb.append(arrTmp[i]);
        }
        String expectedSignature = encrypt(sb.toString());
        //3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (expectedSignature.equals(signature)) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        ///open/WeiXin/10001/check.do?timestamp=1379054455&nonce=862291559053424&echostr=229155&signature=ee6eac146dc5a4b0ee78d52d347ca67e48fd6b93
        String signature = "ee6eac146dc5a4b0ee78d52d347ca67e48fd6b93";//加密需要验证的签名
        String timestamp = "1379054455";//时间戳
        String nonce = "862291559053424";//随机数

        WeixinMessageDigest wxDigest = WeixinMessageDigest.getInstance();
        boolean bValid = wxDigest.validate("hyjj_10001", signature, timestamp, nonce);
        if (bValid) {
            System.out.println("token 验证成功!");
        } else {
            System.out.println("token 验证失败!");
        }
    }

}
