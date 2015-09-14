package com.newleader.nlsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.dao.ChannelDao;
import com.newleader.nlsite.model.Channel;

/**
 * 渠道相关操作
 * @author Luoshuhong
 * @Company  
 * 2015年9月11日
 *
 */
@Service
public class ChannelService {
	@Autowired
	private ChannelDao channelDao;
	
	/**
	 * 添加渠道
	 * @param channel
	 * @return true or false
	 */
	public boolean add(Channel channel) {
		return this.channelDao.add(channel);
	}
	
	/**
	 * 查询所有记录
	 * @return List<Channel>
	 */
	public List<Channel> query() {
		return this.channelDao.query();
	}
	
}
