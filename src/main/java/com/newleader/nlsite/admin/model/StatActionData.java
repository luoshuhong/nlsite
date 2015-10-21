package com.newleader.nlsite.admin.model;

import java.util.Date;

/**
 * 统计原始数据记录 
 * @author Luoshuhong
 * @Company  
 * 2015年10月21日
 *
 */
public class StatActionData {
	private int      id;	             //主键id
	private String type;  	    	 //类型  professionalLogin:职业登陆 loveLogin:爱情登陆
	private String openId;       //用户openid
	private String channelId;   //渠道编码
	private Date   createTime; //创建时间
	private long   interval;       //时间间隔（于上一次访问）
	private String product;      //所属产品 (固定love, preffesion )
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		if (null == createTime) {
			this.createTime = new Date();
		} else {
			this.createTime = createTime;
		}
	}
	public long getInterval() {
		return interval;
	}
	public void setInterval(long interval) {
		this.interval = interval;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "StatOrgDataModel [id=" + id + ", type=" + type + ", openId="
				+ openId + ", channelId=" + channelId + ", createTime="
				+ createTime + "]";
	}
}
