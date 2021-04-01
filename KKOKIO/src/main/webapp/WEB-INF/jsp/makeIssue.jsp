<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.next.kko.dtos.IssueDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KKOKIO | 이슈작성</title>
<!-- 폰트 -- 나중에 다운받아서 사용하기 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<!-- swal알림. -->
<link rel="stylesheet" type="text/css" href="./css/sweetalert.css?ver=1.2">
<!-- 달력ui 파일 -->
<link rel="stylesheet" type="text/css" media="screen" href="./jqgridui/jquery-ui.css">
<!-- 아코디언 메뉴 -->
<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<!-- mainPage CSS -->
<link rel="stylesheet" type="text/css" href="./css/mainPage_v3.css">
<style type="text/css">
p input#bluecol {
	color: white;
	border: #50B0FF;
	background: #50B0FF;
	cursor: pointer;
	height: 50px;
	width: 150px;
	margin-left: 10px;
}
p input#graycol {
	color: white;
	border: #B8B8B8;
	background: #B8B8B8;
	cursor: pointer;
	height: 50px;
	width: 150px;
	margin-left: 200px;
}
</style>
</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/sweetalert.min.js?ver=1"></script>
<!-- 달력 ui파일 -->
<script type="text/javascript" src="./jqgridui/jquery-ui.js"></script>
<!-- 아코디언 메뉴 -->
<script type="text/javascript" src="./js/accordionMenu_v3.js"></script>
<script type="text/javascript">
	
	function insertissue() {
		var frm = document.forms[0];
		var vals = [frm.illyung.value,frm.issuedetail.value,frm.actiondetail.value,frm.issueetc.value,frm.actionresult.value];
		if(vals[0]==null|| vals[0]=="" || vals[0]==undefined){
			swal("정보 부족", "일령을 적어주세요");
		}else if(vals[1]==null|| vals[1]=="" || vals[1]==undefined){
			swal("정보 부족", "이슈내용을 적어주세요");
// 		}else if(vals[2]==null|| vals[2]=="" || vals[2]==undefined){
// 			swal("정보 부족", "조치사항을 적어주세요")
// 		}else if(vals[3]==null|| vals[3]=="" || vals[3]==undefined){
// 			swal("정보 부족", "비고를 적어주세요");			
		}else{
		frm.method = "post";
		frm.action ="./insertIssue.do";
		frm.submit();
		}
	}
</script>
<body>
<div id="container">
	   <%@include file="/WEB-INF/jsp/template_v3.jsp" %>
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title">이슈사항</div>
<div>
<form action="#" method="post">
	<hr>
	<table>
	<tr><td width="5%" align="center">*</td><td width="20%">일령</td><td><input type="number" placeholder="일령" value="" name="illyung" style="width: 268px"></td></tr>
	<tr height="7"><td colspan="3"><hr></td></tr>      
	<tr><td width="5%" align="center">*</td><td width="20%">이슈내용</td><td><input type="text" placeholder="이슈내용" value="" name="issuedetail" size="30px"></td></tr>
	<tr height="7"><td colspan="3"><hr></td></tr>      
	<tr><td width="5%" align="center">*</td><td width="20%">조치사항</td><td><input type="text" placeholder="조치사항" value="" name="actiondetail" size="30px"></td></tr>
	<tr height="7"><td colspan="3"><hr></td></tr>      
	<tr><td width="5%" align="center">*</td><td width="20%">비고</td><td><input type="text" placeholder="비고" value="" name="issueetc" size="30px"></td></tr>
	<tr height="7"><td colspan="3"><hr></td></tr>      
	<tr><td width="5%" align="center">*</td><td width="20%">조치결과</td>
	<td><select name="actionresult" style="width: 265px"> 
	<option value="Y">Y / 조치완료</option>
	<option value="N" selected="selected">N / 조치안됨</option>
	</select></td></tr>
	<tr>
	<td><input type="hidden" value="" name="issuecode"></td>
	<td><input type="hidden" value="0" name="recordilji_seq"></td>
	<td><input type="hidden" value="${passcode}" name="passcode"></td>
	<td><input type="hidden" value="" name="contentcode"></td>
	<td><input type="hidden" value="0" name="actioncode"> </td>
	</tr>
	<tr height="7"><td colspan="3"><hr></td></tr>
	</table>
	<p>	<input type="reset" id="graycol" value="초기화">
			<input type="button" id="bluecol" onclick="insertissue()" value="이슈 추가하기"></p>
</form>
</div>
			</div>
<!-- rightDiv-->
		<%@include file="/WEB-INF/jsp/template_right.jsp" %>
	</div>
</body>
</html>