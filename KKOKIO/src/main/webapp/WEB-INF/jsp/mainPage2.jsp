<%@page import="java.util.ListIterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인페이지</title>
<link rel="stylesheet" type="text/css" href="./css/mainPage.css">
</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="./js/accordionMenu.js"></script>
<script type="text/javascript" src="./js/FileSaver.min.js"></script>
<script type="text/javascript" src="./js/xlsx.full.min.js"></script>
<script type="text/javascript" src="./js/excelAPI.js"></script>
<body>
	<div id="container">
<!-- topDiv-->
	<form method="get" id="frm">
		<div id="topDiv">
			<div id="topMenu">
				<div class="topSubMenu">
					<span style="width:100px;">${memid.ID}님 환영합니다.</span>
					<a href="./userSearch.do"><span>회원정보</span></a>
				</div>
				<div class="topSubMenu">
					<a href="./logOut.do">로그아웃</a>
				</div>
			</div>
		</div>
	</form>
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
					<a href="./gonewsPage.do">뉴스</a></p>
			</div>

			<button class="excelBtn" onclick="excelAPI()">엑셀파일 업로드/다운로드</button>

		</div>
		
		

		
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title">
			</div>
		</div>


<!-- rightDiv-->
		<div id="rightDiv">

		</div>


	</div>
</html>