package com.newleader.nlsite.admin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newleader.nlsite.admin.service.ChannelStatService;
import com.newleader.nlsite.common.DateUtils;
import com.newleader.nlsite.common.RequestUtils;

/**
 * 渠道推广统计相关
 * @author Luoshuhong
 * @Company donottel.me
 * 2015年9月11日
 *
 */
@Controller
@RequestMapping("/admin/channelStat")
public class ChannelStatController {
	Logger log = Logger.getLogger("admin");
	
	@Autowired
	private ChannelStatService channelStatService;
	
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
        log.info("method=statQuery,sDate=" + sDate + ",eDate=" + eDate);
        
		try {
			String data = channelStatService.queryByChannelCode(sDate, eDate);
			return RequestUtils.successReturn(data);
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
}
