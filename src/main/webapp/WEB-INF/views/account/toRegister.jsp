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
<body>
		
		<table width="100%" border="1" style="margin-top: 100px;">
			<tr>
				<td align="right" width="17%">用户名：</td>
				<td align="left"><input type="text" name="name" value="zhangshj"/></td>
				<td align="right" width="17%">密码：</td>
				<td align="left"><input type="password" name="password" value="aimuchun99"/></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<button id="ajax">ajax submit</button>
				</td> 
			</tr>
		</table>
	<script type="text/javascript">
		$(function(){
			
			$("#ajax").bind("click",ajaxSubmit);
		});
		function ajaxSubmit(){
			
			$.ajax({
				url:"/jweb/account",
				type: 'post',
				dataType:"text",
				data:{
					
					"name":"zhanfdffgshj",
					"password":"fads"
				},
				success:function(data){
					
					alert(data);
				},
				error:function(data){
					
					alert(data);
				}
			});
			
		}
	</script>
</body>
</html>