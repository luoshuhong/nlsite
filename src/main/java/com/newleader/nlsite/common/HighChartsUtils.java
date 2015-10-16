package com.newleader.nlsite.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.newleader.nlsite.admin.model.StatModel;

/**
 * highcharts数据处理工具类 
 * @author Luoshuhong
 * @Company  
 * 2015年10月16日
 *
 */
public class HighChartsUtils {
	
	/**
	 * 处理成图表展示的数据
	 * @param list 需要处理的数据
	 * @return json串 {'xAxis':{'categories':[]},'series':xxx}  xAxis:横轴  series:数据域
	 */
	public static String dealHightChartsData(List<StatModel> list) {
		JSONObject jobRes = new JSONObject();
		
		if (null == list || 0 == list.size()) {
			jobRes.put("series", "");//数据域
			jobRes.put("xAxis", ""); //横轴
			return jobRes.toJSONString();
		}
		
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
		Set<String> statItemSet = new HashSet<String>();     //统计项：渠道编码/分享页场景值
		for (StatModel model : list) {
			statItemSet.add(model.getStatItem());
			if (!dateList.contains(model.getDate())) {
				dateList.add(model.getDate());
			}
			if (statMap.containsKey(model.getDate())) {
				statMap.get(model.getDate()).put(model.getStatItem(), model.getCount());
			} else {
				Map<String,Integer> detailMap = new HashMap<String,Integer>();
				detailMap.put(model.getStatItem(), model.getCount());
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
		for (String code : statItemSet) {
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
