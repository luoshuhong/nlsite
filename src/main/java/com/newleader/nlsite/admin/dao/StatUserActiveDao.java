package com.newleader.nlsite.admin.dao;

import java.util.Date;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.model.StatActionData;

/**
 * 记录用户活跃 信息
 * @author Luoshuhong
 * @Company  
 * 2015年10月21日
 */
public class StatUserActiveDao extends JdbcDaoSupport {
//		CREATE TABLE `aa_stat_user_active` (
//			  `id` int(11) DEFAULT NULL COMMENT '主键',
//			  `openId` varchar(50) DEFAULT '' COMMENT '用户openid',
//			  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
//			  `channelId` varchar(50) DEFAULT '' COMMENT '用户所属渠道',
//			  `product` varchar(50) DEFAULT '' COMMENT '产品',
	public boolean add(StatActionData model) {
		String insertSql = "insert into aa_stat_user_active(openId,createTime,channelId,product) values(?,?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql, 
				new Object[] {model.getOpenId(), new Date(), model.getChannelId(), model.getProduct()});
	}
	
	/**
	 *  查询30分钟内 有没有数据
	 * @param openId  openId
	 * @return 渠道编码
	 */
	@SuppressWarnings("deprecation")
	public int queyByOpenId30Min(String openId) {
		String selSql = "Select count(id) from  aa_stat_user_active  where openId = ? and createTime BETWEEN DATE_SUB(now(), INTERVAL 30 MINUTE) and now()";
		return this.getJdbcTemplate().queryForInt(selSql, new Object[]{openId});
	}
	
	
}
