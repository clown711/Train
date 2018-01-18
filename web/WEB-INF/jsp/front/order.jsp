<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <title>Title</title>
    <link rel="stylesheet" href="static/front/static/css/reset.css">
    <link rel="stylesheet" href="static/front/static/css/index.css">
    <link rel="stylesheet" href="static/front/static/css/details.css">
    <link rel="stylesheet" href="static/front/static/css/my_ticket.css">
    <link rel="stylesheet" href="static/front/lib/layer/3.0/skin/default/layer.css">
</head>

<body>
           <div class="header">
        <div class="header-bd">
            <a href="<%=basePath%>front">
                <h1 class="logo">中国铁路客户服务中心-客运中心</h1>
            </a>
            <div class="login-info">
                <span class="login-txt" style="">
                	<c:if test="${sessionScope.user != null}">
                		<span>您好，</span>
	                    <a  href="javascript:" class="colorA login"><c:out value="${sessionScope.user.userNickname}"></c:out></a> |
	                    <a  href="user/userLogOut" class="register">退出</a>
                	</c:if>
                	
                	<c:if test="${sessionScope.user == null}">
	                	<span>您好，请</span>
	                    <a id="userLoginAlert_btn" href="javascript:" class="colorA login">登录</a> |
	                    <a id="userRegAlert_btn" href="javascript:" class="register">注册</a>
                	</c:if>
                </span>
             </div>
             <div class="nav">
                <ul>
                    <li>
                        <a href="<%=basePath%>front" >首页</a>
                    </li>
                    <c:if test="${sessionScope.user != null}">
	                    <li>
	                        <a class="current" href="user/userOrderSelect" class="">我的车票</a>
	                    </li>
                    </c:if>
                </ul>
             </div>
        </div>
    </div>

<div class="content clearfix pt10" style="min-height: 414px;">
    <div class="index-side"><!--车票查询框 开始-->
        <div class="layout">
            <div class="lay-hd">我的车票 >> <span>已完成订单</span></div>
            <div class="lay-bd">
                <ul>
                    <li><a class="finish active" href="javascript:">已完成订单</a></li>
                    <li><a class="unfinish" href="javascript:">未完成订单</a></li>
                </ul>
            </div>
        </div><!--车票查询框 结束-->
    </div>

    <div class="index-right">

        <div class="t-list">
            <table>
                <thead>
                    <tr class="th">
                        <th width="75" colspan="1" rowspan="1">车次</th>
                        <th width="75" colspan="1" rowspan="1">出发日期</th>
                        <th width="75" colspan="1" rowspan="1">出发站</th>
                        <th width="75" colspan="1" rowspan="1">到达站</th>
                        <th width="75" colspan="1" rowspan="1">出发时间</th>
                        <th width="75" colspan="1" rowspan="1">到达时间</th>
                        <th width="75" colspan="1" rowspan="1">类型</th>
                        <th colspan="1" rowspan="1">操作</th>
                    </tr>
                </thead>
                <tbody class="done">
     					<c:forEach items="${requestScope.orders}" var="order">
     					 <tr class="bgc">
     						<c:if test="${order.orderStatus == '已完成'}">
     							  	<td><strong><c:out value="${order.trips.tripsName}"></c:out></strong></td>
			                        <td><strong><fmt:formatDate value='${order.trips.tripsBegintime}' pattern='yyyy-MM-dd'/></strong></td>
			                        <td><strong><c:out value="${order.trips.tripsOrigin}"></c:out></strong></td>
			                        <td><strong><c:out value="${order.trips.tripsDestination}"></c:out></strong></td>
			                        <td><strong><fmt:formatDate value='${order.trips.tripsBegintime}' pattern='HH:mm'/></strong></td>
			                        <td><strong><fmt:formatDate value='${order.trips.tripsEndtime}' pattern='yyyy-MM-dd HH:mm'/></strong></td>
			                        <td><strong><c:out value="${order.ticketSeattype}"></c:out></strong></td>
                        			<td class="no-br"></td>
     						</c:if>
     					  </tr>
     					</c:forEach>
                  
                </tbody>
                
                
                <tbody class="undone hidden">
               
              		<c:forEach items="${requestScope.orders}" var="order">
              		 <tr class="bgc">
   						<c:if test="${order.orderStatus == '未完成'}">
   							  	<td><strong><c:out value="${order.trips.tripsName}"></c:out></strong></td>
		                        <td><strong><fmt:formatDate value='${order.trips.tripsBegintime}' pattern='yyyy-MM-dd'/></strong></td>
		                        <td><strong><c:out value="${order.trips.tripsOrigin}"></c:out></strong></td>
		                        <td><strong><c:out value="${order.trips.tripsDestination}"></c:out></strong></td>
		                        <td><strong><fmt:formatDate value='${order.trips.tripsBegintime}' pattern='HH:mm'/></strong></td>
		                        <td><strong><fmt:formatDate value='${order.trips.tripsEndtime}' pattern='yyyy-MM-dd HH:mm'/></strong></td>
		                        <td><strong><c:out value="${order.ticketSeattype}"></c:out></strong></td>
	                      		<td class="no-br">
	                      			<input name="orderId" type="hidden" value="${order.orderId}">
	                      			<input name="ticketSeattype" type="hidden" value="${order.ticketSeattype}">
			                        <a href="javascript:" class="btn72 change" onclick="gaiqian(this, '<%=basePath%>');">改签</a>
			                        <a href="javascript:" class="btn72 debook" onclick="tuipiao(this, '<%=basePath%>');">退票</a>
			                        <a href="javascript:" class="btn72 change" onclick="qupiao(this,'<%=basePath%>');">取票</a>
			                    </td>
   						</c:if>
   						 </tr>
   					</c:forEach>
               
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="static/front/static/js/jquery-3.1.0.min.js"></script>
<script src="static/front/lib/layer/3.0/layer.js"></script>
<script src="static/front/static/js/my_ticket.js"></script>
</body>
</html>