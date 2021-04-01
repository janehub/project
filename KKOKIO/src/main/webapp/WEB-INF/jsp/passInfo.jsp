<%@page import="java.util.Date"%>
<%@page import="com.next.kko.dtos.PassDto"%>
<%@page import="com.next.kko.util.KkokioUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파스 정보</title>
<link rel="stylesheet" type="text/css" href="./css/mainPage.css">
</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="./js/accordionMenu.js"></script>
<script type="text/javascript" src="./js/excel/FileSaver.min.js"></script>
<script type="text/javascript" src="./js/excel/xlsx.full.min.js"></script>
<script type="text/javascript" src="./js/excel/excelAPI.js"></script>
<%
	PassDto pdto = (PassDto)request.getAttribute("pdto");
	String m_startpass = KkokioUtil.removeTime(pdto.getStartpass());
	String m_endpass = KkokioUtil.removeTime(pdto.getEndpass());
%>
<body>
<script type="text/javascript">
	$(function(){
		$("#modifyPass").hide();
	});
	
	function modify(){
		$("#infoPass").hide();
		$("#modifyPass").show();
	}
	
	function cancel(){
		$("#modifyPass").hide();
		$("#infoPass").show();
	}
	
	function deletePass(){
		var val = confirm("정말 삭제하시겠습니까?");
		if(val){
	 		var frm = document.getElementById("infoFrm");
	 		frm.action = "./deletePass.do";
	 		frm.method = "post";
	 		frm.submit();
		}
	}
</script>
	<div id="container">
<!-- topDiv-->
		<div id="topDiv">
			<div id="topMenu">
				<div class="topSubMenu">
					<a href="#"><span>회원정보</span></a>
				</div>
				<div class="topSubMenu">
					<a href="#">로그아웃</a>
				</div>
			</div>
		</div>
<!-- leftDiv -->
		<div id="leftDiv">
			<div id="farmInfo">
			<p>농장명</p>
			</div>
			<div id="accordion">
				<h3>파스정보</h3>
				<!-- 세션에서 값 가져오기 : passcode, id -->
					<p><a href="./infoDetailPass.do">현재 파스 정보</a><br>
					<a href="./infoPassList.do">전체 파스 관리</a></p>
					
				<h3>농장일지</h3>
					<p><a href="#">일지 작성</a><br>
					<a href="#">일지 정보</a></p>
					
				<h3>이슈사항</h3>
					<p><a href="#">이슈사항</a></p>
				
				<h3>비품 관리</h3>
					<p><a href="#">비품</a><br>
					<a href="#">구매 내역</a></p>
					
				<h3>조회</h3>
					<p><a href="#">조회</a></p>
					
				<h3>통계</h3>
					<p><a href="#">통계</a></p>
					
				<h3>게시판</h3>
					<p><a href="#">공지사항</a><br>
					<a href="#">뉴스</a></p>
			</div>

			<button class="excelBtn" onclick="excelAPI()">엑셀파일 업로드/다운로드</button>

		</div>
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title">
				파스 정보
			</div>
			<div id="infoPass">
				파스 코드 ${pdto.getPasscode()}<br>
				파스 시작일 <%=m_startpass%><br>
				파스 종료일 <%=m_endpass%><br>
				입추수수 ${pdto.getIncount()}<br>
				덤 수 ${pdto.getIndum()}<br>
				품종 ${pdto.getIntype()}<br>
				부화장 ${pdto.getInbuhwa()}<br>
				활발도 ${pdto.getInactivity()}<br>
				동별병아리수 '/'로 구분  ${pdto.getDongchicknum()}<br>
				
				<button onclick="modify()">수정하기</button>
				<form id="infoFrm">
					<input type="hidden" value="${pdto.getPasscode()}"name="passcode">
					<button onclick="deletePass()">삭제하기</button>
				</form>
			</div>
			<div id="modifyPass">
				<form action="./modifyPass.do" method="post">
					
					파스 코드 <input type="text" value="${pdto.getPasscode()}" name="passcode" readonly="readonly"><br>
					파스 시작일 <input type="date" value="<%=m_startpass%>" name="startpass" readonly="readonly"><br>
					파스 종료일 <input type="date" value="<%=m_endpass%>" name="endpass" required="required"><br>
					입추수수 <input type="number" value="${pdto.getIncount()}" name="incount" required="required"><br>
					덤 수 <input type="number" value="${pdto.getIndum()}" name="indum"  required="required"><br>
					품종  <input type="text" value="${pdto.getIntype()}" name="intype"><br>
					부화장 <input type="text" value="${pdto.getInbuhwa()}" name="inbuhwa"><br>
					활발도 <input type="text" value="${pdto.getInactivity()}" name="inactivity"><br>
					동별병아리수 '/'로 구분  <input type="text" value="${pdto.getDongchicknum()}" name="dongchicknum" required="required"><br>
					
					<input type="submit" value="수정하기">
				</form>
					<button onclick="cancel()">취소하기</button>
			</div>
		</div>
<!-- rightDiv-->
		<div id="rightDiv">
		
		</div>


	</div>
</html>