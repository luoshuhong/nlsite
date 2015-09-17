package com.newleader.nlsite.model;

/**
 * 渠道推广统计
 * @author Luoshuhong
 * @Company  
 * 2015年9月17日
 *
 */
public class ChannelStat {
	private String channelCode;  //渠道编码
	private int    count;  	     //数量
	private String date;	     //时间
	
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
