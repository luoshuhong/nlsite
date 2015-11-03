package com.newleader.nlsite.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.ChannelDao;
import com.newleader.nlsite.admin.dao.CommonDao;
import com.newleader.nlsite.admin.dao.StatActionDataDao;
import com.newleader.nlsite.admin.dao.StatMajorDao;
import com.newleader.nlsite.admin.dao.StatResultDao;
import com.newleader.nlsite.admin.dao.StatViralLoveDao;
import com.newleader.nlsite.admin.dao.StatViralProDao;
import com.newleader.nlsite.admin.dao.VirualStatDao;
import com.newleader.nlsite.admin.model.StatMajor;
import com.newleader.nlsite.admin.model.StatModel;
import com.newleader.nlsite.admin.model.StatViralLove;
import com.newleader.nlsite.admin.model.StatViralPro;
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
	@Autowired
	private StatViralLoveDao statViralLoveDao;
	@Autowired
	private StatViralProDao statViralProDao;
	@Autowired
	private VirualStatDao virualStatDao;
	@Autowired
	private ChannelDao channelDao;
	
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
	
	/**
	 *   Viral统计(按时间维度)
	 */
	public void quartzViralGroupDateStat() {
		try {
			log.info("[quartzViralGroupDateStat] in  start >> " + DateUtils.getCurrentStringDateYYMDHMSS());
			String nowDate = DateUtils.getNextDay(new Date(), "0", DateUtils.PATTERN_YYYYMMDD);    //当前时间
	    	String pre1Date = DateUtils.getNextDay(new Date(), "-1", DateUtils.PATTERN_YYYYMMDD);   //前一天时间
	    	
	    	log.info("[quartzViralGroupDateStat]nowDate=" + nowDate + ", pre1Date=" + pre1Date );
	    	
	    	//分享量
	    	Map<String,Integer> mapShare = this.switchListToMap(this.virualStatDao.queryShareStat(pre1Date, nowDate ));
	    	Map<String,Integer> mapShareTotal = this.switchListToMap(this.virualStatDao.queryShareStat());//累计
	    	//浏览量
	    	Map<String,Integer> mapVisit = this.switchListToMap(this.virualStatDao.queryVisitShareStat(pre1Date, nowDate));
	    	Map<String,Integer> mapVisitTotal = this.switchListToMap(this.virualStatDao.queryVisitShareStat());//累计
	    	//Viral用户量
	    	Map<String,Integer> mapViral = this.switchListToMap(this.virualStatDao.queryVirulUserStat(pre1Date, nowDate));
	    	Map<String,Integer> mapViralTotal = this.switchListToMap(this.virualStatDao.queryVirulUserStat()); //累计
	    	
	    	//职业Viral
	    	StatViralPro statViralPro = new StatViralPro();
	    	statViralPro.setDate(pre1Date);
	    	statViralPro.setTotalShare(mapShareTotal.get("ranking_list") + mapShareTotal.get("dna"));
	    	statViralPro.setTotalVisit(mapVisitTotal.get("ranking_list") + mapVisitTotal.get("dna"));
	    	statViralPro.setTotalViral(mapViralTotal.get("ranking_list") + mapViralTotal.get("dna"));
	    	statViralPro.setDnaShare(mapShare.get("dna"));
	    	statViralPro.setDnaVisit(mapVisit.get("dna"));
	    	statViralPro.setDnaViral(mapViral.get("dna"));
	    	statViralPro.setListShare(mapShare.get("ranking_list"));
	    	statViralPro.setListVisit(mapVisit.get("ranking_list"));
	    	statViralPro.setListViral(mapViral.get("ranking_list"));
	    	this.statViralProDao.add(statViralPro);
	    	
	    	//爱情Viral
	    	StatViralLove statViralLove = new StatViralLove();
	    	statViralLove.setDate(pre1Date);
	    	statViralLove.setTotalShare(mapShareTotal.get("spider") + mapShareTotal.get("match"));
	    	statViralLove.setTotalVisit(mapVisitTotal.get("spider") + mapVisitTotal.get("match"));
	    	statViralLove.setTotalViral(mapViralTotal.get("spider") + mapViralTotal.get("match"));
	    	statViralLove.setSpiderShare(mapShare.get("spider"));
	    	statViralLove.setSpiderVisit(mapVisit.get("spider"));
	    	statViralLove.setSpiderViral(mapViral.get("spider"));
	    	statViralLove.setMatchShare(mapShare.get("match"));
	    	statViralLove.setMatchVisit(mapVisit.get("match"));
	    	statViralLove.setMatchViral(mapViral.get("match"));
	    	this.statViralLoveDao.add(statViralLove);
	    	log.info("[quartzViralGroupDateStat] out  end >> " + DateUtils.getCurrentStringDateYYMDHMSS());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 *   Viral统计(按渠道维度统计)
	 */
	public void quartzViralGroupChannelStat() {
		try {
			log.info("[quartzViralGroupChannelStat] in  start >> " + DateUtils.getCurrentStringDateYYMDHMSS());
	    	List<String> channelCodeList = this.channelDao.queryChannelCode();
	    	List<StatViralLove> statViralLoveList = new ArrayList<StatViralLove>();  //爱情统计
	    	List<StatViralPro> statViralProList = new ArrayList<StatViralPro>();		 //职业统计
	    	for (String code : channelCodeList) {
	    		//分享量
		    	Map<String,Integer> mapShare = this.switchListToMap(this.virualStatDao.queryShareStat(code));//累计
		    	//浏览量
		    	Map<String,Integer> mapVisit = this.switchListToMap(this.virualStatDao.queryVisitShareStat(code));//累计
		    	//Viral用户量
		    	Map<String,Integer> mapViral = this.switchListToMap(this.virualStatDao.queryVirulUserStat(code)); //累计
		    	
		    	StatViralPro statViralPro = new StatViralPro();
		    	statViralPro.setDate(code);
		    	statViralPro.setTotalShare(mapShare.get("ranking_list") + mapShare.get("dna"));
		    	statViralPro.setTotalVisit(mapVisit.get("ranking_list") + mapVisit.get("dna"));
		    	statViralPro.setTotalViral(mapViral.get("ranking_list") + mapViral.get("dna"));
		    	statViralPro.setDnaShare(mapShare.get("dna"));
		    	statViralPro.setDnaVisit(mapVisit.get("dna"));
		    	statViralPro.setDnaViral(mapViral.get("dna"));
		    	statViralPro.setListShare(mapShare.get("ranking_list"));
		    	statViralPro.setListVisit(mapVisit.get("ranking_list"));
		    	statViralPro.setListViral(mapViral.get("ranking_list"));
		    	
		    	StatViralLove statViralLove = new StatViralLove();
		    	statViralLove.setDate(code);
		    	statViralLove.setTotalShare(mapShare.get("spider") + mapShare.get("match"));
		    	statViralLove.setTotalVisit(mapVisit.get("spider") + mapVisit.get("match"));
		    	statViralLove.setTotalViral(mapViral.get("spider") + mapViral.get("match"));
		    	statViralLove.setSpiderShare(mapShare.get("spider"));
		    	statViralLove.setSpiderVisit(mapVisit.get("spider"));
		    	statViralLove.setSpiderViral(mapViral.get("spider"));
		    	statViralLove.setMatchShare(mapShare.get("match"));
		    	statViralLove.setMatchVisit(mapVisit.get("match"));
		    	statViralLove.setMatchViral(mapViral.get("match"));
		    	
		    	statViralLoveList.add(statViralLove);
		    	statViralProList.add(statViralPro);
	    	}
	    	
	    	//放在本地缓存
	    	Constants.statViralLove = statViralLoveList;
	    	Constants.statViralPro = statViralProList;
	    	log.info("[quartzViralGroupChannelStat] out  end >> " + DateUtils.getCurrentStringDateYYMDHMSS());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***************************** 下面是本类 私有方法  ****************************************/
	/**
	 *  Viral 统计时用 
	 * @param list
	 * @return
	 */
	private Map<String,Integer> switchListToMap(List<StatModel> list) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("ranking_list", 0);
		map.put("dna", 0);
		map.put("spider", 0);
		map.put("match", 0);
		
		for (StatModel model : list) {
			if ("rainking_list".equals(model.getStatItem()) || "ranking_list".equals(model.getStatItem())) {
				map.put("ranking_list", model.getCount());
			}
			if ("dna".equals(model.getStatItem())) {
				map.put("dna", model.getCount());
			}
			if ("match".equals(model.getStatItem())) {
				map.put("match",  model.getCount());
			}
			if ("spider".equals(model.getStatItem())) {
				map.put("spider", model.getCount());
			}
		}
		return map;
	}
	
	
	
}
