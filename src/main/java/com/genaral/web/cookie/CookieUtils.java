package com.genaral.web.cookie;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class CookieUtils {
	
	@SuppressWarnings("unchecked")
    public static Map<String,Cookie> toMap(Cookie[] cookies){
    	if(cookies == null || cookies.length == 0)
    		return new HashMap(0);
    	
    	Map map = new HashMap(cookies.length * 2);
    	for(Cookie c : cookies) {
    		map.put(c.getName(), c);
    	}
    	return map;
    }

}
