<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>상품 수정</h2>
	<hr>
	<form action="updateGoods" method="post" enctype="multipart/form-data">
		상품번호 : ${g.no }
		<input type="hidden" name="no" value="${g.no }"><br>
		상품명 : <input name="name" value="${g.name }"><br>
		가격 : <input name="price" value="${g.price }"><br>
		수량 : <input name="qty" value="${g.qty }"><br>
		<input type="hidden" name="fname" value="${g.fname }">
		사진 : <img src="images/${g.fname }" width="50" height="50"><br>
		<input type="file" name="uploadFile"><br>
		<input type="submit" value="등록">
		<input type="reset" value="취소">
	</form>
</body>
</html>