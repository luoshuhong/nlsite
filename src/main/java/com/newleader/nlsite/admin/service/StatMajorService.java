package com.newleader.nlsite.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newleader.nlsite.admin.dao.StatMajorDao;
import com.newleader.nlsite.admin.model.StatMajor;

/**
 * 总表统计相关
 * @author Luoshuhong
 * @Company donottel.me
 * 2015年11月2日
 *
 */
@Service 
public class StatMajorService {
	
	@Autowired
	private StatMajorDao statMajorDao;
	
	/**
	 * 根据时间查询
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public List<StatMajor> queryByDate(String sDate, String eDate) {
		return this.statMajorDao.queyByDate(sDate, eDate);
	}

	/**
	 * 更新
	 * @param statMajor 
	 * @return
	 */
	public boolean update(StatMajor statMajor) {
		return this.statMajorDao.update(statMajor);
	}
}
