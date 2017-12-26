package com.genaral.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XssFilter implements Filter {
	protected Logger log = LoggerFactory.getLogger(getClass());
	FilterConfig filterConfig = null; 
	
	@Override
    public void destroy() {  
        this.filterConfig = null;  
    }  
  
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
    	String path = ((HttpServletRequest) request).getContextPath(); 
        String basePath = request.getScheme() + "://" + request.getServerName()  
                + ":" + request.getServerPort() + path + "/";  
  
        // HTTP 头设置 Referer过滤  
        String referer = ((HttpServletRequest) request).getHeader("Referer"); // REFRESH  
        if (referer != null && referer.indexOf(basePath) < 0) {  
            ((HttpServletRequest) request).getRequestDispatcher(  
                    ((HttpServletRequest) request).getRequestURI()).forward(  
                    ((HttpServletRequest) request), response);  
            log.info("referer不为空，referer >>>>>>>>>>>>>> " + referer);  
        }  
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);  
        log.info("msg test>>>>>>>>>>>>>> " + xssRequest.getParameter("msg"));  
        chain.doFilter(xssRequest, response);  
    }  
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {  
        this.filterConfig = filterConfig;  
    }

	public String description() {
		// TODO Auto-generated method stub
		return null;
	}
}
