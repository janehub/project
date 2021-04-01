<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/sweetalert.css?ver=1.2">
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/bootstrap-theme.min.css">
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="./js/sweetalert.min.js?ver=1"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript">
	function goback() {
		window.history.back();
	}
	
</script>
<style type="text/css">
/* 폰트 */
	*{
		font-size: 20px;
	}

	p{
		font-size: 20px;
	}
	
	a{
		font-size: 20px;
	}

/* 앵커 */	
	a{
	text-decoration: none;
	color: black;
	}
	
/* 틀*/
	#container {
		width: 1200px;
		height: 700px;
		margin: 0px auto;
		padding: 20px;
	}

	#info {
		display: inline-block;
		width: 50%;
		margin-left: 350px;
		height: 650px;
	}
	
	h1, h2, p {
		text-align: center;
	}
	
	button {
		float: right;
	}
</style>
</head>
<body>
	<div id="container">
			<div id="info">
				<h1>꼬끼오 육계 관리 프로그램</h1>
				<h2>비밀번호 찾기</h2>
				
				<p>안녕하세요. KKOKIO 육계 관리 프로그램입니다. <br/>비밀번호를 찾으시려면은 서비스 센터(전화번호 010-2087-6804)로 전화주시기 바랍니다.</p>
					<button onclick="goback()">뒤로가기</button>
			</div>
	</div>
</body>
</html>