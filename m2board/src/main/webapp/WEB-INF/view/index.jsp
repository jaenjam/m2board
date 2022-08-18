<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%=session.getAttribute("id")%>님 환영합니다.</h1>
	<table border="1">
		<tr>
			<td>id</td>
			<td><%=session.getAttribute("id")%></td>
		</tr>
		<tr>
			<td>name</td>
			<td><%=session.getAttribute("name")%></td>
		</tr>
	</table>
	<div>
		<a href="">로그아웃</a>
		<a href="">상세정보</a>
	</div>
</body>
</html>