<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	String easyUIHome = basePath+"/easyui-1.3.2";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=easyUIHome%>/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=easyUIHome%>/themes/icon.css">
	<script type="text/javascript" src="<%=easyUIHome%>/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=easyUIHome%>/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=easyUIHome%>/easyui-lang-zh_CN.js"></script>
<title>雨幕潇潇</title>
</head>
<body>
	<form action="/user.html" method="post">
		<table width="100%" border="1" style="margin-top: 100px;">
			<tr>
				<td align="right" width="17%">用户名：</td>
				<td align="left"><input type="text" name="username"/></td>
				<td align="right" width="17%">密码：</td>
				<td align="left"><input type="password" name="username"/></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="submit" value="提交"/>
				</td>
			</tr>
		</table>
	</form>	
</body>
</html>