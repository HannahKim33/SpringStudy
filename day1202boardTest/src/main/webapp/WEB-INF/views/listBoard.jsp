<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.highlight{
		background:pink;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".row").click(function(){
			var no=$(this).attr("no");
			location.href="detailBoard?no="+no;
		})
		
		
		$(".row").hover(function(){
			$(this).addClass("highlight");
		},function(){
			$(this).removeClass("highlight");
		})
	})
</script>
</head>
<body>
	<h2>게시글 목록</h2>
	<hr>
	<a href="insertBoard">게시글 등록</a>

	<br>
	
	<c:if test="${not empty loginUser }">
		${loginUser.name }(${loginUser.id})님 안녕하세요.
		<br>
		<a href="logout">로그아웃</a>
	</c:if>
	<c:if test="${empty loginUser }">
		<a href="login">로그인</a>
	</c:if>
	
	
	
	
	<table border='1' width="80%">
		<tr>
			<td>글번호</td>
			<td>글제목</td>
			<td>작성자</td>
			<td>등록일</td>
		</tr>
		<c:forEach var="b" items="${list }">
			<tr class="row" no=${b.no }>
				<td>${b.no }</td>
				<td>
					<c:if test="${b.b_level>0 }">
						<c:forEach begin="1" end="${b.b_level }">
							&nbsp;&nbsp;
						</c:forEach>
						<img src="re.png" width="10" height="10">
					</c:if>
					${b.title }</td>
				<td>${b.writer }</td>
				<td>${b.regdate }</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${startPage>1}">
		<a href="listBoard?pageNUM=${startPage-1 }">◀</a>&nbsp;&nbsp;
	</c:if>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<a href="listBoard?pageNUM=${i }">${i }</a>&nbsp;&nbsp;
	</c:forEach>
	<c:if test="${endPage<totalPage}">
		<a href="listBoard?pageNUM=${endPage+1 }">▶</a>
	</c:if>
</body>
</html>