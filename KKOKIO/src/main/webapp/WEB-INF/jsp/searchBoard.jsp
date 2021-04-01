<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <% response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>조회</title>
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
.ui-jqgrid .ui-state-highlight {
 			background: #FAED7D; 
 }
 
#passDate{
	height: 25px;
}

#illyung{
	height: 25px;
	width: 50px;
	text-align: center;
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
$(function() {
// 	alert(${searchResult});
	<%
		String searchResult=(String)request.getAttribute("searchResult");
		System.out.println("searchResult: "+searchResult);
		if(searchResult==null){
 	%>
 		System.out.println("searchResult: null");
		document.getElementById('passDate').valueAsDate = new Date();
	<%}else{
		System.out.println("searchResult: "+searchResult);
  	%>
	makeGrid(<%=searchResult%>);
	<%}%>	
// 	var year="2000";
// 	var month="02";
// 	document.getElementById('passDate').valueAsDate = new Date(year,month);
// 	alert(${modelPassYM});
// 	setPassDate(${modelPassYM});
// 	makeGrid(${modelPassYM});

});

function setPassDate(passYM){
// 	alert(typeof passYM);
	var year=passYM.toString();
	year=year.substring(0,4)
// 	alert(year);
	var month=passYM.toString();
	month=month.substring(4,6);
// 	alert(month);
	
	document.getElementById('passDate').valueAsDate = new Date(year,month);
}


function searchPass(){
// 	var passYM = $("#passDate").val();
// 	var passYM = passYM.replace("-","");
// 	alert(passYM);
// 	location.href='searchBoard.do?passYM='+passYM;

	var passYM = $("#passDate").val();
// 	alert(passYM);
	if(passYM==null||passYM==""){
		alert("검색일자를 선택해주세요");
		$("#passDate").focus();
		return;
	}else if($("input[name=isAll]:checked").val()=="N"){ //일령 - 라디오버튼 선택시 체크
		if($("#illyung").val()<=0){
			alert("0 이상의 일령을 선택해주세요.");
			return;
		}	
	}else if($("input[name=dong]:checked").val()==undefined){
		alert("동을 선택해주세요");
		return;
	}
	
	var form=document.getElementById("frm");
	form.action="./searchIlji.do";
	form.method="post";
	form.submit();
}

function makeGrid(json){
	
    $("#list").jqGrid({
    	data: json,
    	datatype: "local",
		jsonReader : { 
			page: "page", 
			total: "total", 
			root: "user", 
			records: function(obj){return obj.length;},
			repeatitems: false, 
			id: "passcode"
		},
        colNames:['SEQ','파스CODE','일령','동별구분','폐사수','중량(g)','약품','날씨상태','비고','사육장온도','최고온도','최저온도','작성시간'
        	],
        //컬럼모델
        colModel:[
        	
//         	recordilji_seq, passcode, illyung, hightemp, lowtemp, recorddate, medicine, weathercon, etc, deathcount, weight, buildtemp, distinctdong
        	
            {name:'recordilji_seq', align:'center' ,width:60, key:true, hidden:true, editable:true, editoptions: { readonly: "readonly" }},
            {name:'passcode', align:'center' ,width:160, key:true, editable:true, editoptions: { readonly: "readonly" }},
            {name:'illyung',width:50, align:'center', key:true, editable:true},
            {name:'distinctdong',align:'right',width:80, editable:true},    
            {name:'deathcount',align:'right', width:60, editable:true},    
            {name:'weight', align:'right', width:70, editable:true},    
            {name:'medicine', width:62, editable:true},
            {name:'weathercon', width:80, editable:true},
            {name:'etc', width:62, editable:true},
            {name:'buildtemp', align:'right', width:100, editable:true},    
            {name:'hightemp', align:'right', width:100, editable:true},
            {name:'lowtemp', align:'right', width:100, editable:true},
            {name:'recorddate', align:'center', width:100, editable:true},
        ],
        //그리드타이틀
        caption: "일지 리스트",
        // 레코드가 없을때 보여줄 문구
        emptyrecords : "데이터가 없습니다.",
        //연번
        rownumbers:true,
        //정렬기준
        sortname:'recordilji_seq',
        sortorder:"desc",
        //
        height:300,
        width: 730,
        
	    rowNum:10,
	    rowList:[10,15,20],
         pager:'#pager',
//        pager:'#paginate',
        loadonce: true,
        loadComplete : function(data){  
            // 페이지 셋팅
 
				// 그리드 데이터 총 갯수
		        var allRowsInGrid = jQuery('#list').jqGrid('getGridParam','records');
             
				// 처음 currentPage는 널값으로 세팅 (=1)
				initPage("list",allRowsInGrid,"");
         },
         
         //수정, 삭제시 이동할 url
//          editurl : './editPass.do'
        
    });
    
    // jqGrid navGrid 설정
    $('#list').jqGrid('navGrid', '#pager',
              {
               edit : false,   	// 수정
               add : false,  	// 추가
               del : false,  	// 삭제
               view: true, 		// 상세조회
               refresh: true,	// 갱신
               search : false  	//검색 아이콘
               
              },
              	// edit 작업 설정
              { // 수정

              },{//추가
            	  
              },{//삭제
              }
    
              
             );
}

function allchk(bool){
	$("input[name=dong]").each(function(){
		$(this).prop("checked",bool);
	});
}


//radio 버튼 선택에 따른 일령 숫자 선택 disable 활성화, 비활성화
function activateIllyung(){
// 	alert("selectIllyung");
	document.getElementById("illyung").disabled = false;
}
function disableIllyung(){
	document.getElementById("illyung").disabled = true;
}
</script>
<body>
	<div id="container">
	<%@include file="/WEB-INF/jsp/template_v3.jsp" %>
<!-- middleDiv-->
		<div id="middleDiv">
			<div id="title">일지 조회</div>
			<div id="searchBox">
				<form id="frm">
					 파스 : <input id="passDate" type="month" name="passDate">
					<input type="radio" value="Y" name="isAll" checked="checked" onclick="disableIllyung()"> 전체
					 <input type="radio" value="N" name="isAll" onclick="activateIllyung()"> 일령 : 
					 		<input id="illyung" name="illyung" type="number" value="1" disabled="disabled">
					 <br>
				 	 동 선택 : 
				 	전체 <input type="checkbox" name="allDong" onclick="allchk(this.checked)" checked="checked">
				 	<c:forEach begin="1" end="${memid.DONGNUMBER}" step="1" var="i">			 	
				 		${i}
				 		<input type="checkbox" name="dong" value="${i}" checked="checked">
				 	</c:forEach>
				 	
				 	<input type="submit" onclick="searchPass()" value="검색"/>
<!-- 				 	<button onclick="searchPass()">검색</button> -->
				 </form>
			</div>
			<div id="jqgridDiv">
				<table id="list"></table>
				<div id="pager"></div>
		<!--  새롭게 구현할 커스텀 페이징 태그 -->
		<!-- <div id="paginate"></div> -->
			</div>
		</div>
<!-- rightDiv-->		
	<%@include file="/WEB-INF/jsp/template_right.jsp" %>

	</div>
</body>
</html>