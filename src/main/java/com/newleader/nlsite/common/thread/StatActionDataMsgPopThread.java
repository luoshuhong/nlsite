package com.newleader.nlsite.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.newleader.nlsite.admin.model.StatActionData;
import com.newleader.nlsite.admin.service.StatActionDataService;
import com.newleader.nlsite.common.Constants;
import com.newleader.nlsite.common.RedisUtils;
import com.newleader.nlsite.common.SpringContextUtil;

/**
 *   获取redis队列数据线程(统计操作记录消息)
 * @author Luoshuhong
 * @Company  
 * 2015年10月21日
 *
 */
public class StatActionDataMsgPopThread extends Thread {
	private static Log log = LogFactory.getLog(StatActionDataMsgPopThread.class);
	private static final int MAX_NUM = 20; 			      //最大线程数
	private int syncTimeInterval = 1;  			//同步时间间隔  默认2秒
	private static ExecutorService businessDealPool = Executors.newFixedThreadPool(MAX_NUM); ;  //主业务处理线程池
	
	@Override
	public void run() {
		log.info("StatActionDataMsgPopThread is running…………");
		RedisUtils redisUtils = SpringContextUtil.getBean("redisUtils");
		
		if (null == redisUtils) {
			log.info("StatActionDataMsgPopThread redisUtils is null…………");
			return;
		}
		
		while (true) {
			try {
				// 休眠固定时间
				Thread.sleep(syncTimeInterval * Constants.ONE_SECOND_MS);
				
				if (((ThreadPoolExecutor) businessDealPool).getActiveCount() >= MAX_NUM) {
					log.info("StatActionDataMsgPopThread the thread pool is full .");
					continue;
				}
				
				//从redis中获取
				String actionMsg = redisUtils.lpop(Constants.REDIS_ACTION_MSG);
				if (StringUtils.isEmpty(actionMsg)) {
					continue;
				}
				log.info("statActionDataMsg=" + actionMsg);
				
				//放入线程池执行
				businessDealPool.execute(new StatActionDataDealThread(actionMsg));
			} catch (Exception e) {
				log.info("error!  errMsg=" + e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
	
}

/**
 * 保存记录操作
 * @author luoshuhong
 *
 */
class StatActionDataDealThread extends Thread {
	private static Log log = LogFactory.getLog(StatActionDataMsgPopThread.class);
	String statActionDataMsg;
	public StatActionDataDealThread(String statActionDataMsg) {
		this.statActionDataMsg = statActionDataMsg;
	}
	@Override
	public void run() {
		try {
			StatActionDataService statOrgDataService = SpringContextUtil.getBean("statActionDataService");
			JSONObject job = JSONObject.parseObject(statActionDataMsg); 
			String type = job.getString("type");				 // 类型：login(登陆)
			String openId = job.getString("openId");	     // 用户openId
			String product = job.getString("product");     // 产品 固定:love(爱情测评), profession (职业测评)
			if (StringUtils.isEmpty(type) || StringUtils.isEmpty(openId) || StringUtils.isEmpty(product)) { 
				return;
			} 
			
			StatActionData model = new StatActionData();
			model.setOpenId(openId);
			model.setType(type);
			model.setProduct(product);
			log.info("statActionDataMsg=" + statActionDataMsg + ", result=" + statOrgDataService.add(model));
		} catch (Exception e) {
			log.info("error!  errMsg=" + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
