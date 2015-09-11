package com.newleader.nlsite.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 渠道相关
 * @author Luoshuhong
 * @Company donottel.me
 * 2015年9月11日
 *
 */
@Controller
@RequestMapping("/channel")
public class ChannelController {
	@RequestMapping("/add")
    @ResponseBody
    public void add(HttpServletRequest request, HttpServletResponse response){
		String channelName = request.getParameter("channelName");
		String channelCode = request.getParameter("channelCode");
		System.out.println("name=" + channelName + ", code=" + channelCode);
	}
}
