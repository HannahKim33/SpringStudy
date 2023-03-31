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
	#op{
		display:none;
	}
	#f{
		display:inline;
	}
	#reset{
		display:inline;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".item").hover(function(){
			$(this).addClass("highlight");
		},function(){
			$(this).removeClass("highlight");
		});
		
		$(".item").click(function(){
			var no=$(this).attr("no");
			location.href="detailGoods?no="+no;
		});
		
		$("#searchColumn").change(function(){
			var c_name=$(this).val();
			if(c_name=="name"){
				$("#op").css("display","none")
			}else{
				$("#op").css("display","inline");
			}
		})
		
		$("#f").submit(function(){
			var searchColumn=$("#searchColumn").val();
			var op=$("#op").val();
			var keyword=$("#keyword").val();
			
			sessionStorage.setItem("searchColumn",searchColumn);
			sessionStorage.setItem("op",op);
			sessionStorage.setItem("keyword",keyword);
		});
		
		var sc=sessionStorage.getItem("searchColumn");
		var op=sessionStorage.getItem("op");
		var keyword=sessionStorage.getItem("keyword");
		
		
		$("#searchColumn > option[value="+sc+"]").attr("selected","selected");
		$("#op > option[value='"+op+"']").attr("selected","selected");
		if(keyword!=null){
			$("#keyword").val(keyword);
		}
		
		if(sc==null || sc=='null' || sc=="name"){
			$("#op").css("display","none");
		}else{
			$("#op").css("display","inline");
		}
		
		$("#reset").click(function(){
			sessionStorage.removeItem("searchColumn");
			sessionStorage.removeItem("op");
			sessionStorage.removeItem("keyword");
			
			location.href="listGoods?reset=yes";
		})
	});
</script>
</head>
<body>
	<h2>상품 목록</h2>
	<hr>
	<a href="insertGoods">상품 등록</a><br>
	<form action="listGoods" id="f">
		<select name="searchColumn" id="searchColumn">
			<option value="no">상품번호</option>
			<option value="name" selected="selected">상품명</option>
			<option value="price">가격</option>
			<option value="qty">수량</option>
			<option value="fname">이미지</option>
		</select>
		<select id="op" name="op">
			<option value="=">=</option>
			<option value=">=">>=</option>
			<option value="<="><=</option>
			<option value=">">></option>
			<option value="<"><</option>
			<option value="!=">!=</option>
		</select>
		<input type="search" name="keyword" id="keyword">
		<input type="submit" value="검색">
	</form>
	<button id="reset">검색 초기화</button><br>
	<table border='1'>
		<tr>
			<td><a href="listGoods?orderColumn=no">상품번호</a></td>
			<td><a href="listGoods?orderColumn=name">상품명</a></td>
			<td><a href="listGoods?orderColumn=price">가격</a></td>
			<td><a href="listGoods?orderColumn=qty">수량</a></td>
			<td><a href="listGoods?orderColumn=fname">이미지</a></td>
		</tr>
		<c:forEach var="g" items="${list }">
			<tr class="item" no="${g.no}">
				<td>${g.no }</td>
				<td>${g.name }</td>
				<td>${g.price }</td>
				<td>${g.qty }</td>
				<td>${g.fname }</td>
			</tr>
		</c:forEach>
	</table>
		
	
		
	<hr>
	<c:forEach var="i" begin="1" end="${totalPage }">
		<a href="listGoods?pageNUM=${i }">${i }</a>&nbsp;&nbsp;
	</c:forEach>
</body>
</html>