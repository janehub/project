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
	/*아코이언 메뉴*/
	$(function() {
		$(".sideSubMenu1 p").click(
				function() {
					$(this).next().slideDown().end().parent().siblings("li")
							.find("ul").slideUp();
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
			<p id="farmName">농장명</p>
			<ul class="sideMenu">
				<li class="sideSubMenu1">
					<p>파스정보</p>
					<ul class="sideSubMenu2">
						<li><a href="#">현재 파스 정보</a></li>
						<li><a href="#">전체 파스 관리</a></li>
					</ul>
				</li>
				<li class="sideSubMenu1">
					<p>농장일지</p>
					<ul class="sideSubMenu2">
						<li><a href="#">일지 작성</a></li>
						<li><a href="#">일지 정보</a></li>
					</ul>
				</li>
				<li class="sideSubMenu1">
					<p>이슈사항</p>
				</li>
				<li class="sideSubMenu1">
					<p>비품 관리</p>
					<ul class="sideSubMenu2">
						<li><a href="#">비품</a></li>
						<li><a href="#">구매 내역</a></li>
					</ul>
				</li>
				<li class="sideSubMenu1">
					<p>조회</p>
				</li>
				<li class="sideSubMenu1">
					<p>통계</p>
				</li>
				<li class="sideSubMenu1">
					<p>게시판</p>
					<ul class="sideSubMenu2">
						<li><a href="#">공지사항</a></li>
						<li><a href="#">뉴스</a></li>
					</ul>
				</li>
			</ul>


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