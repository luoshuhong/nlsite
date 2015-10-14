package com.newleader.nlsite.common.thread;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.newleader.nlsite.admin.model.Channel;
import com.newleader.nlsite.admin.service.ChannelService;
import com.newleader.nlsite.admin.service.ChannelStatService;
import com.newleader.nlsite.common.RedisUtils;
import com.newleader.nlsite.common.SpringContextUtil;

/**
 *   统计渠道关注信息
 * @author Luoshuhong
 * @Company  
 * 2015年10月13日
 *
 */
public class StatChannelSubscribeThread extends Thread {
	private static Log log = LogFactory.getLog(StatChannelSubscribeThread.class);
	private static final int ONE_SECOND_MS = 1000;  //一毫秒
	private int syncTimeInterval = 60;  			//同步时间间隔  默认2秒
	
	@Override
	public void run() {
		log.info("StatChannelSubscribeThread is running…………");
//		try {
//			Thread.sleep(syncTimeInterval * ONE_SECOND_MS);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		
		ChannelService channelService = SpringContextUtil.getBean("channelService");
		ChannelStatService channelStatService = SpringContextUtil.getBean("channelStatService");
		
		while (true) {
			try {
				List<Channel> list = channelService.query();
				for (Channel channel : list) {
					int totalSubscribe = channelStatService.getSubscribeByChannel(channel.getCode());
					int unSubscribe = channelStatService.getUnSubscribeByChannel(channel.getCode()); 
					channelService.updateByCode(totalSubscribe, unSubscribe, channel.getCode());
				}
				
				channelStatService.updateCreateDate(); //更新createDate字段 便于统计
				
				Thread.sleep(syncTimeInterval * ONE_SECOND_MS);
			} catch (InterruptedException e) {
				log.info("error!  errMsg=" + e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
	
}
