package com.newleader.nlsite.common;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

/** 
 * session 统一操作工具类
 * @author luoshuhong
 * @Company 
 * date 2015-9-16
 * 
 */
public class SessionUtils {
	//所有key都在这里定义
	public static final String LONIG_FLAG = "login_flag";    //登陆标识
    
	//存放所有session中的key
	private static  Set<String> sessionKey = new HashSet<String>();
	static {
		sessionKey.add(LONIG_FLAG);
	}
	
	/**
	 * 添加session值  
	 * @param request HttpServletRequest
	 * @param key	  key
	 * @param value   value
	 */
	public static void setSession(HttpServletRequest request, String key, Object value) {
		if (null == request || StringUtils.isEmpty(key) || !validateKey(key)) {
			return;
		}
		request.getSession().setAttribute(key, value);
	}
	
	/**
	 * 添加session值  
	 * @param session HttpSession
	 * @param key	  key
	 * @param value   value
	 */
	public static void setSession(HttpSession session, String key, Object value) {
		if (null == session || StringUtils.isEmpty(key) || !validateKey(key)) {
			return;
		}
		session.setAttribute(key, value);
	}
	
	/**
	 * 获取session值
	 * @param session HttpSession
	 * @param key     key
	 * @return Object
	 */
	public static Object getSession(HttpSession session, String key) {
		if (null == session || StringUtils.isEmpty(key) || !validateKey(key)) {
			return null;
		}
		return session.getAttribute(key);
	}
	
	/**
	 * 获取session值
	 * @param request HttpServletRequest
	 * @param key     key
	 * @return Object
	 */
	public static Object getSession(HttpServletRequest request, String key) {
		if (null == request || StringUtils.isEmpty(key) || !validateKey(key)) {
			return null;
		}
		return request.getSession().getAttribute(key);
	}
	
	/**
	 * 移除sesson
	 * @param request HttpServletRequest
	 * @param key     key
	 *  
	 */
	public static void removeSession(HttpServletRequest request, String key) {
		if (null == request || StringUtils.isEmpty(key) || !validateKey(key)) {
			return ;
		}
		request.getSession().removeAttribute(key);
	}
	
	/**
	 * 移除sesson
	 * @param session HttpSession
	 * @param key     key
	 *  
	 */
	public static void removeSession(HttpSession session, String key) {
		if (null == session || StringUtils.isEmpty(key) || !validateKey(key)) {
			return ;
		}
		session.removeAttribute(key);
	}
	
	/**
	 * 校验key是否合法
	 * @param key
	 * @return
	 */
	private static boolean validateKey(String key) {
		return sessionKey.contains(key);
	}
}
