<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
	<form action="/signUp" method="post">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		아이디 : <input name="id"><br>
		암호 : <input type="password" name="pwd"><br>
		이름 : <input name="name"><br>
		<button type="submit">가입</button>
	</form>
</body>
</html>