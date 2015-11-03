<%-- 
Title: statUserAccumulative.jsp
Description: 统计用户累计数据
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
		<h3>用户累计统计</h3>
		<h4>
		 &nbsp;&nbsp; 累计注册人数：<span id="regist"></span> <br>
		 &nbsp;&nbsp; 填写毕业信息人数：<span id="school"></span>  <br>
		 &nbsp;&nbsp; 爱情测评人数：<span id="love"></span> <br>
		 &nbsp;&nbsp; 累计进入爱情人数：<span id="loveInit"></span> <br>
		 &nbsp;&nbsp; 明星DNA人数：<span id="dna"></span> <br>
		 &nbsp;&nbsp; 使命魔镜人数：<span id="value"></span> <br>
		 &nbsp;&nbsp; 特长魔镜人数：<span id="behavior"></span> <br>
		 &nbsp;&nbsp; 力量之境人数：<span id="motivation"></span> <br>
		 &nbsp;&nbsp; 职业报警人数：<span id="passion"></span> <br>
		 </h4>
		 <hr>
		
	</body>
	<script type="text/javascript">
		$(function() {
			totalQuery();
		});
		
		/**
		 *  一些累计数据
		 */
		function totalQuery() {
			$.ajax({
				type: "POST",
				url: "../admin/userActive/totalQuery",
				dataType:"json",
				async:false,
				success : function(msg) {
		            var json = msg;
					if (json.status == 'success') {
						var data = JSON.parse(json.data);
						$("#regist").text(data.regist);
						$("#school").text(data.school);
						$("#love").text(data.love);
						$("#dna").text(data.dna);
						$("#value").text(data.value);
						$("#behavior").text(data.behavior);
						$("#motivation").text(data.motivation);
						$("#passion").text(data.passion);
						$("#loveInit").text(data.loveInit);
					} else {
					}
				} 
			});
		}
		
	</script>
</html> 