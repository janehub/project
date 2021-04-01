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
<title>메인 페이지</title>
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
<style type="text/css">
/* #passModal table{ */
/* 	border: 1px solid black; */
/* } */
/* #passModal tr,th,td{ */
/* 	border: 1px solid black; */
/* } */

#passModal tr,#passModal th,#passModal td{
	text-align: center;
	height: 30px;
}

#passModal td{
	cursor: pointer;
}

#passModal tr:nth-child(odd) {
    background: #bcc98e;
}

#passModal th{
	background: #638270;
	cursor: default;
}

#passModal tr:nth-last-child(1){
	background: #638270;
	font-weight: bold;
}

#createPassBtnDiv{
	text-align: right;
	margin-bottom: 5px;
	margin-right: 8px;
}

.myContent{
	width: 440px;
}

.col1{
	width: 150px;
}

.col2{
	width: 250px;
}

#passDiv{
	width: 290px;
	margin: 0px auto;
}

#table01, #table02,#table03,#table04,#table05,#table06{
	margin: 0px auto;
}

#table06 tr:nth-child(odd) {
    background: #bcc98e;
}

#table01 th,#table02 th,#table03 th,#table04 th,#table05 th{
	background: #638270;
}

.mainTables th,.mainTables tr,.mainTables td{
	text-align: center;
	height: 30px;
}

#innerToptables{
	margin-bottom: 40px;
}

#innerBottomTop{
 	margin: 0px auto;
/* 	overflow-y: scroll; */
/* 	overflow-x: hidden; */
}

#table05{
	width: 720px;
}

#scrollTable{
	height: 160px;
	width: 720px;
 	margin: 0px auto; 
	overflow-y: scroll;
	overflow-x: hidden;
}
/* ::-webkit-scrollbar{width: 16px;} */
/* ::-webkit-scrollbar-track {background-color:#f1f1f1;} */
/* ::-webkit-scrollbar-thumb {background-color:#638270;border-radius: 5px;} */
/* ::-webkit-scrollbar-thumb:hover {background: #555;} */
/* ::-webkit-scrollbar-button:start:decrement,::-webkit-scrollbar-button:end:increment { */
/* width:16px;height:30px;background:#bcc98e;} */


#t1c1{
	width: 360px;
}

#t1c2{
	width: 360px;
}

#t5c1, .t6c1{
	width: 100px;
}

#t5c2, .t6c2{
	width: 140px;
}

#t5c3, #t5c4, .t6c3, .t6c4{
	width: 100px;
}

#t5c5, #t5c6, .t6c5, .t6c6{
	width: 140px;
}

.col3-1{
	width: 240px;
}

#passTitle{
	font-size: 16px;
}

#progress{
	width: 550px;
	float: right;
}

.spacing{
	opacity: 0;
}

#t5space{
	width: 10px;
}

#passDiv .ui-jqgrid{
	cursor: pointer;
}
</style>
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

<script type="text/javascript">
$(function(){
	<%
	Map<String, String> smap = (Map<String, String>)session.getAttribute("memid");
	String s_passcode=smap.get("passcode");
	if(s_passcode==null){
		System.out.println("널");
	%>
		getAllPassList();
	<%
	}else{
// 		System.out.println(s_passcode);
	%>	
// 		$("#title").text('${memid.passcode}');
		choosePass('${memid.passcode}');
	<%
	}
	%>
	
	
});

function choosePass(passcode){
// 	alert(passcode);
	
	$("#mainPasscode").text("[ "+passcode+" ]");
	
	$.ajax({
		url:"./setPasscode.do",
		type:"get",
		data:{"passcode":passcode},
		dataType:"json",
		success:function(json){
// 			alert(JSON.stringify(json));
// 			alert(typeof json.ilji);
//  		$("#passinfo").html(json.ilji);
		//JSON obj로 만들기 
		  	jsonObj = JSON.parse(json.pass);
		  	jsonOb2 = JSON.parse(json.ilji);
// 		  	alert(jsonOb2[0].distinctdong);
// 			alert(jsonOb2.length);
			
//프로그래스 바
			var startpass=new Date(jsonObj.startpass);
			var startTime=startpass.getTime();
// 			alert(startTime);
			
			var endpass=new Date(jsonObj.endpass);
			var endTime=endpass.getTime();
// 			alert(endTime);
			
			var currentDate = new Date();
			var currentTime = currentDate.getTime();
			
			var progressLength = endTime-startTime;
			var drawingLength = currentTime-startTime;
			var percentage=Math.round((drawingLength/progressLength)*100);
			
			var progress="";
			if(percentage>100){
				progress+="<div id='progress' class='progress'>";
				progress+="<div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100' style='width:100%'>";
				progress+="100%";
				progress+="</div></div>";				
			}else{
				progress+="<div id='progress' class='progress'>";
				progress+="<div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='"+percentage+"' aria-valuemin='0' aria-valuemax='100' style='width:"+percentage+"%'>";
				progress+=percentage+"%";
				progress+="</div></div>";
			}
			progress+="<div id='passTitle'>파스진행률</div>";
			
			$("#title").html(progress);
			
			
			var total_deathcount=0;
			
			var total_weight=0;
			var valid_weight_Cnt=0;

// 			var html_ilji="";
			for(var i=0;i<jsonOb2.length;i++){
// 				html_ilji+="동: "+jsonOb2[i].distinctdong+"<br>";
// 				html_ilji+="폐사수: "+jsonOb2[i].deathcount+"<br>";
// 				html_ilji+="중량: "+jsonOb2[i].weight+"<br>";
				
				total_deathcount+=Number(jsonOb2[i].deathcount);
				
				if(Number(jsonOb2[i].weight)>0){
					total_weight+=Number(jsonOb2[i].weight);
					valid_weight_Cnt+=1;
				}
			}
			
// 			alert(total_weight);
// 			alert(valid_weight_Cnt);
			
			var html="";
			html+="<div id='innerToptables'>";
			html+="<table id='table01' class='mainTables'><tr><th id='t1c1'>파스코드</th><th id='t1c2'>입추날짜</th></tr>";
			html+="<tr><td>"+jsonObj.passcode+"</td>";
			html+="<td>"+jsonObj.startpass.substring(0,10)+"</td></tr>";
			html+="</table>";
			
			html+="<table id='table02' class='mainTables'><tr><th class='col3-1'>입추수수</th><th class='col3-1'>덤</th><th class='col3-1'>덤을 포함한 총 마리수</th></tr>";
			html+="<tr><td>"+jsonObj.incount+"</td>";
			//덤, 덤 %
			html+="<td>"+jsonObj.indum+"("+((jsonObj.indum/jsonObj.incount)*100).toFixed(2)+"&#37;)"+"</td><td>";
			html+=(jsonObj.incount+jsonObj.indum)+"</td></tr>";
			html+="</table>";
			
			html+="<table id='table03' class='mainTables'><tr><th class='col3-1'>품종</th><th class='col3-1'>부화장</th><th class='col3-1'>활발도</th></tr>";
			html+="<tr><td>";
			if(jsonObj.intype==undefined){
				html+="-";
			}else{
				html+=jsonObj.intype;
			}
			html+="</td><td>";
			if(jsonObj.inbuhwa==undefined){
				html+="-";
			}else{
				html+=jsonObj.inbuhwa;
			}
			html+="</td><td>";
			if(jsonObj.inactivity==undefined){
				html+="-";
			}else{
				html+=jsonObj.inactivity;
			}
			html+="</td></tr>";
			html+="</table>";
	
			html+="<table id='table04' class='mainTables'><tr><th class='col3-1'>폐사수</th><th class='col3-1'>폐사율</th><th class='col3-1'>평균 중량</th></tr>"
			html+="<tr><td>"+total_deathcount+"</td>";
			//폐사율 = 폐사수/(입추수수+덤)*100 -> 소숫점 2자리까지
			html+="<td>"+(total_deathcount/(jsonObj.incount+jsonObj.indum)*100).toFixed(2)+"&#37;"+"</td>";
			//평균 중량
			html+="<td>";
			if(valid_weight_Cnt==0){	//divide 0 방지
				html+="-";
			}else{
				html+=(total_weight/valid_weight_Cnt).toFixed(2);
			}
			html+="g"+"</td></tr>";
			html+="</table>"
			html+="</div>";
			
			html+="<div id='innerBottomTop'>";
			html+="<table id='table05' class='mainTables'><tr><th id='t5c1'>동</th><th id='t5c2'>마리수</th><th id='t5c3'>폐사수</th><th id='t5c4'>폐사율</th><th id='t5c5'>입추수수 퍼센트</th><th id='t5c6'>평균 중량</th><th id='t5space'></th></tr></table>";
			html+="</div>";
			html+="<div id='scrollTable'>";
			html+="<table id='table06' class='mainTables'>"
			var dongchicknumArray = jsonObj.dongchicknum.split('/');
			
			var j=0;
			<%
			for(int i=0;i<Integer.parseInt(smap.get("DONGNUMBER"));i++){
				System.out.println(i);
			%>
				if(dongchicknumArray[j]==undefined||Number(dongchicknumArray[j])==0){
					html+="<tr><td class='t6c1'>"+(j+1)+"</td>"+"<td class='t6c2'>-</td>";
					html+="<td class='t6c3'>-</td>";
					//폐사율 = 폐사수/(마리수)*100 -> 소숫점 2자리까지
					html+="<td class='t6c4'>-</td>";
					//입추수수 퍼센트
					html+="<td class='t6c5'>-</td>";
					//중량
					html+="<td class='t6c6'>-</td>";
					html+="</tr>";
				}else{
					html+="<tr><td class='t6c1'>"+(j+1)+"</td>"+"<td class='t6c2'>"+dongchicknumArray[j]+"</td>";
					html+="<td class='t6c3'>"+jsonOb2[j].deathcount+"</td>";
					//폐사율 = 폐사수/(마리수)*100 -> 소숫점 2자리까지
					html+="<td class='t6c4'>"+((jsonOb2[j].deathcount/dongchicknumArray[j]*100).toFixed(2))+"&#37;"+"</td>";
					//입추수수 퍼센트
					html+="<td class='t6c5'>"+((((dongchicknumArray[j]-jsonOb2[j].deathcount)/dongchicknumArray[j])*100).toFixed(2))+"&#37;"+"</td>";
					//중량
					html+="<td class='t6c6'>"+(jsonOb2[j].weight).toFixed(2)+" g"+"</td>";
					html+="</tr>";
				}
				j++;
			<%
			}
			%>
			
			html+="</tr>";
			html+="</table>";
			html+="</div>";
			
			$("#passinfo").html(html);
		}
	});
}

function formatDate(date) {
    var d = new Date(date);
    var month = '' + (d.getMonth() + 1);
    var day = '' + d.getDate();
    var year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return year+"년 "+month+"월 "+day+"일";
}

//파스종료일을 현재 날짜와 비교해서 더 크면 진행중 표시
function isOnGoing(endpass){
	var d = new Date(endpass);
	var now = new Date();
	if(d.getTime()>=now.getTime()){
// 		alert(endpass);
		return "<span class='spacing'>-------</span>진행중<span class='spacing'>-------</span>";
	}else{
		return formatDate(endpass);
	}
}

function getAllPassList(){
$("#passModal").modal({backdrop: 'static', keyboard: false});
	
	$.ajax({
		url:"./passList.do",
		type:"get",
		dataType:"json",
		success:function(list){
//  		alert(JSON.stringify(list));
//  		$("#frmChoosePass").text(JSON.stringify(list[0]));
			
			var html="";
			html+="<table>";
			html+="<tr><th class='col1'>파스코드</th><th class='col2'>파스시작일<span class='spacing'>-----</span>~<span class='spacing'>-----</span>파스종료일</th></tr>";
			for(var i=0 ;i<list.length && i<5;i++){
				html+="<tr data-dismiss='modal' onclick=\"choosePass('"+list[i].passcode+"')\">";
				html+="<td>"+list[i].passcode+"</td>";
				html+="<td>"+formatDate(list[i].startpass);
				html+=" ~ ";
				html+=isOnGoing(list[i].endpass);
				html+="</td>";
				html+="</tr>";
			}
			html+="<tr><td data-dismiss='modal' colspan='2'>------------------------------------더보기------------------------------------</td></tr>";
			html+="</table>";
			
 			$("#frmChoosePass").html(html);
			
 			makejqGrid(list);
		}
	});
}


function makejqGrid(list){
// 	alert("makejqGrid");
	$("#allPassList").html(JSON.stringify(list));
	
	//jqGrid껍데기 생성
    $("#list").jqGrid({
    	data: list,
    	datatype: "local",
        colNames:['파스CODE','파스시작일','파스종료일','선택'
        	],
        //컬럼모델
        colModel:[
            {name:'passcode', align:'center' ,width:120, key:true},
            {name:'startpass',width:65},
            {name:'endpass', width:65},
            {name:'select', align:'center', width:65}
//             {formatter: function (cellValue, option) {
//     			return '<input type="radio" name="radio_' + option.gid + '"  />';
// 			}, width:65, align:'center'}
        ],
        //그리드타이틀
        caption: "파스 리스트",
        //연번
        rownumbers:true,
        //정렬기준
        sortname:'startpass',
        //
        height:250,
// 	    rowNum:5,
// 	    rowList:[5,10,15],
//         pager:'#pager',
        sortorder:"desc",
        
        onCellSelect:function(rowid,index,contents,event)
        {
        	var cm=$(this).jqGrid('getGridParam','colModel');
        	if(cm[index].name=='select'){
        		var passcode=$(this).jqGrid('getCell',rowid,'passcode');
        		var isc=confirm(passcode+"로 선택하시겠습니까?");
        		if(isc){
        			choosePass(passcode);
        		}
        	}
        },
        
    });
	
    var gridIDs =  $("#list").jqGrid("getDataIDs");	//그리드의 id값들을 구해옴.
//     alert(gridIDs[5]);
    for (var key in gridIDs) //자 순서대로 key 그리드 id값을 넘겨주고
    {
//     	alert(gridIDs[key]);
    	$("#list").setCell(gridIDs[key],'select', '<button>선택</button>');	//특정 셀값을 변경하여줌.	
    }	

}


</script>
<body>
	<div id="container">
	<%@include file="/WEB-INF/jsp/template_v3.jsp" %>
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title">
			</div>
			<div id="passinfo">
				<div id="passDiv">
					--파스를 선택해 주세요--
					<table id="list"></table>
					<div id="pager"></div>
				</div>
			</div>
			
			<!-- 파스 선택 모달 폼 -->
			<div class="modal fade" id="passModal" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content myContent">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">파스 선택</h4>
						</div>
						<div class="modal-body">
							<div id='createPassBtnDiv'>
								<button onclick='location.href="./makePassPage.do"'>파스 생성하기</button>
							</div>
							<form action="#" method="post" class="form-margin40" role="form" id="frmChoosePass"></form>
						</div>
					</div>
				</div>
			</div>
		</div>
<!-- rightDiv-->
		<%@include file="/WEB-INF/jsp/template_right.jsp" %>

	</div>
</html>