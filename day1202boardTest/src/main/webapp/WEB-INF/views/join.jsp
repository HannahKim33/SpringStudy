<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#f,#div_enterCode,#div_sendCode{
		display:none;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		function printTimeLimit(){
			var limit=130;
			$("#time_limit").text("2:10");
			var timer=setInterval(function(){
				limit--;
				var mm=Math.floor(limit/60);
				var ss=limit%60;
				if(ss<10){
					ss="0"+ss;
				}
				$("#time_limit").html(mm+":"+ss);
				
				if(limit==-1){
					clearInterval(timer);
					$("#div_enterCode").css("display","none");
					alert("제한시간이 지났습니다. 인증번호를 다시 받으세요.")
				}
			}, 1000);
		}
		
		var verifType="";
		var to="";
		
		$("#radioPhone").click(function(){
			$("#verif_type_span").html("휴대폰 번호");
			$("#div_sendCode").css("display","block");
			$("#to").attr("type","tel");
			verifType="tel";
		})
		
		$("#radioEmail").click(function(){
			$("#verif_type_span").html("이메일");
			$("#div_sendCode").css("display","block");
			$("#to").attr("type","email");
			verifType="email";
		})
		

		
		var codeSent="";
		
		
		$("#btnSend").click(function(){
			to=$("#to").val();
			
			var data={
				verifType:verifType,
				to:to
			}
			console.log(data);
			
			$.ajax({
				url:"sendVerifCode",
				data:data,
				success:function(code){
					codeSent=code;
					$("#div_enterCode").css("display","block");
					printTimeLimit();
				}
			})
		})
		
		$("#btnConfirm").click(function(){
			if($("#codeInput").val()==codeSent){
				$("#result").text("인증 성공").css("color","blue");
				$("#f").css("display","block");
				if(verifType=="tel"){
					$("#phone").val(to);
				}else if(verifType=="email"){
					$("#email").val(to);
				}
				$("#verifContainer").css("display","none");
			}else{
				$("#result").text("인증번호 불일치").css("color","red");
			}
		})
	})
</script>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
	
	<div id="verifContainer">
		본인 인증 방법 선택<br>
		<input type="radio" name="veriMethod" id="radioPhone"> 휴대폰 인증
		<input type="radio" name="veriMethod" id="radioEmail"> 이메일 인증
		
		<div id="div_sendCode">
			<span id="verif_type_span">휴대폰 번호</span> : <input type="text" id="to">
			<button id="btnSend">인증번호 전송</button>
		</div>
		<div id="div_enterCode">
			인증번호 입력: <input id="codeInput"><button id="btnConfirm">확인</button>
			제한시간: <span id="time_limit"></span>
		</div>
	</div>
	
	<div id="result"></div>
	<br>
	
	<form action="join" method="post" id="f">
		아이디 : <input name="id"><br>
		암호 : <input type="password" name="pwd"><br>
		이름 : <input name="name"><br>
		이메일 : <input type="email" name="email" readonly="readonly" id="email"><br>
		전화번호 : <input type="tel" name="phone" readonly="readonly" id="phone"><br>
		<input type="submit" value="가입">
	</form>
</body>
</html>