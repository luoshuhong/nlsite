package com.newleader.nlsite.admin.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.StatActionDataDao;
import com.newleader.nlsite.admin.dao.StatUserActiveDao;
import com.newleader.nlsite.admin.dao.VisitorChannelDao;
import com.newleader.nlsite.admin.model.StatActionData;
import com.newleader.nlsite.admin.model.VisitorChannel;

/**
 * 统计原始数据
 * @author Luoshuhong
 * @Company  
 * 2015年10月21日
 *
 */
@Service 
public class StatActionDataService {
	
	@Autowired
	private StatActionDataDao statActionDataDao;
	
	@Autowired
	private VisitorChannelDao visitorChannelDao;
	
	@Autowired
	private StatUserActiveDao statUserActiveDao;
	/**
	 * 保存
	 * @param model StatOrgData
	 * @return 
	 */
	public boolean add(StatActionData model) {
		//查询 channelId		
		VisitorChannel visitorChannel = this.visitorChannelDao.queyByOpenId(model.getOpenId());
		if (null == visitorChannel) {
			model.setChannelId("default");
		}  else {
			model.setChannelId(visitorChannel.getChannelId());
		}
		
		//查询上一次操作记录
		StatActionData statActionData = this.statActionDataDao.queyByOpenId(model.getOpenId());  
		if (null == statActionData) {
			model.setInterval(0);
		} else {
			//计算两次操作之间的间隔时间 秒
			model.setInterval((new Date().getTime() - statActionData.getCreateTime().getTime()) / 1000); 
		}
		//记录操作记录
		this.statActionDataDao.add(model);
		
		//记录用户活跃度 操作表
		if (this.statUserActiveDao.queyByOpenId30Min(model.getOpenId()) >= 1) {
			return true;
		}
		return this.statUserActiveDao.add(model);
	}
	
	
}
