<%-- 
Title: statViralGroupByDate.jsp
Description: 从时间维度统计Viral信息
Company: NewLeader
@author: luoshuhong
date 2015-11-2
@version 1.0 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
		<script type="text/javascript" src="${ctx}/js/DatePicker/WdatePicker.js"></script>
	</head>

	<body>
		<h2>信息统计</h2>
		<form class="form-inline" style="margin: 15 0 10 20;">
			<label for="startDate">日期：</label> 
			<input id="startDate"  type="text" class="form-control" onClick="WdatePicker()" >
			<label for="endDate">~</label> 
			<input type="text" id="endDate" class="form-control" onClick="WdatePicker()">
			<button type="button" onclick="loadData('love');" class="btn btn-default">爱情测评</button>
			<button type="button" onclick="loadData('pro');" class="btn btn-default">职业测评</button>
		</form>
		
		<div class="table-responsive">
			<table class="table table-bordered table-hover">
				<!-- 表头  -->
				<thead class="thead" id="dataListThead"></thead>
				<!-- 数据域  -->
				<tbody id="dataListTbody"></tbody>
			</table>
		</div>
		<div style="margin-top: 50;"></div>
		
	</body>
	<script type="text/javascript">
		$(function() {
			initQueryDate();
			loadData('love');
		});
		
		//加载数据
		function loadData(value) {
			var url = "../admin/virualStat/queryLoveGroupDate";
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var postData = {"startDate":startDate, "endDate":endDate};
			if ('love' == value) {
				url = "../admin/virualStat/queryLoveGroupDate";
				$.ajax({
					type: "POST",
					url: url,
					data: postData,
					async:false,
					success : function(msg) {
						var json = eval("("+msg+")");;
						if (json.status == 'success') {
							$('#dataListThead').html('');
							$('#dataListTbody').html('');
							//表头
							 $('#dataListThead').append('<tr>' 
									 + '<th>日期</th><th>总分享</th><th>总浏览</th><th>总viral</th><th>圈圈页分享</th><th>圈圈页浏览</th>'
									 + '<th>圈圈页viral</th><th>匹配页分享</th><th>匹配页浏览</th><th>匹配页viral</th>'
									 + '</tr>' 
							  );
							var dataList = json.data;
							 $.each(JSON.parse(json.data), function (idx,item) {
								 $('#dataListTbody').append('<tr>' 
									+ '<td>' + item.date + '</td>'
									+ '<td>' + item.totalShare + '</td>'
									+ '<td>' + item.totalVisit + '</td>'
									+ '<td>' + item.totalViral + '</td>'
									+ '<td>' + item.spiderShare  + '</td>'
									+ '<td>' + item.spiderVisit + '</td>'
									+ '<td>' + item.spiderViral + '</td>'
									+ '<td>' + item.matchShare + '</td>'
									+ '<td>' + item.matchVisit + '</td>'
									+ '<td>' + item.matchViral + '</td>'
									+ '</tr>' 
								 );
							 });
						} else {
							alert('错误，请重试！');
						}
					},
					error : function(msg) {
						alert(msg);
					}
				});
			} else {
				//职业查询
				url = "../admin/virualStat/queryProGroupDate";
				$.ajax({
					type: "POST",
					url: url,
					data: postData,
					async:false,
					success : function(msg) {
						var json = eval("("+msg+")");;
						if (json.status == 'success') {
							$('#dataListThead').html('');
							$('#dataListTbody').html('');
							//表头
							 $('#dataListThead').append('<tr>' 
									 + '<th>日期</th><th>总分享</th><th>总浏览</th><th>总viral</th><th>DNA分享</th><th>DNA浏览</th>'
									 + '<th>DNAviral</th><th>排行榜分享</th><th>排行榜浏览</th><th>排行榜viral</th>'
									 + '</tr>' 
							  );
							var dataList = json.data;
							 $.each(JSON.parse(json.data), function (idx,item) {
								 $('#dataListTbody').append('<tr>' 
									+ '<td>' + item.date + '</td>'
									+ '<td>' + item.totalShare + '</td>'
									+ '<td>' + item.totalVisit + '</td>'
									+ '<td>' + item.totalViral + '</td>'
									+ '<td>' + item.dnaShare  + '</td>'
									+ '<td>' + item.dnaVisit + '</td>'
									+ '<td>' + item.dnaViral + '</td>'
									+ '<td>' + item.listShare + '</td>'
									+ '<td>' + item.listVisit + '</td>'
									+ '<td>' + item.listViral + '</td>'
									+ '</tr>' 
								 );
							 });
						} else {
							alert('错误，请重试！');
						}
					},
					error : function(msg) {
						alert(msg);
					}
				});
			}
		}
		
		//默认初始化查询时间范围
		function initQueryDate() {
			var now = new Date();
			var starDate = new Date(now.getTime() - 86400000 * 7);
			var endDate = new Date(now.getTime() + 86400000);
			var start = starDate.getFullYear() + "-" + (starDate.getMonth() + 1) + "-"
					+ starDate.getDate();
			var end = endDate.getFullYear() + "-" + (endDate.getMonth() + 1) + "-"
					+ endDate.getDate();
			$("#startDate").val(start);
			$("#endDate").val(end);
		}
	</script>
</html> 