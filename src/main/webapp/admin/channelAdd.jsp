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
<html lang="zh-CN">
	<head>
		<script type="text/javascript" src="${ctx}/js/DatePicker/WdatePicker.js"></script>
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
			<label for="channelCode" class="col-sm-2 control-label">免费设置：</label> 
			<div class="col-sm-10">
				<select class="form-control" style="width: 200px;" id="freeType" onchange="choseFreeType();">
				  <option value="-1">无免费</option>
				  <option value="0">限时免费</option>
				  <option value="1">免费1份68元</option>
				  <option value="2">免费3份168</option>
				  <option value="3">免费5份268元</option> 
				</select>
			</div>
		</div>
		<div id="freeDesDiv" class="form-group" style="display: none;">
			<label for="freeDes" class="col-sm-2 control-label">前台文案：</label> 
			<div class="col-sm-10">
				<input id="freeDes"  type="text" class="form-control"   name="freeDes"  style="width: 200px; display: inline;" placeholder="请输入前台展示文案"> 
				<label style="color: red;">XXX</label>已为你免单！最长4个字。
			</div>
		</div>
		<div id="freeStartDateDiv"  class="form-group"  style="display: none;">
			<label for="freeStartDate" class="col-sm-2 control-label">开始时间：</label> 
			<div class="col-sm-10">
				<input id="freeStartDate"  type="text" class="form-control" style="width: 200px;" onClick="WdatePicker()" >
			</div>
		</div>
		<div id="freeEndDateDiv" class="form-group" style="display: none;">
			<label for="freeEndDate" class="col-sm-2 control-label">结束时间：</label> 
			<div class="col-sm-10">
				<input type="text" id="freeEndDate" class="form-control" style="width: 200px;" onClick="WdatePicker()">
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button id="addChannel" class="btn btn-primary"  >添加</button>
			</div>
		</div>
		
	</form>
	
	
</body>
<script type="text/javascript">
	$("#addChannel").click(function() {
		var name = $("#channelName").val();
		var code = $("#channelCode").val();
		var freeDes = $("#freeDes").val();
		var freeStartDate = $("#freeStartDate").val();
		var freeEndDate = $("#freeEndDate").val();
		var freeType = $("#freeType").val();
		
		/******** 参数校验 start ******************/
		if (isEmpty(name) || isEmpty(code)) {
			alert('参数不能为空！');
			return;
		}
		if (0 == freeType) {
			if (isEmpty(freeDes) || isEmpty(freeStartDate) || isEmpty(freeEndDate)) {
				alert('参数不能为空！');
				return;
			}
		}
		if (1 == freeType || 2 == freeType || 3 == freeType) {
			if (isEmpty(freeDes)) {
				alert('参数不能为空！');
				return;
			}
		}
		/******** 参数校验 end ******************/
		
		var postData = {"channelName":name, "channelCode":code, "freeType":freeType, "freeDes":freeDes, "freeStartDate":freeStartDate, "freeEndDate":freeEndDate};
        $.ajax({
			type: "POST",
			url: "${ctx}/admin/channel/add",
			data: postData,
			async:false,
			success : function(msg) {
				eval("var json=" + msg);
				if (json.status == 'success') {
					alert('添加成功！');
				} else {
					alert('添加失败，请重试！');
				}
			}
		});
	});
	
	/**
	 * 选中免费类型  处理事件
	 */
	function choseFreeType() {
		var freeType = $("#freeType").val();
		if (-1 == freeType) {
			$("#freeDesDiv").hide();
			$("#freeStartDateDiv").hide();
			$("#freeEndDateDiv").hide();
			return;
		}
		if (0 == freeType) {
			$("#freeStartDateDiv").show();
			$("#freeEndDateDiv").show();
		} else {
			$("#freeStartDateDiv").hide();
			$("#freeEndDateDiv").hide();
		}
		
		$("#freeDesDiv").show();
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