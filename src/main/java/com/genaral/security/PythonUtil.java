package com.genaral.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PythonUtil {
    public static Logger log = LoggerFactory.getLogger(PythonUtil.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        String key = "hahaIosCytfinanceEncrytp";
        String iv = "19283126";
        String data = "{\\\"open_id\\\":\\\"33b70b28196ee04deda86d213829ad9c\\\",\\\"user_id\\\":\\\"15652313063\\\",\\\"code\\\":\\\"50952\\\",\\\"hrefer\\\":\\\"0\\\",\\\"access_token\\\":\\\"95aa6c1d00122d89ecbf9fbece53e171\\\"}";
        String endata = null;
        String dedata = null;
        for (int i = 0; i < 10000; i++) {
            long t0 = System.currentTimeMillis();
            endata = encrypt(key, iv, data);
            System.out.println(t0 - System.currentTimeMillis() + ":" + endata);
            t0 = System.currentTimeMillis();
            dedata = decrypt(key, iv, endata);
            System.out.println(t0 - System.currentTimeMillis() + ":" + dedata);
        }
    }


    public static String encrypt(String path, String key, String iv, String data) {
        try {
            //就是没法调用python程序，而如果是在命令行下用javac编译，然后java执行的话肯定是对的。怎么才能在Eclipse里也能正常运行了，网上查了半天，在run configurations->environment新建一个PATH，值设为安装的python的路径，再运行就OK了。
            //环境变量添加
            log.info("开始解密:" + path + "," + key + "," + iv + "," + data);
            Process pr = Runtime.getRuntime().exec("python " + path + " " + key + " " + iv + " " + data);
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
//            	System.out.println(line);
                sb.append(line);
            }
            in.close();
            pr.waitFor();
            return sb.toString();
        } catch (Exception e) {
            log.info("加密出错:" + path + "," + key + "," + iv + "," + data + "," + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String key, String iv, String data) {
        return encrypt("f:\\pyDes-2.0.1\\encrypt.py", key, iv, data);
    }

    public static String decrypt(String key, String iv, String data) {
        return decrypt("f:\\pyDes-2.0.1\\decrypt.py", key, iv, data);
    }

    public static String decrypt(String path, String key, String iv, String data) {
        try {
            log.info("开始解密:" + path + "," + key + "," + iv + "," + data);
            //就是没法调用python程序，而如果是在命令行下用javac编译，然后java执行的话肯定是对的。怎么才能在Eclipse里也能正常运行了，网上查了半天，在run configurations->environment新建一个PATH，值设为安装的python的路径，再运行就OK了。
            //环境变量添加
            Process pr = Runtime.getRuntime().exec("python " + path + " " + key + " " + iv + " " + data);
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
            pr.waitFor();
            return sb.toString();
        } catch (Exception e) {
            log.info("解密出错:" + path + "," + key + "," + iv + "," + data + "," + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
