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
						<th>名称</th><th>编码</th><th>添加时间</th><th>推广</th>
					</tr>
				</thead>
				<!-- 数据域  -->
				<tbody id="dataListTbody">
				</tbody>
			</table>
		</div>
		
		<div class="modal" id="imgModal">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                <h3 >二维码生成   <small>若二维码未正常显示，请稍等片刻...</small></h3>
	            </div>
	            <div class="modal-body">
<!-- 	            	<p style="color:#AFAFAF; font-size:14px;">如二维码为正常显示，请稍等片刻...</p> -->
	                <img id="img_code" src="" alt="二维码" title="鼠标右键保存图片"><label style="font-size: 20px;">鼠标右键保存图片</label>
	            </div>
	            <div class="modal-footer">
<!-- 	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> -->
	                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
		
	</body>
	
	<script type="text/javascript">
		$(function() {
			$.ajax({
				type: "POST",
				url: "${ctx}/admin/channel/query",
				async:false,
				success : function(msg) {
					var json = eval("("+msg+")");;
					if (json.status == 'success') {
						var dataList = json.data;
						 $.each(JSON.parse(json.data), function (idx,item) {
							 $('#dataListTbody').append('<tr>' 
								+ '<td>' + item.name + '</td>'
								+ '<td>' + item.code + '</td>'
								+ '<td>' + item.createTimeStr + '</td>'
								+ '<td>'
								+ '<a href=\'javascript:qrCodeCreate(\"' + item.code + '\",\"temp\"' + ')\'>临时二维码</a>  | '
								+ '<a href=\'javascript:qrCodeCreate(\"' + item.code + '\",\"perm\"' + ')\'>永久二维码</a>'
								+ '</td></tr>' 
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
		});
		
		//二维码生成
		function qrCodeCreate(code,type) {
			if (isEmpty(code) || isEmpty(type)) {
				return;
			}
			
			if('temp' == type && isNaN(code)){
			   alert("生成临时二维码，编码必须是数字");
			   return;
			}
			
			var postData = {"type":type, "channelCode":code};
	        $.ajax({
				type: "POST",
				url: "${ctx}/admin/qrCode/create",
				data: postData,
				async:false,
				success : function(msg) {
					eval("var json=" + msg);
					if (json.status == 'success') {
						if (isEmpty(json.data)) {
							alert('生成二维码失败，请重试 ！');
							return;
						}
// 						var logo = '&logo=http://dntm.happy6.com.cn:8081/image/logo.jpg';
						var logo = "&logo=http://donottell.me/CSS/Images/homepage/logo.png";
// 						var logo = "";
						$("#img_code").attr('src',"http://qr.liantu.com/api.php?text=" + json.data + logo); 
						$('#imgModal').modal('show');  
					} else {
						alert('添加失败，请重试！');
					}
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