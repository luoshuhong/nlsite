package com.newleader.nlsite.admin.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.model.StatModel;

/**
 * 用户活跃度统计结果
 * @author Luoshuhong
 * @Company  
 * 2015年10月16日
 */
public class StatResultDao extends JdbcDaoSupport {
//	  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
//	  `num` int(10) DEFAULT '0' COMMENT '数量',
//	  `date` varchar(20) DEFAULT '' COMMENT '时间',
//	  `type` varchar(255) DEFAULT '' COMMENT '类型',
//	  `channelId` varchar(50) DEFAULT '' COMMENT '渠道编码',
//	  `product` varchar(50) DEFAULT '' COMMENT '产品',
	  
	public boolean add(StatModel model) {
		String insertSql = "insert into aa_stat_result(num,date,type,channelId,product) values(?,?,?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql, 
				new Object[] {model.getCount(), model.getDate(), model.getType(), model.getChannelId(), model.getProduct()});
	}
	
	/**
	 * 查询某一段时间所有统计
	 * @param sDate
	 * @param eDate 
	 */
	public List<StatModel> queyByOpenId(Date sDate, Date eDate) {
		List<StatModel> listStat = new ArrayList<StatModel>();
		String selSql = "Select id, num,date,type,channelId,product from aa_stat_result where date > ? and date < ?";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(selSql, new Object[]{sDate, eDate});
		for (Map<String,Object> map : list) {
			listStat.add(this.wrapModel(map));
		}
		return listStat;
	}
	
	/**
	 * @param map
	 * @return
	 */
	private StatModel wrapModel(Map<String,Object> map) {
//		num,date,type,channelId,product
		StatModel model = new StatModel();
		if (map.containsKey("num") && null != map.get("num")) {
			model.setCount(Integer.valueOf(map.get("num").toString()));
		}
		if (map.containsKey("date") && null != map.get("date")) {
			model.setDate(map.get("date").toString());
		}
		if (map.containsKey("type") && null != map.get("type")) {
			model.setType(map.get("type").toString());
		}
		if (map.containsKey("channelId") && null != map.get("channelId")) {
			model.setChannelId(map.get("channelId").toString());
		}
		if (map.containsKey("product") && null != map.get("product")) {
			model.setProduct(map.get("product").toString());
		}
		return model;
	}
	
}
