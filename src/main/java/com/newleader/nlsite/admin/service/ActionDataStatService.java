package com.newleader.nlsite.admin.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.newleader.nlsite.admin.dao.CommonDao;
import com.newleader.nlsite.admin.dao.StatActionDataDao;
import com.newleader.nlsite.admin.dao.StatResultDao;
import com.newleader.nlsite.admin.dao.StatUserActiveDao;
import com.newleader.nlsite.admin.dao.VisitorChannelDao;
import com.newleader.nlsite.admin.model.StatActionData;
import com.newleader.nlsite.admin.model.StatModel;
import com.newleader.nlsite.admin.model.VisitorChannel;
import com.newleader.nlsite.common.Constants;
import com.newleader.nlsite.common.DateUtils;
import com.newleader.nlsite.common.HighChartsUtils;

/**
 * 统计原始数据
 * @author Luoshuhong
 * @Company  
 * 2015年10月21日
 *
 */
@Service 
public class ActionDataStatService {
	Logger log = Logger.getLogger("admin");
	@Autowired
	private StatActionDataDao statActionDataDao;
	@Autowired
	private VisitorChannelDao visitorChannelDao;
	@Autowired
	private StatUserActiveDao statUserActiveDao;
	@Autowired
	private StatResultDao statResultDao;
	@Autowired
	private CommonDao commonDao;
	
	/**
	 * 保存
	 * @param model StatOrgData
	 * @return 
	 */
	public boolean add(StatActionData model) {
		//查询 channelId		
		VisitorChannel visitorChannel = this.visitorChannelDao.queyByOpenId(model.getOpenId());
		if (null == visitorChannel) {
			model.setChannelId("default");
		}  else {
			model.setChannelId(visitorChannel.getChannelId());
		}
		//查询上一次操作记录
		StatActionData statActionData = this.statActionDataDao.queyByOpenId(model.getOpenId());  
		if (null == statActionData) {
			model.setInterval(0);
		} else {
			//计算两次操作之间的间隔时间 秒
			model.setInterval((new Date().getTime() - statActionData.getCreateTime().getTime()) / 1000); 
		}
		//记录操作记录
		this.statActionDataDao.add(model);
		
		//记录用户活跃度 操作表
		if (this.statUserActiveDao.queyByOpenId30Min(model.getOpenId()) >= 1) {
			return true;
		}
		return this.statUserActiveDao.add(model);
	}
	
	/**
	 * 按渠道分组查询
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return json 串
	 */
	public String queryStatResultByDate(String sDate, String eDate) {
		List<StatModel> uVesult = this.statResultDao.queyUVByDate(sDate, eDate);
		List<StatModel> pVesult = this.statResultDao.queyPVByDate(sDate, eDate);
		
		JSONObject job = new JSONObject();
		job.put("uv", HighChartsUtils.dealHightChartsData(uVesult));
		job.put("pv", HighChartsUtils.dealHightChartsData(pVesult));
		return job.toJSONString();
	}
	
	/**
	 * 查询一些累计统计
	 * @return
	 */
	public String query() {
		//#填了毕业信息 
		String sql1 = "select count(id) as count from aa_userinfo a where a.ReadingSchool is not null or a.ReadingSchool != '' ";
		//有多少注册用户
		String sql2 = "select count(id) as count from aa_userinfo";
		//#多少个用户答完了爱情的题
		String sql3 = "select count(*) as count from if_testing_result";
		//明星DNA
		String sql4 = "select count(a.Id) from aa_userinfo a, qa_disc_answer_paper b where a.Id=b.AnswerPersonId" ;
		//使命魔镜
		String sql5 = "select count(a.Id) from aa_userinfo a, qa_values_answer_paper b where a.Id=b.AnswerPersonId" ;
		//特长魔镜
		String sql6 = "select count(a.Id) from aa_userinfo a, qa_behavior_answer_paper b where a.Id=b.AnswerPersonId" ;
		//力量之镜
		String sql7 = "select count(a.Id) from aa_userinfo a, qa_motivation_answer_paper b where a.Id=b.AnswerPersonId" ;
		//职业报警
		String sql8 = "select count(a.Id) from aa_userinfo a, qa_passion_answer_paper b where a.Id=b.AnswerPersonId" ;
		//累计进入爱情人数
		String sql9 = "select count(Id) from aa_userinfo where nick <> ''";
		JSONObject jobRes = new JSONObject();
		jobRes.put("school", commonDao.queryCount(sql1));
		jobRes.put("regist", commonDao.queryCount(sql2));
		jobRes.put("love", commonDao.queryCount(sql3));
		jobRes.put("dna", commonDao.queryCount(sql4));
		jobRes.put("value", commonDao.queryCount(sql5));
		jobRes.put("behavior", commonDao.queryCount(sql6));
		jobRes.put("motivation", commonDao.queryCount(sql7));
		jobRes.put("passion", commonDao.queryCount(sql8));
		jobRes.put("loveInit", commonDao.queryCount(sql9));
		return jobRes.toJSONString();
	}
	
	
	/**
	 *  定时执行方法 (每天晚上跑定时)
	 *  每天统计用户活跃度 
	 */
	public void quartzStat() {
		log.info("[statActionData] in  start >> " + DateUtils.getCurrentStringDateYYMDHMSS());
		String nowDate = DateUtils.getNextDay(new Date(), "0", DateUtils.PATTERN_YYYYMMDD);    //当前时间
    	String pre1Date = DateUtils.getNextDay(new Date(), "-1", DateUtils.PATTERN_YYYYMMDD);   //前一天时间
    	String pre7Date = DateUtils.getNextDay(new Date(), "-7", DateUtils.PATTERN_YYYYMMDD);    //前一周时间
    	String pre30Date = DateUtils.getNextDay(new Date(), "-30", DateUtils.PATTERN_YYYYMMDD); //前一月时间
    	
    	log.info("[statActionData]nowDate=" + nowDate + ", pre1Date=" + pre1Date + ",pre7Date=" + pre7Date + ", pre30Date=" + pre30Date);
    	
		//1.UV 统计
		//1.1 日UV统计
		List<StatModel> uvList = this.statActionDataDao.getUVByDate(nowDate, pre1Date, Constants.STAT_TYPE_UV_D, pre1Date);
		this.statResultDao.add(uvList);
		//1.2 周UV统计
		List<StatModel> uv7List = this.statActionDataDao.getUVByDate(nowDate, pre7Date, Constants.STAT_TYPE_UV_W, pre1Date);
		this.statResultDao.add(uv7List);
		//1.3 月UV统计
		List<StatModel> uv30List = this.statActionDataDao.getUVByDate(nowDate, pre30Date, Constants.STAT_TYPE_UV_M, pre1Date);
		this.statResultDao.add(uv30List);
		
		//2.PV统计（30分钟内算一次）
		//2.1日PV
		List<StatModel> pvList = this.statActionDataDao.getPVByDate(nowDate, pre1Date, Constants.STAT_TYPE_PV_30MIN_D, pre1Date);
		this.statResultDao.add(pvList);
		//2.2周PV
		List<StatModel> pv7List = this.statActionDataDao.getPVByDate(nowDate, pre7Date, Constants.STAT_TYPE_PV_30MIN_W, pre1Date);
		this.statResultDao.add(pv7List);
		//2.3周PV
		List<StatModel> pv30List = this.statActionDataDao.getPVByDate(nowDate, pre30Date, Constants.STAT_TYPE_PV_30MIN_M, pre1Date);
		this.statResultDao.add(pv30List);
		
		//3.
		log.info("[statActionData] out  end >> " + DateUtils.getCurrentStringDateYYMDHMSS());
	} 
}


