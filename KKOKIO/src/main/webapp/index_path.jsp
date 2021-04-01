<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인이 성공 후, 파스 선택</h1>
	<a href="./indexToMain.do">파스 선택 후, 다음 화면으로 이동</a>
	
	<h1>로그인 성공 후,파스 생성</h1>
<!--로그인성공 후 이동할 때 처리해야함  -->
	<a href="./makePassPage.do">파스 생성 화면으로 이동</a>
	
	
	<h1>jqGrid</h1>
	<a href="./ProductList.do">jqGrid ProductList.jsp</a>
</body>
</html>