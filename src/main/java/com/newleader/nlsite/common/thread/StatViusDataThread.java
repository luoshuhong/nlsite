package com.newleader.nlsite.common.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.newleader.nlsite.admin.service.ChannelService;
import com.newleader.nlsite.admin.service.ChannelStatService;
import com.newleader.nlsite.common.SpringContextUtil;

/**
 *   virus统计（1.关注用户所属virus渠道  2.分享页层级关系）
 * @author Luoshuhong
 * @Company  
 * 2015年10月13日
 *
 */
public class StatViusDataThread extends Thread {
	private static Log log = LogFactory.getLog(StatViusDataThread.class);
	private static final int ONE_SECOND_MS = 1000;  //一毫秒
	private int syncTimeInterval = 60;  			//同步时间间隔  默认2秒
	
	@Override
	public void run() {
		log.info("StatChannelSubscribeThread is running…………");
		ChannelService channelService = SpringContextUtil.getBean("channelService");
		ChannelStatService channelStatService = SpringContextUtil.getBean("channelStatService");
		
		while (true) {
			try {
				Thread.sleep(syncTimeInterval * ONE_SECOND_MS);
				
				//1.处理浏览过分享页的用户 是否已关注
				
				//2.
				
			} catch (Exception e) {
				log.info("error!  errMsg=" + e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
	
}
