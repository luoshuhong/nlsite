package com.newleader.nlsite.admin.service;

import java.util.List;

import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
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
	@Transient
	public boolean add(Channel channel) {
		//这里没有考虑事务
		if ("-1".equals(channel.getFreeType()) || StringUtils.isEmpty(channel.getFreeType())) {
			return this.channelDao.add(channel);
		} else {
			return this.channelDao.add(channel) && this.channelDao.addChannelActivity(channel);
		}
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
	 * @param shareCount
	 * @param virualCount
	 * @param totalSubscribe	 历史关注量
	 * @param unSubscribe	取消关注量
	 * @param code  渠道编码
	 * @return
	 */
	public boolean updateByCode(int shareCount, int virualCount, int totalSubscribe, int unSubscribe, String code) {
		return this.channelDao.updateByCode(shareCount,virualCount,totalSubscribe, unSubscribe, code);
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
	 * 根据编码查询
	 * @param code
	 * @return
	 */
	public Channel queryModeByCode(String code) {
		return this.channelDao.queryModelByCode(code);
	}
	
	
	/**
	 * 更新
	 * @param  channel
	 * @return true or false
	 */
	@Transient
	public boolean update(Channel channel) {
		if (1 <= this.channelDao.queryChannelActivity(channel.getCode())) {
			return this.channelDao.update(channel) && this.channelDao.updateChannelActivity(channel);
		} 
		return this.channelDao.update(channel) && this.channelDao.addChannelActivity(channel);
	}
	
	/**
	 * 根据code 查询是否存在
	 * @param code 渠道编码
	 * @return 个数
	 */
	public int queryByCode(String code) {
		return this.channelDao.queryByCode(code);
	}
	
	/**
	 * 更新时 检查渠道是否存在
	 * @param code 渠道编码
	 * @return 个数
	 */
	public int updateCheckCode(String code,String id) {
		return this.channelDao.updateCheckCode(code, id);
	}
	
}
