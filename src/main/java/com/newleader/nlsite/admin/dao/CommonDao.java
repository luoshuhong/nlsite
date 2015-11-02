package com.newleader.nlsite.admin.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 公共数据库操作
 * @author Luoshuhong
 * @Company  
 * 2015年10月16日
 */
public class CommonDao extends JdbcDaoSupport {
	
	/**
	 * 查询记录条数 
	 * @param sql 完整的SQL语句 样例:select count(*) from xxxx
	 * @return 记录条数
	 */
	@SuppressWarnings("deprecation")
	public int queryCount(String sql) {
		return this.getJdbcTemplate().queryForInt(sql);
	}
	
	/**
	 * 根据时间查询职业测评 新增用户数
	 * @param sDate	  开始时间
	 * @param eDate	  结束数据
	 * @return	数量
	 */
	@SuppressWarnings("deprecation")
	public int queryProIncrease(String sDate, String eDate) {
		return this.getJdbcTemplate().queryForInt("select count(id) from aa_userinfo where InputDate > ? and InputDate <? ",
				new Object[]{sDate, eDate});
	}
	
	/**
	 * 查询累计爱情测评人数 
	 * @return 数量
	 */
	@SuppressWarnings("deprecation")
	public int queryLoveTotalUser() {
		return this.getJdbcTemplate().queryForInt("select count(Id) from aa_userinfo where nick <> '' ");
	}
	
}
