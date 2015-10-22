package com.newleader.nlsite.admin.model;


/**
 * 分享页阅读记录表
 * @author Luoshuhong
 * @Company  
 * 2015年10月9日
 *
 */
public class RecordVirus {
	private int      id;		     		//id
	private String vOpenId;        //访问者openId
	private String sOpenId;        //分析者openid
	private String channelId;      //分享者渠道信息
	private String scene;		        //场景
	private String   createTime;    //创建时间
	private int	   isSubscribe;     //是否关注 0：未关注   1：关注
	private String rootChannelId = ""; //顶级分享用户id
	
	public RecordVirus() {
	}
	
	public RecordVirus(String vOpenId, String sOpenId, String scene) {
		super();
		this.vOpenId = vOpenId;
		this.sOpenId = sOpenId;
		this.scene = scene;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getvOpenId() {
		return vOpenId;
	}
	public void setvOpenId(String vOpenId) {
		this.vOpenId = vOpenId;
	}
	public String getsOpenId() {
		return sOpenId;
	}
	public void setsOpenId(String sOpenId) {
		this.sOpenId = sOpenId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getScene() {
		return scene;
	}
	public void setScene(String scene) {
		this.scene = scene;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getIsSubscribe() {
		return isSubscribe;
	}
	public void setIsSubscribe(int isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getRootChannelId() {
		return rootChannelId;
	}

	public void setRootChannelId(String rootChannelId) {
		this.rootChannelId = rootChannelId;
	}
	
}
