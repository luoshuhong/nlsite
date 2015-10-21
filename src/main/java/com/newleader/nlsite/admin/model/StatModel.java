package com.newleader.nlsite.admin.model;

/**
 * 统计用
 * @author Luoshuhong
 * @Company  
 * 2015年9月17日
 *
 */
public class StatModel {
	private String statItem;  	    //统计域：渠道编码/分享场景/...
	private int    count;  	    	 //数量
	private String date;	     		//时间
	
	public String getStatItem() {
		return statItem;
	}
	public void setStatItem(String statItem) {
		this.statItem = statItem;
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
	@Override
	public String toString() {
		return "StatModel [statItem=" + statItem + ", count=" + count
				+ ", date=" + date + "]";
	}
}
