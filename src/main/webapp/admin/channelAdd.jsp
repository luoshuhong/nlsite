<%-- 
Title: channelAdd.jsp
Description: 渠道添加
Company: NewLeader
@author: luoshuhong
date 2015-9-11
@version 1.0 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
	</head>

	<body>
	<form class="form-inline" style="margin-top: 20px;" action="${ctx}/channel/add">
		<div class="form-group">
			<label for="channelName">渠道名称：</label> 
			<input type="text" class="form-control" id="channelName" name="channelName" placeholder="请输入渠道名称">
		</div>
		<div class="form-group">
			<label for="channelCode">渠道编码</label> 
			<input type="text" class="form-control" id="channelCode" name="channelCode"  placeholder="请输入渠道编码">
		</div>
		<button type="submit" class="btn btn-default">添加</button>
	</form>
</body>
	
</html> 