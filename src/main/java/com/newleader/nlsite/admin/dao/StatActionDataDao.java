package com.newleader.nlsite.admin.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.model.StatActionData;
import com.newleader.nlsite.common.DateUtils;

/**
 * 记录用户操作记录
 * @author Luoshuhong
 * @Company  
 * 2015年10月16日
 */
public class StatActionDataDao extends JdbcDaoSupport {
//	  `id` int(11) NOT NULL,
//	  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
//	  `type` varchar(30) DEFAULT '' COMMENT '类型  login:登陆',
//	  `openId` varchar(50) DEFAULT '' COMMENT '用户openId',
//	  `product` varchar(50) DEFAULT '' COMMENT '产品',
//	  `channelId` varchar(50) DEFAULT '' COMMENT '用户所属渠道',
//	  `interval` int(10) DEFAULT '0' COMMENT '时间间隔，于上一次访问的时间间隔',
	  
	public boolean add(StatActionData model) {
		String insertSql = "insert into aa_stat_action_data(createTime,type,openId,channelId,product,interval) values(?,?,?,?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql, 
				new Object[] {new Date(), model.getType(), model.getOpenId(), model.getChannelId(), model.getProduct(), model.getInterval()});
	}
	
	/**
	 * 查询用户上一次操作记录
	 * @param openId  openId
	 */
	public StatActionData queyByOpenId(String openId) {
		String selSql = "Select id, createTime,type,openId,channelId,product,interval from aa_stat_action_data where openId = ?  order by createTime desc";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(selSql, new Object[]{openId});
		for (Map<String,Object> map : list) {
			StatActionData model = this.wrapModel(map);
			if (null != model) {
				return model;
			}
		}
		return null;
	}
	
	/**
	 * @param map
	 * @return
	 */
	private StatActionData wrapModel(Map<String,Object> map) {
//		id, createTime,type,openId,channelId,product,interval
		StatActionData model = new StatActionData();
		if (map.containsKey("id") && null != map.get("id")) {
			model.setId(Integer.valueOf(map.get("id").toString()));
		}
		if (map.containsKey("openId") && null != map.get("openId")) {
			model.setOpenId(map.get("openId").toString());
		}
		if (map.containsKey("channelId") && null != map.get("channelId")) {
			model.setChannelId(map.get("channelId").toString());
		}
		if (map.containsKey("createTime") && null != map.get("createTime")) {
			model.setCreateTime(DateUtils.strToSysDate(map.get("level").toString()));
		}
		if (map.containsKey("type") && null != map.get("type")) {
			model.setType(map.get("type").toString());
		}
		if (map.containsKey("product") && null != map.get("product")) {
			model.setProduct(map.get("product").toString());
		}
		if (map.containsKey("interval") && null != map.get("interval")) {
			model.setInterval(Long.valueOf(map.get("interval").toString()));
		}
		return model;
	}
	
}
