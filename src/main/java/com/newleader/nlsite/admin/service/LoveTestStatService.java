package com.newleader.nlsite.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.LoveTestStatDao;

/**
 * 爱情测试统计
 * @author Luoshuhong
 * @Company  
 * 2015年10月15日
 *
 */
@Service 
public class LoveTestStatService {
	
	@Autowired
	private LoveTestStatDao loveTestStatDao;
	
	/**
	 * 更新填充 渠道编码
	 */
	public void updateChannelId() {
		this.loveTestStatDao.updateChannelId();
	}
}
