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
	<link rel="stylesheet" type="text/css" href="<%=resourcePath %>/css/main.css">
<title>雨幕潇潇</title>
</head>
<body>
	<form method="post">
		<fieldset>
			<legend>Basic Info</legend>
			<div>
				<label for="name">Name:<em class="required">required</em></label>
				<input type="text" name="name" id="name" />
			</div>
			<div>
				<label for="email">Email:
					<em class="feedback">Incorrect email address.Please try again</em>
				</label>
				
				<input type="text" name="email" id="email" />
			</div>
			<div>
				<label for="password">Password:</label>
				<input type="password" name="password" id="password" />
			</div>
		</fieldset>
		<fieldset>
			<legend>Details</legend>
			<div>
				<label for="introduce">Introduce:</label>
				<textarea name="introduce" id="introduce"></textarea>
			</div>
			<div>
				<label for="dateOfBirth">Date of Birth:</label>
				<input name="dateOfBirth" id="dateOfBirth" type="text" style="width:3em;">
				<label id="monthOfBirth" for="monthOfBirth" 
						style="text-indent: -1000em;width: 0;">Month of Birth:</label>
				<select name="monthOfBirth" id="monthOfBirth" 
						style="width:10em;margin-right: 0.5em">
					<option value="1">January</option>
					<option value="2">February</option>
					<option value="3">March</option>
				</select>
				<label for="yearOfBirth" 
						style="text-indent: -1000em;width: 0;">Year of Birth:</label>
				<input name="yearOfBirth" id="yearOfBirth" type="text" style="width:5em;">
			</div>
		</fieldset>
		<fieldset>
			<legend>Remember Me</legend>
			<div>
				<input type="radio" name="remember" value="1">Yes
				<input type="radio" name="remember" value="2">No
			</div>
		</fieldset>
		<div>
			<button type="submit">
				Book Now
			</button>
		</div>
	</form>
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