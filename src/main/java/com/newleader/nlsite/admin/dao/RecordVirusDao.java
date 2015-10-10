package com.newleader.nlsite.admin.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.dao.inter.DaoInter;
import com.newleader.nlsite.admin.model.RecordVirus;
import com.newleader.nlsite.common.DateUtils;

/**
 * 
 * @author Luoshuhong
 * @Company  
 * 2015年10月10日
 *
 */
public class RecordVirusDao extends JdbcDaoSupport implements DaoInter<RecordVirus> {
	@Override
	public boolean add(RecordVirus t) {
		String insertSql = "insert into aa_record_virus(vOpenId,sOpenId,channelId,scene,createTime) values(?,?,?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql,
				new Object[] { t.getvOpenId(),t.getsOpenId(),t.getChannelId(),t.getScene(),DateUtils.toDateStr(new Date())});
	}
	
	@Override
	public boolean del(String id) {
		return false;
	}
	
	@Override
	public List<RecordVirus> query() {
		List<RecordVirus> channelList = new ArrayList<RecordVirus>();
		String selectSql = "select Id, vOpenId,sOpenId,channelId,scene,createTime, isSubscribe from aa_record_virus order by CreateTime desc";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(selectSql);
		for (Map<String,Object> map : list) {
			RecordVirus model = this.wrapModel(map);
			if (null != model) {
				channelList.add(model);
			}
		}
		return channelList;
	}
	
	@Override
	public boolean update(RecordVirus t) {
//		String updateSel = "update aa_channel set Name = ?,Code=?,QrcodeUrl=? where Id = ?";
//		return 1 == this.getJdbcTemplate().update(updateSel,t.getName(), t.getCode(), t.getQrCodeUrl(), t.getId());
		return true;
	}
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	private RecordVirus wrapModel(Map<String,Object> map) {
		RecordVirus model = new RecordVirus();
//		Id, vOpenId,sOpenId,channelId,scene,createTime, isSubscribe
		if (map.containsKey("Id") && null != map.get("Id")) {
			model.setId(Integer.valueOf(map.get("Id").toString()));
		}
		if (map.containsKey("vOpenId") && null != map.get("vOpenId")) {
			model.setvOpenId(map.get("vOpenId").toString());
		}
		if (map.containsKey("sOpenId") && null != map.get("sOpenId")) {
			model.setsOpenId(map.get("sOpenId").toString());
		}
		if (map.containsKey("channelId") && null != map.get("channelId")) {
			model.setChannelId(map.get("channelId").toString());
		}
		if (map.containsKey("scene") && null != map.get("scene")) {
			model.setScene(map.get("scene").toString());
		}
		if (map.containsKey("createTime") && null != map.get("createTime")) {
			model.setCreateTime(map.get("createTime").toString());
		}
		if (map.containsKey("isSubscribe") && null != map.get("isSubscribe")) {
			model.setChannelId(map.get("isSubscribe").toString());
		}
		return model;
	}
	
}
