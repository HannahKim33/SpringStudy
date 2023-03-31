<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>상품 등록</h2>
	<form action="insertGoods" method="post" enctype="multipart/form-data">
		상품명 : <input name="name"><br>
		가격 : <input name="price"><br>
		수량 : <input name="qty"><br>
		사진 : <input type="file" name="uploadFile"><br>
		<input type="submit" value="등록">
		<input type="reset" value="취소">
	</form>
</body>
</html>