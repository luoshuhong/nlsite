package com.newleader.nlsite.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.VisitorChannelDao;

/**
 * 用户关注/取消关注 渠道记录
 * @author Luoshuhong
 * @Company  
 * 2015年10月15日
 *
 */
@Service 
public class VisitorChannelService {
	@Autowired
	private VisitorChannelDao visitorChannelDao;
}
