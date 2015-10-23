package com.newleader.nlsite.admin.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.RecordShareDao;
import com.newleader.nlsite.admin.dao.RecordVirusDao;
import com.newleader.nlsite.admin.model.RecordShare;
import com.newleader.nlsite.admin.model.RecordVirus;

/**
 * 分享记录操作
 * @author Luoshuhong
 * @Company  
 * 2015年10月10日
 *
 */
@Service
public class RecordShareService {
	@Autowired
	private RecordShareDao recordShareDao;
	@Autowired
	private RecordVirusDao recordVirusDao;
	
	/**
	 * @param recordShare
	 * @return
	 */
	public boolean add(RecordShare recordShare) {
		//1.是否已经存在
		if (null != this.recordShareDao.queryBySceneOpenId(recordShare.getScene(), recordShare.getOpenId())) {
			return true;
		}
		
		//2.查询superid
		RecordVirus recordVirus = this.recordVirusDao.queryByOpenIdAndScene(recordShare.getOpenId(), recordShare.getScene());
		if (null != recordVirus) {
			recordShare.setRootChannelId(recordVirus.getRootChannelId());  //如果分享前 是通过viral进入 则记录rootChannelId
			
			RecordShare superModel = this.recordShareDao.queryBySceneOpenId(recordShare.getScene(), recordVirus.getsOpenId());
			if (null != superModel) {
				recordShare.setSuperId(superModel.getId());
			}
		} else {
			recordShare.setRootChannelId("default");
		}
		return this.recordShareDao.add(recordShare);
	}
	
	/**
	 * 获取某个渠道累计的分享量
	 * @param channelId  渠道id
	 * @return 累计分享量
	 */
	public int getShareCountByChannel(String code) {
		if (StringUtils.isEmpty(code)) {
			return 0;
		}
		return this.recordShareDao.getShareCountByChannel(code);
	}
	
	/**
	 * 更新填充 渠道编码
	 */
	public void updateChannelId() {
		this.recordShareDao.updateChannelId();
	}
}
