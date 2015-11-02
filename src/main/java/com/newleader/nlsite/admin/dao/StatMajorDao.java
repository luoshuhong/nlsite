package com.newleader.nlsite.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.model.StatMajor;

/**
 * 用户活跃度统计结果
 * @author Luoshuhong
 * @Company  
 * 2015年10月16日
 */
public class StatMajorDao extends JdbcDaoSupport {

	/**
	 * 添加
	 * @param model  
	 * @return true or false
	 */
	public boolean add(StatMajor model) {
		String insertSql = "insert into aa_stat_major(date,wxTotal,wxSubscribe,wxUnsubscribe,wxPureSubscribe,totalUser,proIncrease,proTotal,loveIncrease,loveTotal) values(?,?,?,?,?,?,?,?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql, 
				new Object[] {model.getDate(),model.getWxTotal(),model.getWxSubscribe(),model.getWxUnsubscribe(),model.getWxPureSubscribe(),model.getTotalUser(),
				model.getProIncrease(), model.getProTotal(), model.getLoveIncrease(), model.getLoveTotal()});
	}
	
	/**
	 * 更新
	 * @param model
	 * @return
	 */
	public boolean update(StatMajor model) {
		String updateSql = "update aa_stat_major set wxTotal=?,wxSubscribe=?,wxUnsubscribe=?,wxPureSubscribe=?,totalUser=? where date = ?";
		return 1 == this.getJdbcTemplate().update(updateSql, new Object[]{model.getWxTotal(),model.getWxSubscribe(),model.getWxUnsubscribe(),
				model.getWxPureSubscribe(), model.getTotalUser(), model.getDate()});
	}
	
	/**
	 * 根据时间查询
	 * @param sDate
	 * @param eDate 
	 */
	public List<StatMajor> queyByDate(String sDate, String eDate) {
		List<StatMajor> listStat = new ArrayList<StatMajor>();
		String selSql = "Select id, date,wxTotal,wxSubscribe,wxUnsubscribe,wxPureSubscribe,totalUser,proIncrease,proTotal,loveIncrease,loveTotal"
				+ " from aa_stat_major where date > ? and date < ?  order by id desc";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(selSql, new Object[]{sDate, eDate});
		for (Map<String,Object> map : list) {
			listStat.add(this.wrapModel(map));
		}
		return listStat;
	}
	
	/**
	 * 根据时间查询(查询某一天的数据)
	 * @param date
	 * @param eDate 
	 */
	public StatMajor queyByDate(String date ) {
		String selSql = "Select id, date,wxTotal,wxSubscribe,wxUnsubscribe,wxPureSubscribe,totalUser,proIncrease,proTotal,loveIncrease,loveTotal"
				+ " from aa_stat_major where date = ?   ";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(selSql, new Object[]{date});
		for (Map<String,Object> map : list) {
			return this.wrapModel(map);
		}
		return new StatMajor();
	}
	
	/**
	 * @param map
	 * @return
	 */
	private StatMajor wrapModel(Map<String,Object> map) {
//		id, date,wxTotal,wxSubscribe,wxUnsubscribe,totalUser,proIncrease,proTotal,loveIncrease,loveTotal
		StatMajor model = new StatMajor();
		if (map.containsKey("date") && null != map.get("date")) {
			model.setDate(map.get("date").toString());
		}
		if (map.containsKey("id") && null != map.get("id")) {
			model.setId(Integer.valueOf(map.get("id").toString()));
		}
		if (map.containsKey("wxTotal") && null != map.get("wxTotal")) {
			model.setWxTotal(Integer.valueOf(map.get("wxTotal").toString()));
		}
		if (map.containsKey("wxSubscribe") && null != map.get("wxSubscribe")) {
			model.setWxSubscribe(Integer.valueOf(map.get("wxSubscribe").toString()));
		}
		if (map.containsKey("wxUnsubscribe") && null != map.get("wxUnsubscribe")) {
			model.setWxUnsubscribe(Integer.valueOf(map.get("wxUnsubscribe").toString()));
		}
		if (map.containsKey("wxPureSubscribe") && null != map.get("wxPureSubscribe")) {
			model.setWxPureSubscribe(Integer.valueOf(map.get("wxPureSubscribe").toString()));
		} 
		if (map.containsKey("totalUser") && null != map.get("totalUser")) {
			model.setTotalUser(Integer.valueOf(map.get("totalUser").toString()));
		}
		if (map.containsKey("proIncrease") && null != map.get("proIncrease")) {
			model.setProIncrease(Integer.valueOf(map.get("proIncrease").toString()));
		}
		if (map.containsKey("proTotal") && null != map.get("proTotal")) {
			model.setProTotal(Integer.valueOf(map.get("proTotal").toString()));
		}
		if (map.containsKey("loveIncrease") && null != map.get("loveIncrease")) {
			model.setLoveIncrease(Integer.valueOf(map.get("loveIncrease").toString()));
		}
		if (map.containsKey("loveTotal") && null != map.get("loveTotal")) {
			model.setLoveTotal(Integer.valueOf(map.get("loveTotal").toString()));
		}
		
		return model;
	}
	
}
