package com.newleader.nlsite.common;

import java.util.Map;

/**
 * 公共工具类
 * @author Luoshuhong
 * @Company donottel.me
 * 2015年11月2日
 *
 */
public class CommonUtils {
	
	/**
	 * String 转换成 int
	 * @param str
	 * @return
	 */
	public static int praseStr2Int(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}
	
    /**
     * 获取map中 整形
     * @param map
     * @param key
     * @return
     */
	public static int getIntFromMap(Map<String,Object> map, String key) {
		if (null == map) {
			return 0;
		}
		try {
			if (map.containsKey(key) && null != map.get(key)) {
				return Integer.valueOf(map.get(key).toString());
			}
		} catch (Exception e) {
		}
		return 0;
	}
	
	 /**
     * 获取map中 String
     * @param map
     * @param key
     * @return
     */
	public static String getStrFromMap(Map<String,Object> map, String key) {
		if (null == map) {
			return "";
		}
		try {
			if (map.containsKey(key) && null != map.get(key)) {
				return map.get(key).toString();
			}
		} catch (Exception e) {
		}
		return "";
	}
}
