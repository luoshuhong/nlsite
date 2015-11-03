package com.newleader.nlsite.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.model.StatViralLove;
import com.newleader.nlsite.common.CommonUtils;

/**
 * 爱情测评 viral统计
 * @author Luoshuhong
 * @Company  
 * 2015年11月2日
 */
public class StatViralLoveDao extends JdbcDaoSupport {

	/**
	 * 添加
	 * @param model  
	 * @return true or false
	 */
	public boolean add(StatViralLove model) {
		String insertSql = "insert into aa_stat_viral_love(date,totalShare,totalVisit,totalViral,spiderShare,spiderVisit,spiderViral,matchShare,matchVisit,matchViral) values(?,?,?,?,?,?,?,?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql, 
				new Object[] {model.getDate(), model.getTotalShare(), model.getTotalVisit(), model.getTotalViral(), model.getSpiderShare(),
				model.getSpiderVisit(), model.getSpiderViral(), model.getMatchShare(), model.getMatchVisit(), model.getMatchViral()});
	}
	
	
	/**
	 * 根据时间查询
	 * @param sDate
	 * @param eDate 
	 */
	public List<StatViralLove> queyByDate(String sDate, String eDate) {
		List<StatViralLove> listStat = new ArrayList<StatViralLove>();
		String selSql = "Select id, date,totalShare,totalVisit,totalViral,spiderShare,spiderVisit,spiderViral,matchShare,matchVisit,matchViral"
				+ " from aa_stat_viral_love where date > ? and date < ?  order by id desc";
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
	private StatViralLove wrapModel(Map<String,Object> map) {
//		id, date,totalShare,totalVisit,totalViral,spiderShare,spiderVisit,spiderViral,matchShare,matchVisit,matchViral
		StatViralLove model = new StatViralLove();
		model.setDate(CommonUtils.getStrFromMap(map, "date"));
		model.setId(CommonUtils.getIntFromMap(map, "id"));
		model.setTotalShare(CommonUtils.getIntFromMap(map, "totalShare"));
		model.setTotalVisit(CommonUtils.getIntFromMap(map, "totalVisit"));
		model.setTotalViral(CommonUtils.getIntFromMap(map, "totalViral"));
		model.setSpiderShare(CommonUtils.getIntFromMap(map, "spiderShare"));
		model.setSpiderVisit(CommonUtils.getIntFromMap(map, "spiderVisit"));
		model.setSpiderViral(CommonUtils.getIntFromMap(map, "spiderViral"));
		model.setMatchShare(CommonUtils.getIntFromMap(map, "matchShare"));
		model.setMatchVisit(CommonUtils.getIntFromMap(map, "matchVisit"));
		model.setMatchViral(CommonUtils.getIntFromMap(map, "matchViral"));
		return model;
	}
	
}
