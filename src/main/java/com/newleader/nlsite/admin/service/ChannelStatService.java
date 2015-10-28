package com.newleader.nlsite.admin.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.newleader.nlsite.admin.dao.ChannelStatDao;
import com.newleader.nlsite.admin.model.StatModel;
import com.newleader.nlsite.common.HighChartsUtils;

/**
 * 渠道推广统计相关操作
 * @author Luoshuhong
 * @Company  
 * 2015年9月17日
 *
 */
@Service(value = "channelStatService")
public class ChannelStatService {
	
	@Autowired
	private ChannelStatDao channelStatDao;
	
	/**
	 * 按渠道分组查询
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return json 串
	 */
	public String queryByDate(String sDate, String eDate) {
		List<StatModel> list = this.channelStatDao.query(sDate, eDate);  //关注数据
		List<StatModel> unsubscribeList = this.channelStatDao.queryUnsubscribe(sDate, eDate); //取消关注的数据
		List<StatModel> backflowList = this.channelStatDao.queryBackflow(sDate, eDate); //回流数据
		
		JSONObject job = new JSONObject();
		
		job.put("subscribe", HighChartsUtils.dealHightChartsData(list));
		job.put("unsubscribe", HighChartsUtils.dealHightChartsData(unsubscribeList));
		job.put("backflow", HighChartsUtils.dealHightChartsData(backflowList));
		
		return job.toJSONString();
	}
	
	/**
	 * 获取某个渠道累计关注量
	 * @param channelId  渠道id
	 * @return 累计关注量
	 */
	public int getSubscribeByChannel(String code) {
		if (StringUtils.isEmpty(code)) {
			return 0;
		}
		return this.channelStatDao.getSubscribeByChannel(code);
	}
	
	/**
	 * 获取某个渠道取消关注量
	 * @param channelId  渠道id
	 * @return 累计关注量
	 */
	public int getUnSubscribeByChannel(String code) {
		if (StringUtils.isEmpty(code)) {
			return 0;
		}
		return this.channelStatDao.getUnSubscribeByChannel(code);
	}
	
	
	
}
