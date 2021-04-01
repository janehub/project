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
<title>엑셀파일 업로드/다운로드</title>
<!-- 폰트 -- 나중에 다운받아서 사용하기 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="./css/mainPage.css">
<!-- jqGrid CSS 파일 -->
<link rel="stylesheet" type="text/css" media="screen" href="./jqgridui/jquery-ui.css">
<link rel="stylesheet" type="text/css" media="screen" href="./jqgrid/css/ui.jqgrid.css">
<!-- 아코디언 메뉴 -->
<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="./css/mainPage_v3.css">
<style type="text/css">
#resultPass p{
	font-size: 14px;
}

#filename{
	display: inline-block;
}

#fileUploadDiv{
/* 	border: 1px solid red; */
	text-align: center;
    margin-top: 5px;
}

#sendJson{
	position: absolute;
	left: 780px;
}

#uploadResult{
    background: rgba(238,238,154,0.6);
    border-radius: 10px;
    width: 320px;
    height: 25px;
    display: inline-block;
    padding-top: 2px;
}

#excelTable01, #excelTable02, #excelTable03{
	margin: 0px auto;
}

#excelTable01 th, #excelTable02 th, #excelTable03 th{
	background: #638270;
}

#excelTablesDiv{
/* 	border: 1px solid blue; */
	margin: 10px 0;
}

#excelTablesDiv th,#excelTablesDiv tr,#excelTablesDiv td{
	text-align: center;
	height: 30px;
}

.exT1C1{
	width: 360px;
}

.exT1C2{
	width: 360px;
}

.exT2C1{
	width: 240px;
}

.exT2C2{
	width: 240px;
}

.exT2C3{
	width: 240px;
}

.exT3C1{
	width: 240px;
}

.exT3C2{
	width: 240px;
}

.exT3C3{
	width: 240px;
}

#jqgridDiv {
 	padding-left: 8px;
 }
</style>
</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<!-- jqGrid JavaScript 파일 -->
<script type="text/javascript" src="./jqgrid/src/i18n/grid.locale-kr.js"></script>
<script type="text/javascript" src="./jqgridui/jquery-ui.js"></script>
<script type="text/javascript" src="./jqgrid/js/jquery.jqGrid.min.js"></script>
<!-- excelAPI -->
<script type="text/javascript" src="./js/excel/FileSaver.min.js"></script>
<script type="text/javascript" src="./js/excel/xlsx.full.min.js"></script>
<script type="text/javascript" src="./js/excel/excelAPI.js"></script>
<!-- 아코디언 메뉴 -->
<script type="text/javascript" src="./js/accordionMenu_v3.js"></script>
<!-- 날씨 정보 -->
<script type="text/javascript" src="./js/weather.js"></script>
<body>
	<div id="container">
	<%@include file="/WEB-INF/jsp/template_v3.jsp" %>
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title">
				<button onclick="excelUploadPage()">엑셀파일 업로드</button>
				<button onclick="excelDownloadPage()">엑셀파일 다운로드</button>
				
				<button onclick="downloadExcelTemplate()">엑셀서식 다운로드</button>
			</div>
			<div id="excelUploadForm">
<!-- 				<form action="./fileUpload.do" method="post" enctype="multipart/form-data"> -->
				<div id="fileUploadDiv">
					<form id="uploadFrm"  enctype="multipart/form-data">
						<input multiple="multiple" type="file" name="filename" id="filename"/>
						<input type="button" onclick="fileUpload()" value="업로드">
					</form>
					<div id="uploadResult"></div>
					<button id="sendJson" onclick="sendJson()">DB에 저장하기</button>
				</div>
				<div id="resultPass"></div>
				<div id="jqgridDiv">
					<table id="list"></table>
					<div id="pager"></div>
				</div>
				
<!-- 				<button id="sendJson" onclick="sendJson()">DB에 저장하기</button> -->
				
			</div>
			<div id="excelDownloadForm">
				<div id="passlist"></div>
				<button onclick="download()">다운로드</button>
				<div id="jsonfromDB"></div>
			</div>
		</div>

<!-- rightDiv-->
		<%@include file="/WEB-INF/jsp/template_right.jsp" %>
	</div>
</html>