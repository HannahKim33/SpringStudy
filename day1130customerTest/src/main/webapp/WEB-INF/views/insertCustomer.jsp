<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>�����</h2>
	<form action="insertCustomer" method="post">
		����ȣ : <input type="number" name="custid"><br>
		�̸� : <input type="text" name="name"><br>
		�ּ� : <input type="text" name="address"><br>
		��ȭ : <input type="text" name="phone"><br>
		<input type="submit" value="���">
		<input type="reset" value="���">
	</form>
</body>
</html>