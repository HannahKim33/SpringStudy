<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function check(custid){
		if(confirm("������ �����Ͻðڽ��ϱ�?")){
			location.href="deleteCustomer?custid="+custid;
		}
	}
</script>
</head>
<body>
	<h2>����</h2>
	<hr>
	${c.custid }<br>
	${c.name }<br>
	${c.address }<br>
	${c.phone }<br>
	<hr>
	<a href="updateCustomer?custid=${c.custid }">����</a>
	<a href="#" onclick="check(${c.custid})">����</a>
</body>
</html>