<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KKOKIO | 아이디 찾기</title>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&amp;subset=korean" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="./css/sweetalert.css?ver=1.2">
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/bootstrap-theme.min.css">
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="./js/sweetalert.min.js?ver=1"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<style type="text/css">
/* 폰트 */
/* 	*{ */
/* 		font-size: 20px; */
/* 	} */
	html{
	height: 300px;
	}
	body{
	font-family: 'Noto Sans KR', sans-serif;
	width: 500px;
	height: 300px;
	}
	p{
		font-size: 20px;
		font-weight: bold;
		margin: 0px;
		padding: 0px;
	}
	span#spanname{
	margin-top: 20px;
	}
	span{
	width: 80px;
	margin: 10px 20px;
	font-size: 15px;
	font-weight: bold;
	}
	div#frm{
	height: 300px;
	}
	div#info{
		width: 350px;
		height: 150px;
		margin: auto;
 		margin-top: 30px;
		background: #F1EBE6;
	}
	input#name,#email,#phone{
	width: 200px;
	height: 30px;
	font-size: 10px;
	}
	input#fid{
	width: 100px;
	border: #DB8E29;
	height: 30px;
	background-color:#DB8E29; 
	color: white;
	font-weight: bold;
	}
	input#closeid{
	width: 100px;
	border: #DB8E29;
	height: 30px;
	background-color: gray; 
	color: white;
	font-weight: bold;
	}
	div#divfindid{
 	margin: 15px 140px; 
	}
</style>

</head>
<body>
<!-- 		<p>아이디찾기</p> -->
		<form action="./findUserId.do" method="POST" id="frm">
			<div id="info">
				<span id="spanname" style="display:inline-block;">이름</span><input type="text" id="name" name="name" placeholder="이름을 입력해주세요" required="required"><br/>
				<span style="display:inline-block;">핸드폰번호</span><input type="text" id="phone" name="phone" placeholder="핸드폰번호를 입력해주세요" required="required"><br/>
				<span style="display:inline-block;">이메일</span><input type="text" id="email" name="email" placeholder="이메일을 입력해주세요" required="required"><br/>
<!-- 				<input class="btn btn-sm btn-primary btn-center" type="button" value="돌아가기" onclick="javascript:history.back(-1);"/> -->
			</div>
			<div id="divfindid">
				<input type="submit" id="fid" value="아이디 찾기"/> 
				<input type="button" id="closeid" value="취소" onclick="self.close()"/>
				</div>
		</form>
</body>
</html>