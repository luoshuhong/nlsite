package com.newleader.nlsite.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.newleader.nlsite.common.SessionUtils;

/**
 * <p>Description: 登录过滤器</p>
 * <p>Company:  </p>
 * @author luoshuhong
 * @date 2015-9-16 
 * @version 1.0
 */
public class LoginFilter implements Filter {
    
    private String[] excludeUrls ; 

    @Override
    public void destroy() {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String isLogin = (String) SessionUtils.getSession(httpRequest, SessionUtils.LONIG_FLAG);
        // 过滤URL   
        if(isExclusive(httpRequest.getRequestURL().toString())) {
            chain.doFilter(request, response);
            return;
        }
        // 没有登录，跳到 登录页
        if (StringUtils.isEmpty(isLogin) || !"true".equals(isLogin)) {
            httpResponse.sendRedirect(httpRequest.getContextPath()+ "/admin/login.jsp");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludeUrls = new String[] { "/login.jsp", "/login"};
    }
    
    /**
     * <p>Title: isExclusive</p>
     * <p>Description: isExclusive</p>
     * @param url url
     * @return boolean
     */
    public boolean isExclusive(String url){
    	
    	//这里只拦截/admin下的请求
    	if (url.contains("/admin") && !url.contains("/admin/login") && !url.contains("/admin/test.jsp") && !url.contains("/admin/channel/queryEffect")) {   
    		return false;
    	} else {
    		return true;
    	}
    	
//        for(String exurl:excludeUrls){
//            if (exurl.equals(url) || url.contains(exurl)) {
//                return true;
//            }
//        }
//        return false;
    }

}
