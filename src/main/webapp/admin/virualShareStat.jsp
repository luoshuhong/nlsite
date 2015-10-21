<%-- 
Title: virualShareStat.jsp 
Description: 分享相关统计（分享次数，浏览次数）
Company: NewLeader
@author: luoshuhong
date 2015-10-16
@version 1.0 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <!DOCTYPE html> -->
<html lang="zh-CN">
	<head>
		<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/highcharts.js"></script>
		<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/highcharts-more.js"></script>
		<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/modules/exporting.js"></script>
		<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/modules/funnel.js"></script>
		<script type="text/javascript" src="${ctx}/js/DatePicker/WdatePicker.js"></script>
	</head>

	<body>
		<h2>趋势图展示</h2>
		<form class="form-inline" style="margin: 15 0 10 20;">
			<label for="startDate">日期：</label> 
			<input id="startDate"  type="text" class="form-control" onClick="WdatePicker()" >
			<label for="endDate">~</label> 
			<input type="text" id="endDate" class="form-control" onClick="WdatePicker()">
			<button type="button" onclick="query();" class="btn btn-default">查询</button>
		</form>
		
		<!-- 分享统计 -->
		<div id="shareContainer" > </div>
		
		<!-- 分享浏览统计  -->
		<div id="visitContainer" style="margin-top: 20px;  "> </div>
		
		<!-- virul用户  -->
		<div id="virulUserContainer" style="margin-top: 20px; margin-bottom: 70px;"> </div>
		
	</body>
	<script type="text/javascript" src="${ctx}/js/admin-virualStat.js"></script>
</html> 