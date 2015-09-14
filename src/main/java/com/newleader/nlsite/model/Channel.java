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
}
