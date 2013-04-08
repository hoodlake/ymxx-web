<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath=request.getContextPath();String resourcePath = basePath+"/resources";%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=resourcePath %>/css/main.css">
<title>日历</title>
</head>
<body>
	<table class="cal" summary="A calendar style date picker">
		<caption>
			<a href="#" rel="prev">&lt;</a>
			January 2013
			<a href="#" rel="next">&gt;</a>
		</caption>
		<colgroup>
			<col id="sun"/>
			<col id="mon"/>
			<col id="tue"/>
			<col id="wed"/>
			<col id="thus"/>
			<col id="fri"/>
			<col id="sat"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">Sun</th>
				<th scope="col">Mon</th>
				<th scope="col">Tue</th>
				<th scope="col">Wed</th>
				<th scope="col">Tur</th>
				<th scope="col">Fri</th>
				<th scope="col">Sat</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="null">30</td>
				<td class="null">31</td>
				<td ><a href="#">1</a></td>
				<td ><a href="#">2</a></td>
				<td ><a href="#">3</a></td>
				<td ><a href="#">4</a></td>
				<td ><a href="#">5</a></td>
			</tr>
			<tr>
				<td ><a href="#">6</a></td>
				<td ><a href="#">7</a></td>
				<td ><a href="#">8</a></td>
				<td ><a href="#">9</a></td>
				<td class="selected"><a href="#">10</a></td>
				<td ><a href="#">11</a></td>
				<td ><a href="#">12</a></td>
			</tr>
			<tr>
				<td ><a href="#">13</a></td>
				<td ><a href="#">14</a></td>
				<td ><a href="#">15</a></td>
				<td ><a href="#">16</a></td>
				<td ><a href="#">17</a></td>
				<td ><a href="#">18</a></td>
				<td ><a href="#">19</a></td>
			</tr>
			<tr>
				<td ><a href="#">20</a></td>
				<td ><a href="#">21</a></td>
				<td ><a href="#">22</a></td>
				<td ><a href="#">23</a></td>
				<td ><a href="#">24</a></td>
				<td ><a href="#">25</a></td>
				<td ><a href="#">26</a></td>
			</tr>
			<tr>
				<td ><a href="#">27</a></td>
				<td ><a href="#">28</a></td>
				<td ><a href="#">29</a></td>
				<td ><a href="#">30</a></td>
				<td class="null">1</td>
				<td class="null">2</td>
				<td class="null">3</td>
			</tr>
		</tbody>
	</table>
</body>
</html>