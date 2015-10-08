<%-- 
Title: taglibs.jsp
Description: 公共资源引入
Company: NewLeader
@author: luoshuhong
date 2015-9-11
@version 1.0 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="session"/>

<meta charset="utf-8">
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="X-UA-Compatible" CONTENT="IE=edge">
<META NAME="viewport" CONTENT="width=device-width, initial-scale=1.0">


<!-- cdn  -->
<!-- 	<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<!-- 	<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"> -->
<!-- 	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script> -->
<!-- 	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
    
<!-- 本地 -->
<link type="text/css"  href="${ctx}/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript"  src="${ctx}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/bootstrap-3.3.5/js/bootstrap.min.js"></script>

<style type="text/css">
	body {
		font-family: '微软雅黑','Microsoft YaHei' !important;
		color:#333
	}
</style>

<!--[if lt IE 9]>
  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->