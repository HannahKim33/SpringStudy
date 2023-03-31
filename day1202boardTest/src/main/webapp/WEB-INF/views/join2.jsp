<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#f,#enterCode_email{
		display:none;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var codeSent=-1;
		$("#btnSend").click(function(){
			var data={email:$("#email").val()};
			$.ajax({
				url:"sendCode",
				data:data,
				success:function(code){
					console.log(code);
					codeSent=code;
					$("#codeInput").css("display","block");
					
				}
			})
		})
		
		$("#btnConfirm").click(function(){
			if($("#codeTyped").val()==codeSent){
				$("#result").text("인증이 완료되었습니다.").css("color","blue");
				$("#email_in_form").val($("#email").val());
				$("#f").css("display","block");
				
			}else{
				$("#result").text("코드가 일치하지 않습니다.").css("color","red");
			}
		})
	})
</script>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
	
	이메일 : <input type="email" id="email"><button id="btnSend">인증 코드 전송</button>
	<div id="EnterCode_email">인증코드: <input id="codeTyped"> <button id="btnConfirm">확인</button></div>
	<div id="result"></div>
	
	<br>
	
	<form action="join" method="post" id="f">
		아이디 : <input name="id"><br>
		암호 : <input type="password" name="pwd"><br>
		이름 : <input name="name"><br>
		이메일 : <input type="email" name="email" id="email_in_form" readonly="readonly"><br>
		<input type="submit" value="가입">
	</form>
</body>
</html>