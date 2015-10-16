package com.newleader.nlsite.admin.service;

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
		//获取分享者渠道
		VisitorChannel visitorChannel = this.visitorChannelDao.queyByOpenId(recordVirus.getsOpenId());
		if (null != visitorChannel) {
			recordVirus.setChannelId(visitorChannel.getChannelId());
		}
		
		//1.查看上一级分享 获取rootChannelId
		RecordVirus shareModel = this.recordVirusDao.queryByOpenIdAndScene(recordVirus.getsOpenId(), recordVirus.getScene());
		if (null != shareModel) {
			recordVirus.setRootChannelId(shareModel.getRootChannelId());
		} else {
			recordVirus.setRootChannelId(visitorChannel.getChannelId());
		}
		
		//2.查看是否存在  存在时更新为时间和分享者
		RecordVirus model = this.recordVirusDao.queryByOpenIdAndScene(recordVirus.getvOpenId(), recordVirus.getScene());
		if (null != model) {
			return this.recordVirusDao.update(recordVirus.getvOpenId(), recordVirus.getsOpenId(),model.getRootChannelId(),recordVirus.getChannelId(), recordVirus.getScene());
		} else {
			return this.recordVirusDao.add(recordVirus);
		}
	}
	
	
}
