package com.newleader.nlsite.admin.model;

import java.util.Date;

/**
 * 分享记录表
 * @author Luoshuhong
 * @Company  
 * 2015年10月9日
 *
 */
public class RecordShare {
	private int      id;		     		//id
	private String openId;          //分享者openId
	private String scene;            //场景值
	private Date   createTime;    //创建时间
	private int	   superId;    	    //父id
	private int      count;			//该分享后续被分享了几次
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getScene() {
		return scene;
	}
	public void setScene(String scene) {
		this.scene = scene;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getSuperId() {
		return superId;
	}
	public void setSuperId(int superId) {
		this.superId = superId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
