package com.newleader.nlsite.common;

import com.newleader.nlsite.common.thread.ShareMsgPopThread;
import com.newleader.nlsite.common.thread.ShareVisitPopThread;


/**
 * 做一些初始化工作
 * @author somebody
 * @Company zhe800.com
 * 2015年4月29日
 *
 */
public class InitUtils {
	
	public void start(){
		new ShareMsgPopThread().start();  //分享消息
//		new ShareVisitPopThread().start();  //分享页访问消息
		
	}
}
