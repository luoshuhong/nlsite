package com.newleader.nlsite.common;

/**
 *  常量值
 * @author Luoshuhong
 * @Company  
 * 2015年10月10日
 *
 */
public class Constants {
	
	/**
	 * 普通常量
	 */
	public static final int ONE_SECOND_MS = 1000; 	  //一毫秒
	
	/**
	 *  Redis key
	 */
	public static final String REDIS_SHARE_MSG   = "sharemsg";       //分享消息
	public static final String REDIS_SHARE_BACK  = "shareback";      //浏览分享页消息
	public static final String REDIS_SUBSCRIBE      = "subscribemsg";//浏览分享页消息
	public static final String REDIS_ACTION_MSG  = "actionmsg";     //操作动作消息
	
	/**
	 * 产品
	 */
	public static final String PRODUCT_LOVE              = "love";              //固定:love(爱情测评), profession (职业测评)
	public static final String PRODUCT_PROFESSION  = "profession";    //职业测评
	
	/**
	 * 统计类型
	 */
	public static final String STAT_TYPE_UV = "uv";	
	public static final String STAT_TYPE_PV = "pv";		
	public static final String STAT_TYPE_UV_D = "uv-d";						      //日UV
	public static final String STAT_TYPE_UV_W = "uv-w";						      //周UV
	public static final String STAT_TYPE_UV_M = "uv-m";						      //月UV
	public static final String STAT_TYPE_PV_30MIN_D = "pv30min-d";      //按30分钟一个间隔算pv(不分产品)
	public static final String STAT_TYPE_PV_30MIN_W = "pv30min-w";      //周pv
	public static final String STAT_TYPE_PV_30MIN_M = "pv30min-m";      //月pv
	public static final String STAT_TYPE_LOGIN = "login";				  //登陆动作
	public static final String STAT_TYPE_DNA_START = "dna_start";   //
	public static final String STAT_TYPE_DNA_END = "dna_end";		  //
	
}
