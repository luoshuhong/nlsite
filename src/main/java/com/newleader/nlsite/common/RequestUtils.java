package com.newleader.nlsite.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 处理相关的公共操作
 * @author Luoshuhong
 * @Company  
 * 2015年8月29日
 *
 */
public class RequestUtils {
	
	public static String getValue(HttpServletRequest request, String key, String defValue) {
		String value = request.getParameter(key);
		return null == value ? value : defValue;
	}
	
	/**
	 * 组装返回值
	 * {
     *   status:xxx,  //success：成功  fail:失败 
     *  msg:xxx      //说明 status=success时错误原因   
   	 *	}
	 * @return
	 */
	public static String successReturn(String data) {
		JSONObject job = new JSONObject();
		job.put("status", "success");
		job.put("msg", "success");
		if (!StringUtils.isEmpty(data)) {
			job.put("data", data);
		}
		return job.toJSONString();
	}
	
	public static String failReturn(String errMsg) {
		JSONObject job = new JSONObject();
		job.put("status", "fail");
		job.put("msg", errMsg);
		return job.toJSONString();
	}
	
//	public static Map<String,Object> getReqParam(HttpServletRequest request) {
//		request.getParameterMap();
//		return null;
//	}
}
