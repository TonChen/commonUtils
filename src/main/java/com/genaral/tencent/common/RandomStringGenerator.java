package com.genaral.tencent.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 14:18
 */
public class RandomStringGenerator {

    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
    	List<String> strList = new ArrayList<String>();
    	int i=0;
    	while(true) {
    		String s = getRandomStringByLength(12);
    		if(strList.contains(s)) {
    			System.out.println("出现重复"+i++);
    			break;
    		}
    		strList.add(s);
    		i++;
//    		System.out.println("====="+i);
//    		System.out.println("====="+s);
    	}
	}
}
