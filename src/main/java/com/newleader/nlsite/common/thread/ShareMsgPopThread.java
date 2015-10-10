package com.newleader.nlsite.common.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.newleader.nlsite.common.RedisUtils;

/**
 *   获取redis队列数据线程(页面分享信息)
 * @author Luoshuhong
 * @Company  
 * 2015年10月10日
 *
 */
public class ShareMsgPopThread extends Thread {
	private static Log log = LogFactory.getLog(ShareMsgPopThread.class);
	private static final int ONE_SECOND_MS = 1000;  //一毫秒
	private int syncTimeInterval = 2;  			//同步时间间隔  默认2秒
	
	@Override
	public void run() {
		log.info("thread is running…………");
		while (true) {
			try {
				// 休眠固定时间
				Thread.sleep(syncTimeInterval * ONE_SECOND_MS);
				// openId:分享者id  scene:场景
//				String shareMsg = RedisUtils.
				
			} catch (InterruptedException e) {
				log.info("error!  errMsg=" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
