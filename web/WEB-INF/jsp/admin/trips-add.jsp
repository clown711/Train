<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="lib/html5.js"></script>
	<script type="text/javascript" src="lib/respond.min.js"></script>
	<script type="text/javascript" src="lib/PIE_IE678.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="static/admin/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="static/admin/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="static/admin/lib/Hui-iconfont/1.0.7/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="static/admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="static/admin/static/h-ui.admin/css/style.css" />
	<link rel="stylesheet" type="text/css" href="static/admin/lib/icheck/icheck.css" />
	<link rel="stylesheet" type="text/css" href="static/admin/lib/jedate-3.8/jedate/skin/jedate.css" />
	<!--[if IE 6]>
	<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
</head>
<body>
<div class="page-container">
  
   <div class = "responsive">
	   	<form id="trips_form">
		    
		    <div class="row cl" style="height: 50px">
			      <div class="col-2" style="float: left;">
			       		<span class="c-red">*</span>车次名称：
			       </div>
			       <div class="col-3" style="float: left;">
			       		<input style="width: 150px;"  type="text" class="input-text " placeholder="请输入车次名称" id="tripsName" name="tripsName" value="${requestScope.trips.tripsName}">
			       </div>
		     </div>
		     
		     <div class="row cl" style="height: 50px">
			      <div class="col-2" style="float: left;">
			       		<span class="c-red">*</span>始发地：
			       </div>
			       <div class="col-3" style="float: left;">
			       		<input style="width: 150px;"  type="text" class="input-text " placeholder="请输入始发地" id="tripsOrigin" name="tripsOrigin" value="${requestScope.trips.tripsOrigin}">
			       </div>
		     </div>
		     
		     <div class="row cl" style="height: 50px">
			      <div class="col-2" style="float: left;">
			       		<span class="c-red">*</span>目的地：
			       </div>
			       <div class="col-3" style="float: left;">
			       		<input style="width: 150px;"  type="text" class="input-text " placeholder="请输入目的地" id="tripsDestination" name="tripsDestination" value="${requestScope.trips.tripsDestination}">
			       </div>
		     </div>
		     
		     <div class="row cl" style="height: 50px">
			      <div class="col-2" style="float: left;">
			       		<span class="c-red">*</span>开始时间：
			       </div>
			       <div class="col-3" style="float: left;">
			       		<input style="width: 150px;"  type="text" class="input-text " placeholder="请选择发车时间" id="tripsBegintime" name="tripsBegintime" value="<fmt:formatDate value='${requestScope.trips.tripsBegintime}' pattern='yyyy-MM-dd HH:mm'/>"  readonly="readonly">
			       </div>
		     </div>
		     
		     <div class="row cl" style="height: 50px">
			      <div class="col-2" style="float: left;">
			       		<span class="c-red">*</span>到达时间：
			       </div>
			       <div class="col-3" style="float: left;">
			       		<input style="width: 150px;"  type="text" class="input-text " placeholder="请选择到达时间" id="tripsEndtime" name="tripsEndtime" value="<fmt:formatDate value='${requestScope.trips.tripsEndtime}' pattern='yyyy-MM-dd HH:mm'/>" readonly="readonly">
			       </div>
		     </div>
		  
			  
			  <c:if test="${requestScope.trips!=null}">
			  		<input type="hidden" value="${requestScope.trips.tripsId}" name="tripsId"/>
			  </c:if>
			  
		  </form>
	  	
	  	  <div class="row cl" style="height: 50px">
		        <div class="col-3" style="float: left;">
			    	<input id="trips_submit_btn" class="btn btn-primary radius size-M" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		      	</div>
	      </div>
 	</div>
</div>

<script type="text/javascript" src="static/admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="static/admin/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="static/admin/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="static/admin/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="static/admin/lib/jedate-3.8/jedate/jquery.jedate.min.js"></script> 
<script type="text/javascript">
$(function () {
	
	$("#tripsBegintime").jeDate({
		 	format: 'YYYY-MM-DD hh:mm',
		    minDate: $.nowDate(0), //设定最小日期为当前日期
		    festival:true,
		    ishmsVal:false, 
		    fixed:false,  
		    isToday:false,   
		    maxDate:'2020-06-16 23:59:59', //最大日期
		    minDate:'2000-01-01 00:00:00',   //最小日期
		    choosefun:function(elem, val) {
		    	$("#tripsBegintime").attr("value",val);
		    }
	});
	
	$("#tripsEndtime").jeDate({
		 format: 'YYYY-MM-DD hh:mm',
		    minDate: $.nowDate(0), //设定最小日期为当前日期
		    festival:true,
		    ishmsVal:false, 
		    fixed:false,  
		    isToday:false,   
		    maxDate:'2020-06-16 23:59:59', //最大日期
		    minDate:'2000-01-01 00:00:00',   //最小日期
		    choosefun:function(elem, val) {
		    	$("#tripsEndtime").attr("value",val);
		    }
	})
	
	
	$("#trips_submit_btn").on("click",function(){
		
		var data = $("#trips_form").serialize(); 
		data = data.replace(/\+/g," ");
		data = decodeURIComponent(data);  
		$.ajax({
			
			<c:if test="${requestScope.trips==null}">
			url: "/admin/adminTripsAdd",
			</c:if>
			
			<c:if test="${requestScope.trips!=null}">
			url: "/admin/adminTripsEdit",
			</c:if>
			
	        type: "POST",
	        dataType: "json",
	        async: false,
	        data:data,
	        success: function (json) {
	        	if(json.result == 1){
	        		alert("操作成功 ");
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