<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/jweb/resources/easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/jweb/resources/easyui-1.3.2/themes/icon.css">
	<script type="text/javascript" src="/jweb/resources/easyui-1.3.2/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="/jweb/resources/easyui-1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/jweb/resources/easyui-1.3.2/easyui-lang-zh_CN.js"></script>
<title>雨幕潇潇</title>
</head>
<body class="easyui-layout">
		<div data-options="region:'north'" style=
				"height:50px;text-align: center;vertical-align: middle;">
			雨幕潇潇的小屋
		</div>
		<div data-options="region:'south',split:true" style=
						"height:50px;text-align: center;vertical-align: middle;">
			Copyright © 2010-2013 www.yumuxiaoxiao.com
		</div>
		<div data-options="region:'east',split:true" style="width:260px;">
			<div id="calendar" class="easyui-calendar" style="width:250px;height:180px;"></div>  
		</div>
		<div data-options="region:'west',split:true" title="我的菜单" style="width:230px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div title="系统结构" style="padding:10px;" >
					<ul class="easyui-tree" data-options="url:'tree_data1.json',animate:true,dnd:true"></ul>
				</div>
				<div title="商品出售" style="padding:10px;">
					
				</div>
				<div title="博客分享" data-options="selected:true" style="padding:10px;">
					content2
				</div>
				<div title="留言版" style="padding:10px">
					content3
				</div>
			</div>
		</div>
		<div data-options="region:'center',title:'我的地盘'"> 
			<div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
				<div title="首页"  style="padding:10px">
					<a href="/jweb/account">注册</a>
				</div>
				<div title="表格" style="padding:5px">
					
				</div>
			</div>
		</div>
</body>
</html>