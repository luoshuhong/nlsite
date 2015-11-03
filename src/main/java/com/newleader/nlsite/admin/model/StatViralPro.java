package com.newleader.nlsite.admin.model;

/**
 *  职业测评 Viral统计用
 * @author Luoshuhong
 * @Company  
 * 2015年11月2日
 *
 */
public class StatViralPro {
	private int id;	             //
	private String date;       //日期 按渠道分组是，该字段表示渠道名称
	private int totalShare;  //总分享
	private int totalVisit;    //总浏览
	private int totalViral;   //总viral
	private int dnaShare;   //dna分享
	private int dnaVisit;     //dna浏览
	private int dnaViral;    //dnaViral
	private int listShare;	    //排行榜分享
	private int listVisit;	    //排行榜浏览
	private int listViral;      //排行榜Viral
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
	public int getDnaShare() {
		return dnaShare;
	}
	public void setDnaShare(int dnaShare) {
		this.dnaShare = dnaShare;
	}
	public int getDnaVisit() {
		return dnaVisit;
	}
	public void setDnaVisit(int dnaVisit) {
		this.dnaVisit = dnaVisit;
	}
	public int getDnaViral() {
		return dnaViral;
	}
	public void setDnaViral(int dnaViral) {
		this.dnaViral = dnaViral;
	}
	public int getListShare() {
		return listShare;
	}
	public void setListShare(int listShare) {
		this.listShare = listShare;
	}
	public int getListVisit() {
		return listVisit;
	}
	public void setListVisit(int listVisit) {
		this.listVisit = listVisit;
	}
	public int getListViral() {
		return listViral;
	}
	public void setListViral(int listViral) {
		this.listViral = listViral;
	}
	@Override
	public String toString() {
		return "StatViralPro [id=" + id + ", date=" + date + ", totalShare="
				+ totalShare + ", totalVisit=" + totalVisit + ", totalViral="
				+ totalViral + ", dnaShare=" + dnaShare + ", dnaVisit="
				+ dnaVisit + ", dnaViral=" + dnaViral + ", listShare="
				+ listShare + ", listVisit=" + listVisit + ", listViral="
				+ listViral + "]";
	}
}