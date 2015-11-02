package com.newleader.nlsite.admin.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.CommonDao;
import com.newleader.nlsite.admin.dao.StatActionDataDao;
import com.newleader.nlsite.admin.dao.StatMajorDao;
import com.newleader.nlsite.admin.dao.StatResultDao;
import com.newleader.nlsite.admin.model.StatMajor;
import com.newleader.nlsite.admin.model.StatModel;
import com.newleader.nlsite.common.Constants;
import com.newleader.nlsite.common.DateUtils;

/**
 * 供spring-quartz调用
 * @author Luoshuhong
 * @Company  
 * 2015年11月2日
 *
 */
@Service 
public class QuartzService {
	@Autowired
	private StatResultDao statResultDao;
	@Autowired
	private StatActionDataDao statActionDataDao;
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private StatMajorDao statMajorDao;
	
	Logger log = Logger.getLogger("admin");
	
	/**
	 *   统计用户活跃度 
	 */
	public void quartzUserActiveStat() {
		log.info("[quartzUserActiveStat] in  start >> " + DateUtils.getCurrentStringDateYYMDHMSS());
		String nowDate = DateUtils.getNextDay(new Date(), "0", DateUtils.PATTERN_YYYYMMDD);    //当前时间
    	String pre1Date = DateUtils.getNextDay(new Date(), "-1", DateUtils.PATTERN_YYYYMMDD);   //前一天时间
    	String pre7Date = DateUtils.getNextDay(new Date(), "-7", DateUtils.PATTERN_YYYYMMDD);    //前一周时间
    	String pre30Date = DateUtils.getNextDay(new Date(), "-30", DateUtils.PATTERN_YYYYMMDD); //前一月时间
    	
    	log.info("[quartzUserActiveStat]nowDate=" + nowDate + ", pre1Date=" + pre1Date + ",pre7Date=" + pre7Date + ", pre30Date=" + pre30Date);
    	
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
		log.info("[quartzUserActiveStat] out  end >> " + DateUtils.getCurrentStringDateYYMDHMSS());
	} 
	
	/**
	 *   主表信息统计
	 *   (日期	新关注人数(微信） 	取消关注人数(微信）	净增关注人数(微信）累积关注人数(微信）	总用户数（62805+9月23日的每日新关注人数） 职业新增用户	职业累计用户	爱情新增用户	爱情累计用户)
	 */
	public void quartzMajorStat() {
		log.info("[quartzMajorStat] in  start >> " + DateUtils.getCurrentStringDateYYMDHMSS());
		String nowDate = DateUtils.getNextDay(new Date(), "0", DateUtils.PATTERN_YYYYMMDD);    //当前时间
    	String pre1Date = DateUtils.getNextDay(new Date(), "-1", DateUtils.PATTERN_YYYYMMDD);   //前一天时间
    	String pre2Date = DateUtils.getNextDay(new Date(), "-2", DateUtils.PATTERN_YYYYMMDD);   //前一天时间
    	
    	log.info("[quartzMajorStat]nowDate=" + nowDate + ", pre1Date=" + pre1Date );
    	StatMajor model = new StatMajor();
    	int proIncreaseUser = this.commonDao.queryProIncrease(pre1Date, nowDate);  //昨天的职业进入人数
    	int totalLoveUser = this.commonDao.queryLoveTotalUser();								  //爱情累计
    	//查询统计前一天的数据 
    	StatMajor yesterdayModel = this.statMajorDao.queyByDate(pre2Date);
    	model.setDate(pre1Date);
    	model.setLoveTotal(totalLoveUser);
    	model.setLoveIncrease(totalLoveUser - yesterdayModel.getTotalUser());  //爱情新增
    	model.setProIncrease(proIncreaseUser);
    	model.setProTotal(yesterdayModel.getProTotal() + proIncreaseUser);
    	
    	this.statMajorDao.add(model);
    	log.info("[quartzMajorStat] out  end >> " + DateUtils.getCurrentStringDateYYMDHMSS());
	}
	
	
}
