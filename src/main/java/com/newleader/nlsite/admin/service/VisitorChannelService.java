package com.newleader.nlsite.admin.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.RecordVirusDao;
import com.newleader.nlsite.admin.dao.VisitorChannelDao;
import com.newleader.nlsite.admin.model.RecordVirus;
import com.newleader.nlsite.admin.model.VisitorChannel;

/**
 * 用户关注/取消关注 渠道记录
 * @author Luoshuhong
 * @Company  
 * 2015年10月15日
 *
 */
@Service 
public class VisitorChannelService {
	@Autowired
	private VisitorChannelDao visitorChannelDao;
	
	@Autowired
	private RecordVirusDao recordVirusDao;
	
	/**
	 * 添加
	 * @param model
	 * @return
	 */
	public boolean add(VisitorChannel model) {
		//1.是否是回流
		if (1 <= this.visitorChannelDao.isUserExist(model.getOpenId())) {
			model.setIsBind(2);
		}
		
		//2.是否是virus来
		RecordVirus recordVirus = this.recordVirusDao.queryByOpenId(model.getOpenId());
		if (null != recordVirus) {
			//2.1更新分享浏览记录 为已关注
			this.recordVirusDao.updateSubscribe(recordVirus.getId());
			
			//2.2查询上一级分享用户级别
			VisitorChannel superModel = this.visitorChannelDao.queyByOpenId(recordVirus.getsOpenId());
			model.setLeavel(superModel.getLeavel() + 1);
		}
		
		model.setId(UUID.randomUUID().toString().toUpperCase());
		return this.visitorChannelDao.add(model);
	}
}
