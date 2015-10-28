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
	private int    count;  	        //数量
	private String date;	     		//时间
	
	
	//下面统计活跃度用
	private String type = "";     		   //类型Constants.STAT_TYPE_UV_*
	private String channelId = "";	   //
	private String product = "";        //固定:love(爱情测评), profession (职业测评)
	private String mainType = "";     //大类uv/pv/xxx 统计的大项
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getMainType() {
		return mainType;
	}
	public void setMainType(String mainType) {
		this.mainType = mainType;
	}
	@Override
	public String toString() {
		return "StatModel [statItem=" + statItem + ", count=" + count
				+ ", date=" + date + ", type=" + type + ", channelId="
				+ channelId + ", product=" + product + "]";
	}
}
