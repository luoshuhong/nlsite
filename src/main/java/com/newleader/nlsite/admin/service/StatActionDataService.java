package com.newleader.nlsite.admin.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.StatActionDataDao;
import com.newleader.nlsite.admin.dao.StatResultDao;
import com.newleader.nlsite.admin.dao.StatUserActiveDao;
import com.newleader.nlsite.admin.dao.VisitorChannelDao;
import com.newleader.nlsite.admin.model.StatActionData;
import com.newleader.nlsite.admin.model.StatModel;
import com.newleader.nlsite.admin.model.VisitorChannel;
import com.newleader.nlsite.common.DateUtils;

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
	
	@Autowired
	private StatResultDao statResultDao;
	
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
	
	
	/**
	 *  定时执行方法
	 *  每天统计用户活跃度 
	 */
	public void quartzStat() {
		//1.UV 统计
		//1.1 日UV统计
		StatModel uvDay = new StatModel();
		this.statResultDao.add(uvDay);
		//1.2 周UV统计
		StatModel uvWeek = new StatModel();
		this.statResultDao.add(uvWeek);
		//1.3 月UV统计
		StatModel uvMonth = new StatModel();
		this.statResultDao.add(uvMonth);
		
		//2.PV统计（30分钟内算一次）
		//2.1日PV
		StatModel pvDay = new StatModel();
		this.statResultDao.add(pvDay);
		//2.2周PV
		StatModel pvWeek = new StatModel();
		this.statResultDao.add(pvWeek);
		//2.3周PV
		StatModel pvMonth = new StatModel();
		this.statResultDao.add(pvMonth);
		
		//3.
		
		System.out.println("-----------------" + DateUtils.getCurrentStringDateYMDHMS( ));
	} 
	
}


