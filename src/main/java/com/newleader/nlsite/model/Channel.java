package com.newleader.nlsite.model;

/**
 * 渠道信息
 * @author Luoshuhong
 * @Company newleader
 * 2015年9月14日
 *
 */
public class Channel {
	private String id;
	private String name;
	private String code;
	
	private String createTimeStr; //前台展示用
	
	public Channel() {
	}
	
	
	public Channel(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", code=" + code
				+ ", createTimeStr=" + createTimeStr + "]";
	}
}
