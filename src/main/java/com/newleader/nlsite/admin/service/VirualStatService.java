package com.newleader.nlsite.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.newleader.nlsite.admin.dao.StatViralLoveDao;
import com.newleader.nlsite.admin.dao.StatViralProDao;
import com.newleader.nlsite.admin.dao.VirualStatDao;
import com.newleader.nlsite.admin.model.StatModel;
import com.newleader.nlsite.admin.model.StatViralLove;
import com.newleader.nlsite.admin.model.StatViralPro;
import com.newleader.nlsite.common.HighChartsUtils;

/**
 * Virual统计相关操作
 * @author Luoshuhong
 * @Company  
 * 2015年10月16日
 *
 */
@Service 
public class VirualStatService {
	
	@Autowired
	private VirualStatDao virualStatDao;
	@Autowired
	private StatViralProDao statViralProDao;
	@Autowired
	private StatViralLoveDao statViralLoveDao;
	/**
	 * 按场景分组查询
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return json 串
	 */
	public String queryByDate(String sDate, String eDate) {
		List<StatModel> shareStatList = this.virualStatDao.queryShareStat(sDate, eDate);       //分享数据
		List<StatModel> visitShareList = this.virualStatDao.queryVisitShareStat(sDate, eDate); //分享浏览数据
		List<StatModel> virulUserList = this.virualStatDao.queryVirulUserStat(sDate, eDate); //分享浏览数据
		
		JSONObject job = new JSONObject();
		job.put("shareData", HighChartsUtils.dealHightChartsData(shareStatList));
		job.put("visitShareData", HighChartsUtils.dealHightChartsData(visitShareList));
		job.put("virulUserData", HighChartsUtils.dealHightChartsData(virulUserList));
		return job.toJSONString();
	}
	
	/**
	 * 查询按时间统计维度结果  (职业测评)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 */
	public List<StatViralPro> queryViralProByDate(String sDate, String eDate) {
		return this.statViralProDao.queyByDate(sDate, eDate);
	}
	
	/**
	 * 查询按时间统计维度结果 (爱情测评)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 */
	public List<StatViralLove> queryViralLoveByDate(String sDate, String eDate) {
		return this.statViralLoveDao.queyByDate(sDate, eDate);
	}
}
