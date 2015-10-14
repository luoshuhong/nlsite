package com.newleader.nlsite.common;

import com.newleader.nlsite.common.thread.ShareMsgPopThread;
import com.newleader.nlsite.common.thread.ShareVisitPopThread;
import com.newleader.nlsite.common.thread.StatChannelSubscribeThread;


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
		new StatChannelSubscribeThread().start();
		new ShareVisitPopThread().start();  //分享页访问消息
		
	}
}
