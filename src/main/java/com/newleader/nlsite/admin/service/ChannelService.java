package com.newleader.nlsite.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.ChannelDao;
import com.newleader.nlsite.admin.model.Channel;

/**
 * 渠道相关操作
 * @author Luoshuhong
 * @Company  
 * 2015年9月11日
 *
 */
@Service(value = "channelService")
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
	
	/**
	 * 更新渠道关注量
	 * @param totalSubscribe	 历史关注量
	 * @param unSubscribe	取消关注量
	 * @param code  渠道编码
	 * @return
	 */
	public boolean updateByCode(int totalSubscribe, int unSubscribe, String code) {
		return this.channelDao.updateByCode(totalSubscribe, unSubscribe, code);
	}
	/**
	 * 模糊查询
	 * @param value 条件
	 * @return List<Channel>
	 */
	public List<Channel> vagueQuery(String value) {
		return this.channelDao.vagueQuery(value);
	}
	
	/**
	 * 根据id 删除
	 * @param id
	 * @return true or false
	 */
	public boolean chacel(String id) {
		return this.channelDao.del(id);
	}
	
	/**
	 * 更新
	 * @param  channel
	 * @return true or false
	 */
	public boolean update(Channel channel) {
		return this.channelDao.update(channel);
	}
}
