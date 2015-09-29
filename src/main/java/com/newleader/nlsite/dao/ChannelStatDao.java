package com.newleader.nlsite.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.model.ChannelStat;

/**
 * 渠道推广统计
 * @author Luoshuhong
 * @Company  
 * 2015年9月11日
 *
 */
public class ChannelStatDao extends JdbcDaoSupport {
	
	/**
	 * 根据时间段查询 渠道关注量
	 * @param sDate	开始时间
	 * @param eDate 结束时间
	 * @return List<ChannelStat>
	 */
	public List<ChannelStat> query(String sDate, String eDate) {
		List<ChannelStat> retList = new ArrayList<ChannelStat>();
		String selSql = "select channelId,createDate,count(Id) as count  from aa_visitor_channel "
				+ "where createTime > ? and createTime < ? and isBind <> 1 GROUP BY channelId,createDate";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(
				selSql, new Object[] { sDate, eDate });
		
		for (Map<String,Object> map : list) {
			ChannelStat model = new ChannelStat();
			if (map.containsKey("channelId") && null != map.get("channelId")) {
				model.setChannelCode(map.get("channelId").toString());
			}
			if (map.containsKey("createDate") && null != map.get("createDate")) {
				model.setDate(map.get("createDate").toString());		
			}
			if (map.containsKey("count") && null != map.get("count")) {
				model.setCount(Integer.valueOf(map.get("count").toString()));
			}
			retList.add(model);
		}
		
		return retList;
	}
	
	/**
	 * 根据时间段查询 回流数据
	 * @param sDate	开始时间
	 * @param eDate 结束时间
	 * @return List<ChannelStat>
	 */
	public List<ChannelStat> queryBackflow(String sDate, String eDate) {
		List<ChannelStat> retList = new ArrayList<ChannelStat>();
		String selSql = "select channelId,createDate,count(Id) as count  from aa_visitor_channel "
				+ "where createTime > ? and createTime < ? and isBind = 2 GROUP BY channelId,createDate";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(
				selSql, new Object[] { sDate, eDate });
		
		for (Map<String,Object> map : list) {
			ChannelStat model = new ChannelStat();
			if (map.containsKey("channelId") && null != map.get("channelId")) {
				model.setChannelCode(map.get("channelId").toString());
			}
			if (map.containsKey("createDate") && null != map.get("createDate")) {
				model.setDate(map.get("createDate").toString());		
			}
			if (map.containsKey("count") && null != map.get("count")) {
				model.setCount(Integer.valueOf(map.get("count").toString()));
			}
			retList.add(model);
		}
		
		return retList;
	}
	
	/**
	 * 根据时间段查询 取消关注
	 * @param sDate	开始时间
	 * @param eDate 结束时间
	 * @return List<ChannelStat>
	 */
	public List<ChannelStat> queryUnsubscribe(String sDate, String eDate) {
		List<ChannelStat> retList = new ArrayList<ChannelStat>();
		String selSql = "select createDate,count(Id) as count from aa_visitor_channel "
				+ "where createTime > ? and createTime < ? and isBind = 1 GROUP BY createDate";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(
				selSql, new Object[] { sDate, eDate });
		
		for (Map<String,Object> map : list) {
			ChannelStat model = new ChannelStat();
//			if (map.containsKey("channelId") && null != map.get("channelId")) {
				model.setChannelCode("unsubscribe");
//			}
			if (map.containsKey("createDate") && null != map.get("createDate")) {
				model.setDate(map.get("createDate").toString()); 
			}
			if (map.containsKey("count") && null != map.get("count")) {
				model.setCount(Integer.valueOf(map.get("count").toString()));
			}
			retList.add(model);
		}
		
		return retList;
	}
	
	/**
	 * 获取某个渠道累计的关注量
	 * @param channelId  渠道id
	 * @return 累计关注量
	 */
	@SuppressWarnings("deprecation")
	public int getSubscribeByChannel(String code) {
		String selSql = " select count(id) from aa_visitor_channel where isBind in(0,2) and channelId = ?";
		return this.getJdbcTemplate().queryForInt(selSql, new Object[]{code});
	}
	
	/**
	 * 更新创建时间（方便统计用字段）
	 */
	public void updateCreatData() {
		this.getJdbcTemplate()
				.update("update aa_visitor_channel set createDate = left(createTime,10) where createDate = 'NULL'");
	}
}
