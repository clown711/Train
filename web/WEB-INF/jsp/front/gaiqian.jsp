<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">
</head>
<body>
	<div>
		<form id="gaiqian_form" style="text-align: center;">
			<input type="hidden" name="orderId" value="${requestScope.orderId}">
			<div>
				<div style="float: left;margin-left: 20px"><h3>席位：</h3></div>
				<div style="clear:both;">
					<c:out value="${requestScope.xiwei}"></c:out>
				</div>
			</div>
			<div style="float: left;margin-left: 20px"><h3>选择日期：</h3></div>
			<div style="clear:both;">
				<select id="trips_Select" name="tripsId" style="width:200px">
					<c:if test="${fn:length(requestScope.tripses)> 0}">
						<c:forEach items="${requestScope.tripses}" var="trips">
							<option value="${trips.tripsId}"><fmt:formatDate value='${trips.tripsBegintime}' pattern='yyyy-MM-dd HH:mm'/></option>
						</c:forEach>
					</c:if>
					
					<c:if test="${fn:length(requestScope.tripses)<= 0}">
							<option value="-1">当前车票不可改签</option>
					</c:if>
				</select>
			</div>
			<div style="float: right;margin-right: 20px">
				<input id="gaiqian_btn" type="button" value="提交">
			</div>
		</form>
	</div>
</body>
<script src="static/front/static/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
	$("#gaiqian_btn").on("click",function(){
		 var test = $("#trips_Select").val();
		 if(test == -1){
			 alert("无法改签");
			 return fasle;
		 }
		 $.ajax({
  			url: "<%=basePath%>user/userOrderChange",
  	        type: "POST",
  	        dataType: "json",
  	        async: false,
  	        data:$("#gaiqian_form").serialize(),
  	        success: function (json) {
  	        	if(json.result == 1){
  	        		alert("改签成功");
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
</script>
</html>