package com.newleader.nlsite.common;

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
}
