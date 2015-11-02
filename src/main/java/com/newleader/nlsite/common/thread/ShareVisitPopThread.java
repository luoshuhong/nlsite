package com.newleader.nlsite.common.thread;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.newleader.nlsite.admin.model.RecordVirus;
import com.newleader.nlsite.admin.service.RecordVirusService;
import com.newleader.nlsite.common.Constants;
import com.newleader.nlsite.common.RedisUtils;
import com.newleader.nlsite.common.SpringContextUtil;

/**
 *   获取redis队列数据线程（分享页面访问信息）
 * @author Luoshuhong
 * @Company  
 * 2015年10月10日
 *
 */
public class ShareVisitPopThread extends Thread {
	private static Log log = LogFactory.getLog(ShareVisitPopThread.class);
	private int syncTimeInterval = 2;  			//同步时间间隔  默认2秒
	
	@Override
	public void run() {
		log.info("ShareVisitPopThread is running…………");
		RedisUtils redisUtils = SpringContextUtil.getBean("redisUtils");
		
		if (null == redisUtils) {
			log.info("redisUtils is null…………");
			return;
		}
		
		while (true) {
			try {
				RecordVirusService recordVirusService = SpringContextUtil.getBean("recordVirusService");
				// 休眠固定时间
				Thread.sleep(syncTimeInterval * Constants.ONE_SECOND_MS);
				
				// sOpenId:分享者id  currOpenId:访问者id scene:场景
				String shareBack = redisUtils.lpop(Constants.REDIS_SHARE_BACK);
				if (StringUtils.isEmpty(shareBack)) {
					continue;
				}
				log.info("shareBack=" + shareBack);
				
				JSONObject job = JSONObject.parseObject(shareBack);
				String vopenId = job.getString("vopenId");  //浏览者openId
				String sopenId = job.getString("sopenId");  //分享着openId
				String scene = job.getString("scene");         //场景
				if (StringUtils.isEmpty(vopenId)  || StringUtils.isEmpty(sopenId) || StringUtils.isEmpty(scene)) {
					continue;
				}
				
				//保存记录
				RecordVirus model = new RecordVirus(vopenId, sopenId, scene);
				log.info("shareBack=" + shareBack + ", result=" + recordVirusService.add(model));
			} catch (Exception e) {
				log.info("error![shareBack]  errMsg=" + e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
}
