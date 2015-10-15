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
	
	/**
	 * 更新记录
	 * @param vOpenId		浏览者
	 * @param sOpenId	    分享者
	 * @param vChannelId 分享者渠道编码
	 * @param scene			场景
	 * @return
	 */
	public boolean update(String vOpenId, String sOpenId, String vChannelId, String scene) {
		String updateSql = "update aa_record_virus set sOpenId = ?,channelId=?, createTime = ? where vOpenId = ? and scene = ?";
		return 1 == this.getJdbcTemplate().update(updateSql, new Object[] { sOpenId,vChannelId,DateUtils.toDateStr(new Date()), vOpenId, scene});
	}
	
	/**
	 * 根据浏览者openId和场景者查询
	 * @param openId openid
	 * @param scene   场景者
	 * @return RecordVirus
	 */
	public RecordVirus queryByOpenIdAndScene(String openId, String scene) {
		List<RecordVirus> recordVirusList = new ArrayList<RecordVirus>();
		String selectSql = "select Id, vOpenId,sOpenId,channelId,scene,createTime, isSubscribe from aa_record_virus where vOpenId = ? and scene = ?";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(selectSql, new Object[]{openId, scene});
		for (Map<String,Object> map : list) {
			RecordVirus model = this.wrapModel(map);
			if (null != model) {
				recordVirusList.add(model);
			}
		}
		if (0 != recordVirusList.size()) {
			return recordVirusList.get(0);
		}
		return null;
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
