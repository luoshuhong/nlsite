package com.newleader.nlsite.common.thread;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.newleader.nlsite.admin.model.Channel;
import com.newleader.nlsite.admin.service.ChannelService;
import com.newleader.nlsite.admin.service.ChannelStatService;
import com.newleader.nlsite.admin.service.LoveTestStatService;
import com.newleader.nlsite.admin.service.RecordShareService;
import com.newleader.nlsite.admin.service.RecordVirusService;
import com.newleader.nlsite.common.Constants;
import com.newleader.nlsite.common.SpringContextUtil;

/**
 *   统计渠道关注信息(每个渠道累计关注量/取消关注量)
 * @author Luoshuhong
 * @Company  
 * 2015年10月13日
 *
 */
public class StatChannelSubscribeThread extends Thread {
	private static Log log = LogFactory.getLog(StatChannelSubscribeThread.class);
	private int syncTimeInterval = 10;  			//同步时间间隔  
	
	@Override
	public void run() {
		log.info("StatChannelSubscribeThread is running…………");
		ChannelService channelService = SpringContextUtil.getBean("channelService");
		ChannelStatService channelStatService = SpringContextUtil.getBean("channelStatService");
		LoveTestStatService loveTestStatService = SpringContextUtil.getBean("loveTestStatService");
		RecordShareService rcordShareService = SpringContextUtil.getBean("recordShareService");
		RecordVirusService recordVirusService = SpringContextUtil.getBean("recordVirusService");
		
		while (true) {
			try {
				Thread.sleep(syncTimeInterval * Constants.ONE_SECOND_MS);
				
				loveTestStatService.updateChannelId();     //更新填充爱情测评者 渠道编码
				rcordShareService.updateChannelId();       //更新填充分享者 渠道编码
				
				List<Channel> list = channelService.query();  //获取所有渠道
				for (Channel channel : list) {
					int shareCount = rcordShareService.getShareCountByChannel(channel.getCode());   //分享总数
					int virualCount = recordVirusService.getVirualCountByChannel(channel.getCode());  //virual总数
					int totalSubscribe = channelStatService.getSubscribeByChannel(channel.getCode());	   //当前关注量
					int unSubscribe = channelStatService.getUnSubscribeByChannel(channel.getCode());  //查询取消关注量
					if (0 == totalSubscribe) {
						continue;
					}
					channelService.updateByCode(shareCount,virualCount,totalSubscribe, unSubscribe, channel.getCode());      //更新
				}
				
			} catch (Exception e) {
				log.info("error![StatChannelSubscribeThread]  errMsg=" + e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
	
}
