<%-- 
Title: admin-left-menu.jsp
Description: 后台左侧菜单页
Company: NewLeader
@author: luoshuhong
date 2015-9-11
@version 1.0 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
		#main-nav {
            margin-left: 1px;
        }
        #main-nav.nav-tabs.nav-stacked > li > a {
            padding: 10px 8px;
            font-size: 12px;
            font-weight: 600;
            color: #4A515B;
            background: #E9E9E9;
            background: -moz-linear-gradient(top, #FAFAFA 0%, #E9E9E9 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FAFAFA), color-stop(100%,#E9E9E9));
            background: -webkit-linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
            background: -o-linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
            background: -ms-linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
            background: linear-gradient(top, #FAFAFA 0%,#E9E9E9 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9');
            -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#FAFAFA', endColorstr='#E9E9E9')";
            border: 1px solid #D5D5D5;
            border-radius: 4px;
        }
        #main-nav.nav-tabs.nav-stacked > li > a > span {
            color: #4A515B;
        }
        #main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover {
            color: #FFF;
            background: #3C4049;
            background: -moz-linear-gradient(top, #4A515B 0%, #3C4049 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#4A515B), color-stop(100%,#3C4049));
            background: -webkit-linear-gradient(top, #4A515B 0%,#3C4049 100%);
            background: -o-linear-gradient(top, #4A515B 0%,#3C4049 100%);
            background: -ms-linear-gradient(top, #4A515B 0%,#3C4049 100%);
            background: linear-gradient(top, #4A515B 0%,#3C4049 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049');
            -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr='#4A515B', endColorstr='#3C4049')";
            border-color: #2B2E33;
        }
        #main-nav.nav-tabs.nav-stacked > li.active > a, #main-nav.nav-tabs.nav-stacked > li > a:hover > span {
            color: #FFF;
        }
        #main-nav.nav-tabs.nav-stacked > li {
            margin-bottom: 4px;
        }
        /*定义二级菜单样式*/
        .secondmenu a {
            font-size: 10px;
            color: #4A515B;
            text-align: center;
        }
        .navbar-static-top {
            background-color: #212121;
            margin-bottom: 5px;
        }
        .navbar-brand {
            background: url('') no-repeat 10px 8px;
            display: inline-block;
            vertical-align: middle;
            padding-left: 50px;
            color: #fff;
        }
        .secondmenu a {
		    font-size: 12px;
		    color: #4A515B;
		    text-align: center;
		}
		.secondmenu li.active {
		    background-color: #eee;
		    border-color: #428bca;
		}
		/*控制菜单箭头*/
		.nav-header.collapsed > span.glyphicon-chevron-toggle:before {
		    content: "\e114";
		}
		.nav-header > span.glyphicon-chevron-toggle:before {
		    content: "\e113";
		}
	</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
					<li class="active">
						<a href="#" >  <i class="glyphicon glyphicon-th-large"></i> 首页</a>
					</li>
					<li>
						<a href="#channelManage" class="nav-header collapsed" data-toggle="collapse"> 
							<i class="glyphicon glyphicon-credit-card" ></i> 渠道管理 <span
							class="pull-right glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul id="channelManage" class="nav nav-list collapse secondmenu" style="height: 0px; ">
							<li><a href="#" onclick="fillDataArea('channelAdd');"><i class="glyphicon glyphicon-plus"></i>渠道添加</a></li>
							<li><a href="#" onclick="fillDataArea('channelQuery');"><i class="glyphicon glyphicon-search"></i>渠道查询</a></li>
						</ul>
					</li>
					<li>
						<a href="#virualManage" class="nav-header collapsed" data-toggle="collapse"> 
							<i class="glyphicon glyphicon-credit-card" ></i> 运营统计 <span class="pull-right glyphicon glyphicon-chevron-down"></span>
						</a>
						<ul id="virualManage" class="nav nav-list collapse secondmenu" style="height: 0px; ">
							<li><a href="#" onclick="fillDataArea('channelStat');"><i class="glyphicon glyphicon-th-list"></i>推广效果统计</a></li>
							<li><a href="#" onclick="fillDataArea('shareStat');"><i class="glyphicon glyphicon-th-list"></i>viral效果图表统计</a></li>
							<li><a href="#" onclick="fillDataArea('viralGroupByDate');"><i class="glyphicon glyphicon-th-list"></i>viral统计-时间维度</a></li>
							<li><a href="#" onclick="fillDataArea('viralGroupByChannel');"><i class="glyphicon glyphicon-th-list"></i>viral统计-渠道维度</a></li>
							<li><a href="#" onclick="fillDataArea('userActive');"><i class="glyphicon glyphicon-th-list"></i>活跃用户统计</a></li>
							<li><a href="#" onclick="fillDataArea('statMajor');"><i class="glyphicon glyphicon-th-list"></i>总表统计</a></li>
							<li><a href="#" onclick="fillDataArea('statUserAccumulative');"><i class="glyphicon glyphicon-th-list"></i>用户累计统计</a></li>
							
<!-- 							<li><a href="#" onclick="fillDataArea('shareVisitStat');"><i class="glyphicon glyphicon-th-list"></i>virual用户统计</a></li> -->
						</ul>
					</li>
<!-- 					<li> -->
<!-- 						<a href="#systemSetting" class="nav-header collapsed" data-toggle="collapse">  -->
<!--   							<i class="glyphicon glyphicon-cog"></i>系统管理  -->
<!-- 							<span class="pull-right glyphicon glyphicon-chevron-down"></span>   -->
<!--  						</a>   -->
<!-- 						<ul id="systemSetting" class="nav nav-list collapse secondmenu" style="height: 0px;"> -->
<!-- 							<li><a href="#"><i class="glyphicon glyphicon-user"></i>用户管理</a></li> -->
<!-- 							<li><a href="#"><i class="glyphicon glyphicon-th-list"></i>菜单管理</a></li> -->
<!-- 							<li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>角色管理</a></li> -->
<!-- 							<li><a href="#"><i class="glyphicon glyphicon-edit"></i>修改密码</a></li> -->
<!-- 							<li><a href="#"><i class="glyphicon glyphicon-eye-open"></i>日志查看</a></li> -->
<!-- 						</ul> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<a href="#"> <i class="glyphicon glyphicon-globe"></i> 分发配置 <span class="label label-warning pull-right">5</span></a> -->
<!-- 					</li> -->
				</ul>
			</div>
			<div id="data"  class="col-md-10" > <h3>这里还没有想好放神马...  Orz </h3></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function fillDataArea(type){
		var iframe = "";
		if (type == 'channelAdd') {
			iframe = "<iframe src='channelAdd.jsp' id='iframepage' frameBorder=0 scrolling=no width='95%' height='90%' onLoad='iFrameHeight()' ></iframe>";
		} else if(type == 'channelQuery') {
			iframe = "<iframe src='channelQuery.jsp' id='iframepage' frameBorder=0 scrolling=no width='95%' height='95%' onLoad='iFrameHeight()' ></iframe>";
		} else if(type == 'channelStat') {
			iframe = "<iframe src='channelStat.jsp' id='iframepage' frameBorder=0 scrolling=no  width='95%' height='95%' onLoad='iFrameHeight()' ></iframe>";
		} else if(type == 'shareStat') {
			iframe = "<iframe src='statViralTrendChart.jsp' id='iframepage' frameBorder=0 scrolling=no  width='95%' height='95%' onLoad='iFrameHeight()' ></iframe>";
		}  else if(type == 'userActive') {
			iframe = "<iframe src='userActiveStat.jsp' id='iframepage' frameBorder=0 scrolling=no  width='95%' height='95%' onLoad='iFrameHeight()' ></iframe>";
		} else if(type == 'statMajor') {
			iframe = "<iframe src='statMajorQuery.jsp' id='iframepage' frameBorder=0 scrolling=no  width='95%' height='95%' onLoad='iFrameHeight()' ></iframe>";
		} else if(type == 'statUserAccumulative') {
			iframe = "<iframe src='statUserAccumulative.jsp' id='iframepage' frameBorder=0 scrolling=no  width='95%' height='95%' onLoad='iFrameHeight()' ></iframe>";
		} else if(type == 'viralGroupByDate') {
			iframe = "<iframe src='statViralGroupByDate.jsp' id='iframepage' frameBorder=0 scrolling=no  width='95%' height='95%' onLoad='iFrameHeight()' ></iframe>";
		} else if(type == 'viralGroupByChannel') {
			iframe = "<iframe src='statViralGroupByChannel.jsp' id='iframepage' frameBorder=0 scrolling=no  width='95%' height='95%' onLoad='iFrameHeight()' ></iframe>";
		} else {
			iframe = "<iframe src='test.jsp' frameBorder=0  width='95%' height='90%' scrolling=no onLoad='iFrameHeight()' ></iframe>";
		}
		 
		$("#data").html(iframe);
	}
	
	function iFrameHeight() {
		var ifm = document.getElementById("iframepage");
		var subWeb = document.frames ? document.frames["iframepage"].document
				: ifm.contentDocument;
		if (ifm != null && subWeb != null) {
			ifm.height = subWeb.body.scrollHeight;
		}
	}
</script>

</html>