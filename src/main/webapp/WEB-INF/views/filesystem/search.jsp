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
	<div id="toolbar">
		<table class="searchTable">
			<tr>
				<td class="_show">搜索磁盘:</td>
				<td class="_input">
					<select class="easyui-combobox" id="disk">
						<option value="C">C:</option>
						<option value="D">D:</option>
						<option value="E">E:</option>
						<option value="F">F:</option>
					</select>
				</td>
				<td class="_show">文件后缀:</td>
				<td class="_input">
					<select class="easyui-combobox" id="suffix">
						<option value="exe">exe</option>
						<option value="torrent">torrent</option>
						<option value="rmvb">rmvb:</option>
						<option value="flv">flv</option>
						<option value="xlsx">xlsx</option>
						<option value="xls">xls</option>
						<option value="docx">docx</option>
						<option value="doc">doc</option>
						<option value="zip">zip</option>
						<option value="tar.gz">tar.gz</option>
						<option value="dll">dll</option>
					</select>
				</td>
				<td class="_show">文件包含:</td>
				<td class="_input">
					<input type="text" id="pattern"/>
				</td>
				<td>
					<input type="button" id="search" value="查询">
				</td>
			</tr>
		</table>
	</div>
	<table id="result_show"></table>
	<script type="text/javascript">
		var height = $(window).height();
		$(function(){
			$("#disk").combobox({
				panelHeight:"auto"
			});
			$("#search").bind("click",function(){
				doSearch($("#disk").combobox("getValue"),$("#pattern").val(),$("#suffix").combobox("getValue"));
			})
			$("#result_show").datagrid({
				url:"<%=basePath%>/filesystem/search",
				width:"auto",
				height:height*0.9,
				rownumbers:true,
				pagination:true,
				pageList:[20,30,50,100],
				columns:[[
				          {field:"name",title:"Name",width:180,align:"center"},
				          {field:"length",title:"Length",width:180,align:"right",halign:"center"},
				          {field:"path",title:"path",width:380,align:"left",halign:"center"}
				          ]]
			});
		});
		function doSearch(disk,pattern,suffix){
			
			if(!disk){
				$.messager.alert("木屋消息","参数不完整","info");
				return;
			}
			$("#result_show").datagrid("reload",{
				disk:disk,
				pattern:pattern
			});
		}
	</script>
</body>
</html>