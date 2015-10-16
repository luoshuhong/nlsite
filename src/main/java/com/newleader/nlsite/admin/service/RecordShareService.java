package com.newleader.nlsite.admin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.RecordShareDao;
import com.newleader.nlsite.admin.dao.RecordVirusDao;
import com.newleader.nlsite.admin.model.RecordShare;
import com.newleader.nlsite.admin.model.RecordVirus;
import com.newleader.nlsite.common.thread.ShareMsgPopThread;

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
		if (1 <= this.recordShareDao.queryBySceneOpenId(recordShare.getScene(), recordShare.getOpenId())) {
			return true;
		}
		
		//2.查询superid
		RecordVirus recordVirus = this.recordVirusDao.queryByOpenIdAndScene(recordShare.getOpenId(), recordShare.getScene());
		if (null != recordVirus) {
			recordShare.setSuperId(recordVirus.getId());
		}
		return this.recordShareDao.add(recordShare);
	}
	
	
}
