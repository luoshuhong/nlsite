package com.newleader.nlsite.admin.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 爱情测试统计
 * @author Luoshuhong
 * @Company  
 * 2015年9月11日
 *
 */
public class LoveTestStatDao extends JdbcDaoSupport {
	
	/**
	 * 更新测试 渠道编码
	 */
	public void updateChannelId() {
		this.getJdbcTemplate().update("update if_testing_result a, aa_visitor_channel b set a.channelId = b.channelId where a.username = b.openId and a.channelId = ''");
	}
}
