package com.newleader.nlsite.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.newleader.nlsite.admin.model.VisitorChannel;
import com.newleader.nlsite.admin.service.VisitorChannelService;
import com.newleader.nlsite.common.Constants;
import com.newleader.nlsite.common.RedisUtils;
import com.newleader.nlsite.common.SpringContextUtil;

/**
 *   获取redis队列数据线程(关注消息)
 * @author Luoshuhong
 * @Company  
 * 2015年10月15日
 *
 */
public class SubscribeMsgPopThread extends Thread {
	private static Log log = LogFactory.getLog(SubscribeMsgPopThread.class);
	private static final int MAX_NUM = 20; 			      //最大线程数
	private int syncTimeInterval = 1;  			//同步时间间隔  默认2秒
	private static ExecutorService businessDealPool = Executors.newFixedThreadPool(MAX_NUM); ;  //主业务处理线程池
	
	@Override
	public void run() {
		log.info("SubscribeMsgPopThread is running…………");
		RedisUtils redisUtils = SpringContextUtil.getBean("redisUtils");
		
		if (null == redisUtils) {
			log.info("SubscribeMsgPopThread redisUtils is null…………");
			return;
		}
		
		while (true) {
			try {
				// 休眠固定时间
				Thread.sleep(syncTimeInterval * Constants.ONE_SECOND_MS);
				
				if (((ThreadPoolExecutor) businessDealPool).getActiveCount() >= MAX_NUM) {
					log.info("SubscribeMsgPopThread the thread pool is full .");
					continue;
				}
				
				//从redis中获取
				String subscribeMsg = redisUtils.lpop(Constants.REDIS_SUBSCRIBE);
				if (StringUtils.isEmpty(subscribeMsg)) {
					continue;
				}
				log.info("subscribeMsg=" + subscribeMsg);
				
				//放入线程池执行
				businessDealPool.execute(new SubscribeDealThread(subscribeMsg));
				
				//放在redis中 01用
				redisUtils.lpush(Constants.REDIS_SUBSCRIBE_LOVE, subscribeMsg);
			} catch (Exception e) {
				log.info("error![subscribeMsg]  errMsg=" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}

/**
 * 保存关注账号记录操作
 * @author luoshuhong
 *
 */
class SubscribeDealThread extends Thread {
	private static Log log = LogFactory.getLog(SubscribeMsgPopThread.class);
	String subscribeMsg;
	public SubscribeDealThread(String subscribeMsg) {
		this.subscribeMsg = subscribeMsg;
	}
	@Override
	public void run() {
		try {
			VisitorChannelService visitorChannelService = SpringContextUtil.getBean("visitorChannelService");
			// openId:关注者openid  channelId:渠道编码
			JSONObject job = JSONObject.parseObject(subscribeMsg);
			String openId = job.getString("openId");
			String channelId = job.getString("channelId");
			if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(channelId)) {
				return;
			}
			VisitorChannel model = new VisitorChannel(openId, channelId);
			log.info("subscribeMsg=" + subscribeMsg + ", result=" + visitorChannelService.add(model));
		} catch (Exception e) {
			log.info("error!  errMsg=" + e.getMessage());
			e.printStackTrace();
		}
	}
}
