<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="lib/html5.js"></script>
	<script type="text/javascript" src="lib/respond.min.js"></script>
	<script type="text/javascript" src="lib/PIE_IE678.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/admin/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/admin/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/admin/lib/Hui-iconfont/1.0.7/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/admin/static/h-ui.admin/css/style.css" />
			<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/admin/lib/icheck/icheck.css" />
	<!--[if IE 6]>
	<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
</head>
<body>
<div class="page-container">
  
   <div class = "responsive">
	   	<form id="admin_form" method="post" action="" >
	   	 	<c:if test="${requestScope.admin==null}">
		   	 	<div class="row cl" style="height: 50px">
			      		<div class="col-2" style="float:left;">
				    		<span class="c-red">*</span>选择管理员级别：
				    	</div>
				    	<div class="col-3" style="float:left;">
				    			<div style="float: left; width: 100px">
						    		<select name="adminPower" class="select" size="1">
										<option value="1"  selected>普通管理员</option>
										<option value="2"  selected>超级管理员</option>
						    		</select>
						    	</div>
				    	</div>
			    </div>
            </c:if>
		    
		    <div class="row cl" style="height: 50px">
			      <div class="col-2" style="float: left;">
			       		<span class="c-red">*</span>管理员账号：
			       </div>
			       <div class="col-3" style="float: left;">
			       		<input style="width: 150px;"  type="text" class="input-text " placeholder="请输入管理员账号" name="adminAccount" value="${requestScope.admin.adminAccount}">
			       </div>
		     </div>
		     
		     <div class="row cl" style="height: 50px">
			      <div class="col-2" style="float: left;">
			       		<span class="c-red">*</span>管理员密码：
			       </div>
			       <div class="col-3" style="float: left;">
			       		<input style="width: 150px;"  type="text" class="input-text " placeholder="请输入管理员密码" name="adminPassword" value="${requestScope.admin.adminPassword}">
			       </div>
		     </div>
		     
			  
		  </form>
	  	
	  	  <div class="row cl" style="height: 50px">
		        <div class="col-3" style="float: left;">
			    	<input id="admin_submit_btn" class="btn btn-primary radius size-M" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		      	</div>
	      </div>
 	</div>
</div>

<script type="text/javascript" src="static/admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="static/admin/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="static/admin/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="static/admin/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript">
$(function () {
	
	$("#admin_submit_btn").on("click",function(){
		$.ajax({
			url: "<%=basePath%>admin/adminAdminAdd",
	        type: "POST",
	        dataType: "json",
	        async: false,
	        data:$("#admin_form").serialize(),
	        success: function (json) {
	        	if(json.result == 1){
	        		alert("插入成功 ");
	        	}
	        	else{
	        		alert(json.errorInfo);
	        	}
	        },
	        error:function(){
	        	alert("连接失败");
	        }
		});
	});
});
</script>
</body>
</html>