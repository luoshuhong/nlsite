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
		
<!-- 		<script src="http://cdn.hcharts.cn/prototype/prototype-1.7.2.js"></script> -->
<!-- 		<script src="http://code.highcharts.com/adapters/prototype-adapter.js"></script> -->
<!-- 		<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>  -->
	</head>

	<body>
	
		<h2>推广统计</h2>
		<!-- 图表区域  -->
		<div id="container" style="min-width:700px;height:400px"> </div>
		
	</body>
	<script type="text/javascript">
	$(function() {
		
		$.ajax({
			type: "POST",
			url: "${ctx}/admin/channelStat/query",
			async:false,
			success : function(msg) {
				var json = eval("("+msg+")");;
				if (json.status == 'success') {
					var data = JSON.parse(json.data);
					
					//绘制图表
// 					alert(data.xAxis);
// 					alert(data.series);
					
					$('#container').highcharts({
						xAxis:data.xAxis,
						series:data.series
					});
					
				} else {
					alert('错误，请重试！');
				}
			},
			error : function(msg) {
				alert(msg);
			}
		});
	});
	
	</script>
</html> 