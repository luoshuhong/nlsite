package com.newleader.nlsite.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.model.StatModel;

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
	public List<StatModel> query(String sDate, String eDate) {
		List<StatModel> retList = new ArrayList<StatModel>();
		String selSql = "select channelId,date_format(createTime,'%Y-%m-%d') as createDate,count(Id) as count  from aa_visitor_channel "
				+ "where createTime > ? and createTime < ? and isBind <> 1 GROUP BY channelId,TO_DAYS(createTime)";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(
				selSql, new Object[] { sDate, eDate });
		
		for (Map<String,Object> map : list) {
			StatModel model = new StatModel();
			if (map.containsKey("channelId") && null != map.get("channelId")) {
				model.setStatItem(map.get("channelId").toString());
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
	public List<StatModel> queryBackflow(String sDate, String eDate) {
		List<StatModel> retList = new ArrayList<StatModel>();
		String selSql = "select channelId,date_format(createTime,'%Y-%m-%d') as createDate,count(Id) as count  from aa_visitor_channel "
				+ "where createTime > ? and createTime < ? and isBind = 2 GROUP BY channelId,TO_DAYS(createTime)";
		
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(
				selSql, new Object[] { sDate, eDate });
		
		for (Map<String,Object> map : list) {
			StatModel model = new StatModel();
			if (map.containsKey("channelId") && null != map.get("channelId")) {
				model.setStatItem(map.get("channelId").toString());
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
	public List<StatModel> queryUnsubscribe(String sDate, String eDate) {
		List<StatModel> retList = new ArrayList<StatModel>();
		String selSql = "select date_format(bindTime,'%Y-%m-%d') as createDate,count(Id) as count from aa_visitor_channel "
				+ "where bindTime > ? and bindTime < ? and isBind = 1 GROUP BY TO_DAYS(bindTime)";
		
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(
				selSql, new Object[] { sDate, eDate });
		
		for (Map<String,Object> map : list) {
			StatModel model = new StatModel();
//			if (map.containsKey("channelId") && null != map.get("channelId")) {
				model.setStatItem("unsubscribe");
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
		String selSql = " select count(DISTINCT(openid)) from aa_visitor_channel where channelId = ?";
		return this.getJdbcTemplate().queryForInt(selSql, new Object[]{code});
	}
	
	/**
	 * 获取某个渠道取消关注量
	 * @param channelId  渠道id
	 * @return 累计关注量
	 */
	@SuppressWarnings("deprecation")
	public int getUnSubscribeByChannel(String code) {
		String selSql = " select count(DISTINCT(openid)) from aa_visitor_channel where  channelId = ? "
				+ " and openid not in (select openid from aa_visitor_channel where channelId = ? and isBind = 2)  and isBind = 1";
		return this.getJdbcTemplate().queryForInt(selSql, new Object[]{code,code});
	}
	
}
