<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인페이지</title>
<link rel="stylesheet" type="text/css" href="./css/mainPage.css">
</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	/*아코디언 메뉴*/
	$(function() {
		$("#accordion p").eq(0).show();
		$("#accordion p").eq(0).siblings("p").hide();
		$("#accordion h3").eq(0).addClass("active");
		
		$("#accordion h3").click(function(){
//	 		alert($(this).next().val());
			$(this).next().stop().slideToggle();
			$(this).next().siblings("p").stop().slideUp();
			$(this).toggleClass("active");
			
			$(this).siblings("h3").each(function(){
				if($(this).hasClass("active")){
					$(this).removeClass("active");
				}
			});
		});
	});
</script>
<body>
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
					<p><a href="#">현재 파스 정보</a><br>
					<a href="#">전체 파스 관리</a></p>
					
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

			<button class="excelBtn">엑셀파일 업로드/다운로드</button>

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