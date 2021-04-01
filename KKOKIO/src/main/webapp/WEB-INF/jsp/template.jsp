<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<script type="text/javascript">
	function logOut() {
		var con = confirm("로그아웃 하시겠습니까?");
	
		if(con) {
			location.href="./logOut.do";
		} else {
			return false;
		}
	}
</script>
<!-- topDiv-->
		<div id="topDiv">
			<a href="./homePage.do">홈</a>
			<div id="topMenu">
				<div class="topSubMenu">
					<a href="./userSearch.do"><span>회원정보</span></a>
				</div>
				<div class="topSubMenu">
					<a onclick="logOut()">로그아웃</a>
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
					<p><a href="./makePassPage.do">파스 생성</a><br>
					<a href="./infoPassList.do">전체 파스 관리</a></p>
					
				<h3>농장일지</h3>
					<p><a href="./iljiWrite.do">일지 작성</a><br>
					<a href="#">일지 정보</a></p>
					
				<h3>이슈사항</h3>
					<p><a href="./IssueList.do">이슈사항</a></p>
				
				<h3>비품 관리</h3>
					<p><a href="./productList.do">비품</a><br>
					<a href="./purchaseList.do">구매 내역</a></p>
					
				<h3>조회</h3>
					<p><a href="#">조회</a></p>
					
				<h3>통계</h3>
					<p><a href="#">통계</a></p>
					
				<h3>게시판</h3>
					<p><a href="./getAllboard.do">공지사항</a><br>
					<a href="./gonewsPage.do">뉴스</a></p>
			</div>

			<button class="excelBtn" onclick="excelPage()">엑셀파일 업로드/다운로드</button>

		</div>