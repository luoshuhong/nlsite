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
	<form class="form-horizontal" style="margin-top: 20px;">
		<div class="form-group">
			<label for="channelName" class="col-sm-2 control-label">渠道名称：</label> 
			<div class="col-sm-10">
				<input  id="channelName" type="text" class="form-control" name="channelName" style="width: 200px;" placeholder="请输入渠道名称">
			</div>
		</div>
		<div class="form-group">
			<label for="channelCode" class="col-sm-2 control-label">渠道编码：</label> 
			<div class="col-sm-10">
				<input id="channelCode" type="text" class="form-control"  name="channelCode"  style="width: 200px;" placeholder="请输入渠道编码">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button id="addChannel" class="btn btn-primary"  >添加</button>
			</div>
		</div>
		<div class=" col-sm-offset-1 col-sm-4">
			<div id="msg"  role="alert"> </div>
		</div>
	</form>
</body>
<script type="text/javascript">
	$("#addChannel").click(function() {
		var name = $("#channelName").val();
		var code = $("#channelCode").val();
		
		if (isEmpty(name) || isEmpty(code)) {
			return;
		}
		var postData = {"channelName":name, "channelCode":code};
        $.ajax({
			type: "POST",
			url: "${ctx}/admin/channel/add",
			data: postData,
			async:false,
			success : function(msg) {
				eval("var json=" + msg);
				if (json.status == 'success') {
// 					$("#msg").attr("class", "alert alert-success");
// 					$("#msg").html('添加成功！');
					alert('添加成功！');
// 					$("#msg").show();
				} else {
// 					$("#msg").attr("class", "alert alert-danger");
// 					$("#msg").html('添加失败，请重试！');
					alert('添加失败，请重试！');
// 					$("#msg").show();
				}
			}
		});
	});
	
	
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