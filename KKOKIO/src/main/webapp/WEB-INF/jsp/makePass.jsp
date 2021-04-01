<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KKOKIO | 파스생성</title>
<!-- 폰트 -- 나중에 다운받아서 사용하기 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<!-- jqGrid CSS 파일 -->
<link rel="stylesheet" type="text/css" media="screen" href="./jqgridui/jquery-ui.css">
<link rel="stylesheet" type="text/css" media="screen" href="./jqgrid/css/ui.jqgrid.css">
<!-- 아코디언 메뉴 -->
<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="./css/mainPage_v3.css">
<style type="text/css">
 #makePassForm{
	width: 400px;
	height: auto;
	margin: 0px auto;
}
#scrollTable{
	height: 450px;
	width: 400px;
 	margin: 0px auto; 
	overflow-y: scroll;
	overflow-x: hidden;
}
#makePassForm table input{
	height: 30px;
	width: 100%;
}

/* #makePassForm tr,#makePassForm th,#makePassForm td{
	border-bottom: 1px solid #638270;
	border-right: 1px solid #638270;
}



#makePassForm th{
	background: #638270;
	text-align: center;
}  */

/* #table1 th,#table3 th{
	width:120px;
}

#table1 td,#table3 td{
	width:280px;
}

#table2 {
	width:398px;
}

#table2 input{
	text-align: center;
} */
input#bluecol {
	color: white;
	border: #50B0FF;
	background: #50B0FF;
	cursor: pointer;
	height: 30px;
	width: 150px;
	margin-left: 100px;
}
</style>
</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<!-- jqGrid JavaScript 파일 -->
<script type="text/javascript" src="./jqgrid/src/i18n/grid.locale-kr.js"></script>
<script type="text/javascript" src="./jqgridui/jquery-ui.js"></script>
<script type="text/javascript" src="./jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="./js/jqGridPaging.js"></script>
<!-- 아코디언 메뉴 -->
<script type="text/javascript" src="./js/accordionMenu_v3.js"></script>
<!-- 날씨 정보 -->
<script type="text/javascript" src="./js/weather.js"></script>
<script type="text/javascript">
$(function(){
	document.getElementById('startpass').valueAsDate = new Date();
});

function makePass(){
	
//파스종료일
// 	if($("#endpass").val()==""){
// 		alert("파스 종료일을 입력해주세요.");
// 		return;
// 	}
	
	var startpass = $("#startpass").val();
	startpass =new Date(startpass);
	var startTime=startpass.getTime();
// 	alert(startTime);
	
	var endpass=$("#endpass").val();
	endpass =new Date(endpass);
	var endTime=endpass.getTime();
// 	alert(endTime);
	
	if(endTime<=startTime){
		alert("파스종료일을 파스시작일보다 크게 설정해주세요.");
		return;
	}
	
//입추수수
// 	alert($("#incount").val());
	if($("#incount").val()==""){
		alert("입추수수를 입력해주세요.");
		return;
	}
	
	if($("#incount").val()<=0){
		alert("입추수수 값을 0보다 크게  입력해주세요.");
		return;
	}
	
//덤 수	
// 	alert($("#indum").val());
	if($("#indum").val()==""){
		alert("덤수를 입력해주세요.");
		return;
	}

	if($("#indum").val()<0){
		alert("덤 수 값이 음수입니다 .");
		return;
	}

//동별 병아리수	
	var dongchicknumList=document.getElementsByName("dongchicknum_i");
	var dongarr=new Array();
	
	for(var i=0;i<dongchicknumList.length;i++){
		if(dongchicknumList[i].value<0){
			alert("동별 병아리수 값이 음수입니다.");
			return;
		}else if(dongchicknumList[i].value==""){
			dongarr[i]=0;
		}else{
		dongarr[i]=dongchicknumList[i].value;
		}
	}

	var dongchickconcat=dongarr.join('/');
// 	alert(dongchickconcat);	
// 	$("#dongchicknum").text(dongchickconcat);
	
//전송	
	var frm=document.getElementById("frm");
	var formData = new FormData(frm);
	
	$.ajax({
		url:"./makePass.do?dongchicknum="+dongchickconcat,
		type:"POST",
    	processData:false,
    	contentType: false,
  		data: formData,
        cache: false,
        success: function(msg){
//     	 	alert(msg);
    	 	if(msg=="pass_duplicated"){
    	 		alert("해당 입추날짜의 파스가 이미 존재합니다.");
    	 	}else{
    	 		alert("파스가 성공적으로 생성되었습니다.");
    	 		location.href="./homePage.do";
    	 	}
        }
	});
	
// 	form.action="./makePass.do?dongchicknum="+dongchickconcat;
// 	form.method="post";
// 	form.submit();
}
</script>
<body>
	<div id="container">
	<%@include file="/WEB-INF/jsp/template_v3.jsp" %>
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title">파스 생성</div>
			<div id="makePassForm">
			<div id="scrollTable">
				<form id="frm">
					<table id="table1">
						<tr><td width="5%" align="center">*</td><td width="20%">파스 시작일</td><td><input type="date" id="startpass" name="startpass" style="width: 268px"></td></tr>
						<tr height="7"><td colspan="3"><hr></td></tr>
						<tr><td width="5%" align="center">*</td><td width="20%">파스 종료일</td><td><input type="date" id="endpass" name="endpass" style="width: 268px"></td></tr>
						<tr height="7"><td colspan="3"><hr></td></tr>
						<tr><td width="5%" align="center">*</td><td width="20%">입추수수</td><td><input type="number" id="incount" name="incount" style="width: 268px"></td></tr>
						<tr height="7"><td colspan="3"><hr></td></tr>
						<tr><td width="5%" align="center">*</td><td width="20%">덤 수</td><td><input type="number" id="indum" name="indum" style="width: 268px"></td></tr>
						<tr height="7"><td colspan="3"><hr></td></tr>
					</table>
					
					<table id="table2">
						<tr><td width="5%" align="center">*</td><td width="20%">동별 마리수</td></tr>
							<c:forEach begin="1" end="${memid.DONGNUMBER}" step="1" var="i">			 	
						<tr><td width="5%" align="center"> </td><td width="20%">${i} 동</td><td><input type="number" placeholder="${i}동 마리수를 입력해주세요" name="dongchicknum_i" style="width: 268px"></td></tr>
							</c:forEach>
						<tr height="7"><td colspan="3"><hr></td></tr>
					</table>
					<table id="table3">
						<tr><td width="5%" align="center">*</td><td width="20%">품종</td><td><input type="text" placeholder="ex) 하바드, 로스, 코브 " name="intype" style="width: 268px"></td></tr>
							<tr height="7"><td colspan="3"><hr></td></tr>
						<tr><td width="5%" align="center">*</td><td width="20%">부화장</td><td><input type="text" name="inbuhwa" style="width: 268px"></td></tr>
							<tr height="7"><td colspan="3"><hr></td></tr>
						<tr><td width="5%" align="center">*</td><td width="20%">활발도</td><td><input type="text" placeholder="ex) 상, 중, 하" name="inactivity" style="width: 268px"></td></tr>
						<tr height="7"><td colspan="3"><hr></td></tr>
					</table>
					<input type="button" id="bluecol"onclick="makePass()" value="파스 생성">
				</form>
			</div>
		</div>
</div>
<!-- rightDiv-->
		<%@include file="/WEB-INF/jsp/template_right.jsp" %>
	</div>
</html>