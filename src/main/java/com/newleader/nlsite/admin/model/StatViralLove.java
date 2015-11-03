package com.newleader.nlsite.admin.model;

/**
 *  爱情测评 Viral统计用
 * @author Luoshuhong
 * @Company  
 * 2015年11月2日
 *
 */
public class StatViralLove {
	private int id;	             	//
	private String date;       	//日期
	private int totalShare;  	//总分享
	private int totalVisit;    	//总浏览
	private int totalViral;   		//总viral
	private int spiderShare;    //蜘蛛页分享
	private int spiderVisit;		//蜘蛛页浏览
	private int spiderViral;	    //蜘蛛页viral
	private int matchShare;	//匹配页分享
	private int matchVisit;	    //匹配页浏览
	private int matchViral;     //匹配页viral
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
	public int getTotalShare() {
		return totalShare;
	}
	public void setTotalShare(int totalShare) {
		this.totalShare = totalShare;
	}
	public int getTotalVisit() {
		return totalVisit;
	}
	public void setTotalVisit(int totalVisit) {
		this.totalVisit = totalVisit;
	}
	public int getTotalViral() {
		return totalViral;
	}
	public void setTotalViral(int totalViral) {
		this.totalViral = totalViral;
	}
	public int getSpiderShare() {
		return spiderShare;
	}
	public void setSpiderShare(int spiderShare) {
		this.spiderShare = spiderShare;
	}
	public int getSpiderVisit() {
		return spiderVisit;
	}
	public void setSpiderVisit(int spiderVisit) {
		this.spiderVisit = spiderVisit;
	}
	public int getSpiderViral() {
		return spiderViral;
	}
	public void setSpiderViral(int spiderViral) {
		this.spiderViral = spiderViral;
	}
	public int getMatchShare() {
		return matchShare;
	}
	public void setMatchShare(int matchShare) {
		this.matchShare = matchShare;
	}
	public int getMatchVisit() {
		return matchVisit;
	}
	public void setMatchVisit(int matchVisit) {
		this.matchVisit = matchVisit;
	}
	public int getMatchViral() {
		return matchViral;
	}
	public void setMatchViral(int matchViral) {
		this.matchViral = matchViral;
	}
	@Override
	public String toString() {
		return "StatViralLove [id=" + id + ", date=" + date + ", totalShare="
				+ totalShare + ", totalVisit=" + totalVisit + ", totalViral="
				+ totalViral + ", spiderShare=" + spiderShare
				+ ", spiderVisit=" + spiderVisit + ", spiderViral="
				+ spiderViral + ", matchShare=" + matchShare + ", matchVisit="
				+ matchVisit + ", matchViral=" + matchViral + "]";
	}
}