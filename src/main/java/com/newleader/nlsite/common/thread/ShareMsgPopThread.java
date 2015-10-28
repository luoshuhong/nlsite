package com.newleader.nlsite.common.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.newleader.nlsite.admin.model.RecordShare;
import com.newleader.nlsite.admin.service.RecordShareService;
import com.newleader.nlsite.common.Constants;
import com.newleader.nlsite.common.RedisUtils;
import com.newleader.nlsite.common.SpringContextUtil;

/**
 *   获取redis队列数据线程(页面分享信息)
 * @author Luoshuhong
 * @Company  
 * 2015年10月10日
 *
 */
public class ShareMsgPopThread extends Thread {
	private static Log log = LogFactory.getLog(ShareMsgPopThread.class);
	private int syncTimeInterval = 2;  			//同步时间间隔  默认2秒
	
	public static void main(String[] args) {
		DataSource dataSource = SpringContextUtil.getBean("dataSource");
		try {
			ResultSet resultSet = dataSource.getConnection().prepareCall("xxx").executeQuery();
			if (resultSet.next()) {
				resultSet.getInt("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		log.info("ShareMsgPopThread is running…………");
		RedisUtils redisUtils = SpringContextUtil.getBean("redisUtils");
		RecordShareService recordShareService = SpringContextUtil.getBean("recordShareService");
		
		if (null == redisUtils) {
			log.info("redisUtils is null…………");
			return;
		}
		
		while (true) {
			try {
				// 休眠固定时间
				Thread.sleep(syncTimeInterval * Constants.ONE_SECOND_MS);
				
				//从redis中获取
				String shareMsg = redisUtils.lpop(Constants.REDIS_SHARE_MSG);
				if (StringUtils.isEmpty(shareMsg)) {
					continue;
				}
				log.info("shareMsg=" + shareMsg);
				
				// openId:分享者id  scene:场景
				JSONObject job = JSONObject.parseObject(shareMsg);
				String openId = job.getString("openId");
				String scene = job.getString("scene");
				if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(scene)) {
					continue;
				}
				RecordShare model = new RecordShare(openId, scene);
				log.info("shareMsg=" + shareMsg + ", result=" + recordShareService.add(model));
			} catch (Exception e) {
				log.info("error![shareMsg]  errMsg=" + e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
	
}
