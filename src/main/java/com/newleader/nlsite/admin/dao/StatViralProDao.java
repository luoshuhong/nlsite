package com.newleader.nlsite.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.model.StatViralPro;
import com.newleader.nlsite.common.CommonUtils;

/**
 * 职业测评 viral统计
 * @author Luoshuhong
 * @Company  
 * 2015年11月2日
 */
public class StatViralProDao extends JdbcDaoSupport {

	/**
	 * 添加
	 * @param model  
	 * @return true or false
	 */
	public boolean add(StatViralPro model) {
		String insertSql = "insert into aa_stat_viral_pro(date,totalShare,totalVisit,totalViral,dnaShare,dnaVisit,dnaViral,listShare,listVisit,listViral) values(?,?,?,?,?,?,?,?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql, 
				new Object[] {model.getDate(), model.getTotalShare(), model.getTotalVisit(), model.getTotalViral(), model.getDnaShare(),
				model.getDnaVisit(), model.getDnaViral(), model.getListShare(), model.getListVisit(), model.getListViral()});
	}
	
	
	/**
	 * 根据时间查询
	 * @param sDate
	 * @param eDate 
	 */
	public List<StatViralPro> queyByDate(String sDate, String eDate) {
		List<StatViralPro> listStat = new ArrayList<StatViralPro>();
		String selSql = "Select id, date,totalShare,totalVisit,totalViral,dnaShare,dnaVisit,dnaViral,listShare,listVisit,listViral"
				+ " from aa_stat_viral_pro where date > ? and date < ?  order by id desc";
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
	private StatViralPro wrapModel(Map<String,Object> map) {
//		id, date,totalShare,totalVisit,totalViral,dnaShare,dnaVisit,dnaViral,listShare,listVisit,listViral
		StatViralPro model = new StatViralPro();
		model.setDate(CommonUtils.getStrFromMap(map, "date"));
		model.setId(CommonUtils.getIntFromMap(map, "id"));
		model.setTotalShare(CommonUtils.getIntFromMap(map, "totalShare"));
		model.setTotalVisit(CommonUtils.getIntFromMap(map, "totalVisit"));
		model.setTotalViral(CommonUtils.getIntFromMap(map, "totalViral"));
		model.setDnaShare(CommonUtils.getIntFromMap(map, "dnaShare"));
		model.setDnaVisit(CommonUtils.getIntFromMap(map, "dnaVisit"));
		model.setDnaViral(CommonUtils.getIntFromMap(map, "dnaViral"));
		model.setListShare(CommonUtils.getIntFromMap(map, "listShare"));
		model.setListVisit(CommonUtils.getIntFromMap(map, "listVisit"));
		model.setListViral(CommonUtils.getIntFromMap(map, "listViral"));
		return model;
	}
	
}
