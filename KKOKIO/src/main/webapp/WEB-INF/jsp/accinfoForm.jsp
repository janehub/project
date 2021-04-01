<%@page import="java.util.Map"%>
<%@page import="com.next.kko.dtos.AccountDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KKOKIO | 개인정보</title>
<style type="text/css">

/*
프로그램 전체의 틀
*/
#container {
	width: 1200px;
	height: 700px;
	margin: 0px auto;
	padding: 20px;
	border: 1px solid black;
}


/*
회원이 정보를 기입할 수 있는 항목 내 div
*/
#infocontainer{
	display: inline-block;
	width: 70%;
	margin: 0px auto;
	margin-left: 450px;
	height: 650px;
/* 	width:600px; */
/* 	height:650px; */
/* 	margin: 0px auto; */
/* 	align-items: center; */
/* 	float: left; */
}

/*
모든 입력 사항들의 크기조절 CSS
*/
#name, #id, #pw, #passOk, #email, #phone, #address, #name, #farmname, #farmaddress, #farmtel, #dongnumber, #sample6_postcode, #sample6_address, #sample6_address2 {
	width:200px;
	height: 25px;
}

#bottom {
	width: 950px;
	height: 100px;
	color: gray;
	font-size: 11px;
	text-align: center;
}

#bottomstrong {
	position: relative;
	font-size: 18px;
	color: black;
	left: 10px;
	top: 10px;
	bottom: 10px;
	margin-bottom: 10px;
}

#button {
	margin: 10px auto;
}

input#dropout{
	color: white;
	position: relative;
	right: 0px;
	top: 0px;
	border: red;
	background: red;
	cursor: pointer;
	height: 30px;
	width: 150px;
	margin: 10px 0px 0px 90px;
}

a{
	text-decoration: none;
}
div p{
	margin-left: -150px;
}
#headline{
	margin-left: -150px;
	width: 480px;
}
button#bluecol {
	color: white;
	border: #50B0FF;
	background: #50B0FF;
	cursor: pointer;
	height: 30px;
	width: 150px;
	margin-left: 10px;
}
input#bluecol{
color: white;
	border: #50B0FF;
	background: #50B0FF;
	cursor: pointer;
	height: 30px;
	width: 150px;
	margin-left: 10px;
}
input#graycol {
	color: white;
	border: #B8B8B8;
	background: #B8B8B8;
	cursor: pointer;
	height: 30px;
	width: 150px;
}


 a{ 
 	text-decoration: none;
 }
 
 span #accemail,#accphone,#accaddress,#accname,#accfarmname,#accfarmaddress,#accfarmtel{
 	margin: 10px;
 	padding: 0px;
 }
</style>
<link rel="stylesheet" type="text/css" href="./css/sweetalert.css?ver=1.2">
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/bootstrap-theme.min.css">
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="./js/sweetalert.min.js?ver=1"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#dbinfo").show();
	$("#writeinfo").hide();
	
	
	$("button").click(function(){
		$("#dbinfo").hide();
		$("#writeinfo").show();
	});
	
	
	
	$("#email").keyup(function(){
		var inputLength = $(this).val().length;
		$("#email").html("");
		var email = "";
		email = $(this).val();
		
		if($("#email").val().length == "") {
			$("#accemail").css("color", "white");
		} else if(!/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/.test(email)) {
			$("#accemail").css("color", "red")
			$("#accemail").html("이메일 형식이 맞지 않습니다");			
		} else {
			$("#accemail").css("color", "blue")
			$("#accemail").html("사용가능한 이메일 입니다.");			
		}
	});
	
	
	
	$("#phone").keyup(function(){
		var inputLength = $(this).val().length;
		$("#phone").html("");
		var email = "";
		email = $(this).val();
		
		if($("#phone").val().length == "") {
			$("#accphone").css("color", "white");
		} else if(!/^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/.test(email)) {
			$("#accphone").css("color", "red")
			$("#accphone").html("연락처 형식이 맞지 않습니다");			
		} else {
			$("#accphone").css("color", "blue")
			$("#accphone").html("사용가능한 연락처입니다.");			
		}
	});
	
	
// 	$("#address").keyup(function(){
// 		var inputLength = $(this).val().length;
// 		$("#address").html("");
// 		var email = "";
// 		email = $(this).val();
		
// 		if($("#address").val().length == "") {
// 			$("#accaddress").css("color", "white");
// 		} else if(!/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/.test(email)) {
// 			$("#accaddress").css("color", "red")
// 			$("#accaddress").html("주소 형식이 맞지 않습니다");			
// 		} else {
// 			$("#accaddress").css("color", "blue")
// 			$("#accaddress").html("사용가능한 전화번호입니다.");			
// 		}
// 	});

		 
	$("#name").keyup(function(){
		var inputLength = $(this).val().length;
		$("#name").html("");
		var name = "";
		name = $(this).val();
		
		if($("#name").val().length == "") {
			$("#accname").css("color", "white");
		} else if(!/^[가-힣]{3,4}$/.test(name)) {
			$("#accname").css("color", "red")
			$("#accname").html("한글로 이름을 입력해주세요");			
		} else {
			$("#accname").css("color", "blue")
			$("#accname").html("사용가능합니다.");			
		} 
	});
	
// 	$("#farmname").keyup(function(){
// 		var inputLength = $(this).val().length;
// 		$("#farmname").html("");
// 		var farmname = "";
// 		farmname = $(this).val();
		
// 		if($("#farmname").val().length == "") {
// 			$("#accfarmname").css("color", "white");
// 		} else if(!/^[가-힣]{3,20}$/.test(farmname)) {
// 			$("#accfarmname").css("color", "red")
// 			$("#accfarmname").html("한글로 이름을 입력해주세요");			
// 		} else {
// 			$("#accfarmname").css("color", "blue")
// 			$("#accfarmname").html("사용가능합니다.");			
// 		} 
// 	});
	
// 	$("#address").keyup(function(){
// 		var inputLength = $(this).val().length;
// 		$("#address").html("");
// 		var address = "";
// 		address = $(this).val();
		
// 		if($("#address").val().length == "") {
// 			$("#accaddress").css("color", "white");
// 		} else if(!/^[가-힣\s]+$/.test(address)) {
// 			$("#accaddress").css("color", "red")
// 			$("#accaddress").html("한글로 이름을 입력해주세요");			
// 		} else {
// 			$("#accaddress").css("color", "blue")
// 			$("#accaddress").html("사용가능합니다.");			
// 		} 
// 	});
	
	
	$("#farmaddress").keyup(function(){
		var inputLength = $(this).val().length;
		$("#farmaddress").html("");
		var farmaddress = "";
		farmaddress = $(this).val();
		
		if($("#farmaddress").val().length == "") {
			$("#accfarmaddress").css("color", "white");
		} else if(!/^[가-힣\s]+$/.test(farmaddress)) {
			$("#accfarmaddress").css("color", "red")
			$("#accfarmaddress").html("한글로 이름을 입력해주세요");			
		} else {
			$("#accfarmaddress").css("color", "blue")
			$("#accfarmaddress").html("사용가능합니다.");			
		} 
	});
	
	
	$("#farmtel").keyup(function(){
		var inputLength = $(this).val().length;
		$("#farmtel").html("");
		var farmtel = "";
		farmtel = $(this).val();
		
		if($("#farmtel").val().length == "") {
			$("#accfarmtel").css("color", "white");
		} else if(!/^\d{2,3}-\d{3,4}-\d{4}$/.test(farmtel)) {
			$("#accfarmtel").css("color", "red")
			$("#accfarmtel").html("연락처 형식이 맞지 않습니다");			
		} else {
			$("#accfarmtel").css("color", "blue")
			$("#accfarmtel").html("사용가능한 연락처입니다.");			
		}
	});
	
});


function canceldrop() {
	var con = confirm("계정을 삭제하시겠습니까?");
	
	if(con) {
		location.href="./dropUser.do";
	} else {
		return null;
	}
}


function cancel() {
	var con = confirm("정보 수정을 취소하시겠습니까?");
	
	if(con) {
		location.href="./userSearch.do";
// 		$("#writeinfo").hide();
// 		$("#dbinfo").show();
	} else {
		return null;
	}
}


function canceltoMain() {
	var con = confirm("메인으로 돌아가시겠습니까?");
	
	if(con) {
		location.href="./mainForm.do";
	} else {
		return null;
	}
}


//정규화 표현식
function check() {
	
	var telregExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	var farmtelregExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
	var emailregExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/; 
	var hangeulregExp = /^[가-힣]{3,4}$/;
	var hangeulspzceregExp = /^[가-힣\s]+$/;
	
	
	var email = document.getElementById("email").value;
	var phone = document.getElementById("phone").value;
	var address = document.getElementById("address").value;
	var name = document.getElementById("name").value;
	var farmname= document.getElementById("farmname").value;
	var farmaddress = document.getElementById("farmaddress").value;
	var farmtel = document.getElementById("farmtel").value;
	

	if(!(emailregExp.test($("#email").val()))) {
		swal("회원수정 오류", "정확한 이메일 주소를 입력해주세요");
		return false;
	} else if(!(farmtelregExp.test($("#phone").val()))) {
 		swal("회원수정 오류", "휴대전화 정보를 제대로 입력해주세요");
 		return false;
	} else if(!isNaN(address)) {
		swal("회원수정 오류", "주소 정보를 제대로 입력해주세요");
		return false;
	}
// 	} else if(!isNaN(name)) {
// 		swal("회원수정 오류", "이름을 제대로 입력해주세요");
// 		return false;
// 	} else if(!isNaN(farmname)) {
// 		swal("회원수정 오류", "농장명을 제대로 입력해주세요");
// 		return false;
// 	} else if(!isNaN(farmaddress)) {
// 		swal("회원수정 오류", "농장주소를 제대로 입력해주세요");
// 		return false;
// 	} else if(!(farmtelregExp.test($("#farmtel").val()))) {
// 		swal("회원수정 오류", "농장 전화번호를 제대로 입력해주세요");
// 		return false;
// 	}
}

</script>
</head>
<body>
<div id="container">
	<!-- span 태그와 input 태그가 모여있는 div -->
	<div id="infocontainer">
			<!-- DB에 저장되어있는 회원 정보를 모은 div 클래스 -->
			<div id="dbinfo">	
					<p><span><b>${id}</b> 회원님의 정보</span></p>
					<hr id="headline">
					<table>
					<tr><td width="5%" align="center">*</td><td width="30%">이메일</td><td><input type="text" value="${dto.email}" readonly="readonly" size="25px"></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>       
					<tr><td width="5%" align="center">*</td><td width="30%">연락처</td><td><input type="text" value="${dto.phone}" readonly="readonly" size="25px"></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>       
					<tr><td width="5%" align="center">*</td><td width="30%">주소</td><td><input type="text" value="${dto.address}" readonly="readonly" size="25px"></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>       
					<tr><td width="5%" align="center">*</td><td width="30%">농장주</td><td><input type="text" value="${dto.name}" readonly="readonly" size="25px"></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>       
					<tr><td width="5%" align="center">*</td><td width="30%">농장명</td><td><input type="text" value="${dto.farmname}" readonly="readonly" size="25px"></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>  
					<tr><td width="5%" align="center">*</td><td width="30%">농장주소</td><td><input type="text" value="${dto.farmaddress}" readonly="readonly" size="25px"></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>      
					<tr><td width="5%" align="center">*</td><td width="30%">농장전화번호</td><td><input type="text" value="${dto.farmtel}" readonly="readonly" size="25px"></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>      
					<tr><td width="5%" align="center">*</td><td width="30%">동 갯수</td><td><input type="text" value="${dto.dongnumber}" readonly="readonly" size="25px"></td></tr>
					</table>
					<br><br>
					<button id="bluecol">수정하기</button>
					<input type="button" id="graycol" onclick="canceltoMain()" value="메인으로 돌아가기">
			</div>
			
			<!-- 회원이 수정할 정보를 입력할 수 있는 span과 input 태그가 있는 div -->
			<form action="./userModify.do" method="POST" onsubmit="return check()">
			<div id="writeinfo">
				<p><b>${id}</b> 회원님의 정보를 수정해주세요</p>
				<hr id="headline">
				<table>
					<tr><td width="5%" align="center">*</td><td width="100px">이메일</td><td><input type="text" value="${dto.email}" id ="email" name="email" size="25px"><span id="accemail"></span></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>       
					<tr><td width="5%" align="center">*</td><td width="100px">연락처</td><td><input type="text" value="${dto.phone}" id ="phone" name="phone" size="25px"><span id="accphone"></span></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>    
					<tr><td width="5%" align="center">*</td><td width="100px">주소</td><td><input type="text" value="${dto.address}" id ="address" name="address" size="25px"><span id="accaddress"></span></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>   
					<tr><td width="5%" align="center">*</td><td width="100px">농장주</td><td><input type="text" value="${dto.name}" id ="name" name="name" size="25px"><span id="accname"></span></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>  
					<tr><td width="5%" align="center">*</td><td width="100px">농장명</td><td><input type="text" value="${dto.farmname}"  id="farmname" name="farmname" size="25px"><span id="accfarmname"></span></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>
					<tr><td width="5%" align="center">*</td><td width="100px">농장주소</td><td><input type="text" value="${dto.farmaddress}" id="farmaddress" name="farmaddress" size="25px"><span id="accfarmaddress"></span></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>   
					<tr><td width="5%" align="center">*</td><td width="100px">농장전화번호</td><td><input type="text" value="${dto.farmtel}" id="farmtel" name="farmtel" size="25px"><span id="accfarmtel"></span></td></tr>
					<tr height="7"><td colspan="3"><hr></td></tr>     
					</table>
					<p>동 갯수는 해당 페이지에서 바꾸실 수 없습니다. 바꾸시길 희망하시면은 관리자에게 연락주세요.</p>
				<input type="submit" value="정보 수정하기" id="bluecol">
				<input type="button" onclick="cancel()" value="취소하기" id="graycol"><br>
				<a onclick="canceldrop()"><input type="button" id="dropout" value="회원탈퇴"></a>
			</div>
			</form>
	</div>

</div>	<!-- id="info" -->
</body>
</html>