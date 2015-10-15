package com.newleader.nlsite.common.thread;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.newleader.nlsite.admin.model.Channel;
import com.newleader.nlsite.admin.service.ChannelService;
import com.newleader.nlsite.admin.service.ChannelStatService;
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
		ChannelService channelService = SpringContextUtil.getBean("channelService");
		ChannelStatService channelStatService = SpringContextUtil.getBean("channelStatService");
		
		while (true) {
			try {
				Thread.sleep(syncTimeInterval * ONE_SECOND_MS);
				
				List<Channel> list = channelService.query();  //获取所有渠道
				for (Channel channel : list) {
					int totalSubscribe = channelStatService.getSubscribeByChannel(channel.getCode());	   //查询历史关注量
					int unSubscribe = channelStatService.getUnSubscribeByChannel(channel.getCode());  //查询取消关注量
					if (0 == totalSubscribe) {
						continue;
					}
					
					channelService.updateByCode(totalSubscribe, unSubscribe, channel.getCode());      //更新
				}
				
				channelStatService.updateCreateDate();    //更新createDate字段为"YYYY-MM-DD"格式 便于统计
			} catch (InterruptedException e) {
				log.info("error!  errMsg=" + e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
	
}
