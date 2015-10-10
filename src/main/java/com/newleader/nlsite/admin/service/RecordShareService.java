package com.newleader.nlsite.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.RecordShareDao;
import com.newleader.nlsite.admin.model.RecordShare;

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
	
	/**
	 * 
	 * @param recordShare
	 * @return
	 */
	public boolean add(RecordShare recordShare) {
		return this.recordShareDao.add(recordShare);
	}
	
	
	
}
