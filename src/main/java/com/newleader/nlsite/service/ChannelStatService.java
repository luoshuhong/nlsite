package com.newleader.nlsite.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.newleader.nlsite.common.DateUtils;
import com.newleader.nlsite.dao.ChannelStatDao;
import com.newleader.nlsite.model.ChannelStat;

/**
 * 渠道推广统计相关操作
 * @author Luoshuhong
 * @Company  
 * 2015年9月17日
 *
 */
@Service
public class ChannelStatService {
	
	@Autowired
	private ChannelStatDao channelStatDao;
	
	/**
	 * 按渠道分组查询
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return json 串
	 */
	public String queryByChannelCode(String sDate, String eDate) {
		//先更新时间
		this.channelStatDao.updateCreatData();
		
		List<ChannelStat> list = this.channelStatDao.query(sDate, eDate);
		
		/******  预处理数据  start  *****/
		/**
		 * key:时间 --> 2015-9-11
		 * value:对应时间下各渠道-关注数量 map key:chennalCode value:count
		 */
		Map<String,Map<String,Integer>> statMap = new HashMap<String,Map<String,Integer>>();
		/**
		 * 有序时间集[2015-09-11,2015-09-12,2015-09-12,...]
		 * 直接作为 图表展示横坐标
		 */
		List<String> dateList = new ArrayList<String>();
		Set<String> codeSet = new HashSet<String>();     //渠道编码
		String dateStr = "";
		for (ChannelStat model : list) {
			codeSet.add(model.getChannelCode());
			if (!dateStr.contains(model.getDate())) {
				dateList.add(model.getDate());
			}
			if (statMap.containsKey(model.getDate())) {
				statMap.get(model.getDate()).put(model.getChannelCode(), model.getCount());
			} else {
				Map<String,Integer> detailMap = new HashMap<String,Integer>();
				detailMap.put(model.getChannelCode(), model.getCount());
				statMap.put(model.getDate(), detailMap);
			}
		}
		dateList = DateUtils.sortDate(dateList,DateUtils.Simple_Date_Format,0);
		/******  预处理数据  end  *****/
		
		/****** 结果数据拼装 start ****/
		/**
		 * key:channelCode
		 * value:count(关注数量)，按时间有序list
		 */
		Map<String,int[]> resultMap = new HashMap<String,int[]>();
		//初始化
		for (String code : codeSet) {
			int[] arr = new int [dateList.size()] ; 
			resultMap.put(code, arr);
		}
		for(int i = 0; i < dateList.size(); i++) {
			String date = dateList.get(i);
			Map<String,Integer> channelDateMap = statMap.get(date);
			for (String channelCode : channelDateMap.keySet()) {
				int[] arr = resultMap.get(channelCode);
				arr[i] = channelDateMap.get(channelCode);
				resultMap.put(channelCode, arr);
			}
		}
		/****** 结果数据拼装 end ****/
		
		/****  拼装成前台图片展示的json数据  *****/
		JSONObject jobRes = new JSONObject();
		JSONObject xAxis = new JSONObject();
		xAxis.put("categories", dateList);
		jobRes.put("xAxis", xAxis); //横轴
		//数据域
		JSONArray jobArr = new JSONArray();
		for (String code : resultMap.keySet()) {
			JSONObject dataJob = new JSONObject();
			dataJob.put("name", code);
			dataJob.put("data", resultMap.get(code));
			jobArr.add(dataJob);
		}
		jobRes.put("series", jobArr);
		return jobRes.toJSONString();
	}
	
}
