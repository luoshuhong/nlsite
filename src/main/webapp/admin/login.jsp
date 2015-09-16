
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
    	<style type="text/css">
			body {
				background: #444;
			}
			.loginBox {
				padding: 0 15px;
				border: 1px solid #fff;
				color: #000;
				border-radius: 8px;
				background: white;
				box-shadow: 0 0 15px #222;
  				position: absolute;  
				width: 350px;
				height: 250px;
  				left: 50%;  
  				top: 50%;  
   				margin-left: -225px;   
   				margin-top: -160px;   
			}
			.loginBox h2 {
				height: 40px;
				font-size: 25px;
				font-weight: normal;
			}
		</style>
	</head>
	
	<body>
		<div class="container">
		  <div class="loginBox row">
		  		<h2 class="text-center">后台登录</h2>
				<form id="wyccn" name="wyccn"  method="post" class="form-horizontal">
				  <div class="form-group has-success">
				    <label for="username" class="col-sm-3 col-md-3 control-label">用户名</label>
				    <div class="col-sm-9 col-md-9">
				      <input id="username" type="text" class="form-control" name="username" placeholder="用户名" value="" required>
				    </div>
				  </div>
				  <div class="form-group has-success">
				    <label for="password" class="col-sm-3 col-md-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
				    <div class="col-sm-9 col-md-9">
				      <input id ="password" type="password" class="form-control" name="password" placeholder="密码" required>
				    </div>
				  </div>
			  	  <div class="form-group">
			  	  	<div id="errMsg" class="col-sm-offset-4 col-sm-10" style="color: #990033;"></div> <!-- 这里是登陆错误信息 -->
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-3 col-sm-10 col-md-10">
				      	<button id="loginBtn" class="btn btn-info" data-loading-text="正在登录..." type="submit" >登 录</button>
				      	    <button class="btn btn-info" type="reset">清 空</button>
				    </div>
				  </div>
		  		</form>
			</div>
		</div>
		<!--.content-->
	</body>
	
	<script type="text/javascript">
   		$(document).ready(function(){
   			$('#loginBtn').click(function(){
   				login();
   			});
   		});
   		// 登录方法
        function login() {
        	$('#login-form').form({
                url: "${ctx}/admin/login",
                onSubmit: function (param) {
                    return $(this).form('validate');
                },
                success: function (result) {
                	var result = eval('(' + msg + ')');
    				if (result.status == 'success') {
    					alert('success');
    					window.location.href = "${ctx}/admin/index.jsp";
    				} else {
    					alert('error');
    					$("#errMsg").html('用户名或密码错误,请重试');
    				}
                }
            });
        	
        	
//         	var username = $("#username").val();
//     		var password = $("#password").val();
//     		var postData = {"username":username, "password":password};
//             $.ajax({
//     			type: "POST",
//     			url: "${ctx}/admin/login",
//     			data: postData,
//     			async:false,
//     			success : function(msg) {
//     				var result = eval('(' + msg + ')');
//     				if (result.status == 'success') {
//     					alert('success');
//     					window.location.href = "${ctx}/admin/index.jsp";
//     				} else {
//     					alert('error');
//     					$("#errMsg").html('用户名或密码错误,请重试');
//     				}
//     			}
//     		});
        }
   	</script>
</html>