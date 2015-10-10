package com.newleader.nlsite.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.RecordVirusDao;
import com.newleader.nlsite.admin.model.RecordVirus;

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
	
	public boolean add(RecordVirus recordVirus) {
		return this.add(recordVirus);
	}
	
	
}
