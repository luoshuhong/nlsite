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
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
	</head>

	<body>
		<h2>渠道查询</h2>
		<div class="table-responsive">
			<table class="table table-bordered table-hover">
				<thead class="thead">
					<tr>
						<th>名称</th><th>编码</th><th>添加时间</th><th>推广</th><th>操作</th>
					</tr>
				</thead>
				<!-- 数据域  -->
				<tbody id="dataListTbody">
				</tbody>
			</table>
		</div>
		<div style="margin-top: 50;"></div>
		
		<!-- 二维码生成弹框 -->
		<div class="modal" id="imgModal">
	     <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                <h3 >二维码生成   <small>若二维码未正常显示，请稍等片刻...</small></h3>
	            </div>
	            <div class="modal-body">
	                <img id="img_code" src="" alt="二维码" title="鼠标右键保存图片"><label style="font-size: 20px;">鼠标右键保存图片</label>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	  </div><!-- /.modal -->
	  
	  <!-- 更新渠道信息弹框 -->
		<div class="modal" id="updateModal">
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
							<input id="uchannelCode" type="text" class="form-control"  name="uchannelCode"  style="width: 300px;" placeholder="请输入渠道编码">
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
	<script type="text/javascript" src="${ctx}/js/admin-channelQuery.js"></script>
</html> 