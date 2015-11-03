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
<!-- <!DOCTYPE html> -->
<html lang="zh-CN">
	<head>
		<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
		<script type="text/javascript" src="${ctx}/js/admin-channelQuery.js?v=1.0"></script>
		<script type="text/javascript" src="${ctx}/js/DatePicker/WdatePicker.js"></script>
	</head>

	<body>
		<div class="row">
			<div class="col-xs-3">
				<h2 >渠道查询</h2>
			</div>
			<div class="col-xs-9" style="margin-top: 20px; text-align: right;">
				<form class="form-inline" action="">
				    <input type="text"  class="form-control" id="value" onblur="query();" name="value" placeholder="请输入查询条件">
				    <button type="button" id="queryBtn" onclick="query();" class="btn btn-primary">query</button>
				</form>
			</div>
		</div>
		
		<div class="table-responsive">
			<table class="table table-bordered table-hover">
				<thead class="thead">
					<tr>
						<th>名称</th><th>编码</th>
						<th>累计</th><th>当前</th><th>退订</th><th>退订率</th>
						<th>分享</th><th>Viral</th><th>合计</th>
						<th>渠道免费</th><th>添加时间</th><th>操作</th>
					</tr>
				</thead>
				<!-- 数据域  -->
				<tbody id="dataListTbody">
				</tbody>
			</table>
		</div>
		<div style="margin-top: 50;"></div>
		
		<!-- 二维码生成弹框 -->
		<div class="modal" id="imgModal" style="margin: auto">
	     <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                <h3 >二维码生成   <small>若二维码未正常显示，请稍等片刻...</small></h3>
	            </div>
	            <div class="modal-body"  style="width: 490px; height: 320px;">
						<img id="img_code" src=""  style="width: 300px; height: 300px;"  alt="二维码" title="鼠标右键保存图片"><label style="font-size: 20px;">鼠标右键保存图片</label>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	  </div><!-- /.modal -->
	  
	  <!-- 更新渠道信息弹框 -->
		<div class="modal" id="updateModal"  >
	     <div class="modal-dialog" style="width:500px;">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                <h3 >更新渠道信息</h3>
	            </div>
	            <div class="modal-body">
	                <form class="form-horizontal" >
						<div class="form-group ">
							<label for="channelName" class="col-sm-3 control-label">渠道名称：</label> 
							<input  id="uchannelName" type="text" class="form-control" name="uchannelName" style="width: 300px;" placeholder="请输入渠道名称">
						</div>
						<div class="form-group">
							<label for="channelCode" class="col-sm-3 control-label">渠道编码：</label> 
							<input id="uchannelCode" type="text" readonly="readonly" class="form-control"  name="uchannelCode"  style="width: 300px;" placeholder="请输入渠道编码">
						</div>
						
						<div class="form-group">
							<label for="channelCode" class="col-sm-3 control-label">免费设置：</label> 
								<select class="form-control" style="width: 150px;" id="freeType" onchange="choseFreeType();">
								  <option value="-1">无免费</option>
								  <option value="0">限时免费</option>
								  <option value="1">渠道免费</option>
								</select>
						</div>
						<div id="freeCountDiv" class="form-group">
							<label for="freeCount" class="col-sm-3 control-label">免费份数：</label> 
								<select class="form-control" style="width: 150px;" id="freeCount" >
								  <option value="1">免费1份68元</option>
								  <option value="3">免费3份168</option>
								  <option value="5">免费5份268元</option> 
								</select>
						</div>
						<div id="freeDesDiv" class="form-group"  >
							<label for="freeDes" class="col-sm-3 control-label">前台文案：</label> 
								<input id="freeDes"  type="text" class="form-control"   name="freeDes"  style="width: 150px; display: inline;" placeholder="请输入前台展示文案"> 
								<label style="color: red;">XXX</label>已为你免单！最长4个字。
						</div>
						<div id="freeDateDiv"  class="form-group"  >
								<label for="freeStartDate" class="col-sm-3 control-label" style="display: inline;">开始时间：</label> 
								<input id="freeStartDate"  type="text" class="form-control" style="width: 120px;display: inline;" onClick="WdatePicker()" > &nbsp;
								<label for="freeEndDate"  style="display: inline;">结束时间：</label>
								<input id="freeEndDate"  type="text" class="form-control" style="width: 120px;display: inline;" onClick="WdatePicker()" >
						</div>
						
						<input  id="uid" type="hidden"  name="uid" >
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
	
</html> 