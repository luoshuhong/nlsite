package com.newleader.nlsite.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.dao.inter.DaoInter;
import com.newleader.nlsite.model.Channel;

/**
 * 
 * @author Luoshuhong
 * @Company  
 * 2015年9月11日
 *
 */
public class ChannelDao extends JdbcDaoSupport implements DaoInter<Channel> {
	@Override
	public boolean add(Channel t) {
		String insertSql = "insert into aa_channel(id,Name,Code) values(?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql,
				new Object[] { t.getId(), t.getName(), t.getCode() });
	}
	
	@Override
	public boolean del(int id) {
		return false;
	}
	
	@Override
	public List<Channel> query() {
		List<Channel> channelList = new ArrayList<Channel>();
		String selectSql = "select * from aa_channel";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(selectSql);
		for (Map<String,Object> map : list) {
			channelList.add(this.wrapModel(map));
		}
		return channelList;
	}
	
	@Override
	public boolean update(Channel t) {
		return false;
	}
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	private Channel wrapModel(Map<String,Object> map) {
		Channel model = new Channel();
		String code = "";
		String name = "";
		String id = "";
		if (map.containsKey("Id")) {
			id = map.get("Id").toString();
		}
		if (map.containsKey("Code")) {
			code = map.get("Code").toString();
		}
		if (map.containsKey("Name")) {
			name = map.get("Name").toString();
		}
		model.setCode(code);
		model.setName(name);
		model.setId(id);
		
		return model;
	}
}
