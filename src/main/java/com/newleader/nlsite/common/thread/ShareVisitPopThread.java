package com.newleader.nlsite.common.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *   获取redis队列数据线程（分享页面访问信息）
 * @author Luoshuhong
 * @Company  
 * 2015年10月10日
 *
 */
public class ShareVisitPopThread extends Thread {
	private static Log log = LogFactory.getLog(ShareVisitPopThread.class);
	private static final int ONE_SECOND_MS = 1000;  //一毫秒
	private int syncTimeInterval = 2;  			//同步时间间隔  默认2秒
	
	@Override
	public void run() {
		log.info("thread is running…………");
		while (true) {
			try {
				// 休眠固定时间
				Thread.sleep(syncTimeInterval * ONE_SECOND_MS);
				
				// sOpenId:分享者id  currOpenId:访问者id scene:场景
				
			} catch (InterruptedException e) {
				log.info("error!  errMsg=" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
