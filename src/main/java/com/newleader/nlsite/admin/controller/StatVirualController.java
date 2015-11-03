package com.newleader.nlsite.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.newleader.nlsite.admin.model.StatViralLove;
import com.newleader.nlsite.admin.model.StatViralPro;
import com.newleader.nlsite.admin.service.VirualStatService;
import com.newleader.nlsite.common.Constants;
import com.newleader.nlsite.common.DateUtils;
import com.newleader.nlsite.common.RequestUtils;

/**
 * virual统计相关
 * @author Luoshuhong
 * @Company donottel.me
 * 2015年9月11日
 *
 */
@Controller
@RequestMapping("/admin/virualStat")
public class StatVirualController {
	Logger log = Logger.getLogger("admin");
	
	@Autowired
	private VirualStatService virualStatService;
	
	@RequestMapping("/query")
    @ResponseBody
    public String query(HttpServletRequest request, HttpServletResponse response){
		String sDate = request.getParameter("startDate");
		String eDate = request.getParameter("endDate");
		//默认查询一周的数据
        if (StringUtils.isEmpty(sDate) || StringUtils.isEmpty(eDate)) {
        	eDate = DateUtils.getNextDay(new Date(), "1", DateUtils.PATTERN_YYYYMMDD);
        	sDate = DateUtils.getNextDay(new Date(), "-7", DateUtils.PATTERN_YYYYMMDD);
        }
        log.info("method=virualStatQuery,sDate=" + sDate + ",eDate=" + eDate);
        
		try {
			String data = this.virualStatService.queryByDate(sDate, eDate);
			return RequestUtils.successReturn(data);
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
	
	@RequestMapping("/queryLoveGroupDate")
    @ResponseBody
    public String queryLoveStatGroupDate(HttpServletRequest request, HttpServletResponse response){
		String sDate = request.getParameter("startDate");
		String eDate = request.getParameter("endDate");
		//默认查询一周的数据
        if (StringUtils.isEmpty(sDate) || StringUtils.isEmpty(eDate)) {
        	eDate = DateUtils.getNextDay(new Date(), "1", DateUtils.PATTERN_YYYYMMDD);
        	sDate = DateUtils.getNextDay(new Date(), "-7", DateUtils.PATTERN_YYYYMMDD);
        }
        log.info("method=queryLoveStat,sDate=" + sDate + ",eDate=" + eDate);
        
		try {
			List<StatViralLove> list = this.virualStatService.queryViralLoveByDate(sDate, eDate);
			//计算合计项
			StatViralLove totalData = new StatViralLove();
			totalData.setDate("合计");
			for (StatViralLove model : list) {
				totalData.setSpiderShare(totalData.getSpiderShare() + model.getSpiderShare());
				totalData.setSpiderVisit(totalData.getSpiderVisit() + model.getSpiderVisit());
				totalData.setSpiderViral(totalData.getSpiderViral() + model.getSpiderViral());
				totalData.setMatchShare(totalData.getMatchShare() + model.getMatchShare());
				totalData.setMatchVisit(totalData.getMatchVisit() + model.getMatchVisit());
				totalData.setMatchViral(totalData.getMatchViral() + model.getMatchViral());
			}
			list.add(totalData);
			return RequestUtils.successReturn(JSONArray.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
	
	@RequestMapping("/queryProGroupDate")
    @ResponseBody
    public String queryProStatGroupDate(HttpServletRequest request, HttpServletResponse response){
		String sDate = request.getParameter("startDate");
		String eDate = request.getParameter("endDate");
		//默认查询一周的数据
        if (StringUtils.isEmpty(sDate) || StringUtils.isEmpty(eDate)) {
        	eDate = DateUtils.getNextDay(new Date(), "1", DateUtils.PATTERN_YYYYMMDD);
        	sDate = DateUtils.getNextDay(new Date(), "-7", DateUtils.PATTERN_YYYYMMDD);
        }
        log.info("method=queryProStat,sDate=" + sDate + ",eDate=" + eDate);
        
		try {
			List<StatViralPro> list = this.virualStatService.queryViralProByDate(sDate, eDate);
			//计算合计项
			StatViralPro totalData = new StatViralPro();
			totalData.setDate("合计");
			for (StatViralPro model : list) {
				totalData.setDnaShare(totalData.getDnaShare() + model.getDnaShare());
				totalData.setDnaVisit(totalData.getDnaVisit() + model.getDnaVisit());
				totalData.setDnaViral(totalData.getDnaViral() + model.getDnaViral());
				totalData.setListShare(totalData.getListShare() + model.getListShare());
				totalData.setListVisit(totalData.getListVisit() + model.getListVisit());
				totalData.setListViral(totalData.getListViral() + model.getListViral());
			}
			list.add(totalData);
			
			return RequestUtils.successReturn(JSONArray.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
	
	@RequestMapping("/queryLoveGroupChannel")
    @ResponseBody
    public String queryLoveStatGroupChannel(HttpServletRequest request, HttpServletResponse response){
		try {
			List<StatViralLove> list = Constants.statViralLove;
			//计算合计项
			StatViralLove totalData = new StatViralLove();
			totalData.setDate("合计");
			for (StatViralLove model : list) {
				totalData.setTotalShare(totalData.getTotalShare() + model.getTotalShare());
				totalData.setTotalVisit(totalData.getTotalVisit() + model.getTotalVisit());
				totalData.setTotalViral(totalData.getTotalViral() + model.getTotalViral());
				
				totalData.setSpiderShare(totalData.getSpiderShare() + model.getSpiderShare());
				totalData.setSpiderVisit(totalData.getSpiderVisit() + model.getSpiderVisit());
				totalData.setSpiderViral(totalData.getSpiderViral() + model.getSpiderViral());
				totalData.setMatchShare(totalData.getMatchShare() + model.getMatchShare());
				totalData.setMatchVisit(totalData.getMatchVisit() + model.getMatchVisit());
				totalData.setMatchViral(totalData.getMatchViral() + model.getMatchViral());
			}
			list.add(totalData);
			return RequestUtils.successReturn(JSONArray.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
	
	@RequestMapping("/queryProGroupChannel")
    @ResponseBody
    public String queryProStatGroupChannel(HttpServletRequest request, HttpServletResponse response){
        
		try {
			List<StatViralPro> list = Constants.statViralPro;
			//计算合计项
			StatViralPro totalData = new StatViralPro();
			totalData.setDate("合计");
			for (StatViralPro model : list) {
				totalData.setTotalShare(totalData.getTotalShare() + model.getTotalShare());
				totalData.setTotalVisit(totalData.getTotalVisit() + model.getTotalVisit());
				totalData.setTotalViral(totalData.getTotalViral() + model.getTotalViral());
				
				totalData.setDnaShare(totalData.getDnaShare() + model.getDnaShare());
				totalData.setDnaVisit(totalData.getDnaVisit() + model.getDnaVisit());
				totalData.setDnaViral(totalData.getDnaViral() + model.getDnaViral());
				totalData.setListShare(totalData.getListShare() + model.getListShare());
				totalData.setListVisit(totalData.getListVisit() + model.getListVisit());
				totalData.setListViral(totalData.getListViral() + model.getListViral());
			}
			list.add(totalData);
			return RequestUtils.successReturn(JSONArray.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
}
