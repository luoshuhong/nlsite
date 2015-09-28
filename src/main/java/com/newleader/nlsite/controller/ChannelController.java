package com.newleader.nlsite.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.newleader.nlsite.common.QrCodeProduce;
import com.newleader.nlsite.common.RequestUtils;
import com.newleader.nlsite.model.Channel;
import com.newleader.nlsite.service.ChannelService;

/**
 * 渠道相关
 * @author Luoshuhong
 * @Company donottel.me
 * 2015年9月11日
 *
 */
@Controller
@RequestMapping("/admin/channel")
public class ChannelController {
	
	@Autowired
	private ChannelService channelService;
	
	@RequestMapping("/add")
    @ResponseBody
    public String add(HttpServletRequest request, HttpServletResponse response){
		String channelName = request.getParameter("channelName");
		String channelCode = request.getParameter("channelCode");
		if (StringUtils.isEmpty(channelCode) || StringUtils.isEmpty(channelName)) {
			return RequestUtils.failReturn("param is empty");
		}
		//获取微信二维码链接
		String qrCodeUrl = QrCodeProduce.permanentCode(channelCode);
		if (StringUtils.isEmpty(qrCodeUrl) ) {
			return RequestUtils.failReturn("error");
		}
		Channel channel = new Channel(channelName,channelCode);
		channel.setQrCodeUrl(qrCodeUrl);
		channel.setId(UUID.randomUUID().toString().toUpperCase());
		//生成本地二维码图片
		QrCodeProduce.createQrCord(request.getRealPath("/"),channel);
		
		try {
			if (this.channelService.add(channel)) {
				return RequestUtils.successReturn("");
			} else {
				return RequestUtils.failReturn("add fail");
			}
		} catch (Exception e) {
			return RequestUtils.failReturn("exception");
		}
	}
	
	@RequestMapping("/query")
    @ResponseBody
    public String query(HttpServletRequest request, HttpServletResponse response){
		try {
			List<Channel> list = null;
			String value = request.getParameter("value");
			System.out.println("---value:" + value);
			if (StringUtils.isEmpty(value)) {
				list = this.channelService.query();
			} else {
				list = this.channelService.vagueQuery(value);
			}
			
			if (null != list) {
				return RequestUtils.successReturn(JSONArray.toJSONString(list));
			} else {
				return RequestUtils.failReturn("fail");
			}
		} catch (Exception e) {
			return RequestUtils.failReturn("exception");
		}
	}
	
	@RequestMapping("/cancel")
    @ResponseBody
    public String cancel(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = request.getParameter("id");
			if (StringUtils.isEmpty(id)) {
				return RequestUtils.failReturn("param is null");
			}
			if (this.channelService.chacel(id)) {
				return RequestUtils.successReturn("");
			} else {
				return RequestUtils.failReturn("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
	
	@RequestMapping("/update")
    @ResponseBody
    public String update(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = request.getParameter("id");
			String code = request.getParameter("channelCode");
			String name = request.getParameter("channelName");
//			System.out.println(id + "=" + code + "=" + name);
			if (StringUtils.isEmpty(id) || StringUtils.isEmpty(code) || StringUtils.isEmpty(name)) {
				return RequestUtils.failReturn("param is null");
			}
			//获取微信二维码链接
			String qrCodeUrl = QrCodeProduce.permanentCode(code);
			System.out.println("qrCodeUrl=" + qrCodeUrl);
			if (StringUtils.isEmpty(qrCodeUrl) ) {
				return RequestUtils.failReturn("error");
			}
			
			//生成二维码（）
			Channel channel = new Channel(id, name, code);
			channel.setQrCodeUrl(qrCodeUrl);
			
			//生成本地二维码图片
			QrCodeProduce.createQrCord(request.getRealPath("/"),channel);
			
			if (this.channelService.update(channel)) {
				return RequestUtils.successReturn("");
			} else {
				return RequestUtils.failReturn("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
}
