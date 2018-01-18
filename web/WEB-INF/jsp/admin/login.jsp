<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员登录</title>
	<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/admin/static/person/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/admin/static/person/css/body.css"/>
</head>
<body>
<div class="container">
	<section id="content">
		<form id="adminLoginForm">
			<h1>管理员登录</h1>
			<div>
				<input type="text" placeholder="账号" id="username" name="adminAccount" />
			</div>
			<div>
				<input type="password" placeholder="密码" id="password" name="adminPassword" />
			</div>
			<div class="">
				<span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>
			</div> 
			<div>
				<input type="button" value="登录" class="btn btn-primary" id="js-btn-login"/>
			</div>
		</form>
	</section>
</div>
</body>
<script type="text/javascript" src="static/admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script>
			$("#js-btn-login").click(function(){
					      $.ajax({
					        url: "<%=basePath%>admin/loginAdmin",
					        type: "POST",
					        dataType: "json",
					        data: $("#adminLoginForm").serialize(),
					        success: function (data) {
					          if (data.result == "0") {
					        	  alert(data.errorInfo);
					          } else {
					        	  location.replace("<%=basePath%>admin");
					          }
					        },
					        error: function () {
					        	alert("连接服务器失败，请稍后重试！!");
					        }
					      });
			    });
		</script>
</html>
