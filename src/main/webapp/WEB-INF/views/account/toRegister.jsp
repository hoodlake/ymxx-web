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
<title>雨幕潇潇</title>
</head>
<body>
		
	<table width="100%" border="1" style="margin-top: 100px;"> 
		<tr>
			<td align="right" width="17%">用户名：</td>
			<td align="left">
				<input type="text" id="name" name="name" value="zhangshj"/></td>
			<td align="right" width="17%">密码：</td>
			<td align="left">
				<input type="password" id="password" name="password" value="aimuchun99"/>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<button id="ajax">ajax submit</button> 
			</td> 
		</tr>
	</table>
	<div id="processBarWin" style="width:600px;">
		<div id="p" class="easyui-progressbar" style="width:580px;display:none;margin-top: 60px"></div>  
	</div>
	<script type="text/javascript">
		var timer = null;
		$(function(){
			
			$("#ajax").bind("click",ajaxSubmit);
			$("#processBarWin").window({
				title:"系统正在处理",
				collapsible:false,
				minimizable:false,
				maximizable:false,
				closable:false,
				closed:true,
				resizable:false,
				draggable:false,
				modal:false,
				top:10,
				height:200
			});
		});
		function showProcess(){
			
			$.ajax({
				
				url:"<%=basePath%>/account/getCount",
				type: 'post',
				dataType:"json",
				success:function(data){
					var count = data.count;
					$("#p").progressbar("setValue",count);
				}
			});
		}
		function ajaxSubmit(){
			
			$.ajax({
				url:"<%=basePath%>/account",
				type: 'post',
				dataType:"json",
				data:{
					
					"name":$("#name").val(),
					"password":$("#password").val()
				},
				beforeSend:function(xmlHttpRequest){
					
					$("#p").css("display","block");
					$("#processBarWin").window("open");
					//启动定时器
					$("#p").progressbar({
						
						value:0
					});
					timer = setInterval(showProcess, "1000");
				},
				success:function(data){
					
					clearTimeout(timer);
					$("#processBarWin").window("close");
					$("#p").css("display","none");
					if(data.status){
						
						$.messager.alert("木屋消息",data.msg,"success");
					}
					
				},
				error:function(data){
				
					$.messager.alert("木屋消息","链接断开了","success");
				}
			});
			
		}
	</script>
</body>
</html>