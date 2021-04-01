<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KKOKIO 회원가입</title>
</head>
<body>

<div id="container">
		<div id="title">
			<img id="titleImg" alt="title" src="./image/signuptitle.gif"> <br/>
		</div>

		<!-- 이번에는 onsubmit -->
		<input type="hidden" id="chkval" value="0">

		<form action="./signUp.do" method="post" id="frm" onsubmit="return check()">
			<div id="info">
				<div id="leftinfo">정보입력</div>

				<div id="centerinfo">
					<input type="text" id="name" name="name" placeholder="이름" required="required"> <br/> 
					<input type="text" id="id" name="id" placeholder="아이디" required="required"><br/> 
						
					<span id="result"></span><br/> 
					<input type="password" id="pw"name="pw" placeholder="비밀번호" required="required">

					<div>
						<input id="passOk" type="password" required="required"
							placeholder="비밀번호 확인" />
					</div>

				</div>
				<div id="rightinfo"></div>
			</div> 		<!-- id="info" -->
			
			<div id="line"></div>
			
			
			<div id="bottom"> 		<!-- 약관사항 넣을 것 -->
				<br>
				*만 14세 미만은 법정대리인의 동의 후에 회원 서비스 이용이 가능합니다.<br>
				*동의하지 안으셔도 가입은 됩니다.?<br>
				<strong id="bottomstrong">
					약관과 개인정보 수집에 대해서 확인하였으며 이에 동의하십니까?
				</strong><br>
				
				<div id="button">
					<input class="btn btn-success" type="submit" value="동의하고 회원가입"/>
					<input class="btn btn-sm btn-primary btn-center" type="button" value="돌아가기" onclick="javascript:history.back(-1);"/>
				</div>
			
			</div>
			
		</form>
	</div>


</body>
</html>