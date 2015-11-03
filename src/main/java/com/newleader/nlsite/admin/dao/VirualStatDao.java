package com.newleader.nlsite.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.model.StatModel;
import com.newleader.nlsite.common.CommonUtils;

/**
 * Virual统计
 * @author Luoshuhong
 * @Company  
 * 2015年10月16日
 *
 */
public class VirualStatDao extends JdbcDaoSupport {
	
	/********************** query-分享-浏览-viral 按场景、时间分组 有时间条件 start****************************************/
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
			model.setStatItem(CommonUtils.getStrFromMap(map, "scene"));
			model.setDate(CommonUtils.getStrFromMap(map, "createDate"));		
			model.setCount(CommonUtils.getIntFromMap(map, "count"));
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
			model.setStatItem(CommonUtils.getStrFromMap(map, "scene"));
			model.setDate(CommonUtils.getStrFromMap(map, "createDate"));		
			model.setCount(CommonUtils.getIntFromMap(map, "count"));
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
		String selSql = "select scene,date_format(createTime,'%Y-%m-%d') as createDate,count(Id) as count  from aa_record_virus  "
				+ "where createTime > ? and createTime < ? and isSubscribe = 1 GROUP BY scene,TO_DAYS(createTime)";
//		String selSql = "select rootChannelId,date_format(createTime,'%Y-%m-%d') as createDate,count(Id) as count  from aa_record_virus  "
//				+ "where createTime > ? and createTime < ? and isSubscribe = 1 GROUP BY rootChannelId,TO_DAYS(createTime)";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(selSql, new Object[] { sDate, eDate });
		for (Map<String,Object> map : list) {
			StatModel model = new StatModel();
//			if (map.containsKey("rootChannelId") && null != map.get("rootChannelId")) {
//				model.setStatItem(map.get("rootChannelId").toString());
//			}
			model.setStatItem(CommonUtils.getStrFromMap(map, "scene"));
			model.setDate(CommonUtils.getStrFromMap(map, "createDate"));		
			model.setCount(CommonUtils.getIntFromMap(map, "count"));
			retList.add(model);
		}
		return retList;
	}
	/********************** query-分享-浏览-viral 按场景、时间分组 有时间条件 end****************************************/
	
	/********************** query-分享-浏览-viral 按场景  无时间条件 start****************************************/
	/**
	 *  根据时间查询 分享数据
	 * @return List<StatModel>
	 */
	public List<StatModel> queryShareStat() {
		List<StatModel> retList = new ArrayList<StatModel>();
		String selSql = "select scene,count(Id) as count  from aa_record_share GROUP BY scene ";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList( selSql);
		for (Map<String,Object> map : list) {
			StatModel model = new StatModel();
			model.setStatItem(CommonUtils.getStrFromMap(map, "scene"));
			model.setCount(CommonUtils.getIntFromMap(map, "count"));
			retList.add(model);
		}
		return retList;
	}
	
	/**
	 *  根据时间查询  浏览分享数据
	 * @return List<StatModel>
	 */
	public List<StatModel> queryVisitShareStat() {
		List<StatModel> retList = new ArrayList<StatModel>();
		String selSql = "select scene,count(Id) as count  from aa_record_virus  GROUP BY scene";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(selSql);
		for (Map<String,Object> map : list) {
			StatModel model = new StatModel();
			model.setStatItem(CommonUtils.getStrFromMap(map, "scene"));
			model.setCount(CommonUtils.getIntFromMap(map, "count"));
			retList.add(model);
		}
		return retList;
	}
	
	/**
	 *  根据时间查询  Virul用户数据
	 * @return List<StatModel>
	 */
	public List<StatModel> queryVirulUserStat() {
		List<StatModel> retList = new ArrayList<StatModel>();
		String selSql = "select scene,count(Id) as count  from aa_record_virus  where  isSubscribe = 1 GROUP BY scene";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(selSql);
		for (Map<String,Object> map : list) {
			StatModel model = new StatModel();
			model.setStatItem(CommonUtils.getStrFromMap(map, "scene"));
			model.setCount(CommonUtils.getIntFromMap(map, "count"));
			retList.add(model);
		}
		return retList;
	}
	/********************** query-分享-浏览-viral 按场景  无时间条件 end****************************************/
	
	
	/********************** query-分享-浏览-viral 按场景  有渠道条件 start****************************************/
	/**
	 *  根据时间查询 分享数据
	 *  @param channelCode
	 * @return List<StatModel>
	 */
	public List<StatModel> queryShareStat(String channelCode) {
		List<StatModel> retList = new ArrayList<StatModel>();
		String selSql = "select scene,count(Id) as count  from aa_record_share  where rootChannelId = ?  GROUP BY scene ";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList( selSql, new Object[]{channelCode});
		for (Map<String,Object> map : list) {
			StatModel model = new StatModel();
			model.setStatItem(CommonUtils.getStrFromMap(map, "scene"));
			model.setCount(CommonUtils.getIntFromMap(map, "count"));
			retList.add(model);
		}
		return retList;
	}
	
	/**
	 *  根据时间查询  浏览分享数据
	 *  @param channelCode
	 * @return List<StatModel>
	 */
	public List<StatModel> queryVisitShareStat(String channelCode) {
		List<StatModel> retList = new ArrayList<StatModel>();
		String selSql = "select scene,count(Id) as count  from aa_record_virus where  rootChannelId = ?  GROUP BY scene";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(selSql, new Object[]{channelCode});
		for (Map<String,Object> map : list) {
			StatModel model = new StatModel();
			model.setStatItem(CommonUtils.getStrFromMap(map, "scene"));
			model.setCount(CommonUtils.getIntFromMap(map, "count"));
			retList.add(model);
		}
		return retList;
	}
	
	/**
	 *  根据时间查询  Virul用户数据
	 *  @param channelCode
	 * @return List<StatModel>
	 */
	public List<StatModel> queryVirulUserStat(String channelCode) {
		List<StatModel> retList = new ArrayList<StatModel>();
		String selSql = "select scene,count(Id) as count  from aa_record_virus  where  isSubscribe = 1  and rootChannelId = ? GROUP BY scene";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(selSql, new Object[]{channelCode});
		for (Map<String,Object> map : list) {
			StatModel model = new StatModel();
			model.setStatItem(CommonUtils.getStrFromMap(map, "scene"));
			model.setCount(CommonUtils.getIntFromMap(map, "count"));
			retList.add(model);
		}
		return retList;
	}
	/********************** query-分享-浏览-viral 按场景 有渠道条件 end****************************************/
	
}
