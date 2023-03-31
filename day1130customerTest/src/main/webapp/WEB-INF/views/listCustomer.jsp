<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>고객목록</h2>
	<hr>
	<a href="insertCustomer">고객등록</a>
	<ul>
		<c:forEach var="c" items="${list }">
			<li><a href="detailCustomer?custid=${c.custid }">${c.name }</a></li>
		</c:forEach>
	</ul>
</body>
</html>