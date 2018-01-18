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
      <link rel="stylesheet" href="static/front/static/css/login.css">
</head>
<body>
    <div class="header">
        <div class="header-bd">
            <a href="/front">
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
                        <a href="/front" class="current">首页</a>
                    </li>
                    <c:if test="${sessionScope.user != null}">
	                    <li>
	                        <a href="user/userOrderSelect" class="">我的车票</a>
	                    </li>
                    </c:if>
                </ul>
             </div>
        </div>
    </div>
    
    <div class="content" style="min-height: 444px;">
        <div class="sear-result" id="sear-result" style="display: block;">
            <p><strong><c:out value="${requestScope.tripsOrigin}"></c:out> --&gt; <c:out value="${requestScope.tripsDestination}"></c:out>（ <c:out value="${requestScope.tripsBegintime}"></c:out>）</strong></p>
        </div>

        <div class="t-list">
            <table>
                <thead>
                <tr class="th">
                    <th width="120" colspan="1" rowspan="1">车次</th>
                    <th width="120" colspan="1" rowspan="1">出发站<br>到达站</th>
                    <th width="120" colspan="1" rowspan="1">出发时间<br>到达时间</th>
					<th width="93" colspan="1" rowspan="1">软座</th>
					<th width="93" colspan="1" rowspan="1">硬座</th>
					<th width="93" colspan="1" rowspan="1">无座</th>
					<th width="93" colspan="1" rowspan="1">软卧</th>
					<th width="93" colspan="1" rowspan="1">硬卧</th>
                    <th colspan="1" rowspan="1"></th>

                </tr>

                </thead>
                <tbody>
                <c:forEach items="${requestScope.tripses}" var="trips">
	               	<tr class="bgc">
	               		<td name="tripsId" style="display: none;" value="${trips.tripsId}"></td>
	                    <td class="no-bottom" style="cursor: pointer;"><strong><c:out value="${trips.tripsName}"></c:out></strong></td>
	                    <td style="cursor: pointer;"><strong><c:out value="${trips.tripsOrigin}"></c:out></strong></td>
	                    <td style="cursor: pointer;"><strong><fmt:formatDate value='${trips.tripsBegintime}' pattern='yyyy-MM-dd HH:mm'/></strong></td>
	                   
	                    <c:forEach items="${trips.tickets}" var="ticket">
                    		<c:if test="${ticket.ticketSeattype == '软座'}">
                    			<td class="yes" style="cursor: pointer;">
			                    	<c:if test="${ticket.ticketNumber!= null&&ticket.ticketNumber>0}">
			                    		<c:out value="${ticket.ticketNumber}"></c:out>
			                    	</c:if>
			                    	<c:if test="${ticket.ticketNumber!= null&&ticket.ticketNumber==0}">
			                    		无 
			                    	</c:if>
			                    	<c:if test="${ticket.ticketNumber== null}">
			                    		--
			                    	</c:if>
		                    	</td>
	                    	</c:if>
	                    	
	                    	<c:if test="${ticket.ticketSeattype == '硬座'}">
                    			<td class="yes" style="cursor: pointer;">
			                    	<c:if test="${ticket.ticketNumber!= null&&ticket.ticketNumber>0}">
			                    		<c:out value="${ticket.ticketNumber}"></c:out>
			                    	</c:if>
			                    	<c:if test="${ticket.ticketNumber!= null&&ticket.ticketNumber==0}">
			                    		无
			                    	</c:if>
			                    	<c:if test="${ticket.ticketNumber== null}">
			                    		--
			                    	</c:if>
		                    	</td>
	                    	</c:if>
	                    	
	                    	<c:if test="${ticket.ticketSeattype == '无座'}">
                    			<td class="yes" style="cursor: pointer;">
			                    	<c:if test="${ticket.ticketNumber!= null&&ticket.ticketNumber>0}">
			                    		<c:out value="${ticket.ticketNumber}"></c:out>
			                    	</c:if>
			                    	<c:if test="${ticket.ticketNumber!= null&&ticket.ticketNumber==0}">
			                    		无
			                    	</c:if>
			                    	<c:if test="${ticket.ticketNumber== null}">
			                    		--
			                    	</c:if>
		                    	</td>
	                    	</c:if>
	                    	
	                    	<c:if test="${ticket.ticketSeattype == '软卧'}">
                    			<td class="yes" style="cursor: pointer;">
			                    	<c:if test="${ticket.ticketNumber!= null&&ticket.ticketNumber>0}">
			                    		<c:out value="${ticket.ticketNumber}"></c:out>
			                    	</c:if>
			                    	<c:if test="${ticket.ticketNumber!= null&&ticket.ticketNumber==0}">
			                    		无
			                    	</c:if>
			                    	<c:if test="${ticket.ticketNumber== null}">
			                    		--
			                    	</c:if>
		                    	</td>
	                    	</c:if>
	                    	
	                    	<c:if test="${ticket.ticketSeattype == '硬卧'}">
                    			<td class="yes" style="cursor: pointer;">
			                    	<c:if test="${ticket.ticketNumber!= null&&ticket.ticketNumber>0}">
			                    		<c:out value="${ticket.ticketNumber}"></c:out>
			                    	</c:if>
			                    	<c:if test="${ticket.ticketNumber!= null&&ticket.ticketNumber==0}">
			                    		无
			                    	</c:if>
			                    	<c:if test="${ticket.ticketNumber== null}">
			                    		--
			                    	</c:if>
		                    	</td>
	                    	</c:if>
	                    </c:forEach>
	                    <td class="no-br no-bottom" style=""><a onclick="yuding(this)" href="javascript:" class="btn72">预订</a></td>
	                </tr>
	                
	                <tr class="bgc bgd">
	                    <td align="center"></td>
	                    <td align="center"><strong><c:out value="${trips.tripsDestination}"></c:out></strong></td>
	                    <td align="center"><strong><fmt:formatDate value='${trips.tripsEndtime}' pattern='yyyy-MM-dd HH:mm'/></strong></td>
	                    
	                    <c:forEach items="${trips.tickets}" var="ticket">
	                    
                    		<c:if test="${ticket.ticketSeattype == '软座'}">
			                    	<c:if test="${ticket.ticketPrice!= null}">
			                    		<td class="p-num" align="center">¥<c:out value="${ticket.ticketPrice}"></c:out></td>
			                    	</c:if>
			                    	<c:if test="${ticket.ticketPrice == null}">
			                    		<td class="p-num" align="center">--</td>
			                    	</c:if>
	                    	</c:if>
	                    	
	                    	<c:if test="${ticket.ticketSeattype == '硬座'}">
			                    	<c:if test="${ticket.ticketPrice!= null}">
			                    		<td class="p-num" align="center">¥<c:out value="${ticket.ticketPrice}"></c:out></td>
			                    	</c:if>
			                    	<c:if test="${ticket.ticketPrice== null}">
			                    		<td class="p-num" align="center">--</td>
			                    	</c:if>
	                    	</c:if>
	                    	
	                    	<c:if test="${ticket.ticketSeattype == '无座'}">
			                    	<c:if test="${ticket.ticketPrice!= null}">
			                    		<td class="p-num" align="center">¥<c:out value="${ticket.ticketPrice}"></c:out></td>
			                    	</c:if>
			                    	<c:if test="${ticket.ticketPrice== null}">
			                    		<td class="p-num" align="center">--</td>
			                    	</c:if>
	                    	</c:if>
	                    	
	                    	<c:if test="${ticket.ticketSeattype == '软卧'}">
			                    	<c:if test="${ticket.ticketPrice!= null}">
			                    		<td class="p-num" align="center">¥<c:out value="${ticket.ticketPrice}"></c:out></td>
			                    	</c:if>
			                    	<c:if test="${ticket.ticketPrice== null}">
			                    		<td class="p-num" align="center">--</td>
			                    	</c:if>
	                    	</c:if>
	                    	
	                    	<c:if test="${ticket.ticketSeattype == '硬卧'}">
			                    	<c:if test="${ticket.ticketPrice!= null}">
			                    		<td class="p-num" align="center">¥<c:out value="${ticket.ticketPrice}"></c:out></td>
			                    	</c:if>
			                    	<c:if test="${ticket.ticketPrice== null}">
			                    		<td class="p-num" align="center">--</td>
			                    	</c:if>
	                    	</c:if>
	                   		
	                    </c:forEach>
	                    
	                    <td class="p-num no-br" align="center">
	                    
	                    </td>
	                </tr>
                </c:forEach>
             
                </tbody>

            </table>

        </div>
    </div>
</body>
<script src="static/front/static/js/jquery-3.1.0.min.js"></script>
<script src="static/front/lib/layer/3.0/layer.js"></script>
<script src="static/front/static/js/serach.js"></script>
<script src="static/front/static/js/common.js"></script>
</html>