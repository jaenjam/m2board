<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/insertBoard">
		<table border="1">
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="boardTitle" id="boardTitle">
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="boardWriter" id="boardWriter">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<!-- name에 작성한 String으로 저장 -->
					<textarea row="5" cols="30" name="boardContent" id="boardContent"></textarea>
				</td>
			</tr>
		</table>
		<button>확인</button>
	</form>
</body>
</html>