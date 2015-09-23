package com.newleader.nlsite.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
		String insertSql = "insert into aa_channel(id,Name,Code,CreateTime,delete_flag) values(?,?,?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql,
				new Object[] { t.getId(), t.getName(), t.getCode(),new Date(),"0"});
	}
	
	@Override
	public boolean del(String id) {
		String delSql = "update aa_channel set delete_flag = 1 where id = ?";
		return 1 == this.getJdbcTemplate().update(delSql,id);
	}
	
	@Override
	public List<Channel> query() {
		List<Channel> channelList = new ArrayList<Channel>();
		String selectSql = "select Id, Code,Name,CreateTime from aa_channel where delete_flag <> '1' order by CreateTime desc";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(selectSql);
		for (Map<String,Object> map : list) {
			Channel model = this.wrapModel(map);
			if (null != model) {
				channelList.add(model);
			}
		}
		return channelList;
	}
	
	@Override
	public boolean update(Channel t) {
		String updateSel = "update aa_channel set Name = ?,Code=? where Id = ?";
		return 1 == this.getJdbcTemplate().update(updateSel,t.getName(), t.getCode(), t.getId());
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
		if (map.containsKey("Id") && null != map.get("Id")) {
			id = map.get("Id").toString();
		}
		if (map.containsKey("Code") && null != map.get("Code")) {
			code = map.get("Code").toString();
		}
		if (map.containsKey("Name") && null != map.get("Name")) {
			name = map.get("Name").toString();
		}
		if (map.containsKey("CreateTime") && null != map.get("CreateTime")) {
			model.setCreateTimeStr(map.get("CreateTime").toString());
		}
		
		//这里先只处理不为空的
		if (StringUtils.isEmpty(code) || StringUtils.isEmpty(name)) {
			return null;
		}
		
		model.setCode(code);
		model.setName(name);
		model.setId(id);
		return model;
	}
}
