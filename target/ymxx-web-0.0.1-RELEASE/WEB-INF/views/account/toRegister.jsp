<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/resources/easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/resources/easyui-1.3.2/themes/icon.css">
	<script type="text/javascript" src="/resources/easyui-1.3.2/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="/resources/easyui-1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/resources/easyui-1.3.2/easyui-lang-zh_CN.js"></script>
<title>雨幕潇潇</title>
</head>
<body>
	<form action="/jweb/user.html" method="post" id="_regForm">
		<table width="100%" border="1" style="margin-top: 100px;">
			<tr>
				<td align="right" width="17%">用户名：</td>
				<td align="left"><input type="text" name="name" value="张少举"/></td>
				<td align="right" width="17%">密码：</td>
				<td align="left"><input type="password" name="password" value="aimuchun99"/></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="submit" value="提交"/>
					<button id="ajax">ajax submit</button>
				</td> 
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		$(function(){
			
			$("#ajax").bind("click",ajaxSubmit);
		});
		function ajaxSubmit(){
			
			$.ajax({
				url:"/jweb/user.html",
				type:"post",
				dataType:"json",
				data:{
					"name":"张少举",
					"password":"aimuchun99"
				},
				success:function(data){
					
					alert(data+"sfdsffdsfsdf");
				},
				error:function(data){
					
					alert("error");
				}
			});
		}
	</script>
</body>
</html>