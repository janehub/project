<%@page import="java.util.Map"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>통계</title>
<!-- 폰트 -- 나중에 다운받아서 사용하기 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<!-- jqGrid CSS 파일 -->
<link rel="stylesheet" type="text/css" media="screen" href="./jqgridui/jquery-ui.css">
<link rel="stylesheet" type="text/css" media="screen" href="./jqgrid/css/ui.jqgrid.css">
<!-- 아코디언 메뉴 -->
<link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="./css/mainPage_v3.css">

</head>
<script type="text/javascript" src="./js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<!-- jqGrid JavaScript 파일 -->
<script type="text/javascript" src="./jqgrid/src/i18n/grid.locale-kr.js"></script>
<script type="text/javascript" src="./jqgridui/jquery-ui.js"></script>
<script type="text/javascript" src="./jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="./js/jqGridPaging.js"></script>
<!-- 아코디언 메뉴 -->
<script type="text/javascript" src="./js/accordionMenu_v3.js"></script>
<!-- 날씨 정보 -->
<script type="text/javascript" src="./js/weather.js"></script>
<!-- GoogleChart -->
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<%
	Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
%>

<script type="text/javascript">
		//Load Charts and the corechart package.
		google.charts.load('current', {'packages':['corechart']});
		
		// Draw the pie chart for Chart when Charts is loaded.
		google.charts.setOnLoadCallback(deathCount);

		
// Callback that draws the column chart for DeathCount
function deathCount() {
    //동 갯수 -세션에서 가져오기
    var dongnumber=0;
	<%
	for(int i=0;i<Integer.parseInt(smap.get("DONGNUMBER"));i++){
		System.out.println(i);
	%>
		dongnumber++;
		
	<%
	}
	%>		
		  // Create the data table for DeathCount
		  
		  $.ajax({
			  url:"./getDeathCountStatic.do",
// 			  dataType:"json",
			  async:true,
			  success: function(msg){
// 				  alert(dongnumber);
// 					alert(msg);
					//JSON으로 만들기 
					  var json = JSON.parse(msg);
					
					if(json.length<=0){
						alert("통계값이 없습니다.");
						return;
					}
// 					alert(Number(json[json.length-2].illyung)); //:가져온 값 중 최대 일령
					
//			 		  var totalArr=new Array();	//[[],[],[]]
					//2차원 배열 [[일령,동별 폐사수,동별 폐사수,......,알령별 동전체 폐사수],[]]
				      var totalArr=Array(Number(json[json.length-2].illyung)).fill(null).map(() => Array());	//[[],[],[]] 50 -> 일령 수 만큼
				  														//Number(json[json.length-2].illyung) :가져온 값 중 최대 일령
					  
				  	//초기화
				  	for(var i=0;i<Number(json[json.length-2].illyung);i++){
				  		for(var j=0;j<dongnumber+2;j++){
				  			totalArr[i][j]=0;
				  		}
				  	}
				  														
// 					  var html="";
// 					  alert(json.length);
					  for(var i=0;i<json.length;i++){
// 			  		  	alert(JSON.stringify(json[i]));
// 						html+=json[i].illyung +" : "+json[i].distinctdong+" : "+json[i].deathcount+"<br>";
					  	if(Number(json[i].illyung)>0){				//총 합계가 아닌 것들
					  		totalArr[Number(json[i].illyung)-1][0]=Number(json[i].illyung);	// 2차원 배열안 1차원 배열의 첫 원소는 일령
					  		if(Number(json[i].distinctdong)>0){		//동별 폐사수
// 					  			html+=json[i].illyung +" : "+json[i].distinctdong+" : "+json[i].deathcount+"<br>";
					  			totalArr[Number(json[i].illyung)-1][Number(json[i].distinctdong)]=Number(json[i].deathcount);
					  		}else{	//동합계
					  			totalArr[Number(json[i].illyung)-1][dongnumber+1]=Number(json[i].deathcount);
					  		}
				  		}
					  }
						console.log(totalArr);
// 					  $("#json_result").html(totalArr);
						var data = new google.visualization.DataTable();
						  data.addColumn('number', '일령');
						  
						  for(var i=0;i<dongnumber;i++){
							  data.addColumn('number', (i+1)+'동 폐사수');
						  }
						  data.addColumn('number', '전체 폐사수');
						  
//				 		  var arr=[
//				 		        [1, 10,  5],  [2, 23, 12],  [3, 17, 13],  [4, 18, 14],  [5, 9, 15],
//				 		        [6, 11, 24],  [7, 27, 5],  [8, 33, 6],  [9, 40, 5],  [10, 32, 14], [11, 35, 10],
//				 		        [12, 30, 2], [13, 40, 12], [14, 42, 7], [15, 47, 8], [16, 44, 14], [17, 48, 1]
//				 		  ]
						  
//				 		  console.log(arr);
						  
						  
						  data.addRows(totalArr);
						
						  // Set options for Sarah's pie chart.
						      var options = {
								  title: '동별 폐사수',
								  fontSize:'12',
								  fontName:'sans serif',
								  width: 600,
								  height: 300,
						        hAxis: { // Y축
						          title: '일령'
						          },
						        vAxis: { // X축
						          title: '폐사수'
						        }
						      };
						
						  // Instantiate and draw the chart for Sarah's pizza.
						      var chart = new google.visualization.ColumnChart(document.getElementById('DeathCount_Chart'));

						      chart.draw(data, options);
			  
						      
						      var pie_options = {'title':'PIE TEST TITLE',
				                       'width':400,
				                       'height':300};

			  			}
			  
		  });
} 
		  
 		  

</script>


<body>

	<div id="container">
	<%@include file="/WEB-INF/jsp/template_v3.jsp" %>
<!-- middleDiv-->
		<div id="middleDiv">

			<div id="title">통계
			</div>
			<div id="passinfo">

			</div>
			<div>
				
			</div>
			
			<div>
			
			 <table class="columns">
			      <tr>
			        <td><div id="DeathCount_Chart" style="border: 1px solid #ccc"></div></td>
			       </tr>
			 </table>
			
			
			</div>
			
			<div id="json_result"></div>
		</div>
<!-- rightDiv-->
		<%@include file="/WEB-INF/jsp/template_right.jsp" %>

	</div>
</html>