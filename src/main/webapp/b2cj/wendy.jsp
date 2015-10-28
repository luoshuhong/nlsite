<%-- 
Title: wendy.jsp
Description: 推广统计
Company: NewLeader
@author: luoshuhong
date 2015-10-28
@version 1.0 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>

<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.PreparedStatement" %>

<%@ page import="com.newleader.nlsite.common.DateUtils" %>
<%@ page import="com.newleader.nlsite.common.SpringContextUtil" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html lang="zh-CN">
	<head>
		<title>运营数据</title>
		<style type="text/css">
			body
				{
/* 				  text-align:center !important;  */
				}
		</style>
		<%!
			//查询
			private int queryCount(String sql) {
				DataSource dataSource = SpringContextUtil.getBean("dataSource");
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					conn = dataSource.getConnection();
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					while (rs.next()) {
						return rs.getInt(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// 关闭记录集
					try {
						if (rs != null) {
							rs.close();
						}
						if (ps != null) {
							ps.close();
						}
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return 0;
			}
		%>
		
		<%!
			
			//
    	 	String ss= "select count(*) as count from aa_userinfo a where a.LogOnDate > " + DateUtils.getCurrentStringDateYMD();
			
			//#多少个用户填了毕业信息 
			String sql1 = "select count(id) as count from aa_userinfo a where a.ReadingSchool is not null or a.ReadingSchool != '' ";
			int count1 = queryCount(sql1);
			//有多少注册用户
			String sql2 = "select count(id) as count from aa_userinfo";
			int count2 = queryCount(sql2);
			//#多少个用户答完了爱情的题
			String sql3 = "select count(*) as count from if_testing_result";
			int count3 = queryCount(sql3);
        %>
    
	</head>
	<body  >
		<h4>&nbsp;&nbsp; 累计注册人数：<%= count2%></h4>
		<h4>&nbsp;&nbsp; 用户填写了毕业信息人数：<%= count1%></h4>
		<h4>&nbsp;&nbsp; 答完爱情测评人数：<%= count3%></h4>
	</body>
	
</html> 