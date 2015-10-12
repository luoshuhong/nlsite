package com.newleader.nlsite.common;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring 上下文 工具类
 * @author Luoshuhong
 * @Company zhe800.com
 * 2015年10月12日
 *
 */
public class SpringContextUtil implements ApplicationContextAware{
	
	private static ApplicationContext context = null;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName){
        return (T) context.getBean(beanName);
    }
	
	public static String getMessage(String key){
        return context.getMessage(key, null, Locale.getDefault());
    }

}
