<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<!--[if IE 6]>
	<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
</head>
<body>
<div class="page-container">
  
   <div class = "responsive">
	   	<form id="ticket_form">
		     <div class="row cl" style="height: 50px">
		      		<div class="col-2" style="float:left;">
			    		<span class="c-red">*</span>选择车次：
			    	</div>
			    	<div class="col-3" style="float:left;">
			    		<select id="trips_select" name="tripsId" class="select" size="1"  value="${requestScope.ticket.tripsId}">
							<option value="-1"  selected>空</option>
			    		</select>
			    	</div>
		     </div>
		     
		     <div class="row cl" style="height: 50px">
			       <div class="col-2" style="float:left;">
			    		<span class="c-red">*</span>座位类型：
			    	</div>
			    	<div class="col-3" style="float:left;">
			    		<select id="ticketSeattype" name="ticketSeattype" class="select" size="1"  value="${requestScope.ticket.ticketSeattype}">
							<option value="硬座"  >硬座</option>
							<option value="软座"  >软座</option>
							<option value="硬卧"  >硬卧</option>
							<option value="软卧"  >软卧</option>
							<option value="无座"  >无座</option>
			    		</select>
			    	</div>
		     </div>
		      
		     <div class="row cl" style="height: 50px">
			       <div class="col-2" style="float: left;">
			       		<span class="c-red">*</span>车票数量：
			       </div>
			       <div class="col-3" style="float: left;">
			       		<input style="width: 150px;"  type="text" class="input-text " placeholder="请输入车票数量" id="ticketNumber" name="ticketNumber" value="${requestScope.ticket.ticketNumber}">
			       </div>
			       
		     </div>
		     
		     <div class="row cl" style="height: 50px">
			       <div class="col-2" style="float: left;">
			       		<span class="c-red">*</span>车票价格：
			       </div>
			       <div class="col-3" style="float: left;">
			       		<input style="width: 150px;"  type="text" class="input-text " placeholder="请输入车票价格" id="ticketPrice" name="ticketPrice" value="${requestScope.ticket.ticketPrice}">
			       </div>
			       
		     </div>
		     
		     <c:if test="${requestScope.ticket!=null}">
			  		<input type="hidden" value="${requestScope.ticket.ticketId}" name="ticketId"/>
			 </c:if>
		  
		  </form>
	  	
	  	  <div class="row cl">
		        <div class="col-3" style="float: left;">
			    	<input id="ticket_submit_btn" class="btn btn-primary radius size-M" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
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
	getTrips();
	setDefaultValue();
	
	function getTrips(){
		$.ajax({
			url: "<%=basePath%>admin/adminTripsSelect",
	        type: "POST",
	        data:{"flag":"ticket_add"},
	        dataType: "json",
	        async: false,
	        beforeSend:function(){
	         $("#trips_select").html("<option value='-1'>"+"空"+"</option>");
	        },
	        success: function (json) {
	        	if(json.result == "1"){
	        		for(var i=0;i<json.content.length;i++){
	        			 $("#trips_select").append( "<option value="+json.content[i].tripsId+">"+json.content[i].tripsName+"</option>" );
	        		}
	        	}
	        	else{
	        		alert("数据请求失败");
	        	}
	        },
	        error:function(){
	        	alert("连接失败");
	        }
		});
	}
	
	
 	$("#ticket_submit_btn").on("click",function(){
		if($("#trips_select").val() == -1){
			alert("车次名不能为空");
			return false;
		}
		$.ajax({
			<c:if test="${requestScope.ticket==null}">
			url: "<%=basePath%>admin/adminTicketAdd",
			</c:if>
			
			<c:if test="${requestScope.ticket!=null}">
			url: "<%=basePath%>admin/adminTicketEdit",
			</c:if>
			
	        type: "POST",
	        dataType: "json",
	        async: false,
	        data:$("#ticket_form").serialize(),
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
 	
 	function setDefaultValue(){
 		var tripsId = $("#trips_select").attr("value");
 		var ticketSeattype = $("#ticketSeattype").attr("value");
 		
 		if(tripsId.length>0&&tripsId!=null){
 			$("#trips_select option").each(function(){
 				if(tripsId == $(this).attr("value")){
 					$(this).attr("selected",true);
 				}
 			});
 		}
 		
 		if(ticketSeattype.length>0&&ticketSeattype!=null){
 			
 			$("#ticketSeattype option").each(function(){
 				if(ticketSeattype == $(this).attr("value")){
 					$(this).attr("selected",true);
 				}
 			});
 		}
 		
 	}
});
</script>
</body>
</html>