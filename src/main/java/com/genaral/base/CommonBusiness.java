package com.genaral.base;

import com.model.JsonBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaokeheng
 */
public interface CommonBusiness{
	/**
	 * 由渠道启动处理入口点
	 * @param req 传入数据
	 * @param res 传入数据
	 * @return
	 */
	public Object service(HttpServletRequest req, HttpServletResponse res);
	
	public JsonBean service(String bcode, String phone);
	
	public JsonBean checkValidCode(String bcode, String phone, String checkCode);
}
