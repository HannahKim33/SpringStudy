<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	.highlight{
		background:yellow;
	}
	.active{
		background:pink;
	}

	.goods_div{
		float:left;
		height:250px;
		width:200px;
		border:solid 1px pink;
		overflow:auto;
	}
</style>
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$(document).on("mouseover",".item",function(){
			$(this).addClass("highlight");
		})
		$(document).on("mouseleave",".item",function(){
			$(this).removeClass("highlight");
		})
		$(document).on("click",".item",function(){
			$(".item").removeClass("active");
			$(this).addClass("active");
		})
		
		$(document).on("click",".item",function(){
			var td_list=$(this).find("td");
			$("#dno").val($(td_list[0]).text());
			$("#dname").val($(td_list[1]).text());
			$("#dloc").val($(td_list[2]).text());
		});
		
		
		
		function listDept(){
			$.ajax({
				url:"listDept",
				success:function(arr){
					$("#list").empty();
					$.each(arr,function(){
						var tr=$("<tr></tr>").addClass("item");
						var td1=$("<td></td>").html(this.dno);
						var td2=$("<td></td>").html(this.dname);
						var td3=$("<td></td>").html(this.dloc);
						$(tr).append(td1,td2,td3);
						$("#list").append(tr);
					})
				}
			})
		}
		
		listDept();
		
		$("#btnInsert").click(function(){
			var data=$("#f").serializeArray();
			$.ajax({
				url:"insertDept",
				data:data,
				success:function(re){
					console.log(re);
					listDept();
				}
			})
		})
		
		$("#btnUpdate").click(function(){
			var data=$("#f").serializeArray();
			console.log(data)
			$.ajax({
				url:"updateDept",
				data:data,
				success:function(re){
					console.log(re);
					listDept();
				}
			})
		})
		
		$("#btnDel").click(function(){
			if(confirm("정말로 삭제하시겠어요?")){
				$.ajax({
					url:"deleteDept",
					data:{dno:$("#dno").val()},
					success:function(re){
						console.log(re);
						listDept();
					}
				})
			}
		})		
	})
</script>
</head>
<body>
	<table border='1'>
		<thead>
			<tr>
				<td>부서번호</td>
				<td>부서명</td>
				<td>부서위치</td>
			</tr>
		</thead>
		<tbody id="list"></tbody>
	</table>
	
	<hr>
	<form id="f">
		부서번호: <input type="number" name="dno" id="dno"><br>
		부서이름: <input name="dname" id="dname"><br>
		부서위치: <input name="dloc" id="dloc"><br>
		<input type="reset" value="리셋">
	</form>
	<button id="btnInsert">등록</button>
	<button id="btnUpdate">수정</button>
	<button id="btnDel">삭제</button>
	
</body>
</html>