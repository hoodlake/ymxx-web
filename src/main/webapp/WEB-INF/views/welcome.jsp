<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath=request.getContextPath();String resourcePath = basePath+"/resources";%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=resourcePath %>/easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=resourcePath %>/easyui-1.3.2/themes/icon.css">
	<script type="text/javascript" src="<%=resourcePath %>/easyui-1.3.2/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=resourcePath %>/easyui-1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=resourcePath %>/easyui-1.3.2/easyui-lang-zh_CN.js"></script>
	<style type="text/css">
		.title{
			font-size: 18pt;
			font-weight:bold;
			text-align: left;
			font-family: Arial;
			margin-top: 10px;
		}
	</style>
<title>雨幕潇潇</title>
</head>
<body class="easyui-layout" style="color: red">
	<!-- 最上方 -->
	<div data-options="region:'north'" style="height:50%;">
		<font class="title">雨幕潇潇的小屋</font>
	</div>
	<!-- 最左边 -->
	<div data-options="region:'west',split:true" title="我的菜单" style="width:230px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="文件系统" style="padding:10px;" >
				
			</div>
			<div title="商品出售" style="padding:10px;">
				
			</div>
			<div title="博客分享" data-options="selected:true" style="padding:10px;">
				<a url="http://www.baidu.com" href="javascript:void(0);" class="easyui-linkbutton"
					onclick="toMainPanel(this);">百度</a>
				<a url="<%=basePath %>/account" href="javascript:void(0);" class="easyui-linkbutton"
					onclick="toMainPanel(this);">注册</a>
				<a url="http://www.oschina.net" href="javascript:void(0);" class="easyui-linkbutton"
					onclick="toMainPanel(this);">开源中国</a>
				<a url="<%=basePath %>/component/cal" href="javascript:void(0);" class="easyui-linkbutton"
					onclick="toMainPanel(this);">日历</a>
			</div>
			<div title="留言版" style="padding:10px">
				
			</div>
		</div>
	</div>
	<!-- 主面板 -->
	<div data-options="region:'center',title:'我的地盘'"> 
		<iframe src="http://www.baidu.com" 
			name="ymxx_main" id="ymxx_main" frameborder="0" scrolling="auto"
				style="width:100%;height:98%;">
			
		</iframe>
	</div>
	<!-- 最右边 -->
	<div data-options="region:'east',split:true" style="width:260px;">
		<div id="calendar" class="easyui-calendar" style="width:250px;height:180px;"></div>  
	</div>
	
	<!-- 最下方 -->
	<div data-options="region:'south',split:true" style=
					"height:40%;text-align: center;vertical-align: middle;">
		Copyright © 2010-2013 www.yumuxiaoxiao.com
	</div>
	<script type="text/javascript">
		function toMainPanel(elem){
			
			var url = $(elem).attr("url");
			if(!url){
				$.messager.alert("木屋消息","链接出现问题了","info");
				return;
			}
			$("#ymxx_main").attr("src",url);
		}
	</script>
</body>
</html>