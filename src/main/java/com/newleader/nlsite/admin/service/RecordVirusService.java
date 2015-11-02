package com.newleader.nlsite.admin.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.RecordVirusDao;
import com.newleader.nlsite.admin.dao.VisitorChannelDao;
import com.newleader.nlsite.admin.model.RecordVirus;
import com.newleader.nlsite.admin.model.VisitorChannel;

/**
 * 分享页阅读记录操作
 * @author Luoshuhong
 * @Company  
 * 2015年10月10日
 *
 */
@Service
public class RecordVirusService {
	@Autowired
	private RecordVirusDao recordVirusDao;
	@Autowired
	private VisitorChannelDao visitorChannelDao;
	
	/**
	 * 添加记录
	 * @param recordVirus RecordVirus
	 * @return 是否成功
	 */
	public boolean add(RecordVirus recordVirus) {
		if (recordVirus.getvOpenId().equals(recordVirus.getsOpenId())) {
			return true;
		}
		
		//获取分享者渠道
		VisitorChannel visitorChannel = this.visitorChannelDao.queyByOpenId(recordVirus.getsOpenId());
		if (null != visitorChannel) {
			recordVirus.setChannelId(visitorChannel.getChannelId());
		} else {
			recordVirus.setChannelId("default");
		}
		
		//1.查看上一级分享 获取rootChannelId
		RecordVirus shareModel = this.recordVirusDao.queryByOpenIdAndScene(recordVirus.getsOpenId()); //并且处于关注状态
		if (null != shareModel) {
			recordVirus.setRootChannelId(shareModel.getRootChannelId());
		} else {
			recordVirus.setRootChannelId(recordVirus.getChannelId());  //如果没有上级  则rootChannelId 为自己的channelId
		}
		
		//如果已经属于viral用户 就不记录了
		if (null != this.recordVirusDao.queryByOpenIdAndScene(recordVirus.getvOpenId())) {
			return true;
		}
		
		//2.查看是否存在  存在时更新为时间和分享者
//		RecordVirus model = this.recordVirusDao.queryByOpenIdAndSceneIgnoreSubscribe(recordVirus.getvOpenId(), recordVirus.getScene());
		if (1 <= this.recordVirusDao.queryByOpenIdAndSceneIgnoreSubscribe(recordVirus.getvOpenId(), recordVirus.getScene())) {
			return this.recordVirusDao.update(recordVirus.getvOpenId(), recordVirus.getsOpenId(),recordVirus.getRootChannelId(),recordVirus.getChannelId(), recordVirus.getScene());
		} else {
			return this.recordVirusDao.add(recordVirus);
		}
	}
	
	
	/**
	 * 获取某个渠道累计的virual量
	 * @param channelId  渠道id
	 * @return 累计分享量
	 */
	public int getVirualCountByChannel(String code) {
		if (StringUtils.isEmpty(code)) {
			return 0;
		}
		
		return this.recordVirusDao.getVirualCountByChannel(code);
	}
	
	
}
