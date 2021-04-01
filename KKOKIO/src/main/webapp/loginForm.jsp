<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
<link rel="stylesheet" type="text/css" href="./css/Login.css">
<link rel="stylesheet" type="text/css" href="./css/sweetalert.css?ver=1.2">
<script type="text/javascript" src="./js/sweetalert.min.js?ver=1"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="./js/loginForm.js"></script>
<script type="text/javascript">
function signUp() {
	var frm = document.forms[0];
	frm.action = './signUpForm.do';
	frm.submit();
}

//로그인을 ajax를 통해 처리함
function loginCheck() {
	var id = document.getElementById("inputId").value;//$("#inputId").val()과 똑같다.
	var pw = document.getElementById("inputPw").value;
// 	alert(id+":"+pw);
// 	alert($("#inputId").val() + ":" + $("#inputPw").val());

	var frm = document.forms[0];
	frm.action = "./loginUser.do";
	
	var result = "";
	
	//버튼으로 ajax처리하고 넘김
	if(id==null || id=="") {
		swal("로그인", "아이디를 입력해주세요");
	} else if(pw==null || pw=="") {
		swal("로그인", "비밀번호를 입력해 주세요");
	} else {
		//$.ajax의 원래 format은 jQuery.ajax 였다.
		jQuery.ajax({
			//type, URL, data, success 세가지밖에 없다.
			type:"POST",
			url: "./loginCheck.do",
			data : "id="+id+"&"+"pw="+pw,
			success : function(msg){
					alert(msg);
			var temp = "";
			var temp1 = "";
			temp = msg;
			temp1 = msg;
			
			temp = temp.slice(0,2);
			result  = temp;
// 				alert(result);

			if(result == "성공"){
				temp1 = temp1.split("/")[1];
// 					alert(temp1);
				document.getElementById("loginChk").value = temp1; 
				frm.submit();
			}else{
				swal("로그인 실패","아이디나 비밀번호를 확인해 주세요");
			}
			}
		});
	}
	
}

function idSearch() {
	var frm = document.forms[0];
	frm.action = './searchId.do';
	frm.submit();
}

function pwSearch() {
	var frm = document.forms[0];
	frm.action = './searchPw.do';
	frm.submit();
}
</script>
</head>
<body>
	<div id='container'>
	
			<div id='title'>Happy Login</div>
			<div id='id'>아이디</div>
		
		<form method='post' id='frm'>
		
			<input type='hidden' id='loginChk' name='auth' value='0'/>
			<input type='text' name='id' id='inputId' required='required'>
				<br/>
			<div id='pw'>비밀번호</div>
			
			<input type='password' name='pw' id='inputPw' required='required'>
				<br/>
			<input type='button' id='login' name='login' value='로그인' onclick='loginCheck()'/>
				<br/>
			
			<div id='bottom'>
			
				<a href='#' onclick='signUp()'>
					<input type='button' id='SignUp' name='signup' value='회원가입'/>
				</a>
				<a href="#" onclick='idSearch()'>
					<input type='button' id='SearchId' name='SearchId' value='아이디 찾기'/>
				</a>
				<a href="#" onclick='pwSearch()'>
					<input type='button' id='SearchPw' name='SearchPw' value='비밀번호 찾기' onclick='pwSearch()' />
				</a>
			</div>
			
		</form>
		
	</div>
</body>
</html>