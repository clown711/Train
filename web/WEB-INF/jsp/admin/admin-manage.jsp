<%--
  Created by IntelliJ IDEA.
  User: XC
  Date: 2016/10/24
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>"/>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
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
    <title>管理员列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span
        class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
        
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
	    
	    <span class="r">
	    	共有数据：
	    	<strong id="allCount">0</strong>
	    	 条
	    </span>
    </div>
        <div class="dataTables_wrapper no-footer">
            <div class="dataTables_length"><label>显示
                <select id="select_limit" class="select">
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="25">25</option>
                    <option value="50">50</option>
                </select> 条</label>
            </div>
            <table class="table table-border table-bordered table-hover table-bg table-sort dataTable no-footer" 
            method-delete="admin_delete"   method-advocacy="admin_advocacy"   method-stop="admin_stop" id="EP_table" role="grid" url="admin/adminAdminSelect" current_page="1">
                <thead>
					<tr class="text-c">
						<th field="adminId" width="40">ID</th>
						<th field="adminAccount" width="150">账号</th>
						<th field="adminPower" width="100">等级</th>
						<th field="adminStatus" width="100">状态</th>
						<th field="EP_opation" button-type="3_4_2" width="100">操作</th>
					</tr>
                </thead>
                <tbody id="EP_table_body">
                </tbody>
            </table>
            <!-- 分页部分 -->
			<div class="dataTables_paginate paging_simple_numbers" >
			    <a id="prevPage" class="paginate_button previous" title="上一页">
			       <i id="prevPage_btn" class="icon Hui-iconfont">&#xe6d4;</i>
			    </a>
			   	 第<input id="input_page" name="data-page" type="text" style="width:40px; height:28px;margin-bottom: 5px;" class="input-text" value="1"/>页，共 <b id="totalPage" name="data-totalPage">1</b> 页
			    <a id="nextPage" class="paginate_button next">
			        <i id="nextPage_btn" class="icon Hui-iconfont" title="下一页">&#xe6d7;</i>
			    </a>
			    <a id="jumpPage" class="paginate_button next"  title="转到">
			    	<i id="jumpPage_btn" class="icon Hui-iconfont">&#xe67a;</i>
			    </a>
			</div>
        </div>
    </div>
<script type="text/javascript" src="static/admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="static/admin/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="static/admin/lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="static/admin/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="static/admin/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="static/admin/static/EP/js/EP.js"></script>

<script type="text/javascript">
$(function () {
    getData();
});
function admin_delete(dom) {
	var adminId = $(dom).parent().parent().children("[name='adminId']").text();
	var layerId = layer.confirm('确认删除', {
	  btn: ['是','否']
	}, function(){
		 layer.close(layerId);
		 $.ajax({
		        url:"<%=basePath%>admin/adminAdminDelete",
		        type: "POST",
		        dataType: "json",
		        async: false, 
		        data: {
		        	"adminId":adminId
		        },
		        success: function (json) {
		        	if(json.result == "1"){
		        		alert("删除成功");
		        		getData();
		        	}
		        	else{
		        		alert(json.errorInfo);
		        	}
		        },
		        error:function(){
		        	alert("连接失败");
		        }
		});
	}, function(){
		layer.close(layerId);
	 	return;
	});
   
}

function admin_advocacy(dom) {
	var adminId = $(dom).parent().parent().children("[name='adminId']").text();
	var layerId = layer.confirm('确认启用', {
	  btn: ['是','否']
	}, function(){
		 layer.close(layerId);
		 $.ajax({
		        url:"<%=basePath%>admin/adminAdminStatus",
		        type: "POST",
		        dataType: "json",
		        async: false, 
		        data: {
		        	"adminId":adminId,
		        	"adminStatus":"1"
		        	
		        },
		        success: function (json) {
		        	if(json.result == "1"){
		        		alert("启用成功");
		        		getData();
		        	}
		        	else{
		        		alert(json.errorInfo);
		        	}
		        },
		        error:function(){
		        	alert("连接失败");
		        }
		});
	}, function(){
		layer.close(layerId);
	 	return;
	});
   
} 

function admin_stop(dom) {
	var adminId = $(dom).parent().parent().children("[name='adminId']").text();
	var layerId = layer.confirm('确认禁用', {
	  btn: ['是','否']
	}, function(){
		 layer.close(layerId);
		 $.ajax({
		        url:"<%=basePath%>admin/adminAdminStatus",
		        type: "POST",
		        dataType: "json",
		        async: false, 
		        data: {
		        	"adminId":adminId,
		        	"adminStatus":"0"
		        },
		        success: function (json) {
		        	if(json.result == "1"){
		        		alert("禁用成功");
		        		getData();
		        	}
		        	else{
		        		alert(json.errorInfo);
		        	}
		        },
		        error:function(){
		        	alert("连接失败");
		        }
		});
	}, function(){
		layer.close(layerId);
	 	return;
	});
   
} 
</script>

