package com.newleader.nlsite.admin.model;

/**
 * 统计用
 * @author Luoshuhong
 * @Company  
 * 2015年9月17日
 *
 */
public class StatMajor {
//	`id` int(11) NOT NULL AUTO_INCREMENT,
//	  `date` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '日期（YYYY-MM-DD）格式',
//	  `wxTotal` int(10) DEFAULT '0' COMMENT '微信累计关注数',
//	  `wxSubscribe` int(10) DEFAULT '0' COMMENT '微信新增关注数',
//	  `wxUnsubscribe` int(10) DEFAULT '0' COMMENT '微信取消关注数',
//	  `totalUser` int(10) DEFAULT '0' COMMENT '总用户数（62805+9月23日的每日新关注人数）',
//	  `proIncrease` int(10) DEFAULT '0' COMMENT '职业新增用户',
//	  `proTotal` int(10) DEFAULT '0' COMMENT '职业累计用户',
//	  `loveIncrease` int(10) DEFAULT '0' COMMENT '爱情新增用户',
//	  `loveTotal` int(10) DEFAULT '0' COMMENT '爱情累计用户',
	
	private int id;				 		//主键
	private String date;	     		//时间	格式 --> YYYY-MM-DD
	private int wxTotal;	     		//微信累计关注数
	private int wxSubscribe; 	    //微信新增关注数（按天）
	private int wxUnsubscribe;  //微信取消关注数（按天）
	private int wxPureSubscribe;//微信净增关注人数
	private int totalUser;	       //总用户数（62805+9月23日的每日新关注人数）
	private int proIncrease;	   //职业测评新增用户数（按天）
	private int proTotal;	           //职业测评累计用户
	private int loveIncrease;	   //爱情新增用户（按天）
	private int loveTotal;		   //爱情累计用户
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getWxTotal() {
		return wxTotal;
	}
	public void setWxTotal(int wxTotal) {
		this.wxTotal = wxTotal;
	}
	
	public int getWxSubscribe() {
		return wxSubscribe;
	}
	public void setWxSubscribe(int wxSubscribe) {
		this.wxSubscribe = wxSubscribe;
	}
	public int getWxUnsubscribe() {
		return wxUnsubscribe;
	}
	public void setWxUnsubscribe(int wxUnsubscribe) {
		this.wxUnsubscribe = wxUnsubscribe;
	}
	public int getTotalUser() {
		return totalUser;
	}
	public void setTotalUser(int totalUser) {
		this.totalUser = totalUser;
	}
	public int getProIncrease() {
		return proIncrease;
	}
	public void setProIncrease(int proIncrease) {
		this.proIncrease = proIncrease;
	}
	public int getProTotal() {
		return proTotal;
	}
	public void setProTotal(int proTotal) {
		this.proTotal = proTotal;
	}
	public int getLoveTotal() {
		return loveTotal;
	}
	public int getWxPureSubscribe() {
		return wxPureSubscribe;
	}
	public void setWxPureSubscribe(int wxPureSubscribe) {
		this.wxPureSubscribe = wxPureSubscribe;
	}
	public void setLoveTotal(int loveTotal) {
		this.loveTotal = loveTotal;
	}
	public int getLoveIncrease() {
		return loveIncrease;
	}
	public void setLoveIncrease(int loveIncrease) {
		this.loveIncrease = loveIncrease;
	}
	@Override
	public String toString() {
		return "StatMajor [id=" + id + ", date=" + date + ", wxTotal="
				+ wxTotal + ", wxSubscribe=" + wxSubscribe + ", wxUnsubscribe="
				+ wxUnsubscribe + ", totalUser=" + totalUser + ", proIncrease="
				+ proIncrease + ", proTotal=" + proTotal + ", loveIncrease="
				+ loveIncrease + ", loveTotal=" + loveTotal + "]";
	}
}