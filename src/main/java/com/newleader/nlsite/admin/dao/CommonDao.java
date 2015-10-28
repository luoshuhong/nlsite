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
	
}
