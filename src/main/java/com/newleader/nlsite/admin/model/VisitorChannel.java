package com.newleader.nlsite.admin.model;

import java.util.Date;

/**
 * 用户关注/取消关注 渠道记录
 * @author Luoshuhong
 * @Company newleader
 * 2015年9月14日
 *
 */
public class VisitorChannel {
	private String id;               //主键
	private String openId;   	 //用户openId
	private String channelId;   //渠道code
//	private String createDate; //创建时间 "YYYY-MM-DD"格式
	private Date   createTime; //创建时间
	private Date   bindTime;   //
	private int	   isBind;	     //关注标识 0：关注 1：已取消关注 2：取消关注后重新关注
	private int leavel = 0;    //  用户来源等级 0:表示自己关注 其他表示通过分享层级关注
	
	public VisitorChannel() {
	}
	
	public VisitorChannel(String openId, String channelId) {
		super();
		this.openId = openId;
		this.channelId = channelId;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
		this.createTime = createTime;
	}
	public Date getBindTime() {
		return bindTime;
	}
	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}
	public int getIsBind() {
		return isBind;
	}
	public void setIsBind(int isBind) {
		this.isBind = isBind;
	}
	public int getLeavel() {
		return leavel;
	}
	public void setLeavel(int leavel) {
		this.leavel = leavel;
	}
}
