package com.newleader.nlsite.admin.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.newleader.nlsite.admin.dao.inter.DaoInter;
import com.newleader.nlsite.admin.model.Channel;

/**
 * 
 * @author Luoshuhong
 * @Company  
 * 2015年9月11日
 *
 */
public class ChannelDao extends JdbcDaoSupport implements DaoInter<Channel> {
	@Override
	public boolean add(Channel t) {
		String insertSql = "insert into aa_channel(id,Name,Code,CreateTime,QrcodeUrl,delete_flag) values(?,?,?,?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql,
				new Object[] { t.getId(), t.getName(), t.getCode(),new Date(),t.getQrCodeUrl(),"0"});
	}
	
	@Override
	public boolean del(String id) {
		String delSql = "update aa_channel set delete_flag = 1 where id = ?";
		return 1 == this.getJdbcTemplate().update(delSql,id);
	}
	
	/**
	 * 根据编码查询
	 * @param code
	 * @return
	 */
	public Channel queryModelByCode(String code) {
		String selectSql = "select a.Id, a.Code,a.Name,a.CreateTime,a.QrcodeUrl ,a.UserCount,a.DisCountUsed,a.shareCount,a.virualCount,"
				+ " b.type, b.name freeDes, b.freeStartDate, b.freeEndDate ,b.freeCount "
				+ "from aa_channel a LEFT JOIN  aa_channel_activity b on  a.code = b.code where  a.delete_flag <> '1' and a.code=? order by CreateTime desc";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(selectSql, new Object[]{code});
		for (Map<String,Object> map : list) {
			return this.wrapModel(map);
		}
		return null;
	}
	
	@Override
	public List<Channel> query() {
		List<Channel> channelList = new ArrayList<Channel>();
		String selectSql = "select a.Id, a.Code,a.Name,a.CreateTime,a.QrcodeUrl ,a.UserCount,a.DisCountUsed,a.shareCount,a.virualCount,"
				+ " b.type, b.name freeDes, b.freeStartDate, b.freeEndDate ,b.freeCount "
				+ "from aa_channel a LEFT JOIN  aa_channel_activity b on  a.code = b.code where  a.delete_flag <> '1' order by CreateTime desc";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(selectSql);
		for (Map<String,Object> map : list) {
			Channel model = this.wrapModel(map);
			if (null != model) {
				channelList.add(model);
			}
		}
		return channelList;
	}
	
	/**
	 * 根据code 查询是否存在
	 * @param code 渠道编码
	 * @return 个数
	 */
	@SuppressWarnings("deprecation")
	public int queryByCode(String code) {
		String selSql = "select count(id) from aa_channel where Code = ?";
		return this.getJdbcTemplate().queryForInt(selSql, new Object[]{code});
	}
	
	/**
	 * 更新时 检查渠道是否存在
	 * @param code 渠道编码
	 * @return 个数
	 */
	@SuppressWarnings("deprecation")
	public int updateCheckCode(String code, String id) {
		String selSql = "select count(id) from aa_channel where Code = ? and id <> ?";
		return this.getJdbcTemplate().queryForInt(selSql, new Object[]{code, id});
	}
	
	/**
	 * 模糊查询
	 * @param value
	 * @return
	 */
	public List<Channel> vagueQuery(String value) {
		List<Channel> channelList = new ArrayList<Channel>();
		String selectSql = "select a.Id, a.Code,a.Name,a.CreateTime,a.QrcodeUrl ,a.UserCount,a.DisCountUsed,a.shareCount,a.virualCount,"
				+ " b.type, b.name freeDes, b.freeStartDate, b.freeEndDate,b.freeCount "
				+ "from aa_channel a LEFT JOIN  aa_channel_activity b on a.code = b.code "
				+ " where  a.delete_flag <> '1' and (a.Name like '%" + value + "%' or a.Code like '%" + value + "%') order by a.CreateTime desc";
		List<Map<String,Object>> list = this.getJdbcTemplate().queryForList(selectSql);
		for (Map<String,Object> map : list) {
			Channel model = this.wrapModel(map);
			if (null != model) {
				channelList.add(model);
			}
		}
		return channelList;
	}
	
	@Override
	public boolean update(Channel t) {
		String updateSel = "update aa_channel set Name = ?,Code=?,QrcodeUrl=? where Id = ?";
		return 1 == this.getJdbcTemplate().update(updateSel,t.getName(), t.getCode(), t.getQrCodeUrl(), t.getId());
	}
	
	/**
	 * 更新渠道关注量
	 * @param shareCount
	 * @param virualCount
	 * @param totalSubscribe
	 * @param unSubscribe
	 * @param code
	 * @return
	 */
	public boolean updateByCode(int shareCount, int virualCount, int totalSubscribe, int unSubscribe, String code) {
		String updateSel = "update aa_channel set shareCount=?,virualCount=?, UserCount = ?,DisCountUsed=? where Code = ?";
		return 1 == this.getJdbcTemplate().update(updateSel,shareCount, virualCount, totalSubscribe, unSubscribe, code);
	}
	
	/************  渠道免费信息相关 start ****************************/
//	`code` varchar(30) CHARACTER SET utf8 DEFAULT '' COMMENT '渠道编码(同aa_channel中code)',
//	  `type` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '类型  0：限时免费  1：免费1份68元 2：免费3份168 3：免费5份268元',
//	  `name` varchar(20) CHARACTER SET utf8 DEFAULT '' COMMENT '免费文案提示名称：“xxx已为您免单”中xxx',
//	  `freeStartDate` datetime DEFAULT NULL COMMENT 'type=0有效，免费开始时间',
//	  `freeEndDate` datetime DEFAULT NULL COMMENT 'type=0有效，免费结束时间',
	public boolean addChannelActivity(Channel model) {
		String insertSql = "insert into aa_channel_activity(code,type,name,freeStartDate,freeEndDate,freeCount) values(?,?,?,?,?,?)";
		return 1 == this.getJdbcTemplate().update(insertSql,new Object[]{model.getCode(), model.getFreeType(),
				model.getFreeDes(),	model.getFreeStartDate(), model.getFreeEndDate(),model.getFreeCount()});
	}
	/**
	 * 根据code查询是否存在 渠道免费信息
	 * @param code 渠道编码
	 * @return 数量
	 */
	@SuppressWarnings("deprecation")
	public int queryChannelActivity(String code) {
		String selSql = "select count(*) from  aa_channel_activity where code = ?";
		return this.getJdbcTemplate().queryForInt(selSql, new Object[]{code});
	}
	public boolean updateChannelActivity(Channel model) {
		String insertSql = "update aa_channel_activity set type=?,name=?, freeStartDate=?,freeEndDate=?,freeCount=? where code = ?";
		return 1 == this.getJdbcTemplate().update(insertSql,new Object[]{ model.getFreeType(),
				model.getFreeDes(),	model.getFreeStartDate(), model.getFreeEndDate(),model.getFreeCount(),model.getCode()});
	}
	
	/************  渠道免费信息相关 end ****************************/
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	private Channel wrapModel(Map<String,Object> map) {
		Channel model = new Channel();
		String code = "";
		String name = "";
		String id = "";
		String qrcodeUrl = "";
		if (map.containsKey("Id") && null != map.get("Id")) {
			id = map.get("Id").toString();
		}
		if (map.containsKey("Code") && null != map.get("Code")) {
			code = map.get("Code").toString();
		}
		if (map.containsKey("Name") && null != map.get("Name")) {
			name = map.get("Name").toString();
		}
		if (map.containsKey("QrcodeUrl") && null != map.get("QrcodeUrl")) {
			qrcodeUrl = map.get("QrcodeUrl").toString();
		}
		if (map.containsKey("CreateTime") && null != map.get("CreateTime")) {
			model.setCreateTimeStr(map.get("CreateTime").toString());
		}
		
		if (map.containsKey("UserCount") && null != map.get("UserCount")) {
			model.setTotalSubscribe(Integer.valueOf(map.get("UserCount").toString()));
		} 
		
		if (map.containsKey("DisCountUsed") && null != map.get("DisCountUsed")) {
			model.setUnSubscribe(Integer.valueOf(map.get("DisCountUsed").toString()));
		} 
		if (map.containsKey("shareCount") && null != map.get("shareCount")) {
			model.setShareCount(Integer.valueOf(map.get("shareCount").toString()));
		} 
		if (map.containsKey("virualCount") && null != map.get("virualCount")) {
			model.setVirualCount(Integer.valueOf(map.get("virualCount").toString()));
		} 
		
		//下面是渠道免费信息
		if (map.containsKey("type") && null != map.get("type")) {
			model.setFreeType(map.get("type").toString());
		} 
		if (map.containsKey("freeDes") && null != map.get("freeDes")) {
			model.setFreeDes(map.get("freeDes").toString());
		} 
		if (map.containsKey("freeStartDate") && null != map.get("freeStartDate")) {
			model.setFreeStartDate(map.get("freeStartDate").toString());
		} 
		if (map.containsKey("freeEndDate") && null != map.get("freeEndDate")) {
			model.setFreeEndDate(map.get("freeEndDate").toString());
		} 
		if (map.containsKey("freeCount") && null != map.get("freeCount")) {
			model.setFreeCount(Integer.valueOf(map.get("freeCount").toString()));
		} 
		
		//这里先只处理不为空的
		if (StringUtils.isEmpty(code) || StringUtils.isEmpty(name)) {
			return null;
		}
		
		model.setCode(code);
		model.setName(name);
		model.setId(id);
		model.setQrCodeUrl(qrcodeUrl);
		return model;
	}
}
