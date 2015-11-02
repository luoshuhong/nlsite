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
import com.newleader.nlsite.admin.model.StatMajor;
import com.newleader.nlsite.admin.service.StatMajorService;
import com.newleader.nlsite.common.CommonUtils;
import com.newleader.nlsite.common.DateUtils;
import com.newleader.nlsite.common.RequestUtils;

/**
 * 总表统计相关
 * （日期	新关注人数(微信） 	取消关注人数(微信）	净增关注人数(微信） 累积关注人数(微信） 总用户数（62805+9月23日的每日新关注人数） 
 *  职业新增用户	  职业累计用户	爱情新增用户	 爱情累计用户）
 * @author Luoshuhong
 * @Company donottel.me
 * 2015年11月2日
 *
 */
@Controller
@RequestMapping("/admin/majorStat")
public class StatMajorController {
	Logger log = Logger.getLogger("admin");
	
	@Autowired
	private StatMajorService statMajorService;
	
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
        log.info("[StatMajorController] method=statQuery,sDate=" + sDate + ",eDate=" + eDate);
        
		try {
			List<StatMajor> list = this.statMajorService.queryByDate(sDate, eDate);
			return RequestUtils.successReturn(JSONArray.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
	
	@RequestMapping("/update")
    @ResponseBody
    public String update(HttpServletRequest request, HttpServletResponse response){
		StatMajor statMajor = new StatMajor();
		String date =  request.getParameter("date");
		int wxTotal = CommonUtils.praseStr2Int(request.getParameter("wxTotal"));
		int wxSubscribe = CommonUtils.praseStr2Int(request.getParameter("wxSubscribe"));
		int wxUnsubscribe = CommonUtils.praseStr2Int(request.getParameter("wxUnsubscribe"));
		int wxPureSubscribe = CommonUtils.praseStr2Int(request.getParameter("wxPureSubscribe"));
		int totalUser = CommonUtils.praseStr2Int(request.getParameter("totalUser"));
		
		//默认查询一周的数据
        log.info("[StatMajorController]  method=updateStatMajor,date=" + date + ",wxTotal=" + wxTotal + ",wxSubscribe=" + wxSubscribe + 
        		",wxUnsubscribe=" + wxUnsubscribe + ",wxTotal=" + wxTotal + ",wxPureSubscribe=" + wxPureSubscribe + ",totalUser=" + totalUser);
        
        statMajor.setDate(date);
        statMajor.setWxTotal(wxTotal);
        statMajor.setWxPureSubscribe(wxPureSubscribe);
        statMajor.setWxSubscribe(wxSubscribe);
        statMajor.setWxUnsubscribe(wxUnsubscribe);
        statMajor.setTotalUser(totalUser);
        
		try {
			this.statMajorService.update(statMajor);
			return RequestUtils.successReturn(JSONArray.toJSONString(true));
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
	
}
