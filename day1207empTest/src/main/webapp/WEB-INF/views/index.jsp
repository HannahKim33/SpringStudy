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
		
		/////////////////////////goods
		
		function listGoods(){
			$.ajax({
				url:"listGoods",
				success:function(list){
					$("#ul").empty();
					$.each(list,function(){
						var li=$("<li></li>").html(this.name).addClass("goods").attr("no",this.no);
						$("#ul").append(li);
					})
					$("#detail").empty();
				}
			})
		}
		
		listGoods();
		
		
		$(document).on("mouseover",".goods",function(){
			$(this).addClass("highlight");
		})
		$(document).on("mouseleave",".goods",function(){
			$(this).removeClass("highlight");
		})
		
		$(document).on("click",".goods",function(){
			
		})
		
		$(document).on("click",".goods",function(){
			$("#goodsForm")[0].reset();
			
			$(".goods").removeClass("active");
			$(this).addClass("active");
			
			var no=$(this).attr("no");
			console.log(no);
			
			$.ajax({
				url:"detailGoods",
				data:{no:no},
				success:function(g){	
					$("#detail").empty();
					var div1=$("<div></div>").html(g.no);
					var div2=$("<div></div>").html(g.name);
					var div3=$("<div></div>").html(g.price);
					var div4=$("<div></div>").html(g.qty);
					var img=$("<img>").attr({src:"images/"+g.fname,width:100,height:100})
					$("#detail").append(div1,div2,div3,div4,img);
					
					$("#no").val(g.no);
					$("#name").val(g.name);
					$("#price").val(g.price);
					$("#qty").val(g.qty);
					$("#fname").val(g.fname);
				}
			})
			
		})
		
		$("#btnGoodsInsert").click(function(){
			var form=new FormData(document.getElementById("goodsForm"));
			console.log(form);
			
			$.ajax({
				url:"insertGoods",
				type:"post",
				processData:false,
				contentType:false,
				data:form,
				dataType:"json",
				success:function(re){
					listGoods();
					console.log(re);
				}
			})
			
		})
		
		
		$("#btnGoodsUpdate").click(function(){
			var form=new FormData(document.getElementById("goodsForm"));
			console.log(form);
			
			$.ajax({
				url:"updateGoods",
				type:"post",
				processData:false,
				contentType:false,
				data:form,
				dataType:"json",
				success:function(re){
					listGoods();
					console.log(re);
				}
			})
			
		})
		
		$("#btnGoodsDelete").click(function(){
			if(confirm("정말로 삭제하시겠습니까?")){
				$.ajax({
					url:"deleteGoods",
					data:{no:$("#no").val(),fname:$("#fname").val()},
					success:function(re){
						listGoods();
						console.log("re: ",re);
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
	
	<hr>
	<h3>상품목록</h3>
	<hr>
	<div class="goods_div" id="ul_div">
		<ul id="ul"></ul>
	</div>
	<div class="goods_div" id="detail"></div>
	<div class="goods_div">
		<form id="goodsForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="no" id="no" value="0">
			상품이름 : <input type="text" name="name" id="name"><br>
			상품가격 : <input type="text" name="price" id="price"><br>
			상품수량 : <input type="text" name="qty" id="qty"><br>
			<input type="hidden" name="fname" id="fname">
			상품사진 : <input type="file" name="uploadFile" id="uploadFile"><br>
			<input type="button" value="등록" id="btnGoodsInsert">
			<input type="button" value="수정" id="btnGoodsUpdate">
			<input type="button" value="삭제" id="btnGoodsDelete">
		</form>
	</div>
</body>
</html>