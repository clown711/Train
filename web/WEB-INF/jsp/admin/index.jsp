<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<!--[if lt IE 9]>
		<script type="text/javascript" src="static/admin/lib/html5.js"></script>
		<script type="text/javascript" src="static/admin/lib/respond.min.js"></script>
		<script type="text/javascript" src="static/admin/lib/PIE_IE678.js"></script>
		<![endif]-->
		<link rel="stylesheet" type="text/css" href="static/admin/static/h-ui/css/H-ui.min.css" />
		<link rel="stylesheet" type="text/css" href="static/admin/static/h-ui.admin/css/H-ui.admin.css" />
		<link rel="stylesheet" type="text/css" href="static/admin/lib/Hui-iconfont/1.0.7/iconfont.css" />
		<link rel="stylesheet" type="text/css" href="static/admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
		<link rel="stylesheet" type="text/css" href="static/admin/static/h-ui.admin/css/style.css" />
		<!--[if IE 6]>
		<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
		<script>DD_belatedPNG.fix('*');</script>
		<![endif]-->
		<title>迅捷网上售票后台管理</title>
	</head>
	
	<body>
	<!-- header start -->
		<jsp:include page="_header.jsp" flush="true"/>
	<!-- header end -->
	
	<!-- menu start -->
		<jsp:include page="_menu.jsp" flush="true"/>
	<!-- menu end -->
	
	<!-- content start -->
		<jsp:include page="_content.jsp" flush="true"/>
	<!-- content end -->
	
	
	<script type="text/javascript" src="static/admin/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="static/admin/lib/layer/2.1/layer.js"></script>
	<script type="text/javascript" src="static/admin/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
	<script type="text/javascript" src="static/admin/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript" src="static/admin/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>
	<script type="text/javascript" src="static/admin/static/h-ui/js/H-ui.js"></script>
	<script type="text/javascript" src="static/admin/static/h-ui.admin/js/H-ui.admin.js"></script> 
	
	<!-- 导航条下拉选项JS
		<script type="text/javascript">
		/*资讯-添加*/
		function article_add(title,url){
			var index = layer.open({
				type: 2,
				title: title,
				content: url
			});
			layer.full(index);
		}
		/*图片-添加*/
		function picture_add(title,url){
			var index = layer.open({
				type: 2,
				title: title,
				content: url
			});
			layer.full(index);
		}
		/*产品-添加*/
		function product_add(title,url){
			var index = layer.open({
				type: 2,
				title: title,
				content: url
			});
			layer.full(index);
		}
		/*用户-添加*/
		function member_add(title,url,w,h){
			layer_show(title,url,w,h);
		}
		</script> 
	 -->
	</body>
</html>