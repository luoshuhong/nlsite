package com.newleader.nlsite.admin.model;

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
	private String qrCodeUrl;       //二维码Url
	
	private String createTimeStr; //前台展示用
	private int 	totalSubscribe;   //关注量  - 包括已取消关注的
	private int   currSubscribe;    //当前关注量
	private int 	unSubscribe;	     //取消关注的量
	private String unSubscribeRate = "0"; //取消关注的比率 -- 流失率
	
	private int shareCount;   //渠道累计分享次数
	private int virualCount;   //渠道累计virual人数
	
	private String freeType = "-1";		//类型  -1：无免费  0：限时免费   1：渠道免费
	private String freeDes;					//前台优惠是文案描述
	private int freeCount;                  //免费份数  1：免费1份68元 2：免费3份168 3：免费5份268元
	private String freeStartDate;	        //优惠开始时间
	private String freeEndDate;          //优惠结束时间
	
	public Channel() {
	}
	
	public Channel(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public Channel(String id, String name, String code) {
		super();
		this.id = id;
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

	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	
	public int getTotalSubscribe() {
		return totalSubscribe;
	}

	public void setTotalSubscribe(int totalSubscribe) {
		this.totalSubscribe = totalSubscribe;
	}
	public int getUnSubscribe() {
		return unSubscribe;
	}
	public void setUnSubscribe(int unSubscribe) {
		this.unSubscribe = unSubscribe;
	}
	public String getUnSubscribeRate() {
		return unSubscribeRate;
	}

	public void setUnSubscribeRate(String unSubscribeRate) {
		this.unSubscribeRate = unSubscribeRate;
	}

	public int getCurrSubscribe() {
		return currSubscribe;
	}

	public void setCurrSubscribe(int currSubscribe) {
		this.currSubscribe = currSubscribe;
	}
	
	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public int getVirualCount() {
		return virualCount;
	}
	public void setVirualCount(int virualCount) {
		this.virualCount = virualCount;
	}
	public String getFreeType() {
		return freeType;
	}

	public void setFreeType(String freeType) {
		this.freeType = freeType;
	}

	public String getFreeDes() {
		return freeDes;
	}

	public void setFreeDes(String freeDes) {
		this.freeDes = freeDes;
	}

	public String getFreeStartDate() {
		return freeStartDate;
	}

	public void setFreeStartDate(String freeStartDate) {
		this.freeStartDate = freeStartDate;
	}

	public String getFreeEndDate() {
		return freeEndDate;
	}

	public void setFreeEndDate(String freeEndDate) {
		this.freeEndDate = freeEndDate;
	}

	public int getFreeCount() {
		return freeCount;
	}

	public void setFreeCount(int freeCount) {
		this.freeCount = freeCount;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", name=" + name + ", code=" + code
				+ ", qrCodeUrl=" + qrCodeUrl + ", createTimeStr="
				+ createTimeStr + ", totalSubscribe=" + totalSubscribe
				+ ", currSubscribe=" + currSubscribe + ", unSubscribe="
				+ unSubscribe + ", unSubscribeRate=" + unSubscribeRate
				+ ", shareCount=" + shareCount + ", virualCount=" + virualCount
				+ ", freeType=" + freeType + ", freeDes=" + freeDes
				+ ", freeCount=" + freeCount + ", freeStartDate="
				+ freeStartDate + ", freeEndDate=" + freeEndDate + "]";
	}
}
