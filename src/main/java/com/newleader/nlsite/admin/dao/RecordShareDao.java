package com.newleader.nlsite.admin.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.dao.inter.DaoInter;
import com.newleader.nlsite.admin.model.RecordShare;
import com.newleader.nlsite.common.DateUtils;

/**
 * 
 * @author Luoshuhong
 * @Company  
 * 2015年10月10日
 *
 */
public class RecordShareDao extends JdbcDaoSupport implements DaoInter<RecordShare> {
//	 `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
//	  `openId` varchar(50) DEFAULT NULL COMMENT '分享者id',
//	  `scene` varchar(50) DEFAULT NULL COMMENT '场景',
//	  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
//	  `superId` int(10) DEFAULT '-1' COMMENT '上一级分享id',
//	  `count` int(11) DEFAULT '0' COMMENT '下级分享次数',
	  
	@Override
	public boolean add(RecordShare t) {
		String insertSql = "insert into aa_record_share(openId,scene,createTime,superId) values(?,?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql,
				new Object[] {t.getOpenId(),t.getScene(),DateUtils.toDateStr(new Date()),t.getSuperId()});
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
