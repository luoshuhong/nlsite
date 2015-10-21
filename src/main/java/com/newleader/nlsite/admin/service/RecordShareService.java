package com.newleader.nlsite.admin.service;

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
			RecordShare superModel = this.recordShareDao.queryBySceneOpenId(recordShare.getScene(), recordVirus.getsOpenId());
			if (null != superModel) {
				recordShare.setSuperId(superModel.getId());
			}
		}
		return this.recordShareDao.add(recordShare);
	}
	
	
}
