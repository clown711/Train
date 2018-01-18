<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
    <title>首页</title>
    <link rel="stylesheet" href="static/front/static/css/reset.css">
    <link rel="stylesheet" href="static/front/static/css/index.css">
    <link rel="stylesheet" href="static/front/static/css/login.css">
    <link rel="stylesheet" type="text/css" href="static/front/lib/jedate-3.8/jedate/skin/jedate.css" />
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
                        <a href="<%=basePath%>front" class="current">首页</a>
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

    <div class="content clearfix pt10" style="min-height: 414px;">
        <div class="index-side"><!--车票查询框 开始-->
            <div class="layout booking">
                <div class="lay-hd">车票查询</div>
                <div class="lay-bd">
                    <div class="booking-in">
                        <div class="booking-bd">
                        	<form id="search_trips_form" method="post" action="user/userTripsSearch">
	                            <ul class="clearfix">
	                                <li>
	                                    <span class="label">出发地</span>
	                                    <div class="inp-w">
	                                        <input id="fromStation" type="hidden" value="" name="leftTicketDTO.from_station">
	                                        <input name="tripsOrigin" maxlength="15" type="text" id="fromStationText" class="inp-txt" value="">
	                                        <span id="from_station_imageB" class="i-city" style="cursor: pointer;"></span>
	                                    </div>
	                                </li>
	                                <li>
	                                    <span class="label">目的地</span>
	                                    <div class="inp-w">
	                                        <input id="toStation" type="hidden" value="" name="leftTicketDTO.to_station">
	                                        <input maxlength="15" type="text" id="toStationText" class="inp-txt" name="tripsDestination">
	                                        <span id="to_station_imageB" class="i-city" style="cursor: pointer;"></span>
	                                    </div>
	                                </li>
	                                <li>
	                                    <span class="label">出发日</span>
	                                    <div class="inp-w">
	                                        <input style="cursor:pointer;" readonly="readonly" type="text" class="inp-txt" id="train_date" name="tripsBegintime">
	                                        <span id="from_imageClick" class="i-date" style="cursor: pointer;"></span>
	                                    </div>
	                                </li>
	                            </ul>
	                            <div class="tc">
	                                <a id="search_trips_btn" href="javascript:" class="btn-login">查&nbsp;&nbsp;&nbsp;&nbsp;询</a>
	                            </div>
                            </form>
                           
                        </div>
                    </div>
                </div>
            </div><!--车票查询框 结束-->
        </div>

        <div class="banner" id="b04">
            <ul>
                <li><img src="static/front/static/imgs/01.jpg" alt="" width="600" height="333" ></li>
                <li><img src="static/front/static/imgs/02.jpg" alt="" width="600" height="333" ></li>
                <li><img src="static/front/static/imgs/03.jpg" alt="" width="600" height="333" ></li>
                <li><img src="static/front/static/imgs/04.jpg" alt="" width="600" height="333" ></li>
            </ul>
        </div>
    </div>
    
    <script src="static/front/static/js/jquery-3.1.0.min.js"></script>
    <script src="static/front/static/js/unslider.min.js"></script>
    <script src="static/front/lib/layer/3.0/layer.js"></script>
    <script type="text/javascript" src="static/front/lib/jedate-3.8/jedate/jquery.jedate.min.js"></script> 
    <script src="static/front/static/js/index.js"></script>
     <script src="static/front/static/js/common.js"></script>
</body>
</html>