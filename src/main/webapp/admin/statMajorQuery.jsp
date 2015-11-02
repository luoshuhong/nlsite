<%-- 
Title: statMajorQuery.jsp
Description: 总表统计查询
Company: NewLeader
@author: luoshuhong
date 2015-11-2
@version 1.0 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <!DOCTYPE html> -->
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
			<button type="button" onclick="query();" class="btn btn-default">查询</button>
		</form>
		
		
		<div class="table-responsive">
			<table class="table table-bordered table-hover">
				<thead class="thead">
					<tr>
<!-- 					日期	新关注人数(微信） 	取消关注人数(微信）	净增关注人数(微信） -->
<!-- 					累积关注人数(微信）	总用户数（62805+9月23日的每日新关注人数） 职业新增用户	职业累计用户	爱情新增用户	爱情累计用户 -->
						<th>日期</th><th>新关注</th><th>取消关注</th><th>净增关注</th><th>累计关注</th><th>总用户</th>
						<th>新增职业</th><th>累计职业</th><th>新增爱情</th><th>累计爱情</th><th>更新</th>
					</tr>
				</thead>
				<!-- 数据域  -->
				<tbody id="dataListTbody">
				</tbody>
			</table>
		</div>
		<div style="margin-top: 50;"></div>
		
	  <!-- 更新渠道信息弹框 -->
		<div class="modal" id="updateModal"  >
	     <div class="modal-dialog" style="width:500px;">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                <h3 >更新</h3>
	            </div>
	            <div class="modal-body">
	                <form class="form-horizontal" >
						<div class="form-group ">
							<label for="wxSubscribe" class="col-sm-4 control-label">新增关注人数：</label> 
							<input  id="wxSubscribe" type="text" class="form-control" name="wxSubscribe" style="width: 300px;" >
						</div>
						<div class="form-group">
							<label for="wxUnsubscribe" class="col-sm-4 control-label">取消关注人数：</label> 
							<input id="wxUnsubscribe" type="text"  class="form-control"  name="wxUnsubscribe"  style="width: 300px;" >
						</div>
						<div class="form-group">
							<label for="wxPureSubscribe" class="col-sm-4 control-label">净增关注人数：</label> 
							<input id="wxPureSubscribe" type="text"  class="form-control"  name="wxPureSubscribe"  style="width: 300px;" ">
						</div>
						<div class="form-group">
							<label for="wxTotal" class="col-sm-4 control-label">累计关注人数：</label> 
							<input id="wxTotal" type="text"  class="form-control"  name="wxTotal"  style="width: 300px;" >
						</div>
						<div class="form-group">
							<label for="totalUser" class="col-sm-4 control-label">总用户数：</label> 
							<input id="totalUser" type="text"  class="form-control"  name="totalUser"  style="width: 300px;" >
						</div>
						<input  id="date" type="hidden"  name="date" >
					</form>
					<div id="errMsg" style="color:red; text-align:center;"></div>
	            </div>
	            <div class="modal-footer" >
	            	<button type="button" class="btn btn-primary" onclick="updateComfirm();">确认</button>
	                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	  </div><!-- /.modal -->
		
	</body>
	<script type="text/javascript">
		$(function() {
			initQueryDate();
			loadData('');
		});
		
		//加载数据
		function loadData(value) {
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var postData = {"startDate":startDate, "endDate":endDate};
			$.ajax({
				type: "POST",
				url: "../admin/majorStat/query",
				data: postData,
				async:false,
				success : function(msg) {
					var json = eval("("+msg+")");;
					if (json.status == 'success') {
						$('#dataListTbody').html('');
						var dataList = json.data;
						 $.each(JSON.parse(json.data), function (idx,item) {
// 								<th>日期</th><th>新关注</th><th>取消关注</th><th>净增关注</th><th>累计关注</th><th>总用户</th>
// 								<th>新增职业</th><th>累计职业</th><th>新增爱情</th><th>累计爱情</th>
							 $('#dataListTbody').append('<tr>' 
								+ '<td>' + item.date + '</td>'
								+ '<td>' + item.wxSubscribe + '</td>'
								+ '<td>' + item.wxUnsubscribe + '</td>'
								+ '<td>' + item.wxPureSubscribe + '</td>'
								+ '<td>' + item.wxTotal  + '</td>'
								+ '<td>' + item.totalUser + '</td>'
								+ '<td>' + item.proIncrease + '</td>'
								+ '<td>' + item.proTotal + '</td>'
								+ '<td>' + item.loveIncrease + '</td>'
								+ '<td>' + item.loveTotal + '</td>'
								+ ' <td><a href=\'javascript:update(\"' + item.date + '\",\"' + item.wxSubscribe  + '\",\"' + item.wxUnsubscribe + '\",\"' + item.wxPureSubscribe + '\",\"' + item.wxTotal + '\",\"' + item.totalUser + '\")\'>更新</a></td>'
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

		//更新name:渠道名 
		function update(date,wxSubscribe, wxUnsubscribe, wxPureSubscribe, wxTotal, totalUser ) {
			$('#date').val(date);
			$('#wxSubscribe').val(wxSubscribe);
			$('#wxUnsubscribe').val(wxUnsubscribe);
			$('#wxPureSubscribe').val(wxPureSubscribe);
			$('#wxTotal').val(wxTotal);
			$('#totalUser').val(totalUser);
			$('#updateModal').modal('show');  
		}

		//更新保存
		function updateComfirm() {
			var date = $('#date').val();
			var wxSubscribe = $('#wxSubscribe').val();
			var wxUnsubscribe = $('#wxUnsubscribe').val();
			var wxPureSubscribe = $('#wxPureSubscribe').val();
			var wxTotal = $('#wxTotal').val();
			var totalUser = $('#totalUser').val();
			
			var postData = {"date":date,"wxSubscribe":wxSubscribe, "wxUnsubscribe":wxUnsubscribe, "wxPureSubscribe":wxPureSubscribe, "wxTotal":wxTotal, "totalUser":totalUser};
		    $.ajax({
				type: "POST",
				url: "../admin/majorStat/update",
				data: postData,
				async:false,
				success : function(msg) {
					eval("var json=" + msg);
					if (json.status == 'success') {
						$('#dataListTbody').html('');
						loadData();
						$('#updateModal').modal('hide'); 
					} else {
						$("#errMsg").html('修改失败，请重试！');
						$("#errMsg").show();
					}
				}
			});
		}
		
	</script>
</html> 