<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>넘어오나 보자</title>
<script type="text/javascript" src="./js/sweetalert.min.js?ver=1"></script>
<script type="text/javascript"
	src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/issueList.js"></script>
<link rel="stylesheet" type="text/css"
	href="./css/sweetalert.css?ver=1.2">
<link rel="stylesheet" type="text/css"
	href="./css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">

<style type="text/css">
	td{
	text-align:center; 
	}
	input{
		width: auto;
	}
</style>
<script type="text/javascript">
	function modify() {
		
	}

</script>
</head>
<body>
<div>
<form action="./modify.do" method="post">
<table border="1px solid black">
			<tr>
				<td>일령</td>
				<td>이슈내용</td>
				<td>비고</td>
			</tr>		
			<tr>
				<td>${dto.illyung}일령</td>
				<td><input type="text" placeholder='${dto.issuedetail}' name="issuedetail" value=""></td>
				<td><input type="text" placeholder='${dto.issueetc}' value="" name="issueetc"></td>
			</tr>
		</table>
		<input type="hidden" value='${dto.issuecode}' name="issuecode">
		
		<input type="submit" value="수정하기">
		<input type="button" value="뒤로가기" onclick="javascript:history.back(-1)">
		</form>
		</div>
</body>
</html>