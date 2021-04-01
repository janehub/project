<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KKOKIO | 공지사항 작성</title>
<!-- 폰트 -- 나중에 다운받아서 사용하기 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<!-- 아코디언 메뉴 -->
<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/mainPage_v3.css">
</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/accordionMenu_v3.js"></script>
<body>
	<div id="container">
	<%@include file="/WEB-INF/jsp/template_v3.jsp" %>
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title"> 공지사항 작성 </div>
			<div>
				<form action="./insertBoard.do" method="post">
					작성자  	<input type="text" width="100px" value="관리자"  name="writer" readonly="readonly"><br/>
					제목 		<input type="text" width="300px" value="" required="required" name="title"><br/>
					내용 		<textarea rows="16" cols="100" required="required" name="content"></textarea><br/>
					<input type="submit" value="글 작성">
				</form>
			</div>
		</div>
<!-- rightDiv-->
		<%@include file="/WEB-INF/jsp/template_right.jsp" %>
	</div>
</body>
</html>