package com.newleader.nlsite.admin.model;

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
	private String   createTime;    //创建时间
	private int	   superId;    	    //父id
	private int      count;			//该分享后续被分享了几次
	private int 	   isDeal;           //是否处理(该分享给上级父分享产生的影响) 0：为处理 1：已处理
	private String  channelId;     //channelId
	private String rootChannelId;//
	public RecordShare() {
	}
	
	public RecordShare(String openId, String scene) {
		super();
		this.openId = openId;
		this.scene = scene;
	}

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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
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
	public int getIsDeal() {
		return isDeal;
	}
	public void setIsDeal(int isDeal) {
		this.isDeal = isDeal;
	}

	public String getChannelId() {
		return channelId;
	}
	public String getRootChannelId() {
		return rootChannelId;
	}
	public void setRootChannelId(String rootChannelId) {
		this.rootChannelId = rootChannelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
}
