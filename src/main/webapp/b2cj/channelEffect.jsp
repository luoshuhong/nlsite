<%-- 
Title: channelQuery.jsp
Description: 推广统计
Company: NewLeader
@author: luoshuhong
date 2015-9-11
@version 1.0 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
	<head>
		<title>渠道推广效果统计</title>
		<style type="text/css">
			body
				{
				  text-align:center !important; 
				}
		</style>
	</head>
	<body  >
<!-- 		<div class="row"> -->
<!-- 		    <div class="col-xs-3"></div> -->
<!-- 			<div class="col-xs-3">  -->
<!-- 				<h2 >推广效果展示</h2> -->
<!-- 			</div> -->
<!-- 			<div class="col-xs-3" style="margin-top: 20px; text-align: left;"> -->
<!-- 				<form class="form-inline" action=""> -->
<!-- 				    <input type="text"  class="form-control" id="value" onblur="query();" name="value" placeholder="请输入渠道编码"> -->
<!-- 				    <button type="button" id="queryBtn" onclick="query();" class="btn btn-primary">query</button> -->
<!-- 				</form> -->
<!-- 			</div> -->
<!-- 			<div class="col-xs-3"></div> -->
<!-- 		</div> -->
		<h2 style="text-align: center;">大学生圈推广1015即时数据</h2>
<!-- 		<h2 style="text-align: center;">大学生圈推广1010即时数据</h2> -->
		<table class="table table-bordered table-hover"  style="width: 450px; margin: auto;" >
			<thead class="thead">
				<tr>
					<th>编码</th><th>累计</th><th>当前</th><th>退订</th><th>退订率</th>
				</tr>
			</thead>
			<!-- 数据域  -->
			<tbody id="dataListTbody">
			</tbody>
		</table>
		
	</body>
	<script type="text/javascript">
		$(function() {
			loadData('daxueshengquan101501,daxueshengquan101502,daxueshengquan101503,daxueshengquan101504,daxueshengquan101505,daxueshengquan101506,daxueshengquan101507,daxueshengquan101508,daxueshengquan101509,daxueshengquan101510');
// 			loadData('daxueshengquan101001,daxueshengquan101002,daxueshengquan101003,daxueshengquan101004,daxueshengquan101005,daxueshengquan101006,daxueshengquan101007,daxueshengquan101008,daxueshengquan101009,daxueshengquan101010');
		});
		
		/**
		 * 查询数据
		 */
		function query(){
			var value = $("#value").val();
			if (isEmpty(value)) {
				return;
			}
			loadData(value);
		}
		
		//加载数据
		function loadData(value) {
			var postData = {"channel":value};
			$.ajax({
				type: "POST",
				url: "../admin/channel/queryEffect",
				data: postData,
				async:false,
				success : function(msg) {
					var json = eval("("+msg+")");;
					if (json.status == 'success') {
						$('#dataListTbody').html('');
						var dataList = json.data;
						 $.each(JSON.parse(json.data), function (idx,item) {
							 $('#dataListTbody').append('<tr>' 
								+ '<td>' + item.code + '</td>'
								+ '<td>' + item.totalSubscribe + '</td>'
								+ '<td>' + item.currSubscribe + '</td>'
								+ '<td>' + item.unSubscribe + '</td>'
								+ '<td>' + item.unSubscribeRate + '%</td>'
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
		
		/**
		 *校验是否为null
		 */
		function isEmpty(value) {
			if ('' == value || undefined == value || null == value) {
				return true;
			}
			return false;
		}
	
	</script>
</html> 