<%@page import="com.next.kko.dtos.RecordIljiDto"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>농장일지_일지작성</title>
<!-- 폰트 -- 나중에 다운받아서 사용하기 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<!-- jqGrid CSS 파일 -->
<link rel="stylesheet" type="text/css" media="screen" href="./jqgridui/jquery-ui.css">
<link rel="stylesheet" type="text/css" media="screen" href="./jqgrid/css/ui.jqgrid.css">
<!-- 아코디언 메뉴 -->
<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!-- BootStrap CSS 파일 -->
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/bootstrap-theme.min.css">
<!-- mainPage_new CSS -->
<link rel="stylesheet" type="text/css" href="./css/mainPage_v3.css">

<style type="text/css">
	#iljiTabs{
		width: 650px;
		height: 200px;
	}
	
	 .inputdong{
		width: 50px;
	}
	
	#tabContent{
		width: 680px;
		height: 150px;
		border: 1px solid gray;
	}
	
	#chkBtnArea{
		width: 680px;
		height: 35px;
		border-top: 1px solid gray;
		/* padding-left: 610px; */
		padding-top: 5px;
		margin-bottom: 10px;
	}
	
	.chkTitle{
		font-weight: bolder;
		margin-left: 10px;
		margin-right: 510px;
	}
	
	#showtable{
		width: 690px;
		height: 250px;
		/* border: 1px solid gray; */
	}
	
	#showtableRE{
		width: 690px;
		height: 250px;
	}
	
	
	
</style>

</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<!-- BootStrap JavaScript 파일-->
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<!-- jqGrid JavaScript 파일 -->
<script type="text/javascript" src="./jqgrid/src/i18n/grid.locale-kr.js"></script>
<script type="text/javascript" src="./jqgridui/jquery-ui.js"></script>
<script type="text/javascript" src="./jqgrid/js/jquery.jqGrid.min.js"></script>
<!-- 아코디언 메뉴 -->
<script type="text/javascript" src="./js/accordionMenu_v3.js"></script>
<!-- 날씨 정보 -->
<script type="text/javascript" src="./js/weather.js"></script>

	<%
	
		session.getAttribute("memid");
		
		List<RecordIljiDto> lists = (List<RecordIljiDto>)request.getAttribute("lists");
		List<RecordIljiDto> wlists = (List<RecordIljiDto>)request.getAttribute("wlists");
		List<RecordIljiDto> chklists = (List<RecordIljiDto>)request.getAttribute("chklists");
		
	%>
<script type="text/javascript">


	$(function () {

/* 		$("#deathTab").click(function () {
			alert("폐사수 확인");
			
		var deathCnts = new Array();
		var dongnum = document.getElementsByName("deathcount").length;
			alert("동수"+dongnum);
		
 		for(var i=0; i <dongnum; i++ ){
			deathCnts[i] = document.getElementsByName("deathcount")[i].value;
 		}
 		
 		for(var i=0; i <dongnum; i++ ){
  			var death = deathCnts[i]
  				alert("폐사수 확인"+death);
  				
  				if(death != null || death != "" || death != undefined){
  				alert("이미 작성하셨습니다.일지관리에서 수정해주세요");
  				}
 	 	}
 	 	
		});
		 */
		
		
/* 		$("#weightTab").click(function () {
			alert("중량 확인");
			
			var dongWeight = new Array();
		var dongnum = document.getElementsByName("weight").length;
			alert("동수"+dongnum);
		
 		for(var i=0; i <dongnum; i++ ){
 			dongWeight[i] = document.getElementsByName("weight")[i].value;
 		}
 		
 		for(var i=0; i <dongnum; i++ ){
  			var weight = dongWeight[i];
  				alert("중량 확인"+weight);
  				
  				if(weight != null || weight != "" || weight != undefined){
  				alert("이미 작성하셨습니다. 일지관리에서 수정해주세요");
  				}
 	 	}
			
		}); */
		
	});


	function newDeathCnt() {
		// alert("확인");
		
		var deathCnts = new Array();
		var dongnum = document.getElementsByName("deathcount").length;
			//alert("동수"+dongnum);
		
 		for(var i=0; i <dongnum; i++ ){
			deathCnts[i] = document.getElementsByName("deathcount")[i].value;
 		}
 		
 		for(var i=0; i <dongnum; i++ ){
  			var death = document.getElementsByName("deathcount")[i].value;
  				//alert("폐사수 확인"+death);
 	 	}
 	 	
 	 	/* 폐사수 입력시 ajax를 통해 처리*/
	  	$.ajax({
	  		type:"get",
	  		url: "./dongDeathCntInsert.do",
	  		data: "dCount="+deathCnts,
	  		async:true,
	  		success: function (result) {
				alert(result);
				if(result == true){
					alert("입력이 완료되었습니다");
					// 작성한 내용 반영하여 하단에 입력내용 표출
					refresh();
				}
			}
	  	});
	} //newDeathCnt() 끝
	
	function newWeight() {
		// alert("확인");
		
		var dongWeight = new Array();
		var dongnum = document.getElementsByName("weight").length;
			//alert("동수"+dongnum);
		
 		for(var i=0; i <dongnum; i++ ){
 			dongWeight[i] = document.getElementsByName("weight")[i].value;
 		}
 		
 		for(var i=0; i <dongnum; i++ ){
  			var weight = dongWeight[i];
  				//alert("중량 확인"+weight);
 	 	}

		/* 중량 입력시 ajax를 통해 처리*/ 		
 	  	$.ajax({
	  		type:"get",
	  		url: "./dongWeightInsert.do",
	  		data: "dWeight="+dongWeight,
	  		async:true,
	  		success: function (result) {
				alert(result);
				if(result == true){
					alert("입력이 완료되었습니다");
					// 작성한 내용 반영하여 하단에 입력내용 표출
					refresh();
				} 
			}
	  	});
 		
		
	}// newWeight() 끝
	
	function newBuildTemp() {
		// alert("확인");
		
		var dongBuildTemp = new Array();
		var dongnum = document.getElementsByName("buildtemp").length;
			//alert("동수"+dongnum);
		
 		for(var i=0; i <dongnum; i++ ){
 			dongBuildTemp[i] = document.getElementsByName("buildtemp")[i].value;
 		}
 		
 		for(var i=0; i <dongnum; i++ ){
			var buildTemp = dongBuildTemp[i];
 				//alert("사육장 온도 확인"+buildTemp);
 	 	}

 		/* 중량 입력시 ajax를 통해 처리*/
  	  	$.ajax({
	  		type:"get",
	  		url: "./dongBuildTempInsert.do",
	  		data: "dBuildTemp="+dongBuildTemp,
	  		async:true,
	  		success: function (result) {
				alert(result);
				if(result == true){
					alert("입력이 완료되었습니다");
					// 작성한 내용 반영하여 하단에 입력내용 표출
					refresh();					
				} 
			}
	  	});
 		
		 
	}// newBuildTemp() 끝
	
	function refresh() {
//		alert("확인");
		$("#showtable").remove();
		
		$.ajax({
			type:"get",
			url: "./illyungNewData.do",
	  		dataType: "json",
	  		async:true,
	  		success: function (json) {
//				alert(json);
//				alert(JSON.stringify(json));
//				alert(typeof json.newdata);

			//JSON obj로 만들기
				jsonObj = JSON.parse(json.newdata);
//				alert(jsonObj[0].distinctdong);
			
			for(var i=0; i<jsonObj.length; i++){
			
				if(jsonObj[i].medicine == undefined)
					jsonObj[i].medicine = "";
	
				if(jsonObj[i].etc == undefined)
					jsonObj[i].etc = "";
			
			}

			/* Table 만들기 */
			var refresh_html="";
			
			refresh_html += "		<table style='width: 650px;'>";
			refresh_html += "		<tr>";
			refresh_html += "			<th width='70px'>#</th>";
			refresh_html += "			<th width='70px'>폐사수</th>";
			refresh_html += "			<th width='70px'>중량</th>";
			refresh_html += "			<th width='70px'>온도</th>";
			refresh_html += "			<th width='70px'>약품</th>";
			refresh_html += "			<th width='70px'>비고</th>";
			refresh_html += "			<th width='70px'>동구분</th>";
			refresh_html += "		</tr>";
			refresh_html += "	</table>";
	
 			refresh_html += "	<div style='width: 670px; height: 200px; overflow: auto;'>";
			refresh_html += "	<table style='width: 650px;' class='table table-striped'>";
			

			for(var i=0; i<jsonObj.length; i++){
				
			refresh_html += "		<tr>";
			refresh_html += "	<th  width='70px'>"+(jsonObj.length-i)+"</th>";
			refresh_html += "	<td  width='70px'>"+jsonObj[i].deathcount+"</td>";
			refresh_html += "	<td  width='70px'>"+jsonObj[i].weight+"</td>";
			refresh_html += "	<td  width='70px'>"+jsonObj[i].buildtemp+"</td>";
			refresh_html += "	<td  width='70px'>"+jsonObj[i].medicine+"</td>";
			refresh_html += "	<td  width='70px'>"+jsonObj[i].etc+"</td>";
			refresh_html += "	<td  width='70px'>"+jsonObj[i].distinctdong+"</td>";
			refresh_html += "		</tr>";
				
			}
		
			refresh_html += "	</table>";
			refresh_html += "	</div>"; 
			
			$("#showtableRE").html(refresh_html); 

			}
			
		});
		
		
	}//refresh() 끝
	
</script>

<body>
	<div id="container">
	<%@include file="/WEB-INF/jsp/template_v3.jsp" %>
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title">일지작성<br></div>
			<div id="passInfo">기본파스정보<br>
			<span>파스코드: ${memid.passcode}</span> <br>
			<span>동개수: ${memid.DONGNUMBER}</span> 
			</div>
			
			<div id="iljiTabs">
			
				<ul class="nav nav-tabs">
					<li class="nav-tabs active" id="deathTab">
						<a href="#tabs-1" data-toggle="tab">폐사수</a>
					</li>
					<li id="weightTab">
						<a href="#tabs-2" data-toggle="tab">중량</a>
					</li>
					<li>
						<a href="#tabs-3" data-toggle="tab">사육장 온도</a>
					</li>
					<li>
						<a href="#tabs-4" data-toggle="tab">약품</a>
					</li>
					<li>
						<a href="#tabs-5" data-toggle="tab">비고</a>
					</li>
				</ul>

				
			<div class="tab-content" id="tabContent">
				<!-- 폐사수 -->
				<div class="tab-pane" id="tabs-1">
					<form id="deathCntFrm" style="height: 220px; ">
					<c:forEach var = "i" begin = "1" end = "${memid.DONGNUMBER}" step="1" varStatus="vs">
						<c:choose>
							<c:when test="${i eq lists[i-1].distinctdong}">
								${vs.count}동 <input type="text" value="${lists[i-1].deathcount}" readonly="readonly" name="deathcount" class="inputdong"/> (마리) &nbsp;
							</c:when>
							<c:otherwise>
								${vs.count}동 <input type="text" name="deathcount" class="inputdong"/>  (마리) &nbsp;
							</c:otherwise>
						</c:choose> 
					</c:forEach>
					<p></p>
					<input type="button" value="폐사수 입력" onclick="newDeathCnt()"/>
					</form>
				</div>
				
				<!-- 중량 -->
				<div class="tab-pane" id="tabs-2">
					<form id="weightFrm">
					<c:forEach var = "i" begin = "1" end = "${memid.DONGNUMBER}" step="1" varStatus="vs">
						<c:choose>
							<c:when test="${i eq wlists[i-1].distinctdong}" >
								${vs.count}동 <input type="text" value="${wlists[i-1].weight}" readonly="readonly" name="weight" class="inputdong"/> (g) &nbsp;
							</c:when>
							<c:otherwise>
								${vs.count}동 <input type="text" name="weight" class="inputdong"/> (g) &nbsp;
							</c:otherwise>
						</c:choose> 
					</c:forEach>
					<p></p>
					<input type="button" value="중량 입력" onclick="newWeight()"/>
					</form>
				</div>
				
				<!-- 사육장 온도 -->
				<div class="tab-pane" id="tabs-3">
					<form id="buildTempFrm">
						<c:forEach var = "i" begin = "1" end = "${memid.DONGNUMBER}" step="1" varStatus="vs"> 
								${vs.count}동 <input type="text" name="buildtemp" class="inputdong"/>
						</c:forEach>
						<p></p>
						<input type="button" value="사육장 온도 입력" onclick="newBuildTemp()"/>
					</form>
				</div>
				
				<!-- 약품 -->
				<div class="tab-pane" id="tabs-4">
					<form id="mediFrm" action="./dongMediInput.do" method="post">
						<c:forEach var = "i" begin = "1" end = "${memid.DONGNUMBER}" step="1" varStatus="vs">
								${vs.count}동 <input type="checkbox" value="${vs.count}" name="dong"/>
						</c:forEach>
						<p></p>
						<textarea rows="4" cols="90" name="medi"></textarea>
						<input type="submit" value="약품 사용정보 작성" />
					</form>
				</div>
				
				<!-- 비고 -->
				<div class="tab-pane" id="tabs-5">
					<form id="mediFrm" action="./dongEtcInput.do" method="post">
						<c:forEach var = "i" begin = "1" end = "${memid.DONGNUMBER}" step="1" varStatus="vs">
								${vs.count}동 <input type="checkbox" value="${vs.count}" name="dong"/>
						</c:forEach>
						<p></p>
						<textarea rows="4" cols="90" name="etc"></textarea>
						<input type="submit" value="비고 작성" />
					</form>
				</div>

				
			</div><!-- tab-content 영역 (끝) -->
			</div><!-- iljiTab 전체 영역 (끝) -->

			<div id="chkBtnArea"><span class="chkTitle">[작성내용 확인]</span><button type="button" class="btn btn-success btn-sm" onclick="refresh()">새로고침</button></div>

			<div id="showtable">
					<table style="width: 650px;">
						<thead>
						<tr>
							<th width="70px">#</th>
							<th width="70px">폐사수</th>
							<th width="70px">중량</th>
							<th width="70px">온도</th>
							<th width="70px">약품</th>
							<th width="70px">비고</th>
							<th width="70px">동구분</th>
						</tr>
						</thead>
					</table>
					
					<div style="width: 670px; height: 200px; overflow: auto;">
					<table style="width: 650px; border: 1px;" class="table table-striped">
						<c:forEach var="i" begin="1" end="${chklists.size()}" step="1" varStatus="vs">
						<tr>
							<th  width="70px" >${chklists.size()-vs.index}</th>
							<td  width="70px">${chklists[i-1].deathcount}</td>
							<td  width="70px">${chklists[i-1].weight}</td>
							<td  width="70px">${chklists[i-1].buildtemp}</td>
							<td  width="70px">${chklists[i-1].medicine}</td>
							<td  width="70px">${chklists[i-1].etc}</td>
							<td  width="70px">${chklists[i-1].distinctdong}</td>
						</tr>
						</c:forEach>
					</table>
					</div>
				
				
					</div><!-- showtable(끝) -->
					
					<div id="showtableRE"></div>
					
					
				</div>
<!-- rightDiv-->
		<%@include file="/WEB-INF/jsp/template_right.jsp" %>

	</div>
</html>