<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<aside class="Hui-aside">
	<input runat="server" id="divScrollValue" type="hidden" value="" />
	<div class="menu_dropdown bk_2">
	
		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe613;</i>车次管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="admin/toAdminTripsAdd" data-title="车次增加" href="javascript:void(0)">车次增加</a></li>
				</ul>
				<ul>
					<li><a _href="admin/toAdminTripsManage" data-title="车次管理" href="javascript:void(0)">车次管理</a></li>
				</ul>
			</dd>
		</dl>
		
		
		<dl id="menu-messages">
			<dt><i class="Hui-iconfont">&#xe692;</i>车票管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="admin/toAdminTicketAdd" data-title="车票添加" href="javascript:void(0)">车票添加</a></li>
				</ul>
				<ul>
					<li><a _href="admin/toAdminTicketManage" data-title="车票管理" href="javascript:void(0)">车票管理</a></li>
				</ul>
			</dd>
		</dl>
		
		<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 订单管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="admin/toAdminOrderManage" data-title="订单管理" href="javascript:;">订单管理</a></li>
				</ul>
			</dd>
		</dl>
		
		<dl id="menu-system">
			<dt><i class="Hui-iconfont">&#xe62e;</i> 会员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="admin/toAdminUserManage" data-title="会员管理" href="javascript:void(0)">会员管理</a></li>
				</ul>
			</dd>
		</dl>
		
		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="admin/toAdminAdd" data-title="管理员增加" href="javascript:void(0)">管理员增加</a></li>
				</ul>
				<ul>
					<li><a _href="admin/toAdminManage" data-title="管理员管理" href="javascript:void(0)">管理员管理</a></li>
				</ul>
			</dd>
		</dl>
		
	</div>
</aside>