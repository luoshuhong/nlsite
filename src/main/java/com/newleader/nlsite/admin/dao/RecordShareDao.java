package com.newleader.nlsite.admin.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.dao.inter.DaoInter;
import com.newleader.nlsite.admin.model.RecordShare;

/**
 * 
 * @author Luoshuhong
 * @Company  
 * 2015年10月10日
 *
 */
public class RecordShareDao extends JdbcDaoSupport implements DaoInter<RecordShare> {
	@Override
	public boolean add(RecordShare t) {
		String insertSql = "insert into aa_record_share(openId,scene,createTime,superId) values(?,?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql,	
				new Object[] {t.getOpenId(),t.getScene(),new Date(),t.getSuperId()});
	}
	
	/**
	 * @param scene    场景id
	 * @param openId  openId
	 * @return
	 */
	public int queryBySceneOpenId(String scene, String openId) {
		String selectSql = "select count(Id) from aa_record_share where openId = ? and scene = ?";
		return this.getJdbcTemplate().queryForInt(selectSql, new Object[]{openId, scene});
	}
	
	
	
	@Override
	public boolean del(String id) {
		return false;
	}
	
	@Override
	public List<RecordShare> query() {
		List<RecordShare> channelList = new ArrayList<RecordShare>();
		String selectSql = "select Id, openId,scene,createTime,superId, count from aa_record_share order by CreateTime desc";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(selectSql);
		for (Map<String,Object> map : list) {
			RecordShare model = this.wrapModel(map);
			if (null != model) {
				channelList.add(model);
			}
		}
		return channelList;
	}
	
	@Override
	public boolean update(RecordShare t) {
//		String updateSel = "update aa_channel set Name = ?,Code=?,QrcodeUrl=? where Id = ?";
//		return 1 == this.getJdbcTemplate().update(updateSel,t.getName(), t.getCode(), t.getQrCodeUrl(), t.getId());
		return true;
	}
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	private RecordShare wrapModel(Map<String,Object> map) {
		RecordShare model = new RecordShare();
//		Id, openId,scene,createTime,superId, count
		if (map.containsKey("Id") && null != map.get("Id")) {
			model.setId(Integer.valueOf(map.get("Id").toString()));
		}
		if (map.containsKey("openId") && null != map.get("openId")) {
			model.setOpenId(map.get("openId").toString());
		}
		if (map.containsKey("scene") && null != map.get("scene")) {
			model.setScene(map.get("scene").toString());
		}
		if (map.containsKey("createTime") && null != map.get("createTime")) {
			model.setCreateTime(map.get("createTime").toString());
		}
		if (map.containsKey("superId") && null != map.get("superId")) {
			model.setSuperId(Integer.valueOf(map.get("superId").toString()));
		}
		if (map.containsKey("count") && null != map.get("count")) {
			model.setCount(Integer.valueOf(map.get("count").toString()));
		}
		
		return model;
	}
	
}
