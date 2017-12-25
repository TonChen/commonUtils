package com.genaral.data;

/**
 * Created by renfx on 2016/5/23.
 */
public class NullUtils {
    public static String handleNull(String n){
        return n==null?"":n;
    }
    public static Float handleNull(Float n){
        return n==null?0f:n;
    }
    public static Double handleNull(Double n){
        return n==null?0d:n;
    }
    public static Integer handleNull(Integer n){
        return n==null?0:n;
    }
    public static Long handleNull(Long n){
        return n==null?0:n;
    }
    public static Character handleNull(Character n){
        return n==null?"".charAt(0):n;
    }
	public static boolean handleNull(Boolean isCom) {
		if(isCom==null) return false;
		
		return isCom;
	}
}
