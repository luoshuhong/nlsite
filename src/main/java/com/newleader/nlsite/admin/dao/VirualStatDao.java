package com.newleader.nlsite.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.model.StatModel;

/**
 * Virual统计
 * @author Luoshuhong
 * @Company  
 * 2015年10月16日
 *
 */
public class VirualStatDao extends JdbcDaoSupport {
	
	/**
	 *  根据时间查询 分享数据
	 * @param sDate	开始时间
	 * @param eDate 结束时间
	 * @return List<StatModel>
	 */
	public List<StatModel> queryShareStat(String sDate, String eDate) {
		List<StatModel> retList = new ArrayList<StatModel>();
		String selSql = "select scene,date_format(createTime,'%Y-%m-%d') as createDate,count(Id) as count  from aa_record_share  "
				+ "where createTime > ? and createTime < ? GROUP BY scene,TO_DAYS(createTime)";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList( selSql, new Object[] { sDate, eDate });
		for (Map<String,Object> map : list) {
			StatModel model = new StatModel();
			if (map.containsKey("scene") && null != map.get("scene")) {
				model.setStatItem(map.get("scene").toString());
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
	 *  根据时间查询  浏览分享数据
	 * @param sDate	开始时间
	 * @param eDate 结束时间
	 * @return List<StatModel>
	 */
	public List<StatModel> queryVisitShareStat(String sDate, String eDate) {
		List<StatModel> retList = new ArrayList<StatModel>();
		String selSql = "select scene,date_format(createTime,'%Y-%m-%d') as createDate,count(Id) as count  from aa_record_virus  "
				+ "where createTime > ? and createTime < ? GROUP BY scene,TO_DAYS(createTime)";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(selSql, new Object[] { sDate, eDate });
		for (Map<String,Object> map : list) {
			StatModel model = new StatModel();
			if (map.containsKey("scene") && null != map.get("scene")) {
				model.setStatItem(map.get("scene").toString());
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
	 *  根据时间查询  Virul用户数据
	 * @param sDate	开始时间
	 * @param eDate 结束时间
	 * @return List<StatModel>
	 */
	public List<StatModel> queryVirulUserStat(String sDate, String eDate) {
		List<StatModel> retList = new ArrayList<StatModel>();
		String selSql = "select rootChannelId,date_format(createTime,'%Y-%m-%d') as createDate,count(Id) as count  from aa_record_virus  "
				+ "where createTime > ? and createTime < ? and isSubscribe = 1 GROUP BY rootChannelId,TO_DAYS(createTime)";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(selSql, new Object[] { sDate, eDate });
		for (Map<String,Object> map : list) {
			StatModel model = new StatModel();
			if (map.containsKey("rootChannelId") && null != map.get("rootChannelId")) {
				model.setStatItem(map.get("rootChannelId").toString());
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
	
}
