<%-- 
Title: channelStat.jsp
Description: 渠道推广统计
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
		<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/highcharts.js"></script>
		<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/highcharts-more.js"></script>
		<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/modules/exporting.js"></script>
		<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/modules/funnel.js"></script>
		<script type="text/javascript" src="${ctx}/js/DatePicker/WdatePicker.js"></script>
<!-- 		<script src="http://cdn.hcharts.cn/prototype/prototype-1.7.2.js"></script> -->
<!-- 		<script src="http://code.highcharts.com/adapters/prototype-adapter.js"></script> -->
<!-- 		<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>  -->
	</head>

	<body>
		<h2>推广统计</h2>
		<form class="form-inline" style="margin: 15 0 10 20;">
			<label for="startDate">日期：</label> 
			<input id="startDate"  type="text" class="form-control" onClick="WdatePicker()" >
			<label for="endDate">~</label> 
			<input type="text" id="endDate" class="form-control" onClick="WdatePicker()">
			<button type="button" onclick="query();" class="btn btn-default">查询</button>
		</form>
		
		<!-- 图表区域  -->
		<div id="container" > </div>
		
		<!-- 退订  -->
		<div id="unsubscribe" style="margin-top: 20px;"> </div>
		
		<!-- 回流 -->
		<div id="backflow" style="margin-top: 20px; margin-bottom: 70px;"> </div>
		
	</body>
	<script type="text/javascript" src="${ctx}/js/admin-channelStat.js"></script>
</html> 