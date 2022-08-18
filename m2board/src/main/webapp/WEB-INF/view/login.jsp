<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>LOGIN</h1>
	<form method="post" action="<%=request.getContextPath()%>/loginController">
	<table border="1">
		<tr>
			<td>ID</td>
			<td>
				<input type="hidden" name="loginMember" value="member">
				<input type="text" name="memberId">
			</td>
		</tr>
		<tr>
			<td>PASSWORD</td>
			<td>
				<input type="password" name="memberPw">
			</td>
		</tr>
	</table>
	<button>로그인</button>
	<a href="<%=request.getContextPath()%>/insertMemberController">회원가입</a>
	</form>
</body>
</html>