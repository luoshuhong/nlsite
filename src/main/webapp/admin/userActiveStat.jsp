<%-- 
Title: userActiveStat.jsp 
Description:用户活跃度统计
Company: NewLeader
@author: luoshuhong
date 2015-10-22
@version 1.0 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/highcharts.js"></script>
		<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/highcharts-more.js"></script>
		<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/modules/exporting.js"></script>
		<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/modules/funnel.js"></script>
		<script type="text/javascript" src="${ctx}/js/DatePicker/WdatePicker.js"></script>
	</head>

	<body>
		<h3>用户活跃度统计</h3>
		<form class="form-inline" style="margin: 15 0 10 20;">
			<label for="startDate">日期：</label> 
			<input id="startDate"  type="text" class="form-control" onClick="WdatePicker()" >
			<label for="endDate">~</label> 
			<input type="text" id="endDate" class="form-control" onClick="WdatePicker()">
			<button type="button" onclick="query();" class="btn btn-default">查询</button>
		</form>
		
		<!-- 用户独立访(UV)问统计-->
		<div id="uVContainer" > </div>
		
		<!-- PV统计(30min以内算一次)   -->
		<div id="pVContainer" style="margin-top: 20px; margin-bottom: 70px;  "> </div>
		 
		<!-- 产品活跃统计  -->
	</body>
	<script type="text/javascript" src="${ctx}/js/admin-userActiveStat.js"></script>
	<script type="text/javascript">
		query();          //查询数据
	</script>
	
</html> 