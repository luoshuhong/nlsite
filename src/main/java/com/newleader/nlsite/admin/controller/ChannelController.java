package com.newleader.nlsite.admin.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.newleader.nlsite.admin.model.Channel;
import com.newleader.nlsite.admin.service.ChannelService;
import com.newleader.nlsite.admin.service.ChannelStatService;
import com.newleader.nlsite.common.QrCodeProduce;
import com.newleader.nlsite.common.RequestUtils;
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
	Logger log = Logger.getLogger("admin");
	
	@Autowired
	private ChannelService channelService;
	@Autowired
	private ChannelStatService channelStatService;
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/add")
    @ResponseBody
    public String add(HttpServletRequest request, HttpServletResponse response){
		Channel channel = this.verify(request, "add");
		if (null == channel) {
			System.out.println("-----null ");
			return RequestUtils.failReturn("param is error!");
		}
		
		//检查是否存在
		if (this.channelService.queryByCode(channel.getCode()) >= 1) {
			System.out.println("-----exist ");
			return RequestUtils.failReturn("code is exist");
		}
		//获取微信二维码链接
		String qrCodeUrl = QrCodeProduce.permanentCode(channel.getCode());
		if (StringUtils.isEmpty(qrCodeUrl) ) {
			return RequestUtils.failReturn("error");
		}
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
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
	
	@RequestMapping("/query")
    @ResponseBody
    public String query(HttpServletRequest request, HttpServletResponse response){
		try {
			List<Channel> list = null;
			String value = request.getParameter("value");
			log.info("method=queryChannel,value=" + value);
			
			if (StringUtils.isEmpty(value)) {
				list = this.channelService.query();
			} else {
				list = this.channelService.vagueQuery(value);
			}
			
			if (null != list) {
				//获取每个渠道累计关注量
				for (Channel channel : list) {
					channel.setCurrSubscribe(channel.getTotalSubscribe() - channel.getUnSubscribe());
					if (0 != channel.getTotalSubscribe() && 0 != channel.getUnSubscribe()) {
						channel.setUnSubscribeRate(new DecimalFormat("###.00").format((100.0 * channel.getUnSubscribe())/channel.getTotalSubscribe() ));
					}
				}
				return RequestUtils.successReturn(JSONArray.toJSONString(list));
			} else {
				return RequestUtils.failReturn("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
	
	@RequestMapping("/queryEffect")
    @ResponseBody
    public String queryChannelEffect(HttpServletRequest request, HttpServletResponse response){
		try {
			List<Channel> list = new ArrayList<Channel>();
			String channel = request.getParameter("channel");
			log.info("method=queryChannelEffect,channel=" + channel);
			int subscribe = 0,unsubscribe = 0;
			for (String code : channel.split(",")) {
				Channel model = new Channel();
				int totalSubscribe = this.channelStatService.getSubscribeByChannel(code);
				int unSubscribe = this.channelStatService.getUnSubscribeByChannel(code); 
				subscribe += totalSubscribe;
				unsubscribe += unSubscribe;
				
				model.setCode(code);
				model.setTotalSubscribe(totalSubscribe);
				model.setUnSubscribe(unSubscribe);
				model.setCurrSubscribe(totalSubscribe - unSubscribe);
				if (0 != totalSubscribe && 0 != unSubscribe) {
					model.setUnSubscribeRate(new DecimalFormat("###.00").format((100.0 * unSubscribe)/totalSubscribe));
				}
				list.add(model);
			}
			
			//汇总
			Channel model = new Channel(); 
			model.setCode("total");
			model.setTotalSubscribe(subscribe);
			model.setUnSubscribe(unsubscribe);
			model.setCurrSubscribe(subscribe - unsubscribe);
			if (0 != subscribe && 0 != unsubscribe) {
				model.setUnSubscribeRate(new DecimalFormat("###.00").format((100.0 * unsubscribe)/subscribe));
			}
			list.add(model);
			
			return RequestUtils.successReturn(JSONArray.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
			return RequestUtils.failReturn("exception");
		}
	}
	
	@RequestMapping("/cancel")
    @ResponseBody
    public String cancel(HttpServletRequest request, HttpServletResponse response){
		try {
			String id = request.getParameter("id");
			log.info("method=cancelChannel,value=" + id);
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
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/update")
    @ResponseBody
    public String update(HttpServletRequest request, HttpServletResponse response){
		try {
			Channel channel = this.verify(request, "update");
			if (null == channel) {
				return RequestUtils.failReturn("param is error!");
			}
			
			//获取微信二维码链接
			String qrCodeUrl = QrCodeProduce.permanentCode(channel.getCode());
			if (StringUtils.isEmpty(qrCodeUrl) ) {
				return RequestUtils.failReturn("error");
			}
			
			//生成二维码（）
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
	
	/**
	 * 校验参数 
	 * @param request
	 * @param method add or update
	 * @return Channel
	 */
	private Channel verify(HttpServletRequest request, String method) {
		String id = request.getParameter("id");
		String channelName = request.getParameter("channelName");      //渠道名
		String channelCode = request.getParameter("channelCode");        //渠道编码
		String freeType = request.getParameter("freeType"); 					 //类型  0：限时免费  1：免费1份68元 2：免费3份168 3：免费5份268元
		String freeDes = request.getParameter("freeDes");						 //前台优惠是文案描述
		String freeStartDate = request.getParameter("freeStartDate");	     //优惠开始时间
		String freeEndDate = request.getParameter("freeEndDate");          //优惠结束时间
		
		log.info("method=" + method + "Channel,id=" + id + ",code=" + channelCode + ",name=" + channelName
				+ ",freeType=" + freeType + ",freeDes=" + freeDes + ",freeStartDate=" + freeStartDate + ",freeEndDate=" + freeEndDate);
		if (StringUtils.isEmpty(channelCode) || StringUtils.isEmpty(channelName)) {
			return null;
		}
		
		//限免
		if ("0".equals(freeType)) {
			if(StringUtils.isEmpty(freeDes) || StringUtils.isEmpty(freeStartDate) || StringUtils.isEmpty(freeEndDate)) {
				return null;
			}
		}
		
		//免1、3、5份
		if ("1".equals(freeType) || "2".equals(freeType) || "3".equals(freeType)) {
			if(StringUtils.isEmpty(freeDes) ) {
				return null;
			}
		}
		
		Channel channel = new Channel(id, channelName, channelCode);
		channel.setFreeDes(freeDes);
		channel.setFreeType(freeType);
		channel.setFreeStartDate(freeStartDate);
		channel.setFreeEndDate(freeEndDate);
		
		return channel;
	}
	
}

