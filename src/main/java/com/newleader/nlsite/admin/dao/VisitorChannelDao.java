package com.newleader.nlsite.admin.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.dao.inter.DaoInter;
import com.newleader.nlsite.admin.model.VisitorChannel;

/**
 * 用户关注/取消关注 渠道记录
 * @author Luoshuhong
 * @Company  
 * 2015年10月15日
 *
 */
public class VisitorChannelDao extends JdbcDaoSupport implements DaoInter<VisitorChannel> {
	@Override
	public boolean add(VisitorChannel t) {
		String insertSql = "insert into aa_visitor_channel(id,openId,channelId,createTime,level) values(?,?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql, new Object[] {t.getId(), t.getOpenId(), t.getChannelId(), new Date() , t.getLeavel()});
	}
	
	/**
	 * 根据openId查询 是否存在
	 * @param openId  openId
	 * @return 个数
	 */
	@SuppressWarnings("deprecation")
	public int isUserExist(String openId) {
		String selSql = "select count(id) from aa_visitor_channel where openId = ?";
		return this.getJdbcTemplate().queryForInt(selSql, new Object[]{openId});
	}
	
	@Override
	public boolean del(String id) {
		return true;
	}
	
	@Override
	public List<VisitorChannel> query() {
		return null;
	}
	
	/**
	 * 查询用户所属渠道编码
	 * @param openId  openId
	 * @return 渠道编码
	 */
	public VisitorChannel queyByOpenId(String openId) {
		String selSql = "Select id, openId, level,channelId, createTime, isBind from aa_visitor_channel where openId = ? and isBind in (0,2) order by createTime desc";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(selSql, new Object[]{openId});
		for (Map<String,Object> map : list) {
			VisitorChannel model = this.wrapModel(map);
			if (null != model) {
				return model;
			}
		}
		return null;
	}
	
	@Override
	public boolean update(VisitorChannel t) {
		return true;
	}
	
	/**
	 * 更新渠道关注量
	 * @param totalSubscribe
	 * @param unSubscribe
	 * @param code
	 * @return
	 */
	public boolean updateByCode(int totalSubscribe, int unSubscribe, String code) {
		return true;
	}
	
	/**
	 * @param map
	 * @return
	 */
	private VisitorChannel wrapModel(Map<String,Object> map) {
		VisitorChannel model = new VisitorChannel();
		if (map.containsKey("id") && null != map.get("id")) {
			model.setId(map.get("id").toString());
		}
		if (map.containsKey("openId") && null != map.get("openId")) {
			model.setOpenId(map.get("openId").toString());
		}
		if (map.containsKey("channelId") && null != map.get("channelId")) {
			model.setChannelId(map.get("channelId").toString());
		}
		if (map.containsKey("level") && null != map.get("level")) {
			model.setLeavel(Integer.valueOf(map.get("level").toString()));
		}
		if (map.containsKey("isBind") && null != map.get("isBind")) {
			model.setIsBind(Integer.valueOf(map.get("isBind").toString()));
		}
		return model;
	}
}
