package com.genaral.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 3DES加密工具类(支持3des加密模式ECB、CBC)
 * @author rsh 2016-04-20
 */
public class Des3 {

	//默认编码
	private final static String DEFAULT_CHARSET = "UTF-8";
	//加密算法
	private final static String ALGORITHM = "DESede";
	//填充方式
	private final static String PADDING = "PKCS5Padding";
    
    /**
     * ECB加密,不要IV
     * @param key 密钥
     * @param data 明文
     * @return 密文
     * @throws Exception
     */
    public static String des3EncodeECB(String key, String data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key.getBytes(DEFAULT_CHARSET));
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(ALGORITHM);
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(ALGORITHM +"/ECB/"+ PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        return new BASE64Encoder().encode(cipher.doFinal(data.getBytes(DEFAULT_CHARSET)));
    }
    
    /**
     * ECB解密,不要IV
     * @param keyStr 密钥
     * @param data 密文
     * @return 明文
     * @throws Exception
     */
    public static String des3DecodeECB(String keyStr, String data)
            throws Exception {
        return des3DecodeECB(keyStr, new BASE64Decoder().decodeBuffer(data));
    }
    
    /**
     * ECB解密,不要IV
     * @param key 密钥
     * @param data 密文
     * @return 明文
     * @throws Exception
     */
    public static String des3DecodeECB(String key, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key.getBytes(DEFAULT_CHARSET));
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(ALGORITHM);
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(ALGORITHM + "/ECB/"+ PADDING);
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return new String(bOut, DEFAULT_CHARSET);
    }
    
    
    /**
     * CBC加密
     * @param keyStr 密钥
     * @param keyivStr IV
     * @param data 明文
     * @return 密文
     * @throws Exception
     */
    public static String des3EncodeCBC(String keyStr, String keyivStr, String data)
            throws Exception {
        return des3EncodeCBC(keyStr.getBytes(DEFAULT_CHARSET),
        				keyivStr, 
        				data);
    }
    
    /**
     * CBC加密
     * @param keyStr 密钥
     * @param keyivStr IV
     * @param data 明文
     * @return 密文
     * @throws Exception
     */
    public static String des3EncodeCBC(byte[] key, String keyivStr, String data)
            throws Exception {
        return des3EncodeCBC(key,
        				keyivStr.getBytes(DEFAULT_CHARSET), 
        				data.getBytes(DEFAULT_CHARSET));
    }
    
    /**
     * CBC加密
     * @param key 密钥
     * @param keyiv IV
     * @param data 明文
     * @return 密文
     * @throws Exception
     */
    public static String des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(ALGORITHM);
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(ALGORITHM+ "/CBC/"+ PADDING);
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(data);
        return new BASE64Encoder().encode(bOut);
    }
    
    /**
     * CBC加密,通过调用pythod脚步加密（为了和php 3des加密相同使用）
     * @param pyfilePath py脚步文件路径
     * @param keyStr 密钥
     * @param ivStr IV
     * @param data 原文
     * @return 密文
     * @throws Exception
     */
    public static String des3EncodeCBCByPy(String pyfilePath, String keyStr, String ivStr, String data)
            throws Exception {
        return PythonUtil.encrypt(pyfilePath, keyStr, ivStr, data);
    }
    
    
    /**
     * CBC解密
     * @param keyStr 密钥
     * @param keyivStr IV
     * @param data 密文
     * @return 明文
     * @throws Exception
     */
    public static String des3DecodeCBC(String keyStr, String keyivStr, String data)
            throws Exception {
        return des3DecodeCBC(keyStr, keyivStr, new BASE64Decoder().decodeBuffer(data));
    }
    
    
    /**
     * CBC解密
     * @param keyStr 密钥
     * @param keyivStr IV
     * @param data 密文BASE64解码字节数组
     * @return 明文
     * @throws Exception
     */
    public static String des3DecodeCBC(String keyStr, String keyivStr, byte[] data)
            throws Exception {
        return des3DecodeCBC(keyStr.getBytes(DEFAULT_CHARSET), 
        				keyivStr.getBytes(DEFAULT_CHARSET),
        				data);
    }
    
    /**
     * CBC解密
     * @param key 密钥
     * @param keyiv IV
     * @param data 密文BASE64解码字节数组
     * @return 明文
     * @throws Exception
     */
    public static String des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(ALGORITHM);
        deskey = keyfactory.generateSecret(spec);
        
        Cipher cipher = Cipher.getInstance(ALGORITHM+ "/CBC/"+ PADDING);
        IvParameterSpec ips = new IvParameterSpec(keyiv);
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        return new String(cipher.doFinal(data), DEFAULT_CHARSET);
    }
    
    /**
     * CBC解密,通过调用pythod脚步解密（为了和php 3des解密相同使用）
     * @param pyfilePath py脚步路径
     * @param keyStr 密钥
     * @param keyivStr IV
     * @param data 密文
     * @return 明文
     * @throws Exception
     */
    public static String des3DecodeCBCByPy(String pyfilePath, String keyStr, String ivStr, String data)
            throws Exception {
        return PythonUtil.decrypt(pyfilePath, keyStr, ivStr, data);
    }
    
    /**
    * 去掉加密字符串换行符
    * @param str
    * @return
    */
    public static String filter(String str) {
    	if(str==null) return null;
    	String output = "";
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < str.length(); i++) {
    		int asc = str.charAt(i);
			if (asc != 10 && asc != 13) {
				sb.append(str.subSequence(i, i+1));
			}
    	}
    	output = new String(sb);
    	return output;
    }
    
    
    public static void main(String[] args) throws Exception {
        String keyStr = "CfthOpenApi@haha#Encrytp";
        String keyiv = "19283127";
        String data = "测试3descbc加密";
        
        String str5 = des3EncodeCBC(keyStr, keyiv, data);
        System.out.println("密文="+str5);
        String desData = java.net.URLEncoder.encode(str5, "utf-8");
        System.out.println("urlEncode编码="+desData);
        String str6 = des3DecodeCBC(keyStr, keyiv, str5);
        System.out.println("明文="+str6);
        str6 = des3DecodeCBC(keyStr, keyiv, new BASE64Decoder().decodeBuffer(""));
        System.out.println("明文="+str6);
        
        
    }
    
}
